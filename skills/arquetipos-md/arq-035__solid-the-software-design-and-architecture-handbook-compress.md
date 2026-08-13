---
id: arq-035
source_file: "solid-the-software-design-and-architecture-handbook_compress.pdf"
words: 76483
---

1

2

solidbook | The Software Design & Architecture
Handbook
Learntowritetestable,flexible&maintainablecode
KhalilStemmler
Contents
Intro 15
Whydidyoudecidetowritethisbook? . . . . . . . . . . . . . . . . . . . . . . . . 15
Whylearnsoftwaredesignandarchitecture? . . . . . . . . . . . . . . . . . . . . 17
TheDomain-DrivenDeveloper . . . . . . . . . . . . . . . . . . . . . . . . . . . . 19
Softwaredesignistakinganeducatedguessatthefuture . . . . 19
Thankyou . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 19
1. TheWorldofSoftwareDesignandArchitecture 20
Introduction . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 20
Softwaredevelopmentisayoungprofession . . . . . . . . . . . . . . . . . . . . 21
FirstPrinciples . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 21
Chaptergoals . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 22
Let’stalkaboutyou . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 22
Thegoalofsoftware . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 23
Users’technicalexpectationsvarybasedontheirneeds . . . . . . . . . . . 23
Systemqualityattributes(SQAs) . . . . . . . . . . . . . . . . . . . . . . . . 25
Howdowedesignaprojecttobesuccessful? . . . . . . . . . . . . . . . . . . . . 25
Aren’tallsystemqualityattributesessential? . . . . . . . . . . . . . . . . . 26
Whatisarchitecture? . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 26
Whyissoftwarearchitectureimportant? . . . . . . . . . . . . . . . . . . . . . . 26
Whatissoftwaredesign? . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 27
Levelsofdesign . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 27
TheSoftwareDesignandArchitectureStack&Roadmap . . . . . . . . . . . . . . 28
Resource: TheStack . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 28
Resource: TheMap . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 29
Step1: Cleancode . . . . . . . . . . . . . . . . . . . . . . . . . . . . 30
CleanCode . . . . . . . . . . . . . . . . . . . . . . . . . . . . 30
Step2: Programmingparadigms . . . . . . . . . . . . . . . . . . . . 31
Step3: ObjectOrientedProgrammingandDomainModeling . . . . 33
Step4: DesignPrinciples . . . . . . . . . . . . . . . . . . . . . . . . 34
Step5: Designpatterns . . . . . . . . . . . . . . . . . . . . . . . . . 35
3

Step6: ArchitecturalPrinciples . . . . . . . . . . . . . . . . . . . . . 37
Step7: ArchitecturalStyles . . . . . . . . . . . . . . . . . . . . . . . 37
Step8: ArchitecturalPatterns . . . . . . . . . . . . . . . . . . . . . . 40
Step9: Enterprisepatterns . . . . . . . . . . . . . . . . . . . . . . . 41
Chapterconclusion . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 41
References . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 42
2. TypeScript 42
IntroductiontoTypeScript . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 42
Chaptergoals . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 43
PrimarygoalsofTypeScript . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 43
AllJavaScriptisvalidTypeScript . . . . . . . . . . . . . . . . . . . . . . . . . . . 44
TypeScripttypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 44
ConvenientImplicitTypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . 45
ExplicitTypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 45
StructuralTypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 46
Nominaltyping . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 46
Ducktyping . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 47
Ambienttypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 49
MigratingtoTypeScript . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 50
TypescriptstillemitserroredJavaScriptcode . . . . . . . . . . . . . . . . . 50
WhyJavaScriptdoesn’tscale . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 50
WhentouseTypeScript . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 51
Categoriesofhardsoftwareproblems . . . . . . . . . . . . . . . . . . . . . 51
1-Theperformantsystemproblem . . . . . . . . . . . . . . . . . . 51
2-Theembeddedsystemproblem . . . . . . . . . . . . . . . . . . . 52
3-Complexdomainproblem . . . . . . . . . . . . . . . . . . . . . . 52
Object-OrientedJavaScript . . . . . . . . . . . . . . . . . . . . . . . . . . . 53
Codesize . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 53
Productionsoftwarevs.petprojects . . . . . . . . . . . . . . . . . . 54
LackofUnitTests . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 54
Startups . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 54
WorkingonTeams . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 55
Largeteams . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 55
Communicatingpatterns&implementingdesignprinciples . . . . 55
Smallerteams&codingstyles . . . . . . . . . . . . . . . . . . . . . 57
Frameworks . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 58
React . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 58
Angular . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 58
SummaryonwhentouseTypeScript . . . . . . . . . . . . . . . . . . . . . . 58
GettingstartedwithTypeScript . . . . . . . . . . . . . . . . . . . . . . . . . . . . 59
Prerequisites . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 59
InitialSetup . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 59
SetupNode.jspackage.json . . . . . . . . . . . . . . . . . . . . . . . 59
AddTypeScriptasadevdependency . . . . . . . . . . . . . . . . . . 60
InstallambientNode.jstypesforTypeScript . . . . . . . . . . . . . 60
Createatsconfig.json . . . . . . . . . . . . . . . . . . . . . . . . . . 60
4

Createthesrc/folderandcreateourfirstTypeScriptfile . . . . . . . 61
CompilingourTypeScript . . . . . . . . . . . . . . . . . . . . . . . . 61
Ourcompiledcode . . . . . . . . . . . . . . . . . . . . . . . . . . . . 61
Usefulconfigurations&scripts . . . . . . . . . . . . . . . . . . . . . . . . . 61
Coldreloadingdevelopmentscript . . . . . . . . . . . . . . . . . . . 61
Creatingproductionbuilds . . . . . . . . . . . . . . . . . . . . . . . 62
Productionstartupscript . . . . . . . . . . . . . . . . . . . . . . . . 62
ViewtheStarterProjectsource . . . . . . . . . . . . . . . . . . . . . 63
ScriptsRecap . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 63
TypeScriptLanguageFeatures . . . . . . . . . . . . . . . . . . . . . . . . . . . . 63
Basictypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 63
PrimitiveTypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 63
Number . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 64
String . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 64
Boolean . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 64
Arrays . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 64
Object-OrientedProgrammingFeatures . . . . . . . . . . . . . . . . . . . . 64
Classes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 65
Classinheritance . . . . . . . . . . . . . . . . . . . . . . . . . 65
Staticproperties . . . . . . . . . . . . . . . . . . . . . . . . . 66
Instancevariables . . . . . . . . . . . . . . . . . . . . . . . . 66
AccessModifiers . . . . . . . . . . . . . . . . . . . . . . . . . 67
ReadonlyModifier . . . . . . . . . . . . . . . . . . . . . . . . 68
Interfaces . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 68
Classesimplementinginterfaces . . . . . . . . . . . . . . . . 69
Interfacesextendinginterfaces . . . . . . . . . . . . . . . . . 69
Generics . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 70
ConvenienceGeneric . . . . . . . . . . . . . . . . . . . . . . 71
Abstractclasses . . . . . . . . . . . . . . . . . . . . . . . . . 72
Specialtypes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 72
Typeassertions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 72
The“type”keyword . . . . . . . . . . . . . . . . . . . . . . . . . . . 73
TypeAliases . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 75
UnionType . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 75
IntersectionType . . . . . . . . . . . . . . . . . . . . . . . . . . . . 76
Enum . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 76
Any . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 77
Void . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 78
Inline&LiteralTypes . . . . . . . . . . . . . . . . . . . . . . . . . . 78
TypeGuards . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 80
TypeofGuard . . . . . . . . . . . . . . . . . . . . . . . . . . . 80
InstanceofGuard . . . . . . . . . . . . . . . . . . . . . . . . . 80
InGuard . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 81
ChapterSummary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 81
Resources . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 81
References . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 81
5

3. CleanCode 81
Introduction|Cleancodeisyourgripstrength . . . . . . . . . . . . . . . . . . . 81
ChapterGoals . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 82
Understandingcleancode . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 83
Cleancodeisanoverloadedterm . . . . . . . . . . . . . . . . . . . . . . . . 84
Whatthecommunitythinksaboutcleancode . . . . . . . . . . . . . . . . . 84
Communityopinions . . . . . . . . . . . . . . . . . . . . . . . . . . 85
Whattheexpertsthinkaboutcleancode . . . . . . . . . . . . . . . . . . . . 87
Howdoesuncleancodegetwritten? . . . . . . . . . . . . . . . . . . . . . . 88
Twolawsofsoftwaredevelopmentmaintenance . . . . . . . . . . . . . . . 89
Whyit’shardtolearncleancode . . . . . . . . . . . . . . . . . . . . . . . . 89
Reason1—Humansarecomplex . . . . . . . . . . . . . . . . . . . . 89
Reason2—It’shardtodeconstructhumanpsychology . . . . . . . 90
Reason3—Tradeskillsareacquiredthroughmentorship . . . . . . 90
Thethreepillarsofcleancode . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 90
(cid:0)Developermindset . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 91
(cid:0) Codingconventions . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 91
(cid:0)(cid:0)Skills&knowledge . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 91
SectionOne-Developermindset . . . . . . . . . . . . . . . . . . . . . . . . . . . 91
Summary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 91
SoftwareCraftsmanship . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 91
Abriefhistoryofsoftwaredevelopment . . . . . . . . . . . . . . . . 94
Programmingpickingupspeed(50s) . . . . . . . . . . . . . . 94
Thesoftwarecrisisofthe60s-80s . . . . . . . . . . . . . . . 96
Dot-combubble,OOP,andExtremeProgramming(1995–2001) 97
Agile(2001—today) . . . . . . . . . . . . . . . . . . . . . . . 99
TheAgileManifesto . . . . . . . . . . . . . . . . . . . . . . . 100
The(Misled)EraofAgile . . . . . . . . . . . . . . . . . . . . . 101
Whydidn’tAgilework? . . . . . . . . . . . . . . . . . . . . . 101
SoftwareCraftsmanship(2006—today) . . . . . . . . . . . . 102
TheSoftwareCraftsmanshipManifesto . . . . . . . . . . . . 103
BacktoBasics(XP) . . . . . . . . . . . . . . . . . . . . . . . . 103
Craftsmanship: Professionalisminsoftwaredevelopment . . . . . . 103
Definition . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 104
Areyouasoftwarecraftsman? . . . . . . . . . . . . . . . . . 104
Art,science,engineeringpractice,ortrade . . . . . . . . . . 104
Understandingthemanifesto . . . . . . . . . . . . . . . . . . . . . . 104
Notonlyworkingsoftware,butalsowell-craftedsoftware . 104
Notonlyrespondingtochange,butalsosteadilyaddingvalue104
Not only individuals and interactions, but also a commu-
nityofprofessionals . . . . . . . . . . . . . . . . 104
Notonlycustomercollaboration, butalso productivepart-
nerships . . . . . . . . . . . . . . . . . . . . . . . 105
Yourhomework . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 105
Towritewell-craftedsoftware… . . . . . . . . . . . . . . . . 105
Tosteadilyaddvalue… . . . . . . . . . . . . . . . . . . . . . . 106
Engageinthecommunity… . . . . . . . . . . . . . . . . . . . 107
6

Consideryourselfapartner… . . . . . . . . . . . . . . . . . . 108
Growth . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 108
Twomindsets . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 109
Fixedmindset . . . . . . . . . . . . . . . . . . . . . . . . . . 109
Growthmindset . . . . . . . . . . . . . . . . . . . . . . . . . 110
Youneedtohaveagrowthmindsetasasoftwaredeveloper . . . . . 110
Fixedmindsetasadeveloper . . . . . . . . . . . . . . . . . 110
Growthmindsetasadeveloper . . . . . . . . . . . . . . . . 110
Finalthoughts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 113
Lifeismorefun . . . . . . . . . . . . . . . . . . . . . . . . . . 113
Craftsmanshiprequiresagrowthmindset . . . . . . . . . . . 113
DesignThinking . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 113
Structurevs.DeveloperExperience . . . . . . . . . . . . . . . . . . 114
Human-CenteredDesign . . . . . . . . . . . . . . . . . . . . . . . . 117
Whatisit? . . . . . . . . . . . . . . . . . . . . . . . . . . . . 117
Howisthishelpfulforus? . . . . . . . . . . . . . . . . . . . . 118
KnowledgeintheHeadvs.World . . . . . . . . . . . . . . . . . . . . 118
KnowledgeintheHead . . . . . . . . . . . . . . . . . . . . . 119
KnowledgeintheWorld . . . . . . . . . . . . . . . . . . . . . 120
Whyisthishelpfulforus(developers)? . . . . . . . . . . . . 121
Affordances . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 122
Whatarethey? . . . . . . . . . . . . . . . . . . . . . . . . . . 122
Real-lifeexamples . . . . . . . . . . . . . . . . . . . . . . . . 123
Whyisthisuseful? . . . . . . . . . . . . . . . . . . . . . . . . 123
Affordancesinsoftwaredevelopment . . . . . . . . . . . . . 126
Howtodoaffordanceswell . . . . . . . . . . . . . . . . . . . 127
Signifiers . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 127
Whatarethey? . . . . . . . . . . . . . . . . . . . . . . . . . . 127
Real-lifeexamples . . . . . . . . . . . . . . . . . . . . . . . . 128
Whyisthisuseful? . . . . . . . . . . . . . . . . . . . . . . . . 128
Signifiersinsoftwaredevelopment . . . . . . . . . . . . . . . 129
Howtodosignifierswell . . . . . . . . . . . . . . . . . . . . 130
Constraints . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 130
Aboutconstraints . . . . . . . . . . . . . . . . . . . . . . . . 130
Real-lifeexamples(physicalconstraints) . . . . . . . . . . . 130
Real-lifeexamples(culturalconstraints) . . . . . . . . . . . . 131
Real-lifeexamples(semanticconstraints) . . . . . . . . . . . 131
Real-lifeexamples(logicalconstraints) . . . . . . . . . . . . 131
Constraintexamplesinsoftwaredevelopment . . . . . . . . 131
Howtouseconstraintswell . . . . . . . . . . . . . . . . . . . 131
Mappings . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 131
Whatarethey? . . . . . . . . . . . . . . . . . . . . . . . . . . 132
Real-lifeexamples . . . . . . . . . . . . . . . . . . . . . . . . 132
Whyisthisuseful? . . . . . . . . . . . . . . . . . . . . . . . . 134
Howtodomappingswell . . . . . . . . . . . . . . . . . . . . 134
Mappingsinsoftwaredevelopment . . . . . . . . . . . . . . 136
Feedback . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 137
7

Whatisit? . . . . . . . . . . . . . . . . . . . . . . . . . . . . 137
Typesoferrors . . . . . . . . . . . . . . . . . . . . . . . . . . 138
Real-lifeexamples . . . . . . . . . . . . . . . . . . . . . . . . 138
Whyisthisuseful? . . . . . . . . . . . . . . . . . . . . . . . . 139
Feedbackinsoftwaredevelopment . . . . . . . . . . . . . . . 139
Howtodofeedbackwell . . . . . . . . . . . . . . . . . . . . . 140
ConceptualModels . . . . . . . . . . . . . . . . . . . . . . . . . . . 140
Whatarethey? . . . . . . . . . . . . . . . . . . . . . . . . . . 140
Real-lifeexamples . . . . . . . . . . . . . . . . . . . . . . . . 140
Whyisthisuseful? . . . . . . . . . . . . . . . . . . . . . . . . 141
Conceptualmodelsinsoftwaredevelopment . . . . . . . . . 141
Howtodoconceptualmodelswell . . . . . . . . . . . . . . . 142
Testingyourcodeforcleanliness . . . . . . . . . . . . . . . . . . . . 142
SummaryonDesignThinking . . . . . . . . . . . . . . . . . . . . . 143
SectionTwo-Cleancodingconventions . . . . . . . . . . . . . . . . . . . . . . . 144
Summary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 144
Aboutthissection . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 144
Conventionswe’llcover . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 144
Projectplanning . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145
Notes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145
Documentation . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145
Organizingthings . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145
Whycodeorganizationmatters . . . . . . . . . . . . . . . . . . . . . 145
Practicalnaming . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145
Principles . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 145
Organizingandcontext . . . . . . . . . . . . . . . . . . . . . 146
Howtosettledesignarguments . . . . . . . . . . . . . . . . . 146
Formatting&style . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 146
Objectivereadabilitytruths . . . . . . . . . . . . . . . . . . . . . . . 147
Whitespace . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 147
Useobviousspacingrules . . . . . . . . . . . . . . . . . . . 149
Keepcodedensitylow . . . . . . . . . . . . . . . . . . . . . . 150
Breakhorizontallywhennecessary . . . . . . . . . . . . . . 152
Prefersmallerfiles . . . . . . . . . . . . . . . . . . . . . . . . 152
Consistency . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 153
Capitalization . . . . . . . . . . . . . . . . . . . . . . . . . . 153
Whitespacerules . . . . . . . . . . . . . . . . . . . . . . . . . 155
Storytelling . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 155
NewspaperCodeandtheStep-downPrinciple . . . . . . . . 155
Maintainingaconsistentlevelofabstraction . . . . . . . . . 159
Codeshoulddescendinabstractiontowardslower-leveldetails160
Keepingrelatedmethodsclosetoeachother . . . . . . . . . . 160
Enforcingformattingruleswithtooling . . . . . . . . . . . . . . . . 161
ESLint . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 162
Prettier . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 163
Husky . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 163
Comments . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 164
8

Codeexplainswhatandhow,commentsexplainwhy . . . . . . . . 164
Commentscluttercode . . . . . . . . . . . . . . . . . . . . . . . . . 166
Turningcommentsintoclear,explanatory,declarativecode167
Badcomments . . . . . . . . . . . . . . . . . . . . . . . . . 168
Whentowritecomments . . . . . . . . . . . . . . . . . . . . . . . 169
Demonstration . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 169
Example: Addingadditionalcontext . . . . . . . . . . . . . . 172
RelationshiptoHumanCenteredDesign . . . . . . . . . . . . . . . 172
Therelationshipbetweencomments&humancentereddesign . . . 172
Moredoorsexamples . . . . . . . . . . . . . . . . . . . . . . 173
Namingthings . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 173
Thesevenprinciplesofnaming . . . . . . . . . . . . . . . . . 173
Thesevenprinciplesofnaming . . . . . . . . . . . . . . . . . . . 174
Summary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 174
1-Consistency&uniqueness . . . . . . . . . . . . . . . . . . . . . . 174
Consistency . . . . . . . . . . . . . . . . . . . . . . . . . . . . 174
Uniqueness . . . . . . . . . . . . . . . . . . . . . . . . . . . . 177
Bestpractices . . . . . . . . . . . . . . . . . . . . . . . . . . . 178
2-Understandability . . . . . . . . . . . . . . . . . . . . . . . . . . 181
Knowledgeintheworld . . . . . . . . . . . . . . . . . . . . . 181
Representingreal-worldconcepts . . . . . . . . . . . . . . . 181
BestPractices . . . . . . . . . . . . . . . . . . . . . . . . . . . 185
3-Specificity . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 187
Over-specifying . . . . . . . . . . . . . . . . . . . . . . . . . 188
Under-specifying . . . . . . . . . . . . . . . . . . . . . . . . 189
Bestpractices . . . . . . . . . . . . . . . . . . . . . . . . . . . 191
4-Brevity . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 195
Compressionvs.Context . . . . . . . . . . . . . . . . . . . . 195
Thelaw,reiterated . . . . . . . . . . . . . . . . . . . . . . . . 196
Bestpractices . . . . . . . . . . . . . . . . . . . . . . . . . . . 196
5-Searchability . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 200
Bestpractices . . . . . . . . . . . . . . . . . . . . . . . . . . . 200
6-Pronounceability . . . . . . . . . . . . . . . . . . . . . . . . . . . 201
Bestpractices . . . . . . . . . . . . . . . . . . . . . . . . . . . 201
7-Austerity . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 202
Noteveryonehasthesamesenseofhumorasyou . . . . . . 202
Don’tusetemporarilyrelevantconcepts . . . . . . . . . . . . 202
Bestpractices . . . . . . . . . . . . . . . . . . . . . . . . . . . 202
Errorsandexceptionhandling . . . . . . . . . . . . . . . . . . . . . . . . . 202
Relevantlinks . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 203
Testing . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 203
BDD&TDD . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 203
Resources . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 203
UnitTests . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 203
Refactoring . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 204
Architecturebasics . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 204
SectionThree-Skills&knowledge . . . . . . . . . . . . . . . . . . . . . . . . . . 204
9

Summary . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 204
Details . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
ExtremeProgramming . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
Toolsinyourtoolbox . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
Infra . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
Knowtoolstodeploywebsites . . . . . . . . . . . . . . . . . . . . . 205
Knowascriptinglanguage . . . . . . . . . . . . . . . . . . . . . . . 205
Backenddevelopment . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
Knowageneralpurposelanguage . . . . . . . . . . . . . . . . . . . 205
KnowaSQLdatabase . . . . . . . . . . . . . . . . . . . . . . . . . . 205
KnowanORM . . . . . . . . . . . . . . . . . . . . . . . . . . 205
KnowanoSQLdatabase . . . . . . . . . . . . . . . . . . . . . . . . . 205
Frontenddevelopment . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 205
Knowastatemanagementlibrary . . . . . . . . . . . . . . . . . . . 205
Knowaview-layerlibraryorframework . . . . . . . . . . . . . . . . 205
KnowaCSSpre-processor . . . . . . . . . . . . . . . . . . . . . . . 205
Softwaredevelopmentapproaches . . . . . . . . . . . . . . . . . . . . . . . 205
Knowhowtogatherrequirementsandplanaproject . . . . . . . . . 205
Knowhowtowritetests . . . . . . . . . . . . . . . . . . . . . . . . . 205
Knowhowtoconsistentlyprovidevalue . . . . . . . . . . . . . . . . 206
SoftwareQuality . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 206
Structuralquality . . . . . . . . . . . . . . . . . . . . . . . . . . . . 206
Reportonprogrammingerrors . . . . . . . . . . . . . . . . . . . . . 207
Softwarequalitythread . . . . . . . . . . . . . . . . . . . . . . . . . 208
CodeSmells . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 209
Codesmellsdependonthelanguage,context,anddeveloper . . . . 210
AntiPatterns . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 212
Anti-patternsarenotdeterminate . . . . . . . . . . . . . . . . . . . 212
AnemicDomainModelsvs.EntityComponentSystem . . . . 212
DRYvsOverengineering . . . . . . . . . . . . . . . . . . . . 214
Overengineering . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 217
CyclamaticComplexity . . . . . . . . . . . . . . . . . . . . . . . . . 217
Optimizingcode . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 217
Absoluteandrelativecomplexity . . . . . . . . . . . . . . . . . . . . . . . . 217
Bestpractices,principles,andpatterns . . . . . . . . . . . . . . . . . . . . . 217
Guidingprinciplesforcleancoders . . . . . . . . . . . . . . . . . . . . . . . . . . 217
Principle#1-Caredeeplyabouttheprojectandthedomain . . . . . . . . . 217
Principle#2-Aimtoempowerteammatesandfuturemaintainers . . . . . 218
Principle#3-Humans>computers . . . . . . . . . . . . . . . . . . . . . . 218
Principle#4-Conventionsandpatternsarehelpfulbuttheyaresecondary
totheneedsofyourusersandfuturemaintainers . . . . . . . . . . . 218
Wheretogofromhere . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 218
Resources . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 218
References . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 219
4. ProgrammingParadigms 220
Thethreeorganizationalarchetypes . . . . . . . . . . . . . . . . . . . . . . . . . 220
10

5. Object-OrientedProgramming&DomainModeling 221
6. DesignPrinciples 221
SOLID . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 221
SingleResponsibilityPrinciple . . . . . . . . . . . . . . . . . . . . . . . . . 221
Open-ClosedPrinciple(OCP) . . . . . . . . . . . . . . . . . . . . . . . . . . 221
LiskovSubstitutionPrinciple(LSP) . . . . . . . . . . . . . . . . . . . . . . . 221
InterfaceSegregationPrinciple . . . . . . . . . . . . . . . . . . . . . . . . . 221
DependencyInversionPrinciple(DIP) . . . . . . . . . . . . . . . . . . . . . 221
Terminology . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 221
Components . . . . . . . . . . . . . . . . . . . . . . . . . . . 222
DependencyInjection . . . . . . . . . . . . . . . . . . . . . . 222
DependencyInversion . . . . . . . . . . . . . . . . . . . . . . . . . . 224
Usingamockobject . . . . . . . . . . . . . . . . . . . . . . . . . . . 226
TheprimarywinsofDependencyInversion . . . . . . . . . . . . . . 227
InversionofControl&IoCContainers . . . . . . . . . . . . . . . . . 228
DesignbyContract(DBC) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 228
SeparationofConcerns . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
Relatedblogposts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
CQS(CommandQuerySeparation) . . . . . . . . . . . . . . . . . . . . . . . . . . 229
PrincipleofLeastSurprise . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
LawofDemeter(PrincipleofLeastKnowledge) . . . . . . . . . . . . . . . . . . . 229
CompositionoverInheritance . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
YAGNI . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
KISS(KeepItSimple,Silly) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
DRY(Don’tRepeatYourself) . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
TheFourPrimaryObject-OrientedDesignPrinciples . . . . . . . . . . . . . . . . 229
Compositionoverinheritance . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
Aimforshallowclasshierarchies . . . . . . . . . . . . . . . . . . . . . . . . 229
Encapsulatewhatvaries . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
Programtointerfaces,nottoimplementations . . . . . . . . . . . . . . . . . . . 229
RelationshiptoPortsandAdaptersarchitecture . . . . . . . . . . . . . . . . 229
RelationshiptoDependencyInversionPrinciple . . . . . . . . . . . . . . . 229
TheHollywoodPrinciple . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
Allsoftwareiscomposition . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
Designpatternsarecomplexity . . . . . . . . . . . . . . . . . . . . . . . . . . . . 229
Knowofthem,butknowwhenyouneedthem . . . . . . . . . . . . . . . . 230
SeparationofConcerns . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 230
Example: overloadedcontroller . . . . . . . . . . . . . . . . . . . . . . . . . 230
Separationofconcerns . . . . . . . . . . . . . . . . . . . . . . . . . 232
Cross-cuttingconcerns . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 234
PrincipleofLeastSurprise . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 234
Striveforloosecouplingbetweenobjectsthatinteract . . . . . . . . . . . . . . . 234
PrincipleofLeastResistance . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 234
Tell,Don’tAsk . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 234
7. DesignPatterns 234
11

Factorypattern . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 234
8. ArchitecturalPrinciples 234
Componentprinciples . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 234
Reuse-ReleaseEquivalencePrinciple . . . . . . . . . . . . . . . . . . . . . . 234
Commonclosureprinciple(CCP) . . . . . . . . . . . . . . . . . . . . . . . . 234
TheCommonReusePrinciple(CRP) . . . . . . . . . . . . . . . . . . . . . . 234
StableComponents . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
VolatileComponents . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Policy . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Conway’sLaw . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
TheDependencyRule . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Boundaries . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Coupling&cohesion . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
9. ArchitecturalStyles 235
Structural . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Component-basedarchitectures . . . . . . . . . . . . . . . . . . . . . . . . 235
LayeredArchitectures . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Monolithicarchitectures . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Message-based . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Event-Drivenarchitectures . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Publish-Subscribearchitectures . . . . . . . . . . . . . . . . . . . . . . . . 235
Distributed . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 235
Client-serverarchitectures . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Peer-to-peerarchitectures . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
10. ArchitecturalPatterns 236
Cleanarchitecture . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Layers . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Domainlayer . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Applicationlayer . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Infrastructurelayer . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Adapterlayer . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Similararchitectures . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Ports&Adapters . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Vertical-slicearchitecture . . . . . . . . . . . . . . . . . . . . . . . . 236
Domain-DrivenDesign . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
EventSourcing . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
Notes . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 236
EverythingI’verecordedaboutEventSourcingsofar . . . . . . . . . . . . . 237
Aboutthis . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 237
Internallinks . . . . . . . . . . . . . . . . . . . . . . . . . . . 237
ProgressiontoEventSourcing . . . . . . . . . . . . . . . . . 237
SamHotoum’sEventSourcingw/TypeScriptrepo . . . . . . . . . . 238
WhyEventSourcing? . . . . . . . . . . . . . . . . . . . . . . . . . . 238
Statemanagementcodecangetmessy . . . . . . . . . . . . . 238
Thisishowwe’reensuringthatwehaveeventhandlers . . . 238
12

Projectionsanddeserializingeventshappenslikethis! . . . . 238
CopyofBestPlacestoLearnCQRS,EventSourcing . . . . . . . . . . 239
11. BuildingaReal-WorldDDDapp 240
Aboutthischapter . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 240
Chaptergoals . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 241
Domain-DrivenDesign . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 241
UbiquitousLanguage . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 242
ImplementingDDD&ensuringdomainmodelpurity . . . . . . . . . . . . 242
DDDaddressestheshortcomingsofMVC . . . . . . . . . . . . . . . . . . . 243
Slim(Logic-less)Models . . . . . . . . . . . . . . . . . . . . . . . . . 244
Pickyourobject-modelingpoison . . . . . . . . . . . . . . . . . . . 245
ConcernsoftheunspecifiedlayerinMVC . . . . . . . . . . . . . . . 248
Undesirableside-effectswithalackofadomainmodel . . . . . . . . 249
Modelbehaviorandshape . . . . . . . . . . . . . . . . . . . . . . . 249
TechnicalBenefits . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 249
TechnicalDrawbacks . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 250
AlternativestoDDD . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 250
DDDBuildingBlocks . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 250
Entities . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 251
ValueObjects . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 252
Aggregates . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 252
DomainServices . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 253
Repositories . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 253
Factories . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 253
DomainEvents . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 253
Architecturalconcepts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 255
Subdomains . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 255
Typesofsubdomains . . . . . . . . . . . . . . . . . . . . . . . 257
Benefitsofusingsubdomains . . . . . . . . . . . . . . . . . . 258
BoundedContexts . . . . . . . . . . . . . . . . . . . . . . . . . . . . 258
DeploymentasaModularMonolith . . . . . . . . . . . . . . 261
DeploymentasDistributedMicro-services . . . . . . . . . . 262
Howtoplananewproject . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 263
Imperativedesign . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 264
Imperativedesignapproachesareforsmall,simpleCRUDapplications266
Dimensionsthatinfluencethedesignapproachweshouldtake . . . . . . . 267
Use-casedrivendesign . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 267
Usecases&actors . . . . . . . . . . . . . . . . . . . . . . . . . . . . 268
Applicationsaregroupingsofusecases . . . . . . . . . . . . . . . . 268
Ausecaseisacommandoraquery . . . . . . . . . . . . . . . . . . . 269
Usecaseartifacts . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 269
Functionalrequirementsdocumentbusinesslogic . . . . . . . . . . 269
Given-When-Then . . . . . . . . . . . . . . . . . . . . . . . . 269
ParallelswithAPI-firstdesign . . . . . . . . . . . . . . . . . . . . . 271
Stepstoimplementusecasedesign . . . . . . . . . . . . . . . . . . 271
PlanningwithUMLUseCaseDiagrams . . . . . . . . . . . . . . . . . . . . 272
13

1—Identifyingtheactors . . . . . . . . . . . . . . . . . . . . . . . . 272
2—Identifyingtheactorgoals . . . . . . . . . . . . . . . . . . . . . 273
3—Identifyingthesystemsweneedtocreate . . . . . . . . . . . . . 273
4—Identifyingtheusecasesforeachrole . . . . . . . . . . . . . . . 275
Roles,boundaries,andConway’sLawinUseCaseDesign . . . . . . 278
Roledictatesresponsibility . . . . . . . . . . . . . . . . . . . 279
Boundaries . . . . . . . . . . . . . . . . . . . . . . . . . . . . 282
UsingsubdomainstodefinelogicalboundariesinDDDForum 282
Conway’sLaw . . . . . . . . . . . . . . . . . . . . . . . . . . 286
Summaryonusecasediagrams . . . . . . . . . . . . . . . . . . . . . 287
EventStorming . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 287
Whyweneedeventstorming . . . . . . . . . . . . . . . . . . . . . . 289
Howtoconductaneventstormingsession . . . . . . . . . . . . . . 290
Step0—Createalegendofalltheeventstormingconstructs 290
Step1—BrainstormDomainEvents . . . . . . . . . . . . . . 291
Step2—CreatetheCommandsthatcauseDomainEvents . 294
Step3—IdentifytheAggregatethattheCommandisexecuted
againstandtheresultingDomainEvent . . . . . 296
Step4—CreateSubdomainandBoundedContextboundaries 297
Step5—IdentifyViews&Roles . . . . . . . . . . . . . . . . 299
Step6(Optional)—Identifyrules/policies . . . . . . . . . . . 300
EventModeling . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 302
BuildingDDDForum . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 304
Projectarchitecture . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 304
Decision1: We’regoingtouseDomain-DrivenDesign . . . . . . 304
Decision2: We’regoingtouseaLayeredArchitecture . . . . . . 305
Decision3: We’regoingtodeployaModularMonolith . . . . . . 305
Decision4:We’regoingtouseCQRS(CommandQueryResponse
Segregation) . . . . . . . . . . . . . . . . . . . . . . . . . 306
Decision5: We’renotgoingtouseEventSourcing . . . . . . . . . 306
Startingwiththedomainmodels . . . . . . . . . . . . . . . . . . . . . . . . 307
ModelingaUserAggregate . . . . . . . . . . . . . . . . . . . . . . . 308
EmittingDomainEventsfromaUserAggregate . . . . . . . . . . . 312
WritingDomainEvents . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 313
BuildingaDomainEventsSubject . . . . . . . . . . . . . . . . . . . . . . . 314
MarkinganAggregatethatjustcreatedDomainEvents . . . . . . . 318
Howtosignalthatthetransactioncompleted . . . . . . . . . . . . . 319
HowtoregisterahandlertoaDomainEvent? . . . . . . . . . . . . . 321
Whodictateswhenatransactioniscomplete? . . . . . . . . . . . . 322
Feature1: CreatingaMember . . . . . . . . . . . . . . . . . . . . . . . . . . 324
IssuinganAPIrequest . . . . . . . . . . . . . . . . . . . . . . . . . . 325
ApplicationServices/UseCases . . . . . . . . . . . . . . . . . . . . . 327
Usecaseinterface . . . . . . . . . . . . . . . . . . . . . . . . 327
AddingtheCommand(requestobject) . . . . . . . . . . . . . 328
Forkintheroad: TransactionScriptvs.DomainModel . . . . 329
Handlingerrorsasdomainconcepts . . . . . . . . . . . . . . 332
SummaryonUseCases/ApplicationServices . . . . . . . . . 334
14

InsidetheCreateUserusecasetransaction . . . . . . . . . . . . . . . 336
| UsinganExpress.jsRouteHandlertodirecttherequest |     |     |     |     |     |     |     |       | . . . 337 |
| ----------------------------------------------- | --- | --- | --- | --- | --- | --- | --- | ----- | --------- |
| HandlingtheAPIrequestwithanAPIController        |     |     |     |     |     |     | .   | . . . | . . . 337 |
InvokingtheApplicationService/UseCase . . . . . . . . . . 339
SavingtheAggregatewithSequelize . . . . . . . . . . . . . . 343
| Notifying | subscribers      | and   | dispatching |       | Domain |       | Events | from  |           |
| --------- | ---------------- | ----- | ----------- | ----- | ------ | ----- | ------ | ----- | --------- |
|           | SequelizeHooks   |       | .           | . . . | . . .  | . . . | . . .  | . . . | . . . 345 |
| Chaining  | the CreateMember |       | command     |       | from   | the   | Forum  |       | sub-      |
|           | domain           | . . . | . . .       | . . . | . . .  | . . . | . . .  | . . . | . . . 347 |
Feature2: Upvoteapost . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 353
Understandingvotingdomainlogic . . . . . . . . . . . . . . . . . . 353
Gherkintestspecifications . . . . . . . . . . . . . . . . . . . 353
Handlingtheupvotepostrequest . . . . . . . . . . . . . . . . . . . . 355
InsidetheUpvotePostusecase . . . . . . . . . . . . . . . . . . . . . 356
Aggregatedesignprinciples . . . . . . . . . . . . . . . . . . . . . . . 359
| Rule#1-AlltransactionshappenagainstAggregates |             |           |          |         |        |       |             | . .   | . . . 359 |
| --------------------------------------------- | ----------- | --------- | -------- | ------- | ------ | ----- | ----------- | ----- | --------- |
| Rule#2-DesignAggregatestobeassmallaspossible  |             |           |          |         |        |       |             | .     | . . . 360 |
| Rule #3                                       | - You may   | not alter | entities |         | within | the   | aggregate’s |       |           |
|                                               | transaction | boundary  |          | without |        | going | through     |       | the       |
|                                               | aggregate   | .         | . . .    | . . .   | . . .  | . . . | . . .       | . . . | . . . 360 |
UsingaDomainService . . . . . . . . . . . . . . . . . . . . . . . . . 360
| ImplementingtheUpvotePostlogicinaDomainService |     |     |     |     |     |     |     |     | . . . 360 |
| ---------------------------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | --------- |
Persistingtheupvotepostoperation . . . . . . . . . . . . . . . . . . 363
Signalingrelationshipchanges . . . . . . . . . . . . . . . . . 363
| Persistingcomplexaggregatesusingdatabasetransactions |     |     |     |     |     |     |     |     | . 369 |
| ---------------------------------------------------- | --- | --- | --- | --- | --- | --- | --- | --- | ----- |
Feature3: GetPopularPosts . . . . . . . . . . . . . . . . . . . . . . . . . . . 370
Readmodels . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 370
| Modelingreadmodelsasdomainconcepts |     |     |     |     |     | .   | . . . | . . . | . . . 370 |
| ---------------------------------- | --- | --- | --- | --- | --- | --- | ----- | ----- | --------- |
Modelingreadmodelsasrawdata . . . . . . . . . . . . . . . 373
HandlinganAPIrequesttoGetPopularPosts . . . . . . . . . . . . . 374
Usingarepositorytofetchthereadmodels . . . . . . . . . . . . . . 375
Implementingpagination . . . . . . . . . . . . . . . . . . . . . . . . 376
Wheretogofromhere? . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 376
Resources . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 377
References . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 377
Intro
Whydidyoudecidetowritethisbook?
Itallstartedwith“Howwouldyoudesignyourbusiness-logiclayer?”
RightafterIgraduatedfromUniversity,Istartedtheprocessofinterviewingforjobs.
I read and practiced all of the basic interview questions, studied Cracking The Coding In-
terview,andmadesureIknewmystuffonalgorithms,datastructures,JavaScript,andits
languagequirks. IwentovertonsofthingsIhaven’thadtothinkaboutforalongtime,like
closures,IIFEs,passingbyreferencevs.value,etc.
15

Most of the interviews went pretty well. I did interviews at several startups and a couple
largercompanies. Yet,itwaswhenaninterviewforaFull-StackJavaScriptjobatapopular
AIstartupthatacoginmymindirreversiblyactivated.
The recruiter for that role told me to really know my stuff on AI and Python (which, as a
Node.jsdeveloper,IadmitIdidn’tknowenoughabout),soIspentthemajorityofmytime
cramming knowledge about AI and popular Python libraries, attempting to appear as if I
knewsomethingaboutthatspace.
Aftergettingthroughthefirstroundofinterviewsandgettinginvitedforasecondtechnical
interview,IfeltlikeIhaditinthebag.
Theinterviewwasgoingsmoothrightupuntilmyinterviewerasked,“howdoyoudesign
yourbusiness-logic”layer?
That’sthequestionthatstartedeverythingforme.
Itwasasifhewasaskingmetospeakanotherlanguage. Ihadn’ttheslightestideaofhow
toanswerit.
IjustrecitedwhatIknewaboutMVC(model-view-controller),butasthewordswereleav-
ing my mouth, I was realizing more and more that nothing about MVC really screamed
“business-logic”.
Safe to say, I didn’t get the job. My recruiter even dropped me. But it was that moment
I realized there was an entire world of software design and architecture that I needed to
teachmyself. Thatfailureandinadequacymademereallyinterestedtolearntheanswers.
Attheendofthathiringcycle,IendedupinajobasaFrontendConsultant. Iwasmostly
lookingforalow-stressjobIcouldperformtohackmycareerandexpeditemylearningby:
• purchasing and studying as many books on software design, patterns, and architec-
tureaspossible
• and applying everything I learned from those books to improve my cumbersome
~300k-lineNode.jsstartupapp
Everyeveningafterworkfor8months,Ireadbooksandwrotecode.
I started from the basics. Reviewing everything I knew about Object-Oriented Program-
ming,makingsureIreallyunderstoodtheconceptsIlearnedinschool,butprobablynever
internalized(abstractclasses,the4principlesofobject-orientedprogramming).
Ibrowsedtheinternet, writingdownallofthetermsandconceptsthatI’veheardofbut
neverreallycaredtotrytounderstand(POJOs,dependencyinjection,dependencyinver-
sion,inversionofcontrol,concreteclasses,designpatterns,etc.)
ThenImovedovertoArchitecture.
I didn’t get too far without studying basic UML relationships again. This is what most of
theliteratureaboutarchitectureandsoftwaredesignutilizestoexpressideas. Aftergetting
familiarwiththatstuffagain,Iwentstartedlookingintothingslike:
• Coupling
• Cohesion
• Managingdependencies
16

• Separationofconcerns
• Layered/onion/cleanarchitectures/ports&adapters
• Conway’slaw
• Use-casedrivendevelopment
• Packaginglargeapplicationsinmodules
• TDD
• andtheSOLIDprinciples.
ThepointwhereitallreallyfeltlikeitpaidoffwaswhenIdiscoveredDomain-DrivenDe-
sign. Mylearningapproachwastolearnbydoinginadditiontoteachingothers(whichis
whymyblogexiststoday).
Over those 8 months, a lot happened. I quit my job, refactored Univjobs’ codebase to
Domain-Driven Design, made considerable improvements to the codebase’s maintain-
ability,flexibility, andreliabilityandhavebeenpassionatelyattemptingtosharewhat
I’velearnedwithmypeersonline@khalilstemmler.com.
Those books cost me a lot of money though. Among the purchased were “Clean Architec-
ture”, “Clean Code”, “Domain-Driven Design”, “Implementing Domain-Driven Design”,
“The Clean Coder”, “Refactoring”, “Patterns of Enterprise Application Architecture”, etc. I
easilyspentover$1000onbooks.
Butnow,ifyouweretoaskme“howwouldyoudesignyourbusiness-logiclayer”,I’dhavea
lottosay…(cid:0)WORTHIT.
Whylearnsoftwaredesignandarchitecture?
Designproblemsoccurmorefrequentlythanalgorithmicones
Inourdailyprogrammingjobs,we’remoreoftenrequiredto:
• namethingswellsothatthey’reunderstoodandcanbefound
• structurethingswellsothattheycanbeunderstoodandchanged
• changethingsquickly
If you went to school for Computer Science, you’d probably remember focusing a lot on
mathematicsandalgorithms.
Formany(myselfincluded),theseweresomeofthehardestcoursesduringmyundergrad.
ThefirstweekofmysecondyearatBrockUniversity,takingAdvancedDataStructureand
Algorithms,the“welcomeback”assignmentwastobuildaLISPinterpreterusingJava.
The assignment had a prerequisite knowledge of linked-lists, queues, stacks, and other
ADTs. I remember sittingdownto solvethatprogramming assignment on a Fridaynight
in my basement apartment. My parents had just come to drop off some food, and my
roommatelefttovisithisfamilyfortheweekend.
The Sunday night, my roommate returned, and I asked me, “have you left that spot since
Friday?”.IrealizedthatIactuallyhadn’t.I’dbeensittinginthesamespotfor3daysworking
onthesolutiontotheproblem. Eventually, Isolvedthechallenge, handeditin, andgota
decentgrade.
17

Iattributemysuccesstosolvingthisproblemtopurebruteforce.
Whilethereisalotofvalueinstudyingmathematicsandpracticingalgorithms,themagni-
tudeofalgorithmsthatwetypicallyencounterinourdailyprogrammingliveslooksmore
likethis:
| export class  | JobTemplateUtils                         | {                  |     |
| ------------- | ---------------------------------------- | ------------------ | --- |
| public static | getJobTemplatesSorted                    | (): IJobTemplate[] | {   |
| return        | Object.keys(JobTemplatesTypes).map((key) | =>                 | {   |
| return        | JobTemplatesTypes[key]                   |                    |     |
})
| .sort((a,       | b) =>             |     |     |
| --------------- | ----------------- | --- | --- |
| (a.templateName | < b.templateName) |     |     |
? -1 :
| (a.templateName | > b.templateName) |     |     |
| --------------- | ----------------- | --- | --- |
? 1
| : 0) |     |     |     |
| ---- | --- | --- | --- |
}
}
Thatcodesimplysortsahashtableofjobtemplatesalphabetically.
Underwhelmed?
Thatcovers95%ofthecomplexcodeyou’llendupwritingasawebdeveloper.
Althoughthetypeofindustryyou’reworkinginandthedomainyou’rewritingcodeindoes
haveaninfluenceonthejusthowoftenyou’llbecraftingalgorithms,inmostwebapplica-
tions,they’rerelativelysimple.
Ifonlytherewasacourseinuniversitythattaughtyouhowtoconsistentlyprovidevalue
toabusinesswithoutwritingunmaintainablecode.
Anotherreasonyoushouldstudysoftwaredesignandarchitectureis:
Over~$85billionwasspentfixingbadcodein2018
IwroteanarticleallaboutthisremarkablephenomenonandwhereIbelieveitoriginated.
Theexecutivesummaryis:
• Developersaren’tbeingtaughttheessentialsoftwaredesignskills
• Mostcompaniespracticeagile
• Practicingagilemeanschangingandrefactoringcode
• Torefactorcode,weneedtests
• Towritetests,weneedtoknowhowtowritetestablecode
• Mostdeveloperscan’twritetestablecode,whichresultsinproductivityplummet-
ing
Thetruthis,notalothaschangedaboutthefundamentalsofsoftwaredesignoverthepast20years,
butthere’sahugelackoftrainingonit.
ItendtoagreewithEricElliot,whowritesexcellentcontentaboutsoftwarecomposition.
18

Hesays,“99%ofworkingdeveloperslacksolidtraininginsoftwaredesignandarchitecture
fundamentals. 3/4 of developers are self-trained, and 1/4 of devs are poorly trained by
dysfunctionalCScurriculum. Andalmostzerocompaniesmakeupforthosedeficiencies
with in-house training and mentorship. In other words, if you simply accept the status
quo and refuse to offer training in-house, your team will be the blind leading the blind.
- [^1](https://medium.com/@_ericelliott/if-training-is-not-realistic-youre-in-the-wrong-
industry-32e488b864ad)”
It’sSantayana’scurse,
“andthosewhoknownotofhistoryarealsodoomedtorepeattheirmistakes”.
TheDomain-DrivenDeveloper
Have you seen this backend developer roadmap? Where we’re going in this book is what
comesrightafterthis.
Fallinlovewiththeproblem,notthetechnology.
Softwaredesignistakinganeducatedguessatthefuture
Sometimes,Iequatesoftwaredesigntoplayingmidfieldinsoccer.Asamidfielder,youhave
tobeawareofwhat’sgoingonaroundyouatalltimes.Agoodmidfieldershould,atalltimes,
beattemptingtopredictwhat’sgoingtohappen3secondsinthefuture.
A great midfielder is very perceptive and alert to their surroundings. They will often be
positionedonthefieldatalocationthattheirteammatesneedthemtobe,evenbeforethey
knowthey’regoingtoneedthemtobethere.
They’reabletoidentifyifandwhentheirteammatesaregoingtogetblockedandpressured
topasstheball,sotheypositionthemselftobeavailableforthatpass.
Softwaredesign&architectureissimilar. We’remakingbestguesses(throughabstractions
andinterfaces)atwhatwepredictisgoingtoneedtohappeninthefuture,withoutinvesting
alloftheupfrontenergyofimplementingconcretionsofthingswedon’tneed(YAGNI).
Theonlywayforustomakethoseinformedandeducateddesigndecisions?
Understandthedomainwe’reworkingin
Ifwedon’tunderstandthedomainwe’rewritingcodein,we’redoomedtomakeexpensive
messes,becausesoftwarerequirementsaresuretochangeovertime.
Thankyou
It’shardtoforeseethatyou’deverwanttowriteabook,butIhavethepeoplewhoseemails
I’vereadinthemornings,describingtheirgratefulnessandenthusiasmtowardstheEnter-
priseNode.js+TypeScriptblogtothank.
Specifically,I’dliketothankmygirlfriend,Annick,foryourseeminglyinfiniteamountof
heart, understanding, and care in between the long days of me working on this book and
thestartup. Youmustreallylikeme(cid:0) . ThankstoPeterLevels,fortheinspirationbehindthe
19

deploymentofthissiteandtoEricRafatfromFoundersBetaforpushingme“tojustlaunch
thedamnthing”.
To my sharp reviewers Fahad Ahmad, Mario Tacke, Sophia Brandt, Ameur Khaldi, Benji
Speer,MuhammadUmair,andmanymore,foryourideasandhelpinshapingthisbook.
ToPatrickRozaforintroducingmetothescaryfunctionalTypeScriptstuffandcontributing
totheblog.
ToTaniaRascia,whosein-deptharticlesinspiredmetotrybloggingagain,andwhosesite
designImore,orlesscopiedthebestpartsof.
And to the greats: Eric Evans, Alistair Cockburn, Uncle Bob, Vaughn Vernon, Martin
Fowler and James Coplein, for distilling years of knowledge and experience into artifacts
thathaveshapedourindustryforever.
1. The World of Software Design and Architecture
Introduction
TheUnitedStatesSecretaryofDefense,DonaldRumsfeld,oncesaidthatthereare:
• knownknowns(thethingsthatwedoknow)
• knownunknowns(thingsweknowthatwedon’tknow)
• andunknownunknowns(thingswedon’tknowthatwedon’tknow).
Learninganewfieldofstudycanholdalotof unknownunknowns.
Ifforsome reason, youhadtolearna newtradetomorrow-likewelding; foryouand me,
thereareprobablymoreunknownunknownsthanknownunknownsandevenknownknowns.
Softwaredesignandarchitecture,whilebeingrelatedtocomputing,isafieldofstudyinand
ofitself,justlikeDevOpsorUXDesign.
Justbecauseyouknowhowtocode,itdoesn’tmeanyouknowhowtowritewell-designed
software.
Fortunately,it’ssomethingthatcanbetaught.
Unfortunately,it’ssomethingthatmostofuswerenot(formally)taught(andI’mthegreatest
exampleofthat).
SomeofuswenttoschoolandgotCSdegrees.
Someofustooktheself-taughtpathtolearnenoughtogetpaidfortheworkwedo.
Butjustbecauseweknowwhatclassesare, thatdoesn’tmean wenecessarilyknowhowto
writegoodobject-orientedcode.
Justbecauseweputallourcodeintoservices,thatdoesn’tmeanwe’reimplementingaservice-
orientedarchitecture.
And just because we use the Model-View-Controller architectural pattern, it doesn’t mean
that’sit’sthebestforyourproject.
20

Softwaredevelopmentisayoungprofession
Softwaredevelopment(andcomputingingeneral,really)isarelativelyyoungprofession
whencomparedtoothertrades.
For example, the seminal book on Design Patterns was released in 1994, Domain-Driven
Design was only observed and documented in 2003, and the best source for architecture
wascompiledandreleasedin2017.
Meanwhile,othertrades-likeplumbingandwelding,goallthewaybacktothe18and1500s.
Eventhoughtheprofessionisyoung,brilliantdeveloperspavedthewayforusbydiscover-
ingpatterns,principles,paradigms,artifacts,andapproachesinvolvedindevelopingqual-
itysoftware.
In the scientific community, the results of research projects are published in peer-
reviewed journals so that other researchers can repeat experiments or build upon it
to discover new findings. That’s not something that only smart people in lab coats do.
Buildinguponalreadyproventruthsisthebestwayforward.
Overthepast40years,thetechnologiesweusedailyhavechangedalot-butthefundamen-
talshavestayedthesame.
In Uncle Bob’s “Clean Architecture”, he recollects the fact that only 3 dominant program-
ming paradigms have been discovered (each of which uniquely constrains how we write
code). We’reveryunlikelytorealizeanother.
For years, these established truths about software design and architecture discovered
byourcode-forefathershavebeenscatteredacrossbooksandWikipediapages. Thisbook
you’rereadingnowaimstoremedythatproblem. Itseekstopresentthefoundationalideas
thatIbelieveallpeoplegettingpaidtowritecodeshouldprobablyknowabout.
Andhonestly,therearealotofthingsIwishIknewwaybeforeIdiscoveredJavaScriptand
wentgung-hobrute-forcingcodeuntilitworked.
“Thosewhoforgetthepastarecondemnedtorepeatit.” -GeorgeSantayana
“Thosewhoforgetthepastarecondemnedtorepeatit.” -GeorgeSantayana
FirstPrinciples
We’regoingbacktofirstprinciplesonthisone.
First-principlesisthemosteffectivewaytobreakdownproblems. Itworksbydeconstruct-
ingaproblemallthewaydowntotheatomiclevelwherewecan’tdeconstructitanymore,
andthenreconstructingasolutionfromthepartsthatwe’reabsolutelysurearetrue.
It’swhatElonMuskdidwhenhewastryingtofigureouthowtobuildamorecost-efficient
rocket.
It’s what Karl Benz did when he invented the car. He challenged the status quo towards
transportation, as riding horses was the bee’s knees at the time, and made the initial dis-
coveriesagainstwhatwouldgoontobecometheautomobile.
21

In fact, it’s what you did in high school when you had to “solve for A” in simple algebra
problemslike:
5a=2b+c
Youhadtorememberthelawsofaddition,rulesofsubtraction,division,andmultiplication
tosolvesimpleproblemsanddecomposebiggerones.
Butwealwaysstartedwiththosefirst,undeniabletruths,didn’twe?
That’swhatwe’regoingtodowiththisbook.
WearegoingtoapplyarelaxedversionofMethodologicalreductionism(feltcooltosaythat)
inordertomakesenseoftheindustry.
Methodologicalreductionism: Thescientificattempttoprovideexplanationintermsof
eversmallerentities. Alotofthetoolsandprinciplesthatweusetobuildsoftwareatthe
high-leveltoday,canbeconstructedfromdiscoverieswehavebuiltupovertimeatthelow-
level.
Software design and architecture is a huge thing to get acquainted with. But if we decon-
structit,goingallthewaydowntothosepurelyundeniabletruthsaboutwhatconstitutes
well-designedsoftware,andthenreconstructitallthewayuptoarchitecture,Ithinkwe’ll
beingoodshapetohaveminimizedthoseunknownunknowns.
Chaptergoals
In this chapter, my goal is to help you minimize the unknown unknowns about soft-
waredesignandarchitecturebyfirstdiscoveringthegoalofsoftware,thenidentifythe
fundamental, undeniable truths about software design and architecture that enable us to
meetthosegoals.
Let’stalkaboutyou
Idon’tthinkIactuallygotthechancetowelcomeyoutotheworldofsoftwarearchitecture
anddesign.
Welcome!
Ifyou’rehereandyou’rereadingthis,it’sbecausesomeonehasentrustedyoutotakepart
inwritingcodeonsomethingthatmatters,oryou’redeeplyinvestedinyourowngrowthas
adeveloperandwouldliketolearnhowtowritecodethatdoesmoregoodthanbad.
You’rethekindofpersonthatwhenitcomestowritingcode,gettingthejobdoneisn’tenough
foryou. Gettingitdonewellisequallyessential.
You’realsothekindofpersonthat’swellawarethattheactualactofcodingitselfiseasy.
Youcouldsayit’sjusttyping.
Moreover,anythingiseasyifit’snotsubjecttoacertainstandardofquality.
What’shardisproducingsoftwarethat’s:
22

• Simple
• Clean
• Satisfiestheneedsofitsuserstoday
• Canbechangedtosatisfytheneedsofitsuserstomorrow
That’shard. That’salsotheprimarygoalofsoftware.
Thegoalofsoftware
Todiveintothedepthsofwhatmakessoftwaregood,let’sunderstandthegoalofsoftware.
The goal of software is to continually produce something that satisfies the
needsofitsuserswhileminimizingtheeffortittakestodoso
Whetheritbeaclock,anote-takingapp,oreventhecodethatrunsonJavainyourwashing
machine,goal#1istosatisfytheusers’needs.
Goal#1ofsoftware: Satisfytheusers’needs.
Doesthesoftwaremeettheneedsofitsusers? Yes? Awesome!
Now,ifwecanaccomplishthatimpressivefeatatleastonce,goal#2istofigureouthowwe
canconsistentlyachievegoal#1-overandover,withminimaldevelopmenteffort.
Goal#2ofsoftware:ConsistentlyaccomplishGoal#1,withasminimaldevelopmenteffort
aspossible.
That’susuallythetrickypart. Thisiswherecodequalityanddesignstarttomatter. Cleaner,
simpler, and generally better-designedcode is a lot easiertotakefrom point A topoint B
thanbrute-forcedcodethatservestheinitialneedsoftheusers.
It’snotasblackandwhiteasI’mmakingitsound,though.
Satisfyingtheusers’needsisincrediblysubjective.
Here’swhatImean…
Users’technicalexpectationsvarybasedontheirneeds
Dependingontheapplication,whatitdoes,howusersintendtouseit,andhowbadlyusers
actuallyneedit,theirtechnicalexpectationswillvary.
Let’slookatafewexamples.
Firstone. Let’sconsiderthetechnicalexpectationsofamachine-learningapplicationthat,
withAI,wasabletotakeyouroldVHShomevideosand(drastically)improvethepicture
quality.
23

AI-ImprovedHomeMovies:UsersmightbeOKwiththerenderingprocesstaking2hours
tocomplete(-speed),aslongasitalwaysworks(-reliability).
However,usersmightnotbeOK withittakinglong(-speed)ANDfallingvictimtorender
jobsoccasionallyfailingaroundthe1hour,15minmark(-reliability).
Anotherexample,acommonone,isbeingabletotapyourdebitcardtopayinsteadofhav-
ingtoinsertorswipe(IbelievetheactualnameforthisiscalledContactlessPayment).
Tap-enabledDebitCards: Buyersareabletopayforthingsbytappingtheirdebitcardon
the merchant’s card-reader, removing the need for the buyer to type their pin every time
theymakeapurchase(-speed,-efficiency).
Older models readers don’t have tap built-in, so a software upgrade won’t work. Replac-
ing the reader is the only option (- adoptability). Even still, for buyers, there are a fair
amountofmerchantswithreadersouttherethatdoaccepttap,soit’sworthwhiletohavea
tap-enableddebitcard.
Ifcardswereeasytohack,asin,ifitweretrivialforsomeonetocreateafakepaymentreader
andbumpyourpocketwithit(-safety),thatmaydrasticallyaffectthetechnology’sadop-
tionrate,andtheopinionofitbeinggoodsoftware.
Onemoreexample. It’sactuallybasedonascenarioI’mintimatelyacquaintedwith.
24

Real-timeonlinejobfair: Consideryou’vebeenaskedtobuildareal-timeonlinejob-fair
system.
Yourclientcurrentlyrunsapopularforum(hackedtogetherwithWordpress)wherepeople
canfindco-foundersandlearnhowtobuildastartup.
Onceeverymonth, yourclientwantstorunanonlinejob-fairfeaturingoneormorestar-
tups. Jobseekersshouldbeabletojointhechatroom,askquestions,andmakeconnections
withrecruitersfromthestartupcompaniesthatattendtheevent.
They’vebeenabletorunacoupleoftheseeventswithoutanyproblemsonsomeverycheap
sharedhosting.
Thereasonwhyyou’rebeingaskedtoworkonthisprojecttodayisthatthelasteventhad
over 500 people in a chatroom at one time. During that time, several users complained
aboutlagandmessagestakingalongtimetosend.
Yourclienthasnoplantostopgrowing. Eventually,hewantstohostonlineeventscontain-
ingover50,000concurrentusers.
Howwilltheuser(andbusiness)needsaffecttheresultingcode?
Itappearsthatscalabilityisofprimaryconcerntoyourclient(andthecontinuedsuccess
ofthebusiness,overall). Meanwhile,fortheusers,responsivenessisofprimaryconcern.
Theywantittofeelsnappy(-responsiveness).
If you take the gig, you’re tasked with figuring out the best way you can organize code to
fulfillthoseneeds.
Systemqualityattributes(SQAs)
Ineachprojectexample,therewasasetofmetricsthatneededtobesatisfiedtomakethe
usershappy.
Thosemetrics,likespeed,reliability,availability,andscalability,arewhatwecallsystem
qualityattributes(SQAs).
Here’sanentirelistofsoftwarequalityattributesforyoutocheckoutifyou’reinterested.
Howdowedesignaprojecttobesuccessful?
When starting a new project (like any of the ones listed in “Users’ technical expectations
varybasedontheirneeds”),Ilistenverycarefullytotheproblemstatement.
25

Butmostimportantly,IkeepmyearpeeledtopluckoutthoseessentialSQAsinbetween
thelines.
ThoseSQAsaregoingtosignaltoyouwhatismostimportantforthesystemtosucceed,
whathasthemostsignificantpotentialtomakethesystemfail,andwhatarchitectural
choiceswillstacktheoddsofsuccessinyourfavor.
Aren’tallsystemqualityattributesessential?
If we designed a system that possessed all of the positive SQAs to a very high degree of
effectiveness,andnoneofthenegativeones,itjustmightbeasymptoticallyperfect.
Butachievingthatisbothveryhard,andalsoprobablynotnecessarytothebusiness.
Realistically understanding that we can’t be good at everything right off the bat, depend-
ing on what the system is supposed to do, only some of these are critically system quality
attributesessential.
Identifyingthosewillhelpusmakesomeofthosebigup-frontdecisions.
That’swhatarchitectureisabout.
Whatisarchitecture?
Architectureisabouttheimportantstuff. Whateverthatis. —MartinFowler
Architectureisaboutidentifyingthesoftwaresystemqualityattributesthataremostre-
latedtothesuccessorfailureofasystem,thenstackingtheoddsofsuccessbychoosing
therighttechnologies,tools,frameworks,andoveralldesignoftheentiresystem,around
that.
Architectureisaboutcontinuallyensuringthatthoseessentialmake-or-breakattributes
stayhealthythroughoutthesystem’slifespan.
It’salsoeasytosaythatarchitectureisthestuffthatwewishedwegotrightfromthestart
becausechangingtheoverallarchitectureofasysteminthefuturecanbeverychallenging
andtime-consumingtodoefficiently.
Whyissoftwarearchitectureimportant?
Ineachofthesoftwareprojectexamplesfromtheprevioussection,weweregivenaproblem
tosolve—somereal-lifebusinessopportunity,thatcouldpotentiallymakesomeonealot
ofmoney.
Atthisstage,ifweknowtheSQAsthatweneedtoprotect,whatweneednextisafounda-
tionthatbestprotectstheSQAs,tellsushowtowirethelargepiecestogether,andhow
towritecodewithinthatskeleton.
It’s“thefoundationthathasaprofoundeffectonthequalityofwhatisbuiltontopofit”.
26

There are several architectural patterns (such as Domain-Driven Design, Model-View-
Controller, and Microservices) that each originate from a specific architectural style
(structural, message-based, or distributed). Each architectural pattern is uniquelyequipped
toprotectcertainSQAs.
Thisisamassivepartoftheearlydecision-makingprocesswhenitcomesdowntomaking
thebigup-frontdecisionsaboutarchitecture.
“Plansareworthless,butplanningiseverything”—Eisenhower,1950
Whatissoftwaredesign?
There’salotofconfusionaboutthedifferencebetweensoftwaredesignandarchitecture.
Ultimately,theymeanthesamething.
It’salldesign,really.
Softwaredesignandarchitecturearetwosidesofthesamecoin.
Software architecture & design is the structure of a system, the elements it con-
tains,andtherelationshipbetweenthoseelements.
Ilovethisdefinition. We’llcomebacktothisinafewbecausethere’ssomethingwithinit
thatIthinkisreallyexciting.
Forasecond,thinkaboutthewordarchitecture. Theworditselfandtheconnotationsitcar-
riesmakesiteasytoseewhyitseemslikearchitectureandsoftwaredesignaretwoseparate
things.
They’renot.
Levelsofdesign
Thewordarchitecturecarrieswithitaconnotationofbeingrelatedtothingsthatare“large”
and“high-level”.
Thewordalsooftendrawsthemindtoparallelsamongtheconstructionofbuildings,cars,
orothervitalandexpensive-to-changethings.
However, professionals responsible for the high-level designs of these creations, like
houses, planes, or Teslas, also understand the implementation details (the low-level
things)thatwillneedtobeaccomplishedtofulfillthedesign.
The point I’m trying to make is that you can’t be a high-level designer (software archi-
tect),withoutknowledgeofthelow-leveldetails(writingcleancode,usingaprogramming
paradigmeffectively,adheringtodesignprinciples). Thedevilisinthedetails.
Youcan,however-bealow-levelcoder,proceedtodumpcodeintoaproducttomakethe
nextfeaturework,allwithoutrespectforthehigh-leveldesign,andpossiblygetawaywith
itmaybeacoupleoftimes.
Butremembergoal#2? Beingabletoconsistentlysatisfytheusers’needs?
27

Whenthelow-leveldetailsgoagainstthegrainofthehigh-levelpolicy,it’sonlyamatter
oftimeuntilwehavealegacysystemthat’snolongereasy(orworthwhile)tomaintainon
ourhands.
Both levels of software design (high and low) are essential. They form a sym-
bioticrelationshipwitheachotherthatwheninsync,canleadtohigh-quality
softwarethatiseasytomaintainandchange.
That means everyone on the team holds the shared responsibility of understanding the
high-levelarchitectureandhowthelow-leveldetailssupportit.
TheSoftwareDesignandArchitectureStack&Roadmap
Let’stakeasecondtorecapwhatwejustdiscovered. We’llregularlybedoingthisthrough-
outthebook:
• Thegoalsofsoftwareareto:
– Goal#1: Satisfytheusers’needswhileminimizingtheeffortittakestodoso.
– Goal#2: ConsistentlyaccomplishGoal#1astherequirementschange.
• Architectureisaboutidentifyingthesystemqualityattributesthatwillstackour
oddsofsuccessfullyperformingGoal#1,andourchoiceofarchitecturalpatternthat
willbestaccommodatetheproject(wecoulddivemuchdeeperherethough).
• Lastly, software design isn’t much different from architecture besides the fact that
theyhavedifferentlevelsofdesignthattheyappearat.
Great, that’s a good start. We’ve defined what we’re fighting for, and we understand at a
high-levelhowtogetthere.
Let’s continue the descent to get to the bottom of what’s involved in designing good soft-
ware.
I’d like to introduce to you two artifacts that I spent a really long time thinking about to
visualizethescopeandbreadthofsoftwaredesignandarchitecture.
Theyarethestackandtheroadmap.
Resource: TheStack
Thesoftwaredesignandarchitecturestack
The stack. It depicts the scope of learning from the most intimate details of the enterprise
patternyou’vechosentothewayyouwritecleancode.
Theknowledgerequiredtogettothetopofthestackislayered.SimilarlytotheOSIModelin
networking,eachlayerofthestackbuildsontopofthefoundationofthepreviousone.
28

Thesoftwaredesignandarchitecturestackdepictsthelayersofsoftwaredesignandarchi-
tecture.
Inthegraphicstack,I’veincludedexamplestosomeofthemostimportantconceptsateach
respectivelayer. Becausetherearejusttoomanyconceptsateachlayer,Ididn’tincludeall
of’em.
Resource: TheMap
TheSoftwareDesignandArchitectureRoadmap
Checkoutthemap. WhileIthinkthestackisgoodtoseethebiggerpictureofwhatwehave
to cover at a glance, the map is a little bit more detailed, and as a result, I think it’s more
useful.
FromcleancodetoDomain-DrivenDesignconcepts,themapisindicativeofthepaththat
we’regoingtotakeinthisbooktogetyourampedupintheworldofsoftwaredesignand
architecture.
Youcanviewtheentiremap(it’squitelargeandnotincludedhere,justforEPUBandPDF
readers)hereviathislink.
29

Step1: Cleancode
CleanCode
Goal: Learnhowtowritecleancode.
Theveryfirststeptowardscreatinglong-lastingsoftwareisfiguringouthowtowriteclean
code.
Ifyouaskanyonewhattheythinkconstitutescleancode,you’llprobablygetadifferentan-
swereverytime. Alotoftimes,you’llhearthatcleancodeiscodethatiseasytounderstand
andchange. Atthelow-level,thismanifestsinafewdesignchoiceslike:
• beingconsistent
• preferringmeaningfulvariable,methodandclassnamesoverwritingcomments
• ensuringcodeisindentedandspacedproperly
• ensuringallofthetestscanrun
• writingpurefunctionswithnosideeffects
• notpassingnull
Thesemayseemlikesmallthings,butthinkofitlikeagameofJenga. Inordertokeepthe
structureofourprojectstableovertime,thingslikeindentation,smallclassesandmethods,
andmeaningfulnames,payoffalotinthelongrun.
Ifyouaskme,thisaspectofcleancodeisabouthavinggoodcodingconventionsandfollowing
30

them.
Ibelivethat’sonlyoneaspectofwritingcleancode.
Mydefinitiveexplanationofcleancodeconsistsof:
• (cid:0)Yourdevelopermindset(empathy,craftsmanship,growthmindset,designthinking)
• (cid:0) Yourcodingconventions(namingthings,refactoring,testing,etc)
• (cid:0)(cid:0)Yourskills&knowledge(ofpatterns,principles,andhowtoavoidcodesmellsand
anti-patterns)
Somuchofwhatmakessoftwaregreathappensbeforeweeventouchthekeyboard.
Onerequirementisthatyoushouldcareenoughtolearnaboutthebusinessyou’rewriting
codewithin. Ifwedon’tcareaboutthedomainenoughtounderstandit,thenhowcanwebe
surewe’reusinggoodnamestorepresentdomainconcepts? Howcanwebesurethatwe’ve
accuratelycapturedthefunctionalrequirements?
Ifwedon’tcareaboutthecodethatwe’rewriting,it’salotlesslikelythatwe’regoingtoim-
plementessentialcodingconventions, havemeaningfuldiscussions, andaskforfeedback
onoursolutions.
We often think that code is solely written to serve the needs of the enduser, but we forget
theotherpeoplewewritecodefor: us,ourteammates,andtheproject’sfuturemaintainers.
Havinganunderstandingoftheprinciplesofdesignandhowhumanpsychologydecides
whatisgoodandbaddesign,willhelpuswritebettercode.
Soessentially,thebestwordthatdescribesthisstepofyourjourney?Empathy.
Once we’ve got that down, learn the tricksofthetrade and continue to improve them them
overtimebyimprovingyourknowledgeoftheessentialsoftwaredevelopmentpatternsand
principles.
InChapter3,wediscusswhatcleancodeisandhowtowritecodethatisclean.
Step2: Programmingparadigms
Goal: Understand the differences between each mainstream programming
paradigm,whateachuniquelybringstothetable,andwhentousethem.
31

Now that we’re writing readable code that’s easy to maintain, it would be a good idea to
reallyunderstandthe3dominantprogrammingparadigmsandthewaytheyinfluencehow
wewritecode.
InUncleBob’sbook,CleanArchitecture,hebringsattentiontothefactthat:
• Object-OrientedProgrammingisthetoolbestsuitedfordefininghowwecrossar-
chitecturalboundarieswithpolymorphismandplugins
• Functionalprogrammingisthetoolweusetopushdatatotheedgesofourapplica-
tionsandelegantlyhandleprogramflow
• andStructuredprogrammingisthetoolweusetocomposealgorithms
Thisimpliesthatrobustsoftwareusesahybridall3programmingparadigmsstylesatdif-
ferenttimes.
Whileyoucouldtakeastrictlyfunctionalorstrictlyobject-orientedapproachtowritecode
inaproject,understandingwhereeachexcelswillimprovethequalityofyourdesigns.
It’soneofthosescenarioswhere:
…ifallyouhaveisahammer,everythingseemslikeanail.
InChapter4,wediscusstheseprettyboldstatementsaboutprogrammingparadigms.
32

Step3: ObjectOrientedProgrammingandDomainModeling
Goal: Re-learnobject-orientedprogrammingbutthistime,withmodel-driven
designinmind.
Inabookaboutsoftwaredesignandarchitecture, Object-OrientedProgrammingisgoing
togetalotoflovebecauseit’sthecleartoolforarchitecture.
NotonlydoesObject-Orientedprogrammingenableustocreateapluginarchitectureand
buildflexibilityintoourprojects,OOPcomeswiththe4principlesofOOP(encapsulation,
inheritance,polymorphism,andabstraction)thathelpsuscreaterichdomainmodels.
Most developers learning Object-Oriented Programming never get to this part: learning
howtocreateasoftwareimplementationoftheproblemdomain,andenablingittoliveinthecen-
terofalayeredwebapp.
33

Functional programming seems to be growing in popularity recently, and I expect that it
has a lot to do with React and the JavaScript ecosystem, but don’t be so quick to dismiss
OOP,model-drivendesignandDomain-DrivenDesign.
In Chapter 5, we spend some time towards understanding the big picture on how object-
modelersencapsulateanentirebusinessandtheirprocesseswithinazero-dependencydomain
model.
Whyisthatahugedeal?
Becauseifwecancreateamentalmodelofabusiness,wecancreatethesoftwareimplemen-
tationofthebusiness.
Step4: DesignPrinciples
Goal: Learn the object-oriented design principles for keeping code flexible,
testable,andmaintainable.
Object-OrientedProgrammingisbeneficialforencapsulatingrichdomainmodelsandsolv-
ingthe3rdtypeof“HardSoftwareProblems”-ComplexDomains,butitcanintroducesome
designchallenges.
WhenshouldIuseextendsandinheritance?
WhenshouldIuseaninterface?
WhenshouldIuseanabstractclass?
Design Principles are well-established and battle-tested object-oriented best practices
thatwecanuseasguardrails.
Examplesofcommondesignprincipleswewillfamiliarizeourselveswithare:
34

• Compositionoverinheritance
• Encapsulatewhatvaries
• Programagainstabstractions,notconcretions
• TheHollywoodprinciple: “Don’tcallus,we’llcallyou.”
• TheSOLIDprinciples,especiallytheSingleresponsibilityprinciple
• DRY(DoNotRepeatYourself)
• YAGNI(YouAren’tGonnaNeedIt)
ThesearejustafewofmanyOOdesignprinciplesthatcanhelpusimproveourdesigns. We
discussthemindetailinChapter6.
Step5: Designpatterns
Goal: Learnthepatternsthataresolutionstocommonlysolvedproblemsand
howtoapplythemattheclasslevel.
Genericversionsofthemostcommonlyoccurringproblemsinsoftwaredevelopmenthave
alreadybeencategorizedandsolved. Wecallthesepatterns. Designpatterns,actually.
Thereare3categoriesofdesignpatterns: creational,structural,andbehavioral.
CreationalDesignPatterns
Creationalpatternsarepatternsthatcontrolhowobjectsarecreated.
Examplesofcreationalpatternsinclude:
35

• TheSingletonpattern*forensuringonlyasingleinstanceofaclasscanexist
• TheAbstractFactorypattern,forcreatinganinstanceofseveralfamiliesofclasses
• ThePrototypepattern,forstartingoutwithaninstancethatisclonedfromanexist-
ingone
StructuralDesignPatterns
Structuralpatternsarepatternsthatsimplifyhowwedefinerelationshipsbetweencompo-
nents.
Examplesofstructuraldesignpatternsinclude:
• TheAdapterpattern,forcreatinganinterfacetoenableclassesthatgenerallycan’t
worktogether,toworktogether.
• TheBridgepattern,forsplittingaclassthatshouldactuallybeoneormore,intoaset
of classes that belong to a hierarchy, enabling the implementations to be developed
independentlyofeachother.
• TheDecoratorpattern,foraddingresponsibilitiestoobjectsdynamically.
BehavioralDesignPatterns
Behavioralpatternsarecommonpatternsforfacilitatingelegantcommunicationbetween
objects.
Examplesofbehavioralpatternsare:
• TheTemplatepattern,fordeferringtheexactstepsofanalgorithmtoasubclass.
• The Mediator pattern, for defining the exact communication channels allowed be-
tweenclasses.
• TheObserverpattern,forenablingclassestosubscribetosomethingofinterestand
tobenotifiedwhenachangeoccurred.
Designpatterncriticisms
Designpatternsaregreatandall,butsometimestheycanaddadditionalcomplexitytoour
designs. It’sessentialtorememberYAGNIandattempttokeepourdesignsassimpleaspos-
sible. Onlyusedesignpatternswhenyou’rereallysureyouneedthem. You’llknowwhen
youwill.
If we know what each of these patterns is, when to use them, and when to notevenbother
usingthem,we’reingoodshapetobegintounderstandhowtoarchitectlargersystems.
Thereasonbehindthatisbecausearchitecturalpatterns(Chapter10)arejustdesignpat-
ternsblown-upinscaletothehigh-level,wheredesignpatternsarelow-levelimplemen-
tations(closertoclassesandfunctions).
WediscussdesignpatternsinChapter7.
36

Step6: ArchitecturalPrinciples
Goal: Learn how to manage relationships between components, express high-
levelpolicy,andidentifyarchitecturalboundaries.
Nowwe’reatahigherlevelofthinkingjustabovetheclasslevel.
Atthispointinourjourney,weunderstandthattherelationshipsbetweencomponentswill
haveasignificantimpactonthemaintainability,flexibility,andtestabilityofourproject.
InChapter8,we’llcovertheguidingprinciplesthathelpus:
• Improveflexibilitywithinourcodebasetobeabletoreacttonewfeaturesandrequire-
ments
• Separateconcerns
• Improve readability and scan-ability by organizing our code into cohesive modules
dictatedbytheusecasesofourapplication
Here’saglimpseofwhatwe’reinterestedinlearning:
• Componentdesignprinciples:TheStableAbstractionPrinciple),TheStableDepen-
dencyPrinciple,andTheAcyclicDependencyPrinciple,forhowtoorganizecompo-
nents,theirdependencies,whentocouplethem,andtheimplicationsofaccidentally
creatingdependencycyclesandrelyingonunstablecomponents.
• Policy vs. Detail, for understanding how to separate the rules of your application
fromtheimplementationdetails.
• Boundaries,andhowtoidentifythesubdomainsthatthefeaturesofyourapplication
belongswithin.
Step7: ArchitecturalStyles
Goal: Learn the different approaches to organizing our code into high-level
modulesanddefiningtherelationshipsbetweenthem.
37

WediscoveredthatSystemQualityAttributes(SQAs)arethemetricsweneedtoprotectto
stacktheoddsofsuccessofourapplication.
Architecturalstylesaregroupingsofallthedifferenttypesofarchitecturesthatyoucan
employ. Eachofthesestyleshasuniquelypositiveeffectsonmaintainingthehealthofone
ormoreSQAs.
Forexample,asystemthathasalotofbusinesslogiccomplexitywouldbenefitfromusing
alayeredarchitecturetoencapsulatethatcomplexity.
AsystemlikeUberneedstobeabletohandlealotof realtime-eventsatonceandupdate
drivers’locations,sopublish-subscribeorevent-drivenstylearchitecturemightbemost
effective.
I’ll repeat myself here because it’s important to note that the 3 categories of architectural
stylesaresimilartothe3groupsofdesignpatternsbecausearchitecturalstylesarejust
designpatternsatthehigh-level.
Structrual
Projectswithvaryinglevelsofcomponents,andwide-rangingfunctionalityareusuallylook-
ingfor-flexibilityasanSQA.Structuralarchitecturalstylesmakeiteasiertoextendand
separatetheconcernsofcomplexsystems.
Hereareafewexamples:
• Component-basedarchitecturesemphasizeseparationofconcernsbetweentheindivid-
ual components within a system. Think Google for a sec. Consider how many appli-
cationstheyhavewithintheirenterprise(GoogleDocs, GoogleDrive, GoogleMaps,
etc). Forplatformswithlotsoffunctionality, component-basedarchitecturesdivide
theconcernsintolooselycoupledindependentcomponents. Thisisahorizontalsepa-
38

ration.
• Monolithicmeansthattheapplicationiscombinedintoasingleplatformorprogram,
deployedalltogether. Note: Youcanhaveacomponent-basedANDmonolithicarchitectureif
youseparateyourapplicationsproperly,yetdeployitallasonepiece.
• Layeredarchitecturesseparatetheconcernsbycuttingsoftwareintoinfrastructure,
application,anddomainlayers. Thisisaverticalseparation.
Anexampleofcuttingtheconcernsofanapplicationverticallybyusingalayeredarchitec-
ture.
Message-based
Messagingmightbeacrucialcomponenttothesuccessofthesystem. Message-basedarchi-
tecturesbuildontopoffunctionalprogrammingprinciplesandbehavioraldesignpatterns
liketheobserverpattern.
Hereareafewexamplesofmessage-basedarchitecturalstyles:
• Event-Driven architectures view all significant changes to the state as events. For
instance,withinavinyl-tradingapp,anOffer’sstatemightchangefrom“pending”to
“accepted” when both parties agree on the trade. Commands and Events become the
primarymechanismstoinvokeandreacttochangeswithinthesystem.
• Publish-subscribe architectures make heavy use of the Observer design pattern
by enabling subscribers to listen in on something of interest (a chatroom, or event
stream) and publish events to all appropriate subscribers. A subscriber can be
something within the system itself, end-users / clients, and other systems and
components.
Distributed
A distributed architecture simply means that the components of the system are deployed
39

separately and operate by communicating over a network protocol. Distributed systems
canbehandyforscalingthroughput,scalingteams,anddelegating(potentiallyexpensive
tasksor)responsibilitytoothercomponents.
Afewexamplesofdistributedarchitecturalstylesare:
• Client-serverarchitecture.Oneofthemostcommonarchitectures,wherewedivide
theworktobedonebetweentheclient(presentation)andtheserver(businesslogic).
• Peer-to-peer architectures distribute application-layer tasks between equally-
privilegedparticipants,formingapeer-to-peernetwork.
WediscussarchitecturalstylesinmoredetailinChapter9.
Step8: ArchitecturalPatterns
Goal:Learnthearchitecturalpatternsthatimplementoneormorearchitectural
stylestosolveaproblem.
Architecturalpatternsaretacticalimplementationsofoneormorearchitecturalstyles.
AndwhenIsay“tactical”implementation,Ireallymeanthat. Theseareexactpatternsyou
canusetocreatethearchitecturethatprotectsyourSQAs.
Here are a couple of examples of architectural patterns in addition to the styles that they
inheritfrom:
• Domain-Driven Design is an approach to software development against really
complex problem domains. For DDD to be most successful, we need to implement
a layered (structural style) architecture to separate the concerns of a domain
model from the infrastructural details that make the application actually run, like
databases,webservers,caches,etc.
40

• Model-View-Controller is probably the mostwell-known architectural pattern for de-
velopinguserinterface-basedapplications. Stylistically,it’sadistributedarchitec-
ture. It works by dividing the app into 3 components: model, view, and controller.
MVCisincrediblyusefulwhenyou’refirststartingout,andithelpsyoupiggybackto-
wards other architectures, but there hits a point when we realize MVC isn’t enough
forproblemswithlotsofbusinesslogic.
• Eventsourcingisafunctionalapproachwherewestoreonlythetransactions, and
neverthestate. Ifweeverneedthestate,wecanapplyallthetransactionsfromthe
beginning of time. You probably guessed, but this is an event-driven approach to
architecture.
WediscusstheseinChapter10.
Step9: Enterprisepatterns
Goal:Learntheinsandoutsoftheconceptsinvolvedinimplementingyourcho-
senarchitecturalpattern.
Dependingonthearchitecturalpatternyouchosebestsuitsyourneeds,there’sgoingtobe
plentyofnewconstructsandtechnicaljargontomakesenseof.
For example, when you decide that Domain-Driven Design is the architectural pattern
makesthemostsenseforyourproject,youneedtolearnabout:
• Entities: theydescribemodelsthathaveanidentity.
• ValueObjects: thesearemodelsthathavenoidentity,andcanbeusedtoencapsulate
validationlogic.
• DomainEvents: theseareeventsthatsignifysomerelevantbusinesseventoccurring,
andcanbesubscribedtofromothercomponents.
AndifyoudecidethatEventSourcingmakessense,you’llhaveanentirelynewsetofcon-
ceptstolearnlike:
• Retroactive Events: Automatically correct the consequences of an incorrect event
that’salreadybeenprocessed.
• EventualConsistency: awaytoachievehighavailabilitythatinformallyguarantees
that,ifnonewupdatesaremadetoagivendataitem,eventuallyallaccessestothat
itemwillreturnthelastupdatedvalue. -viaWiki
Note: It’sverycommontocombineseveralofthearchitecturalpatternstogetherintoanar-
chitecturethatmeetsyourSQAs.ConsiderthechallengesofDDD+EventSourcing,orMVC
+MessageQueues/Streams.
Dependingonthearchitecturalstyleyou’vechosen,therearegoingtobeatonofconcepts
foryoutolearntoimplementthatpatterntoit’sfullestpotential.
InChapter11,webuildareal-worldappwithDomain-DrivenDesign.
Chapterconclusion
Wejustwentfrom0to100onsoftwaredesignandarchitecture.
41

Theultimategoalofthischapterwastotakeyouforawalkthroughwhatthereistoknow.
Hopefully,wewereabletoknockdownyourunknownunknownsanotch.
Thebiggesttakeawaysfromthischapter:
Thegoalsofsoftwareareto:
• Goal#1: Satisfytheusers’needswhileminimizingtheeffortittakestodoso.
• Goal#2: ConsistentlyaccomplishGoal#1astherequirementschange.
Architectureisabout
• Identifyingthesystemqualityattributes(SQAs)thatwillstackouroddsofsuccess-
fullyaccomplishingGoal#1,and
• ChoosingthecorrectarchitecturalpatterntosatisfythecriticalSQAs.
Softwaredesignisn’tmuchdifferentfromarchitecturebesidesthefactthat:
• They have different levels of design that they appear at. The best example of this
is design patterns, which are essential at the class level and appear as (high-level)
architecturalpatternsthathelpusmeetourSQAs.
References
• Wikipedia: Listofarchitecturalstylesandpatterns
• Architecturalstylesvs.architecturalpatternsvs.designpatterns
• TheCleanArchitecture
• How&whydoscientistsshareresults
• Whatiscleancodeandwhyshouldyoucare?
• Software Architects Handbook: Become a Successful Software Architect by Imple-
mentingEffectiveArchitectureConcepts
• Reductionism
2. TypeScript
IntroductiontoTypeScript
Over the past few years, I’ve witnessed JavaScript development take off. It’s come a long
way. JavaScriptwasoncesomethingthatmymanagerwouldlaughatandcallatoylanguage.
Now, it has become a first-class citizen for serverless applications, AR / VR experiences,
event-driven architectures at fortune 500 companies, and pretty much anything else you
canthinkabout-it’snotgoinganywhere.
Foraconsistentnumberofyearsnow,JavaScripthasbeenthemostpopularprogramming
language.
Languagescomeandgo,butsomethingsdon’tchange.
Softwaredesignprinciplesandthefactthattypesimprovecodequalityandreadabilityhas
beensomethingthatwe’veknownforalongtime.Yet,it’sbeenleftoutofmodernJavaScript
foralongtime.
42

TypeScript, developedandappropriatedlabeledbyMicrosoftas“JavaScriptthatscales”, isa
supersetofJavaScript,meaningthateverythingJavaScriptcando,TypeScriptcando(and
morebetter).
Inthischapter,we’llgetacquaintedwithTypeScript:
• A language more appropriate for learning software design and architecture than
JavaScriptand,
• Alanguagethatwe’regoingtobeprimarilyfocusedonthroughoutthebook
FordevelopersskilledwithTypeScriptalready,I’dencourageyoutoskipthischapterand
gostraighttothenextone.
Chaptergoals
Theforthischapterareto:
• UnderstandthedesigndecisionsofTypeScriptandtheshortcomingsTypeScriptwas
meanttosolveimproveinJavaScriptdevelopment
• KnowwhichtypesofJavaScriptprojectsTypeScriptisbestsuitedfor
• LearnhowtoinstallandsetupaTypeScriptproject
• ExploreTypeScriptlanguagebasicsandfeaturesthatmightbenewtodevelopersfa-
miliarwitheitherJavaorC#
PrimarygoalsofTypeScript
TypeScriptwasmeanttosolvetwoproblems:
1-ProvideJavaScriptdeveloperswithanoptionaltypesystem.
Because JavaScript projects have gotten increasingly complex in recent years, developers
haverealizedtheutilityofhavingatypesystemtomanagecomplexity.
“HowdoweconvertalegacyJavaScriptprojecttoTypeScript”? Itwouldsurelytakemonths
todoitinonego. Isthereawaythatwecangraduallyconvertthecodebasetousetypes,
withoutleavingtheappinanunstablestate?
Yep. TypeScriptwasdesignedtocompilebothTypeScriptandJavaScript. Thisenablesde-
velopers to opt-in to using types and take as long as they need to convert a codebase to
TypeScriptovertime.
2 - Provide JavaScript developers with the ability to utilize planned features
fromfutureJavaScripteditionsagainstcurrentJavaScriptengines.
While being used for several other things now, JavaScript is still the language of the
browsers. EvenNode.js, theserver-sideruntimethatallowsustocreatebackendapplica-
tionswithJavaScript,isbuiltontopoftheChromeV8engine.
However, it can be tough for modern web browsers to keep up with supporting the latest
version.Thisismostlyduetothefactthatthelanguageiscontinuouslyundergoingchanges,
andfeaturesareaddedcontinuallytofutureECMAScriptproposalsandfindingtheirway
intonewECMAScriptstandards.
43

As of writing this, the current JavaScript version fully supported by modern browsers is
ECMAScriptv5.
Towritecodeusingthesenewfeaturesandhavethemworkonmodernbrowsers,weneed
atoolthatcancompilenewerECMAScriptlanguagefeaturesdowntoES5.
That’sanotherthingTypeScriptcando.
ECMAScript: ECMAScript is a language specification standard that was created in order to
standardizeJavaScript.
AllJavaScriptisvalidTypeScript
TypeScriptallowsustouselanguagefeaturesnotavailablecurrentJavaScriptversions(such
asdecorators,accessmodifiers,typeannotations). Manyofthesefeatureswillseemfamil-
iartoC#andJavadevelopersbutmaybenewtodevelopersthatonlyknowJavaScript.
BecauseTypeScriptisasupersetofJavaScript,everythinginJavaScriptisalsovalidinType-
Script. However,theinverseisnottrue(noteverythinginTypeScriptisvalidinJavaScript).
TypescriptisasupersetofJavaScript
Superset—asetwhichincludesanothersetofsets.Inthiscase,theentiresetof
thingspossibleinJavaScriptisincludedasasmallpartofthelargersetofthings
possiblewithTypeScript.
Forexample,usageofthetypeannotationisvalidTypeScriptbutnotvalidJavaScript.
const age: number = 12; // ￿ Valid TypeScript, ￿ invalid JavaScript
Yet,notincludingatypeannotationforavariableisbothvalidJavaScriptandTypeScript.
const age = 12; // ￿ Valid TypeScript, ￿ Valid JavaScript
TypeannotationsareoneofmanyfeaturessupportedonlybyTypeScript.
TypeScripttypes
TherearefourprimarytypesoftypesinTypeScriptyou’llbedealingwith: implicit,explicit,
structural,andambient.
44

ConvenientImplicitTypes
ExplicitTypes
StructuralTypes
Ambienttypes
ConvenientImplicitTypes
InheritingfromthecarefreenatureofJavaScript,TypeScriptwilldoit’sverybesttotryto
figureoutthetypesofyourvariablesifyoudon’texplicitlydefinethem.
Forexample,usingVisualStudioCode,avariableagedeclaredwiththevalue13willassume
thatthetypeisnumberandprovidealloftheprimitivenumbermethodsasaresultofthat
typeinference
ThisconvenienceenablesJavaScriptdeveloperstocontinuetocodewithouthavingtodefine
thetypesoftheirvariablesandenablesoldJavaScriptcodetobemigratedtoTypeScript.
Andinthefollowingexample,becauseTypeScriptinfersthetypesofvariablesnotexplicitly
expressed,changingthevalueof agetoatypethatisn’tnumberwillpresentsanerror.
Dependingonwhoyouask,thoseimplicittypechecksareeitheramazingoranuisance(I
leanstronglyontheamazingside).
ExplicitTypes
Implicitchecksareprettyhandyforcatchingsillyerrors. Andwhilethecompilercanmake
alotofassumptionsaboutwhatwe’relikelytryingtodo,weshouldpreferexplicittypeanno-
tationstovalidatethecompiler’sassumptions.
45

| *const* | artist: | string | = "Nick Cave | and the Bad | Seeds"; |
| ------- | ------- | ------ | ------------ | ----------- | ------- |
InTypeScript,thetypeannotationisappliedtothevariableinaPolishpostfixnotationstyle
(wheretheoperator/typefollowstheoperand/variable). Thismightfeelalittlebitstrangefor
developerscomingfromaJavaorC#background,butyou’llgetusedtoit.
SimilartoJavaandotherstrictlytypedlanguages,functionsandmethodscanalsospecify
a return type. Un-similarly to C# and Java, the return type is also denoted using Polish
postfixnotation.
| function | sayHello | (): | string { |     |     |
| -------- | -------- | --- | -------- | --- | --- |
| return   | 'Hello'  |     |          |     |     |
}
| class       | Greeter { |           |        |     |     |
| ----------- | --------- | --------- | ------ | --- | --- |
| constructor | ()        | {}        |        |     |     |
| sayHello    | (name:    | string):  | string | {   |     |
| return      | `Hello    | ${name}`; |        |     |     |
}
}
Ifweattempttoassignthereturnvaluetoanimproperlytypedvariable,we’llalsogetthe
errorsonewouldexpectfromstricttypechecking.
StructuralTypes
Wehavealotofoptionsforexplicitlydefiningtypes. Let’stalkalittlebitaboutNominal
andDuckTyping.
Nominaltyping
Ducktyping
Nominaltyping
InNominaltypesystems,aparticulartypeisdeemedvalidbasedon:
1. Theexplicitdeclarationofthenameofthetypeand/or,
2. Ifthetypeisasubtype.
Nominaltypesystemsprimarilyuseabstractionslikeinterfacesandclassestodeterminetype
compatibility.
46

Instrument Guitar
In the following example, we create an class which is subclassed by a
TheGuitarclassisthensubclassedbyaFenderclass.
class.
| class Instrument                  | {}         |            |            |             |     |                 |
| --------------------------------- | ---------- | ---------- | ---------- | ----------- | --- | --------------- |
| class Guitar                      | extends    | Instrument |            | {}          |     |                 |
| class Fender                      | extends    | Guitar     | {}         |             |     |                 |
| // Valid,                         | through    | subtypes   |            |             |     |                 |
| const thingThatMakesNoise:        |            |            | Instrument |             | =   | new Fender();   |
| // Valid,                         | through    | subtypes   |            |             |     |                 |
| const pluckedThingThatMakesNoise: |            |            |            | Fender      |     | = new Guitar(); |
| // Valid,                         | explicitly | defined    |            | as a Fender |     |                 |
| const fender:                     | Fender     | = new      | Fender();  |             |     |                 |
Instrument=>Guitar=>Fender.
Thiscreatesahierarchyschemeof:
Therefore,aFenderisavalidtypeforbothaGuitarandanInstrument.
Note: Thisisademonstration. Youtypicallydon’twanttocreateelaborateclasshierarchies
likethis. See“CompositionoverInheritance”fromChapter6—DesignPrinciples.
LanguageslikeJavausenominaltyping. Theyrequireyoutoexplicitlydeclareandcast the
types of your variables. Even in scenarios where you know that a particular type (struc-
turally)satisfiestherequiredmembersofabstraction,youstillneedtoexplicitlydefinethe
type,becausethat’shownominaltypesystemscanwork. Thiscansometimesresultinalot
ofredundantcode.
InTypeScript,therulesaremuchmorerelaxed.
BecauseoneofTypeScript’sdesigngoalswastoprovideanoptional-typesystemwithout
disrupting productivity for JavaScript developers, TypeScript comes with the concept of
DuckTypingbuilt-in.
Ducktyping
DuckTypingisanexampleofastructuraltypesysteminwhichtypecompatibilityandequiv-
alencearedeterminedbythetype’sactualstructure.
Thenamecomesfromthesayingthat“ifitlookslikeaDuckanditquackslikeaDuck…it
mustbeaDuck”.
Inthefollowingexample,thepostComment(comment: Comment)functionneedssomething
comment. Comment
that looks like a The attributes that exist on the interface needs to be
presentinthetypeoftheargumentpassedintothefunction.
| interface | Comment | {   |     |     |     |     |
| --------- | ------- | --- | --- | --- | --- | --- |
id: number;
| name:    | string; |     |     |     |     |     |
| -------- | ------- | --- | --- | --- | --- | --- |
| content: | string; |     |     |     |     |     |
}
47

| interface | Reply | {   |     |     |     |     |
| --------- | ----- | --- | --- | --- | --- | --- |
id: number;
| name:            | string; |         |         |     |     |     |
| ---------------- | ------- | ------- | ------- | --- | --- | --- |
| content:         |         | string; |         |     |     |     |
| parentCommentId: |         |         | number; |     |     |     |
}
| const | comment: | Comment | =   | {   |     |     |
| ----- | -------- | ------- | --- | --- | --- | --- |
id: 1,
| name:    | 'Khalil', |            |        |     |     |     |
| -------- | --------- | ---------- | ------ | --- | --- | --- |
| content: |           | "Is anyone | here?" |     |     |     |
};
| const | reply: | Reply | = { |     |     |     |
| ----- | ------ | ----- | --- | --- | --- | --- |
id: 2,
| name:            | 'Don | Draper',  |       |         |     |     |
| ---------------- | ---- | --------- | ----- | ------- | --- | --- |
| content:         |      | "Yes, I'm | right | here.", |     |     |
| parentCommentId: |      |           | 1     |         |     |     |
}
| function | postComment |     | (comment: |         | Comment) | {   |
| -------- | ----------- | --- | --------- | ------- | -------- | --- |
| // Do    | something   |     | with the  | comment |          |     |
}
| // ￿ Perfect |     | - exact | match |     |     |     |
| ------------ | --- | ------- | ----- | --- | --- | --- |
postComment(comment);
| // ￿ OK | - extra | information |     | still | alright |     |
| ------- | ------- | ----------- | --- | ----- | ------- | --- |
postComment(reply);
| // ￿ Missing |     | info not | OK. |     |     |     |
| ------------ | --- | -------- | --- | --- | --- | --- |
// Type '{ id: number; }' is missing the following properties from
| // type       | 'Comment': |     | name,  | content |     |     |
| ------------- | ---------- | --- | ------ | ------- | --- | --- |
| postComment({ |            | id: | number | });     |     |     |
NoticethatanobjectsatisfyingtheshapeofaReplyisOKtobepassedinbecauseithasall
thestructuralattributesthatwoulddeemaReplytobeaComment?
Yet, not satisfying the minimum requirements of the Comment interface can be caught at
compiletime.
That’sprettypowerful. ComparethatwiththepreviousJavaScriptconventionofchecking
fortypesatruntime. BeforeTypeScript,DuckTypinginJavaScriptlookedalotlikethis:
/**
| * This | method      | posts | a comment. |         | A comment | needs an        |
| ------ | ----------- | ----- | ---------- | ------- | --------- | --------------- |
| * 'id' | attribute   |       | of type    | number, | a 'name'  | of type string, |
| * and  | a 'content' |       | of type    | string. |           |                 |
48

*/
| function | postComment           | (comment) | {                                |     |
| -------- | --------------------- | --------- | -------------------------------- | --- |
| const    | isIdPresentAndValid   |           | = comment.hasOwnProperty('id')   |     |
| &&       | !isNaN(comment.id);   |           |                                  |     |
| const    | isNamePresentAndValid |           | = comment.hasOwnProperty('name') |     |
| &&       | typeof comment.name   |           | === "string";                    |     |
const isContentPresentAndValid = comment.hasOwnProperty('content')
| &&  | typeof comment.content |     | === "string"; |     |
| --- | ---------------------- | --- | ------------- | --- |
if (!isIdPresentAndValid) throw new Error('Must provide an integer id');
if (!isNamePresentAndValid) throw new Error('Must provide a string name');
if (!isContentPresentAndValid) throw new Error('Must provide a string content');
| // Do | things |     |     |     |
| ----- | ------ | --- | --- | --- |
...
JoiValidation:WhileTypeScriptcandocompiletimestructuraltypechecking,Joi,apopular
JavaScriptvalidationlibrary,candoruntimestructuraltypechecking.
Ambienttypes
AprimarydesigngoalofTypeScriptwastomakeitpossibleforyoutosafelyandefficiently
useexistingJavaScriptlibrariesinTypeScript.
TypeScript does this through declaration. TypeScript provides you with a sliding scale of
how much or how little effort you want to put in your declarations, the more effort you
put,themoretypesafety+codeintelligenceyouget. Notethatdefinitionsformostofthe
popularJavaScriptlibrarieshavealreadybeenwrittenforyoubytheDefinitelyTypedcom-
munitysoformostpurposeseither:
1. Thedefinitionfilealreadyexists.
2. Orattheveryleast,youhaveavastlistofwell-reviewedTypeScriptdeclarationtem-
platesalreadyavailable
Asaquickexampleofhowyouwouldauthoryourowndeclarationfile,considerthetrivial
caseofusingjQuery.Bydefault,TypeScriptexpectsyoutodeclare(i.e.,usevar,letorconst
Ifyou’reloadingjQuerythrougha<script>tagand
somewhere)beforeyouuseavariable.
expectingittobeloadedintotheglobalscope,TypeScriptwillcomplainthatitdoesn’tknow
anythingaboutthat.
| $('.awesome').show(); |     | //  | Error: cannot find | name `$` |
| --------------------- | --- | --- | ------------------ | -------- |
Asaquickfix,youcantellTypeScriptthatthereisindeedsomethingcalled$:
| declare               | var $: any; |     |       |     |
| --------------------- | ----------- | --- | ----- | --- |
| $('.awesome').show(); |             | //  | Okay! |     |
49

If you want, you can build on this basic definition and provide more information to help
protectyoufromerrors:
declare var $: {
(selector:string): any;
};
$('.awesome').show(); // Okay!
$(123).show(); // Error: selector needs to be a string
MigratingtoTypeScript
BymerelyaddingallowJs: truetothetsconfig.jsoncompileroptions,youcantakeyour
timemigratingeventhehugestprojectsfromJavaScripttoTypeScriptbychangingthefile
extensionfrom.jsto.ts.
TypescriptstillemitserroredJavaScriptcode
TypescriptstillemitserroredJavaScriptcode
TypeScript’stoleranceforJavaScriptisgenuinelyunlikeanyotherprogramminglanguage.
WhenTypeScriptcomesacrossJavaScriptcodewitherrorsinit,liketheageexampleshown
before,TypeScriptwillstillcompileandemittheJavaScriptcodetotheresultingcompiled
build,regardlessofittherearetypeerrorsinit.
Thiscodecompilesandisemitted:
// index.js
var age = 13;
age = 'old as heck'; // TypeError: number not assignable to string
TypeScript’stoleranceoftotallyun-typedvanillaJavaScriptmakesitveryeasyforsomeone
tograduallyrefactoralegacyJavaScriptprojecttoTypeScript.
Ifyoutried todo this in a.ts file, the code wouldn’tcompile. Theidea isthat.js files are
the Wild West, able to get away with whatever. Who really knows how things were back
then? Whereas.tsfilesembodythenewcivilizederaofenterprisecraftsmanship,heldto
theTypeScriptcompiler’sscrutiny. Nocowboy-codehere.
WhyJavaScriptdoesn’tscale
MicrosoftcalledTypeScript“JavaScriptthatscales”…what’ssounscalableaboutJavaScript?
Concerningsoftwaredevelopment,therearetwowaystothinkaboutscalability.
1. Performancescalability
2. Productivityscalability
TypeScriptismeanttoaddress2. Productivityscalability.
Like most dynamically-typed languages, the lack of types in JavaScript can drastically
improveinitialproductivitylevelsoncertainprojects. Still, somefactorsexistinother
50

projects (team size, code size, intended code lifespan, domain complexity), in which the
lackoftypescanbedetrimentaltocodequalityandunderstandability.
It’sbeenagreeduponthat:
• It’sbettertoenablethecompilertocatchsillybugs,typos,andothererrorsatcompile-
time,ratherthaninproductionatruntime.
• Testsarethebestdocumentationpossibleforyourcode. Typesarenosubstitutefor
writingtests,buttheycandoanexcellentjobofreducingthesurfaceareaofbugs.
• Testsalsoenablefasterandsaferrefactoring. Similarly,ifnotestsexist,typescan(at
theveryleast)catchsyntacticinaccuracies.
WhentouseTypeScript
EventhoughTypeScripthasmadeasignificantefforttobeasuncumbersomeaspossible,
somedevelopersstillfinditpreferabletousevanillaJavaScript.
WhenIfirstdiscoveredTypeScript,Icertainlydidn’tseethebenefit…upuntilIstartedex-
periencingsomereallyannoyingstufflikebuilds,notfailingwhentheyshould. Eventually,
thebuggycodeandtyposthatcontinuouslyfoundtheirwayintoproductioncodestarted
togettome.
Tomakemattersworse,asmyprojectdemandsstartedtogetmorecomplex,Ifounditin-
creasinglychallengingtoexpressdomainconceptsinacleanandclearobject-orientedway.
9 months into adopting TypeScript, I’ve built new features in Angular apps for clients,
changed a massive React/Redux front-end to TypeScript, and ported several backend
servicestoTypeScriptfromvanillaNode.js,refactoringanddeletingmassamountsofcode
alongtheway.
Inthissection,we’lltakealookatsomeofthemostcommonscenariosandidentifywhen
itmightbevitaltouseTypeScript,andwhenwecouldprobablydowithoutitandstickto
vanillaJS.
Categoriesofhardsoftwareproblems
Firstly,I’dliketopresenttoyouaframeworkforhowIcategorizeallofthehardsoftware
development problems that currently exist. They fit into one of three categories of hard
softwareproblems.
1. ThePerformantsystemproblem
2. TheEmbeddedsystemproblem
3. TheComplexdomainproblem
1-Theperformantsystemproblem
Tobestexplainthis,let’stakeTwitterasanexample.
Twitter is a straightforward concept in actuality. You sign up, you make tweets, you like
otherpeople’stweets,andthat’sprettymuchit. IfTwitteristhatsimple,whycouldn’tsome-
oneelsedoit?
51

BecausetherealchallengeforTwitterisnotactuallysomuchas“whatitdoes”,butit’s“how
it’sabletodowhatitdoes”.
Twitterhastheuniquechallengeofservingrequestsfromapproximately500millionusers
everysingleday.
ThehardproblemthatTwittersolvesisactuallyaperformanceproblem.
When the challenge is performance, the discussion about the language and paradigm you
choosetosolvethatproblemisalotlesscritical.
2-Theembeddedsystemproblem
Anembeddedsystemisacombinationofcomputerhardwareandsoftwaretoenablecon-
troloverthemechanicalorelectricalaspectsofasystem.
Most systems we use today are built on a very complex layer of code that, if not initially
writtenin,compilesdowntoCorC++usually.
Codingintheselanguagesisnotforthefaintofheart.
InC,thereisnosuchthingasobjects,andweashumanslikeobjectsbecausewecaneas-
ilyunderstandthem. Cisalsoprocedural,whichmakesthecodeinthislanguageachal-
lengetokeepclean. Embeddedsystemsproblemsalsorequireknowledgeofthelower-level
details.
C++doesmakelifeawholelotbetterbecauseithasobject-orientation,butthechallengeis
stillfundamentallyinteractingwithlower-levelhardwaredetails.
Becausewedon’treallyhavethatmuchofchoiceonthelanguagesweuseandbecausethe
fundamentalcomplexityhereisprimarilyinteractionwithlow-leveldetails,it’slessusefulto
discusslanguageandparadigmhere.
3-Complexdomainproblem
Number 3 is my favorite challenge in software development, which is the complexdomain
problem.
Withtheseproblems,thechallengeislessaboutscalingintermsofhandlingmorerequests,
but instead scaling the codebase to keep it maintainable and flexible while pushing out
newfeaturesforusers,withoutbreakingexistingones(reliability).
To me, this is the exciting stuff that enterprise companies like Gitlab, Salesforce, Google,
andAirbnbhaveinvestedcountlesshoursinmanagingproblemdomaincomplexity.The
mostsignificantengineeringchallengesareusually:
• Modelingthedomainconceptsandactuallysolvingtheproblemsofthedomain,us-
ingterminologyeveryonecanunderstand.
• Beingabletologicallyseparatepartsofthatmonolithintosmallerapps.
• Physicallysplitthosepartsoftheappintoteamstobeassignedtomaintainthoseapps
(microservices).
• Integratingbusinesslogicandsynchronizingdatabetweenthesemicroservices.
• Notgettinglostinthemassamountsofcodewritten.
52

• Notdegradingproductivityandslowingdowntothepointwhereitbecomesimpos-
sibletoaddnewfeatureswithoutbreakingexistingones.
• Writingcodethataccuratelyexpressesthedomainmodelandlanguage,enablingnew
developerstolearnthedomainbysimplyscanningthecode(i.e.,embeddingthebusi-
nessinthesoftware).
I’vemainlydescribedthetypesofproblemsthatDomainDrivenDesignsolves. Forthese
types of projects, you wouldn’t even think about not using a strictly-typed language like
TypeScript.
Object-OrientedJavaScript
For complex domain problems, if you don’t choose TypeScript and instead, choose
JavaScript, it will require some extra effort to be successful. Not only will you have
to be extra comfortable with your object modeling abilities in vanilla JavaScript, but
you’ll also have to know how to utilize the 4 principles of object-oriented programming
(encapsulation,abstraction,inheritance,andpolymorphism).
Thiscanbehardtodo. JavaScriptdoesn’tnaturallycomewithconceptsofinter-
facesandabstractclasses.
“Interface Segregation” from the SOLID design principles isn’t easily achievable with
vanillaJavaScript.
UsingJavaScriptalonewouldalsorequireacertainlevelofdisciplineasadevelopertokeep
the code clean, and this is vital once the codebase is sufficiently large. You’re also left to
ensurethatyourteamsharesthesamediscipline,experience,andknowledgelevelonhow
toimplementuniversaldesignpatternsinJavaScript. Ifnot,you’llneedtoguidethem.
InDomain-Drivenprojectslikethis,thestrongbenefitfromusingastrictlytypedlanguage
islessaboutexpressingwhatcanbedone, butmoreaboutusingencapsulationandinfor-
mationhidingtoreducethesurfaceareaofbugsbylimitingwhatdomainobjectsareactually
allowedtodo.
Wecanlivewithoutthisonthefront-end,butit’sahardlanguagerequirementforthe
backend in my books. It’s also the reason why I moved my Node.js backend services to
TypeScript.
Outofallthreecategoriesofhardsoftwareproblems,theComplexDomainProblemisthe
onlyonewhereIwouldrecommendTypeScriptasanabsolutenecessity.
Besides this, other factors might determine when it’s best to use TypeScript for your
JavaScriptproject.
Codesize
Codesizeoftentiesbacktothecomplexdomainproblem,wherealargecodebasemeansacom-
plexdomain,butthat’snotalwaysthecase.
Whentheamountofcodeinaprojectgetstoacertainsize,itbecomeshardertokeeptrack
ofeverythingthatexistsandbecomeseasiertoendupre-implementingsomethingalready
coded.
53

Duplicationistheenemytowell-designedandstablesoftware.
This is especially heightened when new developers start coding on an already large code-
base.
Visual Studio Code’s autocompletion and Intellisense helps to navigate through huge
projects. ItworksreallywellwithTypeScript,butit’ssomewhatlimitedwithJavaScript.
ForprojectsthatIknowwillstaysimpleandsmall,orifIknowthatitwillbethrownaway
eventually,IwouldbelesspressedtorecommendTypeScriptasanecessity.
Productionsoftwarevs.petprojects
Productionsoftwareiscodethatyoucareaboutorcodethatyou’llgetintroubleforifit
doesn’twork. It’salsocodethatyou’vewrittentestsfor. Thegeneralruleofthumbisthatif
youcareaboutthecode,youneedtohaveunittestsforit.
Ifyoudon’tcareandwon’tgetintroublefornothavingtests,don’tworryabouttests.
Petprojectsareself-explanatory. Dowhateveryoulike. Youhavenoprofessionalcommit-
menttoupholdinganystandardsofcraftsmanshipwhatsoever.
Goonandmakethings! Makesmallthings,makebigthings.
Maybe someday you’ll experience the pain when your pet project turns into your main
project, which turns into production software, which is buggy because it didn’t have
testsortypes—notlikeI’vebeenthereoranything…
LackofUnitTests
It’snotalwayspossibletohavetestsforeverything,because,well…life.
In that case, I’d say that if you don’t have unit tests, the next best thing you could have is
compile-timecheckingwithTypeScript.
However,compile-timecheckingisnotasubstituteforhavingunittests. Thegoodthing
is that unit tests can be written in any language- so the argument for TypeScript here is
irrelevant. What’simportantisthattestsarewritten,andweareconfidentaboutourcode.
Startups
Definitelyusewhateverhelpsyoubecomeproductive.
Atthistime,thelanguageyouchoosemattersalotless.
Themostimportantthingforyoutodoistovalidateyourproduct.
Choosing a language (Java, for example) or a tool (like Kubernetes) that you heard would
help you scale in the future, while being totally unfamiliar with it and needing to spend
timelearning,mayormaynotbethebestoptioninthecaseofastartup.
Dependingonhowearlyyouare,themostimportantthingforyoutodoistobeproductive.
InPaulGraham’sfamousarticle,ThePythonParadox,hismainpointisthatstartupengi-
neersshouldjustusethetechnologythatmaximizestheirproductivity.
54

Overall, in this case, use whatever you’re most comfortable with: types or no types. You
canalwaysrefactortowardsabetterdesignonceyouknowyou’vebuiltsomethingpeople
actuallywant.
WorkingonTeams
Depending on the size of your team and the frameworks you’re using, using TypeScript
mightbeamakeorbreakkindofthing.
Largeteams
Whenteamsaresufficientlylarge(becausetheproblemsaresufficientlylarge),it’sagood
reasontouseanopinionatedframework,likeAngularforthefront-end,andTypeScriptfor
thebackend.
Thereasonwhyusinganopinionatedframeworkisbeneficialisbecauseyoulimitthenum-
berofpossiblewaysforpeopletoaccomplishsomething. InAngular,there’sprettymuch
one main way to add a Route Guard, use Dependency Injection, hook up Routing, Lazy-
Loading,andReactiveForms.
ThehugebenefithereisthattheAPIiswellspecified.
WithTypeScript,wesavemassiveamountsoftimeandmakecommunicationefficient.
Theabilitytoquicklydeterminetherequiredargumentsanditsreturntypefor
anymethod,ortheabilitytoexplicitlydescribeprogramintentthroughpublic,
private,andprotectedvariablesaloneareincrediblyuseful.
Yes,someofthisispossiblewithJavaScript,butit’shacky.
Communicatingpatterns&implementingdesignprinciples
Not only is it hard to express program intent without explicit types, but design patterns,
thesolutionstocommonlyoccurringproblemsinsoftwarearemoreeasilycommunicated
throughexplicitstrictly-typedlanguages.
Here’saJavaScriptexampleofacommonpattern. Seeifyoucanidentifywhatitis.
// audioDevice.js
class AudioDevice {
constructor () {
this.isPlaying = false;
this.currentTrack = null;
}
play (track) {
this.currentTrack = track;
this.isPlaying = true;
this.handlePlayCurrentAudioTrack();
}
55

| handlePlayCurrentAudioTrack |                      |     | () {           |     |         |
| --------------------------- | -------------------- | --- | -------------- | --- | ------- |
| throw                       | new Error(`Subclasss |     | responsibility |     | error`) |
}
}
| class Boombox | extends | AudioDevice |     | {   |     |
| ------------- | ------- | ----------- | --- | --- | --- |
| constructor   | ()      | {           |     |     |     |
super()
}
| handlePlayCurrentAudioTrack |         |             | () {     |     |     |
| --------------------------- | ------- | ----------- | -------- | --- | --- |
| // Play                     | through | the boombox | speakers |     |     |
}
}
| class IPod  | extends | AudioDevice | {   |     |     |
| ----------- | ------- | ----------- | --- | --- | --- |
| constructor | ()      | {           |     |     |     |
super()
}
| handlePlayCurrentAudioTrack |            |          | () {    |     |     |
| --------------------------- | ---------- | -------- | ------- | --- | --- |
| // Ensure                   | headphones | are      | plugged | in  |     |
| // Play                     | through    | the ipod |         |     |     |
}
}
| const AudioDeviceType |            | = { |     |     |     |
| --------------------- | ---------- | --- | --- | --- | --- |
| Boombox:              | 'Boombox', |     |     |     |     |
| IPod:                 | 'Ipod'     |     |     |     |     |
}
| const AudioDeviceFactory |                          | = {        |     |     |     |
| ------------------------ | ------------------------ | ---------- | --- | --- | --- |
| create:                  | (deviceType)             | => {       |     |     |     |
| switch                   | (deviceType)             | {          |     |     |     |
| case                     | AudioDeviceType.Boombox: |            |     |     |     |
|                          | return new               | Boombox(); |     |     |     |
| case                     | AudioDeviceType.IPod:    |            |     |     |     |
|                          | return new               | IPod();    |     |     |     |
default:
return null;
}
}
}
| const boombox | = AudioDeviceFactory |     |     |     |     |
| ------------- | -------------------- | --- | --- | --- | --- |
.create(AudioDeviceType.Boombox);
56

| const | ipod = | AudioDeviceFactory |     |     |     |
| ----- | ------ | ------------------ | --- | --- | --- |
.create(AudioDeviceType.IPod);
IfyouguessedAbstractFactoryPattern,you’reright. Dependingonyourfamiliaritywith
thepattern,itmightnothavebeenthatobvioustoyou.
Let’s look at it in TypeScript now. Look at how much more intent we can signify about
AudioDeviceinTypeScript.
// audioDevice.ts
| abstract    | class | AudioDevice   | {       |          |     |
| ----------- | ----- | ------------- | ------- | -------- | --- |
| protected   |       | isPlaying:    | boolean | = false; |     |
| protected   |       | currentTrack: | ITrack  | = null;  |     |
| constructor |       | () {          |         |          |     |
}
| play              | (track: | ITrack) | : void   | {   |     |
| ----------------- | ------- | ------- | -------- | --- | --- |
| this.currentTrack |         |         | = track; |     |     |
| this.isPlaying    |         | =       | true;    |     |     |
this.handlePlayCurrentAudioTrack();
}
| abstract | handlePlayCurrentAudioTrack |     |     |     | () : void; |
| -------- | --------------------------- | --- | --- | --- | ---------- |
}
Immediateimprovements
• Weknowtheclassisabstractrightaway. WeneededtosniffaroundintheJavaScript
example.
• AudioDevicecanbeinstantiatedintheJavaScriptexample. Thisisbad,weintended
forAudioDevicetobeanabstractclass.
Andabstractclassesshouldn’tbeabletobein-
stantiated,they’reonlymeanttobesubclassedandimplementedbyconcreteclasses.
ThislimitationissetinplacecorrectlyintheTypeScriptexample.
• We’vesignaledthescopeofthevariables.
CurrentTrack
• refers to an interface. As per the Dependency Inversion design prin-
ciple, we should prefer depending on abstractions instead of concretions. This isn’t
possibleintheJavaScriptimplementation.
• We’ve also indicated that any subclasses of AudioDevice needs to implement the
handlePlayCurrentAudioTrack. IntheJavaScriptexample,weexposedthepossibil-
ityforsomeonetointroduceruntimeerrorstryingtoexecutethemethodfromeither
theillegalabstractclassorthenon-completeconcreteclassimplementation.
Takeaway: Ifyouworkonalargeteamandyouneedtominimizethepotentialwayssome-
onecouldmisuseyourcode,TypeScriptisagoodwaytohelpfixthat.
Smallerteams&codingstyles
57

Smaller teams are a lot easier to manage coding styles and communication. Paired with
lintingtools,frequentdiscussionsabouthowthingswillgetdoneandpre-commithooks,I
thinksmallteamscanbereallysuccessfulwithoutTypeScript.
Ithinkthatsuccessisanequationinvolvingthesizeofthecodebaseandthesizeoftheteam.
Asthecodebasegrows,theteammightfindthattheyneedtorelyonsomehelpfromthe
languageitselftorememberwherethingsareandhowtheyshouldbe.
Astheteamgrows,theymightfindtheyneedmorerulesandrestrictionstokeepthestyle
consistentandpreventduplicatecode.
Frameworks
React
MuchofwhatdrawsmeandotherdeveloperstoReactistheabilitytowritecodehowever
youwantandinanelegant/cleverway.
It’struethatReactmakesyouabetterJavaScriptdeveloperbecauseitforcesyoutoapproach
problems differently, it forces you to be aware of how thisbinding in JavaScript works and
enablesyoutocomposelargecomponentsoutofsmallones.
Reactalsoallowsyoutohaveabitofyourownstyle. AndbecauseofthenumberofwaysI
canimplementanygiventask,IwillmostoftenwritevanillaReact.jsappswhen:
• Thecodebaseissmall
• It’sjustmecodingit
AndIwillcompileitwithTypeScriptwhen:
• Morethan3peoplearecodingit,or
• Thecodebaseisexpectedtobeverylarge
Angular
IwillalsooptionallyuseAngularforthesamereasonsIwillcompileReactwithTypeScript.
SummaryonwhentouseTypeScript
Insummary,youshouldconsiderTypeScriptwhen:
• Outofthe3typesofHardSoftwareProblems,yoursis#3,theComplexDomainprob-
lem(meaningthattherealchallengecomesfromyoubeingabletomanagethecom-
plexityofareallycomplexdomain).
• ComplexdomainscallforimplementingDomain-DrivenDesign. TypeScriptisvery
wellsuitedforthat.
• Whenthecodebaseisexpectedtobeexceptionallylargeorlivealonglife.
TypeScriptmakesiteasierto:
• Catcherrorsandexpressexpectedtypes
• Implement design patterns and classical object-oriented programming principles
(JavaScriptdoesn’tevenhaveinterfaces)
58

• WriteSOLIDcode(mucheasierwithTypeScript)
• Whenyou’reworkingonlargeteams.
• Larger teams means considerably more effort required towards communicating
program intent and restricting illegal program behavior. If we use TypeScript, the
compilercanenforcealotofthat. Ifwedon’t, wehavetorelyonlintersandalotof
verbalagreementswithinourteam.
• ThisisalsowhyAngularissopopularintheenterprisecommunity. Becauseit’sopin-
ionatedandthere’sasinglespecificwaytoaccomplishanyonetaskwhereaswithRe-
act,youcouldimplementthesamefeatureinonehundreddifferentways.
UsingJavaScriptisprobablyagoodchoicewhen:
• Theproblemyou’resolvingisnotaComplexDomainproblem.
• Andifitis,onlyagoodideawhenyouandyourteamhaveanexceptionallygoodgrasp
ondomainmodelingusingJavaScript’sprototypalOOPstyleandhowtowriteSOLID
codewithoutthepresenceofinterfacesorabstractclasses.
• Thecodesizeisrelativelysmall.
• It’sjustyouworkingonit.
• You’reinastartupandfindingproduct-marketfitandtractioniswaymoreimportant
thanengineeringsomethingthatmightnotstick.
GettingstartedwithTypeScript
Let’swalkthroughtheprocessofcreatingabasicTypeScriptapplicationandcompilingit.
You’dbesurprisedathowtrivialitis.
Afterwards, we’ll setup a few scripts for cold-reloading in development, building for
production,andrunninginproduction.
ForktheTypeScriptstartercode: YoucanviewandforkthesourcecodethestarterType-
Scriptprojectrighthere.
Prerequisites
• YoushouldhaveNodeandnpminstalled(installhere)
• YoushouldbefamiliarwithNodeandthenpmecosystem
• You have a code editor installed (preferably VS Code, it’s the champ for TypeScript,
installhere)
InitialSetup
Let’screateafolderforustoworkin.
mkdir typescript-starter
cd typescript-starter
Next,we’llsetuptheprojectpackage.jsonandaddthedependencies.
SetupNode.jspackage.json
Usingthe-yflagwhencreatingapackage.jsonwillapproveallthedefaults.
59

npm init -y
AddTypeScriptasadevdependency
Thisprobablydoesn’tcomeasasurprise;)
npm install typescript --save-dev
After we install typescript, we get access to the command line TypeScript compiler
throughthetsccommand. Moreonthatbelow.
InstallambientNode.jstypesforTypeScript
RecallthatTypeScripthasImplicit,Explicit,andAmbienttypes? Ambienttypesaretypes
thatgetaddedtotheglobalexecutionscope. Sincewe’reusingNode,itwouldbegoodifwe
couldgettypesafetyandauto-completionontheNodeAPIslikefile, path, process, etc.
That’swhatinstallingtheDefinitelyTypedtypedefinitionforNodewilldo.
npm install @types/node --save-dev
Createatsconfig.json
The tsconfig.json is where we define the TypeScript compiler options. We can create a
tsconfigwithseveraloptionsset.
npx tsc --init --rootDir src --outDir build \
--esModuleInterop --resolveJsonModule --lib es6 \
--module commonjs --allowJs true --noImplicitAny true
• rootDir: ThisiswhereTypeScriptlooksforourcode. We’veconfiguredittolookin
thesrc/folder. That’swherewe’llwriteourTypeScript.
• outDir:WhereTypeScriptputsourcompiledcode. Wewantittogotoabuild/folder.
• esModuleInterop: If you were in the JavaScript space over the past couple of years,
youmighthaverecognizedthatmodulessystemshadgottenalittlebitoutofcontrol
(AMD,SystemJS,ESModules,etc). Foratopicthatrequiresamuchlongerdiscussion,
ifwe’reusingcommonjsasourmodulesystem(andforNode.jsapps, youshouldbe),
thenweneedthistobesettotrue.
• resolveJsonModule: IfweuseJSONinthisproject,thisoptionallowsTypeScriptto
useit.
• lib:Thisoptionaddsambienttypestoourproject,allowingustorelyonfeaturesfrom
differentECMAscriptversions,testinglibraries,andeventhebrowserDOMapi.We’d
liketoutilizesomees6languagefeatures. Thisallgetscompileddowntoes5.
• module: commonjsisthestandardNodemodulesystemin2019. Let’susethat.
• allowJs: Ifyou’reconvertinganoldJavaScriptprojecttoTypeScript,thisoptionwill
allowyoutoinclude.jsfilesamong.tsones.
• noImplicitAny: InTypeScriptfiles,requireeverytypetohavebeenexplicitly,never
implicitly — typed. Every type needs to either have a specific type or be explicitly
declaredany. Noimplicitany.
Atthispoint,afteryoushouldhaveatsconfig.jsonthatlookslikethis:
60

{
"compilerOptions": {
/* Basic Options */
"target": "es5", /* Specify ECMAScript target version: 'ES3' (default), 'ES5', 'ES2015', 'ES2016', 'ES2017', 'ES2018', 'ES2019' or 'ESNEXT'. */
"module": "commonjs", /* Specify module code generation: 'none', 'commonjs', 'amd', 'system', 'umd', 'es2015', or 'ESNext'. */
"lib": ["es6"], /* Specify library files to be included in the compilation. */
"allowJs": true, /* Allow javascript files to be compiled. */
"outDir": "build", /* Redirect output structure to the directory. */
"rootDir": "src", /* Specify the root directory of input files. Use to control the output directory structure with --outDir. */
/* Strict Type-Checking Options */
"strict": true, /* Enable all strict type-checking options. */
"noImplicitAny": true, /* Raise error on expressions and declarations with an implied 'any' type. *
/* Advanced Options */
"resolveJsonModule": true /* Include modules imported with '.json' extension */
}
}
Forlegibilitypurposes,I’vecleanedupalltheothercommented-outoptions.
Nowwe’resettorunourfirstTypeScriptfile.
Createthesrc/folderandcreateourfirstTypeScriptfile
mkdir src
touch src/index.ts
Andlet’swritesomecode.
console.log('Hello world!')
CompilingourTypeScript
Tocompileourcode,we’llneedtorunthetsccommandusingnpx,theNodepackageexe-
cuter. tscwillreadthetsconfig.jsoninthecurrentdirectory,andapplytheconfiguration
againsttheTypeScriptcompilertogeneratethecompiledJavaScriptcode.
npx tsc
Ourcompiledcode
Checkoutbuild/index.js,we’vecompiledourfirstTypeScriptfile.
"use strict";
console.log('Hello world!');
Usefulconfigurations&scripts
Coldreloadingdevelopmentscript
61

Cold reloading is nice for local development. In order to do this, we’ll need to rely on a
couplemorepackages:ts-nodeforrunningTypeScriptcodedirectlywithouthavingtowait
foritbecompiled,andnodemon,towatchforchangestoourcodeandautomaticallyrestart
whenafileischanged.
Coldvs.Hot-reloading: Coldreloadingiswhentheentireapplicationmustrestartinor-
dertoupdate. Hot reloadingiswhensourcecodechangescanbeappliedagainstthecur-
rentlyrunningprocesswithoutrequiringarestart.
Let’sinstall’emwiththiscommand.
| npm install | --save-dev |     | ts-node nodemon |
| ----------- | ---------- | --- | --------------- |
Addanodemon.jsonconfig.
{
| "watch":  | ["src"],   |                 |     |
| --------- | ---------- | --------------- | --- |
| "ext":    | ".ts,.js", |                 |     |
| "ignore": | [],        |                 |     |
| "exec":   | "ts-node   | ./src/index.ts" |     |
}
Andthentoruntheproject,allwehavetodoisrunnodemon.
Let’saddascriptforthat.
| "start:dev": | "nodemon", |     |     |
| ------------ | ---------- | --- | --- |
Byrunningnpm run start:dev,nodemonwillstartourappusingts-node ./src/index.ts,
watchingforchangesto.tsand.jsfilesfromwithin/src.
Creatingproductionbuilds
Inordertocleanandcompiletheprojectforproduction,wecanaddabuildscript.
rimraf, rm -rf
Install a cross-platform tool that acts like the command (just obliterates
whateveryoutellitto).
| npm install | --save-dev |     | rimraf |
| ----------- | ---------- | --- | ------ |
Andthen,addthistoyourpackage.json.
| "build": | "rimraf | ./build | && tsc", |
| -------- | ------- | ------- | -------- |
Now,whenwerunnpm run build,rimrafwillremoveouroldbuildfolderbeforetheType-
Scriptcompileremitsnewcodetobuild.
Productionstartupscript
build
In order to start the app in production, all we need to do is run the command first,
andthenexecutethecompiledJavaScriptatbuild/index.js.
Thestartupscriptlookslikethis.
| "start": | "npm run | build | && node build/index.js" |
| -------- | -------- | ----- | ----------------------- |
Itoldyouitwassimple!
62

ViewtheStarterProjectsource
Areminderthatyoucanviewtheentiresourcecodeforthishere.
ScriptsRecap
npm run start:dev
Startstheapplicationindevelopmentusingnodemonandts-nodetodocoldreloading.
npm run build
Buildstheappatbuild,cleaningthefolderfirst.
npm run start
Startstheappinproductionbyfirstbuildingtheprojectwithnpm run build,andthenex-
ecutingthecompiledJavaScriptatbuild/index.js.
TypeScriptLanguageFeatures
Since TypeScript has a lot in common with other strictly-typed classical object-oriented
programming languages like C# or Java, you might be familiar with the majority of what
TypeScripthastooffer.
Thenovelaspectofthelanguageishowexpressivethestructuraltypesystemcanbe.
Inthissection,we’llglossoverthemostcommonlanguagefeaturesyou’lluseinyourdevel-
opmenteffortswithTypeScript.
TypeScript Playground: If you’re curious to see what the resulting JavaScript code your
TypeScriptcompilesto,checkoutTypeScriptPlayground.
Basictypes
Object-OrientedProgrammingFeatures
Specialtypes
Basictypes
Wementionedearlierthattypesareannotatedusingthepolishpostfixnotation(though,that
probablydoesn’tmeanmuchtoyou). Justknowthatanytimeyouwanttospecifythetype
ofsomething,it’llappearintheformofa:TypeAnnotation.
Todemonstrate,let’sseeusagewithsomeofthebasicprimitiveTypeScripttypes.
PrimitiveTypes
The primitive types of JavaScript are also primitive types of TypeScript. That’s number,
string,andboolean.
63

Number
| let num:    | number | =      | 12;      |     |     |     |
| ----------- | ------ | ------ | -------- | --- | --- | --- |
| let binary: |        | number | = 0b110; |     |     |     |
num = 55;
binary = '222' // Error - Type "222" is not assignable to type 'number'.
String
Eithersinglequotesordoublequotesareok.
| let firstName: |     | string | = 'Khalil';   |     |     |     |
| -------------- | --- | ------ | ------------- | --- | --- | --- |
| let lastName:  |     | string | = "Stemmler"; |     |     |     |
Youcanalsousethebacktick(‘)andstringembedexpression(${})inordertoembedother
primitives.
| let firstName: |     | string | = 'Khalil';   |     |     |     |
| -------------- | --- | ------ | ------------- | --- | --- | --- |
| let lastName:  |     | string | = "Stemmler"; |     |     |     |
let message: string = `This book was written by ${firstName} ${lastName}`;
Boolean
It’sprettymuchwhatyouwouldexpect.
| let isLoading: |     | boolean  | = false; |     |     |     |
| -------------- | --- | -------- | -------- | --- | --- | --- |
| isLoading      | =   | true;    |          |     |     |     |
| isLoading      | =   | 'false'; | // Error |     |     |     |
Arrays
Youcanusethetype[]formatorthe
TherearetwowaystodeclareanarrayinTypeScript.
Genericformat.
| // Common             | usage.   |     |               |       |            |               |
| --------------------- | -------- | --- | ------------- | ----- | ---------- | ------------- |
| let firstFivePrimes:  |          |     | number[]      | = [2, | 3, 5,      | 7, 11];       |
| // Using              | Generics |     | (more later). | Not   | as common. |               |
| let firstFivePrimes2: |          |     | Array<number> |       | = [2,      | 3, 5, 7, 11]; |
Object-OrientedProgrammingFeatures
TypeScript lets you write code using the traditional object-oriented style (opposed to
JavaScript’s prototypal OOP style) and enables us to utilize familiar object-modeling
constructslikeabstractclassesandinterfaces.
Ifyou’rerustyonyourOOP,that’stotallycool. We’llgetyoubackuptospeedinChapter5
—Object-OrientedProgramming&DomainModeling.
64

Classes
Object-Oriented developers who’ve started with C# or Java will be pleased to find that it
feelsprettysimilarinTypeScript.
| class      | Point | {           |     |           |     |     |     |     |
| ---------- | ----- | ----------- | --- | --------- | --- | --- | --- | --- |
| x: number; |       | // instance |     | variables |     |     |     |     |
y: number;
| constructor |     | (x:  | number, | y: number) |     | { // | constructor |     |
| ----------- | --- | ---- | ------- | ---------- | --- | ---- | ----------- | --- |
| this.x      |     | = x; |         |            |     |      |             |     |
| this.y      |     | = y; |         |            |     |      |             |     |
}
| add    | (point: | Point)           | {   | // method  |     |        |             |     |
| ------ | ------- | ---------------- | --- | ---------- | --- | ------ | ----------- | --- |
| return |         | new Point(this.x |     | + point.x, |     | this.y | + point.y); |     |
}
}
| var p1 | = new         | Point(0,  | 10); |             |     |     |     |     |
| ------ | ------------- | --------- | ---- | ----------- | --- | --- | --- | --- |
| var p2 | = new         | Point(10, |      | 20);        |     |     |     |     |
| var p3 | = p1.add(p2); |           | //   | {x:10,y:30} |     |     |     |     |
Classinheritance
Similartootherlanguages,classesinTypeScriptsupportsingleinheritance. Thismeans
thatwecanusetheextendskeywordtocreateaclasshierarchy,butonlyonceperclass:
| import     | { Point | } from  | './point' |             |     |     |     |     |
| ---------- | ------- | ------- | --------- | ----------- | --- | --- | --- | --- |
| // Point3D |         | extends | the       | Point class |     |     |     |     |
| class      | Point3D | extends | Point     | {           |     |     |     |     |
z: number;
| constructor(x: |     | number, |          | y: number, |     | z: number) | {   |     |
| -------------- | --- | ------- | -------- | ---------- | --- | ---------- | --- | --- |
| super(x,       |     | y); //  | Required |            |     |            |     |     |
| this.z         |     | = z;    |          |            |     |            |     |     |
}
| add(point: |         | Point3D)               | {                 |     |            |     |        |             |
| ---------- | ------- | ---------------------- | ----------------- | --- | ---------- | --- | ------ | ----------- |
| var        | point2D | =                      | super.add(point); |     |            |     |        |             |
| return     |         | new Point3D(point2D.x, |                   |     | point2D.y, |     | this.z | + point.z); |
}
}
Any time we extend a class, the subclass must invoke the parent’s constructor using
super(). Thisisamandatorything,andTypeScriptwillyellatyouifyoudon’tdoit.
Insomelanguages,thefirststatementinaconstructorfromachildclassneedstobeone
65

thatcallssuper().
|     |     | ****InTypeScript, |     | itdoesn’tmatterwhenyoucallsupersolongasyou |     |     |
| --- | --- | ----------------- | --- | ------------------------------------------ | --- | --- |
do.
| constructor(x: |     | number,    | y:   | number, | z: number) | {   |
| -------------- | --- | ---------- | ---- | ------- | ---------- | --- |
| this.z         | =   | z; // this | also | works!  |            |     |
| super(x,       |     | y);        |      |         |            |     |
}
Tryitoutyourself: SeewhattheresultingJavaScriptlookslikeonTypeScriptPlayground.
Staticproperties
TypeScriptalsosupportstheabilitytolabelpropertiesasstatic. staticproperties(this
couldbemembers/attributesormethods)aredifferentinthesensethattheybelongtothe
classthemselves,nottoinstancesoftheclass—objects.
| class       | Player           | {    |     |         |                |     |
| ----------- | ---------------- | ---- | --- | ------- | -------------- | --- |
| static      | instancesCreated |      |     | = 0; // | class variable |     |
| constructor |                  | () { |     |         |                |     |
Player.instancesCreated++;
}
// Static (class) method (only accessible through the class itself)
| public | static | createPlayer |     | (type: | PlayerType): | Player { |
| ------ | ------ | ------------ | --- | ------ | ------------ | -------- |
...
}
// Instance method (only accessible through an instance of the class)
| public | shoot | (): | void { |     |     |     |
| ------ | ----- | --- | ------ | --- | --- | --- |
...
}
}
| var p1                                | = new | Player(); |     |     |      |     |
| ------------------------------------- | ----- | --------- | --- | --- | ---- | --- |
| var p2                                | = new | Player(); |     |     |      |     |
| console.log(Player.instancesCreated); |       |           |     |     | // 2 |     |
p1.shoot();
| Player.shoot(); |         | /*      | ErrorProperty | 'shoot' | does | not |
| --------------- | ------- | ------- | ------------- | ------- | ---- | --- |
| exist           | on type | 'typeof | Player'.      | */      |      |     |
console.log(p1.instancesCreated); /* Property 'instancesCreated' is a
| static | member | of type | 'Player' | */  |     |     |
| ------ | ------ | ------- | -------- | --- | --- | --- |
Instancevariables
There’salotofconfusionaroundwhatwecallsomeofthesethings. Aninstancevariableis
66

anon-staticclassmember/attribute. Theyareaccessibleonlythroughinstancesoftheclass.
Frominsidetheclass,usingthethiskeywordgivesusaccesstotheinstancevariables.
| class Point | {           |     |           |     |     |     |     |
| ----------- | ----------- | --- | --------- | --- | --- | --- | --- |
| x: number;  | // instance |     | variables |     |     |     |     |
y: number;
...
| public          | printCoordinates |            | (): void | {          |     |     |     |
| --------------- | ---------------- | ---------- | -------- | ---------- | --- | --- | --- |
| //              | Accessed through |            | `this`   |            |     |     |     |
| console.log(`x: |                  | ${this.x}, | y:       | ${this.y}` |     |     |     |
}
}
Fromoutsidetheclass,whenworkingwithanobjectcreatedfromthatclass,wecanaccess
instancevariablesusingdot-notation.
| const point | = new | Point(12, | 14); |     |     |     |     |
| ----------- | ----- | --------- | ---- | --- | --- | --- | --- |
console.log(point.x);
console.log(point.y);
Ofcourse,yourabilitytoaccessthesevariablesdependsentirelyontheaccessmodifiersthat
describethescopeofthem.
AccessModifiers
|            |          | public, | private, | protected |            |                 |              |
| ---------- | -------- | ------- | -------- | --------- | ---------- | --------------- | ------------ |
| Typescript | supports |         |          |           | modifiers, | which determine | the accessi- |
bilityofaclassproperty.
67

Apublicmodifieristhemostpermissive.
Whendeclaringapropertyonaclass,ifwedon’t
includeanaccessmodifier,bydefault,TypeScriptassumesthatthepropertyispublic.
| class Person |         | {          |             |     |     |
| ------------ | ------- | ---------- | ----------- | --- | --- |
| name:        | string; | // public, | by default. |     |     |
...
}
Amethodormember/attributewithapublicmodifiercanbeaccessedthrough:
• aninstanceoftheclass(object)
insidethecontainingclass(this)
•
A property with a private modifier can only be accessed from inside the class where it’s
Thismeansthatinstancesoftheclass(objectscreatedusingthenewkeyword)don’t
defined.
havetheabilitytoaccesstheseproperties.
A protected modifier dictates that only the class that defines a protected modifier and
subclassesofthatclasscanaccessit.
ReadonlyModifier
Readonlypropertiesarepropertiesthatcan’tbechangedoncethey’vebeenset. Aread-only
propertymustbeinitializedattheirdeclarationorintheconstructor.
| class Spider |               | {          |         |      |     |
| ------------ | ------------- | ---------- | ------- | ---- | --- |
| readonly     | name:         | string;    |         |      |     |
| readonly     | numberOfLegs: |            | number  | = 8; |     |
| constructor  |               | (theName:  | string) | {    |     |
| this.name    |               | = theName; |         |      |     |
}
}
Interfaces
Interfaces allow us to declare the structure of classes and variables. For a class or a vari-
abletobedeemedvalidtothetypespecifiedbytheinterface, itneedstoincludeallofthe
propertiesandmethodsincludedintheinterfacedefinition.
| interface     | Coordinate |         | {             |          |             |
| ------------- | ---------- | ------- | ------------- | -------- | ----------- |
| latitude:     | number;    |         |               |          |             |
| longitude:    |            | number; |               |          |             |
| dateCreated?: |            | Date;   | // Properties | can also | be optional |
}
const coordinate: Coordinate = { latitude: 42.122, longitude: -28.241 }
OnereallyinterestingthingtonoteaboutinterfacesinTypeScriptisthattheydon’tcom-
piletoanythinginJS.
SoifwetookthefollowingTypeScriptcode:
68

| console.log('No |            | coordinate |     | interface | exists'); |     |     |     |
| --------------- | ---------- | ---------- | --- | --------- | --------- | --- | --- | --- |
| interface       | Coordinate |            | {   |           |           |     |     |     |
| latitude:       | number;    |            |     |           |           |     |     |     |
| longitude:      |            | number;    |     |           |           |     |     |     |
}
| console.log("See, |     |     | it's | not there.") |     |     |     |     |
| ----------------- | --- | --- | ---- | ------------ | --- | --- | --- | --- |
…andranitthroughaTypeScriptcompiler, theresultingJavaScriptcodewouldlooklike
this:
| console.log('No   |     | coordinate |      | interface    | exists'); |     |     |     |
| ----------------- | --- | ---------- | ---- | ------------ | --------- | --- | --- | --- |
| console.log("See, |     |            | it's | not there.") |           |     |     |     |
Classesimplementinginterfaces
Whenaclassimplementsaninterface,fortheclasstobecomplete,itneedstoincludeallof
themembersdefinedintheinterface(whetherthatbepropertiesormethods).
| interface | ILogEvents |     | {      |          |     |     |     |     |
| --------- | ---------- | --- | ------ | -------- | --- | --- | --- | --- |
| logger:   | Logger;    |     |        |          |     |     |     |     |
| logEvent: | (event:    |     | Event) | => void; |     |     |     |     |
}
| class DomainEvents |         |          | implements | ILogEvents |     | {   |     |     |
| ------------------ | ------- | -------- | ---------- | ---------- | --- | --- | --- | --- |
| logger:            | Logger; |          | // logger  | property   |     |     |     |     |
| constructor        |         | (logger: |            | Logger) {  |     |     |     |     |
| this.logger        |         | =        | logger;    |            |     |     |     |     |
}
| logEvent | (event: |     | Event) | : void | { // | same method | signature | as  |
| -------- | ------- | --- | ------ | ------ | ---- | ----------- | --------- | --- |
this.logger.log(event); // logEvent: (event: Event) => void */
}
}
Interfacesextendinginterfaces
Uniquefromotherlanguageswithinterfaces,interfacescanactuallyextendoneormore
interfaces.
| interface | ICircle |         | {   |     |     |     |     |     |
| --------- | ------- | ------- | --- | --- | --- | --- | --- | --- |
| readonly  | id:     | string; |     |     |     |     |     |     |
| center:   | {       |         |     |     |     |     |     |     |
| x:        | number; |         |     |     |     |     |     |     |
| y:        | number; |         |     |     |     |     |     |     |
},
69

| radius: | number; |     |             |     |          |     |
| ------- | ------- | --- | ----------- | --- | -------- | --- |
| color?: | string; |     | // Optional |     | property |     |
}
| interface | ICircleWithArea |     |         | extends | ICircle | {   |
| --------- | --------------- | --- | ------- | ------- | ------- | --- |
| getArea:  | ()              | =>  | number; |         |         |     |
}
| const circle3: |     | ICircleWithArea |     |     | = { |     |
| -------------- | --- | --------------- | --- | --- | --- | --- |
id: '003',
| center:  | {            | x: 0, | y: 0 | },    |            |     |
| -------- | ------------ | ----- | ---- | ----- | ---------- | --- |
| radius:  | 6,           |       |      |       |            |     |
| color:   | '#fff',      |       |      |       |            |     |
| getArea: | function     |       | ()   | {     |            |     |
| return   | (this.radius |       |      | ** 2) | * Math.PI; |     |
},
};
Generics
Genericsareincrediblyuseful.
The key motivation for generics is to document meaningful type dependencies between
| members. | Thememberscanbe: |     |     |     |     |     |
| -------- | ---------------- | --- | --- | --- | --- | --- |
• classinstancemembers
• classmethods
• functionarguments
• functionreturnvalue
Let’slookatanexampleofaqueuethatthrowsmebacktomyJavadaysofcreatingAbstract
DataTypes.
| interface | Queue<T> |       | {          |     |     |     |
| --------- | -------- | ----- | ---------- | --- | --- | --- |
| data:     | T[];     |       |            |     |     |     |
| push:     | (t:      | T) => | void       |     |     |     |
| pop:      | () =>    | T |   | undefined; |     |     |     |
}
Bythisdeclaration,wecancreateaQueueof numbers,strings,Monkeys,Dealies,andany
othertypewewant.
| interface | Monkey  |     | {   |     |     |     |
| --------- | ------- | --- | --- | --- | --- | --- |
| name:     | string; |     |     |     |     |     |
| color:    | string; |     |     |     |     |     |
}
| class MonkeyQueue |           |     | implements |     | Queue<Monkey> | {   |
| ----------------- | --------- | --- | ---------- | --- | ------------- | --- |
| data:             | Monkey[]; |     |            |     |               |     |
70

| constructor |           |     | () {  |     |     |     |
| ----------- | --------- | --- | ----- | --- | --- | --- |
|             | this.data |     | = []; |     |     |     |
}
| push | (t: | Monkey) |     | : void { |     |     |
| ---- | --- | ------- | --- | -------- | --- | --- |
this.data.push(t);
}
| pop | ()     | : Monkey           |     | | undefined | {   |     |
| --- | ------ | ------------------ | --- | ----------- | --- | --- |
|     | return | this.data.shift(); |     |             |     |     |
}
}
ConvenienceGeneric
Here’sacommonusecase. Considertryingtoapplytypestovaluescomingintofromthe
internetandhandledbyawebcontroller.Usually,whenwepasscomplexobjectstobackend
applications,theycomebackasrawstrings.
| type      | CreateUserRequestDTO |         |     | =   | {   |     |
| --------- | -------------------- | ------- | --- | --- | --- | --- |
| userId:   |                      | string; |     |     |     |     |
| email:    |                      | string; |     |     |     |     |
| password: |                      | string; |     |     |     |     |
}
| class | CreateUserController |     |     |     | {   |     |
| ----- | -------------------- | --- | --- | --- | --- | --- |
...
| public |               | handleRequest    |     | (    |                  |     |
| ------ | ------------- | ---------------- | --- | ---- | ---------------- | --- |
|        | req:          | express.Request, |     | res: | express.Response |     |
| ):     | Promise<void> |                  |     | {    |                  |     |
const createUserDTO: CreateUserRequestDTO = req.body; /* Error,
|     | not | assignable |     | to 'CreateUserRequestDTO' |     | */  |
| --- | --- | ---------- | --- | ------------------------- | --- | --- |
}
}
OnethingIliketodoishandlemarshalltherawstringintoaJSONobjectandthenapply
thetypetoitusingaParseUtilsclasswithaparseObject<T>method.
| export | class      |             | ParseUtils     | {                |             |     |
| ------ | ---------- | ----------- | -------------- | ---------------- | ----------- | --- |
| public |            | static      | parseObject<T> |                  | (raw: any): | T { |
|        | let        | returnData: |                | T;               |             |     |
|        | try        | {           |                |                  |             |     |
|        | returnData |             | =              | JSON.parse(raw); |             |     |
|        | } catch    | (err)       |                | {                |             |     |
|        | throw      | new         | Error(err);    |                  |             |     |
71

}
return returnData;
}
}
Wecanusethisby:
type CreateUserRequestDTO = {
userId: string;
email: string;
password: string;
}
class CreateUserController {
...
public handleRequest (
req: express.Request, res: express.Response
): Promise<void> {
const createUserDTO: CreateUserRequestDTO = ParseUtils
.parseObject<CreateUserRequestDTO>(req.body);
}
}
Abstractclasses
abstractclassescanbethoughtofasanaccessmodifier. Wepresentitseparatelybecause
opposedtothepreviouslymentionedmodifiers,itcanbeonaclassaswellasanymember
oftheclass. Havinganabstractmodifierprimarilymeansthatsuchfunctionalitycannotbe
directlyinvoked,andachildclassmustprovidethefunctionality.
• abstractclassescannotbedirectlyinstantiated. Instead,theusermustcreatesome
classthatinheritsfromtheabstractclass.
• abstract members cannot be directly accessed, and a child class must provide the
functionality.
Specialtypes
Let’sgetintothefunones. Here’sacollectionofsomeofthemostcommonusefulfeatures
ofTypeScript.
Typeassertions
Similartotypecasting,whenwehaveabetterunderstandingofwhataparticulartypemight
bethanthecompilerdoes,wecanassertthetype. Thishelpsthecompilerfigureouthowto
dealwiththattype.
72

| const       | friend  | = {};     |           |     |          |             |     |
| ----------- | ------- | --------- | --------- | --- | -------- | ----------- | --- |
| friend.name |         | = 'John'; | /* Error! |     | Property | 'name' does |     |
| not         | exist   | on type   | '{}' */   |     |          |             |     |
| interface   | Person  |           | {         |     |          |             |     |
| name:       | string; |           |           |     |          |             |     |
| age:        | number; |           |           |     |          |             |     |
}
| const       | person | = {}      | as Person; |     |     |     |     |
| ----------- | ------ | --------- | ---------- | --- | --- | --- | --- |
| person.name |        | = 'John'; | // Okay    |     |     |     |     |
The“type”keyword
InTypeScript,thereareseveraldifferentwaystospecifythetypeofsomething. Youcantype
avariable,parameter,orreturnvalueusingaclass,interface,orthetypekeyword.
Let’ssaywewereworkingonafeaturetocreateauser. Wecandefineatypethatcontains
everythingweneedinordertodothat.
| type CreateUserRequestDTO |         |         | =   | {   |     |     |     |
| ------------------------- | ------- | ------- | --- | --- | --- | --- | --- |
| userId:                   | string; |         |     |     |     |     |     |
| email:                    | string; |         |     |     |     |     |     |
| password:                 |         | string; |     |     |     |     |     |
}
| function | createUser |     | (request:  | CreateUserRequestDTO): |        |     | User { |
| -------- | ---------- | --- | ---------- | ---------------------- | ------ | --- | ------ |
| // Do    | things     | to  | create and | return                 | a user |     |        |
}
Looksgood. Watchthis,though. Wecanachieveexactsamethingusinganinterface.
| interface | CreateUserRequestDTO |         |     | {   |     |     |     |
| --------- | -------------------- | ------- | --- | --- | --- | --- | --- |
| userId:   | string;              |         |     |     |     |     |     |
| email:    | string;              |         |     |     |     |     |     |
| password: |                      | string; |     |     |     |     |     |
}
| function | createUser |     | (request:  | CreateUserRequestDTO): |        |     | User { |
| -------- | ---------- | --- | ---------- | ---------------------- | ------ | --- | ------ |
| // Do    | things     | to  | create and | return                 | a user |     |        |
}
Not only that, but because TypeScript is structurally typed, we could also use a class. This
worksaslongastheclasspropertiesstructurallyequivalenttothemembersoftheinterface
| ortype. | Checkitout.          |         |         |     |     |     |     |
| ------- | -------------------- | ------- | ------- | --- | --- | --- | --- |
| class   | CreateUserRequestDTO |         |         | {   |     |     |     |
| public  | userId:              |         | string; |     |     |     |     |
| public  | email:               | string; |         |     |     |     |     |
73

| public | password: |     | string; |     |     |     |     |     |
| ------ | --------- | --- | ------- | --- | --- | --- | --- | --- |
constructor (userId: string, email: string, password: string) {
| this.userId   |     | =   | userId;     |     |     |     |     |     |
| ------------- | --- | --- | ----------- | --- | --- | --- | --- | --- |
| this.email    |     | =   | email;      |     |     |     |     |     |
| this.password |     |     | = password; |     |     |     |     |     |
}
}
/**
| * The | class | members | for | CreateUserRequestDTO |     |     | looks | like: |
| ----- | ----- | ------- | --- | -------------------- | --- | --- | ----- | ----- |
*
* {
| //        | ====      | Public   | properties  |      |          |          |             |     |
| --------- | --------- | -------- | ----------- | ---- | -------- | -------- | ----------- | --- |
| userId:   |           | string;  |             |      |          |          |             |     |
| email:    | string;   |          |             |      |          |          |             |     |
| password: |           | string;  |             |      |          |          |             |     |
| //        | ====      | Class    | constructor |      |          |          |             |     |
| //        | Remember, |          | a type      | is   | valid    |          |             |     |
| //        | even      | if it    | has         | more | than the | required | attributes. |     |
| new:      | ()        | => User; |             |      |          |          |             |     |
}
**/
| function | createUser |     | (request: |     | CreateUserRequestDTO): |        |     | User { |
| -------- | ---------- | --- | --------- | --- | ---------------------- | ------ | --- | ------ |
| // Do    | things     | to  | create    | and | return                 | a user |     |        |
}
Classproperties: Theentiretyofmembers(attributes)andmethodsofaclassaretheclass
properties.
typeoverinterfacesor
Thisdoesbegthequestionofwhenwemightconsidertheuseof
| classes? | Iftheycanalldothesamething,whybotherusingtypeatall? |     |     |     |     |     |     |     |
| -------- | --------------------------------------------------- | --- | --- | --- | --- | --- | --- | --- |
Unlikeaninterface,typealiasescanbeusedtocreatemorecomplextypes.
// Primitive
| type Name | =   | string; |     |     |     |     |     |     |
| --------- | --- | ------- | --- | --- | --- | --- | --- | --- |
// Tuple
| type Data | =   | [number, | string]; |     |     |     |     |     |
| --------- | --- | -------- | -------- | --- | --- | --- | --- | --- |
// Object
| type PointX |     | = { x: | number; |     | };  |     |     |     |
| ----------- | --- | ------ | ------- | --- | --- | --- | --- | --- |
| type PointY |     | = { y: | number; |     | };  |     |     |     |
74

| // Union             | (Or              | - At     | least           | one required)    |        |      |     |
| -------------------- | ---------------- | -------- | --------------- | ---------------- | ------ | ---- | --- |
| type IncompletePoint |                  |          | =               | PointX | PointY; |        |      |     |
| // Extends           |                  | (And     | - All required) |                  |        |      |     |
| type Point           |                  | = PointX | & PointY        |                  |        |      |     |
| const                | pX: PointX       |          | = { x:          | 1 };             |        |      |     |
| const                | incompletePoint: |          |                 | IncompletePoint  | = { x: | 1 }; |     |
const point: Point = { x: 1 } // Error Property 'y' is missing
|     |     |     |     | // in       | type '{ x: | number;   | }' but |
| --- | --- | --- | --- | ----------- | ---------- | --------- | ------ |
|     |     |     |     | // required | in type    | 'PointY'. |        |
TypeAliases
Typescanrefertoprimitivedatatypes. Sometimesthismakessensetodoinordertomake
yourcodemoreexpressiveandintention-revealing.
| type BandName |     | =   | string; |     |     |     |     |
| ------------- | --- | --- | ------- | --- | --- | --- | --- |
Wecanaliasjustaboutanyexistingtype. Forexample,checkouthowwecancreateanalias
Jobs.
foranarrayof
| class       | Job {  |         |         |     |     |     |     |
| ----------- | ------ | ------- | ------- | --- | --- | --- | --- |
| public      | title: |         | string; |     |     |     |     |
| constructor |        | (title: | string) | {   |     |     |     |
| this.title  |        | =       | title;  |     |     |     |     |
}
}
| type JobCollection |       |               | = Job[]; | // Alias      | for an array | of  | jobs |
| ------------------ | ----- | ------------- | -------- | ------------- | ------------ | --- | ---- |
| const              | jobs: | JobCollection |          | = [];         |              |     |      |
| jobs.push(new      |       | Job("Software |          | Developer")); |              |     |      |
| jobs.push(12)      |       | //            | Error    |               |              |     |      |
UnionType
TypeScriptallowsustocreateatypefromoneormoretypes. Thisiscalledauniontype.
| type Password |     | =   | string | | number; |     |     |     |
| ------------- | --- | --- | ------ | --------- | --- | --- | --- |
TheuniontypeworkslikeaconditionalOR.Inordertopasstypechecking,avariablemust
conformtoatleastoneofthetypesdefinedintheunion.
| type Password |     | =                   | string | | number; |     |     |     |
| ------------- | --- | ------------------- | ------ | --------- | --- | --- | --- |
| let password  |     | = "secretpassword"; |        |           |     |     |     |
75

| password | = 1234354 |     |     |     |     |     |
| -------- | --------- | --- | --- | --- | --- | --- |
password = true // error - Password isn't assignable to type 'boolean'.
IntersectionType
Theintersectiontypeisatypethatcombinesallofthepropertiesofoneormoretypes.
| interface | PointX | {   |     |     |     |     |
| --------- | ------ | --- | --- | --- | --- | --- |
x: number;
}
| interface | PointY | {   |     |     |     |     |
| --------- | ------ | --- | --- | --- | --- | --- |
y: number;
}
| type Point          | = PointX | & PointY; |         |        |     |     |
| ------------------- | -------- | --------- | ------- | ------ | --- | --- |
| const initialPoint: |          | Point =   | { x: 0, | y: 0 } |     |     |
const incompletePoint: Point = { x: 0 } // Error Property 'y' is missing
|     |     |     |     | // in type  | '{ x: number;     | }' but |
| --- | --- | --- | --- | ----------- | ----------------- | ------ |
|     |     |     |     | // required | in type 'PointY'. |        |
Enum
Anenumisawaytoorganizeacollectionofrelatedvalues. Thisisalanguagefeaturethat’s
commoninotherprogramminglanguagesbutwasneveraddedtoJavaScript. TypeScript,
however,hasthisfeature.
Usagelookslikethis,similartootherlanguages:
| enum Instrument | {   |     |     |     |     |     |
| --------------- | --- | --- | --- | --- | --- | --- |
Guitar,
Bass,
Keyboard,
Drums
}
| let instrument    | =                | Instrument.Guitar;    |           |                      |     |     |
| ----------------- | ---------------- | --------------------- | --------- | -------------------- | --- | --- |
| instrument        | = "screwdriver"; |                       | /* Error! | Type '"screwdriver"' |     |     |
| is not assignable |                  | to type 'Instrument'. |           |                      |     |     |
*/
It’sreallyinterestinghowenumsworkunderthehood. SinceeverythingwewriteinType-
ScripthastobecompiledtovalidJavaScript,lookwhattheresultingJavaScriptlookslike
fortheInstrumentenumafteritgoesthroughthecompiler.
76

var Instrument;
| (function                         | (Instrument)   | {   |                    |
| --------------------------------- | -------------- | --- | ------------------ |
| Instrument[Instrument["Guitar"]   |                |     | = 0] = "Guitar";   |
| Instrument[Instrument["Bass"]     |                |     | = 1] = "Bass";     |
| Instrument[Instrument["Keyboard"] |                |     | = 2] = "Keyboard"; |
| Instrument[Instrument["Drums"]    |                |     | = 3] = "Drums";    |
| })(Instrument                     | || (Instrument | =   | {}));              |
The first line in the function block says Instrument[Instrument["Guitar"] = 0] =
"Guitar";.
It’ssaidthat:
"Guitar"is0AND
• Thevalueof
0is"Guitar"
• Thevalueof
Thisresultsinthefollowingobject:
/**
* {
| * 0:        | "Guitar",   |     |     |
| ----------- | ----------- | --- | --- |
| * 1:        | "Bass",     |     |     |
| * 2:        | "Keyboard", |     |     |
| * 3:        | "Drums",    |     |     |
| * Guitar:   | 0,          |     |     |
| * Bass:     | 1,          |     |     |
| * Keyboard: | 2,          |     |     |
| * Drums:    | 3           |     |     |
* }
*/
Thatmeansthatenumsare,bydefault,number-based. Weaccessthatfirstiteminthelist
witheitherInstrument[0]orInstrument.Guitar.
Ifyoudon’tlikeusingnumbers,alternatively,wecaninitializeenumswithstrings.
| enum Instrument | {             |     |     |
| --------------- | ------------- | --- | --- |
| Guitar          | = 'GUITAR',   |     |     |
| Bass            | = 'BASS',     |     |     |
| Keyboard        | = 'KEYBOARD', |     |     |
| Drums           | = 'DRUMS'     |     |     |
}
Any
anyisatypethatwecanbeusedwithalltypes. Anythingcanbeassignedtoany,andwecan
assignanythingwithany. Weoftenuseanywhenwewanttoopt-outoftypecheckingforthe
moment.
| let anything: | any = | 'any is now | a string'; |
| ------------- | ----- | ----------- | ---------- |
| anything      | = 5;  |             |            |
77

| anything                             | = false; |                    |            |        |
| ------------------------------------ | -------- | ------------------ | ---------- | ------ |
| anything.aMethodThatMightNotExist(); |          |                    | /* If this |        |
| doesn't                              | exist at | runtime and we try | to call    | on it, |
| it will                              | throw an | error. */          |            |        |
InlegacyprojectsmigratingtoTypeScript, it’snotuncommontotemporarilytypethings
asanybeforeaddingmorespecifictypesovertimeduringrefactoring.
Void
voidistheabsenceofhavinganyreturntype. Formethodsthatreturnnovalue,it’sagood
practicetotypethemasvoidexplicitly.
| function | executeCommand | (name: string): | void | {   |
| -------- | -------------- | --------------- | ---- | --- |
console.log(`Executing ${name}`);
}
| executeCommand('Say |     | hello'); |     |     |
| ------------------- | --- | -------- | --- | --- |
There’sanobject-orienteddesignprincipletitledCommand/QuerySegregationthatspec-
ifiesthatamethodshouldbeeitheracommandthatchangesthesysteminsomewaybut
returnsnovalue,ORaquerythatreturnsavaluebutcausesnoside-effects.Wetalkmoreabout
thisprincipleinSection4-DesignPrinciples.
Inline&LiteralTypes
Sometimes, instead of defining an entire interface for a type, you might feel inclined just
to define the type inline. Take the following example where we might receive an update
to a field name of a (string primitive) Literal Type of either "email", "password," or
"phonenumber".
| function | onUpdate | (   |     |     |
| -------- | -------- | --- | --- | --- |
props: { fieldName: 'email' | 'password' | 'phonenumber', value: any }
| ): void | {   |     |     |     |
| ------- | --- | --- | --- | --- |
// Possibly => { fieldName: 'email', value: 'me@khalil.com' };
// Or maybe => { fieldName: 'password', value: 'secretpassword' };
this.setState({
...this.state,
| [fieldName]: |     | value |     |     |
| ------------ | --- | ----- | --- | --- |
})
}
Literal Types like in the example shown above are most commonly used with the union
typetocreatequickabstractions.
Here’sanothercommonone. Whataboutthishashtableof number’stostring’s?
| const | GenreType | = { |     |     |
| ----- | --------- | --- | --- | --- |
1: "Metal",
78

2: "Rap",
3: "Pop"
}
Howdoweproperlydefinethetypeforthis? Easy. Here’saninlinetypethatsaysthatevery
keyofthisobjectisanumber,andthevalueisastring.
| const | GenreType: | {   | [index: | number]: | string | } = { |     |
| ----- | ---------- | --- | ------- | -------- | ------ | ----- | --- |
1: "Metal",
2: "Rap",
3: "Pop"
}
And now we get the type safety to reach inside of the hash-table properly. This pattern
comesifwewanttousethefactorypatterntocreatedomainmodelsfromapre-determined
listofpossiblevariations.
| const | GenreType: | {   | [index: | number]: | string | } = { |     |
| ----- | ---------- | --- | ------- | -------- | ------ | ----- | --- |
1: "Metal",
2: "Rap",
3: "Pop"
}
| interface | GenreProps |     | {   |     |     |     |     |
| --------- | ---------- | --- | --- | --- | --- | --- | --- |
id: number;
| description: |     | string |     |     |     |     |     |
| ------------ | --- | ------ | --- | --- | --- | --- | --- |
}
| class   | Genre  | {              |     |     |     |     |     |
| ------- | ------ | -------------- | --- | --- | --- | --- | --- |
| private | props: | GenreProps;    |     |     |     |     |     |
| get     | id (): | number         | {   |     |     |     |     |
| return  |        | this.props.id; |     |     |     |     |     |
}
| get    | description |                        | (): string | {   |     |     |     |
| ------ | ----------- | ---------------------- | ---------- | --- | --- | --- | --- |
| return |             | this.props.description |            |     |     |     |     |
}
| constructor |     | (props:  | GenreProps) |     | {   |     |     |
| ----------- | --- | -------- | ----------- | --- | --- | --- | --- |
| this.props  |     | = props; |             |     |     |     |     |
}
}
| function | createGenreFromGenreId |         |      |     | (id: number): | Genre | null | {   |
| -------- | ---------------------- | ------- | ---- | --- | ------------- | ------------ | --- |
| if (id   | <                      | 1 || id | > 3) | {   |               |              |     |
// It's not great to return null like this, but we'll keep it
| //  | simple | for | now. |     |     |     |     |
| --- | ------ | --- | ---- | --- | --- | --- | --- |
79

| return | null; |     |     |     |     |     |
| ------ | ----- | --- | --- | --- | --- | --- |
}
| return | new Genre({ | id, | description: |     | GenreType[id] | })  |
| ------ | ----------- | --- | ------------ | --- | ------------- | --- |
}
TypeGuards
TypeGuardsallowyoutonarrowdownthetypeofanobjectwithinaconditionalblock.
TypeofGuard
Usingtypeofinaconditionalblock,thecompilerwillknowthetypeofavariabletobediffer-
Inthefollowingexample,TypeScriptunderstandsthatoutsidetheconditionalblock,x
ent.
mightbeaboolean,andthefunctiontoFixedcannotbecalledonit.
| function   | example(x:    | number        | |   | boolean) | {   |     |
| ---------- | ------------- | ------------- | --- | -------- | --- | --- |
| if (typeof | x             | === 'number') |     | {        |     |     |
| return     | x.toFixed(2); |               |     |          |     |     |
}
return x.toFixed(2); // Error! Property 'toFixed' does not exist on type 'boolean'.
}
InstanceofGuard
Similarconditionalcheckingispossibleusingtheinstanceofguard.
Wecanconditionally
ruleouttypepossibilitiesbyassertingifaclassisorisnotaninstanceofaparticularclass.
| class  | MyResponse | {         |     |     |     |     |
| ------ | ---------- | --------- | --- | --- | --- | --- |
| header | = 'header  | example'; |     |     |     |     |
| result | = 'result  | example'; |     |     |     |     |
// ...
}
| class   | MyError {  |           |     |     |     |     |
| ------- | ---------- | --------- | --- | --- | --- | --- |
| header  | = 'header  | example'; |     |     |     |     |
| message | = 'message | example'; |     |     |     |     |
// ...
}
| function | example(x: | MyResponse  |     | | MyError) | {   |     |
| -------- | ---------- | ----------- | --- | ---------- | --- | --- |
| if (x    | instanceof | MyResponse) |     | {          |     |     |
console.log(x.message); // Error! Property 'message' does not exist on type 'MyResponse'.
| console.log(x.result);  |            |       |      | // Okay |         |     |
| ----------------------- | ---------- | ----- | ---- | ------- | ------- | --- |
| } else                  | {          |       |      |         |         |     |
| //                      | TypeScript | knows | this | must be | MyError |     |
| console.log(x.message); |            |       |      | // Okay |         |     |
console.log(x.result); // Error! Property 'result' does not exist on type 'MyError'.
}
}
80

InGuard
Theinoperatorchecksfortheexistenceofapropertyonanobject.
| interface Person | {   |     |     |
| ---------------- | --- | --- | --- |
| name: string;    |     |     |     |
age: number;
}
| const person: | Person | = { |     |
| ------------- | ------ | --- | --- |
| name: 'John', |        |     |     |
age: 28,
};
| const checkForName | =   | 'name' in person; | // true |
| ------------------ | --- | ----------------- | ------- |
ChapterSummary
Youmadeit!ThatconcludesourdiscussionabouttheTypeScriptlanguageinparticular.We
| coveredalotsofar. | Youlearned: |     |     |
| ----------------- | ----------- | --- | --- |
• The design decisions of TypeScript and the shortcomings TypeScript was meant to
solveimproveinJavaScriptdevelopment
• WhichtypesofJavaScriptprojectsTypeScriptisbestsuitedfor
• HowtoinstallandsetupaTypeScriptproject
• TypeScriptlanguagefeaturesandthebasicsinordertobedeadly
Resources
• TheDefinitiveTypeScriptHandbook
• TypeScriptDeepDive
• Javascript,theGoodParts
References
• WhyisJavaScriptsopopular?
• TheTragedyofCraftsmanship
• TypeScriptOfficialSite
• WhyTypeScript?
| 3. Clean | Code |     |     |
| -------- | ---- | --- | --- |
Introduction|Cleancodeisyourgripstrength
Ifyou’reintoliftingweights,oneofthefirstthingsweshoulddoisworkonourgripstrength,
thoughnotmanypeopledo. Withoutpropergrip,we’renotgoingtobeabletolifttheheavy
weights,andwecouldenduphurtingourselves.
81

The equivalent of hurting yourself in programming before you get to the heavy weights is
writinguncleancode. Uncleancodeisalotofthings. Askanydeveloper,andthey’llhavealot
tosay.
Offthetopofthedome,uncleancodeis:
• codewecan’tunderstand
• codethatmakesithardtofindwhatwe’relookingfor
• codethat’spoorlyformatted,untestable,andinflexible
• codethatyouandyourteamareafraidtochange
• expensive
Uncleancodekillscompanies.Uncleancodekillscompaniesbecauseitresultsinre-work,
wastedtimeandmoney,andfailedprojects.
Clean code is your grip strength. It’s your form. We need to get it right before we take on
the heavyweights. Writing clean code is not everything, but it’s worth investing in learning
howtodoright—buildinggoodhabits,followingwell-knownbestpractices,andbeingre-
sponsible. It’sworthyourdedicationtodaybecauseuncleancodecompoundsandbecomes
unmanageablelater.
We’re going to arm you with the correct mindset, a set of coding conventions, and the
abilitytounderstandtheconsequencesyourdesignsmayhaveinthefuture.
Withtheseoutoftheway,whenwefinallygettotheheavyweights(thosechallengingyet
fulfillingproblemstosolve),writingcleancodewillfeelaseasyandnaturalasbreathing.
Solet’sgetthatgripstrengthupforthosecriticalsituations;lifters,weallknowyoudon’t
wantaone-waytriptosnapcity.
ChapterGoals
Thischapterisbrokenupintofourparts:
Understandingcleancode
• Fullyunderstandwhatitmeansforcodetobeclean.
• Learnagradualandprincipledapproachtoendlesslyimprovingyourabilitytowrite
codethatisbothstructuredandeasytoworkwithusingthePillarsofCleanCode.
Developermindset
Tooptimizeoureffortsgoingforward,let’sfosterthebestdevelopermindsetpossible.
82

• Understand what makes programing a trade, the goals behind the software crafts-
manshipcodeofhonor,andwhyweneedastandardizedsetofwaystobuildsoftware.
• Producebetterdesignsbycultivatingagrowthmindset, utilizingpositiveandnega-
tivefeedbackeffectively,andacknowledgingimperfectionsasgrowthopportunities.
• Learnhowtoincreasethediscoverabilityofyourdesignswiththeessentialhuman-
centereddesignprinciplestraditionallyonlyusedbyUXdesigners.
Cleancodingconventions
Thissectioncontainsaseriesofguidesthatteachesyouthemostessentialcodingconven-
tions. Armed with a craftsmanship, growth, and design thinking mindset, these conven-
tionsshouldactasabaseline. Fromhere,yourstylecanevolveandgetmoreopinionatedas
youexperimentandlearnintherealworld.
Each guides aims to improve one or more of the following positive code characteristics:
maintainability,testability,andflexibility.
• Learnhowtodecoderequirements,planaproject,andprovideincreasinglyaccurate
estimates(maintainability,testability,flexibility).
• Learnformatting&stylebestpractices(maintainability).
• Learnhowtowriteusefulcommentsandwhentoavoidwritingcomments(maintain-
ability).
• Learnhowtonamethings(maintainability).
• Learnhowtoorganizethings(maintainability).
• Learnhowtopreventillegalerrorstatesandelegantlyhandleerrorsandexceptions
(maintainability).
• Learntestingapproaches(testability).
• Learn architecture basics and designing to accommodate future changes (flexibility,
maintainability).
• Learntosafelyandeffectivelyrefactorcode(maintainability).
Skills&knowledge
Togetbetterthroughoutyourcareer,youmustrefineyourcodingconventions.
Yourrefinementscomefromtwoplaces: yourskills(newlanguages,tooling,frameworks)
andknowledge(designpatterns,principles,approaches,experience,andjudgement).
Thissectionistherestofyourcareer,andit’saboutmovingyouclosertowisdom.
• Learnwhytheonlymajorinnovationleftinsoftwaredesignistooling.
• Learntheessentialtoolingyouneedtohaveinyourdevelopertoolboxasafull-stack
developer.
• Learn how to determine if you’re dealing with absolute complexity or relative com-
plexity.
• Learnhowtodetectcodesmells&anti-patterns.
• Learnwhentooptimizecode.
Understandingcleancode
Everyoneseemstohavetheirownopinionaboutwhattheydeemtobecleancode.
83

I’ve heard clean code described as plainly as when “everything is organized nicely”. I’ve
heardittakeamoreopinionatedformas“codewithtests”. AndI’vehearditreferredtoas
everythingelseinbetween.
Clean code is, in part, about code cleanliness. It is about following best practices, making
codereadable,andwritingitsothateverypositivestructuralandstylistictraityoucanassign
toacodebaseisevident.
Evenasexhaustiveasthatsounds,itstillbarelyscrapesthesurface.
Theterm,cleancode,isincrediblyoverloaded.
Cleancodeisanoverloadedterm
Weexpectcleancodetobereadable,understandable,flexible,testable,andmaintainable.
That’salottoaskrightoffthebat(especiallyifit’sthefirststepinoursoftwaredesignand
architectureroadmap.
Asaneworjuniordevelopernowbeingpaidtowritecodeinaprofessionalsetting, the
processoflearninghowtowritecleancodeisbothdauntingandsomewhatnebulous.
It’sdauntingbecauseyourprofessionalreputationridesondoingagoodjob,andit’sneb-
ulousbecausetellingsomeonetowritecleancodeisliketellingsomeonetojustrunfaster—
it’snoteasytojuststartdoingeitherofthosethings.
Inthischapter, I’lll introduceyoutoThePillarsof CleanCode. It’s aguided approachfor
learninghowtowritecleancode.
1. (cid:0)Developermindset
2. (cid:0) Codingconventions
3. (cid:0)(cid:0)Skills&knowledge
Ultimately, you get your head right, develop a set of principled coding conventions, and
improvethemovertimebylearningtheessentialsoftwaredesignprinciplesandpatterns.
Soundsstraightforwardenough,right?
Beforewediveintotheframework,Iwanttoshowyousomething.
To better appreciate the work we’re about to do, let’s discover just how vast this topic of
cleancodereallyis. Ithastakenmeseveralmonthsandattemptstotamethismaterial.
Let’sstartbyhearingouttheopinionsofdevelopersandexpertsfromthecommunity. Let’s
seewhattheythinkcleancodeis.
Whatthecommunitythinksaboutcleancode
Iopenedthefloodgates.
Bythat,ImeanIwentonTwitterandaskedthegeneralpublicfortheiropinionsonwhat
theythinkconsistutescleancode.
84

• Seethreadhere.
Whatfollowsareabunchofreallygoodopinions. Ican’tsayIdisagreewithanyoneofthem.
Butassomeonewhowantstolearnhowtowritecleancode,withoutaframework,youmay
haveahardtimenarrowingdownactionablenextsteps.
Communityopinions
“Clean code is easy to read and modify, reveals the intended purpose without
anyobfuscation,andspeaksaclearandconsistentdomainlanguage.”
Mythoughtsandcomments:
• Easytoreadcouldbeacommentonnamingthings. Itcouldalsobeacommentabout
howthecodeisphysicallystyledandformattedtobeeasilyreadbyhumans.
• Easytomodifycouldbeacommentoncodebeingabletorefactorcodewithoutbreaking
it.
• Revealstheintendedpurpose could mean that the names of the classes, methods, func-
tions,andvariablesaresounderstandablethattheycouldn’tbenamedanybetter.
“[Cleancodeis]easytoread. Italsohasnocouplingbetweenlibraries.”
Mythoughtsandcomments:
• Humanreadabilityisundoubtedlyoneofthemostimportantconsiderations. Inthe
coding conventions section of this chapter, we discuss human-centered-design and
explorewhatmakescodereadableandwhatdoesn’t.
• Thesecondcommentisfascinating. Itpointstosomethingmorearchitectural. Admit-
tedly, it’sabitofarabbithole-butthey’reright. Coupling, packaging, dependency
inversion,separationofconcerns,anddecomposition. Theseareallaboutenforcing
architectural boundaries and keeping dependencies at a distance. It’s funny. Cou-
plingisn’toneofthefirstthingsyouthinkaboutintheconversationofcleancode,but
it’snottobeexcluded. ThesectionaboutSkill&knowledgearguesthatbeingprinci-
plediswhatfostersbettercodingconventions. Tome, thisneverends. Ifyou’vegot
yourDeveloperMindsetright,you’realwaysinvestinginyourknowledge,pickingup
best practices, and learning ways to handle common problems in software develop-
ment. Forexample,tokeeplibrariesormodulesdecoupled,we,asanindustry,have
establishedwell-knownapproachestodealwiththisexactscenario.
“Ittellsagoodstoryaboutthedomain,anditisevolvable.”
85

• Evolvablesoftwarwe. Yes. Wepreviouslyestablishedthisasoneoftheprimarygoals
ofsoftware: Thegoalofsoftware. Changeisaconstant. We’realwaysgoingtoneed
tochangecode. Solet’sdesignitinawaytoaccommodatechange.
• Tellingthestoryofthedomainmeansthatnewdeveloperscanlearnhowthebusiness
worksbyreadingthecode. Codethatencapsulatestheessenceofthebusinessisde-
sirable. InChapter5-Object-OrientedProgramming&DomainModeling,welearn
howtoseparatetheconcernsofourappandcarveoutadomainmodeltobuildacodi-
fiedversionofthebusiness. Regardlessofifwedothatornot,namingthingsbytheir
real-lifecounterpartsmakesforagoodnamingconvention.
“Icanreaditwithoutitbeingpainful. Icanchangeitwithoutitbreakingevery-
thing.”
• Readability! Again! Butalso,howdowewritecodethatenablesotherstochangeit
withoutbreakingthings? Tests,thatis. Speakingoftests…
“Generallyspeaking,cleancodeistestablecode!”
Easiersaidthandone! Inherentlytestablewritingcodeisn’tobvious. Atleast,itwasn’tto
mewhenIfirststarted. Icertainlydoagreewiththisstatement,though.
“Tome,it’skindalikeart. Asakid,IusedtolookatPicassoandthink: Icando
thisstuff. Now,Ilookatit,andit’sgenius. Ireallycan’ttellwhy…sureIlearned
moreaboutshapes,colors,etc. Butit’smorethanjusttechnique. Codingisthe
same.”
I’mcarefulcomparingcleancodetoart,eventhoughthisisacharmingwayoflookingatit.
Makingsomethingappearsimpleoftentakesalotmoretime,effort,andexperiencethanit
doestomakesomethingappearcomplex.
Cleancodeisasmuchanartasaplumberinstallingatoilet,oranautomechanicperforming
anoilchangeis. Cleancodeiscodewrittenprofessionally. Themainthemeofprofession-
alism? Takingresponsibility.
“Nomagic. Simplicity.”
Thisechoessimplicityovercomplexity. Icouldsayalotmorehereaswell:
• Sometimesthesolutioniscomplicatedbecausetheproblemiscomplicated. Acciden-
tal and Essential Complexity is a way to determine if the problem is difficult, or we
makeitdifficult.
“KISS,DRY,YAGNI,POLA,DIP,ideallyfacilitatedbyTDD”
Keep it simple, silly, Do Not Repeat Yourself, You Aren’t Gonna Need It, Principle of Least Astonish-
ment,DependencyInversionPrinciple,andgoodol’Test-DrivenDevelopment. Yeah,thisisdefinitely
somedesignprinciple-soup. Buthonestly,ifyouknowwhateachoftheseare,evenifyou
choosenottofollow’em,havingtheminthebackofyourmindwhilecodingcanimprove
thestructuralqualityofyourdesigns.
“Easilyreplaceable.”
How do youdesign code tobe replaceable? By making it simple, readable, and providing
tests. UsingtheSOLIDprinciplesalsohelps.
86

Andthelastone,
“CodethatIdon’tcurseit’screator”
Yeah-we’veallbeenthere…
Whattheexpertsthinkaboutcleancode
Alright,nowlet’slookathowafewoftheexpertsinourindustryeloquentlydescribeclean
code.
“The cost of ownership for a program includes the time humans spend to un-
derstandit. Humansarecostly,sooptimizeforunderstandability”. —Mathias
Verraes
Mathiashasafantasticpointhere. Haveyoueverstartedonanewprojectinanewdomain
andhadtogetrampeduponanexistingcodebase? Theamountoftimeittakesforyouto
contributecodeisdirectlyrelatedtohowunderstandableitis. Understandability,aswe’ll
learn—isanotherwaytosaydiscoverability,in*designer-*terminology.
“Codeislikehumor. Whenyouhavetoexplainit,it’sbad”. —CoryHouse
PerhapsCoryistakingastanceinthetopicofleavingcommentsincode. Iscodethatrequires
commentsclean? Isitdirty? Ihaveanprincipledargumentforthis,andyou’llhearabout
itsoonenough.
“Clean code always looks like it was written by someone who cares. There is
nothingobviousyoucandotomakeitbetter.” —MichaelFeathers
Perhapsthefirstthingtodiscussisn’tactuallyhowtowritecleancode. Instead,maybewe
shouldstartwithdeterminingifyou’reintherightheadspacetoadvocateforcodecleanliness,
craftsmanship,andyourfellowhumanbeings. Ifyou’reapatheticaboutthequalityofthe
code and future maintainers’ ability to maintain your code, we might want to get to the
bottomofthatfirst. SectionOne-DeveloperMindsettalksaboutthisinmoredetail.
“Programmingistheartoftellinganotherhumanwhatonewantsthecomputer
todo.” —DonaldKnuth
Consider where you were when you first started coding. Remember wading through bad
code(writtenbyyouandmaybeothers)? Rememberallthestruggleyouwentthroughin
ordertojustmakeitwork? Rememberhowgooditfelttofinallygetsomethingtoworkafter
flippinglinesbackandforthforhours? IalwaysthinkbacktomyJavadaysinuniversity…
Isatonthatcouchforhours.
Ifyoufeellikeyou’reflippinglinesbackandforth,justthrowingmorecodeontosomething
untilitworks,you’reprobablywritingcodeforthecomputer,inwhichcase,wecanassume
itwillnotbesomethingahumanbeingfindspleasureinreading.
The more I learn about what makes a project successful, the more I believe we should be
writingcodeforhumansovercomputers. Onceyou’reproficientwithaprogramming
languageandnolongerfightingwiththesyntaxorhowtoaccomplishcommonthings,fig-
uringouthowtomakeyourdesignsoptimizedforhumanscomesnext.
87

“If you want your code to be easy to write, make it easy to read” — Robert C.
Martin
Isn’titfascinatingthatmostoftheopinionsaboutcleancodefromtheexpertshavemore
todowithhumansthantheydoaboutthecode? Callitchance, butthesefolkshavespent
decadesdoingthisstuff.
Consider the fact that you and I spend around ~60% of our time reading code vs. writing it
(Imadethatnumberup). NoonejustletslooseandhammersattheirkeyboardlikeaGod
(Iwish). That’ssimplynotthewayitis. Wearereading(andwriting)thebookofourcom-
pany’ssolutionspace,sotakeyourtimeandwriteitwell.
Howdoesuncleancodegetwritten?
Uncleancodedoesn’tjustsneakuponyoulikeseasonalaffectivedisorder. Herearewaysit
canmanifest.
Whenwetrytogofastwitharbitrarydeadlinesandreallyneedmoretime. Haveyou
everdoneanythingwellbeingrushed? Whenweskipoutonplanningandestimationorfind
ourselvesneedingmoretimeforwhateverreason, insteadofpushingback, werushtotie
uplooseends. Thisusuallyresultsinsuboptimal,andunclean,code.
Whenwedon’twritetests. Youmightcursemeoutforthisone,butIdobelievethatcode
without tests is unclean. Code without tests is code that cannot be refactored or changed
safely. Sincemoretimeisspentonmaintenanceandimprovementthanoninitialdevelop-
ment,andchangeisalwaysaconstantfactor,weneedtofeellikewecanchangecodesafely.
Codeunabletobechangedsafelyinducesunnecessarystressandanxiety. Italsointroduces
risk. Codewithouttestsisbadforyoursanity,andit’scertainlynotclean.
Whenwedon’tcare aboutthedomain. Ican admit tothis one. Sometimesyoujust re-
allydon’tgiveas**tabouttheprojectyou’reworkingon. It’slesslikelythenextpersonwill
beabletomaintainthecodeifwedon’tcareaboutthedomain,andthenamesdon’treflect
thebusiness. Languageisahugepartofsoftwaredevelopment. Notonlymightnamesbe
bad,butcodeendsupinthewrongplace,abstractionsgetmissed,architecturalboundaries
areillegallycrossed,andthingsinevitablybecomeamess.
Whenwedon’tcareaboutthelongevityofaproject. Igetit. Tosome,programmingis
justsomethingyoudoforapaycheck. Maybeyougotpinchedthrowingtogetherawebsite
foryouraunt. Let’sgetitdone,andgetthehellout,right? That’saveryirresponsibleway
tolookatyourjob.
Whenwedon’tcareaboutcraftsmanship. Whenwedon’tcareaboutcraftmanship,any
solutionwilldo. Ifthecodeworks,it’sgoodenough. Whenwedon’tcareaboutcraftsman-
ship, a mess that works is acceptable. When we don’t care about craftsmanship, we won’t
challengeourpeers’codewithconstructivecriticism. Potentialconflictisscary,andpeople
thatdon’tcareaboutcraftsmanshipwon’tfigureoutwaystohaveproductivediscussions.
Apatheticdeveloperswilltoleratebadcodetostaycomfortable.
Whenwe’retooscaredtohavedesigndiscussionswithotherdevelopers. Socialanxi-
etyisalltoocommonintheprogrammingindustry. It’sreallyunfortunate,becausesome
88

ofthebestdesigndecisionsI’veevermadecamefrommorethanonedeveloper’spointof
view. ThroughtoolslikeSlack,GitHub,andJira,wecanbuildcollaborationintotheprocess
ofdevelopingsoftware-butittakeswill-power.
Whenwetrytogetcleverwithourcode: Iusedtousethe~(tilde)operatorinJavaScript
becauseitmademefeelreallycoolandclever. Whatdidn’tmakemefeelcoolandcleverwas
watchingmyteammatesavoidthecodeIwrotebecausetheycouldn’tunderstandit.Writing
cleancodeoftenmeansavoidingfinesse,evenwhenit’sfuntodoso.
When we our design skills are poor: There are two groups of people that we’re writing
softwarefor: ourusers,andourselves(you,yourteammates,futuremaintainers). Aswe’ve
saidseveraltimesthroughoutthedurationofthisbooksofar(andwe’reonlyinChapter3),
makingcodeworkonceisn’tenough—theinitialcompletionofaprojectisonlythestarting
point. Designdictatesiftheprojectcanendureornot: anddesignreliesonempathy,abasic
understandingofthepsychologybehindhowwelearnhowtousethings,andhowtomake
thingsdiscoverableandunderstandable. Theyain’tteachin’thatinschool,bub.
When systemic issues in the way we educate developers exists. We live in a society
that prefers to take a reactive approach to dealing with problems rather than a preventative
one. Likemostpolitics,alastingchangetothisproblemishardandstraightforwardways
to fix them in the real-world don’t exist. If developers are making messes in production
codebases,potentialwaystofixitareto: a)investinbetteronboardingandtraining,b)ed-
ucatedeveloperswithpracticalcodingskillsinpost-secondaryeducationandboot-camps,
c)standardizepracticalcodingskillsinsteadofnewtechnologies.
Thesearesomeofthemainwaysuncleancodegetswritten.
Longstoryshort,it’saboutpeople.
Twolawsofsoftwaredevelopmentmaintenance
SunMicrosystems,thecompanybehindJava,hasthefollowingtosayaboutmaintenance
ofasoftwareproject:
• 40%–80%ofthelifetimecostofapieceofsoftwaregoestomaintenance.
• Hardlyanysoftwareismaintainedforitswholelifebytheoriginalauthor.
Wow.
Ifthat’strue,thentheimportanceofwritingcleancodeisparamount.
Whyit’shardtolearncleancode
Reason1—Humansarecomplex
Clean code has been hard to describe, learn, and teach because it aims to bring structure
aroundwritingcodeforhumans,andhumansarecomplex.
There’salotthatwestilldon’tunderstandabouthowwe—humanbeings,work.
Thehumanbrainisstillverymuchabigmysterytous. Forexample,wedon’tunderstand
jackabouthowthebrainprocessesvisualinformation(AllenInstitute.org).
89

“Imagine you have to infer who loves whom, who backstabs whom, and what’s going on
fromjusta1200x1200pixelimage”. Humansaresocomplexthatwestilldon’tfullyunder-
standhowourbrainscomeupwithanswerstothesequestions.
Reason2—It’shardtodeconstructhumanpsychology
Whilewecanapplyreductionismtocomputingandengineering,tryingtodothesamewith
humansishard(andalmostalwayswrong). Thisislikelywhyformaleducationhaschosen
toavoidattemptingtoteachusthefollowingtopics:
• Howtonamethings
• Howtoorganizecode
• Howtodecideonandenforcecodingconventions
• Howtodetectcodesmells
• Howtoavoidanti-patterns
Theanswertoeachofthesequestionsisdeeplyrootedinourunderstandingofhu-
manpsychology,notsomethingthatcaneasilybetackedintoacollegecuriculum.
Designerslearnthebasicsofhumanpsychology,buttraditionally,softwaredevelopersdon’t. I
believeweshould. Ibelievesoftwaredevelopersarealsosoftwaredesigners.
Reason3—Tradeskillsareacquiredthroughmentorship
Formaleducationisagreatthing.
They excel at teaching us scientific and theoretical aspects of computer science; but that’s
becausetheyconsidermodernprogrammingtobeascienceorengineeringdiscipline,not
atrade.
Thepracticaltricksofthetrade;the**thingsyou’llspend90%ofyourtimedoinginthereal-
world, like testing, refactoring, and design, **are passed down to us. These learnings are
passeddowninapprenticeships,onlinetraining,co-ops,orjuniordevelopmentjobswith
accesstomentorsandmoreexperienceddevelopers.
Thethreepillarsofcleancode
Developermindset,codingconventions,andskills&knowledge
ThePillarsofCleanCodeisaframeworkIdevelopedtoilluminateeachcomponentinvolved
inlearningtowritecleancode.
Itbreakstheentiretopicofcleancodeintothreeparts:
90

(cid:0)Developermindset
Professionalism,growthfromfailure,anddesignthinking.
(cid:0) Codingconventions
Developing your own set of principled coding conventions to produce the highest
qualitysoftwarepossibleusingindustrystandards,tools,approaches,andmethods.
(cid:0)(cid:0)Skills&knowledge
Become wise. Refine your preferred coding conventions from your skills (new
languages, tooling, frameworks) and knowledge (design patterns, principles,
approaches,experience,andjudgement).
Historically,conversationsaboutcleancodewereeitherexclusivetooneofthesethreepil-
larsortoovaguetoexpresstheintricaciesofhowtheyintertwine.
SectionOne-Developermindset
Summary
Professionalism,growthfromfailure,anddesignthinking.
SoftwareCraftsmanship
BeforeIwasaprogrammer,Iwasalandscaper.
When I was in my teens, with my stepdad’s help, I started my own lawn care business to
raisefundsformyfirstfewyearsofuniversity. IcalleditStepsLawnCare. Itwasaproper
91

name for the company since my stepdad would teach me the basics and Shepard me to a
levelwhereIfeltcomfortableenoughtodojobsindependently.
Istartednotknowingthefirstthingaboutlandscaping.
Forthefirstfewweeks,wewentoutanddidjobstogether. Iwatchedhowheworked,helped
outwhereIcould,andaftertime,Iwasdoingitallbymyself.
Eventually,throughguidanceandcorrection,Ilearnedhowtofindclients,scopeajob,pro-
videanestimateforthework,andsatisfythecustomerbygettingthejobdonewellandon
time.
Mystepdadtaughtmehowtobeaprofessionalinthatindustry.
I’verealizedthatmentorship,thewayIlearnedthetradeoflandscaping,hasbeenprac-
ticedforgenerations,andstemsbacktomedievalEuropewithmastersandjourneymen.
“Thecarpenter”-byAnonymousartist(http://www.digibib.tu-bs.de/?docid=00000286,
PublicDomain,https://commons.wikimedia.org/w/index.php?curid=981584)
Back in those days, the master demonstrates the correct way of completing a task; then,
92

thejourneymenattempttoimitatethemaster’sskillsandgetcorrectedforanymistakes.
It’sinterestingtonotethatthismodel, theapprenticeshipmodel, isstillwidelyusedin
traditionaltrades,likesteel-working,baking,plumbing,andsomanymore!
These trades understand the importance of professionalism. They understand it
so much so that through regulation, they’ll make it impossible to go out on your own
until you’ve completed an apprenticeship program: a certain number of hours with a more
experiencedmemberofthetrade.
Programming,ontheotherhand,isanincrediblyyoung(andturbulent)trade.
We expect new developers to learn what it takes to be a professional independently, and
often,there’sadetachmentfromtheoldergenerationofprogrammerstothenewerones.
Not only that, but we’re still in the process of agreeing upon a standard procedure for re-
liablyproducingqualitysoftwareontime(IbelievetheanswerhereisAgile, butwiththe
propertechnicalpracticeslikeXP-ExtremeProgramming).
Imagineifplumberswerestilltryingtofigureouthowtofixleakypipes.
It’snotasurprisethatsomanydevelopersareleftconfused,unequipped,andunguidedto
workprofessionally,becausewe,asanindustry,haven’tconvergedonwhatitmeansto
workprofessionally.
And let’s consider that developers do know the right things to do. Non-technical project
managersmakeithardforus. Theycan’tseethevalueinactivitieslikeTDDorrefactoring
-thatjustlookslikenot-working-on-features.
Ah,challenges.
We might not ever regulate the industry of professional programming. Companies will
stillbestringenttohireprofessionaltrainers. Itisunlikelythatanon-technicalmanager
willurgeyoutoimplementtechnicalbestpractices,andwemightneverintroducemanda-
toryformalapprenticeships(somehavetried-see8thLight).
Butwhatwecandotodayisadoptacraftsmanshipmentality.
Softwarecraftsmanshipisprofessionalisminsoftwaredevelopment.
Softwarecraftsmanshipisanemergingmindsetsharedbyagrowingcommunityofdevelopers
dedicatedtoraisingthebar.
Whilethisguideisaboutsoftwarecraftsmanship,anothertitlecouldhavebeen“howtobe
aprofessionalsoftwaredeveloper”.
Beingaprofessional(orcraftsman—Iconsiderthemtomeanthesamething)meanstak-
ingresponsibilityforourcareers,clients,andcommunity. Italsomeanstakingpridein
oursolutions,workingwithpragmatism,andseekingtoimproveourreputationswiththe
excellentworkwedocontinually.
Professionalismisparamounttowritingcleancode.
93

Thisguidedistillseverythingyouneedtoknowaboutsoftwarecraftsmanship. Bytheendof
thisit,you’llhaveamuchbetterunderstandingof:
• Thehistoryofsoftwaredevelopmenttillnow,andtwoproblemsfromthe60swe’re
stillattemptingtoresolve
• Agile,howitchangedourindustry,andwhyit’simpossibletodorightwithoutsoft-
warecraftsmanship
• Thesoftwarecraftsmanshipmovement,mindset,andresponsibilities
• Howtobecomeasoftwarecraftsman
Abriefhistoryofsoftwaredevelopment
First, tofullyunderstandtheoriginofcraftsmanshipandrespecttheurgencyofwhyyou
shouldstrivetobecomeonetoday, weneedtodiscusssignificantchangesinourindustry
thatleftuswherewearetoday.
Programmingpickingupspeed(50s)
In the 50s, computer science picked up. Around this time, we started to understand the
lifecycleofhowsoftwareisplanned,designed,built,andmaintained.
WecalledthistheSDLC(SoftwareDevelopmentLifeCycle).
94

TheSoftwareDevelopmentLifeCycledemonstratedthegeneralcyclethatsoftwareprojects
operatein.
Until then, we had considered software development to be the act of writing code merely
andfixingbugs. Therewasn’tanyformalprocessaroundhowthisworked. Butin1956,we
cameupwiththeWaterfallmodel: aniterativeapproachtobuildingsoftware.
Inlateryears(towardsthe80sand90s),industryprofessionalswouldcomeupwithother,
againopinionatedwaystowork,likeXP(ExtremeProgramming),FDD(Feature-DrivenDe-
velopment),Scrum,andsoon. Wecallthesesoftwaredevelopmentmethodologies,andtheyact
as a framework to move through each phase in the SDLC, specifically designed to cir-
cumventbadcodingpracticesandpitfalls. Theygiveyourulestofollow.
For example, one rule in XP is that thecustomerwritesacceptancetests, that way, developers
knowwhattheyshouldbuildandcandefinecompletionmoreprecisely.
SoftwareDevelopmentMethodology: Anapproachtosoftwaredevelopment,morespe-
cificthantheSoftwareDevelopmentLifeCycle(SDLC)thatformalizesrules,strategies,and
practicesfordeliveringsoftwareprojects. Examples: XP,FDD,Scrum,Waterfall.
95

But back in the 50s and 60s, processes were pretty stripped down. And so we went on to
learnsometoughlessons.
Thesoftwarecrisisofthe60s-80s
Fromthe60stothe80s,wediscoveredmostofthemajorproblemsinsoftwareengineer-
ingthatwouldcontinuetoplagueustoday;theyarebasedpredominantlyaroundproductiv-
ityandquality.
I’llgiveyouanexampleofeach.
TheIBMSystem/360
In 1964, IBM announced the IBM System/360: the first general-purpose computer system
with cross-compatibility between models. Cross-compatibility was huge back then; the
abilitytofinallyrunthesameprogramsoneverysysteminsteadofneedingtosticktoolder,
slowermodelstoruntheprogramsyouneededforeverydayusewasrevolutionary.
While spectacular when it was finally released, the productivity failure was legendary.
IttookIBManentiredecadeand1000developerstocompletethesystembecausethey
didn’tdevelopasoundandunderstandablearchitecture. Theinfluentialbook“TheMythical
Man-Month”,writtenbyFredBooks,isbasedonhisobservationsmanagingtheIBMsystem’s
development. By1975,aftercompletingtheprojectandpublishingthebook,we,asanin-
dustry,cametolearnthat”addingmanpowertoalatesoftwareprojectmakesitlater”. This
isoneofmanythingswehadtolearnthehardway, astherewerefewprojectsaslargeas
thisbefore.
96

TheTherac-25: amachinethatkilledfourpeople,andlefttwowithlifelonginjuries
Oneofthemostfamousqualityfailuresisthe1986-87storyoftheTherac-25:abuggyradi-
ationtherapymachinethatmalfunctionedanddeliveredtentimestheamountofradiation
acceptable,killingfourpeopleandleavingtwopatientswithlifelonginjuries.
Whathappenedhere? Lots. Thearchitecturewasdoomedfromthestart. Theoriginalde-
veloperwhowrotethecoreoftheapplicationhadneverdoneconcurrencyprogramming,
sothefoundation—theentiresystemdesign,wasmadeextremelyhardtounderstandafter
hehadleftthecompany. Further,tocutcostsandmakethecodeeasiertoreasonabout,de-
velopersremovedthehardwaresafetyfeaturesonpreviousmodels,replacingthemtobecon-
trolledentirelybysoftwaresafetyfeaturesinstead. Thatmeantthatwhenthingswentwrong
becauseofsoftwarebugs,andthemachineexpelledwaymoreradiationthanitevershould
have,therewasnohardwaredetectortoshutthemachinedown. Developersweretooover-
confident. Theydidn’twriteandruntestscapableoftestingcurrency. Evenwhenoperators
discoveredthatpeopleweredyingduetowhatappearedtobeacriticalbug,theengineers
repeatedlyshiftedtheblameoffthemselves. Theyinsistedthatthedeathsweredueto“op-
eratorerror”.
Eventually, a dedicated staff physicist dug deep, found the bug, and reproduced it. AECL,
thecompanybehindthemachine,thenreleasedupdatestofixthebug. Butevenafterthey
fixedit,somemonthslater,someoneelsediedfromabugcausedbyanentirelydifferentissue:
acounteroverflow.
There’s a certain legacy to this event. It’s a perfect demonstration of how poor software
practices,alackoftechnicalexcellence,andprofessionalismtotakeresponsibilitycanresult
interrible(sometimesfatal)outcomes.
ReadmoreabouttheTherac-25inthisarticletitled,“KilledByAMachine”.
Dot-combubble,OOP,andExtremeProgramming(1995–2001)
Wecalledthe90sthedot-comboombecausetheinternetcompletelytookover. Atsome
point, companiesrealizedthatiftheycoulddeliverinternet-basedproductsfaster, they’d
holdamassivecompetitiveadvantage.
Unfortunately, many teams were still using iterative software development methodologies
likeWaterfall,which,inthisnewclimateofneedingfast,quick,andsnappychanges(which
97

wecontinuetoexpecttoday),typicallyledtobothlowproductivityandquality.
ManyWaterfallprojectsstartedstrong,butascustomerrequirementschanged,projectsfelt
morelikedeathmarches.
WhendevelopersprogressedthroughthePlanningphase,toAnalysis,Design,andImple-
mentation,ifnewrequirementscameinlate,wedidn’tknowhowtobestdealwithitother
thanfinishwhatwewereworkingon,andthenstartallover. Iterativeapproachesdidnot
helpuskeepupwiththedemandsofourcustomers. Weoftendeliveredhalf-bakedthings
thatweren’treallywhatourcustomerswanted.
Twoothersignificantthingshappenedaroundthistimeinthe90s.
1. Object-oriented programming became the most popular programming paradigm,
surpassingproceduralprogramming
2. WeformalizedtheExtremeProgramming(XP)methodology
OOPbecamethego-toformanydevelopersbecauseitmadeiteasiertocreatedomainmod-
elsthatmoreacutelycapturedtheessenceofthebusinessandwereeasiertochangeasnew
requirementscamein.
The“CircleofLife”—technicalpracticesinvolvedinimplementingExtremeProgramming
(XP).
AndExtremeProgramming(XP),asoftwaredevelopmentmethodologyandthemostwell-
98

definedandcompleteAgileprocess(beforeAgilewasevenathing), wasformalizedby
KentBeck.XPlaysoutasetofprinciplesandtechnicalbestpracticestosuccessfullymanage
softwareprojectsinthedot-comeraofquicklychangingrequirements.
Agile(2001—today)
Agileisaboutdeliveringvalueincrementallyinsteadofallatonce.
Even though XP was very promising, we used and relied on other methodologies, too
(i.e., Scrum, the Dynamic Systems Development Model (DSDM), Adaptive Software Develop-
ment,Crystal,Feature-DrivenDevelopment,andpragmaticprogramming).
In February 2001, seventeen influential developers, including those who created those
methodologies, agreed to meet up at a ski resort to discuss better software delivery. The
goalwastoseeiftheycouldagreeonsomethingtobewrittendown.
99

ThelistofdeveloperswasKentBeck,MikeBeedle,ArievanBennekum,AlistairCockburn,
Ward Cunningham, Martin Fowler, James Grenning, Jim Highsmith, Andrew Hunt, Ron
Jeffries,JonKern,BrianMarick,RobertC.Martin,SteveMellor,KenSchwaber,JeffSuther-
land,andDaveThomas.
The2001skitripmeetinginUtahthatbirthedtheAgileManifestoandbegananeweraof
softwaredevelopment.
Miraculously, bytheendofthetrip, thegrouphadagreeduponwhatwouldbecalledthe
AgileManifesto,hence,creatingAgileandchangingthetrajectoryofourindustryforever.
TheAgileManifesto
TheAgileManifestoisasfollows:
”Individualsandinteractionsoverprocessesandtools
Workingsoftwareovercomprehensivedocumentation
Customercollaborationovercontractnegotiation
Respondingtochangeoverfollowingaplan
Thatis,whilethereisvalueintheitemsontheright,wevaluetheitemsontheleftmore.”
100

The(Misled)EraofAgile
TherewerenotablechangestotheindustrybecauseofAgile.
The first is the introduction of Agile coaches. Companies would hire Agile coaches to
cometotheirsiteandhelpthemperformanAgiletransformationbyintroducingprocesses
andAgileframeworkslikeScrum. It’simportanttonotethattheseAgilecoachesdidnoth-
ingtohelpdeveloperschangehowtheywritecode—theyjustintroducedprocess.
Thesecondchangeisthatdevelopersbecamegeneralists. Agileintroducedtheneedfor
well-rounded developers. Today, developers need to know how to design, develop across
the stack, set up continuous integration, deployment, databases, and know how to speak
withcustomersandgatherfeedback.
WhileAgilepromisedtheresolutionofqualityandproductivityissues,andmanycompanies
dived right in, fixing their process, but still feeling plagued with both problems. And so
cametheAgile-haters,thedeathmarches,andmorefailedprojects.
Whydidn’tAgilework?
AlotofcompaniesfailedtorealizethatforAgiletowork,twothingsarerequired:
1. Process
2. andTechnicalexcellence
IfAgiledidn’twork,itsbecause#2—technicalexcellence,wasmissing. Andanotherway
tosay“technicalexcellence”istomerelysaycraftsmanship.
101

Teams were too focused on the process-oriented frameworks like Scrum but paid little
attention to technical practices like XP: the original technical Agile framework. Not
onlythat,butnon-technicalmanagersdidn’tseethevalueinXPpracticeslikepairprogram-
ming and Test-Driven Development. “Why have two developers working on the same thing
whenyoucouldbegettingmoredoneworkingseparately?”. “Andyou’retellingmeitmight
take longer for you initially if you do TDD? Then don’t do it.” The top-down, I’m smarter
than you, so do what I say, industrial, factory-worker attitude applied to software developers
has enabled non-technical leaders to successfully push back on things developers see the
long-termvaluein,butthey,themselvesdonotvalueasmuchasdeliveringontime.
ForcompaniestoproperlymigratetoAgile,theyneededtocommittotechnicalbestprac-
tices. WeneededSoftwareCraftsmanship. Themissinglink.
SoftwareCraftsmanship(2006—today)
There had been some talk about Software Craftsmanship before. The first book to hint that
softwaredevelopmentmightbemoreofatradethansciencewas“ThePragmaticProgrammer:
FromJourneymentoMaster.”Itdrewcomparisonsbetweenhowdeveloperslearnandgainse-
niority to the apprenticeship model in medieval Europe, and was one of the first texts to
attempttodistillsomesenseofprofessionalismtowardsoftwaredevelopers.
In2006,PeterMcBreenpublishedthebookSoftwareCraftsmanship,solidifyingthenameand
102

explaining many of today’s professional practices. Unfortunately for the craftsmanship
community around this time, Agile had the limelight. We considered Agile to be the so-
lutiontoourbiggestproblems,givinglittleneedforthisthingcalledSoftwareCraftsmanship.
Tonudgecraftsmanshipbackintothespotlight,in2008,UncleBobproposedafifthvalue
fortheAgileManifesto: “craftmanshipoverexecution”.
Sincethingsweren’tgoingthewaywewantedthemtowithAgile,andbecausewedeviated
fromtechnicalAgileframeworkslikeXP,in2009,agroupofdevelopersaimedtoproduce
somethingtobewrittendownthatcouldconciselydescribethecraftsmanshipmovement.
Eventually, wecreatedtheSoftwareCraftsmanshipManifesto. Themanifesto, printed
below,tooktheAgileManifesto’svaluesandpusheditfurther,promotingtechnicalexcellence
andprofessionalism.
TheSoftwareCraftsmanshipManifesto
Themanifestoisasfollows:
”As aspiring Software Craftsmen we are raising the bar of professional software develop-
mentbypracticingitandhelpingotherslearnthecraft. Throughthisworkwehavecome
tovaluethefollowing:
• Notonlyworkingsoftware,butalsowell-craftedsoftware
• Notonlyrespondingtochange,butalsosteadilyaddingvalue
• Notonlyindividualsandinteractions,butalsoacommunityofprofessionals
• Notonlycustomercollaboration,butalsoproductivepartnerships”
BacktoBasics(XP)
There’s still a lot of work to be done, but today, we’re seeing some positive changes in the
softwareindustryaroundAgileandSoftwareCraftsmanship.
First, the software craftsmanship community is growing. Developers worldwide are
organizingmeetups,signingtheoriginalmanifesto,andspreadingthewordofprofession-
alism. This community may very well be the one that comes together to popularize and
standardizetheapproachtosolvingthat60-yearoldproductivityandqualityproblem.
Secondly,in2019,afrustratedUncleBobpublishedabooktitled“CleanAgile:BacktoBasics”,
whichisalmostentirelyawalkthroughofthetechnicalpracticesofXP.
Ifyouaskmeaboutmyfutureassumptions,Iwouldsaythatinthenextdecadeorso,we’re
goingtoseethecraftsmanshipandAgilecommunitiesconverge,finally. It’sonlyamatter
oftimebeforethishappensbecausetheysharethesamegoal:todeliverhigh-qualitysoft-
wareontime. IcanseewhatitwilltakeforustohavethatfullAgiletransformation. We’ve
seennowthatwecan’tleaveouttechnicalpractices,andthatweneedtobemoredisciplined
aboutstickingtothosepractices.
Nowit’suptous,thesoftwarecraftsmen,todoourpartandsetanexample.
Craftsmanship: Professionalisminsoftwaredevelopment
103

Definition
We’llrepeatthebestdefinitionforsoftwarecraftsmanship.
Craftsmanshipisprofessionalisminsoftwaredevelopment.
SoftwareCraftsmanshipisaprofessionalismmindset. It’snotaboutifyouhaveacertifi-
cation,ifyou’reaseniordeveloper,orifyoupracticeTDDallthetime(thoughyoushould).
It’sabouttakingresponsibilityforourcareers,clients,andcommunity. Italsomeanstak-
ingprideinoursolutions,workingwithpragmatism,andseekingtoconstantlyimprove
ourreputationswiththegoodworkwedo.
Areyouasoftwarecraftsman?
If you do the things mentioned above, you’re already a software craftsman. There’s no
magictoit. Evenifyoudon’tlikethelabelanddon’twanttocallyourselfacraftsman,that’s
totallycool. What’simportantisthatyoudothesethings.
Art,science,engineeringpractice,ortrade
There are valid arguments for software development being an art, science, and engineer-
ingpractice. Ultimately,thesoftwaredevelopmentwedotodayaswebdevelopersisabout
helping clients/companies achieve their goals (make money, save money, protect rev-
enue). Thatispredominantlywhatwedo.
Understandingthemanifesto
Notonlyworkingsoftware,butalsowell-craftedsoftware
It’simportanttorememberthatworkingsoftwarecanalsobesoftwarethat’s:
• hardtochange
• difficulttounderstand
• slow
Butwell-craftedsoftwareissoftwarethatistestable,flexible,andmaintainable. It’salsosoft-
warethatgetsbetterovertime,growinginvalueasyouputmoretimeandcareintoit.
Notonlyrespondingtochange,butalsosteadilyaddingvalue
Addingvalueisn’tjustaddingnewfeaturesorfixingbugs. Youcanconsideraddingvalueto
becontinuallyimprovingthestructureofthecode,keepingitclean,testable,flexible,and
maintainable.Itcostsalotofmoneytoperformre-writes,andthougheveryapplicationhas
alife-span,usually—ifthat’stheonlyfeasibleoptiononarelativelyyoungapplication,it
meanswe’vefailed.
Notonlyindividualsandinteractions,butalsoacommunityofprofessionals
104

We’renotaloneinthis. Yourfellowsoftwaredevelopersaregoingthroughthesamechal-
lengesyouare. Topushthisindustryforward,weneedtoshareknowledge,learnfromoth-
ers,inspire,mentorandpreparethenextgenerationofcraftsmen.
Because software development is so young, and because there isn’tyetastandard for many
things,knowledgesharingandmentorshipareincrediblyimportant. Wedon’twanttolose
whatwe’vediscoveredoverthelast40years. Wewanttokeepqualityhigh, andteaching
lessexperienceddevelopershowtoworkbetterisaformofleveragethatwillpayoffinyears
tocome. Weallhavethisresponsibility.
Notonlycustomercollaboration,butalsoproductivepartnerships
We don’t believe in an employer-employee relationship. That doesn’t exist in software
craftsmanship. Whatever is written on your contract is a formality. Instead, treat your
employer as your customer, the same way you would if you were just a consultant or a
contractor. Someimportantthingstoremember:
• Youremployerisn’tresponsibleforensuringyougrowprofessionally—youare.
• Youremployerisn’tresponsibleforensuringyougetabookbudget,training,orsent
toconferencesandseminars—youare.
• Yourresponsibilityistotreatyouremployerasacustomerandprovidethemwith
excellentservice(advice,consulting,development)andhelpthemachievetheir
goalsbyperforminghigh-valueactivities,evenifthatsometimesdoesn’tmean
coding. Sometimesitmeansgivingatalk,apresentation,orwritingablogpost.
Unfortunately,notallcompanieshaveamanagementstructurethatletsyouworkthisway,
anditcanbehardtopushbackonwhatyoubelieveisbestforthebusinesswhentheother
partydoesn’tseeyouasapartner.
Yourhomework
Stayingtruetothemanifesto,we’rereadytodiscussactionablethingsyoucandotobecome
abettersoftwarecraftsman,startingtoday.
Towritewell-craftedsoftware…
Careaboutwhatyoudo. TheworldreliesontheworkyouandIdo. Thereareatleast4-10
differentcomputersystems(yourearbuds,yourphone,computer,appliances)inanygiven
room. Putloveandcareintowhatyou’redoing,becausemerelyenjoyingtheworkyoudo
increasesthelikelihoodofdoingagoodjob.
Learntechnicalbestpractices.Therearesomethingsthatallprofessionalsoftwaredevel-
opersshouldknowhowtodo,andwhen. TDD,refactoring,pairprogramming,andsimple
designareallessentialtechnicalAgilepracticesfromXP.We’llcovereachoftheseanda
furtherdiscussionaboutXPlaterinthischapter.Followingthat,designprinciples,patterns,
architecturalstyles,etc. —thesearealsoimportant. We’llcovereachofthesethroughout
thebooktoo.
Always be improving yourself. There is always room for improvement. After reading
thisbook,Iencourageyoutograduallyworkyourwaythroughtheresourcesandreadings
105

I’velistedineachchapter. Regularlylearnnewprogramminglanguages, techniques, and
vendors, exchanging the tools in your toolbox for better ones. We’ll discuss this more in
theGrowthMindsetguide.
Knowyourindustry. Wediscussedthebriefhistoryofsoftwaredevelopment,Agile,and
theemergenceofsoftwarecraftsmanship. I’llbethefirsttoadmitthatIhadnoideawhat
Agilewasabout,whattheoriginalgoalswere,andwhatitmeantforustobeAgileatwork.
It’simportanttoknowwhywedowhatwedo. Keepaneyeonemergingtrendsandupdates
tothelibraries,frameworks,andlanguagesyouusetodevelopsoftware.
Learnthedomain. Yourjobistounderstandtheproblemspace(whatyourclientwants
toaccomplish)andcreatethesolutionspace(software). Ifyouknowyourclient’sbusiness,
you’llbetterunderstandtherequirements;thisenablesyoutoaskbetterquestions,under-
stand likely ways that the code may or may not need to change, and contribute to discus-
sionswithdomainexperts. Whenyouknowthedomain,thecodebecomesametaphorfor
whathappensinreallife. InDomain-DrivenDesign,wecallthistheUbiquitousLanguage,to
usethesamewordsfromreal-lifeinthebusiness,inthecode. IfAgileisaboutmakingin-
crementalimprovementsandshortfeedbackloops,wecankeepthecodesimpleandavoid
excessivedesignsbyprojectingwhathappensinreallifetothecode.
Ruthlesssimplicity. Buildpreciselywhatisneededtogetthefeaturetowork. Nomore.
Getafeelforwhenyou’redivergingfromthat, addingmorecodeorunnecessaryabstrac-
tions,andcatchyourself. Themorecodeyouhavetoaccountfor,themorewaysitcango
wrong,andthemoretestsyouneedtowritetoensureitbehavesthewayitshould. Remem-
berYAGNI—YouAren’tGonnaNeedIt.
Practice. Ifyoudon’tuseit,youloseit. AsIadvanceinmycareer,Ifrequentlyfindmyself
doing more writing, speaking, mentoring, and less coding. You need to keep your skills
sharp. Myfavoritewaytopracticeistoworkonside-projectsbasedonyourinterests. Pick
somethingbasedonyourinterests,soyoustaymotivatedanddriventoimproveitasmuch
aspossible. I’mintomusic, soIbuiltavinyl-tradingapp. Anotheroptionistocontribute
toopen-source. Pickalibraryorframeworkyouusefrequently,runthetests,readthedocs,
andtakealookattheopenissues. Notonlyisthisagreatwaytopractice,butyougettosee
howothersdevelopsoftwareinthereal-world.
Tosteadilyaddvalue…
ApplytheBoyScout’sRule. TheBoyScout’sruleistoleavethecampgroundcleanerthan
whenwefoundit. Ifyoucomeacrosssomeconfusing,messy,wrongcodeorcodewithun-
used variables, take some time to refactor it right away. It sets an excellent example for
others, fostering the idea that this codebase is a place of cleanliness and quality. Hopefully,
otherswillclueintothatandgiveittherespectitdeservestoo. Somecallthisthe“Broken
WindowTheory”.
Refactor,guardedbytests. Ifyou’regoingtorefactorsomethingtobecleaner,youshould
firstsafeguarditwithtests. Wesimplycannotsafelychangecodewithoutthepresenceof
tests. Anothergoodtimetoaddtestsandrefactoriswhenwecomeacrossabug. Tofixbugs,
writethetestthatprovesthebug’sexistence,thenrefactorthecodesothatthetestpasses,
andthebugnolongerexists. Overtime,you’llhardenyourappandsleepwellknowingthat
youcanchangecodewithanincreasingamountofsafety.
106

Bebrave. Comingfromtheprevioustwostatements, whenyouseecodeyou’rescaredto
changebecauseyou’rescareditmightbreak,andyou’rescaredofwhatmighthappentoyou
ifyoubreakit,thisisthestartofsoftwarerot. Everyoneelseisalsoscared. Bethebraveone.
Bebravetosurrounditwithtestsandrefactoritintosomethingunderstandable. Testsare
yoursavinggraceandwillenableyoutoactlikeaprofessional.
LearnandapplyXP.It’sthemostextensive,well-thought-outAgilewaytodeliverasoft-
wareproject. Learnitandchallengebureaucracyandnon-technicalmanagementtoap-
ply its technical practices in your day-to-day work. It’s the professional thing to do. See
“Helpthemseethevalueoftechnicalpractices”belowtolearnhowtopitchit.
Delight customers, helping them achieve whatever they want. Their goal is to make
money,savemoney,andpreserverevenuestreams. Yourgoalistohelpthemdoprecisely
that,bytakingtheirfuzzyrequirements,applyingAgilebestpractices(XP),andturningit
intotherightthing.
Engageinthecommunity…
Learnfromothers.There’sonlysomuchyoucanlearnonyourown.Readdeveloperblogs,
books,watchcourses,YouTubevideos,andaskquestions. Dopairprogramming. Somede-
velopersfearpairprogrammingbecausetheotherdevelopermightseethemmakeamis-
take. The best advice is to overcome this fear. Exposing yourself to how another person
thinksandworkscanhelpyoueitherrefineorreconstructthewayyouwork.
Mentor less experienced developers. Unfortunately, we don’t have more software ap-
prenticeships. Whenyougraduatefromcollege,university,orbootcamp,you’reanappren-
tice. You have little experience delivering quality software on time. You may have spent
time learning a front-end JavaScript library or framework, but the professional practices
(TDD,BDD,DDD,etc)aremissing. Craftsmentakeanotefromothertradeslikeplumbing
andcarveouttimetonurturenewcomersintotheprofessionalwayofdeliveringsoftware
products. AtAmazon,juniorsoftwaredevelopers’PRsaresubjecttomanyscrutinies,but
moreexperiencedengineersleaveincrediblyhelpfulcommentsandfeedback(sometimes
100+ comments on a PR). Eventually, junior developers get to the point where their PRs
requirealotlessfeedback,andtheircodegetsmergedin1or2revisionsratherthan10.
Share what you know. Teaching is learning. I’ve learned more than I thought possible
by sharing what I currently knew on the internet. If you’re wrong, someone will eventu-
allycorrectyou. Thisisimportantasanindustrybecauseweneedtopreservewhatwe’ve
learnedovertimesothatotherscanavoidthesamemistakes. Therefore,writeblogposts,
code, share your wins, failures, ideas, tips — these help developers at different stages in
theirjourney.
Socialize with others. I was amazed I could ask for Vaughn Vernon’s advice on Twitter
andactuallygetit. IwasalsosurprisedIcouldrunpolls,askingforwhatdevelopersthink
“cleancode”means,andreceiveaninfluxofopinionstoinformmyownbetter. Twittercan
beabitofastrangeplacesometimes, butasadeveloper, there’salotofvalueinbeingon
the platform. You can network with others by engaging with their content and ideas, and
havingtechnicaldiscussions. Offline,checkoutmeetupsandmeetotherdevelopersusing
tools,technologiesandapproachesyou’reinterestedin.
107

Consideryourselfapartner…
Take responsibility for your own learning. Your employer is not responsible for your
education. Imagineyourdoctorsaying,“yes,Icanhelpcureyou,butI’mgoingtoneedyou
topayformetoreadthesebooksaswell.” Again,thisisareminderthattheworkwedoisa
trade,andit’suptoyoutocarveouttimetoimprove.
Take responsibility for success/failures. It sucks when things go wrong, but it’s noble
totakeresponsibilityandtakeactiontofixsituations. Takingresponsibilityforyourshort-
comingsisanexcellentwaytopreserveyourrelationshipsandreputation. Youwillscrew
up. Trustme. Andreputationmatters,sothatyoucanlandbetterworkandopportunities.
Lookatitthisway, whenthingsgoright, youcantakethepraiseforthataswell, andthat
feelsgood.
Helpthemseethevalueoftechnicalpractices. Howdoyougetyourbosstoseethevalue
inTDDorrefactoring? Don’tpromotethetechnicalpractices;promotethevalueinstead. Peo-
ple won’t change the way their minds unless they can see the value. So then, what’s the
valueofTDD?Well, youcouldtellyourboss, whoisincrediblyaggressiveintryingtoget
you tofinish the project in as littletime as possible, thatTDDreduces the time it takesto
test the system, results in fewer bugs, which means fewer hours coding. And that means
theysavemoney. Youcouldalsotellthemthatwecouldevenreleasesoftwarealotmorere-
liably. Theymightask,“what’sthecostofthis?”. Andthat’sagoodquestiontoaskbecause
everythingcomeswithacost. TDD’scostisthateveryoneneedstoknowhowtodoit,and
that may not be the case. You could offer to learn it and devote an hour to teaching your
teamhowtodosomethingthatwillsavethemhundredsofhoursfixingbugs.
Don’toverworkyourself. Mostpeopledon’tdogoodworkwhenrushed. Mostpeoplealso
don’t do good work when they’re tired. Personally, as soon as I start to feel brain fatigue,
Istepawayfromthecomputerandgoforawalk, exercise, orcallitaday. Why? Because
I know that whatever code comes out of my hands next is more than likely going to be a
liability rather than an asset. You’re only human. My tip? Get the most important work
donewheneveryoufeelmostalertintheday. Forme,that’srightinthemorning. Itmight
bedifferentforyou,though.
Providevalue, evenwhenit’snotcoding. WeexpectdevelopersinanAgile-eraofsoft-
waredevelopmenttoactalotmorelikegeneralists. Soaimtoprovidethemostvaluepossi-
ble,evenifitdoesn’tinvolvecoding. Forexample,youmightseeanopportunitytoimprove
aprocess, pitchthat. Questionrequirementsandmakesurewe’rebuildingwhatweneed.
Spendtimeunderstandingthebusinessandhowitmakesmoney. Helpleadersprioritize
themostcriticalwork. Givethemoptions. Hopefully,youcanseehowvaluablethesethings
are.
Growth
“Notonlyarepeoplewithagrowthmindsetnotdiscouragedbyfailure,butthey
don’tactuallyseethemselvesasfailinginthosesituations—theyseethemselves
aslearning.”
—CarolS.Dweck,Ph. D,authorofMindset: TheNewPsychologyofSuccess
While working as a Junior Software Consultant, I remember feeling visibly annoyed this
108

one occasion I submitted a PR for a feature on an Angular project. The team lead/senior
developerlefttwoorthreecommentsandsuggestionsforrevisionsonjustabouteveryfile
Isubmittedtobemerged.
Imuttered,“Man…Isn’titgoodenough? Ispentsolongonthis. Whatthehell…”
Lookingbackonit,Iunderstandwherehewascomingfrom.
Inthatproject,theentirearchitecture,designedbybothhimandanotherseniordeveloper,
wasconstructedtosolvethehardproblemsusingAngularObservables.
Theirapproachtoarchitecturewaselegant—elegant,buthardtocontributeto. Youcould
saythedeveloperexperiencewaspoor.
Iwasn’tproficientwithObservablessoaftertwohourstryingtolearnthem,Igaveupand
usedPromisesinstead.
InthePR,mostofthecommentsfromtheteamleadweredetailedexamplesofhowtore-
placemyPromiseswithObservable. Today,Iapplaudhisefforttomentorme. Atthetime,
Ididn’tcare-IjustwantedtousePromises.
Lookingback,andafteraconversationwithhimandtheotherdeveloper,Iunderstoodwhy
Iwaswrong.
As the team lead, he had to act as something of a code groundskeeper. He explained the
importanceofaconsistentarchitectureandusingthesamecodingconventionsthroughout
theentiretyoftheproject. Eventhoughtheapproachhadalearningcurve,heintroduced
me to a variety of challenges that his functional approach was well-equipped to solve. It
was then thatI learned about ideal things todo likemaking dataimmutableand pushing
side-effectstoapplicationboundaries.
Thiswasmyintroductiontofunctionalprogramming.
Sincethen, IdecidedI’dreallylistenandtrytounderstandothers’pointsofview. Evenif
youdon’tagree,you’dbesurprisedhowmuchthereistogainbyhearingsomeoneout.
IalsodecidedthatI’mnotscaredofbeingwrong. Ifyouwriteablogpostontheinternetand
it’snottechnicallycorrect,someonewillinevitablycorrectyou. That’svaluable. Failureis
learning.
Twomindsets
Our behavior and relationship with success and failure (both professionally and person-
ally),hastodowithourmindset.
Studieshaveshownthatinthefaceofchallengeandpotentialfailure,peoplehaveeithera
fixedmindsetoragrowthmindset.
Fixedmindset
Peoplewithafixedmindsetassumetheycan’timprove. Theybelievethattheirpersonality,
character,andintelligence,arestaticandcircumstantial.
Thefixedmindsetassumesadeterministicviewoftheworld: it’sjustthewayitis.
109

Growthmindset
People with a growth mindset believe that they can improve by learning. Who they are
today,isnotwhotheyhavetobetomorrow. Theybelievethattheirpersonality,character,
andintelligenceareallfluidandcapableofbeingchangedandenhanced.
Thegrowthmindsetassumesthatnothingdictateswhoweareexceptus—it’sfree
will: andwecanaccomplishspectacularthingsifwelearnfromourmistakes.
Youneedtohaveagrowthmindsetasasoftwaredeveloper
Itreallyshouldgowithoutsaying,butasasoftwaredeveloper—youneedtohaveagrowth
mindset.
We’llneverbeabletoknoweverythingupfront. Weneedtoknowifoursolutionsareclean
andwell-structured,andwegrowbyusingfailureasinformation.
Fixedmindsetasadeveloper
Peoplewithafixedmindsettendtobescaredofchallenge. Perhapstheyonlyworkonthe
front-endbecausetothem,theback-endseemschallenging. Theydreadhavingtheircode
reviewed,theyworkonthesameprojects, usethesametechnologies,andattempttofind
waystodotheexactsamework.
Fixedmindsetdevelopersareafraidoffailure.
They’re afraid of failure because failure means people could figure out who they reallyare.
Andsincewhotheyarecanneverbeimproved(intheirminds),havingtheirfaultsonpub-
licdisplaycanbeaveryheavythingtodealwith. Fixedmindsetdevelopersmakecareful
effortstoonlyplacethemselvesinsituationswheretheycanlooksmart. Thesearethede-
velopersyouseeleavingmeanandpedanticcommentsonStackOverflow.
Fixedmindsetdevelopers:
• Avoidchallengingsituations
• Enterdiscussionstoexpressthemselves,nottopotentiallychangetheirminds
• Developcodeinavacuum,awayfromothers,inanattemptingtoavoidreceivingfeed-
back
• Aren’tgreatlisteners
• Viewnegativefeedbackasapersonalattackontheircharacter
• Giveupeasily
• Don’tputinmoreeffort—sincetheirabilitiesarestatic,moreeffortisawasteoftime
Growthmindsetasadeveloper
Sincepeoplewithagrowthmindsetthriveonfailure,theyconstantlychallengethemselves.
Forthesedevelopers,failureactsasa“springboard”forgrowth,stretchingtheircapabil-
itiesandactingasanopportunitytodiscoverwhattheydon’tknow,andfillinthoseblanks.
Growthmindsetdevelopers:
• Putthemselvesinsituationswheretheyhaveroomtogrow
110

• Involveotherswhentheydevelopcode(pairprogramming,discussions,reviews)
• Aren’tafraidoflookingsillyornotknowingtheanswersup-front(theybelievethey
canfindtheanswers)
• Aregreatlisteners—andalwayschallengetheirownwayofsolvingaproblemusing
someoneelse’sPOV
• Treatnegativecriticismasawaytolearnhowtogrow
• Don’tgiveupeasily
• Seeputtingineffortasthewaytoaccomplishmore
• Aresoftwarecraftsmen
111

GrowthMindset&FixedMindsetbyCarolS.Dweck,Ph.Dhttps://fs.blog/wp-content/
uploads/2015/02/Carol-Dweck-Two-Mindsets.jpg
112

Finalthoughts
Lifeismorefun
The capacity for happiness and fulfillment you can have with a growth mindset is the
biggestreasonwhyIadvocatefortryingtoadoptone. Tome,lifebecomesmorefun. From
a position of privilege, it can be enjoyable to look at the world as a set of systems that we
gettheopportunitytomaster,startingfromnothing.
Craftsmanshiprequiresagrowthmindset
In Craftsmanship, we learned that the mentorship (or apprenticeships, in more formal
trades)oneofthebestwaystowardsmastery. Forthattowork,weneedagrowthmindset.
Answers,bestpractices,andexperiencesthatcansaveyouyearsfiguringitoutforyourself
areheldbymoreexperiencedcraftsmen.
They’reatyourworkplace. They’reinyourcommunity. They’reonTwitter. They’vewritten
books. They write blogs. They’ve documented the Design Patterns they’ve seen. They’ve
helpeddiscoverDesignPrinciples.They’veuncoveredtheunwaveringfundamentalstruths
ofsoftwaredevelopmentoverthelast40years.
Thequestionforyou: areyoureadytolistenandlearnfromwhattheyhavetosay?
DesignThinking
One thing I love about design principles like SOLID, YAGNI, or SoC is that they become
engrainedintoyourprofessionalwayofensuringabaselineofgoodstructuralcodequality.
Ifyoueverrunintosituationswhereyou’renotsureifyou’redoingthingscorrectly,design
principlessitinthebackofyourmindandbecomeavoiceofreason.
Youcanquicklygetagistofifyou’rebreakingsoftwaredesignrulesorbestpractices,what
theimplicationsofbreakingthemare, andwhatyouneedtodotopullthecodebackinto
bestpracticezone.
It’salsogreatbecausesometimesyoutrulydoneedtobreaktherules. Butit’simportantto
firstknowtherulesandtheirimplicationsbeforewecandecidewhentobreak’em.
MartinFowleroncesaid,
“…anyfoolcanwritecodethatacomputercanunderstand. Goodprogrammers
writecodethathumanscanunderstand”(1999).
Wouldn’t it be amazing if there was a principled way for us to write code that could be
understoodbyhumans? Awayforustointrinsicallyunderstandhowtowritecleancode
insteadofjustfollowingsomeoneelse’scodingconventions?
Itturnsoutthereis.
113

Design Thinking is the final topic in the developer mindset portion of the pillars of clean
code.
I’mgoingtointroduceyoutothebasicsofHuman-CenteredDesignhowyoucanuseitto
optimizeyourcodetobeunderstoodbyhumans.
We’regoingtolearnthepsychologybehindhowhumansdiscoverandunderstandwhatcan
bedoneandhowthingswork.
Then,we’regoingtolearnthefundamentalprinciplesofdesign. Justlikehowthereare
software design principles, there are human-centered design principles. We can use them to
optimizethediscoverabilityandunderstandabilityofyourcode.
Armedwiththatknowledge,we’llbeabletodetermineifcodeiscleanandeasytoworkwith,
what makes it challenging to use, and how to improve it so that we can keep the developer
experienceofourcoworkersandfuturemaintainershigh.
ButKhalil,can’tIjustreadUncleBob’s“CleanCode”?
WhileIdorecommendreading“CleanCode”,thisguideisgoingtoactasapartofthephi-
losophy—thebaseline, behindhowwereasonaboutourowncodingconventions. Itwill
thenactasacomplimenttotherestofthestructuralsoftwaredesignprincipleswecoverin
thisbook.
Lastly,afterhavingestablishedthephilosophyhere,itwillbecomepossibleforustohave
principledandempatheticdiscussionstowardsbalancingcodethatisstructurallycorrect
yetstillunderstandabletohumans.
Let’sgetintoit.
Structurevs.DeveloperExperience
Designisapushandpullofpriorities.
Intherealworld, officefurnituremanufacturersneedtoproduceitemsascostefficiently
aspossible. Conversely,consumerswantgoodqualityfurniture,butdon’tatareasonable
price.
Theattributesatoddswitheachotherarecostvs.quality.
Otherimportantbalancestomaintain:
• Note-taking=contextvs.compression.
• Securitysystem=securityvs.easeofconfiguration.
Softwaredesignisnodifferent. Thetwoforcesatplayarestructureanddeveloperexpe-
rience.
Softwaredesignisstructurevsdeveloperexperience.
Structure
The“structuralside”ofsoftwaredesignisthetechnical,computer-science-ystuff. I’mtalk-
ingaboutdoingalltherightthings,followingbestpractices,makingtheimplicit—explicit,
andadheringtodesignprincipleslikeSOLID,YAGNI,PoLS,andSeparationofConcerns.
114

Theseprinciples,whichweexploreindetailinlaterchapters,specificallythedesignprinci-
plesone,helpsuswritemoresturdy,robust,flexible,andtestablecode.
The challenge with structure is that the more we have, the more challenging it can be for
other(especiallynew)developerstobecomeproductiveinourcodebases.
Developerswillfindmorecode,morethingstobeaccountablefor,morethingstoneedto
learn—thisis,overall,ahigherlearningcurve.
Ahigherlearningcurvetypicallymeansittakesmoretimetolearnyouroptionsforcom-
pletingatask,howtoperformtheoption,andhowtoknowifyou’redoingthingscorrectly.
Thisnotagreatdeveloperexperience,atleastinitiallyuntilwefigurethingsout.
DeveloperExperience
WhereUX(userexperience)designisaboutdesigningapplicationsforendusers, DX(de-
veloper experience) design is about designing APIs, tools, languages, frameworks,
andcodebasesfordevelopers.
Andyou’realwayswritingcodeforanotherdeveloperdowntheroad.
Whendevelopershaveapositiveexperienceandrunintofewissuesadding,changing,im-
proving, or removing features in your codebase, we’re more inclined to call that a main-
tainablecodebase. It’swhatmakesussay,“thisissuchanicecodebase,Iloveworkingin
it”or“thislegacycodeisterrible—weshouldjustrewriteit”.
Whenit’seasytodowhatyouneededtodo,withminimallearningoradditionalconsider-
ationsrequired, thatusuallymeansthatthecodebase, library, framework, orwhatever—
doesn’thavealotofmandatorystructurethatyouneedtofirstfamiliarizeyourselfwithto
beproductive.
Whilethatcanbeincrediblyliberating,it’scommonthathighdeveloperexperienceandlow
structurecodebasesarepronetobecomingmessyandunmaintainable.
That’s largely due to there being several ways to solve a problem or implement a feature
withlittleenforcementofquality,correctness,andstandardization.
115

Structurevs.DeveloperExperienceinPractice: AngularandReact
Experiencematters. Itcanbewhywechooseonelibraryorframeworkthatsolvestheexact
sameproblemsoveranother.
Let’stakeAngularandReactasexamples.
Angular is a front-end framework that, if you look closely, is thoughtfully crafted to
promote the use of object-oriented software design best practices. To become productive
with Angular means learning TypeScript, brushing up on your OOP skills, and learning
theAngular-waytogetthingsdone.
Forexample,inapreviousdiscussion,wenotedthatAngularhasacorrectwaytoruncode
beforearouteisloaded,andthat’saRouteGuard.
React,aUIlibrary,ontheotherhand,isalotmorefunctionalandflexible. Developersare
capableofbeingproductivewithReactalotfasterbecausetheAPIissmaller,there’slessto
learn,anditcontainsfewerstructuralpoliciesthatmandatehowyoucanwritecodewithin
it.
Thechallengeis, ofcourse, maintainability. Whatifyouhave10developersworkingon
thesameproject? Howwouldyouenforcestructureinalibrarydesignedtohaveaspositive
ofadeveloperexperienceaspossible?
PerhapsifyouweretouseReactandyouneededanicestructure/developerexperiencemid-
dleground,writingyourReactcodewithTypeScriptcouldbethewaytogetthere.
116

Structure vs. Developer Experience in Practice: Object-Oriented vs. Functional Program-
ming
Object-Orientedprogrammingishardbecauseit’seasytocreatestructure,buthardtomake
thingsdiscoverable,understandable,anddelightfultouse.
Functional programming is also hard, but that’s because it’s delightful and easy to get
started,buthardtoimposestructure.
Goal: Balancingthetwo
Essentially,
• Toomuchstructure,developerexperienceislow.
• Toomuchloosenessandflexibility,whiledeveloperexperiencemaybehigh,structure
islow.
It’simportantthatwepracticedesignprinciples,usedesignpatternswhentheymakesense,
andstriveforstructurallyhighqualitycode(andwe’lllearnhowtoinlaterchapters), but
it’salsoimportanttodevelopcodethatkeepsthedeveloperexperiencehigh.
Great developer experiences comes from being able to meet your goals quickly. To write
codethathelpsothersdothat,let’stalkaboutHuman-CenteredDesign.
Human-CenteredDesign
Whatisit?
HCDisadesignphilosophythatputstheusers’needs,behavior,characteristics,painpoints,
andmotivationsfirst.
Forexample,insteadofjustdesigningsomethingthatworks,webrainstorm,test,andim-
plementbasedonwhatweknowaboutthehumanswe’redesigningfor.
117

—HumanCenteredDesignapproach(fromOutwitlyInc)
Howisthishelpfulforus?
Well, we’re in a unique design situation here. Normally, if we were building an app for a
particularsetofusers,likeadatingapporsomething,we’dhavetogetoutthereandactually
interviewtheuserstolearnmoreaboutthemandfindoutabouttheirneeds.
Sincewe’retryingtofigureouthowtowritecodesothatitcanbeeasilymaintainedbyour
coworkersandfuturemaintainers,thisprocessisaloteasier. We’realsotheuserswhoneed
cleancodeinthefirstplace.
Let’sthinkaboutit. Whatarethemostcommonusecasesasadeveloper,thatyouneedtodo
yourjobwell?
Developergoals
• Ineedtoknowhowtoruntheapp
• Ineedtoknowhowtorunthetests
• Ineedtounderstandhowandwhycodeisorganizedthewayitis
• Ineedtounderstandhowthedomainisexpressedincodeandwherepolicybelongs
• Ineedtounderstandhowtoaddanewfeatureandknowwhereitbelongs
• IneedtolocateaspecificfeaturewithinthecodesothatIcanchangeitbasedonanew
specification
• I need to be able to run through the code in debug mode to test a feature or debug a
bug
• Ineedtobeabletoimprovecodeovertimewithoutbreakinganything
Thisisgreat.
Ifyouhaveanyothergoalsthatyoucanthinkof,feelfreetocopythislistandaddit. These
arethethingsthatwe’regoingtobefocusedonoptimizing.
Thenextthingweneedtolearnisthepsychologybehindhowhumanslearntousethings
toaccomplishtheirgoals.
KnowledgeintheHeadvs.World
Mostofusunderstandwhattodowhenweseeahandle.
118

We’vealllearnedthathandlesarethingsthatcanbegripped.
Whenyouseeahandleonanobjectyou’venevercomeacrossbefore,likemaybeanewstyle
ofcar, aweirdartpiece, hell—evenaferociousdragon; whenyouseeahandle, it’shardto
stopyourbrainfromthinking“that’sforgripping,right”?
Ofcourse.
Though,ifyoucomeacrossanysortofdragononyourwalktothegrocerystore,youmightbebetteroff
justrunningtheoppositedirection.
Eachofthedesignprincipleswejustmentionedinthelastsectioncapitalizesonourknowl-
edge. Knowledgeisinvolvedinhowwediscoverwhatwecandoandwhatthingsmean.
Butwheredoesknowledgecomefromanyway?
KnowledgeintheHead
Thehead.
119

Knowledge in our head can be fast, efficient, treated like muscle memory, but only if
we’veputintheworktomakeitso.
Knowledgeintheheadisstuffthatweknow. It’salsothestuffwecandowithlittleeffort
becausewe’veinvestedthetimeandenergyintolearningthem.
Forexample,drivingacarhasbecomemusclememoryforalotofpeople. Butifyouwere
tothinkbacktowhenyoufirststarteddriving,youmightrecallyouractionsweresubstan-
tiallymorecalculated.
Anotherexampleishobbies. Somepeopleproducemusic; andwhilethatmaybehardfor
mostpeople,it’sactuallyquitestraightforwardformusicproducers.
Alsoimportanttonoteisthatifyoudon’tdrive,workonyourhobby,orcodeforalongtime,
youstarttolosethoseskills. Thingsfalloutofyourworkingknowledge.
Knowledgeintheheadcomesfromconceptualmodels(understanding)andconstraints
(cultural,semantic,andlogicalones).
KnowledgeintheWorld
Theworld.
120

Knowledgeintheworldiseasytouseandinterpreted,whichcanalsomakeitsuperfast. The
keythingaboutknowledgeintheworldisthatitdoesn’thavetobelearned.
Remindersarethebestexamplesofknowledgeintheworld.
For example, how would you remember to collect your lunch from the fridge before you
leaveforwork?
You’dprobablywanttoputsomethingalongyourpathtogettingready,likeastickynote,to
remindyourself. Inthiscase,youdon’tneedtolearnanything,knowledge(thatyoushould
remembertocollectyourlunch)isintheworld,andaslongasyoucanpercieveit,youknow
whattodo.
ThisisalsowhymostATMsguideyoualongwithwhatstepstotakenext. Imagineyouhad
tolearnhowtotakeoutmoney. ImagineaworldwherewithdrawingmoneyfromanATM
tookthesameamountoftribalknowledgeaschangingatire.
Knowlegeintheworldisexpressedwithinaffordances,signifiers,mappingsandphys-
icalconstraints.
Whyisthishelpfulforus(developers)?
Whyarewetalkingaboutthis?
We’reinestablishinghowtocreatehuman-centeredcodebases.
Therearesomeactivitiesthatyouwantdeveloperstospendtimelearningandtherearesome
thatyouwanttotakeabsolutelynotimetolearn.
Thingsthatyou’dlikedeveloperstoutilizeknowledgeintheheadandspendtimelearning:
• Thedomain.Youcan’tforcethis.Developersneedtospendtimeandlearnthedomain
tomakegooddesigndecisions.
121

• Thearchitectureoftheapplication. Youwantdeveloperstohaveasolidunderstand-
ingofhowwe’rebuildingtheapplication. Itmaytakesometimetofullyunderstand,
butthat’sanecessarylearning,becauseitwouldbedangerousforanotherdeveloper
todevelopfeatureswithoutanunderstandingofthearchitecturethatcontainsthem.
• The features within the project, what they do, and how to add new ones. You want
developerstoeasilydiscoverfeatures,andthat’strivialifwe’reusingscreamingarchi-
tecture.Asforwhatthefeaturesdoandhowtoaddnewones,thistakesnotonlylearning
thedomain,butthetechnicalconstructsusedtorealizethefeaturesaswell.Thisisnot
somethingthatcanbemerelyinterpretedasworldknowledge. Theresponsiblethingto
doistospendenergyunderstanding. ThiscanbehelpedthroughtheuseofBDD-style
teststhatexposetheintendedbehaviourofthefeatures.
Things that you’d like developers to spend zero time on learning and you’d like them to
merelyneedinterpretandreactto.
• Typeerrors.ByusingTypeScript,weforceotherdeveloperstodealwitherrorsasthey
occur,atcompiletime.
• Failingtests. Howareyousupposedtokeeptrackofifyoubrokesomethingornot?
It’snotreasonabletoexpectyoutotesteveryfeaturemanually. Instead,wepractice
TDD,andwhilewe’recoding, ifwenoticethatwe’vedonesomethingtoputtheapp
intoabrokenstate,weknowthatwenowhavetodealwiththeissuerightthereatthat
momentintime.
• An easy-to-reason-about folder structure. The names of the files and folders (signi-
fiers)shouldhelpguideustowardsafeature,component,ormoduleofcodethatwe’re
looking for. It’s not reasonable to ask someone to memorize the folder structure. It
should be designed in a way that we can rely on the current directory to figure out
howtomaneuvertowardswherewewanttobe.
• Pre-commitchecks. Howdoyougetdeveloperstoremembertorunthetestsandlint
theircodebeforetheycommit? Youdon’t. It’stoohardtoteachdeveloperstoremem-
bertodothesethings. Instead,weenforcepre-commitpolicywithtoolslikeHusky.
Andiftestsfailorcodeneedstogetcleanedup,well—nowthey’reforcedtodealwith
itbeforetheycancontinue(physicalconstraint).
Affordances
Whatarethey?
Communicatestheintendedpurposeofanobjectbasedonwhatweknowwe
cando,andthehintsgiventousbythephysicalattributesoftheobject.
Inotherwords,affordancesarevisualcues,properties,orattributesoftheobjectthattells
youhowsomethingworks.
122

Real-lifeexamples
Chairs. A chair is for support. So you might say that a chair affords sitting. A light might
haveanaffordanceforliftingorbeingcarried—onethat’sheavymightnotaffordlifting
orbeingcarriedthough.
Glass. Glassistransparent,soithasanaffordanceforseeingthrough. Italsohasananti-
affordance,whichlimitswhatispossible,andthat’stoblockair-flowandallowingthings
topassthroughit. Thechallengewiththisisthatifglassistransparentandwedon’tnotice
it’sthere,wemightnotnoticetheanti-affordance. That’showpeoplewalkintoglassdoors.
Thishappens. Toremedythis,wesometimesusesignifiers(we’lldiscussthosenext);they
actascluesthathelpaidalongthediscoveryofaffordances.
Whyisthisuseful?
Ifwehaven’tseensomethingbefore,eventhoughit’snew,ifithasphysicalpropertiesthat
we’refamiliarwith,thenwemaybeabletoquicklydiscoverwhatwemaybeabletodo
withit.
123

Forexample,Ihaveatypewriterathome.
Thoughnoteveryonehasusedatypewriter,therearesomefamiliaraspectssuchasthekeys.
124

Keys are buttons, and buttons afford pushing. Therefore, you’d likely be able to figure out
howtouseatypewriterprettyquickly.
Therearesomelessfamilaraspectsofthetypewriter,however.
Today,whileusingtraditionalkeyboards,togettothenextline,toperformacarriagere-
turn operation, you’d press the ENTER key. There is no such concept of an ENTER key on a
typewriter.
Whenyougettotheendofalineonatypewriter,you’llhearading(feedback)whichsignals
thatweneedtodosomethingtoperformacarriagereturntomovetothebeginningofthe
nextline.
What you may notice is that there is a bar that sticks out, and at this point, it’ll be pretty
muchrightinthemiddleofyourview.
Becausewehaveseenbarsbefore,andweknowthatbarshaveanaffordanceforpushing,it’s
likelythatwe’lltrythatandseewhathappens.
Wepushthebar,andvoila—we’reonthenextline.
Again,ifthephysicalpropertiesarevisibleandfamiliar,wewillknowwhatactionswecan
perform.
125

| That’swhataffordancesdo. |     |     |     | Helpusmaneuvernewobjects. |     |     |     |
| ------------------------ | --- | --- | --- | ------------------------- | --- | --- | --- |
Affordancesinsoftwaredevelopment
1—Programminglanguages.
Theyhaveaffordances. Thingsthatarepossibleinonelanguagemaynotbepossibleinan-
other.
When programming languages express concepts differently, or are missing features that
otherswe’refamilarwithdohave,thatreducesthesetofoptionsweknowoftoaccomplish
ourgoal.
Forexample,ifsomeonetoldyoutobuildaquickin-memorysolutiontokeeptrackofusers
thatvisitedasite,recordingthembyuserId,howmightwedothat?
Anefficientsolutiontothisproblemmightbetouseahashtable.
JavaScriptdevelopersarefamiliarwiththefactthatobjects,whichcanbecreatedwithcurly
braces,canbeusedashashtables.
| // Hashtable     |             | in JavaScript |           |     |     |     |     |
| ---------------- | ----------- | ------------- | --------- | --- | --- | --- | --- |
| const            | visitors    | = {};         |           |     |     |     |     |
| function         | saveVisitor |               | (userId)  |     | {   |     |     |
| visitors[userId] |             |               | = userId; |     |     |     |     |
}
IfweweretomovetousingJavathough,we’dbehardpressedtoaccomplishthisinasimilar
way.
InJava,weneedtouseclasses. ObjectstakeamuchdifferentforminJava. Wecan’tjustuse
curlybracestodothis.
| import | java.util.*;              |             |     |         |                   |     |     |
| ------ | ------------------------- | ----------- | --- | ------- | ----------------- | --- | --- |
| class  | HashTableDemo             |             | {   |         |                   |     |     |
| public | Hashtable<String,         |             |     |         | String> visitors; |     |     |
| public | void                      | saveVisitor |     | (String | userId)           | {   |     |
|        | this.visitors.put(userId, |             |     |         | userId);          |     |     |
}
|     | public        | static | main   | void  | (String args[])   |     | {          |
| --- | ------------- | ------ | ------ | ----- | ----------------- | --- | ---------- |
|     | // creating   |        | a hash | table |                   |     |            |
|     | this.visitors |        |        | = new | Hashtable<String, |     | String>(); |
}
}
Javaismuchmoreexplicitandverbose.
While there are convenience methods, and this code may be more intentional, the take-
awayisthataJavaScriptdevelopermayhavetroubleaccomplishingthistaskinJavaifthey
126

weren’tfamiliarwiththeHashtableclass.
2—Designpatterns.
Somelanguageshaveagoodaffordancesforcertaindesignpatterns,somehavepooraffor-
dances,andsometimes,it’scompletelyimpossible.
ToimplementtheobserverpatterninNode.jsistrivial;wecouldjustusetheEventEmitter
API.
Ontheotherside,toimplementtheabstractfactorypatterninJavaScriptisnearlyimpos-
sibletodoproperly,becauseJavaScriptdoesn’thaveanytheabstractkeyword.
Howtodoaffordanceswell
Sincemostofusarecodingwiththegoalof writingcodeinawaythatmakesiteasyfor
future maintainers to understand, improve, add, change, and remove features, we
havetoaskourselves,whatarethenecessaryaffordances?
Whatarethephysicalpropertiesthatwillgiveourcodeaffordancestobeunderstood,changed,
improved,andaddedonto?
Well,codeisn’treallyphysical. It’smoreorlessinvisible. Buttheformofcodeisdocumen-
tation&reading.
Atahigh-level,asfarawayfromtheactualcodeaspossible,wefeedforwardwith:
• Repo docs that shows examples, how to install, run, run the tests, contribute, and
learnmoreabouttheprojectdomainandarchitecture.
• Folderstructureandfile/foldernames.
• BDD-styleteststhatexplainswhatthecodedoes.
Atthenextleveldown,we’reconcernedwith:
• Formatting,style,filesize
• Modularunits
• Commentswhennecessary
Signifiers
Whatarethey?
When an affordance isn’t discoverable, signifiers help. They are a mark or
sound,andcancommunicateintendedbehaviortoaperson.
Signifierscomefromsomethingcalledsemiotics—thestudyofsignsandhowmeaningis
derivedfromthem.
Asigncanbethoughtofasatwo-partrelationshipbetweenasignifierandasignified.
• Signifier=thephysicalthingyousee,hear,sense,imagine
• Signified=thementalcontentthatthesignifierproduces
127

Real-lifeexamples
Intentionalvs.Accidental. Signifierscanbebothintentionalandaccidental.
• Intentional—itwasconsciouslycreatedtohelpusersdiscoverwhattheycandowith
theobject
– Example: APUSHorPULLlabelonadoor.
• Accidental—itwasaccidentallycreated
– Example: A trail or a path made visible by people walking along it over a long
periodoftime. Thissignalsthatit’sapaththatcanbetaken.
Purple. Asanotherexample,taketheEnglishnameforthecolorpurpleasasign.
The writtenword“p.u.r.p.l.e” is the signifier — or if I was to speak it aloud, the sound “purr-
puHL”,that’sthesignifieraswell. What’sbeingconveyed? Whatisthementalcontentpro-
ducedbythat? It’stheconceptofthecolor,purple. That’swhat’ssignified.
Whyisthisuseful?
Ifaffordancessignifywhichactionsarepossible,signifiers,whichnormallyuselabels,com-
municatewheretheactionshouldtakeplaceandwhattheoptionsare.
128

Signifiersareacheapwaytoenhancethediscoverabilityofaffordancesandmakesurethatthe
feedbackisunderstoodclearlybytheuser.
Signifiersinsoftwaredevelopment
Intentionalsignifiers
• Designpatterns. Therearetwowaystorecognizeadesignpatternincode. Thefirst
way is to see the code, the structure, and recognize that it’s a pattern. For example,
ifyoueverseeaprivateconstructor,andastaticcreatemethod,it’slikelywe’re
lookingatafactorymethod. Thesecondwaytorecognizeapatternistoseethepat-
ternnameintheactualnameoftheclassorfunction,likeStudentController. Both
of these examples have the pattern as the signifier, whether it’s the just name or the
entirepattern’scodeitself,butwhatissignifiedisanunderstandingof“understanding
of what[theprogrammer]can(andalso,sometimesmoreimportantly,cannot)
dowiththatcode.”
• Comments. Comments that describe what something is, rather than allowing the
codetodescribewhatitdoes(wegenerallydiscouragethese).
• Tests. IfwedoBDD-styletests,wesignifytheproblemsthatthecodesolves. Thisis
themostobvious,intentionalcluefordemonstratingwhatthecodedoes.
Accidentalsignifiers
• Dead,commentedoutcode,ortodocommentsleftfortoolong. Thesesignifythatthe
relatedcodemightbeaworkinprogress.
129

• Toomanynestedconditionals—signifiestousthatthisisaplacethatmaycontaina
lotofapplicationordomainlogic.
• Thin controllers — signifies that the application and domain logic is being handled
elsewhereandthatthearchitecturemaybemorerobustthanasimpleMVCapp.
• Codesmellsandanti-patterns. Theysignifythatthecodehasissuesforustotendto.
Howtodosignifierswell
Use good names for files, folders, variables and classes, write tests, make error handling
explicit,andutilizedesignpatternswhenappropriate.
Constraints
Constraintslimitthesetofpossibleactionswecantakeincertainsituations
Aboutconstraints
Constraintscanbephysical,cultural,semantic,andlogical. Eachtypeofconstraintlimits
thesetofpossibleactionanddrivesustowardsthingsareappropriatetodoatthattime.
Real-lifeexamples(physicalconstraints)
Restrictsthewayinwhichobjectscanbemovedormanipulated.
• Wecan’tforcepuzzlepiecestofitinspotsthattheyphysicallydon’tbelong.
130

• Youcannailahammerintothewallwithanail. Youcan’tnailahammerintothewall
withanail.
Real-lifeexamples(culturalconstraints)
Constrains a situation by providing a set of allowable actions in social situa-
tions.
• Howwebehaveinanelevatorwithastranger. InNorthAmerica, itcanbeimpolite
anduncomfortabletomakedirecteyecontactwithastrangerintheelevator.
• Itmightseemabitoddifweopttoofferourbusseattoanathleticperson,whilene-
glectingelderly,pregnant,ordisabledpeople.
Real-lifeexamples(semanticconstraints)
Reliesuponthemeaningofthesituationtocontrolthesetofpossibleactions.
• We wear helmets to ride bikes or skateboard because we are delicate human beings
that,ifwehitourheads,there’sagoodpossibilityofseriousinjuryordeath.
• If somehow we discovered immortality and it was impossible for us to die, helmets
wouldbeuselessandtherewouldbenoreasonforustouse’em.
Real-lifeexamples(logicalconstraints)
Relies upon arithmetic, and logical proofs to constrain the set of possible ac-
tions.
• Whotookmyfoodfromthefridge? Ifallbutoneofourroommateshadvalidalibis,it
musthavebeenthatperson.
Constraintexamplesinsoftwaredevelopment
• Statictypechecking.
• ValueObjectsusetheFactoryPatterntoenforceconstraintsagainstcreatingdomain
objects.
• Accessmodifiers.
• Languagerules. Aconstcannotberedeclared. Astaticmethodcanonlybeaccessed
through its class, not through an instance of the class. In the Object-OrientedPro-
gramming chapter, we rely on these language rules to enforce policy, reducing the
totalsurfaceareaforwaysdeveloperscanmisuseoursolutions.
• Strictly-typed errors. These force you to deal with potential error states instead of
throwingthematyou.
Howtouseconstraintswell
Resource: It’sremarkablehowmuchinformationisconveyedbylimitingthesurfacearea
ofwhat’spossible. Ifyou’dliketolearnmoreabouthowconstraintshelpyouwritebetter
code,read“MakeIllegalStatesUnrepresentable”.
Mappings
131

Whatarethey?
Mappingistherelationshipbetweentwosetsofthings.
Typically,whenwe’retalkingaboutmapping,whatwe’rereallyconcernedwithisthelay-
outofcontrolswithrespecttothedevicesthey’rehookedupto.
Real-lifeexamples
Stoveexample. Takealookattheplacementoftheburnersandtheircontrolsonbothof
thesestovetops.
Inthefirstexampleontheleft,it’snotinherentlyclearwhichcontrolmapstowhichburner.
Thefirstcontrolverywellcouldmaptoeithertheburneronthetop-leftorthebottom-left. In
thiscase,thestoveonthelefthaspoormapping. Ifwelooktothestoveontherightthough,
wecanactuallyspatiallyseewhichcontrolmapstowhichburnerbecausethelayoutofthe
controlsmimicthelayoutoftheburners!
Sincetheexampleontherightisaloteasiertodiscoverhowtousethestovecorrectly,we’d
saythemappingisalotbetter.
The best way to implement mapping is to place the control directly on the item that
132

needs to be controlled. A lot of the time though, that’s not possible. I mean, let’s think
about the stove top. We can’t exactly place the control for the burner on the burner itself,
canwe?
Soreally,ifwehadtothinkabouthowtodomappingthebest,it’s:
• 1—Mountcontrolsdirectlyontheitemstobecontrolled.
• 2—Mountcontrolsasclosetotheitemstobecontrolled.
• 3—Mountcontrolsinthesamespatialarrangementastheitemstobecontrolled.
Thisisaweirdone—butit’sprobablythebestmappingeventhoughit’sstrangelyshaped.
I’mnotevensuretheymakestoveslikethis,especiallyifitneedstofitacounter-top.
Ifyou’restillawake,you’llnoticethatwe’vesettledfor“#3—Mountcontrolsinthesamespatial
arrangementastheitemstobecontrolled”inthisexample.”#1—Mountcontrolsdirectlyontheitems
tobe controlled” is impossible, but if we designed it a little differently, we might be able to
mountcontrolsclosertotheitemstobecontrolled(asper#2),wemightbeabletoimprove
thedesign.
Here’sanimprovement:
Evidently,thisiswhatthestoveIhaveathomelookslike.
133

Whyisthisuseful?
Good mapping helps us better understand the set of possibe actions and understand what
eachcontrolmeans. Thismeansthatit’saloteasierforustospecifywhatactionwewant
toperform.
Whenmappingmakessense,weconstructaconceptualmodelfaster.
Howtodomappingswell
Todomappingwell,wehavetoobeytwoprinciples: groupingandproximity.
• Grouping—Placerelatedcontrolstogether.
• Proximity—Placecontrolsclosetotheobjectthattheycontrol.
Grouping
Imagineweweretryingtohookupthelightpanelsthatcontrolthelightinginourhome.
There’saleftroomandarightroom.
Theleftroomhaslights1and2butthepanelfortheleftroomcontrolslights1and3.
Therightroomhaslights3and4butthepanelfortherightroomcontrolsthelights2and
4.
134

Thegroupingisentirelyoff,andthiswouldcreateatonofconfusion.
It’saneasyfix,weensuretherelatedcontrolsaretogether—andthesecontrolsarerelated
bytheroomthatthelightstheycontrolarein.
Proximity
Nowconsiderwe’retryingtofigureoutwheretoputthelightswitchforabathoom.
Inthisfirstimage(below),therearejustifiablereasonsforhavingthelightswitchrightout-
sidethedoorofthebathroom.Thereisalsogoodreasontoplaceitrightinsidethedoortothe
rightasyouenter. Bothofthesearegoodproximities(andgoodmapping). Youunderstand
whattheswitchcontrolsbecauseoftheattentiontoproximity.
135

Inthefollowingexample,withthelightswitchallthewaydownthehallway,andwithother
rooms in proximity, it’s less clear which room the switch is meant to control the lighting
within. Here,we’dsaythisisnotgreatproximity,andnotgreatmapping.
Mappingsinsoftwaredevelopment
Mappingoccursinprogrammingthroughgroupingandproximityaswell.
Grouping
• Cohesion—dowegrouprelatedmethods,classes,andfunctionstogether?
136

• Single Responsibility Principle & boundaries — do we enforce boundaries between
thingssothattheyhaveasinglereasonforchange? Orcanachangetothecodepo-
tentiallycausearippleinanotherpartofthecodebase?
Proximity
• Coupling—ifcomponentsrelyonandarefrequentlychangedtogether,theyshould
berelativelyclosetoeachotherforbetterdiscoverabilityandlesstimespentflipping
betweenfilesandfolders. Weexplorestrategiesforthisinorganizingthings.
• Anemicdomainmodels—dowekeepcontrolsthatchangestateasclosetotheobjects
(models) as possible? Or do we maintain slim models? For example, a UserManager
classthatdoescreate,update,setPermissionsoperationsontheUsermodel,leaves
the User model responsible for nothing. This can lead to duplication and a lack of
encapsulation.
• API design — The DOM API has good mapping. It keeps the methods that operate
againstobjects,physicallyontheobjectsthemselves. Thecontrolsareasclosetotheitem
tobecontrolledaspossible. Forexample,document.querySelectorreturnsanobjectthat
containsthemethodsthatmaybeperformedagainstit.
// Great!
const node = document
.querySelector('a')
.getAttribute('href')
Imagineifyouneededtoconstructasecondaryclassorwraptheresultofdocument.querySelector
inanotherobjecttocallgetAttributeontheHTMLnode.
const nodeResult = document.querySelector('a');
// This API doesn't exist, but imagine you had to do
// this wrapping everytime you wanted to execute a method
// on a node returned from the DOM.
const node = document.createExecutableNode(nodeResult);
node.getAttribute('href')
SomeJavaAPIsarelikethis,whichcreatesachallengeandaddsanotherlevelofknowledge
necessaryforthedevelopertoknowhowtheymayusewhatwasreturned.
Consequently,thisisalsotheLawofDemeter(PrincipleofLeastKnowledge),ensuringthatan
objectdoesn’tknowtoomuchabouttheotherone(ie: myobjectdoesn’ttalktostrangers).
Feedback
Whatisit?
Communicatingtheresultsofanaction
It’sexpectedthatfeedbackwillbeimmediate. Notgettingimmediatefeedbackfeelsweird,
anduserswilltryagain,expectingtogetitimmediately.
137

If users don’t get feedback immediately, they might assume that the item they’ve tried to
interactwithisbrokenorslow,evenifit’sworkingperfectlyinthebackground.
Success,loading,andfailurestatesareimportanttoreporteverystepoftheway. Eachac-
tionfromausershouldbeconfirmedbyfeedback.
Typesoferrors
It’s possible that users can also run into errors. There are two kinds of errors: slips and
mistakes.
• Slips: Thisiswhenthegoalorplaniscorrect,butthesequenceisoff. Inthesecases,it’s
OK—wecanpointtheuserintherightsequenceofactions.
• Mistakes: Thisiswhenthethegoalorplanisnotcorrect. Thisisbad,andyouwant
toavoidthishappening. Itmeansthattheuserisabouttogocompletelyastrayorit
meansthattheyhavegonecompletelyastray,goingaboutaccomplishingtheirgoalin
anincorrectway.
Real-lifeexamples
• Elevatorbuttons—theylightup(andoftenmakeanoiseaswell)toindicatethatthe
elevatorisnowmoving. Whenweusethe“close”buttoninanelevator,ifthebutton
doesn’tcloserightafterwepressit,wemightassumeitdidn’tregister,andthenpro-
ceedtobashitacouplemoretimes.
138

• Making a purchase on a website — you want to see the loading spinner as soon as
possibleotherwiseyoumightassumeyourtransactionhasn’tstarted. Thiscanlead
tousersdoublepurchasingsomethingifitwasn’tprotectivelydesignedagainstthat.
Whyisthisuseful?
Feedbackistheotherhalfofthesevenstagesofaction. It’swhathelpstobuildourconcep-
tualmodel.
Afterinteractingwithsomething,thenextstagesareperceive,interpret,andcompare.
We want to know that the thing we performed took. We want to be able to interpret what
happened. Then,wewanttoknowifwhathappenedalignedwithourgoal.
Feedbackinsoftwaredevelopment
• TheCQSprinciple. Ifacommandisinvoked,gettingnoresponseisthefeedbackto
signalthatitsucceeded. Gettinganerrorsignalsthatitfailed. Ifaqueryisinvoked,get-
tingnoresponseisthefeedbacktosignalthatsomethingfailed(orisslow). Gettingdata
backsignalsthatitsucceeded.
• Command-line utilities. Communicating progress and the current state of a long-
running task is important. Think about how useful it is to see your progress when
139

you’renpm-installingsomedependencies.
• Clickingthesubmit buttoninaform. Let’ssayyouclicksubmit andnothinghappens.
Do you re-click it? What if a loading spinner appeared right after you clicked it?
Would you still feel inclined to re-click right away? Probably not. Immediate feed-
backisimportanttohelpingusdiscoverwhatstatewe’recurrentlyinateverypoint
intime.
• Autocomplete—thisdepictsthetotalsetofpossibleactions,itshowsthatwe’rewrit-
ingastatementusingthecorrectobject.
• Compile-timetypechecking—showsusimmediatelyifwhatwe’redoingislegalor
not.
• TDD—ifwehaveaconsoleopenandwepracticeTDD,wecanimmediatelydetermine
when we’ve put the system into a broken state, and we can work to fix it. A slip at
thispointisOK,it’scompletelywarranted,butamistakeisnotOK.Ifourtestsaren’t
congruent with what we need to solve in the business, then we’re coding the wrong
thing.
Howtodofeedbackwell
Communicatetheresultimmediately. Assoonaspossible.
ConceptualModels
Whatarethey?
Conceptualmodelsarethetrueunderstandingofhowsomethingworks.
They are high-level explanations of how something works. They’re imperfect, they often
skiporglossoverdetails,butthey’regoodenoughmentalmodelsofthingsthathelpsusun-
derstandhowtousethings.
Conceptualmodelsareconstructedfromexperienceusingsomething.
Allofthefeedforwardprinciplesandfeedbackhelpustofullyconstructamentalmodelof
something.
Thisisthegoalofourdesigns—we’reaimingtobuildupaconceptualmodelsothat
userscanmorequicklyunderstandhowitistobeusedtomeetourgoals.
Real-lifeexamples
• Elevators—Youmightnotknoweverythingaboutthemachinerybehindelevators,
but you generally understand how they work having spent time using them. If you
asktheengineer,they’llprobablyhaveamuchdeeperknowledge.
• Thecloud—Askyourparentswheretheirdatagetsstoredandtheymightsaythecloud.
That’sfair. Theydon’tneedtoknowthatyourdatagetssavedindatabasessomewhere
onserverslikelyrunningonAWSEC2containers. It’sagoodenoughunderstandingfor
themtoknowhowtoutilizeit.
140

Whyisthisuseful?
Agoodconceptualmodelallowsustopredicttheeffectsofouractions. Ifwedon’thaveone,
weendupneedingtojusttryrandomthingstoseewhatworksandwhatdoesn’twork. We
don’tknowwhattoexpect.
Conceptualmodelsinsoftwaredevelopment
• Design Patterns. Design patterns are signs that point to conceptual models. When
you think of the Builder pattern, Factory Pattern, Observer pattern, etc — we have
an understanding of how these work. Each design pattern has a name that helps to
conceptualizewhatitmeans. Forexample,theFactoryPatternimpliesthatacompo-
nentactsasthesoleplacetocreateanobject. TheRepositoryPatternimpliesthatit
hastheresponsibilityofknowinghowandwheretostoreandretrieveobjects,likea
bookkeeperorlibrarian.
• Encapsulation. Evenifyoudon’tfullyunderstandreactivityinReact,youknowthatRe-
actwillre-renderwhenyourdatachanges. That’sgoodenoughforyoutoknow. You
don’tneedtoknoweverythingabouttheshadowDOMandwhathappensunderneath
theAPI.That’sencapsulated.
• Constructs. Controllers,routes,client-sidelibrarieslikeReact,etc. Fornearlyevery-
thingwewanttoworkwith,wehavetoconstructaconceptualmodelaroundhowit
141

works,anditmightnotneedtobecompletelyin-depth,butitneedstobegoodenough
forus.
Howtodoconceptualmodelswell
Useencapsulationtoabstractawaycomplexitythatwedon’twanttheclienttohavetothink
about.
Optimizeallthefeedforwardprinciplesandgoodfeedbacktohelpcreateabetterconcep-
tualmodel.
Testingyourcodeforcleanliness
Askafriend.
Really. When I studied computer networking, my professor often talk about the projects
heworkedon. Irecalloneofhisstoriesfromthetimeheopenedthenetworkingclosetto
checktherouterconfigsandwasexposedtowhathecalledbarbarian-ism. Everytimehewas
remindedaboutthefolkswhoconfiguredthoserouters,he’dmenacinglyuttera“damnthose
barbarians”.
You’renotabarbarian. Andyoudon’twantyourcoworkerstothinkyouareeither.
Beingabletodetermineifyourcodeiscleanismoreorlesssomethingthatcomesfromex-
perienceworkinginbothverycleancodebasesanduncleancodebasesofsubstantial
size. Projectsizeisrelevant,becauseifyouonlygetachancetobuildsmallapplicationsor
proof-of-concepts,anddon’thavesupportanylong-timeprojects,youmaynotgetachance
toexperiencethedifferencebetweentrulycleanandcripplinglyuncleancode. It’seitheryou
stay around long enough and get the opportunity to get to see the codebase thrive or you
stayaroundlongenoughtorealizethatithasproblems. It’simportanttoexperienceboth
of these. Work on a project long enoug to get to the maintainence phase. You’ll know if it’s
goodornotbasedonhoweasilyyoucanunderstand,maneuver,andchangethings.
Ifyou’reworkingonapersonalandyoureallywanttoknowifyourcodeisclean,askanother
developertointeractwithyourcode.
Prototype&testisthesecond-laststepintheHCD(Human-CenteredDesign)philosophy.We
can determine our code cleanliness by observing just how well we empower our fellow
developer. Trythesequestions.
Ask: Whatdoesmycodedo?
Testagainsthowquicklythey’reabletounderstandwhatitdoes. Don’teventellthem
theproblemdomain. Allowthemtolookatthefiles,folders,classes,andvariablenames. If
someoneisn’tabletotellwhatthedomainyou’reworkingin,thatcouldbeasignalthatthat
eitheryourdomainisincrediblyabstract,oryou’vefailedtounderstandandcodifydomain
concepts. Withthisquestion,we’retestingagainst:
• Readability
• Clarity
• Brevity&succinctness
Wewilldiscusshowtoimprovethisbyusing:
142

• Goodnames(classes,variables,methods,folders,files)
• Encapsulation(simplifyAPIs)
• Intentionrevealinginterfaces
Ask: Findthecodethatneedstobechanged
Let’ssaytheynowunderstandtheproblemdomain. Howaboutchangingafeature? Test
against how quickly they can find the code that needs to change for a feature. We’re
testingagainst:
• Locatability,scannability,structure
Thingsthatmassivelyinfluencethisare:
• Goodnames(classes,variables,methods,folders,files)
• Smallerfiles
• Goodpackaging(couplingofconstructsinvolvedinafeature)
Ask: Changethiscodewithoutintroducingbugs
Ramping it right up there, ask them to change the business logic for feature. How safely
cantheychangethecodewithoutbreakingotherfeatures? We’retesting:
• Stability
• Flexibility
Atthispoint,theconceptswecaremostdeeplyaboutare:
• Tests
• Coupling
• DependencyInversionordecompositionofcode
• Boundaries&separationofconcerns
• Typesafety
Trythosethreequestionswithoutprovidingguidanceandobservehowsuccessfultheyare.
Thisislikeatime-machinetest. It’smoreresponsibletowatchyourfriendgetdisgruntled
inpersontodayandfixtheproblemsthanitistoputitonsomeoneelse4yearsafteryou
arelonggonefromtheproject.
SummaryonDesignThinking
Considerthefundamentalsofinteractionwhendesigningsoftware
Theseprinciplesaren’tjustforUXdesigners. Theywereformalizedtohelpcommunicate
howhumanbeingsdiscoverandunderstandhowtousethings.
Sincedesigningcodeformaintainersisanimportantpartofwhatwedo,studytheseprin-
ciplesandkeeptheminthebackofyourmindwhencoding. Theyarejustasimportantas
thestructuralsoftwaredesignprincipleswe’regoingtolearnthroughouttheremainderof
thebook.
Thiswillactasabasisforustobetterreasonaboutwhatexactlyitisthatmakessomething
uncleanandstepswecantaketoimproveit,ratherthanfollowingasetofcodingconventions
becausesomeoneelsesaidto.
143

LearnmoreaboutHuman-CenteredDesign
Human-CenteredDesignisatopicworthyofmoreresearch. Ithinkeverysoftwaredevel-
oper,regardlessofifyoucodeUIsornot,shouldreadthisbook. Isuggestyouspendsome
timelearningmoreaboutdesign. Irecommendthebook,“TheDesignofEverydayThings,
byDonaldNorman”.
Ifyou’relookingforawaytoquantitativelymeasuredesigneffectiveness, checkoutGOMS.
NexttimeyourfriendsaystheycancodefasterthatyouinVimvs.youwithyourkeyboard
andmouse,trymeasuringataskusingaGOMStest.
SectionTwo-Cleancodingconventions
Summary
Developing your own set of principled coding conventions to produce the highest
qualitysoftwarepossibleusingindustrystandards,tools,approaches,andmethods.
Aboutthissection
Therearehundreds,maybeeventhousandsofcodingconventionstolearn. Inthissection,
we’llcoveracuratedsetofconventions. I’vechosenconventionsuncoveredbyformaledu-
cation,yet,sorelyinneedofguidanceforourdailyprogrammingwork.
Myaimisthattheseconventionsareusefulregardlessoftheteam,language,paradigm,or
architectureofyourproject.
While the conventions do apply to just about any language, we’ll call out the Type-
Script/JavaScriptspecificconventionsastheyoccur.
Conventionswe’llcover
Projectplanning
Documentation
Organizingthings
Formatting&style
Comments
Namingthings
Errorsandexceptionhandling
Testing
Refactoring
Architecturebasics
144

Projectplanning
Notes
This is a section that has a lot of the content already written about in How to plan a new
project.
Howdopeopleplanprojects?
• Alotoftimes,developersdon’treallyplan—theyjuststartbuildingouttheAPI
– APIfirst
* ThisiswhenwestartbuildingouttheAPIendpointsandthenstartquerying
them.
⋅
Benefits:
⋅
We’restartingimperatively. We’reactuallystartingoutbybuilding
outsomething.
⋅
Drawbacks
⋅
It’seasiertomisssmallnuancesaboutthe
⋅
Workswellfor:
⋅
CRUDapps
⋅
Doesn’tworkwellfor
⋅
Appswithdomainlogiccomplexity
– Databasefirst
Documentation
Organizingthings
Comingsoon!
Whycodeorganizationmatters
• Thewayyouorganizecodehasaprofoundimpacton:
– Howlongittakestodevelopnewfeatures
– Howlongittakestolocateafeature
– Howmuchmentalenergyittakestochangecode
* Flippingbackandforthbetweenfilesandfolders
– Knowingwherenewcodeshouldgo
• Colocation
• Differenttypesofpackagingandtheergonomicsof’em.
Practicalnaming
Principles
• Useconventions
– Yoursourcecodegoesinthesrc/folder
– docsgointhedocs/folder
– config
– examples
145

– dist/build/forcompiledcode
• KeepyourcodeDRY/don’trepeatyourselfwithfiles
• Groupfilesrelatedtoafeatureclosetoeachother(colocatefileswithhighcohesion).
– Experimentandnoticehowoftenyou’reflippingaroundandgettingfatigued.
– Examples:
* DDDForumusecases
• Asflataspossible
• Screamingarchitecture
• Sharedfolderforanythingthatdoesn’t
• Usepackagebyinfrastructureonsmallprojects
• Packagebyfeatureonlargerprojects
Greatexamples:
• DDDForum
• ApolloClientopensourcelibrary
Organizingandcontext
Herewehaveallofthesefilesadheringtothesamenamingconvention. Theyallhavethe
nameoftheusecaseatthefront.
useCases/
createUser/
editUser/
deleteUser/
DeleteUserUseCase.ts
DeleteUserErrors.ts
DeleteUserController.ts
DeleteUserResolver.ts
Butwhatif
Howtosettledesignarguments
• In subjective conversations about design, there’s only really one way to decide on
which approach is better — observe users. This is what we do in Human-Centered-
Design.
– GOMS—thisprocessexistssoyoucanmeasuretheefficiencyittakesauserto
accomplishatask
– Ifyou’reuncertainaboutsomeapproachtodesigningyourcodeormakingsome
APIeasytouse,thisisawaytohandlethat.
Formatting&style
Eachofthefollowingsuggestionsaretacticalwaysforustoimprovethedesignofourcode
topromotebetterreadabilityanddiscoverability.
Formatting is the visual appearance of the source code. By applying whitespace, consistency,
andstorytelling,codecanbecomemorepleasanttoread.
146

It’simportanttoaddressthatformattingisincrediblysubjective. Somedeveloperspre-
fertousetabsoverspaces; someprefertheusingmaxlinelengthsof80characters,while
otherspreferittobemuchlonger. Thesearesubtlechoicesthatwillalwaysbedebatable.
Ifyou’reworkingalone,you’refreetoenforceyourownstyle.
Ifyou’reworkingonateamoranopen-sourceproject,youhavetwotasks:
• Establishtheformattingrulesthatyouwouldlikeeverytoadhereto.
• Enforcethoserules.
Afterweshedsomelightonthestylechoicesyouneedtoagreewithyourteam,I’llshowyou
howtoenforcethosedecisionsusingmoderntoolingforTypeScript&JavaScriptprojects.
Objectivereadabilitytruths
A moment ago, I said that formatting is subjective. This is true of anything visual. Code
lookinggoodisextremelysubjective.
WhoareyoutosaythatdeKooningisbadandvanGoghisgood?
Javadevelopersmayprefertowritetheirconditionalstatementslikethis:
if (isAccountOverdue) {
...
}
WhereCdevelopersmaypreferthebracestobeonthenextline.
if (isAccountOverdue) {
...
}
Isoneapproachbetterthantheother? Whoknows. That’sanage-olddebate.
However,sinceyouandIarebothhumanbeings,therearecertainphysiologicallimita-
tionsweallshareincommon.
Forinstance,ifyouholdyourbreathunderwater,youwilleventuallyneedtocomeupfor
air.
Ifsomethingisveryfaraway,itmaybeveryhardtoread.
Bringingitbackintocontext,becausewearehuman:
• Withoutproperuseofwhitespace,codebecomesvirtuallyimpossibletoread.
• Without consistency, we can’t build turn on the pattern matching algorithm in our
brains. This increases the amount of time it takes to grow accustomed to a new
codebase.
• Without storytelling and presenting the most important details upfront, readers can
loseinterestandgetfatigued.
Whitespace
147

Whitespaceanatomiccomponentoffosteringreadablecode. It’susedtoseparatethoughts,
tokens,algorithms,andsoon.
On a small scale, look at this code. While it is syntactically correct, the lack whitespace
makesithardtoread.
const artists=this
.artistRepo.getArtists();const artistNames:string=artists.map(
(a)=>a.name);
Doesn’tmakeyoufeelgood,right? Readingcodethiswayishard. Considerhowunhappy
youwouldfeelworkingonacodebasewhereallofthecodewasformattedthispoorly. On
aslightlylargerscale,trytounderstandthefollowingcodeblock,whatitdoes,andhowit
doesit.
users/services/usersService.ts
export class UsersService extends BaseAPI implements IUsersService { private referralCodeCookieName = 'referral_code'; constructor () { super(); } public getProfilePicture (store: any): string {
const state: any = store.getState(); const userState: UsersState = state.users;
| if     | (userState.isAuthenticated) |                 |     |     | {                     |     |        |     |
| ------ | --------------------------- | --------------- | --- | --- | --------------------- | --- | ------ | --- |
| return |                             | (userState.user |     | as  | User).profilePicture; |     | } else | {   |
|        | return                      | "";}            |     |     |                       |     |        |     |
} public updateProfilePicture (picture: File): Promise<any> {
|        | const                          | data = | new FormData(); |                  |           |       |       |     |
| ------ | ------------------------------ | ------ | --------------- | ---------------- | --------- | ----- | ----- | --- |
|        | data.append('profilepicture',  |        |                 |                  | picture); |       |       |     |
| return | this.post('users/picture/new', |        |                 |                  |           | data, | null, |     |
|        | { authorization:               |        |                 | getAccessToken() |           | }     |       |     |
)
| }   | private | static                                 | decodeToken |     | (token: | string) | : JWTProps | {   |
| --- | ------- | -------------------------------------- | ----------- | --- | ------- | ------- | ---------- | --- |
|     | return  | JSON.parse(atob(token.split(".")[1])); |             |     |         |         |            |     |
} private saveAuthToken (authToken: string) { window.localStorage.setItem(
"univjobs-access-token",
|                                               | JSON.stringify({ |     |     | token: | authToken})); |     |             |     |
| --------------------------------------------- | ---------------- | --- | --- | ------ | ------------- | --- | ----------- | --- |
| CookieUtil.setCookie('univjobs-access-token', |                  |     |     |        |               |     | authToken); |     |
} private removeAuthTokens (): void { localStorage.removeItem("univjobs-access-token");localStorage.removeItem("univjobs-fb-access-token");
CookieUtil.eraseCookie('univjobs-access-token');localStorage.removeItem('last-email-seen');
| }                                              | private | saveLastEmailSeen     |        |        | (email:  | string)      | : void { |     |
| ---------------------------------------------- | ------- | --------------------- | ------ | ------ | -------- | ------------ | -------- | --- |
| window.localStorage.setItem('last-email-seen', |         |                       |        |        |          |              | email);  |     |
| } public                                       |         | isAStudent            | (user: | User): | boolean  | {            |          |     |
| return                                         |         | !!user.roles.find((r) |        |        | => r === | 'Student');} |          |     |
}
Notthateasy,right? Whitespace,sometimesreferredtoasaseparator,isoneofthefivedif-
ferenttypesoftokensthatexistsinanyprogramminglanguage. Whengoodwhitespacing
isused,readingcodebecomessignificantlyeasier.
Fivedifferenttypesofprogrammingtokens:
| 1.  | Keywordsarereservedwords: |     |     |     | function,class,const. |     |     |     |
| --- | ------------------------- | --- | --- | --- | --------------------- | --- | --- | --- |
2. Identifiersarewhatweareallowedtouseasclass,variable,andmethodnames: user,
userOne,userModel.
148

3. Operatorsaretokensthatenableustodologicaloperations: assignment(=),addition
(+),andsubtraction(-). 4. Separatorscreatewhitespaceinourcode: tabs,spaces,
andnewlines.
4. Literalsareintegers,decimals,andstrings.
Hereareafewwaystousewhitespacewell.
Useobviousspacingrules
Howtousespacingisalmostneverexplicitlytaught,butit’soneofthefirstthingswefigure
outwhenwestartprogramming.
You’relikelyawareoftheobviousspacingrules.Formostlanguages,whenwestartlearning,
we’reshownexampleswithspacesinbetweenkeywords,identifiers,operators,andliterals.
We’realsoshowntousehorizontalindentationwhenwestepinsideofaclass,method,func-
tionorblock(ingeneral).
Thesearethebasicspacingrules.
Trytostickto’em.
Badhorizontalspacing
const x=12;
const user={name:"khalil"}
class Employer {
public update(details:CompanyDetails):Result<UpdateResult>{
...
}
}
Goodhorizontalspacing
const x = 12;
const user = { name: "khalil" }
class Employer {
public update (details: CompanyDetails): Result<UpdateResult> {
...
}
}
Usinghorizontalspacinghelpstodelineatetokentypes. Eachlineofcodeisslightlyeasier
toread. Thesesmallactsofcareforthecodecompoundoveranentirecodebase.
Horizontalindentationalsomakesiteasiertovisualizethescopeofamethod,class,orfunc-
tion. ScopeiseasytoseebycomparinghowstatementssitupanddowntheY-axis.
149

Keepcodedensitylow
Codedensityisameasurementofhowmanylinesofcodegowithoutalinebreak.
LinebreaksarelikecommasinEnglish. Bothsignalarestingpoint,anotherstep,orasep-
aratethoughtbeingexpressed. Linebreakshelptomakecodeeasiertodigest.
Here’sanexamplefromApolloGraphQL’sopen-sourceRESTDataSourceAPI.
| export | class | HTTPCache |     | {   |     |     |     |     |     |
| ------ | ----- | --------- | --- | --- | --- | --- | --- | --- | --- |
...
|     | async fetch(         |              |     |       |     |     |     |     |     |
| --- | -------------------- | ------------ | --- | ----- | --- | --- | --- | --- | --- |
|     | request:             | Request,     |     |       |     |     |     |     |     |
|     | options:             | FetchOptions |     | = {}, |     |     |     |     |     |
|     | ): Promise<Response> |              |     | {     |     |     |     |     |     |
/**
* 1. Create the cache key. You can either supply a cache key or leave it blank
|     | * and | Apollo | will | use the | URL | of the request | as the | key. |     |
| --- | ----- | ------ | ---- | ------- | --- | -------------- | ------ | ---- | --- |
*/
const cacheKey = options.cacheKey ? options.cacheKey : request.url;
/**
|     | * 2. | Using | that | key, see | if the | cache has | the value | already. |     |
| --- | ---- | ----- | ---- | -------- | ------ | --------- | --------- | -------- | --- |
*/
|     | const | entry | = await | this.keyValueCache.get(cacheKey); |     |     |     |     |     |
| --- | ----- | ----- | ------- | --------------------------------- | --- | --- | --- | --- | --- |
/**
|     | * 3.        | If it | doesn't | already      | have | the response, | we'll      | need | to  |
| --- | ----------- | ----- | ------- | ------------ | ---- | ------------- | ---------- | ---- | --- |
|     | * get       | it,   | store   | the response |      | in the cache, | and return |      | the |
|     | * response. |       |         |              |      |               |            |      |     |
*/
|     | if (!entry) |          | {   |                  |                          |     |     |     |     |
| --- | ----------- | -------- | --- | ---------------- | ------------------------ | --- | --- | --- | --- |
|     | const       | response |     | = await          | this.httpFetch(request); |     |     |     |     |
|     | const       | policy   | =   | new CachePolicy( |                          |     |     |     |     |
policyRequestFrom(request),
policyResponseFrom(response),
);
|     | return | this.storeResponseAndReturnClone( |     |     |     |     |     |     |     |
| --- | ------ | --------------------------------- | --- | --- | --- | --- | --- | --- | --- |
response,
request,
policy,
cacheKey,
options.cacheOptions,
150

);
}
/**
| * 4.    | Returns the  | object from the | cache (respecting | any |
| ------- | ------------ | --------------- | ----------------- | --- |
| * cache | invalidation | policies).      |                   |     |
*/
...
}
}
Youdon’treallyhavetofullyunderstandwhatthiscodeisdoing,butlinebreakshelpmake
iteasiertodigestandcombthroughpotentiallychallenginglogic.
It’s also good when functions or class methods are kept small. Line breaks between each
methodarenotonlyconventional,buthelpreadability.
chatEvents.ts
| export class                      | ChatEvents | {         |     |     |
| --------------------------------- | ---------- | --------- | --- | --- |
| public subscriptions:             |            | any = {}; |     |     |
| public registerSubscriberForEvent |            |           | (   |     |
| eventName:                        | ChatEvent, |           |     |     |
| subscriptionName:                 |            | string,   |     |     |
| cb: SubscriptionCallback          |            |           |     |     |
| ): void                           | {          |           |     |     |
this.createEventKeyIfNotExists(eventName);
| this.addEventSubscription(eventName, |     |     | subscriptionName, | cb); |
| ------------------------------------ | --- | --- | ----------------- | ---- |
}
private createEventKeyIfNotExists (eventName: ChatEvent): void {
| const                         | exists = this.subscriptions.hasOwnProperty(eventName); |     |       |     |
| ----------------------------- | ------------------------------------------------------ | --- | ----- | --- |
| if (!exists)                  | {                                                      |     |       |     |
| this.subscriptions[eventName] |                                                        |     | = {}; |     |
}
}
| private                                         | addEventSubscription | (       |     |     |
| ----------------------------------------------- | -------------------- | ------- | --- | --- |
| eventName:                                      | ChatEvent,           |         |     |     |
| subscriptionName:                               |                      | string, |     |     |
| cb: SubscriptionCallback                        |                      |         |     |     |
| ): void                                         | {                    |         |     |     |
| this.subscriptions[eventName][subscriptionName] |                      |         | =   | cb; |
}
151

Breakhorizontallywhennecessary
Sometimeslinesgetalittletoolongtoread. Iadvisetostrategicallybreakyourcodehor-
izontally when it surpasses an appropriate line length (most developers use line lengths
from80to120characterslong).
Badhorizontalbreaking
export class UpvotePost implements UseCase<UpvotePostDTO, Promise<UpvotePostResponse>> {
...
constructor (memberRepo: IMemberRepo, postRepo: IPostRepo, postVotesRepo: IPostVotesRepo, postService: PostService) {
| this.memberRepo    | =           | memberRepo;     |     |
| ------------------ | ----------- | --------------- | --- |
| this.postRepo      | = postRepo; |                 |     |
| this.postVotesRepo |             | = postVotesRepo |     |
| this.postService   |             | = postService;  |     |
}
}
Goodhorizontalbreaking
| export class | UpvotePost | implements | UseCase< |
| ------------ | ---------- | ---------- | -------- |
UpvotePostDTO,
Promise<UpvotePostResponse>
> {
...
| constructor    | (               |     |     |
| -------------- | --------------- | --- | --- |
| memberRepo:    | IMemberRepo,    |     |     |
| postRepo:      | IPostRepo,      |     |     |
| postVotesRepo: | IPostVotesRepo, |     |     |
| postService:   | PostService     |     |     |
) {
| this.memberRepo    | =           | memberRepo;     |     |
| ------------------ | ----------- | --------------- | --- |
| this.postRepo      | = postRepo; |                 |     |
| this.postVotesRepo |             | = postVotesRepo |     |
| this.postService   |             | = postService;  |     |
}
}
Breakingyourcodethiswaypreventsotherswithsmallermonitorsfromneedingtostopto
scrollhorizontallyinordertoreadyourcode.
Prefersmallerfiles
InUncleBob’sresearchonCleanCode,hediscoveredthattheaveragefilesizeacrossseveral
enterpriseJavaprojectswere200to500lineslong.
Smallerfilesaregenerallyeasiertoreadandmaintain. Lesscodeinafilemeanslesstoread.
If there’s less to read, there’s less to understand, and the surface area of getting confused
aboutwhatthefiledoes(separationofconcerns),andwhatit’sresponsiblefor(singlere-
sponsibility)issmaller.
152

That is, smaller files are an indication that good Separation of concerns and Singular Re-
sponsibilitywereimplemented.
Consistency
Readabilitytruth#2-Consistencyhelpsreadersbuildcomprehensionmomen-
tum
As you grow acclimated to a new project, your ability to read code and understand how
thingsworkshouldincreaseexponentially.
Humanspickuponpatterns. It’showwemakesenseoftheworld. It’showwelearnand
buildmomentum. Withoutconsistency, wecan’tidentifypatterns, andwecertainlycan’t
buildmomentum.
Capitalization
Thewayweusecapitalizationinprogrammingisstrategic.
Therearethreeprimarycapitalizationconventions:
Pascalcase,whichLooksLikeThis
•
Camelcase,whichlooksLikeThis,and
•
Underscores,whichlooks_like_this
•
Capitalizationprovidesadditionalinformationaboutindentifiers. Forexample, ifweuse
camel casing for variables and methods, and pascal case for classes and namespaces, we
canquicklyfigureoutwhenwhattypeofconstructwe’relookingatjustbythecasingalone.
That’syourinternalpatternmatchingatwork.
In JavaScript and TypeScript, camelCase is preferred for variables, functions, and class
members,whilePascalCaseispreferredforeverythingelselikeclassnames,namespaces,
types,andinterfaces.
| // Type   | names are | pascal-cased    |
| --------- | --------- | --------------- |
| type User | = {       |                 |
| // Type   | members   | are camel-cased |
id: string;
| name: | string; |     |
| ----- | ------- | --- |
}
| // Class     | names are      | pascal-cased |
| ------------ | -------------- | ------------ |
| class        | UserModel      | {            |
| // Accessors | are            | camel-cased  |
| get          | id (): string  | {            |
| return       | this.props.id; |              |
}
| get    | name (): string  | {   |
| ------ | ---------------- | --- |
| return | this.props.name; |     |
153

}
...
}
| // Variables |                        | and functions |     | are | camel-cased         |     |     |
| ------------ | ---------------------- | ------------- | --- | --- | ------------------- | --- | --- |
| const        | shouldListenToJohnMaus |               |     |     | = likesWeirdMusic() |     |     |
&& isGenerallyAHappyPerson();
| type CanPlaySynth |     |       | = {          |     |         |     |              |
| ----------------- | --- | ----- | ------------ | --- | ------- | --- | ------------ |
| // Class,         |     | type, | or interface |     | members | are | camel-cased. |
| favouriteSynth:   |     |       | Synth;       |     |         |     |              |
}
Inotherlanguages,likePython,theruleforeverythingisthat“functionandvariablenames
shouldbelowercase,withwordsseparatedbyunderscores”.
ThismeansthatPythoncodeoftenlooksmuchdifferentthanTypeScriptorJavaScriptcode,
whereunderscorecasingisrarelyused.
| user_name | =   | "Khalil"; |     |     |     |     |     |
| --------- | --- | --------- | --- | --- | --- | --- | --- |
InC#,methodsarepascal-cased.
user.ResetPassword(newPassword);
Intheend,itdoesn’tmatterwhatyouchooseasyourcapitalizationrules,solongasyouand
yourteamsticktoit.
Bad(TypeScript):
| const | DAYS_IN_WEEK |     | = 7;  |        |            |     |     |
| ----- | ------------ | --- | ----- | ------ | ---------- | --- | --- |
| const | daysInMonth  |     | = 30; | // Not | consistent |     |     |
const songs = ['Back In Black', 'Stairway to Heaven', 'Hey Jude'];
const Artists = ['ACDC', 'Led Zeppelin', 'The Beatles']; // Not consistent
| function       | eraseDatabase()    |        |        | {}   |           |            |     |
| -------------- | ------------------ | ------ | ------ | ---- | --------- | ---------- | --- |
| function       | restore_database() |        |        |      | {} // Not | consistent |     |
| type animal    |                    | = { /* | ...    | */ } |           |            |     |
| type Container |                    | = {    | /* ... | */   | } // Not  | consistent |     |
Good(TypeScript):
| const | DAYS_IN_WEEK  |     | = 7;  |     |     |     |     |
| ----- | ------------- | --- | ----- | --- | --- | --- | --- |
| const | DAYS_IN_MONTH |     | = 30; |     |     |     |     |
const SONGS = ['Back In Black', 'Stairway to Heaven', 'Hey Jude'];
| const | ARTISTS | = ['ACDC', |     | 'Led | Zeppelin', |     | 'The Beatles']; |
| ----- | ------- | ---------- | --- | ---- | ---------- | --- | --------------- |
154

| function       | eraseDatabase()   |            | {}  |      |
| -------------- | ----------------- | ---------- | --- | ---- |
| function       | restoreDatabase() |            |     | {}   |
| type Animal    |                   | = { /* ... | */  | }    |
| type Container |                   | = { /*     | ... | */ } |
Whitespacerules
Ever worked on a project where you were able to tell who wrote the code you’re looking
| atjustbythewayitwasformatted? |     |     |     | That’snotnormallyagoodthing. |
| ----------------------------- | --- | --- | --- | ---------------------------- |
If one developer’s code editor adds 4 spaces when they hit the TAB, and another adds 2
spaces when they hit TAB, you’ll start to resent any developer writing code in files you’ve
previouslywrittencode,withthemmessingupyourbeautifulformatting.
Whowantstospendtimeandeffortcleaningupthelivingroomifsomeoneisjustgoingto
trackmudeverytimetheywalkthroughthehouse?
Asacodewriter,thisisannoying-andifyoucareaboutthecode,you’llworktofixit. That’s
timeawayfromdoingmeaningfulwork.
Asacodereader,it’sdistracting.
You want to get this fixed and out of the way as soon as possible on any project. Luckily,
thisissomethingthatcanbeaddressedwiththepropertooling. See#3-2-1-2-Toolingto
enforceconventionsforhowtoremedythis.
Storytelling
Readabilitytruth#3-Peopleloseinterestandgetconfusedifthemostimpor-
tantdetailsaren’tprovidedupfront
NewspaperCodeandtheStep-downPrinciple
Inatraditionalstory, wetakethereaderthroughajourneybysettingupthescene, intro-
ducingthemtothecharacters,thenposingtheconflict.
Wedon’twanttodothatwithcode. Instead,let’sgettotheinterestingstuff rightaway.
TheNewspaperCodePrinciplesaystofront-loadafilewiththemostimportantthings. By
puttingthemostessentialthingsthatwewantthereadertoknowaboutfirst,andmoving
thelesscritical(yetlikelystillimportant)detailstowardsthebottom,readerscanlearnthe
primaryreasonwhytheclassexistsinthefirstplace,muchquicker.
Here’sanexampleofaRecordingStudioclass.
| export  | class      | RecordingStudio |            | {   |
| ------- | ---------- | --------------- | ---------- | --- |
| private | bandroom:  |                 | Bandroom;  |     |
| private | metronome: |                 | Metronome; |     |
| private | controls:  |                 | Controls;  |     |
155

| constructor | (...) | {   |     |     |     |     |
| ----------- | ----- | --- | --- | --- | --- | --- |
...
}
// OK, this is the first thing I'm seeing... I'm not sure how this
// is useful to me as a reader. Also, it's private. It's most likely
| // a detail | that | is used | somewhere | else. | Onwards... |     |
| ----------- | ---- | ------- | --------- | ----- | ---------- | --- |
private getDemoFromLibrary (demoNameQuery: string, artist: Artist): Demo {
| return | this.demoLibrary.find(demoNameQuery, |     |     |     | artist); |     |
| ------ | ------------------------------------ | --- | --- | --- | -------- | --- |
}
// Another private method. Seems like another internal detail.
| private recordVocals |     |     | (demo: Demo): | void | {   |     |
| -------------------- | --- | --- | ------------- | ---- | --- | --- |
...
}
| // Yet another            |                    | internal | detail.      |     |              |        |
| ------------------------- | ------------------ | -------- | ------------ | --- | ------------ | ------ |
| private prepareInstrument |                    |          | (instrument: |     | Instrument): | void { |
| switch                    | (instrument.class) |          | {            |     |              |        |
| case                      | Guitar.class:      |          |              |     |              |        |
| case                      | Bass.class:        |          |              |     |              |        |
instrument.tune();
instrument.setTone();
| case | Drum.class: |     |     |     |     |     |
| ---- | ----------- | --- | --- | --- | --- | --- |
instrument.replaceDrumHeads();
instrument.tune();
default:
}
}
private assembleMusiciansForInstruments (instruments: Instrument[]): void {
instruments.forEach(
| (instrument) |     | => this.bandroom.callMusicianFor(instrument) |     |     |     |     |
| ------------ | --- | -------------------------------------------- | --- | --- | --- | --- |
);
}
| private masterDemo |     | (demo: | Demo): | void | {   |     |
| ------------------ | --- | ------ | ------ | ---- | --- | --- |
...
}
| private mixLevels |     | (demo: | Demo): | void | {   |     |
| ----------------- | --- | ------ | ------ | ---- | --- | --- |
...
}
// Ah, finally - something that clients can call. This is probably
// what this class is for... I just wish I didn't have to get to the
156

// bottom of the file and read everything else in order to find it.
public async recordSong (demoNameQuery: string, artist: Artist): void {
| const    | demo = this.getDemoFromLibrary(demoNameQuery, |                          |     | artist); |
| -------- | --------------------------------------------- | ------------------------ | --- | -------- |
| const    | instruments                                   | = demo.getInstruments(); |     |          |
| for (let | instrument                                    | of instruments)          | {   |          |
this.prepareInstrument();
}
this.metronome.setBpm(demo.bpm);
this.assembleMusiciansForInstruments(instruments);
this.controls.startRecording();
| await | this.bandroom.performSong(demo); |     |     |     |
| ----- | -------------------------------- | --- | --- | --- |
this.controls.stopRecording();
| await | this.recordVocals(demo); |     |     |     |
| ----- | ------------------------ | --- | --- | --- |
| await | this.mixLevels(demo);    |     |     |     |
| await | this.masterDemo(demo);   |     |     |     |
}
}
By convention, when we use classes, the first two things that appear at the top of the class
aretheclassmembervariablesandtheconstructor. That’sconvention,soweusuallydon’t
messwiththat.
Now, after that (the constructor and member variables, that is), in the example provided,
we’re looking at private methods that appear to be responsible for low-level details. To
speedupthereader’sabilitytounderstandtheusefulnessofthisclassandwhyitexists,we
shouldpromotethemostimportantmethod,thepublicrecordSongmethod,tothetop.
recordSong should be higher up not only because it is more important, but because it is
publically exposed to the client. For someone reading this class, within the first couple
of seconds, we should aim to let them know what the most important methods are,
recordSong
and is important because it is what the client will call in order to kick off the
process.
Let’simprovethisbymovingrecordSongclosertothetopofthefile.
| export class | RecordingStudio     | {          |     |     |
| ------------ | ------------------- | ---------- | --- | --- |
| private      | bandroom: Bandroom; |            |     |     |
| private      | metronome:          | Metronome; |     |     |
| private      | controls: Controls; |            |     |     |
| constructor  | (...) {             |            |     |     |
...
}
public async recordSong (demoNameQuery: string, artist: Artist): void {
| const | demo = this.getDemoFromLibrary(demoNameQuery, |     |     | artist); |
| ----- | --------------------------------------------- | --- | --- | -------- |
157

| const    | instruments | = demo.getInstruments(); |              |     |     |
| -------- | ----------- | ------------------------ | ------------ | --- | --- |
| for (let | instrument  | of                       | instruments) | {   |     |
this.prepareInstrument();
}
this.metronome.setBpm(demo.bpm);
this.assembleMusiciansForInstruments(instruments);
this.controls.startRecording();
| await | this.bandroom.performSong(demo); |     |     |     |     |
| ----- | -------------------------------- | --- | --- | --- | --- |
this.controls.stopRecording();
| await | this.recordVocals(demo); |     |     |     |     |
| ----- | ------------------------ | --- | --- | --- | --- |
| await | this.mixLevels(demo);    |     |     |     |     |
| await | this.masterDemo(demo);   |     |     |     |     |
}
private getDemoFromLibrary (demoNameQuery: string, artist: Artist): Demo {
| return | this.demoLibrary.find(demoNameQuery, |     |     | artist); |     |
| ------ | ------------------------------------ | --- | --- | -------- | --- |
}
| private | recordVocals | (demo: | Demo): | void { |     |
| ------- | ------------ | ------ | ------ | ------ | --- |
...
}
| private | prepareInstrument  |     | (instrument: | Instrument): | void { |
| ------- | ------------------ | --- | ------------ | ------------ | ------ |
| switch  | (instrument.class) |     | {            |              |        |
| case    | Guitar.class:      |     |              |              |        |
| case    | Bass.class:        |     |              |              |        |
instrument.tune();
instrument.setTone();
| case | Drum.class: |     |     |     |     |
| ---- | ----------- | --- | --- | --- | --- |
instrument.replaceDrumHeads();
instrument.tune();
default:
}
}
private assembleMusiciansForInstruments (instruments: Instrument[]): void {
instruments.forEach(
| (instrument) |     | => this.bandroom.callMusicianFor(instrument) |     |     |     |
| ------------ | --- | -------------------------------------------- | --- | --- | --- |
);
}
| private | masterDemo | (demo: | Demo): | void { |     |
| ------- | ---------- | ------ | ------ | ------ | --- |
...
}
| private | mixLevels | (demo: | Demo): | void { |     |
| ------- | --------- | ------ | ------ | ------ | --- |
158

...
}
}
That’sanimprovement,butwhatdowedowiththerestofthemethods? Dowejustleave
themwheretheyare?
TheNewspaperprinciplesuggeststore-orderthesesothattheleastimportantdetailsareto-
wardsthebottom,sowemaybeabletoleavethisifwechoosetofollowthatprinciple.
Alternatively,theStepdownPrinciple,whichpairsnicelywiththeNewspaperprinciple,saysthat
we should organize code so that we can read it from top-to-bottom. Easier said than
done,myfriends.
Toaccomplishthis,wehavetomakemethodcallersandcalleesclosetogether. Ifamethod
callsanothermethod,thecalleeshouldbedirectlybelowit. Thiswouldemulatetheeffect
ofreadingabook.
I have found this to be more challenge than it’s worth since it forces you to refactor your
methodsinanunnaturalway.
Youcouldgiveitatry,butifyouaregoingtoimplementit,considertheCQSprincipleand
trytosticktoitasyourefactor.
Maintainingaconsistentlevelofabstraction
Sometimes you’ll notice that there’s a mismatch with the level of abstraction and de-
tailswithinamethod.
Checkthisout. Inarefactoredversionofthepreviousexample,afterwegetthedemoobject,
thesetofinstructionsthatfollowsseemsalotmoredetail-orientedthanthesetofinstruc-
tionsthathappenstowardstheendofthemethod.
| export class | RecordingStudio |     | {   |     |     |
| ------------ | --------------- | --- | --- | --- | --- |
...
public async recordSong (demoNameQuery: string, artist: Artist): void {
| const    | demo = this.getDemoFromLibrary(demoNameQuery, |                          |         |     | artist); |
| -------- | --------------------------------------------- | ------------------------ | ------- | --- | -------- |
| // The   | level of abstraction                          |                          | here... |     |          |
| const    | instruments                                   | = demo.getInstruments(); |         |     |          |
| for (let | instrument                                    | of instruments)          |         | {   |          |
this.prepareInstrument();
}
this.metronome.setBpm(demo.bpm);
this.assembleMusiciansForInstruments(instruments);
this.controls.startRecording();
| await | this.bandroom.performSong(demo); |     |     |     |     |
| ----- | -------------------------------- | --- | --- | --- | --- |
this.controls.stopRecording();
| // ... | is different             | from | the level | of abstraction | here |
| ------ | ------------------------ | ---- | --------- | -------------- | ---- |
| await  | this.recordVocals(demo); |      |           |                |      |
159

| await | this.mixLevels(demo);  |     |     |     |     |
| ----- | ---------------------- | --- | --- | --- | --- |
| await | this.masterDemo(demo); |     |     |     |     |
}
}
Thisleadsusintoanotherconventionyoucouldimplement.
Codeshoulddescendinabstractiontowardslower-leveldetails
recordMusicFromDemo,
We can group those first few operations as maintaining a similar
levelofabstractionfromwithinrecordSongmethodandleavingthedetailstolivewithin
eachrespectivemethodforfurtherdecomposition.
| export class | RecordingStudio | {   |     |     |     |
| ------------ | --------------- | --- | --- | --- | --- |
...
public async recordSong (demoNameQuery: string, artist: Artist): void {
| const | demo = this.getDemoFromLibrary(demoNameQuery, |     |     |     | artist); |
| ----- | --------------------------------------------- | --- | --- | --- | -------- |
| await | this.recordMusicFromDemo(demo);               |     |     |     |          |
| await | this.recordVocals(demo);                      |     |     |     |          |
| await | this.mixLevels(demo);                         |     |     |     |          |
| await | this.masterDemo(demo);                        |     |     |     |          |
}
}
Itwillalmostneverbeperfect,butjustattemptingtooptimizeforreadabilityisempathetic
andsmalleffortsaddupovertime.
Keepingrelatedmethodsclosetoeachother
Sometimestherearemethodsorfunctionsthatjustbelongtogether. Forexample,takethis
getter/setterpairbrokenupbyalogoutmethod.
Bad
| export class | Member extends | Aggregate<MemberProps> |     | {   |     |
| ------------ | -------------- | ---------------------- | --- | --- | --- |
...
| get username | (): Username         | {   |     |     |     |
| ------------ | -------------------- | --- | --- | --- | --- |
| return       | this.props.username; |     |     |     |     |
}
| // Breaks               | relationship    | between               | getter/setter | above | and below |
| ----------------------- | --------------- | --------------------- | ------------- | ----- | --------- |
| public                  | logout (): void | {                     |               |       |           |
| this.addDomainEvent(new |                 | UserLoggedOut(this)); |               |       |           |
}
| set username | (username: | string): | void { |     |     |
| ------------ | ---------- | -------- | ------ | --- | --- |
const newUserNameResult: Result<Username> = Username.create(username);
160

| if (newUserNameResult.isSuccess()) |     |                                 | {   |     |
| ---------------------------------- | --- | ------------------------------- | --- | --- |
| this.addDomainEvent(new            |     | UsernameChanged(this));         |     |     |
| this.props.username                |     | = newUserNameResult.getValue(); |     |     |
}
}
}
Whenthereisaninherentgroupingbetweenrelatedmethods,strivetokeepthemcloseto
eachother.
Good
| export class | Member extends | Aggregate<MemberProps> |     | {   |
| ------------ | -------------- | ---------------------- | --- | --- |
...
| get username | (): Username         | {   |     |     |
| ------------ | -------------------- | --- | --- | --- |
| return       | this.props.username; |     |     |     |
}
| set username | (username: | string): | void { |     |
| ------------ | ---------- | -------- | ------ | --- |
const newUserNameResult: Result<Username> = Username.create(username);
| if (newUserNameResult.isSuccess()) |     |                                 | {   |     |
| ---------------------------------- | --- | ------------------------------- | --- | --- |
| this.addDomainEvent(new            |     | UsernameChanged(this));         |     |     |
| this.props.username                |     | = newUserNameResult.getValue(); |     |     |
}
}
| public logout           | (): void | {                     |     |     |
| ----------------------- | -------- | --------------------- | --- | --- |
| this.addDomainEvent(new |          | UserLoggedOut(this)); |     |     |
}
}
In summary, be empathatic that we’re writing code for humans and that we all have con-
| straints. Whenyouformatcode, |     |     |     |     |
| ---------------------------- | --- | --- | --- | --- |
• Usewhitespaceappropriately
• Beconsistentwithformatting
• Usestorytellingtoputthemostimportantthingsfirstandlogicallygroupcodethat
belongstogether
Enforcingformattingruleswithtooling
Now. Howtheheckdoweenforcetheseguidelines? Whatdowedowhenwe’reonateam
| with20+developers? | Howdowegeteveryonetobeonthesamepage? |     |     |     |
| ------------------ | ------------------------------------ | --- | --- | --- |
Weusetooling.
ThecurrenttrifectaoftoolingforformattingintheJavaScript/TypeScriptcommunityis:
161

• ESLint(overTSLint)
• Prettier
• Husky
ESLint
ESLintisaJavaScriptlinterthatenablesyoutoenforceasetofstyle,formatting,andcoding
standardsforyourcodebase. Itlooksatyourcode,andtellsyouwhenyou’renotfollowing
thestandardthatyousetinplace.
Forexample,ifIwantedtocheckmycodetomakesurethattherewerenoconsole.logstate-
ments,IcoulduseanESLintruletoenforcethat.
eslint.rc
{
"root": true,
"parser": "@typescript-eslint/parser",
"plugins": [
"@typescript-eslint",
"no-loops"
],
"extends": [
"eslint:recommended",
"plugin:@typescript-eslint/eslint-recommended",
"plugin:@typescript-eslint/recommended"
],
"rules": {
"no-console": "error" // you can also use the int value "2"
}
}
Andwithannpmscriptinmypackage.jsonfortheproject,Icouldaddalintcommand:
package.json
{
"scripts": {
...
"lint": "eslint . --ext .ts",
}
}
Andrunitwith:
npm run lint
DependingonhowI’veconfiguredmyrules,Iwilleitherseenothing,awarning,oranerrorin
myconsole.
InESLint,youcansetyourrulestobeeitheroff,warn,orerror.
162

• “off”means0(turnstheruleoffcompletely)
• “warn”means1(turnstheruleonbutwon’treturnanon-zeroexitcode)
• “error”means2(turnstheruleonandwillreturnanon-zeroexitcode)
ESLintbecomesmoreusefulwhenwecombineitwithPrettierandHusky.
Prettier
Whatifwedon’twanttohavetoremembertorunthelintereverytimewewritenewcode?
What if there was a way that we could, while coding, have it automatically format things
basedonourconventions?
Prettierisanopinionated(yetfullyconfigurable)codeformatter. ESLintcankindof format
code too, but ESLint is mostly intended to simply sniff out when we’re not following the
mandatedcodingconventions.
Prettiercanbeconfiguredtoformatyourcode(ie: makeitlookprettier(cid:0))afteryousaveafile
ormanuallytellitto. Bydefault,itcomesconfiguredwithasetofcommoncodecleanliness
rules.
WithESLintandPrettier,
• ESLintdefinesthecodeconventions
• Prettierperformstheauto-formattingbasedontheESLintrulesintheconfig.
PrettiercaneitherbeinstalledasVSCodeplugin,orconfiguredtoformatyourcodeviathe
commandline.
YoucansetandenforceruleslikesettingamaxprintWidthanddecidingoniftrailingCommasare
allowedbynotbyinstallingprettierasadevdependency,thenwritinga.prettierrccon-
figfile.
npm install --save-dev prettier && touch .prettierrc
._prettierrc_
{
"semi": true,
"trailingComma": "none",
"singleQuote": true,
"printWidth": 80
}
There’salittlebitofconfigurationinvolvedtogetPrettiertolooktoESLintfortherules. You
canlearnabouthowtodothatinthisshortguideIputtogether.
ThelastpieceinthepuzzleisHusky.
Husky
Huskyisannpmpackagethat“makesGithookseasy”.
When you initialize Git (the version control tool that you’re probably familar with) on a
project,itautomaticallycomeswithafeaturecalledhooks.
163

IfyougototherootofaprojectinitializedwithGitandtype:
ls .git/hooks
You’llseealistofsamplehookslikepre-push,pre-rebase,pre-commit,andsoon. Thisis
awayforustowriteplugincodetoexecutesomelogicbeforeweperformtheaction.
Ifwewantedtoensurebeforesomeonecreatesacommitusingthegit commitcommand,
thattheircodewasproperlylintedandformatted,wecouldwriteapre-commitGithook.
Writingthatmanuallyisn’tataskforthefaintofheart. Itwouldalsobeachallengetodis-
tributeandensurethathookswereinstalledonotherdevelopers’machines.
ThesearesomeofthechallengesthatHuskyaimstoaddress.
WithHusky,wecanensurethatforanewdeveloperworkinginourcodebase(usingatleast
Nodeversion10):
• Hooksgetcreatedlocally
• HooksarerunwhentheGitcommandiscalled
• Policythatdefineshowsomeonecancontributetoaprojectisenforced.
Therefore,
• ESLintdefinesthecodeconventions
• Prettierperformstheauto-formattingbasedontheESLintrulesintheconfig.
• Huskyensuresthattheformattingscriptsgetappliedbeforeanycodemakesits
wayintosourcecontrol.
Insummary,don’tspendtimefumblingaroundformattingrulesinPRswhenyoucanusing
toolingtoenforce’em.
ReadtheCleanCodeToolingserieshere: 1. HowtouseESLintwithTypeScript2. Howto
usePrettierwithESLintandTypeScriptinVSCode3. EnforcingCodingConventionswith
HuskyPre-commitHooks
Comments
Iusedtolovecomments. Ithoughtthatcodewasincompleteifeachmethoddidn’thavea
comment. I thought that commenting my code made it more readable and improved the
overallqualityofthecode.
The discussion about when to comment your code can get pretty heated. Some swear by
comments. Somesayit’sunprofessionalnottocomment. Somethinkitcluttersyourcode.
Here’smymethodology.
Codeexplainswhatandhow,commentsexplainwhy
WhenweuseEnglishtowritedeclarativecodethatusesgoodnames,weestablishthewhat.
Whatisthiscodedoing?Ah,thiscreateMusicRecommendationsmethodusesmylisteningHistory,
theartistsIfollow,andmyplayliststocreatemusicRecommendations.
164

| export                           | class SpotifyService       | {   |     |     |     |
| -------------------------------- | -------------------------- | --- | --- | --- | --- |
| public                           | createMusicRecommendations |     | (   |     |     |
| history:                         | ListeningHistory,          |     |     |     |     |
| artists:                         | Artists,                   |     |     |     |     |
| playLists:                       | Playlists                  |     |     |     |     |
| ): Promise<MusicRecommendations> |                            |     | {   |     |     |
...
}
}
Ataveryhigh-level,weshouldbeabletodeducefromthemethodname,parameters,andas
muchdeclarativecodeaspossibleinsideofamethodorfunctionbody-whatthecodedoes.
Without descending a layer deeper into subsequent method calls and lower-level details,
thehigh-levelcodeexcelsatexplainingthewhat.
Lower-level code describes the how. With each descending layer of abstraction, the code
shouldcontinuetoexplainthathow.
Ifyoucangetallthewaytothebottomwithoutfeelinglikecommentsarenecessarybecause
thecodeisthatclearandreadable,applaudyourself.
However,it’spossible(andlikely)thatyou’ll:
• Encountersomethingthatcan’teasilyberefactoredtobesimpler.
• Require the use of an algorithm or implementation that is fundamentally more
complex(absolutecomplexity),usuallyforperformanceoroptimizationreasons.
At this point, it’s a good idea to write a comment; not to describe the what or how, but to
explainwhy.
/* You might be wondering why we're using a Splay Tree. We've
| discovered | that a binary | search tree | gets very  | slow once | we  |
| ---------- | ------------- | ----------- | ---------- | --------- | --- |
| have over  | 5000 entries. | We're using | Splay Tree | because   |     |
everytime an entry is accessed, it pushes it closer towards the
top of the tree, making subsequent retrievals more efficient. */
Commentsshouldn’texplainwhatthecodeisdoing. Makethecodeexplainthat.
Assumeyouhaveaone-linerthatisnecessary,butcomplex.
InJavaScript,thisishowyoupadatwo-digitnumberwithzeros.
num = ('0'+num).substr(-2);
Thislineconvertsanumber,suchas1,intoazero-paddedstringlike"01".
Atthemoment,thiscodeanswersnoneofthefollowingquestions.
| • What: | Whatdoesthisdo?     |                            |     |     |     |
| ------- | ------------------- | -------------------------- | --- | --- | --- |
| • How:  | Howdoesitdoit?      | (reliesonfirstknowingwhat) |     |     |     |
| • Why:  | Whyisitdonethisway? |                            |     |     |     |
165

| Ourfirstrefactoringsolvesthequestionof |                      |       |        |            | whatandhow. |        |     |
| -------------------------------------- | -------------------- | ----- | ------ | ---------- | ----------- | ------ | --- |
| function                               | padZeros             | (num: | number | | string): |             | string | {   |
| return                                 | ('0'+num).substr(-2) |       |        |            |             |        |     |
}
...
num = padZeros(num);
That’sgreat,butwestilldon’treallyknowwhywe’dneedtousethis.
Thisiswherewecoulduseawell-placedcomment.
/**
| * We've | found | that | when conditionally |     |     | displaying | numbers |
| ------- | ----- | ---- | ------------------ | --- | --- | ---------- | ------- |
* with leading zeros, like seconds in the format of hh:mm:ss,
* we can't rely on JavaScript's default string formatting abilities.
*
* Use this when the minutes or seconds are less than ten, and
| * you | want | to see | :00 and | not just | :0. |     |     |
| ----- | ---- | ------ | ------- | -------- | --- | --- | --- |
*/
| function | padZeros             | (num: | number | | string): |     | string | {   |
| -------- | -------------------- | ----- | ------ | ---------- | --- | ------ | --- |
| return   | ('0'+num).substr(-2) |       |        |            |     |        |     |
}
...
num = padZeros(num);
Furthermore,sincethewhyisclear,wenowalsoknowthatthisfunctionprobablybelongs
withotherTextorDateutilities(seealso,context).
| export | class | TextUtils | {   |     |     |     |     |
| ------ | ----- | --------- | --- | --- | --- | --- | --- |
/**
| * We've |     | found that | when conditionally |     |     | display | numbers |
| ------- | --- | ---------- | ------------------ | --- | --- | ------- | ------- |
* with leading zeros, like seconds in the format of hh:mm:ss,
* we can't rely on JavaScript's default string formatting abilities.
*
* Use this when the minutes or seconds are less than ten, and
| * you | want | to see | :00 and | not just | :0. |     |     |
| ----- | ---- | ------ | ------- | -------- | --- | --- | --- |
*/
| public | static | padZeros             | (num: | number |     | | string): | string { |
| ------ | ------ | -------------------- | ----- | ------ | --- | ---------- | -------- |
| return |        | ('0'+num).substr(-2) |       |        |     |            |          |
}
}
...
num = TextUtils.padZeros(num);
Commentscluttercode
166

CommentsthatanswerwhyaretheonlytimeIcanadvocateforthembeingnecessary. To
make my argument in another illustration, look at the following unclean code with com-
mentsinit.
| const | _x: number | = abs(x | - deviceInfo.position.x) |     |     | / scale; |
| ----- | ---------- | ------- | ------------------------ | --- | --- | -------- |
let directionCode;
| if (0         | < _x & x !=                     | deviceInfo.position.x)     |            |     | {   |     |
| ------------- | ------------------------------- | -------------------------- | ---------- | --- | --- | --- |
| if            | (0 > x - deviceInfo.position.x) |                            |            | {   |     |     |
| directionCode |                                 | = 0x04                     | /*left*/;  |     |     |     |
| } else        | if (0 <                         | x - deviceInfo.position.x) |            |     | {   |     |
| directionCode |                                 | = 0x02                     | /*right*/; |     |     |     |
}
}
Commentsdon’tnecessarilymeanreadablecode. Infact,thosecommentswedgedinthere
don’thelp. It’slipstickonapig. Itdoesn’tmaketheuncleancodeanycleanerandthecode
isjustashardtounderstand.
Nowwatchhowmuchmorereadablethecodegetswhenwerefactoritbystrippingoutthe
comments,declaringvariablesatthetopandusingtheconstant-variablenamingconven-
tion.
| const | DIRECTIONCODE_RIGHT:          |     | number | = 0x02; |     |     |
| ----- | ----------------------------- | --- | ------ | ------- | --- | --- |
| const | DIRECTIONCODE_LEFT:           |     | number | = 0x04; |     |     |
| const | DIRECTIONCODE_NONE:           |     | number | = 0x00; |     |     |
| const | oldX = deviceInfo.position.x; |     |        |         |     |     |
const directionCode = (x > oldX) ? DIRECTIONCODE_RIGHT : (x < oldX) ? DIRECTIONCODE_LEFT : DIRECTIONCODE_NONE;
Commentsoftenhurtmorethanithelpsreadability.
Turningcommentsintoclear,explanatory,declarativecode
Usually,it’spossibleforustorefactorcommentsintocode.
| // Check | to see | if buyer | eligible | for | loan for | property |
| -------- | ------ | -------- | -------- | --- | -------- | -------- |
// if the buyers credit score is greater than the minimal approval
// and the last job they were at, they were there for longer than the
|     | // minimum | employment | length |     |     |     |
| --- | ---------- | ---------- | ------ | --- | --- | --- |
// AND, their downpayment preferred value is the minimum downpayment
| //  | based on the  | type    | of property | it   | is, |     |
| --- | ------------- | ------- | ----------- | ---- | --- | --- |
| //  | THEN, we will | approve | their       | loan |     |     |
Thenamesofthevariablesandmethodscanusethesamewordsfromourcomments,and
thisiswhatmakesitdeclarative.
| // Check | to see | if buyer | eligible | for | loan for | property |
| -------- | ------ | -------- | -------- | --- | -------- | -------- |
if (
| (buyer.creditScore |     | >=  | MIN_APPROVAL_SCORE) |     | &&  |     |
| ------------------ | --- | --- | ------------------- | --- | --- | --- |
(buyer.jobHistory
.getLast()
| .getEmploymentLength() |     |     | >= MIN_EMPLOYMENT_LENGTH) |     |     | &&  |
| ---------------------- | --- | --- | ------------------------- | --- | --- | --- |
167

| (downpayment.value) |     |                        | >= getMinimumDownpayment( |     |     |     |
| ------------------- | --- | ---------------------- | ------------------------- | --- | --- | --- |
| property,           |     | downpaymentPercentage) |                           |     |     |     |
)
This is the heart of what we do in the domain layer in a clean architecture using Domain-
DrivenDesign,butit’spossibleinanycode.
This code is a bit verbose, so we can now encapsulate that complexity within the correct
objects,maintainingthelanguageweinitiallyusedwhenwewrotethecomments.
if (buyer.isEligibleForLoan(property, downpaymentPercentage))
Whichwouldyouratherread?
When possible, use a variable or a method/function to express what you would normally
trytoexpressasacomment.
Badcomments
Redundancy-Usingcommentstosaysomethingthatisalreadyadequatelyexpressedwith
code.
| export class | UserService |     | {   |     |     |     |
| ------------ | ----------- | --- | --- | --- | --- | --- |
/**
| * This | method | gets | the | user by | user id. |     |
| ------ | ------ | ---- | --- | ------- | -------- | --- |
*/
| getUserByUserId |     | (userId: |     | string): | Promise<User> | {   |
| --------------- | --- | -------- | --- | -------- | ------------- | --- |
...
}
}
Log/Journalentries-Commentsthatdescribewhenandwhatwaschanged,andwhois-
suedthechange. Thisinformationshouldbetrackedinsourcecontrolinsteadofthesource
codeitself.
/**
* 01-03-2008 - Tony Soprano - Added the ability to also work on strings.
* 01-02-2008 - D. Draper - Added this method to act as a utility that
| * can be | reused. |     |     |     |     |     |
| -------- | ------- | --- | --- | --- | --- | --- |
*/
Commentedoutcode-Codethatiscommentedoutshouldbedeleted.
| // function | parseHandle              |     | (url, | type)            | {   |     |
| ----------- | ------------------------ | --- | ----- | ---------------- | --- | --- |
| // switch   | (type)                   | {   |       |                  |     |     |
| // case     | "twitter":               |     |       |                  |     |     |
| // url      | = url.replace("@",       |     |       | "");             |     |     |
| // url      | = removeUpTo(url,        |     |       | 'twitter.com/'); |     |     |
| // url      | = stripQueryParams(url); |     |       |                  |     |     |
168

// return url;
// default:
// return url;
// }
// }
Closingbracecomments
if (this.exists(userId)) {
if (this.wasEmailNotificationSent(userId)) {
...
} // end of inner if
} // end of if
Whentowritecomments
Tosummarize,here’sthewaythatcommentsshouldnaturallyoccurinyourcode.
• Noticethatcodeseemscomplexorthatanotherhumanbeingmaynotunderstandit.
• Firstattempttorefactorthecode.
• Ifitcan’tbeeasilyrefactoredorfurtherrefactoringwouldmakeitevenmorecomplex,
orifthereissomethingcontextuallyimportantthatcannotbesaidwithcode,leavea
comment.
Ultimately,asaruleofthumb…
Preferrefactoringcodeinsteadofwritingcomments
Anotherwayformetosaythisandmaintainthesamesentimentistosay:
Preferrefactoring(imperative)code(todeclarativecode)insteadofwritingcom-
ments
Demonstration
Mypeer,Swizec,hasadifferentmethodologytocommentingthanIdo. Here’satweetfrom
himsuggestingthatseniordevelopersleavecomments“toexplainwhythingswork”.
169

Asyou’veheardmesayinthissectionalready,ifyou’recuriousabouthowthecodeworks,
it’stheresponsibilityofthecodetoexplainthattoyou,notthecomments.
Ithoughtitwouldbefuntodemonstratearefactoringofthiscode.
Here’sSwizec’scodebelow,withthecomments.
class Stuff {
// the datamodel supports recursion but we haven't used that since 2017
// for simplicity. The UI skips it for now. You can add it back here.
| get formConfig () | {   |     |
| ----------------- | --- | --- |
...
| // Roll them                | up     |      |
| --------------------------- | ------ | ---- |
| .reduce((fields,            | field) | => { |
| if (!Array.isArray(fields)) |        | {    |
field = [field]
}
| // Currently | last field              | in section           |
| ------------ | ----------------------- | -------------------- |
| const        | last =                  | fields.slice(-1)[0], |
| lastValue    | = this.values[last.id]; |                      |
170

|     | if (this.values[field.id]) |        |        | {    |     |     |
| --- | -------------------------- | ------ | ------ | ---- | --- | --- |
|     | // answered                | fields | always | show |     |     |
|     | return [...fields,         |        | field] |      |     |     |
} else if (this.values[last.id] && lastValue.next_field_id === field.id) {
|     | // this            | is the next     | field  | depending  | on the previous | value |
| --- | ------------------ | --------------- | ------ | ---------- | --------------- | ----- |
|     | return [...fields, |                 | field] |            |                 |       |
|     | } else {           |                 |        |            |                 |       |
|     | // this            | field shouldn't |        | be visible | yet             |       |
|     | return fields;     |                 |        |            |                 |       |
}
})
})
}
}
Andhere’sthenewversion,withrefactoringstomakethecodemoredeclarative,un-reliant
oncomments.
—
| class Form | {                       |     |           |     |           |     |
| ---------- | ----------------------- | --- | --------- | --- | --------- | --- |
| private    | isSectionFirstInArray   |     | (fields): |     | boolean { |     |
| return     | !Array.isArray(fields); |     |           |     |           |     |
}
| private | makeArray      | (field) | {   |     |     |     |
| ------- | -------------- | ------- | --- | --- | --- | --- |
|         | return [field] |         |     |     |     |     |
}
| private | getLastValue              | (fields)               | {   |     |     |     |
| ------- | ------------------------- | ---------------------- | --- | --- | --- | --- |
| const   | lastField                 | = fields.slice(-1)[0]; |     |     |     |     |
| return  | this.values[lastField.id] |                        |     |     |     |     |
}
| ... //         | and so on |     |     |     |     |     |
| -------------- | --------- | --- | --- | --- | --- | --- |
| get formConfig | ()        | {   |     |     |     |     |
...
| .reduce((fields, |                                         | field)                  | => { |     |     |     |
| ---------------- | --------------------------------------- | ----------------------- | ---- | --- | --- | --- |
|                  | if (this.isSectionFirstInArray(fields)) |                         |      |     | {   |     |
|                  | fields =                                | this.makeArray(fields); |      |     |     |     |
}
if (this.isAnsweredField(fields, field) || this.isNextField(fields, field)) {
|     | return | [...fields, |     | field] |     |     |
| --- | ------ | ----------- | --- | ------ | --- | --- |
}
171

// Shouldn't be visible yet - I left this, as it explains what cannot
|     | // reasonably | be refactored | to be said | in words |
| --- | ------------- | ------------- | ---------- | -------- |
return fields;
})
})
}
}
| Example: | Addingadditionalcontext |     |     |     |
| -------- | ----------------------- | --- | --- | --- |
InApolloClient,ifyoudon’tdeclareyourprimarykeyfields,it’spossibleyoumaylosedata
There’sawarnAboutDataLossfunctionthat
whenitgetsfetchedandmergedontheclient.
letsyouknowwhenyoumightlosedataduetoanimproperconfig. Thisfunctionisuseful
indevelopment,butit’slikelynotimportantinyourproductionbuilds.
Toaddadditionalcontext,theauthorleftacommentlettingreadersknowthatthefunction
wouldlikelyberemovedfromproductionbuildsifcodegetsminified.
// Note that this function is unused in production, and thus should be
| // pruned       | by any well-configured |     | minifier. |     |
| --------------- | ---------------------- | --- | --------- | --- |
| function        | warnAboutDataLoss(     |     |           |     |
| existingRef:    | Reference,             |     |           |     |
| incomingObj:    | StoreObject,           |     |           |     |
| storeFieldName: | string,                |     |           |     |
| store:          | NormalizedCache,       |     |           |     |
) {
...
}
Thisisagreatexampleofacommentthataddsvaluebyprovidingcontext.
RelationshiptoHumanCenteredDesign
Therelationshipbetweencomments&humancentereddesign
Therelationshipbetweencomments&humancentereddesign
• TheargumentI’mtryingtomake
Commentsshouldonlyexplainthewhy.Ifyouseeacomment,itshouldneverexplain
what. It should never need to explain how. If you need to explain these two things
withacomment,itprobablymeansthatthedesignneedswork.
In our industry’s research into design, we’ve found that two of the most important
characteristicsofdesignarediscoverabilityandunderstanding.
Discoverability=Whatarethepossibleactions? Wherearethey? HowdoIperform
them?
172

Understanding=Whatdoesitmean? Howisthissupposedtobeused? Whatdothe
controlsmean?
Inthebook,TheDesignofEverydaythings,theauthortellsastoryofwhenhisfriend
gottrappedinthedoorwayofapostofficeinaEuropeancity.
Moredoorsexamples
• Twoofthemostimportant
– Adoorwithacurvedgrippablehandleonit-whatwouldyouassumeitcando?
– Adoorwithaflatmetallicsurface-whatwouldyouassumeitcando?
– Adoorwithnohandle,noflat,metallicsurface,andnolabels
– AdoorwithahandlethatsaysPUSH-whatwouldyouassumeitcoulddo?
Namingthings
Amongthehardestproblemsincomputerscience.
It’sremarkablethatwhenwestartoutasdevelopers,welearnaboutalldifferentkindsof
things like variables, methods, classes, and object-oriented programming, yet we almost
neverformallytalkaboutnamingthings: oneofthefirstandmostchallengingproblems
wefaceasprogrammers.
Namingisatthecenterofeverythingwedoatwork. Fromthewordswetype, tothecon-
versationsweholdaboutthethingswe’vetyped,goodnameshavealastingimpactandit’s
aninvestmenttonamethingswell.
Whennamesaregood,ittellsreaderswhatourcodedoesandhow.
When names are poor, it subtracts from the understandability and overall quality of our
code. Thismeansthatittakesnewdeveloperslongertorampuptoanewcodebase,learn
theglossaryofnewtermsfromthedomain,andcontributeinmeaningfulways.
Thesevenprinciplesofnaming
173

Thissectionteachesyouhowtoapproachthetaskofnamingprogrammingconceptsusingpsy-
chological science, engineering, and structure, ratherthan approachingit from a place of
art,creativity,ornoveltythatwetypicallyusetonamecompanies,bands,andproductsfor
commercialreasons.
TheseprincipleswereoriginallyenumeratedbyTomBennerfromNamingThings.co.
From everything I’ve learned throughout my personal experiences working on good and
bad codebases, reading Eric Evans’ “Domain-Driven Design”, Vaughn Vernon’s “Imple-
menting Domain-Driven Design”, and Robert C. Martin’s “Clean Code”, the takeaways all
appeartobecongruentacrosstheseprinciples.
Of course, like most things in science, there’s no such thing as perfect- and with naming
things,you’llcertainlyfindthattobetrue. However,usingtheseprinciples,Ibelievethere’s
awaytogetprettydangclosetogoodenough.
Thesevenprinciplesofnaming
1-Consistency&uniqueness
2-Understandability
3-Specificity
4-Brevity
5-Searchability
6-Pronounceability
7-Austerity
Summary
Names are important but it’s important to remember that they can always be refactored
duringdevelopment. Don’tgettoohungup. Usetheseprinciples, have’eminthebackof
yourmind,pickaname,askforfeedbackandimprove’emovertime.
1-Consistency&uniqueness
Eachconceptshouldberepresentedbyasingle,uniquename-viaNamingThings.co
Consistency
Thehumanbrain,inordertolearn,reliesonconsistency.
Skilledconferencespeakerstakeadvantageofthisfactbystrategicallyusingrepetitionin
ordertoreallydrivehomethemaintakeaways.
Ashumans, werelyalotofpatterns, pastexperiences, andconceptualmodelsinorderto
equipourselveswithhowweshouldactinfuturesituations(rememberwhatwediscussed
aboutdesignforhumans).
174

Forexample,ifyoutoldmeIhadtohitahomerunwithabaseballbat,well…that’sjustnot
happening. SinceIhaveprettymuchzeropreviousbaseballexperience,nounderstanding
ofproperform,andnopredictiononhowthingsmightgo,it’sapotentiallyuncomfortable
situationtobein. Tobeaskedtodemonstratesomethingthattakesalotofskillandknowl-
edge,andpossessingnoneofitupfrontisuncomfortable.
Jumpingintoanewcodebasecanfeelverysimilar.
Itcanfeelasifitmighttakealongtimetobegintofeelproductive.
And while we do work in an industry containing widely accepted patterns around
paradigms,styling,formatting,packaging,architecture,andcodingconventions,it’svery
likelythatyou’llcomeacrossprojectsthatthrowallofthatoutthewindow.
Notonlythat,it’salsolikelythatyou’llstartworkonprojectthatoperatesinadomainthat
youknownothingabout. Frankly, it’simpossibletoassumethatyou’regoingtoknowev-
erything about marine magnetometers, call centers and telephony, or technical re-
cruiting. Itis,however,possible(andexpected)thatyouwilllearn.
Inanewcodebase,
• Wereadcode
• Weidentifythechoicesthathavebeenmade
• Weaskquestions
• Welookforpatterns
Usingthepatternswe’veidentified,weattempttoextendthosepatternsinfuturescenarios.
Ifwefailorneedtobecorrected,weadjustandimprovethenexttimearound.
Thisprocesstakesavariableamountoftime,butweshouldbeconsistentlyimproving-like
afeedbackloop.
175

We gain confidence when we know what to expect, and then we get what we
expect.
Full understanding is when we can correctly address future scenarios on our
own.
Consistency (helps reinforce) → Expectations (when met, turn into) → Confi-
dence(whichimplies)→Understanding
Consistencyinnaming
Failingtobeconsistentwithnameshasthepotentialtodegradeeachoftheotherprinciples
ofnamingthings. Forexample:
• (Name) understandability is negatively affected when consistency is poor because
notonlydowehavetounderstandwhatatermmeansonce,butwealsohavetodeter-
mineifanotherslightlysimilaryetdifferentnamealsoreferstothesameconcept.
• (Name) searchability is negatively affected when consistency is poor. When a con-
ceptneedstoberenamedorchanged,whatwouldnormallybeeasytochangeusing
thefindandreplacefeatureinatexteditorbecomeschallenging.
Consistencywitheverything
It’snotjustimportanttonaming.
Ifarchitecturaldecisionsareconsistent,weunderstandthearchitecturefaster.
176

Ifpackagingdecisionsareconsistent,weunderstandhowpackagingishandledonthis
project,faster.
If certainparadigms are being used in certainsituations on a consistent basis, that paves the
wayforustoassumewhatparadigmtoleantowardsinthosesamesituationsinthe
future.
Therefore,beconsistentbecause:
• Consistencyhelpsbuildmomentum.
• Itimproveseachiterationofthatfeedbackloop,increasingourabilitytobecomepro-
ductive,and
• Seeingthingsdoneaparticularwayconsistentlypreparesusforhowtohandlesimi-
larsituationsinthefuture. Bypresentingknowledgeinaconsistentway,peoplecan
buildpatternsagainstit.
Uniqueness
Name uniqueness is important for several of the same reasons that consistency is impor-
tant,butIthinkthemostimportantisthatitensuresasingularunderstandingofanew
concept.
Startingworkonanewprojectmeansthatwefirsthavetospendalotoftimereading.When
wecomeacrosswordsordomainconceptsthatwe’renotfamiliarwith,wehavetoaskques-
tionstofigureoutwhattheymeaninordertomoveon.
Ifwemastertheunderstandingofthatclass,method,orfunctiononce,thenweunderstand
itsresponsibilityandsubsequentusagethroughouttheentiretyofthecodebase.
Confusionariseswhentheexactsameconceptoroneextremelysimilarappearsandis
representedusingadifferentword.
Here’saconfusingsituation:
• Q:What’sthedifferencebetweenaJobandJobObject?
• Q:WhenshouldIuseJobandwhenshouldIuseJobObject?
• Q:Let’ssayIhavesomenewfunctionalitytoadd. ShouldIaddittoJoborJobObject?
The good thing is that we have lots of best practices against naming things to avoid these
typesofscenariosentirely.
For example, Prefer Domain-Specific Names over Tech-y Sounding Names from 3 -
Specificityintroducesonepossiblewaytomitigateintroducingconfusionlikethis.
Consistency & uniqueness are two of the most important principles because they affect
alloftheothers.
Hereareafewbestpracticestofollowtoreinforce’em.
177

Bestpractices
Avoidusingsimilarwordstoexpressthesameconcept(thesaurusnames)
|            |            |     |         |     | get, show, | display |       | present? |            |              |
| ---------- | ---------- | --- | ------- | --- | ---------- | ------- | ----- | -------- | ---------- | ------------ |
| What’s the | difference |     | between |     |            |         | , and |          | If they’re | all supposed |
torepresentthesamebehaviour,it’snoteasytodeterminethat. Ifthey’reallsupposedto
representdifferentbehaviour,it’snoteasytodeterminethateither.
| export            | class | PostAPI           |                 | {                       |       |     |     |       |     |     |
| ----------------- | ----- | ----------------- | --------------- | ----------------------- | ----- | --- | --- | ----- | --- | --- |
| getAuthor         |       | ():               | Promise<Author> |                         | {     | ... | }   |       |     |     |
| fetchPosts        |       | ():               | Promise<Posts>  |                         | {     | ... | }   |       |     |     |
| showTags          |       | (): Promise<Tags> |                 |                         | { ... | }   |     |       |     |     |
| presentCategories |       |                   |                 | (): Promise<Categories> |       |     | {   | ... } |     |     |
}
Myintentionforeachofthesewastomerelyfetchdatafromabackendserviceandreturn
ittothecaller.
Because we’re using different words, the reader will constantly need to check inside the
functionormethodblocktoseewhatitactuallydoes-that’snotveryIntentionRevealing.
Insteadofusingdifferentwords,chooseonewordtoexpresstheconceptthatwe’retrying
torepresent,andsticktothatthroughouttheentiretyoftheproject.
Followprogramminglanguageandproject(naming)codingconventions
TypeScriptandJavaScripthaveasetofcodingandnamingconventions, likeusingpascal
casetosignifythatanidentifierisaclass,type,enum,orinterface.
| // This   | is  | conventional! |     | Use | PascalCase |     | for types. |     |     |     |
| --------- | --- | ------------- | --- | --- | ---------- | --- | ---------- | --- | --- | --- |
| type User | =   | {             |     |     |            |     |            |     |     |     |
id: string;
| name: | string |     |     |     |     |     |     |     |     |     |
| ----- | ------ | --- | --- | --- | --- | --- | --- | --- | --- | --- |
}
| // Also   | conventional |     |     | usage! |     |     |     |     |     |     |
| --------- | ------------ | --- | --- | ------ | --- | --- | --- | --- | --- | --- |
| interface | Serializable |     |     | {      |     |     |     |     |     |     |
// But notice that property names/attributes are NOT PascalCase, but
| // camelCase |     | instead |     |     |     |     |     |     |     |     |
| ------------ | --- | ------- | --- | --- | --- | --- | --- | --- | --- | --- |
| toJSON       | (): | string; |     |     |     |     |     |     |     |     |
}
| // PascalCase   |     | on  | classes    | are | conventional |     |     |     |     |     |
| --------------- | --- | --- | ---------- | --- | ------------ | --- | --- | --- | --- | --- |
| class UserModel |     |     | implements |     | Serializable |     | {   |     |     |     |
...
}
But non-constants, variables, and functions are supposed to be represented using camel
caseinTypeScriptandJavaScript.
178

const shouldListenToJohnMaus = likesWeirdMusic()
&& isGenerallyAHappyPerson();
Ofcourse,thelanguageconventionsaresecondarytowhatevercodingconventionsthatyou
andyourteamdecideuponenforcing(hopefullyusingtooling).
• Example(naming)codingconventionsyoucouldenforce(viaFormik):
– UsePascalCasefortypenames
– Donotuse“I”asaprefixforinterfacenames
– Donotuse“_”asaprefixforprivateproperties
– Use isXXXing or hasXXXXed for variables representing states of things
(e.g.isLoading,hasCompletedOnboarding)
Themostimportantthingisthatyou’reconsistentwithyourchoices.
Avoid very similar variable names by mis-spelling (or using correct, alternate
spellings)
Sometimesbymisspellingavariablename,weendupwithtwoormorecopiesofthesame.
There’salsothecaseofdifferentspellingsoccurringforthesameword. Man,Englishcan
beweird.
• Exampleofdifferentspelling: color,colour
• Exampleofmisspelling: PaymentProcessor,PaymentProccessor
Don’tusethesamenametoexpressdifferentconceptsfromwithinthesamenames-
pace
It’simportanttoreally:
• Imagine importing formatEmail from shared/utils and importing formatEmail
fromusers/domain/email.
• ThisisreallyjustanotherwaytosaywriteDRYcode.
Thenagain:
• AThnginashippingsubdomain,andAThngfromabiddingsubdomainhaveentirely
differentmeaningsdependingonthecontext.
– Make sure that if you’re going to use similar names to enforce boundaries be-
tweenthosedomainstoallowthesamenamestobeusedinawaythatdoesn’t
clashsyntactically(errors)anddoesn’tclashsemantically(ourunderstanding).
• It does have the potential to introduce confusion if a new subdomain C relied on a
conceptfromAthatalsohassomethiingwiththesamenamefromB.
– Ifallweknowisthename,howdoesCknowwhichoneisthecorrectonetouse?
Don’trecyclevariablenames
You have a keyboard with all 26 numbers of the alphabet. When writing functions with
temporaryvariables,it’sadvisedtocreateasmanytemporaryvariablesasyouneedrather
thanre-initializeandoverwriteoneusedforseveraldifferentpurposes.
Ifavariableisbeingusedforseveralpurposes,it’sactuallyratherchallengingtodistinguish
the current purpose of the variable at any point in time, and it’s rather easy to forget to
179

reinitializeitwhenpurposeschange.
Here’sanexampleofreusingthetemporarysumvariablefortwodifferentpurposes.
Bad
| let sum =          | 0;                    |      |     |     |     |
| ------------------ | --------------------- | ---- | --- | --- | --- |
| categories.map((c) |                       | => { |     |     |     |
| if (c.name         | === selectedCategory) |      |     | {   |     |
| sum +=             | 1;                    |      |     |     |     |
}
})
| console.log("Category |     | match | total | score is", | sum); |
| --------------------- | --- | ----- | ----- | ---------- | ----- |
sum = 0;
| tags.map((t) | => {             |     |     |     |     |
| ------------ | ---------------- | --- | --- | --- | --- |
| if (t.name   | === selectedTag) |     | {   |     |     |
| sum +=       | 1;               |     |     |     |     |
}
})
| console.log("Tag | match | total | score | is", sum); |     |
| ---------------- | ----- | ----- | ----- | ---------- | --- |
Abetterdemonstrationwouldbetogetmorespecificwiththenamestomakesurethateach
variableservesasingleresponsibilityandisuniqueintheprocess.
Good
| let categorySum    | = 0;                  |      |     |     |     |
| ------------------ | --------------------- | ---- | --- | --- | --- |
| categories.map((c) |                       | => { |     |     |     |
| if (c.name         | === selectedCategory) |      |     | {   |     |
| categorySum        | +=                    | 1;   |     |     |     |
}
})
| console.log("Category |                  | match | total | score is", | categorySum); |
| --------------------- | ---------------- | ----- | ----- | ---------- | ------------- |
| let tagSum            | = 0;             |       |       |            |               |
| tags.map((t)          | => {             |       |       |            |               |
| if (t.name            | === selectedTag) |       | {     |            |               |
| tagSum                | += 1;            |       |       |            |               |
}
})
| console.log("Tag | match | total | score | is", tagSum); |     |
| ---------------- | ----- | ----- | ----- | ------------- | --- |
Namesshouldbeunique,regardlessofthecase
empName, EmpName, and Empname all refer to the same concept. They should not all exist in
thesameprogramasdifferentthings. That’sasure-firewaytofosterconfusion.
180

2-Understandability
Anameshoulddescribetheconceptitrepresents.-viaNamingThings.co
Whenweunderstandsomething,itmeansweunderstandwhatsomethingis,andhowwe
canuseit. Inapreviousdiscussionaboutthepsychologyofdesign,welearnedthatdiscov-
erryinvolvescombining:
• Knowledgeinthehead(logic,conceptualmodels,semantic,memory),and
• Knowledgeintheworld(culture,experiences,physicalthings)
Knowledgeintheworld
As a software designer, when naming things, lean more on naming things to rely on
knowledgeintheworld.
Knowledgeintheheadismemory—thingswehavetothinkabout. Thingsthatcouldtake
usamomenttorecallhowtheywork. It’softenlogicalorrequiressomeupfrontprocessing
tosummon.
Ie: What’s12x9?
Knowledgeintheworldiseasilyrecollected. It’ssodeeplyingrainedinusthatwebarely
havetothinkaboutit. Weknowwhatitis,andweknowwhattodorightaway.
Ie: Ifyousawasnakeoralargespideronthegroundnearyourfoot,howlong
wouldittakeyoutodiscoverthatyoushouldrun? Notlongatall,Ihope.
Knowledgeistheworldismuchdeeperandcanbeusedinmoredexterouswayswithless
effort.
Ifwecangivethingsnamesthattapintoknowledgeintheworld,theycanpotentiallybe
reallygood.
Therefore,tobesuccessfulwithaname,representtherealworldconcept.
Representingreal-worldconcepts
Beingcleverenoughtonamethingsafterreal-worldconceptsassumesthatwe’readevel-
oper dedicated to the craftsmanship of the product and empathetic enough to care about
learningthedomainifwe’renotfamiliarwithit.
Thewaythisworksis:
• Ifyou’refamiliarwiththedomain,theneverydomainconceptthatappearsincodeis
goingtohavemeaningforyou
• Ifyou’renotfamiliarwiththedomain,thenasyoulearnthebusiness,thecodestarts
tomakemoresense
Ithinkthisisthebestmethodologyfornamingthings. Thatway,whenwegetcaughtupto
speedonthewaythebusinessworks,notonlydoweknowwhat’sbeingreferredtointhe
code,butwe’vegotabetterunderstandingofwhattheseobjectscansemanticallydointhe
real-world.
Theyhelpusworktowardsaconceptualmodeloftheentirebusinessasasystem.
181

Domain-specificnamesarealongterminvestment
Usingnamesthatdescribetheconcepttheyrepresentisalongterminvestment.
It’smucheasiertojustchooseanamethatworksfornow,butiftheoriginalauthorleaves
thecompanyandisinaccessible,themeaningcouldbelostforeveranditmaybeveryhard
tointerpretinthefuture.
“Why did Khalil name this class, Dealie? I have no idea what the hell that is.
Doesanyonehavehisemail? Canyouchecktoseeifyoustillhavehisnumber?
Doyouknowwhereheendedup?”
Personally,Ihadateacherthatusedtocalljustabouteverythingadealie.Sotome,adealie
issomethingtemporaryorephemeral. Butunlessyouwerethere,youwouldn’tknowthat.
Namesthatrefertotheirrealworldcounterpartsaremorelikelytolivelongerlives
andcontinuetomaintaintheirunderstandability.
Thisistrue. Howlikelyisitthatthecoreaspectofabusinesswouldchange. JimBob’sFur-
niture Warehouse isn’t going to shift their business model to decide to do kitten grooming
instead,overnight. Ifwecancountonthebusinesstofundamentallybethesame,wecan
besurethatnameswillmaintaintheirusefulnessassoonasthebusinessisunderstoodby
newdevelopers.
Thedomainlayerdescribescorebusinessrules
Towritenamesthatdescribethebusiness,thatkindaimpliesthatthere’saplaceinourcode
wecanusetodothis.
That’scalledthedomainlayer.
There’sasoftwaredesignandarchitectureprinciplecalledSeparationofConcerns.Itsays
thatweshoulddividecodeintosectionsthateachaddressspecificconcerns.
Forexample, oneofthemostpopularwaystothinkaboutbuildingwebapplicationsisto
apply the Model-View-Controller architectural pattern. In MVC, the model, view, and
controller take on a unique set of responsibilities that, when combined, give us the basic
architectureneededtopowerawebapp.
Aswe’vediscussedin“KnowingWhenCRUD&MVCIsn’tEnough”,theMinMVCisn’tvery
specificaboutthestructureandresponsibilityitholds.
Toremedythisproblem,wecanlooktoamorerobustSeparationofConcernsthroughthe
useoftheCleanArchitecture(alsoknownasHexagonalArchitecture).
182

Thedomainlayer,whichisatthecenter,bestdescribesthewaythebusinesslooksinthereal
world.
Checkoutthedomain-specificnamesoftheclasseswithinthedomainlayerforddd-forum
hereonGitHub.
Here, you’llfindclasses, interfaces, methods, andvariablesnamedexactlyastheyappear
inconversation. Theseclassescontainmethodsandinterfaceswhichdescribethebusiness
rulesandshouldbewrittenwithasmuchclarityandexpressivenesstothedomainaspos-
sible.
| // UserName   | is         | from a separate                   | subdomain, | `Users` |
| ------------- | ---------- | --------------------------------- | ---------- | ------- |
| import        | { UserName | } from '../../users/domain/types' |            |         |
| type MemberId | =          | string;                           |            |         |
| type Member   | = {        |                                   |            |         |
id: MemberId,
| name: | UserName |     |     |     |
| ----- | -------- | --- | --- | --- |
}
| type Text      | = string; |           |     |     |
| -------------- | --------- | --------- | --- | --- |
| type Link      | = string; |           |     |     |
| type PostTitle |           | = string; |     |     |
| type PostId    | = string; |           |     |     |
| type Post      | = {       |           |     |     |
| postId:        | PostId    |           |     |     |
183

| postedBy:         | Member, |     |     |     |     |     |
| ----------------- | ------- | --- | --- | --- | --- | --- |
| title: PostTitle, |         |     |     |     |     |     |
| content: Text     | | Link, |     |     |     |     |     |
}
| type Upvote     | = {      |     |     |     |     |     |
| --------------- | -------- | --- | --- | --- | --- | --- |
| postId: PostId, |          |     |     |     |     |     |
| memberId:       | MemberId |     |     |     |     |     |
}
| type Downvote   | = {      |     |     |     |     |     |
| --------------- | -------- | --- | --- | --- | --- | --- |
| postId: PostId, |          |     |     |     |     |     |
| memberId:       | MemberId |     |     |     |     |     |
}
Youprobablyhaveagoodideaabouthowthedomainworkssimplybylookingatthenames
ofthetypesandtheirrelationships.
Theideaistomakethecodeheresoclearandunderstandablethatevennon-technicaldo-
mainexpertscouldunderstandit.
Namingthingsinmoretechnicallayers
Thedomainlayeristhemostdeclarativelayerofcodethatreferstothingsascloselyastheir
real-worldcounterparts.
Theapplicationlayercontainsusecases. It’sextremelystraightforwardtonameusecases
byname: CreatePost,UpvoteComment,DeleteComment,GetPostBySlug,etc.
| Infrastructure |                |          |             |             | controllers, | routers, |
| -------------- | -------------- | -------- | ----------- | ----------- | ------------ | -------- |
|                | layer, however | concerns | itself with | things like |              |          |
caches, repositories,
etc. These concepts are pretty much purely technical and they
can only be understood by programmers that are familiar with those types of objects in
computerscience.
Usingarchitecture&frameworkstodictatehowtonamethings
Frameworks like Angular and Nest.js introduce a convention where the name of the con-
structisincludedinthenameofthefile.
Examples:
cats.service.ts—it’saservicethatoperatesagainstcats
•
• cars.module.ts—it’samodulethataggregateseverythingtodowithcars
Thisconventioncanbehelpfultoincreasediscoverabilityofafile.
There’saconstructforeverything
Don’tknowwhattonamethatclassorfile?
There’sachancethatyoumightnotyetknowthebestconstructforit.
OneofthebestpracticesistoAvoidtech-ysoundingnames.ThatincludesthingslikeProcessor,
Manager,andHelper.
|     | They’refluffy. | Theydon’treallyaddtohelpingunderstandwhatthe |     |     |     |     |
| --- | -------------- | -------------------------------------------- | --- | --- | --- | --- |
184

class does. We’re writing code— isn’t all code processing, managing or helping in some
way,shape,orform?
IwascallingclassesthatperformvalidationlogicValidatorsforalongtime. Thatworks,but
IlaterdiscoveredthecorrectconstructwasaValueObjec.
Examplesofdifferentvalidconstructsforthesameresourcetype:
• User,UserMapper,UserRepo,Username,UserCreated.
BestPractices
Don’trandomlycapitalizesyllableswithinwords
It’s remarkable how much meaning a variable loses when we capitalize a syllable in the
middleofaword. Example: PaidJobDeTails.
Avoidingnameswithdigits
Sincethelowercasellooksalotlikethedigit1,weshouldavoidnameswithdigitsin’em.
Forexample,10lismorelikelytobemistakenfor101than10Lis.
If you have to refer to a number, prefer the English word to represent the number rather
thanthenumericalversion:
• ThirdCard>3rdCard
• ThreeSixtyNoScope>360NoScope
• OneIota>1Iiota
Usepronounceablenames
Whennamesarepronounceable,readerscancommunicatethemwithdomainexpertsand
developers in conversation. Try meeting up with your customers and asking them what
XEN_94_PHXX_issupposedtomean.
UsetheCQS(CommandQuerySeparation)principlefornamingmethods
The CQS principle helps to simplify code paths. It states that there are two types of oper-
ations: commandsandqueries. Tomitigateconfusionandunexpectedside-effects,acom-
mandresultsinside-effectsandreturnsnodata,whileaqueryreturnsdataandperforms
noside-effects.
Beveryclearwiththisinyourmethodsignatures.
export class UserRepo implements IUserRepo {
createUser (user: User): Promise<any> { ... }
getUserByUserId (userId: UserId): Promise<User> { ... }
}
Documentsideeffectsinmethodswithseveralnotablesideeffects
If methods have unexpected side-effects, be clear with the side effects in the name of the
methodsothatusersdon’tneedtoreadintotheentiremethodtofigureoutwhatitdoes.
Examples:
185

• createUservs.createUserAndSendAccountVerificationEmail
DoingthisisimplementingthePrincipleofLeastSurprise.
Usedomainconceptstorefertothingsfromthebusiness
Themainbestpractice. Namethingsastheyoccurfromthebusinesswithinthedomainand
applicationlayers.
Usetechnicalconceptstoexpresstechnicalthings
Whenyou’renaminginstancesofcaches,databases,factories,adapters,mappers,repositories,etc—
usethecorrectnameoftheconstruct. Notsomethingyoumadeup.
Usesimple(grammaticallycorrect)English(withoutspellingerrors)
ThisWikipediaarticlelistsalltheprogramminglanguagesthatdonotusekeywordstaken
fromorinspiredbyEnglishvocabulary. They’refewandfar,solet’sagreethatinorderto
writecodetobeunderstoodbyhumans,useasformalEnglishaspossible.
Usesimplenounsandadjectives. Forexample,
• bad>amateurish
• student>pupil
• fast>expeditious
Avoidspellingerrorsandusegrammaticallycorrectversionsofwords.
Don’tomitvowelsorunnecessarilyabbreviatewords
Mostprogramminglanguagesletyoucreateverylongidentifiernames. There’snoneedto
sayCntrllrinsteadof Controller.
Avoidmisleadingnames
Saywhatyoumeanandmeanwhatyousay. Iftheclassormethodsaysitdoessomething,
makeitdothat,andthatonly.
For example*,* you don’t want to see an isValid(x) that doesn’t return a boolean, but in-
steadreturnsastring. Maketheobviousthinghappen.
Avoidusingnegativesinmethodsthatreturnboolean
Considerafunctionthatvalidatesanemail. PerhapsthenameisisEmailNotValid(email:
string). Trytorefrainfromusingnegativesinthenamesofthevariables. It’slessobvious
andtakesasmallamountoflogicalprocessingtofigureoutiftheyshouldnegatetheresponse
ornot.
if (isEmailNotValid(email)) {
return;
}
Instead
if (!isEmailValid(email)) {
}
186

Avoidirrelevantnames
marypoppins = (superman + starship) / god;
Makemeaningfuldistinctions
Distinguishnamesinsuchawaythatreaderknowswhatthedifferencesoffer.
Forexample,moneyAmountisindistinguishablefrommoney,whereasmoneyInRupeesis
clearlydistinguishablefrommoneyInDollars.
3-Specificity
Anameshouldn’tbeoverlyvagueoroverlyspecific. —NamingThings.co
Ican’trememberwhereIheardthispieceofadvice,butitwasthatit’s“bettertoover-specify
thanunder-specify”. Icanseethelogicinthat. Ifwe’renotreallysurewhattonamesome-
thing yet, a longer and more specific name might prove easier to refactor than a short and
nebulousone.
Nowadays, with the find-and-replace tools we have, it can be much easier to write a very
specificnameandmoveon,leavingroomtocomebacktothatnamelater.
Let’s say we were working on writing some auth code and we ended up writing this very
specificmethodname:
class AuthenticationService {
public userHasAuthenticatedAndVerifiedEmailShouldTheyBeNonAdmins (
user: User
): boolean { ... }
}
OK,that’salot—yes. Butlet’strytofindsomegoodinit.
Goodthings
• It’swellintentioned—theauthoristryingtomakeitwellknownwhatthisisfor.
• Thereismoreinformationprovidedaboutwhatit’sfor, andhowwe’redetermining
it.
Andnow,thebadthings.
Badthings
• Fatiguing—IhaveasuspicionthatyoudislikethenameasmuchasIdo. Theremight
bemoreinformationencodedinthere,butit’shardtoread.
• Misinformation—Eachwordinhereneedstocontributetousunderstandinghowto
useit. Thereareseveralwordsinthisnamethatdonothingforus. Thistakesusback
toEnglishclassinaway. Inthisname,herearetheuselesswordsthatdon’thelpus:
and,should,they,be,non.
It’salsousefultopointoutthatsomeofthesewordssignifyconditionals—whichtellsusthat
theycouldbesplitintotheirownmethods.
187

| class  | AuthenticationService |     | {      |        |           |
| ------ | --------------------- | --- | ------ | ------ | --------- |
| public | userIsAuthenticated   |     | (user: | User): | boolean { |
...
}
| public | userVerifiedEmail |     | (user: | User): | boolean { |
| ------ | ----------------- | --- | ------ | ------ | --------- |
...
}
| public | userIsAdmin | (user: | User): | boolean | {   |
| ------ | ----------- | ------ | ------ | ------- | --- |
...
}
}
Interesting,right? Suddenly,withouttheover-specification,thiscodeappearstobemuch
less abrasive and more useful. To decide whether a user should be allowed to access a re-
sourcemightbeawholelotclearer.
| function                           | canUserViewResource |     | (): | boolean | {   |
| ---------------------------------- | ------------------- | --- | --- | ------- | --- |
| if (authService.userIsAdmin(user)) |                     |     |     | {       |     |
| return                             | true;               |     |     |         |     |
}
| if (authService.userVerifiedEmail(user) |                                        |     |     |     | &&  |
| --------------------------------------- | -------------------------------------- | --- | --- | --- | --- |
|                                         | authService.userIsAuthenticated(user)) |     |     |     | {   |
|                                         | return true;                           |     |     |     |     |
}
| return | false; |     |     |     |     |
| ------ | ------ | --- | --- | --- | --- |
}
Over-specifying
Whatleadsustoneedingtoover-specifyanyways?
Ithinkit’stwomainthings.
• Whenmorethanonevariantofaconceptisknowntoexist
• Whenwefeelwehavenotprovidedadequatecontext
Multiplevariants
Aclassicexampleofmultiplevariantsisanapplicationthatletsyoucreateabunchofdif-
ferentducks.
Bad
| class | Duck {}               |         |         |      |     |
| ----- | --------------------- | ------- | ------- | ---- | --- |
| class | DuckThatCanFly        | extends | Duck    | {}   |     |
| class | DuckThatCanFlyAndCook |         | extends | Duck | {}  |
188

Namingvariablesthiswayistotallycool. However,namingconcreteconstructslikemethods,
functions, and classes is not only over-specification, but **it’s a really stinky code smell as
well. Wecanfixthisbyrefactoringtodesignpatterns,namelytheFactoryPatternhere.
Good
const duckThatCanCook = DuckFactory.create({ capabilities: ['cook'] });
const duckThatCanFlyAndCook = DuckFactory.create({ capabilities: ['cook', 'fly'] })
Lackingnecessarycontext
Inthefollowingexample
Bad—Exampleofavariableprovidingcontextastohowitisabouttobeused
| async function | exists (userId:   | userId): | Promise<boolean> |            | {   |
| -------------- | ----------------- | -------- | ---------------- | ---------- | --- |
| // This        | variable explains | how it's | going            | to be used |     |
const tempUserToDetermineIfExistsOrNot = await this.userRepo.getUser()
| return !!tempUserToDetermineIfExistsOrNot; |     |     |     |     |     |
| ------------------------------------------ | --- | --- | --- | --- | --- |
}
It’simprovedbyasenseofname-spacing. ThisiswhyIpreferclassesoverstrayfunctions,
because the methods within class exist to perform something that semantically makes
senseforittodowithintheclass.
Thisremovestheneedforustodescribewhythevariablesexistandallowsustoshiftto
namingthembasedonwhatisstoredwithinthem.
Good—thevariablesimplydescribeswhatisstoredinit,notanyadditionalcontextastowhyitwas
created.
| export class | User implements                      | IUserRepo | {                |     |     |
| ------------ | ------------------------------------ | --------- | ---------------- | --- | --- |
| public async | exists (userId:                      | userId):  | Promise<boolean> |     | {   |
| const        | user = await this.userRepo.getUser() |           |                  |     |     |
| const        | userExists = !!user;                 |           |                  |     |     |
| return       | userExists;                          |           |                  |     |     |
}
...
}
Here’sarule:
Inasminimalwordsnecessary,
Anameshoulddescribewhatisinsidethevariable—nothingmore,noth-
ingless.
Under-specifying
Let’s say we’ve got this Option class, but we want it to be used in a very specific way by a
specificclass. Actually, let’salsosaythatallusageofthisclassoutsideofthatveryspecific
189

wayiscompletelywrong.
Checkthefollowingcodesnippet,what’swrongwithit?
| class Option | {               |          |            |     |
| ------------ | --------------- | -------- | ---------- | --- |
| private      | food: Food;     |          |            |     |
| private      | selected:       | boolean; |            |     |
| public get   | displayValue    |          | (): string | {   |
| return       | this.food.text; |          |            |     |
}
| constructor   | (food: | Food)    | {   |     |
| ------------- | ------ | -------- | --- | --- |
| this.food     |        | = food;  |     |     |
| this.selected |        | = false; |     |     |
}
| public isSelected |                | (): | boolean { |     |
| ----------------- | -------------- | --- | --------- | --- |
| return            | this.selected; |     |           |     |
}
}
There’salotofreferencetothingsaboutfood,itappearsthisOptionclassisn’tasgenericas
wethoughtitmightinitiallybe. Imean,thenameOptiondoesn’treallysignifythatit’sonly
goingtobeusedasawaytodisplayFoodoptions.
Optionaltypeannotations
InTypeScript,wehavetheoptionofannotatingavariablewiththetypelikeso:
// The optional type explicitly tells us that this is a `User` type.
| let u: User | = this.getUser(); |     |     |     |
| ----------- | ----------------- | --- | --- | --- |
Since this is an optional feature, it makes it that much more important to choose variable
namesthatdescribewhatisinsidethevariable.
ReacthooksAPI
IwanttotalkabouttheReactHooksAPIforasecond. IfounditkindoffunnywhenIfirst
encounteredit.
ReactHooksisawayforyoutoenableview-layerreactivityusingtheReactlibrary. Itacts
WhenyoucalluseState,
asaplaceforyoutolocatecomponentstateandinteractionlogic.
youcanpassinaninitialvalue,andinreturn,getanarraythatcanbedecomposedintotwo
variables.
Thefirstvariablecontainsthecurrentvalueofstate.
Thesecondvariablecontainsafunctionthat—wheninvoked,setsthecurrentvalueofthe
state.
Itlookslikethis:
190

| const [replyText, |     | setReplyText] |     | = useState(''); |     |     |
| ----------------- | --- | ------------- | --- | --------------- | --- | --- |
TakeamomenttofirstofallunderstandhowunintuitivethisAPIis.
Now,takeamomenttoappreciatehowgoodthenamesare.
replyTextisastringvalue—thenamesaysnomorethanwhatisstoredwithinit.
setReplyTextisafunctioncapableofchangingthereplyText. It’savariable, butbecause
it’snamedlikeamethodorafunction,appreciateourabilitytodiscoverhowtouseit.
| setReplyText('This      |     | is  | interesting, | indeed!')       |     |         |
| ----------------------- | --- | --- | ------------ | --------------- | --- | ------- |
| console.log(replyText); |     |     | // "This     | is interesting, |     | indeed" |
Considerifthenamesweredifferent.Here’sareseveralexamplesofunder-specifyingwhat
isstoredineachvariableandeveninsomecases,incorrectly.
| const [text,   | text2] | =             | useState(''); |     |     |     |
| -------------- | ------ | ------------- | ------------- | --- | --- | --- |
| const [t,      | t1] =  | useState(''); |               |     |     |     |
| const [a,      | b] =   | useState(''); |               |     |     |     |
| const [_,      | __] =  | useState(''); |               |     |     |     |
| const [setVal, |        | val] =        | useState(''); |     |     |     |
Hopefullywe’reseeingjusthowmuchthisAPIreliesonus:
1. Rememberingtheorderofthevariablestobedecomposed
2. Rememberingthatthefirstvariableisthetypeofthevaluethatwaspassedininitially
3. Rememberingthatthesecondisafunctiontochangestate
4. Choosing names that adequately specify both variables’ capabilities and how
theymaybeused.
Bestpractices
Singular/pluralnaming
Maintakeaways
(optional)Useatosignalthatasingularitemisstoredwithinthevariable
1.
| // Instead  | of this |        |     |     |     |     |
| ----------- | ------- | ------ | --- | --- | --- | --- |
| const car   | = new   | Car(); |     |     |     |     |
| // Try this |         |        |     |     |     |     |
| const aCar  | = new   | Car(); |     |     |     |     |
2. Use collection, list, array or plural names to signal that a group/array of items is
storedwithinthevariable
// Good
| const todos: | Todo[]   | =       | [      |            |       |     |
| ------------ | -------- | ------- | ------ | ---------- | ----- | --- |
| { id:        | 0, text: | "First  | todo", | completed: | false | },  |
| { id:        | 1, text: | "Second | todo", | completed: | false | },  |
]
191

// Good
| const todosList: |          | Todo[]  | = [    |            |          |
| ---------------- | -------- | ------- | ------ | ---------- | -------- |
| { id:            | 0, text: | "First  | todo", | completed: | false }, |
| { id:            | 1, text: | "Second | todo", | completed: | false }, |
]
// Bad
| const todo: | Todo[]   | =       | [      |            |          |
| ----------- | -------- | ------- | ------ | ---------- | -------- |
| { id:       | 0, text: | "First  | todo", | completed: | false }, |
| { id:       | 1, text: | "Second | todo", | completed: | false }, |
]
With abstracted collections, the fact that a variable is actually a list can be sometimes be
hidden.
Abstractedcollections
| type Todos            | = Todo[] |          |          |     |     |
| --------------------- | -------- | -------- | -------- | --- | --- |
| type TodosList        |          | = Todos; |          |     |     |
| class TodosCollection |          |          | {        |     |     |
| private               | todo:    | Todo[];  |          |     |     |
| constructor           |          | (todos:  | Todos[]) | {   |     |
| this.todos            |          | = todos; |          |     |     |
}
}
| class Collection<T> |        | {       |        |     |     |
| ------------------- | ------ | ------- | ------ | --- | --- |
| private             | items: | T[];    |        |     |     |
| constructor         |        | (items: | T[]) { |     |     |
| this.items          |        | = T;    |        |     |     |
}
}
Abstractedcollectionscanbeuseful. It’simportanttoapplythisruleespeciallywhenwork-
ingwiththem.
Avoidreferringtothingsasvariables,classesormethodsinthename
Unlessyou’rebuildingthenextCodeAcademy,writingyourowncompiler,ordoingsome
verymeta-levelprogramming,it’snotnecessarytoincludethenameoftheidentifiertype
inthenameofavariable.
Insteadofsomethinglikethis:
| const userVariable |     | =   | new User(); |     |     |
| ------------------ | --- | --- | ----------- | --- | --- |
Preferomittingtheidentifiertypename.
192

| const user | = new | User(); |     |     |
| ---------- | ----- | ------- | --- | --- |
Namepeoplebytheirroles
User.
It’s unlikely that the best thing to call a user in every single application is actually a
Thisisverymuchadomain-drivenconcern,butunderstandingtheroleofaparticulartype
ofuserinyourdomainleads
Forexample,aUsercouldactuallybeanAdmin,Editor,Employee,Cashier,Visitor,etc.
Directlyrelatedconcepts: SingleResponsibilityPrinciple&Conway’sLaw.
Specificityagainstunions
Ifit’spossibleforavariabletoholdoneormoretypes, it’sagoodpracticetoaddressthat
possibilityinthename.
| export | class UserRepo | {   |     |     |
| ------ | -------------- | --- | --- | --- |
// The name is inclusive to describe what may be stored in the variable
| // using | a union | type. |     |     |
| -------- | ------- | ----- | --- | --- |
public getUser (userOrUserId: User | UserId): Promise<User> {
...
}
}
Avoidblobparameters
Blobparametersareparametersthatsaynothingaboutwhatneedstobepassedin.
Here’ssomeJavaScriptcodeofaUserfactorymethod.
| export | class User    | {      |     |     |
| ------ | ------------- | ------ | --- | --- |
| public | static create | (args) | {   |     |
...
}
}
IwanttosaythatthisismostlyaJavaScriptissue,andnotaTypeScriptone,butusingthe
anykeywordwithTypeScriptcancreatethesameconundrum.
Alwaysstrictlytypeobjectparameters.
| interface  | UserProps  | {   |     |     |
| ---------- | ---------- | --- | --- | --- |
| email:     | Email;     |     |     |     |
| password:  | Password;  |     |     |     |
| firstName: | FirstName; |     |     |     |
| lastName:  | LastName;  |     |     |     |
}
| export | class User    | {       |            |     |
| ------ | ------------- | ------- | ---------- | --- |
| public | static create | (props: | UserProps) | {   |
...
193

}
}
Avoidnumberseriesparameters
Thenumberofcaseswhereit’sactuallypreferabletonameparametersbasedontheirorder,
ratherthananintentionrevealingname,israre. Takethefollowingexample.
| function            | cloneArray | (arr1, | arr2) { |     |
| ------------------- | ---------- | ------ | ------- | --- |
| arr1.forEach((item, |            | key)   | => {    |     |
| arr2[key]           | = item;    |        |         |     |
});
| return | arr2; |     |     |     |
| ------ | ----- | --- | --- | --- |
}
Thiscouldbemademuchclearerusingthewordssourceanddestination.
| function              | cloneArray | (source, | destination) | {   |
| --------------------- | ---------- | -------- | ------------ | --- |
| source.forEach((item, |            | key)     | => {         |     |
| destination[key]      |            | = item;  |              |     |
});
| return | destination; |     |     |     |
| ------ | ------------ | --- | --- | --- |
}
Utilizenamespaces
It’spossibleforanametoexistwithinseveraldomainsofyourapplication. Theconceptofa
JobintheMediaProccessingdomainofyourappmightbealotdifferentfromthemeaning
ofaJobintheHRportion.
Waystoenforcethis:
Thenamespacelanguageconstruct
•
• Name-spacingbypackagingthingsintomodules
• Andinworsecasescenarios,name-spacingusingdomain-specificprefixes.
MediaProcessingJobvs.Job.
– Ie:
– Weshouldmakeeveryefforttoavoidthissituation. Imaginesomeonefroman
entirelydifferentcitytoldyouthatyouweren’tallowedtohavethesamename
asthembecausetheyalreadyhavethatname.
Useabbreviationssparingly
AFI,whatisthat? TheAmericanFilmAssociation? Thepunkband,AFireInside? AnAb-
soluteFileInstance? Contextcanscopethingsthingsdown,butunderstandthatabbrevia-
tionsareaformofunder-specification. Commonabbreviationsarecultural,andcantaketime
| tocatchon. | **  |     |     |     |
| ---------- | --- | --- | --- | --- |
Example:
• Doyouknowwhatbtoaandatobareinprogramming?
References
194

Forfurtherreading,checkoutthisthisconversationfromWardCunningham’swikiironi-
callytitledVeryLongDescriptiveNamesThatProgrammingPairsThinkProvideGoodDe-
scriptions.
4-Brevity
Anameshouldbeneitheroverlyshortnoroverlylong.
Codethat’stooverbose(long)ishardtoread. Weirdly,codethat’stoosuccinct(short)isalso
hardtoread.
Thisphenomenonisthefight—thepushandpullof compressionvs.context.
Compressionvs.Context
Compression
Tocommunicateanything,wehavetocompressit. Examplesofthisexisteverywhere.
Booksummaries,movietrailers,blogpostsubtitles,etc. Theyconveythemainideabutsac-
rificethedetailsintheprocess.
Bycompressingideas,wesayalotbysayinglittle. Thisiswhypeoplelikelifequotes.
“Lifeiswhathappenswhenyou’rebusymakingotherplans.”—JohnLennon
Compressedtoomuch,ideasloseallcontextandmeaning.
Context
To explain something, we provide context. Context is provided by reading the book,
watchingthemovie,readingthearticle,anddigestingthematerial.
Forexample,theNamingThingschapterstatedthatnamingcomesdowntosevenprinciples.
To provide context to that statement, we’re spending some time making an argument for
eachone.
Thistakestimetodo.
Additionally, going too far contextually by providing too much information, we can
losethemaintakeaway.
Withmorecompressioncomesmore*discoverability*****butlesscontext.
Withmorecontextcomesmoreunderstandingbutlesscompression.
It’slikebalancingascale.
195

Thelaw,reiterated
Communicatewhatwithnames,explainwhywithcontext.
Ibelievethisistheanswer. It’sthesamestatementfromthelastsection,butIbelieveinit.
Askyourselfthequestions:
• Areweadequatelydescribingwhatisinthisname?
• Andthen,doesthecontexthelptoreinforcewhyitmakessenseforthisnametoexist?
Doyouseethepatternherewithrespecttocomments? Commentscanbeusedtohelppro-
videadditionalcontextbyexplainingwhy: thingsnotcommunicatedwithcode.
Thisprinciplesharesalotincommonwiththespecificitybecausetheimportantattributes,
contextandcompressionareattheforefrontofwhatmakesthemsuccessful.
Bestpractices
UseconciseEnglish
Somenamescontainwordsthat,whenremoved,donothingtochangethemeaningofthe
name. There’s a book called The Elements of Style. It lists several rules for good writing.
Oneofthenamesisto“omitneedlesswords”.
Herearepracticalexamples:
• inorderto=to
• beableto=to
• inspiteof⇒despite
• anumberof⇒some
• intheeventthat→if
196

• hastheopportunityto→can
• despitethefactthat→Although
Considerthatthiscanbeappliedtocreatingnamesaswell. Askyourselfifremovingaword
fromthenamewouldchangeitsmeaningornot.
Refrainfromnon-conventionalsingle-lettervariables
Whenwefirststartprogramming,weencounterconventionsthateventuallyfeelasnatural
asbreathing.
Oneofthoseconventionsisusingshortlocalvariablesinsideofshortblocks.
Conventional(localvariablesinsideofshortmethods):
• sum(a, b)—commonmathutilitieslikethisareculturallyknownbyprogrammers.
• for (let i; i = 0; i++) — short variables in for-loops are also conventional.
Thoughthey’reconvention,I’dalmostalwaysrecommendrefactoringthemtoarray
methodslikemap,filter,forEach,etc.
Thesefewconventions,widelyacceptedinprogrammerculture,areOK.
Outsideofconvention,shortvariablesusedinunconventionalfunctionsandmethodsare
harmful.
Sincethere’snoconventiontogobasedon,wecouldimprovethedeveloperexperienceby
choosingnamesthathelpusersdiscoverhowtheyaretobeused.
• createUser(a, b)—thissaysnothingtous
• createUser(arg1, arg2)—thisstillsaysnothing
• createUser(email, password)—therewego,that’smuchbetter.
Groupingindicatescontext
Howcanweprovidecontextwithoutencodingitintoaname?
Classes, namespaces, folder names, and proximity to other similar functions or
constructs—theseallcommunicatecontext.
Takethefollowingcodeexample. Itprovidesabsolutelynocontextastowhatwe’reoperat-
ingagainst.
function create(props) {}
function edit(id, props) {}
function del (id) {}
Howdoweimprovetheunderstandabilityofthis?Wecandoanyofthethingswedescribed
above. Introduce context. One way is to encapsulate the operations within a class or an
object.
Here’stheclassversion.
export class StudentRepo {
197

| public | create(props) |      | {}        |
| ------ | ------------- | ---- | --------- |
| public | edit(id,      |      | props) {} |
| public | del           | (id) | {}        |
}
Andhere’stheobjectversion.
| export  | const StudentRepo |        | = { |
| ------- | ----------------- | ------ | --- |
| create: | (props)           |        | {}  |
| edit:   | (id,              | props) | {}  |
| del:    | (id) {}           |        |     |
}
Much better, right? Before the refactor, it was impossible to have known that these were
operationsagainststudents.
Thisbestpracticeleadsintothenextonenicely.
Theoperationandresourcenamemustbeincontext
Inthepreviousexample,weprovidedabunchoffunctionsthatindicatedtheywerecapable
create,edit,anddelete.
ofperformingtheactions: Theoperationswereincontext.
| function | create(props) |        | {}  |
| -------- | ------------- | ------ | --- |
| function | edit(id,      | props) | {}  |
| function | del (id)      | {}     |     |
Buttheresourcethattheyoperatedagainst—thesewerenotincontext.
Ifwedon’thaveboththeoperationandtheresourcethattheoperationisexecutedagainst
incontext,thenit’sgoingtobehardforustounderstandthename.
// Poor
| function | create | (props) | {}  |
| -------- | ------ | ------- | --- |
// Good
| function | createUser |     | (props) {} |
| -------- | ---------- | --- | ---------- |
// Good
| class    | User {     |     |            |
| -------- | ---------- | --- | ---------- |
| function | createUser |     | (props) {} |
}
// Good
198

const user = {
create: (props) {}
}
Don’tuseunnecessarymemberprefixes
Sometimes,developersprependtheirprivatemembervariableswithanunderscoretosig-
nifythatit’sprivate.
Example: _firstName.
InJavaScript,there’snoconceptof privateaccessmodifiers,sotheconventionistosignal
thatit’sprivatethisway.
InTypeScript,wedohaveprivateaccessmodifiers.
Refactormemberprefixesoutofobjects
Anotherthingsometimespromotedistoprefixsomememberswithaspecificsetofchar-
acterstodenotethatthey’respecial.
InAngularJS,itwasrecommendedtoputavminfrontofyourmemberstosignalthatthey’re
viewmodelvariables.
Example: vmFirstName
Inmyopinion,weshouldgroupthesevariablesintoanobject,maybecreatedfromaclass
or object called ViewModel, to remove the need for us to include signaling in the member
names.
Insteadofthis
// Instead of this
class UserController {
get firstName () {
return this.vmFirstName;
}
get lastName () {
return this.vmLastName;
}
get avatar () {
return this.vmAvatar;
}
constructor () {
this.vmFirstName = "";
this.vmLastName = "";
this.vmAvatar = null;
}
}
199

Trythis
| // Try | this                  |     |     |
| ------ | --------------------- | --- | --- |
| class  | UserController        | {   |     |
| get    | firstName ()          | {   |     |
| return | this.model.firstName; |     |     |
}
| get    | lastName ()          | {   |     |
| ------ | -------------------- | --- | --- |
| return | this.model.lastName; |     |     |
}
| get    | avatar () {        |     |     |
| ------ | ------------------ | --- | --- |
| return | this.model.avatar; |     |     |
}
| constructor | () {  |                  |     |
| ----------- | ----- | ---------------- | --- |
| this.model  | = new | UserViewModel(); |     |
}
}
5-Searchability
Anameshouldbeeasilyfoundacrosscode,documentation,andotherresources.
—NamingThings.co
Searchablenamesisgoodbecauseit:
• Makesrefactoringeasier—wecanchangealloccurrencesofsomethingthatcanbe
foundusingatexteditor
• Canbelocatedindocumentation
• Canbeunderstoodwhereandhowitworksthroughouttheentiretyofacodebase.
Therearecertainthingscanmakesnamesunsearchable,however.
| • Namesbeingtooshort—ex:   |     | a   |      |
| -------------------------- | --- | --- | ---- |
| • Namesbeingtoogeneric—ex: |     |     | user |
• Differentnamesreferringtothesameconcept(outdateddocumentation).
Search-abilityismostlyinfluencedbythepreviousprinciples:consistencyanduniqueness,
specificity,andbrevity.
Todothiswell,followthosepracticesandconsiderthefollowingoneshereaswell.
Bestpractices
Avoidusingnumericconstants
Considerwewantedtofigureoutwherethedefaultmovieratingsettingwas.
| // Default | rating         | of 8    |         |
| ---------- | -------------- | ------- | ------- |
| function   | addMovieRating | (rating | = 8) {} |
200

Thatwouldbehardusinganumericconstant.
Numericconstantscanbereplacedwithaconstantvariable.
const DEFAULT_MOVIE_RATING = 8;
function addMovieRating (rating = DEFAULT_MOVIE_RATING) {}
Keepdocumentationuptodate
Ideally, make updating documentation a part of the release process for new code and fea-
tures.
6-Pronounceability
Anameshouldbeeasytouseincommonspeech. —NamingThings.co
Names that aren’t pronounceable aren’t easily communicated. And being able to discuss
codewithourpeersisagreatwaytoimprovethequalityofourdesigns.
Not only that, but it’s a lot harder to remember names that we have trouble pronouncing
(thisisjustastrueatsocialgatheringswithnewfriends).
Bestpractices
Verystandardabbreviationsdon’thavetobepronounceable
MostpeopleunderstandthatssnstandsforSocialSecurityNumber.
Usecamelcasetosignalwordbreaksinvariables
Imagineyouhavethenamepreparearead.
It’s saying “prepare a read”, but it takes an unnecessary amount of mental processing to
discoverthat.
Wecanfixthiswithcamelcasing: prepareARead.
Booleansshouldaskaquestionormakeanassertion
Booleansandmethodsthatreturnbooleansshouldeitheraskaquestionormakeanasser-
tionthatiseithertruthyorfalsy.
Good
• Assertion—potIsEmpty()
• Question—isPostEmpty()
Bad
• makeVar—thisactuallysignalsthatthevariableandperformanoperation
• hello—doesnotsignalthatabooleanwillbestoredhere
Don’tomitvowels
Forexample. This…
201

type timeStamp = Date;
isbetterthan…
type tmStmp = Date;
7-Austerity
A name should not be clever or rely on temporary concepts. — NamingTh-
ings.co
Iloveagoodlaughasmuchasanyoneelse, butweshouldrememberwhatwe’reworking
towards. Encoding cute and funny things in our code is hilarious in the short-term, but
dreadfultoworkwithinthelong-term.
And90%ofwhatwedoisinthelong-term(Imadethatnumberup).
Noteveryonehasthesamesenseofhumorasyou
There’salreadyenoughchallengeinwritinganametobeunderstood. Sowhywriteaname
withsecondarymeanings?
Don’tusetemporarilyrelevantconcepts
Historicalevents,popularculture,memes,jokes,andanythingthatisgenerallyunrelated
tothebusinessisallstuffthatwilleventuallyneedcleaningup.
Someprojectssurvivefor5years. Somesurvivefor10,15,and20years.
Imagine having to explain a joke over and over for a decade. I don’t think the maintainer
workingonthosecoremoduleswouldfinditveryfunny.
Bestpractices
Avoidbeingcute,funny,clever
Iknow,I’mastickler—butreally,youwantyourpeerstobesuccessfulworkingwithyour
code. Savethequipsandthehumorforhappyhourafterwesuccessfullywrapupthesprint.
Don’tincludeincludepopularculturereferences
NoteveryonehasseenStarWars. Andwhilethatmaybeashame,someoneshouldn’tneed
toknowHanSolo’sbestmatetounderstandwhataparticularblockofcodeisresponsible
for.
Errorsandexceptionhandling
Comingsoon!
Makesmethinkweshouldaddressnullabilityanderrors.InDDDForum,wethrowanerror.
202

Relevantlinks
• 200OK!ErrorHandlinginGraphQL
Testing
Comingsoon!
Thisisalsoveryimportanttocleancode
ThingsIshouldtalkaboutinthetestingchapter
• BDD
• TDD
• Unittesting
– Whatmakesaunittestaunittest?
– Isitworthit?
• IntegrationTesting
• End-to-endTesting
• KentC.Dodds’testingpyramid
• Nuancesoftestingonthebackend
• Nuancesoftestingonthefrontend
BDD&TDD
• BDDtendstolenditselfmoretowardsintegrationtesting
• TDDtendstolenditselfmoretowardsunittesting
UnitTests
Resources
https://kentcdodds.com/blog/how-to-know-what-to-test
https://kentcdodds.com/blog/write-tests
Honestly, with the amount that there is to know about testing and testing in React
apps,wemightjustbebetteroffgettingKent’scourseandgettingworktopayforit
—https://testingjavascript.com/
UnitTests
WhatisaUnitTest?
Also,MichaelFeather’sdefinitionofaUnitTest
Atestisnotaunittestif:
• Ittalkstothedatabase
• Itcommunicatesacrossthenetwork
• Ittouchesthefilesystem
• Itcan’trunatthesametimeasanyofyourotherunittests
203

• Youhavetodospecialthingstoyourenvironment(suchaseditingconfigfiles)torun
it.
Related
Refactoring
Comingsoon!
Architecturebasics
SectionThree-Skills&knowledge
Summary
Become wise. Refine your preferred coding conventions from your skills (new
languages, tooling, frameworks) and knowledge (design patterns, principles,
approaches,experience,andjudgement).
Skills&knowledge
• Coding conventions can be implemented blindly. But when we hold a deep under-
standing of the implications of what we’re doing positively impacts the structural
quality of the code, we can adapt to new situations, scenarios, code in new pro-
gramminglanguages,understandtheimplicationsofusingnewtechnologies,detect
anti-patterns&code-smells,etc.
• Thismeanslearningthepatterns,principles,
WriteasummarysothatIcanputitatthestartofthechapter
• Atthispoint,it’sabouttheskillsandknowledge.
– Understandability,flexibility,maintainability.
• Whencodeisunderstandable,flexible,maintainable,projectsgetcompletedandcode
canlivealongandhealthylife.
– Craftspeople are interested in arming themselves with knowing how to keep
eachofthesepillarsstrong.
– Theywanttoknowbestpractices,buttheyalsoknowwhentobreak.
* Youhavetoknowtherulesfirsttoknowwhentobreak’em.
* Theyknowtheimpactit’sgoingtohavewhentheybreak’em.
• Wheretogofromhere?
– Thesoftwaredesignandarchitectureroadmap. Cleancodeisthestart. Youwant
to invest in the right mindset (empathy, growth mindset), invest in setting up
yourcodebaseconventions(rules,createasenseofconsistencywithyourteam),
andthencontinuetoacquireasmuchknowledgeaboutsoftwaredesignaspos-
sible.
– There is a pattern, architectural style, tool, approach or methodology for most
problemsrightnow.
– It’sremarkablehowmuchhasstayedthesameoverthepast40yearsofsoftware
design.
– Andthosewhoknownotofhistoryarealsodoomedtorepeattheirmistakes.
204

Details
ExtremeProgramming
Toolsinyourtoolbox
SoftwareQuality
CodeSmells
AntiPatterns
Optimizingcode
Absoluteandrelativecomplexity
Bestpractices,principles,andpatterns
ExtremeProgramming
Toolsinyourtoolbox
Infra
Knowtoolstodeploywebsites
Knowascriptinglanguage
Backenddevelopment
Knowageneralpurposelanguage
KnowaSQLdatabase
KnowanORM
KnowanoSQLdatabase
Frontenddevelopment
Knowastatemanagementlibrary
Knowaview-layerlibraryorframework
KnowaCSSpre-processor
Softwaredevelopmentapproaches
Knowhowtogatherrequirementsandplanaproject
Knowhowtowritetests
205

Knowhowtoconsistentlyprovidevalue
SoftwareQuality
Todo: Explainthatsoftwarequalitycanbeunderstoodintwostrategicways.
Whenwetalkaboutthequalityofsoftware,we’retalkingabouteitherstructuralquality
orfunctionalquality.
• Structuralquality-canbeformallymeasured
• Functionalquality-canonlybemeasuredthroughtesting
Structuralquality
Structuralqualitydescribestheattributesaboutsoftwarethatcanbeformallymeasured.
206

Relationshipbetweensoftwaredesirablecharacteristics(right)andmeasurableattributes
(left).
CCBY-SA3.0,https://en.wikipedia.org/w/index.php?curid=32370588
Softwarequality
Reportonprogrammingerrors
“Correlations between programming errors and production defects unveil that basic code
errors account for 92% of the total errors in the source code. These numerous code-level
issueseventuallycountforonly10%ofthedefectsinproduction. Badsoftwareengineering
practices at the architecture levels account for only 8% of total defects, but consume over
halftheeffortspentonfixingproblems,andleadto90%oftheseriousreliability,security,
andefficiencyissuesinproduction.”
207

Softwarequalitythread
https://twitter.com/stemmlerjs/status/1266380004293783553
Points:
• Mostofthetime,thewaythatjuniordevelopersinterpretthequalityofsoftwarehas
todowithcodingconventions.
// An example of the discussion that junior developers often have.
208

| // This  | is weird     | - don't | do it |
| -------- | ------------ | ------- | ----- |
| if (!!id | === false)   | { }     |       |
| // Do    | this instead |         |       |
| if (!id) | { }          |         |       |
– That’sfine,wecanhavethesediscussionstoo-buttherearemuchmoremean-
ingfulthingstodiscussaswell,suchasArchitecture-whichisveryoftenmissing
almostentirely.
CodeSmells
No,acodesmellisnotthesmellofyourlaptopoverheatingwhilewaitingforyourJavacode
tocompile.
Ourindustrydescribesacodesmellassomecharacteristicofourcodethatisnotidealand
typicallysignifiesthattheremaybealargerproblemathand.
Forexample,considerthismethodwiththeToomanyparameterscodesmell.
| export class            | PaymentCalculator |     | {   |
| ----------------------- | ----------------- | --- | --- |
| public calculatePayment |                   | (   |     |
| userType:               | UserType,         |     |     |
| user:                   | User,             |     |     |
| currency:               | Currency,         |     |     |
| hours:                  | Hours,            |     |     |
| salary:                 | Salary,           |     |     |
| isContractor:           | boolean,          |     |     |
| isEmployee:             | boolean,          |     |     |
| isAdmin:                | boolean           |     |     |
| ): PaymentDetails       |                   | {   |     |
...
}
}
Not only is this method awkward to read, and testing is probably a nightmare, but the
amount of parameters it has provides a really good signal that there’s probably an issue
withourdesign.
A common refactoring for something like this is to refactor the list of parameters into an
objectlikethis:
| type CalculatePaymentConfig |           |     | = { |
| --------------------------- | --------- | --- | --- |
| userType:                   | UserType, |     |     |
| user: User,                 |           |     |     |
| currency:                   | Currency, |     |     |
| hours: Hours,               |           |     |     |
| salary:                     | Salary,   |     |     |
| isContractor:               | boolean,  |     |     |
209

isEmployee: boolean,
isAdmin: boolean
}
export class PaymentCalculator {
public calculatePayment (
config: CalculatePaymentConfig
): PaymentDetails {
...
}
}
Thisworks,thoughsomeonelookingatthismightalsosniffouttheDataclumpcodesmell.
Abetterrefactoringwouldbetorefactorthemethodintosmalleronessothateachmethod
isresponsibleforaspecificpartoftheentirealgorithm.Bytheend,wemayenduprealizing
thattheparametersofthemethod(likeUserType,User,andCurrency)needtoberemodeled
inawaythatmakesmoresensetothedomain.
The funny thing about codesmells is that they aren’t bugs. It’s not blaringly obvious that
they’re hurting the design, and they don’t stop the code from compiling either. It’s not
likethehouseisburningdown(yet)-youcanjustsmellsomethingfunnycomingfromthe
kitchen.
Ifnotaddressed,codesmellscantallyup,andturnanentirecodebaseintoanunmaintainable
pileofstinkylaundry.It’satthispointthatdevelopersarestoppedfrombeingabletomake
futureprogressonaproject.
Catalogofcodesmells: Therearetonsofcodesmells. Familiarizeyourselfwithsomeof
these here. Code smells are organized into three groups: Application-level smells, Class-
levelsmells,andMethod-levelsmells.
Acodesmellisanunideal(thoughnotterminal)characteristicofourcodethat
signifiestheremaybealargerdesignproblemathand
Codesmellsdependonthelanguage,context,anddeveloper
Eachlanguagecomeswithitsownsetofconventionstofollow. Forexample,theJavapro-
gramminglanguagehasit’sowndocumentedsetofconventions.
CodingConventions: Conventionsareguidelinesforalanguagewhichmakesrecommen-
dationsagainstthingslikestyle,fileorganization,namingconventions,bestpractices,and
handlingwhitespace. Thesecondpartofthischapterpresentsthemostcommon,language-
agnosticcodingconventions.
In untyped languages like JavaScriptand Python, underscores are conventionally used to
signaltootherdevelopersthatavariableshouldbeusedinternallyonly.
user.js
210

| function       | User (username, | firstname,   | lastname) | {   |
| -------------- | --------------- | ------------ | --------- | --- |
| this.userId    | =               | uuid();      |           |     |
| this.firstname |                 | = firstname; |           |     |
| this.lastname  |                 | = lastname;  |           |     |
this._hash = createUniqueObjectHash(); // internal use only, please
}
IntypedlanguageslikeJavaandTypeScript, usageoftheunderscoreconventionisdiscour-
agedbecausewehaveaccesstoscopemodifierslikeprivateandprotected. Weshoulduse
thelanguageconstructs,giventheiravailability,andlooktoconventionswhenacommon
taskcannotbedonewiththelanguage.
In some languages and frameworks, the convention may actually be a requirement.
For example, some React/JavaScript developers follow the convention that you should
haveonecomponentperfile. Thisisn’tnecessaryatall,andaggressiveusagemayactuallylead
to the LazyClass code smell (a class that does too little). Meanwhile, in Java, the compiler
actuallythrowsanerrorifitfindsmorethanonepublicclassperfile.
Some languages also have language constructs that while we can make use of them, it is
recommended to limit (or abstain from) using. In JavaScript, the eval() function en-
ablesyoutoevaluateJavaScriptcodeasastring. Mostdeveloperswilladvocateagainstus-
ingeval()justbecauseofthehugesecurityriskitposes(it’squiteeasyforamaliciousactor
togetbadJScodetorun).
Similarly,JavaScripthastheabilitytoperformLabeledBreakswithincontrolblocks. Thisis
essentiallythesamethingasthegotostatement,shunnedformakingittoohardtodebug
andtrace.
| loop:  | for (let y | = 0; y < | 3; y++) { |     |
| ------ | ---------- | -------- | --------- | --- |
| switch | (y) {      |          |           |     |
| case   | 0:         |          |           |     |
console.log(0)
break
| case | 1:  |     |     |     |
| ---- | --- | --- | --- | --- |
console.log(1)
|      | break loop |     |     |     |
| ---- | ---------- | --- | --- | --- |
| case | 2:         |     |     |     |
console.log(2)
break
}
}
LabeledbreakswithJavaScript. Thisprintsout“01”. Notrecommendedbecauseofitsreminiscenceto
GOTOstatements,abolishedfrommostprogramminglanguages.
Dependingonthecontextordomain,somecodesmellsmayactuallybeideal. Forexample,
inmicrocontrollerdevelopment,it’softenpreferredtohaveallofthecodeinasinglefileor
amaintainafewlargefiles. ThisistheGodobjectcodesmell(readaboutithere),andnor-
mallyconsideredbadpractice,butinmicrocontrollerdevelopment,youmaybeabletoget
aperformanceincreasethisway,andperformanceismoreimportantthanmaintainability
211

andprogrammingeleganceinthecontextofmicrocontrollers.
AntiPatterns
An anti-pattern is an approach to some commonly occuring problem in software that we
deemtobebad. Thechosenapproachisinsomewayineffective, messy, orproblematicto
thedesign.
Anti-patterns and code smells are similar in definition, though some believe a code smell is
something done unwillingly or without much thought behind how it impairs the de-
sign, where some believe an anti-pattern to be much more deliberate in its intent to
(unfortunately,poorly)solveaproblem.
Anti-patternsareusuallyafull-blownapproachtosolvingaproblemthathasverynegative
consequences. Hereareafewexamples.
Soft-code(anti-pattern): Storingbusinesslogicinconfigurationfilesratherthansource
code. You know about hard-coding right? That’s when you manually write the values in
for commonly changed variables. Not ideal. However, abstracting too much turns into
softcode. Withtheadventofcompaniestryingtomakecodingobseletewithnocode,this
sounds like something that definitely happened in the real-world somewhere. Someone,
somewhere,istryingtoputtheirentireapplicationinaYAMLfile. Whentheconfiguration
filesbecomethesourcecode,andthesourcecodebecomesanabstraction,werunintoprob-
lems.Toomuchabstractionmakesunderstandingthecodeandmaintainingitconsiderably
harder.
Interfacebloat(anti-pattern): Makinganinterfacesorobustthatitischallengingtoim-
plement. Thisisafunnyone. Intypedlanguages,weuseinterfacestocreateacontract. If
wewroteaninterfaceforaIWarrior,wegettheopportunitytospecifyalltheattributesand
methodsthatdeemanobjecttobeanIWarrior.
Typically,wetrytokeepinterfacesrelativelysmall. Ifwehavestuffrelatedtohowthewar-
rioreats,hunts,orevenhisapparel,it’sgoodpracticetoextractthatresponsibilityintosep-
arateinterfaces. It’spreferabletohaveseveralinterfaceswithsingleresponsibility,instead
ofonebigone. Nowthinkabouthowyou’dfeelifyouknewyouneededtocreateaIWarrior
implementation,andtherewere100attributesandmethodsforyoutoimplementinorder
tocreateit…Mad. You’dfeelmad. “WhydoIneedtodefinewhatthiswarrior’sfavouritetv
showis? WhythehellisthisapartoftheIWarriorinterface?”
Anti-patternsarenotdeterminate
Whatisandwhatisnotananti-patternisnotdeterminate.
Depending on the programmer, the project, and the software development methology / ap-
proach, there are scenarios when a pattern is a vice in one context, yet a strategic saving
graceinanother.
AnemicDomainModelsvs.EntityComponentSystem
One of my favourite anti-patterns to bring attention to is the Anemic Domain Model.
An AnemicDomainModel is something that develops when all of the business logic for our
212

models doesn’t actually live in the model, but instead lives in things like services, controllers,
orhelperclasses.
ConsidertheexamplewhereyouhaveaUsermodelandaUserService.TheUserServicehas
methodscalledcreateUserandupdateUserthateachdovalidationandencapsulatesany
domainlogicthatdictateshowsomeonemaycreateorupdateauser.
user.ts
| export      | class      | User    | {       |       |         |        |         |     |
| ----------- | ---------- | ------- | ------- | ----- | ------- | ------ | ------- | --- |
| public      | id:        | string; |         |       |         |        |         |     |
| public      | name:      | string; |         |       |         |        |         |     |
| public      | email:     | string; |         |       |         |        |         |     |
| constructor |            | (id:    | string, | name: | string, | email: | string) | {   |
|             | this.id    | = id;   |         |       |         |        |         |     |
|             | this.name  | = name; |         |       |         |        |         |     |
|             | this.email | =       | email;  |       |         |        |         |     |
}
}
userService.ts
| export | class | UserService |     | {   |     |     |     |     |
| ------ | ----- | ----------- | --- | --- | --- | --- | --- | --- |
...
| public | createUser                             |     | (userFields: |      | UserFields): |     | User { |     |
| ------ | -------------------------------------- | --- | ------------ | ---- | ------------ | --- | ------ | --- |
|        | // Validation                          |     | logic        | here |              |     |        |     |
|        | if (                                   |     |              |      |              |     |        |     |
|        | !this.isValidUserName(userFields.name) |     |              |      |              |     | ||     |     |
!this.isValidEmail(userFields.email)
) {
|     | throw | new Error("Invalid |     |     | fields | for user"); |     |     |
| --- | ----- | ------------------ | --- | --- | ------ | ----------- | --- | --- |
}
return new User(this.createId(), userFields.name, userFields.email);
}
public updateUser (userFields: UserFields, user: User): void {
|     | // Same | validation |     | logic | here |     |     |     |
| --- | ------- | ---------- | --- | ----- | ---- | --- | --- | --- |
if (userFields.hasOwnProperty('name') && this.isValidUserName(userFields.name)) {
|     | user.name | =   | userFields.name; |     |     |     |     |     |
| --- | --------- | --- | ---------------- | --- | --- | --- | --- | --- |
}
if (userFields.hasOwnProperty('email') && this.isValidEmail(userFields.email)) {
|     | user.email |     | = userFields.email; |     |     |     |     |     |
| --- | ---------- | --- | ------------------- | --- | --- | --- | --- | --- |
}
}
}
213

What’sstoppingsomeonefromcircumventingtheUserServiceentirelyandjustcreating
a User using new User({})? Nothing, really. That’s because the operations against the
modelareseparatefromthemodelitself. Therefore,theUsermodelisanemic. Itdoesn’t
reallyhaveanyrealpurpose.
You don’t want an AnemicDomainModel because it leads to writing a lot of duplicate code.
I want the code that does the validation logic against my User to live in the User, not
inUserService. HowamIsupposedtoremembertowritethevalidationlogicforUserin
everysinglemethodthatchangestheUserinUserService?
ThisbaddesignisremediedbyusingaDomainModel,whichwe’llexploreindetailinChapter
5.
Itturnsoutthatthere’sacontextwhereit’sactuallyadvantageoustohaveanAnemicDomain
Model. Thatcontextisgameprogramming.
Withwebdevelopment,wedon’twantanAnemicDomainModel. However,ingameprogram-
ming, having the model separate from the operations that you can perform against a
modelisideal.ThatprincipleisformalizedasEntityComponentSystemwhichfavorscom-
positionoverinheritance.
Have you ever played a game called Garry’s Mod? In it, you can be just about anything.
There’s a popular game mode called Prop Hunt where the hiders need to find any prop on
the map (car, flower, plant, box, shoe, you name it), select it to become it, and hide so that
theseekersdon’tfindthem. Thegoalistolooksoinconspicuousthatnoonefindsyou.
When you start out, you’re a human, and you’re able to move. When you find your special
prop and become it, you can still move, though you move at the same speed as when you
wereahuman.
Inthiscase,thebehaviourofmovingisabstractedawayfromthehuman,box,shoe,etcmodel.
Thatlogicisnotpartofthemodel. Isn’tthatbad?
Notinthiscase! Becausenewitemsareconstantlygettingadded,thisarchitectureenables
gamedeveloperstosatisfynewmapswithnewpropsbysimplycreatingthemodels,with-
outhavingtoworryabouthoweachmodelworks(thatcanbedecideduponlater).
Anti-patternsareusuallyfull-blownapproachestosolvingaproblem. They’re
usuallythewrongapproach. It’sananti-patternwhenchoosingthewrongap-
proachhasnegativelong-termconsequences.
DRYvsOverengineering
DanAbramov,creatorofReduxandengineeratFacebook,releasedablogpostcalled“Good-
bye,CleanCode”. Init,hetellsthestoryabouthowhisengineercheckedinsomecodethat
hadalotofrepetitioninit. Here’sthecode.
let Rectangle = {
resizeTopLeft(position, size, preserveAspect, dx, dy) {
// 10 repetitive lines of math
},
214

| resizeTopRight(position, |       | size, preserveAspect, |     | dx, dy) { |
| ------------------------ | ----- | --------------------- | --- | --------- |
| // 10 repetitive         | lines | of math               |     |           |
},
| resizeBottomLeft(position, |       | size, preserveAspect, |     | dx, dy) { |
| -------------------------- | ----- | --------------------- | --- | --------- |
| // 10 repetitive           | lines | of math               |     |           |
},
| resizeBottomRight(position, |       | size, preserveAspect, |     | dx, dy) { |
| --------------------------- | ----- | --------------------- | --- | --------- |
| // 10 repetitive            | lines | of math               |     |           |
},
};
let Oval = {
| resizeLeft(position, |       | size, preserveAspect, | dx, | dy) { |
| -------------------- | ----- | --------------------- | --- | ----- |
| // 10 repetitive     | lines | of math               |     |       |
},
| resizeRight(position, |       | size, preserveAspect, |     | dx, dy) { |
| --------------------- | ----- | --------------------- | --- | --------- |
| // 10 repetitive      | lines | of math               |     |           |
},
| resizeTop(position, | size, | preserveAspect, | dx, | dy) { |
| ------------------- | ----- | --------------- | --- | ----- |
| // 10 repetitive    | lines | of math         |     |       |
},
| resizeBottom(position, |       | size, preserveAspect, |     | dx, dy) { |
| ---------------------- | ----- | --------------------- | --- | --------- |
| // 10 repetitive       | lines | of math               |     |           |
},
};
let Header = {
| resizeLeft(position, |       | size, preserveAspect, | dx, | dy) { |
| -------------------- | ----- | --------------------- | --- | ----- |
| // 10 repetitive     | lines | of math               |     |       |
},
| resizeRight(position, |       | size, preserveAspect, |     | dx, dy) { |
| --------------------- | ----- | --------------------- | --- | --------- |
| // 10 repetitive      | lines | of math               |     |           |
},
}
| let TextBlock = {       |       |                       |     |           |
| ----------------------- | ----- | --------------------- | --- | --------- |
| resizeTopLeft(position, |       | size, preserveAspect, |     | dx, dy) { |
| // 10 repetitive        | lines | of math               |     |           |
},
| resizeTopRight(position, |       | size, preserveAspect, |     | dx, dy) { |
| ------------------------ | ----- | --------------------- | --- | --------- |
| // 10 repetitive         | lines | of math               |     |           |
},
| resizeBottomLeft(position, |       | size, preserveAspect, |     | dx, dy) { |
| -------------------------- | ----- | --------------------- | --- | --------- |
| // 10 repetitive           | lines | of math               |     |           |
},
| resizeBottomRight(position, |     | size, preserveAspect, |     | dx, dy) { |
| --------------------------- | --- | --------------------- | --- | --------- |
215

| //  | 10 repetitive |     | lines | of  | math |     |
| --- | ------------- | --- | ----- | --- | ---- | --- |
},
};
To remove the repetition, Dan grouped the code together and abstracted its parts like the
following.
| let {top, | bottom,                  |         | left, | right} | = Directions; |     |
| --------- | ------------------------ | ------- | ----- | ------ | ------------- | --- |
| function  | createHandle(directions) |         |       |        | {             |     |
| // 20     | lines                    | of code |       |        |               |     |
}
| let fourCorners       |     | =   | [        |          |     |     |
| --------------------- | --- | --- | -------- | -------- | --- | --- |
| createHandle([top,    |     |     | left]),  |          |     |     |
| createHandle([top,    |     |     | right]), |          |     |     |
| createHandle([bottom, |     |     |          | left]),  |     |     |
| createHandle([bottom, |     |     |          | right]), |     |     |
];
| let fourSides |     | = [ |     |     |     |     |
| ------------- | --- | --- | --- | --- | --- | --- |
createHandle([top]),
createHandle([left]),
createHandle([right]),
createHandle([bottom]),
];
| let twoSides |     | = [ |     |     |     |     |
| ------------ | --- | --- | --- | --- | --- | --- |
createHandle([left]),
createHandle([right]),
];
| function | createBox(shape, |         |     | handles) | {   |     |
| -------- | ---------------- | ------- | --- | -------- | --- | --- |
| // 20    | lines            | of code |     |          |     |     |
}
| let Rectangle |                   | = createBox(Shapes.Rectangle, |     |     |             | fourCorners); |
| ------------- | ----------------- | ----------------------------- | --- | --- | ----------- | ------------- |
| let Oval      | =                 | createBox(Shapes.Oval,        |     |     | fourSides); |               |
| let Header    |                   | = createBox(Shapes.Rectangle, |     |     |             | twoSides);    |
| let TextBox   |                   | = createBox(Shapes.Rectangle, |     |     |             | fourCorners); |
| Alright.      | Overtoyou,reader. |                               |     |     |             |               |
Whichversioniscleaner?
Thefirstversion,whilefullofrepetition,wasmucheasiertounderstandandchangethan
theversionthatabstractedeverythingaway.
ImplementingtheDRY(donotrepeatyourself)principlepullsusclosertowardsOverengi-
216

neering (a terrible anti-pattern), while Repeating yourself (also an anti-pattern) may pull us
closertowardsmorehumanreadablecodeinsomecontexts.
Computerscienceisagameoftrade-offs, andwhereOverengineeringtradesreadabilityfor
abstraction,it’salmostalwaysabadtrade. Ifwecan’tunderstandandchangethecode,itis
nolongeradheringtowhatwehavedeemedoneoftheprimarygoalsofsoftware.
Alwaysputhumansfirst! Makeyourfellowdevelopersawesome!
Catalogofanti-patterns:Readtheanti-patternsthatpertaintosoftwareengineeringhere.
I highly recommend you spend some time on this page and peak through them. We all
know software is hard. Take in the fact that each of these patterns exists because some-
where, somehow, a project failed- and it was the emergence of one or more of these pat-
ternsthatplayedapartinitsfailure. Anti-patternsaredecidedlyorganizedintoSoftware
design,Object-orientedprogramming,Methodological,Programming,andConfiguration
managementrelatedanti-patterns.
Overengineering
CyclamaticComplexity
Overengineering
CyclamaticComplexity
Optimizingcode
Optimizingcodeforhumansmeansmakingiteasiertoreadandunderstand.
Optimizingcodeforcomputersmeansmakingitmoreefficient.
Often, optimizing code for computers means making it less readable and understandable
forhumans.
Thisisatoughbalancetoaccomplish,butusually,Irecommendoptimizingforhumans>
computers.
Absoluteandrelativecomplexity
Bestpractices,principles,andpatterns
Guidingprinciplesforcleancoders
Principle#1-Caredeeplyabouttheprojectandthedomain
Youneedtocaredeeply. Noneofthisworksifyoudon’tcare.
Reasonsyoumightnotcareaboutwritingcleancode:
• Youhateyourjob
• You’regoingtoleavethecompany
• It’sjustahackyproject
• You’retheonlyoneworkingonit
217

• Theprojectisn’tgoingsowellanditfeelslikeadeathmarch
Withoutthisfirstprinciple,nothingelsemattersandyoumayneverwritecleancode.
Getyourselfintoajobyouenjoywithpeopleyoudon’tmindworkingwith,orintoadomain
thathasthepotentialtoseeminteresting,andbecomepassionateaboutthesuccessofthe
project, and the become empathetic about the lives of other developers that will need to
interactwithyourcodetomorrowand5yearsfromnow.
Principle#2-Aimtoempowerteammatesandfuturemaintainers
Agoalofyoursshouldbemakeotherdevelopersfeelliketheyaregoodattheirjob. They
shouldbeabletounderstand,locate,andchangecodequicklyandsafely.
Putrealcodeyouwroteinfrontofafellowdeveloperandaskthemtocomprehend,locate,
andchangeafeature. Dothistodetermineifyourempatheticcodingabilityneedstuning.
Principle#3-Humans>computers
Principle #4 - Conventions and patterns are helpful but they are secondary to the
needsofyourusersandfuturemaintainers
Whenitisnotadetrimenttotheempowermentofotherdevelopers,enforceconven-
tions,designpatterns,andprinciples.
DRYissecondary.
Abstractionissecondary.
Even the popular coding conventions that I’m about to show you in the next section are
secondaryiftheydon’tempoweryourteamandfuturemaintainers.
Wheretogofromhere
Therestofwhatyou’regoingtolearninthisbookareprinciples,practices,theoryandap-
proachestodesigningsoftware.
Everythingfromhereonoutisfoodforthoughttobetterinformyourpreferredcodingcon-
ventions.
ThetheoryyoulearninProgrammingParadigmsshouldhelpyouunderstandthebenefits
ofObject-OrientedProgrammingandwhenitmightmakesensetotakeamorefunctional
approachinyourwork.
Learninghowto
Resources
CuratethissectionalmostlikehowDavidPerrellcurateshisstuffonhisemaillisttosend
toothers. Giveeachofthesebooksandlinksalittleblurbaboutthetakeaways. Soforeach
book,explainwhy—whatwillthereadergetfromit.
• Craftsmanship
218

– ThePragmaticProgrammer
– TheSoftwareCraftsman: Professionalism,Pragmatism,Pride
– TheCleanCoder—UncleBob
– CleanAgile: BacktoBasics
– ApprenticeshipPatterns: GuidancefortheAspiringSoftwareCraftsman
– SoftwareCraftsman: TheNewImperative
– ExtremeProgrammingExplained—KentBeck
– https://en.wikipedia.org/wiki/Agile_software_development#The_Agile_Man
ifesto
– https://en.wikipedia.org/wiki/Software_craftsmanship
– https://8thlight.com/blog/paul-pagel/2009/03/11/history-of-the-software-
craftsmanship-manifesto.html
– https://en.wikipedia.org/wiki/Journeyman
• Growth
– https://www.brainpickings.org/2014/01/29/carol-dweck-mindset/
• Designthinking
– TheDesignofEverydayThings
– EmpathyinHumanCenteredDesign
– https://www.benjaminjohnson.me/blog/empathetic-code/#:~:text=Having a
consistentcodestyleisempatheticbecauseitallows,theyaretryingtosolve.
– https://compassionatecoding.com/
– KathySierra’s“Badass: MakingUsersAwesome”.
– Onknowledgeintheheadvs.theworld
* [https://medium.com/@matthewraychiang/doet-knowledge-in-the-
head-and-in-the-world-64f901627eb3#:~:text=Knowledge in the head
is,that needs to be remembered.&text=Memory for arbitrary things is
straight memorization](https://medium.com/@matthewraychiang/doet-
knowledge-in-the-head-and-in-the-world-64f901627eb3#:~:text=Knowledge%20in%20the%20head%20is,that%20needs%20to%20be%20remembered.&text=Memory%20for%20arbitrary%20things%20is%20straight%20memorization).
References
ThisisjustacollectionofitemsandthingsthatIusedtodevelopthissection,personally.
https://alleninstitute.org/what-we-do/brain-science/news-press/articles/5-unsolved-
mysteries-about-brain
https://en.wikipedia.org/wiki/Code_smell
https://en.wikipedia.org/wiki/Softcoding
https://en.wikipedia.org/wiki/Anti-pattern
https://en.wikipedia.org/wiki/No_Silver_Bullet
https://en.wikipedia.org/wiki/God_object
https://en.wikipedia.org/wiki/Poltergeist_(computer_programming)
https://en.wikipedia.org/wiki/Call_super
https://en.wikipedia.org/wiki/Yo-yo_problem
219

https://en.wikipedia.org/wiki/Principle_of_least_astonishment
https://en.wikipedia.org/wiki/Coding_conventions
https://medium.com/mindorks/how-to-write-clean-code-lessons-learnt-from-the-clean-
code-robert-c-martin-9ffc7aef870c
https://overreacted.io/goodbye-clean-code/
https://cvuorinen.net/2014/04/what-is-clean-code-and-why-should-you-care/
https://dev.to/d_ir/clean-code-dirty-code-human-code-6nm
https://dev.to/carlillo/clean-code-applied-to-javascript-part-i-before-your-start-16ic
https://www.python.org/dev/peps/pep-0008/
https://medium.com/@p_arithmetic/a-collection-of-my-6-favorite-javascript-one-
liners-7c80a4b731f8
https://blog.codinghorror.com/code-tells-you-how-comments-tell-you-why/
https://www.reddit.com/r/askscience/comments/24z4qv/why_is_it_so_difficult_to_remember_a_short/
“CodeConventionsfortheJavaProgrammingLanguage”
https://www.oracle.com/technetwork/java/index-135089.html
https://en.wikipedia.org/wiki/Softcoding
https://en.wikipedia.org/wiki/Code_smell
https://en.wikipedia.org/wiki/Anti-pattern
https://en.wikipedia.org/wiki/God_object
https://medium.com/better-programming/how-to-create-meaningful-names-in-code-
20d7476537d4
TheCleanCoder—Chapter14,Chapter1,
4. Programming Paradigms
Thethreeorganizationalarchetypes
As I said earlier, there are really only three organizational archetypes we need to master:
behavioralobjects,stateobjects,andnamespaces.Onceyou’rethere,alltheparticulartribal
schoolslikeDDDdon’tmatter.
Apparently,thesemakeupthemajorityofsoftwaredesign
• behavioralobjects,
• stateobjects,
• andnamespaces
Tome,thissoundslikethenatureofDDD
220

(Theory)vs.(Implementation)Behaviors=domaineventsStateobjects=aggregatesNames-
paces=subdomains/boundedcontexts
5. Object-Oriented Programming & Domain Modeling
6. Design Principles
SOLID
SingleResponsibilityPrinciple
Open-ClosedPrinciple(OCP)
LiskovSubstitutionPrinciple(LSP)
InterfaceSegregationPrinciple
DependencyInversionPrinciple(DIP)
SingleResponsibilityPrinciple
Open-ClosedPrinciple(OCP)
LiskovSubstitutionPrinciple(LSP)
InterfaceSegregationPrinciple
DependencyInversionPrinciple(DIP)
One of the first things we learn in programming is to decompose large problems into
smaller parts. That divide-and-conquer approach can help us to assign tasks to others,
reduceanxietybyfocusingononethingatatime,andimprovemodularityofourdesigns.
Buttherecomesatimewhenthingsarereadytobehookedup.
That’swheremostdevelopersgoaboutthingsthewrongway.
Mostdevelopersthathaven’tyetlearnedaboutthesolidprinciplesorsoftwarecomposition,
andproceedtowritetightlycouplemodulesandclassesthatshouldn’tbecoupled,resulting
incodethat’shardtochangeandhardtotest.
Inthissection,we’regoingtolearnabout:
• Components&softwarecomposition
• HowNOTtohookupcomponents
• HowandwhytoinjectdependenciesusingDependencyInjection
• HowtoapplyDependencyInversionandwritetestablecode
• ConsiderationsusingInversionofControlcontainers
Terminology
Let’smakesurethatweunderstandtheterminologyonwiringupdependenciesbeforewe
continue.
221

Components
I’mgoingtousethetermcomponentalot. ThattermmightstrikeachordwithReact.jsor
Angulardevelopers,butitcanbeusedbeyondthescopeofweb,Angular,orReact.
Acomponentissimplyapartofanapplication. It’sanygroupofsoftwarethat’sintended
tobeapartofalargersystem.
Theidea is tobreaka largeapplicationupintoseveralmodular componentsthatcan be
independentlydevelopedandassembled.
The more you learn about software, the more you realize that good software design is all
aboutcompositionofcomponents.
Failuretogetthisrightleadstoclumpycodethatcan’tbetested.
DependencyInjection
Eventually, we’ll need to hook components up somehow. Let’s look at a trivial (and non-
ideal)waythatwemighthooktwocomponentsuptogether.
Inthefollowingexample,wewanttohookupaUserControllersothatitcanretrieveall
| User[]s |     | UserRepo |     |     |     |     |
| ------- | --- | -------- | --- | --- | --- | --- |
the from a (repository) when someone makes an HTTP GET request to
/api/users.
// repos/userRepo.ts
/**
| * @class | UserRepo    |             |     |            |              |     |
| -------- | ----------- | ----------- | --- | ---------- | ------------ | --- |
| * @desc  | Responsible | for pulling |     | users from | persistence. |     |
**/
| export      | class UserRepo | {               |     |          |           |      |
| ----------- | -------------- | --------------- | --- | -------- | --------- | ---- |
| constructor | ()             | {}              |     |          |           |      |
| getUsers    | ():            | Promise<User[]> | {   |          |           |      |
| //          | Use Sequelize  | or TypeORM      | to  | retrieve | the users | from |
| //          | a database.    |                 |     |          |           |      |
}
}
Andthecontroller…
// controllers/userController.ts
| import | { UserRepo | } from '../repos' |     | // Bad |     |     |
| ------ | ---------- | ----------------- | --- | ------ | --- | --- |
/**
| * @class | UserController |              |     |              |     |     |
| -------- | -------------- | ------------ | --- | ------------ | --- | --- |
| * @desc  | Responsible    | for handling |     | API requests | for | the |
| * /user  | route.         |              |     |              |     |     |
**/
222

| class         | UserController | {                 |         |              |         |     |
| ------------- | -------------- | ----------------- | ------- | ------------ | ------- | --- |
| private       | userRepo:      | UserRepo;         |         |              |         |     |
| constructor   | ()             | {                 |         |              |         |     |
| this.userRepo |                | = new UserRepo(); | // Also | bad, read on | for why |     |
}
| async  | handleGetUsers         | (req, res):                       | Promise<void> | {   |     |     |
| ------ | ---------------------- | --------------------------------- | ------------- | --- | --- | --- |
| const  | users                  | = await this.userRepo.getUsers(); |               |     |     |     |
| return | res.status(200).json({ |                                   | users });     |     |     |     |
}
}
Intheexample,weconnectedaUserRepodirectlytoaUserControllerbyreferencingthe
nameoftheUserRepoclassfromwithintheUserControllerclass.
| Thisisn’tideal. | Whenwedothat,wecreateasourcecodedependency. |     |     |     |     |     |
| --------------- | ------------------------------------------- | --- | --- | --- | --- | --- |
Sourcecodedependency: Whenthecurrentcomponent(class,module,etc)re-
liesonatleastoneothercomponentinordertobecompiled. Sourcecodede-
pendenciesshouldbelimited.
TheproblemisthateverytimethatwewanttospinupaUserController,weneedtomake
surethattheUserRepoisalsowithinreachsothatthecodecancompile.
WhenmightyouwanttospinupanisolatedUserController?
Duringtesting.
It’sacommonpracticeduringtestingtomockorfakedependenciesofthecur-
rentmoduleundertestinordertoisolateandtestdifferentbehaviors.
Noticehowwe’rea)importingtheconcreteUserRepoclassintothefileandb)creatingan
instanceofitfromwithintheUserControllerconstructor?
Thatrendersthiscodeuntestable. Oratleast,if UserRepowasconnectedtoarealliverun-
ningdatabase,we’dhavetobringtheentiredatabaseconnectionwithustorunourtests,
makingthemveryslow…
DependencyInjectionisatechniquethatcanimprovethetestabilityofourcode.
Itworksbypassingin(usuallyviaconstructor)thedependenciesthatyourmoduleneeds
tooperate.
|              |     | UserRepo          |      | UserController, |                |     |
| ------------ | --- | ----------------- | ---- | --------------- | -------------- | --- |
| If we change | the | way we inject the | from |                 | we can improve | it  |
slightly.
// controllers/userController.ts
| import | { UserRepo | } from '../repos' | // Still | bad |     |     |
| ------ | ---------- | ----------------- | -------- | --- | --- | --- |
223

/**
* @class UserController
| * @desc Responsible | for | handling | API | requests | for the |
| ------------------- | --- | -------- | --- | -------- | ------- |
* /user route.
**/
| class UserController | {         |     |     |     |     |
| -------------------- | --------- | --- | --- | --- | --- |
| private userRepo:    | UserRepo; |     |     |     |     |
constructor (userRepo: UserRepo) { // Better, inject via constructor
| this.userRepo | = userRepo; |     |     |     |     |
| ------------- | ----------- | --- | --- | --- | --- |
}
| async handleGetUsers          | (req,                             | res): | Promise<void> |     | {   |
| ----------------------------- | --------------------------------- | ----- | ------------- | --- | --- |
| const users                   | = await this.userRepo.getUsers(); |       |               |     |     |
| return res.status(200).json({ |                                   |       | users         | }); |     |
}
}
Eventhoughwe’reusingdependencyinjection,there’sstillaproblem.
UserControllerstillreliesonUserRepodirectly.
Thisdependencyrelationshipstillholdstrue.
Evenstill,ifwewantedtomockoutourUserRepothatconnectstoarealSQLdatabasefora
mockin-memoryrepository,it’snotcurrentlypossible.
UserControllerneedsaUserRepo,specifically.
// controllers/userRepo.spec.ts
| let userController: | UserController;       |      |        |       |              |
| ------------------- | --------------------- | ---- | ------ | ----- | ------------ |
| beforeEach(() =>    | {                     |      |        |       |              |
| userController      | = new UserController( |      |        |       |              |
| new UserRepo()      | // Slows              | down | tests, | needs | a db running |
)
});
So.. whatdowedo?
IntroducingtheDependencyInversionPrinciple!
DependencyInversion
DependencyInversionisatechniquethatallowsustodecouplecomponentsfromonean-
other. Checkthisout.
224

Whatdirectiondoestheflowofdependenciesgoinrightnow?
| Fromlefttoright. |     | TheUserControllerreliesontheUserRepo. |     |     |     |     |     |
| ---------------- | --- | ------------------------------------- | --- | --- | --- | --- | --- |
OK.Ready?
Watch what happens when we slap an interface in between the two components make
UserRepoimplementanIUserRepointerface, andthenpointtheUserControllertorefer
tothatinsteadoftheUserRepoconcreteclass.
// repos/userRepo.ts
/**
| * @interface |             | IUserRepo |     |         |     |            |              |
| ------------ | ----------- | --------- | --- | ------- | --- | ---------- | ------------ |
| * @desc      | Responsible |           | for | pulling |     | users from | persistence. |
**/
| export   | interface |                     | IUserRepo | {   |     | //  | Exported |
| -------- | --------- | ------------------- | --------- | --- | --- | --- | -------- |
| getUsers |           | (): Promise<User[]> |           |     |     |     |          |
}
| class       | UserRepo | implements          |     | IUserRepo |     | { // | Not exported |
| ----------- | -------- | ------------------- | --- | --------- | --- | ---- | ------------ |
| constructor |          | ()                  | {}  |           |     |      |              |
| getUsers    |          | (): Promise<User[]> |     |           | {   |      |              |
...
}
}
And update the controller to refer to the IUserRepo interface instead of the UserRepo con-
creteclass.
// controllers/userController.ts
| import | { IUserRepo |     | } from | '../repos' |     | // Good! |     |
| ------ | ----------- | --- | ------ | ---------- | --- | -------- | --- |
/**
| * @class | UserController |     |     |          |     |              |         |
| -------- | -------------- | --- | --- | -------- | --- | ------------ | ------- |
| * @desc  | Responsible    |     | for | handling |     | API requests | for the |
| * /user  | route.         |     |     |          |     |              |         |
**/
| class       | UserController |            | {          |            |     |           |          |
| ----------- | -------------- | ---------- | ---------- | ---------- | --- | --------- | -------- |
| private     | userRepo:      |            | IUserRepo; |            | //  | like here |          |
| constructor |                | (userRepo: |            | IUserRepo) |     | { //      | and here |
225

| this.userRepo |     | =   | userRepo; |     |     |     |
| ------------- | --- | --- | --------- | --- | --- | --- |
}
| async  | handleGetUsers         |         | (req,                     | res): | Promise<void> | {   |
| ------ | ---------------------- | ------- | ------------------------- | ----- | ------------- | --- |
| const  | users                  | = await | this.userRepo.getUsers(); |       |               |     |
| return | res.status(200).json({ |         |                           |       | users });     |     |
}
}
Nowlookatdirectionoftheflowofdependencies.
Youseewhatwejustdid? Bychangingallofthereferencesfromconcreteclassestoin-
terfaces, we’ve just flipped the dependency graph and created an architecturalboundary
in-betweenthetwocomponents.
Designprinciple: Programagainstinterfaces,notimplementations.
Maybeyou’renotasexcitedaboutthisasIam. Letmeshowyouwhythisissogreat.
RememberwhenIsaidthatwewantedtobeabletoruntestsontheUserControllerwith-
out having to pass in a UserRepo, solely because it would make the tests slow(UserRepo
needsadbconnectiontorun)?
Well,nowwecanwriteaMockUserRepowhichimplementsIUserRepoandallthemethods
ontheinterface,andinsteadofusingaclassthatreliesonaslowdbconnection,useaclass
| thatcontainsaninternalarrayof |     |     |     | User[]s(muchquicker! |     | (cid:0)). |
| ----------------------------- | --- | --- | --- | -------------------- | --- | --------- |
That’swhatwe’llpassthatintotheUserControllerinstead.
Usingamockobject
// repos/mocks/mockUserRepo.ts
| import      | { IUserRepo  | }          | from            | '../repos'; |     |     |
| ----------- | ------------ | ---------- | --------------- | ----------- | --- | --- |
| class       | MockUserRepo | implements |                 | IUserRepo   | {   |     |
| private     | users:       | User[]     | =               | [];         |     |     |
| constructor |              | () {}      |                 |             |     |     |
| async       | getUsers     | ():        | Promise<User[]> |             | {   |     |
| return      | this.users;  |            |                 |             |     |     |
}
226

}
Tip: Adding “async” to a method auto-wraps it in a Promise, making it easy to fake asyn-
chronousactivity.
WecanwriteatestusingatestingframeworklikeJest.
// controllers/userRepo.spec.ts
| import              | { MockUserRepo                    | } from '../repos/mock/mockUserRepo'; |     |     |
| ------------------- | --------------------------------- | ------------------------------------ | --- | --- |
| let userController: |                                   | UserController;                      |     |     |
| const               | mockResponse                      | = () => {                            |     |     |
| const               | res = {};                         |                                      |     |     |
| res.status          | = jest.fn().mockReturnValue(res); |                                      |     |     |
| res.json            | = jest.fn().mockReturnValue(res); |                                      |     |     |
| return              | res;                              |                                      |     |     |
};
| beforeEach(()  | => { |                     |     |     |
| -------------- | ---- | ------------------- | --- | --- |
| userController | =    | new UserController( |     |     |
new MockUserRepo() // Speedy! And valid since it inherits IUserRepo.
)
});
test ("Should 200 with an empty array of users", async () => {
| let   | res = mockResponse();               |     |       |     |
| ----- | ----------------------------------- | --- | ----- | --- |
| await | userController.handleGetUsers(null, |     | res); |     |
expect(res.status).toHaveBeenCalledWith(200);
| expect(res.json).toHaveBeenCalledWith({ |     |     | users: | [] }); |
| --------------------------------------- | --- | --- | ------ | ------ |
})
| Congrats. | You(moreorless)justlearnedhowwritetestablecode! |     |     |     |
| --------- | ----------------------------------------------- | --- | --- | --- |
TheprimarywinsofDependencyInversion
Notonlydoesthisdecouplingmakeyourcodetestable,butitimprovesthefollowingcharac-
teristicsofyourcode:
• Testability: Wecansubstituteexpensivetoinfrastructurecomponentsformockones
duringtesting.
• Substitutability:Ifweprogramagainstaninterface,weenableapluginarchitecture
adheringtotheLiskovSubstitutionPrinciple, whichmakesitincrediblyeasyforus
to swap out valid plugins, and program against code that doesn’t yet exist. Because
theinterfacedefinestheshapeofthedependency, allweneedtodotosubstitutethe
current dependency is create a new one that adheres to the contract defined by the
| interface. | Seethisarticletodivedeeperonthat. |     |     |     |
| ---------- | --------------------------------- | --- | --- | --- |
• Flexibility:AdheringtotheOpenClosedPrinciple,asystemshouldbeopenforexten-
sionbutclosedformodification. Thatmeansifwewanttoextendthesystem,weneed
227

onlycreateanewplugininordertoextendthecurrentbehavior.
• Delegation: InversionofControlisthephenomenonweobservewhenwedelegate
behaviortobeimplementedbysomeoneelse,butprovidethehooks/plugins/callbacks
todoso.Wedesignthecurrentcomponenttoinvertcontroltoanotherone.Lotsofweb
frameworksarebuiltonthisprinciple.
InversionofControl&IoCContainers
Applicationsgetmuchlargerthanjusttwocomponents.
Notonlydoweneedtoensurewe’rereferringtointerfacesandNOTconcreteimplemen-
tations,butwealsoneedtohandletheprocessofmanuallyinjectinginstancesofdependen-
ciesatruntime.
If your app is relatively small or you’ve got a style guide for hooking up dependencies on
yourteam,youcoulddothismanually.
If you’ve got a huge app and you don’t have a plan for how you’ll accomplish dependency
injectionwithininyourapp,ithaspotentialtogetoutofhand.
It’sforthatreasonthatInversionofControl(IoC)Containersexist.
Theyworkbyrequiringyouto:
1. Createacontainer(thatwillholdallofyourappdependencies)
2. Makethatdependencyknowntothecontainer(specifythatitisinjectable)
3. Resolvethedependenciesthatyouneedbyaskingthecontainertoinjectthem
SomeofthemorepopularonesforJavaScript/TypeScriptareAwilixandInversifyJS.
Personally,I’mnotahugefanofthemandtheadditionalinfrastructure-specificframe-
worklogicthattheyscatterallacrossmycodebase.
Inversion of Control: Traditional control flow for a program is when the program only
doeswhatwetellittodo(today). Inversionofcontrolflowisacommonthingtoenablein
framework development and plugin architecture with areas of code that can be hooked
into.
In these cases, we mightnotknow(today) what we want the behavior to be, or - we wish to
enableclientsofourAPI,otherdevelopers,tomakethatdecisionontheirown.
ThatmeansthateverylifecyclehookinReact.jsorAngularisagoodexampleofInversion
of Control in practice. IoC is also often referred to as the “Hollywood Design Principle”:
Don’tcallus,we’llcallyou.
DesignbyContract(DBC)
Chapter21inThePragmaticProgrammerisreallygoodforthis!!!
228

SeparationofConcerns
Relatedblogposts
CQS(CommandQuerySeparation)
PrincipleofLeastSurprise
LawofDemeter(PrincipleofLeastKnowledge)
CompositionoverInheritance
YAGNI
KISS(KeepItSimple,Silly)
DRY(Don’tRepeatYourself)
TheFourPrimaryObject-OrientedDesignPrinciples
Compositionoverinheritance
Aimforshallowclasshierarchies
Aimforshallowclasshierarchies
Encapsulatewhatvaries
Programtointerfaces,nottoimplementations
Dependuponabstractions. Don’tdependuponconcreteclasses.
RelationshiptoPortsandAdaptersarchitecture
RelationshiptoDependencyInversionPrinciple
RelationshiptoPortsandAdaptersarchitecture
RelationshiptoDependencyInversionPrinciple
TheHollywoodPrinciple
Striveforlooselycoupleddesignbetweenobjectsthatinteract
Allsoftwareiscomposition
Designpatternsarecomplexity
Knowofthem,butknowwhenyouneedthem
229

Knowofthem,butknowwhenyouneedthem
SeparationofConcerns
| Example: | overloadedcontroller |     |     |     |     |     |     |
| -------- | -------------------- | --- | --- | --- | --- | --- | --- |
| Example: | overloadedcontroller |     |     |     |     |     |     |
Here is some code that demonstrates what not to do. It’s an example of a RESTful API
createUser
controller class. In it, only the method is shown, but given the class name
AppController,it’srightfultoassumethatthiscontrollerwouldcontainallofthemethods
fortheapp’sRESTapi.
// AppController.ts
| export | class | AppController | {   |     |     |     |     |
| ------ | ----- | ------------- | --- | --- | --- | --- | --- |
...
createUser (req: express.Request, res: express.Response): Promise<void> {
| //    | Get                       | values from            | request                         |                        |       |               |           |
| ----- | ------------------------- | ---------------------- | ------------------------------- | ---------------------- | ----- | ------------- | --------- |
| const |                           | { username,            | email,                          | password               | }     | = req.body;   |           |
| //    | Validate                  | request                | values                          |                        |       |               |           |
| const |                           | isUsernameValid        | =                               | TextUtils.isAtLeast(3, |       |               | username) |
|       | && TextUtils.isAtMost(30, |                        |                                 | username);             |       |               |           |
| const |                           | isEmailValid           | = TextUtils.isValidEmail(email) |                        |       |               |           |
| const |                           | isPasswordValid        | =                               | TextUtils.isAtLeast(3, |       |               | password) |
|       | && TextUtils.isAtMost(25, |                        |                                 | password);             |       |               |           |
| //    | Check                     | if username            | has                             | already                | been  | taken         |           |
| const |                           | existingUserByUserName |                                 | =                      | await | this.userRepo |           |
.getUserByUserName(username);
| if  | (existingUserByUserName) |                        |         | {   |        |     |     |
| --- | ------------------------ | ---------------------- | ------- | --- | ------ | --- | --- |
|     | return                   | res.status(409).json({ |         |     |        |     |     |
|     | message:                 | "Username              | already |     | taken" |     |     |
});
}
| //    | Check | if email            | already | exists  |               |     |     |
| ----- | ----- | ------------------- | ------- | ------- | ------------- | --- | --- |
| const |       | existingUserByEmail |         | = await | this.userRepo |     |     |
.getUserByEmail(email);
| if  | (existingUserByEmail) |                        |         | {       |     |         |      |
| --- | --------------------- | ---------------------- | ------- | ------- | --- | ------- | ---- |
|     | return                | res.status(409).json({ |         |         |     |         |      |
|     | message:              | "User                  | already | exists. | Try | logging | in." |
});
}
230

| // Otherwise, |      | create                         | the user |     |     |
| ------------- | ---- | ------------------------------ | -------- | --- | --- |
| const         | user | = await this.userRepo.create({ |          |     |     |
username,
email,
password
})
| // Send | email                                                 | verification |      | email |     |
| ------- | ----------------------------------------------------- | ------------ | ---- | ----- | --- |
| await   | this.emailService.sendVerificationEmaill(user.email); |              |      |       |     |
| // Add  | email                                                 | to mailing   | list |       |     |
| await   | this.mailingList.add(user.email);                     |              |      |       |     |
| // Do   | more                                                  | stuff...     |      |       |     |
...
}
}
Withoutgoingonatangent,thisfilehasthepotentialtobecomeverylarge.
Inarequest,thereareseveralconcernstoaddress:
1. Authentication—istherequesterauthorized?
2. Controller — pull the args and data from the request object (and maybe even
sanitizethedata)thenhandletheresponse.
3. Authorization—doestherequesterhaveaccesstothisresource?
4. UseCase—whataretheapplication-levelbusinessrules?
5. DomainLogic—whatarethecorebusinessrules?
Thisisalottohandleinasinglecontrollermethod. Thecontrollershouldonlybedoing#2
inthelistprovidedabove. AdheringtotheSeparationofConcernsprincipleisgoingtohelp
keepfilessmallbydelegatingconcernstotheappropriateclass.
Takingononly#2,here’swhatanalteredversionoftheAppControllercouldlooklike.
// AppController.ts
...
| export class | AppController       |     | extends            | BaseController     | {   |
| ------------ | ------------------- | --- | ------------------ | ------------------ | --- |
| private      | createUserUseCase:  |     | CreateUserUseCase; |                    |     |
| constructor  | (createUserUseCase: |     |                    | CreateUserUseCase) | {   |
super();
| this.createUserUseCase |     |     | = createUserUseCase; |     |     |
| ---------------------- | --- | --- | -------------------- | --- | --- |
}
| async createUser            |     | (   |     |     |     |
| --------------------------- | --- | --- | --- | --- | --- |
| req: DecodedExpressRequest, |     |     |     |     |     |
| res: express.Response       |     |     |     |     |     |
| ): Promise<any>             |     | {   |     |     |     |
231

| let dto:  | CreateUserDTO     | =           | req.body | as CreateUserDTO; |     |      |     |
| --------- | ----------------- | ----------- | -------- | ----------------- | --- | ---- | --- |
| // It's   | OK for us         | to sanitize | data     | coming            | in- | that |     |
| // sounds | like a controller |             | concern. |                   |     |      |     |
dto = {
| username: | TextUtils.sanitize(dto.username), |     |     |     |     |     |     |
| --------- | --------------------------------- | --- | --- | --- | --- | --- | --- |
| email:    | TextUtils.sanitize(dto.email),    |     |     |     |     |     |     |
| password: | dto.password                      |     |     |     |     |     |     |
}
try {
| // All | the business   | logic                                | happens | in  | the use     | case |       |
| ------ | -------------- | ------------------------------------ | ------- | --- | ----------- | ---- | ----- |
| const  | result = await | this.createUserUseCase.execute(dto); |         |     |             |      |       |
| // If  | the operation  | failed,                              | present | the | appropriate |      | error |
// response.
| if (result.isLeft()) |                                           | {             |     |     |     |     |     |
| -------------------- | ----------------------------------------- | ------------- | --- | --- | --- | --- | --- |
| const                | error =                                   | result.value; |     |     |     |     |     |
| switch               | (error.constructor)                       |               |     | {   |     |     |     |
|                      | case CreateUserErrors.UsernameTakenError: |               |     |     |     |     |     |
return this.conflict(error.errorValue().message)
|     | case CreateUserErrors.EmailAlreadyExistsError: |     |     |     |     |     |     |
| --- | ---------------------------------------------- | --- | --- | --- | --- | --- | --- |
return this.conflict(error.errorValue().message)
default:
|     | return this.fail(res, |     | error.errorValue().message); |     |     |     |     |
| --- | --------------------- | --- | ---------------------------- | --- | --- | --- | --- |
}
| } else | {             |          |     |     |     |     |     |
| ------ | ------------- | -------- | --- | --- | --- | --- | --- |
| //     | Otherwise,    | success! |     |     |     |     |     |
| return | this.ok(res); |          |     |     |     |     |     |
}
| } catch | (err) {        |      |     |     |     |     |     |
| ------- | -------------- | ---- | --- | --- | --- | --- | --- |
| return  | this.fail(res, | err) |     |     |     |     |     |
}
}
Thisexampleprovidesaclassthathandleseverythingthathastodowithbeingacontroller,
anditdoesitbetterwithpropererrorhandling,andconsistentresponsestoo.
Separationofconcerns
Inthepreviousexample,wefixedtheAppControllerclasstoaddresstheSeparationofCon-
cernsproblem. WhatabouttheSingleResponsibilityPrinciplethatwe’restillviolating?
AppControllerisanobjectthathasmanyreasonstochangebecauseitisreliedonbysev-
IfwehaveUsers,Notifications,Billing,AnalyticsandMediateams,
eraldifferentroles.
232

ifeachteamreliesonthisGod-likecontroller,everyone,evenfromdifferentdomains,will
eventually find reasons for it to need to change. Code needing to change isn’t necessarily
thebadthing,butcodechangingforareasonthatcouldinadvertentlyaffectanotherteam,
isabadthing. Understandingthesingleresponsibilityofaclassanddecouplingittoensure
thatadherestothatcontractisawaytoinsulateourcode. It’salsoawaytoreducefilesize.
One solution the controller’s responsibility down to only the Users subdomain would re-
ducetheissue.
// modules/users/infra/http/userController.ts
export class UserController {
...
}
Evenbetter,scopingthecontrollerdowntoafeaturewithintheUserssubdomain,let’ssay
inafoldercalledcreateUser,alongwithalltheotherfilesneededtomakethefeaturework
(inputtype,responsetype,potentialerrornamespace,etc),we’vecanbothimprovetheSin-
gleResponsibilityandtheSeparationofConcerns.
// modules/users/useCases/createUser/createUserController.ts
export class CreateUserController {
...
}
The Separation of Concerns and Single Responsibility Principle help to keep
files small by indicating howtosplityourfilesintosmallerpieces instead of arbi-
trarilysplittingthem.
233

Cross-cuttingconcerns
PrincipleofLeastSurprise
Striveforloosecouplingbetweenobjectsthatinteract
PrincipleofLeastResistance
Tell,Don’tAsk
7. Design Patterns
Factorypattern
8. Architectural Principles
Componentprinciples
Reuse-ReleaseEquivalencePrinciple
Commonclosureprinciple(CCP)
TheCommonReusePrinciple(CRP)
StableComponents
VolatileComponents
Policy
Reuse-ReleaseEquivalencePrinciple
“Thegranuleofreuseisthegranuleofrelease.”
Excerpt From: Robert C. Martin. “Clean Architecture: A Craftsman’s Guide to Software
StructureandDesign(RobertC.MartinSeries).” AppleBooks.
Commonclosureprinciple(CCP)
“Gather into components those classes that change for the same reasons and at the same
times. Separateintodifferentcomponentsthoseclassesthatchangeatdifferenttimesand
fordifferentreasons.”
Excerpt From: Robert C. Martin. “Clean Architecture: A Craftsman’s Guide to Software
StructureandDesign(RobertC.MartinSeries).” AppleBooks.
TheCommonReusePrinciple(CRP)
“Don’tforceusersofacomponenttodependonthingstheydon’tneed.”
Excerpt From: Robert C. Martin. “Clean Architecture: A Craftsman’s Guide to Software
StructureandDesign(RobertC.MartinSeries).” AppleBooks.
234

StableComponents
VolatileComponents
Policy
Conway’sLaw
TheDependencyRule
Boundaries
Coupling&cohesion
9. Architectural Styles
Structural
Component-basedarchitectures
LayeredArchitectures
Monolithicarchitectures
Component-basedarchitectures
LayeredArchitectures
Monolithicarchitectures
Message-based
Event-Drivenarchitectures
Publish-Subscribearchitectures
Event-Drivenarchitectures
Publish-Subscribearchitectures
Distributed
Client-serverarchitectures
Peer-to-peerarchitectures
235

Client-serverarchitectures
Peer-to-peerarchitectures
10. Architectural Patterns
Cleanarchitecture
Layers
Similararchitectures
Layers
Domainlayer
Applicationlayer
Infrastructurelayer
Adapterlayer
Domainlayer
Applicationlayer
Infrastructurelayer
Adapterlayer
Similararchitectures
Ports&Adapters
Vertical-slicearchitecture
Ports&Adapters
Vertical-slicearchitecture
Thisisreallycool
https://jimmybogard.com/vertical-slice-architecture/
Domain-DrivenDesign
EventSourcing
Notes
EverythingI’verecordedaboutEventSourcingsofar
236

EverythingI’verecordedaboutEventSourcingsofar
Aboutthis
Thisisthemostrequestedcontentontheblogsofar.
I’mgoingtoputallmynotesandeverythingIknowaboutthisthusfar,here:
Internallinks
CopyofBestPlacestoLearnCQRS,EventSourcing
ProgressiontoEventSourcing
Inordertodemonstratehowfarweareinourjourney,Iwanttopresentamaporsomething
sothatpeopleknowhowfartheyare.
Thistweetby@buenosvinoslaysouttheroadmapforsoftwaredesignreallynicelyinmy
opinion. I think that this is a great way to describe the roadmap from no DDD to DDD +
EventSourcing. Icancomeupwithmyownlist,butusingthisasaresourcewouldbehelp-
ful.
https://twitter.com/JellyBellyDev/status/931153536171302922
237

SamHotoum’sEventSourcingw/TypeScriptrepo
SamwrotethisamazingbaselinerepositoryforeventsourcingwithTypeScript. Weneed
toutilizethisstuffforarticlesandallkindsofotherthing.
Takealookatthedescriptionintherepobecauseit’sexcellent.Ithasalotofcontentinthere
thatwecanwriteblogpostsaboutandwhatnot.
stemmlerjs/typescript-event-sourcing
Here’satweetIputoutsofar.
https://twitter.com/stemmlerjs/status/1262365339578400769
WhyEventSourcing?
Somanyreasons.
Statemanagementcodecangetmessy
Structured data for state management introduces accidental complexity. Separating the
write&readmodelssolvesthis. YouneedES/CQRStodothis. GregYoung’sclassis6h. I
thinkwecancondenseit1h. Watchthisspace:)
AllthereasonsdescribedinWhyEventBasedSystems
WhyEvent-BasedSystems? |EnterpriseNode.js+TypeScript|KhalilStemmler
FortheComplexitypart,
Thisishowwe’reensuringthatwehaveeventhandlers
Projectionsanddeserializingeventshappenslikethis!
238

CopyofBestPlacestoLearnCQRS,EventSourcing
Chapter
HowtolearnEventSourcing[https://github.com/MerrionComputing/EventsSourcing-
on-Azure-Functions/wiki/Worked-Example-:-Bank-Account]
Hey,Khalil: Rebooks. TheappendixintheredDDDbookfromVaughnVernon,writtenby
RinatAbdullinisGoldforAggregates+EventSourcing. Alsocoversthisproblemabit.
Loadsofonlinestufffrom@gregyoung
@UdiDahan and many others. @mathiasverraes blogged about many messaging and ES
patterns not long ago!!! I don’t know a book that is very deep in detail here. But classic
@dddesignBCandcontextmapsarestillsuperimportant.
[https://www.amazon.com/o/asin/0321200683/ref=nosim/enterpriseint-20]
That is a good book - I’d add http://dataintensive.net for messaging/architecture related
thingsandifyouareintheMicrosoftsideofthingsthenthishttps://amazon.com/Explori
ng-Sourcing-Microsoft-patterns-practices-ebookisagoodresourcefor“eventsourcing”.
[https://www.youtube.com/watch?time\_continue=1&v=kpM5gCLF1Zc&feature=emb\_title]
—
Khalil’snotes: Damn, the chapter in Vaughn Vernon’s book is actually excellent. This code
sampleisthebestexamplethatI’veseensofar:[https://github.com/abdullin/iddd-sample].
Otherresources:
https://www.pluralsight.com/courses/modern-software-architecture-domain-models-
cqrs-event-sourcing?fbclid=IwAR1EjdEPW32oNfyMItDEGcTPzhQ06J1tT2X0xEAARxGV
AJV_96m4hPg5UcU
239

https://leanpub.com/implementing-ddd-cqrs-and-event-sourcing-in-nodejs?fbclid=Iw
AR2o-1mLodUpGHlZzvMw92P_pK_gfWQSbEs2CtFI3jl_g9-gtpZgcxyo-Zk
https://dev.to/heroku/best-practices-for-event-driven-microservice-architecture-
2lh7?utm_source=additional_box&utm_medium=internal&utm_campaign=regular&b
ooster_org=heroku
DDDisverytoughsubject. SofarI’vereadafewbooksaboutitandtheonlypositionthat
I can recommend for tech and non-tech people is “Patterns, Principles, and Practices of
Domain-Driven Design” by Scott Millett and Nick Tune. They covered almost all aspects
ofDDDwithexamplesanddetailedexplanations.
Thisbook
Designing_Event_Driven_Systems(1).pdf
[Scott-Millett,-Nick-Tune]-Patterns,-Principles,-a(z-lib.org).pdf
HandsonDomain-DrivenDesignwith.NET(epub,code,mobi,pdf)
9781788834094_HANDS_ON_DOMAIN_DRIVEN_DESIGN_WITH_NET_CORE.pdf
11. Building a Real-World DDD app
Aboutthischapter
WhenIfirstlaunchedsolidbook.io,Ichosetogoaboutbuildingitinawaythatitcouldbe
mostusefultoitsreaders,assoonaspossible.
Thatmeantnotwritinganexorbitantamountofcontentupfrontbutinsteadaskingreaders
tohelpmedeterminethetrajectoryofthebook.
ThemostcommonfeedbackIgotwasthatreadersaremostexcitedaboutgoingfromAto
Zontheirobject-modelingskillsandlearninghowtobuildreal-worldapplications
withDomain-DrivenDesign.
For those of you that know me, you’d know that I’m obsessed with Domain-Driven Design
andtakeanychanceIhavetochatwithyouaboutit.
That said, this is a book on software design and architecture overall - not a book solely de-
votedtoDomain-DrivenDesign(whichisatopicthattrulydeservesanentirebook’sworth
ofeffort).
Eventually,Iwouldlovetocreateanapproachableadaptationofthebluebookandthethe
redbookforJavaScriptandTypeScriptdevelopersinterestedinDDD.
I’msurereadersaresatisfiedwithsolidbook.io,I’llbeworkingoncreatingaDomain-Driven
DesignwithTypeScriptcourse.
WhileIhavenoproblemdevotingacoupleofchapterstoDDD,it’simportanttonotethat
wearechoosingaparticulararchitecturalpatterntofocusinon.
It’s essential to address this because, in Chapter 1, we learned that upon starting a new
project,ithelpstoidentifytheSQAsmosttetheredtotheproject’ssuccess,thenchoosethe
240

architecturalpatternthathasthepotentialtosatisfythoseSQAsbest.
Dependingonyourproject,thatpatternmightNOT beDDD.
However,inthischapter,IlineyouupwithaprojectthatcouldbenefitfromadoptingDDD.
IthinkthatlearningDDDisthenextlogicalstepfordeveloperscomfortablewithMVC.
ThischaptermaynotbethelastpieceofliteratureorvideoyouwatchonDDD,butitshould
atleastopenyourmindandanswerseveralquestions. Mygoalforthischapteristoprovide
youwithareallysolid,hands-on,andpracticalintroonmyfavoredapproachtosolvecomplex
softwareproblems: Domain-DrivenDesign.
Chaptergoals
Thischapterisseparatedinto5parts. Here’swhat’sahead. Wewill:
• UnderstandwhatDomain-DrivenDesignisandhowitcanaddresstheshortcomings
ofMVC.
• Conduct a crash course on the essential Domain-Driven Design concepts: entities,
value objects, aggregates, domain events, subdomains, bounded contexts, and two
popular deployment styles for DDD projects (modular monoliths & distributed mi-
croservices).
• LearnaboutDDDForum.com,thereal-worldDDDappwe’regoingtobuild.
• Learn different approaches for project planning before coding, such as imperative-
drivendesign,usecasemodeling,EventStorming,andEventModeling.
• ExploretheDDDForum.comcodebase,features,anddesignchoices.
Domain-DrivenDesign
Domain-DrivenDesignisanapproachtosoftwaredevelopment,andoneofourarchitectural
patternsmeanttoapplyagainstprojectswithlotsofbusinesslogiccomplexity.
Here’showitworks:
• Discoverthedomainmodelbyinteractingwithdomainexpertsandagreeingupona
commonlanguagetorefertoprocesses,actorsandanyotherimportantphenomenon
(likeeventsandside-effectstoevents)thatoccurinyourproblemdomain.
• Take those newly discovered terms and embed them in the code, creating a rich do-
mainmodelthatreflectstheactualliving,breathingbusinessanditsrules.
• Protectthat(zero-dependency)domainmodelfromalltheothertechnicalintricacies
involvedincreatingawebapplication(likedatabases,webservers,etc)
• Continuously crunch domain knowledge into a software implementation of that
knowledge.
You may first wish to read Chapter 5 - Object-Oriented Programming and Domain Mod-
eling, a chapter that’s in equal part theory and hands-on coding where we learn to write
object-orientedcodetocreatedomainmodels.
Domainmodel: Adomainmodelisadeclarativelayerofcode(withoutdependenciestoany
upper-layerconcerns)thatencapsulatesthebusinessrulesofaparticularproblemdomain.
241

Youcouldsaythatadomainmodelisthesolutionspacetoaproblemdomain.
Domainmodelsarecentraltothearchitecture,holdthehighest-levelpolicy,arethemost
stable(sinceadrasticchangeofthedomainmodelwouldmeanadrasticchangeofthebusi-
nessitself-whichisunlikely),andmaynotrelyonanythingfromalayeraboveit,yet-
canbyreliedonbyanyupperlayer(suchasapplication,adapter,andinfra).
Fundamentally, if you can understand the problem domain (your real-life business), you
cancreatethesoftwareversionofit.
UbiquitousLanguage
TheUbiquitousLanguage(whichisafancyDDDtermforthecommonlanguagethatbest
describes the domain model concepts) is learned through conversation with domain ex-
perts.
Tocommunicateeffectively,thecodemustbebasedonthesamelanguageused
towritetherequirements-thesamelanguagethatdevelopersspeakwitheachother
andthedomainexperts-EricEvans
Once the common language is established and agreed upon, we use those words as our
classes,usecasesandtypesinthecode.
Itisnotthedomainexpert’sknowledgethatgoestoproduction,itistheassumption
ofthedevelopersthatgoestoproduction-AlbertoBrandolini
ImplementingDDD&ensuringdomainmodelpurity
Protectingourdomainmodelandkeepingitpureisgoingtotakesomehardworkandrely
heavilyonouruseof:
• aLayeredArchitecture,mostformallyunderstoodasthecleanarchitecture(forsepa-
rationofconcerns)
• DependencyInversion(tokeepinnerlayercodetestable)
• TheDependencyRule(toenforcetheuseofDependencyInversion)
242

Alayeredarchitecture(alsoknownasthe“cleanarchitecture”)iscriticaltooursuccessto-
wardskeepingourdomainmodelpure.
DDDaddressestheshortcomingsofMVC
YoumightrememberthatIstartedthisbookwithastoryabouthowIfailedajobinterview
whenIwasaskedthequestion,“howwouldyoudesignyourbusiness-logiclayer”?
Letmeturnitaroundtoyou.
Howwouldyoudesignyourbusiness-logiclayer?
Ifyou’veneverworkedonthebackendofachallengingenterpriseapplication, youmight
nothavetheanswertothatquestion,likeIdidn’t.
Maybeyoudon’tunderstandthequestionlikeIdidn’t.
Here’swhatImean: considerwe’reworkingonaVinyl-Tradingapplicationbuiltusingthe
popularModel-View-Controllerpattern.
Let’s say that we need to implement some sort of Role-based Access Management. We
want to restrict who has to access to what. Let’s say that Traders can only view their own
Vinyl,whileAdminscanvieweveryone’sVinyl.
Ifwe’rethinkingaboutbuildingthisappAPI-first,oneofourgoalsmighttosetupretriev-
ingVinylbyid.
Wheredothebusinessrulesgo?
Howdoweenforce:
• TradersonlybeingabletoviewtheirownVinyl
243

Adminsbeingabletoviewanyone’sVinyl
•
Intheview? No,we’renotsupposedtoputbusinesslogicintheview. Weknowthat.
Inthecontroller? No.That’sjustsupposedtohandleHTTPrequestsandpassoffexecution
tosomethingelse.
| Inthemodel? | Theanswerisactually“yes”. |     | Buthow? |
| ----------- | ------------------------- | --- | ------- |
Slim(Logic-less)Models
Ifyou’veworkedwithJavaScriptorTypeScriptoverthelast4yearsinafull-stackcapacity,
youmightbefamiliarwithatleastoneNode.jsORM(object-relationalmapper).
ORM (Object-relational mapper): An ORM is a technique(though some people refer to
the library that performs this technique as an ORM) to query and manipulate data from
adatabaseusingobject-orientedprogrammingconcepts. Somewell-knownORMsinthe
Node.jsworldareSequelizeandTypeORM.
Earlyusageofthesetoolsyieldsveryslimmodels. They’rejustdefinitionfiles. Theirsolepur-
poseistodefineaschemaandrelationshipsbetweeneachothersothattheobject-relational
mappercandosomeobject-relationalmappingmagictomaptorealtablesinadatabase.
Forexample,usingtheSequelizeORM,definingaUsermodelisdonelikethis:
// models/user.js
| // Sequelize                    | ORM's way     | of creating | a User model. |
| ------------------------------- | ------------- | ----------- | ------------- |
| module.exports                  | = (sequelize, | type)       | => {          |
| return sequelize.define('user', |               |             | {             |
| id: {                           |               |             |               |
| type:                           | type.INTEGER, |             |               |
| primaryKey:                     | true,         |             |               |
| autoIncrement:                  |               | true        |               |
},
| firstName: | {            |     |     |
| ---------- | ------------ | --- | --- |
| type:      | type.STRING, |     |     |
| allowNull: | true,        |     |     |
},
| lastName:  | {            |     |     |
| ---------- | ------------ | --- | --- |
| type:      | type.STRING, |     |     |
| allowNull: | true,        |     |     |
},
| age: {     |               |     |     |
| ---------- | ------------- | --- | --- |
| type:      | type.INTEGER, |     |     |
| allowNull: | true          |     |     |
}
})
}
Similarly,usingTypeORM,wecandefinethesameUsermodellikethis:
244

// models/user.ts
// TypeORM's way of creating a User model.
import { Entity, PrimaryGeneratedColumn, Column } from "typeorm";
@Entity()
export class User {
@PrimaryGeneratedColumn()
id: number;
@Column()
firstName: string;
@Column()
lastName: string;
@Column()
age: number;
}
Just about anyone can build a simple CRUD-based MVC app today. Within a minimal
Node.js + Express.js + Sequelize (or TypeORM) stack, everyone knows that the ORM
handlesthemodel.
Whilethisisanexcellentapproachinordertolaunchaprototypequickly,it’snotsuitable
forambitiousprojects.
TheproblemliesinthefactthattheMinMVCisresponsiblefortoomuch,whiletools
likeSequelizeandTypeORMmakesitseemliketheMisonlyresponsiblefortheshapeof
thedata.
Whatdodevelopersusuallydointhissituation?
Pickyourobject-modelingpoison
In my experience, this mismatch between the responsibility of the model in MVC and
beginner-level tutorial code that promotes slim ORM models makes it hard to interpret
wherebusinesslogicshouldgo.
Thisconfusionmanifestsasoneofthree(poisonous)options:
A:Controllerholdsbusinesslogic-Putbusinesslogicinthecontroller(whichisn’tcor-
rect) and is only an option because putting it in the Sequelize ORM model is going to feel
dirty.
B:Startcreating“services”toholdbusinesslogic-It’sacommon(dangerous)thought
pattern that anything that doesn’t naturally fit within the confines of three constructs of
MVC(model,view,controller),isaservice. Youmightfindthatthisapproachisaquicktrain
245

ridetowritingcodethathasnoSingleResponsibilityandcontributestowardsaturning
acodebaseintoanAnemicDomainModel,whichisasbadasitsounds.
C: Do the dirty thing and put the business logic inside the Sequelize ORM model as
aninstancemethod. Idon’trecommendthis. Sequelizeisaninfrastructure-layerconcern
andneedsanactivedatabaseconnectiontouse. Puttingthedomainlogicheremeansthat
anyunittestsrelyingonSequelizewillbeslowsincewehavetoaccountfortheadditional
overhead of setting up and tearing down tables and connections. This is not clean at all.
Sequelize (and any other infrastructure-layer technology) should be far from the domain
logic.
Choosingoneoftheseoptionsisproblematic,indeed.Noneofthemaregreat,yetthepoison
mostdeveloperspickisA:controllersholdthelogic.
Let’sentertainthat:
// modules/vinyl/controller.ts
| class       | VinylController |          |           | {    |     |     |     |     |     |
| ----------- | --------------- | -------- | --------- | ---- | --- | --- | --- | --- | --- |
| constructor |                 | (models: |           | any) | {   |     |     |     |     |
| this.models |                 |          | = models; |      |     |     |     |     |     |
}
| //  | HTTP | GET /vinyl/:vinylId |     |     |     |     |     |     |     |
| --- | ---- | ------------------- | --- | --- | --- | --- | --- | --- | --- |
async getVinylById (req: express.Request, res: express.Response) {
| const |          | { userId  | }         | = req.decoded   |     | as            | DecodedExpressRequest; |     |     |
| ----- | -------- | --------- | --------- | --------------- | --- | ------------- | ---------------------- | --- | --- |
| const |          | { Vinyl,  | User,     | Trader          | }   | = this.models |                        |     |     |
| const |          | { vinylId |           | } = req.params; |     |               |                        |     |     |
| //    | Get      | trader    | and       | associated      |     | user.         |                        |     |     |
| const |          | user      | = await   | User.findOne({  |     |               |                        |     |     |
|       | where:   | {         | user_id:  | userId          | },  |               |                        |     |     |
|       | include: |           | [{ model: | Trader,         |     | as: 'Trader'  |                        | }]  |     |
});
| const |                | isUserAdmin         |             | = user.Trader.is_admin; |           |            |                 |                 |           |
| ----- | -------------- | ------------------- | ----------- | ----------------------- | --------- | ---------- | --------------- | --------------- | --------- |
| //    | If             | the user            | isn't       | an                      | admin,    | then       | we              | have to confirm | this      |
| //    | vinyl          | is                  | owned       | by the                  | person    | requesting |                 | it.             |           |
| if    | (!isUserAdmin) |                     |             | {                       |           |            |                 |                 |           |
|       | const          | traderThatOwnsVinyl |             |                         | =         | await      | Vinyl.findOne({ |                 |           |
|       | where:         |                     | { vinyl_id: | vinylId                 |           | },         |                 |                 |           |
|       | include:       |                     | [           |                         |           |            |                 |                 |           |
|       |                | { model:            | Trader,     | as:                     | 'Trader', |            | where:          | { user_id:      | userId }} |
]
});
246

const traderExists = !!traderThatOwnsVinyl === true;
if (!traderExists) {
return res.status(403).json({
message: "You don't have access to this"
})
}
}
// Otherwise continue
const vinyl = await Vinyl.findOne({ where: { vinyl_id: vinylId }});
// Return the vinyl (raw)
return res.status(200).json({ vinyl })
}
}
WhatIjustpresentedishowIwrotecodeforalongtime. Unfortunately,thereareseveral
drawbacks:
• Ihavetorepeatedlywritethispermissionlogicineverysinglecontrollertoseeif
Ihaveaccesstoaresource.
• We’rereturningtheentirevinylobjectraw. Ifweweretoaddorchangeacolumnon
themodel,we’vepotentiallybrokensomeone’scodethatreliedonthisfromtheAPI
response.
• Nowhere in the code do we represent the concept of Role in this supposedly role-
based access management. The concept of a Role is not explicitly expressed; it’s ex-
pressedinadvertentlythroughtheabsenceorpresenceofadatabaserow. Thatkindof
beating-around-the-bushmakesitprettychallengingtofollowalongunderstandthe
domainlogicquickly. Itactuallyforcesreaderstoreadbetweenthelines. Thecode
shouldreadlikeabook,anditdoesn’t.
• AlthoughInamedvariablesexpressively,low-leveldetails(Sequelize-data-access
logic) are mixed in with high-level rules (role-based access control). This is a bad
separationofconcerns.
Thesearelegitimateproblemsthatneedtobesolved. Andwe’llsolve‘em. That’swhy“how
wouldyoudesignyourbusiness-logiclayer”issuchanexcellentquestion.
Infact,ifIweretostartinterviewingcandidatesforafull-stacksoftwaredevelopmentrole,
that might be one of the next questions I ask after “can you remember the 4 principles of
Object-OrientedProgramming?”.
“How would you design your business-logic layer” is a good litmus test for the scope of
projectsadeveloperhaspreviouslyworkedon.
Whenaskedthatquestion,mostofthetime,developerswhohavea)neverhadthechance
toworkoncomplexfull-stackapplications,orb)didn’tknowhowtoaddressagrowingap-
plication’scomplexneeds,tendtodescribeMVC.
247

ConcernsoftheunspecifiedlayerinMVC
MVCisanexcellentstartingpointforalotofsimpleCRUDapps,butcomplexonesstrug-
gletokeepthingsundercontrolbecausetheMinMVCisresponsiblefortoomanythings,
and there’s no simple framework to tell us how we should approach designing our
models.
Let’ssayweextractedallofthataccess-controllogictosomemiddlewarefunctions.
WhereintheModel(M)dowehandlethesethings?:
• validationlogic
• invariantrules
• domainevents
• usecases
• complexqueries
• andbusinesslogic
Thosearealotofconcernstojustorganizeintofunctionsandmiddleware.
Leavingallthatimportantstufftointerpretationtendstofinditplacedinsomeunspecified
layerbetween(whatshouldbearich)modelandthecontroller.
ThemissinglayerinMVC.
Misguidedly trying to fit all these important concerns somewhere in between a thin
model and the controller can feel like trying to push a square-shaped block inside a
triangle-shapedone.
Thatmysterylayercontainstheheartofoursoftware. It’sthefamilyjewels. It’sthemoney-
maker. It’sthestuffthatwecan’tjustcopyandpasteoroutsource. It’sthemostchallenging
andrewardinglayertomaster. Itaccountsfor90%ofthecodeweactuallywrite.
248

That’sourdomainmodel.
ThemissinglayerinMVCisactuallyourdomainmodel,whichcontainsthebusinessrules
ofourdomain.
Andit’swhatweshouldspendmostofourtimethinkingabouthowtodesigneffectively.
Undesirableside-effectswithalackofadomainmodel
Ifwefailtorecognizewhenweneedadomainmodel,we’llrunintoproblemslike:
• Missingabstractionsandfosteringabreedinggroundofduplicatecode
• Writinguntestableandtightly-coupledclasses
• Usingcomputer-ysoundingclassnameslike‘handlers’,‘factories’,‘managers’,‘inter-
actors’,makingcomprehensionarealchallengeforanyoneelseotherthantheorigi-
nalauthor
• Notcaringabouttheactualproblemdomainandwritingcodethatdoesn’texpressthe
real-worldproblemsitsolves
Modelbehaviorandshape
Ultimately, the model is responsible for both the behavior and the shape of our
data,wherethebehaviorismuchmorechallengingtodiscoverandrepresent.
Andwithoutadomainmodel,it’sfrequentthatbehaviorisanafterthought.
InHowtoplananewproject,weintroduceapproachestostartaprojectbyfirstidentifying
thebehavior,avertingoutfocustotheshapeofthedatasecond.
TechnicalBenefits
TherearehugepayoffstoDDDanddomainmodeling. Whenourcodelinesupwiththereal-
lifedomain,weendupwitharichdeclarativedesignthatenablesustomakechangesand
addnewfeaturesexponentiallyfaster.
ProjectsthatadoptDDDcanexpectthefollowingtechnicalbenefits:
• Testablebusiness-layerlogic
• Lesstimefixingbugs
• Acodebasethatthatimprovesratherthandegradesovertimeascodegetsaddedtoit
249

• Longlife-spans
TechnicalDrawbacks
Domainmodelingistime-consuming,takesrepeatedeffort,andcanbechallenging.
Depending on the project, it might be more worthwhile to continue building an Anemic
DomainModel.
ChoosingDDDcoincideswithalotoftheargumentsImadeforwhenit’srighttouseType-
ScriptoverJavaScriptforyourproject. UseDDDfor#3ofthe3HardSoftwareProblems: The
ComplexDomainProblem.
AlternativestoDDD
Thereareonlytwoapproaches. EitheryouwriteaTransactionScript, oryouwriteadomain
model.
Ifyou’veneverwrittenadomainmodel,you’vebeenwritingTransactionScripts.
Transactionscript: Thesimplestwaytoorganizedomainlogicaspossible. By
usingsimpleif andelsecontrolstatements,wecanexpressdomainlogiceasily.
Thisisperfectforsimpleapplicationswithoutahugeamountofdomainlogic
thatcandowithoutalotoftimespentonarchitecture.
TransactionScripts are great for simple CRUD apps but for applications where the problem
domainiscomplex,weneedtobreakdownthe“model”partevenfurther.
Todothat,weusethebuildingblocksofDDD.
DDDBuildingBlocks
Verybriefly,thesearethemaintechnicalartifactsinvolvedinimplementingDDD.
250

Entities
Domainobjectsthatwecaretouniquelyidentify.
Thingslike: User,Job,Vinyl,Post,Comment,etc.
Entitieslivealifeenablingthemtobecreated,updated,persisted,retrievedfromper-
sistence,archived,anddeleted.
Entitiesarecomparedbytheiruniqueidentifier(usuallyaUUIDorPrimaryKeyofsome
sort).
251

| Resource: | ReadthisarticleaboutEntities. |     |     |
| --------- | ----------------------------- | --- | --- |
ValueObjects
| Valueobjectshavenoidentity. |     | TheyareattributesofEntities. |     |
| --------------------------- | --- | ---------------------------- | --- |
Think:
• NameasaValueObjectonaUser.
• JobStatusasaValueObjectonJob
• PostTitleasaValueObjectonPost
// A valid (yet not very efficient) way to compare Value Objects
const khalilName = { firstName: 'Khalil', lastName: 'Stemmler' };
| const nick             | = { firstName: | 'Nick', lastName:        | 'Cave' } |
| ---------------------- | -------------- | ------------------------ | -------- |
| JSON.stringify(khalil) |                | === JSON.stringify(nick) | // false |
ValueObjectsarecomparedbytheirstructuralequality.
| Resource: | ReadthisarticleaboutValueObjects. |     |     |
| --------- | --------------------------------- | --- | --- |
Aggregates
Anaggregateisacollectionofentitiesboundtogetherbyanaggregateroot. Theaggregate
rootisthethingthatwerefertoforlookups. Nomembersfromwithintheaggregatebound-
arycanbereferredtodirectlyfromanythingexternaltotheaggregate. Thisishowtheag-
gregatemaintainsconsistency.
252

Everytransactionthathappensinourapphappensagainstanaggregate-andit’stheag-
gregatethatprotectsagainstclassinvariants.
ThemostpowerfulpartaboutaggregatesisthattheydispatchDomainEvents,which
can be used to decouple sequences of business logic so that they can be handled from the
appropriatesubdomain. ****
Resource: ReadthisarticleaboutAggregates.
DomainServices
Thisiswherewelocatedomainlogicthatdoesn’tbelongtoanyoneobjectconceptually.
Domain Services are most often executed by application layer Application Services / Use
Cases. BecauseDomainServicesareapartoftheDomainLayerandadheretotheDepen-
dencyrule,theyaren’tallowedtodependoninfrastructurelayerconcernslikeRepositories
togetaccesstothedomainentitiesthattheyinteractwith. ApplicationServicesfetchthe
necessaryentities,thenpassthemtoDomainServicestorunallowthemtointeract.
CheckoutPostService.ts,aDomainServicefromDDDForum.com,theappweexplorelater
inthischapter.
Repositories
Weuserepositoriesinordertoretrievedomainobjectsfrompersistencetechnologies. Us-
ingsoftwaredesignprinciplesliketheLiskovSubstitutionPrincipleandlayeredarchitec-
ture,wecandesignthisinawaysothatwecaneasilymakearchitecturedecisionstoswitch
between an in-memory repository for testing, a MySQL implementation for today, and a
MongoDBbasedimplementation2yearsfromnow.
Resource: Readthisarticleaboutimplementingtherepositorypattern.
Factories
We’llwanttocreatedomainobjectsinmanydifferentways. Wemaptodomainobjectsus-
ingafactorythatoperatesonrawSQLrows,rawjson,ortheActiveRecordthat’sreturned
fromyourORMtool(likeSequelizeorTypeORM).
Wemightalsowanttocreatedomainobjectsfromtemplatesusingtheprototypepatternor
throughtheuseofanabstractfactory.
Resource: ReadthisarticleaboutStaticFactoryMethods.
DomainEvents
ThebestpartofDomain-DrivenDesign.
Domaineventsaresimplyobjectsthatdefinesomesortof eventthatoccursinthedomain
thatdomainexpertscareabout.
Typicallywhenwe’redealingwithCRUDapps,weaddnewdomainlogicthatwe’veiden-
tifiedbyaddingmoreif/elsestatements.
253

However,incomplexapplicationsthatcanbecomeverycumbersome(thinkGitlaborNet-
flix).
UsingDomainEvents,insteadofaddingmoreandmoreif/elseblockslikethis:
// userController.ts
// Example of handling domain logic (transaction script-style).
| class  | UserController |     | extends | BaseController |     |     | {   |
| ------ | -------------- | --- | ------- | -------------- | --- | --- | --- |
| public | createUser     |     | () {    |                |     |     |     |
...
|     | await                               | User.save(user);            |              |          |           |         |       |
| --- | ----------------------------------- | --------------------------- | ------------ | -------- | --------- | ------- | ----- |
|     | // After                            | creating                    | a            | user,    | we handle |         | both: |
|     | // 1.                               | Recording                   | a referral   |          | (if       | one was | made) |
|     | if (user.referred_by_referral_code) |                             |              |          |           |         | {     |
|     | // calculate                        |                             | payouts      |          |           |         |       |
|     | // ..                               | there                       | could        | be a lot | more      | logic   | here  |
|     | await                               | Referral.create({           |              |          |           |         |       |
|     | code:                               | this.req.body.referralCode, |              |          |           |         |       |
|     | user_id:                            |                             | user.user_id |          |           |         |       |
});
}
|     | // 2. | Sending | an email | verification |     |     | email |
| --- | ----- | ------- | -------- | ------------ | --- | --- | ----- |
EmailToken.createToken();
await EmailService.sendEmailVerificationEmail(user.user_email);
// mind you, neither of these 2 additonal things that need to get
// done are particularly the responsibility of the "user" subdomain
this.ok();
}
}
Wecanachievesomethingbeautifullikethis:
254

Usingdomainservicesandapplicationservices,DomainEventsareanexcellentwayto
separate concerns and decouple domain logic across DDD boundaries known as Subdomains
andBoundedContexts(readon).
Resource: ForaresourcesonusingDomainEventstodecoupledomainlogic,readthe“De-
couplingLogicwithDomainEvents”guideand“WhereDoDomainEventsGetCreated?”.
Architecturalconcepts
ThetwomostimportantarchitecturalconceptstograspinDomain-DrivenDesignaresub-
domainsandboundedcontexts. Subdomainsareaboutcreatinglogicalboundariesand
boundedcontextsareaboutcreatingphysicalones.
Subdomains
255

In DDD, a Subdomain is a smaller piece (logical boundary) within the entire problem
space.
A Subdomain is a smaller piece (logical boundary) within the entire problem
space.
What’saproblemspace?
Theproblemspaceistheentiretyofthethingsthatabusinessisfacedwithsolving.
Forexample,WhiteLabel,theappI’mworkingonfortheupcomingDDDwithTypeScript
course,isabouttradingvinyl.
Unfortunately,tradingvinylisn’ttheonlythingthatneedsmyattention.There’smuchmore
thatneedstobeaccountedfor.
In addition to the trading aspect (Trading), the enterprise also has to account for several
other concerns: identity and access management (Users), cataloging items (Catalog),
billing(Billing),notifications(Notifications)andmore.
Eachoftheseconcernsaresubdomains;decomposedlogicalslicesoftheentireproblemdo-
main.
4subdomains: Billing,Trading,Catalog,andUsersfromavinyl-tradingenterprise.
EachoneoftheseSubdomainstakesresponsibilityoveracertainsetofproblemstobesolved
inthebusiness. Youcansaythatcertainproblemsaremoreappropriatetobesolvedwithin
the context of the Billing Subdomain (like making payments and creating subscriptions)
whileothersaremoreappropriatetohandofftotheUsersSubdomain(likeverifyinganac-
countorresettingapassword).
Not only can we horizontally assign problems throughout our business to the appropriate
subdomain,butwecanverticallyseparatetheconcernsforeachsubdomainbyeachoneim-
plementingitsveryownlayered(clean)architecture.
256

Typesofsubdomains
InEricEvans’DDDbook,heprovidesuswithafewquestionswecanasktodeterminewhich
partsofoursystemarecoretothedomain. Thequestionsare:
1. Whatmakesthesystemworthwriting?
2. Whynotbuyitofftheshelf?
3. Whynotoutsourceit?
If this subdomain is something that doesn’t yet exist and we can’t just buy it off the shelf,
thenitmightbeworthwriting.
Ifaccesstothedomainknowledgeisnotreadilyavailableandsomehow,we’reinpossession
ofthatknowledgeandconcernedaboutbuildingasystemarounditproperly,wemightnot
wanttooutsourceit.
Ifweknowthatwecanmakelotsofmoneyorhelplotsofpeoplewithit, thenthatmight
reallymakethesystemworthwriting.
Genericsubdomains
Thesearethingsthataren’tcoretothebusiness. Yes,justabouteverySaaSapplicationneeds
tofigureoutidentity&authentication,butthoughthat’simportant,it’slikelythatit’snot
thecriticalbusinessproblemsthatwe’refocusedonaddressing. Wecouldoutsourcethatjob
tosomeonelikeAuth0.
In White Label, the generic subdomains are: Users, Notifications, and Billing. That
meansthereshouldbenoexplicitmentionofanythingregardingvinyl,traders,artists,
oranythingelsefromthecoreorsupportingsubdomains.
Supportingsubdomains
257

Thesearepartsoftheapplicationthatarestillnecessaryinhelpingthebusinessdowhatit
does, yetwecannotjustoutsourceittosomeotherservicebecauseit’sspecifictothedomain. In
WhiteLabel, there’sasubdomainresponsibleforShippingvinyl. Shippingisn’tthemost
importantthingtous,butshippingisanecessarypartofthebusiness-though,evenifnot
thecore.
Thesupportingsubdomainsare: Shipping(arguably)andCatalog.
Coresubdomain(s)
Thecoredomainisthemainthingthatwe’refocusedon. InWhiteLabel,that’stradingvinyl:
thetradingsubdomain. Evanssaysthat“theCoredomainshoulddeliverabout20%ofthe
total value of the entire system, be about 5% of the code base, and take about 80% of the
effort”.
Benefitsofusingsubdomains
Themainbenefitsofenforcingboundarieswithinyourapplicationisthatthey:
• a) Helptopreventdomainconceptsfromothersubdomainsbleedingintoyourcore
one.
• b) Sometimes we refer to the same concept but from different contexts; subdo-
mainsactasasafespacetorepresentitinthewaythatmakesthemostsenseper
context(eg: CustomerandRecipientareequivalentconceptsbuthavedifferent
responsibilitiesdependingontheSubdomain).
• b) Properlypreparesyouforscale.
Therearetwomeaningstoscale,atleasthowImean:
1. Scalingthesizeofyourteamandyourdesiretobeabletodelegateownershipofsub-
domainstoateam.
2. Scalingasthetrafficdemandsinyourapplicationhavegrown,andyounowneedto
deploymoreinstances(orsplicetheapplicationinsomeway)tokeepupwithtraffic.
Logicallyorganizingcodeintosubdomainsisthefirststep,BoundedContextsarewhatweac-
tuallydeploy.
BoundedContexts
A Bounded Context is another logical boundary like Subdomains, but this time, it’s a logical
boundary around all of the subdomains needed in order for an application achieve
itsgoals.
ABoundedContextisalogicalboundaryaroundallofthesubdomainsneededin
orderforanapplicationtoachieveitsgoals.
SomesaythattheBoundedContext isthesolutionspacetotheproblemspace. Technically, this
checksoutbecausewe’reidentifyingtheactualblocksnecessarytoaddresstheproblemdo-
main.
Let’slookatanexample.
258

AssumeDDDForum.comwassuccessfulenoughthattheCEOdecidedtheywantedtoadd
severalnewservicestotheiralreadyverysuccessfulenterprise.
Dependingonthefeaturesthateachserviceneedsandtheproblemsthateachexistingsub-
domainiscapableofaddressing,itmightmeanweneedtoaddmoresubdomainstoaddress
newproblems.
HereareacoupleideasthattheCEOcameupwith:
DDDDating(dating,users,location,billing,notifications): “Loveisubiquitous. Find
yourdomain-modelingmatchtoday”.
Thiscouldbeadatingappforanyonetofinddateswithpeoplenearthem. Theappwould
bepartoftheDDD-appsenterprise,butwemightneedsomenewsubdomain. Forexample,
there’safeaturethatenablesyoutomeetwithpeoplenearby,solocation-tracking,comput-
ingdistances,etc-mightbeapartofthealocationsubdomain. Aswell,thiswouldlikely
bepaidapplication,sowe’llneedtofigureoutthebillingsubdomainaswellasthedating
subdomaintoholdalldataandoperationsspecifictothisapplicationitself.
DDDMeetups (meetups, users, location, billing, notifications): “Find a local DDD
Meetupnearyou”.
You know Meetup.com? We could create our own version of that strictly for the DDD
community. Forthoseof youwho aren’tfamiliarwith Meetup.com, it’s aplatformwhere
you can organize and find meetups based on things you’re interested in. In order to
build this, we’d definitely need a new meetups subdomain in addition to billing if we
wanted to charge event promoters to create events and notifications in order to send
announcementstopeoplebeforeevents.
DDDMerch(merch,inventory,billing,notifications,shipping,users): “Findallyour
value objects here on the world’s first e-commerce store dedicated to everything DDD! (so
nerdy,Iknow)”
DDDMerch.comcouldbeane-commercewebsitesellingt-shirts,sweaters,stickers,mugs,
etc. Ifwebuiltthisentirethingfromscratch,we’dneedamerchsubdomain(whichmight
justbeaCMSlikeContentful)inordertochangethetext,promotions,andsetcouponsfor
thesite,aninventorysubdomaintokeeptrackofalltheitemsinstock,andalsoashipping
subdomaintohandletrackingpackages.
In order to create the solution space for each of our new Bounded Contexts, we can utilize
several existing Subdomains that already solve those problems for us, while introducing a
fewnewones(suchasdating,meetups,andmerch)tosolveproblemsnotyetaddressed.
259

AllsubdomainsintheDDD-appsenterprise.
Take note that not every Subdomain is required for each BoundedContext. For example, the
onlyBoundedContextthatneedsaccesstotheshippingsubdomainisDDDMerch.
WecoulddrawtheboundaryaroundallofthesubdomainsneededtopowerDDDMerch.
260

BoundedcontextforDDDMerch—it’sacollectionofseveralsubdomains!Thesubdomains
neededinordertoachieveitsgoalsarecircled.
InthecontextofDDDMerch,theproblemspaceisthatwe“needtosellmerch,putonpromo-
tions,shipitems,andtrackthem”. Thesubdomainshelptorealizethesolution.
Somesubdomainsmakesensetouse,andsomeofthemdon’t. There’snoreasonforusto
needtousethelocation,meetup,dating,orforumsubdomainsforDDDMerch.
Aswecontinue,werealizethatwehaveoptionsforrealizingourBoundedContexts:Modular
MonolithsandDistributedMicro-services.
DeploymentasaModularMonolith
Notallapplicationsstartoutasmicro-services. Manyofthemstartaswell-organizedMod-
ularMonolithsuntiltheyreachthecriticalmassatwhichitmakessenseforustobreakinto
separateteamsanddeployments.
Ifweweretostartbuildinganapplicationasamonolith,atthefolderlevel,wecouldenforce
thoseSubdomainboundariesbyusingthepackagebymoduleprinciple.
261

src
| modules | # All | subdomains |     |     |     |     |
| ------- | ----- | ---------- | --- | --- | --- | --- |
...
| billing       | # Customers, | subscriptions,      |             |             | invoices, | etc      |
| ------------- | ------------ | ------------------- | ----------- | ----------- | --------- | -------- |
| catalog       | # Vinyl,     | artists,            | albums,     |             | etc       |          |
| notifications | # Email,     | push notifications, |             |             | slack     | webhooks |
| shipping      | # Shipments, | tracking,           |             | routes,     | etc       |          |
| trading       | # Trades,    | offers,             | reputation, |             | etc       |          |
| user          | # Users,     | passwords,          |             | jwt, roles, | groups,   | etc      |
Inthiscase,eachoftheSubdomainsforaoneBoundedContextlivewithinasingledeployable
unit.
ModularMonolith: asingleBoundedContextwithseveralSubdomainswithinit.
IfwemodeledoneoftheDDD-appslikeDDDMerchasaModularMonolith,whatdowedo
whenwewanttoalsodeployanotheronelikeDDDDatingorDDDForum?
What should we do if we want to utilize common Subdomains across Bounded Contexts
(DDDDating, DDDForum both need users and notifications for example) but we don’t
wanttomaintainduplicateSubdomaincodeacrossseparateBoundedContexts? How
doweavoidthisturningintoadevelopmentnightmare?
ThiswouldbeanappropriatetimetoinvestigateDistributedMicro-services.
DeploymentasDistributedMicro-services
262

Asteamsizesandtrafficneedsgrow,andasmoreapplicationsenterourenterprise,wecan
breakSubdomainsintotheirownBoundedContextssothattheycanbemanagedanddeployed
asDistributedMicro-services.
A generalization of a microservice architecture where each microservice is a subdomain
fromourproblemdomain.
There’s a considerable amount of complexity added here, and that’s not to be underesti-
mated. As long as we have networking and ops under control, teams can be assigned to
manage a bounded context and integrate with others. This event-driven architecture is ar-
guablythebestwaytoscaleanapplication—bothintermsofcodesize&complexity,and
traffic.
Designprinciple: “Striveforloosecouplingbetweenobjectsthatinteract.”
Implementingtheonedatabaseperservicepattern,wecanthinkofourenterpriseasaplat-
formtobebuiltontopof. Thisremovestheneedtoperformduplicateworkandalterthe
existingplatform,andshiftstheworktowardssimplyintegratingwiththeexistingservices.
Note: If we can utilize our existing architecture to build new applications on top of it with
minimal-to-zeromodificationnecessary,thatmeanswe’reapplyingtheOpen-Closedprinci-
plearchitecturally. Thatrightthereisabeautifulthing.
Howtoplananewproject
I love the process of starting a new project. After having come up with a plan, I get this
efficiencyandconfidenceboost,knowingthatI’mnotgoingtorunintoanyhugesurprises,
anditfeelsempoweringtoknowhowIplantousemyenergyinatargetedway.
Unfortunately, at least for me- I wasn’t taught suitable formal methods for planning
projects. Sure,IlearnedaboutUMLUseCaseandClassDiagrams,buttheyweren’tuseful
ineveryscenario. Theyfeltquiteceremoniousformanyprojects.
As many readers of this book, I began with questions like “what’s the best way to plan a
project? Should you start from the database and go upwards in the stack? Should I start
withtheAPIfirst? MaybetheUIfirst”?
263

Ieventuallydevelopedapersonalpreferenceandstucktoitforyears. Thatgotmethrough
alotofrelativelysimpleprojectsforquitesometime.
Butitwasn’tuntilIstartedworkinginateamsettingonlarge,complex,challengingprojects
in domains that I knew nothing about that I realized I needed a different approach towards
projectplanning.
Withthepressuretodeliverworkingcodewithinstricttimeframes,it’snotuncommonthat
you’llencounterpushbacktoshortendeadlinesandeliminateupfrontdesign. Becausewe
knowthattheearlydesigneffortsinaprojecthavethepotentialtoinfluenceitssuccessor
failure,weshouldneversacrifice.
In this section, I’ll cover several conventional approaches to project planning, how to
identify which approach would be most appropriate given the context, and two practical
domain-drivenapproachesforprojectplanning.
We’lllearnabout:
• Imperative design processes (Database-first, API-first, and UI-first) benefits and
drawbacks.
• Projectdimensionsthatinfluencewhichdesignapproachmakesthemostsense.
• Planningaprojectusingtraditionalusecasedesign.
• HowidentifyingrolesandapplyingboundariesusingConway’sLawcanhelptocreate
high-qualitydesigns.
• Event Storming: a design technique (focused more on the problem domain rather
thanthetechnology)thatinvolvesbothDomainExpertsanddevelopers.
• EventModeling:anemergingtechniquesimilartoEventStormingthatcombinesand
buildsuponthebestofover40yearsofdesignprinciplesandbestpractices.
Imperativedesign
Atrivialandimperativewaytoplanoutthedevelopmentofaprojectistosingleoutoneof
thetechnologiesrequiredtocompletetheprojectandcodethatoutfirst. Components
involvedinbuildingwebappsusingMVCare:
Database+RESTfulAPI+Front-endapplication
Imperativedesignstartseitherdatabase-first,UI-first,orAPI-first.
Takingadatabase-firstapproach,ImightstartbydrawingoutallofthetablesI’msurethat
I’llneed,definingtheirrelationships,andaddingalloftheircolumns.
264

Database-firstdesignapproach. Startwiththedatabaseschema.
DoingdesignUI-firstmeansthatwestartbycreatingallofthewireframesforthesystem.
Then,wediscovertheAPIcallsweneedbasedonthefunctionalrequirementsofthecom-
ponents. Additionally,theshapeofthedatabaseisdeterminedbasedonfieldsandrelation-
shipspresentintheUI.
API-firstdesigninvolvesidentifyingandenumeratingalloftheAPIcallsup-front. Outof
all three of these approaches, I like API-first design most. I like it most because it draws
severalparallelsagainststartingwithaUseCaseDiagram. Naturally,that’sbecauseanAPI
callisausecaseandausecaseiseitheracommandORaquery. Identifyingalloftheuse
caseswouldhelpusunderstandatahigh-levelalloftheapplication-specificfunctionality
thatweneedtosupport—notabadplacetobe.
I think the name imperative design is a proper name for this style of design because the
processprioritizesmerelycompletingall3partsofMVCarchitectureinordertoproducea
working,fullycompletesystem.
265

I don’t think that’s properly guided. Quite often, central business rules get displaced and
putinplacesthattendtomakeithardtomaintainasprojectsgrowinsizeandcomplexity.
Thisimperativeapproachworkswellforcertaintypesofapplications: CRUDones.
Imperativedesignapproachesareforsmall,simpleCRUDapplications
ImperativedesignapproachesworkremarkablywellforbasicCRUD(CreateReadUpdate
&Delete)+MVCapplications.That’sbecausetheviewistheUI,thecontrolleristheAPI,andthe
modelisthedatabase.Buildingallthreecomponentsmeanswe’vecompletedthedevelopment
oftheapplication.
Codegenerators,realtimeGraphQLAPIsthattrytobetheentireMinMVC,andfull-stack
frameworksaregreatforsimpleCRUD-basedimperativeprojects.Giventhatthereareonly
threecomponentstoMVC,it’sattractivetocutcostandtimebyhavingtheframeworkdo
theentireMortheentireCforyou. ForsimpleCRUDapps,Isayhaveat’er. Foranythingelse,
proceedwithcaution.
Here’swhy.
Codequalityquicklydegradesonbusiness-logicheavyprojectsdesignedanddevelopedus-
266

ingoneoftheseimperativedesignapproaches.
Because of a lack of a domain model, codifying business rules becomes an af-
terthought. Ifbusinessrulesarepresentintheproblemspace,acommonmis-
takeistopatchduplicatelogicthroughoutcontrollercodeorineffectuallylocate
itwithinananemicserviceclass.
In a nutshell, CRUD-based apps expect the M in MVC to represent the shape of the data
simply. CRUD-basedappsfailtoaccountfordesigningthebehaviorofthedata.
Formoreonthisphenomenon,read“REST-firstdesignisImperative,DDDisDeclarative”
and“KnowingWhenCRUD&MVCIsn’tEnough”.
Dimensionsthatinfluencethedesignapproachweshouldtake
Ultimatelyspeaking,thedimensionsthataregoingtomattermostindeterminingwhether
it’sOKtodoimperativedesignare:
• Iftheprojectsizeislarge.
• Iftheprojectcomplexityishigh(wejustdiscussedthisonewithCRUDapps).
• Iftheprojectisenterprisesoftware(thisissoftwareservinganorganizationwithsev-
eralteams,whereeachteamhasasetofemployeeswithrolesthatcarryoutaspecific
set of activities- think major airports, large e-commerce giants like Amazon, or Wal-
Mart).
• Iftheteamsizeorthenumberofteamsworkingcollaborativelyontheprojectissig-
nificant.
• Ifweneedtolearntheproblemdomainfromdomainexpertsfirst.
Ifwecansafelyanswernotoallofthese,thenthere’sanexcellentchancewe’redealingwith
asimpleCRUDapp,andweshouldbefinewiththeimperativeapproach.
ExamplesofCRUDprojectsthatwe’dbeOKwithdesigningimperatively: Anadmin
dashboard to perform CRUD operations, todo apps, basic weather apps, a comment-
moderationsystem,ahomefilesharingapp,orotherhobbyistprojects.
Ifweansweryestoanyofthese,thenitstimetorollupoursleeves. It’sverylikelyweneed
totakeamoreinvolvedapproachtodesignbecausethere’salotmoreisgoingagainstusto
seetothesuccessoftheproject.
Examples of ambitious projects that require better upfront design: A vinyl-trading
application (like White Label), a large scale e-commerce website and fulfillment network
(likeAmazon.com),asource-codemanagementplatform(likeGitHub).
Let’slookatconventionalapproachestohandleplanningchallengingsoftwareprojects.
Use-casedrivendesign
Programmingcanfeellikeacreativeandnever-endingartform. WhileIthinkthere’sroom
forcreativeprogramming,inaprofessionalsetting,wewanttoknowwhatwehavetodo,
andwhatdictatesaprojectbeingfunctionallycomplete.
267

Beinguse-casedrivenisarguablythebestwaytospendnomoretimeonaprojectthanis
absolutelynecessary. Ithelpsyoumakebetterestimates,writemoredirectandintentional
code,andplanoutthetestsyou’llneedinordertomakesureyourstuffworks.
Resource: “BetterSoftwareDesignwithApplicationLayerUseCases”isanessentialread.
It’squitepossiblymyfavouriteessay. Ihighlyrecommendyoutakeareadafteryoufinish
thissection.
Usecases&actors
Ausecaseisahigh-levelwaytodocumentbehaviorthatanactorperformswithinasystem.
Anactorisaroleplayedbyeitheraspecifictypeofuserorasystemitself(automationsand
externalsystemscanbeactorstoo—thinkpaymentprocessorslikeStripe).
Forexample:
• Ifthedomainisanecommerceapplication,anactormightbeacustomer,andsome
oftheirusecasesmightbetomakePurchase,getOrders,search,andpostReview.
• Ifthedomainisaforumapplication,anactormightbeamember,andsomeoftheir
usecasesmightbetocreatePost,postComment,upvotePost,andgetPopularPosts.
AverysimpleusecasediagramofaForumsystem.
Usecasedesignisoneofthemoretraditionalapproachesofdocumentingthefunctionalre-
quirementsofasystem,usuallybeforewritinganycode.
Applicationsaregroupingsofusecases
Allapplicationscanbethoughtofasagroupingofusecases. That’sallappsareanyways.
Forexample,inasimpleTodoapp,theusecasesthattheactorneedstobeabletoaccomplish
are: create todos,edit todos,delete todos,andget todos.
Onecouldargue,“isitstillatodoappifitdoesn’thavealltheusecasesthatatodoappneedstohave”?
Whileitmightposeasalight-heartedtheoreticaldiscussionforanotherday,wecanatleast
agreethattheappisnotcompleteuntilalloftheagreed-uponusecasesarebuiltandworking.
268

Youcanseehowthisisusefulifweneedtoscopeoutaprojectforapotentialclient,creating
aproposalfortheworkwe’lldo.
This is why I love use case design. The work is done once we decide on all the use cases
and implement them. Again, since software development can be a creatively unbounded
practice,it’shelpfultohaveawaytoobjectivelyunderstandwhatthecompletionstatelooks
like.
Ausecaseisacommandoraquery
TheCQS(Command-QuerySeparation)principlefromChapter6-DesignPrinciplessays
thateveryoperationshouldbeeitheracommand(somethingthatchangesthesystem)ora
query(somethingthatreturnsdatafromthesystem),butneverboth.
Usecasesfollowthisprinciple. Insteadofdesigningausecasetoperformbothacommand
andaquerylikecreateAndReturnPost,we’densuretwoseparatecodepathsforwritesand
readsbydesigningcreatePost(thecommand)andgetPostById(thequery).
We yield the same benefits in simplicity when applying CQS at the design-level with use
casesdocumentationaswedoatthe:
• method-level: createPost(post: Post)+getPostById(postId: PostId)
• API-level: POST /post/new+GET /post/:postId
• andarchitectural-level:Postwritemodel(aggregate)+PostDetailsreadmodel(DTO
orGraphQLobjecttype)
Usecaseartifacts
Therearetwoartifactsthatyoucancreatefromdoingusecasedesign:
1. Usecasediagramsand
2. Usecasereports
Diagramshelptounderstandatahigh-levelwhatsystems,actors,andusecasesexist.
Reportsaregoodcomplimentstousecasediagrams. Theycancontainquiteabitmorede-
tailandaremostusefulwhentheydocumentthefunctionalrequirementsandhowthe
systemshouldrespondtodifferentscenarioswhenthepreconditionschange.
Functionalrequirementsdocumentbusinesslogic
Whencreatingausecasereport,wecanoutlineallofthefunctionalrequirements.These
areourusecases. Buthowdowedocumentbusinesslogicandhowthesystemshouldre-
spondinvariousscenarios?
Given-When-Then
Inadditiontodocumentingourusecaseitself,wecanutilizebothpreconditionsandpost-
conditions to provide additional context as to how the system should interact in certain
scenarios.
269

This is often enough information for developers to translate it directly into failing BDD-
styleunittests,andthenwritethecodetomakethetestspass.
LetmeshowyouwhatImeanbytranslatingaDDDForum.comrequirementspokeninplain
EnglishtoBDD-styleunittests.
“Givenanexistingpostthatthememberhasn’tyetupvoted,whentheyupvoteit,thenthe
post’sscoreshouldincreasebyone”.
| Usecasename: | UpvotePost. |     |      |     |
| ------------ | ----------- | --- | ---- | --- |
|              | Member      |     | Post |     |
Precondition(s): A exists. A created by a different member also exists. The
Memberhasn’tyetupvotedthePost.
| Postcondition(s): | ThePostsscoreincreasedbyone. |     |     |     |
| ----------------- | ---------------------------- | --- | --- | --- |
Totranslatethatintoafailingtestcase,wecouldsimplywriteenoughcodetoexpresswhat
shouldhappen(notfocusedtoomuchonthedesignatthispoint),creatinganyclassesthat
wementioninthetestcase:
| // A failing           | BDD-style    | unit test.    |           |         |
| ---------------------- | ------------ | ------------- | --------- | ------- |
| let post:              | Post;        |               |           |         |
| let member:            | Member;      |               |           |         |
| let postService:       | PostService; |               |           |         |
| let existingPostVotes: |              | PostVote[];   |           |         |
| describe("A            | post the     | member hasn't | upvoted", | () => { |
| beforeEach             | (() =>       | {             |           |         |
| post                   | = null;      |               |           |         |
| member                 | = null;      |               |           |         |
| existingPostVotes      |              | = [];         |           |         |
})
it ("When upvoted, it should upvote the post's score by one", () => {
| // Start                                  | out with              | a failing        | test.   |                |
| ----------------------------------------- | --------------------- | ---------------- | ------- | -------------- |
| let initialScore:                         |                       | number;          |         |                |
| post                                      | = Post.create(...);   |                  |         |                |
| initialScore                              | =                     | post.score;      |         |                |
| member                                    | = Member.create(...); |                  |         |                |
| postService.upvotePost(existingPostVotes, |                       |                  |         | member, post); |
| // Should                                 | fail                  | since we haven't | written | any logic yet  |
| expect(post.score).toEqual(initialScore   |                       |                  |         | + 1);          |
expect(post.votes.getNewItems().length).toEqual(1)
})
})
Thegoalfromhereonwouldbetofurtherfleshouttheclasseswithdomainlogicandcon-
tinueuntilalltestspass.
270

Asweidentifymoretestcases,weshouldaimtowritetestsforthoseaswell. Testsarehow
wecantellifourusecasesthatutilizedomainlayerentitiesarecorrect.
ParallelswithAPI-firstdesign
Usecaseshelpusunderstandwhatneedstohappenatthebusinesslevel,whichiswhyIthink
API-firstdesignisadequateformostscenarios.
WithAPI-firstdesign,we’retechnicallydiscoveringalltheusecases. They’rejustmasquer-
adedasAPIcalls. IfyouweretodoaAPI-firstdesign, functionally, you’redoinguse-case
drivendesignsolongasyouincludeGiven-When-ThentestcasesforeachAPIcallinyour
planning.
Stepstoimplementusecasedesign
Ifyouwanttousethisapproachonyournextproject,here’sareafewstepsthatIuse. Here’s
howtogetstarted.
1-Identifytherolesoftheactorsusingthesystem.
Simplyput,whoneedsasystembuilt? Figureoutwhattheroleofthatpersonis.
2-Understandtheirendgoal(s).
Theendgoalforsomeonewhoneedsatodoappmightsoundlike“Ineedtobemoreorga-
nizedwithmydailytasks”.
The end goal for a vinyl enthusiast might be “I want to make some money off of my vinyl
collection”or“Iwanttotradeinsomeofmyoldrecordsforbetterones.”
For DDDForum.com, the goal for someone interested in DDD might be that they “want a
placetolearnaboutDDD”andgettheirquestionsanswered.
3-Identifythesystem(s)thatneedtobeconstructedinordertoenabletheac-
tor(s)tomeettheirgoals.
Forthetodoapp,adesktopormobileappmightdo.
For White Label, the vinyl marketplace, we might need a web app for Traders to make
trades, an admin panel for Admins to monitor activity and perform administrative tasks,
andanapplicationforShipping Stafftopackitemsandtrackthatthey’redelivered.
4-Foreachactorineachsystem,listoutalloftheusecasesinvolvedinhelping
thatactormeettheirgoal(s).
Forexample,inthevinyl-tradingenterprisecomprisedofa)atradingwebapp,b)anadmin
panel,c)apackingandshippingapplication,Tradersfromthetradingwebapp(a)requires
severalusecaseslike:
• postVinyl(details: VinylDetails): void
• getVinylDetails(vinylId: VinylId): VinylDetails
• makeOffer(tradeItems: TradeItems[]): void
• etc
Let’strythisoutwithDDDForumbymakingsomeUMLusecasediagrams.
271

PlanningwithUMLUseCaseDiagrams
Ifyougettiredofreadingandwanttolearnmoreabouthowtodesignusecasediagrams,
here’sanexcellentfreevideotutorial.You’llwanttostickaroundandreadthissectioneither
way,becauseusecasediagramsarehelpfulupuntilapoint.
In use case diagrams, the square represents the system, the stick-man represents an actor
ofthesystem,andthecirclesrepresenttheuse cases. Theusecasesconnecttotheactor(s)
thatshouldbeabletoexecutethem.
Aslightlymoredetailedusecasediagramofawebsite.
1—Identifyingtheactors
Let’sstartwiththeroles. Whoisinvolved?
WeknowthatDDDForum.comisanonlineforumsitewherepeoplecanlearnaboutDDD
andgettheirquestionsanswered,sowecouldstartbyidentifyingtworoles: Membersand
Visitors.
Members are users who have registered to the site and can post questions, articles, com-
ments,andcastvoteswhileVisitorsareanyonewhohasn’tcreatedanaccountandisjust
ananonymoususer.
272

There’sanothertypeofroleinthisdomainthatcouldalsobeimportant,andthat’stheAdmin
role.
2—Identifyingtheactorgoals
We’recreatinganapplicationforuserstolearnaboutDDDandgettheirquestionsan-
swered. That’sacommongoalforVisitorsandMembers.
ForAdminusers,theirgoalistoensurethattheDDDForumcommunityiswelcoming,
helpful,andrespectful. Todothat,theyneedtheabilitytomoderateusersandcontent
postedwithinthesite.
3—Identifyingthesystemsweneedtocreate
Itmaynotcomeasasurprisetoyou,butweneedtobuildtheactualforumsiteforMembers
andVisitors,soDDDForum.comisthefirstsystemweneedtobuild.
273

ForAdminusers,theadminpanelcouldexisttoservetheirmoderationneeds.
Tocontainthescopeofourdiscussioninthischapter,we’rejustgoingtofocusonDDDFo-
rum.comforMembersandVisitors,leavingouttheadminpanelandAdminsfornow.
WemightexploreincludingtheAdminPanelinthischapterinafuturereleaseofthisbook.
274

OK, so the DDDForum.com is a system that is going to serve the needs of Members and
Visitors.
Whatcantheydowithinthesystem? Let’sthinkofafewusecases.
4—Identifyingtheusecasesforeachrole
Starting with Visitors, we can brainstorm use cases that describe the capabilities of a
Visitor. I landed on facts that specify they can register, login, view popular posts,
view recent posts, view a specific post, view the comments for the post, view a
comment threadand…thatmightbejustaboutit. Don’tworryifyoumissafewrightnow.
275

Rememberthateachoftheseusecasesareeitheracommandoraquery. Try
toidentifywhicharewhich.
Andlet’shookupsomeoftheusecasesforMembers. MemberscandoeverythingVisitors
candoandmore(we’lldocumentallofthosesoon).
276

Iknowthingsaregoingwellsofar,butI’mabouttoplungeastickinthespokes. Ineedto
raisesomethingimportantabouttheregisterandloginusecases. Let’sremovethelines
thatshowthatMembersandVisitorscanlog inandregister.
277

WhydidIdothat?Well,it’scomplicatedandlong-winded,butithastodowithrole,boundaries,
and,mostimportantly-Conway’sLaw. Thedetourisworthwhile(trustme).
Roles,boundaries,andConway’sLawinUseCaseDesign
Startingwithrole,letmeaskyouaquestion. Ifyouweredoingthisbyyourselfforthefirst
time,wouldyouhavelabeledVisitororMemberasUser? IknowIwouldhave.
Sowhydidn’twe?
Whydidn’twejustlabelUserastheprimaryactor?
278

It’sbecauseroledictatesresponsibility.
Roledictatesresponsibility
While we could call everyone a User, that could be unproductive to the ubiquitous lan-
guage.
RecallthatinDDD,aconsiderablepartofourdomainmodelingeffortsistoidentifyandcap-
tureasharedlanguageusedbetweendomainexperts,programmers,andanyonein-between?
That language that we capture should appear everywhere: in conversation, in code, and
technicaldocumentslikethisone.
Becausewe’redevelopersanddevelopersareaccustomedtocallingeverythingaUser,it’s
easytofallintothistrap.Ifwewereworkingwithdomainexperts,itwouldbemoreunlikely
we’d hear them use the term User to describe the role of someone that they work with or
manageintheirday-to-day.
Normally,you’dworkcollaborativelywithdomainexpertstoidentifythecom-
monlanguage.
ThereisatimeandplacetocallauseraUser,suchasinanIdentity&AccessManagement
/Authenticationcontext(likeifwewerebuildingserviceslikeAmazonIAM,AmazonCog-
279

nito, or Auth0). If our core domain was IAM (auth), the primary actor type is, in fact, a
User- since an Identity & Access Management (auth context) makesnoassumptionaboutthe
roleofusersoutsideofit’scontext. Auth0 and other popular IAM services understand that
developersintegratewithadomainunrelatedtotheirAPI,andtheroleof Userisofasimi-
larlevelofimportancebuthasadifferentmeaninginaseparatecontext.
ComparinganAuthentication(Identity&AccessManagement)contexttoa
Forumcontext,theconceptof Useristhesame,butdifferentforeachcontext.
Here’sanexampleofaContextMaptoillustratewhatImean.
GoingbeyondtheForumcontext,IcanthinkofplentyofotheralternativestoUserdepend-
ingonthecontext:
• Abillingsystem: Customer,Subscriber,Accountant,Treasurer,Employee
• Abloggingsystem: Editor,Reviewer,Guest,Author
• Arecruitmentplatform: JobSeeker,Employer,Interviewer,Recruiter
• Avinyl-tradingapplication: Trader,Admin
• Anemailmarketingcompany: Contact,Recipient,Sender,ListOwner
Getthepoint? Rolematters. Whenidentifyingactors,namethembasedontheirrole.
Becauseroledictatesresponsibility,sometimeswhenweuncoveraresponsibility(likelog
in and register), we have to ask ourselves if we’ve assigned it to the correct role. In this
case, it’s good that we were specific about Members and Visitors because it enabled us to
understandtheresponsibilitiesthattheyholdfromwithinthecontextof Forum.
Identifying actors in our systems by thinking about their role helps us deter-
minewhattheirresponsibilitiesare,andmoreimportantly,whatthey’re
not.
Weshouldnowunderstandthattheregisterandlog inusecasesarenot significanttoa
MemberorVisitor,buttheyaresignificanttoaUser.
280

WecouldcontinuebyaddingasmanyotherthingsthataMembercoulddo,butusingtheUse
Caseformat,it’schallengingtodoitinawaythatdoesn’tbecomemessy.
281

UMLUseCaseDiagramwithoutagoodseparationofconcerns
Yeah,thatdoesn’tlookgreat.AndIdidn’tevengettoaddalloftheusecases.There’sabunch
of other things that a User is responsible for, like getting the user account, deleting
their account,andsoon. Thiscluttersignifiesadesignproblemwithusecases.
Everythingfromauthusecases,toforumusecases,andperhapsevenifwewantedtosend
notifications-notificationsusecases,areallclumpedtogetherinthisdocument.
Weneedawaytorepresentboundaries.
Boundaries
Thebiggestproblemwithusecasediagramsisthelackofbeingabletorepresentarchitectural
boundaries.
Iunderstandboundariesasalogicalsurfaceareawhereeveryconstructisinthesamecontext.
Previously, we say that we had an Auth/Users context and a Forum context. Because use
cases tend to be high-level documents, they have trouble representing boundaries. As a
result, having all the use cases for a system with several boundaries within one grouping
canmakethedesignoverwhelming.
Yousawthemesswemadeamomentago.
What we need is a good way to represent the architecturalboundaries and the use cases that
belongtothoseboundarieswithinoursystem(s).
InDomain-DrivenDesign,theconceptofsubdomainsisequivalenttotheseboundaries.
UsingsubdomainstodefinelogicalboundariesinDDDForum
282

We’regoingtoorganizealltheactorsandtheiruse casesintosubdomains.
First,let’slistallourusecases(commands&queries)out.
UseCases
• Register
• Login
• Logout
• Getcurrentuser
• Getuserbyusername
• Refreshaccesstoken
• Verifyemail
• Deleteuser
• Createpost
• Deletepost
• Downvotepost
• Upvotepost
• Getpopularposts
• Getrecentposts
• Getpostbyslug
• Getcommentsforpost
• Getcommentthread
• Upvotecomment
• Downvotecomment
• Replytocomment
• Replytopost
Andlet’slistalloftheactors.
Actors
• User
• Member
• Visitors
Idon’tknowifyoucanseethis,buttherearetwosubdomainsthatIseerightaway.
There is a forum subdomain, which appears to be our core subdomain that allows us to
focusonposts,comments,votes,andsuch-anessentialpartofourapplication.
There’s also a generic users subdomain which takes care of all of our identity and access
managementforusers.
Aswell,theprimaryactorintheforumisthemember,whiletheprimaryactoroftheusers
subdomainistheuser.
283

Users(generic)andForum(core)subdomainsforDDDForum.com
Wemightalsohaveonemoresubdomainfornotifications.
Yousee,ifwe’regoingtobesendingemailstodoemailverificationorifwe’regoingtobesending
notifications to members if someone replies to their comment, we need another supporting
subdomaintodecoupletheconcernsofeverythingrelatedtoemails,notifications,etc.
Users (generic), Forum (core) subdomain and Notifications (supporting) subdomains for
DDDForum.com
Excellent.
Let’sdecomposethesystemfromourusecasediagramintosmallerpiecesbasedonoursub-
domains. Here’stherefactoredusecasediagramofDDDForum.com.
284

AusecasediagramdisplayingtheentireDDDForum.comsystemandthesubdomainsthat
it’scomprisedof.
Ilikethisalotmore. Here’swhatwecantakeawayfromthisnow:
• Weknowthatthereare3subdomains: users,notifications,forum.
• Weknowthespecificsubdomainsoursystemneeds.
• Weknowtheactorsforeachsubdomain.
• Weknowalloftheusecases,theactorsthatcanexecutethem,andthesubdomain
theybelongto.
285

Conway’sLaw
HowdidIknowweneededausers,forum,andnotificationssubdomain?
Well,there’sthefactthatI’vedonethisseveraltimesbefore,butthere’salsoausefulfact.
Alaw,actually. It’scalledConway’sLaw.
In1967,MelvinConway,aclevercomputerscientistandobject-modeler,wascreditedwith
thefollowingquotewithrespecttodesigningsystems. Hesaid:
“Organizationswhichdesignsystemsareconstrainedtoproducedesignswhich
arecopiesofthecommunicationstructuresoftheseorganizations”. —Wikipedia.
Inmyterms,Conwayissaying:
Whenwebuildsoftware, weneedtoknowthedifferentgroups/teams/rolesof
peopleitserves,anddividetheappupintoseparateparts,similartohowthose
groupsofpeoplenormallycommunicateinreallife.
Rememberthatthisisthefirststepofbuildingusecasediagrams? Nowweunderstandwhy.
SRP (Single Responsibility Principle): Conway somehow discovered that if we allow
eachgrouptohavetheirsystem,weconstrainthepossiblesurfaceareaofrequiredchanges
from one group rippling into another one as side effects, thus satisfying the single
responsibilityprinciple(beforeitwasevendiscovered).
Boundaries: Conway’slawdictatesarchitecturalboundaries, whichinformsarchitects
how to split an application into either distributed micro-services (micro-services split
upandnetworkedtogether)oramodularmonolith(severalsubdomainswell-separated
withinonedeployableunitofcode).
So,todiscoverthesubdomains,allwehavetodoisthinkaboutthedifferentteamsthatwe
couldassembletotakeownershipoveraspecificpartofthebusiness.
Andifweknowtheteams(subdomainslikeusers,forum,notifications),thenevery
domainconceptlikeanactor/role(andtheirusecases)belongstoasubdomain.
Tyingeverythingtogether, here’sanillustrationoftheinfluenceConway’sLawhasonar-
chitecture.
286

Thisismyfavouritediagraminsolidbook.io. Reallythinkaboutthesepathwaysandseeif
theymakessensetoyou.
Summaryonusecasediagrams
Usecasediagramsandreportsareprettyusefultoolsthatyoucanusetodocumentproject
requirementsandbusinessruleswithtestcases.
Iwouldadvocateforusingusecasediagramswhenweunderstandthedomain,andwe’re
okwithdoingthemajorityoftheusecasemodelingworkinisolation,awayfromdomain
expertswhomightnotunderstandeventheslightestsemanticsofusecasediagrams.
However,itcanberiskyfordeveloperstospenddesigntimealonesinceweknowthatit’s
theinitialdesignofaprojectthathasthepotentialtohavethemostprofoundimpactonthe
overallqualityofthesystem.
Theremustbeadesigntoolthatinvolvesboththedevelopersandthedomainexpertsinthis
process.
Thereis,andit’scalledEventStorming.
EventStorming
A group or workshop-based modeling technique that brings stakeholders and
developerstogetherinordertounderstandthedomainquickly.
287

WeuselotsofstickynoteswhenwedoEventStormingsessions.
AdevelopernamedAlbertoBrandolinifoundhimselfshortontimeforatraditionalUML
usecasedesignsession,butimprovisedwithsomestickynotes,markers,andawhiteboard,
inadvertentlycreatingEventStorming.
EventStorminghasbecomesomethingofastapleintheDDDcommunity. It’sisaninterac-
tivedesignprocessthatengagesbothdevelopersandbusiness-folktoquicklyandcheaply
learn the business and create a shared understanding of the problemdomain. The result is
either:
• a) abig-pictureunderstandingofthedomain(lessprecise).
• b) adesign-levelunderstanding(moreprecise),whichyieldssoftwareartifacts(ag-
gregates,commands,views,domainevents)agreedonbybothdevelopersanddomain
expertsthatcanbeturnedintorichdomainlayercode.
Itworksby:
• Gettingtherightpeopleintheroom. Ideally, wewantthedevelopersbuildingthe
system, developers responsible for other third party systems we need to integrate
with(ifany),anddomainexpertswe’rebuildingthesystemfor(theactorsweneedto
serve). You’llneedpeopletoanswerquestionsandhelpbuildtheubiquitouslanguage.
• Findingalargeerasablesurfacelikeawhiteboardorawalltoplacestickiesand
drawon. Youcanalsousealongrollofpaper,whichmayworkasabetteradhesive
288

forthestickies. Alternatively,ifyouworkonadistributedteam,youcanusesoftware
likeMiro.
• Bringing a lot of colored stickies and markers. We’ll write the name of domain
conceptswediscoverontheseandplacethemonthewall.
• Spending anywhere from a couple hours working on creating a model. You’ll
want to take breaks and bring snacks. It can be pretty intense- that’s because it’s
straight-upcriticalthinking.Notthetypeofactivityyoucandowhileknittingorplay-
inganiPhonegame. Youcanchunkitoutovertheperiodofacoupleofdaysinone
or two hour sessions, or do it all in a day. It might be a challenge to convince man-
agementtogeteveryonetogetherforanextendedamountoftime; though,thetime
iswellspentforthereturnoninvestmentofahigh-qualitydesignforsoftwarethat
canlastforyears.
Aftertheeventstormingsession,youcan:
• Convertthedesignintocodeusingtheterminologyagreeduponinthesession.
When we use the ubiquitous language and a layered architecture, our domain layer
code is declarative and can be understood by domain experts. Developers have a
sharedmentalmodelofwhatthebusinessis,andhowwe’rerepresentingthesolution
spaceincode.
• Understand the relationships between your core domain and other supporting
subdomains. Sometimeswemightnotwishtocodeeverythingourselves. Sometimes
we might just want to buy a tool instead of developing it in house. Again, consider
Auth0fortheUserssubdomain,orPusherforaNotificationssubdomain. It’slikely
thatUsersandNotificationsaren’tpartofyourcoredomain. Aftereventstorming,
we have a better understanding of those boundaries, and we can make an informed
decision.
• Run another event storming session to evolve the model. When new business
requirements or rules come into existence, it doesn’t hurt to run another session to
determinewhereinthetimelineofeventsthingsneedtochange.
• Take it further by defining scenarios with domain experts and then building
aseriesoftestcasestoexercisethatthemodelisworkingcorrectly. Domainex-
pertscanhelpyouensurethatyou’reusingqualitytestdatawhentestingagainstyour
scenarios. They’realsoessentialinverifyingthatthemodeliscorrectsincetheyare
theonesthatknowthedomainbest.
Whyweneedeventstorming
Thedomainexpertsaretheonesthatunderstandthebusinessbestbecauseit’sthem, not
thedevelopers,thatliveit.
Whendesignchoicesaremadeinisolationbydevelopers,wemayendupwithsoftwarethat
doesn’tfullymeettheneedsofitsusers.
Additionally, when developers code without fully understanding the domain, each devel-
operisleftwiththeirownsingularunderstandingofthedomain,whichmaynotlineupwhatis
actuallyimplemented. Brandolinisays,
“Too many developers on the project make individual mental models; this
makestheprojectunreliable”-viaTwitter(@ziobrando-Oct18th,2019)
289

Eventstorminginanutshell—via@ziobrando.
Howtoconductaneventstormingsession
Let’swalkthrougheachofthestepsinvolvedinholdinganeventstormingsessionwithyour
team.
Step0—Createalegendofalltheeventstormingconstructs
Beforeweevengetstarted,VaughnVernonrecommendsweusealegendsothattheevent
stormingconstructsandtheircolorschemesarewellunderstoodbyallparticipants.
• DomainEvents—orange
• Commands—lightblue
• Aggregates—yellow
• Issues—red
• Actors/Roles—lightyelloworyellowwithastickfigure
• Views—green
• Boundedcontexts(boundary)-solidline+namedw/apinksticky
• Subdomains(boundary)—dashedlines+namedw/apinksticky
• EventFlow—arrows
290

Step1—BrainstormDomainEvents
Fromlefttoright,chronologicallymapoutthestoryofthebusinessusingorange
stickynotes.
291

Togetstarted,everyonegrabssomestickies,getsasharpie,andworkstogethertoputthe
domaineventsontheboard,makingsurethattheorderofeventsiscorrect.
Thinkingintermsofdomaineventsistheclosestwecangettoexpressingwhathappensin
therealworld. Non-technicalfolkcancommunicatetheentirebusinessprocessasaseries
ofdomainevents.
UserRegistered -> EmailVerificationSent -> EmailVerified
Acoupleofthingstonoteaboutthisstep:
• Domaineventsarepast-tenseverbs. Forsubsequentsteps,it’simportanttofollow
this convention. The challenge here is that domain experts often think of business
processesastasksratherthanevents,sowemayhavetowarmlynursethemtogetin
thehabitofusingthepast-tenseformatlikePostCreatedorCommentUpvoted.
• Parallel or alternative domain events (like failure states) can be placed
vertically. For example, in an Orders domain, if we had a domain event called
OrderPaid, it’s also possible that the order could fail; that means we’d need an
OrderPaymentFaileddomaineventaswell. Becausethesearealternativeoutcomes,
andoneoftheseeventsoccurschronologicallyforsomepreconditions,wecanplace
bothoutcomesontheboardvertically.
Vertically placed domain events for scenarios where one of many meaningful domain
eventscouldoccur.
• It’spossiblefordomaineventstobetheresultofsomethinghappeninginan-
other system that floats into this one. In this case, we can still document the do-
mainevent.
• When the outcome of a domain event is a process, document it with a purple
sticky. Aprocessisanyscenariowherethereiseitherasinglestepormultiplesteps
thatwecancollectivelyidentify. Iftheprocessinvolvesimportantdomainevents,we
292

canputstickiesbesidetheprocesstorepresentthem.Drawalinefromtheoriginating
domaineventtotheprocess.
Example of a process started after the PostCreated domain event. PostUpvotedByOwner
andNewPostNotificationSentareparalleldomainevents.
• Iftheprocessissomethingnotimportanttothedomainyou’recurrentlyfocusedon
(like an intensive user registration process, for example), we can just stick a simple
domaineventtorepresenttheresultoftheentireprocesslikeUserRegistered.
• Onlylistmeaningfuldomainevents. Meaningfuldomaineventsarethosethatare
followed up by a process or subsequent domain events. Refrain from listing domain
eventslikePostCreationFailedwhereit’snotfollowedbyaprocessorasubsequent
domainevent.
• If you find an area that’s troublesome and you don’t have all the answers to,
nameanddocumentitwitharedstickynote. Thismighthappeniftherearestill
someunsolvedproblemsortherightpeoplearen’tpresent. Markingthisasatrouble
spotremindsyoutogobackandresearchitlater.
293

Anexampleofencounteringameaningfulalternativedomaineventthatwewouldliketo
haveawaytohandle,butwedon’thavealltheanswersforatthemoment.
Ifwecan’tthinkofanymoredomainevents,it’stimetomoveontothenextstep: commands.
Step2—CreatetheCommandsthatcauseDomainEvents
Foreachdomainevent,writethecommandthatcausesit.
294

ThecommandisexactlythesamethingascommandsfromourUMLusecasediagramsand
theCQSprinciple.Youdon’tneedtohavedoneaUMLusecasediagramoranythinginorder
todothisstep. Youcangothrougheachdomainevent,andplacethenameofthecommand
thatcreatesthedomainevent,beforeeachone.
Forexample,thePostCreateddomaineventwastheresultoftheCreatePostcommand.
Bytheendofthisstep,eachdomaineventshouldbeaccompaniedbyacommandinCom-
mand/DomainEventformat.
Otherrelevantthingsaboutthisstep:
• (Optional) If you know the Actor/Role that issues the command, place a small
yellowstickyonthebottomleftofthecommandtodocumentit. Forexample,I
knowthataMemberisresponsiblefortheCreatePostcommand,soIplacethesmall
yellowstickyonthecommand.
295

ExampleofdocumentingtheActor/Roleforacommand.
• Youmightfindmoredomaineventsinthescenariothatonecommandcreates
multipledomainevents. Inthatcase,justdothesamething:putthebluecommand
stickyontheleft,andputthedomaineventsontherightofthecommand.
Ifwe’vefoundallourcommands,we’rereadyforthenextpart.
Step 3 — Identify the Aggregate that the Command is executed against and the re-
sultingDomainEvent
ForeachCommand/DomainEventpair,putapaleyellowstickyinslightlyabove
andbetweenthemtorepresenttheAggregate.
296

Recall that in DDD, an Aggregate is a special type of Entity. Aggregates domain objects that
protectmodelinvariants. Theyarewhatweperformcommandsagainst.
Finally,we’vegottentothepartwhereweidentifythemodelsthatappearinourcode. No-
ticethatthisisthethirdstepofouranalysis-wheresomeapproachesputthisfirst.
Inthedatabase-firstapproach(orevenUMLclassdiagrams),wewouldhaveaimedtoattempt
todiscoverentitiesupfront.
Theuniquevalueofevent-baseddesignapproacheslikeeventstormingisthatwechange
the order of discovery to prioritize understanding the business. We put the behavior of
thebusinessbeforethestructureofthedatainthebusiness.
Reasonswhythisapproachisbetter:
• It’smucheasiertodescribethedatarequiredafterhavingdiscoveredthebehaviorof
thebusiness,whiletheoppositeissignificantlymorechallenging.
• Business-folkdon’tunderstandUMLandentity-relationshipdiagrams(itmakesfor
poordiscussion).
Forthisstep,hereareafewnotes:
• UsethewordEntityorDataifAggregateisconfusingtoothers.DDDiswellknown
forthechallengingnamesforallofitsconstructs. Iwouldn’texpectanyonetoknow
whatanAggregateis,soit’srecommendedtousethewordEntity.
• IfAggregatesareusedmultipletimes,createcopiesandplacethemrepeatedly
onthetimeline.
• IfatanypointyourealizeanymoreDomainEventsorCommands,feelfreeto
alsodocumentthose.
Next,we’reontoboundaries.
Step4—CreateSubdomainandBoundedContextboundaries
297

Withallthecurrentstickiesontheboard,it’stimetoestablishthesubdomainandbounded
contextboundaries.
ThisstepisaskingyoutoapplyConway’sLaw.
Thisislikelythemostchallengingstepofeventstormingbecauseitrequireshavingagood
understandingofsubdomainsandboundedcontexts.
Goodthingsaboutbeingabletoapplythisare:
• ItbecomeseasytoseehowsomeDomainEventsendupwithinourcoredomain
withoutneedingacommandtobeinvokedfirst;thisisbecausewesubscribeto
themfromanotherdomain.
When we apply this step to DDDForum.com, we should end up with a similar diagram to
theonewecreatedwhenapplyingConway’sLawtoourUseCasediagrams.
298

SubdomainswithinDDDForum.com(asingleboundedcontextifbuiltasamodularmono-
lith)illustratingthedirectionofdomaineventsthatflowbetweensubdomainboundaries.
Step5—IdentifyViews&Roles
ForeachCommand/DomainEventpair,identifytheview(s)neededtoprovidein-
formationbeforetheRole(s)invoketheCommand.
299

• Ifthereareimportantroles,makesuretolistthemhere.
• (Optional) Create wireframes or mockups to illustrate what the views might
looklike. Youwanttodowhateverisgoingtobemostusefulforyouandeveryone
elsetounderstandwhat’snecessaryforthemodel.
Steps0to5arealloftheabsolutelynecessarysteps, butfeelfreetoinventconstructsto
use. Everythingisfairgamehereifithelpsusimprovethemodel.
Forexample, IliketodocumentthepreconditionsthatspecifywhenandhowaCommand
canbeinvoked.
Step6(Optional)—Identifyrules/policies
Place neon blue stickies before the Command and document the preconditions
thatallowordisallowtheCommandtobeinvoked.
300

Dependingonhowdetailedyouwanttogetandifyou’redoingbig-pictureeventstorming
ordesign-levelstorming,documentingtheruleswithintheeventstormedmodelisanop-
tion.
TheserulesareBDD-stylegivenX,thenYpolicies.
Someone’sproductiveEventStormingsession.
301

EventModeling
ThefinalapproachtoplanningaprojectthatIwanttomentionbrieflyisactuallythenewest
one: it’scalledEventModeling.
Event modeling brings together all of the discoveries of Event Sourcing, Event Storming,
DDD,Conway’sLaw,andUseCasedesign. Imagecourtesyofeventmodeling.org.
There’snotmuchnewaboutEventModeling. It’smoreofaformalizationofalltheknowl-
edgewe’veacquiredaboutbuildingevent-drivensystems.
Thistracesallthewaybacktoaround2003whenEricEvansreleasedtheoriginalDomain
DrivenDesign book, Fowler wrote about Event-Sourcing in 2005, Greg Young popularized
CQRSandEventSourcingfrom2007to2012,and2013whenBrandoliniusedEventStorm-
ingasawaytounderstandandplanaprojectaroundaproblemdomain.
In 2008, Canadian consultant, Adam Dymitruk formalized the work from 2003-2018 re-
volvingaroundDDDandevent-basedsystems,coiningthetermEventModeling.
EventModelingisanotherapproach,verysimilartoEventStorming,thatputsallthepiecesof
thepuzzletogetherinordertoplanasystembeforecodingit.
WhileitsharesitssimilaritieswithEventStorming,themainadditiontoEventModelingthat
wasn’tformallydefinedinEventStormingwastheuseofUIdrawingsinordertopresentthe
viewsmoreeffectively.
302

SnippetofanEventModelingsessiondoneonaHotelReservationdomain. Imagecourtesy
ofeventmodeling.org.
SometimespeopleinEventStormingsessionswoulddothisanyways,butEventStormingfor-
malizestheapproachtocreateroughwireframesaspartofthedesignprocess.
Hereisanoutlineofthestepsinvolved. NoticethesimilaritywithEventStorming:
1. Identifyevents
2. Plottheeventsonatimeline
3. Createwireframes/mockupsofthestory
4. Identifyinputs
5. Identifyoutputs
6. ApplyConway’sLaw
7. Elaborateoneachscenario(BDD-styletestcases)
Adamarguesthattheresultingapproachisamorereasonablewaytodesignanevent-based
systeminorderto:
• designforscalability
• achievezerodataloss
• achievefastertransactionalperformance
• keepthesystemmodelsimple
• reducedevelopmenttimelines
Formoreinformation,Irecommendyoucheckout“EventModeling: Whatisit?” onEvent-
Modeling.organdgetafeelforityourself.
303

BuildingDDDForum
Thisisthepartofthebookwhereweapplyeverythingwe’velearned,andyougettoseeifI
practicewhatIpreach.
After having come up with a design, whether it be using EventStorming, EventModeling, or
Eventjustboxes,shapes,andarrows,we’reinamuchbetterpositiontostartcodingupour
project.
ForDDDForum.com,beforeIstartedcoding,Ihad:
• an EventStormed model created that identified all of the events, commands, queries,
aggregates,andviews
• Several of the policies/business rules thought out and identified that would affect how
andwhenspecificcommandsandeventsoccur.
• Wireframes created with Figma to verify the exact attributes that I’d need on each
model
Withthatinplace,we’reinformedandreadytobuildDDDForum.com.
Letmewalkyouthroughit.
Viewthecode:YoucanfindthecodeforDDDForum.cominitscompletionhere
onGitHub.
Projectarchitecture
Someofthesignificantupfrontdecisionsthatwe’remakingaboutthisprojectarethefol-
lowing:
Decision1: We’regoingtouseDomain-DrivenDesign
Thatshouldn’tbeasurprisetoyouatthispointinthechapter. UsingDomain-DrivenDe-
sign means that we’re going to start our development journey by encoding the business
ruleswithinourdomainmodels. Thetaskistodefineallthemodels,therelationshipsbe-
tweenthem,thepoliciesthatgovernwhenandhowtheycanchange,andmakeitvirtually
impossibletorepresentanyillegalstate.
For example, if we had a User entity (which we do in the users subdomain), consider the
implicationsofhavingagetterandasetterfortheuserIdproperty.
// users/domain/user.ts
export class User extends Entity<UserProps> {
get userId () : UserId {
return this.props.userId;
}
// set userId (userId: UserId) {
// this.props.userId = userId;
// }
304

...
}
Concerningtheuserssubdomain,there’snoreasonwhytheuserIdshouldeverchangetoa
newvalue.DoingsowouldbreaktherelationshipsbetweenUserandanyothersubdomains
thathavea1-to-1relationshipwithUser,likeMemberfromtheForumsubdomain.
Soweremovethesetter. WemakeitimpossibletomutateUserinawaythatputsitinan
invalidstate.
// users/domain/user.ts
export class User extends Entity<UserProps> {
get userId () : UserId {
return this.props.userId;
}
...
}
Therefore, our task is to create plain ol’ TypeScript objects and ensure that they can only
performvalidoperations.
It’skindoflikebuildingyourveryownDSL(domain-specificlanguage).
Domainobjectshavezerodependenciesandonlycreatesourcecodedependenciestoother
domain objects. Because of this, we can write tests to ensure that the business logic con-
tainedinentities,valueobjects,anddomainservicesarecorrect,andwecanexpecttheseteststo
runveryfast.
Decision2: We’regoingtouseaLayeredArchitecture
We’vementioneditbefore,butyou’llfinditchallengingtoimplementDomain-DrivenDe-
signwithoutsomesortofLayeredArchitecture.
That’s especially true because we need a way to isolate our domain layer from outer layer
concerns like databases, controllers, web servers, and other things that might slow down
ourabilitytoruntestsandclashwiththeUbiquitousLanguage.
Becausesoftwaredoesn’tdoawholelotunlesswecanconnectthepieces,wecanimplement
DependencyInversiontobridgethegapbetweenlayers.
Asaruleofthumb,thedirectionofsourcecodedependenciesmustalwayspointinwards,
towardsthedomain-layercode. ThisruleofthumbiscalledTheDependencyRule.
Additionalreading: Ifyou’reinterested,youcanreadmoreaboutTheDepen-
dencyRulehere.
Decision3: We’regoingtodeployaModularMonolith
LikewetalkedaboutinDeploymentsasaModularMonolith,onnewprojectswithasmaller
team, it could be a good idea to start with a ModularMonolith instead of jumping to imple-
mentingMicro-servicesrightaway.
305

AmonolithicapplicationenablesboththeUsersandForumsubdomainstolivewithinthe
samecodebasebutfromwithinseparatemodules.
In DDD, the way that subdomains or bounded contexts communicate with each other is
throughthepublishingandsubscribingofDomainEvents.
UsingDomainEventsastheprimarymechanismformessagingisanexcellentwaytofoster
loosecouplingbetweenmodules.
Inareal-worldmicro-servicedeployment,DomainEventsgetpublishedtoaqueueandsent
outacrossthenetworktosubscribers.
Inourproject,we’llimplementanIn-MemoryDomainEventsQueuesothatwecanex-
change messages between the subdomains in our modular monolith and maintain loose
coupling.
Decision4: We’regoingtouseCQRS(CommandQueryResponseSegregation)
WhenwefirstlearnaboutDDD,it’scommontoalsohearaboutconceptslikeCQRS(Com-
mandQueryResponseSegregation)aswell.
Basedonmyexperience,CQRSsolvesalotofdesignissuesforus.Themostapparentdesign
issuethatitaddressesisrelatedtoAggregatedesign.
Whenwe’rebuildingAggregates,ourgoalistodesignanobjectthatenforcesmodelinvariants
againstoperationsthatchangethestateofthesystem. Namely,writes. Writecommands.
Ourtaskbecomesevenmorechallengingwhenwealsohavetodesignaggregatestoreturn
enoughinformationtobuildviewmodels(orDTOs)fromaswell.
Asaresultofthesetworesponsibilitieslivingonthesameobject(writes&reads),weendup
withanAggregatemodelthatbecomesmessy,unreasonable,volatile,andunclearofwhich
propertiesarenecessaryforthesakeofprotectinginvariants,andwhicharenecessaryfor
merelycreatingreadmodels.
Toaddressthesechallenges,wecanadopttheCQRSpattern. Takingitonestepfurtherthan
theCQS(Command-QuerySeparation)pattern,CQRSimpliesthatwehaveseparatemod-
elsforreadingandwriting. Thatis,foraPostaggregate,wehaveonewritemodelandat
leastonereadmodelopposedtohavingonlyonemodelresponsibleforbothoperations.
Decision5: We’renotgoingtouseEventSourcing
Event Sourcing is another approach toimplementingDDD thatcomes up oftenin discus-
sion.
Inmyopinion,EventSourcingishard. Iwouldn’trecommendusingitonyourfirstDomain-
DrivenDesignproject.
Thatsaid,thereareincrediblyvalidreasonstouseEventSourcing.
Additional reading: Communication, auditing & reasoning, estimates, scalability, and
tamingcomplexityaregoodargumentsforevent-basedsystems. Youcanread“WhyEvent-
BasedSystems?” formoreinformationonthis.
306

Because DDDForum.com is relatively simple, and because I don’t want to expand too far
pastthescopeofthissection,wewon’tbeimplementingEventSourcingtoday. Allofwhat
we’lllearnhowtodoinDDDwillbeapplicablewhenwefinallygettoEventSourcing. And
thesearethebasics. Let’sstartsmall.
Startingwiththedomainmodels
Inthecomingsection5. Object-OrientedProgramming&DomainModel-
ing, we will have thoroughly learned how to use the best of Object-Oriented
Programmingtocreaterichdomainmodels. Inthissection,ourfocusistounder-
standattheclasslevelhowalloftheconceptsfromDomain-DrivenDesignwork
togetherinconjunctiontopoweraflexible,testable,andmaintainablewebap-
plication.
Let’s start with the domain models in the Users subdomain because for us to even begin
to have the equivalent of users in Forum, a UserCreated DomainEvent needs to be emitted
fromtheUserssubdomain. TheUsersforumiswherethefirstDomainEventsoccurinour
timeline.
Here’s the section of our Event Stormed model that depicts how Members from the Forum
subdomainarecreatedasaresultoftheUserCreatedDomainEvent.
The UserCreated domain event from the Users subdomain crossing the boundary and re-
sultinginthedispatchofaCreateMembercommand.
SothenitonlymakessensetobeginourjourneyfromtheUsermodel.
Let’s look inside of the modules/users/domain folder in DDDForum, where all of the do-
mainlayerconceptsfortheuserssubdomainlive.
307

ModelingaUserAggregate
IfIweretostartfromscratchoncreatingthisUsermodel,I’dstartwithtryingtoidentify
allofthedifferentthingsorpropertiesaUserhas.
ImightstartbycreatinganinterfacethatholdsalloftheUserPropslikeso:
// users/domain/user.ts
| interface         | UserProps | {       |     |     |     |     |
| ----------------- | --------- | ------- | --- | --- | --- | --- |
| email:            | string    |         |     |     |     |     |
| username:         | string    |         |     |     |     |     |
| password:         | string    |         |     |     |     |     |
| isEmailVerified?: |           | boolean |     |     |     |     |
| isAdminUser?:     |           | boolean |     |     |     |     |
| accessToken?:     |           | string  |     |     |     |     |
| refreshToken?:    |           | string  |     |     |     |     |
| isDeleted?:       | boolean   |         |     |     |     |     |
| lastLogin?:       | Date      |         |     |     |     |     |
}
This is great. We’ve identified pretty much all of the properties that we need in order to
createaUser.
Isthereanywaythatwecanimprovethisinterface?
Why,yes,thereis.
PartofthejobinDomain-DrivenDesignisprotectingagainstillegalstates. Whenweuse
| primitive |            | string | number            |           |             |          |
| --------- | ---------- | ------ | ----------------- | --------- | ----------- | -------- |
|           | types like |        | or for properties | that have | fundamental | business |
rulesencapsulatedwiththem,we’reopeningourselvesuptothepossibilityofhavingan
objectimpossibletothedomain.
I’mtalkingaboutusingValueObjects.
Thepropertiesemail,username,password,accessToken,andrefreshTokenalleither:
• Havevalidationrulesthatdictatewhatmakesitvalid
• Orareimportanttonominallytypesothatitcannotbesubstitutedforatypethatis
similar
Forexample,wewanttopreventbeingabletopassinanemailsthatlooklikethis:
test.com
imnotanemailaddresss@hello
khalilstemmler
Andifwesimplyusedastringprimitiveforemail,itwouldbeentirelypossibletocreate
aninvalidUserandhavethatfloatingaroundandpersistedtothedatabase.
Domain-DrivenDesignisnotaString-lytypedaffair:)
Wecanimprovethedesignbypromotingtheprimitiveswithbusinessrulesorwithillegal
substitutabilitytoValueObjectslikeso.
308

// users/domain/user.ts
| interface         | UserProps    |              | {        |          |          |        |     |
| ----------------- | ------------ | ------------ | -------- | -------- | -------- | ------ | --- |
| email:            | UserEmail    |              | // value | object   |          |        |     |
| username:         | UserName     |              | // value |          | object   |        |     |
| password:         | UserPassword |              |          | // value | object   |        |     |
| isEmailVerified?: |              |              | boolean  |          |          |        |     |
| isAdminUser?:     |              | boolean      |          |          |          |        |     |
| accessToken?:     |              | JWTToken     |          | // value | object   |        |     |
| refreshToken?:    |              | RefreshToken |          |          | // value | object |     |
| isDeleted?:       |              | boolean      |          |          |          |        |     |
| lastLogin?:       |              | Date         |          |          |          |        |     |
}
It’sawiseideatohaveunittestsagainstourValueObjectsandensurethattheybehavecor-
rectly.
// users/domain/userEmail.spec.ts
| import            | { UserEmail |                                                | } from                             | "./userEmail" |         |         |         |
| ----------------- | ----------- | ---------------------------------------------- | ---------------------------------- | ------------- | ------- | ------- | ------- |
| import            | { Result    | }                                              | from "../../../shared/core/Result" |               |         |         |         |
| let email:        | UserEmail   |                                                |                                    |               |         |         |         |
| let emailOrError: |             |                                                | Result<UserEmail>                  |               |         |         |         |
| test("Should      |             | be able                                        | to create                          |               | a valid | email", | () => { |
| emailOrError      |             | = UserEmail.create("khalil@apollographql.com") |                                    |               |         |         |         |
expect(emailOrError.isSuccess).toBe(true)
| email | = emailOrError.getValue() |     |     |     |     |     |     |
| ----- | ------------------------- | --- | --- | --- | --- | --- | --- |
expect(email.value).toBe("khalil@apollographql.com")
})
| test("Should |     | fail                           | to create | an  | invalid | email", | () => { |
| ------------ | --- | ------------------------------ | --------- | --- | ------- | ------- | ------- |
| emailOrError |     | = UserEmail.create("notvalid") |           |     |         |         |         |
expect(emailOrError.isSuccess).toBe(false)
})
Torestrictobjectcreationandmakesurethatit’sonlypossibleinthecasethatwehavevalid
Userprops,wecanimplementtheFactoryPatternbyplacingtheprivatekeywordonour
constructor.ThisforceseveryonetousethestaticcreatemethodifwewanttocreateaUser.
ItalsomakesitimpossibleforyoutocreateaninvalidUser.
// users/domain/user.ts
| interface | UserProps     |     | {   |     |     |     |     |
| --------- | ------------- | --- | --- | --- | --- | --- | --- |
| email:    | UserEmail;    |     |     |     |     |     |     |
| username: | UserName;     |     |     |     |     |     |     |
| password: | UserPassword; |     |     |     |     |     |     |
309

| isEmailVerified?: |     |          | boolean;      |     |     |     |     |     |     |
| ----------------- | --- | -------- | ------------- | --- | --- | --- | --- | --- | --- |
| isAdminUser?:     |     |          | boolean;      |     |     |     |     |     |     |
| accessToken?:     |     |          | JWTToken;     |     |     |     |     |     |     |
| refreshToken?:    |     |          | RefreshToken; |     |     |     |     |     |     |
| isDeleted?:       |     | boolean; |               |     |     |     |     |     |     |
| lastLogin?:       |     | Date;    |               |     |     |     |     |     |     |
}
/**
| *   | User   | is an Aggregate |            | Root     | since | it's the |     |     |     |
| --- | ------ | --------------- | ---------- | -------- | ----- | -------- | --- | --- | --- |
| *   | object | that            | we perform | commands |       | against. |     |     |     |
*/
| export | class | User | extends | AggregateRoot<UserProps> |     |     |     | {   |     |
| ------ | ----- | ---- | ------- | ------------------------ | --- | --- | --- | --- | --- |
...
/**
|     | * Private       | constructor |          | that     | disables | us       | from |     |     |
| --- | --------------- | ----------- | -------- | -------- | -------- | -------- | ---- | --- | --- |
|     | * circumventing |             | the      | creation | rules    | by using |      |     |     |
|     | * the           | `new`       | keyword. |          |          |          |      |     |     |
*/
private constructor (props: UserProps, id?: UniqueEntityID) {
|     | super(props, |     | id) |     |     |     |     |     |     |
| --- | ------------ | --- | --- | --- | --- | --- | --- | --- | --- |
}
/**
|     | * Static | factory  | method             | that | forces | the  | creation | of  | a   |
| --- | -------- | -------- | ------------------ | ---- | ------ | ---- | -------- | --- | --- |
|     | * user   | by using | User.create(props, |      |        | id?) |          |     |     |
*/
public static create (props: UserProps, id?: UniqueEntityID): Result<User> {
|     | // Guard     | clause      | that            | fails                                | if the        | required | properties |            | aren't |
| --- | ------------ | ----------- | --------------- | ------------------------------------ | ------------- | -------- | ---------- | ---------- | ------ |
|     | // provided. |             |                 |                                      |               |          |            |            |        |
|     | const        | guardResult |                 | = Guard.againstNullOrUndefinedBulk([ |               |          |            |            |        |
|     | {            | argument:   | props.username, |                                      | argumentName: |          |            | 'username' | },     |
|     | {            | argument:   | props.email,    |                                      | argumentName: |          | 'email'    | }          |        |
]);
|     | if (!guardResult.succeeded) |                                        |     |     | {   |     |     |     |     |
| --- | --------------------------- | -------------------------------------- | --- | --- | --- | --- | --- | --- | --- |
|     | return                      | Result.fail<User>(guardResult.message) |     |     |     |     |     |     |     |
}
310

|     | const | isNewUser | =     | !!id === | false; |     |     |     |
| --- | ----- | --------- | ----- | -------- | ------ | --- | --- | --- |
|     | const | user      | = new | User({   |        |     |     |     |
...props,
|     | //         | Assemble | default         | props |     |                 |          |     |
| --- | ---------- | -------- | --------------- | ----- | --- | --------------- | -------- | --- |
|     | isDeleted: |          | props.isDeleted |       | ?   | props.isDeleted | : false, |     |
isEmailVerified: props.isEmailVerified ? props.isEmailVerified : false,
|     | isAdminUser:            |     | props.isAdminUser |     |                     | ? props.isAdminUser |     | : false |
| --- | ----------------------- | --- | ----------------- | --- | ------------------- | ------------------- | --- | ------- |
|     | }, id);                 |     |                   |     |                     |                     |     |         |
|     | if (isNewUser)          |     | {                 |     |                     |                     |     |         |
|     | user.addDomainEvent(new |     |                   |     | UserCreated(user)); |                     |     |         |
}
|     | return | Result.ok<User>(user); |     |     |     |     |     |     |
| --- | ------ | ---------------------- | --- | --- | --- | --- | --- | --- |
}
}
WecontinuebywritinggettersandanyappropriatesettersonUser(note: thereareno
settersfortheUsermodel).
// users/domain/user.ts
| export | class | User | extends | AggregateRoot<UserProps> |     |     | {   |     |
| ------ | ----- | ---- | ------- | ------------------------ | --- | --- | --- | --- |
...
| get | userId | ():                     | UserId | {   |     |     |     |     |
| --- | ------ | ----------------------- | ------ | --- | --- | --- | --- | --- |
|     | return | UserId.create(this._id) |        |     |     |     |     |     |
.getValue();
}
| get | email  | ():               | UserEmail | {   |     |     |     |     |
| --- | ------ | ----------------- | --------- | --- | --- | --- | --- | --- |
|     | return | this.props.email; |           |     |     |     |     |     |
}
| get | username |                      | (): UserName |     | {   |     |     |     |
| --- | -------- | -------------------- | ------------ | --- | --- | --- | --- | --- |
|     | return   | this.props.username; |              |     |     |     |     |     |
}
| get | password |                      | (): UserPassword |     | {   |     |     |     |
| --- | -------- | -------------------- | ---------------- | --- | --- | --- | --- | --- |
|     | return   | this.props.password; |                  |     |     |     |     |     |
}
...
}
| That’sitfortheUsermodelfornow. |     |     |     |     | Afewthingstonote: |     |     |     |
| ------------------------------ | --- | --- | --- | --- | ----------------- | --- | --- | --- |
311

• TheUsermodelhaszeroreferencestoanythingotherthanotherplainol’TypeScript
objects. Thatmakesthetestsreallyfast.
• TheUsermodelextendsanAggregateRootclass,whichhassomeadditionalfunction-
alitythatwearegoingtodiscussimmediatelyinthefollowingsection.
• WenestthepropswithintheUsermodelinsteadofdeclaringthemdirectlyontheclass
sothatwecanhavecontroloverotherdevelopers’abilitytogetandsetpropertieson
instancesof User.
• Domainobjects(Aggregates,Entities,andValueObjects)holdthehighestlevelofpolicy
intheentiretyofourapplication. Upperlayerclassesrelyonit. It’sononeofthese
threeobjectsthatyouwanttoaimtoencapsulatebusinessruleswithinfirst.
• TheStableDependencyPrinciple(SDP)saysthatallcomponentsshouldbeinthedi-
rectionofstability. Sincetheseclassesaredependedonbyupperlayers,itneedstobe
themoststable. Thisislikelytohappennaturallysincedomainlayerclassesmimic
the business rules of the domain, and needing drastic changes to the domain code
would be unlikely since it would mean a drastic change to the way the business fun-
damentallyworks.
• Thisisn’ttheonlywaytomodelaUsermodel. Thisiswhatworksforme,andit’sideal
forteamstocreatetheirowncoreEntity,ValueObject,andotherimportantconceptual
DDDclassesthatworkforthemandtheirunderstandingofhowtheywork.
Viewthecode: Youcanreadusers/domain/user.tsinitsentiretyhereonGitHub.
EmittingDomainEventsfromaUserAggregate
Youmighthavenoticedthatinsideofthecreate(props: UserProps, id?: UniqueEntityID)
method,there’saconditionthatdetermineswhenweshouldemittheUserCreateddomain
event.
// users/domain/user.ts
const isNewUser = !!id === false
const user = new User(
{
...props,
isDeleted: props.isDeleted ? props.isDeleted : false,
isEmailVerified: props.isEmailVerified ? props.isEmailVerified : false,
isAdminUser: props.isAdminUser ? props.isAdminUser : false,
},
id
)
if (isNewUser) {
user.addDomainEvent(new UserCreated(user))
}
If you recall from Entities, we discuss the lifecycle of an Entity. An Entity doesn’t have
312

User.create(props:
an identifier until after it’s created, that is, until after we invoke
| UserProps, | id?: | UniqueEntityID)andgetaUserback. |     |     |     |
| ---------- | ---- | ------------------------------- | --- | --- | --- |
Ifwe’recreatingaUserforthefirsttime,wewon’tpassinanid: UniqueEntityIDbecausewe
don’thaveoneyet.
Inthatscenario,wewanttomakesurewefireofftheDomainEventtoaSubject(seeObserver
pattern)sothatwhenatransaction(oraUnitofWork)completes,wecanpropagatethatDo-
mainEventcrossourenterpriseandallowanysubdomainsorboundedcontextsinterested
inthatEvent,todosomethingafterhavingreceivedit.
In a monolithic application, we pass messages between subdomains using a
class-levelimplementation of the Observer Pattern. In a micro-service applica-
tion,wepassmessagesbetweenBoundedContextsbyusinganarchitecture-level
implementationoftheObserverPatternwithMessageQueues.
ThisishowaMemberintheForumsubdomaingetscreated: inresponsetotheUserCreated
domaineventfromtheUserssubdomain.
WritingDomainEvents
Most DDD developers will use a base domain events interface. The one shown be-
low describes the contract for a domain event. It says that a domain event needs a
dateTimeOccurredanditmustdefineafunctionthatknowshowtogettheaggregateidfor
theDomainEventinquestion.
// IDomainEvent.ts
| import            | { UniqueEntityID |                     | } from "../UniqueEntityID"; |     |     |
| ----------------- | ---------------- | ------------------- | --------------------------- | --- | --- |
| export            | interface        | IDomainEvent        | {                           |     |     |
| dateTimeOccurred: |                  | Date;               |                             |     |     |
| getAggregateId    |                  | (): UniqueEntityID; |                             |     |     |
}
AMemberCreatedeventtakingshapecouldlooklikethefollowing.
// forum/domain/events/memberCreated.ts
| import | {   |     |     |     |     |
| ------ | --- | --- | --- | --- | --- |
IDomainEvent
| } from | "../../../../shared/domain/events/IDomainEvent"; |     |     |     |     |
| ------ | ------------------------------------------------ | --- | --- | --- | --- |
| import | {                                                |     |     |     |     |
UniqueEntityID
| } from | "../../../../shared/domain/UniqueEntityID"; |                     |            |              |     |
| ------ | ------------------------------------------- | ------------------- | ---------- | ------------ | --- |
| import | { Member                                    | } from "../member"; |            |              |     |
| export | class MemberCreated                         |                     | implements | IDomainEvent | {   |
| public | dateTimeOccurred:                           |                     | Date;      |              |     |
| public | member:                                     | Member;             |            |              |     |
313

| constructor           |     | (member: |           | Member) | {           |     |     |     |     |
| --------------------- | --- | -------- | --------- | ------- | ----------- | --- | --- | --- | --- |
| this.dateTimeOccurred |     |          |           | =       | new Date(); |     |     |     |     |
| this.member           |     |          | = member; |         |             |     |     |     |     |
}
| getAggregateId |     |                 | (): | UniqueEntityID |     | {   |     |     |     |
| -------------- | --- | --------------- | --- | -------------- | --- | --- | --- | --- | --- |
| return         |     | this.member.id; |     |                |     |     |     |     |     |
}
}
BuildingaDomainEventsSubject
Whathappenswhenwesay,aggregate.addDomainEvent(event: IDomainEvent)froman
Aggregate?
Here’sthebaseAggregateRootclassthatwe’reusinginDDDForum.
// shared/domain/AggregateRoot.ts
| import | { Entity         |     | } from | "./Entity"       |                         |     |         |           |     |
| ------ | ---------------- | --- | ------ | ---------------- | ----------------------- | --- | ------- | --------- | --- |
| import | { IDomainEvent   |     |        | } from           | "./events/IDomainEvent" |     |         |           |     |
| import | { DomainEvents   |     |        | } from           | "./events/DomainEvents" |     |         |           |     |
| import | { UniqueEntityID |     |        | } from           | "./UniqueEntityID"      |     |         |           |     |
| export | abstract         |     | class  | AggregateRoot<T> |                         |     | extends | Entity<T> | {   |
/**
* All of the domain events for a subclass of AggregateRoot<T>
| * get | added |     | to this | private | array. |     |     |     |     |
| ----- | ----- | --- | ------- | ------- | ------ | --- | --- | --- | --- |
*/
| private | _domainEvents: |                |     | IDomainEvent[] |     |     | = [] |     |     |
| ------- | -------------- | -------------- | --- | -------------- | --- | --- | ---- | --- | --- |
| get     | id():          | UniqueEntityID |     | {              |     |     |      |     |     |
| return  |                | this._id       |     |                |     |     |      |     |     |
}
| get    | domainEvents(): |                    |     | IDomainEvent[] |     | {   |     |     |     |
| ------ | --------------- | ------------------ | --- | -------------- | --- | --- | --- | --- | --- |
| return |                 | this._domainEvents |     |                |     |     |     |     |     |
}
/**
| * @method |     | addDomainEvent |     |     |     |     |     |     |     |
| --------- | --- | -------------- | --- | --- | --- | --- | --- | --- | --- |
* @protected
| * @desc |     | Called | by  | a subclass    | in  | order     | to add | a Domain       | Event |
| ------- | --- | ------ | --- | ------------- | --- | --------- | ------ | -------------- | ----- |
| * to    | the | list   | of  | Domain Events |     | currently | on     | this aggregate |       |
* within a transactional boundary. Also notifies the DomainEvents
* subject that the current aggregate has at least one Domain Event
| * that | we  | will | need | to publish |     | if the | transaction | completes. |     |
| ------ | --- | ---- | ---- | ---------- | --- | ------ | ----------- | ---------- | --- |
314

*/
| protected |     | addDomainEvent(domainEvent: |     |     |     | IDomainEvent): | void { |
| --------- | --- | --------------------------- | --- | --- | --- | -------------- | ------ |
// Add the domain event to this aggregate's list of domain events
this._domainEvents.push(domainEvent)
// Add this aggregate instance to the domain event's list of aggregates who's
| //  | events | it eventually |     | needs | to  | dispatch. |     |
| --- | ------ | ------------- | --- | ----- | --- | --------- | --- |
DomainEvents.markAggregateForDispatch(this)
| //  | Log | the domain | event |     |     |     |     |
| --- | --- | ---------- | ----- | --- | --- | --- | --- |
this.logDomainEventAdded(domainEvent)
}
| public                       | clearEvents(): |     | void | {   |                            |     |     |
| ---------------------------- | -------------- | --- | ---- | --- | -------------------------- | --- | --- |
| this._domainEvents.splice(0, |                |     |      |     | this._domainEvents.length) |     |     |
}
private logDomainEventAdded(domainEvent: IDomainEvent): void {
| const | thisClass        |     | = Reflect.getPrototypeOf(this) |                                       |     |     |     |
| ----- | ---------------- | --- | ------------------------------ | ------------------------------------- | --- | --- | --- |
| const | domainEventClass |     |                                | = Reflect.getPrototypeOf(domainEvent) |     |     |     |
console.info(
|     | `[Domain | Event | Created]:`, |     |     |     |     |
| --- | -------- | ----- | ----------- | --- | --- | --- | --- |
thisClass.constructor.name,
"==>",
domainEventClass.constructor.name
)
}
}
SotherealmagicthathappenswiththissubclassiswithintheaddDomainEvent()method.
NotonlydoweaddtheIDomainEventtoalistofDomainEventscurrentlyontheAggregatefor
thetransaction,butwenotifytheDomainEventssubjectthatthecurrentAggregateshouldbe
markedfordispatch. ThismeansthatwhenthetransactionforthisAggregatecompletes,we
shouldpublishtheDomainEventsattachedtotheAggregate.
MyimplementationoftheDomainEventssubjectissomethingIportedtoTypeScriptfrom
UdiDahan’s2009blogpostaboutDomainEventsinC#.
Hereitisinitsentirety.
// shared/domain/events/DomainEvents.ts
| import  | { IDomainEvent   |                   | } from | "./IDomainEvent"         |                      |     |      |
| ------- | ---------------- | ----------------- | ------ | ------------------------ | -------------------- | --- | ---- |
| import  | { AggregateRoot  |                   | }      | from "../AggregateRoot"  |                      |     |      |
| import  | { UniqueEntityID |                   | }      | from "../UniqueEntityID" |                      |     |      |
| export  | class            | DomainEvents      |        | {                        |                      |     |      |
| private | static           | handlersMap       |        | = {}                     |                      |     |      |
| private | static           | markedAggregates: |        |                          | AggregateRoot<any>[] |     | = [] |
315

/**
| * @method markAggregateForDispatch |     |     |     |     |
| ---------------------------------- | --- | --- | --- | --- |
* @static
* @desc Called by aggregate root objects that have created domain
* events to eventually be dispatched when the infrastructure commits
| * the unit of | work. |     |     |     |
| ------------- | ----- | --- | --- | --- |
*/
public static markAggregateForDispatch(aggregate: AggregateRoot<any>): void {
const aggregateFound = !!this.findMarkedAggregateByID(aggregate.id)
| if (!aggregateFound) | {   |     |     |     |
| -------------------- | --- | --- | --- | --- |
this.markedAggregates.push(aggregate)
}
}
/**
| * @method dispatchAggregateEvents |     |     |     |     |
| --------------------------------- | --- | --- | --- | --- |
* @static
* @private
* @desc Call all of the handlers for any domain events on this aggregate.
*/
private static dispatchAggregateEvents(aggregate: AggregateRoot<any>): void {
| aggregate.domainEvents.forEach((event: |     |     | IDomainEvent) | =>  |
| -------------------------------------- | --- | --- | ------------- | --- |
this.dispatch(event)
)
}
/**
| * @method removeAggregateFromMarkedDispatchList |     |     |     |     |
| ----------------------------------------------- | --- | --- | --- | --- |
* @static
| * @desc Removes | an aggregate | from the | marked list. |     |
| --------------- | ------------ | -------- | ------------ | --- |
*/
| private static                | removeAggregateFromMarkedDispatchList( |     |     |     |
| ----------------------------- | -------------------------------------- | --- | --- | --- |
| aggregate: AggregateRoot<any> |                                        |     |     |     |
): void {
const index = this.markedAggregates.findIndex(a => a.equals(aggregate))
| this.markedAggregates.splice(index, |     |     | 1)  |     |
| ----------------------------------- | --- | --- | --- | --- |
}
/**
| * @method findMarkedAggregateByID |     |     |     |     |
| --------------------------------- | --- | --- | --- | --- |
* @static
316

* @desc Finds an aggregate within the list of marked aggregates.
*/
| private | static | findMarkedAggregateByID( |     |     |     |
| ------- | ------ | ------------------------ | --- | --- | --- |
id: UniqueEntityID
| ): AggregateRoot<any>        |                    | {                         |        |     |     |
| ---------------------------- | ------------------ | ------------------------- | ------ | --- | --- |
| let found:                   | AggregateRoot<any> |                           | = null |     |     |
| for (let                     | aggregate          | of this.markedAggregates) |        |     | {   |
| if (aggregate.id.equals(id)) |                    |                           | {      |     |     |
| found                        | = aggregate        |                           |        |     |     |
}
}
| return | found |     |     |     |     |
| ------ | ----- | --- | --- | --- | --- |
}
/**
| * @method | dispatchEventsForAggregate |     |     |     |     |
| --------- | -------------------------- | --- | --- | --- | --- |
* @static
* @desc When all we know is the ID of the aggregate, call this
* in order to dispatch any handlers subscribed to events on the
* aggregate.
*/
public static dispatchEventsForAggregate(id: UniqueEntityID): void {
| const          | aggregate | = this.findMarkedAggregateByID(id) |     |     |     |
| -------------- | --------- | ---------------------------------- | --- | --- | --- |
| if (aggregate) |           | {                                  |     |     |     |
this.dispatchAggregateEvents(aggregate)
aggregate.clearEvents()
this.removeAggregateFromMarkedDispatchList(aggregate)
}
}
/**
| * @method | register |     |     |     |     |
| --------- | -------- | --- | --- | --- | --- |
* @static
| * @desc | Register | a handler | to a domain | event. |     |
| ------- | -------- | --------- | ----------- | ------ | --- |
*/
| public static   | register( |               |     |       |     |
| --------------- | --------- | ------------- | --- | ----- | --- |
| callback:       | (event:   | IDomainEvent) | =>  | void, |     |
| eventClassName: |           | string        |     |       |     |
| ): void         | {         |               |     |       |     |
if (!this.handlersMap.hasOwnProperty(eventClassName)) {
| this.handlersMap[eventClassName] |     |     |     | = [] |     |
| -------------------------------- | --- | --- | --- | ---- | --- |
317

}
this.handlersMap[eventClassName].push(callback)
}
/**
| * @method | clearHandlers |     |     |     |
| --------- | ------------- | --- | --- | --- |
* @static
| * @desc Useful | for testing. |     |     |     |
| -------------- | ------------ | --- | --- | --- |
*/
| public static    | clearHandlers(): |     | void { |     |
| ---------------- | ---------------- | --- | ------ | --- |
| this.handlersMap | =                | {}  |        |     |
}
/**
| * @method | clearMarkedAggregates |     |     |     |
| --------- | --------------------- | --- | --- | --- |
* @static
| * @desc Useful | for testing. |     |     |     |
| -------------- | ------------ | --- | --- | --- |
*/
| public static         | clearMarkedAggregates(): |      | void | {   |
| --------------------- | ------------------------ | ---- | ---- | --- |
| this.markedAggregates |                          | = [] |      |     |
}
/**
| * @method | dispatch |     |     |     |
| --------- | -------- | --- | --- | --- |
* @static
* @desc Invokes all of the subscribers to a particular domain event.
*/
| private static        | dispatch(event: |        | IDomainEvent):           | void { |
| --------------------- | --------------- | ------ | ------------------------ | ------ |
| const eventClassName: |                 | string | = event.constructor.name |        |
if (this.handlersMap.hasOwnProperty(eventClassName)) {
| const    | handlers: any[] | = this.handlersMap[eventClassName] |     |     |
| -------- | --------------- | ---------------------------------- | --- | --- |
| for (let | handler         | of handlers)                       | {   |     |
handler(event)
}
}
}
}
MarkinganAggregatethatjustcreatedDomainEvents
TheDomainEventssubjectneedsacleanandclearwaytoholdontotheAggregatesthatjust
createdDomainEvents.
318

ThemarkAggregateForDispatch()methodtakesintheAggregateRootthatjustcreatedan
markedAggregates.
event,andplacesitintoanarrayof
// shared/domain/events/DomainEvents.ts
| export class | DomainEvents             | {     |                      |       |
| ------------ | ------------------------ | ----- | -------------------- | ----- |
| private      | static handlersMap       | = {}; |                      |       |
| private      | static markedAggregates: |       | AggregateRoot<any>[] | = []; |
/**
| * @method | markAggregateForDispatch |     |     |     |
| --------- | ------------------------ | --- | --- | --- |
* @static
* @desc Called by aggregate root objects that have created domain
* events to eventually be dispatched when the infrastructure commits
| * the unit | of work. |     |     |     |
| ---------- | -------- | --- | --- | --- |
*/
public static markAggregateForDispatch (aggregate: AggregateRoot<any>): void {
const aggregateFound = !!this.findMarkedAggregateByID(aggregate.id);
| if (!aggregateFound) |     | {   |     |     |
| -------------------- | --- | --- | --- | --- |
this.markedAggregates.push(aggregate);
}
}
...
}
Howtosignalthatthetransactioncompleted
Whenwe’resurethatthetransactionhascompleted,theDomainEventssubjectprovidesa
methodtonotifyallObserversofeachDomainEventofitsoccurrence.
// shared/domain/events/DomainEvents.ts
| export class | DomainEvents             | {     |                      |       |
| ------------ | ------------------------ | ----- | -------------------- | ----- |
| private      | static handlersMap       | = {}; |                      |       |
| private      | static markedAggregates: |       | AggregateRoot<any>[] | = []; |
...
/**
| * @method | dispatchEventsForAggregate |     |     |     |
| --------- | -------------------------- | --- | --- | --- |
* @static
* @desc When all we know is the ID of the aggregate, call this
* in order to dispatch any handlers subscribed to events on the
* aggregate.
*/
319

public static dispatchEventsForAggregate(id: UniqueEntityID): void {
| const aggregate | = this.findMarkedAggregateByID(id); |     |     |     |     |
| --------------- | ----------------------------------- | --- | --- | --- | --- |
| if (aggregate)  | {                                   |     |     |     |     |
this.dispatchAggregateEvents(aggregate);
aggregate.clearEvents();
this.removeAggregateFromMarkedDispatchList(aggregate);
}
}
| private static | removeAggregateFromMarkedDispatchList( |     |     |     |     |
| -------------- | -------------------------------------- | --- | --- | --- | --- |
| aggregate:     | AggregateRoot<any>                     |     |     |     |     |
| ): void {      |                                        |     |     |     |     |
const index = this.markedAggregates.findIndex(a => a.equals(aggregate));
| this.markedAggregates.splice(index, |     |     |     | 1); |     |
| ----------------------------------- | --- | --- | --- | --- | --- |
}
/**
| * @method | dispatchAggregateEvents |     |     |     |     |
| --------- | ----------------------- | --- | --- | --- | --- |
* @static
* @private
* @desc Call all of the handlers for any domain events on this aggregate.
*/
private static dispatchAggregateEvents(aggregate: AggregateRoot<any>): void {
| aggregate.domainEvents.forEach((event: |     |     |     | IDomainEvent) | =>  |
| -------------------------------------- | --- | --- | --- | ------------- | --- |
this.dispatch(event)
);
}
/**
| * @method | dispatch |     |     |     |     |
| --------- | -------- | --- | --- | --- | --- |
* @static
* @desc Invokes all of the subscribers to a particular domain event.
*/
| private static                                       | dispatch(event: |                                     | IDomainEvent):            | void { |     |
| ---------------------------------------------------- | --------------- | ----------------------------------- | ------------------------- | ------ | --- |
| const eventClassName:                                |                 | string                              | = event.constructor.name; |        |     |
| if (this.handlersMap.hasOwnProperty(eventClassName)) |                 |                                     |                           |        | {   |
| const                                                | handlers: any[] | = this.handlersMap[eventClassName]; |                           |        |     |
| for (let                                             | handler         | of handlers)                        | {                         |        |     |
handler(event);
}
}
}
320

}
HowtoregisterahandlertoaDomainEvent?
To register a handler to Domain Event, from another class, we can pass a callback to the
registermethodalongwiththeDomainEventwe’reinterestedinbeingnotifiedabout.
// shared/domain/events/DomainEvents.ts
| export class | DomainEvents |                   | {   |                      |     |       |
| ------------ | ------------ | ----------------- | --- | -------------------- | --- | ----- |
| private      | static       | handlersMap       | =   | {};                  |     |       |
| private      | static       | markedAggregates: |     | AggregateRoot<any>[] |     | = []; |
...
| public static                                         |         | register(     |     |          |     |     |
| ----------------------------------------------------- | ------- | ------------- | --- | -------- | --- | --- |
| callback:                                             | (event: | IDomainEvent) |     | => void, |     |     |
| eventClassName:                                       |         | string        |     |          |     |     |
| ): void                                               | {       |               |     |          |     |     |
| if (!this.handlersMap.hasOwnProperty(eventClassName)) |         |               |     |          |     | {   |
| this.handlersMap[eventClassName]                      |         |               |     | = [];    |     |     |
}
this.handlersMap[eventClassName].push(callback);
}
...
}
ItacceptsbothacallbackfunctionandtheeventClassName,whichisthenameoftheclass
(wecangetthatusingClass.name).
Whenweregisterahandlerforadomainevent,itgetsaddedtothehandlersMap.
For3differentdomaineventsand7differenthandlers,thedatastructureforthehandler’s
mapcanenduplookinglikethis:
| // The handlersMap |            | is an | Identity | map of Domain | Event | names |
| ------------------ | ---------- | ----- | -------- | ------------- | ----- | ----- |
| // to callback     | functions. |       |          |               |       |       |
{
| "UserCreated": |     | [Function, | Function,  | Function], |     |     |
| -------------- | --- | ---------- | ---------- | ---------- | --- | --- |
| "UserEdited":  |     | [Function, | Function], |            |     |     |
| "PostCreated": |     | [Function, | Function]  |            |     |     |
}
Here’sanexampleofahandlerthatsubscribestoadomainevent.
// modules/users/subscriptions/afterUserCreated.ts
import { IHandle } from "../../../core/domain/events/IHandle"
321

import { DomainEvents } from "../../../core/domain/events/DomainEvents"
import { UserCreated } from "../../users/domain/events/userCreated"
| import | { User } from | "../../users/domain/user" |     |     |
| ------ | ------------- | ------------------------- | --- | --- |
export class AfterUserCreated implements IHandle<UserCreated> {
| constructor() | {   |     |     |     |
| ------------- | --- | --- | --- | --- |
this.setupSubscriptions()
}
| setupSubscriptions(): |             | void       | {     |     |
| --------------------- | ----------- | ---------- | ----- | --- |
| //                    | Register to | the domain | event |     |
DomainEvents.register(this.onUserCreated.bind(this), UserCreated.name)
}
private async onUserCreatedEvent(event: UserCreated): Promise<void> {
| const | { user | } = event |     |     |
| ----- | ------ | --------- | --- | --- |
/**
| *   | Do something | with the | domain event, | like |
| --- | ------------ | -------- | ------------- | ---- |
| *   | invoke a use | case     |               |      |
*/
}
}
Whodictateswhenatransactioniscomplete?
This tends to be one of the more challenging things to understand. What should call
dispatchEventsForAggregate(aggregateId: UniqueEntityID)method?
ShouldwecallitattheendofeveryapplicationlayerUseCase?
ShouldwemodeltheUnitofWorkpatternandbuilditintothat?
For most simple scenarios, I leave this single responsibility of knowing if the transaction
wassuccessfulintheORMbeingusedintheproject.
The thing is, a lot of these ORMs actually have mechanisms built in to execute code after
| thingsgetsavedtothedatabase. |     |     | They’reusuallycalledhooks. |     |
| ---------------------------- | --- | --- | -------------------------- | --- |
Forexample,theSequelizedocshashooksforeachoftheselifecycleevents.
(1)
| beforeBulkCreate(instances, |     |     | options) |     |
| --------------------------- | --- | --- | -------- | --- |
beforeBulkDestroy(options)
beforeBulkUpdate(options)
(2)
| beforeValidate(instance, |     |     | options) |     |
| ------------------------ | --- | --- | -------- | --- |
(-)
validate
(3)
322

| afterValidate(instance, |     | options) |     |     |
| ----------------------- | --- | -------- | --- | --- |
- or -
| validationFailed(instance, |     | options, | error) |     |
| -------------------------- | --- | -------- | ------ | --- |
(4)
| beforeCreate(instance,  | options) |          |     |     |
| ----------------------- | -------- | -------- | --- | --- |
| beforeDestroy(instance, |          | options) |     |     |
| beforeUpdate(instance,  | options) |          |     |     |
| beforeSave(instance,    | options) |          |     |     |
| beforeUpsert(values,    | options) |          |     |     |
(-)
create
destroy
update
(5)
| afterCreate(instance,  | options) |     |     |     |
| ---------------------- | -------- | --- | --- | --- |
| afterDestroy(instance, | options) |     |     |     |
| afterUpdate(instance,  | options) |     |     |     |
| afterSave(instance,    | options) |     |     |     |
| afterUpsert(created,   | options) |     |     |     |
(6)
| afterBulkCreate(instances, |     | options) |     |     |
| -------------------------- | --- | -------- | --- | --- |
afterBulkDestroy(options)
afterBulkUpdate(options)
We’reinterestedintheonesin(5).
Ifthisisthecase,usingSequelize,wecandefineacallbackfunctionforeachhookthattakes
themodelnameandtheprimarykeyfieldinordertodispatchtheeventsfortheaggregate.
// infra/sequelize/hooks/index.ts
| import models | from "../models" |     |     |     |
| ------------- | ---------------- | --- | --- | --- |
import { DomainEvents } from "../../../core/domain/events/DomainEvents"
import { UniqueEntityID } from "../../../core/domain/UniqueEntityID"
const dispatchEventsCallback = (model: any, primaryKeyField: string) => {
const aggregateId = new UniqueEntityID(model[primaryKeyField])
DomainEvents.dispatchEventsForAggregate(aggregateId)
}
| (async function | createHooksForAggregateRoots() |     |     | {   |
| --------------- | ------------------------------ | --- | --- | --- |
| const { User    | } = models                     |     |     |     |
User.addHook("afterCreate", (m: any) => dispatchEventsCallback(m, "user_id"))
User.addHook("afterDestroy", (m: any) => dispatchEventsCallback(m, "user_id"))
User.addHook("afterUpdate", (m: any) => dispatchEventsCallback(m, "user_id"))
User.addHook("afterSave", (m: any) => dispatchEventsCallback(m, "user_id"))
User.addHook("afterUpsert", (m: any) => dispatchEventsCallback(m, "user_id"))
323

})()
Thebenefitofthisapproachisitsabilitytokeeptheinfrastructuralconcernsofatrans-
actionoutoftheapplicationanddomainlayers.
Want to learn more?: For a more detailed discussion on how to decoupling
business logic, design transactions, and signal their completion using this ap-
proach,read“DecouplingLogicwithDomainEvents[Guide]-Domain-Driven
Designw/TypeScript”.
Allofthismightbegthequestion,
WhynotuseNode.jsEventEmitter?
To be fair, you totally could! And I encourage you to build your own Domain Events Sub-
ject yourself (no copy-pasting). You may find that it works better for your needs. I enjoy
thestrict-typingandsimplicityofUdi’soriginalapproach. They’rejustplainol’TypeScript
objects.
Hopefully,you’restillfollowingalongandstartingtounderstandhowthisallworks. Ifnot,
hanginthere.Inthenextsection,I’lltakeyouthroughtheprogramexecutionthatdescribes
exactlyhowaMembergetscreated.
Feature1: CreatingaMember
OK, so remember the Event Stormed model that we created earlier? Remember how a
Member gets created? A Member gets created within the Forum subdomain when it hears a
UserCreated event. This means that we’re going to need a way to publish domain events
between subdomains. It also means we’re going to need a way to subscribe to Domain
Eventsthatwe’reinterestedinsothatwecanchaintheexecutionofcommands.
324

| Alright,enoughtheory. |     | Let’sgetintosomecode. |     |     |     |
| --------------------- | --- | --------------------- | --- | --- | --- |
IssuinganAPIrequest
ThetransactionstartsrightfromwhenanAPIrequestcomesintooursystemandroutesto
acontrolleroraresolver.
ThetransactionstartsrightfromwhenanAPIrequestcomesintooursystemandroutesto
acontrolleroraresolver.
| # Using | CURL to | send an | HTTP POST | to a RESTful | API |
| ------- | ------- | ------- | --------- | ------------ | --- |
| curl -d | \       |         |           |              |     |
'{"email":"khalil@apollographql.com", "password":"changeit"}' \
| -H "Content-Type: |                                       | application/json" |     | \   |     |
| ----------------- | ------------------------------------- | ----------------- | --- | --- | --- |
| -X POST           | https://api.dddforum.com/v1/users/new |                   |     |     |     |
Ifwe’reintoGraphQL,itmightlookmorelikethis.
| # Using  | a GraphQL   | mutation | to issue | issue a | command |
| -------- | ----------- | -------- | -------- | ------- | ------- |
| # into   | our system. |          |          |         |         |
| mutation | {           |          |          |         |         |
createUser(email: "khalil@apollographql.com", password: "changeit") {
accessToken
refreshToken
}
}
325

Nonetheless,becausewe’reusingaHexagonalArchitecture,thetypeofAPIthatweusedoesn’t
affecthowourcommandworks. Itjustaffectswhatcallsourcommand. Let’snotforgetthat
GraphQL and RESTful APIs, while we can spend a lot of time on their design, are simply
APIstylesthatbelongtotheInfrastructureLayerandshouldhavezerosayastohowcodefrom
withinthecriticallayersofourprojectworks(application&domain).
| //     | Using | GraphQL | to execute | the | CreateUser |     | use | case. |     |
| ------ | ----- | ------- | ---------- | --- | ---------- | --- | --- | ----- | --- |
| import | {     |         |            |     |            |     |     |       |     |
createUserUseCase
| } from | '../../users/useCases/createUser' |     |     |     |     |     |     |     |     |
| ------ | --------------------------------- | --- | --- | --- | --- | --- | --- | --- | --- |
...
| const      | server      | =       | new ApolloServer({                      |       |          |          |     |             |         |
| ---------- | ----------- | ------- | --------------------------------------- | ----- | -------- | -------- | --- | ----------- | ------- |
| context:   |             | () =>   | ({ createUserUseCase                    |       |          | }),      |     |             |         |
| resolvers: |             | {       |                                         |       |          |          |     |             |         |
|            | Mutation:   | {       |                                         |       |          |          |     |             |         |
|            | createUser: |         | (obj,                                   | args, | context, | info)    |     | => {        |         |
|            |             | // Pass | execution                               | off   | to a     | Use Case | /   | Application | Service |
|            |             | return  | context.createUserUseCase.execute(args) |       |          |          |     |             |         |
}
}
}
})
WhetheritbethroughanExpress.jscontrolleroraGraphQLresolver,whateverinfrastruc-
ture object handles API requests, it passes execution off to an Application Service (also
knownasaUseCase).
326

Thedirectionofdependenciesflowsinward,asdoestheflowofcontrolinordertoexecute
acommand. Thedomainlayercontainsthehighestpolicylevelofpolicy.
ApplicationServices/UseCases
TheApplicationLayer,whichsitsbetweentheDomainandInfralayers,hasasinglerespon-
sibility.
The role of the application layer is handle all use cases for a particular subdo-
main
UseCases (also known as ApplicationServices) are objects that perform either a COMMAND
or a QUERY against the system. Yes, these are the same commands and queries that we
identifiedusingEventStorming, API-firstdesign, or UseCasemodeling(asthename implies).
WetypicallynameaUseCasebytheparticularcommandorqueryitperforms.
InDDDForum,you’llseeseveraluseCases/folderscontainingtheUseCasesthatwecanper-
formfromwithinthatdomain.
Here’swhattheylooklikeinpractice.
Usecaseinterface
Use Cases are simple in principle. They have an optional request and response, and an
executemethodthattakesinthatrequestandreturnsaresponse.
327

| export interface  |     | UseCase<IRequest, |            | IResponse>         |     | {   |             |     |     |
| ----------------- | --- | ----------------- | ---------- | ------------------ | --- | --- | ----------- | --- | --- |
| execute(request?: |     |                   | IRequest): | Promise<IResponse> |     |     | | IResponse |     |     |
}
CreatinganewUseCaseisaseasyasimplementingthisinterface.
AddingtheCommand(requestobject)
Let’sstartbydefiningtherequestobject(somecallthistheCommandobject).
Sincewe’reworkingonCreateUser,wecanmakeanewUseCasecalledCreateUserUseCaseby
|              |     | UseCase<IRequest, |     | IResponse> |     |           |     |             |             |
| ------------ | --- | ----------------- | --- | ---------- | --- | --------- | --- | ----------- | ----------- |
| implementing | the |                   |     |            |     | interface | and | temporarily | setting the |
twogenerictypestoany.
export class CreateUserUseCase implements UseCase<any, any> {
| public | async | execute(request: |     | any): | Promise<any> |     | {   |     |     |
| ------ | ----- | ---------------- | --- | ----- | ------------ | --- | --- | --- | --- |
| return | null  |                  |     |       |              |     |     |     |     |
}
}
IRequest, the Command object, is the first generic parameter to the UseCase interface. It
allowsustodefinetheshapeofthedatathatwereceivefromtheoutsideworld.
Some also refer to this as the DTO (Data Transfer Object) since it’s data that is being trans-
ferredfromonesystemtoanother.
We need to create a DTO/Command that contains all of the properties required in order to
executetheCreateUserUseCase.
Sincethetaskofthisusecaseissimplytocreateauser,we’ll
goaheadandincludeallthestuffittakesinordertocreateaUser.
InordertobringanewUserintothisworld,it’smandatorythatweincludeusername,email,
password.
and This is something we’d already be acutely aware of due to our time spent
writingunitteststhatconfirmwecanonlycreateaUserwiththosethreeproperties.
| // The CreateUserDTO  |        |               | is the       | input object | for   | our         | Use | Case. |     |
| --------------------- | ------ | ------------- | ------------ | ------------ | ----- | ----------- | --- | ----- | --- |
| // Any infrastructure |        |               | layer        | technology   | can   | execute     | our |       |     |
| // Use Case           | as     | long          | as it passes | in           | these | properties. |     |       |     |
| export interface      |        | CreateUserDTO |              | {            |       |             |     |       |     |
| username:             | string |               |              |              |       |             |     |       |     |
| email:                | string |               |              |              |       |             |     |       |     |
| password:             | string |               |              |              |       |             |     |       |     |
}
UseCase
Let’s add our new type to the first parameter of the to represent the request part
We’llalsochangethetypefortheparameterintheexecute()methodto
ofthiscontract.
representrequestDTOaswell.
// users/createUser/CreateUserUseCase.ts
export class CreateUserUseCase implements UseCase<CreateUserDTO, any> {
328

| public | async        | execute(request: |      | CreateUserDTO): |     | Promise<any> | {   |
| ------ | ------------ | ---------------- | ---- | --------------- | --- | ------------ | --- |
|        | return null; | //               | todo |                 |     |              |     |
}
}
We’vegotourCreateUserUseCasemostlysetup,itisanIntentionRevealingInter-
Great!
face,andallthat’sleftforustodoisimplementthelogicthatcreatesauser.
| Forkintheroad: |     | TransactionScriptvs.DomainModel |     |     |     |     |     |
| -------------- | --- | ------------------------------- | --- | --- | --- | --- | --- |
Let’spause.
Fromhere,wecanproceedintwoways.
ThefirstistheTransactionScriptapproach.
Thisunhingesustowritethecodehoweverwewantinordertomakeitwork. Asaresult,the
codewewriteisquiteimperativelyinnature. Wewriteexactlywhatwewanttohappenwith-
outthesweetnessofanyabstractiontoencapsulatethecomplexity. Ourendgoalistomake
aUserrowappearinadatabasesomewhere. Usingthisapproach,onepossiblesolutionis
todirectlyimportSequelize,TypeORM,orperhapsmysqljsinordertoencodethestepsthat
achieveourgoal.
Thatfinalcodetoachieveourtaskmightlookalittlesomethingasfollows:
// users/createUser/CreateUserUseCase.ts
| //     | In Sequelize,  | all    | of your                            | models are | exported | on a single |     |
| ------ | -------------- | ------ | ---------------------------------- | ---------- | -------- | ----------- | --- |
| //     | models object. |        |                                    |            |          |             |     |
| import | { models       | } from | '../../../infra/sequelize/models'; |            |          |             |     |
export class CreateUserUseCase implements UseCase<CreateUserDTO, void> {
public async execute(request: CreateUserDTO): Promise<void> {
|     | const isUsernameValid                           |                     |                   | = !!request.username |          | === true |     |
| --- | ----------------------------------------------- | ------------------- | ----------------- | -------------------- | -------- | -------- | --- |
|     | && UserUtils.isValidUsername(request.username); |                     |                   |                      |          |          |     |
|     | const isEmailValid                              |                     | = !!request.email |                      | ===      | true     |     |
|     | && UserUtils.isValidEmail(request.email);       |                     |                   |                      |          |          |     |
|     | const isPasswordPresent                         |                     |                   | = !!request.password |          |          |     |
|     | && UserUtils.isValidPassword(request.password); |                     |                   |                      |          |          |     |
|     | if (!isUsernameValid)                           |                     |                   | {                    |          |          |     |
|     | throw                                           | new Error("Username |                   | is not               | valid"); |          |     |
}
|     | if (!isEmailValid) |                  | {   |                 |     |     |     |
| --- | ------------------ | ---------------- | --- | --------------- | --- | --- | --- |
|     | throw              | new Error("Email |     | is not valid"); |     |     |     |
}
|     | if (!isPasswordPresent) |     |     | {   |     |     |     |
| --- | ----------------------- | --- | --- | --- | --- | --- | --- |
329

|     | throw new | Error("Password | is not valid"); |     |
| --- | --------- | --------------- | --------------- | --- |
}
| try | {                          |                   |     |     |
| --- | -------------------------- | ----------------- | --- | --- |
|     | await models.User.create({ |                   |     |     |
|     | username:                  | request.username, |     |     |
email: request.email,
|     | password: | UserUtils.hashPassword(request.password), |     |     |
| --- | --------- | ----------------------------------------- | --- | --- |
});
| }   | catch (err) | {                 |        |                     |
| --- | ----------- | ----------------- | ------ | ------------------- |
|     | throw new   | Error (`Sequelize | error: | ${err.toString()}`) |
}
}
}
Benefitsofthisapproach:
• It’ssimpleandeasytounderstand.
• It’sfasttoimplement.
Thiscodedoesexactlywhatyou’dexpectittodo. Itmakesarowappearinthedatabaseand
does some validation checks before that. I call this the bruteforce of application layer use
cases.
Nowletmeask…Doyouseeanythingwrongwiththisapproach? Takeamomenttothink
aboutit. Lookatthecodeandseehowmanypotentialissuesyoucanfind.
Thereare3bigones.
Disadvantagesofthisapproach:
1. We’vecreatedahardsource-codedependencytotheSequelizemodelsbyreferenc-
ingitdirectly.
| // Hard | source-code | dependency                                | to Sequelize | models. |
| ------- | ----------- | ----------------------------------------- | ------------ | ------- |
| import  | { models    | } from '../../../infra/sequelize/models'; |              |         |
Becausewereferenceoursequelizemodelsdirectlylikethis,ifweeverwantedtoruntests
againstourCreateUserUseCase(whichwemostcertainlywillwanttodo),that’llmakeour
classbringthetheentiredatabaseconnectionwithiteverytimewecreateaninstanceof
CreateUserUseCase.
Khalil’s Class-level Dependency Methodology: If, from one class, you need
toreferanother,beawareofthedependencyrelationshipthatitcreates. Doso
onlyif thedependencyiseitheranabstraction(suchasaninterfaceorabstract
class),isfromaninnerlayer(asperTheDependencyRule),orisastabledepen-
dency. Most importantly, abstain from using hard source-code dependencies
onclassesthatyouwanttotest. Read“HowIWriteTestableCode|Khalil’sSim-
pleMethodology”foramorein-depthdiscussion.
2. Ourdomainmodelisanemicandfailstoencapsulatemandatoryvalidationlogicto
createaUser.
330

Adomainmodelisanemicwhenservices(oranynon-domainlayerclasses)containallthe
domainlogic,yetthedomainobjectsthemselvescontainpracticallynone. Intheprevious
example,UserUtils,autilityclass,containsthebusinessrulesthatdictatehowtocreatea
username,email,andpassword.
Thisisn’tideal. Becauseofthelackofencapsulation,itopensupthesurfaceareaforsome-
onetobeabletocreateaUserwithoutadheringtotheUservalidation/creationrules.
ConsiderifwenextneededtodeveloptheEditUserUseCase. Howeasywoulditbetoforget
tofirstvalidatethattheusernamewasvalidbyutilizingUserUtils.isValidUsername(name:
string)?
// users/editUser/EditUserUseCase.ts
| //     | In Sequelize,  | all    | of your                            | models are | exported | on a single |     |
| ------ | -------------- | ------ | ---------------------------------- | ---------- | -------- | ----------- | --- |
| //     | models object. |        |                                    |            |          |             |     |
| import | { models       | } from | '../../../infra/sequelize/models'; |            |          |             |     |
export class EditUserUseCase implements UseCase<EditUserDTO, void> {
| public | async                                           | execute(request: |                   | EditUserDTO):        |          | Promise<void> | {   |
| ------ | ----------------------------------------------- | ---------------- | ----------------- | -------------------- | -------- | ------------- | --- |
|        | // Username                                     | validation       |                   | missing.             |          |               |     |
|        | const isEmailValid                              |                  | = !!request.email |                      | ===      | true          |     |
|        | && UserUtils.isValidEmail(request.email);       |                  |                   |                      |          |               |     |
|        | const isPasswordPresent                         |                  |                   | = !!request.password |          |               |     |
|        | && UserUtils.isValidPassword(request.password); |                  |                   |                      |          |               |     |
|        | // Throwing                                     | user             | validation        | error                | missing. |               |     |
|        | if (!isEmailValid)                              |                  | {                 |                      |          |               |     |
|        | throw                                           | new Error("Email |                   | is not valid");      |          |               |     |
}
|     | if (!isPasswordPresent) |                     |     | {      |          |     |     |
| --- | ----------------------- | ------------------- | --- | ------ | -------- | --- | --- |
|     | throw                   | new Error("Password |     | is not | valid"); |     |     |
}
}
}
With this approach, not only do we need to remember to include validation logic, but this
mandatorybusinessrulenowneedstobemaintainedinatleasttwoseparateplaces. Repeti-
tion. Notgood.
IfwewantedtokeepthecodeDRYandenforcetherules,thelogicshouldbeapartofaUser
However,inthiscase,thereisn’tevenaUserdomainmodeltoencapsulate
domainmodel.
| ruleswithin. | There’sjusttherawSequelizeORMmodel. |     |     |     |     |     |     |
| ------------ | ----------------------------------- | --- | --- | --- | --- | --- | --- |
3. Thereareseriousproblemswitherrorhandling.
331

Throwingerrorsisn’talwaysthathelpful. Butwhy? Moreonthisinamoment.
ThesecondapproachistouseaDomainModel.
Withthisapproach,we’regoingtorespecttheseparationofconcernsoftheLayeredArchitec-
ture,encapsulatebusinessrulesinmodels,andreducethesurfaceareaofbeingabletowrite
codethatbreaksthoserules.
Beforewecontinue,weshouldtalkalittlebitaboutErrorsinarichdomainmodel.
Whatfollowsisanapproachwecanusetohandleerrorsandrepresentthemexplicitlyas
domainconcepts.
Handlingerrorsasdomainconcepts
Inmostprogrammingprojects,there’sconfusionastohowandwhereerrorsshouldbehan-
dled.
Errorsaccountofalargeportionofourapplication’spossiblestates, andmoreoftenthan
not,it’soneofthelastthingsconsidered.
When we encounter some code that will probably result in a non-optimal state, we often
askourselvesquestionslike:
• DoIthrowanerrorandlettheclientfigureouthowtohandleit?
• DoIreturnnull?
Neitherofthesearefantasticapproaches.
Whenwethrowerrors,wedisrupttheflowoftheprogramandmakeittrickierforsomeone
towalkthroughthecode,sincebreakingthenaturalflowofaprogramwitherrorsshares
similaritiestothesometimescriticizedGOTOcommandinolderprogramminglanguages.
Andwhenwereturnnull,we’rebreakingthedesignprinciplethat“amethodshouldre-
turnasingletype”. Notadheringtothiscanresultinmoreerrors,trustissues,andcanlead
tothemisuseofourmethodsfromclients.
It’sfunnythatwedon’treallyknowhowtotreaterrorswhentheyaccountforsomuchpro-
grambehavior.
It’salsoveryoftenthatforasingleusecase, thereareatleastoneormorewaysthatthe
usecasecouldfail. Forexample,creatingausercouldfailforoneofthesetworeasons:
1. Theuseralreadyexists
2. Theusernamehasbeentaken
Andthatdoesn’tevenaccountforunexpectederrorsorvalidationerrors.
It’stimetounderstandthatsomeerrorsaredomainconcepts.
Errors have a place in the domain, and they deserve to be modeled as domain
concepts
Whenweexpress our errors as domain concepts, our domain model becomes a lot richer,
saysalotmoreabouttheactualproblemdomain,andwecanconstructareturntypethat
forcestheclienttodosomethingwithanerror.
332

Thatleadstoimprovedreadabilityandfewerbugs.
Here’showIliketomodelmyerrorsexpressively.
IcreateabaseUseCaseErrorclassthatrepresentserrorsthatcouldhappenforanyusecase
(obviously).
// shared/core/UseCaseError.ts
| interface | IUseCaseError |     | {   |     |     |     |
| --------- | ------------- | --- | --- | --- | --- | --- |
| message:  | string        |     |     |     |     |     |
}
export abstract class UseCaseError implements IUseCaseError {
| public               | readonly | message:  | string  |     |     |     |
| -------------------- | -------- | --------- | ------- | --- | --- | --- |
| constructor(message: |          |           | string) | {   |     |     |
| this.message         |          | = message |         |     |     |     |
}
}
ThenIcreateanerrornamespace,writingtheusecase-specificwaysthatausecasecanfail.
CheckoutwhatImeanbelow.
// users/useCases/createUser/CreateUserErrors.ts
import { UseCaseError } from "../../../../shared/core/UseCaseError"
| import | { Result  | } from           | "../../../../shared/core/Result" |     |     |     |
| ------ | --------- | ---------------- | -------------------------------- | --- | --- | --- |
| export | namespace | CreateUserErrors |                                  | {   |     |     |
export class EmailAlreadyExistsError extends Result<UseCaseError> {
| constructor(email: |              |     | string) | {   |     |     |
| ------------------ | ------------ | --- | ------- | --- | --- | --- |
|                    | super(false, | {   |         |     |     |     |
message: `The email ${email} associated for this account already exists`,
|     | } as UseCaseError) |     |     |     |     |     |
| --- | ------------------ | --- | --- | --- | --- | --- |
}
}
export class UsernameTakenError extends Result<UseCaseError> {
| constructor(username: |                    |      | string)  | {           |             |         |
| --------------------- | ------------------ | ---- | -------- | ----------- | ----------- | ------- |
|                       | super(false,       | {    |          |             |             |         |
|                       | message:           | `The | username | ${username} | was already | taken`, |
|                       | } as UseCaseError) |      |          |             |             |         |
}
}
}
Withtheseerrorsdefined,IcanconstructareturntypefortheUseCasethatadequatelysays,
“Whatyou’regoingtogetbackfromthismethodisanobjectthatiseitheroneof
333

allpossibleerrorstatesORthesuccessvalue.”
Checkitout.
import { Either, Result } from "../../../../shared/core/Result";
import { CreateUserErrors } from "./CreateUserErrors";
import { AppError } from "../../../../shared/core/AppError";
export type CreateUserResponse = Either<
CreateUserErrors.EmailAlreadyExistsError |
CreateUserErrors.UsernameTakenError |
AppError.UnexpectedError |
Result<any>,
Result<void>
Youprobablyhavealotofquestionsabouthowthisworks.
I’mgoingtodirectyoutotworesourcesthatI’dlikeyoutoread.
Resource 1: “Flexible Error Handling w/ the Result Class | Enterprise Node.js
+ TypeScript”. This explains the Result class, which is foundational to how we
functionallyhandleerrors.
Resource2: “FunctionalErrorHandlingwithExpress.jsandDDD|Enterprise
Node.js+TypeScript”. ThisshowsyouhowtoutilizetheEithermonadtosegre-
gatesuccessstatesfromfailurestates.
Readthosefirst,thencontinue.
SummaryonUseCases/ApplicationServices
Usecasesareinfrastructure-layerconcernagnostic. Ifwedesignthemaslittlemodulesthat
aslongaswecanprovidethecorrectinputs,they’llknowhowtoexecutethefeaturesofour
system,theycanstayagnostictowhatAPIstyleexecutesthem.
You’llnoticethatintheDDDForumcodebase,asingleuseCase/folderhasseveralfilesinit.
EachfolderhastheController,theDTO,theUseCaseErrors,andtheUseCaseitself.
This type of co-location of files that are closely related to each other for a common task is
goodforseveralreasons.
334

Ausecasemodulecontainsallofthecomponentsthatitneedsinordertodoitsjob(high
cohesion).
1. Highcohesion
2. Locate-ability
3. Improvedmaintainability
Becauseeachofthesefilesneedeachotherinordertoaccomplishtheirtask,havingthem
closetoeachotherimprovesmaintainability. Thinkaboutotherdevelopersforasecond. Ifwe
organizedallofourcodebyconstructtype,likecontrollers/,errors/,dtos/,andhadevery
singleconstructfromourappinoneofthosefolders,itwouldmakemaintainingthecode
anightmare. We’dhavetoflipbetweenfoldersseveraltimesinordertoupdateafeature.
Bypackagingbymodule,wekeepeverythingforaparticularfeatureascloseaspossible.
Andweexporteverythingthattheoutsideworldneedstoknowabout!
| // The  | createUser | folder    | acts as a module, | exporting |
| ------- | ---------- | --------- | ----------------- | --------- |
| // only | what is    | necessary | for the outside   | world to  |
| // know | about.     |           |                   |           |
// useCases/createUser/index.ts
| import | { CreateUserUseCase |     | } from "./CreateUserUseCase"; |     |
| ------ | ------------------- | --- | ----------------------------- | --- |
import { CreateUserController } from "./CreateUserController";
| import | { userRepo           | } from | "../../repos";                     |     |
| ------ | -------------------- | ------ | ---------------------------------- | --- |
| const  | createUserUseCase    |        | = new CreateUserUseCase(userRepo); |     |
| const  | createUserController |        | = new CreateUserController(        |     |
createUserUseCase
)
| // Just | these two | things! |     |     |
| ------- | --------- | ------- | --- | --- |
| export  | {         |         |     |     |
335

createUserUseCase,
createUserController
}
Seealso: “WhyIDon’tUseaDIContainer|Node.jsw/TypeScript”
InsidetheCreateUserusecasetransaction
AlookinsidetheCreateUserUseCaseshouldhelpyoutounderstandthebigpictureofhow
all the pieces of this architecture work together. Ultimately, the goal is to perform an op-
erationsagainstanAggregateandpublishDomainEventsemittedfromtheAggregatetosub-
scribersinterestedintheiroccurrences.
The figure below is a depiction of how an Application Service/Use Case handles a request
andfacilitatesatransactionagainstanAggregate.
An Application Service/Use Case handling a request to perform a transaction against an
Aggregate.
1. AclientrequestcancomefromanAPIendpoint,passingoffcontroltoanApplication
Service/UseCase.
2. Application Service/Use Case uses the dependency injected repositories in order to
retrieveanyAggregatesneededtocontinuewiththerequest.
3. ApplicationService/UseCasecallstheAggregate’smethodusingparameterssupplied
bytheApplicationService. Ifwe’redealingwithmorethanoneAggregateinthisre-
quest, and the domain logic for our transaction doesn’t belong to a single Aggregate,
wepasstheparameterstoaDomainServiceinstead(seeFeature2-UpvotingaPostfor
example).
336

4. The Aggregate’s method dispatches Domain Events as a side effect. These Event are
addedtotheDomainEventsSubject,animplementationoftheObserverPattern.
5. ThetransactioniscompletedwhentheAggregateissavedtothedatabasesuccessfully.
6. Uponsuccessfuldatabasesave,theDomainEventsSubjectnotifiesObserversbysending
themtheDomainEventsfortheAggregatejustpersistedinthelasttransaction.
Finally,anyothersubdomainswithinourMonolithicapplicationcansubscribetotheDo-
mainEventsbynameaheadoftimeinordertobenotifiedwhenthey’reemitted.
UsinganExpress.jsRouteHandlertodirecttherequest
ForaRESTfulAPI,anExpress.jsroutehandlerisdefinedforthecreateuserendpoint. The
route handler passes control to a createUserController (one of the dependencies we ex-
portfromwithinouruseCases/createUsermodule).
| // Express.js           | RESTful | API | uses   | an exported |            |     |
| ----------------------- | ------- | --- | ------ | ----------- | ---------- | --- |
| // createUserController |         | to  | handle | a post      | to /users. |     |
// users/infra/http/routes/index.ts
| import express | from | 'express' |     |     |     |     |
| -------------- | ---- | --------- | --- | --- | --- | --- |
| import {       |      |           |     |     |     |     |
createUserController
| } from '../../../useCases/createUser'; |     |                   |     |     |     |     |
| -------------------------------------- | --- | ----------------- | --- | --- | --- | --- |
| const userRouter                       | =   | express.Router(); |     |     |     |     |
userRouter.post('/',
| (req, res) | => createUserController.execute(req, |     |     |     |     | res) |
| ---------- | ------------------------------------ | --- | --- | --- | --- | ---- |
);
...
HandlingtheAPIrequestwithanAPIController
Insideoftheusers/useCases/createUserfolder,wefindtheCreateUserController.
TheCreateUserControllerhasasingledependencyinjectedintoit.TheCreateUserUseCase.
It’stheApplicationService/UseCasethatdoesalltheaction.
Theresponsibilitiesofacontrollerareto:
InvoketheCreateUserUseCase.
•
• RespondtotheAPIcallwiththeappropriateHTTPresponsecodebasedontheresult
oftheexecutedusecase.
SeetheCreateUserControllerbelow.
| // CreateUsersController |           |            | - Handling | the request       |     | data from |
| ------------------------ | --------- | ---------- | ---------- | ----------------- | --- | --------- |
| // the internet          | and       | passing    | it         | to the dependency |     | injected  |
| // use case              | class for | execution. |            |                   |     |           |
337

// users/useCases/createUser/CreateUserController.ts
| import | { CreateUserUseCase |     |     | }      | from "./CreateUserUseCase" |     |     |
| ------ | ------------------- | --- | --- | ------ | -------------------------- | --- | --- |
| import | { CreateUserDTO     |     |     | } from | "./CreateUserDTO"          |     |     |
| import | { CreateUserErrors  |     |     | }      | from "./CreateUserErrors"  |     |     |
import { BaseController } from "../../../../shared/infra/http/models/BaseController"
import { TextUtils } from "../../../../shared/utils/TextUtils"
import { DecodedExpressRequest } from "../../infra/http/models/decodedRequest"
| import               | * as  | express              | from              | "express"          |         |                |     |
| -------------------- | ----- | -------------------- | ----------------- | ------------------ | ------- | -------------- | --- |
| export               | class | CreateUserController |                   |                    | extends | BaseController | {   |
| private              |       | useCase:             | CreateUserUseCase |                    |         |                |     |
| constructor(useCase: |       |                      |                   | CreateUserUseCase) |         | {              |     |
super()
|     | this.useCase |     | = useCase |     |     |     |     |
| --- | ------------ | --- | --------- | --- | --- | --- | --- |
}
| async | executeImpl(                |                                |                                   |     |          |                  |     |
| ----- | --------------------------- | ------------------------------ | --------------------------------- | --- | -------- | ---------------- | --- |
|       | req: DecodedExpressRequest, |                                |                                   |     |          |                  |     |
|       | res: express.Response       |                                |                                   |     |          |                  |     |
| ):    | Promise<any>                |                                | {                                 |     |          |                  |     |
|       | let dto:                    | CreateUserDTO                  |                                   | =   | req.body | as CreateUserDTO |     |
|       | dto =                       | {                              |                                   |     |          |                  |     |
|       | username:                   |                                | TextUtils.sanitize(dto.username), |     |          |                  |     |
|       | email:                      | TextUtils.sanitize(dto.email), |                                   |     |          |                  |     |
|       | password:                   |                                | dto.password,                     |     |          |                  |     |
}
|     | try { |     |     |     |     |     |     |
| --- | ----- | --- | --- | --- | --- | --- | --- |
/**
|     | * Execute |     | the CreateUserUseCase |     |     | use case. |     |
| --- | --------- | --- | --------------------- | --- | --- | --------- | --- |
*/
|     | const                | result | = await        | this.useCase.execute(dto) |     |     |     |
| --- | -------------------- | ------ | -------------- | ------------------------- | --- | --- | --- |
|     | if (result.isLeft()) |        |                | {                         |     |     |     |
|     | const                | error  | = result.value |                           |     |     |     |
/**
|     | *   | This    | is an example |       | of how    | one can standardize | a   |
| --- | --- | ------- | ------------- | ----- | --------- | ------------------- | --- |
|     | *   | RESTful | API's         | error | messages. |                     |     |
*
|     | *   | We utilize | the | Use | Case | Error types. |     |
| --- | --- | ---------- | --- | --- | ---- | ------------ | --- |
*/
338

switch (error.constructor) {
case CreateUserErrors.UsernameTakenError:
return this.conflict(error.errorValue().message)
case CreateUserErrors.EmailAlreadyExistsError:
return this.conflict(error.errorValue().message)
default
return this.fail(res, error.errorValue().message)
}
} else {
return this.ok(res)
}
} catch (err) {
return this.fail(res, err)
}
}
}
Like this?: If you like the way this Express.js controller uses declarative re-
sponse methods, check out “Clean & Consistent Express.js Controllers | Enter-
priseNode.js+TypeScript”.
Let’stakealookattheusecasenow.
InvokingtheApplicationService/UseCase
InDomain-DrivenDesign,ApplicationServiceshaveaspecificpurpose.
ThepurposeofanApplicationServicesistogetallthestuffneededinorderfordomainlayer
concernstointeract,thantosavetheresulttopersistence.
Inthefollowingexample,usingInterfaceAdapters(likeIUserRepo),theresponsibilityisto:
• Fetch the Aggregate(s) and any other objects needed in order to execute the domain
logic.
• PasstheobjectstoeitheranAggregate’smethodORaDomainService.
• Savetheresultsofthetransactiontopersistence.
Essentially, anApplicationServiceisthegluethatinteractswithdatabases, caches, andany-
thingelseneededinordertoconnectourfullyencapsulateddomainmodeltotherealworld.
Forexample,inourCreateUserUseCase,weneedawaytodetermineiftheUserwasalready
createdornot,andwealsoneedawaytodetermineiftheusernamefortheUserwe’retrying
tocreatehasalreadybeentaken.
WeneedaUserRepository. Wecangetonebyreferringtotheinterface,thatis-theInter-
faceAdapter. Itdoesn’tmattertotheApplicationService, butwecanimplementoneusing
Sequelize,TypeORM,MongoDB,etc-intheconstructorfortheCreateUserUseCase.
// useCases/createUser/CreateUserUseCase.ts
export class CreateUserUseCase
339

implements UseCase<CreateUserDTO, Promise<CreateUserResponse>> {
| private               | userRepo: |          | IUserRepo; |              |            |
| --------------------- | --------- | -------- | ---------- | ------------ | ---------- |
| // Dependency         |           | injected |            | and inverted | IUserRepo. |
| constructor(userRepo: |           |          |            | IUserRepo)   | {          |
| this.userRepo         |           |          | = userRepo |              |            |
}
...
}
AnimportantconsiderationaboutApplicationServicesisthattheyshouldcontainlittletono
domainlogicatallwithinthem. Anythingthatsmellslikedomainlogicshouldliveineither
anAggregateoraDomainService,butneveranApplicationService.
Thereasonwhywedothisisprimarilytoavoidcodeduplication. Byencapsulatingdomain
logicintheDomainLayer,anytimewewanttoexecuteafeatureviatheApplicationLayer,we
havetooffloadthebusinesslogictotheDomainLayer.
ApplicationServices,er-UseCases,aren’tDomainLayerconstructs. They’reApplicationLayer
constructs. Andifwecreatenewapplications,we’llhavenewUseCases/ApplicationServices. If
there’saformofdomainlogicthat’simportanttosomeofthenewUseCases/ApplicationSer-
vicesbutitliveswithinanoldApplicationService,thatlogicwillneedtobeduplicated. Again,
that’sananemicdomainmodel.
So,wehavetokeepaneyeoutforstuffthatlookslikedomainlogic,andaimtomoveitinto
theAggregateoraDomainServiceitbestbelongswithin.
ApplicationService/UseCasedesigntip: TakeastepbackandlookattheCy-
clomatic Complexity ****of your Application Service/Use Cases. If you notice
there’smorethan2layersofcomplexity(atleast2nestedcontrolblockslikeif,
while,orswitch),there’sagoodchancethatwe’vegotsomeleakydomainlogic.
Here’swhatourCreateUserUseCaseApplicationService/UseCaselookslike.
// users/useCases/createUser/CreateUserUseCase.ts
| import | { CreateUserDTO    |     |     | } from "./CreateUserDTO" |                      |
| ------ | ------------------ | --- | --- | ------------------------ | -------------------- |
| import | { CreateUserErrors |     |     | } from                   | "./CreateUserErrors" |
import { Result, left, right } from "../../../../shared/core/Result"
| import | { AppError           |        | } from              | "../../../../shared/core/AppError" |                        |
| ------ | -------------------- | ------ | ------------------- | ---------------------------------- | ---------------------- |
| import | { IUserRepo          |        | } from              | "../../repos/userRepo"             |                        |
| import | { UseCase            | }      | from                | "../../../../shared/core/UseCase"  |                        |
| import | { UserEmail          |        | } from              | "../../domain/userEmail"           |                        |
| import | { UserPassword       |        | }                   | from "../../domain/userPassword"   |                        |
| import | { UserName           |        | } from              | "../../domain/userName"            |                        |
| import | { User               | } from | "../../domain/user" |                                    |                        |
| import | { CreateUserResponse |        |                     | } from                             | "./CreateUserResponse" |
340

export class CreateUserUseCase implements UseCase<CreateUserDTO, Promise<CreateUserResponse>> {
| private userRepo:     |     | IUserRepo  |            |          |     |            |     |     |
| --------------------- | --- | ---------- | ---------- | -------- | --- | ---------- | --- | --- |
| // Dependency         |     | injected   | and        | inverted |     | IUserRepo. |     |     |
| constructor(userRepo: |     |            | IUserRepo) |          | {   |            |     |     |
| this.userRepo         |     | = userRepo |            |          |     |            |     |     |
}
async execute(request: CreateUserDTO): Promise<CreateUserResponse> {
// Run validation logic on each of the inputs needed to create
| // a user | by  | creating |     | their | Value | Objects. |     |     |
| --------- | --- | -------- | --- | ----- | ----- | -------- | --- | --- |
const emailOrError: Result<UserEmail> = UserEmail.create(request.email)
const passwordOrError: Result<UserPassword> = UserPassword.create({
| value: | request.password, |     |     |     |     |     |     |     |
| ------ | ----------------- | --- | --- | --- | --- | --- | --- | --- |
})
| const usernameOrError: |                   |     |     | Result<UserName> |     |     | = UserName.create({ |     |
| ---------------------- | ----------------- | --- | --- | ---------------- | --- | --- | ------------------- | --- |
| name:                  | request.username, |     |     |                  |     |     |                     |     |
})
| // Determine    |     | if they | are              | valid. |     |     |     |     |
| --------------- | --- | ------- | ---------------- | ------ | --- | --- | --- | --- |
| const dtoResult |     | =       | Result.combine([ |        |     |     |     |     |
emailOrError,
passwordOrError,
usernameOrError,
])
| if (dtoResult.isFailure) |                                          |     |     | {   |     |     |     |     |
| ------------------------ | ---------------------------------------- | --- | --- | --- | --- | --- | --- | --- |
| return                   | left(Result.fail<void>(dtoResult.error)) |     |     |     |     |     |     |     |
}
| // If each      | value |              | object | is valid,                    |                              | we can  | continue | with the      |
| --------------- | ----- | ------------ | ------ | ---------------------------- | ---------------------------- | ------- | -------- | ------------- |
| // request.     |       | We pull      | the    | results                      | out                          | so that | we       | can use them. |
| const email:    |       | UserEmail    |        | = emailOrError.getValue()    |                              |         |          |               |
| const password: |       | UserPassword |        |                              | = passwordOrError.getValue() |         |          |               |
| const username: |       | UserName     |        | = usernameOrError.getValue() |                              |         |          |               |
try {
| // Determine           |                   | if   | the    | user already |       | exists.                     | And | if it does, |
| ---------------------- | ----------------- | ---- | ------ | ------------ | ----- | --------------------------- | --- | ----------- |
| // the                 | use               | case | should | fail         | with  | a EmailAlreadyExistsError   |     |             |
| const                  | userAlreadyExists |      |        | =            | await | this.userRepo.exists(email) |     |             |
| if (userAlreadyExists) |                   |      |        | {            |       |                             |     |             |
return left(new CreateUserErrors.EmailAlreadyExistsError(email.value))
}
341

| // Determine | if       | the username |           | is already            | in use. | If it does, |
| ------------ | -------- | ------------ | --------- | --------------------- | ------- | ----------- |
| // the       | use case | should       | fail with | a UsernameTakenError. |         |             |
try {
const alreadyCreatedUserByUserName = await this.userRepo.getUserByUserName(
username
)
const userNameTaken = !!alreadyCreatedUserByUserName === true
| if (userNameTaken) |     | {   |     |     |     |     |
| ------------------ | --- | --- | --- | --- | --- | --- |
return left(new CreateUserErrors.UsernameTakenError(username.value))
}
| } catch            | (err) {}    |              |               |                 |       |            |
| ------------------ | ----------- | ------------ | ------------- | --------------- | ----- | ---------- |
| // If all          | is well,    | we           | get to        | create the      | user. |            |
| // Behind          | the scenes, |              | a UserCreated | domain          | event | is created |
| // and             | the User    | aggregate    | is            | marked.         |       |            |
| const userOrError: |             | Result<User> |               | = User.create({ |       |            |
email,
password,
username,
})
| if (userOrError.isFailure) |                                                       |     | {   |     |     |     |
| -------------------------- | ----------------------------------------------------- | --- | --- | --- | --- | --- |
| return                     | left(Result.fail<User>(userOrError.error.toString())) |     |     |     |     |     |
}
| const user: | User | = userOrError.getValue() |     |     |     |     |
| ----------- | ---- | ------------------------ | --- | --- | --- | --- |
// Finally, we can complete the request by passing it off to a
| // repository | to  | save | it to persistence. |     |     |     |
| ------------- | --- | ---- | ------------------ | --- | --- | --- |
await this.userRepo.save(user)
| return        | right(Result.ok<void>()) |                                |     |     |     |     |
| ------------- | ------------------------ | ------------------------------ | --- | --- | --- | --- |
| } catch (err) | {                        |                                |     |     |     |     |
| return        | left(new                 | AppError.UnexpectedError(err)) |     |     |     |     |
}
}
}
FirstthingtheApplicationService/UseCasedoesiscreateValueObjectsfromthestringprop-
ertiespassedinviatheCreateUserDTO.IfanyofthoseValueObjectsfailtopasstheencapsu-
latedvalidationrules,theyreturnafailedResult<T>object.
Otherwise,ifalloftheValueObjectslooktobeinorder,wecontinuefirstdeterminingifthe
Userhasalreadybeencreated,oriftheusernamehasalreadybeentaken.
342

IftheUseriscompletelynew,we’llgoaheadandcreatetheUserdomainobjectbypassing
inallofthepreviouslycreatedandvalidValueObjects.
Inthebackground,whenwecreateaUser,it:
• CreatestheuniqueidentifierfortheUser(UUID).
• Creates a UserCreated Domain Event and notifies the Domain Events subject that it
wascreated.
Lastly,wecompletethetransactionbypassingtheUserdomainobjectwithaDomainEvent
attachedtoittotheUserRepotosavetopersistence.
SavingtheAggregatewithSequelize
InsidethesavemethodfortheSequelizeUserRepothatimplementsIUserRepo, weusea
MapperinordertoconvertaUserintotheshapeneededtopersistittoSequelize,asperthe
Sequelizedocs.
// users/repos/implementations/sequelizeUserRepo.ts
| import              | { UserMap               | } from    | '../mappers/userMap' |           |     |
| ------------------- | ----------------------- | --------- | -------------------- | --------- | --- |
| export              | class SequelizeUserRepo |           | implements           | IUserRepo | {   |
| private             | models:                 | any;      |                      |           |     |
| constructor(models: |                         | any)      | {                    |           |     |
| this.models         |                         | = models; |                      |           |     |
}
| async | exists(userEmail: |     | UserEmail): | Promise<boolean> | {   |
| ----- | ----------------- | --- | ----------- | ---------------- | --- |
...
}
| async | save(user: | User):                  | Promise<void>            | {   |     |
| ----- | ---------- | ----------------------- | ------------------------ | --- | --- |
| const | UserModel  | = this.models.BaseUser; |                          |     |     |
| const | exists     | = await                 | this.exists(user.email); |     |     |
| if    | (!exists)  | {                       |                          |     |     |
// Create a JSON representation { username, password, userId, email }
|     | const rawSequelizeUser                    |       | = await | UserMap.toPersistence(user); |     |
| --- | ----------------------------------------- | ----- | ------- | ---------------------------- | --- |
|     | // Save the                               | user  | model   |                              |     |
|     | await UserModel.create(rawSequelizeUser); |       |         |                              |     |
| }   | else {                                    |       |         |                              |     |
|     | // Update                                 | logic |         |                              |     |
...
}
return;
}
343

}
Note: NoticethatweimportUserMapdirectly? Normally,wewouldn’tdothat.
ButUserMapisastabledependency.
Here’swhattheUserMaplookslike. PayparticularattentiontothetoPersistencemethod
where we take in a User and convert it to an untyped object. That untyped object is what
Sequelizeneedstosavetheusertothedatabase.
// users/mappers/userMap.ts
| import | { Mapper  | }      | from              | '../../../shared/infra/Mapper' |     |     |     |     |
| ------ | --------- | ------ | ----------------- | ------------------------------ | --- | --- | --- | --- |
| import | { User    | } from | '../domain/user'; |                                |     |     |     |     |
| import | { UserDTO | }      | from              | '../dtos/userDTO';             |     |     |     |     |
import { UniqueEntityID } from '../../../shared/domain/UniqueEntityID';
| import | { UserName       |                      | } from            | '../domain/userName';  |                           |         |     |     |
| ------ | ---------------- | -------------------- | ----------------- | ---------------------- | ------------------------- | ------- | --- | --- |
| import | { UserPassword   |                      | }                 | from                   | '../domain/userPassword'; |         |     |     |
| import | { UserEmail      |                      | } from            | '../domain/userEmail'; |                           |         |     |     |
| export | class            | UserMap              | implements        |                        | Mapper<User>              |         | {   |     |
| public | static           | toDTO                | (user:            |                        | User):                    | UserDTO | {   |     |
| return | {                |                      |                   |                        |                           |         |     |     |
|        | username:        | user.username.value, |                   |                        |                           |         |     |     |
|        | isEmailVerified: |                      |                   | user.isEmailVerified,  |                           |         |     |     |
|        | isAdminUser:     |                      | user.isAdminUser, |                        |                           |         |     |     |
|        | isDeleted:       | user.isDeleted       |                   |                        |                           |         |     |     |
}
}
| public | static | toDomain |     | (raw: | any): | User | {   |     |
| ------ | ------ | -------- | --- | ----- | ----- | ---- | --- | --- |
const userNameOrError = UserName.create({ name: raw.username });
const userPasswordOrError = UserPassword.create({ value: raw.user_password, hashed: true });
| const                 | userEmailOrError                       |                                 |                    | =                                | UserEmail.create(raw.user_email); |     |     |       |
| --------------------- | -------------------------------------- | ------------------------------- | ------------------ | -------------------------------- | --------------------------------- | --- | --- | ----- |
| const                 | userOrError                            |                                 | =                  | User.create({                    |                                   |     |     |       |
|                       | username:                              | userNameOrError.getValue(),     |                    |                                  |                                   |     |     |       |
|                       | isAdminUser:                           |                                 | raw.is_admin_user, |                                  |                                   |     |     |       |
|                       | isDeleted:                             | raw.is_deleted,                 |                    |                                  |                                   |     |     |       |
|                       | isEmailVerified:                       |                                 |                    | raw.is_email_verified,           |                                   |     |     |       |
|                       | password:                              | userPasswordOrError.getValue(), |                    |                                  |                                   |     |     |       |
|                       | email:                                 | userEmailOrError.getValue(),    |                    |                                  |                                   |     |     |       |
| },                    | new UniqueEntityID(raw.base_user_id)); |                                 |                    |                                  |                                   |     |     |       |
| userOrError.isFailure |                                        |                                 |                    | ? console.log(userOrError.error) |                                   |     |     | : ''; |
return userOrError.isSuccess ? userOrError.getValue() : null;
}
344

public static async toPersistence (user: User): Promise<any> {
| let password:       | string | = null;     |
| ------------------- | ------ | ----------- |
| if (!!user.password |        | === true) { |
if (user.password.isAlreadyHashed()) {
| password | = user.password.value; |                                 |
| -------- | ---------------------- | ------------------------------- |
| } else { |                        |                                 |
| password | = await                | user.password.getHashedValue(); |
}
}
return {
| base_user_id:      | user.userId.id.toString(), |                       |
| ------------------ | -------------------------- | --------------------- |
| user_email:        | user.email.value,          |                       |
| is_email_verified: |                            | user.isEmailVerified, |
| username:          | user.username.value,       |                       |
| user_password:     | password,                  |                       |
| is_admin_user:     | user.isAdminUser,          |                       |
| is_deleted:        | user.isDeleted             |                       |
}
}
}
Mapper<T>classesaresolelyresponsibleformappingentitiesbetweenDomain,Persistence,
andDTOformat.
BackintheSequelizeUserRepo,wethencompletethetransactionusingthatrawobjectwe
getbackfromtheUserMapwithUserModel.create(rawSequelizeUser).
// users/repos/implementations/sequelizeUserRepo.ts
| // Save the user | model |     |
| ---------------- | ----- | --- |
await UserModel.create(rawSequelizeUser);
That’sallweneedtocompletetheentiretransaction.
ButwhatabouttheDomainEvents?
NotifyingsubscribersanddispatchingDomainEventsfromSequelizeHooks
WithSequelize,recallthatwecanmakeuseofthelovelyHollywoodPrinciple(“don’tcallus,
we’llcallyou”),bydefiningcallbacksonthelifecyclehooksthatgetcalledwhenweperform
operationsagainstthedatabase.
// shared/infra/database/sequelize/hooks/index.ts
| import models from | "../models" |     |
| ------------------ | ----------- | --- |
import { UniqueEntityID } from "../../../../domain/UniqueEntityID"
import { DomainEvents } from "../../../../domain/events/DomainEvents"
345

const dispatchEventsCallback = (model: any, primaryKeyField: string) => {
// Get the aggregate id from the Sequelize model just saved/updated.
const aggregateId = new UniqueEntityID(model[primaryKeyField])
// Dispatch any domain events on that aggregate from a previous transaction.
DomainEvents.dispatchEventsForAggregate(aggregateId)
}
| (async function   | createHooksForAggregateRoots() |      |            |     | {   |
| ----------------- | ------------------------------ | ---- | ---------- | --- | --- |
| const { BaseUser, | Member,                        | Post | } = models |     |     |
// Notify subscribers when the User aggregate transactions complete
| BaseUser.addHook("afterCreate", |     |                 | (m: | any) => |     |
| ------------------------------- | --- | --------------- | --- | ------- | --- |
| dispatchEventsCallback(m,       |     | "base_user_id") |     |         |     |
)
| BaseUser.addHook("afterDestroy", |     |                 | (m: | any) | =>  |
| -------------------------------- | --- | --------------- | --- | ---- | --- |
| dispatchEventsCallback(m,        |     | "base_user_id") |     |      |     |
)
| BaseUser.addHook("afterUpdate", |     |                 | (m: | any) => |     |
| ------------------------------- | --- | --------------- | --- | ------- | --- |
| dispatchEventsCallback(m,       |     | "base_user_id") |     |         |     |
)
| BaseUser.addHook("afterSave", |     |                 | (m: any) | =>  |     |
| ----------------------------- | --- | --------------- | -------- | --- | --- |
| dispatchEventsCallback(m,     |     | "base_user_id") |          |     |     |
)
| BaseUser.addHook("afterUpsert", |     |                 | (m: | any) => |     |
| ------------------------------- | --- | --------------- | --- | ------- | --- |
| dispatchEventsCallback(m,       |     | "base_user_id") |     |         |     |
)
// Notify subscribers when the Member aggregate transactions complete
| Member.addHook("afterCreate", |     |              | (m: any) | =>  |     |
| ----------------------------- | --- | ------------ | -------- | --- | --- |
| dispatchEventsCallback(m,     |     | "member_id") |          |     |     |
)
| Member.addHook("afterDestroy", |     |              | (m: any) | =>  |     |
| ------------------------------ | --- | ------------ | -------- | --- | --- |
| dispatchEventsCallback(m,      |     | "member_id") |          |     |     |
)
| Member.addHook("afterUpdate", |     |              | (m: any) | =>  |     |
| ----------------------------- | --- | ------------ | -------- | --- | --- |
| dispatchEventsCallback(m,     |     | "member_id") |          |     |     |
)
| Member.addHook("afterSave", |     | (m:          | any) | =>  |     |
| --------------------------- | --- | ------------ | ---- | --- | --- |
| dispatchEventsCallback(m,   |     | "member_id") |      |     |     |
)
| Member.addHook("afterUpsert", |     |              | (m: any) | =>  |     |
| ----------------------------- | --- | ------------ | -------- | --- | --- |
| dispatchEventsCallback(m,     |     | "member_id") |          |     |     |
)
// Notify subscribers when the Post aggregate transactions complete
Post.addHook("afterCreate", (m: any) => dispatchEventsCallback(m, "post_id"))
346

Post.addHook("afterDestroy", (m: any) => dispatchEventsCallback(m, "post_id"))
Post.addHook("afterUpdate", (m: any) => dispatchEventsCallback(m, "post_id"))
Post.addHook("afterSave", (m: any) => dispatchEventsCallback(m, "post_id"))
Post.addHook("afterUpsert", (m: any) => dispatchEventsCallback(m, "post_id"))
console.log("[Hooks]: Sequelize hooks setup.")
})()
WeusethehookstotellourDomainEventssubjectthatweshouldnotifyallsubscribersto
theparticularDomainEvent(inthiscase,theUserCreatedevent).
Read the docs: If you’re using Sequelize, can read the documentation on Se-
quelizehookshere.
NotusingSequelize? UsingrawSQL?Write’yerowndanghookslibrary: Inthiscase,
yougettheopportunitytowritethisgreatexampleofinversionofcontrolyourself. Usingthe
HollywoodPrinciple,youcouldwrapeachcreate,insert,delete,orupdatewithpre-hooks
andpost-hookswhereyouacceptacallbackfunctionforfuturelogictogetwritten. Don’t
forgettosupplyusefulmetadatatothecallbacksothatwecandosomethingsimilartowhat
we’vedoneusingSequelize.
ChainingtheCreateMembercommandfromtheForumsubdomain
ThetransactionthatcreatedauserintheUserssubdomainhasfinishedexecuting.Thelast
piecetothepuzzleindecouplingbusinesslogicbetweensubdomainsinamodularmonolith
isgettingtheForumsubdomaintoreacttotheUserCreatedevent.
FromtheForumsubdomain,wecancreateaclasstoactasasubscribertotheUserCreated
event. For scan-ability at the folder level, my personal preference is to name these like
After-[event name].
TakealookattheAfterUserCreatedclass.
// forum/subscriptions/afterUserCreated.ts
import { UserCreated } from "../../users/domain/events/userCreated"
import { IHandle } from "../../../shared/domain/events/IHandle"
import { CreateMember } from "../useCases/members/createMember/CreateMember"
import { DomainEvents } from "../../../shared/domain/events/DomainEvents"
export class AfterUserCreated implements IHandle<UserCreated> {
private createMember: CreateMember
constructor(createMember: CreateMember) {
this.setupSubscriptions()
this.createMember = createMember
}
setupSubscriptions(): void {
// Register to the domain event
347

DomainEvents.register(this.onUserCreated.bind(this), UserCreated.name)
}
private async onUserCreated(event: UserCreated): Promise<void> {
| const | { user | } = event |     |     |
| ----- | ------ | --------- | --- | --- |
| try   | {      |           |     |     |
await this.createMember.execute({ userId: user.userId.id.toString() })
console.log(
`[AfterUserCreated]: Successfully executed CreateMember use case AfterUserCreated`
)
| }   | catch (err) | {   |     |     |
| --- | ----------- | --- | --- | --- |
console.log(
`[AfterUserCreated]: Failed to execute CreateMember use case AfterUserCreated.`
)
}
}
}
ThisclassimplementstheIHandle<T>interface,whichisreallyjustanIntentionRevealing
Interface. Itdoesn’tdomuchotherthanhelptosignaltothereaderwhattheclassisfor,and
remindsustoimplementthesetupSubscriptionsmethod.
// shared/domain/events/IHandle.ts
| import                | { IDomainEvent | } from                | "./IDomainEvent"; |     |
| --------------------- | -------------- | --------------------- | ----------------- | --- |
| export                | interface      | IHandle<IDomainEvent> |                   | {   |
| setupSubscriptions(): |                | void;                 |                   |     |
}
In the constructor for AfterUserCreated, we make sure to import the UseCase<T, U>
fromtheForumsubdomainthatwewanttoinvokeaftertheuseriscreated. Thathappenstobe
theCreateMemberusecase.
// forum/subscriptions/afterUserCreated.ts
export class AfterUserCreated implements IHandle<UserCreated> {
| private                   | createMember: | CreateMember |               |     |
| ------------------------- | ------------- | ------------ | ------------- | --- |
| constructor(createMember: |               |              | CreateMember) | {   |
this.setupSubscriptions()
| this.createMember |     | = createMember |     |     |
| ----------------- | --- | -------------- | --- | --- |
}
...
}
In the setupSubscriptions method, we set up a subscription to the UserCreated Domain
Event by using the Domain Event subject’s register(callback: Function, eventName:
348

string) method. It’s important to bind to this so that we can execute the createMember
usecase,apropertyoftheAfterUserCreatedclass.
// forum/subscriptions/afterUserCreated.ts
export class AfterUserCreated implements IHandle<UserCreated> {
...
setupSubscriptions(): void {
// Register to the domain event
DomainEvents.register(this.onUserCreated.bind(this), UserCreated.name)
}
...
}
Finally, when the Domain Events subject invokes the callbacks for all subscribers to the
UserCreatedDomainEvent,it’llcallthisonUserCreatedmethod.
It’sherethatwecanfollowupwithaninvocationof createMember.
// forum/subscriptions/afterUserCreated.ts
export class AfterUserCreated implements IHandle<UserCreated> {
...
private async onUserCreated(event: UserCreated): Promise<void> {
const { user } = event
try {
await this.createMember.execute({ userId: user.userId.id.toString() })
console.log(
`[AfterUserCreated]: Successfully executed CreateMember use case AfterUserCreated`
)
} catch (err) {
console.log(
`[AfterUserCreated]: Failed to execute CreateMember use case AfterUserCreated.`
)
}
}
...
}
QueuesandEventStores?: WhathappensifwefailtofollowupwiththeCreateUseruse
case in response to the UserCreated DomainEvent? Is there a way to re-try? Maybe after
someamountoftime,likeanexponentialbackoff? Thereareplentyofpatternswecanuse,
like the Transactional Outbox. Just be warned, this kind of thing is called an EnterpriseIn-
tegration Pattern, and the rabbit hole runs deep. I didn’t realize it until later, but EIPs are
theterminalpointofcomplexityinSoftwareDesignandArchitectureforwebapplications.
Dealingwithevents,messaging,andthedesignaroundnetworking,contingency,andcon-
vergenceisifyouaskme,thehardestproblem. It’salsothestuffweneedtosolveifwede-
cidetotakeonEventSourcing,whichiswhywe’vescopedthischapterdownabit. Iplanon
349

learninganddistillingthiscomplexrealmofarchitectureinafollowupchapterin2021. If
you’rekeenonlearningmoretoday,checkoutEnterpriseIntegrationPatterns.comandthe
bookofthesamename.
Of course, in order to get our subscriptions up and running, we’ll need to do a little bit of
|           | npm    | run start | src/index.ts |              |              |      |
| --------- | ------ | --------- | ------------ | ------------ | ------------ | ---- |
| plumbing. | If our |           | script uses  | as the entry | point to our | app, |
since Node.js imports are singletons, we need to make sure to start our subscriptions by
mentioningthenameofwhatevercreatesoursubscriptionclasses.
// src/index.ts
// Infra
| import | "./shared/infra/http/app"           |     |     |     |     |     |
| ------ | ----------------------------------- | --- | --- | --- | --- | --- |
| import | "./shared/infra/database/sequelize" |     |     |     |     |     |
// Subscriptions
| import | "./modules/forum/subscriptions"; |     |     |     |     |     |
| ------ | -------------------------------- | --- | --- | --- | --- | --- |
Followingmodules/forum/subscriptions,wesetupallofourForumsubdomainsubscrip-
tionswiththefollowingcode.
// modules/forum/subscriptions/index.ts
import { createMember } from "../useCases/members/createMember";
| import | { AfterUserCreated   |     | } from "./afterUserCreated";   |     |     |     |
| ------ | -------------------- | --- | ------------------------------ | --- | --- | --- |
| import | { AfterCommentPosted |     | } from "./afterCommentPosted"; |     |     |     |
import { updatePostStats } from "../useCases/post/updatePostStats";
import { AfterCommentVotesChanged } from "./afterCommentVotesChanged";
import { updateCommentStats } from "../useCases/comments/updateCommentStats";
import { AfterPostVotesChanged } from "./afterPostVotesChanged";
// Subscriptions
new AfterUserCreated(createMember);
new AfterCommentPosted(updatePostStats);
new AfterCommentVotesChanged(updatePostStats, updateCommentStats);
new AfterPostVotesChanged(updatePostStats);
Looking at the CreateMember use case that gets called in response to the UserCreated Do-
mainEvent,you’llnoticethatitfollowsthesamestructureastheCreateUserusecase,and
executessimilarlogic.
// forum/useCases/members/createMember/CreateMember.ts
| import | { UseCase         | } from "../../../../../shared/core/UseCase" |                                    |     |     |     |
| ------ | ----------------- | ------------------------------------------- | ---------------------------------- | --- | --- | --- |
| import | { IMemberRepo     | } from                                      | "../../../repos/memberRepo"        |     |     |     |
| import | { CreateMemberDTO |                                             | } from "./CreateMemberDTO"         |     |     |     |
| import | { IUserRepo       | } from                                      | "../../../../users/repos/userRepo" |     |     |     |
import { Either, Result, left, right } from "../../../../../shared/core/Result"
import { AppError } from "../../../../../shared/core/AppError"
350

| import | { CreateMemberErrors |     |         | } from                          | "./CreateMemberErrors" |     |     |     |
| ------ | -------------------- | --- | ------- | ------------------------------- | ---------------------- | --- | --- | --- |
| import | { User               | }   | from    | "../../../../users/domain/user" |                        |     |     |     |
| import | { Member             |     | } from  | "../../../domain/member"        |                        |     |     |     |
| type   | Response             | =   | Either< |                                 |                        |     |     |     |
| CreateMemberErrors.MemberAlreadyExistsError
| CreateMemberErrors.UserDoesntExistError
| AppError.UnexpectedError
| Result<any>,
Result<void>
>
| export                | class           | CreateMember             |            |             |                    |     |              |     |
| --------------------- | --------------- | ------------------------ | ---------- | ----------- | ------------------ | --- | ------------ | --- |
| implements            |                 | UseCase<CreateMemberDTO, |            |             | Promise<Response>> |     |              | {   |
| private               |                 | memberRepo:              |            | IMemberRepo |                    |     |              |     |
| private               |                 | userRepo:                | IUserRepo  |             |                    |     |              |     |
| constructor(userRepo: |                 |                          |            | IUserRepo,  | memberRepo:        |     | IMemberRepo) | {   |
|                       | this.userRepo   |                          | = userRepo |             |                    |     |              |     |
|                       | this.memberRepo |                          | =          | memberRepo  |                    |     |              |     |
}
public async execute(request: CreateMemberDTO): Promise<Response> {
|     | let user:   | User     |        |                                       |     |     |     |     |
| --- | ----------- | -------- | ------ | ------------------------------------- | --- | --- | --- | --- |
|     | let member: |          | Member |                                       |     |     |     |     |
|     | const       | { userId | }      | = request                             |     |     |     |     |
|     | try {       |          |        |                                       |     |     |     |     |
|     | try         | {        |        |                                       |     |     |     |     |
|     | user        | =        | await  | this.userRepo.getUserByUserId(userId) |     |     |     |     |
|     | } catch     | (err)    | {      |                                       |     |     |     |     |
return left(new CreateMemberErrors.UserDoesntExistError(userId))
}
|     | try    | {              |         |                                           |     |      |     |     |
| --- | ------ | -------------- | ------- | ----------------------------------------- | --- | ---- | --- | --- |
|     | member |                | = await | this.memberRepo.getMemberByUserId(userId) |     |      |     |     |
|     | const  | memberExists   |         | = !!member                                | === | true |     |     |
|     | if     | (memberExists) |         | {                                         |     |      |     |     |
return left(new CreateMemberErrors.MemberAlreadyExistsError(userId))
}
|     | } catch | (err) | {}  |     |     |     |     |     |
| --- | ------- | ----- | --- | --- | --- | --- | --- | --- |
// Member doesn't exist already (good), so we want to create it
|     | const   | memberOrError: |              | Result<Member> |     | =   | Member.create({ |     |
| --- | ------- | -------------- | ------------ | -------------- | --- | --- | --------------- | --- |
|     | userId: |                | user.userId, |                |     |     |                 |     |
351

username: user.username,
})
if (memberOrError.isFailure) {
return left(memberOrError)
}
member = memberOrError.getValue()
await this.memberRepo.save(member)
return right(Result.ok<void>())
} catch (err) {
return left(new AppError.UnexpectedError(err))
}
}
}
That’s it! We just went through the entire process of handling a Command. Our first Use
CaseinDDDForum. WesawhowtodispatchDomainEventsandreacttothemfromseparate
subdomainswithinaModularMonolith(singleboundedcontext). Forareminder,checkthe
imagebelowtoseehowfarwe’vecomesofar.
The UserCreated domain event was dispatched from the Users subdomain and reacted to
fromwithintheForumsubdomainbyinvokingtheCreateMembercommand.
352

| Feature2: | Upvoteapost |     |     |     |     |     |     |
| --------- | ----------- | --- | --- | --- | --- | --- | --- |
Inthepreviousexample, welookedatthelifecycleofaCreateoperationinaCleanArchitec-
ture/DDDapplicationusingthePortsandAdaptersapproach. Wesawhowtohandlearequest
fromthemomentitcomesinthroughthepublicAPItothemomentitpassesoffexecution
totheapplicationanddomainlayerconstructs.
Inthisnextfeature,whichistoUpvoteaPost,Ibelieveyou’llgetabetterappreciationforthe
wayDDDenablesustoencapsulatecomplexdomainlogic.
Inthisfeature,we’llhaveabriefdiscussionabouthowwedesignaggregates,whatbelongs
inthem,andhowtousethemtoperformstatechanges. Lastly,andmostimportantly,we’ll
getanopportunitytolearnabouttheutilityofDomainServicesbyseeingoneinaction.
Understandingvotingdomainlogic
AuthenticatedUserscanbothupvoteanddownvotepostsandcomments.
Upvote Post, Upvote Comment,
| During | an Event | Storming | session, | we  | discovered |     | the |
| ------ | -------- | -------- | -------- | --- | ---------- | --- | --- |
Downvote Post,andDownvote Commentcommands,butgiventhenatureofEventStorming,
it’s unlikely we’d have had the time to sit down with each Command and expand on the
scenarios.
AllCommandsinvokechange,it’sessentialtodocumentthepreconditionsthatdictatewhen,
and rules that specify how the system may change. Let’s use the Given-When-Then style
testspecificationstoplanthisout.
Gherkintestspecifications
Gherkinisadomain-specificlanguagemadefordescribingthebusinessbehaviorwithout
needingtointospecificsofimplementationdetails.
Since each Command is a feature, we define the criteria describing how the feature works.
Whenwewritetestsforthecriteria,andtheypass,we’redonethefeatureandontothenext
one!
Here’sanexampleofaGherkinstestspecificationforUpvote Post:
| Feature:  | Upvoting            | a post       |      |              |           |            |         |
| --------- | ------------------- | ------------ | ---- | ------------ | --------- | ---------- | ------- |
| Upvoting  | posts               | is a primary |      | feature      | of        | DDDforum   | that    |
| can be    | done by             | users        | who  | have created |           | an account |         |
| Scenario: | Upvoting            | a            | post | I've already |           | submitted  |         |
| Given     | I am authenticated, |              |      | I have       | submitted |            | a post, |
| and I     | previously          | upvoted      |      | it           |           |            |         |
| When      | I upvote            | the post     |      |              |           |            |         |
| Then      | the post            | should       | not  | change       |           |            |         |
# ...
353

# Come up with as many scenarios necessary to test that
# the feature works the way it should
UsingthekeywordsFeature,Given,When,andThen,wecanlineupafeature,describethe
many scenarios that capture rules and quirks of that feature, specify the inputs, and the
outcomes.
Beforeyouwriteasinglelineofcode,it’susuallyagoodideatohaveallofthesefiguredout
aheadoftime;thisiswhattheTDD-practitionerstellus.
Theimagebelowisastatemachinethatdescribesthepotentialstatesapostcanbeinfor
anygivenuser: upvoted, initial, anddownvoted. Also, noticehowthestatemachinede-
finesthelegalstatetransitions(apostcan’tgodirectlyfromupvotedtodownvoted).
Post&commentstatemachine.
Statemachine: Therearemanywaystointerprethowsystemswork. Oneway
to interpret how a system works is to consider it a state machine. A state ma-
chineisanabstractmentalmodelofasystemwithseveralstates,butcanonly
beinonestateatatime. Italsodefinesfromwhichstates,italsographicallyde-
354

scribeshowthemachinemaychangestates. Ifyou’reintrigued,read“TheRise
ofTheStateMachines”viaSmashingMagazine.
Handlingtheupvotepostrequest
Let’s get into it. Same as before, the request comes into our application through the API,
whetheritbeaGraphQLmutationresolveroraRESTfulAPIcontroller.
Thistimewe’llusetheRESTfulAPI.Asalways,weinjectthecontrollerwiththeupvotePost
UseCase/ApplicationService.
// post/useCases/upvotePost/index.ts
| // Setting |              | up a controller |        | by passing      | in  | the Use | Case. |     |
| ---------- | ------------ | --------------- | ------ | --------------- | --- | ------- | ----- | --- |
| import     | { UpvotePost |                 | } from | "./UpvotePost"; |     |         |       |     |
import { memberRepo, postRepo, postVotesRepo } from "../../../repos";
| import | { postService |     | }   | from "../../../domain/services"; |     |     |     |     |
| ------ | ------------- | --- | --- | -------------------------------- | --- | --- | --- | --- |
import { UpvotePostController } from "./UpvotePostController";
const upvotePost = new UpvotePost(memberRepo, postRepo, postVotesRepo, postService);
| const | upvotePostController |     |     | = new | UpvotePostController( |     |     |     |
| ----- | -------------------- | --- | --- | ----- | --------------------- | --- | --- | --- |
upvotePost
)
| export | { upvotePost, |     | upvotePostController |     |     | };  |     |     |
| ------ | ------------- | --- | -------------------- | --- | --- | --- | --- | --- |
Inthecontroller,wedothesamethingasbefore. PassintherelevantUseCase,calltheUse
Case’sexecutemethodwiththeInputDTO/Command,thenhandletheresult.
// post/useCases/upvotePost/upvotePostController.ts
| // UpvotePostController |     |     |       | calling | execution | of the |     |     |
| ----------------------- | --- | --- | ----- | ------- | --------- | ------ | --- | --- |
| // upvotePost           |     | use | case. |         |           |        |     |     |
import { BaseController } from "../../../../../shared/infra/http/models/BaseController";
| import | { UpvotePost |     | } from | "./UpvotePost"; |     |     |     |     |
| ------ | ------------ | --- | ------ | --------------- | --- | --- | --- | --- |
import { DecodedExpressRequest } from "../../../../users/infra/http/models/decodedRequest";
| import      | { UpvotePostDTO    |                      |             | } from "./UpvotePostDTO"; |                       |                |     |     |
| ----------- | ------------------ | -------------------- | ----------- | ------------------------- | --------------------- | -------------- | --- | --- |
| import      | { UpvotePostErrors |                      |             | } from                    | "./UpvotePostErrors"; |                |     |     |
| import      | * as               | express              | from        | 'express'                 |                       |                |     |     |
| export      | class              | UpvotePostController |             |                           | extends               | BaseController |     | {   |
| private     | useCase:           |                      | UpvotePost; |                           |                       |                |     |     |
| constructor |                    | (useCase:            |             | UpvotePost)               | {                     |                |     |     |
super();
| this.useCase |     |     | = useCase; |     |     |     |     |     |
| ------------ | --- | --- | ---------- | --- | --- | --- | --- | --- |
355

}
async executeImpl (req: DecodedExpressRequest, res: express.Response): Promise<any> {
| const {    | userId } = req.decoded; |     |
| ---------- | ----------------------- | --- |
| const dto: | UpvotePostDTO           | = { |
| userId:    | userId,                 |     |
| slug:      | req.body.slug           |     |
}
try {
| const                | result = await                        | this.useCase.execute(dto); |
| -------------------- | ------------------------------------- | -------------------------- |
| if (result.isLeft()) |                                       | {                          |
| const                | error = result.value;                 |                            |
| switch               | (error.constructor)                   | {                          |
| case                 | UpvotePostErrors.MemberNotFoundError: |                            |
| case                 | UpvotePostErrors.PostNotFoundError:   |                            |
return this.notFound(res, error.errorValue().message)
| case | UpvotePostErrors.AlreadyUpvotedError: |     |
| ---- | ------------------------------------- | --- |
return this.conflict(error.errorValue().message)
default:
|     | return this.fail(res, | error.errorValue().message); |
| --- | --------------------- | ---------------------------- |
}
| } else | {             |     |
| ------ | ------------- | --- |
| return | this.ok(res); |     |
}
| } catch | (err) {        |      |
| ------- | -------------- | ---- |
| return  | this.fail(res, | err) |
}
}
}
InsidetheUpvotePostusecase
OurfirsttaskwiththeUseCase/ApplicationServiceistofetchalloftheAggregatesandentities
necessaryforustoupvoteaPost.
Giventhefirstscenariofromourtestspecifications(upvotingapostwhenonehasalready
upvotedit),whichAggregatesandentitiesareinvolved?
• ThePosttobeupvotedneedstobefetched.
TheMemberabouttodotheupvoting-weneedthattoo.
•
AnyPostUpvotesthatwerealreadycastbytheMemberagainstthisPost.
•
356

Let’sdependencyinjecttheRepositoriesnecessaryforustogetthesethings:
// post/useCases/upvotePost/upvotePost.ts
export class UpvotePost implements UseCase<UpvotePostDTO, Promise<UpvotePostResponse>> {
private memberRepo: IMemberRepo;
private postRepo: IPostRepo;
private postVotesRepo: IPostVotesRepo;
private postService: PostService;
constructor (
memberRepo: IMemberRepo,
postRepo: IPostRepo,
postVotesRepo: IPostVotesRepo,
postService: PostService
) {
this.memberRepo = memberRepo;
this.postRepo = postRepo;
this.postVotesRepo = postVotesRepo
this.postService = postService;
}
public async execute (req: UpvotePostDTO): Promise<UpvotePostResponse> {
let member: Member;
let post: Post;
let existingVotesOnPostByMember: PostVote[];
...
}
}
Next,wefetchallofthesethings,throwingtheappropriateerrorsifthey’renotfound.
// post/useCases/upvotePost/upvotePost.ts
export class UpvotePost implements UseCase<UpvotePostDTO, Promise<UpvotePostResponse>> {
private memberRepo: IMemberRepo;
private postRepo: IPostRepo;
private postVotesRepo: IPostVotesRepo;
private postService: PostService;
constructor (
memberRepo: IMemberRepo,
postRepo: IPostRepo,
postVotesRepo: IPostVotesRepo,
postService: PostService
357

) {
this.memberRepo = memberRepo;
this.postRepo = postRepo;
this.postVotesRepo = postVotesRepo
this.postService = postService;
}
public async execute (req: UpvotePostDTO): Promise<UpvotePostResponse> {
let member: Member;
let post: Post;
let existingVotesOnPostByMember: PostVote[];
try {
try {
member = await this.memberRepo.getMemberByUserId(req.userId);
} catch (err) {
return left(new UpvotePostErrors.MemberNotFoundError())
}
try {
post = await this.postRepo.getPostBySlug(req.slug);
} catch (err) {
return left(new UpvotePostErrors.PostNotFoundError(req.slug));
}
existingVotesOnPostByMember = await this.postVotesRepo
.getVotesForPostByMemberId(post.postId, member.memberId);
// implement logic
...
return right(Result.ok<void>())
} catch (err) {
return left(new AppError.UnexpectedError(err));
}
}
}
OK,sowe’vegotthePostandMemberAggregates,andpossiblyaPostUpvote.
Questionforyou,outofalltheseentities…
Whichisresponsibleforholdingtheupvotepostlogic? ThePost? TheMember?
358

Shouldwedopost.upvote(member)onthePostAggregate?Shouldwedomember.upvotePost(post)?
Theanswerisneither. MentioningthememberAggregatefrompostorvice-versawillbreak
theencapsulationoftheAggregateandalsobreakoneofseveralAggregateDesignPrinciples:
anAggregatemayonlyrefertootherAggregatesbyid.
OK,sowe’vegotthePostandMemberAggregates,andpossiblyaPostUpvote.
Aggregatedesignprinciples
Here are a few principles popularized in the DDD community about how to design Aggre-
gates.
Rule#1-AlltransactionshappenagainstAggregates
The Aggregate is responsible for Command decision-making logic involving a single entity.
Each Entity or Value Object within an Aggregate should be related to that singular purpose:
makingdecisionsagainstCommands.
TodecidewhetherweshouldallowtheCommandtransactiontocomplete, orifweshould
cancel with a failed Result<T> instead requires us to have instances of all the Entities and
ValueObjectsthatbelongtothisAggregateandplaysomepartinmakingthedecision.
Forexample,whenwewanttoeditPost,therearebusinessrulesweneedtoimplement.
IntheGherkinsspecificationforeditPost,ifaPostisalinkpost andwewishtoupdateit,
wecanonlydosoifitdoesn’thaveCommentsonitalready.
Seethecodebelow.
// forum/domain/post.ts
| // Attempting                                | to update              | the link  | on a post          | that already |
| -------------------------------------------- | ---------------------- | --------- | ------------------ | ------------ |
| // has comments                              | will                   | result in | a PostSealedError. |              |
| export type                                  | UpdatePostOrLinkResult |           | = Either<          |              |
| EditPostErrors.InvalidPostTypeOperationError |                        |           |                    | |            |
| EditPostErrors.PostSealedError               |                        |           | |                  |              |
Result<any>,
Result<void>
>
| export class | Post extends | AggregateRoot<PostProps> |     | {   |
| ------------ | ------------ | ------------------------ | --- | --- |
...
public updateLink (postLink: PostLink): UpdatePostOrLinkResult {
| if (!this.isLinkPost()) |     | {   |     |     |
| ----------------------- | --- | --- | --- | --- |
return left(new EditPostErrors.InvalidPostTypeOperationError())
}
| if (this.hasComments()) |          | {                                 |     |     |
| ----------------------- | -------- | --------------------------------- | --- | --- |
| return                  | left(new | EditPostErrors.PostSealedError()) |     |     |
359

}
const guardResult = Guard.againstNullOrUndefined(postLink, 'postLink');
if (!guardResult.succeeded) {
return left(Result.fail<any>(guardResult.message))
}
this.props.link = postLink;
return right(Result.ok<void>());
}
}
Rule#2-DesignAggregatestobeassmallaspossible
We keep the Aggregates small because we want to keep our writes fast, and that becomes
hardtodoifwecontinuallyaddotherentitiesnotnecessaryfordecision-makinglogicto
ourAggregate.
IfusingCQRS,onlyencapsulateentitiesandvalueobjectsnecessaryforprotectingmodel
invariants.
Ifwe’renotusingCQRS,encapsulateeverythingnecessarytoprotectmodelinvariants,but
alsolocateeverythingrequiredtocreateviews(potentiallyexpensivedesign).
Rule #3 - You may not alter entities within the aggregate’s transaction boundary
withoutgoingthroughtheaggregate
When we build an Aggregate, we clump together all of the related Entities, promote one of
themtoactastheAggregateRoot,andwemustmakeallofourtransactionsbygoingthrough
theidentifierfortheAggregateRoot.
With this design, it becomes impossible to circumvent important invariants on Entities
within the Aggregate. Each Entity within the Aggregate is only allowed to change, given the
AggregateRootdeemsitappropriateforittodoso,anditkeepstrackofthosestatechanges.
UsingaDomainService
Whenyou’reunabletolocatesomedomainlogicwithinanAggregatebecausetheCommand
involvesseveralEntities,andassigningthetasktooneoftheinvolvedentitieswouldbreak
encapsulation,useaDomainService.
ImplementingtheUpvotePostlogicinaDomainService
Toimplementtheupvotepostlogic,passalloftheentitiesfetchedbytheapplicationservice
tothePostServiceDomainService.
// domain/services/postService.ts
360

...
| export type                            | UpvotePostResponse |     |     | = Either< |     |
| -------------------------------------- | ------------------ | --- | --- | --------- | --- |
| UpvotePostResponse.MemberNotFoundError |                    |     |     |           | |   |
| UpvotePostResponse.AlreadyUpvotedError |                    |     |     |           | |   |
| UpvotePostResponse.PostNotFoundError   |                    |     |     | |         |     |
| AppError.UnexpectedError               |                    |     | |   |           |     |
Result<any>,
Result<void>
>
| export class | PostService |     | {   |     |     |
| ------------ | ----------- | --- | --- | --- | --- |
...
| public                       | upvotePost                | (        |           |               |          |
| ---------------------------- | ------------------------- | -------- | --------- | ------------- | -------- |
| post:                        | Post,                     |          |           |               |          |
| member:                      | Member,                   |          |           |               |          |
| existingVotesOnPostByMember: |                           |          |           | PostVote[]    |          |
| ): UpvotePostResponse        |                           |          | {         |               |          |
| // If                        | already                   | upvoted, | do        | nothing       |          |
| // If                        | downvoted,                | we       | need      | to remove the | downvote |
| // Otherwise,                |                           | add      | an upvote |               |          |
| return                       | right(Result.ok<void>()); |          |           |               |          |
}
}
Firstly,ifthepostwasalreadyupvoted,donothing.
// domain/services/postService.ts
...
| export class                 | PostService         |                   | {        |                               |     |
| ---------------------------- | ------------------- | ----------------- | -------- | ----------------------------- | --- |
| public                       | upvotePost          | (                 |          |                               |     |
| post:                        | Post,               |                   |          |                               |     |
| member:                      | Member,             |                   |          |                               |     |
| existingVotesOnPostByMember: |                     |                   |          | PostVote[]                    |     |
| ): UpvotePostResponse        |                     |                   | {        |                               |     |
| // If                        | already             | upvoted,          | do       | nothing                       |     |
| const                        | existingUpvote:     |                   | PostVote | = existingVotesOnPostByMember |     |
| .find((v)                    |                     | => v.isUpvote()); |          |                               |     |
| const                        | upvoteAlreadyExists |                   |          | = !!existingUpvote;           |     |
361

| if (upvoteAlreadyExists) |                           |     | {   |     |     |
| ------------------------ | ------------------------- | --- | --- | --- | --- |
| return                   | right(Result.ok<void>()); |     |     |     |     |
}
| // If downvoted, |                           | we need   | to remove the | downvote |     |
| ---------------- | ------------------------- | --------- | ------------- | -------- | --- |
| // Otherwise,    | add                       | an upvote |               |          |     |
| return           | right(Result.ok<void>()); |           |               |          |     |
}
}
Ifdownvoted,removethedownvote.
// domain/services/postService.ts
...
| // If downvoted, | remove | the | downvote |     |     |
| ---------------- | ------ | --- | -------- | --- | --- |
const existingDownvote: PostVote = existingVotesOnPostByMember
| .find((v)                   | => v.isDownvote()); |          |                       |                  |       |
| --------------------------- | ------------------- | -------- | --------------------- | ---------------- | ----- |
| const downvoteAlreadyExists |                     |          | = !!existingDownvote; |                  |       |
| if (downvoteAlreadyExists)  |                     |          | {                     |                  |       |
| // Signal                   | that the            | vote was | removed               | (we'll look into | this) |
post.removeVote(existingDownvote);
| return | right(Result.ok<void>()); |     |     |     |     |
| ------ | ------------------------- | --- | --- | --- | --- |
}
...
Otherwise,addtheupvotetoPostandthenreturnwithasuccessfulResult<T>.
// domain/services/postService.ts
...
| // Otherwise,                  | add                  | upvote     |               |     |     |
| ------------------------------ | -------------------- | ---------- | ------------- | --- | --- |
| const upvoteOrError            |                      | = PostVote |               |     |     |
| .createUpvote(member.memberId, |                      |            | post.postId); |     |     |
| if (upvoteOrError.isFailure)   |                      |            | {             |     |     |
| return                         | left(upvoteOrError); |            |               |     |     |
}
362

const upvote: PostVote = upvoteOrError.getValue();
// Signal that the vote was added (we'll look at this)
post.addVote(upvote);
return right(Result.ok<void>());
...
Acoupleofnotesaboutthedesignhere:
• The CQS principle is at play: Notice that this method doesn’t return anything?
That’sbecauseanoperation(methodstoo)iseitheracommandoraquery. Thisone,
upvotePost,isaCommand,soitchangesPostinsomewaybutdoesn’treturnavalue,
becausethatwouldbreaktheCQSprinciplethataCommandchangesthesystembut
returnsnovalue.
• We’re using Dependency Injection without Mocking: In our pursuits of writing
testablecode,weoftenimplementDependencyInversionbyreferringtoabstractions
overconcreteclasses. We’vedonethisinourUseCase/ApplicationServicebyreferringto
interfacesofRepositoriesinsteadofconcreteones. Noticeherethatwerequirethings
that come from Repositories, yet we haven’t related to a Repository. Some say that
mockingisacodesmell. Mockingisnecessarywithinterfacesandabstractclasses.
Persistingtheupvotepostoperation
WhenimplementingDDDwithoutusingEventSourcing,inatransaction,theAggregategets
mutated, and it needs to know how it was mutated so that we can issue the correct persis-
tencecommandstoreflectthewayithaschanged.
ThisisoneofthedisadvantagesofnotusingEventSourcing. InEventSourcing,wepersistthe
statechangesthemselves,whereaswithhowwe’redoingit,weupdateandoverwritethe
existingstatewiththenewstate.
Atsomepoint,wemustanswerthehardquestionsaboutpersistingAggregatesthisway.
Signalingrelationshipchanges
Howdoyouimplementone-to-one,one-to-many,andmany-to-manyrelationships? How
doyousignalthatanewentityinacollectionwascreated? Howdoyoumarkitdeleted?
For example, when we do post.addUpvote(vote) or post.removeUpvote(vote), we’re
adding or removing an entity (PostUpvote), where a Post can have many PostUpvotes,
fromPost.
To solve this particular problem, I rolled myself a WatchedList<T> base class that keeps
track of the initial, new, and deleted items in a collection of entities, like the collection
of PostUpvotes.
// shared/domain/watchedList.ts
363

| export  | abstract |               | class | WatchedList<T> |     | {   |     |     |     |
| ------- | -------- | ------------- | ----- | -------------- | --- | --- | --- | --- | --- |
| public  |          | currentItems: |       | T[];           |     |     |     |     |     |
| private |          | initial:      |       | T[];           |     |     |     |     |     |
| private |          | new:          | T[];  |                |     |     |     |     |     |
| private |          | removed:      |       | T[];           |     |     |     |     |     |
/**
|     | * When | we  | first | create a | WatchedList<T>, |     | the items | passed | in  |
| --- | ------ | --- | ----- | -------- | --------------- | --- | --------- | ------ | --- |
* initially via the constructor become the initial and current
* items.
*/
| constructor |                   |     | (initialItems?: |                | T[]) | {              |     |       |     |
| ----------- | ----------------- | --- | --------------- | -------------- | ---- | -------------- | --- | ----- | --- |
|             | this.currentItems |     |                 | = initialItems |      | ? initialItems |     | : []; |     |
|             | this.initial      |     | =               | initialItems   |      | ? initialItems | :   | [];   |     |
|             | this.new          |     | = [];           |                |      |                |     |       |     |
|             | this.removed      |     | =               | [];            |      |                |     |       |     |
}
| abstract |        | compareItems       |     | (a:   | T, b: | T): boolean; |     |     |     |
| -------- | ------ | ------------------ | --- | ----- | ----- | ------------ | --- | --- | --- |
| public   |        | getItems           | (): | T[] { |       |              |     |     |     |
|          | return | this.currentItems; |     |       |       |              |     |     |     |
}
| public |        | getNewItems |     | (): T[] | {   |     |     |     |     |
| ------ | ------ | ----------- | --- | ------- | --- | --- | --- | --- | --- |
|        | return | this.new;   |     |         |     |     |     |     |     |
}
| public |        | getRemovedItems |     | (): | T[] | {   |     |     |     |
| ------ | ------ | --------------- | --- | --- | --- | --- | --- | --- | --- |
|        | return | this.removed;   |     |     |     |     |     |     |     |
}
| private |             | isCurrentItem     |     | (item:                     | T): | boolean | {          |     |       |
| ------- | ----------- | ----------------- | --- | -------------------------- | --- | ------- | ---------- | --- | ----- |
|         | return      | this.currentItems |     |                            |     |         |            |     |       |
|         | .filter((v: |                   | T)  | => this.compareItems(item, |     |         | v)).length |     | !== 0 |
}
| private |             | isNewItem |     | (item:                     | T): boolean | {   |            |     |       |
| ------- | ----------- | --------- | --- | -------------------------- | ----------- | --- | ---------- | --- | ----- |
|         | return      | this.new  |     |                            |             |     |            |     |       |
|         | .filter((v: |           | T)  | => this.compareItems(item, |             |     | v)).length |     | !== 0 |
}
| private |        | isRemovedItem |     | (item: | T): | boolean | {   |     |     |
| ------- | ------ | ------------- | --- | ------ | --- | ------- | --- | --- | --- |
|         | return | this.removed  |     |        |     |         |     |     |     |
364

|     | .filter((v: |     | T)  | => this.compareItems(item, |     |     | v)) |
| --- | ----------- | --- | --- | -------------------------- | --- | --- | --- |
|     | .length     | !== | 0   |                            |     |     |     |
}
| private     |     | removeFromNew |                          | (item: T): | void | {       |     |
| ----------- | --- | ------------- | ------------------------ | ---------- | ---- | ------- | --- |
| this.new    |     | =             | this.new                 |            |      |         |     |
| .filter((v) |     |               | => !this.compareItems(v, |            |      | item)); |     |
}
| private           |             | removeFromCurrent |     | (item:                   | T): | void { |     |
| ----------------- | ----------- | ----------------- | --- | ------------------------ | --- | ------ | --- |
| this.currentItems |             |                   |     | = this.currentItems      |     |        |     |
|                   | .filter((v) |                   | =>  | !this.compareItems(item, |     |        | v)) |
}
| private      |             | removeFromRemoved |                | (item:                   | T): | void { |     |
| ------------ | ----------- | ----------------- | -------------- | ------------------------ | --- | ------ | --- |
| this.removed |             |                   | = this.removed |                          |     |        |     |
|              | .filter((v) |                   | =>             | !this.compareItems(item, |     |        | v)) |
}
| private |             | wasAddedInitially |     | (item:                     | T): | boolean | {   |
| ------- | ----------- | ----------------- | --- | -------------------------- | --- | ------- | --- |
| return  |             | this.initial      |     |                            |     |         |     |
|         | .filter((v: |                   | T)  | => this.compareItems(item, |     |         | v)) |
|         | .length     | !==               | 0   |                            |     |         |     |
}
| public | exists |                           | (item: | T): boolean | {   |     |     |
| ------ | ------ | ------------------------- | ------ | ----------- | --- | --- | --- |
| return |        | this.isCurrentItem(item); |        |             |     |     |     |
}
| public | add                        | (item: | T): | void { |     |     |     |
| ------ | -------------------------- | ------ | --- | ------ | --- | --- | --- |
| if     | (this.isRemovedItem(item)) |        |     |        | {   |     |     |
this.removeFromRemoved(item);
}
if (!this.isNewItem(item) && !this.wasAddedInitially(item)) {
this.new.push(item);
}
| if  | (!this.isCurrentItem(item)) |     |     |     | {   |     |     |
| --- | --------------------------- | --- | --- | --- | --- | --- | --- |
this.currentItems.push(item);
}
}
| public | remove |     | (item: | T): void | {   |     |     |
| ------ | ------ | --- | ------ | -------- | --- | --- | --- |
this.removeFromCurrent(item);
365

| if  | (this.isNewItem(item)) |     |     | {   |     |     |
| --- | ---------------------- | --- | --- | --- | --- | --- |
this.removeFromNew(item);
return;
}
| if  | (!this.isRemovedItem(item)) |     |     |     | {   |     |
| --- | --------------------------- | --- | --- | --- | --- | --- |
this.removed.push(item);
}
}
}
Subclassing WatchedList<T> gives the new class all the capabilities of the WatchList<T>.
Forexample,IdidthisverythingwithPostVotes,usingthePostVoteentityasthegeneric.
// forum/domain/postVotes.ts
| import | { PostVote | }   | from | "./postVote"; |     |     |
| ------ | ---------- | --- | ---- | ------------- | --- | --- |
import { WatchedList } from "../../../shared/domain/WatchedList";
| export  | class       | PostVotes | extends        | WatchedList<PostVote> |             | {   |
| ------- | ----------- | --------- | -------------- | --------------------- | ----------- | --- |
| private | constructor |           | (initialVotes: |                       | PostVote[]) | {   |
super(initialVotes)
}
| public | compareItems |     | (a: | PostVote, | b: PostVote): | boolean { |
| ------ | ------------ | --- | --- | --------- | ------------- | --------- |
| return | a.equals(b)  |     |     |           |               |           |
}
public static create (initialVotes?: PostVote[]): PostVotes {
| return | new | PostVotes(initialVotes |     |     | ? initialVotes | : []); |
| ------ | --- | ---------------------- | --- | --- | -------------- | ------ |
}
}
FromthePostclass,insteadofreferringtotheupvotesasPostVote[]likeso…
// forum/domain/post.ts
| export | interface | PostProps |     | {   |     |     |
| ------ | --------- | --------- | --- | --- | --- | --- |
...
| votes?: | PostVote[] |     |     |     |     |     |
| ------- | ---------- | --- | --- | --- | --- | --- |
}
WerefertoanabstractionofthePostVote[]collection.
// forum/domain/post.ts
| export | interface | PostProps |     | {   |     |     |
| ------ | --------- | --------- | --- | --- | --- | --- |
...
| votes?: | PostVotes; |     | //  | collection |     |     |
| ------- | ---------- | --- | --- | ---------- | --- | --- |
}
366

|        | vote | PostVotes |     |     |         |       |     | add(t: | T)          |     |
| ------ | ---- | --------- | --- | --- | ------- | ----- | --- | ------ | ----------- | --- |
| Adding | a    | to        | can | now | be done | using | the |        | method from | the |
WatchedList<T>baseclass.
Theabstractionkeepstrackofnewitemsadded.
// forum/domain/post.ts
| export | class | Post extends | AggregateRoot<PostProps> |     |     |     |     | {   |     |     |
| ------ | ----- | ------------ | ------------------------ | --- | --- | --- | --- | --- | --- | --- |
...
|     | public addVote | (vote: | PostVote): |     | Result<void> |     | {   |     |     |     |
| --- | -------------- | ------ | ---------- | --- | ------------ | --- | --- | --- | --- | --- |
this.props.votes.add(vote);
|     | this.addDomainEvent(new |                    |     | PostVotesChanged(this, |     |     |     | vote)); |     |     |
| --- | ----------------------- | ------------------ | --- | ---------------------- | --- | --- | --- | ------- | --- | --- |
|     | return                  | Result.ok<void>(); |     |                        |     |     |     |         |     |     |
}
}
Post
Inside the repository, having received a dirtied Aggregate, it passes off the post’s
postVotestoaseparatePostVotesReposub-repoforpersistence.
// forum/repo/implementations/sequelizePostRepo.ts
| export | class | SequelizePostRepo |     | implements |     | PostRepo |     | {   |     |     |
| ------ | ----- | ----------------- | --- | ---------- | --- | -------- | --- | --- | --- | --- |
...
|     | private | savePostVotes                           | (postVotes: |     | PostVotes) |     | {   |     |     |     |
| --- | ------- | --------------------------------------- | ----------- | --- | ---------- | --- | --- | --- | --- | --- |
|     | return  | this.postVotesRepo.saveBulk(postVotes); |             |     |            |     |     |     |     |     |
}
|     | public async   | save (post:                          | Post):                    |       | Promise<void>                |     | {   |     |     |     |
| --- | -------------- | ------------------------------------ | ------------------------- | ----- | ---------------------------- | --- | --- | --- | --- | --- |
|     | const          | PostModel =                          | this.models.Post;         |       |                              |     |     |     |     |     |
|     | const          | exists = await                       | this.exists(post.postId); |       |                              |     |     |     |     |     |
|     | const          | isNewPost =                          | !exists;                  |       |                              |     |     |     |     |     |
|     | const          | rawSequelizePost                     | =                         | await | PostMap.toPersistence(post); |     |     |     |     |     |
|     | if (isNewPost) | {                                    |                           |       |                              |     |     |     |     |     |
|     | try            | {                                    |                           |       |                              |     |     |     |     |     |
|     | await          | PostModel.create(rawSequelizePost);  |                           |       |                              |     |     |     |     |     |
|     | await          | this.saveComments(post.comments);    |                           |       |                              |     |     |     |     |     |
|     | await          | this.savePostVotes(post.getVotes()); |                           |       |                              |     |     |     |     |     |
|     | } catch        | (err) {                              |                           |       |                              |     |     |     |     |     |
|     | await          | this.delete(post.postId);            |                           |       |                              |     |     |     |     |     |
|     | throw          | new Error(err.toString())            |                           |       |                              |     |     |     |     |     |
}
|     | } else  | {             |        |        |        |           |     |                |     |     |
| --- | ------- | ------------- | ------ | ------ | ------ | --------- | --- | -------------- | --- | --- |
|     | // Save | non-aggregate |        | tables | before | saving    |     | the aggregate  |     |     |
|     | // so   | that any      | domain | events | on the | aggregate |     | get dispatched |     |     |
367

|     | await | this.saveComments(post.comments);    |     |     |     |     |     |
| --- | ----- | ------------------------------------ | --- | --- | --- | --- | --- |
|     | await | this.savePostVotes(post.getVotes()); |     |     |     |     |     |
|     | await | PostModel.update(rawSequelizePost,   |     |     |     | {   |     |
// To make sure your hooks always run, make sure to include this in
|     | //               | the query  |                           |     |     |     |     |
| --- | ---------------- | ---------- | ------------------------- | --- | --- | --- | --- |
|     | individualHooks: |            | true,                     |     |     |     |     |
|     | hooks:           | true,      |                           |     |     |     |     |
|     | where:           | { post_id: | post.postId.id.toString() |     |     |     | }   |
});
}
}
}
InPostVotesRepo’ssaveBulkmethod,wemakeuseoftheabilitytogetallitemsremoved
andallnewitemsbycallingvotes.getRemovedItems()andvotes.getNewItems().
// forum/repos/implementations/sequelizePostVotesRepo.ts
| export | class | PostVotesRepo | implements |     | IPostVotesRepo |     | {   |
| ------ | ----- | ------------- | ---------- | --- | -------------- | --- | --- |
...
| async | save  | (vote: PostVote): |                         | Promise<any> |     | {   |     |
| ----- | ----- | ----------------- | ----------------------- | ------------ | --- | --- | --- |
|       | const | PostVoteModel     | = this.models.PostVote; |              |     |     |     |
const exists = await this.exists(vote.postId, vote.memberId, vote.type);
const rawSequelizePostVote = PostVoteMap.toPersistence(vote);
|     | if (!exists) | {                                           |     |     |     |     |     |
| --- | ------------ | ------------------------------------------- | --- | --- | --- | --- | --- |
|     | try          | {                                           |     |     |     |     |     |
|     | await        | PostVoteModel.create(rawSequelizePostVote); |     |     |     |     |     |
|     | } catch      | (err) {                                     |     |     |     |     |     |
|     | throw        | new Error(err.toString());                  |     |     |     |     |     |
}
|     | } else | {                  |     |        |       |       |            |
| --- | ------ | ------------------ | --- | ------ | ----- | ----- | ---------- |
|     | throw  | new Error('Invalid |     | state. | Votes | arent | updated.') |
}
}
| public | async      | delete (vote:               |                         | PostVote): | Promise<any> |     | {   |
| ------ | ---------- | --------------------------- | ----------------------- | ---------- | ------------ | --- | --- |
|        | const      | PostVoteModel               | = this.models.PostVote; |            |              |     |     |
|        | return     | PostVoteModel.destroy({     |                         |            |              |     |     |
|        | where:     | {                           |                         |            |              |     |     |
|        | post_id:   | vote.postId.id.toString(),  |                         |            |              |     |     |
|        | member_id: | vote.memberId.id.toString() |                         |            |              |     |     |
}
})
}
368

| async | saveBulk           | (votes: PostVotes):         | Promise<any> |     | {   |
| ----- | ------------------ | --------------------------- | ------------ | --- | --- |
| for   | (let vote          | of votes.getRemovedItems()) |              | {   |     |
| await | this.delete(vote); |                             |              |     |     |
}
| for   | (let vote        | of votes.getNewItems()) |     | {   |     |
| ----- | ---------------- | ----------------------- | --- | --- | --- |
| await | this.save(vote); |                         |     |     |     |
}
}
...
}
TheCompositeDesignPattern: Wrappingacollectionofobjectsandtreating
itasifit’sasingleobject. Usethiswhenyouneedcustomlogicorstatefulness
aroundhowandwhenencapsulatedcollectionchanges.
Persistingcomplexaggregatesusingdatabasetransactions
WhatweretohappenifweweresavinganAggregatelikePost,andwewereabletosaveonly
apartofit,likethenestedPostVoteentity? Shouldtheentiretransactionfail? Shouldwe
rollback?
Yeah,ideally,thisisthebestoption. Wedon’twanttoleaveourdatabaseinaninconsistent
state,soweshouldensurethatifanythingfailswithintheaggregateconsistencyboundary,
theentiretransactionfails.
SequelizeandmostpopularORMordatabaseadaptersforNode.jscomewiththeabilityto
tieseveraloperationstoasingletransaction.
SequelizecomeswiththeabilitytouseUnmanagedtransactions,whichmeansthatyou
includeareferencetothetransactionwitheveryoperation. Whenyou’vecalledallyourop-
erations,youcommitthetransactionifitwassuccessful,andyourollbackthetransaction
ifitwasn’t.
| // First, | we start | a transaction            | and save | it into | a variable |
| --------- | -------- | ------------------------ | -------- | ------- | ---------- |
| const t   | = await  | sequelize.transaction(); |          |         |            |
try {
// Then, we do some calls passing this transaction as an option:
| const      | user =            | await User.create({ |     |     |     |
| ---------- | ----------------- | ------------------- | --- | --- | --- |
| firstName: |                   | 'Bart',             |     |     |     |
| lastName:  |                   | 'Simpson'           |     |     |     |
| }, {       | transaction:      | t });               |     |     |     |
| await      | user.addSibling({ |                     |     |     |     |
369

| firstName:        | 'Lisa',   |       |     |     |     |
| ----------------- | --------- | ----- | --- | --- | --- |
| lastName:         | 'Simpson' |       |     |     |     |
| }, { transaction: |           | t }); |     |     |     |
// If the execution reaches this line, no errors were thrown.
| // We commit | the | transaction. |     |     |     |
| ------------ | --- | ------------ | --- | --- | --- |
await t.commit();
| } catch (error) | {         |              |       |              |         |
| --------------- | --------- | ------------ | ----- | ------------ | ------- |
| // If the       | execution | reaches this | line, | an error was | thrown. |
| // We rollback  | the       | transaction. |       |              |         |
await t.rollback();
}
Thesemanticsofhowtransactionsworkmaybedifferentperlibrary,butitshouldbepos-
sibletodesignaRepositorytohandleoperationsagainstcomplexaggregatesusingtrans-
actions.
Remindme:I’vegotitonmylisttoputtogetheranopen-sourceprojectdemon-
stratinghowtodesignrepositoriestousetransactionsinstead.
| Feature3: | GetPopularPosts |     |     |     |     |
| --------- | --------------- | --- | --- | --- | --- |
WeknowhowCommands,nowlet’stalkabouttheothersideofthefence: Queries.
In CQRS, for any Aggregate, there exists a model for writing (the Aggregate itself), and at
leastonemodelforreading(theviewmodel).
Forexample,intheForumsubdomain,wehavethePostAggregateasthewritemodel.
For
thereadmodel,wejustneedaplainol’TypeScriptobjectthatcontainsallthefieldsrequired
inthePresentationlayer.
Readmodels
When people use an application, they typically have the ability to view data. Sometimes
thereareseveralwaystorepresentthatdata. Sometimesitdependsontheroleyouareor
authscopeyouhave.
InCQRS,areadmodelisasimpleobjectintendedforthePresentationlayer.
Therearetwowaystomodelreadmodels.
1. AsdomainconceptsusingValueObjects
2. Asrawdatawithalooseshape
Modelingreadmodelsasdomainconcepts
Readmodelscanbethoughtaboutasdomainconcepts,andcanberepresentedassuch. We
canmodelthemasValueObjectsandenforcecreationthroughfactorymethods.
370

Here’sanexampleofaPostDetailsreadmodelpropertiesinterface.
// forum/domain/postDetails.ts
| interface         | PostDetailsProps |          | {        |
| ----------------- | ---------------- | -------- | -------- |
| member:           | MemberDetails;   |          |          |
| slug:             | PostSlug;        |          |          |
| title:            | PostTitle;       |          |          |
| type:             | PostType;        |          |          |
| text?:            | PostText;        |          |          |
| link?:            | PostLink;        |          |          |
| numComments:      |                  | number;  |          |
| points:           | number;          |          |          |
| dateTimePosted:   |                  | string   | | Date;  |
| wasUpvotedByMe:   |                  | boolean; |          |
| wasDownvotedByMe: |                  |          | boolean; |
}
Andhere’swhatitlookslikewhenweimplementtheinterfaceasagenericofValueObject<T>.
// forum/domain/postDetails.ts
import { ValueObject } from "../../../shared/domain/ValueObject";
| import | { PostLink      | }      | from "./postLink";             |
| ------ | --------------- | ------ | ------------------------------ |
| import | { PostText      | }      | from "./postText";             |
| import | { PostType      | }      | from "./postType";             |
| import | { PostTitle     | }      | from "./postTitle";            |
| import | { PostSlug      | }      | from "./postSlug";             |
| import | { MemberDetails |        | } from "./memberDetails";      |
| import | { Result        | } from | "../../../shared/core/Result"; |
import { IGuardArgument, Guard } from "../../../shared/core/Guard";
| import            | { Post }         | from     | "./post"; |
| ----------------- | ---------------- | -------- | --------- |
| interface         | PostDetailsProps |          | {         |
| member:           | MemberDetails;   |          |           |
| slug:             | PostSlug;        |          |           |
| title:            | PostTitle;       |          |           |
| type:             | PostType;        |          |           |
| text?:            | PostText;        |          |           |
| link?:            | PostLink;        |          |           |
| numComments:      |                  | number;  |           |
| points:           | number;          |          |           |
| dateTimePosted:   |                  | string   | | Date;   |
| wasUpvotedByMe:   |                  | boolean; |           |
| wasDownvotedByMe: |                  |          | boolean;  |
}
export class PostDetails extends ValueObject<PostDetailsProps> {
371

| get member | ():                | MemberDetails |     |     | {   |     |
| ---------- | ------------------ | ------------- | --- | --- | --- | --- |
| return     | this.props.member; |               |     |     |     |     |
}
| get slug | ():              | PostSlug | {   |     |     |     |
| -------- | ---------------- | -------- | --- | --- | --- | --- |
| return   | this.props.slug; |          |     |     |     |     |
}
| get title | ():               | PostTitle |     | {   |     |     |
| --------- | ----------------- | --------- | --- | --- | --- | --- |
| return    | this.props.title; |           |     |     |     |     |
}
| get postType |                  | (): PostType |     | {   |     |     |
| ------------ | ---------------- | ------------ | --- | --- | --- | --- |
| return       | this.props.type; |              |     |     |     |     |
}
| get text | ():              | PostText | {   |     |     |     |
| -------- | ---------------- | -------- | --- | --- | --- | --- |
| return   | this.props.text; |          |     |     |     |     |
}
| get link | ():              | PostLink | {   |     |     |     |
| -------- | ---------------- | -------- | --- | --- | --- | --- |
| return   | this.props.link; |          |     |     |     |     |
}
| get numComments |                         | (): | number | {   |     |     |
| --------------- | ----------------------- | --- | ------ | --- | --- | --- |
| return          | this.props.numComments; |     |        |     |     |     |
}
| get points | ():                | number | {   |     |     |     |
| ---------- | ------------------ | ------ | --- | --- | --- | --- |
| return     | this.props.points; |        |     |     |     |     |
}
| get dateTimePosted |                            |     | (): | string | | Date { |     |
| ------------------ | -------------------------- | --- | --- | ------ | -------- | --- |
| return             | this.props.dateTimePosted; |     |     |        |          |     |
}
| get wasUpvotedByMe |                            |     | (): | boolean | {   |     |
| ------------------ | -------------------------- | --- | --- | ------- | --- | --- |
| return             | this.props.wasUpvotedByMe; |     |     |         |     |     |
}
| get wasDownvotedByMe |                              |     | (): | boolean | {   |     |
| -------------------- | ---------------------------- | --- | --- | ------- | --- | --- |
| return               | this.props.wasDownvotedByMe; |     |     |         |     |     |
}
| private | constructor |     | (props: |     | PostDetailsProps) | {   |
| ------- | ----------- | --- | ------- | --- | ----------------- | --- |
super(props);
372

}
public static create (props: PostDetailsProps): Result<PostDetails> {
| const guardArgs: | IGuardArgument[] |               | = [ |           |     |
| ---------------- | ---------------- | ------------- | --- | --------- | --- |
| { argument:      | props.member,    | argumentName: |     | 'member'  | },  |
| { argument:      | props.slug,      | argumentName: |     | 'slug' }, |     |
| { argument:      | props.title,     | argumentName: |     | 'title'   | },  |
| { argument:      | props.type,      | argumentName: |     | 'type' }, |     |
{ argument: props.numComments, argumentName: 'numComments' },
| { argument: | props.points, | argumentName: |     | 'points' | },  |
| ----------- | ------------- | ------------- | --- | -------- | --- |
{ argument: props.dateTimePosted, argumentName: 'dateTimePosted' },
];
| if (props.type | === 'link') | {   |     |     |     |
| -------------- | ----------- | --- | --- | --- | --- |
guardArgs.push({ argument: props.link, argumentName: 'link' })
} else {
guardArgs.push({ argument: props.text, argumentName: 'text' })
}
const guardResult = Guard.againstNullOrUndefinedBulk(guardArgs);
| if (!guardResult.succeeded)                           |     | {   |     |     |     |
| ----------------------------------------------------- | --- | --- | --- | --- | --- |
| return Result.fail<PostDetails>(guardResult.message); |     |     |     |     |     |
}
| if (!Post.isValidPostType(props.type)) |     |     |     | {   |     |
| -------------------------------------- | --- | --- | --- | --- | --- |
return Result.fail<PostDetails>("Invalid post type provided.")
}
| return Result.ok<PostDetails>(new |     |     | PostDetails({ |     |     |
| --------------------------------- | --- | --- | ------------- | --- | --- |
...props,
wasUpvotedByMe: props.wasUpvotedByMe ? props.wasUpvotedByMe : false,
wasDownvotedByMe: props.wasDownvotedByMe ? props.wasDownvotedByMe : false
}));
}
}
Modelingreadmodelsasrawdata
Since there’s no real reason for us to enforce model invariants on read operations, one
mightquestion—whybotherenforcingobjectcreation?
That’sagreatpoint.
Ifyouwant,youcanrelaxcreatingreadmodelsandconstructthembygoingtoyourdata
storedirectly.
Youcancreateareadmodelusing:
373

• arawSQLquery
• amethodcallfromyourORM’sAPI
• anyothermethodofretrievingdatafromyourdatasource.
CQRS
ThisisoneofthegreatbenefitsofCQRS.Thestackweuseforreadsdoesn’thavetobethe
samestackweuseforwrites. Theyareindependentoperationsandwecanscalethemsep-
aratelyfromeachother.
Ifit’smoreefficienttouserawSQLqueries,that’sanoptionwecantake.
InEventSourcing, weusuallyhaveatleasttwodatabases. ThefirstdatabaseisanEvent
Store,anditsavestheDomainEventsthatoccurasaresultofasuccessfulcommand. Those
eventsareprojectedandthedataiswrittentoaseconddatabase,whichisusuallyarelational
orobjectdatabase. Thisseconddatabaseistypicallyoptimizedforreads. Allqueriesare
resolvedfromthisseconddatabase. ThisiswhysometimesittakesasecondforTwitterto
update your notifications after someone tweets you — the readdatabase has to catch up to
thewritedatabase.
HandlinganAPIrequesttoGetPopularPosts
Similartolasttime,wecanhandletherequestthrougheitheraRESTfulAPIcontrollerora
GraphQLresolver.
Here’sanexampleofaRESTfulAPIcontrollerthathandlestherequest.TheGetPopularPostsRequestDTO
typedescribesanythingwe’dliketouseasaninputtotheGetPopularPostsusecase.
// useCases/getPopularPosts/GetPopularPostsController.ts
export class GetPopularPostsController extends BaseController {
| private useCase: | GetPopularPosts; |                  |     |
| ---------------- | ---------------- | ---------------- | --- |
| constructor      | (useCase:        | GetPopularPosts) | {   |
super();
| this.useCase | = useCase; |     |     |
| ------------ | ---------- | --- | --- |
}
async executeImpl (req: DecodedExpressRequest, res: express.Response): Promise<any> {
| const dto: | GetPopularPostsRequestDTO |     | = { |
| ---------- | ------------------------- | --- | --- |
| offset:    | req.query.offset          |     |     |
}
try {
| const                | result = | await this.useCase.execute(dto); |     |
| -------------------- | -------- | -------------------------------- | --- |
| if (result.isLeft()) |          | {                                |     |
| const                | error =  | result.value;                    |     |
374

|     | switch | (error.constructor) |     |     | {   |     |
| --- | ------ | ------------------- | --- | --- | --- | --- |
default:
|     | return |     | this.fail(res, |     | error.errorValue().message); |     |
| --- | ------ | --- | -------------- | --- | ---------------------------- | --- |
}
|     | } else | {           |     |                            |     |     |
| --- | ------ | ----------- | --- | -------------------------- | --- | --- |
|     | const  | postDetails |     | = result.value.getValue(); |     |     |
return this.ok<GetPopularPostsResponseDTO>(res, {
|     | posts: | postDetails.map((d) |     |     | => PostDetailsMap.toDTO(d)) |     |
| --- | ------ | ------------------- | --- | --- | --------------------------- | --- |
});
}
| }   | catch (err) |                | {   |      |     |     |
| --- | ----------- | -------------- | --- | ---- | --- | --- |
|     | return      | this.fail(res, |     | err) |     |     |
}
}
}
TheresponsetypeofthisDTOlookslikethefollowing.
// post/dtos/GetPopularPostsResponseDTO.ts
| import | { PostDTO  | }                          | from | "../../../dtos/postDTO"; |     |     |
| ------ | ---------- | -------------------------- | ---- | ------------------------ | --- | --- |
| export | interface  | GetPopularPostsResponseDTO |      |                          |     | {   |
| posts: | PostDTO[]; |                            |      |                          |     |     |
}
Usingarepositorytofetchthereadmodels
The repository often contains a variety of service methods that perform different queries
againstthemodel.
Forexample,intheIPostRepointerface,wedefinemethodsforgettingallrecentpostsby
callinggetRecentPosts (offset?: number),andasimilaroneforgettingpopularposts
aswell.
ThesemethodsexistsolelysothattheQueryusecasecandoitsjob.
// forum/repos/postRepo.ts
| import               | { Post        | } from    | "../domain/post";        |                          |                         |     |
| -------------------- | ------------- | --------- | ------------------------ | ------------------------ | ----------------------- | --- |
| import               | { PostId      | }         | from "../domain/postId"; |                          |                         |     |
| import               | { PostDetails |           | } from                   | "../domain/postDetails"; |                         |     |
| export               | interface     | IPostRepo |                          | {                        |                         |     |
| getPostDetailsBySlug |               |           | (slug:                   | string):                 | Promise<PostDetails>;   |     |
| getPostBySlug        |               | (slug:    | string):                 | Promise<Post>;           |                         |     |
| getRecentPosts       |               | (offset?: |                          | number):                 | Promise<PostDetails[]>; |     |
375

| getPopularPosts | (offset?: |     | number): | Promise<PostDetails[]>; |     |     |
| --------------- | --------- | --- | -------- | ----------------------- | --- | --- |
getNumberOfCommentsByPostId (postId: PostId | string): Promise<number>;
| getPostByPostId | (postId: | PostId            |     | | string): | Promise<Post>; |     |
| --------------- | -------- | ----------------- | --- | ---------- | -------------- | --- |
| exists (postId: | PostId): | Promise<boolean>; |     |            |                |     |
| save (post:     | Post):   | Promise<void>;    |     |            |                |     |
| delete (postId: | PostId): | Promise<void>;    |     |            |                |     |
}
In each of these methods, you’d implement the database query logic that does what the
methodsaysitwilldo.
Implementingpagination
TherearetwowaysthatIknowtoimplementpagination:
• Offset-based
• Cursor-based
Inoffset-basedpagination,wepassintheabsoluteindexofalltheresultsforasearch,and
weonlyseeresultsfromthenforward. It’sprettystraightforwardtoimplementwithbasic
SQLortouseanORM’sAPI.
Here’sanexampleofoffset-basedpaginationwithSequelize.
// repos/implementations/sequelizePostRepo.ts
| export class | SequelizePostRepo |     | implements |     | IPostRepo | {   |
| ------------ | ----------------- | --- | ---------- | --- | --------- | --- |
...
public async getPopularPosts (offset?: number): Promise<PostDetails[]> {
| const                 | PostModel =  | this.models.Post;                |     |          |                        |     |
| --------------------- | ------------ | -------------------------------- | --- | -------- | ---------------------- | --- |
| const                 | detailsQuery | = this.createBaseDetailsQuery(); |     |          |                        |     |
| detailsQuery.offset   |              | = offset                         |     | ? offset | : detailsQuery.offset; |     |
| detailsQuery['order'] |              | =                                | [   |          |                        |     |
| ['points',            | 'DESC'],     |                                  |     |          |                        |     |
];
| const  | posts = await | PostModel.findAll(detailsQuery); |                             |     |     |     |
| ------ | ------------- | -------------------------------- | --------------------------- | --- | --- | --- |
| return | posts.map((p) | =>                               | PostDetailsMap.toDomain(p)) |     |     |     |
}
...
}
Incursor-basedpagination,weuseacursortokeeptrackofwherethenextitemsshould
be fetched from. This works byreferring to the ID of the last objectfetched, and defining
thesearchcriteriathatledthatsearch.
Wheretogofromhere?
| There’ssomuchmoretoexplore! |     |     | Ifyou’reintoDDD,I’drecommend: |     |     |     |
| --------------------------- | --- | --- | ----------------------------- | --- | --- | --- |
376

• PeekingaroundtheDDDForum.comcodebasealittlebit
• ImplementingyourDomain-Drivenapplication
• ReadingtheoriginalDomain-DrivenDesignbook
• SendingmeanemailifyouhavequestionsonhowIcanhelpyou!
Infuturerevisionsofthisbook,I’mgoingtoincludesectionson:
• UpgradingtoEventSourcingforscalability
• Addingacacheinordertospeedupqueries
• Usinganexternalmessagequeueinsteadofanin-memoryimplementation
Resources
• TheDomain-DrivenDesignSeries@khalilstemmer.com
References
• Conway’sLaw. 5Dec.2019.
• Jonathan Oliver, and Jonathan Oliver. “DDD: Strategic Design: Core, Support-
ing, and Generic Subdomains · Jonathan Oliver.”Jonathan Oliver, 4 Apr. 2009,
https://blog.jonathanoliver.com/ddd-strategic-design-core-supporting-and-
generic-subdomains/.
• Evans, E. (2003). Domain-Driven Design: Tackling Complexity in the Heart of Soft-
ware.
• Vernon,V.(2016). ImplementingDomain-DrivenDesign.
• Chapter7ofVernon,V.(2016). Domain-DrivenDesignDistilled.
• HowtosquashbigdesignupfrontwithEventSourcing
• AwesomeEventStorming
• TransactionScript
• DomainEventsbyUdiDahan
• Supporting&CoreSubdomains
• DatabasePerServicePattern
• “UseCase.”Wikipedia,WikimediaFoundation,18Dec.2019,https://en.wikipedia.org/wiki/Use_case.
• “HexagonalArchitecture(Software).” Wikipedia,WikimediaFoundation,6Dec.2019,
https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)
• “Event Modeling.” What Is It?, 23 June 2019, https://eventmodeling.org/posts/what-
is-event-modeling/.
377