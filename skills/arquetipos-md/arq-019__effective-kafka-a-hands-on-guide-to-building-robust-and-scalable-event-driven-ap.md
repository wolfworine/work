---
id: arq-019
source_file: "effective-kafka-a-hands-on-guide-to-building-robust-and-scalable-event-driven-applications-with-code-examples-in-java_compress.pdf"
words: 96129
---

| Effective    | Kafka        |             |           |              |         |
| ------------ | ------------ | ----------- | --------- | ------------ | ------- |
| A Hands-On   | Guide        | to Building | Robust    | and Scalable |         |
| Event-Driven | Applications |             | with Code | Examples     | in Java |
Emil Koutanov
Thisbookisforsaleathttp://leanpub.com/effectivekafka
Thisversionwaspublishedon2021-01-05
ThisisaLeanpubbook.LeanpubempowersauthorsandpublisherswiththeLeanPublishing
process.LeanPublishingistheactofpublishinganin-progressebookusinglightweighttoolsand
manyiterationstogetreaderfeedback,pivotuntilyouhavetherightbookandbuildtractiononce
youdo.
©2019-2021EmilKoutanov

Tweet This Book!
PleasehelpEmilKoutanovbyspreadingthewordaboutthisbookonTwitter!
Thesuggestedhashtagforthisbookis#ApacheKafka.
Findoutwhatotherpeoplearesayingaboutthebookbyclickingonthislinktosearchforthis
hashtagonTwitter:
#ApacheKafka

Dedicatedtomyfamily,whohaveunrelentlesslysupportedmeasIdisappearedforcountlesshours
inmystudy,producingreamsofmildlycohesivetext.

Contents
Chapter1:EventStreamingFundamentals. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 1
Therealchallengesofdistributedsystems . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 1
Event-DrivenArchitecture . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 3
Whatiseventstreaming? . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 7
Chapter2:IntroducingApacheKafka . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 9
ThehistoryofKafka . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 9
Thepresentday . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 10
UsesofKafka . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 11
Chapter3:ArchitectureandCoreConcepts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 17
ArchitectureOverview . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 17
Totalandpartialorder . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 20
Records . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 24
Partitions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 26
Topics . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 29
Consumergroupsandloadbalancing . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 31
Freeconsumers . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 40
Summaryofcoreconcepts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 41
Chapter4:Installation . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 44
InstallingKafkaandZooKeeper . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 44
LaunchingKafkaandZooKeeper . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 48
Runninginthebackground. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 51
InstallingKafdrop . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 52
Chapter5:GettingStarted . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 56
PublishingandconsumingusingtheCLI . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 56
AbasicJavaproducerandconsumer . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 69
Chapter6:DesignConsiderations . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 79
Rolesandresponsibilities . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 79
Parallelism . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 82
Idempotenceandexactly-oncedelivery . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 87

CONTENTS
Chapter7:Serialization . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 90
Keyandvalueserializer . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 90
Keyandvaluedeserializer . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 102
Chapter8:BootstrappingandAdvertisedListeners . . . . . . . . . . . . . . . . . . . . . . . . . 124
Agentleintroductiontobootstrapping . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 124
Asimplescenario. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 127
Multiplelisteners . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 133
ListenersandtheDockerNetwork . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 138
Chapter9:BrokerConfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 142
Entitytypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 142
Dynamicupdatemodes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 142
Configurationprecedenceanddefaults. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 143
Applyingbrokerconfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145
Applyingtopicconfiguration. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 150
UsersandClients . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 152
Chapter10:ClientConfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 154
Configurationgotchas . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 154
Applyingclientconfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 156
Commonconfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 158
Producerconfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 162
Consumerconfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 172
Adminclientconfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 182
Chapter11:RobustConfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 184
Usingconstants . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 184
Type-safeconfiguration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 185
Chapter12:BatchingandCompression . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 193
ComparingdiskandnetworkI/O . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 193
Producerrecordbatching . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 193
Compression. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 195
Chapter13:ReplicationandAcknowledgements . . . . . . . . . . . . . . . . . . . . . . . . . . . 200
Replicationbasics. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 200
Leaderelection . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
Settingtheinitialreplicationfactor . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 206
Changingthereplicationfactor . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 207
Decommissioningbrokernodes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 213
Acknowledgements . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 214
Chapter14:DataRetention. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 218
Kafkastorageinternals . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 218

CONTENTS
Deletion . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 223
Compaction . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 225
Combiningcompactionwithdeletion . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 232
Chapter15:GroupMembershipandPartitionAssignment . . . . . . . . . . . . . . . . . . . . 235
Groupmembershipbasics . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Livenessandsafety. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 244
Partitionassignmentstrategy . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 259
Chapter16:Security . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 269
StateofsecurityinKafka . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 269
Targetstatesecurity . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 271
Networktrafficpolicy . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 274
Confidentiality . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 277
Authentication . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 291
Authorization . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 318
Chapter17:Quotas . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 339
Therationalebehindquotas . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 339
Typesofquotas . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 341
Subjectaffinityandprecedenceorder . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 345
Applyingquotas . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 349
Bufferingandtimeouts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 355
Sensingquotaenforcement . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 359
Tuningthedurationandnumberofsamplingwindows . . . . . . . . . . . . . . . . . . . . . 360
Chapter18:Transactions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 366
Preamble . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 366
Therationalebehindtransactions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 367
Transactionsunderthehood . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 371
Simplestreamprocessingexample . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 377
Limitations. . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 384
Aretransactionsover-hyped? . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 385

Chapter 1: Event Streaming
Fundamentals
It is amazing how the software engineering landscape has transformed over the last decade. Not
longago,applicationswerelargelymonolithicinnature,internally-layered,typicallyhostedwithin
application servers and backed by ‘big iron’ relational databases with hundreds or thousands of
interrelated tables. Distributed applications were the ‘gold standard’ by those measures — coarse-
graineddeployableunitsscatteredamongastaticclusterofapplicationservers,hostedonafleetof
virtual machines and communicating over SOAP-based APIs or message queues. Containerisation,
cloudcomputing,elasticity,ephemeralcomputing,functions-as-a-service,immutableinfrastructure
— all niche concepts that were just starting to surface, making minor, barely perceptible ripples in
anarchitecturalinstitutionthatwasotherwisewell-setinitsways.
That was then. Today, these concepts are profoundly commonplace. Engineers are often heard
interleavingseveralsuchtermsinthesamesentence;itwouldseemthattheengineeringcommunity
had miraculously stumbled upon an elixir that has all but cured us of our prior burdens — at least
when it comes to developer velocity, time-to-market, system availability, scalability, and just about
everyothermaterialconcernthathadkepttheengineeringmanagerofyoreawakeatnight.Today,
wehavemicroservicesinthecloud.Problemsolved.Nextquestion.
Except no such event actually occurred. We did not discover a solution to the problem; we merely
shifted the problem. Aspects of software development that used to be straightforward in the ‘old
world’, such as debugging, profiling, performance management, and state consistency — are now
an order of magnitude more complex. On top of this, a microservices architecture brings its own
unique woes. Services are more fluid and elastic, and tracking of their instances, their versions
anddependenciesisaHerculeanchallengethatballoonsincomplexityasthecomponentlandscape
evolves. To top this off, services will fail in isolation, further exacerbated by unreliable networks,
potentially leaving some activities in a state of partial completeness. Given a large enough system,
partsofitmaybesufferingaminoroutageatanygivenpointintime,potentiallyimpactingasubset
ofusers,quiteoftenwithouttheoperator’sawareness.
With so many ‘moving parts’, how does one stay on top of these challenges? How does one make
the engineering process sustainable? Or should we just write off the metamorphosis of the recent
decadeasafailedexperiment?
The real challenges of distributed systems
If there is one thing to be learned from the opening gambit, it is that there is no ‘silver bullet’.
Architectural paradigms are somewhat like design patterns, but broader scoped, more subjective,

Chapter1: EventStreamingFundamentals 2
and far less prescriptive. However fashionable and blogged-about these paradigms might be, they
only offer partial solutions to common problems. One must be mindful of the context at all times,
andapplythemodeljudiciously.Andcrucially,onemustunderstandthedeficienciesoftheproposed
approach, being able to reason about the implications of its adoption — both immediate and long-
term.
Theprincipalinconvenienceofadistributedsystemisthatitshiftsthecomplexityfromtheinnards
of a service implementation to the notional fabric that spans across services. Some might say, it
lifts the complexity from the micro level to the macro level. In doing so, it does not reduce the net
complexity; on the contrary, it increases the aggregate complexity of the combined solution. An
astute engineering leader is well-aware of this. The reason why a distributed architecture is often
chosen — assuming it is chosen correctly — is to enable the compartmentalisation of the problem
domain. It can, if necessary, be decomposed into smaller chunks and solved in partial isolation,
typically by different teams — then progressively integrated into a complete whole. In some cases,
this decomposition is deliberate, where teams are organised around the problem. In other, less-
than-ideal cases, the breakdown of the problem is a reflection of Conway’s Law, conforming to
organisational structures. Presumed is the role an architect, or a senior engineering figure that
orchestrates the decomposition, assuming the responsibility for ensuring the conceptual integrity
and efficacy of the overall solution. Centralised coordination may not always be present — some
organisations have opted for a more democratic style, whereby teams act in concert to decompose
theproblemorganically,withlittleoutsideinfluence.
Coupling
Whichever the style of decomposition, the notion of macro complexity cannot be escaped. Funda-
mentally, components must communicate in one manner or another, and therein lies the problem:
componentsareofteninadvertentlymadeawareofeachother.Thisiscalledcoupling—thedegreeof
interdependencebetweensoftwarecomponents.Thelowerthecoupling,thegreaterthepropensity
ofthesystemtoevolvetomeetnewrequirements,performancedemands,andoperationalchallenges.
Conversely,tightcouplingshacklesthecomponentsofthesystem,increasingtheirmutualreliance
andimpedingtheirevolution.
There are known ways for alleviating the problem of coupling, such as the use of an asynchronous
communicationstyleandmessage-orientedmiddlewaretosegregatecomponents.Thesetechniques
havebeenusedtovaryingdegreesofsuccess;therearetimeswheremessage-basedcommunication
has created a false economy — collaborating components may still be transitively dependent upon
oneanotherinspiteoftheirdesigners’besteffortstoforgeopaqueconduitsbetweenthem.
Resilience
It would be rather nice if computers never failed and networks were reliable; as it happens, reality
differs. The problem is exacerbated in a distributed context: the likelihood of any one component
experiencing an isolated failure increases with the total number of components, which carries
negativeramificationsifcomponentsareinterdependent.

Chapter1: EventStreamingFundamentals 3
Distributedsystemstypicallyrequireadifferentapproachtoresiliencecomparedtotheircentralised
counterparts. The quantity and makeup of failure scenarios is often much more daunting in dis-
tributed systems. Failures in centralised systems are mostly characterised as fail-stop scenarios —
where a process fails totally and permanently, or a network partition occurs, which separates the
entiretyofthesystemfromoneormoreclients,orthesystemfromitsdependencies.Ateitherrate,
thefailuremodesaretriviallyunderstood.Bycontrast,distributedsystemsintroducetheconceptof
partialfailures,intermittentfailures,and,inthemoreextremecases,Byzantinefailures.Thelatter
representsa special class of failureswhere processes submit incorrect or misleading information to
unsuspectingpeers.
Consistency
Ensuring state consistency in a distributed system is perhaps the most difficult aspect to get right.
Onecanthinkofadistributedsystemasavaststatemachine,withsomeelementsofitbeingupdated
independently of others. There are varying levels of consistency, and different applications may
demand specific forms of consistency to satisfy their requirements. The stronger the consistency
level, the more synchronisation is necessary to maintain it. Synchronisation is generally regarded
as a difficult problem; it is also expensive — requiring additional resources and impacting the
performanceofthesystem.
Costbeingheldaconstant,thegreatertherequirementforconsistency,thelessdistributedasystem
will be. There is also a natural counterbalance between consistency and availability, identified by
Eric Brewer in 1998. The essence of it is in the following: distributed systems must be tolerant of
network partitions, but in achieving this tolerance, they will have to either give up consistency
or availability guarantees. Note, this conjecture does not claim that a consistent system cannot
simultaneously be highly available, only that it must give up availability if a network partition
doesoccur.
Bycomparison,centralisedsystemsarenotboundbythesamelaws,astheydon’thavetocontend
with network partitions. They can also take advantage of the underlying hardware, such as CPU
cache lines and atomic operations, to ensure that individual threads within a process maintain
consistency of shared data. When they do fail, they typically fail as a unit — losing any ephemeral
stateandleavingthepersistentstateasitwasjustbeforefailure.
Event-Driven Architecture
Event-DrivenArchitecture(EDA)isaparadigmpromotingtheproduction,detection,consumption
of, and reaction to events. An event is a significant state in change, that may be of interest within
the domain where this state change occurred, or outside of that domain. Interested parties can be
notifiedofaneventbyhavingtheoriginatingdomainpublishsomecanonicaldepictionoftheevent
toawell-knownconduit—amessagebroker,aledger,orashareddatastoreofsomesort.Note,the
event itself does not travel — only its notification; however, we often metonymically refer to the
notificationoftheeventastheevent.(Whileformallyincorrect,itisconvenient.)

Chapter1: EventStreamingFundamentals 4
An event-driven system formally consists of emitters (also known as producers and agents), con-
sumers (also known as subscribers and sinks), and channels (also known as brokers). We also use
the term upstream — to refer to the elements prior to a given element in the emitter-consumer
relation,anddownstream —torefertothesubsequentelements.
An emitter of an event is not aware of any of the event’s downstream consumers. This statement
captures the essence of an event-driven architecture. An emitter does not even know whether
a consumer exists; every transmission of an event is effectively a ‘blind’ broadcast. Likewise,
consumers react to specific events without the knowledge of the particular emitter that published
the event. A consumer need not be the final destination of the event; the event notification may
be persisted or transformed by the consumer before being broadcast to the next stage in a notional
pipeline.Inotherwords,aneventmayspawnotherevents;elementsinanevent-drivenarchitecture
maycombinetherolesofemittersandconsumers,simultaneouslyactingasboth.
Eventnotificationsareimmutable.Anelementcannotmodifyanevent’srepresentationonceithas
been emitted, not even if it is the originally emitter. At most, it can emit new notifications relating
tothatevent—enriching,refining,orsupersedingtheoriginalnotification.
Coupling
Elements within EDA are exceedingly loosely coupled, to the point that they are largely unaware
ofoneanother.Emittersandconsumersareonlycoupledtotheintermediatechannels,aswellasto
therepresentationsofevents—schemas.Whilesomecouplinginvariablyremains,inpractice,EDA
offersthelowestdegreeofcouplingofanypracticalsystem.Thecollaboratingcomponentsbecome
largelyautonomous,standalonesystemsthatoperateintheirownright—eachwiththeirindividual
setofstakeholders,operationalteams,andgovernancemechanisms.
Bywayofanexample,ane-commercesystemmightemiteventsforeachproductpurchase,detailing
the time, product type, quantity, the identity of the customer, and so on. Downstream of the
emitter, two systems — a business intelligence (BI) platform and an enterprise resource planning
(ERP) platform — might react to the sales events and build their own sets of materialised views.
(In effect, view-only projections of the emitter’s state.) Each of these platforms are completely
independent systems with their own stakeholders: the BI system satisfies the business reporting
and analytics requirements for the marketing business unit, while the ERP system supports supply
chainmanagementandcapacityplanning—theremitofanentirelydifferentbusinessunit.
Toputthingsintoperspective,weshallconsiderthepotentialsolutionstothisproblemintheabsence
ofEDA.Thereareseveralwaysonecouldhaveapproachedthesolution;eachapproachcommonly
foundintheindustrytothisday:
1. Build a monolith. Conceptually, the simplest approach, requiring a system to fulfill all
requirementsandcatertoallstakeholdersasanindivisibleunit.
2. Integration. Allow the systems to invoke one another via some form of an API. Either the
e-commerceplatformcouldinvoketheBIandERPplatformsatthepointofsale,ortheBIand
ERP platforms could invoke the e-commerce platform APIs just before generating a business

Chapter1: EventStreamingFundamentals 5
report or supplier request. Some variations of this model use message queues for systems to
sendcommandsandqueriestooneanother.
3. Data decapsulation. If system integrators were cowboys, this would be their prairie. Data
decapsulation(acoinedterm,ifoneweretoask)seessystems‘reachingover’intoeachother’s
‘backyard’, so to speak, to retrieve data directly from the source (for example, from an SQL
database)—withoutaskingtheownerofthedata,andoftentimeswithouttheirawareness.
4. Shared data. Build separate applications that share the same datastore. Each application is
aware of all data, and can both read and modify any data element. Some variations of this
scheme use database-level permissions to restrict access to the data based on an application’s
role,therebybindingthescopeofeachapplication.
Once laid out, the drawbacks of each model become apparent. The first approach — the proverbial
monolith — suffers from uncontrolled complexity growth. In effect, it has to satisfy everyone and
everything. This also makes it very difficult to change. From a reliability standpoint, it is the
equivalentofputtingallofone’seggsinonebasket—ifthemonolithweretofail,itwillimpactall
stakeholderssimultaneously.
The second approach — integrate everything — is what these days is becoming more commonly
known as the ‘distributed monolith’, especially when it is being discussed in the context of mi-
croservices. While the systems (or services, as the case may be) appear to be standalone — they
mightevenbeindependentlysourcedandmaintained—theyarebynomeansautonomous,asthey
cannotchangefreelywithoutimpactingtheirpeers.
Thethirdapproach—readothers’data—isthearchitecturalequivalentofa‘getrichquickscheme’
that always ends in tears. It takes the path of least resistance, making it highly alluring. However,
the model creates the tightest possible level of coupling, making it very difficult to change the
parties down the track. It is also brittle — a minor and seemingly benign change to the internal
datarepresentationinonesystemcouldhaveacatastrophiceffectonanothersystem.
Thefinalmodel—theuseofashareddatastore—isamorecivilisedvariationofthethirdapproach.
While it may be easier to govern, especially with the aid of database-level access control — the
negativeattributesarelargelythesame.
Now imagine that the business operates multiple disparate e-commerce platforms, located in dif-
ferent geographic regions or selling different sorts of products. And to top it off, the business now
needs a separate data warehouse for long-term data collection and analysis. The addition of each
new component significantly increases the complexity of the above solutions; in other words, they
donotscale.Bycomparison,EDAscalesperfectlylinearly.Systemsareunawareofoneanotherand
react to discrete events — the origin of an event is largely circumstantial. This level of autonomy
permits the components to evolve rapidly in isolation, meeting new functional and non-functional
requirementsasnecessary.
Resilience
TheautonomycreatedbytheuseofEDAensuresthat,asawhole,thesystemislesspronetooutage
ifanyofitsindividualcomponentssufferacatastrophicfailure.Howisthisachieved?

Chapter1: EventStreamingFundamentals 6
Integrated systems, and generally, any topological arrangement that exhibits a high degree of
component coupling is prone to correlated failure — whereby the failure of one component can
take down an entire system. In a tightly coupled system, components directly rely on one another
to jointly achieve some goal. If one of these components fails, then the remaining components that
depend on it may also cease to function; at minimum, they will not be able to carry out those
operationsthatdependonthefailedcomponent.
In the case of a monolith, the failure assertion is trivial — if a fail-stop scenario occurs, the entire
processisaffected.
Under EDA, enduring a component failure implies the inability to either emit events or consume
them. In the event of emitter failure, consumers may still operate freely, albeit without a facility
for reacting to new events. Using our earlier example, if the e-commerce engine fails, none of the
downstream processes will be affected — the business can still run analytical queries and attend to
resource planning concerns. Conversely, if the ERP system fails, the business will still make sales;
however,someproductsmightnotbeplacedonback-orderintime,potentiallyleadingtolowstock
levels.Furthermore,providedtheeventchannelisdurable,thee-commerceenginewillcontinueto
publishsalesevents,whichwilleventuallybeprocessedbytheERPsystemwhenitisrestored.The
failureofaneventchannelcanbecounteredbyimplementingalocal,statefulbufferontheemitter,
sothatanybackloggedeventscanbepublishedwhenthechannelhasbeenrestored.Inotherwords,
not only is an event-driven system more resilient by retaining limited operational status during
componentfailure,itisalsocapableofself-healingwhenfailedcomponentsarereplaced.
In practice, systems may suffer from soft failures, where components are saturated beyond their
capacity to process requests, creating a cascading effect. In networking, this phenomenon is called
‘congestive collapse’. In effect, components appear to be online, but are stressed — unable to turn
aroundsomefractionofrequestswithinacceptabletimeframes.Inturn,therequestingcomponents
— having detected a timeout — retransmit requests, hoping to eventually get a response. This in-
creasespressureonthestressedcomponents,exacerbatingthesituation.Often,themissedresponse
ismerelyanindicationofreceivingtherequest—ineffect,therequesterissimplypilingonduplicate
work.
Under EDA, requesters do not require a confirmation from downstream consumers — a simple
acknowledgement from the event channel is sufficient to assume that the event has been stably
enqueuedandthattheconsumer(s)willgettoitatsomefuturepointintime.
Consistency
EDA ameliorates the problem of distributed consistency by attributing explicit mastership to state,
such that any stateful element can only be manipulated by at most one system — its designated
owner.Thisisalsoreferredtoastheoriginatingdomainoftheevent.Otherdomainsmayonlyreact
to the event; for example, they may reduce the event stream to a local projection of the emitter’s
state.
Under this model, consistency within the originating domain is trivially maintained by enforcing
the single writer principle. External to the domain, the events can be replayed in the exact order

Chapter1: EventStreamingFundamentals 7
theywereobservedontheemitter,creatingsequentialconsistency —amodelofconsistencywhere
updates do not have to be seen instantaneously, but must be presented in the same order to all
observers, which is also the order they were observed on the emitter. Alternatively, events may
be emitted in causal order, categorising them into multiple related sequences, where events within
any sequence are related amongst themselves, but unrelated to events in another sequence. This is
a slight relaxation of sequential consistency to allow for safe parallelism, and is sufficient in the
overwhelmingmajorityofusecases.
Applicability
Forallitsoutstandingbenefits,EDAisnotapanaceaandcannotsupplantintegratedormonolithic
systems in all cases. For instances, EDA is not well-suited to synchronous interactions, as mutual
orunilateralawarenessamongcollaboratingpartiesrunscontrarytothegrainofEDAandnegates
mostofitsbenefits.
EDA is not a general-purpose architectural paradigm. It is designed to be used in conjunction with
other paradigms and design patterns, such as synchronous request-response style messaging, to
solve more general problems. In the areas where it can be applied, it ordinarily leads to significant
improvements in the system’s non-functional characteristics. Therefore, one should seek to max-
imiseopportunitiesforevent-drivencompositions,refactoringthearchitecturetothatextent.
What is event streaming?
Finally, we arrive at the central question: What is event streaming? And frankly, there is little left
to explain. There is but one shortfall in the earlier narrative: EDA is an architectural paradigm —
it does not prescribe the particular semantics of the event interchange. Events could be broadcast
amongpartiesusingdifferentmechanisms,allpotentiallysatisfyingthebasictenetsofEDA.
Eventstreaming isamechanismthatcanbeusedtorealisetheeventchannel elementinEDA.Itis
primarilyconcernedwiththefollowingaspectsofeventpropagation:
• Interfacebetweentheemitterandthechannel,andtheconsumerandthechannel;
• Cardinalityoftheemitterandconsumerelementsthatinteractwithacommonchannel;
• Deliverysemantics;
• Enablingparallelisminthehandlingofeventnotifications;
• Persistence,durability,andretentionofeventrecords;and
• Orderingofeventsandassociatedconsistencymodels.
Thefocalpointofeventstreamingis,unsurprisingly,aneventstream.Atminimum,aneventstream
isadurable,totally-ordered,unboundedsequenceofimmutableeventrecords,deliveredatleastonce
toitssubscriber(s).Aneventstreamingplatformisaconcretetechnologythatimplementstheevent
streamingmodel,addressingthepointsenumeratedabove.Itinterfaceswithemitterandconsumer

Chapter1: EventStreamingFundamentals 8
ecosystems, hosts event streams, and may provide additional functionality beyond the essential set
of event streaming capabilities. For example, an event streaming platform may offer end-to-end
compression and encryption of event records, which is not essential in the construction of event-
drivensystems,butisconvenientnonetheless.
It is worth noting that event streaming is not required to implement the event channel element of
EDA. Other transports, such as message queues, may be used to fulfill similar objectives. In fact,
thereisnothingtosaythatEDAisexclusivetodistributedsystems;theearliestformsofEDAwere
realisedwithintheconfinesofasingleprocess,usingpurelyin-memorydatastructures.Itmayseem
banal in comparison, but even UI frameworks of the bygone era, such as Java Swing, draw on the
foundationsofEDA,asdotheirmorecontemporarycounterparts,suchasReact.
When operating in the context of a distributed system, the primary reason for choosing event
streaming over the competing alternatives is that the former was designed specifically for use in
EDA, and its various implementations — event streaming platforms — offer a host of capabilities
that streamline their adoption in EDA. A well-designed event streaming platform provides direct
correspondencewithnativeEDAconcepts.Forexample,ittakescareofeventimmutability,record
ordering, and supports multiple independent consumers — concepts that might not necessarily be
endemictoalternatesolutions,suchasmessagequeues.
This chapter has furnished an overview of the challenges of engineering distributed systems,
contrasted with the building of monolithic business applications. The numerous drawbacks of dis-
tributedsystemsincreasetheircostandcomplicatetheirupkeep.Generallyspeaking,thecomponents
of a complex system are distributed out of necessity — namely, the requirement to scale in both the
performanceplaneandintheengineeringcapacitytodeliverchange.
We looked at how the state of the art has progressed since the mass adoption of the principles of
distributed computing inmainstream softwareengineering. Specifically,weexploredEvent-Driven
Architectureasahighlyeffectiveparadigmforreducingcoupling,bolsteringresilience,andavoiding
thecomplexitiesofmaintainingagloballyconsistentstate.
Finally, we touched upon event streaming, which is a rendition of the event channel element of
EDA.Wealsolearnedwhyeventstreamingisthepreferredapproachforpersistingandtransporting
eventnotifications.Innouncertainterms,eventstreamingisthemoststraightforwardpathforthe
constructionofevent-drivensystems.

Chapter 2: Introducing Apache Kafka
Apache Kafka (or simply Kafka) is an event streaming platform. But it is also more than that. It is
anentireecosystemoftechnologiesdesignedtoassistintheconstructionofcompleteevent-driven
systems. Kafka goes above and beyond the essential set of event streaming capabilities, providing
richeventpersistence,transformation,andprocessingsemantics.
Eventstreamingplatformsareacomparativelyrecentparadigmwithinthebroadermessage-oriented
middlewareclass.Thereareonlyahandfulofmainstreamimplementationsavailable,comparedto
hundreds of MQ-style brokers, some going back to the 1980s (for example, Tuxedo). Compared to
established messaging standards such as AMQP, MQTT, XMPP, and JMS, there are no equivalent
standardsinthestreamingspace.Kafkaisaleaderintheareaofeventstreaming,andmorebroadly,
event-driven architecture. While there is no de jure standard in event streaming, Kafka is the
benchmarktowhichmostcompetingproductsorientthemselves.Tothiseffect,severalcompetitors
—suchasAzureEventHubsandApachePulsar—offerAPIsthatmimicKafka.
Event streaming platforms are an active area of continuous research and experimentation.
In spite of this, event streaming platforms aren’t just a niche concept or an academic idea
with few esoteric use cases; they can be applied effectively to a broad range of messaging
andeventingscenarios,routinelydisplacingtheirmoretraditionalcounterparts.
Kafka is written in Java, meaning it can run comfortably on most operating systems and hardware
configurations. It can equally be deployed on bare metal, in the Cloud, and a Kubernetes cluster.
And finally, Kafka has libraries written for just about every programming language, meaning that
virtually every developer can start taking advantage of event streaming and push their application
architecturetothenextlevelofresilienceandscalability.
The history of Kafka
Apache Kafka was originally developed by LinkedIn, and was subsequently open-sourced in early
2011. The name ‘Kafka’ was chosen by one of its founders — Jay Kreps. Kreps chose to name the
software after the famous 20ʰ-century author Franz Kafka because it was “a system optimised for
writing”. Kafka gained the full Apache Software Foundation project status in October 2012, having
graduatedfromtheApacheIncubatorprogram.
Kafka was born out of a need to track and process large volumes of site events, such as page
views and user actions, as well as for the aggregation log data. Before Kafka, LinkedIn maintained
severaldisparatedatapipelines,whichpresentedachallengefrombothcomplexityandoperational
scalability perspectives. In July 2011, having consolidated the individual platforms, Kafka was

Chapter2: IntroducingApacheKafka 10
processing approximately one billion events per day. By 2012, this number had risen to 20 billion.
By July 2013, Kafka was carrying 200 billion events per day. Two years later, in 2015, Kafka was
turningoveronetrillioneventsperday,withpeaksofupto4.5millioneventspersecond.
Overthefouryearsof2011to2015,thevolumeofrecordshasgrownbythreeordersofmagnitude.
Bytheendofthisperiod,LinkedInwasmovingwelloverapetabyteofeventdataperweek.Byall
means,thislevelofgrowthcouldnotbeattributedtoKafkaalone;however,Kafkawasundoubtedly
akeyenablerfromaninfrastructureperspective.
AsofOctober2019,LinkedInmaintainsover100Kafkaclusters,comprisingmorethan4,000brokers.
These collectively serve more than 100,000 topics and 7 million partitions. The total number of
recordshandledbyKafkahassurpassed7trillionperday.
The present day
The industry adoption of Kafka has been nothing short of phenomenal. The list of tech giants that
heavilyrelyonKafkaisimpressiveinitself.Tonamejustafew:
• Yahoo uses Kafka for real-time analytics, handling up to 20 gigabits of uncompressed event
data per second in 2015. Yahoo is also a major contributor to the Kafka ecosystem, having
open-sourceditsin-houseClusterManagerforApacheKafka(CMAK)product.
• Twitter heavily relies on Kafka for its mobile application performance management and
analytics product, which has been clocked at five billion sessions per day in February 2015.
TwitterprocessesthisstreamusingacombinationofApacheStorm,Hadoop,andAWSElastic
MapReduce.
• Netflix uses Kafka as the messaging backbone for its Keystone pipeline — a unified event
publishing, collection, and routing infrastructure for both batch and stream processing. As of
2016,Keystonecomprisesover4,000brokersdeployedentirelyintheCloud,whichcollectively
handlemorethan700billioneventsperday.
• Tumblr relies on Kafka as an integral part of its event processing pipeline, capturing up 500
millionpageviewsadaybackin2012.
• Square uses Kafka as the underlying bus to facilitate stream processing, website activity
tracking,metricscollectionandmonitoring,logaggregation,real-timeanalytics,andcomplex
eventprocessing.
• Pinterest employs Kafka for its real-time advertising platform, with 100 clusters comprising
over 2,000 brokers deployed in AWS. Pinterest is turning over in excess of 800 billion events
perday,peakingat15millionpersecond.
• Uber is among the most prominent of Kafka adopters, processing in excess of a trillion
eventsperday—mostlyfordataingestion,eventstreamprocessing,databasechangelogs,log
aggregation, and general-purpose publish-subscribe message exchanges. In addition, Uber is
an avid open-source contributor — having released its in-house cluster replication solution
uReplicator intothewild.

Chapter2: IntroducingApacheKafka 11
And it’s not just the engineering-focused organisations that have adopted Kafka — by some esti-
mates,upathirdofFortune500companiesuseKafkatofulfilltheireventstreamingandprocessing
needs.
There are good reasons for this level of industry adoption. As it happens, Kafka is one of the
mostwell-supportedandwell-regardedeventstreamingplatforms,boasting animpressivenumber
of open-source projects that integrate with Kafka. Some of the big names include Apache Storm,
Apache Flink, Apache Hadoop, LogStash and the Elasticsearch Stack, to name a few There are also
KafkaConnectintegrationswitheverymajorSQLdatabase,andmostNoSQLonestoo.Atthetime
of writing, there are circa one hundred supported off-the-shelf connectors, which does not include
customconnectorsthathavebeenindependentlydeveloped.
Uses of Kafka
Chapter1:EventStreamingFundamentalshasprovidedthenecessarybackground,fittingKafkaas
aneventstreamingplatformwithinalargerevent-drivensystem.
There are several use cases falling within the scope of EDA that are well-served by Apache Kafka.
Thissectioncoverssomeofthesescenarios,illustratinghowKafkamaybeusedtoaddressthem.
Publish-subscribe
Pub-Sub
Anymessagingscenariowhereproducersaregenerallyunawareofconsumers,andinsteadpublish
messagestowell-knownaggregationscalledtopics.Conversely,consumersaregenerallyunawareof
theproducersbutareinsteadconcernedwithspecificcontentcategories.Theproducerandconsumer

Chapter2: IntroducingApacheKafka 12
ecosystemsareloosely-coupled,beingawareofonlythecommontopic(s)andmessagingschema(s).
Thispatterniscommonlyusedintheconstructionofloosely-coupledmicroservices.
WhenKafkaisusedforgeneral-purposepublish-subscribemessaging,itwillbecompetingwithits
‘enterprise’ counterparts, such as message brokers and service buses. Admittedly, Kafka might not
have all the features of some of these middleware platforms — such as message deletion, priority
levels, producer flow control, distributed transactions, or dead-letter queues. On the other hand,
these features are mostly representative of traditional messaging paradigms — intrinsic to how
these platforms are commonly used. Kafka works in its own idiomatic way — optimised around
unbounded sequences of immutable events. As long as a publish-subscribe relationship can be
representedassuch,thenKafkaisfitforthetask.
Log aggregation
Logaggregation
Dealing with large volumes of log-structured events, typically emitted by application or infrastruc-
turecomponents.Logsmaybegeneratedatburstratesthatsignificantlyoutstriptheabilityofquery-
centric datastores to keep up with log ingestion and indexing, which are regarded as ‘expensive’
operations. Kafka can act as a buffer, offering an intermediate, durable datastore. The ingestion
processwillactasasink,eventuallycollatingthelogsintoaread-optimiseddatabase(forexample,
ElasticsearchorHBase).
A log aggregation pipeline may also contain intermediate steps, each adding value en route to the
final destination; for example, to compress log data, encrypt log content, normalise the logs into a
canonicalform,orsanitisethelogentries—scrubbingthemofpersonally-identifiableinformation.

Chapter2: IntroducingApacheKafka 13
Log shipping
Logshipping
Whilesoundingvaguelysimilartologaggregation,theshippingoflogsisavastlydifferentconcept.
Essentially,thisinvolvesthereal-timecopyingofjournalentriesfromamasterdata-centricsystem
to one or more read-only replicas. Assuming stage changes are fully captured as journal records,
replaying those records allows the replicas to accurately mimic the state of the master, albeit with
somelag.
Kafka’s optional ability to partition records within a topic to create independent, causally ordered
sequencesofeventsallowsforreplicastooperateinoneofsequentialorcausalconsistencymodels—
dependingonthechosenpartitioningscheme.Thevariousconsistencymodelswerebrieflycovered
in Chapter 1: Event Streaming Fundamentals. Both consistency models are sufficient for creating
read-onlycopiesoftheoriginaldata.
Logshippingisakeyenablerforanotherrelatedarchitecturalpattern—eventsourcing.Kafkawill
actasadurableeventstore,allowinganynumberofconsumerstorebuildapoint-in-timesnapshot
of their application state by replaying all records up to that point in time. Loss of state information
in any of the downstream consumers can be recovered by replaying the events from the last stable
checkpoint,therebyreducingtheneedtotakefrequentbackups.
SEDA pipelines
SEDApipeline

Chapter2: IntroducingApacheKafka 14
Staged Event-Driven Architecture (SEDA) is the application of pipelining to event-oriented data.
Events flow unidirectionally through a series of processing stages linked by topics, each one per-
formingamappingoperationbeforepublishingatransformedeventtothenexttopic.Intermediate
stages simultaneously act as both consumers and producers, and may scale autonomously and
independentlyofoneanothertomatchtheiruniqueloaddemands.Bybreakingacomplexproblem
intostages,SEDAimprovesthemodularityofthesystem.
A SEDA pipeline may combine fan-in and fan-out topologies. Stages may consume events from
multipletopicssimultaneously,performingtheequivalentofanSQLJOINoneventstreams.Stages
canalsopublishtomultipletopics,feedingseveraldownstreampipelines.
Asapattern,SEDAisreadilyfoundindatawarehousing,datalakes,reporting,analytics,andother
BusinessIntelligencesystems,andisoftenacrucialelementofBigDataapplications.SEDAcanalso
beusedinlogaggregation;infact,logaggregationisanarrowspecialisationofSEDA.
CEP
CEP
Complex Event Processing (CEP) extracts meaningful information and patterns in a stream of
discrete events, or across a set of disjoint event streams. CEP processors tend to be stateful, as they
mustbeabletoefficientlyrecallprioreventstoidentifypatternsthatmightspanabroadtimeframe,
rangingfrommillisecondstodays,dependingonthecontext.
CEP is heavily employed in such applications as algorithmic stock trading, security threat analysis,
real-timefrauddetection,andcontrolsystems.

Chapter2: IntroducingApacheKafka 15
Event-sourced CQRS
CQRS
Command-Query Responsibility Segregation (CQRS) separates the actions that mutate state from
the actions that query the state. Because mutations and queries typically exhibit contrasting run-
time characteristics and require vastly different, often contradictory optimisation decisions, the
separation of these concerns is conducive to building highly performant systems. The flip side is
complexity— requiring multiple datastores and duplication of data — each datastore will maintain
auniqueprojectionofthemasterdataset,builtfromadedicateddatapipe.Kafkacurbssomeofthe
complexity inherent in CQRS architectures by acting as a common event-sourced ledger, using the
conceptofconsumergroups toindividuallyfeedthedifferentquery-centricdatastores.
This pattern is related to log shipping, and might appear identical at first glance. The differences
aresubtle.Logshippingisperformedonlow-level,internalrepresentationsofdata,whereboththe
replicas and the master datastore are coupled to, and share the same internal data structures. Put
differently, log shipping is an internal mechanism that shuttles data within the confines of a single
domain.Incomparison,CQRSassumesdisparatesystemsandspansdomainboundaries.Allparties
are coupled to some canonical representation of events, which is versioned independently of the
parties’internalrepresentationsofthoseevents.

Chapter2: IntroducingApacheKafka 16
ThischapterhasintroducedthereadertoApacheKafka—theworld’smostrecognisedandwidely
deployed event streaming platform. We looked at the history behind Kafka — how it started its
journeyandwhatithasbecome.
WealsoexploredthevarioususecasesthatKafkacomfortablyenablesandsupports.Thesearevast
and varied, demonstrating Kafka’s overall flexibility and eagerness to cater to a diverse range of
eventstreamingscenarios.

Chapter 3: Architecture and Core
Concepts
Thefirsttwochaptershavefurnishedacushydebutoftheessentialconceptsofeventstreamingand
have given the reader an introduction to Apache Kafka as the premier event streaming technology
thatisincreasinglyusedtopowerorganisationsofallshapesandsizes—fromgreen-sproutstartups
tomulti-nationaljuggernauts.
Now that the scene has been set, it is time to take a deeper look at how Kafka works, and more to
thepoint,howoneworkswithit.
Architecture Overview
Whiletheintentionisn’ttoindoctrinatethereaderwiththeminutiaofKafka’sinnerworkings(for
now), some appreciation of its design will go a long way in explaining the foundational concepts
thatwillbecoveredshortly.
Kafkaisadistributedsystemcomprisingseveralkeycomponents.Atanoutlinelevel,theseare:
• Broker nodes: Responsible for the bulk of I/O operations and durable persistence within the
cluster.
• ZooKeeper nodes: Under the hood, Kafka needs a way of managing the overall controller
status within the cluster. ZooKeeper fulfills this role, additionally acting as a consistent state
repositorythatcanbesafelysharedamongthebrokers.
• Producers:ClientapplicationsresponsibleforappendingrecordstoKafkatopics.
• Consumers:Clientapplicationsthatreadfromtopics.
The diagram below offers a brief overview of the Kafka component architecture, illustrating the
relationshipsbetweenitsconstituentparts.Afurtherelaborationofthecomponentsfollows.

Chapter3: ArchitectureandCoreConcepts 18
KafkaArchitectureOverview
Broker nodes
Before we begin, it is worth noting that industry literature uses the terminology ‘Kafka Server’,
‘Kafka Broker’ and ‘Kafka Node’ interchangeably to refer to the same concept. The official docu-
mentation refers to all three, while the shell scripts for starting Kafka and ZooKeeper refer to both
as ‘server’. This book favours the term ‘broker’ or, in some cases, the more elaborate ‘broker node’,
avoidingtheuseof‘server’foritsambiguity.
So, what is a broker? If the reader comes from a background of messaging and middleware, the
concept of a broker should resonate innately and intuitively. Otherwise, the reader is invited to
considerWikipedia’sdefinition:
A broker is a person or firm who arranges transactions between a buyer and a seller for
acommissionwhenthedealisexecuted.
Sans the commission piece, the definition fits Kafka like a glove. We would, of course, substitute
‘buyer’ and ‘seller’ for ‘consumer’ and ‘producer’, respectively, but one point is clear — the broker
actsasanintermediary,facilitatingtheinteractionsbetweentwoparties—addingvalueinbetween.

Chapter3: ArchitectureandCoreConcepts 19
This might make one wonder: Why couldn’t the parties interact directly? A comprehensive answer
wouldboreintothedepthsofcomputerscience,specificallyintothenotionofcoupling.Wearenot
going to do this, to much relief; instead, the answer will be condensed to the following: the parties
mightnotbeawareofoneanotherortheymightnotbejointlypresentatthesamepointintime.The
latterplacesanadditionaldemandonthebroker:itmustbestateful.Inotherwords,itmustpersist
therecordsemittedbytheproducer,sothattheymaybeeventuallydeliveredtotheconsumerwhen
itisconvenienttodoso.Thebrokerneedstobenotonlypersistent,butalsodurable.By‘durable’,it
isimpliedthatitspersistenceguaranteescanbeextendedoveraperiodoftimeandcanvasscenarios
thatinvolvecomponentfailure.
Discussionsofbrokersasameansofdecouplingcommunicatingpartiesmayconjureimages
of message queues from the days of yore. Many Kafka purists would protest: Kafka is
not a message queue, but an event streaming platform. While the argument holds on the
whole,theunderpinningobjectivesremainlargelyunchanged.Fundamentally,westillhave
a publishing party, a subscribing party, and an intermediary to facilitate their interaction.
Andwewouldideallylikethepartiestoremainminimallycoupled.
AKafkabrokerisaJavaprocessthatactsaspartofalargercluster,wheretheminimumsizeofthe
cluster is one. (Indeed, we often use a singleton cluster for testing.) A broker is one of the units of
scalabilityinKafka;byincreasingthenumberofbrokers,onecanachieveimprovedI/O,availability,
anddurabilitycharacteristics.(ThereareotherwaysofscalingKafka,asweshallsoondiscover.)
A broker fulfills its persistence obligations by hosting a set of append-only log files that comprise
thepartitionshostedbythecluster.Amorethoroughdiscussiononpartitionsisyettocome;itwill
sufficetosayfornowthatpartitionsareelementalunitsofstoragethatonecanaddressinKafka.
Each partition is mastered by exactly one broker — the partitionleader. Partition data is replicated
to a set of zero or more follower brokers. Collectively, the leader and the followers are referred to
as replicas. Brokers share the load of leader and follower roles among themselves; a broker node
may act as the leader for certain replicas, while being a follower for others. The roles may change
— a follower replica may be promoted to leader status in the event of failure or as part of a manual
rebalancingoperation.Thenotionofreplicassatisfiesthedurabilityguarantee;themorereplicasin
acluster,thelowerthelikelihoodofdatalossduetoanisolatedreplicafailure.
Brokernodesarelargelyidenticalineveryway;eachnodecompetesforthemastershipofpartition
data on equal footing with its peers. Given the symmetric nature of the cluster, Kafka requires a
mechanism for arbitrating the roles within the cluster and assigning partition leadership statuses
among the broker nodes. Rather than making these decisions collectively, broker nodes follow a
rudimentary chain-of-command. A single node is elected as the cluster controller which, in turn,
directs all nodes (including itself) to assume specific roles. In other words, it is the controller’s
responsibility for managing the states of partitions and replicas, and for performing administrative
taskslikereassigningpartitionsamongthebrokernodes.

Chapter3: ArchitectureandCoreConcepts 20
ZooKeeper nodes
While the controller is entrusted with key administrative operations, the responsibility for electing
acontrollerlieswithanotherparty—ZooKeeper.Infact,ZooKeeperisitselfaclusterofcooperating
processescalledanensemble.EverybrokernodewillregisteritsintentwithZooKeeper,butonlyone
will be elected as the controller. ZooKeeper ensures that at most one broker node will be assigned
the controller status, and should the controller node fail or leave the cluster, another broker node
willpromptlytakeitsplace.
A ZooKeeper ensemble also acts as a consistent and highly available configuration repository of
sorts,maintainingclustermetadata,leader-followerstates,quotas,userinformation,accesscontrol
lists, and other housekeeping items. Owing to the underlying gossiping and consensus protocol of
theZooKeeperensemble,thenumberofZooKeepernodesmustbeodd.
While ZooKeeper is bundled with Kafka for convenience, it is important to acknowledge that
ZooKeeperisnotaninternalcomponentofKafka,butanopen-sourceprojectinitsownright.
Producers
A Kafka producer is a client application that can act as a source of data in a Kafka cluster. A
producercommunicateswiththeclusteroverasetofpersistentTCPconnections,withanindividual
connectionestablishedwitheachbroker.ProducerscanpublishrecordstooneormoreKafkatopics,
and any number of producers can append records to the same topic. Generally speaking, only
producersareallowedtoappendrecordstotopics;aconsumercannotmodifyatopicinanyway.
Consumers
Aconsumerisaclientapplicationthatactsasadatasink,subscribingtostreamsofrecordsfromone
ormoretopics.Consumersareconceptuallymorecomplexthanproducers—theyhavetocoordinate
among themselves to balance the load of consuming records and track their progress through the
stream.
Total and partial order
A minor note before we proceed: there is some theory ahead that one must endure to become an
effectivepurveyorofKafka.Theupcomingcontentmayseemesotericandsomewhatdetachedfrom
thesubjectmatteratfirst;however,thereaderisassuredthatitismostrelevant.Wewillbeasbrief
aspossible.
Without exaggeration, Kafka’s entire event processing architecture is largely underpinned by the
twoprimordialattributesofsettheory:partialorder andtotalorder.
Onthetopicofsettheory,whatisaset?Asetisacollectionofdistinctelements—objectsthatexistin
theirownright.Forexample,thenumbers2,4,and6 aredistinctobjects;whentheyareconsidered

Chapter3: ArchitectureandCoreConcepts 21
collectively, they form a set of size three, written {2, 4, 6}. Developed at the end of the 19ʰ century,
set theory is now a ubiquitous part of mathematics; it is also generally considered fundamental to
theconstructionofdistributedandconcurrentsystems.
Atotallyordered setisonewhereeveryelementhasawell-definedorderingrelationshipwithevery
other element in the set. Consider, for example, the range of natural numbers one to five. When
sortedinincreasingorder,itformsthefollowingsequence:
Totallyorderedset
This is an ordered set, as every element has a well-defined predecessor-successor relationship with
every other element. One can remove arbitrary values from this set and reinsert those values back
into the set and arrive at the same sequence, no matter how many times this is attempted. Stated
otherwise,thereisonlyonepermutationofelementsthatsatisfiestheorderingconstraints.
Orderedsetsexhibittheconvenientpropertyoftransitivity.Fromtheaboveexample,weknowthat
2 must come after 1 and before 3. We also know that 3 must come before 4. Therefore, we can use
thetransitivityrelationtodeducethat4 mustcomeafter2.
The direct antithesis of a totally ordered set is an unordered set. For example, an offhand list of
capital cities {Sydney, New York, London} is unordered. Without applying further constraints, one
cannotreasonwhetherSydneyshouldappearbeforeorafterNewYork.Onecanarbitrarilypermute
theelementstoarriveatdifferentsequencesofcitieswithoutupsettinganyone.
Between the two extremes, we find a partially ordered set. Consider the set of natural numbers
ordered by divisibility, such that a number must appear after its divisor. For the range of numbers
twototwelve,oneinstantiationofasequencethatsatisfiesthispartialorderingconstraintmightbe
[2, 3, 5, 7, 11, 4, 6, 9, 10, 8, 12]. But that is just one instance — there are several such sequences that
are distinct, yet equivalent. Looking at the set, we can state that the numbers 4 and 6 must appear
after 2, but there is no predecessor-successor relationship between 4 and 6 — they are mutually
incomparable.

Chapter3: ArchitectureandCoreConcepts 22
Partiallyorderedset
Multipletotallyorderedsetscanbecontainedinasinglepartiallyorderedset.Forexample,consider
the Latin and Cyrillic alphabet sets {A, B, C, …, Z} and {А, Б, В, …, Я}, with their elements (letters)

Chapter3: ArchitectureandCoreConcepts 23
arranged in alphabetical order — forming two distinct, totally ordered sets. Their union would be
a partially ordered set; it would still maintain the relative order within each alphabet, without
imposingorderacross alphabets.
Likeinatotallyorderedset,theelementsofpartiallyorderedsetsexhibittransitivity.Intheexample
involving divisors, the number 2, appearing before 4, where 4 appears before 8 or 12, implies that
both8 and12 mustappearafter2.
Topping off this discussion is the term ‘causal order’. This type of ordering was first brought up in
Chapter1:EventStreamingFundamentals,aspartofthediscussionontheconsistencyofreplicated
state. Unlike the other flavours, causal order is not a carryover from 19ʰ-century mathematics; it
stems from the study of distributed systems. A notable challenge of constructing such systems is
that messages sent between processes may arrive zero or more times at any point after they are
sent. As a consequence, there is no agreeable notion of time among collaborating processes. If one
process, such as a clock, sends a timestamped message to another process, there is no reliable way
forareceivertodetermine thetime relativeto theclock andsynchronisethetwoprocesses.Thisis
oftencitedasthesolereasonthatbuildingdistributedsystemsishard.
Intheabsenceofaglobalclock,therelativetimingofapairofeventsoccurringinclosesuccession
may be indistinguishable to an outside observer; however, if the two events are causally related,
it is possible to distinguish their order; in other words, they become comparable. Causal order is
a semantic rendition of partial order, where two elements may be bound by a happened-before
relationship.Thisisdenotedbyanarrowappearingbetweenthetwoelements;forexample,ifA→
B,thenAisaneventthatmustlogicallyprecedeB.ThisfurtherimpliesthatAoccurredbeforeBin
a chronological sense. Otherwise, if not(A → B), A cannot have preceded B in a causal sense. The
latterdoesnotimplythatAcouldnothavephysicallyoccurredbeforeB;onesimplyhasnowayof
ascertainingthis.
Causal relationships in distributed systems do not necessarily correspond to the more prevalent
deductive‘cause-and-effect’styleoflogicalreasoning.Acausalrelationshipbetweenapairofevents
simplyimpliesthatoneeventprecedes,ratherthaninduces,theother.Anditmaybethattheoriginal
events themselves are not comparable, but the recorded observations of these events are. These
observationsareeventsintheirownright,andmayalsoexhibitcausality.
Consider, for example, two samples of temperature readings R0 and R1 taken at different sites.
Theyarecommunicatedtoaremotereceiverandrecordedintheorderofarrival,formingacausal
relationship on the receiver. If the message from R0 was received first, we could confidently state
thatreceived(R0)→received(R1).Thisdoesnotimplythatsent(R0)→sent(R1),anditmostcertainly
doesnotimplythatR0 playedanypartininducingR1.
Inconsideringtheconnectionbetweentheterms‘ordered’,‘unordered’,‘partiallyordered’,‘causally
ordered’,and‘totallyordered’,onecandrawthefollowingsynopsis:
• Apartiallyorderedsetimpliesthatnoteverypairofelementsneedstobecomparable.

Chapter3: ArchitectureandCoreConcepts 24
• Atotallyorderedsetisaspecialcaseofapartiallyorderedset,wherethereexistsawell-defined
orderbetweeneveryconceivableelementpair.
• An unordered set is also a special case of a partially ordered set, where there is no pair of
comparableelements.
• Causalorderisarenditionofpartialorder,whereeachelementrepresentsanevent,andsome
pairsofeventshaveahappened-beforerelationship.
• On its own, the term ‘ordered set’ is ambiguous, suggesting that the elements of a set exhibit
someorder-inducingrelationships.Thistermisgenerallyavoided.
With partial and total order out of the way, we can proceed to a discussion of records, topics, and
partitions.Thelinkbetweenthelatterandsettheorywillshortlybecomeapparent.
Records
Arecord isthe mostelemental unitof persistencein Kafka.In the contextof event-drivenarchitec-
ture, which is chiefly how one is meant to use Kafka, a record typically corresponds to some event
ofinterest.Itischaracterisedbythefollowingattributes:
• Key: A record can be associated with an optional non-unique key, which acts as a kind of
classifier — grouping related records on the basis of their key. The key is entirely free-form;
anythingthatcanberepresentedasanarrayofbytescanserveasarecordkey.
• Value: A value is effectively the informational payload of a record. The value is the most
interestingpartofarecordinabusinesssense—itistherecord’svaluethatultimatelydescribes
the event. A value is optional, although it is rare to see a record with anull value. Without a
value,a recordis largelypointless;all other attributesplay asupporting rolein conveyingthe
value.
• Headers: A set of free-form key-value pairs that can optionally annotate a record. Headers in
Kafka are akin to their namesake in HTTP — they augment the main payload with additional
metadata.
• Partitionnumber:Azero-basedindexofthepartitionthattherecordappearsin.Arecordmust
always be tied to exactly one partition; however, the partition need not be specified explicitly
whentherecordispublished.
• Offset:A64-bitsignedintegerforlocatingarecordwithinitsencompassingpartition.Records
arestoredsequentially;theoffsetrepresentsalogicalsequencenumberoftherecord.
• Timestamp:Amillisecond-precisetimestampoftherecord.Atimestampmaybesetexplicitly
bytheproducertoanarbitraryvalue,oritmaybeautomaticallyassignedbythebrokerwhen
arecordisappendedtothelog.
NewcomerstoKafkausuallyhavenoproblemsgraspingtheconceptofarecordandunderstanding
its internals, with the possible exception of the key attribute. Because Kafka is often likened to a
database(albeitoneforstoringevents),arecord’skeyisoftenincorrectlyassociatedwithadatabase

Chapter3: ArchitectureandCoreConcepts 25
key.Thiswarrantspromptclarification,soastonotcauseconfusiondownthetrack.Kafkadoeshave
aprimarykey,butitisnot therecordkey.Arecord’sequivalentofa‘primarykey’isthecomposition
of the record’s partition number and its offset. A record’s key is not unique, and therefore cannot
possibly serve as the primary key. Furthermore, Kafka does not have the concept of a secondary
index,andsotherecordkeycannotbeusedtoisolateasetofmatchingrecords.
Instead, it is best to think of a key as a kind of a pigeonhole into which related records are placed.
Records maintain an association with their key over their entire lifetime. One cannot alter the key,
oranyaspectoftherecordforthatmatter,oncetherecordhasbeenpublished.Itisunfortunatethat
keysarenamedastheyare;aclassifier (orasynonymthereof)wouldhavebeenmoreappropriate.
Thereasonwhytheterm‘key’waschosenislikelyduetoitsassociationwithhashing.Kafka
producersusekeystomaprecordstopartitions—anactionthatinvolveshashingofthekey
bytes and applying the modulo operator. This carries a close resemblance to how keys are
hashedtoyieldabucketinahashtable.
ThecorrespondencebetweenKafkarecordsandobservedeventsmaynotbedirect;forexample,an
eventmightspawnmultipleKafkarecords,typicallyemittedinclosesuccession.Thoserecordsmay,
inturn,beprocessedbyastagedevent-drivenpipeline—spawningadditionalrecordsinthecourse
of processing. An overview of the staged event-driven architecture (SEDA) pattern was presented
inChapter2:IntroducingApacheKafka.
Kafkaisoftenusedasacommunicationmediumbetweenfine-grainedapplicationservicesoracross
entire application domains. In saying that, the employment of Kafka as an internal note-taking or
ledgering mechanism within a bounded context, is a perfectly valid use case. In fact, both Event
Sourcing and CQRS patterns have seen strong adoption within the confines of single a domain, as
wellasacrossdomains.
The recorded event might not have a real-life equivalent, even indirectly or circumstantially; there
isnoassumptionorimplicationthatKafkaisusedsolelyasaregistryofevents.Thisstatementmay
ruffleafewfeathersorsparkanall-outbellumsacrum;afterall,Kafkaisaneventstreamingplatform
— if not for recording events, what could it possibly be used for? Well, it might be used to replace
a more traditional message broker. Much to the despise of Kafka purists (or delight, depending on
one’s personal convictions), an increasingly-growing use for Kafka is to replace technologies such
asRabbitMQ,ActiveMQ,AWSSQSandSNS,GoogleCloudPub/Sub,andsoforth.Kafkarenowned
flexibilityletsitcomfortablydealwithabroadrangeofmessagingtopologiesandapplications,some
ofwhichhavelittleresemblancetoclassicalevent-drivenarchitecture.
Thegenerallyacceptedrelationshipbetweentheterms‘message’and‘event’issuchthatamessage
encompasses a general class of asynchronous communiqués, while an event is a semantic speciali-
sationofamessagethatcommunicatesthatsomeactionofsignificancehasoccurred.Eventshave
one logical owner — the producer (or publisher); they are immutable; they can be subscribed to
and unsubscribed from. The term ‘event’ is often contrasted with another term — ‘command’ —
a specialised message encompassing a directive issued from one party to another, requesting it to

Chapter3: ArchitectureandCoreConcepts 26
performsomeaction.Thelogicalownerofacommandisitssolerecipient;itcannotbesubscribed
toorunsubscribedfrom.
Kafka documentation and client APIs mostly prefer the term ‘record’, where others might use
‘message’ or ‘event’. Kafka literature occasionally uses ‘message’ as a substitute for ‘record’, but
this has been generally discouraged within the community, for the angst of confusing Kafka with
the more traditional message-oriented middleware. In this book, the term ‘record’ is preferred,
particularly when working in the context of event streaming. The term ‘event’ will generally be
used to refer to an external action that triggered the publishing of the record, but may also be
metonymicallyusedtorefertotherecorditself,asitisoftenconvenienttodoso.Finally,thisbook
mayoccasionallyusetheterm‘message’whendescribingrecordsinthecontextofamoretraditional
messagebroker,wheretheuseofthistermaidsclarity.
Partitions
Apartitionisatotallyordered,unboundedsetofrecords.Publishedrecordsareappendedtothehead-
end of the encompassing partition. Where a record can be seen as an elemental unit of persistence,
apartitionisanelementalunitofrecordstreaming.
Because records are totally ordered within their partition, any pair of records in the same partition
is bound by a predecessor-successor relationship. This relationship is implicitly assigned by the
producer application. For any given producer instance, records will be written in the order they
were emitted by the application. By way of example, assume a pair of records P and Q destined
for the same partition. If record P was published before Q, then P will precede Q in the partition.
Furthermore, they will be read in the same order by all consumers; P will always be read before
Q, for every possible consumer. This ordering guarantee is vital when implementing event-driven
systems,moresothanforpeer-to-peermessagingorworkqueues;publishedrecordswillgenerally
correspond to or derive from real-life events, and preserving the timeline of these events is often
essential.
Records published to one partition by the same producer are causally ordered. In other words, if
P precedes Q, then P must have been observed before Q on the producer; the happened-before
relationshipispreserved—impartedfromtheproducerontothepartition.
There is no recognised causal ordering across producers; if two (or more) producers emit records
simultaneouslyforthesamepartition,thoserecordsmaymaterialiseinarbitraryorder.Therelative
order will not depend on which producer application attempted to publish first, but rather, which
record beat the other to the partition leader. That said, whatever the order, it will be consistent —
observed uniformly across all consumers. Total order is still preserved, but some record pairs may
onlyberelatedcircumstantially.
Corollarytotheabove,intheabsenceofproducersynchronisation,causalordercanonlybeachieved
when a single producer emits records to the same partition. All other combinations — involving

Chapter3: ArchitectureandCoreConcepts 27
multiple unrelated producers or different partitions — may result in a record stream that fails to
depictcausality.Whetherornotthisisanissuewilldependlargelyontheapplication.
Arecord’soffsetuniquelyidentifiesitinthepartition.Theoffsetactsasaprimarykey,allowingfor
fast,O(1)lookups.Theoffsetisastrictlymonotonically-increasingintegerinasparseaddressspace,
meaningthateachsuccessiveoffsetisalwayshigherthanitspredecessor,andtheremaybevarying
gaps between neighbouring offsets. Gaps might legitimately appear if compaction is enabled or as
a result of transactions; we don’t need to delve into the details at this stage, suffice it to say that
offsetsneednotbecontiguous.
Given a record, an application shouldn’t attempt to literally interpret its offset or guess what the
next offset might be. It may, however, actively exploit the properties of total order and transitivity
to infer the relative order of any record pair based on their offsets, sort the records by their offset,
andsoforth.
Thediagrambelowshowswhatapartitionlookslikeontheinside.

Chapter3: ArchitectureandCoreConcepts 28
Partitioncontents
The beginning offset, also called the low-water mark, is the first record that will be presented to a
prospectiveconsumer.DuetoKafka’sboundedretention,thisisnotnecessarilythefirstrecordthat
waspublished.Recordsmaybeprunedonthebasisoftimeand/orpartitionsize.Whenthisoccurs,
the low-water mark will appear to advance, and records earlier than the low-water mark will be
truncated.
Conversely,thehigh-watermark istheoffsetimmediatelyfollowingthelastsuccessfullyreplicated
record. Consumers are only allowed to read up to the high-water mark. This prevents a consumer

Chapter3: ArchitectureandCoreConcepts 29
from reading unreplicated data that may be lost in the event of leader failure. The equivalent term
forahigh-watermarkistheendoffset.
Thestatementaboveisaminorsimplification.Theendoffsetcorrespondstothehigh-water
markfornon-transactionalconsumers.Whereamorestrictisolationmodehasbeenselected
ontheconsumer,theendoffsetmaytrailthehigh-watermark.Transactionalmessagingis
anadvancedtopic,coveredinChapter18:Transactions.
The end offset should not be confused with the internal term ‘log end offset’, which is the
offset immediately following that of the last written record. The ‘log end offset’ will be
assigned to the next record that will be published. When the follower replicas lag behind
the leader, the ‘log end offset’ will be greater than the high-water mark. When replication
eventuallycatchesup,thehigh-watermarkwillalignwiththe‘logendoffset’.
Subtractingthelow-watermarkfromthehigh-watermarkwillyieldtheupperboundonthenumber
of securely persisted records in the partition. The actual number may be slightly less, as the offsets
are not guaranteed to be contiguous. The total number of records may be fewer or greater, as the
high-watermarkdoesnotreflectthenumberofunreplicatedrecords.
Topics
So, a partition is an unbounded sequence of records, an open ledger, a continuum of events — each
definitionasgoodasthenext.Alongwitharecord,apartitionisanelementalbuildingblockofan
eventstreamingplatform.Butapartitionistoobasictobeusedeffectivelyonitsown.
A topic is a logical aggregation of partitions. It comprises one or more partitions, and a partition
must be a part of exactly one topic. Topics are fundamental to Kafka, allowing for both parallelism
andloadbalancing.
Earlier,itwassaidthatpartitionsexhibittotalorder.Takingaset-theoreticperspective,atopicisjust
a union of the individual underlying sets; since partitions within a topic are mutually independent,
the topic is said to exhibit partial order. In simple terms, this means that certain records may be
ordered in relation to one another, while being unordered with respect to certain other records. A
Kafkatopic,andspecificallyitsuseofpartialorder,enablesustoprocessrecordsinparallelwherewe
can,whilemaintainingorderwherewemust.Theconceptofconsumerparallelismwillbeexplored
shortly;forthetimebeing,thefocuswillremainontheproducerecosystem.
Since Kafka is an event streaming platform, it may be more instructive to think of a topic and its
partitionsasawidestream,comprisingmultipleparallelsubstreams.Eventswithinasubstreammay
berelatedobjectively,insofarasoneeventmustprecedesomeotherevent.Inotherwords,acausal
relationshipisinplace.(Theeventsarenotrequiredtobecausallyrelatedtoshareasubstream;the
reasonthatwillbetouchedonlater.)Eventsacrosssubstreamsarerelatedsubjectively—theymight

Chapter3: ArchitectureandCoreConcepts 30
refer to a similar class of observations and it may be advantageous to encompass them within the
samestream.
Occasionally,thisbookwillusetheterm‘stream’asasubstitutefor‘topic’;whenreferring
toevents,theuseoftheterm‘stream’isoftenmorenaturalandintuitive.
Preciselyhowrecordsarepartitionedislefttothediscretionoftheproducer.Aproducerapplication
mayexplicitlyassignapartitionnumberwhenpublishingarecord,althoughthisapproachisrarely
used. A much more common approach is for the client application to deal exclusively with record
keys and values, and have the producer library automatically select a partition on the basis of a
record’s key. A producer will digest the byte content of the key using a hash function (Kafka uses
murmur2forthispurpose).Thehighest-orderbitofthehashvalueismaskedofftoforceittoapositive
integer, before taking the result, modulo the number of partitions, to arrive at the final partition
number.Thecontentsofthetopicandtheproducers’interactionswiththetopicaredepictedbelow.
Topiccontents
While this partitioning scheme is deterministic, it is not consistent. Two records with the
samekeyhashedatdifferentpointsintimewillcorrespondtoanidenticalpartitionnumber
ifandonlyif thenumberofpartitionshasnotchangedinthattime.Increasingthenumber
of partitions in a topic (Kafka does not support non-destructive downsizing) results in the
tworecordsoccupyingpotentiallydifferentpartitions—leadingtoabreakdownofanyprior
order. There are many gotchas, such as this one, in Kafka; they will be called out as such
fromtimetotime.
Recordssharingthesamehashareguaranteedtooccupythesamepartition.Assumingatopicwith

Chapter3: ArchitectureandCoreConcepts 31
multiple partitions, records with a different key will likely end up in different partitions. However,
due to hash collisions, records with different hashes may also end up in the same partition. Such is
the nature of hashing; if the reader appreciates how a hash table works, this is no different. It was
previously stated that records in the same partition may be causally related, but do not have to be.
Thereasonisspecificallytodowithhashing;whentherearemorecausallyrelatedrecordgroupings
then there are partitions in a topic, there will invariably be some partitions that contain multiple
unrelated sets of records. In mathematics, this is referred to as the Dirichlet’s Drawer Principle or
thePigeonholePrinciple.Infact,duetotheimperfectspacedistributionofhashfunctions,unrelated
recordswilllikelybegroupedinthesamepartitioneventherearemorepartitionsthandistinctkeys.
Producers rarely care which specific partition the records will map to, only that related records
end up in the same partition, and that their order is preserved. Similarly, consumers are largely
indifferent to their assigned partitions, so long that they receive the records in the same order as
theywerepublished,wherethoserecordsarecausallybound.
Consumer groups and load balancing
Sofarwehavelearnedthatproducersemitrecordstoatopic;theserecordsareorganisedintoneatly
ordered partitions. Kafka’s producer-topic-consumer topology adheres to a flexible and highly
generalised multipoint-to-multipoint model, meaning that there may be any number of producers
and consumers simultaneously interacting with a topic. Depending on the actual solution context,
topologiesmayalsobepoint-to-multipoint,multipoint-to-point,andpoint-to-point.Kafkadoesnot
imposethesortsoflimitsthatoneisusedtoseeingfromthemore‘orthodox’messagingmiddleware.
It’sabouttimewelookedathowrecordsareconsumed.
A consumer is a process or thread that attaches to a Kafka cluster via a client library. A consumer
generally, but not necessarily, operates as part of an encompassing consumer group. Consumer
groups are effectively a load-balancing mechanism within Kafka — distributing partition assign-
mentsapproximatelyevenlyamongtheindividualconsumerinstanceswithinthegroup.Whenthe
first consumer in a group subscribes to the topic, it will receive all partitions in that topic. When
a second consumer subsequently joins, it will get approximately half of the partitions, relieving
the first consumer of half of its prior load. The process runs in reverse when consumers leave (by
disconnectingortimingout)—theremainingconsumerswillabsorbagreaternumberofpartitions.
This book will occasionally use the term ‘subscriber’ to collectively refer to all consumer
instancesinaconsumergroup,asasingle,logicalentity.Whenreferringtoeventstreams,
thenotionofasubscriberissometimesmoreintuitiveandhelpsdistinguishbetweenthose
consumerswhomaynotbeapartofaconsumergroupatall.Thosesortsofconsumerswill
bediscussedlater.
So, a consumer siphons records from a topic, pulling from the share of partitions that have been
assigned to it by Kafka, alongside the other consumers in its group. As far as load-balancing goes,
thisisnothingoutoftheordinary.Buthere’sthekicker—consumingarecorddoesnotremoveitfrom

Chapter3: ArchitectureandCoreConcepts 32
the topic. This might seem contradictory at first, especially if one associates the act of consuming
with depletion. (If anything, a consumer should have been called a ‘reader’, but let’s not dwell on
thechoiceofterminology.)Thesimplefactis,consumershaveabsolutelynoimpactonthetopicand
its partitions; a topic is an append-only log that may only be mutated by the producer, or by Kafka
itself as part of its housekeeping chores. Consumers are ‘cheap’, so to speak — you can have a fair
number of them tail the logs without stressing the cluster. This is a yet another point of distinction
betweenaneventstreamandatraditionalmessagequeue,andit’sacrucialone.
A consumer internally maintains an offset that points to the next record in a partition, advancing
the offset for every successive read. In fact, a consumer maintains a vector of such offsets — one
for each assigned partition. When a consumer first subscribes to a topic, whereby no offsets have
been registered for the encompassing consumer group, it may elect to start at either the head-end
or the tail-end of the topic. Thereafter,the consumer will acquirean offset vector and will advance
theoffsetsinternally,inlinewiththeconsumptionofrecords.
InKafkaterminology,the‘head’ofapartitioncorrespondstothelocationoftheendoffsets,
whilethe‘tail’ofthepartitionisthesideclosesttothebeginningoffsets.Thismightsound
confusingifKafkaisperceivedasaqueueofsorts,wherethehead-endofaqueuecanonically
correspondstothesidewhichhastheoldestelements.InKafka,theoldestelementsareat
thetail-end.
Since consumers across different consumer groups do not interfere, there may be any number
of them reading concurrently from the same topic. Consumers run at their own pace; a slow or
backloggedconsumerhasnoimpactonitspeers.
To illustrate this concept, consider a contrived scenario involving a topic with two partitions. Two
consumergroups—AandB—aresubscribedtothetopic.Eachgrouphasthreeconsumerinstances,
named A0, A1, A2, B0, B1, and B2. The relationship between topics, consumers, and groups is
illustratedbelow.

Chapter3: ArchitectureandCoreConcepts 33
Multipleconsumergroupssharingatopic
As part of fulfilling the subscriptions, Kafka will allocate partitions among the members of each
group.Inturn,eachgroupwillacquireandmaintainadedicatedsetofoffsetsthatreflecttheoverall
progress of the group through the topic. The sharing of the topic and the independent progress of
consumergroupsisdiagrammaticallydepictedbelow.

Chapter3: ArchitectureandCoreConcepts 34
Consumeroffsets
Upon careful inspection, the reader will notice that something is missing. Two things, in fact:
consumers A2 and B0 aren’t there. That is because Kafka ensures that a partition may only be
assigned to at most one consumer within its consumer group. (It is said ‘at most’ to cover the
case when all consumers are offline.) Because there are three consumers in each group, but only
two partitions, one consumer will remain idle — waiting for another consumer in its respective
group to depart before being assigned a partition. In this manner, consumer groups are not only a
load-balancingmechanism,butalsoafence-likeexclusioncontrol,usedtobuildhighlyperformant
pipelineswithoutsacrificingsafety,particularlywhenthereisarequirementthatarecordmayonly
behandledbyonethreadorprocessatanygiventime.
Consumergroupsalsoensureavailability,satisfyingthelivenesspropertyofadistributedconsumer
ecosystem. By periodically reading records from a topic, the consumer implicitly signals to the
clusterthatitisina‘healthy’state,therebyextendingtheleaseoveritspartitionassignment.Should
the consumer fail to read again within the allowable deadline, it will be deemed faulty and its
partitions will be reassigned — apportioned among the remaining ‘healthy’ consumers within its
group.

Chapter3: ArchitectureandCoreConcepts 35
A thorough discussion of thesafety and liveness properties of Kafka will be deferred until
Chapter 15: Group Membership and Partition Assignment. For the time being, the reader
is asked to accept an abridged definition: Liveness is a property that requires a system to
eventually make progress, completing all assigned work. Safety is a property that requires
thesystemtorespectallitskeyinvariants,atalltimes.
Toemployatransportationanalogy,atopicislikeahighway,whileapartitionisalane.Arecord
istheequivalentofacar,anditsoccupantscorrespondtotherecord’svalue.Severalcarscansafely
travel on the same highway, providing they keep to their lane. Cars sharing the same line ride in
a sequence, forming an orderly queue. Now suppose each lane leads to an off-ramp, diverting its
traffictosomelocation.Ifoneoff-rampgetsbankedup,otheroff-rampsmaystillflowsmoothly.
Itispreciselythishighway-lanemetaphorthatKafkaexploitstoachieveitstrademarkend-to-end
throughput,easilyreachingmillionsofrecordspersecondoncommodityhardware.Whencreating
atopic,onecansetthepartitioncount—thenumberoflanes,ifyouwill.Thepartitionsaredivided
approximatelyevenlyamongtheindividualconsumersinaconsumergroup,withaguaranteethat
no partition will be assigned to two (or more) consumers at the same time, providing that these
consumersarepartofthesameconsumergroup.Referringtoouranalogy,acarwillneverendupin
twooff-rampssimultaneously;however,twolanesmightconceivablymergetothesameoff-ramp.

Chapter3: ArchitectureandCoreConcepts 36
TheHighFiveInterchange,Dallas,Texas

Chapter3: ArchitectureandCoreConcepts 37
Committing offsets
It has already been said that consumers maintain an internal state with respect to their partition
offsets. At some point, that state must be shared with Kafka,so that when a partition is reassigned,
the new consumer can resume processing from where the outgoing consumer left off. Similarly, if
theconsumersweretodisconnect,uponreconnectiontheywouldideallyskipoveranyrecordsthat
havealreadybeenprocessed.
Persisting the consumer state back to the Kafka cluster is called committing an offset. Typically, a
consumer will read a record (or a batch of records) and commit the offset of the last record plus
one. If a new consumer takes over the topic, it will commence processing from the last committed
offset—hencetheplus-onestepisessential.(Otherwise,thelastprocessedrecordwouldbehandled
asecondtime.)
Curiousfact:Kafkaemploysarecursiveapproachtomanagingcommittedoffsets,elegantly
utilisingitselftopersistandtrackoffsets.Whenanoffsetiscommitted,thegroupcoordinator
willpublishabinaryrecordontheinternal__consumer_offsetstopic.Thecontentsofthis
topicarecompactedinthebackground,creatinganefficienteventstorethatprogressively
reducestoonlythelastknowncommitpointsforanygivenconsumergroup.
Controllingthepointwhenanoffsetiscommittedprovidesagreatdealofflexibilityarounddelivery
guarantees,furtherhighlightingKafka’sadaptabilitytowardsvariousmessagingscenarios.Theterm
‘delivery’ assumes not just reading a record, but the full processing cycle, complete with any side-
effects.(Forexample,updatingadatabase,orinvokingaservice.)Onecanshiftfromanat-most-once
toanat-least-once deliverymodelbysimplymovingthecommitoperationfromapointbefore the
processingofarecordiscommenced,toapointsometimeafter theprocessingiscomplete.Withthis
model, should the consumer fail midway through processing a record, it will be re-read following
partitionreassignment.
By default, a Kafka consumer will automatically commit offsets at an interval of at least every
five seconds. The interval will automatically be extended in the presence of in-flight records — the
records that are still being processed on the consumer. The lower bound on this interval can be
controlled by the auto.commit.interval.ms configuration property, which is discussed in Chapter
10:ClientConfiguration.Animplicationoftheoffsetauto-commitfeatureisthatitextendsthewin-
dowofuncommittedoffsetsbeyondthesetofin-flightrecords;inotherwords,theconsumermight
finish processing a batch of records without necessarily committing the offsets. If the consumer’s
partitions are then reassigned, the new consumer will end up processing the same batch a second
time. To constrain the window of uncommitted records, one needs to take offset committing into
theirownhands.Thiscanbedonebysettingtheenable.auto.commitclientpropertytofalse.
Gettingoffsetcommitsrightcanbetricky,androutinelycatchesoutbeginners.Acommittedoffset
implies that the record one below that offset and all prior records have been dealt with by the
consumer.Whendesigningat-least-onceapplications,anoffsetshouldonlybecommittedwhenthe
applicationhasdealtwiththerecordinquestion,andallrecordsbeforeit.Inotherwords,therecord

Chapter3: ArchitectureandCoreConcepts 38
hasbeenprocessedtothepointthatanyactionsthatwouldhaveresultedfromtherecordhavebeen
carried out and finalised. This may include calling other APIs, updating a database, committing
transactions, persisting the record’s payload, or publishing more records. Stated otherwise, if the
consumer were to fail after committing the record, then not ever seeing this record again must not
bedetrimentaltoitscorrectness.
Intheat-least-oncescenario,atypicalconsumerimplementationwillcommititsoffsetslinearly,in
tandemwiththeprocessingofarecordbatch.Thatis,readarecordbatchfromatopic,processthe
individual records, commit the outstanding offsets, read the next batch, and so on. This is called a
poll-process loop,illustratedbelow:

Chapter3: ArchitectureandCoreConcepts 39
Consumerpoll-processloop

Chapter3: ArchitectureandCoreConcepts 40
Thepoll-processloopintheabovediagramisasomewhatsimplifiedtakeonreality.Wewillnotgo
intothedetailsofhowrecordsarefetchedfromKafkawhenKafkaConsumer.poll()iscalled;amore
thorough description is presented in Chapter 7: Serialization. We will remark on one optimisation:
a consumer does not always fetch records directly from the cluster; it employs a prefetch buffer to
pipelinethisprocess.
A common tactic is to process a batch of records concurrently (where this makes sense), using a
threadpool,andonlyconfirmthelastrecordwhentheentirebatchisdone.Thecommitprocessin
Kafka is very efficient, the client library will send commit requests asynchronously to the cluster
using an in-memory queue, without blocking the consumer. The client application can register an
optionalcallback,notifyingitwhenthecommithasbeenacknowledgedbythecluster.Andthereis
alsoablockingvariantavailableshouldtheclientapplicationpreferit.
Free consumers
Theassociationofaconsumerwithaconsumergroupisanoptionalone,indicatedbythepresence
of a group.id consumer property. If unset, a free consumer is presumed. Free consumers do not
subscribetoatopic;instead,theconsumingapplicationisresponsibleformanuallyassigningasetof
topic-partitionstoitself,individuallyspecifyingthestartingoffsetforeachtopic-partitionpair.Free
consumersdonotcommittheiroffsetstoKafka;itisuptotheapplicationtotracktheprogressofsuch
consumers and persist their state as appropriate, using a datastore of their choosing. The concepts
of automatic partition assignment, rebalancing, offset persistence, partition exclusivity, consumer
heartbeatingandfailuredetection(safetyandliveness,inotherwords),andotherso-called‘niceties’
accordedtoconsumergroupsceasetoexistinthismode.
The use of the nominal expression ‘free consumer’ to denote a consumer without an
encompassing group is a coined term. It is not part of the standard Kafka nomenclature;
indeed,thereisnowidespreadterminologythatmarksthisformofconsumer.
Free consumers are not observed in the wild as often as their grouped counterparts. There are
predominantlytwousecaseswhereafreeconsumerisanappropriatechoice.Onesuchcaseiswhen
an application genuinely requires full control of the partition assignment scheme, likely utilising a
dedicated datastore to track consumer offsets. This is very rare. Needless to say, it is also difficult
toimplementcorrectly,giventhemultitudeofscenariosonemustaccountfor.Itismentionedhere
onlyforcompleteness.
The more commonly seen use case is when a stateless or ephemeral consumer needs to monitor
a topic. For example, an application might tail a topic to identify specific records, or just as a
monitoring or debugging aid. One might only care about records that were published when the
stateless consumer was online, so concerns such as persisting offsets and resuming from the last
processed record become largely irrelevant. A good example of where this is used routinely is the
Kafdrop tool, which we will explore in one of the upcoming chapters. When the user clicks on a

Chapter3: ArchitectureandCoreConcepts 41
topic to view the records, Kafdrop creates a free consumer and assigns the requested partition to it,
reading the records from the supplied offsets. Navigating to a different topic or partition will reset
theconsumer,discardinganypriorstate.
One scenario that benefits from free consumers is the implementation of the sync-over-async
pattern using Kafka. For example, a producer might issue a command-style request (or query) to a
downstreamconsumerandexpectaresponseonthesameordifferenttopic.Theinitiatingproducer
mightbeoperatinginasynchronouscontext;forexample,itmightberespondingtoasynchronous
requestofitsown,andsoithasnochoicebuttowaitforthedownstreamresponsebeforeproceeding.
To complicate matters, there might be multiple such initiators in operation, and it is essential that
theresponseisprocessedbythesameinitiatorthatissuedtheoriginalrequest.
The sync-over-async scenario is a special case of the stateless consumer scenario presented above.
The initiator starts by assigning itself all partitions of the response topic and resetting the offsets
to the high-water mark. It then publishes the request command with a unique identifier that will
be echoed in the response. (Typically, this is a UUID.) The downstream consumer will eventually
process the message and publish its response. Meanwhile, the initiator will poll the topic for
responses, filtering by ID. Eventually, either the response will arrive within a set deadline, or the
initiatorwilltimeout.Eitherway,theassignmentofthepartitionstotheinitiatoristemporary,and
nostateispreservedbetweensuccessiveassignments.
Summary of core concepts
Theillustrationbelowoutlinestherelationshipbetweenthecoreconceptspresentedinthischapter,
including entities such as the cluster, broker nodes, producers, topics, partitions, consumers, and
consumergroups.

Chapter3: ArchitectureandCoreConcepts 42
Relationshipsbetweencoreconcepts
Thekeytakeawaysare:
• A cluster hosts multiple topics, each having an assigned leader and zero or more follower
replicas.
• Topics are subdivided into partitions, with each partition forming an independent, totally-
orderedsequencewithinawider,partially-orderedstream.
• Multiple producers are able to publish to a topic, picking a partition at will. The partition
maybeselecteddirectly—byspecifyingapartitionnumber,orindirectly—bywayofarecord
key,whichdeterministicallyhashestoapartitionnumber.
• Partitions in a topic can be load-balanced across a population of consumers in a consumer
group,allocatingpartitionsapproximatelyevenlyamongthemembersofthatgroup.
• A consumer in a group is not guaranteed a partition assignment. Where the group’s
population outnumbers the partitions, some consumers will remain idle until this balance
equalisesortipsinfavouroftheotherside.

Chapter3: ArchitectureandCoreConcepts 43
• Aconsumerwillcommittheoffsetofarecordwhenitisdoneprocessingit.Thecommits
aredirectedtoaconsumercoordinator,whichwillendupwrittentoaninternal__consumer_-
offsets topic. The offset of the record is incremented by one before committing, to prevent
unnecessaryreplay.
• Partitions may be manually assigned to free consumers. If necessary, an entire topic may
beassignedtoasinglefreeconsumer—thisisdonebyindividuallyassigningallpartitions.
Event streaming platforms are a highly effective building block in the construction of modular,
loosely-coupled, event-driven applications. Within the world of event streaming, Kafka has solid-
ified its position as the go-to open-source solution that is both flexible and highly performant.
ConcurrencyandparallelismareattheheartofKafka’sarchitecture,formingpartially-orderedevent
streams that can be load-balanced across a scalable consumer ecosystem. A simple reconfiguration
ofconsumersandtheirencompassinggroupscanbringaboutvastlydifferenteventdistributionand
processing semantics; shifting the offset commit point can invert the delivery guarantee from an
at-most-oncetoanat-least-oncemodel.
Theconsumergroupisasomewhatunderstatedconceptthatispivotaltotheversatilityofanevent
streaming platform. By simply varying the affinity of consumers with their groups, one can arrive
at vastly different distribution topologies — from a topic-like, pub-sub behaviour to an MQ-style,
point-to-point model. Because records arenever truly consumed (the advancing offset only creates
the illusion of consumption), one can concurrently superimpose disparate distribution topologies
overasingleeventstream.

Chapter 4: Installation
Previous chapters have given us a reasonably grounded understanding of what Kafka is and isn’t,
where it is used, and how it dovetails into the rest of the software landscape. So far we have just
been circling the shore; time to dive in for a deeper look. Before we can get much further, we need
arunningKafkasetup.
Thiswillrequireustoinstallseveralthings:
1. KafkaandZooKeeper.RecallfromourearliercoverageoftheKafkaarchitecture,afunction-
ingsetuprequiresbothKafkaandZooKeepernodes.TheseareplainJavaapplicationswithno
other requirements or dependencies, and can run on any operating system and any hardware
supportedbyJava.
2. Kafdrop. While this isn’t strictly required to operate Kafka, Kafdrop is the most widely-used
web-based tool for working with Kafka, and would be widely considered as an essential item
ofyourKafkatoolkit.
3. AJavaDevelopmentKit(JDK).Kafkaispart-writteninScalaandpartinJava,andrequires
Javaversion8ornewertorun.Kafdropissomewhatmoremodern,requiringJava11.
If you haven’t done so already, install a copy of the JDK. Version 11 or newer will do. The rest of
thechapterassumesthatyouhaveaJDKinstalled.
The requirement for JDK 11 is to accommodate Kafdrop. If you are installing Kafka on its
own,JDK8willsuffice.However,Java11isstillrecommendedoverJava8asitisthecurrent
Long-TermSupport (LTS)version.ThelastfreepublicupdatesofJDK8willhaveendedin
January 2019. Free updates for JDK 11 will continue through to September 2021, by which
pointtheworldwouldhavetransitionedtoJDK17LTS.(LTSreleasesarepubliclysupported
forthreeyears.)
Installing Kafka and ZooKeeper
Thereareatleastfouravenuesatone’sdisposalforinstallingKafkaandZooKeeper:
1. RunKafkaandZooKeeperusingDocker.
2. Install Kafka and ZooKeeper using a package manager, such as DNF (formerly known as
YUM) for RedHat, CentOS and Fedora Linux distributions, APT for Debian and Ubuntu, and
HomebrewformacOS.Therearemanyothers.

Chapter4: Installation 45
3. ClonetheKafkaand ZooKeeperrepositoriesandbuildfromsourcecode.
4. DownloadandunpacktheofficialKafkadistributionfromkafka.apache.org/downloads¹,which
comesbundledwithZooKeeper.
Let’s briefly touch upon each of these options. It might sound like overkill at first — we could just
pick the easiest and get cracking — but we’re in it for the long haul. And sometimes the easiest
approachisn’ttherightone.
Dockerisanexcellentall-roundapproachforgettingstartedwithKafka,developingagainstalocal
Kafkabroker,andevenrunningKafkainaproductionconfiguration.Andbestofall,aDockerimage
willcomewithanappropriateversionoftheJDK.
Thereisonedrawback,however.KafkainDockerisnotoriouslydifficulttoconfigure,aseverything
isbakedinandnotdesignedforchange.(That’snottosayitcan’tbedone.)Wewon’tgodownthe
Docker path for now, just because we will be making lots of changes to the broker configuration
at various points along our journey, and ideally, we would want this process to be as simple and
painlessaspossible.
InstallingKafkaandZooKeeperfromapackageisconvenienttoo.Andwhileitdoesn’tcomebundled
with a JDK, a package will typically declare the JDK as a dependency, which the package manager
willattempttoresolveatthepointofinstallation(orwhenupdatingthepackage).Still,thisapproach
isn’t without its drawbacks: there may be other applications installed on the target machine that
might require a different version of the JDK, which would warrant further configuration of Kafka
andZooKeepertowireituptothecorrectJDK.
Another drawback to the ‘packaged Kafka’ approach is that the installation path and the layout of
the files will vary depending on the chosen package manager. For example, on macOS, Homebrew
installsKafkainto/usr/local/etc,/usr/local/bin,and/usr/local/var/lib/.Ontheotherhand,
YUM will install it under /bin, /opt, and /var/lib. This makes it tremendously difficult to write
aboutandincludeworkedexamplesthatworkconsistentlyforallreaders.Ratherthanfocusingon
thesubjectmatter,thisbookwouldhavebeenpollutedwithexcerptsandcall-outstargetingdifferent
operatingsystemsandpackagemanagers.
The third option is building from source code. It might sound a bit extreme for someone who has
just opened a book on Kafka. Nonetheless, it is a valid option and the only option if you happen
to be a contributor. Understandably, we won’t delve into it much deeper, and blissfully pretend it
doesn’texist—atleastintheuniverseboundbythisbook.
The final option, and the one we will inevitably proceed with, is to download the latest version of
theofficialKafkatarballfromkafka.apache.org/downloads².Theremightbeseveraloptions—pick
the one in ‘Binary downloads’ that targets the latest version of Scala, as shown in the screenshot
below.
¹https://kafka.apache.org/downloads
²https://kafka.apache.org/downloads

Chapter4: Installation 46
Downloadoptions
Copy the downloaded .tgz file into a directory of your choice, and unpack with tar zxf kafka_-
2.13-2.4.0.tgz, replacing the filename as appropriate. (In this example, the downloaded version
is 2.4.0, but your version will likely be newer.) The files will be unpacked to a subdirectory named
kafka_2.13-2.4.0.WewillrefertothisdirectoryastheKafkahomedirectory.
Whenreferringtothehomedirectoryfromacommand-lineexample,we’llusetheconstant$KAFKA_-
HOME.Youhavethechoiceofeithermanuallysubstituting$KAFKA_HOMEfortheinstallationdirectory,
orassigningtheinstallationpathtotheKAFKA_HOMEenvironmentvariable,asshownintheexample
below.
export KAFKA_HOME=/Users/me/opt/kafka_2.13-2.4.0
You would need to run export KAFKA_HOME... at the beginning of every terminal session.
Alternatively,youcanappendtheexport ...commandtoyourshell’sstartupfile.Ifyou
areusingBash,thisistypically(cid:24)/.bashrcor(cid:24)/.bash_profile.

Chapter4: Installation 47
Take a moment to look around the home directory. You’ll see several subdirectories, chief among
thembeingbin,libs,andconfig.
.
├── LICENSE
├── NOTICE
├── bin
├── config
├── libs
└── site-docs
ThebindirectorycontainsthescriptstostartandstopKafkaandZooKeeper,aswellasvariousCLI
(command-lineinterface)utilitiesforworkingwithKafka.Also,bincontainsawindowssubdirectory,
which(you’veguessedit)containstheequivalentscriptsforMicrosoftWindows.
bin
├── connect-distributed.sh
├── connect-standalone.sh
├── kafka-acls.sh
├── kafka-broker-api-versions.sh
├── kafka-configs.sh
├── kafka-console-consumer.sh
├── kafka-console-producer.sh
├── kafka-consumer-groups.sh
├── kafka-consumer-perf-test.sh
├── kafka-delegation-tokens.sh
├── kafka-delete-records.sh
├── kafka-dump-log.sh
├── kafka-log-dirs.sh
├── kafka-mirror-maker.sh
├── kafka-preferred-replica-election.sh
├── kafka-producer-perf-test.sh
├── kafka-reassign-partitions.sh
├── kafka-replica-verification.sh
├── kafka-run-class.sh
├── kafka-server-start.sh
├── kafka-server-stop.sh
├── kafka-streams-application-reset.sh
├── kafka-topics.sh
├── kafka-verifiable-consumer.sh
├── kafka-verifiable-producer.sh
├── trogdor.sh
├── windows

Chapter4: Installation 48
├── zookeeper-security-migration.sh
├── zookeeper-server-start.sh
├── zookeeper-server-stop.sh
└── zookeeper-shell.sh
The config directory is another important one. It contains .properties files that are used to
configurethevariouscomponentsthatmakeuptheKafkaecosystem.
config
├── connect-console-sink.properties
├── connect-console-source.properties
├── connect-distributed.properties
├── connect-file-sink.properties
├── connect-file-source.properties
├── connect-log4j.properties
├── connect-standalone.properties
├── consumer.properties
├── log4j.properties
├── producer.properties
├── server.properties
├── tools-log4j.properties
├── trogdor.conf
└── zookeeper.properties
The lib directory contains the Kafka binary distribution, as well as its direct and transitive depen-
dencies.You’llneverneedtomodifythecontentsofthisdirectory.
Launching Kafka and ZooKeeper
Now that the applications have been installed, we can start them. The first cab off the rank will be
ZooKeeper,asitisaruntimerequirementforKafka.Runthefollowingcommandinaterminal:
$KAFKA_HOME/bin/zookeeper-server-start.sh \
$KAFKA_HOME/config/zookeeper.properties
ThiswilllaunchZooKeeperinforegroundmode.Youshouldseeabunchofmessagesloggedtothe
console,signifyingthestartingofZooKeeper.Amongthem,youmightspotonewarningmessage:

Chapter4: Installation 49
[2019-12-25 13:30:36,951] WARN Either no config or no quorum defined ￿
in config, running in standalone mode (org.apache.zookeeper. ￿
server.quorum.QuorumPeerMain)
All this is saying is that we started ZooKeeper in standalone mode, without configuring a quorum.
WhenrunningZooKeeperlocally,availabilityisrarelyaconcern,andastandalone(ensembleofone
membernode)configurationissufficient.
Recall from a prior discussion on the Kafka architecture, it was stated that ZooKeeper
acts as an arbiter — electing a sole controller among the available Kafka broker nodes.
Internally,ZooKeeperemploysanatomicbroadcastprotocoltoagreeonandsubsequently
maintain a consistent view of the cluster state throughout the ZooKeepr ensemble. This
protocoloperatesontheconceptofamajorityvote,alsoknownasquorum,whichinturn,
requiresan odd number of participating ZooKeeper nodes. When running in a production
environment,ensurethatatleastthreenodesaredeployedinamannerthatnopairofnodes
maybeimpactedbythesamecontingency.Ideally,ZooKeepernodesshouldbedeployedin
geographicallyseparatedatacentres.
NowthatZooKeeperisrunning,wecanstartKafka.Runthefollowinginanewterminalwindow:
$KAFKA_HOME/bin/kafka-server-start.sh \
$KAFKA_HOME/config/server.properties
Kafka’s logs are a bit more verbose than ZooKeeper’s. The first really useful part of the log relates
to the ZooKeeper connection. Specifically, which ZooKeeper instance(s) Kafka is trying to connect
to,andthestatusoftheconnection:
[2019-12-25 14:02:21,380] INFO Initiating client connection, ￿
connectString=localhost:2181 sessionTimeout=6000 watcher= ￿
kafka.zookeeper.ZooKeeperClient$ZooKeeperClientWatcher$@ ￿
624ea235 (org.apache.zookeeper.ZooKeeper)
[2019-12-25 14:02:21,399] INFO [ZooKeeperClient Kafka server] ￿
Waiting until connected. (kafka.zookeeper.ZooKeeperClient)
[2019-12-25 14:02:21,401] INFO Opening socket connection to server ￿
localhost/0:0:0:0:0:0:0:1:2181. Will not attempt to ￿
authenticate using SASL (unknown error) (org.apache.zookeeper. ￿
ClientCnxn)
[2019-12-25 14:02:21,416] INFO Socket connection established to ￿
localhost/0:0:0:0:0:0:0:1:2181, initiating session ￿
(org.apache.zookeeper.ClientCnxn)
[2019-12-25 14:02:21,458] INFO Session establishment complete on ￿
server localhost/0:0:0:0:0:0:0:1:2181, sessionid = ￿

50
| Chapter4: Installation |     |            |         |          |
| ---------------------- | --- | ---------- | ------- | -------- |
| 0x100433a40960000,     |     | negotiated | timeout | = 6000 ￿ |
(org.apache.zookeeper.ClientCnxn)
[2019-12-25 14:02:21,461] INFO [ZooKeeperClient Kafka server] ￿
| Connected. | (kafka.zookeeper.ZooKeeperClient) |     |     |     |
| ---------- | --------------------------------- | --- | --- | --- |
AmongthelogswecanalsofindthecompleteKafkabrokerconfiguration:
| [2019-12-25                                 | 14:02:22,118] | INFO   | KafkaConfig | values: |
| ------------------------------------------- | ------------- | ------ | ----------- | ------- |
| advertised.host.name                        |               | = null |             |         |
| advertised.listeners                        |               | = null |             |         |
| advertised.port                             | =             | null   |             |         |
| alter.config.policy.class.name              |               |        | = null      |         |
| alter.log.dirs.replication.quota.window.num |               |        |             | = 11    |
alter.log.dirs.replication.quota.window.size.seconds = 1
| authorizer.class.name                     |        | =        |        |       |
| ----------------------------------------- | ------ | -------- | ------ | ----- |
| auto.create.topics.enable                 |        | = true   |        |       |
| auto.leader.rebalance.enable              |        |          | = true |       |
| background.threads                        |        | = 10     |        |       |
| broker.id                                 | = 0    |          |        |       |
| broker.id.generation.enable               |        | =        | true   |       |
| broker.rack                               | = null |          |        |       |
| client.quota.callback.class               |        | =        | null   |       |
| compression.type                          | =      | producer |        |       |
| connection.failed.authentication.delay.ms |        |          |        | = 100 |
...
| (rest of | the log omitted | for | brevity) |     |
| -------- | --------------- | --- | -------- | --- |
This is actually more useful than one might initially imagine. The Kafka broker configuration is
defined in $KAFKA_HOME/config/server.properties, but the file is relatively small and initially
contains mostly commented-out entries. This means that most settings are assigned their default
values. Rather than consulting the official documentation to determine what the defaults might
be and whether or not they are actually overridden in your configuration, you need only look at
the broker logs. This is particularly useful when you need to debug the configuration. Suppose a
particularconfigurationvalueisn’tbeingappliedcorrectly—perhapsduetoasimpletypo,ormaybe
becausetherearetwoentriesforthesameconfigurationkey.TheconfigurationprintoutinKafka’s
| logsprovidesitsvantagepoint—as |     |     | seenfromtheeyesofthebroker. |     |
| ------------------------------ | --- | --- | --------------------------- | --- |
Thenextusefulbitofinformationisemittedbythesocketlistener:
[2019-12-25 14:02:22,587] INFO Awaiting socket connections on ￿
| 0.0.0.0:9092. | (kafka.network.Acceptor) |     |     |     |
| ------------- | ------------------------ | --- | --- | --- |

Chapter4: Installation 51
ThistellsusthatKafkaislisteningforinboundconnectionsonport9092,andisboundtoallnetwork
interfaces (indicated by the IP meta-address0.0.0.0). This is corroborated by the deprecated prop-
erty port, which defaults to 9092. There is a much more sophisticated mechanism for configuring
listeners,whichwewillexamineinoneofthefollowingchapters.Fornow,a0.0.0.0:9092listener
willsuffice.
Believeitornot,themostusefulinformationonecangetoutofKafka’slogsisactuallytheversion
number. Admittedly, it sounds somewhat banal, but how many times have you stared helplessly at
thescreenwonderingwhyapieceofsoftwarethatwasjustupgradedtothelatestversionstillhasthe
samebugthattheauthorshavesworntheyhadfixed?Invariably,itisalwayssomesimplemistake
— a symlink to the wrong binary, a typo in the path, a wrong value in an environment variable, or
some other moth-eaten stuff-up along those lines. Printing the application version number in the
logsisasimplewayoferadicatingtheseclassesoferrors.
Running in the background
WhenlaunchingZooKeeperorKafka,youhavetheoptionofrunningitasadaemonbypassingitthe
-daemonflag.Insimpleterms,thismeanslaunchingZooKeeperinthebackground,withoutholding
uptheterminal.KilltheexistingZooKeeperprocessbypressingCTRL+C,andtrythefollowing:
$KAFKA_HOME/bin/zookeeper-server-start.sh -daemon \
$KAFKA_HOME/config/zookeeper.properties
That’sall welland good, but wherehavethe logs gone?Whenlaunched as adaemon, the standard
outputoftheZooKeeperprocessispipedto$KAFKA_HOME/logs/zookeeper.out.Wecantailthelogs
byrunning:
tail -f $KAFKA_HOME/logs/zookeeper.out
To stop a daemon ZooKeeper process, run $KAFKA_HOME/bin/zookeeper-server-stop.sh. This will
stopthebackgroundprocessifthereisonerunning.Ifnot,itwillrespondwithNo zookeeper server
to stop.
ZooKeeperandKafkashellscriptsareessentiallymirrorimagesofeachother.TolaunchKafkaasa
daemon,run:
$KAFKA_HOME/bin/kafka-server-start.sh -daemon \
$KAFKA_HOME/config/server.properties
Kafka standard output logs are written to $KAFKA_HOME/logs/kafkaServer.out. To stop a daemon
Kafkaprocess,run$KAFKA_HOME/bin/kafka-server-stop.sh.

Chapter4: Installation 52
Installing Kafdrop
Next on our list is Kafdrop. It’s a Java application with no dependencies, and the avenues for
installing it are mostly similar to Kafka, except it does not offer package-based installation. In
practice, Docker largely obviates the need for packages, and the sheer number of Kafdrop Docker
pulls(overamillionatthetimeofwriting)isatestamenttothat.
As practical as a Docker image may be, we are going to ditch this option for now. Because we are
running Kafka on localhost, Docker will struggle to connect to our broker, as Docker containers
are normally unaware of processes running on the host machine. There is a way to change this
but it is not portable across Linux and macOS, and will also require changes to the Kafka broker
configuration—somethingwearenotyetpreparedtodo.WewillrevisitDockerlater.Fornow,we
willgowiththeofficialKafdropbinarydistribution.
KafdropbinariesarehostedonBintray,withadownloadlinkembeddedineachreleaseonGitHub.
Openthereleasespage:github.com/obsidiandynamics/kafdrop/releases³andpickthelatestfromthe
list.Alternatively,youcannavigatestraighttothelatestKafdropreleasebyfollowingthisshortcut:
github.com/obsidiandynamics/kafdrop/releases/latest⁴.
Thereleasewilltypicallyincludeanoutlineofchanges,andwillcontaina‘DownloadfromBintray’
link,asshownintheexamplebelow:
³https://github.com/obsidiandynamics/kafdrop/releases
⁴https://github.com/obsidiandynamics/kafdrop/releases/latest

Chapter4: Installation 53
DownloadKafdroprelease
Clicking on the link will download a .jar file. Save it in a directory of your choice and run it as
shownintheexamplebelow,replacingthefilenameasappropriate.
java -jar kafdrop-3.18.0.jar --kafka.brokerConnect=localhost:9092
Once started, you can open Kafdrop in your browser by navigating to localhost:9000⁵. You’ll be
presentedwithaKafdropcluster overviewscreen,showingourfresh,single-nodeKafkacluster.
⁵http://localhost:9000

Chapter4: Installation 54
Kafdropclusteroverview
There are a few things of interest here. On the top-right corner, you should see the Kafdrop release
versionandbuildstamp.ThiscanbeveryusefulifyouareconnectingtoaremoteKafdropinstance,
anddon’thavethelogsthatdisclosewhichversionofKafdropisrunning.
The next section provides a summary of the cluster. Note the ‘Bootstrap servers’ field: it mirrors
the --kafka.brokerConnect command-line argument, telling us how Kafdrop has been configured
todiscovertheKafkanodes.
Bootstrappingandbrokerdiscoveryisawholetopiconitsown,whichwearegoingtogloss
over for now. For the time being, and unless stated otherwise, assume that the ‘bootstrap
servers’ list is localhost:9092. We will revisit this topic in Chapter 8: Bootstrapping and
AdvertisedListeners.
The‘Brokers’sectionenumeratesovertheindividualbrokersinthecluster.Wearerunningasingle-
brokersetupjustnow,soseeingaone-linetableentryshouldcomeasnosurprise.Naturally,being
theonlybrokerinthecluster,itwillhavebeenassignedthecontrollerrole.

55
| Chapter4: | Installation |     |     |     |     |     |
| --------- | ------------ | --- | --- | --- | --- | --- |
The‘Topics’sectionisempty,aswehaven’tcreatedanytopicsyet.NordowehaveanyACLsdefined.
Thisisallyettocome.
Switch back to the shell running Kafdrop. Looking over the standard output logs we can spot the
versionnumber.Keeplookingandyou’llnoticetheportthatKafdropislisteningonandthecontext
path.Thisisallconfiguration,andit’ssomethingthatmayneedtochangebetweenenvironments.
2019-12-25 19:08:49.465 INFO 82515 [main] k.s.BuildInfo: Kafdrop ￿
| version: | 3.18.0, | build | time: | 2019-12-02T08:36:13.356Z |     |     |
| -------- | ------- | ----- | ----- | ------------------------ | --- | --- |
...
| (some logs | omitted) |     |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- | --- |
...
| 2019-12-25                | 19:08:50.752 | INFO    | 82515    | [main] o.s.b.w.e.u. |            | ￿   |
| ------------------------- | ------------ | ------- | -------- | ------------------- | ---------- | --- |
| UndertowServletWebServer: |              |         | Undertow | started             | on port(s) | ￿   |
| 9000                      | (http) with  | context | path     | ''                  |            |     |
WehavelearnedaboutthevariouswaysonecanobtainandinstallaKafkaandZooKeeperbundle.
WehavealsostartedandstoppedabasicZooKeeperandKafkasetup,learnedaboutforegroundand
daemonmodes,andsurveyedthelogsforusefulinformation.Finally,weinstalledKafdropandtook
abrieflookaround.Thesceneisnowset;wejustneedtomakeuseofitallsomehow.

Chapter 5: Getting Started
With the theoretical foundations nailed, and a fresh installation of Kafka standing by, it is time to
rollupoursleevesforamorepracticalapproachtolearningKafka.
This chapter will focus on the two fundamental operations: publishing records to Kafka topics and
subsequentlyconsumingthem.Wearegoingtoexplorethevariousmechanismsforinteractingwith
thebrokerandalsoforexploringthecontentsoftopicsandpartitions.
Publishing and consuming using the CLI
When discussing producers and consumers, the first thing that might spring to mind is a set of
bespokeapplicationsthatsomeone—anindividual,ormorelikely,ateamofdevelopers—willbuild
andmaintainaspartofoperatingabroaderevent-streamingsystem.Butonedoesnotneedafully-
fledged application to publish to or consume from a Kafka topic — this task can be accomplished
using the set of CLI (command-line interface) tools that are shipped with Kafka, located in the
$KAFKA_HOME/bindirectory.
Creating a topic
Let’s get started then. The first thing is to create a topic, which can be accomplished using the
kafka-topics.shtool:
$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
--create --partitions 3 --replication-factor 1 \
--topic getting-started
Observe,althoughtheparameter--bootstrap-serverisnamedinsingularform,thekafka-topics.sh
toolwill,ratherunexpectedly,acceptacomma-separatedlistofbrokers.Wehavespecifiedlocalhost:9092
as the bootstrap server, because that is where our test cluster is currently running. If you are using
a remote Kafka broker or a managed Kafka service, you will have been provided with an alternate
listofbrokeraddresses.
ThepackagedCLIutilitiesarenotthemostintuitiveoftoolsthatonecanusewithKafka;in
fact,theyarewidelyregardedasbeingawkwardtouseandbarelyadequateinfunctionality.
MostKafkapractitionershavelongabandonedtheout-of-the-boxutilitiesinfavourofother
open-sourceandcommercialtools;Kafdropisonesuchtool,butthereareseveralothers.This
bookcoversthepackagedCLItoolsbecausethatiswhatyouaresuretogetwitheveryKafka
installation.Havingbasicawarenessofthebuilt-intoolingisaboutasessentialasknowing
the basic vi commands when working in Linux — you can berate the archaic tooling and
laudthealternatives,butthatwillonlygetyousofarasyourfirstproductionincident.(In
sayingthat,comparingKafka’sbuilt-intoolingtoVimisatravesty.)

Chapter5: GettingStarted 57
SwitchingtoKafdrop,wecan see thegetting-startedtopicappearin the‘Topics’section.Ifthere
are lots of topics, you can use the filter text box in the table area to refine the displayed topics to
justthosethatmatchachosensubstring.
Kafdrop—showingtopics
Wecantellataglancethatthetopichasthreepartitions,thetopichasnoreplicationissues,andthat
no custom configuration has been specified for this topic. The mechanics for specifying per-topic
configurationwillbeexplainedinChapter9:BrokerConfiguration.
Now,wecouldhavejustaseasilycreatedatopicbyclickingthe‘New’buttonunderthetopicslist:

Chapter5: GettingStarted 58
Kafdrop—creatinganewtopic
However, the point of the exercise is to demonstrate the CLI tool, rather than to explore all the
possiblewaysonecancreateatopicinKafka.
Thismightbeagoodseguetodiscusstheimportanceofexplicittopiccreation.Kafkadoesnotrequire
clients to create topics by default. When the auto.create.topics.enable broker configuration
property is set to true, Kafka will automatically create the topic when clients attempt to produce,
consume,orfetchmetadataforanon-existenttopic.Thismightsoundlikeaniftyideaatfirst,but
thedrawbackssignificantlyoutweightheminorconvenienceonegetsfromnothavingtocreatethe
topicexplicitly.
Firstly,Kafka’sdefaultstobackasfaras2011,andaregenerallyoptimisedforthesortsofusecases
thatKafkawasoriginallydesignedfor—highvolumelogshipping.Manythingshavechangedsince;
asithappens,Kafkaisnolongeraone-trickpony.Assuch,onewouldtypicallywanttoconfigure
thetopicatthepointofcreation,orimmediatelythereafter—certainlybeforeitgetsarealworkout.
Secondly, the partition count: Kafka allows you to specify the default number of partitions for all
newly created topics using the num.partitions broker setting, but this is largely a meaningless
number. Topics should be sized individually on the basis of expected parallelism, and a number

Chapter5: GettingStarted 59
ofotherfactors,whicharediscussedinChapter6:DesignConsiderations.Specifyingthepartition
countrequiresexplicittopiccreation.Asimilarstatementmightbemaderegardingthereplication
factor,butitisarguablyeasiertoagreeonasensibledefaultforthereplicationfactorthanitisfor
thepartitioncount.
Finally, having Kafka auto-create topics when a client subscribes to a topic or simply fetches the
topic metadata is careless, to put it mildly. A misbehaving client may initiate arbitrary metadata
queriesthatcouldinadvertentlycreateacopiousnumberofstraytopics.
Publishing records
With the topic creation out of the way, let’s publish a few records. We are going to use the
kafka-console-producer.shtool:
$KAFKA_HOME/bin/kafka-console-producer.sh \
--broker-list localhost:9092 \
--topic getting-started --property "parse.key=true" \
--property "key.separator=:"
Recordsareseparatedbynewlines.Thekeyandthevaluepartsaredelimitedbycolons,asindicated
bythekey.separatorproperty.Forthesakeofanexample,typeinthefollowing(acopy-pastewill
do):
foo:first message
foo:second message
bar:first message
foo:third message
bar:second message
PressCTRL+Dwhendone.Theterminalechoesarightanglebracket(>)foreveryrecordpublished.
Note, the kafka-topics.sh tool uses the --bootstrap-server parameter to configure the
Kafkabrokerlist,whilekafka-console-producer.shusesthe--broker-listparameterfor
anidenticalpurpose.Also,--propertyargumentsarelargelyundocumented—beprepared
toGoogleyourwayaround.
At this point we can switch back to Kafdrop and view the contents of the getting-started topic.
Wearepresentedwithanoverviewofthetopic,alongwithadetailedbreakdownoftheunderlying
partitions:

Chapter5: GettingStarted 60
Kafdrop—viewtopiccontents
Focusingonthepartitiondetail,wecantellataglancethatofthethreepartitions,twohavedataand
oneisempty.The‘firstoffset’and‘lastoffset’columnscorrespondtothelow-waterandhigh-water
marks, respectively. As the reader might recall from Chapter 3: Architecture and Core Concepts,
subtractingthetwoyieldsthemaximumnumberofrecordspersistedinthepartition.Let’sclickon
partition#2.Kafdropwillshowtheindividualrecords,arrangedinchronologicalorder.
Kafdrop—viewpartitioncontents

Chapter5: GettingStarted 61
Incaseyouwerewondering,thearrowtotheleftoftherecordletsyouexpandandpretty-
print JSON-encoded records. As our examples didn’t use JSON, there’s nothing to pretty-
print.
Consuming records
$KAFKA_HOME/bin/kafka-console-consumer.sh \
--bootstrap-server localhost:9092 \
--topic getting-started --group cli-consumer --from-beginning \
--property "print.key=true" --property "key.separator=:"
Theterminalwillechothefollowing:
bar:first message
bar:second message
foo:first message
foo:second message
foo:third message
Becausetheconsumerisrunningasasubscription,withaprovidedconsumergroup,theoutputwill
stall on the last record. The consumer will effectively tail the topic — continuously polling for new
recordsandprintingthemastheyarriveonthetopic.Toterminatetheconsumer,pressCTRL+D.
Note that we specified the --from-beginning flag when invoking the command above. By default,
a first-time consumer (for a previously non-existent group) will have its offsets reset to the topic’s
high-water mark. In order to read the previously published records, we override the default offset
reset strategy to tail from the topic’s low-water mark. If we run the same command again, we will
seenorecords—theconsumerwillhalt,waitingforthearrivalofnewrecords.
There is no --from-end flag. To tail from the end of the topic, simply delete the consumer
offsetsandstarttheCLIconsumer.Deletingoffsetsandotheroffsetmanipulationcommands
aredescribedinthesectionthatfollows.
Havingconsumedthebacklogofrecordswiththenewcli-consumerconsumergroup,wecannow
switch back to Kafdrop to observe the addition of the new group. The new group appears in the
topicoverviewscreen,underthesection‘Consumers’,inthebottom-right.

Chapter5: GettingStarted 62
Kafdrop—consumergroupsforatopic
Clickingthroughtheconsumerlinktakesustotheconsumeroverview.Thisscreenenumeratesover
alltopicswithintheconsumer’ssubscription,aswellastheper-partitionoffsetsforeachtopic.
Kafdrop—consumeroverview
In our example, the consumer offset recorded for each partition is the same as the respective high-
watermark.Theconsumerlagiszeroforeachcolumn.Thisisthedifferencebetweenthecommitted
offsetandthehigh-watermark.Whenthelagiszero,itmeansthattheconsumerhasworkedthrough
the entire backlog of records for the partition; in other words, the consumer has caught up to the
producer.Lagmayvarybetweenpartitions—thebusierthepartition,intermsofrecordthroughput,
the more likely it will accumulate lag. The aggregate lag (also known as the combined lag) is the
sumofallindividualper-partitionlags.

Chapter5: GettingStarted 63
Among the useful characteristics of tools such as Kafdrop and the Kafka CLI is the ability
toenumerateandmonitorindividualconsumergroups—inspecttheper-partitionlagsand
spotleadingindicatorsofdegradedconsumerperformanceor,intheworst-casescenario,a
stalledconsumer.Muchlikeanyothermiddleware,asolidcomprehensionoftheavailable
tooling—beitthebuilt-insuiteortheexternaltools—isessentialtoeffectiveoperation.This
is particularly crucial for overseeing mission-critical systems in production environments,
whereminutesofdowntimeandthefruitlesshead-scratchingoftheengineeringandsupport
personnelcanresultinsignificantincurredlosses.
Sothereyouhaveit.WehavepublishedandconsumedrecordsfromaKafkatopicusingthebuilt-in
CLItools.Itisn’tmuch,butit’sastart.
Useful CLI commands
To close off the section on the CLI, we will take a brief look at the other useful actions that can be
performedusingthebuilt-intools.
Listing topics
Thekafka-topics.shtoolcanbeusedtolisttopics,aspertheexamplebelow.
$KAFKA_HOME/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--list --exclude-internal
The--exclude-internalflag,asthenamesuggests,eliminatestheinternaltopics(e.g.__consumer_-
offsets)fromthequeryresults.
Describing a topic
By passing the --describe flag and a topic name to kafka-topics.sh, we can get more detailed
informationaboutaspecifictopic,includingthepartitionleaders,followerreplicas,andthein-sync
replicaset:
$KAFKA_HOME/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--describe --topic getting-started
Produces:

Chapter5: GettingStarted 64
Topic: getting-started PartitionCount: 3 ReplicationFactor: 1 ￿
Configs: segment.bytes=1073741824
Topic: getting-started Partition: 0 Leader: 0 Replicas: 0 Isr: 0
Topic: getting-started Partition: 1 Leader: 0 Replicas: 0 Isr: 0
Topic: getting-started Partition: 2 Leader: 0 Replicas: 0 Isr: 0
Deleting a topic
Todeleteanexistingtopic,usethekafka-topics.shtool.Theexamplebelowdeletesthegetting-started
topicfromourtestcluster.
$KAFKA_HOME/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--topic getting-started --delete
Topic deletion is an asynchronous operation — a topic is initially marked for deletion, to be subse-
quentlycleanedupbyabackgroundprocessatanindeterminatetimeinthefuture.In-betweenthe
markingandthefinaldeletion,atopicmightappeartolingeraround—onlytodisappearmoments
later.
Theasynchronousbehaviouroftopicdeletionshouldbetakenintoaccountwhendealingwithshort-
livedtopics—forexample,whenconductinganintegrationtest.Thelattertypicallyrequiresastate
reset between successive runs, wiping associated database tables and event streams. Because there
is no equivalent of a blocking DELETE TABLE DDL operation in Kafka, one must think outside the
box.Theoptionsare:
1. Forciblyresetconsumeroffsetstothehigh-watermarkpriortoeachtest,deletetheoffsets,or
deletetheconsumergroup(allthreewillachieveequivalentresults);
2. Truncatetheunderlyingpartitionsbyshiftingthelow-watermark(truncationwillbedescribed
shortly);or
3. Useunique,disposabletopicnamesforeachtest,deletinganyephemeraltopicswhenthetest
ends.
The latter is the recommended option, as it creates due isolation between tests and allows multiple
teststooperateconcurrentlywithnomutually-observableside-effects.
Truncating partitions
Althoughapartitionisbackedbyanimmutablelog,Kafkaoffersamechanismtotruncateallrecords
intheloguptoauser-specifiedlow-watermark.ThiscanbeachievedbypassingaJSONdocument
to the kafka-delete-records.sh tool, specifying the topics and partitions for truncation, with the
newlow-watermarkintheoffsetattribute.Severaltopic-partition-offsettriplescanbespecifiedas
a batch. In the example below, we are truncating the first record fromgetting-started:2, leaving
recordsatoffset1andnewerintact.

65
| Chapter5: | GettingStarted          |     |     |     |     |     |     |
| --------- | ----------------------- | --- | --- | --- | --- | --- | --- |
| cat <<    | EOF > /tmp/offsets.json |     |     |     |     |     |     |
{
| "partitions": |     | [                  |     |              |     |              |     |
| ------------- | --- | ------------------ | --- | ------------ | --- | ------------ | --- |
| {"topic":     |     | "getting-started", |     | "partition": |     | 2, "offset": | 1}  |
],
| "version": | 1   |     |     |     |     |     |     |
| ---------- | --- | --- | --- | --- | --- | --- | --- |
}
EOF
| $KAFKA_HOME/bin/kafka-delete-records.sh |     |     |                   |     | \   |     |     |
| --------------------------------------- | --- | --- | ----------------- | --- | --- | --- | --- |
| --bootstrap-server                      |     |     | localhost:9092    |     | \   |     |     |
| --offset-json-file                      |     |     | /tmp/offsets.json |     |     |     |     |
In an analogous manner, we can truncate the entire partition by specifying the current high-water
markintheoffsetattribute.
| Listing | consumer |     | groups |     |     |     |     |
| ------- | -------- | --- | ------ | --- | --- | --- | --- |
Thekafka-consumer-groups.shtoolcanbeusedtoqueryKafkaforalistofconsumergroups.
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |     |     |                |     | \      |     |     |
| ---------------------------------------- | --- | --- | -------------- | --- | ------ | --- | --- |
| --bootstrap-server                       |     |     | localhost:9092 |     | --list |     |     |
The result is a newline-separated list of consumer group names. This output format conveniently
allowsustoiterateovergroups,enactingrepetitivegroup-relatedadministrativeoperationsfroma
shellscript.
#!/bin/bash
| list_groups_cmd="$KAFKA_HOME/bin/kafka-consumer-groups.sh |           |      |                       |          |         |     | \   |
| --------------------------------------------------------- | --------- | ---- | --------------------- | -------- | ------- | --- | --- |
| --bootstrap-server                                        |           |      | localhost:9092        |          | --list" |     |     |
| for group                                                 | in $(bash |      | -c $list_groups_cmd); |          | do      |     |     |
| # do                                                      | something | with | the $group            | variable |         |     |     |
done
| Describing | a   | consumer | group |     |     |     |     |
| ---------- | --- | -------- | ----- | --- | --- | --- | --- |
The same tool can be used to display detailed state information about each consumer group —
namely, its partition offsets for the set of subscribed topics. A sample invocation and the resulting
outputisshownbelow.

66
| Chapter5: GettingStarted                 |              |                |     |              |     |
| ---------------------------------------- | ------------ | -------------- | --- | ------------ | --- |
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |              |                |     |              | \   |
| --bootstrap-server                       |              | localhost:9092 |     |              | \   |
| --group                                  | cli-consumer | --describe     |     | --all-topics |     |
Producesthefollowingwhennoconsumersareconnected:
| Consumer group | 'cli-consumer'  |     | has       | no active | members.       |
| -------------- | --------------- | --- | --------- | --------- | -------------- |
| GROUP          | TOPIC           |     | PARTITION |           | CURRENT-OFFSET |
| cli-consumer   | getting-started |     | 1         |           | 0              |
| cli-consumer   | getting-started |     | 0         |           | 2              |
| cli-consumer   | getting-started |     | 2         |           | 3              |
￿
| LOG-END-OFFSET | LAG | CONSUMER-ID |     | HOST | CLIENT-ID |
| -------------- | --- | ----------- | --- | ---- | --------- |
| 0              | 0   | -           |     | -    | -         |
| 2              | 0   | -           |     | -    | -         |
| 3              | 0   | -           |     | -    | -         |
If,ontheotherhand,weattachaconsumer(fromanearlierexample,usingthekafka-console-consumer.sh
tool),theoutputresemblesthefollowing:
| GROUP        | TOPIC           |     | PARTITION |     | CURRENT-OFFSET |
| ------------ | --------------- | --- | --------- | --- | -------------- |
| cli-consumer | getting-started |     | 0         |     | 2              |
| cli-consumer | getting-started |     | 1         |     | 0              |
| cli-consumer | getting-started |     | 2         |     | 3              |
￿
| LOG-END-OFFSET | LAG |     |     |     |     |
| -------------- | --- | --- | --- | --- | --- |
| 2              | 0   |     |     |     |     |
| 0              | 0   |     |     |     |     |
| 3              | 0   |     |     |     |     |
￿
CONSUMER-ID
consumer-cli-consumer-1-077c1bf1-df64-4d3e-a479-350e962119cc
consumer-cli-consumer-1-077c1bf1-df64-4d3e-a479-350e962119cc
consumer-cli-consumer-1-077c1bf1-df64-4d3e-a479-350e962119cc
￿
| HOST       | CLIENT-ID               |     |     |     |     |
| ---------- | ----------------------- | --- | --- | --- | --- |
| /127.0.0.1 | consumer-cli-consumer-1 |     |     |     |     |
| /127.0.0.1 | consumer-cli-consumer-1 |     |     |     |     |
| /127.0.0.1 | consumer-cli-consumer-1 |     |     |     |     |
Inadditiontodescribingaspecificconsumergroup,thistoolcanbeusedtodescribeallgroups:

67
| Chapter5: GettingStarted                 |              |                |              |     |
| ---------------------------------------- | ------------ | -------------- | ------------ | --- |
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |              |                |              | \   |
| --bootstrap-server                       |              | localhost:9092 |              | \   |
| --describe                               | --all-groups |                | --all-topics |     |
The --describe flag has an complementary flag — --state — that drills into the present state of
the consumer group. This includes the ID of the coordinator node, the assignment strategy, the
number of active members, and the state of the group. These attributes are explained in greater
detail in Chapter 15: Group Membership and Partition Assignment. The example below illustrates
thiscommandanditssampleoutput.
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |              |                |                | \    |
| ---------------------------------------- | ------------ | -------------- | -------------- | ---- |
| --bootstrap-server                       |              | localhost:9092 |                | \    |
| --describe                               | --all-groups |                | --state        |      |
| GROUP                                    |              |                | COORDINATOR    | (ID) |
| cli-consumer                             |              |                | localhost:9092 | (0)  |
￿
| ASSIGNMENT-STRATEGY |     | STATE  |     | #MEMBERS |
| ------------------- | --- | ------ | --- | -------- |
| range               |     | Stable |     | 1        |
| Resetting offsets   |     |        |     |          |
InthecourseofworkingwithKafka,weoccasionallycomeacrossasituationwherethecommitted
offsets of a consumer group require minor adjustment; in the more extreme case, that adjustment
might entail a complete reset of the offsets. An adjustment might be necessary if, for example,
the consumer has to skip over some records — perhaps due to the records containing erroneous
data. (These are sometimes referred to as ‘poisoned’ records.) Alternatively, the consumer may
be required to reprocess earlier records — possibly due to a bug in the application which was
subsequently resolved. Whichever the reason, the kafka-consumer-groups.sh tool can be used
withthe--reset-offsetsflagto affect fine-grained controloverthe consumergroup’scommitted
offsets.
Theexamplebelowrewindstheoffsetsfortheconsumergroupcli-consumertothelow-watermark,
usingthe--to-earliestflag—resultingintheforcedreprocessingofallrecordswhentheconsumer
groupreconnects.Alternatively,the--to-latestflagcanbeusedtofast-forwardtheoffsetstothe
high-watermarkextremity,skippingallbackloggedrecords.Resettingoffsetsisanofflineoperation;
theoperationwillnotproceedinthepresenceofaconnectedconsumer.

68
Chapter5: GettingStarted
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |                |         | \            |     |     |
| ---------------------------------------- | -------------- | ------- | ------------ | --- | --- |
| --bootstrap-server                       | localhost:9092 |         | \            |     |     |
| --topic getting-started                  |                | --group | cli-consumer | \   |     |
| --reset-offsets                          | --to-earliest  |         | --execute    |     |     |
By default, passing the --reset-offsets flag will result in a dry run, whereby the tool will list the
partitionsthatwillbesubjecttoareset,theexistingoffsets,aswellasthecandidateoffsetsthatwill
beassigneduponcompletion.Thisisequivalentofrunningthetoolwiththe--dry-runflag,andis
designed to protect the user from accidentally corrupting the consumer group’s state. To enact the
change,runthecommandwiththe--executeflag,asshownintheexampleabove.
In addition to resetting offsets for the entire topic, the reset operation can be performed selectively
onasubsetofthetopic’spartitions.Thiscanbeaccomplishedbypassinginalistofpartitionnumbers
followingthetopicname,intheform<topic-name>:<first-partition>,<second-partition>,...,<N-th-partition>.
An example of this syntax is featured below. Also, rather than resetting the offset to a partition
extremity,thisexampleusesthe--to-offsetparametertospecifyanumericoffset.
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |                |     | \                    |     |     |
| ---------------------------------------- | -------------- | --- | -------------------- | --- | --- |
| --bootstrap-server                       | localhost:9092 |     | \                    |     |     |
| --topic getting-started:0,1              |                |     | --group cli-consumer | \   |     |
| --reset-offsets                          | --to-offset    |     | 2 --execute          |     |     |
ThenextexampleusesKafka’srecordtime-stampingtolocateanoffsetbasedonthegivendate-time
value,quotedinISO8601form.Specifically,theoffsetswillberesettotheearliestpointintimethat
occursatthespecifiedtimestamporafterit.Thisfeatureisconvenientwhenoneneedstowindthe
offsets back to a known point in time. When using the --to-datetime parameter, ensure that the
offsetispassedusingthecorrecttimezone;ifunspecified,thetimezonedefaultstotheCoordinated
Universal Time (UTC), also known as Zulu time. In the example below, the timezone had to be
adjusted to Australian Eastern Daylight Time (AEDT), eleven hours east of Zulu, as this book was
writteninSydney.
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |                |         | \                             |     |     |
| ---------------------------------------- | -------------- | ------- | ----------------------------- | --- | --- |
| --bootstrap-server                       | localhost:9092 |         | \                             |     |     |
| --topic getting-started:2                |                | --group | cli-consumer                  | \   |     |
| --reset-offsets                          | --to-datetime  |         | 2020-01-27T14:35:54.528+11:00 |     | \   |
--execute
Thefinaloptionofferedbythistoolistoshifttheoffsetsbyafixedquantityn,usingthe--shift-by
parameter. The magnitude of the shift may be a positive number — for a forward movement, or
a negative number — to rewind the offsets. The extent of the shift is bounded by the partition
extremities;theresultof‘currentoffset’+nwillbecappedbythelow-waterandhigh-watermarks.

69
| Chapter5: | GettingStarted |     |     |     |
| --------- | -------------- | --- | --- | --- |
| Deleting  | offsets        |     |     |     |
Another method of resetting the offsets is to delete the offsets altogether, shown in the example
below. This is, in effect, a lazy form of reset — the assignment of new offsets does not occur until
aconsumerconnectstothecluster.Whenthishappens,theauto.offset.resetclientpropertywill
stipulatewhichextremitytheoffsetshouldberesetto—eithertheearliestoffsetorthelatest.
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |                 |                | \            |                  |
| ---------------------------------------- | --------------- | -------------- | ------------ | ---------------- |
| --bootstrap-server                       |                 | localhost:9092 | \            |                  |
| --topic                                  | getting-started | --group        | cli-consumer | --delete-offsets |
| Deleting                                 | a consumer      | group          |              |                  |
Deleting a consumer group erases all persistent state associated with it. This is accomplished by
passingthe--deleteflagtothekafka-consumer-groups.shCLI,asshownbelow.
| $KAFKA_HOME/bin/kafka-consumer-groups.sh |              |                | \   |     |
| ---------------------------------------- | ------------ | -------------- | --- | --- |
| --bootstrap-server                       |              | localhost:9092 | \   |     |
| --group                                  | cli-consumer | --delete       |     |     |
Deletingtheconsumergroupisequivalenttodeletingoffsetsforalltopicsandallpartitions.
| A basic | Java | producer | and consumer |     |
| ------- | ---- | -------- | ------------ | --- |
A CLI is a great place to start, and can be driven programmatically from a shell script. But this
is more of a convenience; an automation of repetitive functionality, if you will. Any serious event
streaming application will employ a high-level language such as Java, C, or Python to implement
thebusinesslogicrequiredtopublishrecordsandtoreacttoeventsemittedbyotherapplications.
| Client | libraries |     |     |     |
| ------ | --------- | --- | --- | --- |
Unlike the built-in CLI, which relies on the presence of binaries and a pre-installed Java runtime,
applications rely solely on distributable client libraries. These are available for just about every
programminglanguageunderthesun,fromthemainstreamtotheesoteric.
In this book, we are going to focus solely on the Java ecosystem — being among the most popular
mainstream software development environments and the ‘home turf’ of Kafka and many related
event streaming technologies. The Java client implementation is the most mature of the available
client libraries, being developed alongside and at the same cadence as the Kafka broker. Other
languageswillhavesimilarclients;theyaremaintainedindependentlyofKafkaandfeaturevarying
level of feature support and stability. Bear in mind, these libraries will slightly lag the mainstream
Kafka releases in terms of feature sets; if you are after ‘bleeding edge’ capabilities, you will be best
served by the Java client library and, to a marginally lesser extent, the C library — librdkafka,
maintainedbyMagnusEdenhill.

Chapter5: GettingStarted 70
Using the Java library
To add a Kafka client library to your project, add the following to your build.gradle (if using
Gradle):
dependencies {
implementation "org.apache.kafka:kafka-clients:2.4.0"
}
Alternatively,ifusingMaven,addthefollowingtoyourpom.xml:
<dependency>
<groupId>org.apache.kafka</groupId>
<artifactId>kafka-clients</artifactId>
<version>2.4.0</version>
</dependency>
TheexamplesaboveassumeKafkaversion2.4.0—thelatestatthetimeofwriting.Replacethiswith
amoreup-to-dateversionifappropriate.
The complete source code for the upcoming examples is available at
github.com/ekoutanov/effectivekafka⁶ in the src/main/java/effectivekafka/basic
directory.Code listingswill havetheir packagedeclaration removed forbrevity,and often
willstripoutimportstatementsandouterclassdeclarations.
InterfacingwiththeKafkaclientlibrariesisdoneprimarilyusingthefollowingclasses:
• Producer: The public interface of the producer client, containing the necessary method sig-
natures for publishing records and using transactions. This interface is surprisingly light
on documentation; method comments simply delegate the documentation to the concrete
implementation.
• KafkaProducer: The implementation of Producer. In addition, a KafkaProducer contains de-
tailedJavadoccommentsforeachmethod.
• ProducerRecord: A data structure encompassing the attributes of a record, as perceived by a
producer. To be precise, this is the representation of a record before it has been published to
a partition; as such, it contains only the basic set of attributes: topic name, partition number,
optionalheaders,key,value,andatimestamp.
• Consumer: The definition of a consumer entity, containing message signatures for controlling
subscriptions and topic/partition assignment, fetching records from the cluster, committing
offsets,andobtaininginformationabouttheavailabletopicsandpartitions.
⁶https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/basic

71
| Chapter5: | GettingStarted |     |     |     |     |     |
| --------- | -------------- | --- | --- | --- | --- | --- |
• KafkaConsumer:Theimplementationof Consumer.Likeitsproducercounterpart,thisimplemen-
tationcontainsthecompletesetofJavadocs.
• ConsumerRecord:Aconsumer-centricstructureforhousingrecordattributes.AConsumerRecord
is effectively a superset of the ProducerRecord, containing additional metadata such as the
recordoffset,thechecksum,andsomeotherinternalattributes.
Thereareotherclassesthatareused,fromtimetotime,tointerfacewiththeclientlibrary.However,
the bulk of record publishing and consumption can be achieved using little more than just the six
classesabove.
| Publishing |     |     | records |     |     |     |
| ---------- | --- | --- | ------- | --- | --- | --- |
Asimple,yetcompleteexampleillustratingthepublishingofKafkarecordsispresentedbelow.
| import | static | java.lang.System.*; |     |     |     |     |
| ------ | ------ | ------------------- | --- | --- | --- | --- |
import java.util.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;
| public | final  | class                | BasicProducerSample  |        | {     |     |
| ------ | ------ | -------------------- | -------------------- | ------ | ----- | --- |
| public | static |                      | void main(String[]   |        | args) |     |
|        | throws | InterruptedException |                      |        | {     |     |
| final  |        | var topic            | = "getting-started"; |        |       |     |
| final  |        | Map<String,          | Object>              | config | =     |     |
Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
true);
try (var producer = new KafkaProducer<String, String>(config)) {
|     | while                  | (true) | {              |                    |      |              |
| --- | ---------------------- | ------ | -------------- | ------------------ | ---- | ------------ |
|     | final                  | var    | key = "myKey"; |                    |      |              |
|     | final                  | var    | value = new    | Date().toString(); |      |              |
|     | out.format("Publishing |        |                | record             | with | value %s%n", |
value);

Chapter5: GettingStarted 72
final Callback callback = (metadata, exception) -> {
out.format("Published with metadata: %s, error: %s%n",
metadata, exception);
};
// publish the record, handling the metadata in the callback
producer.send(new ProducerRecord<>(topic, key, value),
callback);
// wait a second before publishing another
Thread.sleep(1000);
}
}
}
}
The first item on our to-do list is to configure the client. This is done by building a mapping of
property names to configured values. More detailed information on configuration is presented in
Chapter 10: Client Configuration; for the time being, we will limit ourselves to the most basic
configurationoptions—justenoughtogetusgoingwithafunctioningproducer.
The configuration keys are strings — being among the permissible property names defined in the
official Kafka documentation, available online at kafka.apache.org/documentation⁷. Rather than
quoting strings directly, our example employs the static constants defined in the ProducerConfig
class,therebyavoidingamistype.
Of the four configuration mappings supplied, the first specifies a list of so-called bootstrap servers.
Inourexample,thisisasingletonlistcomprisingtheendpointlocalhost:9092—theaddressofour
testbroker.Bootstrappingisamoderatelyinvolvedtopic,describedinChapter8:Bootstrappingand
AdvertisedListeners.
The next two mappings specify the serializers that the producer should use for the records’ keys
and values. Kafka offers lots of options around how keys and values are marshalled — using either
built-inorcustomserializers.Forthesakeofexpediency,wewillgowiththesimplestoptionatour
disposal—writingrecordsasplainstrings.Moreelaborateformsofmarshallingwillbeexploredin
Chapter7:Serialization.
Whereasthefirstthreeitemsrepresentmandatoryconfiguration,thefourthisentirelyoptional.By
default, in the absence of idempotence, a producer may inadvertently publish a record in duplicate
or out-of-order — if one of the queued records experiences a timeout during publishing and is
reattempted after one or more of its successors have gone through. With the enable.idempotence
option set to true, the broker will maintain an internal sequence number for each producer and
⁷https://kafka.apache.org/documentation/#producerconfigs

Chapter5: GettingStarted 73
partition pair, ensuring that records are not processed in duplicate or out-of-order. So it’s good
practicetoenableidempotence.
Priortopublishingarecord,weneedtoinstantiateaKafkaProducer,givingittheassembledconfig
mapintheconstructor.Aproducercannotbereconfiguredfollowinginstantiation.Oneinstantiated,
wewillkeepareferencetotheKafkaProducerinstance,asitmustbeclosedwhentheapplicationno
longer needs it. This is important because aKafkaProducer maintains TCP connections to multiple
brokers and also operates a background I/O thread to ferry the records across. Failure to close the
producer instance may result in resource starvation on the client, as well as on the brokers. As
Producer extends the Closeable interface, the best way to ensure that the producer instance is
properlydisposedofistouseatry-with-resources block,asshowninthelistingabove.
Sometimes we need a producer to hang around indefinitely — for example, when an
application publishes events in response to some external stimuli, such as responding to
an API request. In this scenario, the use of a try-with-resources is inappropriate, as the
lifecycle of a KafkaProducer instance is obviously aligned with that of the API controller
or the associated business logic layer (depending on how the application is architected).
Instead, we would let the owner of the producer, whichever component that may be, deal
withlifecycleconcerns.
In order to actually publish a record, one must use the Producer.send() API. There are two
overloadedvariationsof send()method:
1. Future<RecordMetadata> send(ProducerRecord<K, V> record): asynchronously sends the
record,returningaFuturecontainingtherecordmetadata.
2. Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback): asyn-
chronously sends the record, invoking the given Callback implementation when either the
record has been successfully persisted on the broker or an error has occurred. Our example
usesthisvariant.
The send() methods are asynchronous, returning as soon as the record is serialized and staged in
the accumulator buffer. The actual sending of the record will be performed in the background, by
a dedicated I/O thread. To block on the result, the application can invoke the get() method of the
providedFuture.
The publishing of records takes place in a loop, with a one second sleep between each successive
send() call. For simplicity, we are publishing the current date, keyed to a constant "myKey". This
meansthatallrecordswillappearonthesamepartition.
Runningtheexampleaboveresultsinthefollowingoutput(untilterminated):

74
| Chapter5: | GettingStarted |     |     |     |     |     |     |     |     |     |
| --------- | -------------- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
13:14:09/0 INFO [main]: [Producer clientId=basic-producer-sample] ￿
| Instantiated |     | an  | idempotent |     | producer. |     |     |     |     |     |
| ------------ | --- | --- | ---------- | --- | --------- | --- | --- | --- | --- | --- |
13:14:09/66 INFO [main]: [Producer clientId=basic-producer-sample] ￿
| Overriding |     | the        | default | retries | config         |     | to the   | recommended |      | ￿   |
| ---------- | --- | ---------- | ------- | ------- | -------------- | --- | -------- | ----------- | ---- | --- |
| value      | of  | 2147483647 |         | since   | the idempotent |     | producer |             | is ￿ |     |
enabled.
13:14:09/66 INFO [main]: [Producer clientId=basic-producer-sample] ￿
Overriding the default acks to all since idempotence is enabled.
| 13:14:09/82       |        | INFO [main]:               |                                | Kafka                | version:                        | 2.4.0       |                  |          |      |     |
| ----------------- | ------ | -------------------------- | ------------------------------ | -------------------- | ------------------------------- | ----------- | ---------------- | -------- | ---- | --- |
| 13:14:09/82       |        | INFO [main]:               |                                | Kafka                | commitId:                       |             | 77a89fcf8d7fa018 |          |      |     |
| 13:14:09/82       |        | INFO [main]:               |                                | Kafka                | startTimeMs:                    |             | 1570264049533    |          |      |     |
| Publishing        | record | with                       | value                          |                      | Wed Jan                         | 02 13:14:09 |                  | AEDT     | 2020 |     |
| 13:14:09/495      |        | INFO                       | [kafka-producer-network-thread |                      |                                 |             |                  | | basic- | ￿    |     |
| producer-sample]: |        |                            | [Producer                      |                      | clientId=basic-producer-sample] |             |                  |          |      | ￿   |
| Cluster           |        | ID: efkResGcSUWMV6zqj9D8vw |                                |                      |                                 |             |                  |          |      |     |
| 13:14:09/497      |        | INFO                       | [kafka-producer-network-thread |                      |                                 |             |                  | | basic- | ￿    |     |
| producer-sample]: |        |                            | [Producer                      |                      | clientId=basic-producer-sample] |             |                  |          |      | ￿   |
| ProducerId        |        | set                        | to 12000                       | with                 | epoch                           | 0           |                  |          |      |     |
| Published         | with   | metadata:                  |                                | getting-started-0@0, |                                 |             | error:           | null     |      |     |
| Publishing        | record | with                       | value                          |                      | Wed Jan                         | 02 13:14:10 |                  | AEDT     | 2020 |     |
| Published         | with   | metadata:                  |                                | getting-started-0@1, |                                 |             | error:           | null     |      |     |
| Publishing        | record | with                       | value                          |                      | Wed Jan                         | 02 13:14:11 |                  | AEDT     | 2020 |     |
| Published         | with   | metadata:                  |                                | getting-started-0@2, |                                 |             | error:           | null     |      |     |
| Publishing        | record | with                       | value                          |                      | Wed Jan                         | 02 13:14:12 |                  | AEDT     | 2020 |     |
| Published         | with   | metadata:                  |                                | getting-started-0@3, |                                 |             | error:           | null     |      |     |
| Consuming         |        | records                    |                                |                      |                                 |             |                  |          |      |     |
Thefollowinglistingdemonstrateshowrecordsareconsumed.
| import | static | java.lang.System.*; |     |     |     |     |     |     |     |     |
| ------ | ------ | ------------------- | --- | --- | --- | --- | --- | --- | --- | --- |
import java.time.*;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
| public | final  | class | BasicConsumerSample  |     |       | {   |     |     |     |     |
| ------ | ------ | ----- | -------------------- | --- | ----- | --- | --- | --- | --- | --- |
| public | static | void  | main(String[]        |     | args) | {   |     |     |     |     |
| final  | var    | topic | = "getting-started"; |     |       |     |     |     |     |     |

Chapter5: GettingStarted 75
final Map<String, Object> config =
Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName(),
ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName(),
ConsumerConfig.GROUP_ID_CONFIG,
"basic-consumer-sample",
ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
"earliest",
ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
false);
try (var consumer = new KafkaConsumer<String, String>(config)) {
consumer.subscribe(Set.of(topic));
while (true) {
final var records = consumer.poll(Duration.ofMillis(100));
for (var record : records) {
out.format("Got record with value %s%n", record.value());
}
consumer.commitAsync();
}
}
}
}
Thisexampleisstrikinglysimilartotheproducer—samebuildingofaconfigmap,instantiationof
aclient,andtheuseofatry-with-resources blocktoensuretheclientisclosedafteritleavesscope.
Thefirstdifferenceisintheconfiguration.Consumerhavesomeelementscommonwithproducers
—suchasthebootstrap.serverslist,andseveralothers—butbyandlargetheyaredifferent.The
deserializer configuration is symmetric to the producer’s serializer properties; there are key and
valueequivalents.
The group ID configuration is optional — it specifies the ID of the consumer group. This example
usesaconsumergroupnamedbasic-consumer-sample.Theautooffsetresetconfigurationstipulates
what happens when the consumer subscribes to the topic for the first time. In this case, we would
like the consumer’s offset to be reset to the low-water mark for every affected partition, meaning
thattheconsumerwillgetanybackloggedrecordsthatexistedpriortothecreationofthegroupin
Kafka.Thedefaultsettingislatest,meaningtheconsumerwillnotreadanypriorrecords.
Finally, the auto-commit setting is disabled, meaning that the application will commit offsets at its
discretion.Thedefaultsettingistoenableauto-commitwithaminimumintervaloffiveseconds.

76
| Chapter5: GettingStarted |     |     |     |     |     |     |     |
| ------------------------ | --- | --- | --- | --- | --- | --- | --- |
Priortopollingforrecords,theapplicationmustsubscribetooneormoretopicsusingtheConsumer.subscribe()
method.
Once subscribed, the application will repeatedly invoke Consumer.poll() in a loop, blocking up to
a maximum specified duration or until a batch of records is received. For each received records,
this example simply prints the record’s value. Once all records have been printed, the offsets
are committed asynchronously using the Consumer.commitAsync() method. The latter returns as
soon the offsets are enqueued internally; the actual sending of the commit message to the group
coordinator will take place on the background I/O thread. (The group coordinator is responsible
for arbitrating the state of the consumer group.) The reader might also recall from Chapter 3:
Architecture and Core Concepts, that the repeated polling and handling of records is called the
| poll-process loop. |     |     |     |     |     |     |     |
| ------------------ | --- | --- | --- | --- | --- | --- | --- |
Runningtheexampleaboveresultsinthefollowingoutput(untilterminated):
| 10:47:16/0 | INFO | [main]: | Kafka version:     | 2.4.0                    |                  |     |     |
| ---------- | ---- | ------- | ------------------ | ------------------------ | ---------------- | --- | --- |
| 10:47:16/0 | INFO | [main]: | Kafka commitId:    |                          | 77a89fcf8d7fa018 |     |     |
| 10:47:16/0 | INFO | [main]: | Kafka startTimeMs: |                          | 1580341636585    |     |     |
| 10:47:16/2 | INFO | [main]: | [Consumer          | clientId=consumer-basic- |                  |     | ￿   |
consumer-sample-1, groupId=basic-consumer-sample] Subscribed ￿
| to topic(s): | getting-started |     |     |     |     |     |     |
| ------------ | --------------- | --- | --- | --- | --- | --- | --- |
10:47:17/431 INFO [main]: [Consumer clientId=consumer-basic- ￿
consumer-sample-1, groupId=basic-consumer-sample] Cluster ID: ￿
efkResGcSUWMV6zqj9D8vw
10:47:18/1740 INFO [main]: [Consumer clientId=consumer-basic- ￿
consumer-sample-1, groupId=basic-consumer-sample] Discovered ￿
group coordinator 172.20.40.148:9092 (id: 2147483647 rack: null)
10:47:18/1744 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |       | groupId=basic-consumer-sample] |     |     |     | ￿   |     |
| ------------------ | ----- | ------------------------------ | --- | --- | --- | --- | --- |
| (Re-)joining       | group |                                |     |     |     |     |     |
10:47:18/1795 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |       | groupId=basic-consumer-sample] |     |     |     | ￿   |     |
| ------------------ | ----- | ------------------------------ | --- | --- | --- | --- | --- |
| (Re-)joining       | group |                                |     |     |     |     |     |
10:47:18/1828 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |     | groupId=basic-consumer-sample] |     |     |     | Finished | ￿   |
| ------------------ | --- | ------------------------------ | --- | --- | --- | -------- | --- |
assignment for group at generation 1: {consumer-basic-consumer- ￿
sample-1-26dce919-7f7d-4e04-98d9-99b091c73b3d=org.apache.kafka. ￿
clients.consumer.ConsumerPartitionAssignor$Assignment@66ea810}
10:47:18/1881 INFO [main]: [Consumer clientId=consumer-basic- ￿
consumer-sample-1, groupId=basic-consumer-sample] Successfully ￿
| joined group | with | generation | 1   |     |     |     |     |
| ------------ | ---- | ---------- | --- | --- | --- | --- | --- |
10:47:18/1884 INFO [main]: [Consumer clientId=consumer-basic- ￿
consumer-sample-1, groupId=basic-consumer-sample] Adding newly ￿
| assigned | partitions: | getting-started-1, |     | getting-started-0, |     |     | ￿   |
| -------- | ----------- | ------------------ | --- | ------------------ | --- | --- | --- |

77
| Chapter5: | GettingStarted |     |     |     |     |     |
| --------- | -------------- | --- | --- | --- | --- | --- |
getting-started-2
10:47:18/1900 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |        | groupId=basic-consumer-sample] |                   |     | Found no | ￿   |
| ------------------ | ------ | ------------------------------ | ----------------- | --- | -------- | --- |
| committed          | offset | for partition                  | getting-started-1 |     |          |     |
10:47:18/1900 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |        | groupId=basic-consumer-sample] |                   |     | Found no | ￿   |
| ------------------ | ------ | ------------------------------ | ----------------- | --- | -------- | --- |
| committed          | offset | for partition                  | getting-started-0 |     |          |     |
10:47:18/1900 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |        | groupId=basic-consumer-sample] |                   |     | Found no | ￿   |
| ------------------ | ------ | ------------------------------ | ----------------- | --- | -------- | --- |
| committed          | offset | for partition                  | getting-started-2 |     |          |     |
10:47:18/1921 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |               | groupId=basic-consumer-sample] |     |           | Resetting | ￿   |
| ------------------ | ------------- | ------------------------------ | --- | --------- | --------- | --- |
| offset             | for partition | getting-started-1              |     | to offset | 0.        |     |
10:47:18/1921 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |               | groupId=basic-consumer-sample] |     |           | Resetting | ￿   |
| ------------------ | ------------- | ------------------------------ | --- | --------- | --------- | --- |
| offset             | for partition | getting-started-0              |     | to offset | 0.        |     |
10:47:18/1921 INFO [main]: [Consumer clientId=consumer-basic- ￿
| consumer-sample-1, |               | groupId=basic-consumer-sample] |             |           | Resetting | ￿   |
| ------------------ | ------------- | ------------------------------ | ----------- | --------- | --------- | --- |
| offset             | for partition | getting-started-2              |             | to offset | 0.        |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:09 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:10 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:11 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:12 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:13 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:14 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:15 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:16 | AEDT 2020 |           |     |
| Got record         | with value    | Wed Jan                        | 02 13:14:17 | AEDT 2020 |           |     |
This chapter has hopefully served as a practical reflection on the theoretical concepts that were
outlined in Chapter 3: Architecture and Core Concepts. Specifically, we learned how to interact
withaKafkaclusterusingtwodistinct,yetcommonlyusedapproaches.
Thepartexploredtheuseofthebuilt-inCLItools.Thesearebasicutilitiesthatallowausertopublish
andconsumerecords,administertopicsandconsumergroups,makevariousconfigurationchanges,
andqueryvariousaspectsoftheclusterstate.Aswehavecometorealise,thebuilt-intoolingisfar
fromperfect,butitissufficienttocarryoutbasicadministrativeoperations,andattimesitmaybe
theonlytoolsetatourdisposal.
The second part looked at the programmatic interaction with Kafka, using the Java client library.
We looked at simple examples for publishing and consuming records and learned the basics of the

Chapter5: GettingStarted 78
Java client API. Real applications will undoubtedly be more complex than the provided examples,
buttheywillinvariablyutilisetheexactsamebuildingblocks.

Chapter 6: Design Considerations
Previous chapters have taken us through the essentials of event streaming and the core concepts of
Kafka.Bynow,thereadershouldbefamiliarwiththearchitectureofKafka,itsinternalcomponents,
aswellastheproducerandconsumerecosystems.WehavesetupaKafkabroker,aKafdropUIand
builtbasicproducerandconsumerapplicationsusingtheJavaclientAPIs.
Inessence,thereadershouldnowbeequippedwiththetoolsandfoundationalknowledgerequired
tostartbuildingeventstreamingapplications.Butittakestimeandexperiencetobecomeproficient
inKafka.Thisisanotherwayofsaying:Towritegoodsoftware,youneedtomakelotsofmistakes.
Mistakes need to be made; they are an essential part of learning. But learning from other peoples’
mistakes is better than learning from one’s own. So this chapter presents a list of considerations
that are instructive in the design of performant and sustainable event streaming applications;
considerations that have been amassed over years of working with these sorts of systems across
avarietyofindustries.
Roles and responsibilities
Kafka permits a flexible arrangement between producers and consumers, allowing for a host of
similar and disparate applications to interact with a topic simultaneously. In coming to terms with
this,anoften-askedquestionis:Whichpartyownsthetopic,andwhoisresponsibleforitsupkeep?
Event-oriented broadcast
In a broadcast arrangement, where the producer-consumer relationship follows a (multi)point-to-
multipointtopology,itisanacceptedbest-practicefortheproducerecosystemtoassumecustodian-
ship over the topic, and to effectively prescribe the entirety of the topic’s configuration and usage
semantics.Theseinclude—
• The lifecycle of the topic, as well as the associated broker-side configuration, such as the
retentionperiodandcompactionpolicy;
• The nature and content of the published data, encodings, record schema, versioning strategy
andassociateddeprecationperiod;and
• Thesizingofthetopicwithrespecttothepartitioncountandthekeyingoftherecords.
Innouncertainterms:theproducerisking.Theproducerwillwarrantallexistentialandbehavioural
aspects of the topic; the only decision left to the discretion of the consumer is whether to subscribe

Chapter6: DesignConsiderations 80
to the topic or not. This, rather categorical, approach to role demarcation is essential to preserving
the key characteristic of an event-driven architecture — loose coupling. The producer cannot be
intrinsically aware of the topic’s consumers, as doing so would largely defeat the intent of the
design. This is not to say that producers should publish on a whim, or that the suitability of the
published data is somehow immaterial to the outcome. Naturally, the published data should be
correct, complete, and timely; however, the assurance of this lies with the designers of the system
and is heavily predicated on the efficacy of domain modelling and stakeholder consultation. It is
also evolutionary in nature; feedback from the consuming parties during the design, development,
and operation phases should be used to iteratively improve the data quality. Stated otherwise,
while the consuming parties are consulted as appropriate, the final decision rights and associated
responsibilitiesrestwiththeproducer.
Peer-to-peer messaging
Kafka may be used in peer-to-peer messaging arrangement, whereby the consumer is effectively
responding to specific commands issued by a producer, and in most cases emitting responses back
to the initiator. This model sees a role reversal: the consumer plays the role of the service provider,
andthereforeassumescustodyoverthelifecycleofthetopicanditsdefiningcharacteristics.
Wheretheresponseissentoveradifferenttopic,sharedamongmessageinitiators,thesemanticsof
theresponsetopicarealsofullydefinedbytheconsumer.Inmoreelaboratemessagingscenarios,the
initiator of the request may ask that the response is ferried over a dedicated topic to avoid sharing;
in this case, the lifecycle of the topic and its retention will typically be managed by the initiator,
whilerecord-relatedaspectsremainwithintheconsumer’sremit.
Topic conditioning
Reading the section on producer-driven topic modelling may fail to instill confidence in would-be
consumers. The flip side of the coupling argument is the dreaded leap of faith. If the prerogative
of the producer is to optimise topics around its domain model, what measures exist to assure the
consumersthattheupstreamdecisionsdonotimpactthemadversely?Isacompromisepossible,and
howdoesoneneutralisetheapparentbiaswithoutnegativelyimpactingallparties.
These are fair questions. In answering, the reader is invited to consider the case where there
are multiple disparate consumers. Truly, a single-producer-multiple-consumers is a fairly routine
arrangement in contemporary event-driven architecture. And this is precisely the use case that
highlightswhyacompromiseisnotaviableoption.Asthenumberofdisagreeingpartiesgrows,the
likelihoodofstrikinganeffectivecompromisedecreasestothepointwheretheresultingsolutionis
barelytractableforeitherparty.Itisthearchitecturalequivalentofchildrenfightingoverastuffed
toy, where the inevitable outcome is the tearing of the toy, the dramatic scattering of its plush
contents,resultingindiscontentbutultimatelyquiescedchildren.
Sowhatisonetodo?

Chapter6: DesignConsiderations 81
While this problem might not be trivially solvable, it can be readily compartmentalised. The use
of a staged event-driven architecture (SEDA) offers a way of managing the complexity of diverse
consumer requirements without negatively impacting the consumer applications directly or cou-
plingtheparties.Ratherthanfeedingconsumersdirectlyofftheproducer-driventopic,intermediate
processingstagesareemployedtoconditionthedatatoconformtoanindividualconsumergroup’s
expectations. These stages are replicated for each independent set of consumers, as shown in the
diagrambelow.
Conditioningstages
Under this model, the impedance mismatch is resolved by the intermediate stages, leading to
improved maintainability of the overall solution and allowing each of the end-parties to operate
strictlywithintheconfinesoftheirrespectivedomainmodels.Theresponsibilitiesofthepartiesare
unchanged;theconsumertakesownershipoftheconditioningstage,responsibleforitsdevelopment
and upkeep. While this might initially appear like a zero-sum transfer, the benefit of this approach
is in its modularity. It embraces the single responsibility principle, does not clutter the consumer
withtransformationallogic,andcanleadtoamoresustainablesolution.
The acquired modularity may also lead to opportunities for component reuse. If two (or more)
distinctconsumergroupssharesimilardatarequirements,acommonconditioningstagecanpower
both.Intheexampleabove,asingleconditioningstagepowersbothconsumergroupsC andD.

Chapter6: DesignConsiderations 82
Parallelism
It was previously stated in Chapter 3: Architecture and Core Concepts that exploiting partial
orderenablesconsumerparallelism.Thedistributionofpartitionassignmentsamongmembersofa
consumer group is the very mechanism by which this is achieved. While consumer load-balancing
is straightforward in theory, the practical implications of aspects such as topic sizing, record key
selection,andconsumerscalingarenotsoapparent.
There are several factors one must account for when designing highly performant event streaming
applications.Thefollowingisanenumerationofsomeofthesefactors.
Producer-driven partitioning
Irrespectiveoftheparticularmessagingtopologyemployed,theresponsibilityofassigningrecordsto
partitions lies solely with the producer. This stems from a fundamental design limitation of Kafka;
both topics and partitions are physical constructs, implemented as segmented log files under the
hood. The publishing of a record results in the appending of a serialized form of the record to the
head-end of an appropriate log file. Once this occurs, the relationship between a record and its
encompassingtopic-partitioniscementedforthelifetimeoftherecord.
In Kafka terminology, the most recent records are considered to be at the ‘head’ end of a
partition.
The implication of this constraint is that the producer should take the utmost care in keying the
records such as to preserve the essential causal relations, without overly constraining the record
order. In practice, events will relate to some stable entity; the identifier of that entity can serve as
thekeyofthecorrespondingrecord.
By way of example, consider a hypothetical content syndication system catering to football fans.
Our system integrates with various real-time content providers, listening to significant in-play
events from football matches as they unfold, then publishes a consolidated event stream to power
multiple downstream consumers — mobile apps, scoreboards, playerand match stats, video stream
overlays, social networks such as Twitter, and other subscribers. True to the principles of event-
driven architecture, we try to remain agnostic of what’s downstream, focusing instead on the
completeness and correctness of the emitted event stream — the data content of the records, their
timing,andgranularity.Asimplifieddomainmodelforthiscontrivedscenarioisillustratedbelow.

Chapter6: DesignConsiderations 83
Footballdomainmodel
Mimickingthereal-lifeorderofeventsisagoodstartingpointfordesigningastreamingapplication.
With this in mind, it makes sense to appoint the football match as the stable entity — its identifier
will act as a record key to induce the partial order. This means that goals, corners, penalties, and
so forth, will appear in the order they occurred within a match; recordsacross matches will not be
orderedbyvirtueofthematchesbeingunrelated.
Thebasicrequirementforthestabilityofthechosenentitiesisthattheyshouldpersistforthelifetime
of all causally related events. This does not imply that the entities should be long-lived, only that
they exist long enough to survive the causal chains that depend on them. To our earlier example,
thefootballmatchsurvivesallofitsin-playevents.
Record keys bound to some stable identity can be thought of as the equivalent of foreign
keys in database parlance, and the stability characteristic can be likened to a referential
integrityconstraint.Exploringtherelationaldatabaseanalogy,theentityinthelinkedtable
wouldpersistforaslongasitisbeingreferencedbyoneormoreforeignkeys.Likewise,the
linkedstableentitypersistsforthedurationoftimefromthepointwhenthefirstrecordis
published to when the last record leaves the producer. Thereafter, once the causal order is
materialisedinKafka,thelinkedentitybecomeslargelyirrelevant.
Assuming no special predecessor-successor relationship between the chosen stable entities, the
partial order of the resulting records will generally be sufficient for most, if not all, downstream
consumers. Conversely, if the entities themselves exhibit causal relationships, then the resulting
stream may fail to capture the complete causality. Reverting to our earlier example, suppose one
downstreamsubscriberisatournamentleaderboardapplication,needingtocaptureandpresentthe
relative standing of the teams as they progress through a tournament. Match-centric order may be
insufficient,asgoalswouldneedtobecollatedintournamentorder.

Chapter6: DesignConsiderations 84
Where a predecessor-successor relationship exists between stable entities, and that relationship is
significant to downstream subscribers, there are generally two approaches one may take. The first
is to coarsen the granularity of event ordering, for example, using the tournament identifier is the
record key. This preserves the chronological order of in-play events within a tournament, and by
extension,withinamatch.Thesecondapproachistotransfertheresponsibilityofeventreordering
tothedownstreamsubscriber.
Coarsening of causal chains is generally preferred when said order is an intrinsic characteristic of
the publisher’s domain. In our example, the publisher is well aware of the relationship between
tournamentsandmatches,sowhynotdepictthis?
The main drawback of this approach is the reduced opportunity for consumer parallelism, as
coarsening leads to a reduction in the cardinality of the partitioning key. Subscribers will not be
abletoutiliseKafka’sload-balancingcapabilitiestoprocessmatch-levelsubstreamsinparallel;only
tournamentswillbesubjecttoparallelism.Thismaysatisfysomesubscribers,butpenaliseothers.
An extension of this model is to introduce conditioning stages for those subscribers that would
benefit from the finer granularity. A conditioning stage would consume a record from a coarse-
grained input topic, then republish the same value to an output topic with a different key — for
example, switching the key from a tournament ID to a match ID. This model nicely dovetails into
the broader principle of producer-driven domain modelling, while satisfying individual subscriber
needswiththehelpofSEDA.
The second approach — fine-grained causal chains with consumer-side reordering — assumes that
the consumer, or some intermediate stage acting on its behalf, is responsible for coarsening the
granularityoftheeventsubstreamstofitsomebespokeprocessingneed.Theconditioningstagewill
needtobestateful,maintainingastagingdatastoreforincomingeventssothattheycanbereordered.
Inpractice,thisismuchmoredifficultthanitappears.Insomecases,theresimplyisn’tenoughdata
available for a downstream stage to reconstruct the original event order. In short, consumer-side
reorderingmaynotalwaysaviableoption.
Thereisnoone-size-fits-allapproachtopartitioningdomainevents.Asaruleofthumb,theproducer
should publish at the finest level of granularity that makes sense in its respective domain, while
supporting a diverse subscriber base. A crucial point: being agnostic of subscribers does not equate
to being ignorant of their needs. The effective modelling of the domain and the associated event
streams fall on the shoulders of architects and senior technologists; stakeholder consultation and
understandingoftheoveralllandscapeareessentialinconstructingasustainablesolution.
In the absence of consumer awareness, one litmus test for formulating partial order is to ascertain
that the resulting stream can be used to reconstitute the source domain. Ask the question: Can a
hypothetical subscriber rebuild an identical replica of the domain purely from the emitted events
whilemaintainingcausalconsistency?Iftheansweris‘yes’,thentheeventstreamshouldbesuitable
foranydownstreamsubscriber.Otherwise,iftheansweris‘no’,thenthereisagapinthecondition
oftheemittedeventsthatrequiresattention.

Chapter6: DesignConsiderations 85
Topic width
Withthecorrectkeyinggranularityinplace,thenextconsiderationisthe‘width’ofthetopic—the
number of partitions it encompasses. Assuming a fair distribution of keys, increasing the number
of partitions creates more opportunities for the consumer ecosystem to process records in parallel.
Gettingthetopicwidthrightisessentialtoaperformanteventstreamingarchitecture.
Regrettably, Kafka does not make this easy for us. Kafka only permits non-destructive resizing of
topics when increasing the partition count. Decreasing the number of partitions is a destructive
operation—requiringthetopictobecreatedanew,andmanuallyrepopulated.
One of the main gotchas of resizing topics is the effect they have on record order. As stated in
Chapter 3: Architecture and Core Concepts, a Kafka producer hashes the record’s key to arrive
at the partition number. The hashing scheme is not consistent — two records with the same key
hashedatdifferentpointsintimewillcorrespondtoanidenticalpartitionnumberifandonlyif the
numberofpartitionshasnotchangedinthattime.Increasingthepartitioncountresultsinthetwo
records occupying potentially different partitions — a clear breach of Kafka’s key-centric ordering
guarantee.Kafkawillnotrehashrecordsaspartofresizing,asthiswouldbeprohibitivelyexpensive
in the absence of consistent hashing. (To be clear, consistent hashing would not eliminate the need
for rehashing recordsper se, but it would dramatically reduce the number of affected hashes when
thetopiciswidened.)
Whenthecorrectnessofasystemispredicatedonthekey-centricorderingofrecords,avoid
resizing the topic as this will effectively void any ordering guarantees that the consumer
ecosystemmayhavecometorelyupon.
One approach to dealing with prospective growth is to start with a sufficiently over-provisioned
topic, perhaps an order of magnitude more partitions than one would reasonably expect — thereby
avoiding the hashing skew problem down the track. On the flip side, increasing the number of
partitionsmayincreasetheloadonthebrokersandconsumers.
A partition is backed by log files, which require additional file descriptors. (At minimum, there is
one log segment and one index file per partition.) Inbound writes flow to dedicated buffers, which
areallocatedperpartitiononthebroker.Therefore,increasingthenumberofpartitions,inaddition
toconsumingextrafilehandles,willresultinincreasedmemoryutilisationonthebrokers.Asimilar
impact will be felt on consumers, sans the file handles, which also employ per-partition buffers for
fetchingrecords.
A further impact of wide topics may be felt due to the limitations of the inter-broker replication
processanditsunderlyingthreadingmodel.Specifically,abrokerwillallocateonethreadforevery
other broker that it maintains a connection with, which covers the set of replicated partitions —
where the two peers have a leader-follower relationship. The replication threads may act as a
bottleneckwhenshuttlingrecordsfromthepartitionleadertoin-syncreplicas,andtherebyimpact
the publishing latency when the producer requests all replicas to acknowledge the writes. This
problem is ameliorated when the number of brokers increases, as the growth of the cluster has

Chapter6: DesignConsiderations 86
a downward effect on the ratio of partitions to brokers, taking the pressure off each connection.
Confluent—oneofthemajorcontributorstoApacheKafka—recommendslimitingthenumberof
partitionsperbrokerto100×b×r,whereb isthenumberofbrokersinaKafkaclusterandr isthe
replicationfactor.
So while a wider topic provides for greater theoretical throughput, it does carry practical and
immediate implications on the client and broker performance. The impacts of widening individual
topicsmaynotbesubstantial,buttheymaybefeltinaggregate.Thisisnottosaythattopicsshould
not be over-provisioned; rather, decisions regarding the sizing of the topics and the extent of over-
provisioning should not be taken on a whim. The broker topology and the overall capacity of the
clusterplayacrucialrole;thesehavetobeadequatelyspecifiedandtakenintoconsiderationwhen
sizingthetopics.
Ifdealingwithanexistingtopicthathassaturateditscapacityforconsumerparallelism,considera
staged destructive resize. Create a new topic of the desired size, using a tool such as MirrorMaker
to replicate the topic contents onto the wider topic. When the replication catches up, switch the
producerstodouble-publishtoboththeoldandthenewtopics.Theremayneedtobesomeproducer
downtimetoallowfortopicparity.Individualconsumergroupscanstartmigratingtothenewtopic
at their discretion; however, the misalignment of partitions may present a challenge with persisted
offsets.Assumingthatconsumershavebeendesignedwithidempotencyinmind,oneshouldbeable
tosettheauto.offset.resetpropertytoearliesttoforcethereprocessingoftherecordsfromthe
beginning.Dependingontheretentionofthetopic,thismaytakesometime,whichwillalsodelay
the consumers’ ability to process new records. Alternatively, one can reset the offsets to a specific
timestamp,whichwillsubstantiallyreducethequantityofreplayedrecords.Somereprocessingwill
likelybeunavoidable;suchisthepriceforresizingstrongly-orderedtopics.(Thedouble-publishing
codecanberemovedwhenallconsumershavebeenmigrated.)
Scaling of the consumer group
Kafka will allocate partitions approximately evenly among members of a consumer group, up to
the width of the topic. So to increase parallelism, one must ensure sufficient consumer instances
in the group. Allocating a fixed number of instances to the group is usually not economical, as it
may result in idle capacity, particularly for event streams that exhibit cyclic or bursty loading. The
recommended approach is to employ an automated horizontal scaling technique to dynamically
expandorcontractthepopulationofthegroupinresponsetoloaddemand.Forexample,ifdeploying
theconsumergroupwithinapubliccloudenvironmentlikeAWS,onemayuseanautoscalinggroup
to provision additional instances based on CPU utilisation metrics. Alternatively, if the consumer
application is containerised, the use of a container orchestration platform is recommended. For ex-
ample,whendeployedinKubernetes,onewouldemployhorizontalpodautoscalingtodynamically
sizetheconsumergroup.

Chapter6: DesignConsiderations 87
Internal consumer parallelism
An alternate way of increasing consumer throughput, without widening the topic or scaling the
consumer group, is to exploit parallelism within the consumer process. This can be achieved by
partitioning the workload among a pool of threads by independently hashing the record keys
to maintain local order. This strategy would be classed as vertical scaling, requiring increased
parallelism on each consumer node in exchange for reducing the number of consumers, and hence
thenumberofpartitions.
Idempotence and exactly-once delivery
Chapter 3: Architecture and Core Concepts had introduced the concepts of delivery guarantees,
stating that Kafka allows for two different modes of delivery by simply shifting the point when
theconsumercommitsitsoffsets.At-leastonceandat-most-onceguaranteeswerenamed,butthere
wasnomentionofanexactly-once guarantee.Thismightbeagoodseguetodiscussthedifferences
between delivery modes; ultimately, it will help us understand what it means to do something
‘exactlyonce’.
The role of messaging middleware is to decouple communications between collaborating parties.
When a sender publishes a message, there is an assumption that the receiver (or receivers, as
there may be multiple such parties) will eventually consume and process the message. Messaging
middleware is generally divided into two categories: those that offer at-most-once and those that
offerat-least-once guarantees.AndthenwehaveKafka,whichhasafootineachcamp.
The at-most-once guarantee simply means that a message is never redelivered to its recipient, no
matter the contingency. The consumer might read the record, but then fail for whatever reason
beforeprocessingtherecord.Iftheoffsetsforthesaidrecordwerecommittedbeforetherecordwas
processed,thenthereassignmentofthepartitionfollowingtheconsumer’sfailurewillresultinthe
skippingoftherecordbythenewconsumer.
Somemessagingsystemsaretrulyfire-and-forget,inthesensethattheremightnotevenbeaninitial
attempt to deliver the message in the first place. For example, some message brokers support load
shedding,inwhichcaseamessagemightbepurgedfromthequeuetoavoidaccumulatingabacklog
ofstalemessages.Othermessagingsystems,suchasZeroMQ,mightbepurelyin-memory,inwhich
case the loss of a node will result in the loss of undelivered messages. Some systems use the term
‘maybe-once’asastand-infor‘at-most-once’,whichseemsmorefittinginsomecases.Kafka’stake
onat-most-onceprocessingisslightlydifferentfromsomeofitscounterparts.Providedthatarecord
hasbeenstablypersistedtoatopic,andiswithintheretentionperiod,Kafkawillalwaysallowthe
consumertoreadtherecordatleastonce,nomatterwhat.Sotheterm‘at-most-once’appliestothe
processingoftherecord,ratherthantothemereactofreadingtherecord.
The at-least-once guarantee means that a message will only be marked as delivered when it
completesitsentirejourneywithintheconsumerapplication.Failurepriortothispointistreatedas
non-deliveryandaretrywillensue.

Chapter6: DesignConsiderations 88
Theterm‘delivery’mayseemsomewhatconfusing,especiallyifoneperceivesdeliveryina
postalsense.Deliveryisnotjustleavingarecordattheconsumer’sdoorstep,butseeingthe
consumer‘sign’forthedeliverybycommittingtherecord’soffset.
Using the at-most-once approach for delivery is acceptable in many cases, especially where the
occasionallossofarecorddoesnotleaveasysteminaperpetuallyinconsistentstate.At-most-once
delivery is useful where the source of the record is continuously, within a fixed interval, emitting
updatestosomeentityofinterest,suchthatthelossofonerecordcanberecoveredfrominbounded
time. Conversely, the at-least-once approach is more fitting where the loss of a record constitutes
an irreversible loss of data, violating some fundamental invariant of the system. But the flip side
is that processing a record multiple times may introduce undesirable side-effects. This is where
the notion of exactly-once processing enters the scene. In fact, when contrasting at-least-once with
at-most-oncedeliverysemantics,anoften-askedquestionis:Whycan’twejusthaveitonce?
Withoutdelvingintotheacademicdetails,whichinvolveconjecturesandimpossibilityproofs,itis
sufficient to say that exactly-once semantics are not possible without tight-knit collaboration with
the consumer application. As disappointing as it may sound, a messaging platform cannot offer
exactly-onceguaranteesonitsown.Whatdoesthismeaninpractice?
To achieve the coveted exactly-once semantics, consumers in event streaming applications must be
idempotent. In other words, processing the same record repeatedly should have no net effect on
theconsumerecosystem.Ifarecordhasnoadditiveeffects,theconsumerisinherentlyidempotent.
(Forexample,iftheconsumersimplyoverwritesanexistingdatabaseentrywithanewone,thenthe
updateisnaturallyidempotent.)Otherwise,theconsumermustcheckwhetherarecordhasalready
beenprocessed,andtowhatextent,priortoprocessingtherecord.Thecombinationofat-least-once
deliveryandconsumeridempotencecollectivelyleadstoexactly-oncesemantics.
The design of an idempotent consumer mandates that all effects of processing a record must be
traceable back to the record. For example, a record might require updating a database, invoking
some service API, or publishing one or more records to a set of downstream topics. The latter is
particularly common in SEDA systems, which are essentially graphs of processing nodes joined
by topics. When a consumer processes a record, it will have no awareness of whether the record
is being processed for the first time, or whether the given record is a repeat attempt of an earlier
failed delivery. As such, the consumer must always assume that a record is a duplicate, and handle
itaccordingly.Everypotentialside-effectmustbecheckedtoensurethatithasn’talreadyoccurred,
before attempting it a second time. When a side-effect is itself idempotent, then it can be repeated
unconditionally.
Insomecases,theremaynotbeaneasywaytodeterminewhetherapotentialside-effecthadalready
occurredasaresultofapreviousaction.Forexample,theside-effectmightbetopublisharecordon
another topic; there is often no practical way of querying for the presence of a prior record. Kafka
offers an advanced mechanism for correlating the records consumed from input topics with the
resulting records on output topics — this is discussed inChapter 18: Transactions. Transactions can
create joint atomicity and isolation around the consumption and production of records, such that
eitherallscopedactionsappeartohaveoccurred,ornone.

Chapter6: DesignConsiderations 89
Wherethetargetendpointisa(non-Kafka)messagequeue,thedownstreamreceivermustbemade
idempotent. In other words, two (or more) identical records with different offsets must not result
in material duplication somewhere down the track. This is called end-to-end idempotence. As the
namesuggests,thisguaranteespanstheentiretyofanevent-streaminggraph,coveringallnodesand
edges.Inpractice,thisisachievedbyensuringthatanytwoneighbouringnodeshaveanestablished
mechanismforidempotentcommunication.
This chapter has explored some of the fundamental considerations pertinent to the design and
constructionofsafeandperformanteventstreamingapplications.
We started by covering the roles and responsibilities of the various parties collaborating in the
constructionofdistributedevent-drivenapplications.Thekeytakeawayisthatthepartiespublishing
orconsumingeventscanbelikenedtoserviceprovidersandinvokers,andtheirrolesvarydepending
on the messaging topology. We also explored scenarios where producers and consumers might
disagreeonthedomainmodel,andthemethodsbywhichthiscanberesolved.
The concept of key-centric record parallelism — Kafka’s trademark performance enhancer — has
been explored. We looked at the factors that constrain the consumers’ ability to process events in
parallel,andthedesignconsiderationsthatimpacttheproducingparty.
Finally,wecontrastedat-most-onceandat-least-oncedeliveryguaranteesandarrivedatthedesign
requirements for exactly-once — namely, a combination of at-least-once delivery and consumer
idempotence.

| Chapter |     | 7:  | Serialization |     |     |     |
| ------- | --- | --- | ------------- | --- | --- | --- |
TheexampleswehavecomeacrosssofardemonstratedfundamentalKafkaproducerandconsumer
behaviour by serializing basic types, such as strings. While this may be sufficient to garner an
introductorylevelofawareness,itisoflimiteduseinpractice,asreal-lifeapplicationsrarelypublish
orconsumeunstructuredstrings.
Distributed applications communicating in either a message-passing style or as part of an event-
drivenarchitecturewillutiliseabroadcatalogueofstructureddatatypesandcorrespondingschema
contracts. The business logic embedded in producer and consumer applications will typically deal
withnativedomainmodels,requiringabridgingmechanismtomarshalthesemodelstoKafkatopics
whenproducingrecords,andperformtheoppositewhenconsumingfromatopic.
This chapter covers the broad topic of record serialization. In the course of the discussion, we
shallalsoexplorecomplementarydesignpatternsthatstreamlinetheinterfacingofanapplication’s
businesslogicwiththeunderlyingeventstream.
| Key and | value |     | serializer |     |     |     |
| ------- | ----- | --- | ---------- | --- | --- | --- |
PriorexampleshaverevealedthattheKafkaProducerandConsumerAPI,aswellastheProducerRecord
and ConsumerRecord classes, are generically typed. The Producer interface is parametrised with a
keyandavaluetype,denotedKandVinthetypeparameterlist:
/**
| * The interface      |     | for the | {@link | KafkaProducer} |     |     |
| -------------------- | --- | ------- | ------ | -------------- | --- | --- |
| * @see KafkaProducer |     |         |        |                |     |     |
| * @see MockProducer  |     |         |        |                |     |     |
*/
| public interface |         | Producer<K, |     | V> extends | Closeable | {   |
| ---------------- | ------- | ----------- | --- | ---------- | --------- | --- |
| /** some         | methods | omitted     | for | brevity    | */        |     |
/**
| * See {@link |     | KafkaProducer#send(ProducerRecord)} |     |     |     |     |
| ------------ | --- | ----------------------------------- | --- | --- | --- | --- |
*/
| Future<RecordMetadata> |     |     | send(ProducerRecord<K, |     |     | V> record); |
| ---------------------- | --- | --- | ---------------------- | --- | --- | ----------- |
/**
| * See {@link |     | KafkaProducer#send(ProducerRecord, |     |     |     | Callback)} |
| ------------ | --- | ---------------------------------- | --- | --- | --- | ---------- |
*/

Chapter7: Serialization 91
Future<RecordMetadata> send(ProducerRecord<K, V> record,
Callback callback);
}
Thesend()methodsreferencethetypeparameters,requiringthesuppliedProducerRecordtobeof
amatchinggenerictype.
Kafka’singrainedtype-safetymechanismassumesapairofcompatibleserializersforsupportedkey
andvaluetypes.Acustomserializermustconformtotheorg.apache.kafka.common.serialization.Serializer
interface,listedbelow.
public interface Serializer<T> extends Closeable {
default void configure(Map<String, ?> configs, boolean isKey) {
// intentionally left blank
}
byte[] serialize(String topic, T data);
default byte[] serialize(String topic, Headers headers, T data) {
return serialize(topic, data);
}
@Override
default void close() {
// intentionally left blank
}
}
Serializersareconfiguredinoneoftwoways:
1. Passing the fully-qualified class name of a Serializer implementation to the producer via
the key.serializer and the value.serializer properties. Note, there are no default values
assignedtotheseproperties.
2. DirectlyinstantiatingtheserializerandpassingitasareferencetoanoverloadedKafkaProducer
constructor.
Theproperty-basedmechanismhastheadvantageofsimplicity,inthatitlivesalongsidetherestof
the producer configuration. One can look at the configuration properties and instantly determine
that the producer is configured with a specific key and value serializer. The drawback of this con-
figuration style is that it requires theSerializer implementation to include a public, no-argument
constructor. It also makes it difficult to configure. Because the serializer is instantiated reflectively
bytheproducerclient,theapplicationcodeisunabletoinjectitsownsetofargumentsatthepoint
of initialisation. The only way to configure a reflectively-instantiated serializer is to supply a set
of custom properties to the producer, then retrieve the values of these properties in theSerializer
implementation,usingtheoptionalconfigure()callback:

Chapter7: Serialization 92
/**
* Configure this class.
* @param configs configs in key/value pairs
* @param isKey whether is for key or value
*/
default void configure(Map<String, ?> configs, boolean isKey) {
// intentionally left blank
}
Theconfigure()methodalsohelpstheinstancedeterminewhetheritisakeyoravalueserializer.
Anotherdrawbackoftheproperty-basedapproachisthatitignoresthegenerictypeconstraintsim-
posedbytheProducerinterface.Forexample,itispossibletoinstantiateaKafkaProducer<Integer,
String>usingaFloatSerializerforthekeyandaByteArraySerializerforthevalue.Theproblem
will remain unnoticed until you try to publish the first record, which will summarily fail with a
ClassCastException.
Comparedtotheproperty-basedapproach,thepassingofapre-instantiatedserializertoaKafkaProducer
simultaneouslysolvestheproblemsofmaintaininggenerictype-safetyandtheconfigurationofthe
Serializer instance. The application code will instantiate a Serializer and configure it appropri-
atelybeforeinvokingtheKafkaProducerconstructor.Inturn,theconstructor’ssignaturewillensure
thatthegivenpairof SerializerinstancesconformtotheKandVgenerictypeparameters.
The Kafka client library comes with several pre-canned serializers for common data types, listed
below.
Supportedserializers

Chapter7: Serialization 93
Inmostapplications,recordkeysaresimpleunstructuredvaluessuchasintegers,strings,orUUIDs,
and a built-in serializer will suffice. Record values tend to be structured payloads conforming to
some pre-agreed schema, represented using a text or binary encoding. Typical examples include
JSON,XML,Avro,Thrift,andProtocolBuffers.WhenserializingacustompayloadtoaKafkarecord,
therearegenerallytwoapproachesonemaypursue.Theseare:
1. Implementacustomserializertodirectlyhandlethepayload.
2. Serializethepayloadattheapplicationlevel.
Thefirstapproachisidiomatic;unquestionably,itismorefittingtothedesignoftheKafkaAPI.We
wouldsubclassSerializer,implementingitsserialize()method:
/**
* Convert {@code data} into a byte array.
*
* @param topic topic associated with data
* @param data typed data
* @return serialized bytes
*/
byte[] serialize(String topic, T data);
The serialize() method accepts the data argument that is typed in accordance with the generic
typeconstraintoftheSerializerinterface.Fromthereitisjustamatterofmarshallingthepayload
toabytearray.
The alternate method involves piggybacking on an existing serializer that matches the underlying
encoding. When dealing with text-based formats, such as JSON or XML, one would use the
StringSerializer. Conversely, when dealing with binary data, the ByteArraySerializer would
beselected.ThisleavesKafka’sProducerRecordandProducerinstanceunawareoftheapplication-
level datatype, relying on the application code to pre-serialize the value before constructing a
ProducerRecord.
A potential advantage of a custom Kafka serializer over application-level serialization is the addi-
tionaltype-safetythattheformeroffers.Lookingatitfromadifferentlens,theneedfortype-safety
atthelevelofaKafkaproducerisquestionable,asitwouldlikelybeencapsulatedwithinadedicated
messaginglayer.
This is a good segue into layering. A well-thought-out application will clearly separate business
logic from the persistence and messaging concerns. For example, you don’t expect to find JDBC
Connectioninstancesscatteredunceremoniouslyamongthebusinesslogicclassesofawell-designed
application. (Nor are JDBC classes type-safe for that matter.) By the same token, it makes sense for
the Producer class to also be encapsulated in its own layer, ideally using an interface that allows
themessagingcodetobemockedoutindependentlyaspartofunittesting.Throughoutthechapter,

Chapter7: Serialization 94
we will list arguments in favour of encapsulating common messaging concerns within a dedicated
layer.
Returning to the question of a custom serializer versus a piggybacked approach, the former is the
idiomatic approach, and for this reason we will stick with custom (de)serializers throughout the
chapter.
Sending events
For the forthcoming discussion, consider a contrived event streaming scenario involving a basic
applicationformanagingcustomerrecords.
The complete source code for the upcoming examples
is available at github.com/ekoutanov/effectivekafka⁸ in the
src/main/java/effectivekafka/customerevents directory. Most of the relevant code
listingsarealsoincludedhereforthereader’sconvenience;someofthemoreesotericitems
may have been omitted, but they should nonetheless be present in the effectivekafka
repositoryonGitHub.
Every change to the customer entity results in the publishing of a corresponding event to a single
Kafka topic, keyed by the customer ID. Each event is strongly typed, but there are several event
classes and each is bound to a dedicated schema. The POJO representation of these events might
be CreateCustomer, UpdateCustomer, SuspendCustomer, and ReinstateCustomer. The abstract base
classforallcustomer-relatedeventswillbeCustomerPayload.Thebaseclassalsohousesthecommon
fields,whichforthesakeofsimplicityhavebeenreducedtoasingleUUID-baseduniqueidentifier.
ThisistheIDofthenotionalcustomerentitytowhichtheeventrefers.(Forsimplicity,theexamples
willnotcontainthepersistententities—justtheeventnotifications.)
We are going to assume that records should be serialized using JSON. Along with Avro, JSON is
one of the most popular formats for streaming event data over Kafka. The examples in this book
use the FasterXML Jackson library for working with JSON, which is the de facto JSON parser
within the Java ecosystem. Subclasses of CustomerPayload are specified using a @JsonSubTypes
annotation,whichallowsustouseJackson’sbuilt-insupportforpolymorphictypes.Everyserialized
CustomerPayload instance will contain a type property, specifying an aliased name of the concrete
type,forexample,CREATE_CUSTOMERfortheCreateCustomerclass.Jacksonusesthispropertyasahint
duringdeserialization,pickingthecorrectsubclassof CustomerPayloadtomaptheJSONdocument
to.
⁸https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/customerevents

95
| Chapter7: Serialization |     |     |     |
| ----------------------- | --- | --- | --- |
import java.util.*;
import com.fasterxml.jackson.annotation.*;
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME,
include=JsonTypeInfo.As.EXISTING_PROPERTY,
property="type")
@JsonSubTypes({
@JsonSubTypes.Type(value=CreateCustomer.class,
name=CreateCustomer.TYPE),
@JsonSubTypes.Type(value=UpdateCustomer.class,
name=UpdateCustomer.TYPE),
@JsonSubTypes.Type(value=SuspendCustomer.class,
name=SuspendCustomer.TYPE),
@JsonSubTypes.Type(value=ReinstateCustomer.class,
name=ReinstateCustomer.TYPE)
})
| public abstract | class | CustomerPayload | {   |
| --------------- | ----- | --------------- | --- |
@JsonProperty
| private              | final UUID id; |       |     |
| -------------------- | -------------- | ----- | --- |
| CustomerPayload(UUID |                | id) { |     |
| this.id              | = id;          |       |     |
}
| public | abstract String    | getType(); |     |
| ------ | ------------------ | ---------- | --- |
| public | final UUID getId() | {          |     |
| return | id;                |            |     |
}
| protected | final String | baseToString() | {   |
| --------- | ------------ | -------------- | --- |
| return    | "id=" + id;  |                |     |
}
}
Exposing a stable alias rather than the fully-qualified Java class name makes our message
schemaportable,enablingustointeroperatewithnon-Javaclients.Thisalsoalignswiththe
cornerstone principle of event-driven architecture — the producer has minimal awareness
ofthedownstreamconsumers,andmakesnoassumptionastotheirroleandcause,northeir
implementation.
Forasamplingofatypicalconcreteevent,wehavetheCreateCustomerclass.Thereareafewothers,

96
| Chapter7: | Serialization |     |     |     |     |     |     |     |
| --------- | ------------- | --- | --- | --- | --- | --- | --- | --- |
buttheyareconceptuallysimilar.
| public | final | class CreateCustomer |        | extends            | CustomerPayload |     | {   |     |
| ------ | ----- | -------------------- | ------ | ------------------ | --------------- | --- | --- | --- |
| static | final | String               | TYPE = | "CREATE_CUSTOMER"; |                 |     |     |     |
@JsonProperty
| private | final | String | firstName; |     |     |     |     |     |
| ------- | ----- | ------ | ---------- | --- | --- | --- | --- | --- |
@JsonProperty
| private | final                              | String | lastName;                  |     |      |        |            |     |
| ------- | ---------------------------------- | ------ | -------------------------- | --- | ---- | ------ | ---------- | --- |
| public  | CreateCustomer(@JsonProperty("id") |        |                            |     | UUID | id,    |            |     |
|         |                                    |        | @JsonProperty("firstName") |     |      | String | firstName, |     |
|         |                                    |        | @JsonProperty("lastName")  |     |      | String | lastName)  | {   |
super(id);
| this.firstName |     | =           | firstName; |     |     |     |     |     |
| -------------- | --- | ----------- | ---------- | --- | --- | --- | --- | --- |
| this.lastName  |     | = lastName; |            |     |     |     |     |     |
}
@Override
| public | String | getType() | {   |     |     |     |     |     |
| ------ | ------ | --------- | --- | --- | --- | --- | --- | --- |
| return | TYPE;  |           |     |     |     |     |     |     |
}
| public | String     | getFirstName() |     | {   |     |     |     |     |
| ------ | ---------- | -------------- | --- | --- | --- | --- | --- | --- |
| return | firstName; |                |     |     |     |     |     |     |
}
| public | String    | getLastName() |     | {   |     |     |     |     |
| ------ | --------- | ------------- | --- | --- | --- | --- | --- | --- |
| return | lastName; |               |     |     |     |     |     |     |
}
@Override
| public | String                               | toString() | {          |             |             |        |     |     |
| ------ | ------------------------------------ | ---------- | ---------- | ----------- | ----------- | ------ | --- | --- |
| return | CreateCustomer.class.getSimpleName() |            |            |             |             | + " [" |     |     |
|        | + baseToString()                     |            | + ",       | firstName=" | + firstName |        |     |     |
|        | + ",                                 | lastName=" | + lastName | + "]";      |             |        |     |     |
}
}
Ideally, we would like to inject a high-level event sender into the business logic, then have our
business logic invoke the sender whenever it needs to produce an event, without concerning itself
withhowtheeventisserializedorpublishedtoKafka.Thisistheperfectcaseforaninterface:

97
| Chapter7: | Serialization |     |     |     |     |     |     |
| --------- | ------------- | --- | --- | --- | --- | --- | --- |
import java.io.*;
import java.util.concurrent.*;
import org.apache.kafka.clients.producer.*;
| public                 | interface               |               | EventSender |                      | extends Closeable      | {         |     |
| ---------------------- | ----------------------- | ------------- | ----------- | -------------------- | ---------------------- | --------- | --- |
| Future<RecordMetadata> |                         |               |             | send(CustomerPayload |                        | payload); |     |
| final                  | class                   | SendException |             |                      | extends Exception      | {         |     |
|                        | private                 | static        | final       | long                 | serialVersionUID       | =         | 1L; |
|                        | SendException(Throwable |               |             |                      | cause) { super(cause); |           | }   |
}
| default | RecordMetadata |                      |                              | blockingSend(CustomerPayload |                      |     | payload) |
| ------- | -------------- | -------------------- | ---------------------------- | ---------------------------- | -------------------- | --- | -------- |
|         | throws         | SendException,       |                              |                              | InterruptedException |     | {        |
|         | try {          |                      |                              |                              |                      |     |          |
|         | return         | send(payload).get(); |                              |                              |                      |     |          |
|         | } catch        | (ExecutionException  |                              |                              | e) {                 |     |          |
|         | throw          | new                  | SendException(e.getCause()); |                              |                      |     |          |
}
}
@Override
| public | void | close(); |     |     |     |     |     |
| ------ | ---- | -------- | --- | --- | --- | --- | --- |
}
The application might want to send records asynchronously — continuing without waiting for an
outcome, or synchronously — blocking until the record has been published. We have specified a
payload)methodsignaturefortheasynchronous
| Future<RecordMetadata> |     |     |     | send(CustomerPayload |     |     |     |
| ---------------------- | --- | --- | --- | -------------------- | --- | --- | --- |
operation—tobeimplementedbytheconcreteEventSendersubclass.Thesynchronouscaseistaken
careofbytheblockingSend()method,whichsimplydelegatestosend(),blockingontheresultof
the returned Future. The sender implementation may choose to override this method with a more
suitableoneifneedbe.(Hopefully,thedefaultimplementationisgoodenough.)
Next,wearegoingtolookatasampleuserof EventSender—theProducerBusinessLogicclass.

98
| Chapter7: | Serialization                     |       |                       |         |         |     |     |
| --------- | --------------------------------- | ----- | --------------------- | ------- | ------- | --- | --- |
| public    | final                             | class | ProducerBusinessLogic |         | {       |     |     |
| private   |                                   | final | EventSender           | sender; |         |     |     |
| public    | ProducerBusinessLogic(EventSender |       |                       |         | sender) | {   |     |
|           | this.sender                       |       | = sender;             |         |         |     |     |
}
| public | void   | generateRandomEvents()            |     |                      |     |        |           |
| ------ | ------ | --------------------------------- | --- | -------------------- | --- | ------ | --------- |
|        | throws | SendException,                    |     | InterruptedException |     | {      |           |
|        | final  | var create                        | =   |                      |     |        |           |
|        | new    | CreateCustomer(UUID.randomUUID(), |     |                      |     | "Bob", | "Brown"); |
blockingSend(create);
|     | if (Math.random() |                                    | >      | 0.5) { |            |     |           |
| --- | ----------------- | ---------------------------------- | ------ | ------ | ---------- | --- | --------- |
|     | final             | var                                | update | =      |            |     |           |
|     |                   | new UpdateCustomer(create.getId(), |        |        | "Charlie", |     | "Brown"); |
blockingSend(update);
}
|     | if (Math.random() |     | >       | 0.5) {                                 |     |     |     |
| --- | ----------------- | --- | ------- | -------------------------------------- | --- | --- | --- |
|     | final             | var | suspend | = new SuspendCustomer(create.getId()); |     |     |     |
blockingSend(suspend);
|     | if (Math.random() |     |           | > 0.5) {                                 |     |     |     |
| --- | ----------------- | --- | --------- | ---------------------------------------- | --- | --- | --- |
|     | final             | var | reinstate | = new ReinstateCustomer(create.getId()); |     |     |     |
blockingSend(reinstate);
}
}
}
| private |                               | void           | blockingSend(CustomerPayload |                      | payload)  |     |     |
| ------- | ----------------------------- | -------------- | ---------------------------- | -------------------- | --------- | --- | --- |
|         | throws                        | SendException, |                              | InterruptedException |           | {   |     |
|         | System.out.format("Publishing |                |                              | %s%n",               | payload); |     |     |
sender.blockingSend(payload);
}
}
We are not actually going to implement any life-like business logic for this example; the intention
ismerelytosimulatesomeactivityandexerciseourfutureEventSenderimplementation.

99
| Chapter7: Serialization |        |     |     |     |
| ----------------------- | ------ | --- | --- | --- |
| The complete            | sender |     |     |     |
Usinginterfacesisonlygoingtogetussofar;weneedaconcreteimplementationofaEventSender
tomaketheexamplework.Hereisthesimplestsenderimplementationthatwillgetthejobdone:
import java.util.*;
import java.util.concurrent.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;
| public final                    | class DirectSender     | implements            | EventSender     | {          |
| ------------------------------- | ---------------------- | --------------------- | --------------- | ---------- |
| private                         | final Producer<String, | CustomerPayload>      |                 | producer;  |
| private                         | final String topic;    |                       |                 |            |
| public DirectSender(Map<String, |                        | Object>               | producerConfig, |            |
|                                 | String                 | topic) {              |                 |            |
| this.topic                      | = topic;               |                       |                 |            |
| final                           | var mergedConfig       | = new HashMap<String, |                 | Object>(); |
mergedConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName());
mergedConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
CustomerPayloadSerializer.class.getName());
mergedConfig.putAll(producerConfig);
| producer | = new KafkaProducer<>(mergedConfig); |     |     |     |
| -------- | ------------------------------------ | --- | --- | --- |
}
@Override
| public Future<RecordMetadata> |              | send(CustomerPayload |     | payload) { |
| ----------------------------- | ------------ | -------------------- | --- | ---------- |
| final                         | var record = |                      |     |            |
new ProducerRecord<>(topic,
payload.getId().toString(),
payload);
| return | producer.send(record); |     |     |     |
| ------ | ---------------------- | --- | --- | --- |
}
@Override
| public void | close() { |     |     |     |
| ----------- | --------- | --- | --- | --- |
producer.close();
}
}

100
| Chapter7: | Serialization |     |     |     |     |     |
| --------- | ------------- | --- | --- | --- | --- | --- |
There really isn’t much to it. The DirectSender encapsulates a KafkaProducer, configured using
a supplied map of properties. The constructor will overwrite certain key properties in the user-
specified configuration map — properties that are required for the correct operation of the sender
andshouldnotbeinterferedwithbyexternalcode.TheDirectSenderalsorequiresthenameofthe
topic.
Thesend()methodsimplycreatesanewProducerRecordandenqueuesitforsending,delegatingto
theunderlyingProducerinstance.Beforethis,thesend()methodwillalsoassignthenewlycreated
record’skey,which,aspreviouslyagreed,isexpectedoftheproducerapplication.Bysettingthekey
tothecustomerID,weensurethatrecordsarestrictlyorderedbycustomer.
Thebenefitsoflayeringbecomeimmediatelyapparent.WithoutanEventSenderimplementationto
guidetheconstructionandsendingofrecords,theresponsibilityofenforcinginvariantswouldhave
restedwiththebusinesslogiclayer.Thispreventsusfromenforcingsimpleinvariantsthatoperateat
recordscope,suchas“thekeyofarecordmustequaltotheIDoftheencompassedcustomerevent”.
Relying on the business logic to set the key correctly is error-prone, especially when you consider
thattherewillbeseveralplaceswherethiswouldbedone.Bylayeringourproducerapplication,we
canenforcethisbehaviourdeeperinthestack,therebyminimisingcodeduplicationandavoidinga
wholeclassofpotentialbugs.
Totryouttheexample,firstlaunchtheRunRandomEventProducerclass.Asthenamesuggests,itwill
publishasequenceofrandomcustomereventsusingtheProducerBusinessLogicdefinedearlier.
| public | final  | class                 | RunRandomEventProducer |        | {             |     |
| ------ | ------ | --------------------- | ---------------------- | ------ | ------------- | --- |
| public | static | void                  | main(String[]          |        | args)         |     |
|        | throws | InterruptedException, |                        |        | SendException | {   |
|        | final  | Map<String,           | Object>                | config | =             |     |
Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ProducerConfig.CLIENT_ID_CONFIG,
"customer-producer-sample");
|     | final    | var topic         | = "customer.test";         |       |                                |           |
| --- | -------- | ----------------- | -------------------------- | ----- | ------------------------------ | --------- |
|     | try (var | sender            | = new DirectSender(config, |       |                                | topic)) { |
|     | final    | var businessLogic |                            | = new | ProducerBusinessLogic(sender); |           |
|     | while    | (true)            | {                          |       |                                |           |
businessLogic.generateRandomEvents();
Thread.sleep(500);
}
}
}
}
Assumingeverythinggoeswell,youshouldseesomelogsprintedtostandardout:

101
| Chapter7: | Serialization |     |
| --------- | ------------- | --- |
...
| omitted | for brevity |     |
| ------- | ----------- | --- |
...
| Publishing | {"id":"28361a15-7cef-47f3-9819-f8a629491c5a", | ￿   |
| ---------- | --------------------------------------------- | --- |
"firstName":"Bob","lastName":"Brown","type":"CREATE_CUSTOMER"}
| Publishing | {"id":"28361a15-7cef-47f3-9819-f8a629491c5a", | ￿   |
| ---------- | --------------------------------------------- | --- |
"type":"SUSPEND_CUSTOMER"}
| Publishing | {"id":"28361a15-7cef-47f3-9819-f8a629491c5a", | ￿   |
| ---------- | --------------------------------------------- | --- |
"type":"REINSTATE_CUSTOMER"}
| Publishing | {"id":"b3cd538c-90cc-4f5f-a5dc-b1c469fc0bf8", | ￿   |
| ---------- | --------------------------------------------- | --- |
"firstName":"Bob","lastName":"Brown","type":"CREATE_CUSTOMER"}
| Publishing | {"id":"b3cd538c-90cc-4f5f-a5dc-b1c469fc0bf8", | ￿   |
| ---------- | --------------------------------------------- | --- |
"firstName":"Charlie","lastName":"Brown","type":"UPDATE_CUSTOMER"}
| Publishing | {"id":"83bb14e7-9139-4699-a843-8b3a90ae26e2", | ￿   |
| ---------- | --------------------------------------------- | --- |
"firstName":"Bob","lastName":"Brown","type":"CREATE_CUSTOMER"}
| Publishing | {"id":"83bb14e7-9139-4699-a843-8b3a90ae26e2", | ￿   |
| ---------- | --------------------------------------------- | --- |
"firstName":"Charlie","lastName":"Brown","type":"UPDATE_CUSTOMER"}
| Publishing | {"id":"83bb14e7-9139-4699-a843-8b3a90ae26e2", | ￿   |
| ---------- | --------------------------------------------- | --- |
"type":"SUSPEND_CUSTOMER"}
| Publishing | {"id":"83bb14e7-9139-4699-a843-8b3a90ae26e2", | ￿   |
| ---------- | --------------------------------------------- | --- |
"type":"REINSTATE_CUSTOMER"}
| Publishing | {"id":"fe02fe96-a410-44a5-b636-dced53cf4590", | ￿   |
| ---------- | --------------------------------------------- | --- |
"firstName":"Bob","lastName":"Brown","type":"CREATE_CUSTOMER"}
| Publishing | {"id":"fe02fe96-a410-44a5-b636-dced53cf4590", | ￿   |
| ---------- | --------------------------------------------- | --- |
"firstName":"Charlie","lastName":"Brown","type":"UPDATE_CUSTOMER"}
Whenenougheventshavebeenemitted,stoptheproducer.SwitchovertoKafdrop;youshouldsee
aseriesofJSONrecordsappearinthecustomer.testtopic:

102
| Chapter7: Serialization |     |     |     |     |     |
| ----------------------- | --- | --- | --- | --- | --- |
Kafdrop—randomcustomerevents
| Key and | value | deserializer |     |     |     |
| ------- | ----- | ------------ | --- | --- | --- |
AnalogouslytothegenerictypeconstraintsprevalentintheproducerAPI,theConsumerinterfaceen-
forcesanequivalentconstraintvis-à-vis theConsumerRecordsclassreturnedbythepoll()method,
whichcarriesacollectionofindividualConsumerRecordobjects:
/**
| * @see KafkaConsumer |     |     |     |     |     |
| -------------------- | --- | --- | --- | --- | --- |
| * @see MockConsumer  |     |     |     |     |     |
*/
| public interface | Consumer<K, |         | V> extends  | Closeable | {   |
| ---------------- | ----------- | ------- | ----------- | --------- | --- |
| /** some         | methods     | omitted | for brevity | */        |     |
/**
| * @see | KafkaConsumer#poll(Duration) |     |     |     |     |
| ------ | ---------------------------- | --- | --- | --- | --- |
*/
| ConsumerRecords<K, |     | V> poll(Duration |     | timeout); |     |
| ------------------ | --- | ---------------- | --- | --------- | --- |
}

103
| Chapter7: Serialization |     |     |     |     |     |     |     |
| ----------------------- | --- | --- | --- | --- | --- | --- | --- |
/**
| * A container | that             | holds the | list   | {@link | ConsumerRecord} |     | per |
| ------------- | ---------------- | --------- | ------ | ------ | --------------- | --- | --- |
| * partition   | for a particular |           | topic. | There  | is one          |     |     |
* {@link ConsumerRecord} list for every topic partition returned
| * by a {@link | Consumer#poll(java.time.Duration)} |     |     |     |     | operation. |     |
| ------------- | ---------------------------------- | --- | --- | --- | --- | ---------- | --- |
*/
| public class | ConsumerRecords<K,         |         | V>  |         |       |     |     |
| ------------ | -------------------------- | ------- | --- | ------- | ----- | --- | --- |
| implements   | Iterable<ConsumerRecord<K, |         |     |         | V>> { |     |     |
| /** fields   | and methods                | omitted | for | brevity | */    |     |     |
}
Similarly to the producer scenario, a consumer must be configured with the appropriate key and
valuedeserializers.Adeserializermustconformtotheorg.apache.kafka.common.serialization.Deserializer
interface,listedbelow.
| public interface | Deserializer<T> |     | extends |     | Closeable | {   |     |
| ---------------- | --------------- | --- | ------- | --- | --------- | --- | --- |
default void configure(Map<String, ?> configs, boolean isKey) {
| // intentionally |     | left blank |     |     |     |     |     |
| ---------------- | --- | ---------- | --- | --- | --- | --- | --- |
}
| T deserialize(String |     | topic, | byte[] | data); |     |     |     |
| -------------------- | --- | ------ | ------ | ------ | --- | --- | --- |
default T deserialize(String topic, Headers headers, byte[] data) {
| return | deserialize(topic, |     | data); |     |     |     |     |
| ------ | ------------------ | --- | ------ | --- | --- | --- | --- |
}
@Override
| default          | void close() | {          |     |     |     |     |     |
| ---------------- | ------------ | ---------- | --- | --- | --- | --- | --- |
| // intentionally |              | left blank |     |     |     |     |     |
}
}
Theconsumerclientallowstheusertospecifythekeyandvaluedeserializersinoneoftwoways:
1. Passing the fully-qualified class name of aDeserializer implementation to the consumer via
thekey.deserializerorthevalue.deserializerproperty.
2. Instantiating the deserializer and passing its reference to an overloaded KafkaConsumer con-
structor.
In virtually every way, the configuration of deserializers on the consumer is consistent with its
producercounterpart.Behaviourally,deserializersarethelogicalreciprocalofserializers.
Akintotheserializationscenario,theusercanselectoneoftwostrategiesforunmarshallingdata:

Chapter7: Serialization 104
1. Implementacustomdeserializertodirectlyhandletheencodedform,suchthattheapplication
codedealsexclusivelywithtypedpayloads.
2. Piggyback on an existing deserializer, such as a StringDeserializer (for text encodings) or
a ByteArrayDeserializer (for binary encodings), deferring the final unmarshalling of the
encodedpayloadtotheapplication.
There are no strong merits of one approach over the other that are worthy of a debate. Like in the
producerscenario,wewilluseacustomdeserializertoimplementtheforthcomingexamples,being
theidiomaticapproach.
The section on serializers questioned the merits of type safety at the level of the producer, instead
advocatingforafaçadeoverthetopoftheKafkaclientcodetoinsulatethebusinesslogicfromthe
intricacies of Kafka connectivity, and to easily mock the latter in unit tests. As we will shortly
discover, the case for a dedicated insulation layer is further bolstered when dealing with the
consumerscenario.
Receiving events
Continuing from the producer example, let’s examine the routine concerns of a typical business
logiclayerthatmightresideinaconsumerapplication.Howwoulditreacttoeventsreceivedfrom
Kafka?Andmoreimportantly,howwoulditevenreceivetheseevents?
ThestandardmechanismforinteractingwithaKafkaconsumeristoblockonConsumer.poll(),then
iterate over the returned records — invoking an application-level handler for each record. Kafka’s
defaultsaroundautomaticoffsetcommittinghavealsobeendesignedspecificallyaroundthispattern
—thepoll-processloop.
The poll-process loop is a coined term, in lieu of an official name put forth by the Kafka
documentationoracommonnameadoptedbytheusercommunity.
Apoll-processlooprequiresathreadontheconsumer,aswellasallthelife-cyclemanagementcode
thatgoeswithit—whentostartthethread,howtostopit,andsoon.
Ideally, we would simply inject a high-level event receiver into the business logic, then register a
listenercallbackwiththereceivertobeinvokedeverytimethereceiverpullsarecordfromthetopic.
Perhapssomethingalongtheselines:

105
| Chapter7: Serialization                    |                             |           |           |     |
| ------------------------------------------ | --------------------------- | --------- | --------- | --- |
| public final                               | class ConsumerBusinessLogic |           | {         |     |
| private                                    | final EventReceiver         | receiver; |           |     |
| public ConsumerBusinessLogic(EventReceiver |                             |           | receiver) | {   |
| this.receiver                              | = receiver;                 |           |           |     |
receiver.addListener(this::onEvent);
}
| private                     | void onEvent(CustomerPayload |        | payload)  | {   |
| --------------------------- | ---------------------------- | ------ | --------- | --- |
| System.out.format("Received |                              | %s%n", | payload); |     |
}
}
Again,whatwedoinsidethebusinesslogicisoflittleconsequence.Thepurposeoftheseexamples
istoillustratehowthebusinesslogiclayerinteractswithKafka.
TheEventReceiverandEventListenercodelistings,respectively:
| public interface               | EventReceiver | extends    | Closeable | {   |
| ------------------------------ | ------------- | ---------- | --------- | --- |
| void addListener(EventListener |               | listener); |           |     |
void start();
@Override
void close();
}
@FunctionalInterface
| public interface             | EventListener | {         |     |     |
| ---------------------------- | ------------- | --------- | --- | --- |
| void onEvent(CustomerPayload |               | payload); |     |     |
}
This approach completely decouples the ConsumerBusinessLogic class from the consumer code,
being aware only of EventReceiver, which in itself is merely an interface. All communications
withKafkawill beproxiedviaasuitableEventReceiverimplementation.
| Corrupt | records |     |     |     |
| ------- | ------- | --- | --- | --- |
Aproducerhasthebenefitofknowingthattherecordsgiventoitbytheapplicationcodearevalid,
at least as far as the application is concerned. A consumer reading from an event stream does not
have this luxury. A rogue or defective producer may have published garbage onto the topic, which
| wouldbesummarilyfedtoalldownstream |     |     | consumers. |     |
| ---------------------------------- | --- | --- | ---------- | --- |

106
| Chapter7: Serialization |     |     |     |     |
| ----------------------- | --- | --- | --- | --- |
Ideally,weshouldhandleanypotentialdeserializationissuesgracefully.Asdeserializationiswithin
ourcontrol,wehaveseveralchoicesaroundtheerror-handlingbehaviour:
• Justlogtheerroranddiscardtherecord;
• Propagate the error to the application via the (modified) callback, along with the malformed
record;or
• Publishthemalformedrecordtoadedicated‘deadletter’topicforsubsequentinspection.
Assumingthedecisionistopasstheerrortotheapplication,themodifiedcodemightresemblethe
following:
| public final                               | class ConsumerBusinessLogic |     | {         |     |
| ------------------------------------------ | --------------------------- | --- | --------- | --- |
| public ConsumerBusinessLogic(EventReceiver |                             |     | receiver) | {   |
receiver.addListener(this::onEvent);
}
| private                     | void onEvent(ReceiveEvent | event)    | {                    |     |
| --------------------------- | ------------------------- | --------- | -------------------- | --- |
| if (!                       | event.isError()) {        |           |                      |     |
| System.out.format("Received |                           | %s%n",    | event.getPayload()); |     |
| } else                      | {                         |           |                      |     |
| System.err.format("Error    |                           | in record | %s: %s%n",           |     |
|                             | event.getRecord(),        |           | event.getError());   |     |
}
}
}
@FunctionalInterface
| public interface          | EventListener | {       |     |     |
| ------------------------- | ------------- | ------- | --- | --- |
| void onEvent(ReceiveEvent |               | event); |     |     |
}
The new ReceiveEvent class encapsulates both the CustomerPayload object — if one was unmar-
shalledsuccessfully,oraThrowableerror—ifanexceptionoccurredduringunmarshalling.Inboth
cases, the original ConsumerRecord is also included for reference, as well as the original encoded
| value.Thesourcelistingof | ReceiveEventfollows. |     |     |     |
| ------------------------ | -------------------- | --- | --- | --- |

107
| Chapter7:         | Serialization                |       |                        |                        |               |            |            |     |
| ----------------- | ---------------------------- | ----- | ---------------------- | ---------------------- | ------------- | ---------- | ---------- | --- |
| public            | final                        | class | ReceiveEvent           |                        | {             |            |            |     |
| private           |                              | final | CustomerPayload        |                        | payload;      |            |            |     |
| private           |                              | final | Throwable              | error;                 |               |            |            |     |
| private           |                              | final | ConsumerRecord<String, |                        |               | ?> record; |            |     |
| private           |                              | final | String                 | encodedValue;          |               |            |            |     |
| public            | ReceiveEvent(CustomerPayload |       |                        |                        |               | payload,   |            |     |
|                   |                              |       |                        | Throwable              | error,        |            |            |     |
|                   |                              |       |                        | ConsumerRecord<String, |               |            | ?> record, |     |
|                   |                              |       |                        | String                 | encodedValue) | {          |            |     |
| this.record       |                              |       | = record;              |                        |               |            |            |     |
| this.payload      |                              |       | = payload;             |                        |               |            |            |     |
| this.error        |                              |       | = error;               |                        |               |            |            |     |
| this.encodedValue |                              |       |                        | = encodedValue;        |               |            |            |     |
}
| public | CustomerPayload |          |     | getPayload() |     | {   |     |     |
| ------ | --------------- | -------- | --- | ------------ | --- | --- | --- | --- |
| return |                 | payload; |     |              |     |     |     |     |
}
| public | boolean |       | isError() |     | {   |     |     |     |
| ------ | ------- | ----- | --------- | --- | --- | --- | --- | --- |
| return |         | error | != null;  |     |     |     |     |     |
}
| public | Throwable |        | getError() |     | {   |     |     |     |
| ------ | --------- | ------ | ---------- | --- | --- | --- | --- | --- |
| return |           | error; |            |     |     |     |     |     |
}
| public | ConsumerRecord<String, |         |     |     | ?>  | getRecord() | {   |     |
| ------ | ---------------------- | ------- | --- | --- | --- | ----------- | --- | --- |
| return |                        | record; |     |     |     |             |     |     |
}
| public | String |               | getEncodedValue() |     | {   |     |     |     |
| ------ | ------ | ------------- | ----------------- | --- | --- | --- | --- | --- |
| return |        | encodedValue; |                   |     |     |     |     |     |
}
@Override
| public | String |                                    | toString() |         | {       |      |          |            |
| ------ | ------ | ---------------------------------- | ---------- | ------- | ------- | ---- | -------- | ---------- |
| return |        | ReceiveEvent.class.getSimpleName() |            |         |         |      | + "      | [payload=" |
|        | +      | payload                            | + ",       | error=" | + error | + ", | record=" | + record   |

108
| Chapter7: | Serialization |                |                |     |        |     |
| --------- | ------------- | -------------- | -------------- | --- | ------ | --- |
|           | + ",          | encodedValue=" | + encodedValue |     | + "]"; |     |
}
}
| The | complete | receiver |     |     |     |     |
| --- | -------- | -------- | --- | --- | --- | --- |
Now, to complete the implementation, we require a functioning EventReceiver. The listing below
isthatoftheDirectReceiver,whichisanimplementationofthepoll-processloop.
Thechoiceoftheterm‘direct’isforconsistencywiththeproducerexample.Inbothcases,
theimplementationsdirectlyemploytheunderlyingKafkaAPI,withoutdeviatingfromthe
standardbehaviouroracquiringanyadditionalcharacteristics—hencethename.
import java.time.*;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.*;
import org.apache.kafka.common.serialization.*;
import com.obsidiandynamics.worker.*;
| public  | final | class DirectReceiver |                | extends | AbstractReceiver | {   |
| ------- | ----- | -------------------- | -------------- | ------- | ---------------- | --- |
| private | final | WorkerThread         | pollingThread; |         |                  |     |
private final Consumer<String, CustomerPayloadOrError> consumer;
| private | final                      | Duration | pollTimeout; |         |                 |     |
| ------- | -------------------------- | -------- | ------------ | ------- | --------------- | --- |
| public  | DirectReceiver(Map<String, |          |              | Object> | consumerConfig, |     |
String topic,
|                  |     |                | Duration | pollTimeout)    | {          |     |
| ---------------- | --- | -------------- | -------- | --------------- | ---------- | --- |
| this.pollTimeout |     | = pollTimeout; |          |                 |            |     |
| final            | var | mergedConfig   | = new    | HashMap<String, | Object>(); |     |
mergedConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName());
mergedConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
CustomerPayloadDeserializer.class.getName());
mergedConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
false);

109
Chapter7: Serialization
mergedConfig.putAll(consumerConfig);
| consumer | = new KafkaConsumer<>(mergedConfig); |     |     |
| -------- | ------------------------------------ | --- | --- |
consumer.subscribe(Set.of(topic));
| pollingThread | = WorkerThread.builder() |     |     |
| ------------- | ------------------------ | --- | --- |
.withOptions(new WorkerOptions()
.daemon()
|     | .withName(DirectReceiver.class, |     | "poller")) |
| --- | ------------------------------- | --- | ---------- |
.onCycle(this::onPollCycle)
.build();
}
@Override
| public void | start() { |     |     |
| ----------- | --------- | --- | --- |
pollingThread.start();
}
| private void | onPollCycle(WorkerThread | t)  |     |
| ------------ | ------------------------ | --- | --- |
| throws       | InterruptedException     | {   |     |
final ConsumerRecords<String, CustomerPayloadOrError> records;
try {
| records | = consumer.poll(pollTimeout);         |      |                |
| ------- | ------------------------------------- | ---- | -------------- |
| } catch | (InterruptException                   | e) { |                |
| throw   | new InterruptedException("Interrupted |      | during poll"); |
}
| if (! records.isEmpty()) | {                  |                   |     |
| ------------------------ | ------------------ | ----------------- | --- |
| for (var                 | record : records)  | {                 |     |
| final                    | var payloadOrError | = record.value(); |     |
| final                    | var event =        |                   |     |
new ReceiveEvent(payloadOrError.getPayload(),
payloadOrError.getError(),
record,
payloadOrError.getEncodedValue());
fire(event);
}
consumer.commitAsync();
}
}
@Override
| public void | close() { |     |     |
| ----------- | --------- | --- | --- |

Chapter7: Serialization 110
pollingThread.terminate().joinSilently();
consumer.close();
}
}
The DirectReceiver maintains a single polling thread. Rather than incorporating threading from
firstprinciples,theexamplesinthisbookusetheFulcrummicro-library,availableatgithub.com/obsidiandynamics/fulcrum⁹.
Specifically, the examples import the fulcrum-worker module, which provides complete life-cycle
management on top of a conventional java.lang.Thread. A Fulcrum WorkerThread class provides
anopinionatedsetofcontrolsandtemplatesthatstandardiseallkeyaspectsofathread’sbehaviour
—theinitialstartup,steady-stateoperation,interrupt-triggeredtermination,andexceptionhandling.
Thesearetypicalconcernsinmulti-threadedapplications,whicharedifficulttogetrightandrequire
acopiousamountofnon-trivialboilerplatecodetoadequatelycoveralltheedgecases.
Our receiver takes three parameters — a map of configuration properties for the Kafka consumer,
the name of the topic to subscribe to, and a timeout value to use in Consumer.poll(). Like its
DirectSender counterpart, the DirectReceiver will overwrite certain key properties in the user-
specified configuration map — settings that are required for the correct operation of the receiver
andshouldnotbeinterferedwithbyexternalcode.
TheonPollCycle()methodrepresentsasingleiterationofthepoll-processloop.Itsroleisstraightfor-
ward — fetch records from Kafka, construct a correspondingReceiveEvent, and dispatch the event
to all registered listeners. Once all records in the batch have been dispatched, the commitAsync()
method of the consumer is invoked, which will have the effect of asynchronously committing the
offsetsforallrecordsfetchedinthelastcalltopoll().Beingasynchronous,theclientwilldispatch
the request in a background thread, not waiting for the commit response from the brokers; the
responseswillarriveatanindeterminatetimeinthefuture,aftercommitAsync()returns.
The use of commitAsync() makes it possible to process multiple batches before the effects
ofcommittingthefirstarereflectedonthebrokers,increasingthewindowofuncommitted
records.Andwhilethiswillleadtoagreaternumberofreplayedrecordsfollowingpartition
reassignment, this behaviour is still consistent with the concept of at-least-once delivery.
Using the blocking commitSync() variant reduces the number of uncommitted records to
the in-flight batch at the expense of throughput. Unless the cost of processing a record is
veryhigh,theasynchronouscommitmodelisgenerallypreferred.
Finally,theclose()methoddisposesofthereceiverbyterminatingthepollingthread,awaitingits
termination,thenclosingtheKafkaconsumer.
Noticehowwehavecaughtanodd-lookingorg.apache.kafka.common.errors.InterruptException
in the body of the onPollCycle() method, re-throwing a java.lang.InterruptedException in
its place. This is one of the idiosyncrasies of the Kafka API — its origins are traceable to Scala,
⁹https://github.com/obsidiandynamics/fulcrum

111
| Chapter7: Serialization |     |     |     |     |
| ----------------------- | --- | --- | --- | --- |
which does not support checked exceptions, arguing vigorously against their use. As a result, the
unchecked-exceptions-only philosophy has been carried over to the Java port, going against the
grainofidiomaticJava.
| try {   |                                       |     |     |                |
| ------- | ------------------------------------- | --- | --- | -------------- |
| records | = consumer.poll(pollTimeout);         |     |     |                |
| } catch | (InterruptException                   | e)  | {   |                |
| throw   | new InterruptedException("Interrupted |     |     | during poll"); |
}
The standard Java thread interrupt signalling has been unceremoniously discarded in the bowels
of the KafkaConsumer client and replaced with a bespoke runtime exception type. The code above
correctsforthis,trappingthebespokeexceptionandre-throwingastandardone.Whenthisoccurs,
the Fulcrum WorkerThread will detect the interrupt and gracefully shut down the underlying
primordialthread.
Toruntheexample,launchtheRunDirectConsumerclass:
| public final  | class                | RunDirectConsumer      | {     |     |
| ------------- | -------------------- | ---------------------- | ----- | --- |
| public static | void                 | main(String[]          | args) |     |
| throws        | InterruptedException |                        | {     |     |
| final         | Map<String,          | Object> consumerConfig |       | =   |
Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ConsumerConfig.GROUP_ID_CONFIG,
"customer-direct-consumer",
ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
"earliest");
| try (var | receiver | = new DirectReceiver(consumerConfig, |     |     |
| -------- | -------- | ------------------------------------ | --- | --- |
"customer.test",
Duration.ofMillis(100))) {
| new | ConsumerBusinessLogic(receiver); |     |     |     |
| --- | -------------------------------- | --- | --- | --- |
receiver.start();
Thread.sleep(10_000);
}
}
}
This will run for ten seconds, outputting the records that have been published since the consumer
waslastrun.Sincewearerunningitforthefirsttime,expecttoseeallrecordsinthecustomer.test
topic:

Chapter7: Serialization 112
...
omitted for brevity
...
Received CreateCustomer [id=28361a15-7cef-47f3-9819-f8a629491c5a, ￿
firstName=Bob, lastName=Brown]
Received SuspendCustomer [id=28361a15-7cef-47f3-9819-f8a629491c5a]
Received ReinstateCustomer [id=28361a15-7cef-47f3-9819-f8a629491c5a]
Received CreateCustomer [id=b3cd538c-90cc-4f5f-a5dc-b1c469fc0bf8, ￿
firstName=Bob, lastName=Brown]
Received UpdateCustomer [id=b3cd538c-90cc-4f5f-a5dc-b1c469fc0bf8, ￿
firstName=Charlie, lastName=Brown]
Received CreateCustomer [id=83bb14e7-9139-4699-a843-8b3a90ae26e2, ￿
firstName=Bob, lastName=Brown]
Received UpdateCustomer [id=83bb14e7-9139-4699-a843-8b3a90ae26e2, ￿
firstName=Charlie, lastName=Brown]
Received SuspendCustomer [id=83bb14e7-9139-4699-a843-8b3a90ae26e2]
Received ReinstateCustomer [id=83bb14e7-9139-4699-a843-8b3a90ae26e2]
Received CreateCustomer [id=fe02fe96-a410-44a5-b636-dced53cf4590, ￿
firstName=Bob, lastName=Brown]
Received UpdateCustomer [id=fe02fe96-a410-44a5-b636-dced53cf4590, ￿
firstName=Charlie, lastName=Brown]
Pipelining
One material argument for layering a consumer application stems from the realm of performance
optimisation,namelyatechniquecalled‘pipelining’.
A pipeline is a decomposition of a sequential process into a set of chained stages, where the output
ofonestageisfedastheinputtothenextviaanintermediateboundedbuffer.Eachstagefunctions
semi-independently of its neighbours; it can operate for as long as at least one element is available
initsinputbufferandwillalsohaltforaslongastheoutputbufferisfull.
Pipelining allows the application to recruit additional threads — increasing the throughput at the
expense of processor utilisation. To appreciate where the performance gains may be obtained,
consider the routine operation of a regular consumer application — identical to the one we just
implemented—illustratedbelow.

Chapter7: Serialization 113
Consumerwithoutadditionalpipelining
The KafkaConsumer implementation utilises a rudimentary form of pipelining under the hood,
prefetching and buffering records to accelerate content delivery. In other words, our application is
alreadymulti-threadedwithushardlyrealisingthis—separatingtherecordretrievalandprocessing
operationsintodistinctexecutioncontexts.Themainpoller threadwill—
1. Invoke KafkaConsumer.poll(), potentially sending fetch queries to the cluster. If there are
pendingqueriesforwhichresponseshavenotyetbeenreceived,nofurtherqueriesareissued.

Chapter7: Serialization 114
2. Wait for the outcome of a pending fetch, checking the status of the fetch buffer. The accumu-
lation of the fetch results will be performed by a background I/O thread. This operation will
blockuntilthedatabecomesavailableorthepolltimeoutexpires.
3. Decompressthebatchifcompressionwassetontheproducer.
4. Deserializeeachrecordinthebatch.
5. Priortoreturningfrompoll(),initiateaprefetch.Thisactionisnon-blocking;itfiresoffaset
ofqueriestothebrokersandreturnsimmediately,withoutwaitingforresponses.Thecontrolis
transferredbacktotheapplicationcode.Whentheprefetchresponseseventuallyarrive,these
willbedecompressedanddeserialized,withtheresultingrecordsplacedintoafetchbuffer.
6. The application then applies the requisite business logic to each record by invoking the regis-
tered EventListener callbacks. In most applications, this would involve updating a database
and possibly other I/O. More often than not, the cost of processing a record is significantly
greaterthanthecostofreadingitoffaKafkatopic.
7. Aftertheapplicationhascompletedprocessingthebatch,itcansafelycommittheconsumer’s
offsets by invoking Consumer.commitAsync(). This will have the effect of committing the
offsets for all records returned during the last poll(). Being an asynchronous operation, the
committingofoffsetswilloccurinabackgroundI/Othread.
Thelaststepinthepoll-processloopisoptional,inthatwecouldhavejustdeferredtotheconsumer’s
built-in‘automaticoffsetcommitting’featurebyleavingenable.auto.commitatitsdefaultvalueof
true.IntheDirectReceiverexample,wehavechosentodisableoffsetauto-commit,goingwiththe
manual option instead. The principal advantage of committing offsets manually is that it results
in a narrower window of uncommitted offsets compared to the automatic option, even if done
asynchronously, as the auto-commit is bounded by a timer and lazily initiated. Conversely, the
benefitoftheoffsetauto-commitfeatureisthereductioninthenumberofcommitrequestssentto
thebrokers,whichhasasmallpositiveeffectonthroughput.
TheI/Othreadworksinthebackground.Amongitschiefresponsibilitiesisthehandlingofresponses
fromearlierfetchrequests,accumulatingthereceivedbatchesinafetchbuffer.Withtheassistance
ofthebackgroundprefetchmechanism,andassumingasteadyflowofrecordsthroughthepipeline,
mostcallstopoll()shouldnotblock.
While the KafkaConsumer allows for pipelining via its prefetch mechanism, the implementation
stacksthedeserializationofrecordsandtheirsubsequenthandlingontoasinglethreadofexecution.
Thethreadthatisresponsiblefordeserializingtherecordsisalsousedtodrivebusinesslogic.Both
operations are potentially time-consuming; when a record is being deserialized, the polling thread
isunabletoexecutetheEventListenercallbacks,andviceversa.
Whilewecannotcontrolthiselementoftheclient’sstandardbehaviour,wecanmakegreateruseof
the pipeline pattern, harnessing additional performance gains by separating record deserialization
frompayloadhandling.
Thediagrambelowillustratesthis.

Chapter7: Serialization 115
Consumerwithpipelining
The fetching, deserialization, and processing of records has now been separated into three stages,
eachpoweredbyadedicatedthread.Forsimplicity,wearegoingtorefertotheseastheI/Othread,
thepollingthread,andtheprocessingthread.TheI/OthreadisnativetotheKafkaConsumerandits
behaviourisunchangedfromthepreviousexample.
Thepollingthreadisalteredintwocrucialways:
1. Rather than invoking the EventListener in step 6, the thread will append the received record
ontoaboundedbuffer.InaJavaapplication,wecanuseanArrayBlockingQueueoraLinkedBlockingQueue
toimplementthisbuffer.Thisoperationwillblockifthequeueisatitsmaximumcapacity.
2. Insteadofcommittingtheoffsetsoftherecentbatch,thepollingthreadwillcommitjustthose
offsetsthathavebeenappendedtothe‘pendingoffsetsqueue’bytheprocessingthread.
Ontheprocessingthread,wehavethefollowingsteps:
1. Remove the queued record from the bounded buffer. This operation will block if the queue is
empty.
2. InvoketheregisteredEventListenercallbackstoprocesstherecord.
3. Havingprocessedtherecord,appendacorrespondingentrytothe‘pendingoffsetsqueue’.This
entryspecifiesthetopic-partitionpairfortherecord,aswellasitsoffsetplusone.

Chapter7: Serialization 116
The last steps in each of the two threads may appear confusing at first. Couldn’t we just commit
the offsets after enqueuing the batch? What is the purpose of shuttling the offsets back from the
processing thread to the polling thread? And why would we add one to the offset of a processed
record?
Whenpipeliningrecords,oneneedstotakeparticularcarewhencommittingtherecords’offsets,as
therecordsmightnotbeprocessedforsometimeafterbeingqueued.Dependingonthecapacityof
theboundedbuffer,thepollingloopmaycompleteseveralcyclesbeforetheprocessingthreadgetsan
opportunitytoattendtothefirstqueuedrecord.Thefailureoftheconsumerapplicationwouldlead
to missed records; the newly assigned consumer will have naturally assumed that the committed
recordswereprocessed.Toachieveat-least-oncedeliverysemantics,theoffsetsofarecordmustbe
committedatsomepointaftertherecordisprocessed.
While disabling enable.auto.commit is optional in the direct consumer scenario, it must
categorically be disabled in the pipeline scenario. The effect of leaving offset auto-commit
onisthelogicalequivalentofcommittingrecordsafterqueuingthem,withnoregardasto
whethertheywereprocessedbythedownstreamstage.
The need to shuttle the offsets back to the I/O thread addresses an inherent limitation of the
KafkaConsumer implementation. Namely, the consumer is not thread-safe. Attempting to invoke
commitAsync() from a thread that is different to the one that invoked poll() will result in a
java.util.ConcurrentModificationException exception. As such, we have no choice but to repa-
triatetheoffsetstothepollingthread.
Thefinalpoint—theadditionofonetoarecord’soffset—accountsforthefactthataKafkaconsumer
willstartprocessingrecordsfromtheexactoffsetpersistedagainstitsencompassingconsumergroup.
Naively committing the record’s offset ‘as is’ will result in the replaying of the last committed
recordfollowingatopicrebalancingevent,wherepartitionsmaybereassignedamongtheconsumer
population.Byaddingonetotheoffset,weareensuringthatthenewassigneewillskipoverthelast
processedrecord—seamlesslytakingoverfromwherethelastconsumerleftoff.
Invokingtheno-argumentcommitAsync()method,asintheDirectReceiverscenario,will
automaticallyaddoneto theoffsetsofthe lastrecordsprocessedforeach partition.When
specifyingoffsetsexplicitly,theoffsetarithmeticbecomestheresponsibilityoftheapplica-
tion.
For our next trick, we shall conjure up an alternate receiver implementation, this time exploiting
thepipelinepatternoutsideoftheconsumer:

117
Chapter7: Serialization
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;
import org.apache.kafka.common.errors.*;
import org.apache.kafka.common.serialization.*;
import com.obsidiandynamics.worker.*;
import com.obsidiandynamics.worker.Terminator;
public final class PipelinedReceiver extends AbstractReceiver {
| private final | WorkerThread | pollingThread;    |     |
| ------------- | ------------ | ----------------- | --- |
| private final | WorkerThread | processingThread; |     |
private final Consumer<String, CustomerPayloadOrError> consumer;
| private final                        | Duration                    | pollTimeout;             |                         |
| ------------------------------------ | --------------------------- | ------------------------ | ----------------------- |
| private final                        | BlockingQueue<ReceiveEvent> |                          | receivedEvents;         |
| private final                        | Queue<Map<TopicPartition,   |                          | OffsetAndMetadata>>     |
| pendingOffsets                       | = new                       | LinkedBlockingQueue<>(); |                         |
| public PipelinedReceiver(Map<String, |                             |                          | Object> consumerConfig, |
String topic,
|                  |                | Duration pollTimeout,                 |            |
| ---------------- | -------------- | ------------------------------------- | ---------- |
|                  |                | int queueCapacity)                    | {          |
| this.pollTimeout | = pollTimeout; |                                       |            |
| receivedEvents   | = new          | LinkedBlockingQueue<>(queueCapacity); |            |
| final var        | mergedConfig   | = new HashMap<String,                 | Object>(); |
mergedConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName());
mergedConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
CustomerPayloadDeserializer.class.getName());
mergedConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
false);
mergedConfig.putAll(consumerConfig);
| consumer | = new KafkaConsumer<>(mergedConfig); |     |     |
| -------- | ------------------------------------ | --- | --- |
consumer.subscribe(Set.of(topic));

118
Chapter7: Serialization
| pollingThread    | = WorkerThread.builder() |     |     |     |
| ---------------- | ------------------------ | --- | --- | --- |
| .withOptions(new | WorkerOptions()          |     |     |     |
.daemon()
|     | .withName(PipelinedReceiver.class, |     |     | "poller")) |
| --- | ---------------------------------- | --- | --- | ---------- |
.onCycle(this::onPollCycle)
.build();
| processingThread | = WorkerThread.builder() |     |     |     |
| ---------------- | ------------------------ | --- | --- | --- |
| .withOptions(new | WorkerOptions()          |     |     |     |
.daemon()
|     | .withName(PipelinedReceiver.class, |     |     | "processor")) |
| --- | ---------------------------------- | --- | --- | ------------- |
.onCycle(this::onProcessCycle)
.build();
}
@Override
| public void | start() { |     |     |     |
| ----------- | --------- | --- | --- | --- |
pollingThread.start();
processingThread.start();
}
| private void | onPollCycle(WorkerThread |     | t)  |     |
| ------------ | ------------------------ | --- | --- | --- |
| throws       | InterruptedException     | {   |     |     |
final ConsumerRecords<String, CustomerPayloadOrError> records;
try {
| records | = consumer.poll(pollTimeout);         |      |     |                |
| ------- | ------------------------------------- | ---- | --- | -------------- |
| } catch | (InterruptException                   | e) { |     |                |
| throw   | new InterruptedException("Interrupted |      |     | during poll"); |
}
| if (! records.isEmpty()) |                             | {                                |     |     |
| ------------------------ | --------------------------- | -------------------------------- | --- | --- |
| for (var                 | record : records)           | {                                |     |     |
| final                    | var value = record.value(); |                                  |     |     |
| final                    | var event = new             | ReceiveEvent(value.getPayload(), |     |     |
value.getError(),
record,
value.getEncodedValue());
receivedEvents.put(event);
}
}

119
Chapter7: Serialization
| for (Map<TopicPartition,            |                          | OffsetAndMetadata> | pendingOffset; |     |
| ----------------------------------- | ------------------------ | ------------------ | -------------- | --- |
| (pendingOffset                      | = pendingOffsets.poll()) |                    | != null;)      | {   |
| consumer.commitAsync(pendingOffset, |                          |                    | null);         |     |
}
}
| private void | onProcessCycle(WorkerThread    |     | t)  |     |
| ------------ | ------------------------------ | --- | --- | --- |
| throws       | InterruptedException           | {   |     |     |
| final var    | event = receivedEvents.take(); |     |     |     |
fire(event);
| final var | record = event.getRecord(); |     |     |     |
| --------- | --------------------------- | --- | --- | --- |
pendingOffsets
| .add(Map.of(new | TopicPartition(record.topic(), |     |     |     |
| --------------- | ------------------------------ | --- | --- | --- |
record.partition()),
|     | new OffsetAndMetadata(record.offset() |     | +   | 1))); |
| --- | ------------------------------------- | --- | --- | ----- |
}
@Override
| public void                  | close() { |                   |     |     |
| ---------------------------- | --------- | ----------------- | --- | --- |
| Terminator.of(pollingThread, |           | processingThread) |     |     |
.terminate()
.joinSilently();
consumer.close();
}
}
Therearea fewnotabledifferencesbetween aPipelinedReceiveranditsDirectReceivercounter-
part:
1. Theadditionofasecondthread—wenowhaveadistinctprocessingThreadandapollingThread,
whereas the original implementation made do with a singlepollingThread. Correspondingly,
thepipelinedimplementationhastwoonCyclehandlers.
2. The addition of a LinkedBlockingQueue, acting as a bounded buffer between the two worker
threads.
3. Anadditionalparametertotheconstructor,specifyingthecapacityoftheblockingqueue.
TheonPollCycle()methodfetchesrecords,butdoesnotdispatchtheevent.Instead,itputstheevent
onto the receivedEvents queue, blocking if necessary until space becomes available. Having dealt
with the batch, it will gather any pending offsets that require committing, taking care not to block
whileconsumingfromthependingOffsetsqueue.

Chapter7: Serialization 120
Using the non-blocking Queue.poll() method prevents a deadlock condition, where the
polling thread is blocked on the processor thread to submit additional offsets, while the
processorthreadishopelesslywaitingonthepollingthreadtoconveyrecordsthroughthe
pipeline.
The onProcessCycle() method takes records from the head of the receivedEvents queue, waiting
ifnecessaryforaneventtobecomeavailable.Theeventisthendispatchedtoallregisteredlisteners.
Finally, the offsets of the underlying record are incremented and submitted to the pendingOffsets
queueforsubsequentcommittingbythepollingthread.
Torunthepipelinedexample,launchtheRunPipelinedConsumerclass:
public final class RunPipelinedConsumer {
public static void main(String[] args)
throws InterruptedException {
final Map<String, Object> consumerConfig =
Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ConsumerConfig.GROUP_ID_CONFIG,
"customer-pipelined-consumer",
ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
"earliest");
try (var receiver =
new PipelinedReceiver(consumerConfig,
"customer.test",
Duration.ofMillis(100), 10)) {
new ConsumerBusinessLogic(receiver);
receiver.start();
Thread.sleep(10_000);
}
}
}
By applying the pipeline pattern, we have decoupled two potentially slow operations, allowing
them to operate independently of one another. The performance gains are not exclusive to multi-
coreormulti-processorarchitectures;evensingle-core,pseudo-concurrentsystemswillbenefitfrom
pipeliningbymaximisingtheamountofusefulworkaprocessorcando.
One of the perceived drawbacks of pipelining is that it adds latency to the process; the contention
overasharedbufferandtheoverheadofthreadschedulingandcachecoherencewilladdtotheend-
to-end propagation delay of a record flowing through the pipeline. And while the added latency is
typically more than made up for in throughput gains, it is really up to the application designer to

Chapter7: Serialization 121
makethefinalcallontheoptimisationstrategy.AnargumentcouldbemadethatsinceKafka-based
applicationstendtobethroughput-oriented,optimisationsofthisnaturearetimelyandappropriate.
Kafka’sperformancedoctrinehastraditionallybeentoaddmoreconsumers,morepartitions,
andmorebrokers.Often,littleregardisgiventothecost-effectivenessofthismodel.Itisnot
uncommontoobserveahighnumberofmostly-idlingconsumersspawnedinanautoscaling
group to get through a moderately loaded topic. One cannot help but question the actual
numberofconsumerinstancesrequired,hadeachinstanceutiliseditsavailableresourcesto
theirprovisionedcapacity.
And now for the best part. Previous examples have leveraged the EventReceiver interface to
decouple the business logic from the low-level Kafka Consumer handling. As we have just demon-
strated, pipelining can fit entirely into the EventReceiver implementation, with no impact to
the business logic layer. The complexities of multi-threaded code, queuing of records, and the
shuttling of offsets are entirely concealed behind the EventReceiver interface. In fact, we can
stockmultipleEventReceiverimplementations—amoreconventionalDirectReceiverandamulti-
threaded PipelinedReceiver. These are functionally equivalent, but exhibit different performance
and resource utilisation characteristics. The PipelinedReceiver adds 30 or so lines on top of the
DirectReceiver implementation. Comparatively speaking, this may seem like a lot, given that the
extracodeisaround50%oftheoriginalimplementation.Butinthelargerschemeofthings,30lines
are small potatoes and the added complexity is incurred once — the improved resource utilisation
profileandtheresultingperformancegainsmorethanmakeupfortheadditionaleffort.
Incasethereaderiswondering,thepipeliningoptimisationislesseffectiveinproducerap-
plications,asserializationistypicallymuchlessprocessorandmemory-intensivecompared
to deserialization. Also, the KafkaProducer is pipelined internally, separating serialization
fromnetworkI/O.Littleinthewayofperformancegainswouldbeaccomplishedbymoving
recordserializationtoadedicatedthread.
Record filtering
In rounding off this chapter, we shall highlight another compelling reason for an abstraction layer:
thefilteringofrecords.Filteringfulfillsasetofusecaseswhereeitheradeserializer,oranapplication-
level unmarshaller might conditionally present a record to the rest of the application. This is not a
nativecapabilityofaKafkaconsumer,requiringabespokeimplementation.
The natural question one might ask is: Why not filter at the business logic layer with some if
statements?
There are two challenges with this approach, which also become more difficult to solve as one
moves up the application stack. Firstly, it assumes that the client has the requisite domain objects
that can be mapped from a record’s serialized form. Secondly, it incurs the performance overhead
ofunconditionallyunmarshallingallrecords,onlytodiscardsomerecordsshortlythereafter.

Chapter7: Serialization 122
Thefirstproblem—missingdomainobjectsorthelackofknowledgeofcertainrecordschemas(the
twoarelogicallyequivalent)—canbeattributedtoseveralcauses:
• ThesourceKafkatopicisbroadly-versed,containingmoretypesofrecordsthattheconsumer
legitimatelyrequiresforroutineoperation.
• The record types might be known to the consumer, but it may have no interest in processing
them. For example, a topic might represent changes to a global customer database, whereas
a consumer deployed in a single region might only care about the subset of customers in its
locality.
• Therecordstructurehasevolvedovertime,suchthatthetopicmaycontainrecordsthatcomply
to varying schema versions. To accommodate the gradual transition of consumers to a newer
schema,producerswilltypicallypublishthesamerecordinmultipleversions.
At any rate, the consumer will have to selectively parse the record’s value on the basis of some
explicitindicator.Thisindicatormaybeawell-knownheader—forexample,recordType: CREATE_-
CUSTOMER,region: Asia-Pacific,orversion: 2.Alternatively,theindicatormaybeinferredfrom
the record’s value without having to parse the entire payload, let alone mapping the payload to a
POJO. For example, the Jackson library allows you to create a custom deserializer that can inspect
thedocumentobjectmodelbeforedecidingtomapittoanexistingJavaclass.
Insomecases,filtering records fromwithinthe businesslogicisa validapproach,particu-
larly where the filtering predicates require deep inspection of the record’s payload or are
relatedtothecurrentapplicationstate.
Thefinalpointrelatestoperformance.Whileprematureoptimisationshouldbeavoided,theremay
be legitimate cases where the sheer amount of data on a topic places a strain on the consumer
ecosystem,particularlywhenthetopiciscoarse-grained.Iftheoptionsforincreasingthegranularity
of topics and the scaling of consumers have been exhausted, the sole remaining option might be to
pre-filterrecordsinanattempttoextractthelastounceofperformance.Soundsterrible?Agreed!The
recommendation is to avoid complexity on the basis of performance alone. Instead, the application
shouldbearchitectedfromtheoutsettocopewiththeexpectedload.
Kafka’s idiomatic approach for dealing with varying record representations is through custom
(de)serializers.Onceimplementedandconfigured,(de)serializersworkbehindthescenes,accepting
anddeliveringapplication-nativerecordkeysandvaluesviaagenericallytypedAPI.Theproducer
applicationisexpectedtoaddresstheProducerimplementationdirectly,whileontheconsumer-end,
thisapproachisoftenpairedwithasimplepoll-processloop.
This chapter has explored some of the typical concerns of producer and consumer applications,
arguing for the use of an abstraction layer to separate Kafka-specific messaging code from the

Chapter7: Serialization 123
business logic. This makes it easier to encapsulate common behaviour and invariants on one hand,
and on the other, simplifies key aspects of the application, making it easier to mock and test in
isolation.
We have also come to understand the inefficiency inherent in the poll-process loop, namely the
stacking of record deserialization and processing onto a single execution thread. The internal
pipeliningmodelofaKafkaConsumerwasexplained,andweexploredhowtheconceptofpipelining
canbeexploitedtofurtherdecouplethedeserializationofrecordsfromtheirsubsequentprocessing.
Finally, we have looked at record versioning and filtering as prime use cases for concealing non-
trivialbehaviourbehindanabstractionlayer,reducingtheamountofworkthatneedstohappenat
theprocessinglayeranditsresultingcomplexity.

Chapter 8: Bootstrapping and
Advertised Listeners
Whoaretheselisteners?Andwhataretheyadvertising?
Having been in the Kafka game since 2015, without exaggeration, the most common question that
gets asked is: “Why can’t I connect to my broker?” And it is typically followed up with: “I’m sure
the firewall is open; I tried pinging the box; I even tried telnetting into port 9092, but I still can’t
connect.”Thebootstrappingconfigurationwillfrustratethelivingdaylightsoutofmostdevelopers
and operations folk at some point, and that’s unfortunate; for all the flexibility that Kafka has to
offer,itcertainlyisn’twithoutitsdrawbacks.
A gentle introduction to bootstrapping
Before we can start looking into advertised listeners, we need a thorough understanding of the
clientbootstrappingprocess.Asitwaspreviouslystated,Kafkareplicatesatopicanditsunderlying
partitions among several broker nodes, such that one broker will act as a leader for one partition
and a follower for several others. Assuming that a topic has many partitions and the allocation of
replicas is approximately level, no single broker will master a topic in its entirety. The illustration
below depicts a four-broker cluster hosting a topic with four partitions, with a replication factor of
three.(Meaningthateachpartitionwillhaveoneleaderandtwofollowerreplicas.)

Chapter8: BootstrappingandAdvertisedListeners 125
Topicreplication
Now let’s take the client’s perspective for a moment. When a producer wishes to publish a record,
it must contact the lead broker for the target partition, which in turn, will disseminate the record
to the follower replicas, before acknowledging the write. Since a producer will typically publish
on all partitions at some point, it will require a direct connection to most, if not all, brokers in a
Kafkacluster.(Whethertheseconnectionsareestablishedeagerly —atinitialisation,orlazily —on
demand,willlargelydependontheclientlibraryimplementation.)
UnfortunatelyKafkabrokersareincapableofforwardingawriterequesttotheleadbroker.
Whenattemptingtopublisharecordtoabrokerthatisnotadeclaredpartitionleaderwithin
areplicaset,thelatterwillfailwithaNOT_LEADER_FOR_PARTITIONerror.Thiserrorsitsinthe
categoryofretryableerrors,andmayoccurfromtimetotime,notablywhentheleadership
statustransitionsamongthesetofin-syncreplicasforwhateverreason.Theproducerclient
willsimplyfetchthenewclustermetadata,discovertheupdatedleader,andwillre-address
the write request accordingly. The NOT_LEADER_FOR_PARTITION error is mostly harmless in
small doses; however, repeated and unresolved recurrence of this error suggests a more
profoundproblem.

Chapter8: BootstrappingandAdvertisedListeners 126
The challenge boils down to this: How does a client discover the nodes in a Kafka cluster? A naive
solutionwouldhaverequiredustoexplicitlyconfiguretheclientwiththecompletesetofindividual
addresses of each broker node. Establishing direct connections would be trivial, but the solution
would not scale to a dynamic cluster topology; adding or removing nodes from the cluster would
requireareconfigurationofallclients.
The solution that Kafka designers went with is based on a directory metaphor. Rather than being
told the broker addresses, clients look up the cluster metadata in a directory in the first phase in
thebootstrappingprocess,thenestablishdirectconnectionstothediscoveredbrokersinthesecond
phase. Rather than coming up with more moving parts, the role of the directory is conveniently
played by each of the brokers. Since brokers are intrinsically aware of one another via ZooKeeper,
every broker has an identical view of the cluster metadata, encompassing every other broker, and
is able to impart this metadata onto a requesting client. Still, the brokers might change, which
seemingly contradicts the notion of a ‘stable’ directory. To counteract this, clients are configured
with a bootstrap list of broker addresses that only needs to be partially accurate. As long as one
addressinthebootstraplistpointstoalive broker,theclientwilllearntheentiretopology.
Taking advantage of DNS
Youwouldberightinthinkingthatthismodelfeelsbrittle.Whatifwerecycleallbrokersinacluster?
What if the brokers are hosted on ephemeral instances in the Cloud and may come and go as they
please, with a new IP address each time? The bootstrap list would soon become useless. Aren’t we
onlykickingthe‘reconfigurationcan’downtheroad?
Whilethereisnoofficial responsetothis,thepracticeadoptedinthecommunityistouseasecond
tier of DNS entries. Suppose we had an arbitrarily-sized cluster that could be recycled on demand.
Each broker would be assigned an IP address and likely an auto-generated hostname, both being
ephemeral. To complement the directory metaphor, we would create a handful of well-known
canonical DNS CNAME or A records with a minimal TTL, pointing to either the IP addresses or the
hostnames of a subset of our broker nodes. The fully-qualified domain names of new DNS entries
mightbesomethinglike—
• broker0.ext.prod.kafka.mycompany.com.
• broker1.ext.prod.kafka.mycompany.com.
• broker2.ext.prod.kafka.mycompany.com.
The Kafka clients would only be configured with the list of canonical bootstrap addresses. Every
address-impactingbrokerchangewouldentailupdatingthecanonicalDNSentries;butit’seasierto
keepDNSuptodate,thentoensurethatallclients(ofwhichtherecouldbehundredsorthousands)
are correctly configured. Furthermore, DNS entries can easily be tested. One could implement a
trivial ‘canary’ app that periodically attempts to connect to the brokers on one of the canonical
addresses to verify their availability. This way we would soon learn when something untoward
happens,beforetheissueescalatestothepointofanall-outfailure.

Chapter8: BootstrappingandAdvertisedListeners 127
Anelaborationoftheabovetechniqueistouseround-robinDNS.Ratherthanmaintainingmultiple
Arecordsforuniquehosts,DNSpermitsseveralArecordsforthesamehost,pointingtodifferentIP
addresses. A DNS query for a host will return all matching A records, permuting the records prior
toreturning.Furthermore,thereturnedlistmaybeamixtureofIPv4andIPv6addresses.Assuming
the client will try the first IP address in the returned list, each address will serve an approximately
equal number of requests. The client does not have to stop at the first entry; it can try any number
ofthem,potentiallyall ofthem,untilitreachesahostthatisabletosatisfyitsrequest.
The ability to utilise all resolved addresses was introduced to Kafka in version 2.1, as part of KIP-
302¹⁰. (KIP stands for Kafka Improvement Proposal.) To maintain backward-compatible behaviour,
Kafka disables this by default. To enable this feature, set the client.dns.lookup configuration to
use_all_dns_ips.Onceenabled,theclientwillutiliseallresolvedDNSentries.
Theadvantageofthisapproachisthatitdoesnotrequireustoalterthebootstraplistwhenadding
morefallbackaddresses.Thelistmaybereducedtoasingleentry—forexample,broker.ext.prod.kafka.mycompany.com:9092
—whichwillresolvetoanarbitrarynumberofIPaddresses,dependingonhowtheDNSrecordsare
configured.Furthermore,multi-recordDNSresolutionappliesnotonlytobootstrapping,butalsoto
subsequentconnectionstotheresolvedbrokers.
The use of multiple alternate records for the same host requires the resolved brokers to agree on
a common port number. Also, it is limited to A records; the DNS specification forbids the use of
multiple CNAME records for the same fully-qualified domain name. Another potential limitation
relatestotheclientimplementation.Theclient.dns.lookuppropertyisacceptedbytheJavaclient
library;portstootherlanguagesmightnotsupportthiscapability—checkwithyourlibrarybefore
using this feature. With the exception of the last point, these constraints are rarely show-stoppers
inpractice.Thebenefitofthisapproach—havingacentralisedadministrationpointwithaset-and-
forget bootstrap list — may not be immediately discernible with a handful of clients, but becomes
moreapparentastheclientecosystemgrows.
A simple scenario
In a simple networking topology, where each broker can be reached on a single address and port
number, the bootstrapping mechanism can be made to workwith minimal configuration. Consider
a simple scenario with three brokers confined to a private network, such that the producer and
consumer clients are also deployed on the same network. Keeping things simple, let’s assume the
brokerIPaddressesare10.10.0.1,10.10.0.2,and10.10.0.3.Eachbrokerislisteningonport9092.
A client application deployed on 10.20.0.1 is attempting to connect to the cluster. This scenario is
illustratedbelow.
¹⁰https://cwiki.apache.org/confluence/display/KAFKA/KIP-302+-+Enable+Kafka+clients+to+use+all+DNS+resolved+IP+addresses

Chapter8: BootstrappingandAdvertisedListeners 128
Connectingwithinaprivatenetwork
Atthispointonewouldnaturallyassumethatpassingin10.10.0.1:9092,10.10.0.2:9092,10.10.0.3:9092
for the bootstrap list should just work. The problem is that the Kafka broker does not know which
IP address or hostname it should advertise, and it does a pretty bad job at auto-discovering this. In
mostcases,itwilldefaulttolocalhost.
Thediagrambelowcapturestheessenceoftheproblem.Uponbootstrapping,theclientwillconnect
to 10.10.0.1:9092, being the first element in the bootstrap list. (In practice, the client will pick an
address at random, but it hardly matters in this example.) Having made the connection, the client
will receive the cluster metadata — a list of three elements — each being localhost:9092. You can
see where this is going. The client will then try connecting to localhost — to itself. Et voila, that
ishowthedreaded“Connectiontonode-1(localhost/127.0.0.1:9092)couldnotbeestablished.Broker
maynotbeavailable.” errorisobtained.

Chapter8: BootstrappingandAdvertisedListeners 129
Internalclientloopingbacktoitself
This is as much of a problem for simple single-broker Kafka installations as it is for multi-
brokerclusters.Clientswillalwaysfollowaddressesrevealedbytheclustermetadataeven
ifthereisonlyonenode.
This is solved with advertised listeners. (Finally, we are getting around to the crux of the matter.)
AKafkabrokermaybeconfiguredwiththreeproperties—advertised.listeners,listeners,and
listener.security.protocol.map—whichareinterrelatedanddesignedtobeusedinconcert.Let’s
openthebrokerconfigurationandfindtheseproperties.Edit$KAFKA_HOME/config/server.properties;
towardsthebeginningofthefileyoushouldseeseverallinesresemblingthefollowing:

Chapter8: BootstrappingandAdvertisedListeners 130
################# Socket Server Settings ####################
# The address the socket server listens on. It will get the value
# returned from java.net.InetAddress.getCanonicalHostName() if
# not configured.
# FORMAT:
# listeners = listener_name://host_name:port
# EXAMPLE:
# listeners = PLAINTEXT://your.host.name:9092
#listeners=PLAINTEXT://:9092
# Hostname and port the broker will advertise to producers and
# consumers. If not set, it uses the value for "listeners" if
# configured. Otherwise, it will use the value returned from
# java.net.InetAddress.getCanonicalHostName().
#advertised.listeners=PLAINTEXT://your.host.name:9092
# Maps listener names to security protocols, the default is for
# them to be the same. See the config documentation for more details
#listener.security.protocol.map=PLAINTEXT:PLAINTEXT,\
SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
Unless you have previously changed the configuration file, the properties will start off commented
out. The listeners property is structured as a comma-separated list of URIs, which specify the
sockets that the broker should listen on for incoming TCP connections. Each URI comprises a free-
formprotocolname,followedbya://,anoptionalinterfaceaddress,followedbyacolon,andfinally
a port number. Omitting the interface address will bind the socket to the default network interface.
Alternatively,youcanspecifythe0.0.0.0meta-addresstobindthesocketonallinterfaces.
Aninterfaceaddressmayalsobereferredtoasabindaddress.
Inthedefaultexample,thevaluePLAINTEXT://:9092canbetakentomeanalistenerprotocolnamed
PLAINTEXT,listeningonport9092boundtothedefaultnetworkinterface.
A commented-out property will assume its default value. Defaults are listed in the official
Kafkadocumentationpageatkafka.apache.org/documentation¹¹,underthe‘BrokerConfigs’
section.
The protocol name must map to a valid security protocol in the listener.security.protocol.map
property.Thesecurityprotocolsarefixed,constrainedtothefollowingvalues:
¹¹https://kafka.apache.org/documentation/#brokerconfigs

Chapter8: BootstrappingandAdvertisedListeners 131
• PLAINTEXT:PlaintextTCPconnectionwithoutuserprincipalauthentication.
• SSL:TLSconnectionwithoutauthentication.
• SASL_PLAINTEXT: Plaintext connection with SASL (Simple Authentication and Security Layer)
toauthenticateuserprincipals.
• SASL_SSL: The combination of TLS for transport-level security and SASL for user principal
authentication.
Tomapalistenerprotocoltoasecurityprotocol,uncommentthelistener.security.protocol.map
property and append the new mapping. The mapping must be in the form <listener protocol
name>:<security protocol name>.
Theprotocolnameisamajorsourceofconfusionamongfirst-timeKafkausers.Thereisa
misconception, and unsurprisingly so, that PLAINTEXT at the listener level relates to the
encryption scheme (or lack thereof). In fact, the listener protocol names are completely
arbitrary. And while it is good practice to assign them meaningful names, a listener URI
DONALD_DUCK://:9092 is perfectly valid, provided that DONALD_DUCK has a corresponding
mappinginlistener.security.protocol.map,e.g.DONALD_DUCK:SASL_SSL.
In addition to specifying a socket listener inlisteners, you need to state how the listener is adver-
tisedtoproducerandconsumerclients.Thisisdonebyappendinganentrytoadvertised.listeners,
in the form of <listener protocol>://<advertised host name>:<advertised port>. Returning
to our earlier example, we would like the first broker to be advertised on 10.10.0.1:9092. So we
wouldeditserver.propertiestothetuneof:
advertised.listeners=PLAINTEXT://10.10.0.1:9092
Note: There was no need to change listeners or listener.security.protocol.map because we
didn’t introduce a new listener; we simply changed how the existing listener is advertised. In a
multi-brokersetup,wewouldmakesimilarchangestotheotherbrokers.
Don’tforgettorestartKafkaafterchanginganyvaluesinserver.properties.
How does this fix bootstrapping? The client will still connect to a random host specified in the
bootstrap list. This time, the class metadata returned by the host will contain the correct client-
reachableaddressesandportnumbersofbrokernodes,ratherthanasetof localhost:9092entries.
Now the client is able to establish direct connections, provided that these addresses are reachable
fromtheclient.Thediagrambelowillustratesthis.

Chapter8: BootstrappingandAdvertisedListeners 132
Bootstrappingoveraninternalnetwork
Kafdrop comes in useful for understanding the topology of the cluster and gives some clues as to
the listener configuration. The cluster overview screen shows both the bootstrap list, as well as the
addressandportreportedintheclustermetadata.

Chapter8: BootstrappingandAdvertisedListeners 133
Kafdrop,showingbrokermetadata
Earlierinthischapter,wetouchedupontheclient.dns.lookuppropertyandtheeffectofsettingit
touse_all_dns_ips—specifically,theJavaclient’sabilitytoutiliseallresolvedDNSentries.When
enabled, the advertised listeners will also be subjected to the same DNS treatment. If an advertised
listener resolves to multiple IP addresses, the client will cycle through these until a connection is
established.
Multiple listeners
The simple example discussed earlier applies when there is a single ingress point into the Kafka
cluster; every client, irrespective of their type or deployment location, accesses the cluster via that
ingress.
What if you had multiple ingress points? Suppose our three-broker cluster is deployed in a virtual
privatecloud(VPC)onAWS(orsomeotherCloud).Mostclientsarealsodeployedwithinthesame
VPC. However, a handful of legacy consumer and producer applications are deployed outside the
VPCinamanageddatacentre.Therearenoprivatelinks(VPNorDirectConnect)betweentheVPC
andthedatacentre.
OneapproachistoexposethebrokerstotheoutsideworldviaanInternetGateway,suchthateach
broker has a pair of addresses — an internal address and an external address. Assume that security
isanon-issueforthemoment—wedon’tcareaboutencryption,authenticationorauthorization—
we just want to connect to the Kafka cluster over the Internet. The internal addresses will be lifted
from the last example, while the external ones will be 200.0.0.1, 200.0.0.2, and 200.0.0.3. The
desiredbrokerandclienttopologyisillustratedbelow.

Chapter8: BootstrappingandAdvertisedListeners 134
Connectingfromtwolocations
Asyouwouldhaveguessedbynow,settingthebootstraplistto200.0.0.1:9092,200.0.0.2:9092,200.0.0.3:9092
will not work for external clients. The client will make the initial connection, download the cluster
metadata, then attempt fruitlessly to connect to one of the 10.10.0.x private addresses. Brokers
need a way of distinguishing internal clients from external clients so that a tailored set of cluster
metadatacanbeserveddependingonwheretheclientisconnectingfrom.

Chapter8: BootstrappingandAdvertisedListeners 135
Thesituationisresolvedbyaddingasecondlistener,targetingtheexternalingress.Wewouldhave
tomodifyourserver.propertiestoresemblethefollowing:
listeners=INTERNAL://:9092,EXTERNAL://:9093
advertised.listeners=INTERNAL://10.10.0.1:9092,\
EXTERNAL://200.0.0.1:9093
listener.security.protocol.map=INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
inter.broker.listener.name=INTERNAL
Rather than calling our second listener PLAINTEXT2, we’ve gone with something sensible — the
listenerprotocolswerenamedINTERNALandEXTERNAL.Theadvertised.listenerspropertyisused
to segregate the metadata based on the specific listener that handled the initial bootstrapping
connection from the client. In other words, if the client connected on the INTERNAL listener socket
bound on port 9092, then the cluster metadata would contain 10.10.0.1:9092 for the responding
broker as well as the corresponding INTERNAL advertised listener addresses for its peer brokers.
Conversely, if a client was bootstrapped to the EXTERNAL listener socket on port 9093, then the
EXTERNALadvertisedaddressesareservedinthemetadata.Theillustrationbelowputsitalltogether.

Chapter8: BootstrappingandAdvertisedListeners 136
Bootstrappingfromtwolocations
IndividuallistenerconfigurationforeveryKafkabrokernodeispersistedcentrallyintheZooKeeper
cluster and is perceived identically by all Kafka brokers. Naturally, this implies that brokers must
be configured with identical listener names; otherwise, each broker will serve different cluster
metadatatotheirclients.
In addition to the changes tolisteners and advertised.listeners, corresponding entries are also
requiredinthelistener.security.protocol.map.BoththeINTERNALandEXTERNALlistenerprotocol

Chapter8: BootstrappingandAdvertisedListeners 137
nameshavebeenmappedtothePLAINTEXTsecurityprotocol.
KeepingwiththetraditionofdissectingoneKafkafeatureatatime,wehavegonewiththe
simplestPLAINTEXTconnectionsinthisexample.Fromasecuritystandpoint,thisisclearly
nottheapproachoneshouldtakeforaproductioncluster.Securityprotocols,authentication,
andauthorizationwillbediscussedinChapter16:Security.
Clients are not the only applications connecting to Kafka brokers. Broker nodes also form a mesh
network,connectingtooneanothertosatisfyinternalreplicationobjectives—ensuringthatwrites
topartitionleadersarereflectedinthefollowerreplicas.Theinternalmeshconnectionsarereferred
toasinter-brokercommunications,andusethesamewireprotocolandlistenersthatareexposedto
clients.SincewechangedtheinternallistenerprotocolnamefromthedefaultPLAINTEXTtoINTERNAL,
wehadtomakeacorrespondingchangetotheinter.broker.listener.nameproperty.Thisproperty
doesnotappearintheout-of-the-boxserver.propertiesfile—itmustbeaddedmanually.
A port may not be bound to by more than one listener on the same network interface. As such,the
portnumbermustbeuniqueforanygiveninterface.Ifleavingtheinterfaceaddressunspecified,orif
providinga0.0.0.0meta-address,onemustassignauniqueportnumberforeachlistenerprotocol.
Inourexample,wewentwith9092fortheinternaland9093fortheexternalroute.
Thediscussiononportsandnetworkinterfacesleadstoaminorcuriosity:Whatifweattemptedto
assign the same port number to both listeners by explicitly qualifying the IP address? Would this
evenwork?
listeners=INTERNAL://10.10.0.1:9092,EXTERNAL://200.0.0.1:9092
Theanswerdependsonwhetherthehosthas(physicalorvirtual)networkinterfacesthatcorrespond
to these IP addresses. Most hosts will have at least two effective addresses available for binding:
localhostandtheaddressassignedtoanEthernetinterface.Somehostswillhavemoreaddresses
—duetoadditionalnetworkinterfaces,IPv6,andvirtualinterfacedrivers.
In some configurations, the host’s provisioned IP addresses may be assigned directly to network
interfaces. For example, dual-homed hosts will typically have a dedicated network interface for
eachaddress.Specifyingtheroutableaddressinthelistenerconfigurationwillworkasexpected—
thelistener’sbackingserversocketwillbeboundtothecorrectnetworkinterface,avoidingaport
conflict. However, when using a NAT (Network Address Translation) device such as an Internet
GatewaytoselectivelyassignpublicIPaddressestohoststhatareonanotherwiseprivatenetwork,
thehostwillhavenoknowledgeofitspublicIPaddress.Furthermore,itmightonlyhaveonenetwork
interface attached. In these cases, specifying the host’s routableaddressin thelistenersproperty
willcauseaportconflict.
You might be wondering: How often do I need to deal with advertised listeners? Most non-trivial
broker configurations support multiple ingress points, often more than two. Most vendors of man-

138
Chapter8: BootstrappingandAdvertisedListeners
aged Kafka clusters that offer peering arrangements with public cloud providers will also give you
the option of connecting from multiple networks (peered and non-peered). To top it off, there is
anothercommonexampleofmultiplelistenerscomingup.
| Listeners | and the | Docker | Network |
| --------- | ------- | ------ | ------- |
These days it’s common to see a complete application stack deployed across several Docker con-
tainers linked by a common network. Starting with local testing, tools like Docker Compose make
it easy to wire up a self-contained application stack, comprising back-end services, client-facing
APIs,databases,messagebrokers,andsoon—spunuprapidlyonadeveloper’smachine,thentorn
down when not needed. Taking it up a notch, orchestration platforms like Kubernetes, OpenShift,
DockerSwarm,andAWSElasticContainerServices addauto-scaling,zero-downtimedeployments,
andservicediscoveryintothemix,foraproduction-gradecontaineriseddeployment.
A solid understanding of Kafka’s listener and client bootstrapping mechanism is essential to de-
ploying a broker in a containerised environment. The upcoming example will illustrate the use of
multiple listeners in a basic application stack, comprising ZooKeeper, Kafka, and Kafdrop. Docker
Composewillbindeverythingtogether,sosomeknowledgeofComposeisassumed.Tospicethings
up,wewillexposeKafkaoutsideoftheComposestack.
Beforeyourunthisexample,ensurethatKafka,ZooKeeper,andKafdropinstancesthatyou
mayhaverunningfrompreviousexerciseshavebeenstopped.
To get started, create a docker-compose.yaml file in a directory of your choice, containing the
followingsnippet:
| version: "3.2" |     |     |     |
| -------------- | --- | --- | --- |
services:
zookeeper:
| image: bitnami/zookeeper:3 |     |     |     |
| -------------------------- | --- | --- | --- |
ports:
| - 2181:2181 |     |     |     |
| ----------- | --- | --- | --- |
environment:
| ALLOW_ANONYMOUS_LOGIN: |     | "yes" |     |
| ---------------------- | --- | ----- | --- |
kafka:
| image: bitnami/kafka:2 |     |     |     |
| ---------------------- | --- | --- | --- |
ports:
| - 9092:9092 |     |     |     |
| ----------- | --- | --- | --- |
environment:
| KAFKA_CFG_ZOOKEEPER_CONNECT: |     | zookeeper:2181 |     |
| ---------------------------- | --- | -------------- | --- |
| ALLOW_PLAINTEXT_LISTENER:    |     | "yes"          |     |

139
Chapter8: BootstrappingandAdvertisedListeners
| KAFKA_LISTENERS: | >-  |     |
| ---------------- | --- | --- |
INTERNAL://:29092,EXTERNAL://:9092
| KAFKA_ADVERTISED_LISTENERS: | >-  |     |
| --------------------------- | --- | --- |
INTERNAL://kafka:29092,EXTERNAL://localhost:9092
| KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: |     | >-  |
| ------------------------------------- | --- | --- |
INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
| KAFKA_INTER_BROKER_LISTENER_NAME: |     | "INTERNAL" |
| --------------------------------- | --- | ---------- |
depends_on:
- zookeeper
kafdrop:
image: obsidiandynamics/kafdrop:latest
ports:
- 9000:9000
environment:
| KAFKA_BROKERCONNECT: | kafka:29092 |     |
| -------------------- | ----------- | --- |
depends_on:
- kafka
Then bring up the stack by running docker-compose up in a terminal. (This must be run from the
samedirectorywherethedocker-compose.yamlresides.)Onceitboots,navigatetolocalhost:9000¹²
inyourbrowser.YoushouldseetheKafdroplandingscreen.
It’s the same Kafdrop application as in the previous examples; the only minor difference is the
value of the ‘Bootstrap servers’. In this example, we are bootstrapping Kafdrop using
kafka:29092
—beingtheinternalingresspointtotheKafkabroker.Theterm‘internal’herereferstoallnetwork
traffic originating from within the Compose stack. Containers attached to the Docker network are
addressedsimplybytheirservicename,whilethemechanicsofDockerComposebydefaultprevent
thetrafficfromleavingtheDockernetwork.
Externally (that is, outside of Docker Compose), we can access both Kafka and Kafdrop with the
aid of the port bindings defined in docker-compose.yaml file. Let’s find out if this actually works.
CreateatesttopicusingtheKafkaCLItools:
| $KAFKA_HOME/bin/kafka-topics.sh | \              |     |
| ------------------------------- | -------------- | --- |
| --bootstrap-server              | localhost:9092 | \   |
| --create --topic                | test \         |     |
| --replication-factor            | 1 \            |     |
| --partitions 4                  |                |     |
Nowtrylistingthetopics:
¹²http://localhost:9000

Chapter8: BootstrappingandAdvertisedListeners 140
$KAFKA_HOME/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--list
Youshould seeasingleentryechoedtotheterminal:
test
SwitchbacktoyourbrowserandrefreshKafdrop.Asexpected,thetesttopicappearsinthelist.
Dissecting the docker-compose.yaml file, we set up three services. The first iszookeeper, launched
using the bitnami/zookeeper image. This is the first container to start, as it has no declared
dependencies. The next service is kafka, which declares its dependence on the zookeeper service.
Finally,kafdropdeclaresitsdependenceonkafka.
Thekafkaservicepresentsthemostelaborateconfigurationofthethree.TheBitnamiKafkaimage
allows the user to override values in server.properties by passing environment variables. This
configuration should be familiar to the reader — it is effectively identical to our earlier example,
wheretrafficwassegregatedusingtheINTERNALandEXTERNALlistenerprotocolnames.
Dependencies in Docker Compose must form a directed acyclic graph. Simply declare
the dependencies of each service; Docker Compose will start the services in the correct
order,providednocircularitiesexist.Itisagoodpracticetostructuredocker-compose.yaml
in reverse-dependency order — putting the providers towards the beginning of the
docker-compose.yaml, so that it roughly aligns with how the services will actually be
launched.
You can bring down the stack once you are done. Press CTRL+C in the terminal window running
Docker Compose. This might take a few seconds, as Compose will attempt to shut down the stack
gracefully.Oncethepromptreappears,destroythestackbyrunningdocker-compose down -v.This
willalsohavetheeffectofdeletinganynamedoranonymousvolumesattachedtocontainers.
Bootstrapping is a complex, multi-stage process that enables clients to discover and maintain
connections to all brokers in a Kafka cluster. It is complex not just in its internal workings; there
reallyisafairamountofconfigurationonemustcometotermswithinordertocomfortablyoperate
a single Kafka instance or a multi-node cluster, whether it be exposed on one or multiple ingress
points.
This chapter has taken us through the internal mechanics of bootstrapping. We came to appreci-
ate the design limitations of Kafka’s publishing protocol and how this impacts the client-broker
relationship, namely, requiring every client to maintain a dedicated connection to every broker in

Chapter8: BootstrappingandAdvertisedListeners 141
the cluster. We established how clients engage brokers in directory-style address lookups, using
cluster metadata to learn the broker topology and adapt to changes in the broker population.
Various traffic segregation scenarios were discussed, exploring the effects of the listeners and
advertised.listeners configuration properties on how cluster metadata is crafted and served to
theclients.Thelistenerspropertyconfiguresthesocketsthatabrokerwilllistenonforincoming
connections, while advertised.listeners guides the clients in resolving the brokers. Finally, the
use of Docker Compose served a practical everyday example of how listener-based segregation is
employedonaroutinebasistorunKafkainacontainerisedenvironment.

Chapter 9: Broker Configuration
ReminiscingonourKafkajourney,sofarwehavemostlygottenawaywithrunningafairly‘vanilla’
brokersetup.Theexception,ofcourse,beingthetweakstothelistenersandadvertised.listeners
configurationpropertiesthatwereexploredinthecourseofChapter8:BootstrappingandAdvertised
Listeners.
Asonemightimagine,Kafkaoffersamyriadofconfigurationoptionsthataffectvariousaspectsofits
behaviour,rangingfromthesubstantialtotheminute.Thepurposeofthischapteristofamiliarisethe
readerwiththecore configurationconcepts,sufficienttomakeonecomfortableinmakingchanges
toallaspectsofKafka’sbehaviour,usingacombinationofstaticanddynamicmechanisms,covering
abroadsetofconfigurableentities,aswellastargetedcanary-styleupdates.
The knowledge gained in this chapter should be complemented with the official Apache Kafka
online reference documentation available at kafka.apache.org/documentation¹³. There are literally
hundreds of configurable elements described in rigorous detail in the reference documentation.
Furthermore, each major release of Kafka often brings about new configuration and deprecates
some of the existing. There is little value in regurgitating online reference material in a published
book; instead, this chapter will focus on the fundamentals, which are incidentally least catered to
bytheonlinedocumentation.
Entity types
Therearefourentitytypes thataconfigurationentrymayapplyto:
1. brokers:OneormoreKafkabrokers.
2. topics:Existingorfuturetopics.
3. clients:Producerandconsumerclients.
4. users:Authenticateduserprincipals.
The brokers entity type can be configured either statically — by amending server.properties, or
dynamically—viatheKafkaAdminAPI.Otherentitytypesmayonlybeadministereddynamically.
Dynamic update modes
Orthogonal to the entity types, there are three dynamic update modes of configuration entries that
affectbrokerbehaviour:
¹³http://kafka.apache.org/documentation

Chapter9: BrokerConfiguration 143
1. read-only: The configuration is effectively static, requiring a change to server.properties
andasubsequentbrokerrestarttocomeintoeffect.
2. per-broker:Maybeupdateddynamicallyforeachbroker.Theconfigurationisappliedimme-
diately,withoutrequiringabrokerrestart.
3. cluster-wide:Maybeupdateddynamicallyasacluster-widedefault.Mayalsobeupdatedas
aper-brokervalueforcanarytesting.
These modes apply to thebrokersentity type.Each subsequent mode in this list is a strict superset
ofitspredecessors.Inotherwords,propertiesthatsupportthecluster-widemode,willalsosupport
per-broker and read-only modes. However, supporting per-broker does not imply supporting
cluster-wide.Asanexample,theadvertised.listenerspropertycanbedefinedonaper-broker
basis,butitcannotbeappliedcluster-wide.
Forotherentitytypes,suchasper-topicorper-userconfiguration,thesettingscanonly bechanged
dynamically via the API. The scoping rules are also different. For example, there is no concept
of per-broker configuration for topics; one can target all topics or individual topics, but the
configuration always applies to the entire cluster. (It would make no sense to roll out a topic
configurationtojustonebroker.)
Thetermdynamicupdatemodemightsoundalittleconfusing,particularlyfortheread-only
mode.Thelatteriseffectivelyastaticconfigurationentrydefinedintheserver.properties
file—itcannotbeassignedorupdatedviatheAdminAPI,andisanythingbutdynamic.
Dynamicconfiguration(whichexcludestheread-onlymode)ispersistedcentrallyintheZooKeeper
cluster.Youdon’tneedtointeractwithZooKeeperdirectlytoassigntheconfigurationentries.(This
optionisstillsupportedforbackwardcompatibility;however,itisdeprecatedanditsuseisstrongly
discouraged.) Instead, the Kafka Admin API and the built-in CLI tools allow an authorized user to
impartchangestotheKafkaclusterdirectly,whichinturn,willbewrittenbacktoZooKeeper.
The ‘Broker configs’ section in the official Kafka documentation indicates the highest supported
dynamicupdatemodeforeachconfigurationentry.
The documentation has a separate ‘Topic configs’¹⁴ section, listing configuration properties that
target individual topics. There is no dynamic update mode indicated in the documentation; in all
cases,theupdatemodeiseitherper-topicorcluster-wide.
Configuration precedence and defaults
There is a strict precedence order when configuration entries are applied. Stated in the order of
priority(thehighestbeingatthetop)theprecedencechainismadeupof:
1. Dynamicper-entityconfiguration;
¹⁴http://kafka.apache.org/documentation.html#topicconfigs

Chapter9: BrokerConfiguration 144
2. Dynamiccluster-widedefaultconfiguration;followedby
3. Staticread-onlyconfigurationfromserver.properties.
AseasonedKafkapractitionermayhavepickedupontwoadditional,easilyoverlooked,configura-
tionlevelswhichwehavenotmentioned—defaultconfigurationanddeprecatedconfiguration.The
default configuration is applied automatically if no other configuration can be resolved. There is a
caveat:Apropertymaybearelativelyrecentaddition,replacinganolder,deprecatedproperty.Ifthe
newerpropertyisnotset,Kafkawillapplythevalueofadeprecatedpropertyifoneisset,otherwise,
and only then, will the default value be applied. (In fact, a configuration property may shadow
multipledeprecatedproperties,inwhichcaseamorecomplexchainofprecedenceisformed.)
Configuration defaults are also specified in the official Kafka documentation page, but there is a
snag: the default value often does not convey any practical meaning. For example, what does it
mean when the default value of advertised.listeners is stated as null? In addition to viewing
the default, one must carefully read the description of the property, particularly when a property
replacesoneormoredeprecatedproperties.Thedocumentationdelineatesthebehaviourofanentity
(broker,topic,etc.)whenthepropertyisnotset.
DynamicupdatemodeswereintroducedinKafka1.1aspartofKIP-226(KIP standsforKafkaIm-
provementProposal)toreducetheoverheadofadministeringalargeKafkaclusterandpotentialfor
interruptionsandperformancedegradationcausedbyrollingbrokerrestarts.Somekeymotivating
usecasesincluded—
• Updatingshort-livedSSLkeystoresonthebroker;
• Performance-tuningbasedonmetrics(e.g.increasingnetworkorI/Othreads);
• Adding,removing,orreconfiguringmetricsreporters;
• Updatingconfigurationofalltopicsconsistentlyacrossthecluster;
• Updatinglogcleanerconfigurationfortuning;and
• Updatinglistener/securityconfiguration.
Theoriginalproposalconsidereddeprecatingthestaticconfigurationentriesinserver.properties
altogether for those settings that could be altered programmatically. In the course of analysis and
community consultation, this measure was found to be too drastic and consequently rejected —
entriesinserver.propertieswereallowedtostay,asitwasconsideredusefultomaintainasimple
quick-startexperienceofworkingwithKafkawithoutsettingupconfigurationoptionsinZooKeeper
first.
https://cwiki.apache.org/confluence/display/KAFKA/KIP-226+-+Dynamic+Broker+Configuration

Chapter9: BrokerConfiguration 145
Applying broker configuration
Static configuration
The static configuration for all Kafka components is housed in $KAFKA_HOME/config. A broker
sourcesitsread-onlystaticconfigurationfromserver.properties.
With one exception, all entries in server.properties are optional. If omitted, the fallback chain
comprising deprecated properties and the default value takes effect. The only mandatory setting
is zookeeper.connect. It specifies the ZooKeeper connection string as a comma-separated list of
host:port pairs, in the form host1:port1,host2:port2,...,hostN:portN — allowing for multiple
redundantZooKeeperconnections.
Other than sharing the comma-separated host:port format, the ZooKeeper connection
stringhasnothingincommonwiththebootstrapserverslistusedtoconfigureKafkaclients.
WhileaKafkaclientemploysatwo-stepprocesstoconnecttoallbrokersinacluster,aKafka
brokerneedsonlyoneconnectiontoaZooKeepernodeandwillestablishitdirectly.Under
thehood,ZooKeeperemploysanatomicbroadcastprotocoltosyndicateupdatesamongall
peernodes,eliminatingtheneedforeverybrokertoconnecttoeverynodeintheZooKeeper
ensemble.
Anothercrucialconfigurationentryinserver.propertiesisbroker.id,whichspecifiestheunique
identifier of the broker within the cluster. This is being brought up now because awareness of the
broker IDs for nodes in a Kafka cluster is required to administer targeted configuration, where
changesmaybeprogressivelyappliedto selectsubsetsofthebrokernodes.
If left unspecified or set to -1, the broker ID will be dispensed automatically by the ZooKeeper
ensemble, using an atomically-generated sequence starting from 1001. (This can be configured by
settingreserved.broker.max.id;thesequencewillbeginatonehigherthanthisnumber.)
It is considered good practice to set broker.id explicitly, as it makes it easy to quickly deter-
mine the ID by looking at server.properties. Once the ID has been assigned, it is written to a
meta.propertiesfile,residingintheKafkalogsdirectory.Thisdirectoryisspecifiedbythelog.dirs
property,defaultingto/tmp/kafka-logs.
Whilethenamingmightappeartosuggestotherwise,theKafkalogsdirectoryisnotthesame
as the directory used for application logging. The logs directory houses the log segments,
indexes,andcheckpoints—usedbythebrokertopersisttopic-partitiondata.Thecontents
of this directory are essential to the operation of the broker; the loss of the logs directory
amountstothelossofdataforthebrokerinquestion.
To change the ID of an existing broker, you must first stop the broker. Then remove thebroker.id
entry from meta.properties or delete the file altogether. You can then change broker.id in

146
| Chapter9: BrokerConfiguration |     |     |     |     |
| ----------------------------- | --- | --- | --- | --- |
server.properties. Finally, restart the broker. Upon startup, the Kafka application log should
indicatethenewbrokerID.
| Dynamic | configuration |     |     |     |
| ------- | ------------- | --- | --- | --- |
Dynamicconfigurationisappliedremotelyoveraconventionalclientconnection.Thiscanbedone
usingaCLItool—kafka-configs.sh,orprogrammatically—usingaclientlibrary.
The following examples assume that the Kafka CLI tools in $KAFKA_HOME/bin have been
added to your path. Run export PATH=$KAFKA_HOME/bin:$PATH to do this for your current
terminalsession.
Wearegoingtostartbyviewingafairlyinnocuousconfigurationentry—thenumberofI/Othreads.
Let’sseeifthevaluehasbeensetinserver.properties:
| grep num.io.threads | $KAFKA_HOME/config/server.properties |     |     |     |
| ------------------- | ------------------------------------ | --- | --- | --- |
num.io.threads=8
Right,num.io.threadshasbeensetto8.Thismeansthatourbrokermaintainsapoolofeightthreads
tomanagediskI/O.Wecantakeapeekintothebrokerprocesstoseethesethreads:
| KAFKA_PID=$(jps   | -l | grep | kafka.Kafka                      | | awk '{print | $1}') |
| ----------------- | --------- | -------------------------------- | ------------- | ----- |
| jstack $KAFKA_PID | | grep    | data-plane-kafka-request-handler |               |       |
"data-plane-kafka-request-handler-0" #50 daemon prio=5 os_prio=31 ￿
| cpu=672.65ms | elapsed=18234.06s |              | tid=0x00007faf670dc000 | ￿   |
| ------------ | ----------------- | ------------ | ---------------------- | --- |
| nid=0x13b03  | waiting           | on condition | [0x0000700007836000]   |     |
"data-plane-kafka-request-handler-1" #51 daemon prio=5 os_prio=31 ￿
| cpu=671.82ms | elapsed=18234.06s |              | tid=0x00007faf670d9000 |     |
| ------------ | ----------------- | ------------ | ---------------------- | --- |
| nid=0xc803   | waiting           | on condition | [0x0000700007939000]   |     |
"data-plane-kafka-request-handler-2" #52 daemon prio=5 os_prio=31 ￿
| cpu=672.39ms | elapsed=18234.06s |              | tid=0x00007faf670da000 | ￿   |
| ------------ | ----------------- | ------------ | ---------------------- | --- |
| nid=0xca03   | waiting           | on condition | [0x0000700007a3c000]   |     |
"data-plane-kafka-request-handler-3" #53 daemon prio=5 os_prio=31 ￿
| cpu=676.05ms | elapsed=18234.06s |              | tid=0x00007faf68802800 | ￿   |
| ------------ | ----------------- | ------------ | ---------------------- | --- |
| nid=0xcc03   | waiting           | on condition | [0x0000700007b3f000]   |     |
"data-plane-kafka-request-handler-4" #72 daemon prio=5 os_prio=31 ￿
| cpu=104.53ms | elapsed=16207.48s |              | tid=0x00007faf67b5c800 | ￿   |
| ------------ | ----------------- | ------------ | ---------------------- | --- |
| nid=0x1270b  | waiting           | on condition | [0x0000700007f4b000]   |     |

147
| Chapter9: BrokerConfiguration |     |     |     |     |     |
| ----------------------------- | --- | --- | --- | --- | --- |
"data-plane-kafka-request-handler-5" #73 daemon prio=5 os_prio=31 ￿
| cpu=97.75ms | elapsed=16207.48s |              | tid=0x00007faf67b5d800 |     | ￿   |
| ----------- | ----------------- | ------------ | ---------------------- | --- | --- |
| nid=0xe107  | waiting           | on condition | [0x0000700008866000]   |     |     |
"data-plane-kafka-request-handler-6" #74 daemon prio=5 os_prio=31 ￿
| cpu=96.60ms | elapsed=16207.48s |              | tid=0x00007faf650a8800 |     | ￿   |
| ----------- | ----------------- | ------------ | ---------------------- | --- | --- |
| nid=0x1320b | waiting           | on condition | [0x0000700008969000]   |     |     |
"data-plane-kafka-request-handler-7" #75 daemon prio=5 os_prio=31 ￿
| cpu=105.47ms | elapsed=16207.48s |              | tid=0x00007faf68809000 |     | ￿   |
| ------------ | ----------------- | ------------ | ---------------------- | --- | --- |
| nid=0x12507  | waiting           | on condition | [0x0000700008a6c000]   |     |     |
Indeed,eightthreadshavebeenspawnedandarereadytohandleI/Orequests.
Let’susethe--describeswitchtolistthedynamicvalue.Thepropertynamepassedto--describe
isoptional.Ifomitted,allconfigurationentrieswillbeshown.
| kafka-configs.sh | --bootstrap-server |               | localhost:9092 | \   |     |
| ---------------- | ------------------ | ------------- | -------------- | --- | --- |
| --entity-type    | brokers            | --entity-name | 0 \            |     |     |
| --describe       | num.io.threads     |               |                |     |     |
| Configs for      | broker 0 are:      |               |                |     |     |
It’scomeupempty.That’sbecausetherearenomatchingper-brokerdynamicconfigurationentries
persisted in ZooKeeper. Also, this tool does not output static entries in server.properties unless
theyhavebeenoverriddenbydynamicentries.
A minor note on terminology: this book is aligned with the official Kafka documentation
in referring to the ‘name’ part of the configuration entry as the ‘property name’. The CLI
toolsthatshipwithKafkarefertothepropertynameasthe‘key’.WorkingwithKafka,you
will eventually become used to seeing the same thing being referred to by vastly different
names.EvenwithintheCLItools,thereislittleconsistency—forexample,sometoolsrefer
to the list of bootstrap servers with the --bootstrap-server parameter, while others use
--broker-list.Toaddtotheconfusion,sometoolswillacceptmultiplebootstrapserversas
a comma-separated list, but still insist on the singular form of ‘bootstrap server’ (without
the trailing ‘s’). For all the undisputed merits of Kafka, do try to keep your expectations
groundedwhenworkingwiththebuilt-intooling.
Let’schangethenum.io.threadsvaluebyinvokingthefollowingcommand:
| kafka-configs.sh | --bootstrap-server |                  | localhost:9092 | \   |     |
| ---------------- | ------------------ | ---------------- | -------------- | --- | --- |
| --entity-type    | brokers            | --entity-name    | 0 \            |     |     |
| --alter          | --add-config       | num.io.threads=4 |                |     |     |

148
| Chapter9:                                | BrokerConfiguration |                 |                |           |             |     |
| ---------------------------------------- | ------------------- | --------------- | -------------- | --------- | ----------- | --- |
| Completed                                | updating            | config          | for broker:    | 0.        |             |     |
| Ru-runthekafka-configs.sh                |                     |                 | ... --describe |           | ...command: |     |
| Configs                                  | for broker          | 0 are:          |                |           |             |     |
| num.io.threads=4                         |                     | sensitive=false |                | synonyms= | ￿           |     |
| {DYNAMIC_BROKER_CONFIG:num.io.threads=4, |                     |                 |                |           | ￿           |     |
| STATIC_BROKER_CONFIG:num.io.threads=8,   |                     |                 |                |           | ￿           |     |
DEFAULT_CONFIG:num.io.threads=8}
Notonlyis itnowtellingus thataper-brokerentry fornum.io.threadshasbeenset, butit isalso
echoingthestaticvalueandthedefault.
Viewthethreadsagainusingthejstackcommand:
"data-plane-kafka-request-handler-0" #50 daemon prio=5 os_prio=31 ￿
| cpu=730.89ms |     | elapsed=18445.03s |              | tid=0x00007faf670dc000 |     | ￿   |
| ------------ | --- | ----------------- | ------------ | ---------------------- | --- | --- |
| nid=0x13b03  |     | waiting           | on condition | [0x0000700007836000]   |     |     |
"data-plane-kafka-request-handler-1" #51 daemon prio=5 os_prio=31 ￿
| cpu=728.40ms |     | elapsed=18445.03s |              | tid=0x00007faf670d9000 |     | ￿   |
| ------------ | --- | ----------------- | ------------ | ---------------------- | --- | --- |
| nid=0xc803   |     | waiting           | on condition | [0x0000700007939000]   |     |     |
"data-plane-kafka-request-handler-2" #52 daemon prio=5 os_prio=31 ￿
| cpu=729.16ms |     | elapsed=18445.03s |              | tid=0x00007faf670da000 |     | ￿   |
| ------------ | --- | ----------------- | ------------ | ---------------------- | --- | --- |
| nid=0xca03   |     | waiting           | on condition | [0x0000700007a3c000]   |     |     |
"data-plane-kafka-request-handler-3" #53 daemon prio=5 os_prio=31 ￿
| cpu=737.62ms |     | elapsed=18445.03s |              | tid=0x00007faf68802800 |     | ￿   |
| ------------ | --- | ----------------- | ------------ | ---------------------- | --- | --- |
| nid=0xcc03   |     | waiting           | on condition | [0x0000700007b3f000]   |     |     |
Bingo! The number of threads has been reduced, with the action taking effect almost immediately
followingtheupdateofthedynamicconfigurationproperty.
Dynamic configuration is an impressively powerful but dangerous tool. A bad value can take an
entire broker offline or cause it to become unresponsive. As a precautionary measure, Kafka caps
changesofcertainnumericvaluestoeitherhalfordoubletheirpreviousvalue,forciblysmoothening
out changes in the configuration. Increasing a setting to over double its initial value or decreasing
ittounderhalfofitsinitialvaluerequiresmultipleincrementalchanges.
Where a setting supports cluster-wide scoping, a good practice is to apply the setting to an
individual broker before propagating it to the whole cluster. This is effectively what we’ve done
withnum.io.threads.
The --entity-type argument is compulsory and can take the value of brokers, users, clients, or
topics.The--entity-nameargumentiscompulsoryforper-brokerdynamicupdatesandcanbere-
placed with--entity-defaultfor cluster-wide updates. Let’s apply thenum.io.threads.setting
totheentirecluster.

149
| Chapter9: BrokerConfiguration |                    |                  |                |     |     |     |
| ----------------------------- | ------------------ | ---------------- | -------------- | --- | --- | --- |
| kafka-configs.sh              | --bootstrap-server |                  | localhost:9092 |     |     | \   |
| --entity-type                 | brokers            | --entity-default |                |     | \   |     |
| --alter                       | --add-config       | num.io.threads=4 |                |     |     |     |
Thenlisttheconfiguration:
| kafka-configs.sh                                | --bootstrap-server |               | localhost:9092 |     |     | \   |
| ----------------------------------------------- | ------------------ | ------------- | -------------- | --- | --- | --- |
| --entity-type                                   | brokers            | --entity-name |                | 0   | \   |     |
| --describe                                      | num.io.threads     |               |                |     |     |     |
| Configs for                                     | broker 0 are:      |               |                |     |     |     |
| num.io.threads=4                                | sensitive=false    |               | synonyms=      |     | ￿   |     |
| {DYNAMIC_BROKER_CONFIG:num.io.threads=4,        |                    |               |                |     | ￿   |     |
| DYNAMIC_DEFAULT_BROKER_CONFIG:num.io.threads=4, |                    |               |                |     |     | ￿   |
| STATIC_BROKER_CONFIG:num.io.threads=8,          |                    |               |                | ￿   |     |     |
DEFAULT_CONFIG:num.io.threads=8}
The resulting list has an extra value: DYNAMIC_DEFAULT_BROKER_CONFIG:num.io.threads=4. As pre-
viously mentioned, the cluster-wide entries are second in the order of precedence, which is also
reflectedinthelistabove.Whenusingthe--describeswitch,wecanspecify--entity-defaultto
isolatecluster-wideconfiguration.
| kafka-configs.sh | --bootstrap-server |                  | localhost:9092 |     |      | \   |
| ---------------- | ------------------ | ---------------- | -------------- | --- | ---- | --- |
| --entity-type    | brokers            | --entity-default |                |     | \    |     |
| --describe       | num.io.threads     |                  |                |     |      |     |
| Default config   | for brokers        | in the           | cluster        |     | are: |     |
| num.io.threads=4 | sensitive=false    |                  | synonyms=      |     | ￿    |     |
{DYNAMIC_DEFAULT_BROKER_CONFIG:num.io.threads=4}
Having applied thecluster-widesetting, and assuming the update has proved to be stable,wecan
nowremovetheper-brokerentry.Runthefollowing:
| kafka-configs.sh | --bootstrap-server |                | localhost:9092 |     |     | \   |
| ---------------- | ------------------ | -------------- | -------------- | --- | --- | --- |
| --entity-type    | brokers            | --entity-name  |                | 0   | \   |     |
| --alter          | --delete-config    | num.io.threads |                |     |     |     |
Always remove per-broker settings once you apply the cluster-wide defaults. Unless
thereisacompellingreasonforaper-brokersettingtovaryfromacluster-widedefault,
maintaining both settings creates clutter and may lead to confusion in the longer-term —
whenthecluster-widesettingisalteredlaterdownthetrack.
Having just deleted the per-broker configuration, presumably the only remaining entry is the
cluster-wideone.Let’sdescribetheconfigurationforbroker0:

150
| Chapter9: BrokerConfiguration |                    |               |                |     |     |     |
| ----------------------------- | ------------------ | ------------- | -------------- | --- | --- | --- |
| kafka-configs.sh              | --bootstrap-server |               | localhost:9092 |     |     | \   |
| --entity-type                 | brokers            | --entity-name |                | 0   | \   |     |
| --describe                    | num.io.threads     |               |                |     |     |     |
| Configs for                   | broker 0 are:      |               |                |     |     |     |
Thismayleavethereaderinaslightlypuzzledstate.WheredidtheDYNAMIC_DEFAULT_BROKER_CONFIG
entrygo?Wheninvokedwiththe--entity-nameparameter,kafka-configs.shwillonlydisplayan
entryifaper-brokerconfigurationhasbeenassigned,irrespectiveofwhetherornotacluster-wide
entry has also been set. Running the command with the --entity-default switch still works as
expected,showingthecluster-widedefaults:
| Default config   | for brokers     | in the | cluster   |     | are: |     |
| ---------------- | --------------- | ------ | --------- | --- | ---- | --- |
| num.io.threads=4 | sensitive=false |        | synonyms= |     | ￿    |     |
{DYNAMIC_DEFAULT_BROKER_CONFIG:num.io.threads=4}
Now that we’re done with our examples, we can revert the configuration to its original state by
deletingthecluster-wideentry:
| kafka-configs.sh | --bootstrap-server |                  | localhost:9092 |     |     | \   |
| ---------------- | ------------------ | ---------------- | -------------- | --- | --- | --- |
| --entity-type    | brokers            | --entity-default |                |     | \   |     |
| --describe       | num.io.threads     |                  |                |     |     |     |
| Applying         | topic              | configuration    |                |     |     |     |
Settings that apply broadly to all topics, as well as topic-wide defaults, can be edited using the
static configuration in server.properties or via dynamic updates. In addition, some settings can
bealteredonaper-topicbasisusingjustthedynamicapproach.
Thenextexamplewilltinkerwithanotherfairlybenignsetting—flush.messages—whichcontrols
thenumberofmessagesthatcanbewrittentoalogbeforeitisforciblyflushedtodiskwiththefsync
command.Logflushingisdisabledbydefault.(Actually,thevalueissettoaveryhighnumber:2⁶³
–1.)
Kafka requires that the topic exists before making targeted changes. We are going to create a test
topicnamedtest.topic.configforthisdemonstration.Runthecommandbelow.
| kafka-topics.sh | --bootstrap-server |     | localhost:9092 |     |     | --create \ |
| --------------- | ------------------ | --- | -------------- | --- | --- | ---------- |
--topic test.topic.config --replication-factor 1 --partitions 1
Thenextcommandwillapplyanoverrideforflush.messagesforthetest.topic.configtopic.

151
| Chapter9: BrokerConfiguration |                                 |                |                   |     |
| ----------------------------- | ------------------------------- | -------------- | ----------------- | --- |
| kafka-configs.sh              | --zookeeper                     | localhost:2181 | \                 |     |
| --entity-type                 | topics --entity-name            |                | test.topic.config | \   |
| --alter                       | --add-config flush.messages=100 |                |                   |     |
Note: When referring to the topics entity type, you must substitute the --bootstrap-server
argument with --zookeeper, specifying the host:port combination of any node in the ZooKeeper
ensemble.
ThisisnotalimitationofKafka—thestandardAdminAPIsupportssettingandquerying
topic-levelconfiguration;rather,thelimitationiswiththeCLItoolsthatseemtohavetaken
a back seat to the rapid improvements in the Kafka wire protocol and client APIs. This
limitation applies to Kafka 2.4.0 and earlier versions, verified at the time of writing. For
thoseinterested,asolutionproposalisbeingfloatedinKIP-248¹⁵.Thereisasliverofhope
thatbythetimethisbookfallsintothereader’shands,themaintainersofKafkawillhave
addressedit.
Wecannowusethe--describeswitchtoreadbacktheconfiguration:
| kafka-configs.sh | --zookeeper               | localhost:2181 | \                      |     |
| ---------------- | ------------------------- | -------------- | ---------------------- | --- |
| --entity-type    | topics --entity-name      |                | test.topic.config      | \   |
| --describe       | flush.messages            |                |                        |     |
| Configs for      | topic 'test.topic.config' |                | are flush.messages=100 |     |
It was previously stated that the entity name is an optional argument to the--describe switch. To
furtherbroadenthequerytoall topics,usethe--entity-defaultswitch,asshownbelow.
| kafka-configs.sh | --zookeeper             | localhost:2181 | \          |     |
| ---------------- | ----------------------- | -------------- | ---------- | --- |
| --entity-type    | topics --entity-default |                | --describe |     |
Similarly, using --entity-default with the --alter switch will apply the dynamic configuration
defaultstoall topics.Forexample,ifwewantedtoapplyflush.messages=100toalltopics,wecould
haverunthefollowing:
| kafka-configs.sh | --zookeeper                     | localhost:2181 | \   |     |
| ---------------- | ------------------------------- | -------------- | --- | --- |
| --entity-type    | topics --entity-default         |                | \   |     |
| --alter          | --add-config flush.messages=100 |                |     |     |
¹⁵https://cwiki.apache.org/confluence/display/KAFKA/KIP-248+-+Create+New+ConfigCommand+That+Uses+The+New+AdminClient

Chapter9: BrokerConfiguration 152
Using kafka-configs.sh is one way of viewing the topic configuration; however, the limitation of
requiring ZooKeeper for topic-related configuration prevents its use in most production environ-
ments.Thereareotheralternatives—forexample,Kafdrop—thatdisplaytopicconfigurationusing
thestandardKafkaAdminAPI.SwitchtoKafdropandrefreshtheclusteroverviewpage.Youshould
see the recently-createdtest.topic.configtopicappearin the topicslist. Furthermore,thereis an
indicationthatacustomconfigurationhasbeenassigned.
Kafdropclusteroverview,showingtopicnames
Clickthroughtothetopic.Thecustomconfigurationisdisplayedinthetop-rightcornerofthetopic
overviewscreen.
Kafdroptopicoverview,showingcustomconfiguration
Havingdonewiththisexample,wecanreverttheconfigurationtoitsoriginalstatebyrunningthe
commandbelow.
kafka-configs.sh --zookeeper localhost:2181 \
--entity-type topics --entity-name test.topic.config \
--alter --delete-config flush.messages
Users and Clients
The lion’s share of configuration use cases relates to brokers, with the remainder mostly falling
on topics. Users and clients are configured within the broader context of security and quota
management — topic areas that will be covered separately in Chapter 16: Security and Chapter
17:Quotas.
Although users and clients are not going to be covered in this chapter, their configuration closely
resembles that of topics. In other words, they support two dynamic update modes: per-entity and

Chapter9: BrokerConfiguration 153
cluster-wide, with the per-entity taking precedence, followed by cluster-wide defaults, and finally
bytheharddefaults.
Kafka is a highly tunable and adaptable event streaming platform. Understanding the ins and outs
ofKafkaconfigurationisessentialtooperatingaproductionclusteratscale.
Kafkaprovidesafairdegreeofscopegranularityofanindividualconfigurationentry.Configuration
entriesmayapplytoarangeofentitytypes,includingbrokers,topics,users,andclients.Depending
on the nature of the configuration, a change may be administered either statically — by amending
server.properties,ordynamically—viatheKafkaAdminAPI.Acombinationofwhetherremote
changes are supported and the targeted scope of a configuration entry is referred to as itsdynamic
updatemode.Thebroadestofsupporteddynamicupdatemodesappliesacluster-widesettingtoall
entities of a given type. The per-entity dynamic mode allows the operator to target an individual
entity — a specific broker, user, topic, or client. The read-only dynamic update mode bears the
narrowestofscopes,andisreservedforstatic,per-brokerconfiguration.
Inadditiontonavigatingthetheory,wehavealsorackedupsomehands-ontimewiththekafka-configs.sh
built-in CLI tool. This utility can be used to view and assign dynamic configuration entries. And
while it is effective, kafka-configs.sh has its idiosyncrasies and limitations — for example, it
requiresadirectZooKeeperconnectionforadministeringcertainentitytypes.

Chapter 10: Client Configuration
Someofthepreviouschaptershavegottenuswellunderwayinpublishingandconsumingrecords,
without dwelling on the individual client properties. This is a good time to take a brief detour and
exploretheworldofclientconfiguration.
Incontrasttobrokerconfiguration,withitsdynamic update modes, selectiveupdates,andbaffling
CLI tools, configuring a client is comparatively straightforward — in that client configuration is
static and mostly applies to the instance being configured. (There are a few exceptions.) However,
client configuration is significantly more nuanced — requiring a greater degree of insight on the
user’sbehalf.
This chapter subdivides the configuration into producer, consumer, and admin client settings.
Some configurable items are common, and have been extracted into their own section accord-
ingly. The reader may consult this chapter in addition to the online documentation, available at
kafka.apache.org/documentation¹⁶. However, the analysis presented here is much more in-depth,
coveringmaterialthatisnotreadilyavailablefromofficialsources.
Ofthenumerousclientconfigurationproperties,thereareseveraldozenthatrelatetoperformance
tuning and various esoteric connection-related behaviour. The primary focus of this chapter is
to cover properties that either affect the client functionally, or qualitatively impact any of the
guarantees that might be taken for granted. As a secondary objective, the chapter will outline the
performanceimplicationsoftheconfigurationwheretheimpactsarematerialoratleastperceptible,
but the intention is not to cover performance in fine detail — the user may want to defer to more
specialised texts for a finer level of analysis. Other chapters will also cover aspects of Kafka’s
performanceinmoredetail.
Configuration gotchas
Client configuration is arguably more crucial than broker configuration. Broker configuration is
administered by teams or individuals who are responsible for the day-to-day operation of Kafka,
likely among a few other large items of infrastructure under their custodianship. Changes at this
level are known or at least assumed to be broadly impacting, and are usually made with caution,
with due notice given to end-users. Furthermore, the industry is seeing a noticeable shift towards
fully-managedKafkaofferings,wherebrokeroperationfallsunderthecustodianshipofexpertteams.
Managingasingletechnologyforalargenumberofclientseventuallymakesoneproficientatit.
By comparison, there really is no such thing as a ‘fully-managed Kafka client’. Clients are bespoke
applications targeting specific business use cases and written by experts in the application domain.
¹⁶https://kafka.apache.org/documentation

Chapter10: ClientConfiguration 155
We are talking about software engineers with T-shaped skills, who are adept at a broad range of
technologiesandlikelyspecialisinginafewareas—probablyclosertosoftwarethaninfrastructure.
Personal experience working with engineering teams across several clients in different industries
has left a lasting impression upon the author. The overwhelming majority of perceived issues with
Kafkaarecausedbythemisuseofthetechnologyratherthanthemisconfigurationofthebrokers.To
thatpoint,atypicalKafkaend-userknowslittlemorethanwhatthey considertobetheminimally
essential amount of insight they need for their specific uses of Kafka. And herein lies the problem:
Howdoesoneassesswhatisessentialandwhatisnot,iftheydon’tinvestthetimeinexploringthe
breadthofknowledgeattheirdisposal?
SoftwareengineersworkingwithKafkawillinvariablygraspsomekeyconceptsofeventstreaming.
A lot of that insight is amassed from conversations with colleagues, Internet articles, perusing the
official documentation, and even observing how Kafka responds to different settings. Some of it
may be speculative, incomplete, assumed, and in more dire cases, outright misleading. Here is an
example. Most users know that Kafka offers strong durability guarantees with respect to published
records. This statement is made liberally by the project’s maintainers and commercial entities that
deriveincomefromApacheKafka.Thisisamarketingphrase;andwhileitisnotentirelyincorrect,
it has no tangible meaning and can be contorted to imply whatever the user wants it to. Can it be
takenthat—
1. Kafkaneverlosesarecord?
2. Kafkamayoccasionallylosearecord,where‘occasionally’impliesatolerablelevel?(Inwhich
case,whodecidesonwhatistolerableandwhatisnot?)
3. Thisguaranteeisappliedbydefaulttoallclientsandtopics?
4. Kafka offers this guarantee as an option but it is up to the client to explicitly take advantage
ofit?
The answer is a mixture of #2 and #4, but it is much more complex than that. Granted, the cluster
will set a theoretical upper bound on the durability metric, which is influenced by the number of
brokers and some notional recovery point objective attributed to each broker. In other words, the
configuration,topology,andhardwarespecificationofbrokerssetstheabsolutebest-casedurability
rating.Butitisultimatelytheproducerclientthatcontrolsthedurabilityofrecordsbothatthepoint
oftopiccreation—byspecifyingthereplicationfactorandcertainotherparameters,andatthepoint
of publishing the record — by specifying the number of acknowledgments for the partition leader
to request and also waiting for the receipt of the acknowledgment from the leader before deeming
the record as published. Most users will take comfort in knowing they have a solid broker setup,
neglectingtotakethedueactionsontheclient-sidetoensureend-to-end durability.
The importance of client configuration is further underlined by yet another factor, one that stems
from the design of Kafka. Given the present-day claims around the strengths of Kafka’s ordering
and delivery guarantees, one would be forgiven for assuming that the configuration defaults are
sensible, insofar as they ought to favour safety over other competing qualities. In reality, that is
not the case. Historically, Kafka was born out of LinkedIn’s need to move a very large number
of messages efficiently, amounting to multiple terabytes of data on an hourly basis. The loss of a

Chapter10: ClientConfiguration 156
message was not deemed as catastrophic, after all, a message or post on LinkedIn is hardly more
than an episode of self-flattery. Naturally, this has reflected on the philosophy of setting default
valuesthatprioritiseperformanceoverjustabouteverythingelsethatcounts.Thisproverbialsnake-
ladenpithasbestedmanyanunsuspectingengineer.
WhenworkingwithKafka,rememberthefirstruleofoptimisation:Don’tdoit.Infairness,thisrule
speakstopremature optimisation;however,asithappens,mostoptimisationinKafkaispremature.
The good news is: Setting the configuration properties to warrant safety has only a minor impact
onperformance—Kafkaisstillaperformancepowerhouse.
As we explore client configuration throughout the rest of the chapter, pay particular attention to
callouts that underline safety. There are a fair few, and each will have a cardinal impact on your
experiencewithKafka.Thisisn’ttosaythatconfiguration‘gotchas’areexclusivetotheclient-side;
broker configuration has them too. Comparatively, the client configuration has a disproportionate
amount.
Applying client configuration
Client configuration is assembled as a set of key-value pairs before instantiating a KafkaProducer,
KafkaConsumer, or a KafkaAdminClient object. The original way of assembling client properties,
datingtotheearliestreleaseofKafka,wastouseaPropertiesobject:
var props = new Properties();
props.setProperty("bootstrap.servers",
"localhost:9092");
props.setProperty("key.serializer",
StringSerializer.class.getName());
props.setProperty("value.serializer",
StringSerializer.class.getName());
props.setProperty("max.in.flight.requests.per.connection",
String.valueOf(1));
try (var producer = new KafkaProducer<>(props)) {
// do something with producer
}
Aminorannoyanceof Properties-basedconfigurationisthatitforcesyoutouseaStringtypefor
bothkeysandvalues.Thismakessenseforkeys,butvaluesshouldjustbederivedfromtheirobject
representation. Over time, Kafka clients have been enhanced to accept an instance of Map<String,
Object>. Things have moved on a bit, and the same can now be written in a slightly more succinct
way:

157
| Chapter10:                  | ClientConfiguration                      |                                   |     |     |     |
| --------------------------- | ---------------------------------------- | --------------------------------- | --- | --- | --- |
| Map<String,                 | Object> config                           | =                                 |     |     |     |
| Map.of("bootstrap.servers", |                                          | "localhost:9092",                 |     |     |     |
|                             | "key.serializer",                        | StringSerializer.class.getName(), |     |     |     |
|                             | "value.serializer",                      | StringSerializer.class.getName(), |     |     |     |
|                             | "max.in.flight.requests.per.connection", |                                   | 1); |     |     |
| try (var                    | producer = new                           | KafkaProducer<>(config))          | {   |     |     |
| // do something             | with producer                            |                                   |     |     |     |
}
Frankly, whether you use or a has no material bearing on the outcome, and is a
|     |     | Properties Map |     |     |     |
| --- | --- | -------------- | --- | --- | --- |
matterofstyle.Mapoffersatersersyntax,particularlywhenusingaJava9-styleMap.of(...)static
factory method, and has the additional benefit of creating an immutable map. It is also considered
the more ‘modern’ approach by many practitioners. On the flip side, Properties forces you to
acknowledge that the value is a string and perform type conversion manually. The Properties
class also has a convenient load(Reader) method for loading a .properties file. Most of the code
inexistencethatusesKafkastillreliesonProperties.
When a client is instantiated, it verifies that the keys correspond to valid configuration property
names that are supported in the context of that client type. Failing to meet this requirement will
result in a warning message being emitted via the configured logger. Let’s instantiate a producer
client,intentionallymisspellingoneofthepropertynames:
| 16:49:51/0                               | WARN [main]:    | The configuration  | ￿                |       |     |
| ---------------------------------------- | --------------- | ------------------ | ---------------- | ----- | --- |
| 'max.in.flight.requests.per.connectionx' |                 |                    | was supplied     | but ￿ |     |
| isn't                                    | a known config. |                    |                  |       |     |
| 16:49:51/4                               | INFO [main]:    | Kafka version:     | 2.4.0            |       |     |
| 16:49:51/5                               | INFO [main]:    | Kafka commitId:    | 77a89fcf8d7fa018 |       |     |
| 16:49:51/5                               | INFO [main]:    | Kafka startTimeMs: | 1576648191386    |       |     |
Kafkadevelopershaveoptedforafailsafeapproachtohandlingpropertynames.Despitefailingthe
test, the client will continue to operate using the remaining properties. In other words, there is no
exception—justawarninglog.Theonusisontheusertoinspecttheconfigurationforcorrectness
andsiftthroughtheapplicationlogs.
Ratherthansupplyinganunknownpropertyname,let’sinsteadchangethevaluetoanunsupported
| type. Our | original example | had |     | set to 1. Changing | the |
| --------- | ---------------- | --- | --- | ------------------ | --- |
max.in.flight.requests.per.connection
valuetofooproducesaruntimeexception:

158
| Chapter10:       | ClientConfiguration |                                        |                       |     |
| ---------------- | ------------------- | -------------------------------------- | --------------------- | --- |
| Exception        | in thread           | "main" org.apache.kafka.common.config. |                       | ￿   |
| ConfigException: |                     | Invalid value                          | foo for configuration | ￿   |
max.in.flight.requests.per.connection: Not a number of type INT
| at org.apache.kafka.common.config.ConfigDef.parseType |     |     |     | ￿   |
| ----------------------------------------------------- | --- | --- | --- | --- |
(ConfigDef.java:726)
| at org.apache.kafka.common.config.ConfigDef.parseValue |     |     |     | ￿   |
| ------------------------------------------------------ | --- | --- | --- | --- |
(ConfigDef.java:474)
| at org.apache.kafka.common.config.ConfigDef.parse |     |     | ￿   |     |
| ------------------------------------------------- | --- | --- | --- | --- |
(ConfigDef.java:467)
| at org.apache.kafka.common.config.AbstractConfig.<init> |     |     |     | ￿   |
| ------------------------------------------------------- | --- | --- | --- | --- |
(AbstractConfig.java:108)
| at org.apache.kafka.common.config.AbstractConfig.<init> |     |     |     | ￿   |
| ------------------------------------------------------- | --- | --- | --- | --- |
(AbstractConfig.java:129)
| at org.apache.kafka.clients.producer.ProducerConfig.<init> |     |     |     | ￿   |
| ---------------------------------------------------------- | --- | --- | --- | --- |
(ProducerConfig.java:409)
| at org.apache.kafka.clients.producer.KafkaProducer.<init> |     |     |     | ￿   |
| --------------------------------------------------------- | --- | --- | --- | --- |
(KafkaProducer.java:326)
| at org.apache.kafka.clients.producer.KafkaProducer.<init> |     |     |     | ￿   |
| --------------------------------------------------------- | --- | --- | --- | --- |
(KafkaProducer.java:270)
| at effectivekafka.basic.BasicProducerSample.main |     |     | ￿   |     |
| ------------------------------------------------ | --- | --- | --- | --- |
(BasicProducerSample.java:15)
There several gotchas in configuring Kafka clients, and nailing property names is among
them.Chapter11:RobustConfigurationexploresbest-practicesforalleviatingtheinherent
namingissueandoffersahand-rolledremedythatlargelyeliminatestheproblem.
| Common | configuration |     |     |     |
| ------ | ------------- | --- | --- | --- |
This section describes configuration properties that are common across all client types, including
producers,consumers,andadminclients.
| Bootstrap | servers |     |     |     |
| --------- | ------- | --- | --- | --- |
We explored bootstrap.servers in Chapter 8: Bootstrapping and Advertised Listeners in consid-
erable detail. The reader is urged to study that chapter, as it provides the foundational knowledge
necessarytooperateandconnecttoaKafkacluster.Tosummarise,thebootstrap.serverproperty
is mandatory for all client types. It specifies a comma-delimited list of host-port pairs, in the form
host1:port1,host2:port2,...,hostN:portN,representingtheaddressesofasubsetofbrokernodes
that the client can try to connect to, in order to download the complete cluster metadata and
subsequentlymaintaindirectconnectionswithall brokernodes.Theaddressesneednotallpointto

Chapter10: ClientConfiguration 159
livebrokernodes;providedtheclientisabletoreachatleastone ofthebrokers,itwillreadilylearn
theentireclustertopology.
The crunch is in ensuring that the retrieved cluster metadata lists broker addresses that
are reachable from the client. The addresses disclosed in the metadata may be completely
differentfromthosesuppliedinbootstrap.servers.Asaconsequence,theclientisableto
maketheinitialbootstrappingconnectionbutstumbleswhenconnectingtotheremaining
hosts. For a better understanding of this problem and the recommended solutions, consult
Chapter8:BootstrappingandAdvertisedListeners.
Client DNS lookup
The client.dns.lookup is a close relative of bootstrap.servers, and is also covered in Chapter 8:
BootstrappingandAdvertisedListeners.Thepropertyisoptional,acceptinganenumeratedconstant
fromthelistbelow.
• default: Retains legacy behaviour with respect to DNS resolution, in other words, it will
resolve a single address for each bootstrap endpoint — being the first entry returned by the
DNSquery.Thisoptionappliesbothtothebootstraplistandtheadvertisedhostsdisclosedin
theclustermetadata.
• resolve_canonical_bootstrap_servers_only: Detects aliases in the bootstrap list, expanding
them to a list of resolved canonical names using a reverse DNS lookup. This option was
introducedinKafka2.1.0aspartofKIP-235¹⁷,primarilytosupportsecuredconnectionsusing
Kerberosauthentication.Thisbehaviourappliestothebootstraplistonly;theadvertisedhosts
aretreatedconventionally,asperthedefaultoption.
• use_all_dns_ips:SupportsmultipleADNSrecordsforthesamefully-qualifieddomainname,
resolving all hosts for each endpoint in the bootstrap list. The client will try each host in turn
until a successful connection is established. This option was introduced in Kafka 2.1.0 as part
ofKIP-302¹⁸,andappliestoboththebootstraplistandtheadvertisedhosts.
Client ID
The optional client.id property allows the application to associate a free-form logical identifier
with the client connection, used to distinguish between the connected clients. While in most cases
itmaybesafelyomitted,theuseoftheclientIDprovidesforagreaterdegreeofsourcetraceability,
asitisusedforthelogicalgroupingofrequestsinKafkametrics.
Beyond basic traceability, client IDs are also used to enforce quota limits on the brokers. The
discussionofthiscapabilitywillbedeferreduntilChapter17:Quotas.
¹⁷https://cwiki.apache.org/confluence/display/KAFKA/KIP-235%3A+Add+DNS+alias+support+for+secured+connection
¹⁸https://cwiki.apache.org/confluence/display/KAFKA/KIP-302+-+Enable+Kafka+clients+to+use+all+DNS+resolved+IP+addresses

Chapter10: ClientConfiguration 160
Retries and retry backoff
Theretriesandretry.backoff.mspropertiesspecifythenumberofretriesfortransienterrorsand
theinterval(inmilliseconds)towaitbeforeeachsubsequentretryattempt,respectively.Thenumber
ofretriesaccruesontopoftheinitialattempt,inotherwords,theupperboundonthetotalnumber
ofattemptsisretries + 1.
Toclarify,atransienterrorisanyconditionthatisdeemedaspotentially recoverable.Timeoutsare
the most common form of transient error, but there are many others that relate to the cluster state
—forexample,stalemetadataoracontrollerchange.
While the retry.backoff.ms property applies to all three client types, the retries property only
exists for the producer and admin clients; it is not supported by the consumer client. Instead of
limitingthenumberofretries,theconsumerlimitsthetotaltimeaccordedtoaquery—forexample,
when the poll() method is invoked — obviating the need for an explicit retry counter. In spite of
thisminordisparity,wewilldiscussthesetwoconfigurationaspectsasacollectivewhole.
Thedefaultsettingof retriesisInteger.MAX_VALUE,andtheproducerandadminclientswillwait
100msbydefaultbetweeneachattempt.Thereisnodefaultvalueforthepolltimeoutthatappliesto
consumerclients—thetimeoutisspecifiedexplicitlyasaparametertothepoll()method.Whether
these defaults are sensible depends on the combination of your network, the amount of resources
available to both the cluster and the client apps, and your application’s tolerance for awaiting a
successfulorfailedoutcomeofpublishingarecord.
There is a gotcha here, albeit a subtle one. It does not fundamentally matter how many
retries one permits, or the total time spent retrying, there are only two possible outcomes.
Thenumberofretriesandthebackofftimecouldbekepttoaminimum,inwhichcasethe
likelihoodofanerrorreachingtheapplicationishigh.Evenifthesenumbersareempirically
derived,eventuallyonewilleventuallyobserveascenariowheretheretriesareexhausted.
Alternatively, one might leave retries at its designated default of Integer.MAX_VALUE, in
which case the client will just keep hammering the broker, while the application fails to
make progress. We need to acknowledge that failures are possible and must be accounted
forattheapplicationlevel.
When Kafka was first released into the wild, every broker was self-hosted, and most were running
eitherinacorporatedatacentreoronapubliccloud,incloseproximitytotheclientapplications.In
otherwords,thenetworkwasrarelytheculprit.Thelandscapehasshiftedconsiderably;itisfarmore
commontoseemanagedKafkaofferings,whicharedeliveredeitheroverthepublicInternetorvia
VPCpeering.Also,withtheincreasedadoptionofeventstreamingintheindustry,anaveragebroker
nowcarriesmoretrafficthanitusedto,withtheincreaseinloadeasilyoutstrippingtheperformance
advancementsattributabletonewerhardwareandefficiencygainsintheKafkacodebase.Withthe
adoption of public cloud providers, organisations are increasingly looking to leverage availability
zones to protect themselves from site failures. As a result, Kafka clusters are now larger than ever
before, both in terms of the number of broker nodes and their geographic distribution. One needs

Chapter10: ClientConfiguration 161
to take these factors into account when setting retries, retry.backoff.ms and the consumer poll
timeout,andgenerallywhendevisingtheerrorhandlingstrategyfortheapplication.
Analternatewayoflookingattheproblemisthatitisn’taboutthestabilityprofileoftheunderlying
network, the capacity of the Kafka cluster, or the distribution of failures one is likely to experience
on a typical day. Like any other client consuming a service, one must be aware of their own non-
functional requirements and any obligations they might have to their upstream consumers. If a
Kafka client application is prepared to wait no more than a set time for a record to be published,
thentheretryprofileandtheerrorhandlingmustbedevisedwiththatinmind.
Testing considerations
Continuingthediscussionabove,anothercauseoffailuresthatisoftenoverlookedrelatestorunning
Kafka in performance-constrained environments as part of automated testing. Developers will
routinely run single-broker Kafka clusters in a containerised environment or in a virtual machine.
Kafka’s I/O performance is significantly diminished in Docker or on a virtualised file system. (The
reasons as to why are not important for the moment.) The problem is exacerbated when Docker is
runonmacOS,whichadditionallyincursthecostofvirtualisation.Asaconsequence,expectalonger
initialreadinesstimeandmoreanaemicstatetransitionsatthecontroller.Thetestmayassumethat
KafkaisavailablebecausethecontainerhasstartedandKafkaisacceptingconnectionsonitslistener
ports; however, the latter does not imply that the broker is ready to accept requests. It may take
severalsecondsforittobereadyandthetimingwillnotbeconsistentfromruntorun.Thedefault
tolerance(effectivelyindefiniteretries)actuallycopeswellwiththesesortsofscenarios.Tuningretry
behaviourtobetterrepresentproductionscenarios,whileappearingprudent,mayhamperlocaltest
automation—leadingtobrittleteststhatoccasionallyfailduetotiminguncertainty.
Onewayofsolvingthisproblemistoallowforconfigurableretrybehaviour,whichmaylegitimately
vary between deployment environments. The problem with this approach is it introduces variance
between real and test environments, which is rarely ideal from an engineering standpoint. An
alternate approach, and one that is preferred by the author, is to introduce an extended wait loop
at the beginning of each test, allowing for some grace time for the broker to start up. The loop can
poll the broker for some innocuous read-only query, such as listing topic names. The broker may
initially take some time to respond, returning a series of errors — which are ignored — while it is
still in the process of starting up. But when it does respond, it usually indicates that the cluster has
stabilisedandthetestmaycommence.
Security configuration
All three client types can be configured for secure connections to the cluster. We are not going
to explore security configuration in this chapter, partly because the range of supported options is
overbearing,butmostlybecausethistopicareaiscoveredinChapter16:Security.

Chapter10: ClientConfiguration 162
Producer configuration
Thissectiondescribesconfigurationoptionsthatarespecifictotheproducerclienttype.
Acknowledgements
The acks property stipulates the number of acknowledgements the producer requires the leader to
have received before considering a request complete, and before acknowledging the write with the
producer.Thisisfundamentaltothedurabilityofrecords;amisconfiguredackspropertymayresult
inthelossofdatawhiletheproducernaivelyassumesthatarecordhasbeenstablypersisted.
Although the property relates to the number of acknowledgements, it accepts an enumerated
constantbeingoneof—
• 0:Don’trequireanacknowledgementfromtheleader.
• 1:Requireoneacknowledgementfromtheleader,beingthepersistenceoftherecordtoitslocal
log.Thisisthedefaultsettingwhenenable.idempotenceissettofalse.
• -1orall:Requiretheleadertoreceiveacknowledgementsfromallin-syncreplicas.Thisisthe
defaultsettingwhenenable.idempotenceissettotrue.
Each of these modes, as well as the interplay between acknowledgements and Kafka’s replication
protocol,arediscussedindetailinChapter13:ReplicationandAcknowledgements.
Maximum in-flight requests per connection
The max.in.flight.requests.per.connection property sets an upper bound on the number of
unacknowledgedrequeststheproducerwillsendonasingleconnectionbeforebeingforcedtowait
fortheiracknowledgements.Thedefaultvalueofthispropertyis5.
The purpose of this configuration is to increase the throughput of a producer. This is particularly
evident over long-haul, high-latency networks, where long acknowledgement times continually
interrupt a producer’s ability to publish additional records, even if the network capacity otherwise
permits this. The problem is not exclusive to high-latency networks; any internal constraint that
contributestoincreasesinacknowledgementtimes—forexample,slowreplicationwithinthecluster
duetolaggingin-syncreplicas—willnegativelyimpactthetransmissionrate.
This is a classic problem of flow control. Anyone familiar with the inner workings of networking
protocols will immediately liken the behaviour max.in.flight.requests.per.connection to the
venerable sliding window protocol used for TCP’s flow control. However, it is not quite the same;
thereisonekeydistinction—thelackoforderingandreassemblyofin-flightrecordsovertheextent
oftheunacknowledgedwindowwhenidempotenceisdisabled.
Thisproblemisbestexplainedwithanexample.Supposeaproducer,configuredwithdefaultvalues
formax.in.flight.requests.per.connectionandretries,queuesrecordsA,B,andCtothebroker

Chapter10: ClientConfiguration 163
in that precise order, assuming for simplicity that the records will occupy the same partition. The
tacit expectation is that these records will be persisted in the order they were sent, as per Kafka’s
ordering guarantees. Let’s assume thatA gets to the broker and is acknowledged. A transient error
occurs attempting to persist B. C is processed and acknowledged. The producer, having detected a
lackofacknowledgement,willretransmitB.Assumingtheretransmissionissuccessful,therecords
willappearinthesequenceA,C,andB —distinctfromtheordertheyweresentin.
Although the previous example used individual records to illustrate the problem, it was
a simplification of Kafka’s true behaviour. In reality, Kafka does not forward individual
records, but batches of records. But the principle remains essentially the same — just
substitute‘record’for‘batch’.Soratherthanindividualarrivingoutoforder,entirebatches
ofrecordsmayappeartobereorderedontheirtargetpartition.
The underlying issue is that the broker implicitly relies on ordering provided by the underlying
transportprotocol(TCP),whichactsatLayer4oftheOSImodel.Beingunawareoftheapplication
semantics (Layer 7), TCP cannot assist in the reassembly of application-level payloads. By default,
when enable.idempotence is set to false, Kafka does not track gaps in transmitted records and is
unabletoreorderrecordsoraccountforretransmissionsinthefaceoferrors.
In scenarios where strict order is fundamental to the correctness of the system,
and in the absence of idempotence, it essential that either retries is set to 0 or
max.in.flight.requests.per.connectionissetto1.However,thepreferredalternativeis
to set enable.idempotence to true, which will guard against the reordering problem and
alsoavoidrecordduplication.ThisisanotherexamplewhereKafka’sconfigurationfavours
performanceovercorrectness.
Enable idempotence
Theenable.idempotenceproperty,whensettotrue,ensuresthat—
• Anyrecordqueuedattheproducerwillbepersistedatmostoncetothecorrespondingpartition;
• Recordsarepersistedintheorderspecifiedbytheproducer;and
• Recordsarepersistedtoallin-syncreplicasbeforebeingacknowledged.
Thedefaultvalueof enable.idempotenceisfalse.
Enabling idempotence requires max.in.flight.requests.per.connection to be less than or equal
to5,retriestobegreaterthan0andackssettoall.Ifthesevaluesarenotexplicitlysetbytheuser,
suitable values will be chosen by default. If incompatible values are set, aConfigException will be
thrownduringproducerinitialisation.
The problem of non-idempotent producers arises when an intermittent error causes a timeout of a
record acknowledgement on the return path whenacks is set to 1 or to all, and retries is set to a

Chapter10: ClientConfiguration 164
numbergreaterthanzero.Inotherwords,thebrokerwouldhavereceivedandpersistedtherecord,
butthewaitingproducertimesoutduetoadelay.Theproducerwillresendtherecordifithasretries
remaining, which will result in a second identical copy of the record persisted on the partition at
a later offset. As a consequence, all consumers will observe a duplicate record when reading from
thetopic.Furthermore,duetothebatchingnatureoftheproducer,itislikelythatduplicateswillbe
observedascontiguousrecordsequencesratherthanone-offrecords.
The idempotence mechanism in Kafka works by assigning a monotonically increasing sequence
number to each record, which in combination with a unique producer ID (PID), creates a partial
ordering relationship that can be easily reconciled at the receiving broker. The broker maintains
an internal map of the highest sequence number recorded for each PID, for each partition. A
broker can safely discard a record if its sequence number does not exceed the last persisted
sequence number by one. If the increment is greater than one, the broker will respond with an
OUT_OF_ORDER_SEQUENCE_NUMBER error, forcing the batches to be re-queued on the producer. The
requirementthatchangestothesequencenumbersarecontiguousproverbiallykillstwobirdswith
one stone. In addition to ensuring idempotence, this mechanism also guarantees the ordering of
records and avoids the reordering issue when max.in.flight.requests.per.connection is set to
allowmultipleoutstandingin-flightrecords.
The deduplication guarantees apply only to the individual records queued within the producer. If
the application calls send() with a duplicate record, the producer will assume that the records are
distinct, and will send the second with a new sequence number. As such, it is the responsibility of
theapplicationtoavoidqueuingunnecessaryduplicates.
The official documentation describes the enable.idempotence property as mechanism for the pro-
ducer to ensure that exactly one copy of each record is written in the stream and that records are
writteninthestrictordertheywerepublishedin.
Withoutasuitableaprioriassuranceastothelivenessoftheproducer,thebroker,andthereliability
oftheunderlyingnetwork,theconjectureinthedocumentationisinaccurate.Theproducerisunable
toenactanyformofassuranceif,forexample,itsprocessfails.Restartingtheprocesswouldloseany
queuedrecords,astheproducerdoesnotbufferthesetoastablestoragemediumpriortoreturning
from send(). (The producer’s accumulator is volatile.) Also, if the network or the partition leader
becomes unavailable, and the outage persists for an extent of time beyond the maximum allowed
bythedelivery.timeout.msproperty,therecordwilltimeout,yieldingafailedresult.Inthiscase,
thewritesemanticswillbeatmostonce.
Adegradednetworkoraslowbrokermayalsopresentaproblem.Supposearecordwaspublished
successfully, but the response timed out in such as way as to exhaust the delivery.timeout.ms
timeout on the producer. The client will return an error to the application, which may either skip
therecord,orpublishitasecondtime.Inthelattercase,theproducerclientwillnotdetectaduplicate,
andwillpublishwhatiseffectivelyanidenticalrecordasecondtime.Inthiscase,thewritesemantics
willbeatleastonce.
Thus,theofficialdocumentationshouldbetakeninthecontextofencounteringintermittenterrors
withinanotherwisefunctioningsystem,wherethesystemiscapableofmakingprogresswithinall

Chapter10: ClientConfiguration 165
ofthespecifiedtimeouts.Ifandonlyiftheproducerreceivedanacknowledgementofthewritefrom
thebroker,canwebecertainthatexactly-oncewritesemanticswereinforce.
Inatypicalorder-preservingapplication,settingretriesto0isimpractical,asitwillfloodtheappli-
cationwithtransienterrorsthatcouldotherwisehavebeenretried.Therefore,cappingmax.in.flight.requests.per.connection
to1orsettingenable.idempotencetotrueisthemoresensiblethingtodo,withthelatterbeingthe
preferredapproach,beinglessimpactedbyhigh-latencynetworks.
Compression type
Thecompression.typecontrolsthealgorithmthattheproducerwillusetocompressrecordbatches
beforeforwardingthemontothepartitionleaders.Thevalidvaluesare:
• none:Compressionisdisabled.Thisisthedefaultsetting.
• gzip: Use the GNU Gzip algorithm — released in 1992 as a free substitute for the proprietary
compressprogramusedbyearlyUNIXsystems.
• snappy: Use Google’s Snappy compression format — optimised for throughput at the expense
ofcompressionratios.
• lz4: Use the LZ4 algorithm — also optimised for throughput, most notably for the decompres-
sionspeed.
• zstd: Use Facebook’s ZStandard — a newer algorithm introduced in Kafka 2.1.0, intended to
achieveaneffectivebalancebetweenthroughputandcompressionratios.
This topic is discussed in greater detail in Chapter 12: Batching and Compression¹⁹. To summarise,
compression may offer significant gains in network efficiency. It also reduces the amount of disk
I/Oandstoragespacetakenuponthebrokers.
Key and value serializer
The key.serializer and the value.serializer properties allow the user to configure the mecha-
nismforserializingtherecords’keysandvalues,respectively.Thesepropertieshavenodefaults.An
alternative way to specify a serializer is to directly instantiate one and pass it as a reference to an
overloadedKafkaProducerconstructor.
Serializationisacomplexfieldthattranscendsclientconfiguration,touchingonthebroaderissuesof
customdatatypesandapplicationdesign.Thischapterwillnotdiscussthenuancesofserialization;
instead,consultChapter7:Serializationforacomprehensivediscussiononthistopic.
¹⁹chapter-batching-compression

Chapter10: ClientConfiguration 166
Partitioner
Thepartitioner.classpropertyallowstheapplicationtooverridethedefaultpartitioningscheme
byspecifyinganimplementationofaorg.apache.kafka.clients.producer.Partitioner.Unlessin-
structedotherwise,theproducerwillusetheorg.apache.kafka.clients.producer.internals.DefaultPartitioner
implementation.
ThebehaviouroftheDefaultPartitionervariesdependingontheattributesoftherecord:
1. IfapartitionisexplicitlyspecifiedintheProducerRecord,thatpartitionwillalwaysbeused.
2. Ifnopartitionisset,butakeyhasbeenspecified,thekeyishashedtodeterminethepartition
number.
3. If neither the partition nor the key is specified, and the current batch already has a ‘sticky’
partitionassignedtoit,thenmaintainthesamepartitionnumberasthecurrentbatch.
4. If neither of the above conditions are met, then assign a new ‘sticky’ partition to the current
batchanduseitforthecurrentrecord.
Points#1and#2capturetheage-oldbehaviouroftheDefaultPartitioner.Points#3and#4
wereaddedinthe2.4.0releaseofKafka,aspartofKIP-480²⁰.Previously,theproducerwould
vacuouslyallocaterecordstopartitionsinaround-robinfashion.Whilethisspreadstheload
evenlyamongthepartitions,itlargelynegatesthebenefitsofbatching.Sincepartitionsare
masteredbydifferentbrokersinthecluster,thisapproachusedtoengagepotentiallyseveral
brokers to publish a batch, resulting in a much higher typical latency, influenced by the
slowest broker. The 2.4.0 update limits the engagement to a single broker for any given
unkeyedhash,reducingthe99ʰpercentilelatencybyafactoroftwotothree,dependingon
therecordthroughput.Thepartitionsarestillevenlyloadedoveralongseriesofbatches.
Hashing a key to resolve the partition number is performed by passing the byte contents of the
key through a MurmurHash2 function, then taking the low-order 31 bits from the resulting 32-bit
by masking off the highest order bit (bitwise AND with 0x7fffffff). The resulting value is taken,
modulothenumberofpartitions,toarriveatthepartitionindex.
While hashing of record keys and mapping of records to partitions might appear straight-
forward, it is laden with gotchas. A more thorough analysis of the problem and potential
solutionsarepresentedinChapter6:DesignConsiderations.Withoutgoingintothedetails
here,thereaderisurgedtoabidebyonerule:whenthecorrectnessofasystemispredicated
onthekey-centricorderingofrecords,avoidresizingthetopicasthiswilleffectivelyvoid
anyorderingguaranteesthattheconsumerecosystemmayhavecometorelyupon.
InadditiontotheDefaultPartitioner,theJavaproducerclientalsocontainsaRoundRobinPartitioner
andaUniformStickyPartitioner.
²⁰https://cwiki.apache.org/confluence/display/KAFKA/KIP-480%3A+Sticky+Partitioner

Chapter10: ClientConfiguration 167
The RoundRobinPartitioner will forward the record to a user-specified partition if one is set;
otherwise, it will indiscriminately distribute the writes to all partitions in a round-robin fashion,
regardlessofthevalueoftherecord’skey.Becausetheallocationofunkeyedrecordstopartitionsis
nondeterministic,itisentirelypossibleforrecordswiththesamekeytooccupydifferentpartitions
and be processed out of order. This partitioner is useful when an event stream is not subject to
orderingconstraints,inotherwords,whenKafkaisusedasaproverbial‘firehose’ofunrelatedevents.
Alternatively, this partitioner may be used when the consumer ecosystem has its own mechanism
forreassemblingevents,whichisindependentofKafka’snativepartitioningscheme.
TheUniformStickyPartitionerisapureimplementationofKIP-480²¹thatwasintroducedinKafka
2.4.0.Thispartitionerwillforwardtherecordtoauser-specifiedpartitionifoneisset;otherwise,it
willdisregardthekey,andinsteadassign‘sticky’partitionsnumbersbasedonthecurrentbatch.
One other ‘gotcha’ with partitioners lies in them being a pure producer-side concern. The
broker has no awareness of the partitioner used, it defers to the producer to make this
decision for each submitted record. This assumes that the producer ecosystem has agreed
on a single partitioning scheme and is applying it uniformly. Naturally, if reconfiguring
theproducerstouseanalternatepartitioner,onemustensurethatthischangeisrolledout
atomically—therecannotbetwoormoreproducersconcurrentlyoperatingwithdifferent
partitioners.
Onecanimplementtheirownpartitioner,shouldtheneedforonearise.Perhapsyouarefacedwitha
bespokerequirementtopartitionrecordsbasedonthecontentsoftheirpayload,ratherthanthekey.
While a custom partitioner may satisfy this requirement, a more straightforward approach would
be to concatenate the order-influencing attributes of the payload into a synthetic key, so that the
defaultpartitionercanbeused.(Itmaybenecessarytopre-hashthekeytocapitssize.)
Interceptors
The interceptor.classes property enables the application to intercept and potentially mutate
records en route to the Kafka cluster, just prior to serialization and partition assignment. This list
is empty by default. The application can specify one or more interceptors as a comma-separated
list of org.apache.kafka.clients.producer.ProducerInterceptor implementation classes. The
ProducerInterceptorinterfaceisshownbelow,withtheJavadoccommentsremovedforbrevity.
²¹https://cwiki.apache.org/confluence/display/KAFKA/KIP-480%3A+Sticky+Partitioner

Chapter10: ClientConfiguration 168
public interface ProducerInterceptor<K, V> extends Configurable {
public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record);
public void onAcknowledgement(RecordMetadata metadata,
Exception exception);
public void close();
}
The Configurable super-interface enables classes instantiated by reflection to take configuration
parameters:
public interface Configurable {
void configure(Map<String, ?> configs);
}
Interceptors act as a plugin mechanism, enabling the application to inject itself into the publishing
(andacknowledgement)processwithoutdirectlymodifyingtheapplicationcode.
This naturally leads to a question: Why would anyone augment the publisher using obscurely-
configured interceptors, rather than modifying the application code to address these additional
behavioursdirectlyintheapplicationcode?
Interceptors add an Aspect-Oriented Programming (AOP) style to modelling producer behaviour,
allowingonetouniformlyaddresscross-cuttingconcernsatindependentproducersinamannerthat
is modular and reusable. Some examples that demonstrate the viability of AOP-style interceptors
include:
• Accumulation of producer metrics — tracking the total number of records published, records
bycategory,etc.
• End-to-endtracingofinformationflowthroughthesystem—usingcorrelationheaderspresent
in records to establish a graph illustrating the traversal of messages through the relaying
applications,identifyingeachintermediatejunctionandthetimingsateachpoint.
• Loggingofentirerecordpayloadsorasubsetofthefieldsinarecord.
• Ensuringthatoutgoingrecordscomplywithsomeschemacontract.
• Dataleakprevention—lookingforpotentiallysensitiveinformationinrecords,suchascredit
cardnumbersorJWTbearertokens.
Once defined and tested in isolation, this behaviour could then be encompassed in a shared library
andappliedtoanynumberofproducers.
Thereareseveralcaveatstoimplementinganinterceptor:

Chapter10: ClientConfiguration 169
• Runtime exceptions thrown from the interceptor will be caught and logged, but will not be
allowedtopropagatetotheapplicationcode.Assuch,itisimportanttomonitortheclientlogs
when writing interceptors. A trapped exception thrown from one interceptor has no bearing
onthenextinterceptorinthelist:thelatterwillbeinvokedaftertheexceptionislogged.
• Aninterceptormaybeinvokedfrommultiplethreads,potentiallyconcurrently.Theimplemen-
tationmustthereforebethread-safe.
• Whenmultipleinterceptorsareregistered,theironSend()methodwillbeinvokedintheorder
they were specified in the interceptor.classes property. This means that interceptors can
act as a transformation pipeline, where changes to the published record from one interceptor
can be fed as an input to the next. This style of chaining is generally discouraged, as it leads
to content coupling between successive interceptor implementations — generally regarded as
the worst form of coupling and leading to brittle code. An exception in one interceptor will
not abort the chain — the next interceptor will still be invoked without having the previous
transformation step applied, breaking any assumptions it may have as to the effects of the
previousinterceptor.
• The onAcknowledgement() method will be invoked by the I/O thread of the producer. This
blocks the I/O thread until the method returns, preventing it from processing other acknowl-
edgements. As a rule of thumb, the implementation of onAcknowledgement() should be rea-
sonably fast, returning without unnecessary delays or blocking. It should ideally avoid any
time-consuming I/O of its own. Any time-consuming operations on acknowledged records
shouldbedelegatedtobackgroundthreads.
In light of the above, ProducerInterceptor implementations should be simple, fast, standalone
units of code that maintain minimal state, with no dependencies on one another. Any non-trivial
interceptor implementation should have a mandatory exception handler surrounding the bodies of
onSend()andonAcknowledgement(),soastocontrolpreciselywhathappensintheeventofanerror.
Maximum block time
Themax.block.msconfigurationpropertycontrolshowlongKafkaProducer.send()andKafkaProducer.partitionsFor()
willblockfor.Thesemethodscanbeblockedfortworeasons:eithertheinternalaccumulatorbuffer
is full or the metadata required for their operation is unavailable. The default value is 60000 (one
minute). Blocking in the user-supplied serializers or partitioner will not be counted against this
timeout.
Batch size and linger time
Thebatch.sizeandlinger.mspropertiescollectivelycontroltheextenttowhichtheproducerwill
attempt to batch queued records in order to maximise the outgoing transmission efficiency. The
defaultvaluesof batch.sizeandlinger.msare16384(16KiB)and0(milliseconds),respectively.
The linger.ms setting induces batching in the absence of heavy producer traffic by adding a small
amount of artificial delay — rather than immediately sending a record the moment it is enqueued,

Chapter10: ClientConfiguration 170
the producer will wait for up to a set delay to allow other records to accumulate in a batch. This
maximisestheamountofdatathatcanbetransmittedinonego.Althoughrecordsmaybeallowedto
lingerforuptothedurationspecifiedbylinger.ms,thebatch.sizepropertywillhaveanoverriding
effect, dispatching the batch once it reaches the set maximum size. Another way of looking at it:
while the linger.ms property only comes into the picture when the producer is lightly loaded, the
batch.sizepropertyiscontinuouslyineffect,ensuringthebatchnevergrowsaboveasetcap.
This topic is discussed in greater detail in Chapter 12: Batching and Compression. To summarise,
batching improves network efficiency and throughput, at the expense of increasing publishing
latency.Itisoftenusedcollectivelywithcompression,asthelatterismoreeffectiveinthepresence
ofbatching.
Request timeout
The request.timeout.ms property controls the maximum amount of time the client will wait for a
broker to respond to an in-flight request. If the response is not received before the timeout elapses,
theclientwilleitherresendtherequestifithasaretrybudgetavailable(configuredbytheretries
property), or otherwise fail the request. The default value of request.timeout.ms is 30000 (30
seconds).
Delivery timeout
Thedelivery.timeout.mspropertysetsanupperboundonthetimetoreportsuccessorfailureafter
acalltosend()returns,havingadefaultvalueof 120000(twominutes).
Thissettingactsasanoverarchinglimit,encompassing—
• Thetimethatarecordmaybedelayedpriortosending;
• Thetimetoawaitacknowledgementfromthebroker(if acks=1oracks=all);and
• Thetimebudgetedforretryablesendfailures.
The producer may report failure to send a record earlier than this time if either an unrecoverable
errorisencountered,theretrieshavebeenexhausted,ortherecordisaddedtoabatchwhichreached
an earlier delivery expiration deadline. The value of this property should be greater than or equal
tothesumof request.timeout.msandlinger.ms.
Thispropertyisarelativelyrecentaddition,introducedinKafka2.1.0aspartofKIP-91²².Themain
motivation was to consolidate the behaviour of several related configuration properties that could
potentially affect the time a record may be in a pending state, and thereby inadvertently extend
this time beyond the application’s tolerance to obtain a successful or failed outcome of publishing
the record. The consolidated delivery.timeout.ms property acts as an overarching budget on the
totalpendingtime,terminatingthepublishingprocessandyieldinganoutcomeatorjustbeforethis
²²https://cwiki.apache.org/confluence/display/KAFKA/KIP-91+Provide+Intuitive+User+Timeouts+in+The+Producer

Chapter10: ClientConfiguration 171
time is expended. In doing so, it does not deprecate the underlying properties. In fact, it prolongs
their utility by making them safer to use in isolation, allowing for a more confident fine-tuning of
producerbehaviour.
The diagram below, lifted from the description of KIP-91²³, illustrates the delivery.timeout.ms
property and its relation to the other properties that it subsumes. The red circles indicate points
whereatimeoutmayoccur.
Deliverytimeoutbehaviour
The individual stages that constitute the record’s journey from the producer to the broker are
explainedbelow.
• The initial call to send() can block up to max.block.ms, waiting on metadata or queuing for
available space in the producer’s accumulator. Upon completion, the record is appended to a
batch.
• Thebatchbecomeseligiblefortransmissionoverthewirewheneitherlinger.msorbatch.size
hasbeenreached.
• Oncethebatchisready,itmustwaitforatransmissionopportunity.Abatchmaybesentwhen
allofthefollowingconditionsaremet:
– Themetadataforthepartitionisknownandthepartitionleaderhasbeenidentified;
– Aconnectiontotheleaderexists;and
– Thecurrentnumberofin-flightrequestsislessthanthenumberspecifiedbymax.in.flight.requests.per.connection.
• Once the batch is transmitted, the request.timeout.ms property limits the time that the
producerwillwaitforanacknowledgementfromthepartitionleader.
• Iftherequestfailsandtheproducerhasoneormoreretriesremaining,itwillattempttosend
the batch again. Each send attempt will reset the request timeout, in other words, each retry
getsitsownrequest.timeout.ms.
²³https://cwiki.apache.org/confluence/display/KAFKA/KIP-91+Provide+Intuitive+User+Timeouts+in+The+Producer

Chapter10: ClientConfiguration 172
The await-send stage is the most troublesome section of the record’s journey, as there is no way
to precisely determine how long a record will spend in this state. Firstly, the batch could be held
backbyanissueinthecluster,beyondtheproducer’scontrol.Secondly,itmaybeheldbackbythe
preceding batch, which is particularly likely when the max.in.flight.requests.per.connection
propertyissetto1.
Prior to the Kafka 2.1.0, time spent in the await-send stage used to be bounded by the
same transmission timeout that is used after the batch is sent —request.timeout.ms. The
producer would eagerly start the transmission clock when the record entered await-send,
even though technically it was still queued on the producer. Records blocked waiting for
a metadata refresh or for a prior batch to complete could be pessimistically expired, even
thoughitwaspossibletomakeprogress.Thisproblemwascompoundedifthepriorbatch
was stuck in the sending stage, which granted it additional time credit — amplified by the
value of the retries property. In other words, it was possible for an await-send batch to
time out before its immediate forerunner, if the preceding batch happened to have made
moreprogress.
The strength of the delivery.timeout.ms property is that it does not discriminate between the
various stages of a record’s journey, nor does it unfairly penalise records due to contingencies in
aprecedingbatch.
Transactional ID and transaction timeout
Thetransactional.idandtransaction.timeout.mspropertiesalterthebehaviouroftheproducer
with respect to transactions. Transactions would be classed as a relatively advanced topic on the
Kafka‘complexity’spectrum;pleaseconsultChapter18:Transactionsforamorein-depthdiscussion.
Consumer configuration
This section describes configuration options that are specific to the consumer client type. Some
configurationpropertiesarereciprocalsoftheir producercounterparts;thesewillbecoveredfirst.
Key and value deserializer
Analogously to the producer configuration, the key.deserializer, and the value.deserializer
propertiesspecifythemechanismfordeserializingtherecords’keysandvalues,respectively.Asper
the producer scenario, a user can alternatively instantiate the deserializers directly and pass them
asreferencestoanoverloadedKafkaConsumerconstructor.
A broader discussion of (de)serialization is presented in Chapter 7: Serialization. The material
presentedinthatchaptershouldbeconsultedpriortoimplementingcustom(de)serializers.

Chapter10: ClientConfiguration 173
Interceptors
The interceptor.classes property is analogous to its producer counterpart, specifying a comma-
separated list of org.apache.kafka.clients.consumer.ConsumerInterceptor implementations, al-
lowingfortheinspectionandpossiblymutationofrecordsbeforeacalltoConsumer.poll()returns
themtotheapplication.
The ConsumerInterceptor interface is shown below, with the Javadoc comments removed for
brevity.
public interface ConsumerInterceptor<K, V>
extends Configurable, AutoCloseable {
public ConsumerRecords<K, V>
onConsume(ConsumerRecords<K, V> records);
public void onCommit(Map<TopicPartition,
OffsetAndMetadata> offsets);
public void close();
}
Theuseofinterceptorsontheconsumerfollowsthesamerationaleaswehaveseenontheproducer.
Specifically, interceptors act as a plugin mechanism, offering a way to uniformly address cross-
cuttingconcernsatindependentconsumersinamannerthatismodularandreusable.
Therearesimilaritiesanddifferencesbetweentheproducerandconsumer-levelinterceptors.Begin-
ningwiththesimilarities:
• Runtime exceptions thrown from the interceptor will be caught and logged, but will not be
allowedtopropagatetotheapplicationcode.Atrappedexceptionthrownfromoneinterceptor
hasnobearingonthenextinterceptorinthelist:thelatterwillbeinvokedaftertheexception
islogged.
• When multiple interceptors are registered, their onConsume() method will be invoked in the
ordertheywerespecifiedintheinterceptor.classesproperty,allowinginterceptorstoactas
a transformation pipeline. The same caveat applies as per the producer scenario; this style
of chaining is discouraged, as it leads to content coupling between successive interceptor
implementations—resultinginbrittlecode.
• The onCommit() method may be invoked from a background thread; therefore, the implemen-
tationshouldavoidunnecessaryblocking.
Asforthedifferences,thereisone:unlikeKafkaProducer,theKafkaConsumerimplementationisnot
thread-safe — the shared use of poll() from multiple threads is forbidden. Therefore, there is no
requirementthataConsumerInterceptorimplementationmustbethread-safe.

Chapter10: ClientConfiguration 174
Controlling the fetch size
When retrieving records from a Kafka cluster, one ultimately needs to decide how much data is
enough, and how much is too much. More is not always better; while increasing the fetch size will
lead to improved network utilisation and therefore higher throughput, it comes at a price — the
end-to-end propagation delay will suffer as a result. Conversely, fetching just a few records will
maketheapplicationmoreresponsive,butsmallfetchesrequiremoreround-tripstomovethesame
amountofdata,negativelyimpactingthethroughput.
Tuningthefetchsizeisamongtheperformance-impactingdecisionsonehastofacewhenbuilding
consumer applications. Kafka does little to help simplify this process. There is no consolidated
‘throughput ￿ latency’ dial that one can adjust to their satisfaction; instead, there are numerous
consumersettingsthatcollectivelyimpactthefetchbehaviour.
ThemostapparentcontrolisthetimeoutparametertotheConsumer.poll()method.However,this
is only an upper bound on the time that the method call will block for; its effects are limited to
the consumer — it does not limit the amount of data retrieved from the brokers. There are several
otherconsumerpropertiesthatinfluencethefetchingofdata;theirinfluenceextendspasttheclient
behaviour,affectinghowthebrokersrespondtofetchqueries.
Thefirstpairofpropertiesisthefetch.min.bytesandfetch.max.bytes.Respectively,theseproper-
ties constrain the minimum and the maximum amount of data the broker should return for a fetch
request.
Ifinsufficientdataisavailable,therequestwillwaitforthequantityofdataspecifiedbyfetch.min.bytes
to accumulate before answering the request. The default setting is 1, meaning that a broker will
respondassoonasasinglebyteisavailable,orifthefetchrequesttimesout.Thelatterisgoverned
byaseparatebutcomplementaryfetch.max.wait.msproperty,whichdefaultsto500(milliseconds).
Thefetch.max.bytespropertysetsasoft upperboundonthefetchrequest,actingmoreasaguide
thanalimit.Recordsarewrittenandfetchedinbatches,whicharetreatedasindivisibleunitsfrom
a broker’s perspective. Considering that the size of a single record might conceivably eclipse the
fetch.max.bytes limit, and indeed, the size of a given batch might also be correspondingly larger,
thefetchmechanismallowsforthis—potentiallyreturningalargerbatchthanwhatwasspecified
byfetch.max.bytes.Indoingso,itallowstheconsumertomakeprogress,whichwouldotherwise
beindefinitelyobstructedhadthefetch.max.byteslimitbeenenforcedverbatim.Moreaccurately,
the query permits an ‘oversized’ batch if it is the first batch in the first non-empty partition. The
defaultvalueof fetch.max.bytesis52428800(50MiB).

Chapter10: ClientConfiguration 175
Thereasonthatbatchesarenotbrokenupintoindividualrecordsandreturnedinsub-batch
quantities is due to Kafka’s fundamental architecture and deliberate design decisions that
contribute to its performance characteristics. Batches are an end-to-end construct; formed
by the producer, they are transported, persisted, and delivered to the consumers as-is —
withoutunpackingthebatchorinspectingitscontents—minimisingunnecessaryworkon
thebroker.Inthebest-casescenario,abatchispersistedandretrievedusingzero-copy,where
transferoperationsbetweenthenetworkandthestoragedevicesoccurwithnoinvolvement
fromtheCPU.Brokersareoftenthepointofcontention—theyformastatictopologythat
does not scale elastically — unlike, say, consumers. Reducing their workload by imparting
moreworkontotheproducerandconsumerclientsleadstoamorescalablesystem.
A further refinement of fetch.max.bytes is the max.partition.fetch.bytes property, applying a
soft limit on a per-partition basis. The default value of max.partition.fetch.bytes is 1048576 (1
MiB).
TherearenoofficialguidelinesfortuningKafkawithrespecttofetch.max.bytesandmax.partition.fetch.bytes.
While their individual functions are clear, their mutual relationship is not as apparent. One must
considerwhathappenswhenafetchresponseaggregatesbatchesfrommultiplepartitions.Suppose
a topic is unevenly loaded, where relatively few partitions collectively carry more records than the
remaining majority of partitions. If the max.partition.fetch.bytes setting is overly relaxed, the
results of the fetch will be biased towards the heavily-loaded partitions. In other words, the fetch
quotasetbyfetch.max.byteswillbedisproportionatelyexhaustedbytheminoritypartitions.Inthe
bestcase,thiswillnegativelyaffectthepropagationlatenciesofthemajoritypartitions;intheworst
case, this might lead to periods of starvation, where records for certain partitions are unceasingly
droppedfromtheresponse.
ThisParetoEffect isactuallymorecommonthanonemightimagine.Asrecordkeystend toreflect
theidentifiersofreal-worldentities,thedistributionofrecordswithinaKafkatopicoftenacquiresan
uncannyresemblancetotherealworld,whichasweknow,isoftenaccuratelydescribedbythepower
law.Settingaconservativevalueformax.partition.fetch.bytesimprovesfairness,increasingthe
likelihood of aggregating data over the majority partitions by penalising heavily loaded partitions.
However,anoverlyconservativevalueunderminestheallowancesetbyfetch.max.bytes.Further-
more,itmayleadtoabuildupofrecordsinminoritytopics.
Left to its devices, this discussion leads to broader topics, such as queuing and quality of
service,andfurtherstill,tosubjectssuchaseconomics,politics,andphilosophy,whichare
firmlyoutsidethescopeofthistext.Therelativetuningofthefetchcontrolscanbelikened
to the redistribution of wealth. It can only be said that the decision to favour one group
over another (in the context of Kafka’s topic partitioning, of course) must stem from the
non-functionalrequirementsoftheapplication,ratherthansomehardandfastrule.
The final configuration property pertinent to this discussion is max.poll.records, which sets the
upperboundonthenumberofrecordsreturnedinasinglecalltopoll().Unlikesomeoftheother
properties that control the fetch operation on the broker, and akin to the poll timeout, the effects

Chapter10: ClientConfiguration 176
of this property are confined to the client. After receiving the record batches from the brokers, and
having unpacked the batches, the consumer will artificially limit the number of records returned.
The excluded records will remain in the fetch buffer — to be returned in a subsequent call to
Consumer.poll().Thedefault valueof max.poll.recordsis500.
The original motivation for artificially limiting the number of returned records was largely his-
torical, revolving around the behaviour of the session.timeout.ms property at the time. The
max.poll.recordspropertywasintroducedinversion0.10.0.0ofKafka,describedindetailinKIP-
41.
TheactofpollingaKafkaclusterdidnotjustretrieverecords—ithadtheaddedeffectofsignalling
to the coordinator that the consumer is in a healthy state and able to handle its share of the
eventstream.Givenacombinationofasufficientlylargebatchandahightime-costofprocessing
individual records, a consumer’s poll loop might have taken longer than the deadline enforced by
the session.timeout.ms property. When this happened, the coordinator would assume that the
consumer had ‘given up the ghost’, so to speak, and reassign its partitions among the remaining
consumersintheencompassingconsumergroup.Inreducingthenumberofrecordsreturnedfrom
poll(),theapplicationwouldeffectivelyslackenitsprocessingobligationsbetweensuccessivepolls,
increasing the likelihood that a cycle would complete before the session.timeout.ms deadline
elapsed.
A second change was introduced in version 0.10.1.0 and is in effect to this day; the behaviour of
polling with respect to consumer liveness was radically altered as part of KIP-62. The changes
saw consumer heartbeating extracted from the poll() method into a separate background thread,
invokedautomaticallyatanintervalnotexceedingthatof heartbeat.interval.ms.Regularpolling
isstillarequisiteforprovingthataconsumerishealthy;however,thepolldeadlineisnowlocally
enforced on the consumer using the max.poll.interval.ms property as the upper bound. If the
applicationfailstopollwithinthisperiod,theclientwillsimplystopsendingheartbeats.Thischange
fixedtherootcauseoftheproblem—conflatingthecycletimewithheartbeating,resultingineither
compromisingonfailuredetectiontimeorfaultingaconsumerprematurely,inadvertentlycreating
ascenariowheretwoconsumersmightsimultaneouslyhandlethesamerecords.
Thechangetoheartbeatingmarkedlyimprovedthesituationwithrespecttotimeouts,butitdidnot
addressallissues.Thereisnoreliablewayfortheoutgoingconsumertodeterminethatitspartitions
wererevoked—notuntilthenextcalltopoll().Bythen,anyuncommittedrecordsmayhavebeen
replayed by the new consumer — resulting in the simultaneous processing of identical records by
twoconsumers—eachbelievingthatthey‘own’thepartitionsinquestion—ahighlyundesirable
scenarioinmoststreamprocessingapplications.TheuseofaConsumerRebalanceListenerdoesnot
helpinthisscenario,astherebalancecallbacksareonlyinvokedfromwithinacalltopoll(),using
theapplication’spollingthread–thesamethreadthatisoverwhelmedbytherecordbatch.
To be clear, the improvements introduced in Kafka 0.10.1.0 have not eliminated the need for
max.poll.records.Particularlywhentheaveragetime-costofprocessingarecordishigh,limiting
the number of in-flight records is still essential to a predictable, time-bounded poll loop. In the
absence of this limit, the number of returned records could still backlog the consumer, breaching
thedeadlinesetbymax.poll.interval.ms.Thecombinationofthemax.poll.recordsandthemore
recentmax.poll.interval.mssettingsshouldbeusedtoproperlymanageconsumerliveness.

Chapter10: ClientConfiguration 177
For a deeper understanding of how Kafka addresses the liveness and safety properties of the
consumerecosystem,consultChapter15:GroupMembershipandPartitionAssignment.
https://cwiki.apache.org/confluence/display/KAFKA/KIP-41%3A+KafkaConsumer+Max+Records
https://cwiki.apache.org/confluence/display/KAFKA/KIP-62%3A+Allow+consumer+to+send+heartbeats+from+a+
background+thread
Group ID
Thegroup.idpropertyuniquelyidentifiestheencompassingconsumergroup,andisintegraltothe
topic subscription mechanism used to distribute partitions among consumers. The KafkaConsumer
client will use the configured group ID when its subscribe() method is invoked. The ID can be
up to 255 characters in length, and can include the following characters: a-z, A-Z, 0-9, . (period),
_(underscore),and-(hyphen).Consumersoperatingunderaconsumergrouparefullygovernedby
Kafka;aspectssuchasbasicavailability,load-balancing,partitionexclusivity,andoffsetpersistence
aretakencareof.
This property does not have a default value. If unset, afree consumer is presumed. Free consumers
donotsubscribetoatopic;instead,theconsumingapplicationisresponsibleformanuallyassigning
a set of topic-partitions to the consumer, individually specifying the starting offset for each topic-
partitionpair.FreeconsumersdonotcommittheiroffsetstoKafka;itisuptotheapplicationtotrack
the progress of such consumers and persist their state as appropriate, using a data store of their
choosing.Theconceptsofautomaticpartitionassignment,rebalancing,offsetpersistence,partition
exclusivity, consumer heartbeating and failure detection (liveness, in other words), and other so-
called‘niceties’accordedtoconsumergroupsceasetoexistinthismode.
The use of the nominal expression ‘free consumer’ to denote a consumer without an
encompassing group is a coined term. It is not part of the standard Kafka nomenclature;
indeed,thereisnowidespreadterminologythatmarksthisformofconsumer.
Group instance ID
Thegroup.instance.idproperty specifiesa long-term, stableidentityfortheconsumerinstance—
allowing it to act as a static member of a group. This property is optional; if set, the group instance
IDisanon-empty,free-formstringthatmustbeuniquewithintheconsumergroup.
Static group membership is described in detail in Chapter 15: Group Membership and Partition
Assignment. The reader is urged to consult this chapter if contemplating the use of static group
membership,ormixingstaticanddynamicmembershipinthesameconsumergroup.
Asanoutline,staticmembershipisusedincombinationwithalargersession.timeout.msvalueto
avoidgrouprebalancescausedbytransientunavailabilities,suchasintermediatefailuresandprocess

178
| Chapter10: ClientConfiguration |     |     |     |     |     |     |
| ------------------------------ | --- | --- | --- | --- | --- | --- |
restarts. Static members join a group much like their dynamic counterparts and receive a share of
partitions. However, when a static member leaves, the group leader preserves the member’s parti-
tion assignments, irrespective of whether the departure was planned or unintended. The affected
partitions are simply parked; there is no reassignment, and, consequently, the partitions will begin
to accumulate lag. Upon its eventual return, the bounced member will resume the processing of its
partitionsfromitslastcommittedpoint.Staticmembershipaimstolessentheimpactofrebalancing
attheexpenseofindividualpartitionavailability.
| Heartbeat | interval, | session | timeout, | and the | maximum | poll |
| --------- | --------- | ------- | -------- | ------- | ------- | ---- |
interval
Theheartbeat.interval.ms,session.timeout.ms,andmax.poll.interval.mspropertiesareclosely
intertwined,collectivelycontrollingKafka’sfailuredetectionbehaviour.Thisbehaviouronlyapplies
toconsumersoperatingwithinagroup;freeconsumersarenotsubjecttohealthchecks.
The topic of failure detection and liveness of the consumer ecosystem is covered in Chapter 15:
GroupMembershipandPartitionAssignment.Thereaderwillbeadvisedthatthistopicrankshigh
on the ‘gotcha’ spectrum; so much so that the incorrect use of Kafka’s failuredetection capabilities
willjeopardisethecorrectnessofthesystem,leadingtostalledconsumersorstatecorruption.
Thefollowingisahighlycondensedsummaryofthesepropertiesandtheireffects.
| The                   |     | property | controls the frequency | with | which the     | client |
| --------------------- | --- | -------- | ---------------------- | ---- | ------------- | ------ |
| heartbeat.interval.ms |     |          |                        |      | KafkaConsumer |        |
willautomaticallysendheartbeatstothecoordinator,indicatingthatitsprocessisaliveandcanreach
thecluster.Onitsend,thegroupcoordinatorwillallowforuptothevalueof session.timeout.msto
receivetheheartbeat;failuretoreceiveaheartbeatwithinthesetdeadlinewillresultintheforceful
expulsionoftheconsumerfromthegroup,andthereassignmentoftheconsumer’spartitions.(This
istrueforbothstaticanddynamicconsumers.)
Themax.poll.interval.msstipulatesthemaximumdelaybetweensuccessiveinvocationsofpoll(),
enforced internally by the KafkaConsumer. For dynamic consumers, if the poll-process loop fails to
pollintime,theconsumerclientwillceasetosendheartbeatsandwillproactivelyleavethegroup—
promptlycausingarebalanceonthecoordinator.Forstaticconsumers,amisseddeadlinewillresult
inthequiescingofheartbeats,butnoleaverequestissent;itwillbeuptothecoordinatortoevicta
failedconsumerifthelatterfailstoreappearwithinthesession.timeout.msdeadline.
Thetablebelowliststhedefaultvaluesoftheseproperties.
| Property              |     |     |     | Defaultvalue     |     |     |
| --------------------- | --- | --- | --- | ---------------- | --- | --- |
| heartbeat.interval.ms |     |     |     | 3000(3seconds)   |     |     |
| session.timeout.ms    |     |     |     | 10000(10seconds) |     |     |
| max.poll.interval.ms  |     |     |     | 300000(5minutes) |     |     |
Whiletheeffectsofthesethreepropertiesareabundantlydocumentedandclear,theidiosyncrasiesof
the associated failure recovery apparatus and the implications of the numerous edge cases remain
a mystery to most Kafka practitioners. To avoid getting caught out, the reader is urged to study

Chapter10: ClientConfiguration 179
Chapter15:GroupMembershipandPartitionAssignment.
Auto offset reset
Theauto.offset.resetpropertystipulatesthebehaviouroftheconsumerwhennopriorcommitted
offsetsexistforthepartitionsthathavebeenassignedtoit,orifthespecifiedoffsetsareinvalid.
From the perspective of a consumer acting within an encompassing group, the absence of valid
offsetsmaybeobservedinthreescenarios:
1. When the group is initially formed and the lack of offsets is to be expected. This is the most
intuitiveanddistinguishablescenarioandislargelyself-explanatory.
2. When an offset for a particular partition has not been committed for a period of time that
exceedstheconfiguredretentionperiodofthe__consumer_offsetstopic,andwherethemost
recentoffsetrecordhassubsequentlybeentruncated.
3. Whenacommittedoffsetforapartitionexists,butthelocationitpointstoisnolongervalid.
To elaborate on the second scenario: in order to commit offsets, a consumer will send a message to
thegroupcoordinator,whichwillcachetheoffsetslocallyandalsopublishtheoffsetstoaninternal
topicnamed__consumer_offsets.Otherthanbeinganinternaltopic,thereisnothingspecialabout
__consumer_offsets — it behaves like any other topic in Kafka, meaning that it will eventually
start shedding old records. The offsets topic has its retention set to seven days by default. (This
is configurable via the offsets.retention.minutes broker property.) After this time elapses, the
recordsbecomeeligibleforcollection.
PriortoKafka2.0.0,thedefaultretentionperiodof__consumer_offsetswas24hours,which
confusinglydidnotalignwiththedefaultretentionperiodofsevendaysforallothertopics.
Thisusedtoroutinelycatchoutunsuspectingusers;keepingaconsumerofflineforadaywas
allittooktoloseyouroffsets.OnecouldwrapuptheirworkonaFriday,comebackonthe
followingMondayandoccasionallydiscoverthattheiroffsetshadbeenreset.Theconfusion
was exacerbated by the way topic retention works — lapsed records are not immediately
purged,theyonlybecomecandidatesfortruncation.Theactualtruncationhappenswhena
logsegmentfileisclosed,whichcannotbeeasilypredictedasitdependsontheamountof
datathatiswrittentothetopic.KIP-186²⁴addressedthisissueforrelease2.0.0.
Thethirdscenariooccursasaresultofroutinerecordtruncation,combinedwithaconditionwhere
atleastonepersistedoffsetreferstothetruncatedrange.Thismayhappenwhenthetopicinquestion
has shorter retention than the __consumer_offsets topic — as such, the committed offsets outlive
thedataresidingatthoseoffsets.
The offset reset consideration is not limited to consumer groups. A free consumer — one that is
operating without an encompassing consumer group — can experience an invalid offset during a
²⁴https://cwiki.apache.org/confluence/display/KAFKA/KIP-186%3A+Increase+offsets+retention+default+to+7+days

Chapter10: ClientConfiguration 180
call to Consumer.seek(), if the supplied offset lies outside of the range bounded by the low-water
andhigh-watermarks.
Whateverthereasonforthemissingorinvalidoffsets,theconsumerneedstoadequatelydealwith
thesituation.Theauto.offset.resetpropertyletstheconsumerselectfromoneofthreeoptions:
• earliest: Reset the consumer to the low-water mark of the topic — the offset of the first
retainedrecordforeachofthepartitionsassignedtotheconsumerwherenocommittedoffset
exists.
• latest:Resettheconsumertothehigh-watermark—theoffsetimmediatelyfollowingthatof
themostrecentlypublishedrecordforeachrelevantpartition.Thisisthedefaultoption.
• none:Donotattempttoresettheoffsetsifanyaremissing;instead,throwaNoOffsetForPartitionException.
Resetting the offset to latest, being the default setting, has the potential to cause havoc,
as it runs contrary to Kafka’s at-least-once processing tenet. If a consumer group were
to lose committed offsets following a period of downtime, the resulting reset would see
the consumers’ read positions ‘jump’ instantaneously to the high-water mark, skipping
over all records following the last committed point (inclusive of it). Any lag accumulated
by the consumer would suddenly disappear. The delivery characteristics for the skipped
recordswouldbereducedto‘atmostonce’.Whereconsumersrequestasubscriptionunder
aconsumergroup,itishighlyrecommendedthatthedefaultoffsetschemeissettoearliest,
therebymaintainingat-least-oncesemanticsforaslongastheconsumer’struelagdoesnot
exceedtheretentionofthesubscribedtopic(s).
Enable auto-commit and the auto-commit interval
There enable.auto.commit property controls whether automatic offset committing should be en-
abled for grouped consumers. The default setting is true, which activates the periodic background
committing of offsets. This process starts from the point when the application subscribes to one or
moretopicsbyinvokingoneoftheoverloadedsubscribe()methods.Whenenabled,theauto.commit.interval.ms
propertycontrolstheintervalofthebackgroundauto-committask,whichissetto5000(5seconds)
bydefault.Theauto-commitscopeencompassestheoffsetsoftherecordsreturnedduringthemost
recentcalltopoll().
The above narrative reflects the official Kafka documentation, but there is more to it. Taking the
above for gospel, the more cautious among us might spot a problem. Namely, if auto-commit
unconditionallycommitsoffsetseveryfiveseconds(orwhatevertheintervalhasbeensetto),what
happenstothein-flightrecordsthatareyettobeprocessed?Wouldtheybeinadvertentlycommitted,
andwouldn’tthatviolatetheat-least-onceprocessingsemantics?
Indeed,practitionersaremostlydividedintotwocamps:themajority,whohaveremainedoblivious
to this concern, and the remaining minority, who have expressed their discomfort with Kafka’s
defaultapproach.Therealanswerissomewhatparadoxical.Althoughitwouldappearthatthereis

Chapter10: ClientConfiguration 181
acriticalflawintheconsumer’sdesign,and,indeed,thedocumentationseemstosupportthistheory,
theimplementationcompensatesforthisinasubtleandsurreptitiousmanner.Whilstthedocumen-
tationstatesthatacommitwilloccurinthebackgroundatanintervalspecifiedbytheconfiguration,
the implementation relies on the application’s poll-process to initiate the commit from within the
poll() method, rather than tying an auto-commit action directly to the system time. Furthermore,
the auto-commit only occurs if the last auto-commit was longer than auto.commit.interval.ms
milliseconds ago. By committing from the processing thread, and provided record processing is
performed synchronously from the poll-process loop, the KafkaConsumer implementation will not
committheoffsetsofin-flightsrecords,standingbyitsat-least-onceprocessingvows.
While the above explanation might appear reassuring at first, consider the following: the
present behaviour is implementation-specific and unwarranted. It is not stated in the
documentation, nor in the Javadocs, nor in the KIPs. As such, there is no commitment,
implied or otherwise, on Kafka’s maintainers to honour this behaviour. Even a minor
releasecould,intheory,movetheauto-commitactionfromapoll-initiatedtoatimer-driven
model. If the reader is concerned at the prospect of this occurring, it may be prudent to
disable the offset auto-commit feature and to always commit the offsets manually using
Consumer.commitAsync().
Anotherimplicationoftheoffsetauto-commitfeatureisthatitextendsthewindowofuncommitted
offsetsbeyondthesetofin-flightrecords.Whilstthisisalsotrueof Consumer.commitAsync()toade-
gree,auto-commitwillfurthercompoundthedelay—uptothevalueof auto.commit.interval.ms.
Withthatinmind,asynchronousmanualcommittingispreferrediftheobjectiveistoreducetheper-
sistedoffsetlagwhilemaintainingadecentperformanceprofile.If,ontheotherhand,theobjective
is to curtail the persisted offset lag at any cost, the use of the synchronousConsumer.commitSync()
method would be most appropriate. The latter may be fitting when the average time-cost of
processingarecordishigh,andsothereplayingofrecordsishighlyundesirable.
Enabling offset auto-commit may have a minor performance benefit in some cases. If records
are ‘cheap’ to process (in other words, record handling is not resource-intensive), the poll-process
cycle will be short, and manual committing will occur frequently. This results in frequent commit
messages and increased bandwidth utilisation. By setting a minimum interval between commits,
thebandwidthefficiencyisimprovedattheexpenseofalongeruncommittedwindow.Ofcourse,a
similareffectmaybeachievedwithasimpleconditionalexpressionthatchecksthelastcommittime
and only commits if the offsets are stale. Having committed the offsets, it updates the last commit
time(asimplelocalvariable)forthenextgo-round.
Partition assignment strategy
Thepartition.assignment.strategypropertyspecifiesacomma-separatedlistoforg.apache.kafka.clients.consumer.ConsumerPartitionAssignor
implementations(intheorderofpreference)thatshouldbeusedtoorchestratepartitionassignment
amongmembersofaconsumergroup.Thedefaultvalueofthispropertyisorg.apache.kafka.clients.consumer.RangeAssignor,
whichassignscontiguouspartitionrangestothemembersofthegroup.

Chapter10: ClientConfiguration 182
A comprehensive discussion of this topic is presented in Chapter 15: Group Membership and Parti-
tion Assignment. In summary, partition assignment occurs on one of the members of the group —
thegroupleader.Inorderforassignmenttoproceed,membersmustagreeonacommonassignment
strategy — constrained by the assignors in the intersection of the (ordered) sets of assignors across
allmembers.
The reader would have picked up on such terms as ‘group leader’ and ‘group coordinator’
throughoutthecourseofthischapter.Theserefertodifferententities.Thegroupleaderisa
consumerclientthatisresponsibleforperformingpartitionassignment.Ontheotherhand,
thegroupcoordinatorisabrokerthatarbitratesgroupmembership.
Changing assignors can be tricky, as the group must always agree on at least one assignor. When
migrating from one assignor to another, start by specifying both assignors (in either order) in
partition.assignment.strategyandbouncingconsumersuntilallmembershavejoinedthegroup
with both assignors. Then perform the second round of bouncing, removing the outgoing assignor
from partition.assignment.strategy, leaving only the preferred assignor upon the conclusion of
theround.Whenmigratingfromthedefault‘range’assignor,makesureitisaddedexplicitlytothe
partition.assignment.strategylistpriortoperformingthefirstroundofbounces.
Transactions
Theisolation.levelpropertycontrolsthevisibilityofrecordswrittenwithinapendingtransaction
scope—wherebyatransactionhascommencedbutnotyetcompleted.Thedefaultisolationlevelis
read_uncommitted,whichhastheeffectofreturningall recordsfromConsumer.poll(),irrespective
ofwhethertheyformpartofatransaction,andifso,whetherthetransactionhasbeencommitted.
Conversely,theread_committedisolationmodewillreturnallnon-transactionalrecords,aswellas
those transactional records where the encompassing transaction has successfully been committed.
Because the read_committed isolation level conceals any pending records from poll(), it will also
conceal all following records, irrespective of whether they are part of a transaction — to maintain
strictrecordorderfromaconsumer’sperspective.
For a more in-depth discussion on Kafka transactions, the reader may consultChapter 18: Transac-
tions.
Admin client configuration
The admin client does not have any unique configuration properties of its own; the properties it
employsaresharedwiththeproducerandconsumerclients.
There is a difference in the construction of a KafkaAdminClient, compared to its KafkaProducer
and KafkaConsumer siblings. The latter are instantiated directly using a constructor, as we have
seen in the examples thus far. KafkaAdminClient does not expose a public constructor. Instead, the

183
| Chapter10: ClientConfiguration |     |     |     |     |     |     |     |     |
| ------------------------------ | --- | --- | --- | --- | --- | --- | --- | --- |
AdminClientabstractbaseclassoffersastaticfactorymethodforinstantiatingaKafkaAdminClient.
TheAdminClientisamorerecentadditiontotheKafkaclientfamily,appearinginversion0.11.0.0,
and seems to have taken a different stylistic route compared to its older siblings. (At the time of
writing,astaticfactorymethodyettoberetrofittedtotheProducerandConsumerinterfaces.)
TheAdminClientinterfaceisrapidlyevolving;everysignificantKafkareleasetypicallyaddsnewca-
pabilitiestotheadminAPI.TheAdminClientinterfaceismarkedwiththe@InterfaceStability.Evolving
annotation. Its presence means that the API is not guaranteed to maintain backward compatibility
acrossaminorrelease.FromtheJavadocs:
/**
* The administrative client for Kafka, which supports managing
| * and inspecting |     | topics, |     | brokers, | configurations |     |     | and ACLs. |
| ---------------- | --- | ------- | --- | -------- | -------------- | --- | --- | --------- |
*
| * ... omitted | for | brevity |     | ... |     |     |     |     |
| ------------- | --- | ------- | --- | --- | --- | --- | --- | --- |
*
| * This client | was | introduced |     | in     | 0.11.0.0 | and    | the API      | is still |
| ------------- | --- | ---------- | --- | ------ | -------- | ------ | ------------ | -------- |
| * evolving.   | We  | will try   | to  | evolve | the      | API in | a compatible |          |
* manner, but we reserve the right to make breaking changes in
| * minor releases, |     | if  | necessary. |     | We will | update | the |     |
| ----------------- | --- | --- | ---------- | --- | ------- | ------ | --- | --- |
* {@code InterfaceStability} annotation and this notice once the
| * API is | considered | stable. |     |     |     |     |     |     |
| -------- | ---------- | ------- | --- | --- | --- | --- | --- | --- |
*/
This chapter has taken the reader on a scenic tour of client configuration. There is a lot of it,
and almost every setting can materially impact the client. While the number of different settings
might appear overwhelming, there is a method to this madness: Kafka caters to varying event
processingscenarios,eachrequiringdifferentclientbehaviourandpotentiallysatisfyingcontrasting
non-functionaldemands.
Thorough knowledge of the configuration settings and their implications is essential for both the
effective and safe use of Kafka. This is where the official documentation fails its audience in many
ways — while the individual properties are documented, the implications of their use and their
various behavioural idiosyncrasies are often omitted, leaving the user to fend for themselves. The
intent of this chapter was to demystify these properties, giving the reader immense leverage from
priorresearchandanalysis;ideally,learningfromthemistakesofothers,asopposedtotheirown.

Chapter 11: Robust Configuration
Chapter 10: Client Configurationcovered all aspects of client configuration in detail. Among other
points,itwasmentionedthatwhenaclientisinstantiated,itverifiesthatthesuppliedconfiguration
is valid. In other words, it checks that the given keys correspond to valid configuration property
names that are supported in the context of that client type. Failing to meet this requirement will
resultinawarningmessagebeingemittedviatheconfiguredlogger,buttheclientwillstilllaunch.
Kafka’scavalierapproachtoconfigurationraisesthefollowingquestions:Howdoesonemakesure
thatthesuppliedconfigurationisvalidbeforelaunchingtheclientapplication?Ormustwewaitfor
theapplicationtobedeployedbeforebeingtoldthatwemuckedsomethingup?
Using constants
Themostcommonsourceofmisconfigurationisasimpletypo.Dependingonthenatureofthemis-
spelledconfigurationentry,theremaybeasubstantialpricetopayforgettingitwrong.Theexample
presentedinChapter10:ClientConfiguration,involvingmax.in.flight.requests.per.connection,
suggeststhatamistakemayincurthelossofrecordorderundercertaincircumstances.Relyingsolely
on inspecting log files does not inspire a great deal of confidence — the stakes are too high. There
mustbeawayofnailingthepropertynames;knowingup-frontwhatyougivetheclientwillactually
beused;andknowingearly,beforeanyharmisdone.
Kafkadoesnotyethaveasatisfactoryanswertothis,noristhereaKIPinthepipelinethataimsto
solvethis.Asaconcession,Kafkacanmeetyouhalfwaywithconstants:
Map<String, Object> config =
Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
1);
Inthelistingabove,propertynameswerereplacedwithstaticconstants.Theseconstantsarestable
and will not be changed between release versions. So you can be certain that property names have
not been misspelled if they were embedded in code. The last point is crucial, as properties may be
loadedfromanexternalsource,asitisoftenthecase.Constantswillnotguardagainstthis.

Chapter11: RobustConfiguration 185
Constants will also fail to protect the user in those scenarios where one might accidentally add a
constant from ConsumerConfig into a producer configuration, or vice versa. Admittedly, this is less
likelythanmisspellingakey,butitisstillsomethingtobewaryof.
Type-safe configuration
When bootstrapping Kafka client configuration from code or loading configuration artifacts from
anexternalsource,therecommendedapproachistocraftdedicatedJavaclasseswithstronglytyped
values, representing the complete set of configuration items that one can reasonably expect to be
supplied when their application is run. The external configuration documents can then be mapped
toanobjectformusingaJSONorYAMLparser.Itisalsopossibletouseaplain.propertiesfileif
the configuration structure is flat. Alternatively, if using an application framework such as Spring
BootorMicronaut,usethebuilt-inconfigurationmechanismwhichsupportsallthreeformats.
If it is necessary to support free-form configuration in addition to some number of expected
items, add a Map<String, Object> attribute to the configuration class. When staging the Kafka
configuration,applythefree-formpropertiesfirst,thenapplytheexpectedones.Whenapplyingthe
expected properties, check if the staging configuration already has a value set — if it does, throw
a runtime exception. While this doesn’t protect against misspelt property names in the free-form
section,itwillatleastensurethattheexpectedconfigurationtakesprecedence.
For those cases when there is an absolute requirement for the property names to be correct before
initialisation,onecantaketheirvalidationastepfurther:First,thejava.lang.reflectpackagecan
be used to scan the static constants in CommonClientConfigs, ProducerConfig or ConsumerConfig,
then have those constants cross-checked against the user-supplied property names. The validation
method will bail with a runtime exception if a given property name could not be resolved among
thescannedconstants.
Therestofthissectionwillfocusontheuncompromising,fullytype-safecase.Expectabitofcoding.
Allsamplecodeisprovidedatgithub.com/ekoutanov/effectivekafka²⁵,inthesrc/main/java/effectivekafka/typesafeproducer
directory.
There are two classes in this example. The first defines a self-validating structure for containing
producerclientconfiguration.Ithasroomforbothexpectedpropertiesandafree-formsetofcustom
properties.ThelistingforTypesafeProducerConfigfollows.
²⁵https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/typesafeproducer

186
| Chapter11: | RobustConfiguration |                                 |     |     |     |     |     |     |     |
| ---------- | ------------------- | ------------------------------- | --- | --- | --- | --- | --- | --- | --- |
| import     | static              | java.util.function.Predicate.*; |     |     |     |     |     |     |     |
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;
import org.apache.kafka.clients.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.config.*;
import org.apache.kafka.common.serialization.*;
| public | final   | class                               | TypesafeProducerConfig |       |                              | {   |     |             |     |
| ------ | ------- | ----------------------------------- | ---------------------- | ----- | ---------------------------- | --- | --- | ----------- | --- |
| public | static  |                                     | final                  | class | UnsupportedPropertyException |     |     |             |     |
|        | extends | RuntimeException                    |                        |       | {                            |     |     |             |     |
|        | private | static                              | final                  | long  | serialVersionUID             |     | =   | 1L;         |     |
|        | private | UnsupportedPropertyException(String |                        |       |                              |     | s)  | { super(s); | }   |
}
| public | static  |                                     | final | class | ConflictingPropertyException |     |     |             |     |
| ------ | ------- | ----------------------------------- | ----- | ----- | ---------------------------- | --- | --- | ----------- | --- |
|        | extends | RuntimeException                    |       |       | {                            |     |     |             |     |
|        | private | static                              | final | long  | serialVersionUID             |     | =   | 1L;         |     |
|        | private | ConflictingPropertyException(String |       |       |                              |     | s)  | { super(s); | }   |
}
| private |     | String  | bootstrapServers; |                |     |                       |     |     |     |
| ------- | --- | ------- | ----------------- | -------------- | --- | --------------------- | --- | --- | --- |
| private |     | Class<? | extends           | Serializer<?>> |     | keySerializerClass;   |     |     |     |
| private |     | Class<? | extends           | Serializer<?>> |     | valueSerializerClass; |     |     |     |
private final Map<String, Object> customEntries = new HashMap<>();
| public | TypesafeProducerConfig      |       |     |     |                   |     |     |     |     |
| ------ | --------------------------- | ----- | --- | --- | ----------------- | --- | --- | --- | --- |
|        | withBootstrapServers(String |       |     |     | bootstrapServers) |     |     | {   |     |
|        | this.bootstrapServers       |       |     | =   | bootstrapServers; |     |     |     |     |
|        | return                      | this; |     |     |                   |     |     |     |     |
}
| public | TypesafeProducerConfig  |     |         |                | withKeySerializerClass( |                     |     |     |     |
| ------ | ----------------------- | --- | ------- | -------------- | ----------------------- | ------------------- | --- | --- | --- |
|        | Class<?                 |     | extends | Serializer<?>> |                         | keySerializerClass) |     |     | {   |
|        | this.keySerializerClass |     |         |                | = keySerializerClass;   |                     |     |     |     |

187
Chapter11: RobustConfiguration
| return |     | this; |     |     |     |     |     |
| ------ | --- | ----- | --- | --- | --- | --- | --- |
}
| public                    | TypesafeProducerConfig |         |     | withValueSerializerClass( |                       |     |     |
| ------------------------- | ---------------------- | ------- | --- | ------------------------- | --------------------- | --- | --- |
|                           | Class<?                | extends |     | Serializer<?>>            | valueSerializerClass) |     | {   |
| this.valueSerializerClass |                        |         |     | = valueSerializerClass;   |                       |     |     |
| return                    |                        | this;   |     |                           |                       |     |     |
}
public TypesafeProducerConfig withCustomEntry(String propertyName,
|     |     |     |     |     |     | Object value) | {   |
| --- | --- | --- | --- | --- | --- | ------------- | --- |
Objects.requireNonNull(propertyName,
|                                 |     |       |     | "Property | name cannot | be null"); |     |
| ------------------------------- | --- | ----- | --- | --------- | ----------- | ---------- | --- |
| customEntries.put(propertyName, |     |       |     |           | value);     |            |     |
| return                          |     | this; |     |           |             |            |     |
}
| public | Map<String,                 |                   | Object> | mapify() | {               |            |     |
| ------ | --------------------------- | ----------------- | ------- | -------- | --------------- | ---------- | --- |
| final  | var                         | stagingConfig     |         | = new    | HashMap<String, | Object>(); |     |
| if     | (! customEntries.isEmpty()) |                   |         |          | {               |            |     |
|        | final                       | var supportedKeys |         | =        |                 |            |     |
scanClassesForPropertyNames(SecurityConfig.class,
SaslConfigs.class,
ProducerConfig.class);
|     | final | var unsupportedKey |     | =   | customEntries.keySet() |     |     |
| --- | ----- | ------------------ | --- | --- | ---------------------- | --- | --- |
.stream()
.filter(not(supportedKeys::contains))
.findAny();
|     | if (unsupportedKey.isPresent()) |              |                               |          | {                          |     |     |
| --- | ------------------------------- | ------------ | ----------------------------- | -------- | -------------------------- | --- | --- |
|     | throw                           | new          | UnsupportedPropertyException( |          |                            |     |     |
|     |                                 | "Unsupported |                               | property | " + unsupportedKey.get()); |     |     |
}
stagingConfig.putAll(customEntries);
}
Objects.requireNonNull(bootstrapServers,
|     |     |     |     | "Bootstrap | servers | not set"); |     |
| --- | --- | --- | --- | ---------- | ------- | ---------- | --- |
tryInsertEntry(stagingConfig,
ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
bootstrapServers);
Objects.requireNonNull(keySerializerClass,

188
Chapter11: RobustConfiguration
|     |     |     | "Key | serializer not | set"); |     |
| --- | --- | --- | ---- | -------------- | ------ | --- |
tryInsertEntry(stagingConfig,
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
keySerializerClass.getName());
Objects.requireNonNull(valueSerializerClass,
|     |     |     | "Value | serializer | not set"); |     |
| --- | --- | --- | ------ | ---------- | ---------- | --- |
tryInsertEntry(stagingConfig,
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
valueSerializerClass.getName());
| return | stagingConfig; |     |     |     |     |     |
| ------ | -------------- | --- | --- | --- | --- | --- |
}
private static void tryInsertEntry(Map<String, Object> staging,
|                      |                   |                                            |           | String           | key,        |     |
| -------------------- | ----------------- | ------------------------------------------ | --------- | ---------------- | ----------- | --- |
|                      |                   |                                            |           | Object           | value) {    |     |
| staging.compute(key, |                   |                                            | (__key,   | existingValue)   | -> {        |     |
|                      | if (existingValue |                                            | == null)  | {                |             |     |
|                      | return            | value;                                     |           |                  |             |     |
|                      | } else {          |                                            |           |                  |             |     |
|                      | throw             | new ConflictingPropertyException("Property |           |                  |             | "   |
|                      | +                 | key + "                                    | conflicts | with an expected | property"); |     |
}
});
}
| private | static                                  | Set<String> |     |     |          |     |
| ------- | --------------------------------------- | ----------- | --- | --- | -------- | --- |
|         | scanClassesForPropertyNames(Class<?>... |             |     |     | classes) | {   |
| return  | Arrays.stream(classes)                  |             |     |     |          |     |
.map(Class::getFields)
.flatMap(Arrays::stream)
.filter(TypesafeProducerConfig::isFieldConstant)
.filter(TypesafeProducerConfig::isFieldStringType)
.filter(not(TypesafeProducerConfig::isFieldDoc))
.map(TypesafeProducerConfig::retrieveField)
.collect(Collectors.toSet());
}
| private | static                                      | boolean | isFieldConstant(Field |     | field) | {   |
| ------- | ------------------------------------------- | ------- | --------------------- | --- | ------ | --- |
| return  | Modifier.isFinal(field.getModifiers())      |         |                       |     |        |     |
|         | && Modifier.isStatic(field.getModifiers()); |         |                       |     |        |     |
}

Chapter11: RobustConfiguration 189
private static boolean isFieldStringType(Field field) {
return field.getType().equals(String.class);
}
private static boolean isFieldDoc(Field field) {
return field.getName().endsWith("_DOC");
}
private static String retrieveField(Field field) {
try {
return (String) field.get(null);
} catch (IllegalArgumentException | IllegalAccessException e) {
throw new RuntimeException(e);
}
}
}
TheTypesafeProducerConfigclassdefinesapairofpublicnestedruntimeexceptions—UnsupportedPropertyException
and ConflictingPropertyException. The former will be thrown if the user provides a custom
propertywithanunsupportedname,trappingthosepeskytypos.Thelatteroccurswhenthecustom
property conflicts with an expected property. Both are conditions that we ideally would prefer to
avoidbeforeinitialisingtheproducerclient.
Next,wedeclareourprivateattributes.Thisexampleexpectsthreeproperties—bootstrapServers,
keySerializerClass, and valueSerializerClass. For the serializers, we have taken the extra step
of restricting their type to java.lang.Class<? extends Serializer<?>>, ensuring that only valid
serializerimplementationsmaybeassigned.ThecustomEntriesattributeisresponsibleforaccumu-
latinganyfree-formentries,suppliedinadditiontotheexpectedproperties.
Themapify()methodisresponsibleforconvertingthestoredvaluesintoaformsuitableforpassing
to a KafkaProducer constructor. It starts by checking if customEntries has at least one entry in
it. If so, it invokes the scanClassesForPropertyNames() method, passing it the class definitions
of ProducerConfig as well as some common security-related configuration classes. The result
will be a set of strings harvested from those classes using reflection. The ProducerConfig class
already imports the necessary constants from CommonClientConfigs, relieving us from having to
scanCommonClientConfigsexplicitly.
The actual implementation of scanClassesForPropertyNames() should hopefully be straightfor-
ward,requiringbasicknowledgeofJava8andthejava.util.streamAPI.Essentially,itenumerates
over the public fields, filtering those that happen to be constants (having final and static
modifiers), are of a java.lang.String type, and are not suffixed with the string _DOC. This set of
filtersshouldroundupallsupportedpropertynames.Thefilteredfieldsareretrievedandpackedinto
ajava.util.Set.Forconvenience,thecodelistingof scanClassesForPropertyNames()isrepeated
below.

190
| Chapter11:                              | RobustConfiguration    |            |     |
| --------------------------------------- | ---------------------- | ---------- | --- |
| private                                 | static Set<String>     |            |     |
| scanClassesForPropertyNames(Class<?>... |                        | classes) { |     |
| return                                  | Arrays.stream(classes) |            |     |
.map(Class::getFields)
.flatMap(Arrays::stream)
.filter(TypesafeProducerConfig::isFieldConstant)
.filter(TypesafeProducerConfig::isFieldStringType)
.filter(not(TypesafeProducerConfig::isFieldDoc))
.map(TypesafeProducerConfig::retrieveField)
.collect(Collectors.toSet());
}
The filter in scanClassesForPropertyNames() may also inadvertently include other string
constants in the given classes array that happen to match its predicates. Kafka’s main-
tainers haven’t consistently differentiated between supported property names and other
constants. The majority use the _CONFIG suffix to indicate a supported name; however,
ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION has strayed from this conven-
tion. Short of specifying an exclusion filter that blacklists known stray constants, there is
littlewecandoaboutthis.Ablacklistwouldcreatealong-termmaintenanceheadacheand
ishardlyworththeeffort.Thelikelihoodofamisspeltuser-suppliedpropertynamecolliding
withastrayconstantisnegligible.
Let’snowuseourTypesafeProducerConfigtoconfigureanactualclient:
| final var | config = new | TypesafeProducerConfig() |     |
| --------- | ------------ | ------------------------ | --- |
.withBootstrapServers("localhost:9092")
.withKeySerializerClass(StringSerializer.class)
.withValueSerializerClass(StringSerializer.class)
.withCustomEntry(
|          | ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, |                                       | 1); |
| -------- | ----------------------------------------------------- | ------------------------------------- | --- |
| try (var | producer =                                            | new KafkaProducer<>(config.mapify())) | {   |
| // do    | something with                                        | producer                              |     |
}
Theuseof TypesafeProducerConfigfollowsafluentstyleofmethodchaining;thecodeiscompact
butreadable.Butcrucially,itisnowbullet-proof.Youcanseeitforyourself:feedaninvalidproperty
nameintowithCustomEntry()andwatchconfig.mapify()bailwitharuntimeexception,avoiding
| theinitialisationof | KafkaProducer. |     |     |
| ------------------- | -------------- | --- | --- |
We can also populate a TypesafeProducerConfig object from a JSON or YAML configuration file
using a parser such as Jackson, available at github.com/FasterXML/jackson²⁶. Jackson supports a
²⁶https://github.com/FasterXML/jackson

Chapter11: RobustConfiguration 191
wide range of formats, including JSON, YAML, TOML, and even plain .properties files. To map
the parsed configuration document to a TypesafeProducerConfig instance, Jackson requires either
theadditionofJackson-specificannotationstoourclassordefiningacustomdeserializer.
Alternatively, if using an application framework such as Spring Boot or Micronaut, we can wire a
TypesafeProducerConfig object into the framework’s native configuration mechanism. This typi-
cally requires the addition of setter methods to make the encapsulated attributes writable by the
framework.Thedesignof TypesafeProducerConfigdefersallvalidationuntilthemapify()method
isinvoked,therebyremainingagnosticofhowitsattributesarepopulated.
The examples in this book are intentionally decoupled from application frameworks. The
intention is to demonstrate the correct and practical uses of Kafka, using the simplest and
mostsuccinctexamples.Theseexamplesareintendedtoberunasstandaloneapplications
usinganyIDE.
Youmighthavenoticedthattheexamplestillusedaconstanttospecifythecustompropertyname,
inspiteofhavingareliablemechanismfordetectingmisspeltnamesearly,beforeclientinitialisation.
Usingconstantstrapserrorsatcompile-time,whichistheholygrailofbuildingrobustsoftware.
WhiletheTypesafeProducerConfigexamplesolvesthevalidationprobleminitscurrentguise,the
presentedsolutionisnotveryreusable.Itwouldtakeafairamountofcopyingandpastingofcode
to apply this pattern to different configuration classes, even if the variations are minor. With a
modicumofrefactoring,wecanturntheunderlyingideasintoageneralisedmodelforconfiguration
management.
Ifyouareinterested,takealookatgithub.com/ekoutanov/effectivekafka.Thesrc/main/java/effectivekafka/config
directory contains an example of how this can be achieved. The AbstractClientConfig class
serves as an abstract base class for a user-defined configuration class. The base class contains the
customEntries attribute, as well as the validation logic for ensuring the correctness of property
names. The deriving class is responsible for providing the expected attributes and the related
validationlogic.Themapify()methodlivesinthebaseclass,andwillinvokethesubclasstoharvest
itsexpectedconfigurationproperties.
https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/config
ThischapteridentifiedthechallengeswithconfiguringKafkaclients—namely,Kafka’spermissive
stanceonvalidatingtheuser-suppliedconfigurationandtreatingconfigurationentriesasatypeless
mapofstring-basedkeystoarbitraryvalues.
The problem of validating configuration for the general case can be solved by creating an interme-
diateconfigurationclass.Thisclasshousestheexpectedconfigurationitemsastype-safeattributes,

Chapter11: RobustConfiguration 192
as well as free-form configuration, which is validated using reflection. A configuration class acts
as an intermediate placeholder for configuration entries, enabling the application to proactively
validate the client configuration before instantiating the client, in some cases using nothing more
thancompile-timetypesafety.

Chapter 12: Batching and
Compression
Chapter 10: Client Configuration mostly attended to the aspects of client configuration related to
functionality and safety, deliberately sidestepping any serious discussions on performance. The
intent of this chapter is to focus on one specific area of performance optimisation — batching and
compression. The two are related, collectively bearing a significant impact on the performance of
aneventstreamingsystem.
Comparing disk and network I/O
Kafka utilises a segmented, append-only log, largely limiting itself to sequential I/O for both reads
andwrites,whichisfastacrossawidevarietyofstoragemedia.Thereisawidemisconceptionthat
disks are slow; however, the performance of storage media (particularly rotating media) is greatly
dependent on access patterns. The performance of random I/O on a typical 7,200 RPM SATA disk
is between three and four orders of magnitude slower than sequential I/O. Furthermore, a modern
operatingsystemprovidesread-aheadandwrite-behindtechniquesthatprefetchdatainlargeblock
multiplesandgroupsmallerlogicalwritesintolargephysicalwrites.Becauseofthis,thedifference
between sequential I/O and random I/O is still evident in flash and other forms of solid-state non-
volatilemedia,althoughtheeffectsarelessdramaticcomparedtorotatingmedia.
Sequential I/O is comparable to the peak performance of network I/O. Furthermore, disk I/O is
local to the host, whereas network I/O is shared. In practice, this means that a well-designed log-
structuredpersistencelayerwillkeepupwiththenetworktraffic.Infact,oftenthebottleneckwith
Kafka’sperformanceisn’tthedisk,butthenetwork.Statedbluntly,thenetworkfabricwillrunout
ofsteambeforethebroker.
Producer record batching
To counteract the limitations of the network, Kafka clients will batch multiple records together
before sending them over the network. This is independent of, and in addition to, the low-level
batchingprovidedbytheOSattheTCPsocketlayer.Batchingofrecordsamortisestheoverheadof
thenetworkround-trip,usinglargerpacketsandimprovingbandwidthefficiency.
Batching in Kafka can act end-to-end. The producer client uses an accumulator buffer for staging
records prior to forwarding them to the leader broker. Once the records are batched, the broker
will(inmostcases)persistthemas-is,withoutunpackingthebatchorperforminganyintermediate

Chapter12: BatchingandCompression 194
manipulations of the stored records. This is carried through to the consumer. When polling for
records, the consumer is served batches of records by the broker — the same batches that were
originally published. There are cases where a batch is not end-to-end; for example, when a broker
isinstructedtoapplyacompressionschemethatdiffersfromtheproducer.Moreonthatlater.
Being a largely end-to-end concern, batching is controlled by the producer. The batch.size and
linger.mspropertiescollectivelylimittheextenttowhichtheproducerwillattempttobatchqueued
recordsinordertomaximisetheoutgoingtransmissionefficiency.Thedefaultvaluesof batch.size
andlinger.msare16384(numberofbytes)and0(milliseconds),respectively.
Theproducercombinesanyrecordsthatarrivebetweenrequesttransmissionsintoasinglebatched
request. Normally this only occurs under load, when records arrive faster than they can be trans-
mitted. In some circumstances, the client may want to reduce the number of requests even under
moderateload.Thelinger.mssettingaccomplishesthisbyaddingasmallamountofartificialdelay
— rather than immediately sending a record the moment it is enqueued, the producer will wait for
up to a set delay to allow other records to accumulate in a batch, maximising the amount of data
thatcanbetransmittedinonego.Althoughrecordsmaybeallowedtolingerforuptotheduration
specifiedinlinger.ms,thebatch.sizepropertywillhaveanoverridingeffect,dispatchingthebatch
onceitreachesthesetmaximumsize.
While the linger.ms property only comes into the picture when the producer is lightly loaded, the
batch.size property is continually in effect, acting as the overarching and unremitting limiter of
thestagedbatch.
Thelinger.mssettingisoftenlikenedtotheNagle’sAlgorithminTCP,astheybothaimtoimprove
theefficiencyofnetworktransmissionbycombiningsmallandintermittentoutgoingmessagesand
sending them at once. The comparison was originally made by the Apache Kafka design team. It
hassincefounditswayintotheofficialdocumentationandappearstoreverberatestronglywithin
theusercommunity.
While the similarities are superficial, there are two crucial differences. Firstly, Nagle’s Algorithm
doesnotindiscriminatelybufferdataonthebasisofitsapparentintermittence.Itonlytakeseffect
whenthereisatleastoneoutstandingpacketthatisyettobeacknowledgedbythereceiver.Secondly,
Nagle’sAlgorithmdoesnotimposeanartificialtimelimitonthedelay.Datawillbebuffereduntil
afullpacketisformed,ortheACKforthepreviouspacketisreceived,whicheveroccursfirst.
Combined,thetwoinstrumentsmakethealgorithmself-regulating;ratherthanrelyingonarbitrary,
user-definedlingertimes,thealgorithmbuffersdatabasedonthenetwork’sobservedperformance.
Free-flowing networks with frequent ACKs result in lighter buffering and reduced transmission
latency.Ascongestionincreases,thebufferingbecomesmorepronounced,improvingtransmission
efficiencyattheexpenseoflatency,andhelpsavoidacongestivecollapse.

Chapter12: BatchingandCompression 195
Congestive collapse is a phenomenon that occurs when a network is overwhelmed by a
high packet rate, usually at known choke points, leading to increased failures (packet loss
andtimeouts)andacorrespondingescalationofretries.Thiscreatesaperpetuatingfeedback
loopthatdegeneratestoaparasiticstablestatewherethetrafficdemandishigh,butthere
islittleusefulthroughputavailable.
ThebatchingalgorithmusedbyKafkaiscrudebycomparison.Itrequirescarefultuningandimposes
afixedpenaltyonintermittentpublishingpatternsregardlessofthenetwork’sperformanceorthe
observed latency. This does not necessarily render it ineffective. On the contrary, the batching
algorithmcanbeveryeffectiveoverhigh-latencynetworks.Itsmainissueisthelackofadaptability,
makingitsuboptimalindynamicnetworkclimatesorinthefaceofvaryingclusterperformance—
bothfactorsaffectingpublishinglatency.Kafkaplacestheonusoftuningthealgorithmparameters
ontheuser,requiringcarefulandextensiveexperimentationtoempiricallyarriveattheoptimalset
of parameters for a given network and cluster profile. These parameters must also be periodically
revisedtoensuretheircontinuedviability.
Even with the default linger.ms value of 0, the producer will still allow for some buffering due to
theasynchronousnatureofthetransmission:callingsend()serializestherecord,assignsapartition
number,andplacestheserializedcontentsintotheaccumulatorbuffer,butdoesnottransmitit.The
actualcommunicationsarehandledbyabackgroundI/Othread.Soif send()iscalledmultipletimes
inrapidsuccession,atleastsomeoftheserecordswilllikelybebatched.
Due to the diminishing returns exhibited with larger batch sizes and the lack of self-regulation in
Kafka’s batching algorithm, it may be prudent to err on the side of smaller values of linger.ms
initially—fromzerotoseveralmilliseconds.Thenon-functionalrequirementsoftheoverallsystem
shouldalsospecifythetoleranceforlatency,whichoughttobehonouredoveranygainsinnetwork
efficiency or storage savings on the brokers. The extent of batching may be increased further if the
networkisbecomingagenuinebottleneck;however,suchchangesshouldbetemporary—thefocus
shouldbeonaddressingtherootcause.
Compression
Theeffectivenessofbatchingincreasessubstantiallywhencomplementedbyrecordcompression.As
compression operates on an entire batch, the resulting compression ratios increase with the batch
size.Theeffectsofcompressionareparticularlypronouncedwhenusingtext-basedencodingssuch
asJSON,wheretherecordsexhibitlowinformationentropy.ForJSON,compressionratiosranging
from5xto7xarenotunusual,whichmakesenablingcompressionano-brainer.Furthermore,record
batchingandcompressionarelargelydoneasaclient-sideoperation,whichtransferstheloadonto
theclientandhasapositiveeffectnotonlyonthenetworkbandwidth,butalsoonthebrokers’disk
I/Oandstorageutilisation.

Chapter12: BatchingandCompression 196
Thebiggestgainswillbefeltwhenstartingfromsmallbatches.Asthebatchsizeincreases,thelaws
ofdiminishingreturnstakeeffect—anincreaseinthebatchsizewillyieldaproportionallysmaller
gainincompressionratio.Theincrementalreductioninthenumberofpacketsused,andhencethe
transmissionefficiency,willalsobelessnoticeablewithhigherbatchsizes.
The compression.type client configuration property controls the algorithm that the producer will
usetocompressrecordbatchesbeforeforwardingthemontothepartitionleaders.Thevalidvalues
are:
• none:Compressionisdisabled.Thisisthedefaultsetting.
• gzip: Use the GNU Gzip algorithm — released in 1992 as a free substitute for the proprietary
compressprogramusedbyearlyUNIXsystems.
• snappy: Use Google’s Snappy compression format — optimised for throughput at the expense
ofcompressionratios.
• lz4: Use the LZ4 algorithm — also optimised for throughput, most notably for the speed of
decompression.
• zstd: Use Facebook’s ZStandard — a newer algorithm introduced in Kafka 2.1.0, intended to
achieveaneffectivebalancebetweenthroughputandcompressionratios.
Kafka compression applies to an entire record batch, which as we know can be end-to-end —
depending on the settings on the broker. Specifically, when the broker is configured to accept the
producer’s preferred compression scheme, it will not interfere with the contents of the batch. The
batch flows from the producer to the broker, is persisted across multiple replicas, and is eventually
served to one or more consumers — all as a single, indivisible chunk. The broker simply acts as a
relaying party — it does not decompress and re-compress the record as part of its role. Each chunk
hasaheaderthatnotesthealgorithmthatwasusedduringcompression,allowingtheconsumersto
applythesamewhenunpackingthechunk.
The compression.type broker property applies to all topics by default, overriding the producer
property. In addition, it is possible to configure individual topics by using the kafka-configs.sh
CLItospecifyadynamicconfigurationforthetopicsentitytype.
On the flip side, end-to-end compression has a subtle drawback, which can catch out unsuspecting
users.Becausethebrokerisunabletomediatetheinterchangeformat,theresultisalatentcoupling
between the producer’s capabilities and that of the consumer clients. Although Kafka strives to
maintainbinaryprotocolcompatibilitybetweenminorreleases,thispromisedoesnotcoverend-to-
endcontracts,suchascompressionandchecksumming.Assuch,theproducerapplicationmustuse
acompressionformatthisiscompatiblewiththeoldestconsumerversion.

Chapter12: BatchingandCompression 197
The introduction of ZStandard is a good example of a breaking change, and may be
considered as a ‘gotcha’ depending on your client ecosystem. When operating a mixture
2.1.0+andpre-2.1.0consumers,theuseofcompression.type=zstdonaproducerwillrender
therecordsunreadablefortheolderclients,resultinginanUNSUPPORTED_COMPRESSION_TYPE
error. The correct way to enable ZStandard is to upgrade all consumers first, and only
whenthelastpre-2.1.0consumerhasbeenretired,allowtheuseof compression.type=zstd.
Alternatively,onecanenablere-compressiononthebrokertomaintaincompatibilitywith
olderclients;however,thisleadstoincreasedresourceutilisationonthebroker.
End-to-endcompressionhasaprofoundlypositiveimpactonperformance.Compressionisaprocessor-
intensiveoperationandconsumesadditionalmemory,particularlyduringtheencodingphase.(Com-
paratively,decodingacompressedstreamischeaper;thedifferencemaybeanorderofmagnitudein
extremecases.)Byeliminatingthebrokerfromtheequation,thecostofcompressionisabsorbedby
theclients. The distribution of load to the periphery dovetailsinto Kafka’sbroader scaling strategy
—itistypicallymucheasiertoscaletheclientsthanthebroker,atleastforwell-architected,stateless
applications.
Kafka additionally offers compression at the broker level, for those scenarios where it is necessary
to change the compression scheme. This is controlled by the compression.type broker property.
Its default value is producer, meaning that the broker will revert to the producer-assigned com-
pression scheme; in other words, the broker will not meddle the batch. Alternatively, the value of
compression.type may be set to one of the supported compression schemes (as per the producer-
side property). The only difference is when disabling compression: the producer property accepts
none,whereasthebrokerpropertyacceptsuncompressed.
While broker-level configuration can provide for more fine-grained control of the compression
scheme, its main drawback is the increased CPU utilisation and the forfeiting of the zero-copy
optimisation,asthebatchesarenolongerend-to-end.
Zero-copy describes computer operations in which the CPU does not perform the task of
copying data from one memory area to another. In a typical I/O scenario, the transfer of
datafromanetworksockettoastoragedeviceoccurswithouttheinvolvementoftheCPU
andwithareducednumberofcontextswitchesbetweenthekernelandusermode.
The use of compression has no functional effect on the system. Its effect on bandwidth utilisation,
diskspaceutilisation,andbrokerI/Oare,insomecases,astounding.Asaperformanceoptimisation,
anddependingonthetypeofdatatransmitted,theeffectofcompressioncanbesoprofoundthatit
thwartsanypotentialphilosophicaldebatesovertheviabilityof‘premature’optimisation.
Compression algorithms achieve a reduction in the encoded size relative to the uncompressed
original by replacing repeated occurrences of data with references to a single copy of that data
existing earlier in the uncompressed data stream. For compression to be effective, the data must
contain a large amount of repetition and be of sufficient size so as to warrant any structural

Chapter12: BatchingandCompression 198
overheads, such as the introduction of a dictionary. Text formats such as JSON tend to be highly
compressible as they are verbose by nature and contain repeated character sequences that carry
no informational content. They also fail to take advantage of the full eight bits that each byte
cantheoreticallyaccommodate,insteadrepresentingcharacterswithsevenofthelowerorderbits.
Binaryencodingstendtoexhibitmoreinformationaldensity,butmaystillbehighlycompressible,
depending on their internal structure. Binary streams containing digital media — for example,
images,audioorvideo—arehigh-entropysourcesandarevirtuallyincompressible.
Informationentropyisthemeasureoftheinformationalcontentconveyedbyanelementin
astream.Itisdeterminedbythelikelihoodofpredictingthevalueofanelementinastream
basedontheobservationsofpriorelements,withtheresultingscorevaryingbetweenzero
(no entropy, perfectly predictable) and one (highest entropy, completely unpredictable). In
thecontextofadatarecord, anelementmightbeanindividualbit orabyteinthe record.
Theeasieritistopredictthevalueofthenextbyte,thelessnewinformationitcarries.Asan
exampleofalowentropysource,consideraformattedJSONdocument.Havingobserveda
newlinecharacter,itisextremelylikelythatthenextelementisawhitespacecharacter,with
thenextlikelycandidatebeingadoublequote,followedinrelativelikelihoodbyaclosing
brace.Comparethistoatrulyrandomlysequenceofbytes.Thelikelihoodofpredictingthe
next byte is equivalent to chance; this is an example of maximum entropy. Compressing
this type of data will only lead to an increase in the output size as the overheads of the
compressionalgorithmwillbeaddedintotheoutput,notoffsetbyanygainsinentropy.
Without delving into a harrowing analysis of the different compression schemes, the recommen-
dation is to always enable compression for text encodings as well as binary data (unless the latter
is known to contain a high information entropy payload). In some cases, the baseline impact of
enabling compression may be so substantial that the choice of the compression algorithm hardly
matters. In othercases, the choiceof the algorithmmay materially impactthe overallperformance,
andamorecarefulselectioniswarranted.
Asaruleofthumb,useLZ4whendealingwithlegacyconsumers,transmittingoveranetworkthat
offers capacity in excess of your peak uncompressed data needs. In other words, the network is
abletosustainyourtrafficflowevenwithoutcompression.Ifthenetworkhasbeenidentifiedasthe
bottleneck,considerswitchingtoGzipandalsoincreasingbatch.sizeandlinger.mstoincreasethe
sizeoftransmittedbatchestomaximisetheeffectivenessofcompressionattheexpenseof latency.
If all consumers are at a version equal to or greater than 2.1.0, your choices are basically LZ4 and
ZStandard. Use LZ4 for low-overhead compression. When the network becomes the bottleneck,
consider ZStandard, as it is able to achieve similar compression ratios to Gzip at a fraction of the
compressionanddecompressiontime.Youmayalsoneedtoincreasebatch.sizeandlinger.msto
maximisecompressioneffectiveness.
The guidelines above should not be taken to mean that LZ4 always outperforms Snappy or that

Chapter12: BatchingandCompression 199
ZStandardisshouldalwaysbepreferredoverGzip.Whenundertakingseriousperformancetuning,
you should carefully consider the shape of your data and conduct studies using synthetic records
that are representative of the real thing, or better still, using historical data if this is an option.
When benchmarking the different compression schemes, you should also measure the CPU and
memoryutilisationoftheproducerandconsumerclients,comparingthesetothebaselinecase(when
compression is disabled). In some cases you may find that Snappy or Gzip indeed offer a better
compromise. The guidelines presented here should be used as the starting point, particularly when
one’s copious free time is prioritised towards dealing with the matters of building software, over
conductinglarge-scaleperformancetrials.
This chapter has given the reader an insight into Kafka’s performance ‘secret sauce’ — namely,
the use of log-structured persistence to limit access patterns to sequential reads and writes. The
implication: a blazingly fast disk I/O subsystem that can outperform the network fabric, requiring
furtherclient-sideoptimisationstobringthetwointoparity.
Welookedattwocontrolsavailableontheproducerclient—batchingandcompression.Thepoten-
tial performance impacts of these controls are significant, particularly in the areas of throughput
andlatency.Gettingthemrightcouldentailsignificantgainswithrelativelylittleeffort.

Chapter 13: Replication and
Acknowledgements
Fundamentally,ApacheKafkaisadistributedlog-centricdatastore.Dataiswrittenacrossmultiple
nodes in a cluster and may be subject to a range of contingencies — disk failures, intermittent
timeouts, process crashes, and network partitions. HowKafka behaves in the face of a contingency
and the effect this has on the published data should be of material concern to the designer of an
event-drivensystem.
ThischapterexploresoneofthemorenuancedfeaturesofKafka—itsreplicationprotocol.
Replication basics
ThedeliberatedecisionsmadeduringthedesignofKafkaensurethatdatawrittentotheclusterwill
be both durable and available — meaning that it will survive failures of broker nodes and will be
accessibletoclients.Thereplicationprotocolisthespecificmechanismbywhichthisisachieved.
As it was stated in Chapter 3: Architecture and Core Concepts, the fundamental unit of streaming
inKafkaisapartition.Forallintentsandpurposes,apartitionisareplicatedlog.Thebasicpremise
of a replicated log is straightforward: data is written to multiple replicas so that the failure of one
replica does not entail the loss of data. Furthermore, replicas must agree among themselves with
respecttothecontentsofthereplicatedlog—reflectingontheirlocalfacsimileofthelog.Itwould
beunacceptablefortwo(ormorereplicas)todifferintheircontentsinsomeconflictingmanner,as
this would lead to data corruption. Broadly speaking, this notional agreement among the replicas
is referred to as distributed consensus, and is one of the basic challenges faced by the designers of
distributedsystems.
There are several approaches to implementing a distributed log; the one taken by Kafka follows
a leader-follower model — a single leader is assigned by the cluster coordinator to take absolute
mastership of the partition, with zero or more followers that tail the data written by the leader
in near real-time, progressively building their own identical copies of the log. Putting it another
way, replication in Kafka is asynchronous: replicas lag behind the leader, converging on its state
whenthetrafficflowfromtheleaderquiesces.Consensusisformedbyensuringthatonlyoneparty
administers changes to the log; all other parties implicitly agree by unconditionally replicating all
changes from the leader, achieving sequential consistency. Under this consistency model, replicas
can only vary in a contiguous segment comprising the last few records in the log — they cannot
have gaps, nor can two replicas house different records at the same position in the log. This model
vastlysimplifiestheconsensusprotocol,butsomelevelofagreementisrequirednonetheless,because

Chapter13: ReplicationandAcknowledgements 201
sequentialconsistencyisinsufficientonitsown.Theprotocolmustensurethatrecordsaredurably
persisted,whichimpliesthatcertainaspectsoftheprotocolmustbesynchronous.
In Kafka’s parlance, both the leader and the follower roles are collectively referred to as replicas.
Thenumberofreplicasisconfiguredatthetopiclevel,andisknownasthetopic’sreplicationfactor.
During the exercises in Chapter 5: Getting Started, we created topics with a replication factor of
one.Therewasnootherchoicethen,asourtestclustercomprisedasinglebrokernode.Inpractice,
production clusters will comprise multiple nodes — three is often the minimum, although larger
clustersarecommon.
The minimum permitted replication factor is one — offering no redundancy. Increasing the replica-
tionfactortotwoprovidesforasinglefollowerreplica,butwillpreventfurthermodificationstothe
data if one of the replicas fails. This configuration provides durability, but as for availability — this
is only provided in the read aspect, as writes are not highly available. A replication factor of three
providesbothreadandwriteavailability,providingcertainotherconditionsaremet.Naturally,the
replicationfactorcannotexceedthesizeofthecluster.
Anaivereplicationmodelwithareplicationfactorofthreeisdepictedbelow.Thisisasimplification
ofwhatactuallyhappensinKafka,butitisnonethelessusefulinvisualisingtherelationshipbetween
leaderandfollowerreplicas.Itwillbefollowedshortlywithamorecompletemodel.

Chapter13: ReplicationandAcknowledgements 202
Topicreplication—simplifiedmodel
Brokernodesarelargelyidenticalineveryway;eachnodecompetesforthemastershipofpartition
data on equal footing with its peers. Given the symmetric nature of the cluster, Kafka designates
a single node – the cluster controller – for managing the partition assignments within the cluster.
Partition leadership is apportioned approximately evenly among the brokers in a cluster when a
topic is first created. Kafka allows the cluster to scale to a greater number of topics and partitions
by adding more broker nodes; however, changes to the cluster size require explicit rebalancing of
replicasontheoperator’sbehalf.Moreonthatlater.
The main challenge with naively replicating data from a leader to a follower in the manner above,
is that in order to guarantee durability, the leader must wait for the all replicas to acknowledge
the write — before reporting to the producer that the write has been replicated to the degree
impliedbythereplicationfactor.Thismakesthereplicationprotocolsensitivetoslowreplicas,asthe
acknowledgementtimesaredependentontheslowestreplicas.Asinglereplicathatisexperiencing
a period of degraded performance will affect all durable writes to the partition in question. Also,
waitingforallreplicasisnotstrictlynecessarytoachievingconsensusinareplicatedlog.

Chapter13: ReplicationandAcknowledgements 203
Note,weusetheterm‘durablewrite’torefertothepersistenceofthoserecordswherethe
producerhasrequestedthehighestlevelofdurability.Thisisnotnecessarilythecasewithall
writes;theproducercandictatethelevelofdurabilitybyspecifyingthenumberofrequired
acknowledgements.
Tolightentheburdenofslowreplicas,KafkaintroducestheconceptofIn-SyncReplicas,abbreviated
toISR.Thisisadynamicallyallocatedsetofreplicasthatcandemonstrablykeeppacewiththeleader.
TheleaderisalsoincludedintheISR.Normally,thisimpliesthatareplicaistrailingtheleaderwithin
someboundedwindowoftime.(Thetimewindowbeingthedifferencebetweenthetimestampofthe
recordattheleader’slogendoffsetandthatofthefollower.)Slowreplicasareautomaticallyremoved
fromtheISRset,andassuch,thecardinalityoftheISRsetmaybelowerthanthereplicationfactor.
ApartitionthathashadatleastonereplicaremovedfromtheISRissaidtobeunder-replicated.The
responsibility of evaluating the performance of the ISR and maintaining the state of the ISR falls
on the partition leader, which must also persist a copy of the ISR to ZooKeeper upon every change.
This ensures that, should the leader fail, any of the followers can reconstruct the state of the ISR —
enablingthemtotakeovertheleadershipofthetopic.
Insteadofrequiringtheleadertogarneracknowledgementsfromallfollowerreplicasasintheearlier
example, a durable write only requires that acknowledgements are received from those replicas in
theISR.ThelowerboundonthesizeoftheISRisspecifiedbythemin.insync.replicasconfigura-
tion property on the broker. This property is complementary to the default.replication.factor
property, being the defaults that will be applied to all topics. When creating a topic, its custodian
maysetanalternatereplicationfactorandoverridethemin.insync.replicaspropertyasrequired.
The producer client has no say in the replication factor or the minimum size of the in-sync replica
set—itcanonlystipulatewhetherawriteshouldbedurableornot.Naturally,theconsumerclient
hasnosayinanymatterregardingdurability.
Whenapartitionleaderisassertingadurabilityguarantee,itmustensurethatallreplicasintheISR
haveacknowledgedthewrite,beforerespondingtotheproducer.BecausetheISRautomaticallyex-
cludesunderperformingreplicas,theperformanceofthereplicationprotocolisminimallyimpacted
by a stalled or lagging replica. To be specific, a deteriorated replica will still affect the replication
performanceforaslongasitisdeemedamemberoftheISR,buteventuallyitwillberemovedfrom
the ISR — at that point and thereafter it will have no impact on performance.Prior to rejoiningthe
ISR, a replica must catch up to the leader. A replication scheme based on ISR has been depicted
below.

Chapter13: ReplicationandAcknowledgements 204
Topicreplication—modelwithISR
Inthediagramabove,thetopichasbeenconfiguredwithareplicationfactorofthreeandaminimum
ISRsizeof2.ForthosepartitionswheretherearethreereplicasintheISR—aconfirmationfromboth
followersisnecessarybeforeadurablewritecanbeacknowledgedbytheleader.Forthosepartitions
wheretheISRhasbeenreducedtotwo,theleaderwillwaitfortheremainingin-syncreplicaother
thanitself,beforeacknowledgingthewrite.Finally,whentheISRiscompletelydepleted,theleader
willcommunicateanerrorbacktotheinitiatingpublisher,whichinturn,mayreattempttopublish
therecord.
Comparingthismodeltootherdistributedconsensusprotocols,suchasZAB,Raft,andPaxos:one
nolongerrequiresmajorityagreementamongthecohortsbeforeacceptingalogwriteandthereby
deemingitstable.ProvidedthatthereisconsensusonthemembershipoftheISR,andallmembers
of the ISR are synchronised with the leader, then the ISR does not need to constitute the majority
ofthereplicas.Conceivably,wecouldhave100replicas,withonlytwointheISR,andstillachieve
consensus.
Atfirstglance,itmayappearthatKafka’sreplicationprotocolhasaccomplishedafeatthateluded
distributedsystemsresearchesforseveraldecades.Thisisnotthecase.Thetrickisintheconsensus
on the ISR membership state, which is backed by ZooKeeper. ZooKeeper’s ZAB protocol provides
the underlying primitives for Kafka to build upon. So although Kafka does not require a majority
voteforlogreplication,itdoesrequireamajorityvoteformakingupdatestotheISRstate.Putting

Chapter13: ReplicationandAcknowledgements 205
itanotherway,KafkalayersitsreplicationprotocolontopofZAB,formingahybridofthetwo.
Thedefaultvalueofthemin.insync.replicaspropertyis1,whichimpliesthatadurablewriteonly
extendstotheleaderreplica,whichishardlydurable.Atminimum,min.insync.replicasshouldbe
setto2,ensuringthatawriteisreflectedontheleaderaswellasatleastonefollower.Thisproperty
can be set for all topics, as well as for individual topics. Instructions for targetting specific topics
havebeencoveredinChapter9:BrokerConfiguration.
The maximum tolerable replication lag is configured via the replica.lag.time.max.ms broker
property,whichdefaultsto10000(tenseconds).Ifafollowerhasn’tsentanyfetchrequestsorhasn’t
consumed up to the leader’s log end offset within this time frame, it will be summarily dismissed
fromtheISR.Thissettingcanbeappliedtoalltopics,orselectivelytoindividualtopics.
Prior to Kafka 0.8.3.0, the maximum tolerable replication lag was configured via the
replica.lag.max.messagesproperty.Thereplicationprotocolusedtoconsiderthenumber
of records that a follower was trailing by, to determine whether it should be in the ISR.
Replicas could easily be knocked out of the ISR during sudden bursts of traffic, only to
rejoin shortly afterwards. Conversely, low-volume topics would take a long time to detect
anout-of-syncreplica.AspartofKIP-16²⁷,theprotocolhassinceevolvedtoonlyconsider
thetimedelaybetweenthelatestrecordontheleaderandthatofeachfollower.Thismade
iteasiertotunetheprotocol,asitwaslesssusceptibletoflutterduringtrafficbursts.Italso
madeiteasiertosetmeaningfulvalues,asitismorenaturaltothinkoflagintermsoftime,
ratherthanintermsofarbitraryrecords.
Leader election
Only members of the ISR are eligible for leader election. Recall, Kafka’s replication protocol is
generally asynchronous and a replica in the ISR is not guaranteed to have all records that were
written by the outgoing leader, only those records that were confirmed by the leader as having
beendurablypersisted.Inotherwords,theprotocolissynchronousonlywithrespecttothedurable
writes, but not necessarily all writes. It is conceivable then, that some in-sync replicas will have
more records than others, while preserving sequential consistency with the leader. When selecting
the new leader, Kafka will favour the follower with the highest log end offset, recovering as much
oftheunacknowledgeddataaspossible.
Kafka’sguaranteewithrespecttodurabilityispredicatedonatleastonefully-synchronisedreplica
remainingintact.Remember,anin-syncreplicawillcontainalldurablewrites;themin.insync.replicas
states the minimum number of replicas that will be fully-synchronised at any given time. When
²⁷https://cwiki.apache.org/confluence/display/KAFKA/KIP-16+-+Automated+Replica+Lag+Tuning

Chapter13: ReplicationandAcknowledgements 206
suitablysizedandappropriatelyconfigured,aclustershouldtoleratethefailureofaboundednumber
ofreplicas.Butwhathappenswhenwe’vegoneoverthatnumber?
Atthispoint,Kafkaessentiallyprovidestwooptions:eitherwaituntilanin-syncreplicaisrestoredor
perform unclean leader election. The latter is enabled by settingunclean.leader.election.enable
to true (it is false by default). Unclean leader election allows replicas that were not in the ISR at
thetimeoffailuretotakeoverpartitionleadership,tradingconsistencyforavailability.
Tomaintainconsistencyinthefaceofmultiplereplicafailures,oneshouldsetthereplicationfactor
higher than the minimum recommended value of three, and boost themin.insync.replicas value
accordingly. This increases the likelihood of a surviving replica being fully-synchronised with the
leader. One could further boost min.insync.replicas to equate to the replication factor, thereby
ensuring that every replica is fit to act as a leader. The downside of this approach is the loss of
availability in the write aspect: should a replica fail, consistency will be preserved, but no further
writes to the partition will be allowed for having insufficient replicas in the ISR set. It is also less
performant, negating the main purpose of an ISR — to reduce the performance impact of slow
replicas.
Tobeclear,theconsistency-availabilitytradeoffsalludedtoabovearenotuniquetoKafka’s
replicationprotocol,affectingeverydistributedconsensusprotocol.Givenaconstantnum-
berofreplicas,onecanincreasethenumberofreplicasthatmustagreeonawrite,thereby
increasingthelikelihoodthatatleastonecompletereplicasurvives—butindoingso,impede
the system’s ability to make progress with a reduced replica set. Conversely, reducing the
number of voting replicas will allow the system to tolerate a greater number of replica
failures,butincreasesthelikelihoodoflosingallcompletereplicas—leavingjustthepartial
ones, or none at all. The only way to solve both problems simultaneously is to increase
the replication factor, which increases the cost of the setup and impacts the performance
ofdurablewritesbyrequiringmoreacknowledgements.Whichevertheapproach,ithasits
drawbacks.
Setting the initial replication factor
The replication factor can be initially assigned when creating a topic using the Kafka Admin
API. Alternatively, it can be set via the --replication-factor flag in the kafka-topics.sh CLI
tool. Kafdrop also lets you set the replication factor when creating a topic. If unspecified, the
replicationfactorforanewlycreatedtopicissourcedfromthedefault.replication.factorbroker
configurationproperty,whichis1bydefault.
Attempting to create a topic with a replication factor greater than the size of the cluster
resultsinaorg.apache.kafka.common.errors.InvalidReplicationFactorException.

207
| Chapter13: ReplicationandAcknowledgements |                 |        |
| ----------------------------------------- | --------------- | ------ |
| Changing                                  | the replication | factor |
Once a replication factor has been set, either the Admin API or the CLI tool can be used to
subsequently alter the replication factor. As it happens, this is not as straightforward as setting
it initially; changing the replication factor is a semi-manual operation, which entails specifying a
newsetofreplicasforeachpartition.
We need access to a multi-broker cluster, with at least two brokers, to practice changing the
replicationfactor.Ifyouhappentohaveamulti-brokerclusteratyourdisposal—perfect.Otherwise,
don’t fret: you can easily spin one up using the following Docker Compose file. Remember to shut
down any existing Kafka, ZooKeeper, and Kafdrop instances before spinning up a Compose stack,
asitwillconflictonportassignments.
| version: "3.2" |     |     |
| -------------- | --- | --- |
services:
zookeeper:
| image: bitnami/zookeeper:3 |     |     |
| -------------------------- | --- | --- |
ports:
- 2181:2181
environment:
ALLOW_ANONYMOUS_LOGIN: "yes"
kafka-0:
| image: bitnami/kafka:2 |     |     |
| ---------------------- | --- | --- |
ports:
- 9092:9092
environment:
| KAFKA_CFG_ZOOKEEPER_CONNECT: | zookeeper:2181 |     |
| ---------------------------- | -------------- | --- |
ALLOW_PLAINTEXT_LISTENER: "yes"
KAFKA_LISTENERS: >-
INTERNAL://:29092,EXTERNAL://:9092
| KAFKA_ADVERTISED_LISTENERS: | >-  |     |
| --------------------------- | --- | --- |
INTERNAL://kafka-0:29092,EXTERNAL://localhost:9092
| KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: |     | >-  |
| ------------------------------------- | --- | --- |
INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
| KAFKA_INTER_BROKER_LISTENER_NAME: |     | "INTERNAL" |
| --------------------------------- | --- | ---------- |
depends_on:
- zookeeper
kafka-1:
| image: bitnami/kafka:2 |     |     |
| ---------------------- | --- | --- |
ports:
- 9093:9093
environment:
| KAFKA_CFG_ZOOKEEPER_CONNECT: | zookeeper:2181 |     |
| ---------------------------- | -------------- | --- |

Chapter13: ReplicationandAcknowledgements 208
ALLOW_PLAINTEXT_LISTENER: "yes"
KAFKA_LISTENERS: >-
INTERNAL://:29092,EXTERNAL://:9093
KAFKA_ADVERTISED_LISTENERS: >-
INTERNAL://kafka-1:29092,EXTERNAL://localhost:9093
KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: >-
INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
KAFKA_INTER_BROKER_LISTENER_NAME: "INTERNAL"
depends_on:
- zookeeper
kafka-2:
image: bitnami/kafka:2
ports:
- 9094:9094
environment:
KAFKA_CFG_ZOOKEEPER_CONNECT: zookeeper:2181
ALLOW_PLAINTEXT_LISTENER: "yes"
KAFKA_LISTENERS: >-
INTERNAL://:29092,EXTERNAL://:9094
KAFKA_ADVERTISED_LISTENERS: >-
INTERNAL://kafka-2:29092,EXTERNAL://localhost:9094
KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: >-
INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT
KAFKA_INTER_BROKER_LISTENER_NAME: "INTERNAL"
depends_on:
- zookeeper
kafdrop:
image: obsidiandynamics/kafdrop:latest
ports:
- 9000:9000
environment:
KAFKA_BROKERCONNECT: >-
kafka-0:29092,kafka-1:29092,kafka-2:29092
depends_on:
- kafka-0
- kafka-1
- kafka-2
Launchthestackwithdocker-compose up.Thiswilltakeafewsecondstostart,spinningupasingle-
nodeZooKeeperensemblewiththreeKafkabrokersattached.Thebrokerswillbeexternallybound
to ports 9092, 9093, and 9094; therefore, we must adjust our bootstrap list accordingly. Kafdrop is
also bundled — exposed on port 9000. Three brokers are a slight overkill for this example, but you
mayfinditusefulforotherexercises.

209
Chapter13: ReplicationandAcknowledgements
First,wewillcreateatesttopicnamedgrowth-plan,withtwopartitionsandareplicationfactorof
one.Keyinthecommandbelow.
| $KAFKA_HOME/bin/kafka-topics.sh |     | \   |     |     |
| ------------------------------- | --- | --- | --- | --- |
--bootstrap-server localhost:9092,localhost:9093,localhost:9094 \
| --create --topic     | growth-plan | --partitions | 2 \ |     |
| -------------------- | ----------- | ------------ | --- | --- |
| --replication-factor | 1           |              |     |     |
Havingcreatedthetopic,examineitbyrunningthekafka-topics.shCLItool:
| $KAFKA_HOME/bin/kafka-topics.sh |     | \   |     |     |
| ------------------------------- | --- | --- | --- | --- |
--bootstrap-server localhost:9092,localhost:9093,localhost:9094 \
| --describe --topic | growth-plan |     |     |     |
| ------------------ | ----------- | --- | --- | --- |
The output indicates that there are two partitions, with their leadership assigned to two different
brokers. As expected, the replication factor is one. (The assignment of replicas is random, so your
outputmayvaryfromtheonebelow.)
| Topic: growth-plan                | PartitionCount: | 2 ReplicationFactor: |     | 1 ￿ |
| --------------------------------- | --------------- | -------------------- | --- | --- |
| Configs: segment.bytes=1073741824 |                 |                      |     |     |
Topic: growth-plan Partition: 0 Leader: 1002 Replicas: 1002 ￿
Isr: 1002
Topic: growth-plan Partition: 1 Leader: 1001 Replicas: 1001 ￿
Isr: 1001
LookingatKafdrop,wecanseeasimilarpicture:

Chapter13: ReplicationandAcknowledgements 210
Kafdropshowingatopicwithonereplica
Let’sincreasethereplicationfactorfromonetotwo.Wewouldliketokeepthepartitionsbalanced,
lettingthebrokersalternateintheleader-followerstatusforeachpartition.Ideally,wedon’twantto
disruptanyexistingassignments,orchangetheleaderstatusofanyreplicas.Broker1002shouldbe
theleaderforpartition0,andfollowerforpartition1.Conversely,broker1001shouldleadpartition
1andfollowpartition0.
Havingformedaplaninourhead,itistimetocaptureitinareassignmentfile.Createafilenamed
alter-replicas.json,containingthefollowing:

211
Chapter13: ReplicationandAcknowledgements
{
| "version":    | 1,  |     |     |
| ------------- | --- | --- | --- |
| "partitions": | [   |     |     |
{
| "topic":     | "growth-plan", |     |     |
| ------------ | -------------- | --- | --- |
| "partition": | 0,             |     |     |
| "replicas":  | [1002, 1001]   |     |     |
},
{
| "topic":     | "growth-plan", |     |     |
| ------------ | -------------- | --- | --- |
| "partition": | 1,             |     |     |
| "replicas":  | [1001, 1002]   |     |     |
}
]
}
The reassignment file enumerates over all topic-partitions that require alteration, listing the new
replicas as an array of numeric broker IDs. By convention, the first element in the array identifies
thepreferredleader.
Next,applythereassignmentfileusingthekafka-reassign-partitions.shtool.
| $KAFKA_HOME/bin/kafka-reassign-partitions.sh |                | \                   |           |
| -------------------------------------------- | -------------- | ------------------- | --------- |
| --zookeeper                                  | localhost:2181 | \                   |           |
| --reassignment-json-file                     |                | alter-replicas.json | --execute |
The kafka-reassign-partitions.sh tool is limited to working with ZooKeeper. It cannot
beusedwithoutthe--zookeeperflag.
The application of replica changes will echo the original assignments to the console as a JSON
document. This can be saved as a JSON file in case it becomes necessary to revert to the original
configuration.

212
| Chapter13: | ReplicationandAcknowledgements |         |            |     |
| ---------- | ------------------------------ | ------- | ---------- | --- |
| Current    | partition                      | replica | assignment |     |
{"version":1,"partitions":[{"topic":"growth-plan","partition":1, ￿
"replicas":[1001],"log_dirs":["any"]},{"topic":"growth-plan", ￿
"partition":0,"replicas":[1002],"log_dirs":["any"]}]}
Save this to use as the --reassignment-json-file option during ￿
rollback
| Successfully |     | started reassignment | of partitions. |     |
| ------------ | --- | -------------------- | -------------- | --- |
Giventhelackofanypartitiondatainthissimpleexample,thereassignmentshouldbenear-instant.
Thiswouldn’tnecessarilybethecaseforaproductiontopic,potentiallycontaininglotsofdata;the
reassignmenttimewoulddependonthesizeofthepartitions,theavailablenetworkbandwidth,and
theperformanceofthebrokers.Toascertainwhetherthereplicachangescompletedsuccessfully,run
thekafka-reassign-partitions.shtoolwiththe--verifyswitch:
| $KAFKA_HOME/bin/kafka-reassign-partitions.sh |     |                | \                   |          |
| -------------------------------------------- | --- | -------------- | ------------------- | -------- |
| --zookeeper                                  |     | localhost:2181 | \                   |          |
| --reassignment-json-file                     |     |                | alter-replicas.json | --verify |
Whenallpartitionshavebeensuccessfullyreassigned,theoutputshouldresemblethefollowing:
| Status | of partition | reassignment: |     |     |
| ------ | ------------ | ------------- | --- | --- |
Reassignment of partition growth-plan-0 completed successfully
Reassignment of partition growth-plan-1 completed successfully
Reassignment may involve copying large amounts of data over the network, which
may affect the performance of the cluster. To throttle the replication process, run
kafka-reassign-partitions.sh with the -throttle flag, specifying a cap on the desired
bandwidth in bytes per second. The throttle will persist even after the initial replication
completes, affecting regular replication for the topic in question. To lift the throttle, run
thekafka-reassign-partitions.shcommandwiththe-verifyswitch.Thethrottlewillbe
clearedonlyifallpartitionshavehadtheirreassignmentscompletedsuccessfully;otherwise,
thethrottlewillremaininforce.
Once reassignment completes, run the kafka-topics.sh command again. The output should now
resemblethefollowing.

213
| Chapter13: | ReplicationandAcknowledgements |                          |                 |                |                      |        |     |
| ---------- | ------------------------------ | ------------------------ | --------------- | -------------- | -------------------- | ------ | --- |
| Topic:     | growth-plan                    |                          | PartitionCount: |                | 2 ReplicationFactor: |        | 2 ￿ |
|            | Configs:                       | segment.bytes=1073741824 |                 |                |                      |        |     |
| Topic:     | growth-plan                    |                          | Partition:      |                | 0 Leader:            | 1002 ￿ |     |
|            | Replicas:                      | 1002,1001                |                 | Isr: 1002,1001 |                      |        |     |
| Topic:     | growth-plan                    |                          | Partition:      |                | 1 Leader:            | 1001 ￿ |     |
|            | Replicas:                      | 1001,1002                |                 | Isr: 1001,1002 |                      |        |     |
Similarly,Kafdropwillalsoreflectthenewreplicanodes.
Kafdropshowingatopicwithtworeplicas
Preparingreassignmentfilesbyhandcanbelaborious.Thereareseveralopen-sourcehelperscripts
that automate this process. One such script is the kafka-reassign-tool, hosted on GitHub at
github.com/dimas/kafka-reassign-tool²⁸.
| Decommissioning |     |     |     | broker | nodes |     |     |
| --------------- | --- | --- | --- | ------ | ----- | --- | --- |
The built-in kafka-reassign-partitions.sh tool might appear overly low-level and cumbersome
for simple use cases such as adjusting the replication factor. However, there is a reason for that:
this tool is designed to administer arbitrary changes to the replication topology. One such use case
is for rebalancing partitions among broker nodes, levelling the load when new nodes are added
to the cluster. Another use case is for moving partitions off a broker node before it can be safely
decommissioned
Havingselectedabrokerfordecommissioning,createareassignmentfilethatcoversallpartitionsfor
whichtheoutgoingbrokerisareplica.SubstitutetheoutgoingbrokerIDwithoneoftheremaining
brokers,ensuringthattheoriginalreplicationfactorispreserved(unlessyouwishtochangetherepli-
cation factor in the process). Once preparations are complete, run kafka-reassign-partitions.sh
with the -execute switch, then follow up with the -verify switch to ensure that the process
completes.Havingreassignedthepartitions,runthekafka-topics.shcommandwiththe-describe
switchwithoutspecifyingatopicname,toverifythattheoutgoingbrokernolongerfeaturesinany
replicas,acrossanyoftheexistingtopics.Onlyafterthebrokerhasbeencompletelyfencedoff,can
itbepermanentlyremovedfromthecluster.
²⁸https://github.com/dimas/kafka-reassign-tool

Chapter13: ReplicationandAcknowledgements 214
Acknowledgements
The acks property stipulates the number of acknowledgements the producer requires the leader to
have received before considering a request complete, and before acknowledging the write with the
producer.Thisisfundamentaltothedurabilityofrecords;amisconfiguredackspropertymayresult
inthelossofdatawhiletheproducernaivelyassumesthatarecordhasbeenstablypersisted.
Although the property relates to the number of acknowledgements, it accepts an enumerated
constantbeingoneof—
• 0:Don’trequireanacknowledgementfromtheleader.
• 1:Requireoneacknowledgementfromtheleader,beingthepersistenceoftherecordtoitslocal
log.Thisisthedefaultsettingwhenenable.idempotenceissettofalse.
• -1orall:Requiretheleadertoreceiveacknowledgementsfromallin-syncreplicas.Thisisthe
defaultsettingwhenenable.idempotenceissettotrue.
Eachofthesemodesisdiscussedindetailinthefollowingsubsections.
No acknowledgements
Whenacks=0,anyrecordsqueuedontheoutgoingsocketareassumedtohavebeenpublished,with
noexplicitacknowledgementsrequiredfromtheleader.Thisoptionprovidestheweakestdurability
guarantee.Notonlyisitimpactedbyatriviallossofabroker,buttheavailabilityoftheclientisalso
adeterminantinthedurabilityoftherecord.Iftheclientweretofail,anyqueuedrecordswouldbe
lost.
In addition to forfeiting any sort of durability guarantees, the client will not be informed of the
offset of the published record. Normally, a client would obtain the offset in one of two different
ways:supplyingaorg.apache.kafka.clients.producer.CallbacktotheProducer.send()method,
orblockingontheresultofaFuture<RecordMetadata>objectthatisreturnedfromsend().Ineither
case,theresultingRecordMetadatawouldcontaintheoffsetofthepublishedrecord.However,when
acks=0,RecordMetadata.hasOffset()willreturnfalse,andRecordMetadata.offset()willreturn
-1,signifyingthatnooffsetisavailable.
Themainusecaseforacks=0isthatthepublishercanaffordtoloseeitheraone-offrecordorsmall
contiguous batches of records with a negligible impact on the correctness of the overall system.
Consideraremotetemperaturesensorthatisperiodicallysubmittingtelemetryreadingstoaserver,
whichinturn,ispublishingthesetoaKafkatopic.Adownstreamprocesswillingestthesereadings
andpopulateaweb-baseddashboardinreal-time.
In this hypothetical scenario, and provided that historical temperature readings are not significant,
we can afford to lose some number of intermediate readings, provided that a reading for every
sensoreventuallycomesthrough.Thisisnottosaythatahigherdurabilityratingwouldsomehow

Chapter13: ReplicationandAcknowledgements 215
beinapplicable,onlythatitmaynotbestrictlynecessary.Furthermore,byneglectingacknowledge-
ments, we are also largely sidestepping the matters of error handling. This leads to a simpler, more
maintainableapplication,albeitonethatoffersvirtuallynodeliveryguarantees.
Settingacks=0innowayimpliesthatrecordswillnotbereplicatedbytheleadertothein-
sync replicas. Standard asynchronous replication behaviour applies identically in all cases,
irrespectiveofthenumberofrequestedacknowledgements,providedthattherecordactually
arrivesatthebroker.Youcanthinkoftheackspropertyastheassurancelevelofanotional
contract between three parties — the client, the partition leader, and the follower replicas.
Thecontractisalwayspresent,witheachpartyhavingthebestintentions,butnonetheless,
thecontractmaynotbefulfilledifoneofthepartiesweretofail.Atitsmostrelaxedlevel
— acks=0 — the extent of the assurance is constrained to the client. With each increase in
thevalueof acks,theproducerexpandsthescopeofassurancetoincludethenextpartyin
thelist.Withacks=all,theproducerwillnotbesatisfieduntilallpartieshavefulfilledtheir
contractualobligations.
One acknowledgement
When acks=1, the client will wait until the partition reader queues the write to its local log. The
broker will asynchronously forward the record to all follower replicas and may respond to the
publisherbeforeitreceivesacknowledgementsfromallin-syncreplicas.
On the face of it, this setting may appear to offer a significantly stronger durability guarantee over
acks=0.Ifintheacks=0casethesystemwasintolerantofthefailureofasingleparty,intheacks=1
casethefailureofthepartitionleadershouldbetolerated,providedtheclientremainsonline.Should
theleaderfail,theproducercanforwarditsrequesttoanotherreplica,whicheveronetakestheplace
oftheleader.
In reality, this stance is ill-advised for several reasons. One is to do with how Kafka writes records
to its log. Records are considered written when they are handed over to the underlying file system
buffers. However, the broker does not invoke the fsync operation for each written record, in other
words,itdoesnotwaitforthewritetobeflushedbeforereplyingtotheclient.Therefore,itispossible
for the broker to acknowledge the write and fail immediately thereafter, losing the written chunk
beforeithasbeenreplicatedontothefollowers.Theproducerwillcontinueontheassumptionthat
thewritehasbeenacknowledged.
The second reason is a close relative of the first. Assume for the moment that the write was
successfully committed to the leader’s disk, but the leader suffered a failure before the record was
received by any of the followers. The controller node, having identified the failure, will appoint a
newleaderfromtheremainingin-syncreplicas.Thereisnoguaranteethatthenewleaderwillhave
receivedthelastfewrecordsthatwerequeuedforreplicationbytheoutgoingleader.
This is not to say that acks=1 offers no additional durability benefits whatsoever, rather, the
distinction between acks=0 and acks=1 is only discernible if there is a material difference between

Chapter13: ReplicationandAcknowledgements 216
therelativereliabilityoftheclientandbrokernodes.Andsometimesthisisindeedthecase:brokers
may be assembled using higher-grade hardware components with more integrated redundancy,
making them less failure-prone. Conversely, clients may be running on ephemeral instances that
are susceptible to termination at short notice. But taking this for granted would be credulously
presumptuous;itisprudenttotreatabrokerlikeanyothercomponent,assumingthatifsomething
canfail,itprobablywill,andwhenweleastexpectitto.
With the above in mind, setacks=1 in those scenarios where the client can trivially afford to lose a
publishedrecord,butstillrequirestheoffsetofthepossiblypublishedrecord—eitherforitsinternal
housekeepingorforrespondingtoanupstreamapplication.
All acknowledgements
Whenacks=alloracks=-1(thetwoaresynonymous),theclientwillwaituntilthepartitionleader
has gathered acknowledgements from the complete set of in-sync replicas. This is the highest
guarantee on offer, ensuring that the record is durably persisted for as long as one in-sync replica
remains. As it was stated earlier, the number of in-sync replicas may vary from the total number
of replicas; the in-sync replicas are represented by a set that may grow and shrink dynamically
depending on the observed replication lag between the leader and each follower. Since the latency
of a publish operation when acks=all is attributable to the slowest replica in the ISR, the in-sync
replica set is designed to minimise this latency by temporarily dismissing those replicas that are
experiencingstarvationduetoI/Obottlenecksorotherperformance-impactingcontingencies.
There is a pair of subtle configuration gotchas lurking in Kafka: one being on the client
whiletheotheronthebroker.Thedefaultsettingof acksis1whenidempotenceisdisabled.
(IdempotenceisdiscussedinChapter10:ClientConfiguration.)Thisisunlikelytomeetthe
durabilityexpectationsofmostapplications.Whiletherationalebehindthisdecisionisun-
documented,thechoiceof acks=1mayberelatedtothedisclosureof RecordMetadatatothe
producer.Withacks=1,theproducerwillbeinformedoftheoffsetofthepublishedrecord,
whereas with acks=0 the producer remains unaware of this. Kafka’s own documentation
states: “When used together, min.insync.replicas and acks allow you to enforce greater
durabilityguarantees.Atypicalscenariowouldbetocreateatopicwithareplicationfactor
of 3, set min.insync.replicasto 2, and produce withacks of all.” In spite of recognising
acks=allasbeingnecessarytofulfilla‘typical’scenario,thedefaultvalueof acksremains
1.Thesecond‘gotcha’:thedefaultvalueof min.insync.replicasis1,againcontradicting
the‘typical’scenario.
Set acks=all in those scenarios where the correctness of the system is predicated on the consensus
between the producer and the Kafka cluster as to whether a record has been published. In other
words,iftheclientthinksthattherecordhasbeenpublished,thenitbetterbe,ortheintegrityofthe
system may be compromised. This is often the case for transactional business applications where
recorded events correlate to financial activity; the loss of a record may directly or indirectly incur
financiallosses.

Chapter13: ReplicationandAcknowledgements 217
Themaindrawbackof acks=allistheincreasedlatencyasaresultofseveralblockingnetworkcalls
—betweentheproducerandtheleader,andbetweentheleaderandasubsetofitsfollowers.Thisis
thepriceofdurability.
Which setting is right for me?
The explanations above have clarified the behaviour of the producer client and the partition leader
withrespecttothevalueoftheacksproperty.Thereadershouldnowhaveabetterappreciationof
thescenariosthatmaybefittingtothedifferentacknowledgementmodes.
If in doubt, set acks to all. This will ensure that a record is acknowledged by a number of brokers
thatis,atminimum,equaltothevalueof min.insync.replicas.Bearinmindthat‘acknowledged’
isnotthesameas‘stablypersisted’.Failureofabrokerimmediatelyfollowinganacknowledgement
may result in the loss of data, therefore it is essential that the value of min.insync.replicas
accurately reflects your tolerance for data loss. Setting min.insync.replicas to 2 on a topic with
a replication factor of three, and using acks=all on the producer ensures that every published
record is acknowledged by the leader and at least one other replica. The increased latency might
be noticeable, but it may be preferable to data loss. One might even argue that if latency is the
overarching concern that dominates technical decision-making, then Kafka is perhaps not the best
solutionfortheproblemathand.
This chapter explored the innards of Kafka’s internal replication protocol. We learned how Kafka
utilisesin-syncreplicastoreducethenumberofacknowledgementsrequiredfordurablepersistence,
efficientlyachievingdistributedconsensus.
We looked at how partition availability and data consistency concerns may be impacted by the
replicationsetup,namelytheinherentavailability-consistencytrade-offspresentindecidingonthe
replication factor and the min.insync.replicas parameter. This ties into Kafka’s leader election —
thedecisionsmadewhenpromotingfollowerreplicasintheeventofleaderfailure.
Configurationofthereplicationtopologywasthenexplored.Weworkedthroughexamplesofusing
the built-in kafka-reassign-partitions.sh tool to affect fine-grained control over a partition’s
replicas,anditspotentialusecasesoutsideofsimplereplicationfactoradjustments.Theperformance
trade-offs of durable persistence were covered, and we delved into the idiosyncrasies of Kafka’s
defaultconfigurationsettings,andhowtheymaycatchoutunsuspectingusers.
Finally,durabilitywasexploredfromtheeyesoftheproducer.Welookedathowtheacksproducer
settingimpactsthedurabilityguaranteesofindividualrecords,anditsinterplaywiththereplication
factorandthemin.insync.replicasbroker-sidesettings.

Chapter 14: Data Retention
Giventhatinfinitestorageisyetbeinvented,whathappenstoallthoserecordsstoredinKafka?
Chapter 3: Architecture and Core Concepts introduced the concept of low and high-water marks
andhowtheseshiftasrecordsarewrittentothelogandasrecordsareeventuallypurged.Thefocus
on this chapter will be the latter; specifically, the conditions under which data is removed and the
precisebehaviourofKafkainthatregard.
Kafka storage internals
Given the option, most operators prefer to treat Kafka as a black box when configuring its data
retention behaviour, and many other aspects, for that matter. By all means, this is understandable
—beingabletoquicklyconsulttheofficialdocumentationforahandfulofconfigurationproperties,
apply them as stated in the instructions and move on to a less mundane task — almost sounds too
reasonable to be true. And unfortunately, it is; Kafka is brimming with nuanced behaviour that is
stronglycoupledtotheunderlyingimplementation.Withoutthenecessaryinsights,onemayeasily
lose many days trying to explain the reasons that Kafka’s behaves the way it does, seemingly in
contradictiontothewayitwasconfigured.
Organisation of log data
Before exploring how Kafka’s data retention mechanisms operate, it is instructive to gain a basic
understanding of its log structure. As it has been stated numerous times, Kafka is a distributed,
append-only log. The distributed aspect was covered in Chapter 13: Replication and Acknowledge-
ments.Turningtothelocalpersistenceofapartition—which,asweknow,occursateachreplica—
thelogisorganisedondiskasaseriesofdataandindexfiles.
Thelogfilesforeachreplicatedpartitionarestoredinadedicatedsubdirectoryof log.dirs,which
points to /tmp/kafka-logs by default. The subdirectory is named by concatenating the topic name
with the partition index, delimited by the - (hyphen) character. For example, partition 0 of the
getting-started topic can be found at /tmp/kafka-logs/getting-started-0. Listing the contents
ofthedirectorywiththetreecommand,wecanseethefollowing:

Chapter14: DataRetention 219
.
├── 00000000000000000000.index
├── 00000000000000000000.log
├── 00000000000000000000.timeindex
└── leader-epoch-checkpoint
Theleader-epoch-checkpointfilecontainstheleaderepoch—thenumberoftimestheleadershipof
apartitionhasbeenreassignedbytheclustercontroller.Thereplicasusetheleaderepochasameans
ofverifyingthecurrentleader,fencinga priorleader iftheirleadership statushas been superseded.
If a displaced leader attempts to syndicate data, the replicas will sense that it is operating under an
outdatedepochnumberandwillignoreitscommands.
Indexes
Thefilesnamedwithalarge,zero-paddedintegerarelogsegments.Kafkadoesnotstoreitspartition
log in a single, contiguous ‘jumbo’ file. Instead, logs are broken down into discrete chunks. Each
segmentisnamedinaccordancewiththeoffsetofthefirstrecordsthatitcontains.Thisiscalledthe
baseoffset.
Recordsmaybeofarbitrarysize,andthereforeoccupyavariableamountofspaceintheirrespective
.log file. To further complicate matters, records are persisted in batches (which might be com-
pressed). Variable record size and batching mean that a record cannot be trivially located by its
offset using the .log file alone. This is where the .index file comes in. Essentially, an index is a
sorted map of record offsets to the physical locations of the corresponding records in the .log file.
Where a record is encompassed within a batch, the index file points to the beginning of the batch
entry.Forcompactness,theindexfileusesa32-bitintegertostoreoffsets,havingsubtractedthebase
offset from the record’s offset beforehand. The physical locations pointed to by the index are also
storedas32-bitintegers,foracombinedentrysizeof8bytes.Indexesarememory-mappedfiles;the
storagespaceforanindexispreallocatedwhentheindexfileiscreated.Theamountofpreallocated
spaceisprescribedbythelog.index.size.max.bytesproperty,whichdefaultsto10485760(10MiB)
andactsasadefaultvalueforalltopics.Tocontroltheextentofthepreallocationforaspecifictopic,
set the segment.index.bytes property. When a log file is rolled over, the remaining unused space
initsindexfilesisreturnedtothefilesystem.
Kafkausesdifferentnamesforpropertiesthataffectthedefaultbehaviourversusthosethat
target individual topics. There is no particular naming convention to these properties; for
example, the log.roll.ms cluster-wide property corresponds to the segment.ms per-topic
property.
Noteveryrecordintheloghasacorrespondingentryintheindex.Forstarters,recordsmaybepart
of a batch, and only the location of the batch is indexed. In addition, to constrain the size of the
index files and to minimise the amount of additional disk I/O, records are indexed at an interval

Chapter14: DataRetention 220
specified by thelog.index.interval.bytes, which is 4096 by default. To override this setting on a
per-topicbasis,theindex.interval.bytespropertycanbeassignedonthetopicentity.
With the aid of an index file, it is possible to locate a record in O(1) complexity. Given a record’s
offset — by subtracting the base offset, dividing by the index interval, and seeking to the resulting
locationintheindexfile,thephysicallocationoftherecordisrevealed.Wheretherecordinquestion
might not be indexed, the previously indexed offset is employed, requiring a short scan to find the
record.
The.timeindexfileisaclosecousinofthe.indexfile,mappingthemillisecond-preciseUNIXepoch
timestamp of each record to its location in the .log file. The presence of .timeindex files allows
Kafkatolocaterecordsbasedontime,ratherthananoffset.
The diagram below illustrates the segmented log structure and the use of indexes to map record
offsetstophysicallocationsinthelogfiles.

Chapter14: DataRetention 221
Logsegmentsandindexes

Chapter14: DataRetention 222
Rotation of log segments
With the basics covered, we arrive at the most crucial part of our preamble — the rotation of
log segments. Specifically, it is the log rotation behaviour that has the greatest potential to cause
confusioninrelationtoKafka’sdataretentionmechanismsthatwillbediscussedshortly.
Areplicamaintainsasingleactive logsegmentforeverypartitionthatithosts.Theactivesegment
corresponds to the head end of the log. All writes performed on behalf of producers are appended
to the active log segment, while consumer reads may occur from any segment in the log. For a
given active segment, no entity other than a producer is permitted to modify the segment and its
correspondingindexfiles;thisconstraintispositivelyintuitiveinrelationtotheappend-onlynature
ofaloginKafka.
At some point, the active segment file will approach a set of predefined constraints that, when
breached,willtriggeraroll-over.Theseconstraintsaredefinedbythefollowingbrokerproperties:
• log.segment.bytes—themaximumsizeofasinglelogfile.Defaultsto1073741824(1GiB).
• log.roll.hours—themaximumamountoftime(inhours)thatanactivesegmentisallowedto
existbeforebeingclosed.Thedefaultvalueis168(oneweek).Theageofasegmentisderived
by subtracting the timestamp of the first record from the current time. The size-based and
time-based properties are complementary — tripping either of these thresholds is sufficient to
initiatearoll-over.
• log.roll.ms—wheretheuseofhourmultiplestospecifytheroll-overthresholddoesnotpro-
videsufficientgranularity,thispropertymaybeusedtospecifythethresholdwithmillisecond
precision.Whenset,thispropertysupersedeslog.roll.hours.
Replicas typically host multiple log files, particularly when dealing with ‘wide’ topics (i.e. topics
withlotsofpartitions).Itislikelythatwhenalogsegmentreachesitsroll-overthreshold,otherlog
segments would have reached theirs, or are just about to. To prevent a stampede of I/O requests,
a broker will introduce a random amount of jitter — an artificial delay from the time a roll-over
was triggered to the time of enacting the roll-over — thereby spreading the correlated roll-over
of multiple log segments over a larger time period. The jitter values apply to the log.roll.hours
andlog.roll.mssettings,andarenamedlog.roll.jitter.hoursandlog.roll.jitter.ms,respec-
tively.Nojitterisappliedbydefault.
Thepropertieslistedaboverepresentthebaselineconfigurationapplicabletoalltopics,andmaybe
updatedstaticallyordynamically,butcannotbeusedtotargetindividualtopics.Thereareequivalent
per-topicpropertiesthatcanbesetdynamically:
• segment.bytes—inplaceof log.segment.bytes.
• segment.ms—inplaceof log.roll.ms.
• segment.jitter.ms—inplaceof log.roll.jitter.ms.

223
| Chapter14: | DataRetention |     |     |     |     |     |     |     |
| ---------- | ------------- | --- | --- | --- | --- | --- | --- | --- |
While index files are preallocated by default, log segments are not — owing to the log
segment being rather large (1 GiB by default). Also, Linux-based filesystems are generally
performantwithrespecttoappendoperations.Thelackofpreallocationhasbeenknownto
causeperformanceissueswhenKafkaisrunonNTFSandsomeolderLinuxfilesystems.To
enablepreallocationoflogfiles,setthelog.preallocatepropertytotrueforalltopics,or
thepreallocatepropertywhentargettingindividualtopics.
| Viewing | log | contents |     |     |     |     |     |     |
| ------- | --- | -------- | --- | --- | --- | --- | --- | --- |
Bothlogfilesandindexfilesareencodedinbinaryformforcompactness.Readingthedatadirectly
willnottranslatetoanythingparticularlymeaningfultotheuser,withthepossibleexceptionofthe
keyorvalueportionsofarecordifthesehappentobestrings.Toassistindiagnosingissueswithlogs
andindexes,KafkaprovidesaCLItool—kafka-dump-log.sh.Thisutilitymustberunlocallyonthe
broker housing the partition data, and can operate on either file type (including time indexes). The
example below shows the output of kafka-dump-log.sh over a segment of the getting-started-0
log.
| LOG_DIR=/tmp/kafka-logs           |                                                     |     | &&  |     |     |     |     |     |
| --------------------------------- | --------------------------------------------------- | --- | --- | --- | --- | --- | --- | --- |
| $KAFKA_HOME/bin/kafka-dump-log.sh |                                                     |     |     |     | \   |     |     |     |
| --files                           | $LOG_DIR/getting-started-0/00000000000000000000.log |     |     |     |     |     |     | \   |
--print-data-log
Dumping /tmp/kafka-logs/getting-started-0/00000000000000000000.log
| Starting              | offset:       | 0                |     |                  |                 |                 |      |     |
| --------------------- | ------------- | ---------------- | --- | ---------------- | --------------- | --------------- | ---- | --- |
| baseOffset:           | 0 lastOffset: |                  | 0   | count:           | 1 baseSequence: |                 | -1 ￿ |     |
| lastSequence:         |               | -1 producerId:   |     | -1               | producerEpoch:  |                 | -1 ￿ |     |
| partitionLeaderEpoch: |               |                  | 0   | isTransactional: |                 | false           | ￿    |     |
| isControl:            |               | false position:  |     | 0 CreateTime:    |                 | 1577930118584   |      | ￿   |
| size:                 | 73 magic:     | 2 compresscodec: |     |                  | NONE            | crc: 2333002560 |      | ￿   |
| isvalid:              | true          |                  |     |                  |                 |                 |      |     |
| offset: 0 CreateTime: 1577930118584 keysize: -1 valuesize: 5 ￿
| sequence: | -1  | headerKeys: |     | [] payload: | alpha |     |     |     |
| --------- | --- | ----------- | --- | ----------- | ----- | --- | --- | --- |
Deletion
Revertingtotheoriginalquestion,whathappenswhenweaccumulatetoomuchdata?
Kafkaofferstwoindependentbutrelatedstrategies—calledcleanuppolicies—fordealingwithdata
retention.Thefirstandmostbasicstrategyistosimplydelete olddata.Thesecondstrategyismore
elaborate — compact prior data in such a manner as to preserve the most amount of information.

Chapter14: DataRetention 224
Bothstrategiescanco-exist.Thissectionwillfocusonthedeletestrategy;thefollowingsectionwill
addresscompaction.
The cleanup policy can be assigned via the log.cleanup.policy broker property, which takes a
comma-separated list of policy values delete and compact. The default is delete. This setting can
be assigned statically or dynamically, acting as a cross-topic default. A topic-specific policy can
be assigned via the cleanup.policy property — overriding the default log.cleanup.policy for the
topicinquestion.
The delete policy operates at the granularity of log segments. A background process, operating
within a replica, looks at each inactive log segment to determine whether a segment is eligible for
deletion.Thethresholdsconstrainingtheretentionoflogsegmentsare:
• log.retention.bytes — the maximum allowable size of the log before its segments may be
pruned, starting with the tail segment. This value is not set by default as most retention
requirementsarespecifiedintermsoftime,ratherthansize.
• log.retention.hours — the number of hours to keep a log segment before deleting it. The
defaultvalueis168(oneweek).Thesize-basedandtime-basedpropertiesarecomplementary;
thebreachofanyofthetwoconstraintsissufficienttotriggersegmentdeletion.
• log.retention.minutes — specifies the retention in minutes, where hour multiples fail to
providesufficientgranularity.Ifset,thispropertyoverridesthelog.retention.hoursproperty.
• log.retention.ms—specifiestheretentioninmilliseconds,overridingthelog.retention.minutes
property.
Thebackgroundprocesschecksfilesatanintervalspecifiedbylog.retention.check.interval.ms,
defaultingto300000(fiveminutes).Thisissufficientformostretentionsettingswherethelifetimeof
alogismeasuredinhours,daysorweeks.Whentestingthedeletionpolicyoversmallertimeframes,
itmaybenecessarytowindthissettingdowntodecreasethetimebetweensuccessivechecks.
AsitwasstatedinChapter3:ArchitectureandCoreConcepts,thedeletionofrecordsresultsinthe
advancement of the beginning offsets (also known as the low-water mark) to the point of the first
preservedrecord.Thishappensautomaticallyaslogsegmentsaredeleted.
Because the granularity of the delete policy is a log segment, it may be necessary to adjust the
maximum size of a segment depending on one’s expectations around the responsiveness of the
deletionprocess.Supposetherewasarequirementthatarecordshouldnotbekeptlongerthanseven
days,butatleastfivedaysofretentionisrequired.Settinglog.retention.hoursto168(sevendays)
would satisfy the lower bound of five days, but depending on how long it takes for the active log
segment to roll over, the upper bound might not be satisfied. Recall, only producer-initiated writes
areallowedontheactivesegment;allothermanipulations—deletionandcompaction—mayonly
occurontheinactivesegments.Withthedefaultsettingsinplace,logfilesarenotrotateduntilthey
either reach a maturity of seven days or grow to over 1 GiB in size. To satisfy the upper bound, we
must constrain the lifetime of a log segment, so that it forcibly rolls over within a more suitable
time frame. For example, we might set log.retention.hours to 132 (five days and twelve hours)
andlog.roll.hoursto24,therebyforcingacollectionaftersix-and-a-halfdays.

Chapter14: DataRetention 225
Thelog.roll.hourssetting(anditsmorefine-grainedequivalents)areaneffectivemeasure
forthepredictablerotationoflogfilesinlow-throughputtopics,wherethereisinsufficient
writepressuretocausearoll-overduetofilesizealone.However,eventhetime-basedtrigger
requiresatleastonewritetotheactivesegmentforthebrokertorealisethatitstimeisup,so
tospeak.Ifatopicisexperiencingnowritesforwhateverreason,Kafkawillnotproactively
rotatetheactivelogsegment,evenifthelatterhasoutliveditsexpectedlifespan.Sointhe
exampleabove,wecanonlysatisfytheupperboundrequirementifthereisaconstanttrickle
ofrecordstothetopicinquestion.
Log deletion applies not just to user-defined topics, but also internal topics such as __consumer_-
offsets. For more information on how the deletion of log segments may affect consumer groups,
seeChapter5:GettingStarted.
Compaction
Use cases behind compaction
Overwhelmingly, Kafka is used as a replicated log for notifying downstream parties of events of
certain relevance, where the latter typically correlate to changes in an upstream system of record.
Often, this system is backed by a database or some other persistent data store that offers an
interface for querying the current state of the data, should the need for a holistic view of the state
snapshot arise. Consumers may build their bespoke projections of the upstream state by faithfully
replicating all relevant changes, provided these are emitted as events. At either rate, Kafka is used
asanintermediatetransportofsorts—essentialmiddlewarethatunderpinsabroader,event-driven
system.Andasessentialasitmayappearinthiscontext,inthemajorityofitsuses,Kafkadoesnot
entirelyreplaceasystemofrecord.
Although as one might argue, Kafka comes remarkably close to fulfilling the role of a data store —
particularlyasapplicationsgrowmoredependentonareplicatedlog.Insomeevent-drivensystems,
thenotionofqueryingthemasterdatastorehasbeenvirtuallyeliminatedor,moreoften,relegated
tothehydrationscenario,wherefirst-timeconsumersmayquerythemasterdatainordertoprime
theirinternalprojection,andswitchtoKafka-basedreplicationthereafter.Forthereader’sreference,
suchsystemsarecalledbimodal—inthattheyemployonemodefortheinitialhydrationandanother
for the subsequentreplication. While they are mostly straightforward — in that both modes can be
easily reasoned about — there is the added complexity of maintaining code that deals with a one-
off event — a contingency that typically occurs once in the lifetime of an application in its target
operatingenvironment.
WhatifKafkawasgoingtobeusedforeventsourcing?Specifically,intheroleofadefinitiveevent
storethatanyconsumercouldusetoreconstitutethestateofanentiredomainfromfirstprinciples
— by replaying all updates from the point of conception. Such an arrangement would have a clear
benefit—unimodality;specifically,usingonemodeofoperationtoaccommodateboththehydration
scenarioandthesubsequentreplicationofeventdata.Simplercode;easiertotest;easiertomaintain.

Chapter14: DataRetention 226
Aneventstorerecordingallchangestoeveryentityofinterestoveritsentirelifetime?Thatsounds
like a scalability nightmare. Where would one find the space to store all this data? And how long
wouldtheinitialhydrationtake—havingtopainstakinglyreplayallrecordsfromdaydot?
Even with infinite retention, the naive approach of storing everything would hardly be practical —
especially for fast-moving data where any given entity might be characterised by a considerable
numberofupdatesoveritslifetime.Thereplaytimewouldgrowbeyondcomprehension;wewould
have ourselves an event store that no one would dare read from. On the flip side, discarding the
oldest records, while binding the replay time, would result in the loss of data — forcing us back
downthebimodalpath.
Kafkaaccommodatestheclassicalunimodaleventsourcingscenariousingitslogcompactionfeature.
Where deletion operates at the segment level, compaction provides a finer-grained, per-record
cullingstrategy—asopposedtothecoarser-grainedtime-basedretention.Theideaistoselectively
prune a related set of records, where the latter represents updates to some long-lived entity, and
where a more recent (and therefore, more relevant) update record exists for the same key. In doing
so, the log is guaranteed to have at least one record that represents the most recent snapshot of an
entity—sacrificingthetimelineofupdatesinordertomaximallypreservethecurrentstate.
Anotherwayoflookingatcompactionisthatitturnsastreamintoasnapshot,whereonlythemost
recent information is retained. This is akin to a row in a database, where the latter disregards the
timeline of updates — keeping only the last value. For this to work, the granularity of an update
mustalignwiththegranularityoftheunderlyingentity;inotherwords,updatesmustbefullyself-
contained.Compactionisintractablewhenupdatescontaindeltas—piecemealinformationthatcan
be used to incrementally build the state of an entity. If a more recent update supersedes its logical
predecessor, there cannot be any information contained within the preceding record that cannot be
obtainedbyinspectingthesuccessor.
Overwriting and deleting records
Compaction is activated by adding compaction to the list of policies in log.cleanup.policy (or
cleanup.policy for the per-topic setting). Apart from adding the policy, compaction requires the
cleaner process,whichisenabledbydefaultandcontrolledbythelog.cleaner.enableproperty.
Once compaction is activated, there is nothing more required from the producer to overwrite an
older record – one simply publishes an update with the same key. With compaction in force, the
number of records approaches the number of entities that are being described. When the useful
lifespan of an entity nears its conclusion, any previous updates for that entity may be deleted by
publishing a tombstone — a record for the same key as the prior updates but with a null value.
Tombstones will trigger the purging of like records, but will themselves be retained for some time
before eventually being purged. (The reason for this will be discussed shortly.) There is nothing
special about tombstones from a consumer’s perspective — it will see records with null values —
exactlyastheywerewrittenbytheproducer.
Once a topic has been fully compacted (old records removed and tombstones purged), the amount
oftime a consumer will taketo traverse the topic end-to-end willbe proportional to the number of

Chapter14: DataRetention 227
retained unique keys. This is precisely what is needed to sustainably support the majority of event
sourcingscenarios.
Behind the scenes
Compaction is fundamentally asynchronous, operating in the background by trailing the inactive
log segments. Under the hood, log compaction recruits dedicated threads on each replica — the
exact number of threads is given by the log.cleaner.threads broker property (defaulting to 1).
Compactionisdrivenbyfourmainproperties:
• log.cleaner.min.cleanable.ratio — the minimum ratio of the dirty log size to the total log
size for a log to be eligible for cleaning. The default value is 0.5. More on the dirty ratio in a
moment.
• log.cleaner.min.compaction.lag.ms — prevents records newer than a minimum age from
beingsubjectedtocompaction.Thedefaultvalueis0,implyingthereisnominimumtimethat
a record will persist before it may be compacted, subject to the constraint of the active log
segment.(Recall,onlyrecordsininactivesegmentsaresubjecttomanipulation.)
• log.cleaner.max.compaction.lag.ms — the upper bound on the compaction lag. Typically
usedwithlow-throughputtopics,wherelogsmightnotexceedthelog.cleaner.min.cleanable.ratio
and,assuch,willremainineligibleforcompactionforanunboundedduration.Thedefaultset-
tingisLong.MAX_VALUE,implyingthatcompactionisdrivenbythelog.cleaner.min.cleanable.ratio.
(Note,theupperboundisnotaharddeadline,beingsubjecttotheavailabilityofcleanerthreads
andtheactualcompactiontime.)
• log.cleaner.delete.retention.ms — the duration that the tombstone records are persisted
beforebeingpurgedfromthelog.Moreontombstoneretentioninamoment.
Thesettingsaboveactascluster-widedefaults.Theirper-topicequivalentsare:
• min.cleanable.dirty.ratio—inplaceof log.cleaner.min.cleanable.ratio.
• min.compaction.lag.ms—inplaceof log.cleaner.min.compaction.lag.ms.
• max.compaction.lag.ms—inplaceof log.cleaner.max.compaction.lag.ms.
• delete.retention.ms—inplaceof log.cleaner.delete.retention.ms.
Foreverylogbeingcompacted,thecleanermaintainstwologicalpoints—thecleanerpoint andthe
delete retention point. The cleaner point is the location in the log corresponding to the progress of
the cleaner through the log, dividing the log into two parts. The part older than the cleaner point
is called the log tail and corresponds to those segments that have already been compacted by the
cleaner. Conversely, the part of the log that is newer than the cleaner point is called the log head
andcorrespondstothosesegmentsthathaveyettobecompacted,includingtheactivesegment.The
diagrambelowillustratesthelocationofthesepointsinrelationtotheoveralllog.

Chapter14: DataRetention 228
Logcompaction
The cleaner thread prioritises the log with the highest ratio of the head size to the overall length
of the log — known as the dirty ratio. For a log to be selected for compaction, its dirty ratio must
exceedthelog.cleaner.min.cleanable.ratioor,alternativelytheoldestuncompactedrecordmust
exceedthelog.cleaner.max.compaction.lag.ms.

Chapter14: DataRetention 229
Toperformthecompaction,thecleanerscansthesegmentsintheheadportion,includingtheactive
segment, populating an in-memory hash table of unique record keys mapped to their most recent
offsetsinthelog.Allinactivelogsegmentsandtheirindexesarerecopied(includingthosethathave
been previously compacted), purging records that have been superseded – except for tombstones,
which are treated differently to conventional records. As the log cleaner works through the log
segments, new segments get swapped into the log, replacing older segments. This way compaction
doesnotrequiredoublethespaceoftheentirepartition;onlythefreediskspaceforoneadditional
logsegmentisrequired.
Compactionpreservestheorderofthesurvivingrecordsandtheiroriginaloffset;itwillneverreorder
records or manipulate them in a way that might confuse a prospective consumer or contradict the
producer’soriginalintent.Ineffect,compactionconvertsadenselypopulatedlogintoasparseone,
with ‘holes’ in offsets that should be disregarded by consumers. Reverting to what was stated in
Chapter 3: Architecture and Core Concepts, a consumer should not interpret the offsets literally —
usingoffsetspurelyasameansofinferringrelativeorder.
Thedeleteretentionpointlagsbehindthecleaner,prolongingthelifetimeoftombstonerecordsbyan
additional log.cleaner.delete.retention.ms on top of the log.cleaner.min.compaction.lag.ms
accordedtoconventionalrecords.Thelog.cleaner.delete.retention.mspropertyappliesequally
toalltopics,defaultingto86400000(24hours);itsper-topicequivalentisdelete.retention.ms.
Tombstone records are preserved longer by design. The motivation for preserving tombstones is
revealedinthefollowingscenario.Apublisheremitsrecordsforanentity,characterisedbyCreate
andUpdateevents.Eventually,whentheentityisdeleted,theproducerwillfollowwithatombstone
— indicating to the compactor and the consumer ecosystem that the entity in question should be
completelypurgedfromthetopicandanyprojections.Thismakessenseforaconsumerthathasnot
seenanytracesoftheentity.Iftheentityhasalreadybeendropped,thenprocessingCreate,Update
and tombstone records for it is a waste of the consumer’s time. It is more than a trivial waste of
resources;infact,itrunscontrarytotherequirementsofapureeventstore—theconsumershould
only be exposed to events that are legitimately required to reconstitute the snapshot of the current
state,havingbeeninitialisedwithacleanslate.
Theissuewithdeletingtombstonesbecomesapparentwhenconsideringaconsumeroperatingwith
minimal lag that is part-way through handling entity updates for a soon-to-be-deleted entity. Sup-
posetheproducerhaspublishedCreateandUpdateeventsforsomeentity,whichtheconsumerhas
caught up with. The consumer then begins to accrue lag (perhaps it has gone offline momentarily).
Meanwhile,theproducerwritesatombstonerecordfortheentityinquestion.Ifthecompactorkicks
inbeforetheconsumerresumesprocessing,theremovalofallrecordsforthekeyreferencedbythe
tombstonewouldcreateaninconsistencyontheconsumer—leavingitinastatewheretheentityis
retainedinperpetuity.Thehandlingoftombstonescreatesacontradictionbetweentherequirements
of hydration and subsequent replication — the former requires the tombstones to be deleted, while
thelatterneedstombstonesinplacetomaintainconsistency.
By allowing an additional grace period for tombstone records, Kafka supports both use cases. The
tombstone retention time should be chosen such that it covers the maximum lag that a consumer
mightreasonablyaccumulate.Thedefaultsettingof24hoursmaynotbesufficientwhereconsumers

Chapter14: DataRetention 230
areintentionallytakenofflineforlongperiods;forexample,non-productionenvironments.Also,it
might not be sufficient for systems that need to tolerate longer periods of downtime; for example,
toaccountforoperationalcontingencies.
Compaction is a resource-intensive operation, requiring a full scan of the log each time it is run.
The purpose of the log.cleaner.min.cleanable.ratio property (and its per-topic equivalent) is
to throttle compaction, preventing it from running continuously, in detriment to the broker’s
normal operation. In addition to the cleanable ratio, the compactor can further be throttled by
applying the log.cleaner.backoff.ms property, controlling the amount of time the cleaner thread
will sleep in between compaction runs, defaulting to 15000 (15 seconds). On top of this, the
log.cleaner.io.max.bytes.per.second property can be used to apply an I/O quota to log cleaner
thread,suchthatthesumofitsreadandwritediskbandwidthdoesnotexceedthestatedvalue.The
defaultsettingisDouble.MAX_VALUE,whicheffectivelydisablesthequota.
An example
To demonstrate the Kafka compactor in action, we are going to create a topic named prices with
aggressivecompactionsettings:
$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
--create --topic prices --replication-factor 1 --partitions 1 \
--config "cleanup.policy=compact" \
--config "delete.retention.ms=100" \
--config "segment.ms=1" \
--config "min.cleanable.dirty.ratio=0.01"
Normally, setting such small segment sizes and aggressive cleanable ratios is strongly discouraged,
asitcreatesanobscenelylargenumberoffilesandleadstoheavyresourceutilisation—everynew
record will effectively mark the partition as dirty, instigating compaction. This is done purely for
show, as with conservative settings the compactor will simply not kick in for our trivial example.
(Therecordswillbeheldintheactivesegment,wheretheywillremainoutofthecompactor’sreach.)
Next,runaconsoleproducer:
$KAFKA_HOME/bin/kafka-console-producer.sh \
--broker-list localhost:9092 --topic prices \
--property parse.key=true --property key.separator=:
Copy the following line by line, allowing for a second or two in between each successive write for
the log files to rotate. Don’t copy-paste the entire block, as this will likely group the records into a
batchontheproducer,placingthemallintotheactivesegment.

231
Chapter14: DataRetention
AAPL:279.74
AAPL:280.03
MSFT:157.14
MSFT:156.01
AAPL:284.90
IBM:100.50
PressCTRL-Dwhendone.
Now run the consumer. Assuming that the compactor has had a chance to run, the output should
beconstrainedtotheuniquerecordkeysandtheirmostrecentvalues.
| $KAFKA_HOME/bin/kafka-console-consumer.sh |                  | \   |
| ----------------------------------------- | ---------------- | --- |
| --bootstrap-server                        | localhost:9092   | \   |
| --topic prices                            | --from-beginning | \   |
| --property print.key=true                 |                  |     |
MSFT 156.01
AAPL 284.90
IBM 100.50
Also,wecanseetheeffectsofcompactiononthelogfiles:
tree -s /tmp/kafka-logs/prices-0
/tmp/kafka-logs/prices-0
| ├── [ 0]          | 00000000000000000000.index     |     |
| ----------------- | ------------------------------ | --- |
| ├── [ 156]        | 00000000000000000000.log       |     |
| ├── [ 12]         | 00000000000000000000.timeindex |     |
| ├── [ 10]         | 00000000000000000001.snapshot  |     |
| ├── [ 10]         | 00000000000000000002.snapshot  |     |
| ├── [ 10]         | 00000000000000000003.snapshot  |     |
| ├── [ 10]         | 00000000000000000004.snapshot  |     |
| ├── [ 10485760]   | 00000000000000000005.index     |     |
| ├── [ 77]         | 00000000000000000005.log       |     |
| ├── [ 10]         | 00000000000000000005.snapshot  |     |
| ├── [ 10485756]   | 00000000000000000005.timeindex |     |
| └── [ 8]          | leader-epoch-checkpoint        |     |
| 0 directories, 12 | files                          |     |
Theactivelogsegmentis00000000000000000005.log.Allpriorsegmentshavebeencompacted,leav-
inghollowsnapshotsintheirplace;theremaininguniquerecordswerecoalescedinto00000000000000000000.log.
Outofinterest,wecanviewitscontentsusingthekafka-dump-logs.shutility:

232
| Chapter14:                                                | DataRetention                                     |                  |     |                  |                  |      |               |     |
| --------------------------------------------------------- | ------------------------------------------------- | ---------------- | --- | ---------------- | ---------------- | ---- | ------------- | --- |
| $KAFKA_HOME/bin/kafka-dump-log.sh                         |                                                   |                  |     |                  | --print-data-log |      | \             |     |
| --files                                                   | /tmp/kafka-logs/prices-0/00000000000000000000.log |                  |     |                  |                  |      |               |     |
| Dumping /tmp/kafka-logs/prices-0/00000000000000000000.log |                                                   |                  |     |                  |                  |      |               |     |
| Starting                                                  | offset:                                           | 0                |     |                  |                  |      |               |     |
| baseOffset:                                               | 3 lastOffset:                                     |                  | 3   | count:           | 1 baseSequence:  |      | -1 ￿          |     |
| lastSequence:                                             |                                                   | -1 producerId:   |     | -1               | producerEpoch:   |      | -1 ￿          |     |
| partitionLeaderEpoch:                                     |                                                   |                  | 0   | isTransactional: |                  |      | false ￿       |     |
| isControl:                                                |                                                   | false position:  |     | 0 CreateTime:    |                  |      | 1577409425248 | ￿   |
| size:                                                     | 78 magic:                                         | 2 compresscodec: |     |                  | NONE             | crc: | 2399134455    | ￿   |
| isvalid:                                                  | true                                              |                  |     |                  |                  |      |               |     |
| offset: 3 CreateTime: 1577409425248 keysize: 4 valuesize: 6 ￿
| sequence:             | -1            | headerKeys:      |     | [] key:          | MSFT            | payload: | 156.01        |     |
| --------------------- | ------------- | ---------------- | --- | ---------------- | --------------- | -------- | ------------- | --- |
| baseOffset:           | 4 lastOffset: |                  | 4   | count:           | 1 baseSequence: |          | -1 ￿          |     |
| lastSequence:         |               | -1 producerId:   |     | -1               | producerEpoch:  |          | -1 ￿          |     |
| partitionLeaderEpoch: |               |                  | 0   | isTransactional: |                 |          | false ￿       |     |
| isControl:            |               | false position:  |     | 78               | CreateTime:     |          | 1577409434843 | ￿   |
| size:                 | 78 magic:     | 2 compresscodec: |     |                  | NONE            | crc:     | 2711186080    | ￿   |
| isvalid:              | true          |                  |     |                  |                 |          |               |     |
| offset: 4 CreateTime: 1577409434843 keysize: 4 valuesize: 6 ￿
| sequence: | -1  | headerKeys: |     | [] key: | AAPL | payload: | 284.90 |     |
| --------- | --- | ----------- | --- | ------- | ---- | -------- | ------ | --- |
ThisisshowingusthattheMSFTandAAPLrecordshavebeenbuncheduptogether,althoughoriginally
they would have appeared in different segments. The IBM record, having been published last, will
beintheactivesegment.
Theexistenceoftheactivesegmentisprobablythemostconfusingaspectwithrespecttocompaction,
catchingoutitsfairshareofpractitioners.Whenatopicappearstobepartiallycompacted,inmost
cases the reason is that the uncompacted records lie in the active segment, and are therefore out of
scope.
| Combining |     | compaction |     |     | with |     | deletion |     |
| --------- | --- | ---------- | --- | --- | ---- | --- | -------- | --- |
Asitwaspreviouslystated,itispossibletoassignbothdeleteandcompactpoliciestothelog.cleanup.policy
property(orcleanup.policy,asthecasemay be).Thequestionis:doesitmakesensetodoso?
When a topic is configured for bounded retention, the typical use cases that are being serviced
are bimodal replication of events or conventional event stream processing — where a consumer
ecosystem or a series of processing stages react to events from an upstream emitter. Unbounded
retention is typically a surplus to requirements; it is sufficient for the retention time to cover the
maximumforecastconsumerlag.

Chapter14: DataRetention 233
Conversely,whenatopiciscompacted,onenaturallyassumesthatitisbeingutilisedasthesource
of truth — where the preservation of state is an essential attribute — more so than the preservation
of discrete changes. Under this model, there is either a characteristic absence of a queryable data
source, or it may be intractable for the consumer to issue queries. This model enables unimodal
processing,whichisitsdominantadvantage.
Combining the two strategies might appear counterintuitive at first. It has been said: a compacted
topiccanbelogicallyequatedtoadatabase(oraK-Vstore)—themostrecentupdatescorresponding
torowsinatableoffinitesize.Slappingadeletionpolicyontopfeelslikewearebuildingadatabase
that silently drops records when it gets to a certain size (or age). This configuration is peculiar, at
theveryleast.
Nonetheless, a size-bound, compacted topic is useful in limited cases of change data capture and
processing. This can be seen as an extension of the conventional, event-driven replication model.
Inthepresenceoffast-movingdata,whereupdatesareself-containedandthetime-valueofdatais
low,theuseofcompactioncandramaticallyacceleratetheprocessingofdata.Moreoftenthannot,
entity deletion is a non-issue, and tombstones are excluded from the model (although they don’t
have to be). Here, the intention is not to conserve space or to enable unimodal processing; rather,
it is to reduce resource wastage on processing events that rapidly lose their significance. Because
the size of the topic is inherently constrained (by way of deletion), one can afford to run a more
aggressivecompactor,usingamuchlowerlog.cleaner.min.cleanable.ratioinconjunctionwith
smallersegmentsizes.Themoreaggressivethecompaction,thelowerthelikelihoodthatanobsolete
recordisobservedbyaconsumer.
Anexampleofwherethis‘hybrid’modelisusedistheinternal__consumer_offsetstopic,
usedtomanagestateinformationforconsumergroups.Thistopic’ssegmentsaresubjected
to both compaction and deletion. Compaction allows for the rapid reconstruction of con-
sumerstateonthegroupcoordinator,whiledeletionbindsthesizeofthetopicandactsas
anatural‘garbagecollector’ofconsumergroupsthathavenotbeenutilisedforsometime.
This chapter looked at how Kafka deals with data retention. We started with a tour of its storage
internals—understandinghowtopic-partitiondataonthereplicasmapstotheunderlyingfilesystem.
Amongthekeytakeawaysisthenotionofasegmentedlog,wherebythepartitionisbrokenupinto
aseriesofchunks—eachaccompaniedbyasetofindexes—permittingO(1) lookupsofrecordsby
theiroffsetortimestamp.Ofthelogsegments,themostrecentistheactivesegment.Onlyaproducer
may write to the active segment — a fundamental design decision that influences the remainder of
Kafka’sdataretentionmachinery.Andalso,onethatfrequentlycausesconfusion.
Kafka offers two independent but related cleanup policies — deletion and compaction. Deletion is
relatively straightforward — truncating log segments when the log reaches a certain size or where
theoldestrecordmaturestoacertainage.Asacleanuppolicy,deletionissuitablewhereconsumers

Chapter14: DataRetention 234
areexpectedtokeepupwiththedatasyndicatedoveratopic,orwhereanalternatemechanismfor
retrievinganylapseddataexists.
Compaction is the more elaborate cleanup policy, providing a fine-grained, per-record retention
strategy. The idea is to selectively remove records where the latter represent entity updates, and
where a more recent update record exists for the same key. Compaction leaves at least one record
thatrepresentsthemostrecentsnapshotofanentity—turninganeventstreamintoacontinuously
updatingdatastore.
Crucially, compaction is a best-effort endeavour — it ensures that at least one record for a given
key is preserved. There is no exactly-one guarantee when it comes to compaction. Depending on
various factors — the contents of the active segment and the dirty ratio — compaction might not
run, or having run, it might appear to have a partial effect. Also, compaction is not bound by a
deadline — being subject to the availability of cleaner threads, the backlog of competing partitions
inneedofcompaction,andtheactualcompactiontime.
The deletion and compaction policies may be used in concert, which is typically done to expedite
theprocessingofchangerecordswhileconstrainingthesizeofthelog.
Amongthem,Kafka’scleanuppoliciesprovidesufficientflexibilitytoaccommodatearangeofdata
retention needs — from event-driven services to accelerated stream processing graphs and fully-
fledgedevent-sourcingmodels.

Chapter 15: Group Membership and
Partition Assignment
The reader should be aware by now that partitions in a topic are allocated approximately evenly
among the live members of a consumer group, and that partition allocations for one group are
entirely independent of the allocations of any other group. On the whole, group membership is
pivotal to Kafka’s architecture; it acts as a load balancing mechanism and ensures that any given
partitionisassignedtoatmostonememberofagroup.
ThischapterexploresKafka’sgroupmembershipprotocolandtheunderlyingmechanismsbywhich
Kafka ensures that members are always able to make progress in the consumption of records, and
thatrecordsareprocessedintheprescribedorderandonatmostoneconsumerinagroup.
PartsofthischaptercontainsizabledosesoftheoryonKafka’sinnerworkings,andmight
not immediately feel useful to a Kafka practitioner. Nonetheless, an appreciation of the
underlyingmechanicswillgoalongwayinunderstandingthemorepracticalmaterial,such
assessiontimeouts,liveness,partitionassignment,andstaticmembership.
Group membership basics
A consumer group is a set of related consumers that contend for the assignment of a mutually
exclusivesetofpartitionsforthesubscribedtopics,suchthatagiventopic-partitioncombinationis
assigned to at most one consumer. We say ‘at most one’ to cover the case when all consumers are
offline or not responding. Rest assured — when all is well and there is at least one consumer in the
group,apartitionwillbeassignedtoexactlyoneconsumer.
Members of one consumer group cannot interfere with those of another group; in other words,
consumergroupsarecompletelyisolatedfromoneanother.
Establishing group membership
Kafka’s group management protocol is divided into two phases: group membership and state
synchronisation. The group membership phase is used to identify the active members of the group
and appoint a group leader. The state synchronisation phase is used by the group leader to derive
thepartitionassignmentforallgroupmembers,includingitself,andtodisseminatethisstateamong
the remaining population of consumers. The sequence diagram below illustrates the phases of the
protocol.Explanationswillfollow.

Chapter15: GroupMembershipandPartitionAssignment 236
Protocolphases
The group membership phase commences when a consumer sends a JoinGroupRequest to the
group’scoordinator.TherequestwillcontainthegroupID(specifiedbythegroup.idconfiguration
property),theinternalmemberIDoftheconsumer(whichisinitiallyemptyfornewconsumers),the
desiredsessiontimeout(specifiedbysession.timeout.ms),aswellastheconsumer’sownmetadata.
Upon receiving a JoinGroupRequest, the coordinator will update its state and select a leader from
thegroup,ifonehasnotbeenalreadyassigned.Thecoordinatorwillparktherequest,intentionally
delaying the response until all expected members of the group have sent their join requests. The
delayactsasasynchronisationbarrier,ensuringallmembersbecomesimultaneouslyawareofone
another.TheresponseswillcontainthesetofindividualmemberIDsandtheIDofthegroupleader.

Chapter15: GroupMembershipandPartitionAssignment 237
The coordinator of each group is implicitly selected from the partition leaders of the
internal topic __consumer_offsets, which is used to store committed offsets. The group’s
ID is hashed to one of the partitions for the __consumer_offsets topic and the leader of
that partition is taken to be the coordinator. By piggybacking on an existing leadership
election process, Kafka conveniently avoids yet another arbitration process to elect group
coordinators. Also, the management of consumer groups is divided approximately equally
acrossallthebrokersinthecluster,whichallowsthenumberofgroupstoscalebyincreasing
thenumberofbrokers.
State synchronisation
The responsibility of assigning topic-partitions to consumers falls upon the group leader, and is
performed in the state synchronisation phase of the protocol. The assignment (sub-)protocol is
conveniently tunnelled within the broader group management protocol and is concealed from the
coordinator — in the sense that the coordinator largely treats the contents of state synchronisation
messages as a ‘black box’. As such, the assignment protocol is often referred to as an embedded
protocol.Otherembeddedprotocolsmaycoexist,tunnelledinthesamemanner,potentiallybearing
norelationtopartitionassignment.
To make it abundantly clear, the terms ‘group leader’ and ‘group coordinator’ refer to different en-
tities.Thegroupleaderisaconsumerclientthatisresponsibleforperformingpartitionassignment;
thegroupcoordinatorisabrokerthatarbitratesgroupmembership.
Once group membership has been established by the completion of the first phase, the coordi-
nator will enter the AwaitSync state, waiting for the partition assignment to be performed on
the leader. The leader will delegate to an implementation of a ConsumerPartitionAssignor to
perform partition assignment, communicating the outcome of the assignment to the coordinator
inaSyncGroupRequest.
Inthemeantime,allmemberswillsendtheirSyncGroupRequestmessagestothecoordinator.Having
received the SyncGroupRequest from the leader, containing the partition assignments for each
member,thecoordinatorreplieswithaSyncGroupResponsetoallmembersofthegroup—informing
them of their individual partition assignments and completing the protocol. Having done so, the
coordinatorenterstheStablestate,andwillmaintainthisstateuntilgroupmembershipchanges.
As of version 0.9.0.0, Kafka separates the management of group membership from parti-
tion assignment. While group arbitration is performed on the coordinator, the partition
assignment is delegated to one of the members — the group leader. Before Kafka 0.9.0.0,
thecoordinatorwasresponsibleforbothfunctions.
Delayed rebalance
Having the coordinator reply with a JoinGroupResponse immediately after all expected members
have joined generally works well when a group is undergoing minor changes in population — for

Chapter15: GroupMembershipandPartitionAssignment 238
example, as a result of scaling events or one-off consumer failures. However, if group members are
joiningatarapidrate,thenumberofunnecessaryrebalanceswillgothroughtheroof.This‘swarm’
effectcantypicallybefeltonapplicationstartuporwhenpreviouslyconnectedclientsreconnecten
masse duetoanintermittentnetworkfailure.
ThisissuewasaddressedinKafka0.11.0.0aspartofKIP-134²⁹.Anewbrokerproperty—group.initial.rebalance.delay.ms
— was introduced for the group coordinator to allow additional time for consumers to join before
sendingJoinGroupResponsemessages,therebyreducingthenumberofrebalances.Thedelayisonly
applied when the group is empty; membership changes in a non-empty group will not incur the
initialrebalancedelay.Thedefaultvalueofthispropertyis3000(3seconds).
A drawback of the initial rebalance delay is its treatment of a singleton group, where only one
memberexists.Thisistypicaloftestscenariosandlocaldevelopment.WhenusingKafkaforsmall-
scale testing or development, it is recommended that group.initial.rebalance.delay.ms is set to
0.
Changing group membership
The coordinator maintains a generation ID — a monotonically-increasing 32-bit integer — corre-
spondingtoapoint-in-timesnapshotofthegroupmembershipstate.Whenanewmemberwishesto
joinanexistinggroup,theywillsendaJoinGroupRequesttothecoordinator.Conversely,amember
may gracefully leave the group at any point by sending a LeaveGroupRequest. Either action will
result in the coordinator entering the Joining state, awaiting for all remaining members to rejoin
thegroup.
While in the Joining state, the coordinator will reject heartbeats and other operations with a
REBALANCE_IN_PROGRESS error. This will be detected by the remaining members upon their next
heartbeat attempt, forcing them to rejoin with their existing member IDs. Once all members
have successfully rejoined, the coordinator will increment the generation ID and transition to the
AwaitSync state — initiating the synchronisation phase of the protocol and causing the rebalancing
ofpartitionassignmentsonthegroupleader.Uponsuccessfulrebalancingandstatesynchronisation,
thecoordinatorwillonceagainsettleintheStablestate.
Itmaybepossibleforamembertomissagenerationalchangeingroupmembership—for
example,duetoadelayedheartbeat—andtherebybeforciblyexcludedfromthegroupby
thecoordinator.Themembermightstilllegitimatelybelieveittobeapartofthegroupand
may attempt to fetch records or send commit requests. These members are referred to as
‘zombies’. The use of an incrementing generation ID on the coordinator acts as a fencing
mechanism — rejecting requests with an ILLEGAL_GENERATION error where the presented
generationIDdoesnotmatchthecoordinator’sgenerationID.Thisforceszombieconsumers
todiscardtheirassignments,cleartheirmemberID,andrejointhegroupfromacleanslate.
²⁹https://cwiki.apache.org/confluence/display/KAFKA/KIP-134%3A+Delay+initial+consumer+group+rebalance

Chapter15: GroupMembershipandPartitionAssignment 239
State synchronisation barrier
When transitioning partition assignments from one consumer to another — following a member
joinorleaveevent—theconsumersmustcollectivelyensurethatthereisnotime-overlapbetween
the assignment of a partition to a new consumer and the release of the partition from its previous
holder.Whilethetime-separationbetweentherevocationandtheassignmentisundesirablefroma
performance standpoint, allowing the two events to overlap in time is unacceptable. Therefore, we
mustconcludethatsomedegreeofseparationisnecessaryandacceptable.
Toachievethisseparation,aconsumerclientmustinformitsuserofanyimpendingchangestothe
partitionassignments,whichisdonethroughanorg.apache.kafka.clients.consumer.ConsumerRebalanceListener
callbackinterface,listedbelowsanstheJavadoccomments.
public interface ConsumerRebalanceListener {
void onPartitionsRevoked(Collection<TopicPartition> partitions);
void onPartitionsAssigned(Collection<TopicPartition> partitions);
default void onPartitionsLost(
Collection<TopicPartition> partitions) {
onPartitionsRevoked(partitions);
}
}
A rebalance listener is registered with a KafkaConsumer client when calling one of its overloaded
subscribe()methods.
After completing the group join, and just before initiating the state synchronisation phase, a
consumer will assume that all partitions that it presently holds will be revoked by the leader, and
willinvoketheonPartitionsRevoked()callbackaccordingly,passingitthecompletesetofpartitions
that the consumer had owned prior to the rebalance. The execution of the callback may block for
as long as necessary, waiting for the application to complete any in-flight activities. Only when
the callback completes, will the client send a SyncGroupRequest message to the coordinator. (The
synchronisationbehaviourisdifferentwhenincrementalcooperativerebalancingisenabled,which
willbediscussedshortly.)
Later, upon completion of the state synchronisation phase, the consumer will learn of its updated
assignmentsandwillinvokeConsumerRebalanceListener.onPartitionsAssigned()withtheparti-
tionsthattheconsumernowowns.(Thismayincludepartitionsitpreviouslyownediftheyhaven’t
been reassigned.) TheonPartitionsRevoked() method effectively acts as a barrier — satisfying the
strict requirement that the onPartitionsRevoked() callback is invoked on all consumers before
onPartitionsAssigned()isinvokedonany consumer.
Without this barrier in place, it would have been possible for the new assignee of a partition to
commence the processing of records before the outgoing consumer has had a chance to relinquish

Chapter15: GroupMembershipandPartitionAssignment 240
control over that partition. The barrier might result in a short period of time where none of
the partitions are being processed during the rebalancing phase. Kafka’s design documentation
colloquially refers to this as a stop-the-world effect, akin to its namesake in the area of garbage
collection (GC) algorithms. Indeed, a stop-the-world rebalance in Kafka has the same drastic effect
onthethroughputofconsumersastheGChasontheapplicationasawhole.AndunlikeGC,which
affectsoneprocessatatime,astop-the-worldrebalanceaffectsallconsumerssimultaneously.
The final callback method — onPartitionLost() — signals to the application the loss of prior
partitions due to a forced change of ownership. This will happen when a consumer fails to emit a
heartbeatwithinasetdeadline,therebybeingconsidered‘dead’bythecoordinator.Theimplications
ofpartitionlossaredescribedindetailinthesubsequentsectiononlivenessandsafety.
Incremental cooperative rebalancing
One of the main drawbacks of the original state synchronisation protocol, also known as theeager
rebalancing protocol, is that it only allocates one phase to the entire rebalancing operation, which
is one request-response message exchange in practical terms. This results in consumers taking an
overly pessimistic view on the revoked partitions — forced to assume that all partitions might be
revoked — whereas in reality only a subset of the partitions will typically be reassigned following
a rebalance. This unnecessarily triggers the consumers to clean up all partitions, extending the
durationoftherebalancingphase,andtherefore,theextentofthestop-the-worldpause.
Incrementalcooperativerebalancingisarecentimprovementofthestatesynchronisationprotocol,
introduced in Kafka 2.4.0 under KIP-429³⁰. Under the cooperative model, the join and rebalance
phases may be repeated, each introducing an incremental change to partition assignment. The
cooperativerebalancingprotocolintroducesasecond,follow-upjoin-rebalanceroundimmediately
followingthefirstjoin-rebalance.
Cooperativerebalancingaddsanewrebalanceprotocolversion,whichisreferredtobytheCOOPERATIVE
constant (0x01 byte representation), in contrast to the original EAGER constant (0x00). The use of
cooperative rebalancing is optional, enabled automatically in response to the chosen rebalancing
strategy. (Rebalancing strategies will be discussed in one of the subsequent sections.) When the
cooperativeprotocolisinforce,theconsumerdoesnotinvoketheonPartitionsRevoked()callback
before sending the SyncGroupRequest; instead, the callback is deferred to such time when the
SyncGroupResponse is received, and only for a non-empty set of revoked partitions. The client will
then invoke the onPartitionsAssigned() callback, passing it the set of newly assigned partitions,
even if that is an empty set. Having processed the callbacks, the client will initiate a second rejoin,
followedbyasubsequentrebalance.
The trick to making this work is to separate the revocations from the assignments by exactly one
round,sothatthefirstjoin-rebalanceroundcomprisesexclusivelyofrevocations,orofassignments
where no prior assignees exist (as in the case of the initial join). The second join-rebalance round
comprises only of new assignments where a revocation was communicated in the previous round.
³⁰https://cwiki.apache.org/confluence/display/KAFKA/KIP-429%3A+Kafka+Consumer+Incremental+Rebalance+Protocol

Chapter15: GroupMembershipandPartitionAssignment 241
Thus, for any given partition, the onPartitionsRevoked() callback will always complete on the
outgoingassigneebeforetheonPartitionsAssigned()callbackis instigatedonthenewassignee.
Between the two rounds,the groupis said to be in anunbalanced state — not all partitions may be
assigned to a consumer, even if there are sufficient members in the group. By contrast, the eager
rebalance protocol will ensure that a partition is always assigned to a consumer at the conclusion
of a synchronisation round, providing that at least one group member exists. The unbalanced state
isresolveduponthecompletionofthesecondjoin-rebalanceround.
Themainbenefitofcooperativerebalancingisincommunicatingtheexactrevocationstoconsumers,
rather than coercing them towards a worst-case assumption. By reducing the amount of cleanup
work consumers must perform during the onPartitionsRevoked() callback, the duration of the
rebalancingstageisreduced,asistheeffectofthestop-the-worldpause.
Asecondarybenefitisthemarkedimprovementofrebalancingperformanceandreduceddisruptions
to consumers in the event of a rolling bounce — where a rolling restart of a consumer population
resultsinrepeatleave,join,andstatesynchronisationevents.Rebalancingstilloccurs,buttheimpact
ofthisisgreatlyreducedasonlythebouncedclients’partitionsgetshuffledaround.
Intheory,theprotocolmaybeextendedinthefuturetoaccommodateanarbitrarynumberofjoin-
rebalanceroundsandmayevenspacethemoutifnecessary.Inthe(hypothetical)extendedprotocol,
thebalancedstatewouldbeincrementallyconvergedonafterthecompletionofN rounds.
Anotherprospectiveenhancementwouldbetheadditionofa‘scheduledrebalancetimeout’,grant-
ingtemporarilydepartingconsumersagraceperiodwithinwhichtheycanrejoinwithoutsacrificing
priorpartitionassignments.Thisismeanttocombatthefulleffectsofarollingbouncebyavoiding
revocations.
Inaddition,ascheduledrebalancetimeoutcoulddealwithself-healingconsumers,wherethefailure
of a consumer is detected by a separate orchestrator and the consumer is subsequently restarted.
(Kubernetes is often quoted as a stand-in for that role.) With a sufficiently long timeout, the
orchestratorcandetectthefailureofaconsumerprocessandrestartitscontainerwithoutpenalising
theprocessbywayofrevokingitspartitions.Thisallowsthefailedprocesstoberestoredfasterand
alsominimisestheimpactonthehealthyconsumers.(Similarcharacteristicscanbeachievedusing
theexistingstaticmembershipprotocol,aswewillshortlysee.)
The main challenge of the cooperative model is the added join-rebalance round, leading to more
network round-trips. For cooperative rebalancing to be effective, the underlying partition assign-
ment strategy must be sticky — in other words, it must preserve the prior partition assignments as
muchaspossible.Withoutastickyrebalancingstrategy,thecooperativemodelreducestotheeager
rebalancingmodel,albeitwithmoreoverheadandcomplexity.

Chapter15: GroupMembershipandPartitionAssignment 242
A word of caution: Enabling the cooperative protocol changes the semantics of
the ConsumerRebalanceListener callback. Under the eager rebalance protocol, the
onPartitionsAssigned()ishandedthecompletesetofassignedpartitions;whereas,under
thecooperativeprotocol,theonPartitionsAssigned()isgivenjustthesetofnewpartitions
thatwereacquiredsincethelastrebalance.
Static membership
Static group membership takes a different approach to the conventional (join-triggered) partition
assignmentmodelbyassociatinggroupmemberswithalong-term,stableidentity.Underthestatic
model,membersareallowedtoleaveandjoinagroupwithoutforfeitingtheirpartitionassignment
orcausingarebalance,providedtheyarenotawayforlongerthanasetamountoftime.
Themainrationalebehindstaticmembershipistwofold:
1. Toreducetheimpactofrebalancingandtheassociatedinterruptionswheninelasticconsumers
bounce;and
2. To allow for an external health checking and healing mechanism for ensuring liveness of the
consumerecosystem.
Thesecondpointwillbediscussedlaterinthischapter,inthebroadercontextoflivenessandsafety
concerns.Forthetimebeing,weshallfocusonthefirstpoint.
An‘inelastic’consumerisonethatisexpectedtopersistforanextendedperiodoftime,occasionally
goingdownforashortwhile—forexample,toperformaplannedsoftwaredeploymentorasaresult
of an intermittent failure. In both cases, certain assumptions might be made as to the duration of
theoutage.Thepopulationofinelasticconsumerstendstobemoreorlessfixedinsize.Bycontrast,
an ‘elastic’ consumer is likely to be spawned in response to an elevated load demand and will be
terminatedwhenthedemandtapersoff.
Static assignment works by making minor amendments to the group management and state syn-
chronisation protocol phases. It also adds a property — group.instance.id — to the consumer
configuration,beingafree-formstableidentifierfortheconsumerinstance.
Upon joining the group, a static member will submit its member ID in a JoinGroupRequest, along
with the group instance ID in the member metadata. If the member ID is empty — indicating an
initialjoin—thecoordinatorwillissueanewmemberID,asperthedynamicmembershipscenario.
In addition to issuing a member ID, the coordinator will take note of the issued ID, associating it
locally with the member’s group instance ID. Subsequent joins with an existing member ID will
proceedasperthedynamicgroupmembershipprotocol.
Having joined the group, the consumer will impart its group instance ID to the group leader, via
the coordinator. Like dynamic members, static members will have a different member ID on each

Chapter15: GroupMembershipandPartitionAssignment 243
join,renderingit largelyuseless forcorrelatingpartition assignmentsacrossgenerations.Thebuilt-
in assignor implementations have added provisions for this — using the group instance ID, where
oneissupplied,inplaceofthememberID.
Unlikeitsdynamiccounterpart,astaticconsumerleavingthegroupwillnotsendaLeaveGroupRequest
tothecoordinator.Whentheconsumereventuallystartsup,itwillsimplyjoinwithanewmember
ID, as per the explanation given above. The coordinator will still enforce heartbeats as per the
consumer-specified session.timeout.ms property. If a bounced consumer fails to reappear within
the heartbeat deadline, it will be expunged from the group, triggering a rebalance. In other words,
thereisnodifferenceinthehealthcheckbehaviourbetweenthestaticanddynamicprotocolsfrom
the coordinator’s perspective. The session timeout is typically set to a value that is significantly
higherthanthatforanequivalentdynamicconsumer.Thereasonsforthiswillbediscussedlater.
With the recent introduction of incremental cooperative rebalancing and the support for
staticgroupmembership,itislikelythatthegroup.initial.rebalance.delay.msproperty
willbedeprecatedinthenearfuture.
Oneofthechallengeswiththestaticgroupmembershipmodelistheaddedadministrativeoverhead
—namely,theneedtoprovisionuniquegroupinstanceIDstoeachpartakingconsumer.Whilethis
couldbedonemanuallyifneedbe,ideally,itisperformedautomaticallyatdeploymenttime.Atany
rate,ablunderinthemanualprocessoradefectinthedeploymentpipelinemightleadtoasituation
wheretwoormoreconsumersendupwithidenticalgroupinstanceIDs.Thisbegsthequestion:will
twoidentically-configuredstaticconsumersleadtoanon-exclusivepartitionassignment?
Kafka deals with the prospect of misconfigured consumers by implementing an internal fencing
mechanism.InadditiontodisclosingitsgroupinstanceIDinthegroupmembershipphase,thecon-
sumerwillalsoconveythisinformationinSyncGroupRequest,HeartbeatRequest,andOffsetCommitRequest
messages. The group coordinator will compare the disclosed group instance ID with the one on
record, raising a FENCED_INSTANCE_ID error if the corresponding member IDs don’t match, which
propagatestotheclientintheformofaFencedInstanceIdException.
Fencingisbestexplainedwithanexample.SupposeconsumersC0andC1havebeenmisconfigured
to share an instance ID I0. Upon its initial join, C0 presents an empty member ID and is assigned
M0 bythecoordinator.ThecoordinatorrecordsthemappingI0 →M0.C1thendoesthesame;the
coordinatorassignsM1toC1andupdatestheinternalmappingtoI0→M1.WhenC0laterattempts
tosyncwiththeI0-M0tuple,thecoordinatorwillidentifytheoffendingmappingandrespondwith
aFENCED_INSTANCE_IDerror.
If C0 completes both a join and a synchronisation request before C1 does its join, then the incon-
sistencywillbeidentifiedduringthesynchronisationbarrier,justpriortothecoordinatorreplying
withaSyncGroupResponse.Eitherway,oneoftheconsumerswillbefenced.

Chapter15: GroupMembershipandPartitionAssignment 244
Liveness and safety
It would be rather nice if consumers and brokers never failed, networks were reliable, and group
membershipchangeswerealwayscleanlydemarcatedbyjoinandleaverequests.Unfortunately,this
cannotbe;wehavetocontendwiththebleakharshnessofreality,whichisparticularlyexacerbated
whendealingwithdistributedsystems.
In the realm of distributed systems, like in concurrent computing, there are two fundamental
propertiesofinterest:liveness andsafety.
Liveness is a property of a system that requires it to make progress despite its internal components
competingforsharedresources.Asystemthatexhibitslivenesswillguaranteethatsomething‘good’
willeventually happen.
Safetyisanorthogonalpropertythatguaranteesthatnoneofthecriticalinvariantsofasystemwill
ever be violated. In other words, something ‘bad’ will never happen under a system that exhibits
safety.
The18ʰcenturyEnglishjuristWilliamBlackstoneexpressedaformulationthathassincebecomea
stapleoflegalthinkinginAnglo-Saxonjurisdictions.Blackstone’sformulationwas:Itisbetterthat
tenguiltypersonsescapethanthatoneinnocentsuffer.
SirWilliamBlackstone(1723–1780)
John Adams — the second president of the United States — remarked on Blackstone’s formulation
that, paraphrasing: Guilt and crimes are so frequent, that all of them cannot be punished, whereas

Chapter15: GroupMembershipandPartitionAssignment 245
the sanctity of innocence must be protected. One way of looking at this remark, although it was
not conveyed verbatim, is that the guilty will likely reoffend, and therefore will likely be caught
eventually.Conversely,iftheinnocentweretolosefaithintheirsecurity,theywouldbemorelikely
tooffend,forvirtueitselfisnosecurity,touseAdams’sownwords.
JohnAdams(1735—1826)
Unifying Blackstone’s formulation with Adams’s, we can derive the following: The guilty should
be punished eventually, while the innocent must never be punished. How does this relate to our
previousdiscussionondistributedsystems?Asithappens,theaforementionedstatementcombines
both liveness and safety properties. Enacting of punishment for the guilty, at some indeterminate

Chapter15: GroupMembershipandPartitionAssignment 246
pointintime,isamanifestationofliveness.Ensuringthattheinnocentareneverpunishedissafety.
Interestingly,thelivenesspropertymaynotbesatisfiedinafiniteexecutionofadistributedsystem
because the ‘good’ event might only theoretically occur at some time in the future. Eventual
consistency is an example of a liveness property. Returning to the Blackstone-Adams formulation,
thereisnorequirementthattheguiltyarecaughtwithintheirlifetime,orever,forthatmatter.
Whatisthepointofapropertyifitscardinaloutcomemayneverbesatisfied?Formally,theliveness
propertyensuresthataneventualtransitiontoadesirablestateistheoreticallypossiblefromevery
conceivablestateofthesystem;inotherwords,thereisnostatewhichcategoricallyprecludesthe
‘good’eventfromsubsequentlyoccurring.Theoretically,thereisnocrimethatcannotbesolved.
By comparison, the safety property can be violated in a finite execution of a system. Just one
occurrenceofa‘bad’eventissufficienttovoidthesafetyproperty.
Kafka gives us its assurance, that providing at least one consumer process is available, then all
recordswilleventuallybeprocessedintheordertheywerepublished,andnorecordwillbedelivered
simultaneouslytotwoormorecompetingconsumers.Granted,inreality,thisguaranteeonlyholdsif
youuseKafkacorrectly.WehavewitnessednumerousexamplesinChapter10:ClientConfiguration
where a trivially misconfigured, or even a naively-accepted default property will have a drastic
impactonthecorrectnessofasystem.Letuslooktheotherwayforthemoment,blissfullypretending
thatclientshavebeencorrectlyconfiguredandtheapplicationsaredefect-free.
Howdoesallthisrelatetolivenessandsafety?Thepreservationofrecordorderandtheassignment
ofanygivenpartitiontoatmostoneconsumerisamanifestationofthesafetyproperty.Theeventual
identification of a failed consumer and the prompt rebalancing of partition assignments takes care
ofliveness.
Kafkasatisfiesthelivenesspropertyintwoways:
1. Checkingavailability—byrequiringconsumerstoperiodicallyexchangeheartbeatswiththe
group coordinator, thereby indicating that the consumer process is running on the host and
thatanetworkpathisavailablebetweentheconsumerprocessandthecoordinatornode.
2. Checking progress — by requiring that the consumer periodically invoke poll(), thereby
indicatingthatitisabletohandleitsshareofthepartitionassignment.
TheconsumerwillsendaHeartbeatRequestmessageatafixedintervalfromadedicatedheartbeat
thread.Thecoordinatormaintainstimersforeachconsumer,resettingtheclockwhenaheartbeatis
received.Havingreceivedtheheartbeat,thecoordinatorwillreplywithaHeartbeatResponse.Con-
versely,ifthecoordinatordoesnotreceiveaheartbeatwithinthetimespecifiedbythesession.timeout.ms
consumer property, it will assume that the consumer process has died. The coordinator will then
transitiontotheJoiningstate,causingarejoinofallremainingmembers.Whilethesessiontimeout
is configured on the consumer, it is communicated to the coordinator inside a JoinGroupRequest
message,withthedeadlinesubsequentlyenforcedbythecoordinator.

Chapter15: GroupMembershipandPartitionAssignment 247
Thesession.timeout.mspropertydefaultsto10000(10seconds).Itisrecommendedthatthisvalue
is at least three times greater than the value of heartbeat.interval.ms, which defaults to 3000
(3 seconds). As a further constraint, the value of session.timeout.ms must be in the range speci-
fied by the group.min.session.timeout.ms and group.max.session.timeout.ms broker properties,
being 6000 (6 seconds) and 1800000 (30 minutes), respectively, by default. By binding the range
of the session timeout values on the broker, one can be sure that no client will connect with its
session.timeout.mssettingoutsidethepermittedrange.
Whiletheavailabilitycheckisperformedonthecoordinatornode,theprogresscheckisperformed
locallyontheconsumer.Thebackgroundheartbeatthreadwillmaintainitsperiodicheartbeatingfor
longasthelastrecordedpolltimeiswithinthedeadlinespecifiedbythemax.poll.interval.msprop-
erty, which defaults to 300000 (5 minutes). If the application fails to invoke KafkaConsumer.poll()
withinthespecifieddeadline,theheartbeatthreadwillcease,andaLeaveGroupRequestwillbesent
tothecoordinator.Statedotherwise,theheartbeatthreaddoublesupasawatchdogtimer—assoon
as the poll deadline is missed, the client internally ‘self-destructs’, causing a leave, followed by a
rejoin.
Theclientshouldassignsession.timeout.msandheartbeat.interval.msvaluesbasedontheappli-
cation’s appetite for failure detection. Shorter heartbeat timeouts result in quicker failure detection
at the cost of more frequent consumer heartbeating, which can overwhelm broker resources and
leadtofalse-positivefailureevents—wherebyaliveconsumerismistakinglydeclareddead.Longer
timeouts lead to less sensitive failure detection, but may leave some partitions unhandled in the
eventofconsumerfailure.
A certain degree of process predictability and some amount of slack in the session timeout are
required for a consumer to consistently meet its heartbeat deadline — the “prove to me that you
are still alive” check. Given a suitably reliable network and unsaturated consumer processes with
regular garbage collector (GC) activity, the heartbeating process can be made to work reliably and
predictably—evenintheabsenceofagenuinelydeterministicruntimeenvironment.Intheauthor’s
experience, the default values for the heartbeat interval and the session timeout are sufficient in
most scenarios, occasionally requiring adjustments to compensate for heavily loaded consumer
applicationsornetworkcongestion.
Thesituationwiththeprogresscheck—the“provetomethatyouarestillconsumingrecords” audit
— is somewhat more challenging, as it relies on a round of polling to complete within a bounded
time. Depending on what the consumer does with each record, there is little one can do to ensure
thattheprocessingofrecordswillunconditionallycompletewithinasettimeframe.
When faced with nondeterministic record processing, one way of improving the situation is to cap
the number of records that will be returned to the application by KafkaConsumer.poll(). This
is controlled by the max.poll.records consumer property, which defaults to 500. This doesn’t
changethewayrecordsarefetchedfromthebroker,northeirquantity;instead,thepoll()method
artificially limits the number of records returned to the caller. The excluded records will remain
stashedinthefetchbuffer—tobereturnedinasubsequentcalltopoll().
In reducing the number of records returned from poll(), the application effectively slackens its

Chapter15: GroupMembershipandPartitionAssignment 248
processingobligationsbetweensuccessivepolls,increasingthelikelihoodthatacyclewillcomplete
before the max.poll.interval.ms deadline elapses, particularly if the average and worst-case pro-
cessingtimesofarecordarewellknown.
One might think that liveness and safety only apply to niche areas — low-level systems
programming,operatingsystems,anddistributedconsensusprotocols.Inactualfact,weare
never completely insulated from these properties — we might just experience their effects
indifferentandoftensubtleways,sometimesbarelyrealisingit.
Dealing with failures
Whilst an average (mean or median) processing time can often be derived empirically, the distribu-
tion of processing times may, and often does, exhibit a ‘long tail’. In other words, the worst-case
processing time might not be bounded. To illustrate this, consider a fairly typical event streaming
examplewheretheprocessingofarecordresultsintheupdatingofadatabaseorperhapsinvoking
some downstream service. These sorts of operations are inherently fault-prone and may time out,
requiring retries. In the worst-case scenario, a downstream dependency might be experiencing
downtime, leaving the consumer in an indefinite retry loop. Clearly, even with one backlogged
record,theconsumermightnotmakethemax.poll.interval.msdeadline.Sowhattodo?
Unfortunately,Kafkadoesnothaveananswerofitsown.Theuserislefttofendforthemselves.As
ithappens,therearefivestrategies,atleast,fordealingwiththis:
1. Setanabsurdlylargemax.poll.interval.msvalue,toeffectivelydisabletheprogresscheck.
2. Maintain a reasonable progress deadline, allowing Kafka to detect progress failure and rebal-
ancethegroup.
3. Detectanimpendingmax.poll.interval.msdeadlineandvoluntarilyrelinquishthesubscrip-
tionattheconsumer.
4. Implement a record-level deadline within the consumer, such that if a record fails to make
progresswithinasettimeframe,itisrecirculatedbacktothetail-endoftheoriginaltopic.
5. Implementarecord-leveldeadline;therecordwillbeskippedifitisunabletomakethedeadline,
and,ideallysenttoadead-lettertopicforsubsequentpost-mortem.
The first option takes the stance of“do it ‘till it’s done, whatever it takes”. This may be appropriate
ifthecorrectnessoftheapplicationdependsonhavingeveryrecordprocessedandintheorderthat
the records appear in the topic. We are effectively saying that there is no point progressing if the
processcannotdoitsjob,soitmightaswellstalluntilthedownstreamissueisrectified.
The main drawback of this approach is that it assumes that failure to make progress can only be
attributed to an external cause, such as the failure of a downstream dependency. It fails to take
into account potential failures on the consumer, such as a software defect that may have caused a
deadlock,orsomeotherlocal contingencythatisimpedingprogress.

Chapter15: GroupMembershipandPartitionAssignment 249
Thesecondoptionyieldsasimilaroutcometothefirst,inthatitwillnotprogresstheconsumption
of records on the partition containing the troublesome record. The progress check is still in force,
meaning that the poll loop will time out, and the coordinator will exclude the consumer from
the group, triggering a partition rebalance. The new assignee of the troublesome partition will
presumablyexperience thesameissue asthe outgoingconsumer,andtimeout inthe samemanner.
The previous assignee would have rejoined the group, and, in doing so, will have become eligible
for another share of partition assignments. This cycle will continue until the downstream issue is
resolvedandtheconsumersareeventuallyabletomakeprogress.Theadvantageofthisapproachis
thatitmakesnoassumptionsastothecauseoftheproblem.Iftheprogresswasoriginallyimpeded
byadeadlockedconsumer,thenthenewconsumershouldproceedwithoutahitch.Italsorequires
noadditionalcomplexityontheconsumer’sbehalf.
ThisapproachisidiomatictoKafka,butitisnotwithoutitsdrawbacks.Byrebalancingthepartitions
andlettingthenewassigneetimeout,theoverallthroughputofthetopicmaybeimpacted,causing
periodic stutter. Another, more serious drawback, is that although Kafka will detect consumer
failure, the consumer itself might not. Kafka will transfer ownership of the impacted partitions
to the remaining consumers, while the outgoing consumer might assume that it still holds those
partitions and will slowly work through its backlog. This problem is explored in more detail in the
sectiontitled‘Dealingwithpartitionexclusivity’.
The third strategy — voluntarily relinquishing a subscription — is an evolution of the second
approach, adding timing logic and progress checks to the poll-process loop, so that the consumer
process can preempt an impending deadline failure. This would involve proactively cleaning up
its state and unsubscribing. The preemption mechanism implies either some form of non-blocking
processing of records or the ability to interrupt the poll-process thread so that it does not block
indefinitely.Onceasubscriptionisforfeited,theconsumerimmediatelyresubscribes—resettingits
statewithinthegroup.
Thebenefitofthisapproachisthatitmaintainsthestrongestsafetyguaranteeswithrespecttorecord
orderingandat-least-oncedelivery—muchlikethefirsttwoapproaches.Akintothesecondstrategy,
it satisfies the liveness property by proactively yielding its subscription, while still allowing the
coordinator to detect consumer failures. This strategy has the added safety advantage of enforcing
recordprocessingexclusivitybyaddingaconservativelocalfailuredetectionmechanism,ensuring
thatrecordprocessingdoesnotoverrun.
A minor drawback of this model is that it causes a rebalance as part of forfeiting the subscription,
shortly followed by a second rebalance as the consumer rejoins the pack. Each rebalance will
disrupt all consumers, albeit for a short time. In Kafka parlance, this is referred to as ‘bouncing’ —
wherebyconsumerscomeinandoutofthegroup,causingnoneteffect,butforcinganunnecessary
reshuffling of partition assignments. To be fair, the behaviour of this strategy with respect to
rebalancing is no worse than strategy #2. Note that the effect of rebalancing under this strategy
cannotbeamelioratedbyemployingastaticmembershipmodelbecauseunsubscribingfromatopic
willcauseanimplicitrebalance.
Thefourthapproach—implementingarecord-leveldeadline—essentiallyinvolvesstartingatimer
justbeforeprocessingarecordandensuringthatnooperationblocksbeyondtheprocessingdeadline.

Chapter15: GroupMembershipandPartitionAssignment 250
When this deadline is reached, the consumer takes corrective action. In this variant, the corrective
actioninvolvesrepublishingtherecordtoitsoriginaltopic,suchthatitwillbedealtwitheventually
bysomeconsumer—theconsumerthatrepublishedtherecordorsomeotherconsumerifthetopic’s
partitionswerereassignedbetweenre-queuingtherecordanditssubsequentconsumption.
Therecord-leveltimeoutmustbesignificantlyshorterthanmax.poll.interval.msforittobeeffec-
tive.Infact,itshouldbejustunderthequotientofdividingmax.poll.interval.msbymax.poll.records
—allowingfortheworst-casescenarioforallrecordsinthebatch.(Worsethanworst,ifsuchaphrase
isgrammaticallyadmissible.)
This model is predicated on the consumers’ capacity to arbitrarily reorder records; in other words,
itonlyworksifpreservingtheoriginalrecordisnotessentialtothecorrectoperationofthesystem.
Thisistypicallynotthecaseinmosteventstreamingscenarios,butitmaybesuitablewhenrecords
are entirely self-contained and independent of one another, or when the records can be trivially
reordered on the consumer. It should also work well in more traditional peer-to-peer message
queuing scenarios, where messages might correspond to discrete tasks that can be processed out
of order. Consider, for example, a queue-based video transcoding application, where each record is
a pointer to a media file stored in an object store (such as AWS S3) along with instructions for the
target format, resolution, bitrate, etc. Now, suppose the consumer-end of the transcoder requires a
licensedcodecforaspecificvideo,andisexperiencingaproblemwithadownstreamlicenseserver.
It would be reasonable for the transcoder to re-queue the record, leaving it in the queue until such
timethatthelicenseserverisbackonline.
There are several potential variations of the strategy above. The producer might include an expiry
timeintherecordindicatinghowlongthetaskshouldbeinaqueuebeforeitisdeemeduselesstothe
business.Theconsumerwouldonlyattempttore-queuetherecordforaslongasithasn’toutlivedits
usefullifespan.Inanothervariation,theconsumermaywanttolimitthenumberofretryattempts.
This can be accomplished by setting a retry counter when the record is first published, validating
and decrementing it on each subsequent re-queuing attempt. When the record no longer qualifies
for re-queuing, it will be discarded. The consumer might also wish to log this fact and publish a
copyofthediscardedrecordtoadead-lettertopic.
Republishing records mutates the topic for all consumer groups. As such, reordering strategies are
onlyeffectiveforpoint-to-pointmessagingscenarios,involvingjustoneconsumergroup.Inorderto
applyareorderingstrategyto(multi)point-to-multipointscenarios,wheremultipleconsumergroups
share a source topic, it is necessary to implement a fan-out topology. This involves transforming a
(multi)point-to-multipointtopicintoa(multi)point-to-pointtopic,coupledtoseveralpoint-to-point
topics — one for each consumer group. The fan-out model eliminates sharing — each consumer
groupgetsaprivatetopicwhichit maymutateasitchooses.

Chapter15: GroupMembershipandPartitionAssignment 251
Topicfanout
Thefinalstrategyisalimitingcaseofstrategy#4,wheretheretryattemptscounterissettozero.In
other words, a record that fails to complete within the allotted deadline is discarded immediately,
withnosecond-chancere-queuing.Thisisthemostrelaxedmodel,whichguaranteesprogressunder
asignificantlyrelaxednotionofsafety.
Dealing with partition exclusivity
If was previously mentioned that Kafka’s assurance extends to the exclusive processing of records;
namely,thatatmostoneconsumerwillbeallocatedtoapartition,foranygivenconsumergroup.
There is a subtle caveat here, albeit a crucial one — concealed in the wording of the statement
above. The assurance is given only with respect to partition assignment; it doesn’t cover the act of
concurrentlyprocessingtherecord,despitewhatmostKafkapractitionersmightliketobelieve.
Specifically,theproblemisthis:considertwoconsumersC0andC1inacommongroup,contending
overonepartitionP.P isinitiallyassignedtoC0,andthegroupcoordinatorhasastableviewofthe
groupmembership,beingthesetcontainingC0 andC1.
At some point in the course of processing records, C0 is unable to satisfy its progress check while
workingthroughabatchofrecords.Furthermore,C0 isblockedwhileprocessingoneoftherecords
inthebatch,withmorerecordsremaining.TheKafkaConsumerinstancethatisbackingC0willdetect
that the application has failed to invoke poll() within the progress deadline, and the heartbeat
threadwillconsequentlysendanexplicitleaverequest.However,theconsumerremainsblockedon
whateveroperationitwasdoing,oblivioustothebackgroundgoings-onoftheheartbeatthread.

Chapter15: GroupMembershipandPartitionAssignment 252
Havingreceivedtheleaverequest,thecoordinatorwilladjustthegroupmembershiptoasingleton
set comprising C1, which will be followed by a partition reassignment. It is possible that C0 may
haverejoinedthegroup,butthathardlymatters—P hasbeenreassignedtoC1.WhenC0eventually
unblocks,itwillproceedwithitsbacklog,naivelyassumingthatitisstilltheownerofP.Intheworst-
case scenario, C0 could enter a critical section, where the effect of processing a record on C0 may
conflictwiththeconcurrentorearlierprocessingofanidenticalrecordonC1.
Thisdemonstratesthatalthoughthepartitionwasassignedexclusivelyfromtheleader’sperspective,
therestrictionsunderthisassignmentwerenotreflectedcommensuratelyontheconsumers.Under
normal circumstances, when the consumer population changes, the new and existing consumers
are notified of changes to their partition assignments via an optional ConsumerRebalanceListener
callback. The callback, which will be discussed in detail later, essentially informs a consumer that
its existing partitions were revoked and that new partitions were assigned. Crucially, the callback
is globally blocking in the revocation phase — no consumer is allowed to proceed with their new
partitionassignmentuntilallconsumershavehandledtherevocationevent.Thisgivesaconsumera
vitalopportunitytocompletetheprocessingofanyin-flightrecords,gracefullyhandingitsworkload
over to the new consumer. However, the callback is executed in the context of the polling thread,
during its next call to KafkaConsumer.poll(). In our earlier example, C0 was blocked — clearly,
there was no way it could have called poll(). Furthermore, as it was forcibly excluded from the
group, the consumer would not have been eligible to partake in the blocking revocation callback; a
memberthatisdeemeddeadisnotabletoaffecttheremainingpopulationofthegroup.(Itsabilities
will be restored when it rejoins the group, by which time the group would have undergone at least
onerebalance.)
Kafka2.4.0introducesanotherConsumerRebalanceListenercallbackmethod—onPartitionsLost()
—designedtoindicatetotheconsumerthatitspartitionshavebeenforciblyrevoked.Thismethodisa
best-effortindicatoronly;unliketheonPartitionRevoked()method,itdoesnotactasasynchronisa-
tionbarrier—itcannotstoporundotheeffectsoftherevocation,asthenewassigneewilllikelyhave
started processing records by the time the outgoing consumer has reacted to onPartitionsLost().
Furthermore, like its peer callback methods, onPartitionsLost() executes in the context of the
polling thread, invoked from within a call to KafkaConsumer.poll(). Since the failure scenarios
assumethattheconsumermaybeblockedindefinitely,theusefulnessofthiscallbackisquestionable.
Still, at least now Kafka informs us of the calamity that ensued, whereas in previous versions the
consumerhadtodiscoverthisofitsownaccord.Moreoftenthannot,theeffectsoftwoconsumers
operatingonthesamerecordswerediscoveredbydisheartenedusersofthesystem,sometimeslong
afterthefact.
By default, and to maintain compatibility with pre-2.4.0 consumer applications, the
onPartitionsLost() callback simply delegates to onPartitionRevoked(). However, the
rolesofthesecallbackmethodsarevastlydifferent.Whenupgradingaconsumerapplication
thatimplementstheConsumerRebalanceListenercallbacktoutiliseversion2.4.0(ornewer)
oftheclientlibrary,oneshouldimmediatelyoverridethedefaultimplementation.
So how does one prevent the non-exclusive assignment condition? Again, Kafka is coy on answers.

Chapter15: GroupMembershipandPartitionAssignment 253
Once more, we revert to outside strategies for dealing with partition exclusivity. There are three at
ourdisposal:
1. Ensurethatthepollloopalwayscompleteswithintheprogressdeadlinesetbymax.poll.interval.ms,
therebysidesteppingtheproblem.
2. Employanexternalfencingmechanismfordealingwithcriticalsections.
3. Employ fencing at the process level, terminating the outgoing process, or isolating it from the
outsideworld.Thismaybeusedinadditionto#2.
Thefirststrategyhasalreadybeencoveredintheprevioussection.Ittriestoavoidadeadlineoverrun
by either yielding the subscription, shedding the load, or requeueing records. It is not a universal
strategy,inthatithasdrawbacksandmightnotapplyinallcases.
The second strategy relies on an external arbitration mechanism — typically a distributed lock
manager (DLM) — used to ensure exclusivity over a critical section. Before processing a record,
theconsumerprocesswouldattempttoacquireanamedlockwiththeDLM,proceedingonlyupon
successful acquisition. The name of the lock can be derived from the record’s key or the partition
number. This aligns critical sections to partitions and ensures that only one consumer may operate
within a critical section at any one time; however, it does not preclude the record from being
processed multiple times, as the two consumers may have handled the record at different, non-
overlappingtimes.Thisisillustratedinthesequencediagrambelow.

Chapter15: GroupMembershipandPartitionAssignment 254
Criticalregion—per-record
Having entered the critical section, the consumer will check that the effects of the record haven’t
alreadybeenappliedbysomeotherprocess.Ifitdetectsthattherecordhasalreadybeenprocessed
elsewhere,theconsumercansafelyskiptherecordandmoveontothenext.
Inadditiontopromotingsafety,thecheckforpriorprocessingaddsidempotence torecord
consumption—ensuringthattherepeatprocessingofarecordleadstonoobservableeffects
beyondthosethatwereemittedduringthefirstprocessing.
While traversing the critical section, a consumer’s observation that a record has already been
processedmightleadtothefollowingdeduction.Ifonerecordhasalreadybeenprocessedelsewhere,
thenitislikelyduetoabreachoftheexclusivityconstraint.Consequently,itislikelythatmultiple
recordshavebeenprocessedelsewhere.
This deduction might lead to a tempting optimisation. Rather than trying the next record in the
batch,theconsumermightsimplyabortthebatchaltogetherandunsubscribefromthetopic,then
resubscribe again. This will force a group membership change (it will cause two changes, to be
precise)thatwillseetheconsumerreceiveanewpartitionassignmentandstartagain.
Howeverlogicalitmightseem,thisapproachdoesnotwork.Iftheconsumerisfacedwithabatch

Chapter15: GroupMembershipandPartitionAssignment 255
where the first, or indeed all records appear to be processed, then bailing out and resubscribing to
the topic might advance the consumer’s offsets to skip over the processed records, but it equally
might not. This depends on whether the original consumer had committed its offsets in the first
place.Considerasimplescenarioinvolvingasinglepartitionwithanumberofrecords,whichwere
previously processed by some consumer. The consumer crashed after processing the records but
before committing its offsets. A new consumer takes over the topic, and receives the offset of the
first record. It detects a duplicate and attempts to resubscribe, but the new assignment will give it
the same starting offset — leading to a perpetual backoff cycle on the consumer. Having detected
duplicatehandlingofarecord,thecorrectprocedureistomoveontothenextrecord,doingsountil
thebackloghasbeenworkedthrough.
As a strategy, external fencing may be used in a broad range of scenarios as it does not mutate the
topicorsacrificeanynotionofsafety.OtherthantheaddedcomplexityofrequiringanexternalDLM,
the drawback of fencing is its negative impact on throughput and latency. Since record processing
must be surrounded by network I/O and persistent operations on the DLM, fencing may pose a
challenge for both latency-sensitive and throughput-sensitive applications. This can be alleviated
byreorganisingrecordsreturnedfrompoll()intodisjointorderedsetsofkey-relatedrecords,then
processing these sets with dedicated locks. In the simplest case, the disjoint ordered sets may be
derived from the partition number of the records — each record is allocated to a set based on its
partition number. The sets are then processed either sequentially or concurrently on the consumer.
Beforeprocessingeachset,theconsumeracquiresalockcoveringallelementsintheset,releasingthe
lockuponthecompletionoftheset.Bycoalescingvisitstothecriticalsection,theconsumerreduces
both the network I/O and the load on the DLM. This optimisation is illustrated in the sequence
diagrambelow.

Chapter15: GroupMembershipandPartitionAssignment 256
Criticalregion—disjointsets
Whendesigningadistributedconsumerecosystem,caremustbetakentoensurethattheDLMdoes
not become a single point of failure. While focusing on safety, one must take care to not neglect
liveness;iftheDLMbecomesunavailable,theentireconsumerecosystemwillstall.
OnedoesnothavetouseadedicatedDLMforarbitratingaccesstocriticalsections.Somepersistent
datastores,suchasrelationaldatabases,Etcd,Redis,andConsulcanactaslockingprimitives.(Any
store that is both persistent and exposes a compare-and-swap operation may be used to construct
a mutex.) In the case of a relational database (and some NoSQL systems), the database can be used
to affect transactional semantics over a series of operations — using pessimistic locking or multi-
versionconcurrencycontrol.Whenthecriticalsectionisboundtoasingledatabase,thetransactional
capabilitiesofthedatabaseshouldbepreferredoveraDLM,asthisminimisestheamountofnetwork
I/O,simplifiesthedesignoftheapplication,andleadstoastrongerconsistencymodel.

Chapter15: GroupMembershipandPartitionAssignment 257
Notwithstanding the various controls one might employ to ensure partition exclusivity, it can be
shownthatintheabsenceofadeterministicsystem,thesafetypropertycannotbeunconditionally
satisfiedinallcases.
Sources of nondeterminism in the execution of applications, such as interrupts, paging, heap com-
paction,garbagecollection,andsoforth,canleadtoundetectablegapsintheevaluationofsuccessive
statements.Considerthefollowingcode,typicalof‘safe’accesstoacriticalsection.
distributedLock.acquire();
try {
criticalSection.enter();
// ...
criticalSection.leave();
} finally {
distributedLock.release();
}
Theacquisitionofanentrypermittoacriticalsectionmaybeinvalidatedbeforethesectionissubse-
quentlyenteredduetoaneventthatcannotbeforeseenorcorrected,forexample,agarbagecollector
pause.Intheexampleabove,thecriticalSection.enter()callmaybeprecededbyanabnormally
long pause, such that the DLM may deem the lock holder ‘dead’ and transfer the lock to the next
contender.WhenthecodeeventuallyresumesexecutingandcallscriticalSection.enter(),itwill
conflictwiththenewlockholder’sactionsandviolatethesafetyproperty.
Thethirdstrategy—process-levelfencing—augmentsstrategy#2byidentifyingandrestarting(or
otherwiseisolating)processesthathavehadtheirpartitionsrevokedasaresultofaheartbeatfailure.
This can be accomplished using an external health check mechanism that continually queries the
processonawell-knownendpoint—todeterminewhethertheprocesscansatisfyitsroutinepolling
obligations. If the endpoint does not respond within a set timeframe, or if the response is negative,
the process will be forcibly restarted. The health check should be tuned to respond negatively well
before the max.poll.interval.ms deadline elapses, so that the process can be restarted before its
deathisdetectedonthecoordinatorandwellbeforeitspartitionsarereassigned.
Utilising static members
Asmentionedearlierinthischapter,Kafkaoffersthenotionofstaticgroupmembership—allowing
for an external health check and healing mechanism for ensuring the liveness of the consumer
ecosystem. The requirement for static membership stems from the more contemporary use cases
involving container orchestration engines such as Kubernetes. Under the ‘Kubernetes’ model, the
container orchestrator is responsible for the ongoing health check of its subordinate containers,
restartingthemifafailureisdetected.

Chapter15: GroupMembershipandPartitionAssignment 258
Kubernetes,duetoitsoverwhelmingpopularity,isoftenquotedasaubiquitous‘placeholder’
termforanycontrolsystemthattakesonadditionalhealthassuranceresponsibilities.When
‘Kubernetes’iswritteninquotes,asintheprecedinginstance,oneshouldassumethatthe
conversationrelatestoanycontrolsystem.
In the ‘Kubernetes’ deployment model, a container may be terminated spuriously as a result of a
failedhealthcheck,onlytoberestartedshortlyafterwards.Itwouldbehighlyadvantageousforthe
partitions assigned to the failed consumer to remain as such, with no intermediate reassignment,
until the consumer rejoins the group. This model avoids a stop-the-world pause at the expense
of individual partition availability — one or more partitions (depending on the failed consumer’s
assignment) will remain paused for some time. This may result in greater overall throughput and
more pronounced positive skew in the distribution of response times — lower median latencies at
theexpenseofalongerlatencytail.
Under the static model, members are allowed to leave and join a group without forfeiting their
partition assignment or causing a rebalance, providing that they are not away for longer than the
session.timeout.ms deadline; failing to reappear within the heartbeat deadline causes a purge,
triggering a subsequent rebalance. In this respect, there is no material difference in the liveness
behaviourbetweenthestaticanddynamicprotocolsfromthecoordinator’sperspective.
Naturally, if one is using an external orchestratorfor managing consumer health, it makessense to
desensitisethecoordinator’sownhealthcheckmechanismtopreventaprematureexpulsionofthe
bounced consumer, but still allow the coordinator act as a ‘safety net’ if the orchestrator does not
detect and remediate the process failure in a timely manner. This can be achieved by setting the
session.timeout.ms deadline to a value that exceeds the orchestrator’s projected response time by
somemargin.Themax.poll.interval.msdeadlinewouldalsobeelevatedaccordingly.
Theeffectof max.poll.interval.msisdifferentunderthestaticgroupmembershipscheme.
The KafkaConsumer client will cease to heartbeat if the deadline is not met by the poll-
processloop,butnofurtheractionwillbetaken—namely,theclientwillnotsendaleave
request and no reassignment will occur. The stalled consumer process will be given up to
session.timeout.mstorecover.Ifnoheartbeatisreceivedwithinthesession.timeout.ms
deadline,onlythenwillthecoordinatorevictthefailedconsumerandreassignitspartitions.
In the course of introducing the static membership feature, the hard upper bound on
session.timeout.mswasincreasedto1800000(30minutes).
Withtheadditionalconsumerhealthmanagementoptionsofferedbystaticgroupmembership,one
might wonder whether this changes the liveness and safety landscape. The somewhat grim answer
is:notbyalot.
The liveness property is fundamentally satisfied by identifying consumer failures. Whether the
failureisidentifiedbythecoordinatororbyanexternalprocessismostlyanimplementationdetail.A
staticmodelmayreducetheimpactofintermittentfailureonthegroup,attheexpenseofindividual
partitionstalls.

Chapter15: GroupMembershipandPartitionAssignment 259
Thesafetypropertyisachievedbyconveyinganexclusivepartitionassignment,ontheassumption
thattheassignmentiscommensuratelyhonouredontheconsumers.Wepreviouslylookedatprocess
fencing as a mechanism for ensuring partition exclusivity among consumers. Static membership,
coupledwithanexternalorchestrator,offersastraightforwardimplementationpathforthefencing
strategy. The process health check will factor into account the last poll time — reporting a heavily
backloggedprocessasfailed.Theorchestratorwillrestarttheconsumerprocess,causingittorejoin
with its durable group instance ID, restoring any prior assignments. The consumer’s progress in
the stream will be reset per the last committed offsets, allowing it to reprocess the last batch. The
combination of locking critical sections and fencing processes effectively neutralises any residual
likelihoodofalingering‘zombie’process.
Mixing membership models
The static membership model on its own is generally incompatible with the notion of consumer
elasticity. With a programmatic assignment of the group.instance.id property, it is possible to
accommodate the scale-up scenario. A newly spawned static consumer joining a group would
acquireashareofpartitions,workinginmuchthe samewayasadynamicconsumer.Theproblem
occursonthescale-downpath:aterminatedstaticconsumerwillleadtopartitionunavailabilityfor
the period of session.timeout.ms, which has presumably been increased as part of switching to
staticmembership.Inotherwords,scale-upwillberesponsivewhilescale-downwillbesluggish.
Itispossibletomixelasticandinelasticconsumersinthesameconsumergroup.Considerausecase
where an application is experiencing a mostly constant load throughout the day. Occasionally, the
load peaks above the routine threshold, requiring additional burst capacity to process the stream.
Assumingthatthroughputtakespriorityoverlatencyandoccasionalindividualpartitionpausesare
acceptable,theconsumerpopulationmightcompriseamixtureofstatically-configuredanddynamic
consumers. When utilising public cloud infrastructure, it is more cost-effective to provision long-
term capacity for fixed loads, while utilising ephemeral instances (e.g. Spot Instances in AWS) for
spillovercapacity.Kafka’sstaticmembershipfeaturedovetailsnicelyintothismodel.
Whenmixingconsumertypes,dynamicconsumersshouldbeconfigureddifferentlyforthesession.timeout.ms
property.Dynamicconsumerswillhaveamuchshortersession.timeout.msdeadline,lettingKafka
manageconsumerliveness.Staticconsumerswillhaveamorerelaxedsession.timeout.msdeadline,
butwillalsotypicallyutiliseanotherorchestratorwithamoreaggressivehealthcheckinterval.
Partition assignment strategy
All considerations around group membership ultimately serve one purpose — to ensure that par-
titions are assigned among the members of a consumer group. As previously discussed, partition
assignmenttakesplaceonasingleconsumer—theleaderofthegroup.
The principal motivation for performing assignment on a consumer process is that it enables the
application developers to substitute one of the built-in assignment strategies with a custom one,

Chapter15: GroupMembershipandPartitionAssignment 260
without having to include the implementation of the custom strategy in the classpath of each of
the broker nodes. This is particularly crucial when a cluster is being utilised in a multitenancy
arrangement, where different teams or even paying customers might share the same set of brokers.
It would be intractable to redeploy a broker upon a change to the rebalancing strategy, due to the
disruptionthiswouldcausetoallclients.Undertheconsumer-ledmodel,onecaneasilyrolloutan
updatetotheassignmentstrategybyredeployingtheconsumers,withnochangeorinterruptionto
theKafkacluster.
One of the challenges of the consumer-led model is the possible disagreement as to the preferred
assignorimplementationamongthepopulationofconsumers.Theleaderwillattempttodetermine
themost-agreed-uponstrategyfromthelistofpreferredstrategiessubmittedbyeachmember.Ifno
commonstrategycanbeagreedupon,theleaderwillreturnanerrortothecoordinator,whichwill
bepropagatedtotheremaininggroupmembers.
Aconsumerspecifiesitspreferredassignorsbysupplyingalistoftheirfully-qualifiedclassnamesvia
the partition.assignment.strategy property. The assignor implementation must conform to the
org.apache.kafka.clients.consumer.ConsumerPartitionAssignorinterface.Theassignorsappear
intheorderofpriority;thefirstassignorisgiventhehighestpriority,followedbythenextassignor,
andsoon.
Althoughassignorsarespecifiedasclassnames,theyarecommunicatedusingtheirsymbolicnames
inthestatesynchronisationphaseoftheprotocol.Thisallowsforinteroperabilitybetweendifferent
client implementations — for example, Java clients intermixed with Python clients. As long as the
assignornamesmatch,theconsumerscanagreeonacommonone.
Built-in assignors
Therearefourbuilt-inassignorsatone’sdisposal.Atanoverviewlevel,theseare:
1. RangeAssignor: the default assignor implied in the client configuration. The range assignor
works by laying out the partitions for a single subscribed topic in numeric order and the
consumersinlexicographicorder.Thenumberofpartitionsisthendividendbythenumberof
consumerstodeterminetherangeofpartitionsthateachconsumerwillreceive.Sincethetwo
numbersmightnotdivideevenly,thefirstfewconsumersmayreceiveanextrapartition.This
processisrepeatedforeachsubscribedtopic.
2. RoundRobinAssignor: allocates partitions in a round-robin fashion. The round-robin assignor
works by laying out all partitions across all subscribed topics in one list, then assigning one
partition to the first consumer, the second partition to the next consumer, and so on — until
allpartitionshavebeenassigned.Whenthenumberofconsumersisexhausted,thealgorithm
rotatesbacktothefirstconsumer.
3. StickyAssignor:maintainsanevenly-balanceddistributionofpartitionswhilepreservingthe
prior assignment as much as possible, thereby minimising the difference in the allocations
betweensuccessiveassignments.
4. CooperativeStickyAssignor:avariantofthestickyassignorthatutilisesthenewercooperative
rebalancingprotocoltoreducethestop-the-worldpause.

261
Chapter15: GroupMembershipandPartitionAssignment
Range assignor
The range assignor, along with the round-robin assignor, is among the two of the oldest assignors
in Kafka’s arsenal. The range assignor is the default, yielding contiguous partition numbers for
consumers.(Thisiseasierontheeye,notthatitmattersmuch.)Tounderstandhowitworks,consider
the following scenario, where a topic with eight partitions is grown from one consumer to two,
then to three, and finally, to four consumers. Initially, consumer assignments would resemble the
following:
| Consumer | Partitions              |     |
| -------- | ----------------------- | --- |
| C0       | P0,P1,P2,P3,P4,P5,P6,P7 |     |
Followingtheadditionofasecondconsumer,thepartitionassignmentswouldbealteredto:
Consumer Partitions
C0 P0,P1,P2,P3
C1 P4,P5,P6,P7
Comparingthetwostates,wearriveatthefollowingdelta:
| Consumer | Assigned    | Revoked     |
| -------- | ----------- | ----------- |
| C0       |             | P4,P5,P6,P7 |
| C1       | P4,P5,P6,P7 |             |
Thechangeencompassesfourassignmentsandfourrevocationsacrossbothconsumers—fourswaps,
so to speak. This is on par with the best assignment schemes, representing the optimal outcome,
in terms of minimising reassignments and maintaining an evenly-balanced consumer group. By
‘evenly-balanced’,itismeantthatthedifferencebetweenthepartitioncountofthelargestallocation
andthatofthesmallestallocationcannotexceedone.
Nowconsidertheadditionofathirdconsumer.
| Consumer | Partitions |         |
| -------- | ---------- | ------- |
| C0       | P0,P1,P2   |         |
| C1       | P3,P4,P5   |         |
| C2       | P6,P7      |         |
| Consumer | Assigned   | Revoked |
| C0       |            | P3      |
| C1       | P3         | P6,P7   |
| C2       | P6,P7      |         |
There are three swaps in this scenario. How would this compare to an optimal assignment? To
maintainevenbalancewithminimalreshuffling,wewouldsimplyrevokeP3 fromC0 andP7 from
C1,transferringthemtoC2:

262
Chapter15: GroupMembershipandPartitionAssignment
Consumer Partitions
C0 P0,P1,P2
C1 P4,P5,P6
C2 P3,P7
| Consumer | Assigned | Revoked |
| -------- | -------- | ------- |
| C0       |          | P3      |
| C1       |          | P7      |
| C2       | P3,P7    |         |
So in this case, the range assignor loses to the optimum by three reassignments to two. While not
ideal,thisismarginallybetterthananotherpopularbuilt-instrategy—theround-robinassignor.
Let’sincreasetheconsumercounttofour.
Consumer Partitions
C0 P0,P1
C1 P2,P3
C2 P4,P5
C3 P6,P7
| Consumer | Assigned | Revoked |
| -------- | -------- | ------- |
| C0       |          | P2      |
| C1       | P2       | P4,P5   |
| C2       | P4,P5    | P6,P7   |
| C3       | P6,P7    |         |
Comparingthiswiththeoptimum:
Consumer Partitions
C0 P0,P1
C1 P3,P4
C2 P6,P7
C3 P2,P5
| Consumer | Assigned | Revoked |
| -------- | -------- | ------- |
| C0       |          | P2      |
| C1       |          | P6      |
C2
| C3  | P2,P6 |     |
| --- | ----- | --- |
Theefficiencyofarangeassignordropssignificantlycomparedtotheoptimum—fiveswapstotwo.
Beyondthreeconsumers,thepreservationofassignmentsisfairlypoor.
Despite being the default option, the range assignor suffers from one severe deficiency: it leads
to an uneven assignment of partitions to consumers having multiple topic subscriptions, where the
partitioncountis,onaverage,smallerthanthenumberofconsumers.Thisphenomenonisillustrated
below.

Chapter15: GroupMembershipandPartitionAssignment 263
Rangepartitionassignor
Therangeassignorconsidersthepartitionsforeachtopicseparately,allocatingthemevenlyamong
the consumers. In the above example, the first two consumers get an extra partition each for topic
T0,whichisreasonable,since8doesnotdivideevenlyinto3.Sofar,therangeassignorseemsfair.
Moving on to T1, the two available partitions are assigned to consumers C0 and C1, respectively.
With insufficient partitions in T1, C2 is left without an assignment. Again, this does not appear
unreasonable when taken in isolation. However, when considering that the assignment for T0 also
favouredthefirsttwoconsumers,itseemsthatC2 isataslightdisadvantage,beingtowardstheend
of the pack. The inherent bias becomes more pronounced as we consider T2 and T3; there are no
newassignmentsforC2.
Kafka’sperformancedoctrineofscalingconsumerstoincreaseparallelismdoesnotalwaysapplyto
itsdefaultassignor—undercertaincircumstances,asevidentabove,itwillnotscalenomatterhow
many consumers are added into the mix — the new consumers will remain idle, despite having a
sufficientaggregatenumberofpartitionsacrossallsubscribedtopics.Thiscanbehelpedbyensuring
thateachtopicissufficiently‘wide’onitsown.However,therangeassignorisstillinherentlybiased
when the number of partitions does not divide cleanly into the number of consumers — giving
preferencetotheconsumersappearinghigherinthelexicographicalorderoftheirmemberID.
Round-robin assignor
Unlike the range assignor, the round-robin strategy aggregates all available partitions from every
subscribed topic into a single vector, each element being a topic-partition tuple, then maps those
topic-partitionsontotheavailableconsumers.Indoingso,theround-robinassignorcreatesaneven
loadingofconsumers,providingthatthesubscriptionisuniform—inotherwords,eachconsumeris

264
Chapter15: GroupMembershipandPartitionAssignment
subscribedtothesamesetoftopicsaseveryotherconsumer.Thisisusuallythecaseinpractice;itis
raretoseeaheterogeneoussetofconsumerswithdifferentsubscriptioninterestsinthesamegroup.
The diagram below illustrates the effectiveness of a round-robin strategy in achieving a balanced
loading.
Round-robinpartitionassignor
Thepreservationpropertiesofaround-robinassignoraretypicallyonlyslightlyworsethanthatof
therangeassignor.Considertheprevioustestofsubjectingatopicwitheightpartitionstoagrowing
consumerpopulation.Startingwiththeone-to-two(consumers)transition:
Consumer Partitions
C0 P0,P2,P4,P6
C1 P1,P3,P5,P7
| Consumer | Assigned    | Revoked     |
| -------- | ----------- | ----------- |
| C0       |             | P1,P3,P5,P7 |
| C1       | P1,P3,P5,P7 |             |
The performance is on par with the optimum. In fact, it can be trivially shown that every strategy
willperformidenticallytotheoptimumfortheone-to-twotransition,ineitherdirection.
Increasingtheconsumerpopulationtothree:
| Consumer | Partitions |     |
| -------- | ---------- | --- |
| C0       | P0,P3,P6   |     |
| C1       | P1,P4,P7   |     |
| C2       | P2,P5      |     |

265
Chapter15: GroupMembershipandPartitionAssignment
| Consumer | Assigned | Revoked |
| -------- | -------- | ------- |
| C0       | P3       | P2,P4   |
| C1       | P4       | P3,P5   |
| C2       | P2,P5    |         |
Therearefourswapsnow,whereastwowouldhavebeenoptimum.
Nowforthethree-to-fourtransition:
Consumer Partitions
C0 P0,P4
C1 P1,P5
C2 P2,P6
C3 P3,P7
| Consumer | Assigned | Revoked |
| -------- | -------- | ------- |
| C0       | P4       | P3,P6   |
| C1       | P5       | P4,P7   |
| C2       | P6       | P5      |
| C3       | P3,P7    |         |
The performance degrades to the same level as the range assignor, in both cases taking five swaps
comparedtothetwoswapsoftheoptimumstrategy.
Allinall,theround-robinassignorwillshowmarginallyworsepreservationqualitiesthantherange
assignor in some cases. But in all cases, the round-robin assignor provides an ideal distribution of
load in a homogeneous subscription. Frankly, one struggles to comprehend why the range assignor
was chosen as the default strategy over round-robin, given that both are of the same vintage and
thelatterismoresuitableforabroaderrangeofscenarios.
Furthermore, the cost of rebalancing partitions is mostly attributable to the consumer ecosystem;
consumerswillcontributetothestop-the-worldpausebyblockingintheironPartitionsRevoked()
callback. (Even without the optional callback, there will be some amount of cleanup within the
KafkaConsumer, such as committing offsets if offset auto-commit is enabled.) Since both assignors
utilise the eager rebalancing protocol, both sets of consumers will equally assume the worst-case
scenarioandrevokeallpartitionsbeforesynchronisingstate.
Sticky assignor
The observable degradation in preservation abilities of range and round-robin assignors as the
number of consumers increases is attributable to their statelessness. The range and round-robin
assignors arrive at the new allocations by only considering the requested subscriptions and the
availabletopic-partitions.Thestickyassignmentstrategyimprovesuponthisbyaddingathirdfactor
—thepreviousassignments—toarriveatanoptimalnumberofswapswhilemaintaininganideally
balanced group. In other words, the sticky assignor performs as well as the theoretically-optimum
strategyandretainsthebalancingqualitiesofround-robin.

266
Chapter15: GroupMembershipandPartitionAssignment
The main issue with using a sticky assignor is that it is still based on the aging eager rebalance
protocol.Withoutspecialconsiderationoftheassignmentstrategyintherebalancelistenercallback,
a consumer application will still be forced down a worst-case assumption concerning partition
revocations.
The documentation of the StickyAssignor suggests the following approach to mitigate the effects
of the eager protocol. Ordinarily, a ConsumerRebalanceListener will commit offsets and perform
anyadditionalcleanupintheonPartitionsRevoked()callback,theninitialisethenewstateasso:
| new ConsumerRebalanceListener() | {   |     |     |
| ------------------------------- | --- | --- | --- |
void onPartitionsRevoked(Collection<TopicPartition> partitions) {
| for (var partition | : partitions) | {   |     |
| ------------------ | ------------- | --- | --- |
commitOffsets(partition);
cleanupState(partition);
}
}
void onPartitionsAssigned(Collection<TopicPartition> partitions) {
| for (var partition | : partitions) | {   |     |
| ------------------ | ------------- | --- | --- |
initState(partition);
initOffset(partition);
}
}
}
Underastickyassignmentmodel,onemaywanttodeferthecleanupState()operationsuntilafter
thepartitionshavebeenreassigned,providingthatitissafetodoso.Theamendedlistenercode:
| new ConsumerRebalanceListener() | {              |     |            |
| ------------------------------- | -------------- | --- | ---------- |
| Collection<TopicPartition>      | lastAssignment | =   | List.of(); |
void onPartitionsRevoked(Collection<TopicPartition> partitions) {
| for (var partition | : partitions) |     |     |
| ------------------ | ------------- | --- | --- |
commitOffsets(partition);
}
void onPartitionsAssigned(Collection<TopicPartition> partitions) {
| for (var partition | : difference(lastAssignment, |     | assignment)) |
| ------------------ | ---------------------------- | --- | ------------ |
cleanupState(partition);
| for (var partition | : difference(assignment, |     | lastAssignment)) |
| ------------------ | ------------------------ | --- | ---------------- |
initState(partition);

Chapter15: GroupMembershipandPartitionAssignment 267
for (var partition : assignment)
initOffset(partition);
this.lastAssignment = assignment;
}
}
As a matter of fact, this approach could be used with any assignor; however, the sticky assignor
ostensibly makes this more worthwhile. Whether this holds in practice, and to what extent, largely
dependsonthecostofthecleanupState()andinitState()methods,especiallyiftheformerneeds
toblock.
IfcleanupState()isablockingoperation,thenatleastitwillnotholdbacktheonPartitionsRevoked()
method when deferred, meaning that it will not contribute to the stop-the-world pause. By the
same token, any consumer can alter its ConsumerRebalanceListener callbacks to take advantage
of a deferred cleanup, irrespective of the chosen assignor. In other words, partition stickiness on
its own does not yield an advantage in onPartitionsRevoked() — the gains result from refac-
toring and are independent of the chosen strategy. The sticky assignor helps in reducing the
number of cleanupState() and initState() calls that are outside of the stop-the-world phase.
The latter is significant, as it delays the start of record processing on the consumer, but does not
affect the other consumers. The cleanupState() method, on the other hand, should ideally be
executed asynchronously if possible — it is hard to imagine why state cleanup should hold up the
onPartitionsAssigned()callback.
Cooperative sticky assignor
Thecooperativestickyassignorisarelativelyrecentadditiontothefamily,appearinginKafka2.4.0.
ItproducesthesameresultasitseagerStickyAssignorpredecessor,utilisingthenewerincremental
cooperativerebalanceprotocol.
The main difference between the two variants is that in the cooperative case, the consumer is told
exactlywhichpartitionswillberevoked,reducingtheblockingtimeoftheonPartitionsRevoked()
callbackandalleviatingthestop-the-worldpause.
Upgrading assignors and rebalance protocols
As the assignor configuration is defined client-side, one may be tempted to change the assignor by
simplyupdatingthepartition.assignment.strategypropertytoanewassignorandthenbouncing
the consumers. In reality, doing so in the presence of multiple consumers will halt the consumer
group. When the first client bounces and rejoins the group, it will mandate an assignor that isn’t
supported by the remaining members, leading to an INCONSISTENT_GROUP_PROTOCOL error emitted
bythegroupleader,manifestingasanInconsistentGroupProtocolExceptionontheclient.
The correct mechanism for changing an assignor is to perform two bounces. Before the first
round, change the consumer configuration to reflect both the preferred and the outgoing assignor.

Chapter15: GroupMembershipandPartitionAssignment 268
For example, if changing from the default range assignor to the cooperative sticky assignor, the
partition.assignment.strategypropertyshouldbesettoorg.apache.kafka.clients.consumer.RangeAssignor,org.apache.kafka.clients.consumer.CooperativeStickyAssignor.
Having completed the first round, update the configuration to exclude the outgoing assignor. The
assignorsmayinitiallyappearineitherorder;theprioritydifferencewillberesolvedbythestartof
the second round. After two rounds, the client configuration should only contain the cooperative
assignor. This process ensures that at every point there is at least one common protocol that every
consumeriswillingtoaccept.
Whenmigratingfromthedefaultconsumer,itisessentialthattheorg.apache.kafka.clients.consumer.RangeAssignor
isincludedexplicitlyinthestrategylist,astheclientwillnotadditbydefault.
This chapter has taken the reader on a deep-dive tour of Kafka’s elaborate group management pro-
tocol—theunderlyingmechanismfordealingwithconsumers‘comingandgoing’gracefully.And
not too gracefully, as it has turned out — consumers may spuriously disappear due to intermittent
failuresorreappearunexpectedlyaslingering‘zombie’processes.
The ultimate intent of group membership is to allocate partitions to consumers and ensure exclu-
sivity. As we have learned, the mechanics of partition assignment are fulfilled by an embedded
protocol — an encapsulated communication between an elected group leader and the subordinate
members, arbitrated by a dedicated coordinator node. The actual assignment is courtesy of a
ConsumerPartitionAssignor implementation that executes entirely on the lead consumer; several
built-inassignmentstrategiesareavailable,cateringtotheneedsofmostconsumers.
More recently, Kafka has improved support for container orchestration technologies like Kuber-
netes, improving rebalance performance and opening doors to external mechanisms for managing
consumerhealth—meansforpromptlyidentifyingandswiftlydealingwithfailures.
If there is anything that this chapter has imparted, it is that Kafka is no trivial creation. It is a
complex beast, designed to fulfill a broad range of stream processing needs. For all its flexibility
and configuration options, employing Kafka correctly is not straightforward by any stretch of the
imagination. One requires a firm grasp on the theory of distributed and concurrent computing —
at minimum, a thorough appreciation of the liveness and safety properties. Anything that can go
wrong, will go wrong — as it was once famously said. Hopefully, the material in this chapter has
preparedthereaderforallsortsofsituations.

Chapter 16: Security
Withthephenomenallevelofinterconnectednessprevalentinthemodernworld,opportunitiesarise
not just in legitimate commercial enterprises, but also in the more clandestine establishments —
individuals or organisations that seek to capitalise on unprotected systems and networks, with an
overarchingmotivetothreatentheirtargetsforthepurposeofextortion,causeimmediateandlasting
harm,ordirectlyprofitfromillicitactivities.Thetopicofconversationis,ofcourse,cybercrimeand
informationsecurity.
Thereisacommonmisconceptionthatcybercriminalstargetanorganisation’smostsecuredefences
and employ highly sophisticated techniques to penetrate defences. This view has been largely
popularised in modern fiction, where the persona of a hacker has been romanticised as a lone,
reclusiveindividualwithabove-averageskillswhoactsoutofspiteortakestheirtargetasapersonal
challenge. In practice, it is more instructive to view cybercrime as a business, albeit an illegitimate
one. While just about any target is theoretically ‘hackable’, some are just not worth it, insofar as
the cost of breaking into an organisation may outweigh the prospective gains. Like any business,
a criminal organisation will seek to profit with minimal outlay, which means going after the easy
prospects, with trivial or non-existent defences. And where an organisation is heavily defended,
cybercriminals will often probe for alternate ways in, looking for weaknesses at the perimeter, as
wellasfrominsidetheorganisation—usingsocialhackingtogainaccess.Assuch,itisimperative
to utilise multi-layered defences, creating redundancy in the event a security control fails or a
vulnerabilityisexposed
Kafkaisapersistentdatastorethatpotentiallycontainssensitiveorganisationaldata.Itoftenactsas
a communication medium, connecting disparate systems and ensuring seamless information flow
within the organisation. These characteristics make Kafka a lucrative target to cybercriminals: if
breached,theentireorganisationcouldbebroughttoitsknees.Assuch,itisessentialthatparticular
attention is paid to the security of Kafka deployments, particularly if the cluster is being hosted
amongotherinfrastructurecomponentsorifitisaccessiblefromoutsidetheperimeternetwork.
State of security in Kafka
Let us start by making one thing abundantly clear: Kafka is not secure by default. Kafka has
numerous security controls that cover virtually all aspects of information security; however, these
controlsaredisabledoutofthebox.
ThedefaultKafkasecurityprofileisillustratedbelow.

Chapter16: Security 270
Out-of-the-boxstateofKafkasecurity
Specifically,thenotableareasofshortfallare:
• AnyclientcanestablishaconnectiontoaZooKeeperorKafkanode.Thisstatementdoesn’t
justcovertheconventionalports2181(ZooKeeper)and9092(Kafka)—diagnosticportssuchas
thoseusedbyJMX(JavaManagementExtensions)arealsoavailableforanyonetoconnectto.
WhatmakesthissetupparticularlytroublingisthataconnectiontoZooKeeperisnotessential
for the correct operation of legitimate clients. While earlier versions of Kafka required clients
to interface directly with ZooKeeper, this requirement has been removed as of version 0.9 —
a release that dates back to November 2015. ZooKeeper is a highly prized trophy for potential
attackers,aswritingtoznodes (internaldatastructureusedtopersiststatewithinZooKeeper)
willtriviallycompromisetheentirecluster.
• ConnectionstoKafkabrokersareunencrypted.ConnectionsareestablishedoverTCP,with
thepartiesexchangingdataoverawell-documentedbinaryprotocol.Athirdpartywithaccess
tothetransitnetworkmayuseabasicpacketsniffertocaptureandanalysethenetworktraffic,
obtainingunauthorizedaccesstoinformation.Amaliciousactormayaltertheinformationin

Chapter16: Security 271
transit or impersonate either the broker or the client. In addition to client traffic, interbroker
trafficisalsoinsecurebydefault.
• Connections to Kafka brokers are unauthenticated. Any client with knowledge of the
bootstrapendpointscanestablishaconnectiontothebrokers,appearingasalegitimateclient
toKafka.Aclientdoesnothavetoidentifyitself,norprovethatthesuppliedidentityisgenuine.
• No authorization controls are in place. Even if authentication is enabled, a client may
perform any action on a broker. The default authorization policy is allow-all, letting a rogue
clientrunamok.
ThereasonforKafka’srelaxeddefaultsecurityprofileislargelyhistorical.Earlyversionsof
Kafkahadnosupportforsecurity,andintroducingmandatorysecuritycontrolswouldhave
impactedcompatibilitywithexistingclients.
Target state security
Before embarking on the journey of progressively hardening the cluster, it is worthwhile to model
an ideal target state. This will act as a notional reference, allowing us to chart a course and ensure
thatwesticktoit.
An overview of the target state is illustrated below. A further examination of the various security
principlesfollows.

Chapter16: Security 272
Targetstatesecurity
Minimise the attack surface
There are several ways clients can connect to the Kafka and ZooKeeper cluster, and most of these
ingress points are of no use to legitimate clients. On the flip side, they create a large attack surface
area, making it difficult to enforce. As such, we need to limit access to the cluster at the lowest
possiblelevel,exposingtheminimally-essentialsetofendpointsforlegitimate clients.
In addition to limiting ingress, we may also want to explicitly allow specific groups of clients. We
mightonlyallowclientsfromtheorganisation’sinternalnetworktoaccessthecluster.Butwemay
go further than that, segregating traffic based on the clients’ location within the internal network.
For example, we may partition the internal network into multiple segments, and only allow clients
deployedintheoperationalnetwork(suchasthedatacentre)toaccessKafka,whileblockingclients
inthegeneral-purpose(office)network.

Chapter16: Security 273
Theremayalsobelegitimatereasonstoallowexternalclientstoconnect;forexample,ifthoseclients
resideonaremotenetworkthatisalsotrusted,butthetwonetworksareseparatedbyanuntrusted
network, such as the public Internet. Often this scenario occurs in a hybrid deployment topology,
where some parts of an organisation’s IT systems may be deployed in one or more data centres,
whileotherpartsmaybedeployedinthepublicCloud,ormultipleCloudsindifferentgeographical
regions,forthatmatter.
Ensure traffic confidentiality
Traffic flowing in and out of the cluster, and within the cluster, needs to be protected from
eavesdropping and tampering. Ordinarily, this implies some sort of encryption, a message signing
protocol,andasecurekeyexchangeprotocolthatcollectivelyassuretheend-to-endconfidentiality
ofmessages.
Encryption protocols must be chosen such that they comply with industry-accepted standards for
the secure exchange of information. These standards exist for a broad set of applications, curated
by organisations such as the National Institute of Standards and Technology (NIST). Specifically,
NIST breaks standards down into cryptographic hash functions, symmetric key algorithms, and
asymmetrickeyalgorithms—allofwhichwillapplytoKafkaatsomepointduringtheconnection
lifecycle.Inadditiontogeneral-purposestandards,specificindustrybodies—suchasPCI,aswellas
legislativeacts—suchasHIPPA,GLBAandSOX,willmandatesecuritycontrolsinadditiontothe
baseline. When deploying Kafka for applications that are expected to comply with regulation, care
mustbetakentoensurethattheapplications’approachtoinformationsecurityisequallycompliant.
Know the client
Kafka clients are typically embedded into or act on behalf of other applications that play a role
in the overall architecture landscape. Identifying clients is a prerequisite for access control; if we
don’t know who our clients are, we cannot possibly guarantee that access has been restricted to
the minimum set of actions that a client is reasonably expected to undertake. A reader familiar
with the principles of information security will recognise this statement as the Principle of Least
Privilege(PoLP).Tobespecific,attestingtheidentityofaclientdoesnotinitselfrealisePoLP,butis
nonethelessessentialforsubsequentcontrolswhoseroleitistocategoricallyenforcethisprinciple.
Limit access to essential data and functionality
This is the direct enforcement of PoLP. We need to identify all prospective principals — users of
the cluster — and determine which resources in the cluster they are allowed to operate upon. By
‘resources’,itismeantanyentitythatmayposeamaterialthreatifconsumeduncontrollably.These
include topics, consumer groups, brokers, configuration entries, as well as users. The latter is a
resource like any other — the uncontrolled modification of users’ profiles may lead to a privilege
escalation, where a less privileged user may gain a level of access above what has officially been
allocated to them. It may also lead to a denial of service, where a legitimate user has been blocked
fromcarryingouttheirnormalfunctions.

Chapter16: Security 274
With an aspirational target state defined, the scene is set — the rest of the chapter is focused on
fulfillingtheseaspirations—gettingKafkatoastateofacceptablesecurity.
Network traffic policy
Speaking to the earlier point on reducing the attack surface, no control is more effective than a
network-leveltrafficpolicy—orasitismorecommonlyknown,afirewall.
Withoutburrowingintothedetails,thedefiningroleofafirewallistosegregatetrafficbetweentwo
networksegments,suchthatonlylegitimatetrafficispermittedtotraversethefirewall.Conversely,
illegitimate traffic (which may be malicious or accidental) is not permitted to pass through the
firewall.Networkandtransport-levelcontrolstendtobesimple,straightforwardrulesthatrestrict
the flow of packets. Because of their simplicity, they are both efficient and difficult to bypass.
Especiallywhenusedinconjunctionwithadefault-denypolicy,onecanconfidentlyreasonabout
the behaviour of a firewall, and more importantly, verify its behaviour using real and simulated
traffic.
This chapter is not going to examine the different firewall types and how one would practically
configure and maintain a firewall. The technology options and physical deployment topologies
are just too varied. The physical architecture will further be complicated with the introduction of
geographical regions or the use of multiple redundant hosting locations. Rather than attempting to
prescribeaphysicaltopology,theintentionistocapturethenetworkarchitectureatalogicallevel.
Ataminimum,thefollowingnetworkpartitioningschemeisrecommended.

Chapter16: Security 275
Networksegregation
Thereasonthatthefirewallportonthebrokernetworkislistedas9094ratherthan9092is
toaccommodateanalternatelistener,blockingthedefaultone.Moreonthatlater.
In the arrangement above, the network is partitioned into four static segments. The ZooKeeper
ensemble operates within its own, heavily isolated network segment. This might be a subnet or a
series of mutually-routable subnets. The latter would be used in a multi-site deployment, where
disparatephysicalnetworksareusedtocollectivelyhosttheensemble.Onemayalsouseanoverlay
network, or other forms of virtual networks, to merge physically separate networks into a single
logical network. Again, we must abstain from the detail at this point, and stick to the logical
viewpoint.
The brokers operate within a dedicated network segment, which is separated from the ZooKeeper
segment by way of a stateful firewall. This ensures that legitimate connections can be established
from the brokers to the ZooKeeper nodes, but only those connections and nothing else. No other
networksegmentispermittedtoaccesstheZooKeepersegment.Inpractice,theremaybeaneedto
makespecificexclusionsforadministrativepurposes,sothatauthorizedoperatorsofKafkaareable
tomakechangestotheunderlyingZooKeeperconfiguration,deploysoftwareupdates,andsoforth.
The next outermost segment is the internal client network. This includes producer, consumer and
admin clients — essentially the full suite of internal applications that rely on Kafka to shuttle
event data. There may be multiple such client networks, or the client network may be fragmented
internally. This often happens in hybrid deployment topologies involving different sites. But the
topology is more or less stable; in other words, we don’t expect entire sites to come and go on a
whim.

Chapter16: Security 276
The fourth network segment is everything outside the perimeter, which can simply be treated as
the external network. In the simplest case, the external network is the public Internet. However,
thisisarelativeterm.Inmoreelaboratesegregationtopologies,theexternalsegmentmightstillbe
within the organisation’s perimeter, but outside the client network. It may be the general-purpose
staff network or some other corporate intranet. And while in a relative sense, this network might
be more trusted than the public Internet, its level of assurance is still largely classified as a ‘walk-
in’ network — it should not be allowed to interface directly with the key technology infrastructure
underpinningtherevenue-generatingsystemsthatpropeltheorganisation.
In addition to the static segments, there may be a case for one or more dynamic network segments
fortheclientecosystemthatcouldappearanddisappearatshortnotice.Acommonusecaseisedge
computing, where sites may be spun up ‘closer to the action’, so to speak, but the sites themselves
might not be long-lived or may change their location. There may be other ways to allow remotely-
deployed applications to utilise Kafka— for example,via dedicated APIs. However, Kafkadoes not
precluderemoteaccessandinsomecasesthismaybedesirable—forexample,theremotesitemay
have a requirement to persist events internally but might not have a local deployment of Kafka at
itsconvenience.EdgecomputingdovetailsnicelyintoKafka’sauthorizationcontrols,whichwillbe
discussedlater.
Perhaps the most common use case for remote connectivity is telecommuting. Engineers may be
workingfromavarietyoflocations,whichcouldbeamixtureofprivatenetworks,publicnetwork,
fixedlocationandmobilenetworks.Clearly,thesesitescannotbeeasilycontrolledandaretherefore
labelledasuntrusted.Nonetheless,telecommuterswillrequirefirst-classaccesstobackendsystems
and key technology infrastructure to maintain productivity. Although some remote sites may be
untrusted, the individual hosts may still be trusted — for example, a remote worker accessing the
corporatenetworkoverafreeWi-Fiinacafé.
Edgelocationsthatrequiredirectconnectivityintothecoreclientnetworkarebestaccommodated
usingsecurevirtualnetworks,suchasVPNs.Ratherthanmaintainingtemporary‘pinhole’firewall
rules that allow access from specific locations based on origin network addresses (which may not
alwaysbediscernible,particularlyifthesiteisbehindaNATdevice),aVPNcanbeusedtosecurely
span physically separate networks. The sites (or individual hosts) would be dispensed individual
credentials,whichmaybedistributedintheformofclient-sideDigitalCertificates.Thesecredentials
will be used to authenticate the site (or host) to the central VPN gateway. The VPN gateway
typically resides behind the firewall, although some firewall vendors combine VPN and packet-
filtering capabilities into a single appliance. (The latter is sometimes called a VPN concentrator.)
Kafka need not be aware of edge locations and VPN arrangements. Instead, the responsibility of
accommodating remote sites falls upon the client network and its maintainers. As long as remote
sitescansecurelyattachtoandtransitthroughtheclientnetwork,theywillhaveaccesstotheKafka
cluster.

Chapter16: Security 277
Confidentiality
Kafka supports Transport Layer Security (TLS) for encrypting traffic between several key compo-
nents.Unlikethediscussiononnetworktrafficpolicy,whichmaintainedapurelytheoreticalstance,
this section offers hands-on examples for configuring TLS and securely connecting producer and
consumerclientsoverencryptedlinks.
ThereadermaybefamiliarwithTLSjustbyvirtueofbrowsingwebsites.Youmayhavenoticedthe
padlockontheaddressbarofthebrowser—thisisanindicationthatthebrowseriscommunicating
overanencryptedconnection.HTTPSisincreasinglyusedinplaceofHTTPtosecurewebsites;the
‘S’ in HTTPS stands for ‘secure’ and implies the use of SSL and TLS algorithms. In nutshell, SSL
is obsolete and TLS is the new name of the older SSL protocol. Technically, the term TLS is more
accurate,butmostpeoplestilluseSSL.DigitalCertificatesusedtoverifythecommunicatingparties
are often referred to as ‘SSL Certificates’, but the name is purely historical — in fact, they can be
usedtosecurebothSSLandTLStraffic.
LikemostpartsofJavaandtherestoftheworld,KafkausesthetermSSLtorefertoTLS.Thelatest
versionofTLSatthetimeofwritingisTLSv1.3.ThelatestversionsupportedbyKafkaisTLSv1.2.
From a security standpoint, the differences between v1.3 and v1.2 are generally considered to be
minor—v1.3removescertaindeprecatedciphersthathaveknownexploits,reducingthelikelihood
of a misconfiguration impacting the security posture. Where v1.3 trumps its predecessor is in the
areaofperformance—specifically,inconnectionestablishmenttime.
At the time of writing, support for TLS v1.3 is scheduled to be introduced in version 2.5.0 of Kafka.
The main reason for the delayed adoption of TLS v1.3 in Kafka is that it introduces a dependency
on Java 11, which is a step up from its current reliance on Java 8, but also a break in runtime
compatibility.Allinall,whencorrectlyconfigured,TLSv1.2isconsideredtobeadequateforgeneral
use, and mandated by the Payment Card Industry (PCI) Security Standards Council for the Data
SecurityStandard(DSS).
An essential element of TLS is an X.509 Certificate, often referred to as ‘Digital Certificate’ or
simply ‘certificate’. At a minimum, a certificate authenticates the server side of a TLS connection
to the client, the latter being the entity that initiates the connection, while the former accepts the
connection on a TLS socket. In our case, the server is a broker node. By verifying the certificate
presentedbythebrokertotheclientagainsteitheratrustedcertificateauthorityorapre-agreedself-
signedcertificate,theclientcanbeassuredthatthebrokeris,infact,whoitclaimstobe.Certificates
canalsobeusedintheoppositedirection,attestingtheidentityofaclienttothebroker.Thisscenario
willbecoveredlater,inthecourseofauthentication.
Incryptography,X.509isastandarddefiningtheformatofpublic-keycertificates.X.509certificates
are used in TLS/SSL, which is the basis for HTTPS. They are also used in offline applications that

Chapter16: Security 278
requiretamper-proofelectronicsignatures.AnX.509certificatecontainsapublickeyandanidentity
(ahostname,anorganisation,oranindividual)—thisisdepictedineithertheCommonName(CN)
attribute of the certificate or the Subject AlternativeName (SAN), the latter being an extensionto
theX.509basestandard.
A certificate is either signed by a Certificate Authority (CA) or self-signed. When a certificate
is signed by a trusted certificate authority, or validated by other means, someone holding that
certificate can rely on the public key it contains to establish secure communications with another
party,orvalidatedocumentsdigitallysignedbythecorrespondingprivatekey.
A certificate authority is akin to a government office that issues passports. Passports are
printed on specially-crafted materials and use various stamps, watermarks, ultraviolet ink,
magnetic strips and holograms to make them difficult to forge. Other governments may
inspectthepassportusingtheverificationmeansattheirdisposaltoascertainitsauthenticity.
Whereas a passport predominantly relies on physical means for ensuring security, the
signing of X.509 certificates achieves the equivalent using cryptographic algorithms, with
the certificate authority acting as the digital equivalent of a passport office. For as long as
the CA remains a genuine and trusted authority, the clients have the assurance that they
areconnectingtoauthenticparties.(Morerecently,passportshavealsostartedembedding
anRFIDchipwithacryptographicmodulethatholdsdigitallysignedbiometricdataofthe
passportholder—addingdigitalsecuritytoatraditionallyphysicaldevice.)
Complementary to X.509 certificates is Public Key Infrastructure (PKI) — a comprehensive set of
roles, policies, hardware, software and procedures needed to create, manage, distribute, use, store
andrevokedigitalcertificatesandmanagepublic-keyencryption.PKIisanarrangementthatbinds
publickeyswithrespectiveidentitiesofentities(individuals,hostsandorganisations).Thebindingis
establishedthroughaprocessofregistrationandissuanceofcertificatesatandbyaCA.Depending
on the assurance level of the binding, this may be carried out by an automated process or under
humansupervision.
Thediagrambelowillustratestherelationshipbetweenapairofcommunicatingparties—aclient
and a server — and the supporting mechanisms by which the integrity and confidentiality of the
informationexchangeisguaranteed.

Chapter16: Security 279
SecuringcommunicationsusingSSL/TLS
It should be stated from the outset that the use of a Certificate Authority and PKI is unequivocally
preferredoverself-signedcertificates.ItisnotthatCA-signedcertificatesareinherentlymoresecure
from a purely cryptographic perspective; rather, the use of PKI makes it easier to manage large
numbersofcertificates,streamliningtheprocessofissuing,rotating,andrevokingcertificates.This
aspect makes CA-signed certificates more robust overall, as the likelihood of an error is greatly
reduced, compared to the bespoke process of managing self-signed certificates. In addition, self-
signed certificates must be exchanged out of band, before the parties can communicate securely.
Thisincreasesthelikelihoodofthembeinginterceptedandmodifiedenroute,whichalsoerodesthe
trustonemayplaceinself-signedcertificates.
Fortheupcomingexamples,wearegoingtogenerateourownCAforsigningcertificates.Naturally,
wewouldhaveproceededwithacompletePKIscenario,butinpractice,thechoiceofPKItechnology
elementsandtheirdeploymentoptionswillbeindependenttotherestoftechnologyinfrastructure
choices.RatherthandistractthereaderwiththenuancesofPKI,thefocuswillbeongettingasecure
connectionupandrunning,withtheblissfulassumptionthatPKIissomeoneelse’sproblem.
Toworkthroughtheexamplesbelow,youwillneedopensslandkeytoolinstalled.Theseareopen-
source packages that will either be pre-installed or can easily be downloaded through a package
manager. When generating keys and certificates, you will end up with sensitive material that
shouldn’t be left unattended, even if it is on your local machine. As such, it is best to create a
dedicated directory for all intermediate operations, then delete the directory once you are done. In
theupcomingexamples,wewillbeworkingoutof /tmp/kafka-ssl.
Java applications use keystore and truststore files to store keying material. The contents of these
filesareencodedinaformatspecifictotheJavaecosystem.Keystorefilesholdprivatekeysandthe

Chapter16: Security 280
associatedsignedcertificates,presentedbytheclientorserverapplicationsthatholdthem.Truststore
files house the trusted certificates to authenticate the opposing party. Both files are password-
protected. In the case of a keystore, individual keys may be password-protected. The examples in
thischapterusethepasswordsecret.Thisshouldbesubstitutedwithamoreappropriatestring.
All examples use a validity period of 365 days for keys and certificates. This period can easily
be changed by specifying an alternate value for the -days flag (to the openssl command) or the
-validityflag(tokeytool).
Client-to-broker encryption
We need to generate a key and certificate for each broker in the cluster. The common name of the
broker certificate must match the fully qualified domain name (FQDN) of the broker, as the client
compares the CN with the resolved hostname to make sure that it is connecting to the intended
broker (instead of a malicious one). This process is called hostname verification, and is enabled by
default. In our examples, we will assume that we are targetting our local test cluster, and therefore
theCNwillsimplybelocalhost.Wecoulduseanarbitraryhostnameoronecontainingawildcard,
provideditmatchesthehostnameusedinthebootstraplist,aswellasthecorrespondinghostname
declaredintheadvertised.listenersbrokerproperty.
The client may disable hostname verification, which will allow it to connect to any host, provided
thatitsauthenticitycanbeverifiedbytraversingthecertificatechain.However,disablinghostname
verification can constitute a serious security flaw, as it makes the client trust any certificate that
was issued by the CA. This may not be cataclysmic if using a private CA that is constrained to
an organisation’s PKI; but when a certificate is issued by a public CA, the absence of hostname
verification allows any party to impersonate a broker, provided it can hijack the DNS. Hostname
verification is an integral part of server authentication. Disabling hostname verification may only
be safely done in non-production workloads, and even then this practice should be discouraged as
itmayinadvertentlyleadtoamisconfigurationofproductiondeployments.
Generate the private key
To begin, we need to generate an SSL key and certificate for each broker. We have just one in our
testcluster;otherwise,thisoperationwouldhavetoberepeated.(Orbetterstill—automated.)Run
thefollowing:
keytool -keystore server.keystore.jks -alias localhost \
-validity 365 -genkey -keyalg RSA
This command will prompt you for a password. After entering and confirming the password, the
next prompt is for the first and last name. This is actually the common name. Enter localhost for
ourtestcluster.(Alternatively,ifyouareaccessingaclusterbyadifferenthostname,enterthatname
instead.)Leaveotherfieldsblank.Atthefinalprompt,hitytoconfirm.

281
| Chapter16:     | Security      |           |                     |       |       |     |     |
| -------------- | ------------- | --------- | ------------------- | ----- | ----- | --- | --- |
| Enter keystore |               | password: |                     |       |       |     |     |
| Re-enter       | new password: |           |                     |       |       |     |     |
| What is your   | first         | and       | last                | name? |       |     |     |
| [Unknown]:     |               | localhost |                     |       |       |     |     |
| What is the    | name          | of        | your organizational |       | unit? |     |     |
[Unknown]:
| What is the | name | of  | your organization? |     |     |     |     |
| ----------- | ---- | --- | ------------------ | --- | --- | --- | --- |
[Unknown]:
| What is the | name | of  | your City | or  | Locality? |     |     |
| ----------- | ---- | --- | --------- | --- | --------- | --- | --- |
[Unknown]:
| What is the | name | of  | your State | or  | Province? |     |     |
| ----------- | ---- | --- | ---------- | --- | --------- | --- | --- |
[Unknown]:
| What is the | two-letter |     | country | code | for this | unit? |     |
| ----------- | ---------- | --- | ------- | ---- | -------- | ----- | --- |
[Unknown]:
| Is CN=localhost, |     | OU=Unknown, |     | O=Unknown, | L=Unknown, |     | ￿   |
| ---------------- | --- | ----------- | --- | ---------- | ---------- | --- | --- |
| ST=Unknown,      |     | C=Unknown   |     | correct?   |            |     |     |
| [no]:            | y   |             |     |            |            |     |     |
Generating 2,048 bit RSA key pair and self-signed certificate ￿
| (SHA256withRSA)    |     |           | with a      | validity | of 365     | days       |     |
| ------------------ | --- | --------- | ----------- | -------- | ---------- | ---------- | --- |
| for: CN=localhost, |     |           | OU=Unknown, |          | O=Unknown, | L=Unknown, | ￿   |
| ST=Unknown,        |     | C=Unknown |             |          |            |            |     |
Theresultwillbeaserver.keystore.jksfiledepositedintothecurrentdirectory.
Youcanviewthecontentsofthekeystoreatanytime—byrunningthefollowingcommand.(Itwill
requireapassword.)
| keytool -list  |                 | -v -keystore |         | server.keystore.jks |     |     |     |
| -------------- | --------------- | ------------ | ------- | ------------------- | --- | --- | --- |
| Enter keystore |                 | password:    |         |                     |     |     |     |
| Keystore       | type:           | PKCS12       |         |                     |     |     |     |
| Keystore       | provider:       | SUN          |         |                     |     |     |     |
| Your keystore  |                 | contains     | 1 entry |                     |     |     |     |
| Alias name:    | localhost       |              |         |                     |     |     |     |
| Creation       | date:           | 02 Jan.      | 2020    |                     |     |     |     |
| Entry type:    | PrivateKeyEntry |              |         |                     |     |     |     |
| Certificate    | chain           | length:      |         | 1                   |     |     |     |
Certificate[1]:
| Owner: CN=localhost, |     |     | OU=Unknown, |     | O=Unknown, | L=Unknown, | ￿   |
| -------------------- | --- | --- | ----------- | --- | ---------- | ---------- | --- |

282
| Chapter16:  | Security                                |               |             |      |            |            |     |     |
| ----------- | --------------------------------------- | ------------- | ----------- | ---- | ---------- | ---------- | --- | --- |
| ST=Unknown, |                                         | C=Unknown     |             |      |            |            |     |     |
| Issuer:     | CN=localhost,                           |               | OU=Unknown, |      | O=Unknown, | L=Unknown, |     | ￿   |
| ST=Unknown, |                                         | C=Unknown     |             |      |            |            |     |     |
| Serial      | number:                                 | 2e0b92ac      |             |      |            |            |     |     |
| Valid from: |                                         | Thu Jan       | 02 08:51:53 |      | AEDT 2020  | until:     | ￿   |     |
| Fri         | Jan                                     | 01 08:51:53   | AEDT        | 2021 |            |            |     |     |
| Certificate |                                         | fingerprints: |             |      |            |            |     |     |
| SHA1:       | 59:2D:AA:C0:A8:B1:CF:B6:F7:CA:B6:C2:21: |               |             |      |            |            | ￿   |     |
55:44:12:27:44:0F:58
| SHA256: | 27:F5:7F:9E:36:A1:B4:0D:72:F6:71:AC: |     |     |     |     |     | ￿   |     |
| ------- | ------------------------------------ | --- | --- | --- | --- | --- | --- | --- |
A0:8B:F2:BB:06:CA:0C:FD:28:64:86:53:6A:37:BF:EF:81:D0:7F:68
| Signature | algorithm |     | name:      | SHA256withRSA |     |     |     |     |
| --------- | --------- | --- | ---------- | ------------- | --- | --- | --- | --- |
| Subject   | Public    | Key | Algorithm: | 2048-bit      | RSA | key |     |     |
| Version:  | 3         |     |            |               |     |     |     |     |
Extensions:
| #1: ObjectId:        |     | 2.5.29.14 | Criticality=false |     |          |       |       |     |
| -------------------- | --- | --------- | ----------------- | --- | -------- | ----- | ----- | --- |
| SubjectKeyIdentifier |     |           | [                 |     |          |       |       |     |
| KeyIdentifier        |     | [         |                   |     |          |       |       |     |
| 0000: B2             | 00  | B4 C1     | BA 4A 5E          | FC  | 9B 44 B7 | 29 F3 | 78 A2 | CD  |
| 0010: 4A             | BA  | 6D 4E     |                   |     |          |       |       |     |
]
]
*******************************************
*******************************************
| Create | a CA |     |     |     |     |     |     |     |
| ------ | ---- | --- | --- | --- | --- | --- | --- | --- |
Bearingaprivatekeyaloneisinsufficient,asitdoesnotidentifytheuserofthekeyorinstiltrustin
it.Thefollowingstepcreatesacertificateauthorityforsigningkeys.
| openssl | req | -new | -x509 -keyout |     | ca-key -out | ca-cert | -days | 365 |
| ------- | --- | ---- | ------------- | --- | ----------- | ------- | ----- | --- |
You are required to provide a password, which may differ from the password used in the previous
step. Leave all fields empty with the exception of the Common Name, which should be set to
localhost.

Chapter16: Security 283
Generating a 2048 bit RSA private key
.........+++
..............+++
writing new private key to 'ca-key'
Enter PEM pass phrase:
Verifying - Enter PEM pass phrase:
-----
You are about to be asked to enter information that will be
incorporated into your certificate request.
What you are about to enter is what is called a
Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) []:
State or Province Name (full name) []:
Locality Name (eg, city) []:
Organization Name (eg, company) []:
Organizational Unit Name (eg, section) []:
Common Name (eg, fully qualified host name) []:localhost
Email Address []:
Theabovecommandwilloutputca-keyandca-certfilestothecurrentdirectory.
The next two steps will import the resulting ca-cert file to the broker and client truststores. Once
imported,thepartieswillimplicitlytrusttheCAandanycertificatesignedbytheCA.
keytool -keystore client.truststore.jks -alias CARoot \
-import -file ca-cert
Enter keystore password:
Re-enter new password:
Owner: CN=localhost
Issuer: CN=localhost
Serial number: 8f444e15ad8f7067
Valid from: Thu Jan 02 09:28:45 AEDT 2020 until: ￿
Fri Jan 01 09:28:45 AEDT 2021
Certificate fingerprints:
SHA1: 70:55:42:23:69:A1:EA:E8:13:49:41:CC:C3:CE:￿
A3:7B:CB:25:F8:08
SHA256: 7E:CC:21:57:5B:8C:FB:90:D9:9E:2B:84:76:￿

284
| Chapter16: | Security |     |     |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- | --- | --- |
C4:E1:83:D0:2D:B5:D1:17:3A:D2:D5:5A:4D:C5:CB:F3:9B:32:DD
| Signature   | algorithm    |           | name: SHA256withRSA |          |     |     |     |
| ----------- | ------------ | --------- | ------------------- | -------- | --- | --- | --- |
| Subject     | Public       | Key       | Algorithm:          | 2048-bit | RSA | key |     |
| Version:    | 1            |           |                     |          |     |     |     |
| Trust this  | certificate? |           | [no]:               | y        |     |     |     |
| Certificate |              | was added | to keystore         |          |     |     |     |
Repeatforserver.truststore.jks:
| keytool | -keystore |       | server.truststore.jks |     |     | -alias CARoot | \   |
| ------- | --------- | ----- | --------------------- | --- | --- | ------------- | --- |
| -import |           | -file | ca-cert               |     |     |               |     |
GeneratingprivatekeysandthecreationofaCAaremutuallyindependentoperationsand
may be performed in either order. The results of these operations will be combined in the
signingstage.
| Sign the | broker |     | certificate |     |     |     |     |
| -------- | ------ | --- | ----------- | --- | --- | --- | --- |
Thenextstepistogeneratethecertificatesigningrequestonbehalfofthebroker.
| keytool  | -keystore |       | server.keystore.jks |     | -alias | localhost | \   |
| -------- | --------- | ----- | ------------------- | --- | ------ | --------- | --- |
| -certreq |           | -file | cert-req            |     |        |           |     |
Thisproducescert-req,beingthesigningrequest.TosignwiththeCA,runthefollowingcommand.
| openssl                                            | x509        | -req | -CA ca-cert | -CAkey              | ca-key | -in cert-req | \   |
| -------------------------------------------------- | ----------- | ---- | ----------- | ------------------- | ------ | ------------ | --- |
| -out                                               | cert-signed |      | -days       | 365 -CAcreateserial |        |              |     |
| Signature                                          | ok          |      |             |                     |        |              |     |
| subject=/C=Unknown/ST=Unknown/L=Unknown/O=Unknown/ |             |      |             |                     |        |              | ￿   |
OU=Unknown/CN=localhost
| Getting    | CA Private |     | Key         |     |     |     |     |
| ---------- | ---------- | --- | ----------- | --- | --- | --- | --- |
| Enter pass | phrase     |     | for ca-key: |     |     |     |     |
Thisresultsinthecert-signedfile.
TheCAcertificatemustbeimportedintotheserver’skeystoreundertheCARootalias.

Chapter16: Security 285
keytool -keystore server.keystore.jks -alias CARoot \
-import -file ca-cert
Enter keystore password:
Owner: CN=localhost
Issuer: CN=localhost
Serial number: 8f444e15ad8f7067
Valid from: Thu Jan 02 09:28:45 AEDT 2020 until: ￿
Fri Jan 01 09:28:45 AEDT 2021
Certificate fingerprints:
SHA1: 70:55:42:23:69:A1:EA:E8:13:49:41:CC:C3:CE:A3: ￿
7B:CB:25:F8:08
SHA256: 7E:CC:21:57:5B:8C:FB:90:D9:9E:2B:84:76:C4: ￿
E1:83:D0:2D:B5:D1:17:3A:D2:D5:5A:4D:C5:CB:F3:9B:32:DD
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 1
Trust this certificate? [no]: y
Certificate was added to keystore
Then,importthesignedcertificateintotheserver’skeystoreunderthelocalhostalias.
keytool -keystore server.keystore.jks -alias localhost \
-import -file cert-signed
Resultingin:
Enter keystore password:
Certificate reply was installed in keystore
Deploy the private key and signed certificate to the broker
With key generation and signing operations completed, the next step is to install the private key
and the signed certificate on the broker. Assuming the keystore file is in /tmp/kafka-ssl, run the
following:
cp /tmp/kafka-ssl/server.*.jks $KAFKA_HOME/config
Configure the broker to use SSL
Editserver.propertiesandmakechangestoreflectthefollowing.

Chapter16: Security 286
listeners=PLAINTEXT://:9092,SSL://:9093
advertised.listeners=PLAINTEXT://localhost:9092,SSL://localhost:9093
listener.security.protocol.map=PLAINTEXT:PLAINTEXT,\
SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
inter.broker.listener.name=PLAINTEXT
ssl.keystore.location=\
/Users/me/opt/kafka_2.13-2.4.0/config/server.keystore.jks
ssl.keystore.password=secret
ssl.key.password=secret
ssl.truststore.location=\
/Users/me/opt/kafka_2.13-2.4.0/config/server.truststore.jks
ssl.truststore.password=secret
Lookingovertheconfiguration—
• Thefirstchangeistheadditionof SSL://:9093tothelistenerslist.Thiscreatesanewserver
socket,boundtoport9093.
• ThesocketisadvertisedasSSL://localhost:9093intheadvertised.listeners.
• The listener.security.protocol.map and inter.broker.listener.name properties remain
unchangedforthisexample,aswehavenotdefinedanewprotocol,norhavewechangedhow
thebrokerscommunicatewitheachother.
• The addition of ssl... properties configures the broker to use the keying material that was
generatedintheprevioussteps.
Havingsavedserver.properties,restartthebrokerforthechangestotakeeffect.
In the configuration above, the SSL settings were defined in global scope — apply-
ing uniformly to all listeners configured to use TLS, including the interbroker connec-
tion (if applicable). Kafka allows us to specify custom SSL settings for individual lis-
teners, by prefixing the ssl... property names with the lowercase name of the lis-
tener, in the form listener.name.<lowercase_name>.<setting>=<value>; for example,
listener.name.external.ssl.keystore.password=secret.
Deploy the CA certificate to the client
The previous client examples were communicating with the broker over a cleartext connection. In
ordertoenableSSL,wemustfirstcopytheclient.truststore.jksfiletooursourcecodedirectory:
cp client.truststore.jks ~/code/effectivekafka

287
| Chapter16: | Security   |            |     |
| ---------- | ---------- | ---------- | --- |
| Configure  | the client | to use SSL |     |
The complete source code listings for the SSL producer and consumer
client examples are available at github.com/ekoutanov/effectivekafka³¹ in the
src/main/java/effectivekafka/ssl directory. The keystore and truststore files have
notbeencommittedtotherepository,astheyvarybetweendeployments.
The following example configures a producer client to use SSL. With the exception of new SSL-
related configuration properties, it is otherwise identical to the original producer sample presented
inChapter5:GettingStarted.
| import static | java.lang.System.*; |     |     |
| ------------- | ------------------- | --- | --- |
import java.util.*;
import org.apache.kafka.clients.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.config.*;
import org.apache.kafka.common.serialization.*;
| public final | class                | SslProducerSample    | {     |
| ------------ | -------------------- | -------------------- | ----- |
| public       | static void          | main(String[]        | args) |
| throws       | InterruptedException |                      | {     |
| final        | var topic            | = "getting-started"; |       |
| final        | Map<String,          | Object> config       | = Map |
.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9093",
CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
"SSL",
SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
"https",
SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
"client.truststore.jks",
SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
"secret",
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
³¹https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/ssl

288
Chapter16: Security
true);
try (var producer = new KafkaProducer<String, String>(config)) {
| while (true)           | {     |            |                    |       |        |     |
| ---------------------- | ----- | ---------- | ------------------ | ----- | ------ | --- |
| final var              | key   | = "myKey"; |                    |       |        |     |
| final var              | value | = new      | Date().toString(); |       |        |     |
| out.format("Publishing |       |            | record with        | value | %s%n", |     |
value);
| final Callback        |     | callback  | = (metadata,   | exception) |            | -> {   |
| --------------------- | --- | --------- | -------------- | ---------- | ---------- | ------ |
| out.format("Published |     |           | with metadata: |            | %s, error: | %s%n", |
|                       |     | metadata, | exception);    |            |            |        |
};
| // publish        | the | record,                 | handling | the metadata |      | in the callback |
| ----------------- | --- | ----------------------- | -------- | ------------ | ---- | --------------- |
| producer.send(new |     | ProducerRecord<>(topic, |          |              | key, | value),         |
callback);
| // wait | a second | before | publishing | another |     |     |
| ------- | -------- | ------ | ---------- | ------- | --- | --- |
Thread.sleep(1000);
}
}
}
}
The first point of difference is the use of the security.protocol property, which specifies the use
of SSL. This property holds a compound value — not only does it specify the encryption scheme,
butalso the authentication scheme.As wearenot usingauthentication in this example,its value is
simplySSL.
Thessl.endpoint.identification.algorithmpropertyspecifieshowthebroker’shostnameshould
bevalidatedagainstitscertificate.Thedefaultvalueishttps,enablinghostnameverification.(This
istherecommendedsetting.)Todisablehostnameverification,setthispropertytoanemptystring.
Thessl.truststore.locationandssl.truststore.passwordpropertiesarerequiredtoaccessthe
CAcertificatethatwehavejustconfigured.
Theconsumerexampleisanalogousandisomittedforbrevity.
Interbroker encryption
The changes that were made so far focused on securing the communications between brokers and
clients,leavingbrokerstomingleusingtheregularcleartextprotocol.Fortunately,configuringKafka
touseSSLforinterbrokercommunicationsisatrivialmatter.Editserver.propertiesandmakethe
followingone-linechange.

289
| Chapter16: | Security |     |     |
| ---------- | -------- | --- | --- |
inter.broker.listener.name=SSL
Restart the broker for the change to take effect. To verify that SSL is being used, check the startup
logs.Theservershouldoutputthecurrentvalueoftheinter.broker.listener.nameproperty.You
can also run netstat to ensure that there are no remaining connections on port 9092, and that all
trafficisnowbeingservedonport9093.
| netstat -an | | egrep "9092|9093" |                 |             |
| ----------- | ------------------- | --------------- | ----------- |
| tcp4 0      | 0 127.0.0.1.9093    | 127.0.0.1.65134 | ESTABLISHED |
| tcp4 0      | 0 127.0.0.1.65134   | 127.0.0.1.9093  | ESTABLISHED |
| tcp46 0     | 0 *.9093            | *.*             | LISTEN      |
| tcp46 0     | 0 *.9092            | *.*             | LISTEN      |
The filtered output of shows that, although both ports are listening, only is fielding
netstat 9093
anactiveconnection.
OnceithasbeenverifiedthatSSLisworkingasplanned,bothfortheclient-brokerandinterbroker
use cases, the recommended next step is to disable the cleartext listener. This is accomplished by
updating all brokers and all clients first, allowing for both cleartext and SSL connections in the
interim. Only once all clients and brokers have switched to the SSL listener, can the PLAINTEXT
optionberemoved.
Disablingthecleartextlistenercanbedoneinoneoftwoways.Themoststraightforwardapproach
iseditingthestaticconfigurationinserver.properties—removingthePLAINTEXTentryfromboth
the listeners and advertised.listeners properties. The second way is to do it remotely, using
per-brokerorcluster-widedynamicupdatemodes.Dynamicconfigurationupdatesarediscussedin
Chapter9:BrokerConfiguration.
AreaderknowledgeableincryptographymayexperienceaslightconfusionaroundKafka’s
somewhat cavalier overloading of the term ‘plaintext’. The PLAINTEXT protocol refers to a
cleartexttransmission,inthatitdeliberatelyavoidsencryption.Itisnotan‘inputtoacipher’,
astheterm‘plaintext’mightordinarilyimply.
Once the cleartext listener has been disabled, it is good practice to follow up with a corresponding
| firewalldeny        | ruleforalltrafficinboundonport9092. |     |     |
| ------------------- | ----------------------------------- | --- | --- |
| Broker-to-ZooKeeper | encryption                          |     |     |
While traffic between the Kafka brokers and the ZooKeeper ensemble can also be encrypted, the
current version of Kafka (2.4.0 at the time of writing) does not support this feature natively. KIP-
513³²istargetingthisforinclusioninrelease2.5.0.Tobeclear,thelimitationisspecificallywiththe
brokercomponentofKafka,notZooKeeper.ThelattersupportsmutualTLS.
³²https://cwiki.apache.org/confluence/x/Cg6YBw

Chapter16: Security 290
In the meantime, for the particularly security-minded deployments where encryption between
brokers and the ensemble is a mandatory requirement, consider tunnelling the connection over a
secure point-to-point link. This can be accomplished by positioning VPN terminators directly on
the broker nodes, such that unencrypted traffic never leaves a broker. Alternatively, one can use a
servicemeshoralightweightproxycapableoftransparentlyinitiatingTLSconnections,whichare
thennativelyterminatedonZooKeeper.
Encryption at rest
The encryption methods discussed earlier protect the confidentiality of data in transit. When data
arrivesatthebroker,itwillbepersistedtoitsattachedstorageinindexedlogsegments—incleartext.
Kafkadoesnothavenativefacilitiesforenablingencryptedstorageofrecorddata.Onehastoresort
to external options to accomplish this. The two popular approaches are full disk encryption and
filesystem-level encryption. Both ensure that the disk is protected from unauthorized access when
itisdetachedfromthehost.
While both forms of storage encryption provide a high level of protection against threats outside
the host, the only way to protect the confidentiality of persisted data from embedded threats
(rogueprocessesexecutingonthehost)istoutiliseend-to-endencryption.Thisinvolvesencrypting
outgoingrecordsontheproducerwitheitherashared-keyoranasymmetriccipher—tobedecrypted
at the consumer end. This strategy ensures that neither the broker nor any intermediate conduits
are aware of record contents. Kafka does not support end-to-end encryption natively. There is a
provisional KIP-317³³ that discusses the prospect of adding this capability in the future. There are
several open-source projects that implement this capability over the top of Kafka’s serialization
mechanism. One such example is the open-source Kafka Encryption project, hosted on GitHub at
github.com/Quicksign/kafka-encryption³⁴.
When using end-to-end encryption, the information entropy of record batches approaches
its theoretical maximum of unity. Therefore, it is best to disable compression, as the latter
willonlyburnthroughCPUcycleswithoutdecreasingthepayloadsize.(Ifanything,itwill
likely go up due to the overheads of compression.) For more information on compression,
seeChapter12:BatchingandCompression.
End-to-endencryption,beingfocusedsolelyontherecordpayload,doesnoteliminatetheneedfor
transportlayersecurity.SSL(TLS)coversallaspectsoftheinformationexchangebetweenclientsand
brokers,includingmetadata,recordheaders,offsets,groupmembership,andsoon.SSLalsoprotects
the integrity of the data, guards against man-in-the-middle attacks, and uses X.509 certificates to
provideassurancetotheclientpartythatthebrokerisauthentic.
³³https://cwiki.apache.org/confluence/x/AFIYBQ
³⁴https://github.com/Quicksign/kafka-encryption

Chapter16: Security 291
Authentication
Kafkasupportsseveralmodesforattestingtheidentityoftheconnectedclients.Thissectionexplores
theauthenticationoptionsatourdisposal.
Mutual TLS
Mutual TLS (mTLS), also known as client-side X.509 authentication or two-way SSL/TLS, utilises
thesame principle ofcertificate signing used byconventionalTLS, but inthe opposite direction.In
additiontothemandatoryserver-sideauthentication,theclientpresentsacertificatethatisverified
by the broker. Each client will have a dedicated certificate, signed by a CA that is trusted by the
broker.ItmaybethesameCAusedforthebrokercertificatesigning,asisoftenthecase.
To enable client authentication on the broker, one must set the ssl.client.auth property in
server.properties.Thepermissiblevaluesare:
• none:Clientauthenticationisdisabled.
• requested: Client may optionally initiate SSL authentication, but this is not mandated by the
broker.However,shouldtheclientpresentitscertificate,itwillbeverified.
• required: The broker mandates the use of client-side SSL authentication. The client will not
beallowedtoconnectwithoutavalidcertificate.
Therequestedoptionis a permissive‘halfwayhouse’setting that enables the gradualmigration to
mTLSfromunauthenticatedTLS.Tobeginthetransition,setallbrokerstousessl.client.auth=requested.
This can be done statically — by editing each individual server.properties file and bouncing the
brokers, or dynamically — via the admin API or the CLI tools. Once all brokers are running in
permissive mode, begin the rollout of client updates. Once all clients have been verified to work
stablywithmTLS,upgradethessl.client.authsettingtorequired.
Inordertousethisauthenticationscheme,theclientmustbeequippedwithadedicatedprivatekey,
signedwithaCAcertificatethatistrustedbythebroker.Thekeyandcertificateshouldbeinstalled
in the keystore.client.jks file, alongside the existing truststore.client.jks file. The process
belowmimicsthepreviousexamples.
The following provides a worked example for enabling mutual TLS. Once again, the examples are
going to use the /tmp/kafka-ssl directory. The ca-key and ca-cert files have been carried over
fromthepreviousexamples.
Generate the private key
Generateaprivatekeyfortheclientapplication,usinglocalhostasthevalueforthe‘firstandlast
name’attribute,leavingallothersblank.

292
| Chapter16:     | Security  |           |                     |                |     |        |           |     |
| -------------- | --------- | --------- | ------------------- | -------------- | --- | ------ | --------- | --- |
| keytool        | -keystore |           | client.keystore.jks |                |     | -alias | localhost | \   |
| -validity      |           | 365       | -genkey             | -keyalg        | RSA |        |           |     |
| Enter keystore |           | password: |                     |                |     |        |           |     |
| Re-enter       | new       | password: |                     |                |     |        |           |     |
| What is        | your      | first     | and                 | last name?     |     |        |           |     |
| [Unknown]:     |           | localhost |                     |                |     |        |           |     |
| What is        | the name  | of        | your                | organizational |     | unit?  |           |     |
[Unknown]:
| What is | the name | of  | your | organization? |     |     |     |     |
| ------- | -------- | --- | ---- | ------------- | --- | --- | --- | --- |
[Unknown]:
| What is | the name | of  | your | City | or Locality? |     |     |     |
| ------- | -------- | --- | ---- | ---- | ------------ | --- | --- | --- |
[Unknown]:
| What is | the name | of  | your | State | or Province? |     |     |     |
| ------- | -------- | --- | ---- | ----- | ------------ | --- | --- | --- |
[Unknown]:
| What is | the two-letter |     |     | country | code | for this | unit? |     |
| ------- | -------------- | --- | --- | ------- | ---- | -------- | ----- | --- |
[Unknown]:
| Is CN=localhost,   |                 | OU=Unknown, |             | O=Unknown, |            | L=Unknown,  |            | ￿    |
| ------------------ | --------------- | ----------- | ----------- | ---------- | ---------- | ----------- | ---------- | ---- |
| ST=Unknown,        |                 | C=Unknown   |             | correct?   |            |             |            |      |
| [no]:              | y               |             |             |            |            |             |            |      |
| Generating         | 2,048           | bit         | RSA         | key pair   | and        | self-signed |            |      |
| certificate        | (SHA256withRSA) |             |             | with       | a          | validity    | of 365     | days |
| for: CN=localhost, |                 |             | OU=Unknown, |            | O=Unknown, |             | L=Unknown, | ￿    |
| ST=Unknown,        |                 |             | C=Unknown   |            |            |             |            |      |
Thisleavesaclient.keystore.jksfileinthecurrentdirectory.
| Sign the | client    | certificate |                     |     |     |        |           |     |
| -------- | --------- | ----------- | ------------------- | --- | --- | ------ | --------- | --- |
| keytool  | -keystore |             | client.keystore.jks |     |     | -alias | localhost | \   |
| -certreq |           | -file       | client-cert-req     |     |     |        |           |     |
Thisproducestheclientcertificatesigningrequestfile—client-cert-req.
Next,wewillactionthesigningrequestwiththeexistingCA:
openssl x509 -req -CA ca-cert -CAkey ca-key -in client-cert-req \
| -out | client-cert-signed |     |     | -days | 365 | -CAcreateserial |     |     |
| ---- | ------------------ | --- | --- | ----- | --- | --------------- | --- | --- |
Theresultistheclient-cert-signedfile,readytobeimportedintotheclient’skeystore,alongwith
theCAcertificate.
StartingwiththeCAcertificate:

293
| Chapter16:     | Security  |           |                     |     |        |        |     |     |
| -------------- | --------- | --------- | ------------------- | --- | ------ | ------ | --- | --- |
| keytool        | -keystore |           | client.keystore.jks |     | -alias | CARoot |     | \   |
| -import        |           | -file     | ca-cert             |     |        |        |     |     |
| Enter keystore |           | password: |                     |     |        |        |     |     |
Owner: CN=localhost
| Issuer:     | CN=localhost                                   |                  |             |      |           |        |     |     |
| ----------- | ---------------------------------------------- | ---------------- | ----------- | ---- | --------- | ------ | --- | --- |
| Serial      | number:                                        | 8f444e15ad8f7067 |             |      |           |        |     |     |
| Valid from: |                                                | Thu Jan          | 02 09:28:45 |      | AEDT 2020 | until: | ￿   |     |
| Fri         | Jan                                            | 01 09:28:45      | AEDT        | 2021 |           |        |     |     |
| Certificate |                                                | fingerprints:    |             |      |           |        |     |     |
| SHA1:       | 70:55:42:23:69:A1:EA:E8:13:49:41:CC:C3:CE:A3:￿ |                  |             |      |           |        |     |     |
7B:CB:25:F8:08
| SHA256: | 7E:CC:21:57:5B:8C:FB:90:D9:9E:2B:84:76:C4:￿ |     |     |     |     |     |     |     |
| ------- | ------------------------------------------- | --- | --- | --- | --- | --- | --- | --- |
E1:83:D0:2D:B5:D1:17:3A:D2:D5:5A:4D:C5:CB:F3:9B:32:DD
| Signature   | algorithm    |           | name:       | SHA256withRSA |     |     |     |     |
| ----------- | ------------ | --------- | ----------- | ------------- | --- | --- | --- | --- |
| Subject     | Public       | Key       | Algorithm:  | 2048-bit      | RSA | key |     |     |
| Version:    | 1            |           |             |               |     |     |     |     |
| Trust this  | certificate? |           | [no]:       | y             |     |     |     |     |
| Certificate |              | was added | to keystore |               |     |     |     |     |
Movingontothesignedcertificate:
| keytool        | -keystore |           | client.keystore.jks |         | -alias         | localhost |     | \   |
| -------------- | --------- | --------- | ------------------- | ------- | -------------- | --------- | --- | --- |
| -import        |           | -file     | client-cert-signed  |         |                |           |     |     |
| Enter keystore |           | password: |                     |         |                |           |     |     |
| Certificate    |           | reply     | was installed       |         | in keystore    |           |     |     |
| Configure      | the       | broker    | to                  | require | authentication |           |     |     |
Editserver.properties,addingthefollowingline.
ssl.client.auth=required
Restarttheserverforthechangestotakeeffect.Toverify,youcanruntheproducerclientfromthe
previousexample.Theconnectionshouldfailwiththefollowingerror:

294
| Chapter16: | Security |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- |
1:58:57/583 INFO [kafka-producer-network-thread | producer-1]: ￿
| [Producer           | clientId=producer-1] |                | Failed authentication |     | with ￿ |
| ------------------- | -------------------- | -------------- | --------------------- | --- | ------ |
| localhost/127.0.0.1 |                      | (SSL handshake | failed)               |     |        |
11:58:57/584 ERROR [kafka-producer-network-thread | producer-1]: ￿
| [Producer | clientId=producer-1] |     | Connection | to node | -1 ￿ |
| --------- | -------------------- | --- | ---------- | ------- | ---- |
(localhost/127.0.0.1:9093) failed authentication due to: SSL ￿
| handshake | failed |     |     |     |     |
| --------- | ------ | --- | --- | --- | --- |
Thatistobeexpected;ourSSL-enabledclienthasyettobeconfiguredforclient-sideauthentication.
| Deploy                                | the private | key and signed | certificate | to the | client |
| ------------------------------------- | ----------- | -------------- | ----------- | ------ | ------ |
| cp /tmp/kafka-ssl/client.keystore.jks |             |                | \           |        |        |
~/code/effectivekafka
| Configure | the client | to provide | authentication |     |     |
| --------- | ---------- | ---------- | -------------- | --- | --- |
Enabling mutual authentication on the client requires the addition of several properties — namely,
theSSLkeystoreconfiguration.Inthesnippetbelow,wehaveprovidedthessl.keystore.location,
ssl.keystore.passwordandssl.key.password—beingthemirrorimageoftheserver-sideconfig-
uration.
| final var | config = | new HashMap<String, | Object>(); |     |     |
| --------- | -------- | ------------------- | ---------- | --- | --- |
config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9093");
config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
"SSL");
config.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
"https");
config.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
"client.truststore.jks");
config.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
"secret");
config.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
"client.keystore.jks");
config.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,
"secret");
config.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG,
"secret");
config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName());
config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName());

Chapter16: Security 295
config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
true);
// ... the existing producer code
Identifying the user principal
Whenauthenticatingaclienttothebroker,hostnameverificationisnotperformed.Instead,theuser
principal is taken verbatim as the value of the CN attribute of the certificate. One can change how
a certificate’s attributes translate to a username, by overriding the ssl.principal.mapping.rules
propertytoprovideanalternatemapping.Theformatofthissettingisalist,whereeachrulestarts
withtheprefixRULE:,andspecifiesaregularexpressiontomatchthedesiredattributes,followedby
a/(slash)character,thenbyareplacement,anotherslash,andfinally,byanoptionalUorLcharacter
indicating whether the replacement should be capitalised or forced to lower case, respectively. The
formatisdepictedbelow.
RULE:pattern/replacement/
RULE:pattern/replacement/[LU]
ThereareseveralexampleslistedontheofficialKafkadocumentationpageatkafka.apache.org/documentation³⁵.
MutualTLSisfrequentlyused inmachine-to-machineauthentication— ideal forthetypicalKafka
usecase,wheretheclientpartyisanapplicationthatoperatesindependentlyofitsend-users.There
may be complications, however. The degree of authentication assurance in mTLS is limited by the
levelofrigourpresentinthecertificatesigningprocess.Specifically,thebrokercandeterminewith
ahighlevelofassurancethattheclientisatrustedparty.Butwhichtrustedparty?Thatisthemore
challengingquestion.
Recall, the client is trusted because it presents a valid certificate signed by a trusted issuer. The
username implied by the certificate (represented by the CN by default) is assumed to be strongly
associated with the party that presented the certificate, because this relationship was presumably
independentlyverifiedbytheissuer—aCAthatwetrustapriori.
Consider,forexample,twotrustedclients:C0 andC1.Bothhaveadedicatedprivatekey,signedby
a trusted CA for use with a specific CN. However, if the signing process is naively implemented,
thereislittlestoppingC0 fromissuinganothersigningrequesttotheCA,usingtheusernameofC1
in the CN field. If this were to happen, C0 could connect to the broker with an alternate certificate
— impersonating C1. In order to prevent this from occurring, the process of signing certificates
cannot be left entirely in the clients’ hands — irrespective of whether the clients operate within
the organisation’s perimeter. The relationship between a client and the CA must be individually
authenticated,sothataclientcanonlyissueasigningrequestforaCNthathasbeenauthorizedfor
thatclient.
³⁵https://kafka.apache.org/documentation/#security_authz_ssl

Chapter16: Security 296
Alayeredapproachtoinformationsecurity—theuseofprinciplessuchasDefenceinDepth—imply
thatanorganisationshouldtrustinternalentitieslittlemorethanitshouldtrustexternalones.Recent
studieshaveindicatedthatasmuchas60%ofattacksoriginatefromwithintheperimeter.Andwhile
PKIandpublic-keycryptographyallowustoreasonconcretelyastotheauthenticityortheintegrity
ofdata,theyarepredicatedonassumptionsthatareoftentakenforgranted.
Another challenge of using mTLS is that the identity of clients is not known in advance, as there
is no requirement to explicitly enrol each user principal into Kafka. On one hand, this clearly
simplifies identity generation by decoupling it from the authentication mechanism. On the other
hand, the management of identities is significantly more complicated than username/password-
basedmethods,asKafkadoesnotsupportcertificaterevocation.Inotherwords,iftheprivatekeyof
oneclient(orasmallgroupofclients)becomescompromised,thereisnowaytoinstructthebrokers
toblacklisttheoffendingcertificates.Atbest,onewouldhavetodeployanewCAcertificateinplace
ofthecompromisedoneandsignallremaininglegitimateclientcertificateswiththenovatedCA.
Technically, although certificates cannot be revoked, one can remove all privileges from
the affected user principals, and require the rotation of the affected usernames. Frankly,
this cannot be called a definitive solution — more of a workaround. Although username
rotation fixes the immediate threat, it does so at the authorization layer, which is distinct
from authentication. There is still a threat that the original user’s permissions might be
accidentallyrestoredlater,atwhichpointthecompromisedkeywillbecomeathreatagain.
WithoutregeneratingtheCAcertificate,thereisnowaytopermanentlydisableauser.
CertificaterevocationhasbeenanopenissueinKafkasinceMay2016.Assuch,avoidthe
use of mutual TLS in environments where client deployments are not secure, and where
there is a likelihood of a key compromise — even if the client acts as a low-privilege user
onatestsystem.Whiletheimplicationasinglecompromisemightnotbecatastrophic,the
liquidationoftheconsequenceswillbelongandlaborious.Tothatpoint,whenusingmTLS
withmultipleKafkaclusters(e.g.multipleenvironmentsorgeographicalsites),itisbestto
segregatetrustchains,sothateachclustertrustsitsownintermediateCAcertificatebutnot
the others’, and clients are selectively issued with certificates that are individually signed
forthespecificclustersthattheyarelegitimatelyexpectedtoaccess.
Mutual TLS is used frequently for authenticating clients to managed Kafka providers. The service
consumer will typically provide an intermediate CA certificate to the service provider, which then
allowstheserviceconsumertodeployanynumberofKafkaclientswithdedicatedcertificates.
DespiteoperatingatdifferentlayersoftheOSImodelandbeingindependentintheory,two-
way SSL cannot be used in conjunction with application-level authentication schemes. In
Kafka,itiseitheroneortheother.

Chapter16: Security 297
SASL
Kafka supports Simple Authentication and Security Layer (SASL) — an extensible framework for
embedding authentication in application protocols. The strength of SASL is that it decouples au-
thenticationconcernsfromapplicationprotocols,intheoryallowinganyauthenticationmechanism
supportedbySASLtobeembeddedinanyapplicationthatusesSASL.SASLisnotresponsibleforthe
confidentialityofthemessageexchange,andisthereforerarelyusedonitsown.Themostcommon
deploymentmodelofSASLinvolvesTLS.
GSSAPI
Kafka supports the Generic Security Service API (GSSAPI), which enables compliant security-
service vendors to exchange opaque tokens with participating applications. These tokens are inter-
nally tamper-proof, and are used to establish a security context among collaborating parties. Once
thecontextisestablished,thepartiescancommunicatesecurelyandaccessresources.
GSSAPI is commonly associated with Kerberos, being its dominant implementation. The
primary distinction is that the Kerberos API has not been standardised among vendors,
whereasGSSAPIhasbeenvendor-neutralfromtheoutset.Inamannerofspeaking,GSSAPI
standardisesKerberos.
The official Kafka documentation treats GSSAPI synonymously with Kerberos. The professed rela-
tionshipbetweenGSSAPIandKerberostypicallyextendstocentraliseddirectoryservices—Active
Directory (predominantly due to its native Kerberos version 5 support) and similarly positioned
corporatesinglesign-on(SSO)services.
KerberosandActiveDirectoryworkbestforinteractiveusersinacorporatesetting;however,Kafka
usersarerarelyindividuals,butapplications.EvenwhenaKafkaclientactsonbehalfofanend-user,
itwillordinarilyauthenticateitselftothebrokerviaanend-user-agnosticmechanism—typicallya
serviceaccount.Theauthenticatedsessionoutlivesanysingleuserinteraction;duetotheirtimeand
resourcecosts,itisgenerallyinfeasibletospawnanewsetofconnectionsforeveryuserinteraction,
onlytotearthemdownshortlyafterwards.Intheoverwhelmingmajorityofcases,serviceaccounts
aretheonlypracticalwaytomanageKafkaclients.
In theory, a service account could be provisioned in a centralised corporate authentication server
suchasActiveDirectory.Onthesurface,itevensoundsbeneficial.Bycentralisingserviceaccounts,
itshouldmaketheirgovernanceamorestraightforwardandtransparentaffair.
Whilethetheoreticaladvantagesarenotdisputed,themainchallengewiththecentralisedmodelis
that it only works if the principal in question can be modelled as a resource in a directory and all
theresourcesitconsumesarerepresentedinsomesortofapermissionmodelthatcanbeassociated
withtheprincipal.Inotherwords,anapplicationcanaccessallofitsresourcesusingasinglesetof
credentials. (Or these credentials are somehow related, and their relationship can be modelled ac-
cordingly.)Inreality,backendapplicationcomponentsmayconsumenumerousdisparateresources,

Chapter16: Security 298
not all of which may be enrolled in the directory. For example,a single application may connect to
Kafka, a Postgres database, a Redis cluster, and may consume third-party APIs that are external to
theorganisation.WhileKafkaandPostgressupportKerberos,Redisdoesnot.Andathird-partyAPI
willalmostcertainlynotsupportKerberos.(SomeserviceprovidersmayindirectlysupportKerberos,
typicallyviaafederationprotocol.)
When the time comes to control the permissions of a service account, the administrator might
manipulate the representation of the service account in the directory. Perhaps the service account
should be disabled altogether, which is a trivial ‘one-click’ action in a directory service such as
Active Directory. This creates a false sense of security, as the permissions for the service account
do not cover all resources; while the service account may appear to be disabled in the centralised
directory,theunderlyingprincipalisstillabletooperateonasubsetofitsresources.
It is a common misunderstanding that centralised authentication systems exist to simplify the on-
boarding process. In practice, the benefits of such systems are predominantly concentrated in the
off-boardingscenario.Itisconvenienttoquicklysetupaprofileforanewemployeewithoneclick
ofabutton,grantingthemaccesstoallnecessarysystems.Convenient—butnotessential.Without
a centralised authentication system, the on-boarding process may take days or weeks to complete.
And while this is not ideal, it is mostly the productivity loss that affects the organisation — a cost
thatcanbequantifiedandmanagedaccordingly.Conversely,terminationofemploymentrequires
immediate action from a security standpoint — revoking the employee’s access simultaneously
from all resources previously available to them, preferably before the person has left the premises.
Failuretoactinatimelymanner,orforgettingtoapplythechangetosomeresources,mayposean
immediateandpersistentthreattotheorganisation,particularlyiftheemployeewasnotcompletely
contentwiththecircumstancesoftheirdismissal.
Whilethebenefitsofcentralisedauthenticationareclearforend-usersandadministrators,particu-
larlyinacorporatesetting,theequivalentbenefitscannotbeeasilyreproducedforserviceaccounts
— not unless the organisation mandates that all technical infrastructure supports Kerberos and all
interactionsbetweenapplicationcomponentsandtheirdependenciesareauthenticatedaccordingly.
Statedplainly,thisisapipedream.
For service accounts and similar integration use cases, the prevalent industry trend is in the
migrationawayfromcentraliseddirectory-basedauthenticationsystemstowardscentralisedsecrets
management systems. In other words, rather than managing principals, which we established is
futile, we manage their credentials and any other secret material they need to function. Provided
theprincipalreceivestheentiretyofitscredentialsfromthesecretsmanagementsystemanddoes
not cache them locally, then disabling the principal will eventually affect its ability to consume
downstreamresources,effectivelyisolatingit.
One notable drawback of this scheme is that the effect of disabling a principal may not be timely.
While it makes future retrieval of secret material impossible, it does not necessarily render the
existingmaterialinvalidorobsolete.Ontopofthis,theclientmayhavecachedthesecretmaterial
or the latter may have become compromised by other means. This is why security best-practices

Chapter16: Security 299
suggestfrequentrotationofsecretmaterial,downtotheorderofminutes—thetime-exposureofa
sensitiveartifactistherebyminimised.
This chapter does not delve further into the authentication cases for GSSAPI, not solely because of
the statements above. Rather, the benefit of demonstrating Kerberos is not worth the complexity
of setting it up. It is also adequately documented at kafka.apache.org/documentation³⁶. Instead,
the focus will shift towards other SASL authentication methods that are less draconian, more
straightforward to administer, and are likely to remain relevant to the reader for many years to
come.
PLAIN and SCRAM
The complete source code listings for the SASL producer and consumer
client examples are available at github.com/ekoutanov/effectivekafka³⁷ in the
src/main/java/effectivekafka/sasldirectory.
SASL offers two username/password-based authentication modes: PLAIN and SCRAM. (There are
more in existence, but Kafka just supports these two for authenticating clients.) The former refers
to cleartext authentication where credentials are presented verbatim. The latter is an acronym for
SaltedChallengeResponseAuthenticationMechanism—aprotocoldesignedtofulfilauthentication
withouttheexplicittransferofcredentials.
To the user, there is little discernible difference between the two options. In both cases, the appli-
cation is configured with a username and password pair, with the only difference being the fully-
qualified class name of the JAAS (Java Authentication and Authorization Service) module. Under
thehood,thetwoareverydifferent.PLAINprovidesnoconfidentialityofitsown,requiringtheuse
ofanencryptedchannel.SCRAMissecureinitsownright,providingconfidentialityintheabsence
ofanytransportlayerencryption.
In case the question might arise, PLAIN is not an acronym. The exact origin of the name is
unspecified,butitislikelyacandidcapitalisationoftheadjective‘plain’—being“simpleorbasicin
character”,asopposedtoanabbreviationof‘plaintext’—thecryptographictermmeaning“inputto
acipher”.Indeed,RFC4616specifiesPLAINasasimplemechanismfortheexchangeofpasswords.
The exchange always occurs in cleartext — there is no intention of encryption at the application
level.Assuch,PLAINisintendedtobeusedinconcertwithlower-levelmechanismsthatguarantee
theconfidentialityofdata.QuotingfromtheRFC:
As the PLAIN mechanism itself provides no integrity or confidentiality protections, it
³⁶https://kafka.apache.org/documentation/#security_sasl_kerberos
³⁷https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/sasl

Chapter16: Security 300
shouldnotbeusedwithoutadequateexternaldatasecurityprotection,suchasTLSser-
vicesprovidedbymanyapplication-layerprotocols.Bydefault,implementationsshould
notadvertiseandshouldnotmakeuseofthePLAINmechanismunlessadequatedata
securityservicesareinplace.
https://tools.ietf.org/html/rfc4616
Guided by the Defence in Depth principle, the SCRAM option should be preferred over PLAIN.
Thisensuresthatnocontrolwillsinglehandedlyimpacttheintegrityofthesystemifcompromised.
SCRAMactsinbothdirections:notonlymusttheclientprovetothebrokerthatithasthepassword,
but the broker is required to do the same. (Caveat below.) In other words, SCRAM protects the
client from connecting to a rogue broker, which acts in addition to the certificate-based attestation
mechanismusednativelyinTLS.TheonlypracticaldrawbackofSCRAMoverPLAINistheadded
networkroundtrip,whichisnecessaryforbothpartiestoidentifyoneanother.(SCRAMrequiresa
total of two round-trips.) However, this penalty is paid once, during connection establishment — it
hasnosubsequentbearingonthroughputorlatency.
Although SCRAM authentication is bidirectional, it is not completely symmetric — there
is a subtle difference between the assurance each party provides to the other. Specifically,
the client must prove to the broker that it has present knowledge of the password — at
thetimeofconnectionestablishment;whereasthebrokeronlyneedstoprovethatitknew
the password at some point in time. This is a desirable property — it relieves the broker
fromhavingtopersistthepasswordverbatim,andthereforeriskingexposure;instead,the
brokerpersistsirreversiblederivationsofthepasswordthataresubsequentlyusedformutual
authentication.Intheory,theclientcandiscardthepasswordandstorethehashedversion,
provideditknowsthesaltusedontheserver(whichisdisclosedaspartoftheauthentication
flow).
Because of the similarity in how the two authentication methods appear to the user, the worked
exampleswillfocusontheSCRAMcase.
ConfigurethebrokertouseSASL/SCRAM
WeneedtocreateanewlistenerthatsupportstheuseofSASLauthentication.Editserver.properties,
ensuringthatitisinlinewiththefollowing:

Chapter16: Security 301
listeners=PLAINTEXT://:9092,SSL://:9093,SASL_SSL://:9094
advertised.listeners=PLAINTEXT://localhost:9092,\
SSL://localhost:9093,SASL_SSL://localhost:9094
listener.security.protocol.map=PLAINTEXT:PLAINTEXT,\
SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
sasl.enabled.mechanisms=SCRAM-SHA-512
listener.name.sasl_ssl.scram-sha-512.sasl.jaas.config= \
org.apache.kafka.common.security.scram.ScramLoginModule \
required;
Havingsavedthefile,restartthebrokerforthechangetotakeeffect.
Workingthroughtheabovefile,line-by-line:
• WeaddedanotherlistenernamedSASL_SSL,boundtoport9094.
• ThelistenerhasbeenadvertisedasSASL_SSL://localhost:9094.
• Thesecurityprotocol maphas been leftas is,givenitalready containsanSASL_SSL:SASL_SSL
mappingoutofthebox.
• TheenabledSASLmechanismisSCRAM-SHA-512.
• Thelistener.name.sasl_ssl.scram-sha-512.sasl.jaas.configpropertyprovidesaminimal
JAASdocumentthatmandatestheuseoftheScramLoginModule.
SASL authentication is incompatible with SSL client authentication. When the
ssl.client.auth property is set to requested or required, the SSL authentication
settingswillonlybeappliedtotheSSLlistener(oranylistenermappedtotheSSLsecurity
protocol). When connecting over the SASL_SSL listener, the SSL client authentication
settingswillbeignored.ItwouldhavebeenadvantageoustorunSASLontopofSSLclient
authentication for added security; and while this is possible in theory, Kafka does not
support the conjunction of the two. The reason is that Kafka extracts the user principal
fromtheattributesoftheclientcertificate,whichmayconflictwiththeusernameprovided
overSASL.
Kafka supports SCRAM-SHA-256 and SCRAM-SHA-512. Both can be enabled if need be, by setting the
sasl.enabled.mechanisms broker property to SCRAM-SHA-256,SCRAM-SHA-512. Security-wise, both
SHA-256 and SHA-512 are generally considered to be very strong, with the latter having better
collisionresistance.Differenthardwarefavoursdifferentfunctions,withSHA-512optimisedforuse
on 64-bit processors; SHA-256 being more performant on 32-bit processors. SHA-256 remains the
morecommonchoiceforthetimebeing.
Provisiontheuser
Kafka’s SCRAM implementation uses ZooKeeper as a credential store — for persisting usernames
and hashed passwords (which are also salted). Cleartext passwords are never persisted, but the

302
| Chapter16: | Security |     |     |     |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- | --- | --- | --- |
hashes are world-readable. This implies that ZooKeeper should be deployed on a highly trusted
networkthatisprotectedfromaccessbyunauthorisedparties.Configurationisadministereddirectly
againstZooKeeper,usingthekafka-configs.shCLI.Inthefollowingexample,wewillprovisiona
newuserwiththeusernamealice,thepasswordbeingalice-secret.
| $KAFKA_HOME/bin/kafka-configs.sh |          |                                         |               | --zookeeper |                | localhost:2181 |          | \   |
| -------------------------------- | -------- | --------------------------------------- | ------------- | ----------- | -------------- | -------------- | -------- | --- |
| --alter                          |          | \                                       |               |             |                |                |          |     |
| --add-config                     |          | 'SCRAM-SHA-512=[password=alice-secret]' |               |             |                |                |          | \   |
| --entity-type                    |          | users                                   | --entity-name |             | alice          |                |          |     |
| Completed                        | Updating | config                                  | for           | entity:     | user-principal |                | 'alice'. |     |
When enrolling credentials into ZooKeeper, they need to be specified separately for each
variation of the SCRAM algorithm. In other words, SCRAM-SHA-256 credentials may differ
from SCRAM-SHA-512, and can be administered independently. The kafka-configs.sh CLI
convenientlysupportsspecifyinglistsofkey-valuepairsinthe--add-configflag.
Tolistthecurrentconfigurationforauser,runthefollowing(replacingtheusernameasappropriate):
| $KAFKA_HOME/bin/kafka-configs.sh |     |               |       | --zookeeper |               | localhost:2181 |     | \   |
| -------------------------------- | --- | ------------- | ----- | ----------- | ------------- | -------------- | --- | --- |
| --describe                       |     | --entity-type | users |             | --entity-name | alice          |     |     |
Thiswilloutputthecurrentconfiguration:
| Configs                                                | for | user-principal | 'alice' |     | are SCRAM-SHA-512=salt= |     |     | ￿   |
| ------------------------------------------------------ | --- | -------------- | ------- | --- | ----------------------- | --- | --- | --- |
| ZWhuNHk2b2ZxaHRxcHQxamlhdDYzbzg=,stored_key=hpRpfIoJZc |     |                |         |     |                         |     |     | ￿   |
| Orhy9/6Fh80VsBhNCKKpMTZtWKkCs08H8us15pphr5upfTGjvGYYON |     |                |         |     |                         |     |     | ￿   |
| EeDfw002WUqfT6g+TRJqA==,server_key=y8yhtDe20lH2hK9a6tk |     |                |         |     |                         |     |     | ￿   |
| eidFTL2A3E4KV+Pd6U5QfXc5ndj0nzzfl5N1xX0m5W4EVccDphXIDG |     |                |         |     |                         |     |     | ￿   |
T5wtuEAAnmv5g==,iterations=4096
Notethattheoutputof--describeisdifferenttowhatwasfedastheinputto--add-config.
This is because SCRAM persists derived values of the password. These values can be used
tocryptographicallyverifythemutualknowledgeofthepasswordduringanauthentication
session,buttheoriginalpasswordcannotberecoveredusingthismethod.
Credentialsmaybedeletedusingthe--delete-configflag:

303
| Chapter16: Security              |     |                     |     |             |                |     |
| -------------------------------- | --- | ------------------- | --- | ----------- | -------------- | --- |
| $KAFKA_HOME/bin/kafka-configs.sh |     |                     |     | --zookeeper | localhost:2181 | \   |
| --alter                          | \   |                     |     |             |                |     |
| --delete-config                  |     | 'SCRAM-SHA-512'\    |     |             |                |     |
| --entity-type                    |     | users --entity-name |     |             | alice          |     |
Configuretheclient
ConfiguringaclienttouseSASLrequiresjustafewadditionstotheconfigurationmap.Theexample
belowisafully-functionalproducerthatusesacombinationofSSLandSASL.
| import static | java.lang.System.*; |     |     |     |     |     |
| ------------- | ------------------- | --- | --- | --- | --- | --- |
import java.util.*;
import org.apache.kafka.clients.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.config.*;
import org.apache.kafka.common.security.scram.*;
import org.apache.kafka.common.serialization.*;
| public final  | class                | SaslSslProducerSample |     |       | {   |     |
| ------------- | -------------------- | --------------------- | --- | ----- | --- | --- |
| public static |                      | void main(String[]    |     | args) |     |     |
| throws        | InterruptedException |                       |     | {     |     |     |
| final         | var topic            | = "getting-started";  |     |       |     |     |
final var loginModuleClass = ScramLoginModule.class.getName();
| final | var saslJaasConfig |     | = loginModuleClass |     |     |     |
| ----- | ------------------ | --- | ------------------ | --- | --- | --- |
+ " required\n"
+ "username=\"alice\"\n"
+ "password=\"alice-secret\";";
| final | Map<String, | Object> | config | =   | Map |     |
| ----- | ----------- | ------- | ------ | --- | --- | --- |
.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9094",
CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
"SASL_SSL",
SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
"https",
SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
"client.truststore.jks",
SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
"secret",
SaslConfigs.SASL_MECHANISM,

304
Chapter16: Security
"SCRAM-SHA-512",
SaslConfigs.SASL_JAAS_CONFIG,
saslJaasConfig,
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
true);
try (var producer = new KafkaProducer<String, String>(config)) {
| while (true)           | {     |            |                    |       |        |     |
| ---------------------- | ----- | ---------- | ------------------ | ----- | ------ | --- |
| final var              | key   | = "myKey"; |                    |       |        |     |
| final var              | value | = new      | Date().toString(); |       |        |     |
| out.format("Publishing |       |            | record with        | value | %s%n", |     |
value);
| final Callback        |     | callback  | = (metadata,   | exception) |            | -> {   |
| --------------------- | --- | --------- | -------------- | ---------- | ---------- | ------ |
| out.format("Published |     |           | with metadata: |            | %s, error: | %s%n", |
|                       |     | metadata, | exception);    |            |            |        |
};
| // publish        | the | record,                 | handling | the metadata |      | in the callback |
| ----------------- | --- | ----------------------- | -------- | ------------ | ---- | --------------- |
| producer.send(new |     | ProducerRecord<>(topic, |          |              | key, | value),         |
callback);
| // wait | a second | before | publishing | another |     |     |
| ------- | -------- | ------ | ---------- | ------- | --- | --- |
Thread.sleep(1000);
}
}
}
}
Thedifferencesfromthepure-SSLexampleareinthefollowing:
• Connectiontoadifferentport.Inthiscase,weareconnectingtoport9094.
• Useof SASL_SSLforthesecurity.protocolsetting.
• Additionofthesasl.mechanismproperty,settoSCRAM-SHA-512.
• SpecifyingtheJAASconfigurationviathesasl.jaas.configproperty.
TheJAASconfigurationforexampleaboveis:

Chapter16: Security 305
org.apache.kafka.common.security.scram.ScramLoginModule required
username="alice"
password="alice-secret";
Interbrokerauthentication
AlthoughwechangedclientauthenticationtoSASL_SSL,theinterbrokerlistenerremainedSSLfrom
thefirstexample.Wecanupgradeinterbrokercommunicationstouseauthenticationinadditionto
encryption;however,thisrequiresustoconfigureasetofcredentialsspecificallyforbrokeruse.
Startbycreatingasetofadmincredentials(replaceadminandadmin-secretasappropriate):
$KAFKA_HOME/bin/kafka-configs.sh --zookeeper localhost:2181 \
--alter \
--add-config 'SCRAM-SHA-512=[password=admin-secret]' \
--entity-type users --entity-name admin
Theneditserver.propertiestolineupwiththefollowing.(Onlythechangedlinesareshown.)
inter.broker.listener.name=SASL_SSL
sasl.mechanism.inter.broker.protocol=SCRAM-SHA-512
listener.name.sasl_ssl.scram-sha-512.sasl.jaas.config= \
org.apache.kafka.common.security.scram.ScramLoginModule \
required \
username="admin" \
password="admin-secret";
Thisiswhereweneedtobecareful.Havingspecifiedcredentialsinserver.properties,weshould
harden the file permissions to prevent the file from being read by anyone other than the currently
logged in user. If this user is different from the user that will run Kafka, then the ownership and
permissionsshouldbeadjustedaccordingly.
chmod 600 $KAFKA_HOME/config/server.properties
Havingsavedthefileandadjusteditspermissions,restartthebrokerforthechangetotakeeffect.
Summaryofthechanges:
• TheinterbrokerlistenerwaschangedtoSASL_SSL.
• TheSASLmechanismforinterbrokerusewasassignedviathesasl.mechanism.inter.broker.protocol
property.
• TheJAASconfigurationdefinedinlistener.name.sasl_ssl.scram-sha-512.sasl.jaas.config
wasexpandedtoincludeausernameandpasswordpair.
Having switched the interbroker protocol, run netstat to verify that interbroker connections are
directedatport9094:

306
| Chapter16:  | Security          |                  |                 |     |             |     |
| ----------- | ----------------- | ---------------- | --------------- | --- | ----------- | --- |
| netstat -an | | egrep           | "9092|9093|9094" |                 |     |             |     |
| tcp4 0      | 0 127.0.0.1.9094  |                  | 127.0.0.1.56410 |     | ESTABLISHED |     |
| tcp4 0      | 0 127.0.0.1.56410 |                  | 127.0.0.1.9094  |     | ESTABLISHED |     |
| tcp46 0     | 0 *.9094          |                  | *.*             |     | LISTEN      |     |
| tcp46 0     | 0 *.9093          |                  | *.*             |     | LISTEN      |     |
| tcp46 0     | 0 *.9092          |                  | *.*             |     | LISTEN      |     |
SupplyinganexternalJAASconfiguration
InpreviousSASLexamples,wesuppliedtheJAASconfigurationin-line,viaaconfigurationproperty.
And while this approach is convenient, it may not always be appropriate — depending on whether
theapplicationsupportsthismethodofconfiguration.Wherein-lineconfigurationisnotsupported,
JAAScanbeconfiguredusinganexternalfile.
ToalterthepreviousexampletouseanexternalJAASconfigurationfile,editserver.propertiesand
commentout(orremove)thelistener.name.sasl_ssl.scram-sha-512.sasl.jaas.configproperty.
Savethefile.
Ifyouattempttostartthebrokerinthisstate,itwillbailoutwiththefollowingerror:
[2020-01-02 19:22:25,890] ERROR [KafkaServer id=0] Fatal error ￿
| during                              | KafkaServer    | startup.               | Prepare  | to shutdown | ￿      |     |
| ----------------------------------- | -------------- | ---------------------- | -------- | ----------- | ------ | --- |
| (kafka.server.KafkaServer)          |                |                        | ￿        |             |        |     |
| java.lang.IllegalArgumentException: |                |                        |          | Could not   | find   | a ￿ |
| 'KafkaServer'                       | or             | 'sasl_ssl.KafkaServer' |          | entry       | in the | ￿   |
| JAAS                                | configuration. | System                 | property | ￿           |        |     |
| 'java.security.auth.login.config'   |                |                        |          | is not set  |        |     |
To fix this error, create a file in $KAFKA_HOME/config, with the following
kafka_server_jaas.conf
contents:
| sasl_ssl.KafkaServer |     | {   |     |     |     |     |
| -------------------- | --- | --- | --- | --- | --- | --- |
org.apache.kafka.common.security.scram.ScramLoginModule
required
username="admin"
password="admin-secret";
};
The JAAS configuration contains sensitive information. Ensure it is not readable by other users by
runningthefollowing:

Chapter16: Security 307
chmod 600 $KAFKA_HOME/config/kafka_server_jaas.conf
WhenstartingtheKafkabroker,itneedstobetoldwheretofindtheJAASconfiguration.Thiscanbe
accomplished by setting the java.security.auth.login.config system property, which is passed
viatheKAFKA_OPTSenvironmentvariable,asshownbelow.
JAAS_CONFIG=$KAFKA_HOME/config/kafka_server_jaas.conf \
KAFKA_OPTS=-Djava.security.auth.login.config=$JAAS_CONFIG \
$KAFKA_HOME/bin/kafka-server-start.sh \
$KAFKA_HOME/config/server.properties
SwitchingtoSASL/PLAIN
ItwaspreviouslystatedthatSASL/PLAINandSASL/SCRAMarenotmateriallydifferentiatedfrom
auser’sperspective.TochangeclientsovertoSASL/PLAIN,setthesasl.mechanismclientproperty
toPLAINandreplacethefully-qualifiedclassnameoftheJAASmodulefromorg.apache.kafka.common.security.scram.ScramLoginModule
toorg.apache.kafka.common.security.plain.PlainLoginModule.
On the broker, the differences are slightly more perceptible. PLAIN mode does not use ZooKeeper
tostorecredentials.Instead,credentialsaredefineddirectlyintheJAASconfiguration.
ToenableSASL/PLAIN,editserver.properties,changingthefollowinglines:
sasl.enabled.mechanisms=PLAIN
sasl.mechanism.inter.broker.protocol=PLAIN
listener.name.sasl_ssl.plain.sasl.jaas.config= \
org.apache.kafka.common.security.plain.PlainLoginModule
required \
username="admin" \
password="admin-secret" \
user_admin="admin-secret" \
user_alice="alice-secret";
Also, remove the listener.name.sasl_ssl.scram-sha-512.sasl.jaas.config property from the
SASL/SCRAMexample.(UnlessyouneedtomaintainSCRAMalongsidePLAIN.)
Ontheclient-side,changethevalueofthesasl.mechanismpropertytoPLAIN.
OAuth bearer
TheOAUTHBEARERSASLmechanismenablestheuseofOAuth2.0AccessTokenstoauthenticateuser
principals.TheprimarymotivationforOAuthsupportisnotforsecurity,butforeaseofintegration
and testing, allowing applications to impersonate users by way of anUnsecured JWS token in non-
productionenvironments.Inotherwords,anapplicationcanposeasanarbitraryuserbyissuingan
unsignedJWT(JSONWebToken),wheretheheaderdoesnotspecifyanalgorithm:

Chapter16: Security 308
{
"alg": "none"
}
The JWS Signature used for unsecured tokens is an empty octet string, which base-64
encodessimplytoanemptystring.
Unsecured tokens function out-of-the-box with minimal configuration and with no OAuth 2.0
infrastructure required. The principal’s username is taken directly from the sub (subject) claim in
theJWT.
Initsdefaultguise,theOAUTHBEARERmechanismcannotbeusedsecurelyinproductionenvironments,
asitcannotverifyuserclaims.Inordertoproductionisethisalgorithm,onemustimplementapairof
callbackhandlers.Thecallbacksarenecessarytoallowtheclienttogenerateandsignaccesstokens,
andforthebrokertovalidatethem againstatrustedcertificate.
Ontheclient,implementtheorg.apache.kafka.common.security.auth.AuthenticateCallbackHandler
interfacetoretrieveatoken.TheimplementationmustbeabletohandleaninstanceofanOAuthBearerTokenCallback,
feedingitawell-formedOAuthBearerToken.Theimplementationclassisspecifiedviathesasl.login.callback.handler.class
clientproperty.
Onthebroker,implementtheorg.apache.kafka.common.security.auth.AuthenticateCallbackHandler
interfacecapableofhandlinganOAuthBearerValidatorCallback.Itsroleistovalidatetheattributes
ofthetokenandfeedavalidatedtokentothecallback.Theimplementationclassisconfiguredviathe
listener.name.sasl_ssl.oauthbearer.sasl.server.callback.handler.class property. The class
definition, along with all dependencies must be on the classpath of the broker’s JVM instance. This
can be accomplished by packaging the callback handler and its dependencies into a Jar file, and
placingthelatterinto$KAFKA_HOME/libs.
RatherthanimplementingOAuthhandlingfromscratch,youcanusetheopen-sourceKafkaOAuth
project,locatedatgithub.com/jairsjunior/kafka-oauth³⁸.
A secondary objective of supporting OAuth bearer tokens was the internal testing of
SASL. Kafka’s SASL implementation can now be considered mature, but nonetheless, the
OAUTHBEARERcanstillbeusefulfortestingwithdifferentusers.
Delegation tokens
Concludingthediscussiononauthenticationmethods,wehavedelegationtokens.Delegationtokens
are used as a lightweight authentication mechanism that is complementary to SASL. It was intro-
ducedinKIP-48³⁹aspartofrelease1.1.0.Theinitialmotivationfordelegationtokenswastosimplify
³⁸https://github.com/jairsjunior/kafka-oauth
³⁹https://cwiki.apache.org/confluence/x/tfmnAw

Chapter16: Security 309
thelogisticsofkeydistributionforKerberosclients—specifically,theneedtodeployTGTsorkeytab
files to each client. Delegation tokens also reduce the overhead of the authentication process —
ratherthanengagingtheKDCtogetaticketandperiodicallyrenewingtheTGT,theauthentication
protocolisreducedtoverifyingthedelegationtoken,whichispersistedinZooKeeper.Thisapproach
is also more secure, reducing the blast radius of compromised credentials — if a delegation token
is covertly obtained, the attacker is limited to the permissions attached to the delegation token. By
comparison,thecompromiseofaKerberosTGTorkeytabcarriesmoredireconsequences.
The most common use case for delegation tokens is event stream processing where a swarm of
worker nodes is orchestrated by a centralised coordinator. Worker nodes tend to be ephemeral.
Rather than provisioning access independently for each worker node, the coordinator creates a
time-boundeddelegationtoken,thenspinsupaworkernode,handingitthenewly-generatedtoken.
Under this scheme, only the coordinator node requires access to long-lived credentials (a TGT or
keytab for Kerberos, or a username/password pair for SCRAM or PLAIN); the highly sensitive
materialneverleavesthecoordinator.
Delegationtokensareissuedforafinitelifespan—anupperboundonthemaximumageofatoken.
Furthermore,atokenhasanexpirationtime,whichsetsasoftlimitonthetimethatthetokenmay
be used to authenticate a client. The expiry time may be extended by renewing the token, subject
totheupperboundenforcedbyitslifespan.AnexpiredtokeniseventuallypurgedfromZooKeeper
using a background housekeeping thread. Because purging happens asynchronously, it is possible
foratokentobeusedforsometimeafteritsofficialexpiry.
Enabledelegationtokensonthebroker
Toenabledelegationtokens,editserver.properties,addingthefollowinglines:
delegation.token.master.key=secret-master-key
delegation.token.expiry.time.ms=3600000
delegation.token.max.lifetime.ms=7200000
The delegation.token.master.key property specifies a key for signing tokens that must be shared
by all brokers in a cluster. This property is required — delegation tokens are disabled without it.
Replacethestringsecret-master-keyasappropriate.
Thedelegation.token.expiry.time.msspecifiesthetokenexpirytimeinmilliseconds.Thedefault
valueis86400000(24hours).
Thedelegation.token.max.lifetime.msspecifiesthehardupperboundonthelifespanofthetoken
inmilliseconds.Whenissuingatoken,theusercanspecifyanyvalueforthe--max-life-time-period
thatisbelowthissetting.Thedefaultvalueof delegation.token.max.lifetime.msis604800000(7
days).
Restartthebrokerforthechangestotakeeffect.

310
| Chapter16: Security |     |     |     |     |     |     |
| ------------------- | --- | --- | --- | --- | --- | --- |
Creatingdelegationtokens
To create a delegation token, use the kafka-delegation-tokens.sh CLI, as shown in the example
below.
| $KAFKA_HOME/bin/kafka-delegation-tokens.sh |                        |                                      |     |      | \   |     |
| ------------------------------------------ | ---------------------- | ------------------------------------ | --- | ---- | --- | --- |
| --bootstrap-server                         |                        | localhost:9094                       |     | \    |     |     |
| --command-config                           |                        | $KAFKA_HOME/config/client.properties |     |      |     | \   |
| --create                                   | --max-life-time-period |                                      |     | -1 \ |     |     |
| --renewer-principal                        |                        | User:admin                           |     |      |     |     |
The owner of the token is the principal specified in the supplied properties file. In our example,
client.properties has been configured with the credentials of the admin user. This is not ideal in
production,asitgivesthetokenbearersignificantlymoreprivilegesthanitlikelyrequires.
Runningtheabovecommandproducesthefollowingoutput:
| Calling create     | token                  | operation | with    | renewers | : ￿ |     |
| ------------------ | ---------------------- | --------- | ------- | -------- | --- | --- |
| [User:admin]       | , max-life-time-period |           |         | :-1      |     |     |
| Created delegation | token                  | with      | tokenId | : ￿      |     |     |
doFHaIYjQwWhyeBZrBW54w
TOKENID
doFHaIYjQwWhyeBZrBW54w
￿
HMAC
| AFtqnd/cV/fFCawRYhPIHqe9sLZGegYscYu1o8BwSDn11 |     |     |     |     | ￿   |     |
| --------------------------------------------- | --- | --- | --- | --- | --- | --- |
rIqjKWRsKyLS9+CJ5Jor4RsAckTcS0IAWvdAlPqjQ==
￿
| OWNER      |     | RENEWERS     |     |     |     |     |
| ---------- | --- | ------------ | --- | --- | --- | --- |
| User:admin |     | [User:admin] |     |     |     |     |
￿
| ISSUEDATE        |     | EXPIRYDATE       |     | MAXDATE          |     |     |
| ---------------- | --- | ---------------- | --- | ---------------- | --- | --- |
| 2020-01-02T11:43 |     | 2020-01-01T12:43 |     | 2020-01-03T11:43 |     |     |
The--max-life-time-periodflagspecifiedthemaximumlifespanofthetokeninmilliseconds.Ifset
to -1, the maximum admissible value specified by the delegation.token.max.lifetime.ms broker
propertyisassumed.
The--renewer-principalflagspecifiestheuserwhoisallowedtorenewthetoken.Thisusermay
be different from the owner. When renewing the token, the user must present their credentials in
additiontothetoken’sHMACvalue.
Tolistdelegationtokens,invokekafka-delegation-tokens.shwiththe--describeswitch:

311
| Chapter16:                                 | Security |                                      |     |     |
| ------------------------------------------ | -------- | ------------------------------------ | --- | --- |
| $KAFKA_HOME/bin/kafka-delegation-tokens.sh |          |                                      | \   |     |
| --bootstrap-server                         |          | localhost:9094                       | \   |     |
| --command-config                           |          | $KAFKA_HOME/config/client.properties |     | \   |
--describe
Kafka presently does not allow one user to create a delegation token on behalf of another
(where the owner principal is different to the maker of the token). KIP-373⁴⁰ is slated to
addressthisshortfall.
Configuringclients
Delegation tokens are passed in similarly to credentials, using the SASL/SCRAM method. The
username attribute is set to the token ID, while the password attribute is assigned the HMAC value
fromthetableabove.Inadditiontotheseattributes,athirdattributetokenauthmustbepresent,set
to true. This attribute distinguishes token authentication from conventional username/password
authentication.
| final var | saslJaasConfig | = loginModuleClass |     |     |
| --------- | -------------- | ------------------ | --- | --- |
| + "       | required\n"    |                    |     |     |
+ "username=\"doFHaIYjQwWhyeBZrBW54w\"\n"
+ "password=\"AFtqnd/cV/fFCawR...PqjQ==\"\n"
+ "tokenauth=\"true\";";
Renewingtokens
Therenewalofatokencanonlybeattemptedbytheuserlistedin--renewer-principal,andmust
happen before the token expires. The kafka-delegation-tokens.sh command requires both the
user’scredentialsandthetokenbeingrenewed—specifically,theHMACvalue:
| $KAFKA_HOME/bin/kafka-delegation-tokens.sh |                           |                                      | \   |     |
| ------------------------------------------ | ------------------------- | ------------------------------------ | --- | --- |
| --bootstrap-server                         |                           | localhost:9094                       | \   |     |
| --command-config                           |                           | $KAFKA_HOME/config/client.properties |     | \   |
| --renew                                    | --renew-time-period       | -1                                   | \   |     |
| --hmac                                     | AFtqnd/cV/fFCawR...PqjQ== |                                      |     |     |
The--renew-time-periodflagspecifiesthedurationoftime(inmilliseconds)thatthetokenshould
beextendedby.Ifsetto-1,thetokenwillberenewedforthedurationspecifiedbythedelegation.token.expiry.time.ms
brokerproperty.
Expiringtokens
The expiration time of a token can be adjusted following its creation. The most common case is to
explicitlyinvalidateatoken,accomplishedusingthecommandbelow.
⁴⁰https://cwiki.apache.org/confluence/x/cwOQBQ

Chapter16: Security 312
$KAFKA_HOME/bin/kafka-delegation-tokens.sh \
--bootstrap-server localhost:9094 \
--command-config $KAFKA_HOME/config/client.properties \
--expire --expiry-time-period -1 \
--hmac AFtqnd/cV/fFCawR...PqjQ==
The --expiry-time-period switch specifies an extension on the expiration time in milliseconds. If
setto-1,thetokenwillbeexpiredimmediately.
Rotatingsecrets
Kafka does not yet have an elegant mechanism for rotating the shared secret. This is currently a
three-stepprocess:
1. Expireallexistingtokens.
2. Performarollingbounceoftheclusterwiththenewsecret.
3. Generateanddistributenewtokens.
Astokensareverifiedonlyduringconnectionestablishment,anyclientsthatarealreadyconnected
will continue to function normally. New connections using old tokens will be rejected, as will any
attempttoreneworexpiretokens.
ZooKeeper authentication
The final stretch in our authentication journey is hardening the link between the brokers and
the ZooKeeper ensemble. ZooKeeper supports SASL client authentication using the DIGEST-MD5
method.(Inourcontext,theclientistheKafkabrokeroraCLItool.)
ZooKeeper authentication powers its authorization model, which is implemented by way of an
AccessControlList(ACL).AnACLappliesindividuallytoeachznode,inamannerthatissimilar
toUNIXfileanddirectorypermissions.
ZooKeepersupportsthefollowingpermissiontypesonznodes:
• CREATE:createachildnode.
• READ:getdatafromanodeandlistitschildren.
• WRITE:setdataforanode.
• DELETE:deleteachildnode.
• ADMIN:setpermissions.
Permissions are recorded against a znode in a list. Each element is a triplet, comprising the au-
thentication scheme, the allowed principal, and the set of permissions. There are several built-in
schemes. The two notable ones that apply in our context are world — meaning anyone (including
unauthenticatedusers),andsasl—referringtoauserthathasbeenauthenticatedviaSASL.

Chapter16: Security 313
TheversionofZooKeeper(3.5.6)bundledwiththelatestKafkaversion(2.4.0atthetimeofwriting)
does not enforce authentication — only authorization. A client can connect without presenting
any credentials — its session will be associated with the world scheme. Although we can’t block
unauthenticatedconnections,wecanlimittheirlevelofaccessusingznodeACLs.Ineffect,thisis
howZooKeeperrestrictsunauthenticatedconnections—itacceptstheconnectionbutrestrictstheir
furtheractions.
Enabling authentication on a ZooKeeper ensemble that has already been confined to a segregated
network seems excessive. And in some ways, it is. The security of information assets can be
compounded almost indefinitely until the usability of the system is crippled for legitimate users.
At which point one asks: Have we just made the system less secure by blurring the line between
legitimateandillegitimateusers?
The answer to this is subjective, and it really depends on several factors, such as existing security
controls,thenatureoftheindustryandlevelofregulation,existingorganisationalsecuritypolicies
and guidelines, the likelihood of a breach and the cost of mitigation. The following subsections
include worked examples for enabling ZooKeeper authentication for new and existing clusters;
however,therestofthechapterandthebookwillassumethatZooKeeperauthenticationisdisabled.
Enable authentication on ZooKeeper
ZooKeeper supports a different set of SASL methods compared to what Kafka offers its clients;
nonetheless,thebasicprinciplesarethesame.LikeKafka,ZooKeeperusesJAASforconfiguration.
Create a new file named zookeeper_jaas.conf in the $KAFKA_HOME/config directory, with the
contents below. The admin username will be zkadmin. Replace the value zkadmin-secret with a
securepasswordforauthenticatingtoZooKeeper.
Server {
org.apache.zookeeper.server.auth.DigestLoginModule required
user_zkadmin="zkadmin-secret";
};
Makesurethefileisnotworld-readable:
chmod 600 $KAFKA_HOME/config/zookeeper_jaas.conf
Editzookeeper.properties,addingthefollowingconfiguration:

Chapter16: Security 314
authProvider.1=\
org.apache.zookeeper.server.auth.SASLAuthenticationProvider
jaasLoginRenew=3600000
ThelocationoftheJAASfilecanbespecifiedusingthejava.security.auth.login.configsystem
property, passed using the KAFKA_OPTS environment variable. (Both Kafka and ZooKeeper use the
samewrapperscripts,whichworkoffthesameenvironmentvariables.)RestartZooKeeper,butdon’t
connecttoitjustyet.
JAAS_CONFIG=$KAFKA_HOME/config/zookeeper_jaas.conf \
KAFKA_OPTS=-Djava.security.auth.login.config=$JAAS_CONFIG \
$KAFKA_HOME/bin/zookeeper-server-start.sh \
$KAFKA_HOME/config/zookeeper.properties
ThenextstepistoauthoraclientJAASfilethatcanbeusedbyKafkaaswellasCLItoolstoconnect
to ZooKeeper. Create a file named kafka_server_jaas.conf in the $KAFKA_HOME/config directory,
containingthefollowing:
Client {
org.apache.zookeeper.server.auth.DigestLoginModule required
username="zkadmin"
password="zkadmin-secret";
};
Makesurethefileisnotgroup-readable:
chmod 600 $KAFKA_HOME/config/kafka_server_jaas.conf
After ZooKeeper starts with the server-side JAAS configuration, connections will be authenticated
usingSASLandclientswillbeabletosetACLsonznodesasneeded.However,anyexistingznodes
that were created prior to enabling SASL will remain fully-accessible by the world scheme. Recall,
ZooKeeper’sauthenticationexiststosupportitsauthorizationcontrols.Enablingauthenticationon
itsownhasnoeffectonexistingznodes.Fortunately,Kafkaprovidesaconvenientmigrationscript
that edits ACLs on existing znodes, transferring permissions from world to an admin user of our
choice.

315
| Chapter16: | Security |     |     |
| ---------- | -------- | --- | --- |
JAAS_CONFIG=$KAFKA_HOME/config/kafka_server_jaas.conf \
KAFKA_OPTS=-Djava.security.auth.login.config=$JAAS_CONFIG \
$KAFKA_HOME/bin/zookeeper-security-migration.sh \
| --zookeeper.connect |     | localhost:2181 | \   |
| ------------------- | --- | -------------- | --- |
| --zookeeper.acl     |     | secure         |     |
Thisoperationwilltakeafewsecondsandshouldexitwithoutoutputtinganythingtotheconsole.
ThemigrationstepisonlynecessaryforexistingZooKeeperensemblesthathavepreviously
been initialised by Kafka. Fresh ZooKeeper installations (with no prior data) will function
securelywithoutmigration,providedthatSASLhasbeenenabledpriortoconnectingKafka
brokers.
| Configure | the broker | to authenticate | to ZooKeeper |
| --------- | ---------- | --------------- | ------------ |
ThenextstepistoenableZooKeeperauthenticationonthebroker.Editserver.properties,adding
thefollowingline:
zookeeper.set.acl=true
Then,startthebrokerwiththeclient-sideJAASconfigurationcreatedinthepreviousstep:
JAAS_CONFIG=$KAFKA_HOME/config/kafka_server_jaas.conf \
KAFKA_OPTS=-Djava.security.auth.login.config=$JAAS_CONFIG \
| $KAFKA_HOME/bin/kafka-server-start.sh |     |     | \   |
| ------------------------------------- | --- | --- | --- |
$KAFKA_HOME/config/server.properties
| Enable | ZooKeeper | authentication | on CLI tools |
| ------ | --------- | -------------- | ------------ |
The reader may have noticed a pattern among the applications that connect to a SASL-enabled
ZooKeeper:theyallneedtosetjava.security.auth.login.configtothelocationofaJAASfile.
CLI tools are no exception. Once ZooKeeper authentication has been enabled, you must supply a
validJAASfileeverytimeyouneedtoqueryormodifyZooKeeper’sstate.Theexamplebelowshows
how the kafka-config.sh tool can be parametrised with the location of the JAAS file. Most scripts
in support the use of the environment variable for passing arbitrary
| $KAFKA_HOME/bin |     |     | KAFKA_OPTS |
| --------------- | --- | --- | ---------- |
systempropertiestotheprocessinquestion.

316
| Chapter16:                                                | Security      |             |                |     |     |
| --------------------------------------------------------- | ------------- | ----------- | -------------- | --- | --- |
| JAAS_CONFIG=$KAFKA_HOME/config/kafka_server_jaas.conf     |               |             |                | \   |     |
| KAFKA_OPTS=-Djava.security.auth.login.config=$JAAS_CONFIG |               |             |                | \   |     |
| $KAFKA_HOME/bin/kafka-configs.sh                          |               | --zookeeper | localhost:2181 | \   |     |
| --describe                                                | --entity-type | users       |                |     |     |
TheJAASfilemaybeomittedinthescenariowhereyoumightbebrowsingtop-levelznodes
thatareworld-readable;forexample,usingthezookeeper-shell.shtool.
| Reverting | the configuration |     |     |     |     |
| --------- | ----------------- | --- | --- | --- | --- |
IfZooKeeperauthenticationceasestobearequirement,itcanberevertedtoitsoriginal(unauthen-
ticated) state by running zookeeper-security-migration.sh with the --zookeeper.acl unsecure
flag.Afterwards,youmayeditzookeeper.propertiestoremovetheauthProvider.1andjaasLoginRenew
| properties. | Likewise, the     |     | configuration | will also need | its               |
| ----------- | ----------------- | --- | ------------- | -------------- | ----------------- |
|             | server.properties |     |               |                | zookeeper.set.acl |
settingremovedorsettofalse.
Note,therestofthechapterandthebookassumesthatZooKeeperauthenticationisdisabled.Ifyou
haveenableditoutofcuriosity,nowisagoodtimetoreverttheconfiguration.
| Configuring | CLI tools |     |     |     |     |
| ----------- | --------- | --- | --- | --- | --- |
OnceKafkahasbeenconfiguredwithSSLandauthentication,itisn’tjusttheproducerandconsumer
clientsthatmustbeconfigured;thebuilt-inCLItoolsandotheradministrativeapplicationsmustalso
beconfiguredwiththelocationoftheclienttruststorefileandtheparametersoftheauthentication
flow.
Startbycopyingtheclient.truststore.jksfile(thatwasgeneratedaspartofsettingupSSL)toa
locationwhereaclientcaneasilyaccessit:
| cp /tmp/kafka-ssl/client.truststore.jks |     |     | $KAFKA_HOME/config |     |     |
| --------------------------------------- | --- | --- | ------------------ | --- | --- |
Next,createaclient.propertiesfile$KAFKA_HOME/config.Thecontentsofthefilearelistedbelow.
Ontheauthor’smachine,thetruststoreislocatedin/Users/me/opt/kafka_2.13-2.4.0/config.You
willneedtoreplacethiswiththeabsolutepathtothetruststorefile,asmostapplicationsdon’twork
well with relative paths. We are going to use the admin user for authentication, which is fitting for
administrativetools.

317
Chapter16: Security
security.protocol=SASL_SSL
ssl.endpoint.identification.algorithm=https
| ssl.truststore.location= | \   |     |
| ------------------------ | --- | --- |
/Users/me/opt/kafka_2.13-2.4.0/config/client.truststore.jks
ssl.truststore.password=secret
sasl.mechanism=SCRAM-SHA-512
| sasl.jaas.config=                                       | \   |     |
| ------------------------------------------------------- | --- | --- |
| org.apache.kafka.common.security.scram.ScramLoginModule |     | \   |
| required \                                              |     |     |
| username="admin"                                        | \   |     |
password="admin-secret";
In production deployments you are more likely to have multiple admin-like users with
varyingpermissions.Adiscussiononuserpermissionsandthebroadertopicofauthorization
isdeferreduntilthenextsection.
Next,runoneoftheexistingCLItoolstoverifytheconfiguration.Intheexamplebelow,weareusing
kafka-topics.sh to list the current topics. The location of the client.properties file is supplied
viathe--command-configflag.Note:weareconnectingtoport9094inthisexample,not9092.
$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server localhost:9094 \
| --command-config | $KAFKA_HOME/config/client.properties | --list |
| ---------------- | ------------------------------------ | ------ |
Theresultingoutputliststhetopics.
__consumer_offsets
getting-started
| Configuring Kafdrop |     |     |
| ------------------- | --- | --- |
HavingconfiguredtheCLItools,thenextstepistoensurethatwecanconnecttoasecuredcluster
usingKafdrop.LiketheCLIexample,Kafdroprequiresatruststorefile,aswellasasetofproperties
for configuring SSL and SASL. Unlike the CLI, Kafdrop does not require an absolute path to the
truststorefile;instead,youcansimplyleavethetruststorefileinKafdrop’srootdirectory.
Startbycopyingclient.truststore.jkstotheKafdropdirectory,renamingthefiletokafka.truststore.jks
intheprocess.ThisisthestandardtruststorefilenamethatKafdropexpectsbydefault.
cp /tmp/kafka-ssl/client.truststore.jks \
~/code/kafdrop/kafka.truststore.jks
Inaddition,createakafka.propertiesfileinKafdrop’srootdirectory,containingthefollowing:

Chapter16: Security 318
security.protocol=SASL_SSL
ssl.endpoint.identification.algorithm=https
ssl.truststore.password=secret
sasl.mechanism=SCRAM-SHA-512
sasl.jaas.config= \
org.apache.kafka.common.security.scram.ScramLoginModule \
required \
username="admin" \
password="admin-secret";
The contents of kafka.properties are strikingly similar to the client.properties file used in the
previous example. There is only one difference: the location of the truststore file is left unspecified.
Kafdropwilldefaulttokafka.truststore.jksbyconvention.
Next,startKafdrop,thistimeconnectingtolocalhost:9094:
java -jar target/kafdrop-3.22.0-SNAPSHOT.jar \
--kafka.brokerConnect=localhost:9094
Once connected, Kafdrop will behave identically to previous examples. The only difference now is
that it will connect over SSL and will authenticate itself using theadmin user. This is important for
our upcoming examples, which use Kafka’s authorization capabilities. Unless Kafdrop is set up to
useSASLandSSL,itwillbeofnouseoncetheKafkaclusterhasbeenfullyhardened.Thesamecan
besaidaboutbuilt-inCLItoolsandanythird-partytoolsonemightbeusing.
Authorization
With SSL and authentication ticked off, we are two-thirds of the way there. Authentication is
necessarytoidentifytheuserprincipal,andthatisimmenselyusefulforallowingorblockingaccess
toindividualclients.Quiteoften,especiallywhentheclusterissharedacrossmultipleapplications,
thebinaryallow-or-denyoptionisinsufficient;weneedmorefine-grainedcontrolovertheactions
that an authenticated client should be allowed to perform. This is where authorization comes into
thepicture.
Kafkaimplementsauthorizationbywayofresource-centricACLs.AnACLspecifiesthefollowing:
• Principal—theuserperformingtheoperation.
• Operation—theactionbeingperformed.Forexample,ReadorWrite.
• Host—anoptionalrestrictionontheaddressesthattheruleappliesto.
• Resource type — the type of subject on which the action is to be performed. For example,
TopicorGroup.
• Resourcepattern—asimpleruleformatchingthesubjectbyitsname.Rulesspecifyasingle
resourcebyitsliteralname,orcangroupmultipleresourcesusingprefixesorwildcards.
• Outcome—whethertheactionshouldbeallowedordenied.

Chapter16: Security 319
Both Kafka and ZooKeeper utilise ACLs for authorization; however, their implementations are
distinctandshouldnotbeconfused.Thetablebelowoutlinesthedifferences.
ZooKeeperACLs KafkaACLs
Applytodiscreteresources. Canspecifymultipleresourcesusing
prefixesandwildcards.
Restrictoperationstousers. Restrictoperationstousersornetwork
addresses.
Canonlyallowanoperation. Canallowordenyoperations.
Thereareseveralsupportedoperations:
• Read—viewadata-centricresource(forexample,atopic),butnotwritetoitscontents.
• Write—modifythecontentsofadata-centricresource.
• Create—create anewresource.
• Delete—deletearesource.
• Alter—changethecontentsofanon-dataresource(forexample,ACLs).
• Describe—getinformationaboutaresource.
• ClusterAction — operate on the cluster. For example, fetch metadata or initiate a controlled
shutdownofabroker.
• DescribeConfigs—retrievetheconfigurationofaresource.
• AlterConfigs—altertheconfigurationofaresource.
• IdempotentWrite—performanidempotentwrite.IdempotentwritesaredescribedinChapter
10:ClientConfiguration.
• All—alloftheabove.
Theoperationsactonresourcetypes.Thefollowingisalistofsupportedresourcetypesandrelated
operations:
• Cluster — the entire Kafka cluster. Supports: Alter, AlterConfigs, ClusterAction, Create,
DescribeandDescribeConfig.
• DelegationToken — a delegation token. This resource type is different from the others in that
it is bound by special rules, where permissions apply to the owner and renewer. Supports:
Describe.
• Group—aconsumergroup.Supports:Delete,DescribeandRead.
• Topic — a topic. Supports: Alter, AlterConfigs, Create, Delete, Describe, DescribeConfigs,
ReadandWrite.
• TransactionalId — transactional sessions of the same logical producer. Supports: Describe
andWrite.

Chapter16: Security 320
Thelistsabovemayappearconfusing,especiallythecombinationofresourcetypesandoperations.
Kafka’s official documentation provides a more detailed matrix of the relationships between low-
level protocol messages (or API calls), operations and resource types — not that it makes things a
whole lot clearer. (Not unless you are intimately familiar with the underlying protocol messages.)
This might not sound very encouraging, but most ACL-related issues are diagnosed and resolved
throughexperimentationandconsultingpeoplewhohaveseenasimilarproblembefore.Admittedly,
it’snotgreat,butit’sthebestanswerthereis.
Tomakethisprocesslessambiguous,KafkaclientswillthrowspecificsubclassesofanAuthorizationException
iftheevaluationofrulesresultsinadeny outcomeforoneofthefiveresourcestypes.Forexample,
a deny against a topic will result in a TopicAuthorizationException, a deny against a group will
leadtoaGroupAuthorizationException,andsoforth.
There will be times where a developer will swear they have enabled an operation on a
resource, such as a topic, only to get some obscure ClusterAuthorizationException at
runtime. It might seem nonsensical at the time — one is trying to publish a record, but
is inexplicably being knocked back at the cluster level. Confusions such as these typically
occurwhenaclientactionentailsmultipleAPIcalls,andoneofthosecallsisbeingdenied.
Enable authorization on the broker
Toenableauthorization,editserver.properties,addingthefollowinglines:
authorizer.class.name=kafka.security.auth.SimpleAclAuthorizer
super.users=User:admin
Restartthebrokerforchangestotakeeffect.
The super.users property specifies a semicolon-delimited list of privileged users that are able
to perform any operation. In this example, we specified admin for convenience, given that we
already have this user configured, and we are also signing on with the same user for interbroker
authentication.Youmayspecifyotherusers,butmakesuretheinterbrokeruserremainsinthelist.
Kafka delimits usernames with semicolons because a comma is a valid character in a
usernamethathasbeenderivedfromtheattributesofanSSLcertificate.
As it is generally accepted, operating a secure cluster implies that both client and interbroker
operations are subject to authentication and authorization controls. The interbroker operations are
split into two broad classes: cluster and topic. Cluster operations refer to the actions necessary
for the management of the cluster, such as updating broker and partition metadata, changing the
leader and the in-sync replicas of a partition, and initiating a controlled shutdown. One of the
definingresponsibilitiesofabrokeristhereplicationofpartitiondata.Becauseofthewayreplication

Chapter16: Security 321
works internally, it is also necessary to grant topic access to brokers. Specifically, replicas must be
authorized for both Read and Describe operations on the topics being replicated. The Describe
permissionisgrantedimplicitly,aslongastheReadpermissionispresent.
Kafkatakesadefault-denystance,whichisotherwiseknownasapositiveoradditivesecuritymodel.
Unlessanattemptedactionisexplicitlyallowed,itisassumedtobedenied.Thisbehaviourcanbe
invertedbyaddingthefollowingvaluetoserver.properties:
allow.everyone.if.no.acl.found=true
Generally speaking, the default-deny model is recommended over the default-allow, as it takes a
more conservative approach to dispensing access. One should think of it as an additive approach:
startingfromzero—ablankslate,addingpermissionsonaneedsbasis.Atanypoint,itcanbeclearly
reasoned as to who has access to a resource — by simply looking over the permissions list. Unless
anallow ruleexplicitlystatingtheprincipal andtheresource inquestionispresent,thentherecan
benoaccess-grantingrelationshipbetweenthepairofentities.
Once the broker restarts, we can use the kafka-acls.sh tool to view and manipulate ACLs. The
commandbelowwilllistallconfiguredACLs,internallyutilisingtheKafkaAdminAPI.
$KAFKA_HOME/bin/kafka-acls.sh \
--command-config $KAFKA_HOME/config/client.properties \
--bootstrap-server localhost:9094 \
--list
It should come up with an empty list of rules. This is to be expected as we haven’t yet configured
any.
When specifying a bootstrap list via the --bootstrap-server flag, it is also necessary to state the
locationoftheJAASfileusedforauthenticatingtoKafka.WhenKafkaisrunningwithauthorization
enabled,userscannotjustviewandalterACLs—notunlesstheyhavebeenexplicitlyauthorizedto
doso.Byconnectingwiththeadminuser,weareeffectivelyoperatingin‘godmode’.
AnalternatewayofviewingandsettingACLsistoconnecttoZooKeeperdirectly.Thiswillwork
providedZooKeeperisreachablefromthenetworkwherethecommandisrun,andZooKeeperisnot
runningwithauthenticationandACLsofitsown.Thecommandbelowshowshowkafka-acls.sh
canbeuseddirectlywithanunauthenticatedZooKeepernode.
$KAFKA_HOME/bin/kafka-acls.sh \
--authorizer-properties zookeeper.connect=localhost:2181 \

322
| Chapter16: Security |     |     |     |     |     |     |     |
| ------------------- | --- | --- | --- | --- | --- | --- | --- |
--list
Atthispoint,wewillhaveafunctioningbrokerwithnoACLs.Thismeansthat,withtheexceptionof
theadminuser,nootherclientwillbeabletointeractwithKafka.Ofcourse,thepointofACLsisnot
to lock out all users, but restrict access to those users who legitimately need to view or manipulate
specific resources in the cluster. Previously, in the SASL examples, the user published to the
alice
getting-startedtopic.Runningthisexamplenowresultsinaseriesofwarningsanderrors:
21:55:56/1854 WARN [kafka-producer-network-thread | producer-1]: ￿
[Producer clientId=producer-1] Error while fetching metadata ￿
| with correlation |     | id  | 4 : ￿ |     |     |     |     |
| ---------------- | --- | --- | ----- | --- | --- | --- | --- |
{getting-started=TOPIC_AUTHORIZATION_FAILED}
21:55:56/1854 ERROR [kafka-producer-network-thread | producer-1]: ￿
[Producer clientId=producer-1] Topic authorization failed for ￿
| topics | [getting-started] |     |     |     |     |     |     |
| ------ | ----------------- | --- | --- | --- | --- | --- | --- |
Published with metadata: null, error: org.apache.kafka.common. ￿
errors.TopicAuthorizationException: Not authorized to access ￿
| topics: | [getting-started] |     |     |     |     |     |     |
| ------- | ----------------- | --- | --- | --- | --- | --- | --- |
Thisparticularproblemcanbefixedbyaddingreadandwritepermissionstouseralice:
| $KAFKA_HOME/bin/kafka-acls.sh |                   |                                      |                | \     |         |                 |     |
| ----------------------------- | ----------------- | ------------------------------------ | -------------- | ----- | ------- | --------------- | --- |
| --command-config              |                   | $KAFKA_HOME/config/client.properties |                |       |         |                 | \   |
| --bootstrap-server            |                   |                                      | localhost:9094 |       | \       |                 |     |
| --add                         | --allow-principal |                                      | User:alice     |       | \       |                 |     |
| --operation                   | Read              | --operation                          |                | Write | --topic | getting-started |     |
Whichresultsinthefollowingoutput:
Adding ACLs for resource `ResourcePattern(resourceType=TOPIC, ￿
| name=getting-started,  |     |     | patternType=LITERAL)`: |                  |     |     |     |
| ---------------------- | --- | --- | ---------------------- | ---------------- | --- | --- | --- |
| (principal=User:alice, |     |     | host=*,                | operation=WRITE, |     | ￿   |     |
permissionType=ALLOW)
| (principal=User:alice, |     |     | host=*, | operation=READ, |     | ￿   |     |
| ---------------------- | --- | --- | ------- | --------------- | --- | --- | --- |
permissionType=ALLOW)
| Current ACLs | for resource |     | `Topic:LITERAL:getting-started`: |     |             |     |     |
| ------------ | ------------ | --- | -------------------------------- | --- | ----------- | --- | --- |
| User:alice   | has Allow    |     | permission                       | for | operations: | ￿   |     |
| Read         | from hosts:  |     | *                                |     |             |     |     |
| User:alice   | has Allow    |     | permission                       | for | operations: | ￿   |     |
| Write        | from hosts:  |     | *                                |     |             |     |     |

323
| Chapter16: | Security |     |     |     |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- | --- | --- | --- |
However, this is not sufficient. As it was previously stated, some operations require several API
calls. In our producer example, we have set the property enable.idempotence to true Enabling
idempotence ensures that the producer maintains strict order and does not write duplicates in the
eventofanintermittentnetworkerror.However,thispropertyrequirescluster-levelpermissionfor
theIdempotentWriteoperation:
| $KAFKA_HOME/bin/kafka-acls.sh |                    |                   |                                      |                | \         |     |     |     |
| ----------------------------- | ------------------ | ----------------- | ------------------------------------ | -------------- | --------- | --- | --- | --- |
|                               | --command-config   |                   | $KAFKA_HOME/config/client.properties |                |           |     |     | \   |
|                               | --bootstrap-server |                   |                                      | localhost:9094 |           | \   |     |     |
|                               | --add              | --allow-principal |                                      | User:alice     |           | \   |     |     |
|                               | --operation        | IdempotentWrite   |                                      |                | --cluster |     |     |     |
Resultingin:
Adding ACLs for resource `ResourcePattern(resourceType=CLUSTER, ￿
|                        | name=kafka-cluster, |     |     | patternType=LITERAL)`: |                             |     |     |     |
| ---------------------- | ------------------- | --- | --- | ---------------------- | --------------------------- | --- | --- | --- |
| (principal=User:alice, |                     |     |     | host=*,                | operation=IDEMPOTENT_WRITE, |     |     | ￿   |
permissionType=ALLOW)
| Current    | ACLs            | for resource |      | `Cluster:LITERAL:kafka-cluster`: |     |             |     |     |
| ---------- | --------------- | ------------ | ---- | -------------------------------- | --- | ----------- | --- | --- |
| User:alice |                 | has Allow    |      | permission                       | for | operations: | ￿   |     |
|            | IdempotentWrite |              | from | hosts:                           | *   |             |     |     |
Runningthekafka-acls.shcommandwiththe--listswitchnowresultsinacompleteACLlisting,
showingaliceandherassociatedpermissions:
| Current    | ACLs            | for resource |      | `Cluster:LITERAL:kafka-cluster`: |     |             |     |     |
| ---------- | --------------- | ------------ | ---- | -------------------------------- | --- | ----------- | --- | --- |
| User:alice |                 | has Allow    |      | permission                       | for | operations: | ￿   |     |
|            | IdempotentWrite |              | from | hosts:                           | *   |             |     |     |
| Current    | ACLs            | for resource |      | `Topic:LITERAL:getting-started`: |     |             |     |     |
| User:alice |                 | has Allow    |      | permission                       | for | operations: | ￿   |     |
|            | Read            | from hosts:  |      | *                                |     |             |     |     |
| User:alice |                 | has Allow    |      | permission                       | for | operations: | ￿   |     |
|            | Write           | from hosts:  |      | *                                |     |             |     |     |
Trypublishingnow.Itshouldworklikeacharm.
Now,let’sswitchbacktotheconsumersamplethatweranaspartofsettingupSASL.Thatexample
authenticatedasaliceandreadfromthegetting-startedtopicusingthebasic-consumer-sample
consumergroup.Runningthisexamplenowresultsinthefollowingerror:

324
| Chapter16:                   | Security              |        |        |                                 |            |           |     |
| ---------------------------- | --------------------- | ------ | ------ | ------------------------------- | ---------- | --------- | --- |
| Exception                    | in                    | thread | "main" | org.apache.kafka.common.errors. |            |           | ￿   |
| GroupAuthorizationException: |                       |        |        | Not                             | authorized | to access | ￿   |
| group:                       | basic-consumer-sample |        |        |                                 |            |           |     |
The issue is that the ACLs for topics are distinct to the ACLs for consumer groups, as the two are
treatedasdistinctresources.Tofixtheissueabove,addtheReadpermissionontheconsumergroup:
| $KAFKA_HOME/bin/kafka-acls.sh |                   |      |                                      | \                     |     |     |     |
| ----------------------------- | ----------------- | ---- | ------------------------------------ | --------------------- | --- | --- | --- |
| --command-config              |                   |      | $KAFKA_HOME/config/client.properties |                       |     |     | \   |
| --bootstrap-server            |                   |      | localhost:9094                       |                       | \   |     |     |
| --add                         | --allow-principal |      |                                      | User:alice            | \   |     |     |
| --operation                   |                   | Read | --group                              | basic-consumer-sample |     |     |     |
The consumer should now be able to function normally. And there we have it: the world-readable
| getting-startedhasjustbeenconvertedintoahigh-assurance |     |     |     |     |     |     | topic. |
| ------------------------------------------------------ | --- | --- | --- | --- | --- | --- | ------ |
ACLs can be viewed broadly for all users and resources using the Kafdrop tool. It also provides
a convenient filter text box, letting you search for specific principals or resource patterns. The
screenshotbelowshowshowtheACLswehavedefinedsofarappearinKafdrop.
Kafdrop—AClsfortheuser‘alice’
In our examples, we have used alice to demonstrate both read (consume) and write (publish)
privileges. As a convenience, this was sufficient. However, in practice, it is rare to see both the
producerandtheconsumeractingasoneentityforthesametopic.Moreoften,theyaredistinct.It
is considered best-practice to assign individual usernames to application entities. Furthermore, an
entity should only be granted the minimal set of privilegesthat it legitimately requiresto fulfil its
responsibilities.
In the same vein, the correct approach with respect to admin users is to avoid them. It is best to
create a dedicated user for interbroker communications, having the necessary level of cluster and

325
| Chapter16: | Security |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- |
topicaccess,butnoabilitytocreatenewusersormodifyACLs.
| Removing | permissions |     |     |     |     |
| -------- | ----------- | --- | --- | --- | --- |
Toremoveapermission,simplyrunkafka-acls.shwiththe--removeswitchinplaceof --add,with
therestoftheflagsusedforaddingapermission,asshownintheexamplebelow.
| $KAFKA_HOME/bin/kafka-acls.sh |                   |             | \                                |                         |     |
| ----------------------------- | ----------------- | ----------- | -------------------------------- | ----------------------- | --- |
| --authorizer-properties       |                   |             | zookeeper.connect=localhost:2181 |                         | \   |
| --remove                      | --allow-principal |             | User:alice                       | \                       |     |
| --operation                   | Read              | --operation | Write                            | --topic getting-started | \   |
--force
The use of the --force switch prevents the operator from having to respond to a yes/no
safetyprompt.
Laterinthechapter,wewilllookatbulk-removingpermissions.
| Mixing | allow and | deny | permissions |     |     |
| ------ | --------- | ---- | ----------- | --- | --- |
One of the known complications of Kafka’s rules is that they support both allow and deny actions.
Thismakesthemmoreflexible,butalsoeasiertomisconfigure.Forexample,youcanallowaccessto
allusersonaspecifictopic,butthendisallowaccesstoasmallergroupofusers.Anaturalresponse
might be: Doesn’t this contradict the default-deny model, and if not, where would this capability be
useful?
One way of looking at it, is that it allows us to interleave default-allow and default-deny tactics
in the same overall security model, creating a hierarchy of sorts. To understand where this might
be beneficial, consider the following challenge, involving a hypothetical Kafka cluster that permits
guestlevelaccessforasmallsubsetofresources.Guestcredentialscanbeprocuredrelativelyeasily
— there might even be a pre-canned set of shared credentials that are freely available. Assuming
strongnetwork-levelcontrolsareinplace,guestaccessisonlypermittedfromatrustednetwork.
We might be publishing something trivial on a topic which we would like to make available to
guests for reading, as well as to trusted users. Let’s call this low-assurance topic guest-readable.
Now,assumethereisanothertopicwhichcontainsinformationforauthenticatedusersthatahave
higher level of clearance than — a medium assurance topic. We will call it trusted-only.
guest
Andfinally,theclustermaycontainnumeroushigh-assurance topicsthatareadmissibletospecific

Chapter16: Security 326
users.Frankly,thehighassurancetopicsareleastinteresting,astheycanbetriviallyfortifiedusing
standardACLs.Theywereonlyaddedtothechallengeforcompleteness,tomakeitmorelife-like.
To make this challenge more interesting, we do not know in advance who all the users are. All
that can be stated with certainty is the username of the guest user — guest. Any other user that is
authenticated,butnotguest,isatrusteduserandshouldhavereadaccesstothetrusted-onlytopic.
Thediagrambelowillustratestheintendedtargetstateforthischallenge,depictedtherelationships
betweenusersandtopics.
TheACLchallenge
Normally, we would add allow rules to the topics, until all access requirements are satisfied. The
complication here is that we don’t have a definitive list of usernames to work with; furthermore,
evenifwedid, theadditionofnewuserswouldrequireupdatestoavastnumberof ACLs,making
their maintenance unsustainable. Ideally, this problem is solved with Role-Based Access Control
(RBAC);however,theout-of-the-boxKafkasetupdoesnotsupportthis.

327
| Chapter16: Security |     |     |     |     |     |     |     |     |
| ------------------- | --- | --- | --- | --- | --- | --- | --- | --- |
There are third-party extensions to Kafka that support RBAC. This book is predominantly
focusedonthefoundationalinsightsandadvancedskills—featuringavanillaKafkasetup,
as well as the minimally essential set of third-party tools. RBAC is on the far-right of the
notional ‘extensibility’ spectrum, and has not been considered for inclusion into the base
Kafkaoffering.Assuch,wewillnotexploreitfurther.
Beforesolvingthischallenge,weneedtocreateourfixtures.Weneedapairoftopics—guest-readable
andtrusted-only,andweneedtheguestuser.Let’sgetstartedbycreatingtheseentities:
| # add a user                     | (requires      |                                         | ZooKeeper                            |       | access)     |       |                |     |
| -------------------------------- | -------------- | --------------------------------------- | ------------------------------------ | ----- | ----------- | ----- | -------------- | --- |
| $KAFKA_HOME/bin/kafka-configs.sh |                |                                         |                                      |       | --zookeeper |       | localhost:2181 | \   |
| --alter                          | \              |                                         |                                      |       |             |       |                |     |
| --add-config                     |                | 'SCRAM-SHA-512=[password=guest-secret]' |                                      |       |             |       |                | \   |
| --entity-type                    |                | users                                   | --entity-name                        |       |             | guest |                |     |
| # create the                     | guest-readable |                                         |                                      | topic |             |       |                |     |
| $KAFKA_HOME/bin/kafka-topics.sh  |                |                                         |                                      |       | \           |       |                |     |
| --command-config                 |                |                                         | $KAFKA_HOME/config/client.properties |       |             |       |                | \   |
| --bootstrap-server               |                |                                         | localhost:9094                       |       |             | \     |                |     |
| --create                         | --topic        |                                         | guest-readable                       |       |             | \     |                |     |
| --partitions                     |                | 1 --replication-factor                  |                                      |       |             | 1     |                |     |
| # create the                     | trusted-only   |                                         |                                      | topic |             |       |                |     |
| $KAFKA_HOME/bin/kafka-topics.sh  |                |                                         |                                      |       | \           |       |                |     |
| --command-config                 |                |                                         | $KAFKA_HOME/config/client.properties |       |             |       |                | \   |
| --bootstrap-server               |                |                                         | localhost:9094                       |       |             | \     |                |     |
| --create                         | --topic        |                                         | trusted-only                         |       | \           |       |                |     |
| --partitions                     |                | 1 --replication-factor                  |                                      |       |             | 1     |                |     |
Thekafka-topics.shCLIsupportsthecreationoftopicsviaabootstraplist,providedyou
giveittheappropriatecredentialsandtruststore.Ontheotherhand,thekafka-configs.sh
toolislimitedintheentitytypesthatcanbeconfiguredviaabroker.Entitiessuchasusers
mustbeconfigureddirectlyinZooKeeper.
The next step is to try reading from the guest-readable topic using the guest user. Rather than
using our existing SASL consumer example, we will change the code slightly to run it using a free
consumer — in other words, without an encompassing consumer group. This particular variation
replacestheConsumer.subscribe()call withConsumer.assign(), removingthe need to pass in the
group.idconsumerproperty.Thecompletelistingisshownbelow.

328
| Chapter16: Security |                     |     |     |     |
| ------------------- | ------------------- | --- | --- | --- |
| import static       | java.lang.System.*; |     |     |     |
import java.time.*;
import java.util.*;
import java.util.stream.*;
import org.apache.kafka.clients.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;
import org.apache.kafka.common.config.*;
import org.apache.kafka.common.security.scram.*;
import org.apache.kafka.common.serialization.*;
| public final  | class SaslSslFreeConsumerSample |     |         | {   |
| ------------- | ------------------------------- | --- | ------- | --- |
| public static | void main(String[]              |     | args) { |     |
| final         | var topic = "guest-readable";   |     |         |     |
final var loginModuleClass = ScramLoginModule.class.getName();
| final | var saslJaasConfig | = loginModuleClass |     |     |
| ----- | ------------------ | ------------------ | --- | --- |
+ " required\n"
+ "username=\"guest\"\n"
+ "password=\"guest-secret\";";
| final | var config = new | HashMap<String, |     | Object>(); |
| ----- | ---------------- | --------------- | --- | ---------- |
config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9094");
config.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
"SASL_SSL");
config
.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
"https");
config.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
"client.truststore.jks");
config.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "secret");
| config.put(SaslConfigs.SASL_MECHANISM,   |     |     |     | "SCRAM-SHA-512"); |
| ---------------------------------------- | --- | --- | --- | ----------------- |
| config.put(SaslConfigs.SASL_JAAS_CONFIG, |     |     |     | saslJaasConfig);  |
config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName());
config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName());
config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

329
| Chapter16: | Security |     |     |     |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- | --- | --- | --- |
try (var consumer = new KafkaConsumer<String, String>(config)) {
|     | final var     | partitionInfos  |     |        | = consumer.partitionsFor(topic); |     |     |     |
| --- | ------------- | --------------- | --- | ------ | -------------------------------- | --- | --- | --- |
|     | final var     | topicPartitions |     |        | = partitionInfos.stream()        |     |     |     |
|     | .map(partInfo |                 |     | -> new | TopicPartition(partInfo.topic(), |     |     |     |
partInfo.partition()))
.collect(Collectors.toSet());
consumer.assign(topicPartitions);
|     | while (true)    |        | {       |            |                                        |              |                  |     |
| --- | --------------- | ------ | ------- | ---------- | -------------------------------------- | ------------ | ---------------- | --- |
|     | final           | var    | records | =          | consumer.poll(Duration.ofMillis(100)); |              |                  |     |
|     | for (var        | record |         | : records) |                                        | {            |                  |     |
|     | out.format("Got |        |         | record     | with                                   | value %s%n", | record.value()); |     |
}
}
}
}
}
Runningthiscodewillproducethefollowingerror:
| Exception | in                           | thread | "main" | org.apache.kafka.common.errors. |     |            |           | ￿   |
| --------- | ---------------------------- | ------ | ------ | ------------------------------- | --- | ---------- | --------- | --- |
|           | TopicAuthorizationException: |        |        |                                 | Not | authorized | to access | ￿   |
|           | topics: [guest-readable]     |        |        |                                 |     |            |           |     |
Andindeed,thatistobeexpected—afterall,wehadjustcreatedtheguestuser.Weneedtograntthe
due permissions to the guest user. But here’s the snag: if we keep granting permissions additively,
then we have to specify all users. And as it was stated in the challenge, the usernames are now
knowninadvance.
Rather than allowing guest-readable for all known users individually, we simply make it world-
readable.Here‘world’isabitofamisnomer—wearen’tactuallyexposingthetopictotheworld—
onlytothoseusersthathavesuccessfullyauthenticatedviatheSASL_SSLlistener.Runthecommand
below.
| $KAFKA_HOME/bin/kafka-acls.sh |                         |      |                                      |                | \              |     |     |     |
| ----------------------------- | ----------------------- | ---- | ------------------------------------ | -------------- | -------------- | --- | --- | --- |
|                               | --command-config        |      | $KAFKA_HOME/config/client.properties |                |                |     |     | \   |
|                               | --bootstrap-server      |      |                                      | localhost:9094 |                | \   |     |     |
|                               | --add --allow-principal |      |                                      |                | User:"*"       | \   |     |     |
|                               | --operation             | Read | --topic                              |                | guest-readable |     |     |     |
Compared to the earlier examples, we are using a wildcard (quoted asterisk character) in place of
ausername.Thewildcardcanbespecifiedusingsingleordouble-quotes.Avoidusingthewildcard

330
Chapter16: Security
withoutquotes,asmostshellswillexpandthe*charactertoalistoffilesinthecurrentdirectoryof
thecaller.
Tomakethetestingeasier,weshouldalsopublishsomerecordstotheguest-readableandtrusted-only
topics.Thiscanbeaccomplishedusingthekafka-console-producer.shCLI:
| $KAFKA_HOME/bin/kafka-console-producer.sh |     |                                      |     |     | \   |     |
| ----------------------------------------- | --- | ------------------------------------ | --- | --- | --- | --- |
| --producer.config                         |     | $KAFKA_HOME/config/client.properties |     |     |     | \   |
| --broker-list                             |     | localhost:9094                       |     | \   |     |     |
| --topic guest-readable                    |     |                                      |     |     |     |     |
Keyinafewrecords,andpressCTRL-Dwhendone.Repeatforthetrusted-onlytopic.
Run our consumer code again. This time it should work. You can also change the user from guest
toalice,anditwillstillworkasexpected.
Withthelow-assurancetopicoutoftheway,thenextpartofthechallengeistomakeourmedium-
assurance trusted-only topic behave as intended. The solution, as the reader would have guessed,
istomixallowanddenyoutcomes.
Startbymakingthetrusted-onlytopicworld-readable:
| $KAFKA_HOME/bin/kafka-acls.sh |      |                                      |              | \   |     |     |
| ----------------------------- | ---- | ------------------------------------ | ------------ | --- | --- | --- |
| --command-config              |      | $KAFKA_HOME/config/client.properties |              |     |     | \   |
| --bootstrap-server            |      | localhost:9094                       |              |     | \   |     |
| --add --allow-principal       |      |                                      | User:"*"     |     | \   |     |
| --operation                   | Read | --topic                              | trusted-only |     |     |     |
Then,followupbyexcludingtheguestuserfromthetrusted-onlytopic,usingthe--deny-principal
flag:
| $KAFKA_HOME/bin/kafka-acls.sh |      |                                      |              | \   |     |     |
| ----------------------------- | ---- | ------------------------------------ | ------------ | --- | --- | --- |
| --command-config              |      | $KAFKA_HOME/config/client.properties |              |     |     | \   |
| --bootstrap-server            |      | localhost:9094                       |              |     | \   |     |
| --add --deny-principal        |      |                                      | User:guest   |     | \   |     |
| --operation                   | Read | --topic                              | trusted-only |     |     |     |
Essentially, the above is saying: “make ‘trusted-only’ readable by everyone except the ‘guest’ user”,
whichisexactlywhatisneeded.
Run the code again, switching the user to guest and the topic to trusted-only. We should see a
TopicAuthorizationException.However,ifweswitchtheusertoalice,thetopic’scontentswillbe
revealed.
Deny rulestakeprecedenceoverallow rules,irrespectiveofthegranularityoftheresourcepatterns
identified by those rules. Therefore, you should always use an allow rule over a broader-matching

331
| Chapter16: | Security |     |     |     |     |
| ---------- | -------- | --- | --- | --- | --- |
resource pattern than a deny rule. Stated otherwise, deny a subset of the resources that you had
previouslyallowed,nottheotherwayround.Hadweattemptedtoreversetherulesofthechallenge
— deny everyone but allow a specific user, it would not have worked — the deny rule would have
hadthefinalsay.Inpractice,whencombiningallowanddenyoutcomes,theouter-mostdenyruleis
implicit—enforcedbyKafka’sowndefault-deny policy.Thisgivesustheflexibilityof(upto)three
rule tiers — the outer-most default-deny, followed by a custom allow rule, and finally by a custom
deny rule.
| Literal | and prefixed |     | resource | patterns |     |
| ------- | ------------ | --- | -------- | -------- | --- |
Most ACL examples so far have involved the literal matching of resources and user principals.
In other words, we specified the exact name of the topic and the exact username, along with a
concrete allow/deny outcome. These are called literal matches. In one example, when setting up
world-readabletopics,weresortedtotheuseoftheUser:"*"wildcard matchontheuserprincipal.
It should be noted that wildcards in Kafka ACLs behave very differently to the familiar wildcards
used to match files and directories in the UNIX and Windows command shells. When used on its
own, a wildcard implies “match all entities”. However, a wildcard cannot be combined with literal
texttomean“matchapartofastring”.
KafkaresolvedtheissueofpartialresourcematchinginKIP-290⁴¹aspartofrelease2.0.0—
withtheintroductionofprefixedACLs,addressingsomeofthelong-standinglimitationsof
itsmultitenancycapabilities.
The default pattern type is literal, which is the equivalent of invoking kafka-acls.sh with the
--resource-pattern-type=literalflag.Partialmatchescanbeaccomplishedusingthe--resource-pattern-type=prefixed.
Forexample,thefollowingdeniesguestfromalltopicsthatbeginwiththeliteralstringtrusted:
| $KAFKA_HOME/bin/kafka-acls.sh    |                  |                                      | \          |     |     |
| -------------------------------- | ---------------- | ------------------------------------ | ---------- | --- | --- |
| --command-config                 |                  | $KAFKA_HOME/config/client.properties |            |     | \   |
| --bootstrap-server               |                  | localhost:9094                       |            | \   |     |
| --add                            | --deny-principal |                                      | User:guest | \   |     |
| --resource-pattern-type=prefixed |                  |                                      |            | \   |     |
| --operation                      | Read             | --topic                              | trusted    |     |     |
Literalstringsandwildcardscanbeusedtoidentifyresources,aswellastoidentifyuserprincipals.
Prefixedmatchingappliesonlytoresources—itisnotpossibletoprefix-matchauser.
⁴¹https://cwiki.apache.org/confluence/x/QpvLB

332
| Chapter16: | Security         |     |                |           |     |
| ---------- | ---------------- | --- | -------------- | --------- | --- |
| Listing    | and bulk-removal |     | of permissions | using the | CLI |
It has been shown how rules can be created and removed using the CLI. The most convenient way
to list rules, provided you are working in a browser environment, is via Kafdrop’s ACL screen. In
those cases where Kafdrop is not at hand, the kafka-acls.sh can be used to query rules and even
removetheminbulk.
We’ve already seen how the --list switch can be used to display the rules. But a production envi-
ronment containing hundreds or thousands of ACL entries makes the unfiltered --list command
unwieldy.Running--list(withnootherpredicates)onthestateoftheACLsaccumulatedfromall
previousexamplesresultsinthefollowing:
| Current | ACLs for resource | `Topic:LITERAL:guest-readable`: |     |     |     |
| ------- | ----------------- | ------------------------------- | --- | --- | --- |
User:* has Allow permission for operations: Read from hosts: *
| Current    | ACLs for resource | `Cluster:LITERAL:kafka-cluster`: |                 |     |     |
| ---------- | ----------------- | -------------------------------- | --------------- | --- | --- |
| User:alice | has Allow         | permission                       | for operations: | ￿   |     |
|            | IdempotentWrite   | from hosts:                      | *               |     |     |
| Current    | ACLs for resource | `Topic:PREFIXED:trusted`:        |                 |     |     |
User:guest has Deny permission for operations: Read from hosts: *
| Current | ACLs for resource | `Topic:LITERAL:getting-started`: |     |     |     |
| ------- | ----------------- | -------------------------------- | --- | --- | --- |
User:alice has Allow permission for operations: Read from hosts: *
User:alice has Allow permission for operations: Write from hosts: *
| Current | ACLs for resource | `Topic:LITERAL:trusted-only`: |     |     |     |
| ------- | ----------------- | ----------------------------- | --- | --- | --- |
User:guest has Deny permission for operations: Read from hosts: *
User:* has Allow permission for operations: Read from hosts: *
Current ACLs for resource `Group:LITERAL:basic-consumer-sample`:
User:alice has Allow permission for operations: Read from hosts: *
The output is large but still useful. In fact, we can observe that the Deny permission for the literal
trusted-only topic is redundant, as it is covered by the broader-matching Deny permission on the
prefixtrustedtopic;bothtargetingthesameuserprincipalalice.
The basic --list switch can be elaborated upon using two additional resource pattern types: any
and match. The any pattern type locates all rules that match the specified resource name exactly,
whichincludesliteral,wildcardandprefixedpatterns.

Chapter16: Security 333
$KAFKA_HOME/bin/kafka-acls.sh \
--command-config $KAFKA_HOME/config/client.properties \
--bootstrap-server localhost:9094 \
--list --resource-pattern-type=any \
--topic trusted-only
Thiscommandresultsinthefollowing:
Current ACLs for resource `ResourcePattern(resourceType=TOPIC, ￿
name=trusted-only, patternType=LITERAL)`:
(principal=User:*, host=*, operation=READ, permissionType=ALLOW)
(principal=User:guest, host=*, operation=READ, permissionType=DENY)
The any pattern type has located two rules of the three that apply to the trusted-only topic. It
didn’t match the prefixed pattern for the name trusted, even though the latter also applies to the
trusted-onlytopic.Toincludeallrulesthataffectagivenresource,changethepatterntypetypeto
match.Theresult:
Current ACLs for resource `ResourcePattern(resourceType=TOPIC, ￿
name=trusted-only, patternType=LITERAL)`:
(principal=User:*, host=*, operation=READ, permissionType=ALLOW)
(principal=User:guest, host=*, operation=READ, permissionType=DENY)
Current ACLs for resource `ResourcePattern(resourceType=TOPIC, ￿
name=trusted, patternType=PREFIXED)`:
(principal=User:guest, host=*, operation=READ, permissionType=DENY)
Splendid.Allthreeruleshavebeenlocated.ThematchpatterntypeisconvenientfordebuggingACLs
issues and reasoning about the access rights to a specific resource. Using a match query effectively
letsustakealookatACLswiththeeyesofKafka’sbuilt-inSimpleAclAuthorizer.
Theabilitytoroundupseveralrulesforaresourceisalsoconvenientwhenperformingabulkdelete
operation. To remote the matching rules, run the commands above using the --remove switch in
placeof --list.Bearinmindthattheremovecommanddoesnotpreviewtherulesbeforedeleting
them,althoughitdoesprovideforayes/nosafetyprompt,unlessexecutedwiththe--forceswitch.
Consequently,alwaysrunthe--listcommandtoascertainthecompletelistofaffectedrulesbefore
removingthem.
Intheexamplesabove,wefirstcreatedtheguestuser,thenthetwotopics,thenassociatedtheuser
with the topics through several ACL rules. Kafka allows us to carry out these steps in any order.
When creating a rule, there is no requirement that either the principal or the resource must exist.
Thereisnoreferentialdependencyrelationshipbetweenthesethreeentities.Asaconsequence,itis
alsopossibletodeleteusersandtopicsdespitehavingACLrulesthatreferencethem.

Chapter16: Security 334
As much as it is a convenience, it is also a ‘gotcha’: it is possible to accidentally create a rule that
referencesamistypeduserortopic—providedtheruleiswell-formed,Kafkawillvacuouslypersist
it. When creating ACL rules, particularly when these rules relate to high-assurance resources, it
is considered best-practice to validate these rules. The best way to do this is programmatically, by
writing a script that performs specific actions and asserts the allow or deny outcomes. However,
thecostofgettingthetestwrongmaybecatastrophic—imagineassertingaDeleteoperationthat
succeedswhenitisexpectedtofail.Theresultisthedeletionofresources—notjustafailureofthe
test,butthelossofpotentiallyvitaldataandthedisruptionofdownstreamconsumers.
Therecommendedwaytotestrulesistooperatetwoclusters—astaging andaproductionsetup—
where the production ACLs are only manipulated via pre-canned scripts that have been validated
against the staging cluster. This strategy implies that users are mirrored in both clusters and have
identicalpermissions.Inthatsense,astagingclusterisquitedifferentfromatypicalnon-production
cluster,whereusersmightroutinelybegivenelevatedpermissionstoenableproductivity.
Network address restrictions
Inadditiontorestrictionsonuserprincipals,Kafkaallowsrulestospecifythenetworkaddressesof
connectedclients.Thisisdonewiththe--allow-hostor--deny-hostflags,withtheirsoleargument
being the target IP address. Multiple --allow-host and --deny-host flags may be specified in a
singlecommand,resultingintheadditionofmultiplerules.
The hosts passed to --allow-host and --deny-host must be specific addresses — in either
IPv4orIPv6form.HostnamesandIPaddressrangesarenotpresentlysupported.KIP-252⁴²
proposestoextendtheexistingACLfunctionalitytosupportaddressrangesaswellasgroups
ofaddressesinCIDRnotation;however,thisKIPisstillinitsinfancy.
While the option is present, the changing landscape of application architecture — the proliferation
of Cloud-based technologies, containerisation, NAT, and elastic deployment topologies, make it
difficult to recommend host-based rules as a legitimate form of application-level access control. It
isbecomingmorechallengingandsometimesdownrightintractabletoisolateapplicationprocesses
basedontheirnetworkaddress.Wherethismakessense,controllingaccessbasedonoriginaddresses
is best accomplished with a firewall — a device that is designed for this very purpose and provides
effective network and transport-layer controls. Furthermore, firewalls understand ports, protocols
and, crucially, address ranges. The latter represents an absolute requirement when dealing with
elastic deployments. At the application layer, access should be a function of user principals and,
ideally,theirroles.
⁴²https://cwiki.apache.org/confluence/x/jB6HB

335
| Chapter16: Security |               |     |           |     |
| ------------------- | ------------- | --- | --------- | --- |
| Common              | authorization |     | scenarios |     |
Although there are numerous supported operations and resource types supported by Kafka’s au-
thorization model, the majority of authorization needs fall under a handful of use cases. These are
presentedhere.
| Creating | topics |     |     |     |
| -------- | ------ | --- | --- | --- |
ThecreationofatopicrequiresCreateprivilegesontheTopicresourcetype.Thetopicnamecanbe
literal,althoughmoreoftentopiccreationandpublishingrightsaregrantedoveraprefixedresource
pattern. In the example below, user is granted permission to create any topic that begins with
bob
thestringprices..
| $KAFKA_HOME/bin/kafka-acls.sh    |                   |                                      | \          |     |
| -------------------------------- | ----------------- | ------------------------------------ | ---------- | --- |
| --command-config                 |                   | $KAFKA_HOME/config/client.properties |            | \   |
| --bootstrap-server               |                   | localhost:9094                       | \          |     |
| --add                            | --allow-principal |                                      | User:bob \ |     |
| --resource-pattern-type=prefixed |                   |                                      | \          |     |
| --operation                      | Create            | --topic                              | prices.    |     |
| Deleting                         | topics            |                                      |            |     |
Topic deletion privileges are the logical opposite of creation, replacing the Create operate with
Delete:
| $KAFKA_HOME/bin/kafka-acls.sh    |                   |                                      | \          |     |
| -------------------------------- | ----------------- | ------------------------------------ | ---------- | --- |
| --command-config                 |                   | $KAFKA_HOME/config/client.properties |            | \   |
| --bootstrap-server               |                   | localhost:9094                       | \          |     |
| --add                            | --allow-principal |                                      | User:bob \ |     |
| --resource-pattern-type=prefixed |                   |                                      | \          |     |
| --operation                      | Delete            | --topic                              | prices.    |     |
| Publishing                       | to a topic        |                                      |            |     |
Like creating a topic, publishing is often granted as a prefixed permission — requiring
Write
capabilityontheTopicresourcetype.

336
Chapter16: Security
| $KAFKA_HOME/bin/kafka-acls.sh    |       |                                      | \        |     |     |
| -------------------------------- | ----- | ------------------------------------ | -------- | --- | --- |
| --command-config                 |       | $KAFKA_HOME/config/client.properties |          |     | \   |
| --bootstrap-server               |       | localhost:9094                       |          | \   |     |
| --add --allow-principal          |       |                                      | User:bob | \   |     |
| --resource-pattern-type=prefixed |       |                                      |          | \   |     |
| --operation                      | Write | --topic                              | prices.  |     |     |
If the publisher requires idempotence enabled, then a further IdempotentWrite operation must be
allowedfortheClusterresourcetype:
| $KAFKA_HOME/bin/kafka-acls.sh |                 |                                      | \         |     |     |
| ----------------------------- | --------------- | ------------------------------------ | --------- | --- | --- |
| --command-config              |                 | $KAFKA_HOME/config/client.properties |           |     | \   |
| --bootstrap-server            |                 | localhost:9094                       |           | \   |     |
| --add --allow-principal       |                 |                                      | User:bob  | \   |     |
| --operation                   | IdempotentWrite |                                      | --cluster |     |     |
| Consuming                     | from a          | topic                                |           |     |     |
For a free consumer to read from a topic, it requires the operation on the resource
Read Topic
type. Although consumer permissions may be specified using prefixed patterns, the more common
approach is to issue literal credentials to minimise unnecessary topic exposure — in line with the
Principle of Least Privilege. To grant bob read rights to the prices.USD topic, issue the following
command.
| $KAFKA_HOME/bin/kafka-acls.sh |      |                                      | \          |     |     |
| ----------------------------- | ---- | ------------------------------------ | ---------- | --- | --- |
| --command-config              |      | $KAFKA_HOME/config/client.properties |            |     | \   |
| --bootstrap-server            |      | localhost:9094                       |            | \   |     |
| --add --allow-principal       |      |                                      | User:bob   | \   |     |
| --operation                   | Read | --topic                              | prices.USD |     |     |
If the consumer operates within the confines of a consumer group, then the Read operation on the
Group resource type is required. Often, the consumer group is named after the user because that’s
howtheuser-grouprelationshiptendstounfoldinpractice.Aconsumergroupiseffectivelyaprivate
load-balancer, and in the overwhelming majority of cases, the partition load is distributed across
instances of the same application. So it’s safe to assume that users will want to create their own
consumergroups.Thiscanbeaccomplishedusingaprefix-basedconvention.Tograntbobrightsto
allconsumergroupsbeginningwithbob.,runthefollowingcommand.

Chapter16: Security 337
$KAFKA_HOME/bin/kafka-acls.sh \
--command-config $KAFKA_HOME/config/client.properties \
--bootstrap-server localhost:9094 \
--add --allow-principal User:bob \
--resource-pattern-type=prefixed \
--operation Read --group bob.
Thefacetsofinformationsecurityarenumerousandvaried,anditcanbesaidwithoutexaggeration
thatsecuringKafkaisanepicjourney.Ifthereisonethingtobetakenawayfromthischapter,itis
that threats are not confined to external, anonymous actors. Threats can exist internally and may
persist for extended periods of time, masquerading as legitimate users and occasionally posed by
them.
ThecorrectapproachtosecuringKafkaisalayeredone.Startingwithnetworkpolicy,theobjective
is to logically segregate networks and restrict access to authorized network segments, using virtual
networkingifnecessarytosecurelyattachedgenetworkstooperationalsites.Networkpoliciesblock
clientsoperatingoutofuntrustednetworksanderectbarrierstothwartinternalthreads,restricting
theirabilitytoaccessvitalinfrastructurecomponents.
Having segregated the network, the attention should be turned to assuring the confidentiality and
integrity of communications. This is accomplished using transport layer security, which in Kafka
is implemented under the SSL moniker. The use of X.509 certificates complements SSL, providing
furtherassurancetotheclientsastotheidentityofthebrokers—verifyingthatthebrokersarewho
they claim to be. SSL can be applied both to client-to-broker and interbroker communications. We
alsohavelearnedofthepresentlimitationsinKafkavisàvisSSL—namely,theinabilitytouseSSL
tosecurebroker-to-ZooKeepercommunications.
SSL can also be used in reverse, acting as an authentication mechanism — attesting the identity
of the connected client to the broker. On the point of authentication, Kafka offers a myriad of
options using SASL. These range from the enterprise-focused GSSAPI (Kerberos), to PLAIN and
SCRAMschemesforusername/password-basedauthentication,OAuth2.0bearertokens,andfinally
to delegation tokens that simplify the authentication process for large hordes of ephemeral worker
nodes. Authentication isn’t just limited to broker nodes; it can also be enabled on ZooKeeper —
limitingone’sabilitytoviewandmodifythecontentsofpotentially sensitiveznodes.
Authentication, as we have learned, is really a stepping stone towards authorization. There is little
benefit in knowing the identity of a connected party if all parties are to be treated equally. Parties
— being clients and peer brokers — bear different roles within the overall architecture landscape.
Theyhavedifferentneedsandareassociatedwithvaryinglevelsoftrust.Kafkacombineselaborate,
rule-based ACLs with a default-deny permission model to create a tailored access profile for every
client.
The layered security controls supported by Kafka, in conjunction with externally-sourced controls
— such as firewalls, VPNs, transparent TLS proxies and storage encryption — collectively form an

Chapter16: Security 338
environmentthatisimpregnableagainstabroadrangeofthreatactors.Theyenablelegitimateactors
tooperateconfidentlyinasecureenvironment.

Chapter 17: Quotas
There has been a strong emphasis throughout this book on Kafka’s role as a proverbial ‘glue’ that
bindsdisparatesystems.Attheverycoreoftheeventstreamingparadigmisthenotionofmultiple
tenancies.
Chapter 16: Security has set the foundation for multitenancy — delineating how clients having
different roles and objectives can securely connect to, and share broker infrastructure — the under-
lyingtopics,consumergroupsandotherresourcetypes.Despitetheunmistakableoverlapbetween
security and quotas, the latter stands on its own. The discussion on quotas transcends security,
affecting areas such as quality of service and capacity planning. In saying that, the use of quotas
requiresauthenticationcontrols;therefore,Chapter16:Securityisaprerequisiteforthischapter.
The examples in this chapter will not work unless authentication has been enabled on
the broker and suitable client-side preparations have been made. If you have not yet read
Chapter16:Securityandworkedthroughtheexamples,pleasedosobeforeproceedingwith
thematerialbelow.
The rationale behind quotas
Mitigating denial of service attacks
Asithasbeenjustsaid,thediscussiononquotasisalogicalextensionofthe‘security’topic.Atthe
heartofinformationsecurityarethreeprimordialconcepts,oftenreferredtoastheCIAtriad.(Not
to be confused with the intelligence agency.) The acronym deciphers to confidentiality, integrity
and availability — phrased in relation to information assets. Chapter 16: Security touched on all
aspectsoftheCIAtriad,butfocusedmostlyonconfidentialityandintegrity,usingspecificsecurity
controls such as encryption (TLS), X.509 certificates, authentication (Kerberos, SASL, OAuth) and
authorization (ACLs). The availability aspect is partly catered to by the authorization control: by
ensuringthatpartiesareonlyactinginamannerthathasbeenprescribedforthem,wecanprotect
otherpartiesfromunsanctionedinterference.
TheflexibilityofKafka’srule-basedACLsenablesustoapplyvaryinglevelsofassurancetodifferent
resources. We might have low-assurance topics collocated with high-assurance topics, where a
greater number of semi-trusted clients may be allowed to access the former, admitting a much
moreselectgroupofclientstothelatter.Inanotherscenario,wemightbeoperatingaSaaSbusiness,
where resources are partitioned on a per-customer basis and where it is essential that customers
cannotaccessormanipulateeachother’sdata.

Chapter17: Quotas 340
Even with fine-grained ACLs in place, an authorized client with the lowest level of access may
attempt to monopolise cluster resources with the intent of disrupting the operation of legitimate
clients. This might be a client with read-only access to some innocuous topic. Alternatively, in the
SaaS scenario, it may be a low-tier paying customer whose sole intention is to saturate the service
providerandtherebycausefinancialharm.
The mechanism for a denial of service (DoS) attack is fairly straightforward. Once a client gains
access to a resource, it can generate large volumes of read queries or write requests (depending on
itslevelofaccess),thuscausingnetworkcongestion,memorypressureandI/Oloadonthebrokers.
Astheseareallfiniteresources,theirdisproportionateconsumptionbyoneclientcreatesstarvation
fortherest.
Quotas fill the gap left by ACLs, specifying the extent of resource utilisation that is to be accorded
to a user. Where that limit has been breached, the brokers will automatically activate mitigating
controls,throttlingaclientuntilitsrequestprofilecomplieswiththesetquota.Therearenofurther
penalties applied to the offending client beyond throttling. This is intended, as aside from a traffic
spike,thereisnothingtosuggestthataclientismalicious;itmaysimplyberespondingtoelevated
levelsofdemand.
Capacity planning
With multiple clients contending for the use of a common Kafka cluster, how can the operator be
surethatthefiniteresourcesavailabletotheclusteraresufficienttomeettheneedsofitsclients?
Thisanswerleadstothebroadertopicofcapacityplanning.Thisisacomplex,multi-disciplinedtopic
thatincludeselementsofmeasurement,modelling,forecastingandoptimisation.Anditisfairtosay
thatthismaterialiswelloutsideourscope.However,thefirststepofcapacityplanningismodelling
thedemand,andquotasareremarkablyhelpfulinthisregard.Ifallcurrentandprospectiveusersof
a cluster have been identified, and each has had their quotas negotiated, then the aggregate ‘peak’
demand can be determined. Whether or not the cluster’s resources (disks, CPU, memory, network
bandwidth, etc.) will be provisioned to cover the worst-case demand is a separate matter — the
balanceofcostandwillingnesstoaccepttheriskoffailingtomeetdemand.
Quality of service
Qualityofservice(QoS)isstronglyrelatedtoboththesecurityandcapacityaspects.QoSfocuseson
the customer (or the client, in the context of Kafka), ensuring that the latter receives a service that
meets or exceeds the baseline warranted by the service provider. This measure is, in simple terms,
a function of the provider’s ability to furnish sufficient capacity when it is called for, as well as its
resistancetoDoSattacks.
QoS naturally dovetails into operational-level agreements (OLAs) — arrangements between collab-
orating parties that influence the consuming party’s ability to provide a service to its downstream
consumer — ultimately affecting support-level agreements (SLAs) at the organisation’s boundary.

Chapter17: Quotas 341
Without specific QoS guarantees, OLAs cannot be reliably fulfilled — that is, in the absence of
excessive over-provisioning of resources. The latter is expensive and wasteful; in most cases, it is
more economically viable to ration resources than to the purchase excess capacity, unless the cost
ofrationingexceedsthecostoftheresourcesthatarebeingpreserved.
When operating a small cluster with only a handful of connected clients, the baseline capacity is
oftenalreadyinexcessofthepeakdemand,particularlywhentheclustercomprisesmultiplebrokers
foravailabilityanddurability.Insuchdeployments,Kafka’sefficiencyprovidesforampleheadroom,
andmanagingquotasmaynotbethemostproductiveuseanoperator’stimeandresources.Asthe
number of clients grows and their diversity broadens, the need to manage quotas becomes more
apparent.
Types of quotas
Kafkasupportstwotypesofquotas:
1. Networkbandwidthquotas—inhibitproducersandconsumersfromtransferringdataabove
asetrate,measuredinbytespersecond.
2. Request rate quotas — limit a client’s CPU utilisation on the broker as a percentage of one
networkorI/Othread.
Quotas (both types) are defined on a per-broker basis. In other words, a quota is enforced locally
within an individual broker — irrespective of what the client may be doing on other brokers. If a
clientconnectstotwobrokers,itwillbeservedtheequivalentoftwoquotas;amultipleofN quotas
forN brokers.Eventhoughaclientmayreceiveamultipleofitsoriginalquota,itcanneverexceed
theoriginalquotalimitonanygivenbroker.
TheadditionofquotastoKafkawasoneoftheearliestimprovementproposals,implemented
withinKIP-13⁴³andreleasedinversion0.9.0.0.Thedecisiontoenforcequotasatthebroker
level rather than at the cluster level was done out of pragmatism — it was deemed too
complicatedtopoolresourceconsumptionmetricsinreal-timefromallbrokerstodetermine
theaggregateconsumptionandtoenforcethecombinedusageuniformlyacrossallbrokers.
The consensus mechanism for tracking cross-broker resource usage was considered to be
more difficult to implement than the quota enforcement mechanism. As such, the simpler
solution was chosen instead, and despite numerous iterative enhancements, remains per-
brokertothisday.
The application of per-broker quotas suffers from one notable drawback: a client’s effective (mul-
tiplied) quota dilates with the cluster size. The addition of nodes to a cluster (to meet increased
demandortoincreasedurability)carrieswithitanincreasedlikelihoodthataclientwillconnectto
morebrokersasaresult,potentiallyleadingtoahigherquotamultiple.Atthesametime,itcannot
⁴³https://cwiki.apache.org/confluence/x/cpcWAw

Chapter17: Quotas 342
be stated definitively that a client will connect to more brokers as the latter are scaled out — the
actualnumberofbrokerconnectionswilldependonthenumberofpartitionsforthetopicsthatthe
client publishes to or consumes from, as well as the number of backing replicas. When scaling the
cluster,itmaybenecessarytoadjusteachclient’squotaindividually,projectingaggregatenumbers
andworkingbacktodeterminewhattheper-brokerquotasshouldbe—compensatingfortheeffects
ofthedilation.
Network bandwidth quotas
Networkbandwidthquotasrelyontheamountoftransferreddataasadefinitivemetricforassessing
aclient’sutilisationofabroker’savailableresources.Itmaybethoughtofasacompoundmetric—
increasingtherateofdatatransferplacesagreaterstrainonthenetwork,butalsocommensurately
utilisestheI/Ochannelsonthebroker,andmayleadtoincreasedmemorypressure(duetobuffering).
In addition, when the client connects to an SSL listener, the broker loses its ability to employ the
zero-copy optimisation,involvingtheCPUtoencryptanddecryptnetworkdata.WithSSLenabled,
thegreaterthetransferrate,thegreatertheloadontheCPU.
Zero-copy describes computer operations in which the CPU does not perform the task of
copying data from one memory area to another. In a typical I/O scenario, the transfer of
datafromanetworksockettoastoragedeviceoccurswithouttheinvolvementoftheCPU
andwithareducednumberofcontextswitchesbetweenthekernelandusermode.
Anetworkbandwidthquotaisspecifiedasapairofvalues:aproducer_byte_rateandaconsumer_-
byte_rate — representing the upper bound on the allowable bandwidth, in bytes per second (B/s).
This unit of measurement is a slight departure to the conventional way of measuring bandwidth
—bitspersecond;however,whendealingwithapplication-levelpayloadsizes,operatingwithbyte
multiplesismoreconvenient.
Quotasareenforcedbysamplingtheclient’sactivityoveraperiodoftime,usingarudimentaryslid-
ingwindowalgorithm.Apairofbrokerproperties—quota.window.numandquota.window.size.seconds
— stipulate the number of samples N retained and the duration of each sampling S period, respec-
tively. The default values are 1 — for the sample duration, and 11 — for the number of samples.
Collectively,thesamplesrepresentaslidingwindow.
Theenforcementalgorithmcomparestheclient’sobservedresourceutilisationU withthemaximum
allowed by the quota Q, over the observation period T. The precise calculation of T is somewhat
intricate,takingintoaccountthenumberofavailablesamples,theageoftheoldestsampleandthe
amount of time spent in the current sampling period. For simplicity and convenience, assume that
T istheresultofmultiplyingofN byS.Whilethisisnotentirelyaccurate,thedifferenceisatmost
S seconds; the simplified formula will suffice for the purpose of an explanation. We will return to
theprecisecalculationlater.
When U exceeds Q, the broker will penalise the client by introducing an artificial delay into the
response. The client is not aware that a penalty is in force, observing what it believes is network

Chapter17: Quotas 343
congestion. There are no errors as such, other than potential errors caused by network timeouts if
thedelayisinexcessoftheclient’stimeouttolerance.(Thisispossible,aswewillshortlysee.)
ThecalculationofthedelayD isgivenbythefollowingsimpleformula:
T(U (cid:0)Q)
D =
Q
Where the value of U is obtained by summing all values over the last quota.window.num samples,
andT istheproductof quota.window.numandquota.window.size.seconds.
ThedutycycleY ofthesystemisgivenby:
T
Y =
D+T
Wecanworkthroughtheformulausingseveralexamples.Assumingthewritequotaissetto20kB/s,
with three clients C0, C1 and C2, consuming at rates of 14 kB/s, 36 kB/s and 100 kB/s, respectively.
ThevalueforT willbe10 forsimplicity.
ForC0,DC₀=10×(14 –20)/20=–1.
SinceDC₀isanegativenumber,andthedelaymustclearlybegreaterthanzero,nopenaltyisapplied
inthiscase.
ForC1,DC₁=10×(36 –20)/20=8.
That’s eight seconds of delay before the response is returned. After the delay elapses, C1 is free to
publish the next batch. Assuming a constant publishing rate, C1 will cycle between ten seconds of
productivity,followedbyeightsecondsofhiatus—adutycycleofjustunder56%.
ForC2,DC₂=10×(100–20)/20=40.
Adelayoffortysecondsinthiscaseisquitehefty,reducingthedutycycleto20%.Still,thepenalty
isfitting,giventherateatwhichC2 isattemptingtopublish.
In the case of a producer, the broker will append the request batch to the log but will not return a
responseimmediately.Instead,theresponseisqueuedinternallyforthedurationofthepenaltytime
beforebeingreturnedtotheclient.Inthecaseofaconsumer,therequestisdelayedfortheduration
ofthepenaltybeforeperformingtheread.
In both cases, Kafka arranges the I/O operation relative to the delay such that the penalty
doesnotcauseunduememorypressureonthebroker.Whenwriting,thebatchisappended
tothelogbeforepausing.Whenreading,thepauseoccursbeforethediskfetch.
Request rate quotas
When bandwidth quotas were originally introduced, it was assumed that bandwidth is a holistic
indicator of a client’s impact on a broker’s resource utilisation. And in many ways bandwidth is

Chapter17: Quotas 344
still an excellent metric. The main issue with bandwidth metrics is that they focus on the payload,
overlookingtherequestitself.Andindeed,whentherequestsarereasonablysizedandtheclientsare
correctlyconfigured(andwell-behaved),theactofmakingtherequestcarriesanegligibleoverhead
on the broker compared to servicing the request. However, if a client sends requests too quickly
(forexample,aconsumerwithfetch.max.wait.msormax.partition.fetch.bytessettoverysmall
values), it can still overwhelm the broker even though individual request/response sizes may be
small.
RequestratequotaswereintroducedinKIP-124⁴⁴aspartofrelease0.11.0.0,whenitbecameobvious
thatanadditionallevelofcontrolwasrequiredtopreventdenialofservicefromfrequentprotocolac-
tivity.Inadditiontoprotectingbrokersfromexcessiverequestrates,theimprovementproposalwas
generalised to address other scenarios that saw clients utilise the broker’s CPU disproportionately
totherequest/responsesize.Theseinclude—
• Denialofservicefromclientsthatoverloadbrokerswithcontinuousunauthorizedrequests.
• Compression settings on the broker that contradict producers’ assigned compression scheme,
requiringdecompressionandre-compressiononthebroker.
• A mixture of TLS and non-TLS clients (where both listener types have been exposed); the
formerexertasignificantlygreaterloadonthebrokerforthesameamountoftraffic.
Request quotas are configured as a fraction of overall time a client is allowed to occupy request
handler(I/O)threadsandnetworkthreadswithineachquotawindow.Forexample,aquotaof50%
implies that half of a thread can be utilised on average within the measured time window, 100%
impliesthatafullthreadmaybeutilised,200%istheequivalentoftwofull-timethreads,andsoon.
The underlying mechanism for enforcing request rate quotas is virtually identical to the one used
for network bandwidth quotas, with the only notable difference being the metric that is collected
overthesamplingwindow—usingCPUtimeratherthanthenumberofbytessentorreceived.
Therequestratecalculationspiggybackonthequota.window.numandquota.window.size.seconds
properties, originally devised for network bandwidth quotas. The enforcement mechanism applies
an artificial delay, using the formula presented earlier. The parity between the two mechanisms
simplifiestheirmanagementandreducesthenumberoftuningparameters.
Request rate quotas are effective at avoiding unintended DoS from misconfigured appli-
cationsordefectiveclientlibraries,whereabugmayinadvertentlyresultinahighrequest
rate.WhilethiscaterstosomeDoSscenarios,itdoesnotcompletelyinsulatetheclusterfrom
all types of DoS attacks originating from malicious clients. A malicious actor may launch
a distributed denial of service (DDoS) attack by recruiting a large number of connections,
possiblyoriginatingfrommultiplehosts.Thiswouldresultinalargeamountofexpensive
authenticationorCPU-intensiverequeststhatmayoverwhelmthebroker.
Ordinarily, the number of I/O and network threads is set in some proportion to the capabilities of
theunderlyinghardware—typically,thenumberofCPUcores.Oneoftheadvantagesofexpressing
⁴⁴https://cwiki.apache.org/confluence/x/y4IYB

Chapter17: Quotas 345
request rates in absolute percentage terms (i.e. a fraction of one core) is that it pins the quota
values, preventing the quotas from inadvertently dilating with the scaling of a broker’s resources.
For example, if the broker instances were to undergo a hardware refresh to quadruple the number
ofCPUcores,theexistingabsolutequotaswouldnotbeaffected.Hadthequotasbeenexpressedin
relativeterms(i.e.afractionofallcores),allexistingrequestratequotaswouldhavebeenimplicitly
dilatedbyafactoroffour.Note,thedilation-resistanceofrequestratequotasonlyappliestoscaling
intheverticalplane;inotherwords,whenabroker’sprocessingcapacityisincreased.Whenscaling
horizontally—throughtheadditionofbrokernodes—therequestratequotassufferfromthesame
dilationeffectthatwesawwithbandwidthquotas.
Themaindrawbackofstatingabsolutevaluesisthatitrequirestheoperatortobeawareof
the broker’s available capacity — the number of I/O and network threads. These values
are configured through the num.io.threads and num.network.threads broker properties
and, as we saw in Chapter 9: Broker Configuration, can also be changed remotely. Due
attention should be paid to both the contents of server.properties and the dynamic
configuration; otherwise, the quotas may end up misconfigured — dispensing a different
amountofresourcestowhatwasintended.
Subject affinity and precedence order
Quotas apply to two entity types: user principals and client IDs. The reader should by now be
intimately familiar with users, which were covered in detail in Chapter 16: Security. Users are the
subjectsofauthenticationandauthorizationcontrols.ClientIDswerebrieflymentionedinChapter
10:ClientConfiguration.AclientIDisanoptional,free-formlogicalidentifierofaclientconnection,
configured via the client.id client-side property. Client IDs allow Kafka to distinguish between
variationsofaclientandareorthogonaltousernames.
Unlike usernames, whose use is policed by Kafka’s authentication machinery, arbitrary
client IDs can be presented at the discretion of the client. Client IDs may be used to
further subcategorise clients that operate under a single username. Perhaps an application
comprisesmultiplediscreteprocessesthatsharethesameKafkacredentials.Theseprocesses
may be designated different tasks, which they carry out on behalf of the encompassing
application.WhenthemultipledifferentbutrelatedprocessesconnecttoKafka,itisprudent
todistinguishamongthem—maintainingfine-grainedaccountingandtraceabilityofclient
actions.
Quotas are defined for either usernames or client IDs individually, or may cover a combination of
the two. There are a total of eight ways a quota may be associated with its subject, depicted in
the table below. Additionally, the table captures the precedence order of each association, with the
lowestnumbercorrespondingtothehighestpriority.

346
Chapter17: Quotas
Precedenceorder Usernamepartialassociation ClientIDpartialassociation
| 1   | Specificusername | SpecificclientID |
| --- | ---------------- | ---------------- |
| 2   | Specificusername | DefaultclientID  |
| 3   | Specificusername | Unspecified      |
| 4   | Defaultusername  | SpecificclientID |
| 5   | Defaultusername  | DefaultclientID  |
| 6   | Defaultusername  | Unspecified      |
| 7   | Unspecified      | SpecificclientID |
| 8   | Unspecified      | DefaultclientID  |
Upon the consumption of a resource, Kafka will iterate through the table above in the order of
increasing precedence number, stopping at the first rule that matches the client’s attributes (being
theusernameandclientID).IfKafkaisunabletomatchanyoftheruleslistedabove,itwillconsult
thequota.producer.defaultandquota.consumer.defaultbrokerpropertiestodeterminewhether
astaticdefaulthasbeenset.Thesepropertiesaredeprecatedinfavourofdynamicconfiguration;it
isrecommendedtoavoiddefiningquotadefaultsinserver.properties—instead,quotasshouldbe
managedexclusivelyusingthekafka-configs.shCLIortheequivalentAdminClientAPIs.Finally,
intheabsenceofarulematchingthesuppliedusernameandclientID,Kafkawillnotapplyaquota
toaconnectedclient.
With the exception of the quota.producer.default and quota.consumer.default deprecated prop-
erties, quotas are persisted in ZooKeeper, under the /config/users and /config/clients znodes.
Specifically,fortheeightoptionsabove,thecorrespondingpersistenceschemesare:
1. Username+clientIDcombination:/config/users/<username>/clients/<clientId>
2. Username+defaultclientID:/config/users/<user>/clients/<default>
3. Username:/config/users/<username>
4. Defaultusername+specificclientID:/config/users/<default>/clients/<clientId>
5. Defaultusername+defaultclientID:/config/users/<default>/clients/<default>
6. Defaultusername:/config/users/<default>
7. ClientID:/config/clients/<clientId>
8. DefaultclientID:/config/clients/<default>
WiththeZooKeeperaspectofquotapersistencebeingfullyinsulatedfromtheoperator,one
mightwonderwheretheknowledgeofthestoragehierarchymayberequired.Theanswer
speaks to troubleshooting. Should you encounter an irreconcilable difference between the
behaviouroftheclusterandthereportedconfiguration,itmaybenecessarytojumpintoa
ZooKeepershelltodiagnosethepotentialcausesoftheunexplainedbehaviour.
Atthispoint,thereaderwouldberightinpointingoutthatrules#2and#3looksuspiciouslysimilar,
as do #4 and #7. There is a subtle difference among them, relating to how a quota is distributed
amongthe connected clients. When the rule scopes both a username anda client ID,evenif one or

Chapter17: Quotas 347
bothofthosevaluesaresetto<default>,thequotawillbeallocatedforthesoleuseoftheconnected
client,aswellasallotherclientswithidenticalusernameandclientIDattributes.
Conversely, if a quota specifies either the username or the client ID, but not both, then the quota
willbesharedamongallclientsthatmatchthatusernameorclientID,whicheveronewassupplied,
andirrespectiveofthevalueoftheomittedattribute.
Understandably, this might cause a certain amount of confusion, which can be resolved with a few
examples.Weshallfocusonrules#1,#2and#3becausetheycapturescenarioswherebothattributes
areassignedtospecificvalues,whereoneisassignedandtheotherreferencesthedefaultentity,and
finally,whereoneisassignedandtheotherisomitted.Considerthefollowingquotas:
Username ClientID Readrate(kB/s) Ruletype
alice pump 400 1
alice <default> 300 2
alice ￿ 200 3
Note, the value ￿ means that the attribute is not set; the value <default> implies the default entity
fortheattributeinquestion.
When a client C0 with the username/client ID tuple (alice,pump) connects, it will be allocated 400
kB/sofreadcapacity—havingmatchedthemostspecificrule.
WhenC1—aclientwiththesameattributes(alice,pump)—subsequentlyconnects,itwillsharethe
quota with C0 — having matched the same rule. In other words, the two clients will not be able to
consumemorethan400kB/sworthofdatabetweenthem.IfC0over-consumes,itwillaffectC1and
viceversa.
AthirdclientC2 connectswith(alice,sink)—matchingthe(alice,<default>)ruleandqualifying
for300kB/sofbandwidth.
AfourthclientC3connectswith(alice,drain)—matchingthe(alice,<default>)rulefor300kB/s.
However, although C2 and C3 were matched by the same rule, they have different client IDs and
willnotsharethequota.Inotherwords,C2 andC3 have300kB/seach ofallowablebandwidth.
A fifth client C4 connects with the same (alice,drain) attributes as C3. This client will end up
sharingthebandwidthwithC3 —300kB/s amongthem.
Next, we have C5 — a client authenticated asalice, having omitted the optional client ID. Despite
what one might assume, this is matched by the (alice,<default>) rule. When a default entity is
specifiedinarule,itwillmatchaclientwherenovalueforthecorrespondingattributeisprovided.
Essentially,atnopointisthe(alice,￿)rulefired;deletingitwouldmakenodifference.
C6 —anotheralicewithoutaclientID,willsharethequotawithC5.
Finally,C7 — a user authenticated asbob — will not match any of the rules in the table and will be
allowedtooperatewithunboundedcapacity.
Summarisingtheaboveexamples,wehavethetablebelow:

348
Chapter17: Quotas
| Client Username | ClientID | Matching             | Bandwidth | Shared |
| --------------- | -------- | -------------------- | --------- | ------ |
|                 |          | rule                 | (kB/s)    | among  |
| C0 alice        | pump     | (alice,pump)         | 400       | C0,C1  |
| C1 alice        | pump     | (alice,pump)         | 400       | C0,C1  |
| C2              |          | (alice,<default>)300 |           | C2     |
| alice           | sink     |                      |           |        |
| C3 alice        | drain    | (alice,<default>)300 |           | C3,C4  |
| C4 alice        | drain    | (alice,<default>)300 |           | C3,C4  |
| C5              |          | (alice,<default>)300 |           | C5,C6  |
alice
| C6 alice |     | (alice,<default>)300 |           | C5,C6 |
| -------- | --- | -------------------- | --------- | ----- |
| C7 bob   |     | Unmatched            | Unbounded | C7    |
Imagineforamomentthatwedeletedtherule(alice,<default>),leavingjustthe(alice,pump)and
(alice,￿)rules.Howwouldthisaffectthequotadistribution?
| Client Username | ClientID | Matching     | Bandwidth | Shared    |
| --------------- | -------- | ------------ | --------- | --------- |
|                 |          | rule         | (kB/s)    | among     |
| C0              |          | (alice,pump) | 400       | C0,C1     |
| alice           | pump     |              |           |           |
| C1 alice        | pump     | (alice,pump) | 400       | C0,C1     |
| C2 alice        | sink     | (alice,￿)    | 200       | C2,C3,C4, |
C5,C6
| C3 alice | drain | (alice,￿) | 200 | C2,C3,C4, |
| -------- | ----- | --------- | --- | --------- |
C5,C6
| C4 alice | drain | (alice,￿) | 200 | C2,C3,C4, |
| -------- | ----- | --------- | --- | --------- |
C5,C6
| C5 alice |     | (alice,￿) | 200 | C2,C3,C4, |
| -------- | --- | --------- | --- | --------- |
C5,C6
| C6 alice |     | (alice,￿) | 200 | C2,C3,C4, |
| -------- | --- | --------- | --- | --------- |
C5,C6
| C7 bob |     | Unmatched | Unbounded | C7  |
| ------ | --- | --------- | --------- | --- |
The first two clients — C0 and C1 — would be unaffected by the change, as they are matched by
the most specific and, therefore, the highest priority rule. Clients C2 through to C6 would now be
matched by the (alice,￿) rule. Although they have different client IDs (clients C2, C3 and C4) and
in some cases, the client IDs have been omitted (C5 and C6), all five clients will share the same
bandwidthquota.Finally,C7 isunaffectedbythechange.
Comparing the two sets of scenarios above, the difference between the (alice,<default>) and
(alice,￿) rules is not in the way they match clients. Both rules are equally eager in matching all
clientsauthenticatedasalice,irrespectiveofthevalueoftheclientID,orwhetherclientIDispro-
vided.Thedifferencebetweenthetwoisinhowtheydistributethequotas.Inthe(alice,<default>)
scenario, there are multiple quotas distributed evenly among matching clients having the same
usernameandclientIDattributes.Inthe(alice,￿)scenario,thereisasinglequotasharedamongall
clientswiththematchingusername.

349
| Chapter17: Quotas |     |     |     |
| ----------------- | --- | --- | --- |
Precedence order operates irrespective of bandwidth or resource quotas. In the examples
above,the most specific ruleshad themost relaxedbandwidth quotas. Wecould havejust
as easily applied the most conservative quota to the most specific rule — Kafka doesn’t
care either way. There is not a lot in the way of best-practices either. When quotas are
employedasasecuritymechanism,itisbesttostartwiththemostconservativequotaforthe
leastspecificrule—(<default>,<default>),increasingthequotawithrulespecificity.This
approach is the equivalent of a default-deny security policy, where permission is granted,
ratherthantakenaway.
| Applying | quotas |     |     |
| -------- | ------ | --- | --- |
Having ascertained how quotas are structured, persisted and applied, it is only fitting to set up an
examplethatdemonstratestheiruse.Theonlysnagisthatourpreviousexamples,whichpublishedin
smallquantities,arehardlysufficienttotripaquotalimit—unlessthelatterisabsurdlyconservative.
Beforewecandemonstrateanything,weneedarigthatiscapableofgeneratingasufficientvolume
ofrecordstomaketheexperimentworthwhile.
The complete source code listing for the upcoming example is available at
github.com/ekoutanov/effectivekafka⁴⁵ in the src/main/java/effectivekafka/quota
directory.
The listing below is that of a producer that has been rigged to publish records at an unbounded
speed.
import org.apache.kafka.clients.producer.*;
| public final  | class QuotaProducerSample  |                       | {   |
| ------------- | -------------------------- | --------------------- | --- |
| public static | void main(String[]         | args)                 |     |
| throws        | InterruptedException       | {                     |     |
| final         | var topic = "volume-test"; |                       |     |
| final         | var config = new           | ScramProducerConfig() |     |
.withBootstrapServers("localhost:9094")
.withUsername("alice")
.withPassword("alice-secret")
.withClientId("pump")
.withCustomEntry(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,
600_000);
⁴⁵https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/quota

350
| Chapter17: |       | Quotas |       |                    |     |     |     |     |     |
| ---------- | ----- | ------ | ----- | ------------------ | --- | --- | --- | --- | --- |
|            | final | var    | props | = config.mapify(); |     |     |     |     |     |
try (var producer = new KafkaProducer<String, String>(props)) {
|     | final | var    | statsPrinter |                              | =   | new StatsPrinter(); |            |     |      |
| --- | ----- | ------ | ------------ | ---------------------------- | --- | ------------------- | ---------- | --- | ---- |
|     | final | var    | key          | = "some_key";                |     |                     |            |     |      |
|     | final | var    | value        | = "some_value".repeat(1000); |     |                     |            |     |      |
|     | while | (true) | {            |                              |     |                     |            |     |      |
|     | final |        | Callback     | callback                     |     | = (metadata,        | exception) |     | -> { |
statsPrinter.accumulateRecord();
|     |     | if (exception |     | !=  | null) | exception.printStackTrace(); |     |     |     |
| --- | --- | ------------- | --- | --- | ----- | ---------------------------- | --- | --- | --- |
};
|     | producer.send(new |     |     |     | ProducerRecord<>(topic, |     |     | key, | value), |
| --- | ----------------- | --- | --- | --- | ----------------------- | --- | --- | ---- | ------- |
callback);
statsPrinter.maybePrintStats();
}
}
}
}
ThisclientdoesnotusethetraditionalMap<String, Object>forbuildingtheconfiguration;instead,
wehaveoptedforthetype-safeconfigurationpatternthatwasfirstintroducedinChapter11:Robust
Configuration. The complete listing of ScramProducerConfig is available in the GitHub repository.
Ithasbeenomittedhereasitbearslittlebearingontheoutcome.
The value of the record is sized to be exactly 10 kilobytes. The record, combined with the key,
headers, and other attributes will be just over 10 kB in size. The loop simply callsProducer.send()
continuously,attemptingtopublishasmuchdataaspossible.TheStatsPrinterclass,shownbelow,
helpskeeptrackofthepublishingstatistics.
| import  | static             |              | java.lang.System.*;  |           |                   |                               |     |        |     |
| ------- | ------------------ | ------------ | -------------------- | --------- | ----------------- | ----------------------------- | --- | ------ | --- |
| final   | class              | StatsPrinter |                      |           | {                 |                               |     |        |     |
| private |                    | static       | final                | long      | PRINT_INTERVAL_MS |                               | =   | 1_000; |     |
| private |                    | final        | long                 | startTime |                   | = System.currentTimeMillis(); |     |        |     |
| private |                    | long         | timestampOfLastPrint |           |                   | = startTime;                  |     |        |     |
| private |                    | long         | lastRecordCount      |           |                   | = 0;                          |     |        |     |
| private |                    | long         | totalRecordCount     |           |                   | = 0;                          |     |        |     |
| void    | accumulateRecord() |              |                      |           | {                 |                               |     |        |     |
totalRecordCount++;

351
Chapter17: Quotas
}
| void maybePrintStats() |                   |                               | {                 |                        |                       |         |                    |           |
| ---------------------- | ----------------- | ----------------------------- | ----------------- | ---------------------- | --------------------- | ------- | ------------------ | --------- |
| final var              | now               | = System.currentTimeMillis(); |                   |                        |                       |         |                    |           |
| final var              | lastPrintAgo      |                               | =                 | now -                  | timestampOfLastPrint; |         |                    |           |
| if (lastPrintAgo       |                   | > PRINT_INTERVAL_MS)          |                   |                        |                       | {       |                    |           |
| final                  | var elapsedTime   |                               | =                 | now                    | - startTime;          |         |                    |           |
| final                  | var periodRecords |                               |                   | = totalRecordCount     |                       |         | - lastRecordCount; |           |
| final                  | var currentRate   |                               | =                 | rate(periodRecords,    |                       |         | lastPrintAgo);     |           |
| final                  | var averageRate   |                               | =                 | rate(totalRecordCount, |                       |         | elapsedTime);      |           |
| out.printf("Elapsed:   |                   |                               | %,d               | s;                     | " +                   |         |                    |           |
|                        |                   | "Rate:                        | current           | %,.0f                  | rec/s,                | average | %,.0f              | rec/s%n", |
|                        |                   | elapsedTime                   |                   | / 1000,                | currentRate,          |         | averageRate);      |           |
| lastRecordCount        |                   | =                             | totalRecordCount; |                        |                       |         |                    |           |
| timestampOfLastPrint   |                   |                               | =                 | now;                   |                       |         |                    |           |
}
}
| private double |          | rate(long  | quantity, |        | long | timeMs) | {   |     |
| -------------- | -------- | ---------- | --------- | ------ | ---- | ------- | --- | --- |
| return         | quantity | / (double) |           | timeMs | *    | 1000d;  |     |     |
}
}
We won’t dwell on the StatsPrinter implementation details; suffice it to say that it’s a simple
helper class that tracks the total number of published records and may be called periodically to
outputtherateoverthelastsamplingperiod,aswellastheblendedaveragerate.Thiswillbeuseful
forcomparingtheproductionratewithandwithoutquotas.
Before we can run this publisher, we need to create the volume-test topic and assign the Write
operationtouseralice.Runthepairofcommandsbelow.
| $KAFKA_HOME/bin/kafka-topics.sh |         |                                      |     | \   |     |     |     |     |
| ------------------------------- | ------- | ------------------------------------ | --- | --- | --- | --- | --- | --- |
| --command-config                |         | $KAFKA_HOME/config/client.properties |     |     |     |     |     | \   |
| --bootstrap-server              |         | localhost:9094                       |     |     | \   |     |     |     |
| --create                        | --topic | volume-test                          |     | \   |     |     |     |     |
| --partitions                    |         | 1 --replication-factor               |     |     |     | 1   |     |     |

352
| Chapter17:                    | Quotas            |       |                                      |             |     |     |     |
| ----------------------------- | ----------------- | ----- | ------------------------------------ | ----------- | --- | --- | --- |
| $KAFKA_HOME/bin/kafka-acls.sh |                   |       |                                      | \           |     |     |     |
| --command-config              |                   |       | $KAFKA_HOME/config/client.properties |             |     |     | \   |
| --bootstrap-server            |                   |       | localhost:9094                       |             | \   |     |     |
| --add                         | --allow-principal |       |                                      | User:alice  | \   |     |     |
| --operation                   |                   | Write | --topic                              | volume-test |     |     |     |
RuntheQuotaProducerSamplesample,leavingitonforacoupleofminutes.
| Elapsed: | 1 s; | Rate: | current | 15 rec/s,    | average | 15 rec/s |       |
| -------- | ---- | ----- | ------- | ------------ | ------- | -------- | ----- |
| Elapsed: | 2 s; | Rate: | current | 1,162 rec/s, | average | 8 rec/s  |       |
| Elapsed: | 3 s; | Rate: | current | 3,770 rec/s, | average | 393      | rec/s |
| Elapsed: | 4 s; | Rate: | current | 4,948 rec/s, | average | 1,238    | rec/s |
| Elapsed: | 5 s; | Rate: | current | 5,026 rec/s, | average | 1,981    | rec/s |
| Elapsed: | 6 s; | Rate: | current | 4,851 rec/s, | average | 2,489    | rec/s |
| Elapsed: | 7 s; | Rate: | current | 4,751 rec/s, | average | 2,827    | rec/s |
| Elapsed: | 8 s; | Rate: | current | 7,537 rec/s, | average | 3,068    | rec/s |
...
| (omitted | for | brevity) |     |     |     |     |     |
| -------- | --- | -------- | --- | --- | --- | --- | --- |
...
Elapsed: 112 s; Rate: current 9,538 rec/s, average 8,593 rec/s
Elapsed: 113 s; Rate: current 9,145 rec/s, average 8,601 rec/s
Elapsed: 114 s; Rate: current 9,245 rec/s, average 8,606 rec/s
Elapsed: 115 s; Rate: current 9,547 rec/s, average 8,611 rec/s
Elapsed: 116 s; Rate: current 9,531 rec/s, average 8,620 rec/s
Elapsed: 117 s; Rate: current 9,384 rec/s, average 8,627 rec/s
Elapsed: 118 s; Rate: current 9,451 rec/s, average 8,634 rec/s
Elapsed: 119 s; Rate: current 8,779 rec/s, average 8,641 rec/s
Elapsed: 120 s; Rate: current 9,471 rec/s, average 8,642 rec/s
The publisher takes some time before settling into its maximum speed. In the example above, the
unconstrained publisher was operating at around 9,000 records per second, each record being just
over10kBbig.
Foroursecondrun,wewillapplyaconsumerratequotaonthecombinationoftheuseraliceand
thepumpclientID.Quotasareconfiguredusingthekafka-configs.shCLI,targettingeither(orboth)
usersandclientsentitytypes.
| $KAFKA_HOME/bin/kafka-configs.sh |     |                |                     | \             |              |     |     |
| -------------------------------- | --- | -------------- | ------------------- | ------------- | ------------ | --- | --- |
| --zookeeper                      |     | localhost:2181 |                     | --alter       | --add-config |     | \   |
| 'producer_byte_rate=100000'      |     |                |                     | \             |              |     |     |
| --entity-type                    |     |                | users --entity-name |               | alice \      |     |     |
| --entity-type                    |     |                | clients             | --entity-name | pump         |     |     |

353
| Chapter17: | Quotas          |             |                |     |     |
| ---------- | --------------- | ----------- | -------------- | --- | --- |
| Completed  | Updating config | for entity: | user-principal |     | ￿   |
| 'alice',   | client-id       | 'pump'.     |                |     |     |
RuntheQuotaProducerSampleagain.Withtheper-secondoutput,theeffectofthrottlingisapparent.
Kafka will allow for the occasional bursting of traffic, followed by a prolonged period throttling,
before relaxing again. While the throughput might not be smooth, changing abruptly as a result of
the intermittent penalties, the overall throughput quickly stabilises at 10 records per second (just
over100kB/s)andstaysthatwayfortheremainderoftherun.
| Elapsed: | 1 s; Rate: current  | 7 rec/s,   | average | 7 rec/s  |     |
| -------- | ------------------- | ---------- | ------- | -------- | --- |
| Elapsed: | 2 s; Rate: current  | 102 rec/s, | average | 3 rec/s  |     |
| Elapsed: | 3 s; Rate: current  | 1 rec/s,   | average | 35 rec/s |     |
| Elapsed: | 4 s; Rate: current  | 1 rec/s,   | average | 26 rec/s |     |
| Elapsed: | 5 s; Rate: current  | 1 rec/s,   | average | 21 rec/s |     |
| Elapsed: | 6 s; Rate: current  | 1 rec/s,   | average | 18 rec/s |     |
| Elapsed: | 7 s; Rate: current  | 1 rec/s,   | average | 15 rec/s |     |
| Elapsed: | 8 s; Rate: current  | 1 rec/s,   | average | 13 rec/s |     |
| Elapsed: | 9 s; Rate: current  | 1 rec/s,   | average | 12 rec/s |     |
| Elapsed: | 10 s; Rate: current | 1 rec/s,   | average | 11 rec/s |     |
| Elapsed: | 11 s; Rate: current | 1 rec/s,   | average | 10 rec/s |     |
| Elapsed: | 13 s; Rate: current | 1 rec/s,   | average | 9 rec/s  |     |
| Elapsed: | 14 s; Rate: current | 96 rec/s,  | average | 9 rec/s  |     |
| Elapsed: | 15 s; Rate: current | 2 rec/s,   | average | 14 rec/s |     |
| Elapsed: | 16 s; Rate: current | 1 rec/s,   | average | 13 rec/s |     |
| Elapsed: | 17 s; Rate: current | 1 rec/s,   | average | 13 rec/s |     |
| Elapsed: | 18 s; Rate: current | 1 rec/s,   | average | 12 rec/s |     |
| Elapsed: | 19 s; Rate: current | 1 rec/s,   | average | 11 rec/s |     |
| Elapsed: | 20 s; Rate: current | 1 rec/s,   | average | 11 rec/s |     |
| Elapsed: | 21 s; Rate: current | 1 rec/s,   | average | 10 rec/s |     |
| Elapsed: | 23 s; Rate: current | 1 rec/s,   | average | 10 rec/s |     |
| Elapsed: | 24 s; Rate: current | 1 rec/s,   | average | 9 rec/s  |     |
| Elapsed: | 25 s; Rate: current | 96 rec/s,  | average | 9 rec/s  |     |
| Elapsed: | 26 s; Rate: current | 2 rec/s,   | average | 12 rec/s |     |
| Elapsed: | 27 s; Rate: current | 1 rec/s,   | average | 12 rec/s |     |
| Elapsed: | 28 s; Rate: current | 1 rec/s,   | average | 12 rec/s |     |
| Elapsed: | 29 s; Rate: current | 1 rec/s,   | average | 11 rec/s |     |
| Elapsed: | 30 s; Rate: current | 1 rec/s,   | average | 11 rec/s |     |
| Elapsed: | 32 s; Rate: current | 1 rec/s,   | average | 10 rec/s |     |
| Elapsed: | 33 s; Rate: current | 1 rec/s,   | average | 10 rec/s |     |
| Elapsed: | 34 s; Rate: current | 1 rec/s,   | average | 10 rec/s |     |
| Elapsed: | 35 s; Rate: current | 1 rec/s,   | average | 10 rec/s |     |
| Elapsed: | 36 s; Rate: current | 95 rec/s,  | average | 9 rec/s  |     |
...

354
| Chapter17: Quotas |          |     |     |     |     |
| ----------------- | -------- | --- | --- | --- | --- |
| (omitted for      | brevity) |     |     |     |     |
...
| Elapsed: 114 | s; Rate: current | 93 rec/s, | average | 10 rec/s |     |
| ------------ | ---------------- | --------- | ------- | -------- | --- |
| Elapsed: 115 | s; Rate: current | 2 rec/s,  | average | 10 rec/s |     |
| Elapsed: 116 | s; Rate: current | 1 rec/s,  | average | 10 rec/s |     |
| Elapsed: 117 | s; Rate: current | 1 rec/s,  | average | 10 rec/s |     |
| Elapsed: 118 | s; Rate: current | 1 rec/s,  | average | 10 rec/s |     |
| Elapsed: 120 | s; Rate: current | 1 rec/s,  | average | 10 rec/s |     |
In addition to the producer_byte_rate quota used in the previous example, it is also possible to
specify a and a request_percentage. For example, the following sets all three
consumer_byte_rate
intheonecommand:
| producer_limit="producer_byte_rate=1024" |     |     | &&   | \   |     |
| ---------------------------------------- | --- | --- | ---- | --- | --- |
| consumer_limit="consumer_byte_rate=2048" |     |     | &&   | \   |     |
| request_limit="request_percentage=200"   |     |     | && \ |     |     |
limits="${producer_limit},${consumer_limit},${request_limit}" && \
| $KAFKA_HOME/bin/kafka-configs.sh |                     | \       |              |     |           |
| -------------------------------- | ------------------- | ------- | ------------ | --- | --------- |
| --zookeeper                      | localhost:2181      | --alter | --add-config |     | $limits \ |
| --entity-type                    | users --entity-name |         | alice        |     |           |
Similarly,itispossibletoremovemultiplelimitswithonecommand:
| $KAFKA_HOME/bin/kafka-configs.sh |                | \       |                 |     |     |
| -------------------------------- | -------------- | ------- | --------------- | --- | --- |
| --zookeeper                      | localhost:2181 | --alter | --delete-config |     | \   |
"producer_byte_rate,consumer_byte_rate,request_percentage" \
| --entity-type | users --entity-name |     | alice |     |     |
| ------------- | ------------------- | --- | ----- | --- | --- |
Tolistthelimits,runkafka-configs.shwiththe--describeflag:
| $KAFKA_HOME/bin/kafka-configs.sh |                | \          |     |     |     |
| -------------------------------- | -------------- | ---------- | --- | --- | --- |
| --zookeeper                      | localhost:2181 | --describe | \   |     |     |
| --entity-type                    | users          |            |     |     |     |
This is where kafka-configs.sh might get a little unintuitive. Listing the configuration with
--entity-type users brings up those configuration entries that only feature a user, excluding any
entriesthatcontainboththeuserandtheclient.Therefore,ourexistingquotasetforacombination
of user alice and client ID pump will not be listed. To list quotas that apply to the combination of
usersandclients,passbothentitytypestothecommand:

355
| Chapter17:                       | Quotas         |                |               |           |        |     |     |
| -------------------------------- | -------------- | -------------- | ------------- | --------- | ------ | --- | --- |
| $KAFKA_HOME/bin/kafka-configs.sh |                |                | \             |           |        |     |     |
| --zookeeper                      |                | localhost:2181 | --describe    | \         |        |     |     |
| --entity-type                    |                | users          | --entity-type | clients   |        |     |     |
| Configs for                      | user-principal |                | 'alice',      | client-id | 'pump' | ￿   |     |
| are producer_byte_rate=100000    |                |                |               |           |        |     |     |
| Buffering                        |                | and timeouts   |               |           |        |     |     |
Anobservantreaderwouldhavepickedupaminordetailintheconfigurationoftheproducerclient,
whichdidnotappearinanypriorexamplesin thisbook.Specifically,thefollowingline:
.withCustomEntry(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 600_000);
This overrides the delivery.timeout.ms property from its default value of two minutes, setting it
to ten minutes. For the sake of an experiment, remove the setting and run the example again. Just
aftertwominutesintotherun,thefollowingerrorwillbeprintedtotheconsole.
| org.apache.kafka.common.errors.TimeoutException: |          |                      |     |        | Expiring |       | 1 ￿ |
| ------------------------------------------------ | -------- | -------------------- | --- | ------ | -------- | ----- | --- |
| record(s)                                        | for      | volume-test-0:120007 |     | ms has | passed   | since | ￿   |
| batch                                            | creation |                      |     |        |          |       |     |
To understand why this is happening, consider another client-side property buffer.memory, which
defaults to 33554432 (32 MiB). This property sets an upper bound on the amount of memory used
by the transmission buffer. If records are buffered faster than they can be delivered to the broker,
theproducerwilleventuallyblockwhenthebufferbecomesfull.
Given the size of each record is a smidgen over 10 kB, the number of records that can fit into the
bufferisjustunder3,355.Inotherwords,asufficientlyfastproducercanqueueupto3,355records
before the first record reaches the broker. Assuming a producer operating at full blast, the buffer
will be filled almost immediately after starting the producer. (Given there are no
Thread.sleep()
callsinthewhileloop,theproducerwillbegeneratingrecordsatthemaximumrate.)Atanaverage
throughputof10records/second,itwilltakethepublisher335secondstogetthroughonecomplete
allotment — which is just over five and a half minutes. With the default delivery timeout of two
minutes, it is hardly a surprise that the error appears just after the 120-second mark. The delivery
timeout was set to ten minutes to allow for sufficient slack over the minimum timeout, ensuring
thatthetimeoutdoesnotoccurunderanormaloperatingscenario.
Kafkaofferstwoobvioussolutionstotheproblemoftimeouts,wherethebufferchurntakeslonger
thanthedeliverytimeout.Wehavejustdiscussedthefirst—increasethetimeout.Thesecondisits
flipside—decreasethebuffersize.
ThesecondapproachisdemonstratedintheBufferedQuotaProducerSample,listedbelow.

356
| Chapter17: | Quotas |                     |     |     |     |     |     |     |
| ---------- | ------ | ------------------- | --- | --- | --- | --- | --- | --- |
| import     | static | java.lang.System.*; |     |     |     |     |     |     |
import org.apache.kafka.clients.producer.*;
| public | final  | class                | BufferedQuotaProducerSample |                  |                       |       | {   |     |
| ------ | ------ | -------------------- | --------------------------- | ---------------- | --------------------- | ----- | --- | --- |
| public | static |                      | void                        | main(String[]    |                       | args) |     |     |
|        | throws | InterruptedException |                             |                  |                       | {     |     |     |
|        | final  | var topic            |                             | = "volume-test"; |                       |       |     |     |
|        | final  | var config           |                             | = new            | ScramProducerConfig() |       |     |     |
.withBootstrapServers("localhost:9094")
.withUsername("alice")
.withPassword("alice-secret")
.withClientId("pump")
.withCustomEntry(ProducerConfig.BUFFER_MEMORY_CONFIG,
100_000);
|     | final | var props |     | = config.mapify(); |     |     |     |     |
| --- | ----- | --------- | --- | ------------------ | --- | --- | --- | --- |
try (var producer = new KafkaProducer<String, String>(props)) {
|     | final | var      | statsPrinter |                              | =   | new StatsPrinter(); |            |      |
| --- | ----- | -------- | ------------ | ---------------------------- | --- | ------------------- | ---------- | ---- |
|     | final | var      | key          | = "some_key";                |     |                     |            |      |
|     | final | var      | value        | = "some_value".repeat(1000); |     |                     |            |      |
|     | while | (true)   | {            |                              |     |                     |            |      |
|     | final | Callback |              | callback                     |     | = (metadata,        | exception) | -> { |
statsPrinter.accumulateRecord();
|     |     | if (exception |     | !=  | null) | exception.printStackTrace(); |     |     |
| --- | --- | ------------- | --- | --- | ----- | ---------------------------- | --- | --- |
};
|     | final | var                   | record |     | = new      | ProducerRecord<>(topic, |     | key, value); |
| --- | ----- | --------------------- | ------ | --- | ---------- | ----------------------- | --- | ------------ |
|     | final | var                   | tookMs |     | = timed(() | -> {                    |     |              |
|     |       | producer.send(record, |        |     |            | callback);              |     |              |
});
|     | out.format("Blocked |     |     |     | for | %,d ms%n", | tookMs); |     |
| --- | ------------------- | --- | --- | --- | --- | ---------- | -------- | --- |
statsPrinter.maybePrintStats();
}
}
}
| private |       | static    | long | timed(Runnable                |     | task) | {   |     |
| ------- | ----- | --------- | ---- | ----------------------------- | --- | ----- | --- | --- |
|         | final | var start |      | = System.currentTimeMillis(); |     |       |     |     |
task.run();

357
| Chapter17: | Quotas                     |     |          |     |
| ---------- | -------------------------- | --- | -------- | --- |
| return     | System.currentTimeMillis() |     | - start; |     |
}
}
ThefirstchangeistheremovaloftheProducerConfig.DELIVERY_TIMEOUT_MS_CONFIGproperty,and
the use of the ProducerConfig.BUFFER_MEMORY_CONFIG property in its place, set to 100 kB. This
enables the application to fit just under 10 records in the buffer, before the latter results in the
| blockingof | Producer.send(). |     |     |     |
| ---------- | ---------------- | --- | --- | --- |
Thesecondchangeismostlyforillustrativepurposes:Theexamplewasenhancedwithtimingcode
to show the effect of blocking in thesend() method. Every call to send() is followed by a printout
ofthenumberofmillisecondsthatthemethodblockedfor.
| Blocked | for 430 ms |     |     |     |
| ------- | ---------- | --- | --- | --- |
| Blocked | for 1 ms   |     |     |     |
| Blocked | for 0 ms   |     |     |     |
| Blocked | for 0 ms   |     |     |     |
...
| (omitted | for brevity) |     |     |     |
| -------- | ------------ | --- | --- | --- |
...
| Blocked  | for 0 ms            |           |            |          |
| -------- | ------------------- | --------- | ---------- | -------- |
| Blocked  | for 28 ms           |           |            |          |
| Blocked  | for 98 ms           |           |            |          |
| Blocked  | for 102 ms          |           |            |          |
| Elapsed: | 1 s; Rate: current  | 97 rec/s, | average    | 97 rec/s |
| Blocked  | for 1,103 ms        |           |            |          |
| Elapsed: | 2 s; Rate: current  | 4 rec/s,  | average 40 | rec/s    |
| Blocked  | for 1,099 ms        |           |            |          |
| Elapsed: | 3 s; Rate: current  | 1 rec/s,  | average 30 | rec/s    |
| Blocked  | for 1,103 ms        |           |            |          |
| Elapsed: | 4 s; Rate: current  | 1 rec/s,  | average 23 | rec/s    |
| Blocked  | for 1,100 ms        |           |            |          |
| Elapsed: | 5 s; Rate: current  | 1 rec/s,  | average 19 | rec/s    |
| Blocked  | for 1,097 ms        |           |            |          |
| Elapsed: | 7 s; Rate: current  | 1 rec/s,  | average 16 | rec/s    |
| Blocked  | for 1,104 ms        |           |            |          |
| Elapsed: | 8 s; Rate: current  | 1 rec/s,  | average 14 | rec/s    |
| Blocked  | for 1,097 ms        |           |            |          |
| Elapsed: | 9 s; Rate: current  | 1 rec/s,  | average 13 | rec/s    |
| Blocked  | for 1,105 ms        |           |            |          |
| Elapsed: | 10 s; Rate: current | 1 rec/s,  | average    | 11 rec/s |
| Blocked  | for 1,100 ms        |           |            |          |
| Elapsed: | 11 s; Rate: current | 1 rec/s,  | average    | 10 rec/s |

358
| Chapter17:  | Quotas              |          |         |         |
| ----------- | ------------------- | -------- | ------- | ------- |
| Blocked for | 1,099 ms            |          |         |         |
| Elapsed:    | 12 s; Rate: current | 1 rec/s, | average | 9 rec/s |
| Blocked for | 1 ms                |          |         |         |
| Blocked for | 1 ms                |          |         |         |
| Blocked for | 1 ms                |          |         |         |
| Blocked for | 1 ms                |          |         |         |
...
| (omitted | for brevity) |     |     |     |
| -------- | ------------ | --- | --- | --- |
...
| Blocked for | 0 ms                |           |         |          |
| ----------- | ------------------- | --------- | ------- | -------- |
| Blocked for | 1 ms                |           |         |          |
| Blocked for | 1 ms                |           |         |          |
| Blocked for | 50 ms               |           |         |          |
| Blocked for | 1,097 ms            |           |         |          |
| Elapsed:    | 13 s; Rate: current | 82 rec/s, | average | 9 rec/s  |
| Blocked for | 1,103 ms            |           |         |          |
| Elapsed:    | 14 s; Rate: current | 1 rec/s,  | average | 15 rec/s |
| Blocked for | 1,106 ms            |           |         |          |
| Elapsed:    | 16 s; Rate: current | 1 rec/s,  | average | 14 rec/s |
| Blocked for | 1,097 ms            |           |         |          |
| Elapsed:    | 17 s; Rate: current | 1 rec/s,  | average | 13 rec/s |
| Blocked for | 1,097 ms            |           |         |          |
| Elapsed:    | 18 s; Rate: current | 1 rec/s,  | average | 12 rec/s |
| Blocked for | 1,104 ms            |           |         |          |
| Elapsed:    | 19 s; Rate: current | 1 rec/s,  | average | 12 rec/s |
| Blocked for | 1,097 ms            |           |         |          |
| Elapsed:    | 20 s; Rate: current | 1 rec/s,  | average | 11 rec/s |
| Blocked for | 1,103 ms            |           |         |          |
| Elapsed:    | 21 s; Rate: current | 1 rec/s,  | average | 11 rec/s |
| Blocked for | 1,103 ms            |           |         |          |
| Elapsed:    | 22 s; Rate: current | 1 rec/s,  | average | 10 rec/s |
| Blocked for | 1,097 ms            |           |         |          |
| Elapsed:    | 23 s; Rate: current | 1 rec/s,  | average | 10 rec/s |
| Blocked for | 2 ms                |           |         |          |
| Blocked for | 0 ms                |           |         |          |
| Blocked for | 0 ms                |           |         |          |
| Blocked for | 0 ms                |           |         |          |
...
| (omitted | for brevity) |     |     |     |
| -------- | ------------ | --- | --- | --- |
...
There’s an initial blocking of just over 400 ms, spent waiting on the cluster metadata. Once the
metadata has been retrieved, subsequent send() methods are non-blocking, as the buffer is being

Chapter17: Quotas 359
drained at the maximum speed that the broker is capable of. After around a hundred or so records,
the quota limits come into effect and impose an artificial delay on the producer. At this point the
bufferwillfilluprapidly,causingsubsequentblocking.Recordsarebeingacknowledgedatarateof
approximately one every second, which reflects on the blocking time. After the penalty wears off,
theproducerburstsagainforanother100(approximately)records—againleadingtoafree-flowing
send().Thiscyclerepeatsroughlyevery100records.
Sensing quota enforcement
One of the well-known challenges of Kafka’s quota implementation is the lack of explicit commu-
nication of the quota’s enforcement from the broker to the offending (in a manner of speaking)
client. Recall, a quota may be breached by a well-behaved client; the cause being none other than
an attempt to produce or consume at a rate greater than what the broker is willing to support. The
delay introduced by the broker is largely driven by a combination of two intentions: maintaining
compatibility with older client versions and simplifying the client implementation, which remains
agnostictothegoings-oninthebroker.
The drawback of Kafka’s implementation of quotas is that the client observes what it believes is
degraded performance. In the more extreme cases, when the time penalty is substantial or the
number of backlogged records is high, the client will begin to time out. This can result in the
compounding of retry attempts, similar to the effects of acongestive collapse, discussed in Chapter
12: Batching and Compression. What Kafka lacks is a mechanism for signalling to the client that it
iseitherapproachingthelimitofthequotaorisinbreach,sothattheclientcanactatitsdiscretion
(independentofbroker-sideenforcement).
Theconventionalwayofsolvingthisproblem,aswehaveseenearlier,istolimitthebuffersizeusing
thebuffer.memoryproperty—creatingbackpressureontheclient.Thisworkswellinthemajorityof
cases,particularlywhendealingwithnon-time-sensitivedata.Ontheotherhand,iftheclientneeds
toknowwhetheritisbeingthrottled,thereisnoout-of-the-boxmechanismtodeterminethis,noris
thereawayofprobingtheamountofavailablebuffercapacity.KafkaoffersnoProducer.trySend()
methodthatpublishesarecordconditionalontheavailablechannelcapacity.
Onescenariowhereblockingbehaviourisundesirableiswhereaproducerneedstopublishhetero-
geneous data with mixed quality of service characteristics onto a single topic. The client might be
collecting data from multiple sources, but there may be a clear need to emit certain types of events
overothers.Forexample,theclientmayneedtoperiodicallytransmitcriticalstatusupdates.Atthe
same time, it may transmit other metrics which are useful to downstream consumers, but it is not
criticalthatthesemetricsaredeliveredinatimelymanner.(Thisscenarioassumesthat,forwhatever
reason, it is not feasible to separate the data into multiple streams with separate QoS guarantees;
otherwise,thisisaddressedbytheuseoftopic-levelquotas.)
Where blocking behaviour is unacceptable, the client may choose to track the in-flight records and
correlatethesetothereceivedacknowledgements,keepinga‘pendingrecords’counter.Thecounter
will increase as a result of throttling, up to the number of buffered records, and will revert to a

Chapter17: Quotas 360
near-zero value when the limit is lifted. By observing this counter, the application can infer the
approximatelikelihoodofaqueuedrecordbeingsentimmediately.Alternatively,thecountermaybe
replacedwiththetimestampofthemostrecentunacknowledgedrecord.(Thetimestampiscleared
when the last record has been acknowledged). By comparing the wall clock to this timestamp, the
application is again able to infer the presence of throttling and act accordingly. Both methods are
probabilistic;thereisnocategoricalwaytodeterminewhetherathrottleisinforce,orwhetherthe
delayed acknowledgements are a result of genuine congestion. At any rate, both are indicative of
degradedbehaviour.
Tuning the duration and number of sampling windows
As it was previously stated, the quota enforcement algorithm represents a sliding window T
comprising N samples wide (given by quota.window.num), each being S seconds long (given by
quota.window.size.seconds). By default, these values are 11 and 1 respectively, implying that the
sliding window spans at most eleven seconds of observed utilisation. In reality, the calculation of
the window size subtracts the current time from the oldest in the set of retained samples. When
the wall clock approaches the boundary of the current period, the window size T approaches the
product of N and S. However, when the current sample rolls over to the next, the oldest sample
usedinthepreviouscalculationisdropped,leavingthenextoldestsample—S secondslaterthanits
predecessor. This creates a sort of a snapback effect, where the T instantaneously goes from being
N×S secondswide,to(N–1)×S.
Lookingattheoutputofthemostrecentexample,thereisaclearspikeofthroughput,followedbya
periodofsuppressed(butnotentirelyquiesced)activity,whichseemstorecurinapredictablecycle.
To understand the reason for this comb-like shape, consider what happens at various points in the
client-brokerinteraction.
Given a window of 11 seconds at its peak width and a set rate limit of 100 kB/s, the client should,
in theory, be allowed to produce up to 1,100 kB of data in any given eleven-second window. The
window is important; although the quota is stated in bytes per second, the enforcement algorithm
operatesintermsofthecurrentwindowsize—varyingbetweentenandelevenseconds.Following
theinitialmetadataretrieval,theclientisfreetoproduceavolumeoftrafficthatdoesnotbreachthe
threshold within the observed window. At full speed, this allowance will be exhausted in a matter
ofmilliseconds.Infact,theclientwilloverproducebysomemarginbeforethelimitkicksin,asthe
delayisafunctionofthedifferencebetweentheobservedutilisationandtheallowedutilisation.
Atthispoint,theclientisbeingdelayedbyasmallamountoftime,astheextentofoverproduction
is quite minimal. Delayed does not mean stopped; the client will transmit the next batch after the
broker’s response is received. The batch size is given by the batch.size client property, which
defaults to 16 KiB — just enough to fit one record. The client will be gently throttled until the
conclusion of the first sampling period, where T approaches 11 seconds. By this time, the client
wouldhaveproducedjustover1,100kBofdata.
At the commencement of the second period, the value of T will abruptly snap back to 10 seconds.

Chapter17: Quotas 361
Where the client had previously only barely violated the quota, the discontinuity in T now means
thatthequotahasbeenbreachedbyagreaterextent.Specifically,thenewdelayD isgivenby:
D=10×(1100–1000)/1000
With the updated calculation, the delay is now one second. This is a 10% breach for a ten-second
window,penalisedbythedurationofonesamplingperiod.Inotherwords,thenextrequestwillbe
delayed by one second before the broker responds. Incidentally, this amount of delay lines up with
the commencement of the next sampling period, at which point the client will again be in breach
of the quota by 10%, leading to another one-second penalty, and so on. The client will settle into a
rhythmwhereitmanagestosendroughly10kB/s.Thisisillustratedinthediagrambelow,showing
theamountofdatatransferredwithineachsamplingperiod.
Slidingwindow—firstcycle
Theillustrationisonlyaroughguide,beingasimplifiedapproximation;theareaunderthe
realbandwidthcurvewillnotbesoevenlyrationedamongthesamplingperiods.Still,it’s
afairindicationofwhathappens.
Upon the conclusion of the tenth second, we will have one sampling period with a high average
utilisation value, followed by ten periods with relatively low utilisation values. When the 12ʰ
samplingperiodcommencesonthe11ʰsecond,theslidingwindowwillhaveadvancedtothepoint
wherethefirstsamplingperiodisnolongerinitsscope.Atthispoint,theaggregateutilisationacross

Chapter17: Quotas 362
thewindowwillsuddenlydroptoaround100kB—900kBshortoftheallottedmaximumforaten-
second window. This explains the next traffic spike — the client takes the opportunity to saturate
the broker with free-flowing traffic, lasting for just as long as the quota is breached once again, at
whichpointthetrafficismoderatedwithaseriesofsmalldelays.Atthecommencementofthe13ʰ
sampling period, the window will snap back from 11 to 10 seconds, leaving the client in breach of
the quota by 10%, slapping down a one-second penalty. It’s easy to how this pattern repeats when
thesecondpeakdropsoffthescopeoftheslidingwindowonthe22ⁿsecond.Note,thesecondpeak
isapproximately10kBshorterthanthefirstpeak,withallsubsequentpeaksbeingthesameheight.
Theeffectofdroppingthefirstsamplefromtheslidingwindow,markingthecommencementofthe
secondcycleisdepictedbelow.
Slidingwindow—secondcycle
The final diagram depicts the aggregate utilisation over a series of sampling periods, covering
two complete cycles. The diagram calls out the critical inflexion points that were discussed in the
narrativeabove.

Chapter17: Quotas 363
Aggregateutilisation
Recalling the earlier-stated formula relating the delay to the duty cycle, one might assume that the
cyclic shape of the bandwidth and the duty cycle are somehow equivalent or at least comparable.
Curiously,thisisnotthecase:Thedutycyclerelatestoanindividualpenaltyandrepresentstheratio
of the request time to the duration of one request-response cycle. It can be thought of as operating
at the micro level. By comparison, the macro-level cycle that is trivially observable — the one we
have just been discussing — is a function of the length of the sliding window and the duration of
eachsamplingperiod.
This brings us to the next point: how do the quota.window.num and quota.window.size.seconds
properties(thevaluesN andS)affectthebehaviourofthesystem?
By increasing quota.window.num, the overall duration of the window T is increased, elevating the
tolerance for bursty traffic. In the example of the default window (ranging from ten to eleven
seconds), the system tolerated a burst of just over eleven times the prescribed limit. It did so at the
expense of traffic symmetry. Conversely, reducing the overall window duration leads to a flatter
traffic profile, having less tolerance for short periods of heightened activity. The reader may want
to try changing the quota.window.num setting in server.properties — increasing and decreasing
the duration of the window and rerunning the example on each occasion. The behaviour will be
confirmed.Andeitherway,thelong-termaveragethroughputwillremainthesame.
By increasing quota.window.size.seconds, the granularity of the sampling process is decreased,
thereby inflating the magnitude of the snapback effect. When the snapback occurs, the difference

Chapter17: Quotas 364
between the previous excess utilisation and the new excess utilisation is greater, leading to a
proportionally longer penalty delay. For example, when the N is 11 and S is 3 seconds, the size
of the window will vary between 30 seconds and 33 seconds at the two extremes of a sampling
period.Whenasnapbackoccurs,theextentofthedelaywillbethreeseconds(thevalueofS).
There is one ‘gotcha’ in this section worth mentioning. The discussion above depicts the
actualbehaviouroftheKafkabroker.Thecalculationofthewindowsizeandthesnapback
effect are not discussed in the official Kafka documentation, nor are they detailed in the
correspondingKIPs.Thedetailedexplanationoftheshapingbehaviourwasproducedasa
result of analysing the source code of the broker implementation. This implementation is
unwarranted and is subject to change without notice. For example, the maintainers of the
project may fix the snapback issue by using a constant value forT or by interpolating the
value of the oldest sampling period in proportion to the wall clock’s position in the most
recent sampling period. Either way, rectifying the snapback eliminates the corresponding
discontinuity, resulting in a smoother traffic shape. (More frequent, shorter lasting penal-
ties.)Ofcourse,thesnapbackisjustoneaspectoftheimplementationthatmightbealtered
withoutconflictingwiththedocumentation.Assuch,youshouldnotbuildyourapplication
to rely on a specific characteristic of the traffic shape when a quota is enforced. The only
aspectofthetrafficshapethatcanberelieduponistheaveragebandwidth.
Whiletheeffectofquota.window.numiswellunderstoodandthesettingmaybereasonablyalteredin
eitherdirection,theeffectof quota.window.size.secondsismorenuanced,subjecttoidiosyncratic
behaviour that may be changed. There is no compelling reason why the sample period should
be increased; if anything, keeping it low leads to less pronounced discontinuities in the resulting
throughput.Thedefaultvalueof 1isalreadyatitspermittedminimum.
Inthischapter,welookedatoneofKafka’smainmechanismsforfacilitatingmultitenancy.Quotas
support this capability by mitigating denial of service attacks, allowing for capacity planning and
addressingqualityofserviceconcerns.
Quotas may be set in terms of allowable network bandwidth and request rates, applying either
to a user principal or a client ID, or a combination of both. The enforcement algorithm works by
comparing the client’s resource utilisation to the set maximum, over a sliding window. Where the
utilisationexceedsthesetlimit,thebrokerdelaysitsresponses,forcingtheclienttoslowdown.
Finally, we looked at the parameters behind the quota enforcement algorithm and explored their
effectontheresultingtrafficshape.Therelationshipisnuanced,wheretheresultingcharacteristics
of the traffic profile are tightly coupled to the implementation and are difficult to infer. The more
dependable attribute to reason about is that widening the sliding window allows for increased
tolerancetoburstytraffic.
Insomeways,thequotamechanismisanextensionofthesecuritycontrolsdiscussedinChapter16:
Security; in other ways, it may be perceived as a standalone capability that bears merit in its own

Chapter17: Quotas 365
right. Collectively, the combination of encryption, authentication, authorization and quotas ensure
asafeandequitableoperatingenvironmentforallusersofaKafkacluster.

Chapter 18: Transactions
When discussing event stream processing application, one topic of conversation that invariably
comesupisthatofdeliveryguarantees.
To recount our journey thus far, Chapter 3: Architecture and Core Concepts has introduced the
conceptofdeliverysemantics—distinguishingbetweenat-most-onceandat-least-oncemodelsand
howeithercaneasilybeachievedinaKafkaconsumerbychangingtherelativeorderofprocessing
arecordandconfirmingitsoffsets.
Wesubsequentlylookedatthenotionsofidempotenceandexactly-oncedeliveryinChapter6:Design
Considerations—namely,theroleoftheclientapplicationinensuringthattheeffectsofprocessing
arecordarenotrepeatedifthesamerecordweretobeconsumedmultipletimes.
Finally,Chapter10:ClientConfigurationdemonstratedtheuseoftheenable.idempotenceproducer
propertytoensurethatrecordsarenotpersistedinduplicateorout-of-orderinthefaceofintermit-
tenterrors.
This chapter looks at one last control made available by Kafka — transactions. Transactions fill
certain gaps of idempotent consumers with respect to the side-effects of record processing, and
enable end-to-end, exactly-once transactional semantics across a series of loosely-coupled stream
processingstages.
Preamble
Beforewegettooinvolvedintounravellingthejoysoftransactionaleventprocessing,itis
worthtakingamomenttocontemplatetheextentoftheworkthathasgoneintoitsdesign
andconstruction,andacknowledgethecolossaleffortsofnumerouscontributorswhohave
made this possible. The KIP comprised around 60 individual work items and was planned
foroverthreeyears.Thefinaldesignwassubjecttoanine-monthperiodofpublicscrutiny
andevolvedsubstantiallyasaresultofcommunityfeedback.Thedesignwasoneofthemost
profoundchangestoKafkasinceitspublicrelease,deliveringinexcessof15,000linesofunit
testsalone.Crucially,theperformanceoverheadoftransactionswaskepttoaminimum—
subtracting an average of three to fiveper cent from the throughput of an equivalent non-
transactionalproducer.
Transactionsarrivedinrelease0.11.0.0,aspartofamuchlargerKIP-98⁴⁶.Thesignificanceofthismay
notbeimmediatelyapparent,butthereaderhasalreadywitnessedthisKIPinactioninChapter10:
⁴⁶https://cwiki.apache.org/confluence/x/ASD8Aw

Chapter18: Transactions 367
ClientConfiguration,andmostofourexampleshave,infact,utilisedthecapabilitiesintroducedby
thisKIP—namely,theenable.idempotenceproperty.That’scorrect,boththeidempotentproducer
and transactional messaging features are highly related and share a great deal in common. So in
effect,theforthcomingstudyoftransactionalmessagingmightbeseenasclosingoffthediscussions
startedmanychaptersago.
The rationale behind transactions
As it has been extensively discussed in the introductory chapters, the role of Kafka as an Event
StreamingPlatformistofacilitatethedistributionandprocessingofeventswithinabroader,Event-
DrivenArchitecture.
Event streaming systems can be visualised as a set of loosely coupled actors that form a directed
acyclicgraph(DAG),wherethenodesofthegraphareprocessesthatinteractwithKafkatopics,and
the edges are the topics themselves. The processes, also called stages, may act either as a producer
or a consumer, or a combination of both vis à vis one or more topics and client instances. A stage
may also interact with other resources outside of Kafka, such as databases, APIs, and so forth. A
reference model of a single stream processing stage and its neighbouring topics is depicted in the
diagrambelow.
Streamprocessingstage
Recall the discussion on exactly-once delivery in Chapter 6: Design Considerations. To quote from
thechapter:
Toachievethecovetedexactly-oncesemantics,consumersineventstreamingapplications
mustbeidempotent.Inotherwords,processingthesamerecordrepeatedlyshouldhaveno
net effect on the consumer ecosystem. If a record has no additive effects, the consumer
is inherently idempotent. […] Otherwise, the consumer must check whether a record

Chapter18: Transactions 368
has already been processed, and to what extent, prior to processing the record. The
combination of at-least-once delivery and consumer idempotence collectively leads to
exactly-oncesemantics.
The problem — duplicate records
The ability to check and selectively carry out an action works well with databases, given that just
about every database in existence supports the notion of a secondary index. (Put simply, a way of
efficientlyaccessingrecordsbymeansofsomepieceofinformationotherthanaprimarykey.)Kafka,
althoughoftenlikenedtoadatabase,doesnotsupportuser-definedsecondaryindexes.Theprimary
indexofarecordisthetopic-partition-offsettriple;inaddition,Kafkaallowsustoindexrecordsby
their timestamp for convenience. What Kafka lacks is the ability to nominate some attribute of a
recordthatmaybeusedtoefficientlyretrievetherecord,oratleastascertaintherecord’spresence.
Speaking of databases, Kafka was originally designed as a durable record store. Kafka’s
replication mechanism ensures that, with the appropriate configuration in place, once a
record is acknowledged on the broker, that record is persisted in a manner that survives a
broadrangeoffailures.Asidefromthedurability guarantee,Kafkadidnotaddressanyof
the other attributes ofACID (Atomicity, Consistency, Isolation, Durability). We will touch
onACIDagainshortly.
Withoutasecondaryindex,howcanaprocessingstageensurethatarecordhasnotbeenpublished,
for some input record that might be processed repeatedly as a result of at-least-once delivery? To
illustrate the problem, consider a simple stage that transforms an input stream to an output stream
usingastraightforwardmappingfunction:
Simplestreamprocessingexample

Chapter18: Transactions 369
To further distil the problem, let’s assume that a single ‘input’ record trivially maps to one ‘output’
record. Assume the input topic is a set of integers and the transform function simply squares
each integer before forwarding it on. Contrived as it may be, this example serves to demonstrate
the problem of attempting to apply idempotence to a storage medium that does not inherently
support it. The topic topology and the transform function are not important; what is important
is the fundamental property that there is exactly one output record corresponding to a processed
input record, despite the prevalent at-least-once behaviour. And this property must be upheld in
the presence of multiple instances of the transformation stage, operating within an encompassing
consumergroup.
Under a conventional consume-transform-produce model, the consumer side of a stage will read
a record from the input topic, apply a transformation, and publish a corresponding record on
the output topic, via a dedicated producer client. Once it receives an acknowledgement from the
leaderthattherecordhasbeendurablypersisted,itwillcommittheoffsetsoftheinputrecord.For
simplicity, assume the producer is operating with idempotence enabled, being the recommended
modeformostapplications.
Consider the types of failures one might encounter with the model above. Putting aside buggy and
misbehaving applications (and more complex Byzantine faults), at minimum, we would need to
account for the failure of brokers, the abrupt termination of the transform process, and network
connectivityissues.Ontheconsumerside,networkandbrokerfailurecanbedealtwithbytheretry
mechanismembeddedintoConsumer.poll().Ontheproducerend,theuseofidempotencewilldeal
withbrokerandnetworkfailures,providedthefailuredoesnotlastlongerthanthedeliverytimeout.
This leaves us with the most taxing failure scenario: process failure. The outcome will depend on
theexactpointatwhichtheprocessfails.Considertheoptions:
1. Failurebeforeconsumption.
2. Failureafterconsumption,butbeforethetransform.
3. Failurefollowingthetransform,butbeforepublishing.
4. Failureafterpublishing,butbeforeconfirmingtheoffsets.
5. Failureaftercommittingtheoffsets.
Case#1isbenign:nothinghasyethappenedandsotherecoveryistrivial.Case#2islike#1,because
Kafka(unlikeitsmessagequeuingcounterparts)doesnotupdatethestateofthetopicafterarecord
hasbeenread.Assumingthetransformoperationisapurefunctionthatentailsnostatechangeofits
own, case #3 is also a non-issue; the recovering process will replay the consumption and transform
steps.Case#4iswhereitgetsinteresting,beingthefirstpointwherethefailureoccursafterastate
change. The recovering process, having no awareness of the prior publish step will repeat all steps,
resulting in two output records for the same input record. Finally, case #5 is again benign, as the
committingoftheoffsetsmarksthecompletionoftheprocesscycle.
Analysis of the above shows that, in the worst case, multiple output records may be written for a
single input record; in the best case, one output record will be written. Assuming the input record
is eventually processed, resulting in its offsets committed, at no point after the commit will there

Chapter18: Transactions 370
be an observable absence of an output record. And that is the essence of at-least-once delivery. All
priormaterialinthisbookhasconsistentlyguidedthereadertowardsthisdirection.
Wecouldhaveeasilyexpandedupontheexampleabovetoincludemultipleinputandoutput
topics, and more complex transformation logic with possible side-effects. Fundamentally,
thiswouldnothavechangedthebasicfailurescenarios.
This basic formula for at-least-once delivery extends to any other stage that might be downstream
of the one being considered. Provided an application-level identifier is included in every record
that helps the consumers correlate duplicates, and every consumer can behave idempotently with
respect to the processed records, the entireprocessing graph will exhibitexactly-oncebehaviour in
alimitedsense.Specifically,theaspectsofrecordprocessingthatrelatetodatabasesandidempotent
API calls are covered. Conversely, message queues and Kafka topics that don’t provide application-
level deduplication are excluded from the exactly-once guarantee; duplicates will occur, and it is
somethingwehavecometoaccept.
Idempotence requires collaboration not only from the consuming process, but from all
downstreamresourcesthatsaidprocessmanipulates.
Presumably,message-orientedmiddlewareisatransportmechanismthatfacilitatesthedistribution
of messages between endpoints or the replication of events, whichever paradigm one chooses to
accept; it is not the definitive source of truth, in that it does not attempt to replace a database. It
wouldbe unacceptable for a database to return multiple rows for the same entity.But Kafka,being
partofthetransportapparatus,somehowgetsawaywiththis—relyingontheapplicationtoperform
deduplication.
What if Kafka was used as primary storage — the proverbial ‘source of truth’; for example, acting
intheroleofaneventstoreinaneventsourcingsystem?Itwouldhardlybeacceptabletohavetwo
recordsrepresentingthesamelogicalevent.Thisisnottosaythataconsumercouldnotbedesigned
tocopewithduplicates,butitwouldbefairtoassumethatduplicateswouldbeundesirableinmost
cases—irritating,toputitmildly.
The solution — transactions
The transactional messaging capability introduced in release 0.11.0.0 strengthens Kafka’s delivery
semantics. Namely, it introduces limited atomicity, consistency and isolation guarantees on top of
the existing durability pledge, for a combined ACID experience that is characteristic of relational
databases(andoccasionallyofNoSQL).Specifically—
• Atomicity—ensuresthat,foragroupofrecordspublishedwithinanencompassingtransaction
scope, either all records are visibly persisted to their respective logs, or none are persisted. In
other words, the transaction either succeeds or fails as a whole. The logs, in this case, are not
limitedtoasinglepartition;transactionsmayspanmultipletopicsandpartitions.

Chapter18: Transactions 371
• Consistency—alogicalcorollaryofatomicity,consistencyensuresthattheclustertransitions
from one valid state to another, without violating any invariants in between. In our context,
consistencyspeakstothecorrelationbetweenrecordsoninputandoutputtopicsofaprocessing
stage; an input record must result in an output record once consumed, or it must not be
consumedatall.
• Isolation — ensures that concurrent execution of transactions leaves the system in the same
state that would have been obtained if the transactions were executed sequentially. In simple
terms, the effects of a transaction cannot be externally visible until it commits. (In reality, the
visibilityofin-flighttransactionsdependsontheconfiguredisolationleveloftheconsumer.)
Transactions under the hood
Role of the transaction coordinator
Chapter10:ClientConfigurationhadbrieflytouchedonsomeoftheinnerworkingsofidempotent
producers. At the heart of the implementation is a unique producer ID (PID) that is assigned by
a transaction coordinator for the duration of the producer’s session. A transaction coordinator is
a module running within a broker process, servicing idempotent and transactional producers —
akin to the group coordinator used by consumers to manage group membership. The transaction
coordinator is responsible for issuing PIDs and managing transaction state. The producer starts by
issuinganInitProducerIdrequesttotheappropriatetransactioncoordinator(theremaybemultiple
coordinators); the latter replies with a unique PID that is valid for the duration of the producer’s
session.
Transactional messaging builds upon this infrastructure by increasing the lifetime of a producer’s
PID such that it survives a single producer session. This is achieved by specifying an optional
transactional.id property on the producer. If the property is not set, the producer will be is-
sued a new PID on each request; otherwise, the transaction coordinator will associate the issued
PID with the transactional ID and maintain this association for the duration specified in the
transactional.id.expiration.ms broker property, which defaults to 604800000 (one week). The
transactionalID→PIDmappingincludestheepochcorrespondingtothepointwhentheassociation
was last updated. The epoch acts as a fencing mechanism, blocking zombie processes that have
been displaced by a newer PID assignment. (A zombie producer might attempt to use their PID
to manipulate an in-flight transaction or initiate a new one; however, they will act using an older
epochnumber,whichgivesthemaway.)AProducerFencedExceptionisthrownwhentheproducer
attemptstomanipulateatransactionthathasbeenfencedoff.

Chapter18: Transactions 372
Theloadbalancingoftransactioncoordinatordutiesisdonebyassigningthepartitionsof
theinternal__transaction_statetopictothebrokersinthecluster,suchthattheassigned
ownerofanygivenpartitionbecomesthenotionalcoordinatorforthatpartition’sindex.The
identificationofasuitabletransactioncoordinatorisperformedbyhashingthetransactional
ID, modulo the partition index — arriving at the coordinator in charge of the partition.
By piggybacking on an existing leadership election process, Kafka conveniently avoids
yet another arbitration process to elect transaction coordinators. Also, the management of
transactions is divided approximately equally across all the brokers in the cluster, which
allowsthetransactionthroughputtoscalebyincreasingthenumberofbrokers.
Producer API enhancements
TransactionalmessagingaddsseveralmethodstotheProducerAPI,namely:
• initTransactions(): Initialises the transactional subsystem by obtaining the PID and epoch
number,andfinalisingallpriortransactionsforthegiventransactionalIDwiththetransaction
coordinator.(Andindoingso,fencinganyzombiesforthepreviousepoch.)Thismethodshould
onlybecalledonceforaproducersession,andmustbecalledpriortoanyofthemethodsbelow.
Onceinitialised,theproducerwillbetransitionedtotheREADYstate.
• beginTransaction(): Demarcates the start of transaction scope on the producer. Performs a
seriesoflocalcheckstoensurethattheclientisoperatingintransactionalmodeandtransitions
the producer to the IN_TRANSACTION state. This method must be invoked before any of the
methodsbelow.
• sendOffsetsToTransaction():Incorporatesthegivensetofconsumer-sideper-topic-partition
offsets into the scope of the current transaction and forwards them to the group coordinator
of the supplied consumer group. The offsets will come into effect when the transaction
subsequentlycommits.
• commitTransaction(): Flushes any unsent records and commits the current transaction by
instructingthetransactioncoordinatortodoso.ThisplacestheproducerintotheCOMMITTING_-
TRANSACTION state, after which it is not possible to invoke any other method. (It is possible to
retrycommitTransaction()inthisstate.)
• abortTransaction(): Discards any pending records for the current transaction and instructs
thetransactioncoordinatortoabortthetransaction.ThisplacestheproducerintotheABORTING_-
TRANSACTION state, from which point no other transactional methods may be invoked (except
forretryingabortTransaction()).
Transactional messaging in Kafka is largely a producer-side concern. This makes sense, astransac-
tionscontroltheatomicityofwriteoperations.ThereadermightrecallfromChapter3:Architecture
and Core Concepts, Kafka employs a recursive approach to managing committed offsets, utilising
theinternal__consumer_offsetstopictopersistandtrackoffsetsforconsumergroups.Becausethe
committing of an offset is modelled as a write, it is necessary for consumers to piggyback on an
existing transaction scope managed by a producer. In other words, for a typical processing stage

Chapter18: Transactions 373
thatcomprisesbothaconsumerclientandaproducerclient,theconsumermustforwarditsoffsets
to the producer, rather than use its native commitSync() or commitAsync() API. On top of this,
offset auto-commit must be disabled on the consumer; the use of transactions demands manual
offset committing. There is one other aspect of transactional behaviour that must be accounted for
—isolation —whichwillbecoveredshortly.
With transactions enabled, each iteration of a typical consume-transform-produce loop resembles
thefollowing:
producer.beginTransaction();
try {
// process records ...
producer.send(...);
// ...
producer.sendOffsetsToTransaction(...);
producer.commitTransaction();
} catch (KafkaException e) {
producer.abortTransaction();
throw e;
}
Assigning a transactional ID
Andnowwearriveatthecruxofthechallenge—thechoiceofthetransactionalID.Thisiswidely
consideredasamongthemostconfusingandperplexingaspectsoftransactionmanagementinKafka.
Overwhelmingly, the questions in various forums that relate to transactions are posed around the
transactionalID.
The reason this is so hard is that the transactional ID must survive producer sessions. It is also
usedasafencingmechanism,whichmeansthattheremustonlybeone(legitimate)producerusing
the transactional ID at any given time. This is further complicated by the fact that the number of
processesinastageisnotaconstant,andmayscaleinandoutdependingonload,aswellasfactors
outsideofourcontrol,suchasprocessrestarts,networkpartitions,andsoon.
Before revealing the recommended transactional ID assignment scheme, it is instructive to explore
ahandfulofoptionstoappreciatethecomplexityoftheproblem.
Startingwiththesimplest—acommontransactionalIDsharedbyallprocesses.Wecandismissthis
option hastily, as it creates an irreconcilable fencing issue for all but the most recent processes that
presentthesharedtransactionalID.AstheassignmentofPIDsisassociatedwithanepochnumber,
the last producer to call initTransactions() will acquire the highest epoch number — turning its
peersintozombies(fromtheperspectiveofthetransactioncoordinator).
Crossingtotheoppositeextreme—arandomtransactionalIDwithsufficientcollision-resistanceto
guaranteeitsuniqueness.OnemightuseUUIDs(versions3,4and5)orsomeotheruniquequantity.

Chapter18: Transactions 374
Thisclearlyavoidstheissueoffencinglegitimatepeerprocesses,butindoingsoiteffectivelydisables
zombiefencingaltogether.Considertheimplicationsbyrevertingtoourearlierexampleofasimple
processingstage,surroundedbyapairofinputandoutputtopics.AssumetwoprocessesP0 andP1
contend for the assignment of three partitions in the input topic I0, I1 and I2 — transforming the
input and publishing records to some partitions in the output topic. The output partitions, as will
shortlybecomeapparent,areirrelevant—itisonlytheinputpartitions thatmatterforfencing.
Simplestreamprocessingexample
Initially, P0 is assigned I0 and I1, and P1 is assigned I2. Both processes are operating with random
transactionalIDs.Atsomepoint,anissueonP1blocksitsprogressthroughI2,eventuallyresulting
in failure detection on the group coordinator, followed by partition reassignment. Thereafter, P0
becomestheassigneeofallpartitionsintheinputtopic;P1ispresumeddead.
What if P1 had in-flight transactions at the time of reassignment? The transaction coordinator will
provide for up to the value of transactional.id.expiration.ms to honour a pending transaction.
Thissettingdefaultsto3600000(onehour).Duringthistime,producerswillbeallowedtowritetothe
affectedpartitions,butnorecordthatappearsafterthepartialtransactionontheaffectedpartitions
will be delivered to consumers operating under the read_committed assurance level. That amount
ofdowntimeontheconsumerisgenerallyunacceptableinmoststreamprocessingapplications.Of
course, the expiration time could be wound down from its default value, but one must be careful
not to interrupt the normal operation of transactional producers by setting an overly aggressive
expirationtime.Atanyrate,someappreciableamountofdowntimecannotbeavoided.
AnotherproblemwiththisapproachiswhathappensonP1ifitsuddenlyresumesoperatingwithin
thetransactionexpiryperiod.Beingexcludedfromitsconsumergroup,itwillnotbeabletoconsume
further records, but any in-flight records (returned from a previous poll) are still fair game from
its perspective. It may attempt to publish more records on the output topic within the existing
transactionscope—anoperationthatwillbeacceptedbythetransactioncoordinator(whichknows
nothing about the consumer-side status of the process). In the worst case, the output topic will
contain duplicates that have been emitted by the two processes, corresponding to the same input

Chapter18: Transactions 375
record.
Wecantryourhandatvariousothertransactionalassignmentschemes,buttheresultwillinvariably
bethesame—eitherunduefencingoflegitimateproducersorbitterdefeatatthehandsofzombies.
Theverypointoffencingistoeliminatethesesortsofscenarios,butforfencingtofunctioncorrectly,
theproducer’ssessionmustsomehowrelatebacktotheinputsource.Thisiswhereaconfessionmust
be made: the issue isn’t just with the transactional ID assignment scheme, but with the internal
architectureoftheprocessingstage.Intheoriginalexample,weusedapairofclients—aconsumer
and a producer — in a back-to-back arrangement. A process would read from any of the assigned
partitionsintheinputset,viaasingleconsumerinstance.Itwouldthenpublisharecord(ormultiple
records)toanarbitraryoutputpartitionviasingle,long-livedproducerinstance.
BeforecontinuingwiththetransactionalIDconundrum,weneedtoreworktheinternalarchitecture
oftheprocessingstage.Letthesingletonconsumerbe,butreplacethesingletonproducerwithacol-
lectionofproducers—oneforeachassignedpartitionintheinputset.Theassignmentofpartitionsis
subjecttochange,whichcanbeaccommodatedbyregisteringaConsumerRebalanceListener.When
a partition is assigned, create and initialise a corresponding producer instance; when a partition
is revoked, dispose of the producer instance. When publishing an output record, always use the
producer instance that corresponds to the partition index of the input record. The diagram below
illustratesthisinternaltopologicalarrangement.
Pinnedproducers
The number of partitions in the input topic(s) might be in the hundreds or thousands;
spawningaproducerinstanceforeachconceivabletopic-partitionpairiswasteful,entailing
apotentiallysignificantconnectionestablishmentoverhead,inadditiontoutilisingmemory
(forbuffers),filehandlesandOS-levelthreads.Assuch,reducingtheproducerclientstothe
set of assigned partitions is the preferred approach. Furthermore, the producer instances
maybelazilyinitialised—amortisingtheoverheadfromthepointofpartitionassignment
tothepointsofthefirstreadfromeachinputtopic-partition.

Chapter18: Transactions 376
Nowforthereveal:thetransactionalIDforeachproducerinstanceisderivedbyconcatenatingthe
correspondinginputtopicandpartitionindexpair.
Tounderstandwhythisapproachworks,considertheearlierexamplewithP0 andP1,whereI2 was
transferred from P1 to P0 as a result of a perceived failure. For simplicity, assume the input and
output topics are named tx-input and tx-output, respectively. Under the revised architecture, P0
startswithoneconsumerandtwoproducerswithtransactionalIDstx-input-0andtx-input-1.P1
has one transactional producer — tx-input-2. Upon reassignment, P0 has tx-input-0, tx-input-1
andtx-input-2.P1thinks ithastx-input-2.
When P0 acquires I2 and instantiates a producer with the transactional ID tx-input-2, the call
to initTransactions() will result in the finalisation of any pending transaction state with the
transactioncoordinator.Thecoordinatorwilleithercommitorrollbackthetransaction,depending
onthestateofitstransactionlog,recordedintheinternal__transaction_statetopic.Iftheoutgoing
producerwasabletofireoffacommitrequestpriortorevocation,thetransactionwillbecommitted
by writing COMMITTED control messages to the affected partitions in tx-output. Conversely, if the
outgoingproducerabortedthetransactionorotherwisefailedtoexplicitlyendthetransactionprior
to revocation, the transaction will be forcibly rolled back by writing ABORTED control messages to
theaffectedpartitions.Atanyrate,anypendingtransactionwillbefinalisedbeforeP0 ispermitted
to publish new records via its tx-input-2 producer. As part of this ceremony, the epoch number is
incremented.
Whenatransactionisrolledback,Kafkadoesnotdeleterecordsfromtheaffectedpartitions.
Doingsowouldhardlysitwellwiththenotionofanappend-onlyledger.Instead,bywriting
an ABORTED control message to each of the affected partitions, the coordinator signals to a
transactionalconsumerthattherecordsshouldnotbedeliveredtotheapplication.
In the meantime, P1 might attempt to complete its transaction, acting under the tx-input-2 trans-
actional ID. It may add more records to the transaction scope, which is accompanied by an
AddPartitionsToTxnRequesttothetransactioncoordinator.Alternatively,itmayattempttocommit
thetransactionbysendinganEndTxnRequest.Bothrequestsincludeamandatoryepochnumber.Be-
causetheinitialisationoftheproduceronP0 hadincrementedtheepochnumberonthetransaction
coordinator,allfurtheractionsunderthelapsedepochnumberwillbedisallowedbythecoordinator.
To summarise: by pinning a producer client instance to the input topic-partition, we are capturing
the causality among input and output records within the identity of the producer — its PID, by
way of a derived transactional ID. As the partition assignment changes on the group coordinator,
the causal relationship is carried forward to the new assignee; the outgoing assignee will fail if it
attemptstopublisharecordunderthesameidentity.
Transactional consumers
Itwasstatedearlierthattransactionalmessagingislargelyaproducer-sideconcern.Largely,butnot
wholly—consumersstillhavearoletoplayinimplementingtheisolationpropertyoftransactions.

Chapter18: Transactions 377
Specifically, when records are published within transaction scope, these records are persisted to
theirtargetpartitionsevenbeforethetransactioncommits(oraborts,forthatmatter).Atransaction
mightspanmultiplerecordsacrossdisparatetopics.Fortransactionstoretaintheisolationproperty,
itisessentialthattheconsumerecosystemcollaborateswiththeproducerandthetransactioncoor-
dinator. This is accomplished by terminating a sequence of records that form part of a transaction
byacontrolmarker—foreverypartitionthatisfeaturedwithinthetransactionscope.
Allconsumersaboveversion0.11.0.0understandthenotionofcontrolmarkers;however,thewaya
consumer reacts to a control marker varies depending on the consumer’s isolation.level setting.
Thedefaultisolationlevelisread_uncommitted—meaningtheconsumerwilldisregardthecontrol
markers and deliver the records as-is to the polling application. This includes records that are part
ofin-flighttransactionsaswellasthoserecordsthatwerepartofanabortedtransaction.
To enable transactional semantics on a consumer, the isolation.level must be set to read_-
committed. Within this mode, the behaviour of the producer changes with respect to the end offset.
Normally,theendoffsetsareequivalenttothehigh-watermark—theoffsetimmediatelyfollowing
the last successfully replicated record. A transactional consumer replaces its notion of end offsets
with the Last Stable Offset (LSO) — the minimum of the high-water mark and the smallest offset
of any open transaction. Under the constraint of the LSO, a producer will not be allowed to enter a
region in the log that contains an open transaction — not until that transaction commits or aborts.
In the former, the contents of the transaction will be delivered in the result of Consumer.poll(). In
thelatter,therecordswillbesilentlydiscarded.
Having acquired an understanding of how consumers are bound in their progression through the
assignedpartitions,itiseasytoseewhyzombiefencingisanessentialelementoftransactionalmes-
saging.Withoutfencingmeasuresinplace,aproducerstuckinanopentransactionwillimpedethe
advancement of the LSO for up to transactional.id.expiration.ms — impacting all downstream
consumersrunningwithisolation.level=read_committed.
Simple stream processing example
Havingsetthetheoreticalfoundationsfortransactionalmessaging,itistimetoimplementtheabove
scenario.Togetstarted,weneedapairoftopics—tx-inputandtx-output:
$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
--create --topic tx-input --partitions 3 --replication-factor 1
$KAFKA_HOME/bin/kafka-topics.sh --bootstrap-server localhost:9092 \
--create --topic tx-output --partitions 3 --replication-factor 1
The replication factor was kept to the allowed minimum to support our single-node test broker.
Threepartitionsareusedoneithersidetodemonstratetheaffinitybetweenproducerinstancesand

378
| Chapter18: | Transactions |     |     |     |     |     |     |     |     |
| ---------- | ------------ | --- | --- | --- | --- | --- | --- | --- | --- |
the input topic-partition. For simplicity, we are not using authentication or authorization in this
example.
|     | The       | complete |     | source | code                                  | listing | for this | example | is  |
| --- | --------- | -------- | --- | ------ | ------------------------------------- | ------- | -------- | ------- | --- |
|     | available |          | at  |        | github.com/ekoutanov/effectivekafka⁴⁷ |         |          | in      | the |
src/main/java/effectivekafka/transactiondirectory.
Thelistingbelowdepictsasimpletransformationstagethattakesanintegerasinputandsquaresit
beforepublishingtheresultingvalueinanoutputrecord.
| import | static | java.lang.System.*; |     |     |     |     |     |     |     |
| ------ | ------ | ------------------- | --- | --- | --- | --- | --- | --- | --- |
import java.time.*;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.*;
import org.apache.kafka.common.serialization.*;
| public | final       | class       | TransformStage |                    | {       |     |     |     |     |
| ------ | ----------- | ----------- | -------------- | ------------------ | ------- | --- | --- | --- | --- |
| public | static      | void        | main(String[]  |                    | args) { |     |     |     |     |
| final  | var         | inputTopic  |                | = "tx-input";      |         |     |     |     |     |
| final  | var         | outputTopic |                | = "tx-output";     |         |     |     |     |     |
| final  | var         | groupId     | =              | "transform-stage"; |         |     |     |     |     |
| final  | Map<String, |             | Object>        | producerBaseConfig |         | =   |     |     |     |
Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
IntegerSerializer.class.getName(),
ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
true);
| final | Map<String, |     | Object> | consumerConfig |     | =   |     |     |     |
| ----- | ----------- | --- | ------- | -------------- | --- | --- | --- | --- | --- |
Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName(),
⁴⁷https://github.com/ekoutanov/effectivekafka/tree/master/src/main/java/effectivekafka/transaction

379
Chapter18: Transactions
ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
IntegerDeserializer.class.getName(),
ConsumerConfig.GROUP_ID_CONFIG,
groupId,
ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
"earliest",
ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
false);
final var producers = new PinnedProducers(producerBaseConfig);
| try (var consumer         | =   |     |     |                           |     |     |     |
| ------------------------- | --- | --- | --- | ------------------------- | --- | --- | --- |
| new KafkaConsumer<String, |     |     |     | Integer>(consumerConfig)) |     |     | {   |
consumer.subscribe(Set.of(inputTopic),
producers.rebalanceListener());
| while (true)       | {                             |                                          |                                  |        |                     |            |         |
| ------------------ | ----------------------------- | ---------------------------------------- | -------------------------------- | ------ | ------------------- | ---------- | ------- |
| final var          | inRecs                        | = consumer.poll(Duration.ofMillis(100)); |                                  |        |                     |            |         |
| // read the        | records,                      |                                          | transforming                     |        | their values        |            |         |
| for (var           | inRec                         | : inRecs)                                | {                                |        |                     |            |         |
| final var          | inKey                         | =                                        | inRec.key();                     |        |                     |            |         |
| final var          | inValue                       |                                          | = inRec.value();                 |        |                     |            |         |
| out.format("Got    |                               | record                                   | with                             | key    | %s, value           | %d%n",     |         |
|                    | inKey,                        |                                          | inValue);                        |        |                     |            |         |
| // prepare         | the                           | output                                   | record                           |        |                     |            |         |
| final var          | outValue                      |                                          | = inValue                        | *      | inValue;            |            |         |
| final var          | outRec                        | =                                        |                                  |        |                     |            |         |
| new                | ProducerRecord<>(outputTopic, |                                          |                                  |        | inKey,              | outValue); |         |
| final var          | topicPartition                |                                          |                                  | =      |                     |            |         |
| new                | TopicPartition(inRec.topic(), |                                          |                                  |        | inRec.partition()); |            |         |
| // acquire         | producer                      |                                          | for the                          | input  | topic-partition     |            |         |
| final var          | producer                      |                                          | = producers.get(topicPartition); |        |                     |            |         |
| // transactionally |                               |                                          | publish                          | record | and commit          | input      | offsets |
producer.beginTransaction();
try {
producer.send(outRec);
| final | var nextOffset                   |     | =                        |     |     |              |     |
| ----- | -------------------------------- | --- | ------------------------ | --- | --- | ------------ | --- |
| new   | OffsetAndMetadata(inRec.offset() |     |                          |     |     | + 1);        |     |
| final | var offsets                      |     | = Map.of(topicPartition, |     |     | nextOffset); |     |

Chapter18: Transactions 380
producer.sendOffsetsToTransaction(offsets, groupId);
producer.commitTransaction();
} catch (KafkaException e) {
producer.abortTransaction();
throw e;
}
}
}
}
}
/**
* Mapping of producers to input topic-partitions.
*/
private static class PinnedProducers {
final Map<String, Object> baseConfig;
final Map<String, Producer<String, Integer>>
producers = new HashMap<>();
PinnedProducers(Map<String, Object> baseConfig) {
this.baseConfig = baseConfig;
}
ConsumerRebalanceListener rebalanceListener() {
return new ConsumerRebalanceListener() {
@Override
public void onPartitionsRevoked
(Collection<TopicPartition> partitions) {
for (var topicPartition : partitions) {
out.format("Revoked %s%n", topicPartition);
disposeProducer(getTransactionalId(topicPartition));
}
}
@Override
public void onPartitionsAssigned
(Collection<TopicPartition> partitions) {
for (var topicPartition : partitions) {
out.format("Assigned %s%n", topicPartition);
createProducer(getTransactionalId(topicPartition));
}
}

381
Chapter18: Transactions
};
}
Producer<String, Integer> get(TopicPartition topicPartition) {
final var transactionalId = getTransactionalId(topicPartition);
| final var | producer | = producers.get(transactionalId); |     |     |
| --------- | -------- | --------------------------------- | --- | --- |
Objects.requireNonNull(producer,
|        |           | "No such producer: | " + transactionalId); |     |
| ------ | --------- | ------------------ | --------------------- | --- |
| return | producer; |                    |                       |     |
}
| String getTransactionalId(TopicPartition |                               |     | topicPartition) | {   |
| ---------------------------------------- | ----------------------------- | --- | --------------- | --- |
| return                                   | topicPartition.topic()        |     |                 |     |
| + "-"                                    | + topicPartition.partition(); |     |                 |     |
}
| void createProducer(String |     | transactionalId) | {   |     |
| -------------------------- | --- | ---------------- | --- | --- |
if (producers.containsKey(transactionalId))
| throw | new IllegalStateException("Producer |     | already | exists: " |
| ----- | ----------------------------------- | --- | ------- | --------- |
+ transactionalId);
| final var | config = | new HashMap<>(baseConfig); |     |     |
| --------- | -------- | -------------------------- | --- | --- |
config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG,
transactionalId);
| final var | producer              | =                 |     |     |
| --------- | --------------------- | ----------------- | --- | --- |
| new       | KafkaProducer<String, | Integer>(config); |     |     |
producers.put(transactionalId, producer);
producer.initTransactions();
}
| void disposeProducer(String |          | transactionalId)                     | {   |     |
| --------------------------- | -------- | ------------------------------------ | --- | --- |
| final var                   | producer | = producers.remove(transactionalId); |     |     |
Objects.requireNonNull(producer,
|     |     | "No such producer: | " + transactionalId); |     |
| --- | --- | ------------------ | --------------------- | --- |
producer.close();
}
}
}
Theexampleemploysasingleconsumerclientandmultipleproducers.Theproducerconfiguration
is named producerBaseConfig because each producer’s configuration will be slightly different —
havingadistincttransactional.idsetting.Themanagementofproducersisconvenientlydelegated
tothePinnedProducersclass,whichkeepsamappingoftransactionalIDstoproducerinstances.The

382
| Chapter18: | Transactions |     |     |     |     |     |     |     |     |
| ---------- | ------------ | --- | --- | --- | --- | --- | --- | --- | --- |
producerlifecycleismanagedbyexposingaConsumerRebalanceListener,whichspawnsaproducer
upon partition assignment and disposes of the producer upon revocation. The transactional.id
value is derived by concatenating the input topic name with the partition index, delimited by a
hyphencharacter.Forexample,tx-input-2forpartition2inthetopicnamedtx-input.
Returningtothemain()method,wehaveaclassicconsume-process-publishloop.Havingconsumed
a batch of records, the application iterates over the batch and acquires a corresponding pinned
producer instance for each record. A transaction is started for each input record, containing the
output send() call as well as the committing of offsets via the producer. Note, all write operations
must goviatheproducerAPI.
Before we can see this example in action, we need a way of generating input records and viewing
theoutput.Thisistakencareofbyapairofclasses—InputStageandOutputStage:
| import | static | java.lang.System.*; |     |     |     |     |     |     |     |
| ------ | ------ | ------------------- | --- | --- | --- | --- | --- | --- | --- |
import java.util.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;
| public | final  | class                | InputStage         |     | {      |       |     |     |     |
| ------ | ------ | -------------------- | ------------------ | --- | ------ | ----- | --- | --- | --- |
| public | static |                      | void main(String[] |     |        | args) |     |     |     |
|        | throws | InterruptedException |                    |     |        | {     |     |     |     |
| final  |        | var topic            | = "tx-input";      |     |        |       |     |     |     |
| final  |        | Map<String,          | Object>            |     | config | =     |     |     |     |
Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
StringSerializer.class.getName(),
ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
IntegerSerializer.class.getName(),
ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,
true);
try (var producer = new KafkaProducer<String, Integer>(config)) {
|     | while                  | (true) | {     |                         |                    |      |     |           |          |
| --- | ---------------------- | ------ | ----- | ----------------------- | ------------------ | ---- | --- | --------- | -------- |
|     | final                  | var    | key = | new                     | Date().toString(); |      |     |           |          |
|     | final                  | var    | value | = (int)                 | (Math.random()     |      |     | * 1000);  |          |
|     | out.format("Publishing |        |       |                         | record             | with | key | %s, value | %d%n",   |
|     |                        |        | key,  | value);                 |                    |      |     |           |          |
|     | producer.send(new      |        |       | ProducerRecord<>(topic, |                    |      |     | key,      | value)); |

Chapter18: Transactions 383
Thread.sleep(500);
}
}
}
}
import static java.lang.System.*;
import java.time.*;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.*;
public final class OutputStage {
public static void main(String[] args) {
final var topic = "tx-output";
final var groupId = "output-stage";
final Map<String, Object> config =
Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
"localhost:9092",
ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
StringDeserializer.class.getName(),
ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
IntegerDeserializer.class.getName(),
ConsumerConfig.GROUP_ID_CONFIG,
groupId,
ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
"earliest",
ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
false,
ConsumerConfig.ISOLATION_LEVEL_CONFIG,
"read_committed");
try (var consumer = new KafkaConsumer<String, Integer>(config)) {
consumer.subscribe(Set.of(topic));
while (true) {
final var records = consumer.poll(Duration.ofMillis(100));
for (var record : records) {

Chapter18: Transactions 384
out.format("Got record with key %s, value %d%n",
record.key(), record.value());
}
consumer.commitAsync();
}
}
}
}
Run all three, starting them in any order. Each will do their thing; the OutputStage application
will display the resulting records. The TransformStage app has additional logging in place to show
the assignment and revocation of partitions. The logging isn’t overly interesting when running a
singletransforminstance,butwhenmultipleprocessesarelaunched,itclearlyhighlightswherethe
revocationsofpartitionsononeconsumercorrespondtotheassignmentofpartitionsontheother.
Limitations
ThereareseverallimitationsoftransactionalmessaginginKafkawhichthereadershouldbemindful
of.
Bound to Kafka resources
Transactional messaging is implemented using a proprietary Kafka protocol that is not interoper-
able with other persistence systems and messaging middleware. Kafka does not support standard
transactionAPIssuchasXAandJTA.
Cannot span producers
Transactions cannot be used to span multiple producer instances. Transactions are forwarded to
specific transaction coordinators, which are assigned the role of managing transactional IDs by
way of a hash function. Two producers with different transactional IDs may map to two different
coordinators, making cross-producer transactions intractable within the constraints of the current
design.
Cannot span clusters
Wheretheproducerandconsumersidesofaprocessingstageconnecttodifferentclusters,consumer-
sideoffsetscannotbecommittedbyrelayingthemthroughthetransactioncoordinator,asthelatter
residesinadifferentcluster.

Chapter18: Transactions 385
May be partially observed by a consumer
Astransactionscanspanmultiplepartitions,itispossibleforanygivenconsumeroperatingwithin
aconsumergrouptoonlywitnessasubsetoftherecordsemittedwithinabroadertransaction,being
oblivioustotheeffectsofthetransactionthatdonotaffectitsassignedpartitions.Becausepartition
assignment is balanced among members of a consumer group, the records of one transaction may
besubdividedamongmultipleconsumersinagroup.
Transactions may straddle multiple log segments in a partition. When the lapsed log segments are
eventually deleted as a result of retention constraints, it may appear that parts of the transaction
havevanished.Inpractice,thisproblemonlyimpactsconsumerswhohaveaccumulatedsignificant
lag,orarestartingtoreadatopicfromthebeginning.
Recordspublishedwithinatransactionarenotaccordedanyspecialtreatmentfromtheperspective
ofcompaction.Abackgroundcompactionprocessmayvoidindividualrecords,leavingapartialset.
To be fair, the behaviour of transactional records with respect to retention and compaction is not a
limitationoftransactionsassuch,butmoreofacaveatthatwarrantsawareness.
Incomplete exactly-once semantics
The biggest limitation of transactional messaging with respect to exactly-once processing is that
it does nothing to prevent an input record from being handled twice; if the process suffers from a
failuremid-stream,analternateprocesswilltakeoverandmayneedtodealwithpartiallyprocessed
records. This is straightforward if the processing stage embodies a pure function; in other words, it
is both deterministic and has no state of its own. On the other hand, if the processing stage must
writetoadatabaseorinvokeadownstreamservice,priorsideeffectsmustbetakenintoaccount.
Are transactions over-hyped?
For all the proverbial ‘tyre pumping’ of the transactional messaging capability, one must question
the added complexity of dealing with transactions and pinning producers, in terms of benefits that
it provides. Assuming all persistent side effects are idempotent, a competently written consume-
transform-produce loop provides the requisite exactly-once semantics end-to-end. Of course, this
assumeswedon’tcareaboutduplicatesinKafka,andonemightgoasfarasarguingthattheat-least-
oncetenetsofeventstreamprocessingalreadyimplysomedegreeofrobustnessvisàvisduplicates,
providedthereisareliablewayofidentifyingthem.
Transactional messaging eliminates duplicate records in event stream processing graphs. This may
beusefulwhenapplicationsrelyonKafkaasaprimaryeventstoreandwheredealingwithduplicate
recordsmaybenon-trivialattheapplicationlevel.Thecomplexityofmanagingpinnedconsumers
may be encapsulated within a dedicated messaging layer, or a framework. For example, the Kafka
Streamsclientlibrarycantransparentlydealwithtransactions,relievingtheapplicationfromhaving
tomanageconsumersandproducers,demarcatetransactionsandcommitoffsets.KafkaStreamsisa

Chapter18: Transactions 386
goodfitforabroadrangeofmap-reducestyleandsimplewindowedoperations,butunderstandably,
itmightnotsolveallyourstreamprocessingneeds.
WhenthefruitsofKIP-98werereleasedtothegeneralpublic,manyanopportunitywasexploited
to publicise the implications of transparently facilitating exactly-once delivery guarantees at the
middleware level. Suggestions were made that the new capability solves one of the most difficult
problemsindistributedcomputing,andonethatwaspreviouslyconsideredimpossible.
It was stated in Chapter 6: Design Considerations, that exactly-once semantics are not possible at
the middleware layer without tight-knit collaboration with the application. This axiom applies to
thegeneralcase,whererecordprocessingentailssideeffects.Itisimportanttoappreciatethatthis
statementholdsinspiteofKafka’sachievementsintheareaoftransactionalmessaging.Thelatter
solves a limiting case of the exactly-once problem; specifically, it eliminates duplicate records in a
graphofeventstreamprocessingstages,whereeachstagecomprisesexclusivelyofpurefunctions.
Onceagain,thereisno‘silverbullet’.
Itshouldbeacknowledgedthattheexactly-onceimpossibilitydictumdoesnottakeanythingaway
from Kafka; the release of transactional messaging is nonetheless useful in a limited sense. Where
theapplicationdomaindoesnotfitentirelyintothelimitingcaseforwhichtransactionalmessaging
holds, the reader ought to take their own measures in ensuring idempotence across all affected
resources.
https://cwiki.apache.org/confluence/x/ASD8Aw
For the majority of event-driven applications, the most useful and practical aspect of transactional
messaging is the idempotence guarantee on the producer. This feature utilises the same underlying
PIDconceptandtransactionalinfrastructure,ensuringthatrecordsdonoarriveout-of-orderonthe
brokerwithinthetimeframeallowedbydelivery.timeout.ms.
We have just emerged from one of the most taxing topics in all of Kafka. Transactional messaging
wasintroducedinversion0.11.0.0,bringingaboutcapabilitiestosupportacombinationofproducer
idempotenceandend-to-endexactly-oncedeliveryguarantees.
Transactional messaging improves upon Kafka durability guarantee, adding atomicity, consistency
and isolation with respect to the production and consumption of records in a graph of stream
processing applications. When operating within transaction scope, processing stages can avoid
duplicate records in the output topics — ensuring a one-to-one correspondence between the set
ofinputrecordsandtheoutputset.
Thechallengesoftransactionalmessagingarelargelyconcentratedinthemappingofstageinputsto
outputs,bywayofastabletransactionalID.Thelatteridentifiesalogicalproducersessionspanning
multiple physical producer instances. As partitions are reassigned among consumer processes, the
transactional ID follows the partition assignment, resuming the processing of records and fencing

Chapter18: Transactions 387
prioractivitythatmightstillbeineffect.Implementingtransactionalstagesrequirestheapplication
to maintain multiple producer instances that are pinned to the origin of the record, managing the
lifecycleoftheproducersinresponsetochangesinpartitionassignments.
In its leaner guise, the underlying transactional infrastructure can be used to ensure that the
publishingofanygivenrecordisidempotentinsofarasthequeuedrecordwillappearexactly-once
onitstargetpartition,andintheorderprescribedbytheproducer.(Aguaranteethatholdswithinthe
extentofthedeliverytimeout,butcouldbecontravenediftherecordisqueuedasecondtime.)This
featurehasanimmediateandpracticalbenefit,andisrecommendedtobeenabledonallproducers
—notjuststreamprocessinggraphs.