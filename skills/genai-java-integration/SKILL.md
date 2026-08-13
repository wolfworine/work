---
name: genai-java-integration
description: Use whenever building AI/LLM capabilities INTO a Java application as a product feature — wiring a chat model (OpenAI/Anthropic/Mistral/local ONNX) into a Java service, implementing RAG (retrieval-augmented generation), calculating or storing vector embeddings, choosing a vector store (pgvector/Chroma/Milvus/Redis), adding conversational memory to a chatbot, implementing LLM tool/function calling, building an MCP (Model Context Protocol) client or server in Java/Quarkus, or reviewing security/compliance for a service that calls an LLM with user-supplied input. Not for using an AI coding assistant to write code — that's `ai-assisted-software-development`.
---

# GenAI Integration in Java

Operational reference for embedding LLM/GenAI capabilities inside a Java backend as a product feature, distilled from *Applied AI for Enterprise Java Development* (Alex Soto Bueno, Markus Eisele, Natale Vinto — O'Reilly). Covers LangChain4j, vector embeddings/RAG, and the Model Context Protocol (MCP).

**Not the same skill as [`ai-assisted-software-development`](../ai-assisted-software-development/SKILL.md).** That skill is about using an AI coding assistant (Claude Code, Copilot) to *write your code faster*. This skill is about *your application itself* calling an LLM at runtime to serve a feature to its own users (a support chatbot, a fraud-detection endpoint, a doc-search assistant). Different audience, different runtime concerns (latency, per-request cost, prompt injection from *your users*, not from a developer's prompt).

**Cross-links:** for Quarkus-specific plumbing (CDI, `application.properties`, `@RegisterAiService` wiring, testing) see [`quarkus-cloud-native-java`](../quarkus-cloud-native-java/SKILL.md); the source book shares an author (Markus Eisele) with [`enterprise-java-design-patterns`](../enterprise-java-design-patterns/SKILL.md) — the resilience patterns there (circuit breaker, bulkhead, timeout) apply directly to calls made to an external LLM API, which is just another flaky remote dependency.

## Mental model: three layers of integration

```
Layer 1: Raw inference API      Layer 2: Framework (LangChain4j)     Layer 3: Protocol (MCP)
────────────────────────       ─────────────────────────────       ─────────────────────
Plain REST/gRPC client to  →   Unified API across 15+ model    →   Standardized, session-based
a model endpoint you or        providers + embeddings + vector     way for a model to discover
someone else exposed.          stores. Adds memory, prompt         and call tools/resources —
No memory, no RAG, no          templates, structured output        model-agnostic, not tied to
tool-calling — you build       parsing, tool/function calling,     one framework's tool-calling
everything by hand.            and RAG orchestration.              implementation.
```

**Decision table — which layer do you need:**

| Situation | Use |
|---|---|
| One stateless model call, you control both sides (e.g., an internal fraud-scoring model) | Layer 1 — plain REST/gRPC client, no framework needed |
| Conversational feature (needs memory across turns), or you need to swap model providers without rewriting call sites | Layer 2 — LangChain4j `ChatModel` |
| The model needs to answer from information it wasn't trained on (your docs, your DB) | Layer 2 + RAG (data augmentation via a `ContentRetriever`) |
| The model needs to call functions/tools defined elsewhere (query a DB, hit an API), especially tools built or reused across multiple clients/frameworks | Layer 3 — MCP client (consume existing MCP servers: GitHub, Slack, Postgres, etc.) |
| You want to expose your own business logic as reusable tools for *any* MCP-compatible model/agent, not just your own app | Layer 3 — build your own MCP server |

## Accessing a raw inference API from Java

Before reaching for a framework, know how to call a model endpoint directly — useful for simple, stateless models (a fraud classifier, a sentiment scorer) that don't need memory or RAG.

**Quarkus — MicroProfile REST Client** (type-safe, declarative):
```java
@Path("/inference")
@RegisterRestClient(configKey = "fraud-model")
public interface FraudDetectionService {
    @POST
    FraudResponse isFraud(TransactionDetails transactionDetails);
}
```
```properties
quarkus.rest-client.fraud-model.url=http://localhost:8080
```
```java
@RestClient
FraudDetectionService fraudDetectionService;
```

**Quarkus — gRPC client** (when the model is exposed over gRPC, common for high-throughput inference): register `quarkus-grpc`, drop the `.proto` file under `src/main/proto`, and inject with `@GrpcClient`. Quarkus generates the stub at build time via `quarkus-maven-plugin`. Returns are Mutiny `Uni<T>` (reactive by default):
```java
@GrpcClient("fraud")
FraudDetection fraud;

@GET
@Path("/grpc/{txId}")
public Uni<FraudResponse> detectFraudGrpcClient(@PathParam("txId") String txId) {
    Uni<FraudRes> predicted = fraud.predict(txDetails);
    return predicted.onItem().transform(fr -> new FraudResponse(fr.getTxId(), fr.getFraud()));
}
```

**Where plain clients stop being enough:** no memory (each call is stateless — a chatbot needs conversation context), no RAG support, no agent/tool-calling support, and you re-implement the specific wire format per model/provider. That's what LangChain4j solves.

## LangChain4j: the framework layer

**Unified API** — one `ChatModel` interface regardless of provider (OpenAI, Anthropic, Google Gemini, Mistral, Ollama, local ONNX models via Jlama/DJL). Conceptually: LangChain4j is to LLM providers what JPA is to databases — swap the implementation, keep the call sites.
```java
ChatModel model = OpenAiChatModel.builder()
    .apiKey(OPENAI_API_KEY)
    .modelName(GPT_4_O_MINI)
    .build();
String output = model.chat("Who is Lionel Messi");
```

**Prompt templates** — declarative (`@SystemMessage`/`@UserMessage` on an AI-service interface) or programmatic (`Prompt` class) with `{{placeholder}}` interpolation. In Quarkus, a prompt can also be a Qute template expression. Structured prompts bind placeholders to a Java object's fields via `@StructuredPrompt`, avoiding hand-built string concatenation.

**Structured output parsing** — LangChain4j can unmarshal a model's response directly into a Java type instead of leaving you to parse raw text: `String`, `AiMessage`, any POJO (JSON → object mapping), `Enum`/`List<Enum>` (e.g., a sentiment classification maps straight to an enum constant), `boolean`, numeric types, date/time types, `List<String>`, `Map<K,V>`, or `Result<T>` (wraps the value plus `TokenUsage`, `FinishReason`, and RAG `Content` metadata). This is the difference between "the model said something JSON-shaped" and "I have a type-safe object" — always prefer typed extraction over manual string parsing of LLM output.

**Memory (conversation state)** — LLM calls are stateless by default; memory means re-sending prior turns with each request. `ChatMemory` automates this:

| Eviction strategy | Class | Behavior |
|---|---|---|
| Sliding window | `MessageWindowChatMemory` | Keep the N most recent *messages*, drop older ones |
| Token sliding window | `TokenWindowChatMemory` | Keep the N most recent *tokens* (messages are indivisible — a whole message is evicted once its tokens push past the limit) |

```java
ChatMemory chatMemory = MessageWindowChatMemory.builder().maxMessages(20).build();
chatMemory.add(userMessage1);
Response<AiMessage> response1 = model.chat(chatMemory.messages());
chatMemory.add(response1.content());
```
Default storage is in-process; swap in `ChatMemoryStore` (e.g. Redis-backed) for persistence across restarts/instances. **A `ChatMemory` instance is per-user** — sharing one across users leaks one user's conversation into another's context.

**Data augmentation (RAG basics)** — retrieve relevant external content and inject it into the prompt before sending to the model, via `ContentRetriever` → `DefaultRetrievalAugmentor`. Sources aren't limited to vector stores: a `ContentRetriever` can call a REST API (weather, pricing), hit a database, or run a web search — vector similarity search is just the most common case (see next section).

**Tools / function calling** — the model can request that *your code* execute a function (send an email, run a query) and return the result; the model never executes anything itself, it only emits a structured call, your service runs it. This is the pre-MCP, framework-native way to give a model capabilities beyond text generation.

## Vector embeddings and RAG

An embedding is a numeric vector capturing semantic meaning — semantically similar text produces vectors close together under cosine similarity (`"cat"` and `"kitten"` are close; `"cat"` and `"car"` are far, despite similar spelling).

**Three ways to calculate embeddings in Java:**

| Approach | When | Example |
|---|---|---|
| DJL (Deep Java Library) | Full control over model runtime (PyTorch/ONNX/TensorFlow engines), non-LangChain4j pipelines | `Criteria` → `ZooModel` → `Predictor<String, float[]>`; **`Predictor` is not thread-safe — scope it per-request** (`@RequestScoped` in Quarkus, `@Disposes` to close it) |
| LangChain4j in-process (ONNX) | Simplest option — no network call, model bundled as a Maven dependency (e.g. `langchain4j-embeddings-all-minilm-l6-v2`) | `EmbeddingModel embeddingModel = new AllMiniLmL6V2EmbeddingModel();` |
| LangChain4j remote model | Cloud-hosted embedding model, or inference needs to run outside the app for scaling | `MistralAiEmbeddingModel.builder().apiKey(...).modelName("mistral-embed").build()` |

**Vector store choice** (LangChain4j integrates 20+): pgvector when you're already on PostgreSQL and don't want a new piece of infrastructure; Chroma for lightweight local dev/prototyping; Milvus for high-performance distributed search at scale; Redis when you need ultra-fast in-memory search with persistence. Don't introduce a dedicated vector database until pgvector's performance genuinely isn't enough — it's one less moving part in production.

**RAG pipeline, concretely:** ingest documents → chunk → embed → store in a vector store → at query time, embed the user's query → similarity search for the top-k chunks → inject those chunks into the prompt as context → send to the model. Advanced variants worth knowing exist (not detailed here): query routing (send different query types to different retrievers) and re-ranking (a second, more precise pass over the top-k results before injecting them).

## Model Context Protocol (MCP)

MCP is a standardized, session-based protocol for how an application exposes context/tools to a model — positioned to replace ad hoc, framework-specific function/tool calling. Instead of one-shot function calls packed into a prompt, the model operates within a defined context, expresses goals as intents, and interacts with typed resources through a consistent protocol — model-agnostic and interoperable across runtimes/frameworks, not locked to one framework's tool-calling implementation.

**MCP client in Quarkus** — consume an existing MCP server (e.g. the official Postgres MCP server for read-only DB access) declaratively, no client-side Java code required for the wiring itself:
```properties
quarkus.langchain4j.mcp.postgres.transport-type=stdio
quarkus.langchain4j.mcp.postgres.command=npm,exec,@modelcontextprotocol/server-postgres,postgresql://postgres:postgres@localhost:5432/contacts
```
```java
@RegisterAiService
@SystemMessage("You have tools to interact with a database...")
public interface ChatBot {
    @McpToolBox("postgres")   // omit the name to use ALL configured MCP servers
    PersonsDto chat(@UserMessage String message);
}
```
Requires `io.quarkiverse.langchain4j:quarkus-langchain4j-mcp`. Alternatively, configure MCP servers via a Claude-Desktop-format JSON file (`quarkus.langchain4j.mcp.config-file=mcp-config.json`) — stdio transport only.

**MCP server in Quarkus** — expose your own business logic as MCP tools other agents/clients can call, via `io.quarkiverse.mcp:quarkus-mcp-server-stdio` and two annotations:
```java
@Singleton
public class ZodiacMcpServer {
    @Tool(description = "Gets the Chinese zodiac animal for a given date (yyyy-MM-dd)")
    public ToolResponse calculateZodiac(
        @ToolArg(name = "localDate", description = "Date in yyyy-MM-dd format") String localDate
    ) { ... }
}
```
Worth building your own MCP server (instead of just tool-calling inside one app) when the capability should be reusable across multiple agents/clients/frameworks, not just your one LangChain4j service.

## Security, compliance, and production concerns

An AI-infused endpoint is still a production service — everything from `enterprise-java-design-patterns` about resilient calls to flaky remote dependencies (timeouts, circuit breakers, retries with backoff) applies to LLM API calls, which are slower and less predictable in latency than a typical internal service call.

**API-layer responsibilities specific to model access** (source book's list — implement these at the gateway/API layer in front of the model, not per-call in business code):

| Concern | Mechanism |
|---|---|
| AuthN/AuthZ | OAuth / JWT / API keys |
| Role-based access | Different permissions for developers vs. inference consumers |
| Audit | Log every request for monitoring/compliance |
| Abuse prevention | Rate limiting |
| Scale | Load balancing across model instances |
| Cost/latency | Caching (reuse identical/similar requests instead of re-inferring) |

**Compliance, when the app touches regulated data:** encrypt models/data at rest, verify model integrity (hashing/signatures — a tampered model is a supply-chain risk like a tampered dependency), anonymize personal data used for any fine-tuning, keep audit trails of training/inference, and monitor for input drift (the input distribution shifting away from what the model was validated against).

## Pitfalls

- **Trusting structured output without validation.** LangChain4j mapping JSON → a POJO doesn't mean the *content* is correct — the model can still hallucinate a plausible-looking but wrong value into a well-typed field. Structured output solves a parsing problem, not a correctness problem.
- **Unbounded memory growth.** Every token in `ChatMemory` is resent on every subsequent call — cost and latency both scale with unbounded conversation length. Always configure an eviction strategy (`MessageWindowChatMemory`/`TokenWindowChatMemory`); never use an unbounded in-memory list for a long-lived chatbot.
- **Sharing a `ChatMemory` instance across users.** It's a per-user object; a singleton `ChatMemory` silently leaks one user's conversation history into another's requests.
- **Prompt injection via user input.** If any part of the prompt is built from end-user text (not just a developer's own prompt, the concern in `ai-assisted-software-development`), a user can embed instructions that override your system prompt ("ignore previous instructions and..."). Treat user-supplied text going into a prompt the same way you'd treat it going into a SQL query — validate/sanitize, and never let user input alone determine which tool/MCP call gets executed.
- **Blocking calls inside a reactive pipeline.** The gRPC/Mutiny examples above return `Uni<T>` — calling `.block()` on it (or any reactive chain) inside a request thread defeats the point; see [`reactive-java-webflux`](../reactive-java-webflux/SKILL.md) for why this specifically hurts under load.
- **Introducing a dedicated vector database before pgvector's limits are actually hit.** Extra infrastructure to operate, back up, and secure, for a performance ceiling most applications never approach.
- **`Predictor` (DJL) reused across concurrent requests.** It is not thread-safe — scope it per-request, not as a shared singleton.

## Checklist

- [ ] Does this really need Layer 2/3 (LangChain4j/MCP), or is a plain REST/gRPC client (Layer 1) enough for a stateless, single-purpose model call?
- [ ] If conversational: is `ChatMemory` scoped per-user, with an explicit eviction strategy?
- [ ] If RAG: is retrieval failure handled (empty/low-relevance results shouldn't silently degrade to a hallucinated answer with no caveat)?
- [ ] Is user-supplied text treated as untrusted input where it flows into a prompt or determines which tool gets called?
- [ ] Are resilience patterns (timeout, retry, circuit breaker) applied to the model API call like any other flaky remote dependency?
- [ ] Is structured LLM output validated for business correctness, not just successfully parsed?
- [ ] If exposing MCP tools: would this capability be more valuable as a reusable MCP server than as framework-internal tool-calling?

## Honesty notes on this skill's construction

Built from targeted `grep`+`Read` over the full text of *Applied AI for Enterprise Java Development* (`qrk-001`, 104,760 words) — chapters on accessing inference APIs with Java, LangChain4j, vector embeddings/stores, and the MCP sections of the "Advanced Topics" chapter, plus the security/compliance material from "The New Types of Applications." Not read in full: the book's Chapters 1, 4, 5, 6, 10 (LangGraph4j — multi-agent orchestration), and 11 (image processing) — those are real gaps if multi-agent workflows or vision become relevant; a follow-up pass should target LangGraph4j specifically since it's a distinct, non-trivial topic (agent orchestration) not covered at all here. The RAG "advanced" techniques (query routing, re-ranking) are named from the chapter's own table of contents but not expanded from source detail — flagged as named-only in the RAG section above. The resilience-pattern cross-reference to `enterprise-java-design-patterns` and the general "treat it as a flaky remote dependency" framing is standard industry practice applied to this book's content, not an extracted recommendation from the source itself.
