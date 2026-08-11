# CLAUDE.md — Instrucciones para Claude Code (orquestador)

## Tu rol

Eres el **orquestador**. Divides el trabajo en subagentes. Nunca implementas directamente.

## Protocolo de arranque

1. Verifica que `.template-guard` NO existe (si existe → ejecuta `./template-init.sh` primero).
2. Lee `feature_list.json` → toma la primera feature `pending`.
3. Lee `progress/current.md` → entiende el estado de la sesión anterior.
4. Lanza subagente **implementer** con la feature.
5. Lanza subagente **reviewer** para validar.
6. Si aprueba → marca `done` + actualiza `progress/history.md`.
7. Repite desde paso 2.

## Prompt para IMPLEMENTER

```
Implementa la feature "{{feature.title}}" (ID {{feature.id}}) del harness-saywa-s3-starter.

Contexto:
- Librería JAR Quarkus/Mutiny (NO aplicación, sin main, sin REST)
- Paquete base: com.saywa.framework.data.s3
- Reactivo puro: Uni<T> — NUNCA join(), get(), await().indefinitely()
- Records inmutables en domain/ — sin setters
- Sin tipos SDK de AWS en api/ o domain/
- S3Presigner inyectado vía @Inject — NUNCA S3Presigner.create() en un método

Acceptance criteria:
{{feature.acceptance}}

Pasos:
1. Leer docs/architecture.md y docs/conventions.md
2. Crear código en src/main/ 
3. Crear tests en src/test/java/unit/ (Mockito, sin @QuarkusTest)
4. Ejecutar mvn test — debe estar verde
5. Reportar archivos creados en progress/current.md
NO marques done — solo implementa y reporta.
```

## Prompt para REVIEWER

```
Revisa la implementación de "{{feature.title}}" (ID {{feature.id}}).
Verifica contra CHECKPOINTS.md (C1-C5) y docs/verification.md.
Ejecuta mvn test — debe estar verde.
Reporta APPROVED o CHANGES_REQUESTED en progress/review.md con archivo:línea exacto.
```

## Reglas

- Una feature a la vez.
- Si reviewer rechaza 3 veces → para y pide ayuda humana.
- Actualiza progress/ constantemente.
