---
id: arq-023
source_file: "git-github.pdf"
words: 59996
---

Git y GitHub desde cero
Guía de estudio teórico-práctica paso a
paso más curso en vídeo
Brais Moure
Estelibroestáalaventaen
http://leanpub.com/git-github
Estaversiónsepublicóen2024-04-18ISBN
979-83-9120-047-5
Primeraedición:abrilde2023Segundaedición:abrilde
2024
Todoslosderechosreservados.Nosepermitela
reproduccióntotaloparcialdeestaobra,nisu
incorporaciónaunsistemainformáticonisu
transmisiónencualquierformaoporcualquiermedio,
seaésteelectrónico,mecánico,porfotocopia,por
grabaciónuotrosmétodos,sinelpermisoprevioypor
escritodelautor.Lainfraccióndelosderechos
mencionadospuedeserconstitutivadedelitocontrala
propiedadintelectual(Art.270ysiguientesdelCódigo
Penal).Elcopyrightestimulalacreatividad,defiendela
diversidadenelámbitodelasideasyelconocimiento,
promuevelalibreexpresiónyfavoreceunaculturaviva.
Graciasporcomprarunaediciónautorizadadeeste

libroyporrespetarlasleyesdelcopyrightalno
reproducir,escanearnidistribuirningunapartedeesta
obraporningúnmediosinpermiso.
©2023-2024BraisMoureMorais

Alacomunidad:Porapoyarmitrabajodíaadíayconvertirlo
enelmejordelmundo.

| Índice | general |     |     |     |     |     |
| ------ | ------- | --- | --- | --- | --- | --- |
Hola,mundo . . . . . . . . . . . . . . . . . . . . . . . . . 1
| README(Léeme)                 | .       | . . . . | . . . . | . . . . . . | . . . . | . . . 2 |
| ----------------------------- | ------- | ------- | ------- | ----------- | ------- | ------- |
| Guíadeestudiomáscursoenvídeo. |         |         |         | .           | . . . . | . . . 2 |
| Imágenes                      | . . . . | . . . . | . . . . | . . . . . . | . . . . | . . . 3 |
| Comparte                      | . . . . | . . . . | . . . . | . . . . . . | . . . . | . . . 3 |
| Feedbackoerrores              |         | . .     | . . . . | . . . . . . | . . . . | . . . 4 |
| Segundaedición                | .       | . . . . | . . . . | . . . . . . | . . . . | . . . 5 |
Unaño . . . . . . . . . . . . . . . . . . . . . . . . . . . 5
| Contenidoactualizado |     |     | . . . . | . . . . . . | . . . . | . . . 5 |
| -------------------- | --- | --- | ------- | ----------- | ------- | ------- |
Secciones . . . . . . . . . . . . . . . . . . . . . . . . . . . 8
| Comandos | . . . . | . . . . | . . . . | . . . . . . | . . . . | . . . 8 |
| -------- | ------- | ------- | ------- | ----------- | ------- | ------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 8
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 9
Introducción . . . . . . . . . . . . . . . . . . . . . . . . . 10
GIT . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 13
| Capítulo1:Introducción |     |     | . . . . | . . . . . . | . . . . | . . . 14 |
| ---------------------- | --- | --- | ------- | ----------- | ------- | -------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 14
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 16
Capítulo2:Historia . . . . . . . . . . . . . . . . . . . . 20
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 20

ÍNDICEGENERAL
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 22
| Capítulo3:Instalación$git |     |     |     | . . . | . . . | . . . . | . . . . | . 24 |
| ------------------------- | --- | --- | --- | ----- | ----- | ------- | ------- | ---- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 24
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 24
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 27
| Capítulo4:Comandosbásicosdelaterminal |     |     |     |     |     |     | . . | . 31 |
| ------------------------------------- | --- | --- | --- | --- | --- | --- | --- | ---- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 31
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 31
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 33
| Capítulo5:Configuración$git |     |     |     | config |     | . . | . . . . | . 37 |
| --------------------------- | --- | --- | --- | ------ | --- | --- | ------- | ---- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 37
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 37
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 38
| Capítulo | 6: Inicialización |     | de  | un  | repositorio |     |     |     |
| -------- | ----------------- | --- | --- | --- | ----------- | --- | --- | --- |
$git
init . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 42
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 42
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 42
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 44
| Capítulo7:Ramas |     | .   | . . . . | . . . . | . . . | . . . . | . . . . | . 47 |
| --------------- | --- | --- | ------- | ------- | ----- | ------- | ------- | ---- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 47
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 47
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 49
| Capítulo8:Guardado$git |     |     |     | addy$git |     | commit | . . | . 51 |
| ---------------------- | --- | --- | --- | -------- | --- | ------ | --- | ---- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 51
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 51
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 52
| Capítulo9:Estado$git |     |     | logy$git |     | status |     | . . . . | . 58 |
| -------------------- | --- | --- | -------- | --- | ------ | --- | ------- | ---- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 58
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 58
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 60

ÍNDICEGENERAL
| Capítulo      | 10: Operaciones |       | con   | ramas   | $git        |          |
| ------------- | --------------- | ----- | ----- | ------- | ----------- | -------- |
| checkouty$git |                 | reset | . . . | . . . . | . . . . . . | . . . 63 |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 63
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 63
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 65
| Capítulo11:Alias$git |     | alias | .   | . . . . | . . . . . . | . . . 69 |
| -------------------- | --- | ----- | --- | ------- | ----------- | -------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 69
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 69
| Capítulo12:Ignorarficheros.gitignore |     |     |     |     | . . . | . . . 73 |
| ------------------------------------ | --- | --- | --- | --- | ----- | -------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 73
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 73
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 76
| Capítulo13:Comparacióndecommits$git |     |     |     |     | diff | 79  |
| ----------------------------------- | --- | --- | --- | --- | ---- | --- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 79
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 79
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 81
| Capítulo14:Desplazamientosenunarama |     |     |     |     | .   | . . . 84 |
| ----------------------------------- | --- | --- | --- | --- | --- | -------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 84
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 84
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 85
| Capítulo | 15: Reset | y log | de  | referencias |     |     |
| -------- | --------- | ----- | --- | ----------- | --- | --- |
$git
| reset | --hardy$git | reflog |     | . . . . | . . . . . . | . . . 89 |
| ----- | ----------- | ------ | --- | ------- | ----------- | -------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 89
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 89
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 91
| Capítulo16:Etiquetas$git |     |     | tag | . . . | . . . . . . | . . . 95 |
| ------------------------ | --- | --- | --- | ----- | ----------- | -------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 95
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 95
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 97

ÍNDICEGENERAL
| Capítulo | 17: Creación | de  | ramas $git | branch | y   |
| -------- | ------------ | --- | ---------- | ------ | --- |
$git switch . . . . . . . . . . . . . . . . . . . . . . . 100
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 100
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 100
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 103
| Capítulo18:Combinaciónderamas$git |     |     |     | merge | . 107 |
| --------------------------------- | --- | --- | --- | ----- | ----- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 107
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 107
Capítulo19:Conflictos . . . . . . . . . . . . . . . . . . 112
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 112
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 112
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 116
| Capítulo20:Cambiostemporales$git |     |     |     | stash | . . . 122 |
| -------------------------------- | --- | --- | --- | ----- | --------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 122
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 122
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 124
| Capítulo21:Reintegraciónderamas |     |     |     | . . . . | . . . . . 127 |
| ------------------------------- | --- | --- | --- | ------- | ------------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 127
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 127
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 129
| Capítulo22:Eliminaciónderamas |     |     |     | . . . . . | . . . . . 131 |
| ----------------------------- | --- | --- | --- | --------- | ------------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 131
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 131
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 133
GITHUB . . . . . . . . . . . . . . . . . . . . . . . . . . . . 135
| Capítulo23:IntroducciónaGitHub |     |     |     | . . . . . | . . . . . 136 |
| ------------------------------ | --- | --- | --- | --------- | ------------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 136
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 140
| Capítulo24:Primerospasos |     |     | . . . | . . . . . . | . . . . . 144 |
| ------------------------ | --- | --- | ----- | ----------- | ------------- |

ÍNDICEGENERAL
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 144
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 144
| Capítulo25:Repositoriopersonal |     |     |     | . . . . | . . . . | . . . 147 |
| ------------------------------ | --- | --- | --- | ------- | ------- | --------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 147
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 151
| Capítulo26:Localyremoto |     |     | . . | . . . . . . | . . . . | . . . 159 |
| ----------------------- | --- | --- | --- | ----------- | ------- | --------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 159
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 160
| Capítulo27:AutenticaciónSSH |     |     |     | . . . . . | . . . . | . . . 163 |
| --------------------------- | --- | --- | --- | --------- | ------- | --------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 163
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 165
| Capítulo28:Repositorioproyecto. |     |     |     | . . . | . . . . | . . . 174 |
| ------------------------------- | --- | --- | --- | ----- | ------- | --------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 174
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 175
| Capítulo29:Gitenremoto$git |     |     |     | remote | . . . . | . . . 177 |
| -------------------------- | --- | --- | --- | ------ | ------- | --------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 177
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 177
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 179
| Capítulo30:Subidadeunproyecto |     |     |     | . . . | . . . . | . . . 183 |
| ----------------------------- | --- | --- | --- | ----- | ------- | --------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 183
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 183
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 184
| Capítulo31:Sincronizaciónremota$git |           |       |         |             | fetchy  |           |
| ----------------------------------- | --------- | ----- | ------- | ----------- | ------- | --------- |
|                                     | . . . . . | . . . | . . . . | . . . . . . | . . . . | . . . 187 |
| $git pull                           |           |       |         |             |         |           |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 187
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 187
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 188
| Capítulo32:Clonación$git |     |     | clone | . . . | . . . . | . . . 192 |
| ------------------------ | --- | --- | ----- | ----- | ------- | --------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 192

ÍNDICEGENERAL
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 192
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 193
| Capítulo33:Subidadecódigo$git |     | push | .   | . . . . . 196 |
| ----------------------------- | --- | ---- | --- | ------------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 196
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 196
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 198
| Capítulo34:Bifurcaciones | . . | . . . . . | . . . . | . . . . . 200 |
| ------------------------ | --- | --------- | ------- | ------------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 200
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 201
| Capítulo35:Flujocolaborativo. |     | . . . | . . . . | . . . . . 206 |
| ----------------------------- | --- | ----- | ------- | ------------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 206
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 206
| Capítulo36:Pullrequests | . . | . . . . . | . . . . | . . . . . 209 |
| ----------------------- | --- | --------- | ------- | ------------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 209
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 211
| Lección37:Ejerciciopráctico |     | . . . . . | . . . . | . . . . . 214 |
| --------------------------- | --- | --------- | ------- | ------------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 214
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 215
| Capítulo38:Conflictosenpullrequests |     |     | . . | . . . . . 217 |
| ----------------------------------- | --- | --- | --- | ------------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 217
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 219
| Capítulo39:Sincronizacióndebifurcaciones |     |     |     | . . . 225 |
| ---------------------------------------- | --- | --- | --- | --------- |
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 225
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 225
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 227
| Capítulo40:Markdown. | . . . | . . . . . | . . . . | . . . . . 230 |
| -------------------- | ----- | --------- | ------- | ------------- |
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 230
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 233

ÍNDICEGENERAL
GITyGITHUB . . . . . . . . . . . . . . . . . . . . . . . . 236
Capítulo41:Herramientasgráficas . . . . . . . . . . 237
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 237
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 239
Capítulo42:GityGitHubflow . . . . . . . . . . . . . 247
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 247
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 247
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 250
Capítulo43:EjemploGitFlow . . . . . . . . . . . . . . 258
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 258
Introducción . . . . . . . . . . . . . . . . . . . . . . . 258
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 260
Capítulo 44: Otros comandos $git cherry-pick
y$git rebase . . . . . . . . . . . . . . . . . . . . . . 267
Comandos . . . . . . . . . . . . . . . . . . . . . . . . . 267
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 267
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 271
Capítulo45:GitHubPagesyActions . . . . . . . . . 274
Conceptos . . . . . . . . . . . . . . . . . . . . . . . . . 274
Curso . . . . . . . . . . . . . . . . . . . . . . . . . . . . 277
Otroscomandos . . . . . . . . . . . . . . . . . . . . . . 280
Introducción . . . . . . . . . . . . . . . . . . . . . . . 280
Listado . . . . . . . . . . . . . . . . . . . . . . . . . . . 280
Buenasprácticas . . . . . . . . . . . . . . . . . . . . . . 283
Introducción . . . . . . . . . . . . . . . . . . . . . . . 283
Git . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . 283
GitHub . . . . . . . . . . . . . . . . . . . . . . . . . . . 285
Conclusión . . . . . . . . . . . . . . . . . . . . . . . . 286
Próximospasos . . . . . . . . . . . . . . . . . . . . . . . 288

¡Muchasgracias! . . . . . . . . . . . . . . . . . . . . . . 289

Hola, mundo
¡Hola,mundo!MinombreesBraisMoure,autordellibro.
Soy ingeniero de software desde 2010, y GitHub Star
desde2023.
En 2015 creé MoureDev, para dedicarme al desarrollo
de software de forma freelance y especializarme en la
creación de aplicaciones móviles. He publicado más de
150 apps, superado millones de descargas y colaborado
conempresasdediferentespartesdelmundo.
En 2018 comienzo a compartir contenido gratuito sobre
programación en diferentes redes sociales, utilizando
también el nombre de @mouredev. Hoy en día nuestra
comunidad, sumando todos esos canales, ha superado
elmillónymedio.
Actualmente, combino mi trabajo como programador y
divulgador.
Esteesmiprimerlibro,creadocontodomicariñodesde
Galiciaparaelmundo.Esperoqueteresultemuyútil.
Recuerda que puedes encontrar todo mi contenido en
moure.dev1.
1https://moure.dev
1

| README           |            | (Léeme)         |                |             |            |             |         |
| ---------------- | ---------- | --------------- | -------------- | ----------- | ---------- | ----------- | ------- |
| Guía             | de estudio |                 | más            | curso       |            | en vídeo    |         |
| Este libro       | está       | pensado         | para           | facilitar   | el         | aprendizaje | de      |
| las herramientas |            | Git             | y GitHub       | desde       | cero       | y de        | manera  |
| independiente,   |            | pero            | principalmente |             | funcionará |             | de      |
| una manera       |            | más efectiva    |                | si se       | aplica     | como        | recurso |
| complementario   |            | al              | curso          | práctico    | gratuito   |             | y en    |
| vídeo            | (de 5      | horas duración) |                | que         | tengo      | publicado   | en      |
| YouTube.         | Sí,        | 100% gratuito.  |                | Solo tienes |            | que entrar  | en      |
mouredev.com/git-github1.
Entonces¿Porquéunlibro?
| Personalmente, |         | porque      | creo | que           | la mejor | manera | de        |
| -------------- | ------- | ----------- | ---- | ------------- | -------- | ------ | --------- |
| asegurar       | nuestro | aprendizaje |      | es combinando |          |        | esta guía |
1https://mouredev.com/git-github
2

3
de fácil comprensión y el curso en vídeo. La guía servirá
para seguir el curso paso a paso, explicar cada lección,
extender sus conceptos y aprender muchas cosas
nuevas. También encontrarás apartados para destacar y
ampliar las ideas más importantes, y podrás consultar
cualquierdudarápidamente.
Una vez aclarado esto, tú decides si leer este libro puede
servirtedeayuda.
Apoyar esta publicación me sirve para
seguir creando contenido gratuito sobre
programación y desarrollo de software día a
día.¡Muchasgracias!
Imágenes
Las imágenes del libro se corresponden con capturas
de pantalla del curso en vídeo (actualizadas en esta
segunda edición), para ayudarte a obtener una
referencia temporal. No son un elemento esencial
para el seguimiento de la guía. Puedes consultarlas en
máxima resolución, a color, y ordenadas por lección,
accediendoamouredev.com/imagenes-libro-git2.
Comparte
¿Quieres que más gente conozca este recurso?
Nómbrame como @mouredev en redes sociales y
cuéntaleatodoelmundoquétehaparecido.
2https://mouredev.com/imagenes-libro-git

4
| También puedes | dejar | una reseña | con tu opinión | en la |
| -------------- | ----- | ---------- | -------------- | ----- |
plataformaenlaquehayasadquiridoellibro.
| Feedback      | o errores    |           |                 |     |
| ------------- | ------------ | --------- | --------------- | --- |
| Si encuentras | algún error, | o quieres | darme feedback, | no  |
dudesenescribirmeabraismoure@mouredev.com.
Agradezcoenormementetucolaboración.

Segunda edición
Un año
En el momento en el que escribo esta sección ha
transcurrido un año desde la publicación del libro. Sólo
puedo decir una cosa: GRACIAS. Ni en mis mejores
sueñosimaginéquelaacogidaibaasertangrande.
El libro ha vendido miles de copias, cientos de personas
han colaborado en el repositorio, he recibido cientos
de mensajes de agradecimiento, habéis aparecido en
eventos con él para que os lo firme y, un año después,
sigueocupandolasprimerasposicionesenelrankingde
ventasdesucategoría.Lorepito:GRACIAS,decorazón.
Durante este año también sucedió algo que me
hace muy feliz, y me gustaría compartir contigo:
GitHub me reconoció como GitHub Star. Un premio
internacional otorgado a los desarrolladores más
influyentes por sus aportes a la comunidad, y tú eres
parte de él. Puedes encontrar más información en
stars.github.com/profiles/mouredev1.
Contenido actualizado
Aquí tienes un resumen de los cambios introducidos en
estasegundaedición:
1https://stars.github.com/profiles/mouredev
5

6
• Todas las imágenes de los capítulos de la guía se
hangeneradodenuevoparamejorarsulegibilidad.
Si algo se ha señalado en repetidas ocasiones
sobre la primera edición, es que las imágenes
(correspondientes a las capturas de pantalla del
curso) en la versión impresa no poseían la calidad
suficiente. Si bien es cierto, quiero apuntar un par
decosas:
– Lamentablemente,esteesunlibroautoeditado.
Esto quiere decir, entre muchas cosas, que no
tengo control sobre el proceso de impresión
llevado a cabo por Amazon. Al no contar con
una editorial, de hacerlo de otra forma, me
resultaría imposible costear y coordinar los
envíos.Losientomucho.
– Repetir que, igual que se dice en el apartado
anterior, las imágenes son referencias al
curso en vídeo y, por lo tanto, no son
esenciales para comprender el contenido
del libro. Aún así, puedes consultar el vídeo
(mouredev.com/git-github2) y la versión digital
de las imágenes (mouredev.com/imagenes-
libro-git3)encualquiermomento.
• El comando git checkout HEAD se ha modificado
porgit checkout HEAD -- .enelcapítulo14.
• Cada vez que se nombra en el capítulo 19 el
comando git merge --mine, también se hará
referenciaa--ours.Tambiénseespecificaquepara
laresolucióndeconflictosdebeañadirseelnombre
delarchivo.
2https://mouredev.com/git-github
3https://mouredev.com/imagenes-libro-git

7
| • Se    | ha ampliado |         | el apartado |          | correspondiente |              | a      |
| ------- | ----------- | ------- | ----------- | -------- | --------------- | ------------ | ------ |
| GitHub  | Actions     |         | en el       | capítulo | 45.             | Ahora        | podrás |
| conocer |             | también | cómo        | funciona |                 | esta potente |        |
funcionalidaddeGitHub.
• Antesdelúltimoapartadodellibro,llamado“Buenas
| prácticas”, |     | se ha | añadido | uno | nuevo | con | “Otros |
| ----------- | --- | ----- | ------- | --- | ----- | --- | ------ |
comandos”.
| • Por         | último, | se  | han corregido |     | pequeños      |     | errores |
| ------------- | ------- | --- | ------------- | --- | ------------- | --- | ------- |
| ortográficos. |         | Ten | en cuenta     | que | la numeración |     | de      |
laspáginashacambiado.
| No son  | grandes        | cambios, | pero        | espero |        | que ayuden | a   |
| ------- | -------------- | -------- | ----------- | ------ | ------ | ---------- | --- |
| mejorar | la experiencia |          | de lectura. |        | Muchas | gracias    | por |
contribuiralograrlo.

Secciones
El libro estará dividido principalmente en una sección
dedicada a Git y otra a GitHub. Llegando a combinar
ambas partes hacia el final de este. También podrás
encontrar un capítulo completo dedicado a realizar
un ejercicio práctico y colaborativo entre todos los
participantesdelcurso.
Porotraparte,laguíacuentacon45capítulosdiferentes,
divididos en tres apartados (comandos, conceptos y
curso)queserepetiránencadaunodeellos.
Vamos a detallar a continuación el objetivo de cada
apartado.
Comandos
Sección opcional (no aparecerá en todos los capítulos)
que resumirá los comandos de Git que se utilizarán por
primera vez en un capítulo del vídeo. Es una manera
de asociar rápidamente las instrucciones de línea de
comandos relacionadas con cada uno de los temas que
vamosatratarparaaprenderatrabajarconGit.
Conceptos
Sección que servirá para introducir el capítulo y tratar
de forma teórica cada uno de los conceptos que
8

9
| aprenderemos |     | en  | él. Cada | concepto |     | dispondrá | de su |
| ------------ | --- | --- | -------- | -------- | --- | --------- | ----- |
propioapartadoindividual.
Curso
| Sección  | que | aplicará    | de  | forma   | práctica   | los | conceptos  |
| -------- | --- | ----------- | --- | ------- | ---------- | --- | ---------- |
| tratados | en  | el apartado |     | previo. | Explicando |     | cómo hacer |
usodeellosenunsupuestoreal.
Esteapartadoestádirectamenterelacionadoconelcurso
elvídeo,porloqueiniciarásiempreconuntextocomoel
quesigueacontinuación:
Introducción:mouredev.com/git-github1
Inicio:00:00:00|Duración:00:03:15
| En él podrás |         | visualizar | el  | título  | de la lección, |     | un enlace |
| ------------ | ------- | ---------- | --- | ------- | -------------- | --- | --------- |
| directo      | a dicha | clase,     | su  | inicio, | y la duración  |     | total del |
fragmentodevídeo.PruebaaaccederaestaprimeraURL
delcursohttps://mouredev.com/git-github.
Esteúltimoapartado,enalgúnmomento,puederesultar
| redundante |      | con respecto |          | al de | conceptos. | Lo  | considero   |
| ---------- | ---- | ------------ | -------- | ----- | ---------- | --- | ----------- |
| necesario  | para | poder        | explicar | cada  | una        | de  | las ideas y |
favorecersuaprendizaje.
1https://mouredev.com/git-github

Introducción
| Trabajar     | con | nuestro | código   | de     | forma      | segura     |     | es tan |
| ------------ | --- | ------- | -------- | ------ | ---------- | ---------- | --- | ------ |
| importante   |     | como    | aprender | a      | programar, |            | por | eso,   |
| herramientas |     | como    | Git y    | GitHub | son        | esenciales |     | en el  |
mundodeldesarrollodesoftware.
| Registrar | el     | histórico | de         | trabajo | de       | nuestro |             | código, |
| --------- | ------ | --------- | ---------- | ------- | -------- | ------- | ----------- | ------- |
| generar   | copias | de        | seguridad, | y       | trabajar | en      | equipo      | de      |
| forma     | rápida | y sin     | errores.   | Estas   | son      | las     | principales |         |
característicasdeGit.
| Con este | libro, | y a través | de  | 45 capítulos, |     | aprenderemos |     |     |
| -------- | ------ | ---------- | --- | ------------- | --- | ------------ | --- | --- |
desdeceroypasoapasotodolonecesarioparatrabajar
conGit,elsistemadecontroldeversionesporexcelencia,
yGitHub,laplataformaenlanubedecódigocolaborativo.
Cuandomeplanteélatemáticadeunnuevocursosobre
programación,medicuentadequeGitestápresenteen
| todo el      | sector. | Sinceramente, |     | no       | existe   | una          | tecnología |     |
| ------------ | ------- | ------------- | --- | -------- | -------- | ------------ | ---------- | --- |
| que se       | utilice | tanto como    | Git | en       | el mundo | del          | desarrollo |     |
| de software, |         | sin importar  | el  | lenguaje | de       | programación |            | o   |
elentornoenelquetrabajemos.Sinduda,esunestándar
quedebemosdeconocer.
¿Notelocrees?Vamosarevisaralgúndato:
| Comencemos |     | con | la encuesta |     | desarrolladores |     |     | de  |
| ---------- | --- | --- | ----------- | --- | --------------- | --- | --- | --- |
(insights.stackoverflow.com/survey1).
| StackOverflow |     |     |     |     |     |     |     | Es la |
| ------------- | --- | --- | --- | --- | --- | --- | --- | ----- |
másimportantedelsector,yseguramentelamejorpara
| representar |     | las tendencias |            | actuales. |            | Pues bien, | cuando       |     |
| ----------- | --- | -------------- | ---------- | --------- | ---------- | ---------- | ------------ | --- |
| se pregunta |     | sobre          | el sistema |           | de control |            | de versiones |     |
1https://insights.stackoverflow.com/survey
10

11
más utilizado, aquí tienes los resultados: De toda la
gentequeharespondidolaencuesta,másdel93%utiliza
Git. Si revisamos los datos a nivel profesional, casi un
97%. Si en esta misma encuesta buscamos la sección de
plataformas de control de versiones, observaremos algo
muy parecido. GitHub es con diferencia la más utilizada.
Un87%anivelpersonalyun55%anivelprofesional.
¿Quieresmásmotivos?Aquítienes:
Estos son los resultados de la encuesta de la propia
GitHub, su llamado, Octoverse (octoverse.github.com2).
Enelúltimoañohaalcanzadocifrashistóricas,ymásdel
90%delasempresasmejorvaloradasdelmundoutilizan
estaplataformaparaalojarsucódigo.
Creo que estos son motivos que nos dejan muy clara la
importanciadeGitydeGitHub.
Pues bien, lo que vas a leer a continuación es el
resultadodesuimportancia.Unaguíadesdeceroypara
principiantes, basada en mi curso en vídeo y utilizando
cada lección para introducir, ejemplificar y ampliar
conocimientos.
A lo largo del curso encontrarás un ejercicio para poner
en práctica todo lo aprendido, pero antes de comenzar
me gustaría hacer un repaso a los cuatro recursos que
tienes a tu disposición para comenzar tu aprendizaje e
intentarqueseamásamenoycercano.
• Enprimerlugar,susitioweb(github.com/mouredev/hello-
git3). Allí podrás encontrar las 45 clases y un link
que te llevará a cada parte concreta del curso,
donde podrás ver en vídeo cada uno de los
2https://octoverse.github.com
3https://github.com/mouredev/hello-git

12
| conceptos. |     | También | encontrarás |     |     | en este | lugar | toda |
| ---------- | --- | ------- | ----------- | --- | --- | ------- | ----- | ---- |
lainformacióndelcursoyenlacesrelevantes.
| • Por                   | otra     | parte,    | también |             | tienes    | a tu        | disposición |       |
| ----------------------- | -------- | --------- | ------- | ----------- | --------- | ----------- | ----------- | ----- |
| el                      | servidor | de        | Discord |             | de        | la          | comunidad   |       |
| (discord.gg/mouredev4). |          |           |         | En          | él        | encontrarás |             | un    |
| canal                   | que      | se llama  |         | git-github, |           | donde       | preguntar,  |       |
| compartir               |          | y charlar | con     | la          | comunidad |             | sobre       | estas |
tecnologías.
• TambiéntienesmicanaldeTwitch(twitch.tv/mouredev5),
| donde | realizo | directos |     | de  | lunes | a viernes, | y   | donde |
| ----- | ------- | -------- | --- | --- | ----- | ---------- | --- | ----- |
sellevóacaboestecursoenvídeo.
| Aquí va       | un poco | de filosofía |                   | GitHub | (aunque |            | te explicaré |       |
| ------------- | ------- | ------------ | ----------------- | ------ | ------- | ---------- | ------------ | ----- |
| más adelante  |         | qué es       | esta plataforma): |        |         | Si quieres | apoyar       |       |
| este recurso, |         | simplemente  |                   | puedes | hacer   | star       | en el        | sitio |
| web que       | te he   | compartido.  |                   | Es una | manera  | muy        | rápida       | y     |
valiosadeapoyarestecontenido.
| Ya, por                     | último,   | y para          | practicar, |              | tienes    | la         | plataforma |        |
| --------------------------- | --------- | --------------- | ---------- | ------------ | --------- | ---------- | ---------- | ------ |
| de retos                    |           | de programación |            |              | de        | la         | comunidad  |        |
| (retosdeprogramacion.com6). |           |                 |            |              | Un lugar  | donde,     |            | por    |
| un lado,                    | practicar | programación,   |            |              | y, por    | otro       | lado,      | gran   |
| parte de                    | los       | conceptos       | que        | aprenderemos |           |            | en el      | curso, |
| ya que                      | la forma  | que             | tenemos    | de           | compartir | cada       | una        | de     |
| las soluciones              |           | a los retos     | de         | código       | será      | utilizando |            | Git y  |
GitHub.
| Hecho esta | introducción… |     |     | ¡Bienvenido/a |     | a Git | y GitHub |     |
| ---------- | ------------- | --- | --- | ------------- | --- | ----- | -------- | --- |
desdecero!
4https://discord.gg/mouredev
5https://twitch.tv/mouredev
6https://retosdeprogramacion.com

GIT
SeccióndedicadaalestudiodeGit
13

Capítulo 1: Introducción
Conceptos
Introducción
Gitesunsistemadecontroldeversionesdistribuidoque
se ha convertido en una herramienta esencial para la
mayoría de los desarrolladores de software. A menudo,
se confunde con GitHub, pero son dos conceptos
diferentes. GitHub es una plataforma en línea que se
utiliza para alojar proyectos que se gestionan mediante
Git. Este libro se centrará en esta primera parte en Git,
y en cómo utilizarlo para controlar las versiones de
nuestrocódigo.Contaráconunasegundapartecentrada
enGitHubyencómousarlojuntoaGit.
Web oficial
La página web oficial de Git es git-scm.com1. Aquí
podemosencontrartodalainformaciónquenecesitamos
para empezar a trabajar con Git. La página ofrece una
guía detallada sobre cómo utilizar Git, y una lista
completa de comandos que podemos usar. También
tiene publicado online un libro gratuito llamado Pro
Git, que está disponible en varios idiomas, incluyendo
español.
1https://git-scm.com
14

15
Git y GitHub
Git es una herramienta de control de versiones
distribuido, que nos permite a los desarrolladores
trabajar en un proyecto sin necesidad de estar
conectados a un servidor central. Por otro lado, GitHub
es una plataforma en línea que se utiliza para alojar
proyectos que se gestionan mediante Git. Aunque
GitHubdependedeGit,nosonlomismo.
Sistemas de control de versiones
Unsistemadecontroldeversionesesunaherramienta
que se utiliza para mantener un registro de los
cambios que se hacen en un proyecto. Permite a
los desarrolladores trabajar en un proyecto de manera
colaborativa, manteniendo un historial de cambios
y documentando cada uno de ellos. De esta forma,
se puede seguir el progreso del proyecto y volver a
versiones anteriores si es necesario, entre muchas otras
funcionalidades.
Ramas
Unaramaesunnuevocaminoasociadoalcódigoquese
está desarrollando en un proyecto. Las ramas permiten
a los desarrolladores trabajar en diferentes partes del
proyecto al mismo tiempo, sin interferir en el trabajo de
los demás. Una vez que completamos una tarea en una
rama, podemos fusionarla con otra rama del proyecto y
combinarsuscambios.

16
Conclusión
Git es una herramienta muy potente que nos permite
controlar las versiones de nuestro código de manera
eficiente y colaborativa. La página web oficial de Git
es una gran fuente de información para aprender a
utilizar Git y para resolver cualquier duda que podamos
tener. Además, GitHub nos ofrece una plataforma
para alojar nuestros proyectos y colaborar con otros
desarrolladores. En resumen, si nos interesa el sector
del desarrollo de software, es imprescindible conocer y
saberutilizarGit.
Curso
Lección1:mouredev.com/git-github-012
Inicio:00:03:15|Duración:00:07:06
Comencemos hablando del primer sitio importante
que debemos conocer: la página web oficial de Git.
Así, iremos entendiendo poco a poco de qué trata
esta herramienta. Dicha web será git-scm.com,3, y
aquí podremos encontrar prácticamente todo sobre
Git. Hay que dejar claro que existen dos conceptos:
por un lado, Git, y por otro, GitHub. Por ahora, solo
hablaremos de Git, sin confundirlo con GitHub. Más
adelante,comenzaremoslaseccióndedicadaaGitHub.
2https://mouredev.com/git-github-01
3https://git-scm.com,

17
En primer lugar, entendamos que Git es independiente
deGitHub,aunqueGitHubsídependadeGit.
Git es de código abierto, y todo su código, para que nos
hagamos una idea, está en GitHub. Todo el código de
Git es libre y se puede leer desde GitHub, que es una
plataformadondesealojacódigofuente.
Gitesunsistemadecontroldeversionesdistribuidomuy
importante. Existen diferentes sistemas de control de
versiones, pero no todos son distribuidos. ¿Qué significa
esto? Que no depende de un único sitio. Si ese sitio se
borra o falla, el código podría perderse, pero con Git, al
ser distribuido, podemos tener una copia del código en
cadaequipodelaspersonasquetrabajanenunproyecto.
Si el servidor central falla, podríamos recuperar dicho
trabajo al tener almacenado de manera local parte del
códigoyelhistorialdecambios.
Bien, entendido el concepto de distribuido, ¿qué es
un sistema de control de versiones? Si trabajas en
programación, o incluso si estás empezando, quizás has
visto bromas asociadas a proyectos con nombres como
proyecto-final-version-2 o proyecto-final-version-2-final-

18
superfinal. Eso sucede cuando no trabajamos con un
sistema de control de versiones, lo que nos puede llevar
aperderinformación,cometererroresoborrarnuestros
avances.
Un sistema de control de versiones nos permite
llevar un registro de todo el historial de un proyecto,
documentando y trazando cada uno de esos cambios.
Podemos navegar por ese historial como si fueran
los mensajes de WhatsApp, yendo hacia atrás o hacia
adelante, borrando o saltando entre conversaciones.
Iremos entendiendo todo esto poco a poco, no te
preocupes.
También acabaremos comprendiendo los conceptos
principales asociados a las ramas, y cómo nos
moveremos entre ellas según nuestro proyecto
evoluciona. Así que, resumiendo, tengamos siempre
presentelaweboficial,esungranrecurso.
Te contaré un secreto. La web de Git es uno
de los mejores lugares para aprender Git.
Contiene mucha documentación y un libro
gratuitollamadoProGit,enespañol.Traducido
por la comunidad y validado por la gente de
Git. Se puede comprar, pero también está
disponible gratis. Eso sí, se trata de un texto
mucho más académico que el libro que te
encuentrasleyendo.

19
| Cuando      | lleguemos a la  | parte de GitHub,      | también |
| ----------- | --------------- | --------------------- | ------- |
| descubrirás | dónde encontrar | toda la documentación | de      |
GitHub.

Capítulo 2: Historia
Conceptos
Introducción
El control de versiones es fundamental en el mundo del
desarrollo de software. Cuando varios desarrolladores
participanenunmismoproyecto,esnecesariocoordinar
sus esfuerzos y asegurarnos de que nos encontramos
trabajando sobre una versión coherente del código.
Además, es importante poseer un historial de cambios
para poder volver atrás, en caso de cometer errores,
o para recuperar una versión anterior que funcione
correctamente.
Git es uno de los sistemas de control de versiones más
populares y utilizados en la actualidad. En este libro,
profundizaremos en su funcionamiento, configuración y
uso, para sacarle el máximo partido a esta herramienta.
Peroantes,unpocodehistoria.
El origen
Git fue creado por Linus Torvalds, el creador del kernel
de Linux. En sus inicios, Linus utilizaba otro sistema de
control de versiones llamado BitKeeper, para administrar
el desarrollo de Linux. Sin embargo, en 2005, se produjo
unadisputaconlaempresapropietariadeBitKeeper,que
20

21
| llevóaquelacomunidaddedesarrollodeLinux |     |     |     |     |     |     | perdiera |     |
| --------------------------------------- | --- | --- | --- | --- | --- | --- | -------- | --- |
elaccesoaestaherramienta.
Anteestasituación,Linusdecidiócrearsupropiosistema
| de control  |              | de versiones |            | que pudiera |                | cumplir | con   | las    |
| ----------- | ------------ | ------------ | ---------- | ----------- | -------------- | ------- | ----- | ------ |
| necesidades |              | del          | desarrollo | de          | Linux.         | Así     | nació | Git,   |
| que en      | un principio |              | fue        | utilizado   | exclusivamente |         |       | por la |
comunidaddedesarrollodeLinux,peroquerápidamente
seextendióaotrosproyectosyempresas.
Las ventajas
| Una de | las | principales |     | ventajas | de  | Git es | que | es un |
| ------ | --- | ----------- | --- | -------- | --- | ------ | --- | ----- |
sistemadistribuido.Estosignificaque,cadadesarrollador
| tiene una | copia   | completa |     | del repositorio |     | en         | su máquina, |          |
| --------- | ------- | -------- | --- | --------------- | --- | ---------- | ----------- | -------- |
| lo que    | permite | trabajar |     | sin conexión    |     | a internet | y           | facilita |
lacolaboraciónenequiposremotos.
| Otra ventaja      |                 | de Git    | es que   | es muy | eficiente  | en               | la gestión  |       |
| ----------------- | --------------- | --------- | -------- | ------ | ---------- | ---------------- | ----------- | ----- |
| de modificaciones |                 |           | y ramas. | Git    | permite    |                  | crear       | ramas |
| de forma          | muy             | sencilla, |          | lo que | facilita   | la incorporación |             |       |
| de nuevas         | funcionalidades |           |          | y la   | corrección |                  | de errores. |       |
Además,Gittieneherramientasparacompararversiones
| y fusionar | esas | ramas, |     | lo que | hace | que el | proceso | de  |
| ---------- | ---- | ------ | --- | ------ | ---- | ------ | ------- | --- |
integracióndecambiosseamuchomásfácilyseguro.
| Git también  |       | es muy    | flexible | en          | cuanto      | a         | la forma  | de   |
| ------------ | ----- | --------- | -------- | ----------- | ----------- | --------- | --------- | ---- |
| trabajar.    | Nos   | permite   |          | desarrollar | empleando   |           | distintos |      |
| flujos       | para  | gestionar |          | nuestro     | código,     | desde     | los       | más  |
| sencillos    | hasta | los       | más      | complejos.  |             | Esto hace | que       | sea  |
| muy versátil |       | y se      | adapte   | a las       | necesidades |           | de        | cada |
proyecto.
| Por último, |           | Git es      | una | herramienta     | open | source,    |          | lo que |
| ----------- | --------- | ----------- | --- | --------------- | ---- | ---------- | -------- | ------ |
| significa   | que       | es gratuita |     | y cuenta        | con  | el         | respaldo | de     |
| una gran    | comunidad |             | de  | desarrolladores |      | y usuarios |          | que    |

22
| constantemente |     |     | están | mejorando |     | y actualizando |     | la  |
| -------------- | --- | --- | ----- | --------- | --- | -------------- | --- | --- |
herramienta.
Conclusión
| Hemos          | dado | contexto       | a   | Git, | su origen | y      | sus ventajas. |     |
| -------------- | ---- | -------------- | --- | ---- | --------- | ------ | ------------- | --- |
| Es importante  |      | entender       |     | que  | Git       | es una | herramienta   |     |
| que evoluciona |      | constantemente |     |      |           | y que  | sigue siendo  |     |
fundamentalenelmundodeldesarrollodesoftware.
| En los            | próximos |      | capítulos |      | profundizaremos |         |                | en  |
| ----------------- | -------- | ---- | --------- | ---- | --------------- | ------- | -------------- | --- |
| el funcionamiento |          |      | de        | Git, | desde           |         | la instalación |     |
| y configuración,  |          |      | hasta     | el   | uso             | de      | las distintas  |     |
| funcionalidades   |          | para | gestionar |      | el              | control | de versiones   |     |
denuestrosproyectos.
Curso
Lección2:mouredev.com/git-github-021
Inicio:00:10:21|Duración:00:04:14
| Antes          | de introducirnos |          |              | en la  | descarga    | y        | configuración |        |
| -------------- | ---------------- | -------- | ------------ | ------ | ----------- | -------- | ------------- | ------ |
| Git, es        | importante       |          | dar contexto |        | sobre       | este     | sistema       | de     |
| control        | de versiones.    |          | Git          | es una | herramienta |          | que           | se usa |
| principalmente |                  | en       | el sector    | del    | desarrollo  |          | de software,  |        |
| estando        | presente         |          | en él        | desde  | hace        | bastante | tiempo.       |        |
| Apareció       | el 7             | de abril | de           | 2005,  | y cuenta    | con      | el respaldo   |        |
detodalacomunidadopensource.
| Git sigue      | evolucionando |     |          | constantemente, |            |     | y no          | es  |
| -------------- | ------------- | --- | -------- | --------------- | ---------- | --- | ------------- | --- |
| una tecnología |               | en  | absoluto |                 | estancada. |     | De hecho,     | es  |
| muy posible    |               | que | se haya  | lanzado         |            | una | nueva versión |     |
1https://mouredev.com/git-github-02

23
| recientemente |     | (y  | seguramente |     | no  | importa | en  | qué |
| ------------- | --- | --- | ----------- | --- | --- | ------- | --- | --- |
momentoestésleyendoestelibro).Espartefundamental
eneldíaadíadelmundodeldesarrollodesoftware.
| Ahora | bien, | es interesante |     | que | conozcamos |     | quién | es el |
| ----- | ----- | -------------- | --- | --- | ---------- | --- | ----- | ----- |
creadordeGit.SetratadeLinusTorvalds,unafiguraclave
enelmundodelsoftware,que,simepermites,tenemos
laobligacióndeconocer.Linustambiéneselcreadordel
| kernel | de Linux, | y mientras |     | trabajaba |     | en él, | se dio cuenta |     |
| ------ | --------- | ---------- | --- | --------- | --- | ------ | ------------- | --- |
dequelossistemasdecontroldeversionesexistentesno
cumplíansusexpectativasynecesidades.
Porello,yporalgúnotromotivoquecomentéalprincipio
delcapítulo,decidiócrearsupropiosistemadecontrolde
versiones,yasínacióGit.Hoyendía,prácticamentetodo
elmundoenelsectorutilizaestaherramienta.
| Git es       | un sistema |           | de control |               | de versiones |     | con años   | de  |
| ------------ | ---------- | --------- | ---------- | ------------- | ------------ | --- | ---------- | --- |
| trayectoria, |            | que sigue |            | evolucionando |              | y   | cuenta con | el  |
respaldodelacomunidaddedesarrollo.LinusTorvalds,su
| creador, | es una | figura | icónica |     | en el mundo |     | del software, |     |
| -------- | ------ | ------ | ------- | --- | ----------- | --- | ------------- | --- |
yesimportanteconocersucontribuciónatravésdeGity
| elkernel | deLinux. |     |     |     |     |     |     |     |
| -------- | -------- | --- | --- | --- | --- | --- | --- | --- |

Capítulo 3: Instalación
$git
Comandos
1 git
2 git --version
3 git -v
4 git -h
Conceptos
Introducción
AntesdeempezarautilizarGit,necesitamosconfigurarlo
correctamente en nuestro sistema. A continuación,
vamosadetallarcómoinstalarGitendiferentessistemas
operativos.
Instalación en Windows
SiusamosWindows,laformamásfácildeinstalarGites
atravésdelapáginadedescargasdeGitparaWindows:
git-scm.com/download/win1
1https://git-scm.com/download/win
24

25
| Descargaremos      |     | el  | archivo | de       | instalación  | y   | seguiremos   |
| ------------------ | --- | --- | ------- | -------- | ------------ | --- | ------------ |
| las instrucciones. |     |     | Durante | la       | instalación, |     | se instalará |
| automáticamente    |     |     | una     | terminal | compatible   |     | con Git,     |
llamadaGitBash.
| Instalación |        | en     | macOS   |          |          |      |             |
| ----------- | ------ | ------ | ------- | -------- | -------- | ---- | ----------- |
| Si usamos   | macOS, |        | podemos |          | instalar | Git  | a través de |
| Homebrew,   | un     | gestor | de      | paquetes | popular  | para | equipos     |
Apple:
git-scm.com/download/mac2
| Para instalar |     | Git desde | Homebrew, |     | abrimos |     | la terminal y |
| ------------- | --- | --------- | --------- | --- | ------- | --- | ------------- |
escribimoslosiguiente:
| 1 brew install |     | git |            |     |     |     |     |
| -------------- | --- | --- | ---------- | --- | --- | --- | --- |
| Instalación    |     | en  | Linux/Unix |     |     |     |     |
LamayoríadelasdistribucionesdeLinuxyaincluyenGit
ensusrepositorios:
git-scm.com/download/linux3
| Por ejemplo, |     | para | instalar | Git | en Ubuntu |     | o Debian, |
| ------------ | --- | ---- | -------- | --- | --------- | --- | --------- |
abrimoslaterminalyescribimos:
| 1 sudo apt-get |     | install |     | git |     |     |     |
| -------------- | --- | ------- | --- | --- | --- | --- | --- |
EncasodeFedora,abrimoslaterminalyescribimos:
2https://git-scm.com/download/mac
3https://git-scm.com/download/linux

26
| sudo dnf | install |     | git |     |     |     |     |
| -------- | ------- | --- | --- | --- | --- | --- | --- |
1
| Verificación |               | de      | la instalación |                |         |                |           |
| ------------ | ------------- | ------- | -------------- | -------------- | ------- | -------------- | --------- |
| Una vez      | que           | hayamos | instalado      | Git,           | podemos |                | verificar |
| si funciona  | correctamente |         |                | escribiendo    | el      | comando        | git       |
| en la        | terminal.     | Si      | visualizamos   | una            | lista   | de             | posibles  |
| comandos,    | significa     |         | que            | está instalado |         | correctamente. |           |
TambiénpodemoscomprobarlaversióninstaladadeGit
| congit | --versionogit |     |     | -v. |     |     |     |
| ------ | ------------- | --- | --- | --- | --- | --- | --- |
SinosabemoscómoutilizarGit,ocualquierherramienta
| desde   | la terminal, |          | probaremos | a           | escribir | el       | comando  |
| ------- | ------------ | -------- | ---------- | ----------- | -------- | -------- | -------- |
| seguido | de           | -h, para | obtener    | ayuda.      | Por      | ejemplo, | git      |
| -h nos  | mostrará     | una      | lista      | de comandos |          | y        | opciones |
disponibles.
Uso básico
UnavezinstaladoGitcorrectamente,eshoradeempezar
| a utilizarlo. |              | Podremos | hacerlo  | desde  |     | la terminal | o      |
| ------------- | ------------ | -------- | -------- | ------ | --- | ----------- | ------ |
| distintas     | herramientas |          | gráficas | (GUIs) |     | como        | GitHub |
Desktop,GitKraken,SourcetreeoFork.
| Además         | de    | las        | herramientas |                   |          | mencionadas |        |
| -------------- | ----- | ---------- | ------------ | ----------------- | -------- | ----------- | ------ |
| anteriormente, |       | también    |              | podemos           | integrar |             | Git en |
| nuestro        | flujo | de trabajo |              | diario utilizando |          | editores    | de     |
códigooIDEs,comoVisualStudioCodeoIntelliJIDEA,entre
otros.
| Estos        | editores       | e    | IDEs     | ofrecen           |              | extensiones | y       |
| ------------ | -------------- | ---- | -------- | ----------------- | ------------ | ----------- | ------- |
| complementos |                | para | integrar | Git               | directamente |             | en el   |
| entorno      | de desarrollo. |      | Con      | estas extensiones |              |             | podemos |
llevaracaboflujosdetrabajocompletosenGit.

27
Una vez conozcamos el uso de Git, dependerá de
nosotros seleccionar las herramientas que mejor se
adaptenanuestramaneradetrabajar.
Conclusión
Git resulta muy simple de instalar. Siendo su uso igual
entresistemasoperativos,independientementedeenel
quenosencontremos.
Comenzaremos a usar Git desde la terminal, para
así entender los fundamentos del sistema de control
de versiones y generar unas bases más sólidas de
conocimiento.
Curso
Lección3:mouredev.com/git-github-034
Inicio:00:14:35|Duración:00:09:25
Vamos a abordar la configuración de Git y su instalación
en distintos sistemas operativos. Como ya hemos
comentado anteriormente, Git fue creado por Linus
Torvalds y es ampliamente utilizado por empresas
como Google, Microsoft o Netflix, entre otra infinidad de
compañías.
Si usamos Linux o macOS, es probable que ya tengamos
Git instalado por defecto. Sin embargo, puede que la
versión no esté actualizada. No debemos preocuparnos,
ya que, para llevar a cabo este curso, es muy posible
quelaversiónquetengamosinstaladayaseasuficiente.
4https://mouredev.com/git-github-03

28
Vamos a cubrir temas fundamentales que funcionan en
casicualquierversióndeGit.
EnladocumentaciónoficialdeGit(git-scm.com/downloads5),
encontraremos instrucciones detalladas sobre cómo
instalarlo en nuestro sistema operativo. Simplemente
tenemos que dirigirnos a la sección de descargas y
seleccionarnuestrosistemaoperativo.
Enmicaso,yduranteestecurso,estaréusando
macOS, pero también existen opciones para
WindowsyotrossistemasUnix comoLinux.
Una vez tengamos Git instalado, podremos trabajar
con él desde la terminal del sistema, o mediante
herramientas gráficas. Aunque es posible utilizar
herramientas gráficas, inicialmente aprenderemos Git
desde la terminal, ya que nos ayudará a comprender
cómofuncionarealmente.
Existen diferentes clientes gráficos disponibles para
Windows, macOS, Linux, Android e iOS. Durante el curso,
también descubriremos cómo utilizar Git desde editores
de código o IDEs, GitHub Desktop, GitKraken, Sourcetree
o Fork, entre otros, pero siente la libertad de explorar y
elegirlaherramientaquemásteguste.
Hablemosdelainstalaciónpasoapaso.Sinuncahemos
usado una terminal, también aprenderemos a utilizarla.
Dependiendo del sistema operativo que estemos
utilizando, necesitaremos una terminal compatible
con Git, como Git Bash en Windows. Al instalar Git en
Windows, también se instalará dicha terminal Bash
automáticamente.
5https://git-scm.com/downloads

29
En macOS, podemos instalar Git usando Homebrew,
un gestor de paquetes. Simplemente escribimos brew
install gitenlaterminalyseguimoslasinstrucciones.
Siyalotenemosinstalado,laterminalnosloindicará.
Una vez instalado Git, podemos verificar si funciona
correctamenteescribiendoelcomandogitenlaconsola.
Si visualizamos una lista de comandos, significará que
está instalado correctamente. También podemos
comprobarlaversióndeGitutilizandoloscomandosgit
--versionogit -v.
SinosabemoscómoutilizarGit,ocualquierherramienta
desdelaterminal,podemosprobaraescribirelcomando
seguido de -h para obtener ayuda. Por ejemplo, git
-h nos mostrará una lista de comandos y opciones
disponibles.

30
| Recordemos | que Git funciona | de la misma | manera en |
| ---------- | ---------------- | ----------- | --------- |
Windows,LinuxymacOS.Todosloscomandossoniguales
| y se comportan | de la misma | forma | en los distintos |
| -------------- | ----------- | ----- | ---------------- |
sistemasoperativos.

| Capítulo |     | 4:  | Comandos    |     |     |     |
| -------- | --- | --- | ----------- | --- | --- | --- |
| básicos  |     | de  | la terminal |     |     |     |
Comandos
1 ls
2 cd <directorio>
3 cd ..
4 pwd
5 mkdir <nombre>
6 touch <nombre>
7 rm <nombre>
| 8 cp <nombre> |     | <directorio> |     |     |     |     |
| ------------- | --- | ------------ | --- | --- | --- | --- |
| 9 mv <nombre> |     | <directorio> |     |     |     |     |
Conceptos
Introducción
| La terminal,  |               | consola     | o línea   | de comandos, |        | es una      |
| ------------- | ------------- | ----------- | --------- | ------------ | ------ | ----------- |
| herramienta   | muy           | potente     | que       | nos permite  |        | interactuar |
| con nuestro   |               | sistema     | operativo | de           | manera | directa a   |
| través de     | instrucciones |             | en        | formato      | texto. | Aunque      |
| pueda parecer |               | intimidante | al        | principio,   | es     | importante  |
| comprender    |               | algunos     | conceptos | básicos      |        | para poder  |
utilizarlaeficientemente.
31

32
Enlaterminal,comohemoscomentado,todosemaneja
mediantecomandosdetexto.Loscomandosseescriben
en una línea de texto y se ejecutan al presionar la
tecla Enter. La terminal responde, de la misma manera,
también con texto, ya sea una respuesta directa al
comandooalgúnmensajeinformativoodeerror.
Comandos más importantes
Este es un listado con los comandos que más utilizamos
habitualmenteenBash:
• ls:Muestraunalistaconlosarchivosycarpetasdel
directorioactual.
• cd: Nos permite movernos por los diferentes
directorios del sistema de archivos. Por ejemplo,
cd Desktop nos lleva al directorio del Escritorio, y
cd .. sube un nivel (retrocede) en el sistema de
directorios.
• pwd: Muestra la ruta completa del directorio actual
enelsistemadearchivos.
• mkdir: Crea una nueva carpeta en el directorio
actual. Por ejemplo, mkdir "Hello Git" crea una
carpetallamadaHelloGit.
• touch: Crea un nuevo archivo vacío en el directorio
actual. Por ejemplo, touch hello_git.txt crea un
archivollamadohello_git.txt.
• rm:Eliminaunarchivoocarpetadeldirectorioactual.
Por ejemplo, rm hello_git.txt elimina el archivo
hello_git.txt.
• cp:Copiaunarchivodeunlugaraotro.Porejemplo,
cp hello_git.txt /Desktop/Hello \Git copia el
archivohello_git.txt aldirectorioDesktop/HelloGit.

33
• mv: Mueve un archivo de un lugar a otro. Por
ejemplo, mv hello_git.txt /Desktop/Hello
\Git mueve el archivo hello_git.txt al directorio
Desktop/HelloGit.
Estos son solo algunos ejemplos de los comandos que
podemos utilizar en la terminal. Es importante señalar
que, entre sistemas operativos y entornos de ejecución,
se pueden utilizar comandos diferentes, por lo que es
posible que debamos consultar su documentación para
conocer los comandos específicos, y así poder llevar a
cabociertastareas.
Conclusión
Enestecapítulohemosaprendidolosconceptosbásicos
de la terminal y los comandos principales que podemos
utilizarparainteractuarconnuestrosistemadearchivos.
Con una buena comprensión de los conceptos básicos
y una práctica constante, podemos sacarle el máximo
provecho a la terminal y mejorar significativamente
nuestro flujo de trabajo como desarrolladores de
software.
Curso
Lección4:mouredev.com/git-github-041
Inicio:00:24:00|Duración:00:06:13
1https://mouredev.com/git-github-04

34
Es hora de comprender algunos conceptos básicos de
la terminal, ya que quizás no sepamos cómo interactuar
conella.Paraesto,dedicaremosestaclaseaaprenderal
menosloscomandosprincipalesdelaconsola.Siestaha
sidolaprimeravezquehemosabiertounaterminal,esel
momentodeaprendercómousarla.
Git es un sistema de control de versiones que funciona
especialmente bien con código. Está diseñado para
manejarungranvolumendearchivosutilizadosdurante
el proceso de desarrollo de software. Funciona mucho
mejor con código que, por ejemplo, si lo que queremos
hacer es una copia de seguridad de nuestro propio
ordenador,odegrandesficherosmultimedia.
Vamos a hablar de los comandos principales que
podemos manejar en una terminal. Por ejemplo, si
escribimos ls, lo primero que veremos es un listado
de todos los directorios que tenemos en ese punto
concreto de nuestro sistema. Podemos movernos
por los sistemas de archivos con el comando cd. Por
ejemplo, si queremos ir al escritorio, escribiremos cd
Desktop, pudiendo presionar la tecla de tabulación para
autocompletarposiblesdestinos.
Si queremos conocer cuál es la ruta en la que nos
encontramos,podemosescribirpwd.
Para comenzar a trabajar con Git, primero necesitamos
crear una carpeta. El comando para crear una carpeta
es mkdir. Imagínate que queremos crear un directorio
llamadoHelloGit,puesparaellolanzaremoselcomando
mkdir "Hello Git". Para desplazarnos dentro de la
carpeta,podemosusarelcomandocd Hello\ Git/.

35
Aunqueestemosusandolaterminal,estambiénhabitual
| trabajar | dentro de | un editor |     | de código |     | o del sistema | de  |
| -------- | --------- | --------- | --- | --------- | --- | ------------- | --- |
ficherosdelsistema.Porejemplo,podríamosabrirVisual
| Studio Code, | el IDE | que    | yo  | usaré | durante | el curso,      | con |
| ------------ | ------ | ------ | --- | ----- | ------- | -------------- | --- |
| el comando   |        | (puede |     | que   | tengas  | que configurar |     |
code .
| este acceso | directo | al editor). |     | A partir    | de  | aquí, podemos |       |
| ----------- | ------- | ----------- | --- | ----------- | --- | ------------- | ----- |
| empezar     | a crear | archivos    | o   | directorios |     | y trabajar    | en el |
proyectodesoftwarequedeseemos.
| Recordemos | que | esto | es solo | una | introducción |     | a Git |
| ---------- | --- | ---- | ------- | --- | ------------ | --- | ----- |

36
| y la terminal. | Hay muchos   | más comandos | y        | conceptos |
| -------------- | ------------ | ------------ | -------- | --------- |
| que aprender   | para sacarle | el máximo    | provecho | a estas   |
herramientas.

| Capítulo |     |        | 5:  | Configuración |     |     |     |
| -------- | --- | ------ | --- | ------------- | --- | --- | --- |
| $git     |     | config |     |               |     |     |     |
Comandos
| 1 git config |     |          |            |     |          |     |     |
| ------------ | --- | -------- | ---------- | --- | -------- | --- | --- |
| 2 git config |     | --global | user.name  |     | <nombre> |     |     |
| 3 git config |     | --global | user.email |     | <email>  |     |     |
Conceptos
Introducción
| Para comenzar |     | a trabajar    |     | con | Git necesitaremos |        | realizar |
| ------------- | --- | ------------- | --- | --- | ----------------- | ------ | -------- |
| una pequeña   |     | configuración |     |     | inicial. Una      | manera | de       |
identificarnuestrasinteraccionesdentrodelsistema.
Identificación
| Una de       | las   | características |          | clave | de Git      | es que | todas    |
| ------------ | ----- | --------------- | -------- | ----- | ----------- | ------ | -------- |
| las acciones |       | que se          | realizan | en    | el sistema  | deben  | estar    |
| asociadas    | a     | un autor.       | Esto     | es    | importante, | ya     | que nos  |
| permite      | a los | desarrolladores |          |       | rastrear    | quién  | hizo qué |
cambioenelcódigo.Siunerrorounproblemasurgeen
| el proyecto, |     | podremos | revisar |     | el historial | de cambios | y   |
| ------------ | --- | -------- | ------- | --- | ------------ | ---------- | --- |
encontrarquiénhizoelcambioyquécausóelproblema.
37

38
Al trabajar con Git, cada usuario debe poseer su propio
identificador, que se corresponderá con su nombre y
dirección de correo electrónico. Estos identificadores
se utilizan para etiquetar cada cambio realizado en el
proyecto, lo que permite una fácil identificación de cada
acciónregistradaenelsistema.
Configuración inicial
Antes de comenzar a trabajar con Git, es necesario
realizarunaconfiguracióninicialqueincluyelaasignación
de un nombre de usuario y una dirección de correo
electrónico.
La configuración inicial de Git se realiza a través del
comando git config. Si lo ejecutamos junto con
el argumento (o flag) --global, estableceremos la
configuración de Git a nivel global, lo que significará que
dicha configuración se aplicará a todas las interacciones
conGitdesdenuestrasesióndeusuarioenelequipo.
La configuración de Git se realiza mediante dos
propiedades: user.name y user.email. Es importante
que ambos valores se configuren correctamente para
queelsistemadecontroldeversionesfuncionesegúnlo
esperado.
Curso
Lección5:mouredev.com/git-github-051
Inicio:00:30:13|Duración:00:06:34
1https://mouredev.com/git-github-05

39
Vamos a empezar trabajar en el proyecto de ejemplo
(quehemoscreadocomoHelloGitenlalecciónanterior),
para ir entendiendo poco a poco qué es Git. A medida
que avancemos en él, iremos explicando en paralelo los
distintosconceptosdeGit.
Siempre que trabajamos con Git, todo lo que hagamos
tiene que estar asociado a alguien. ¿Por qué? Porque
podríamos tener un proyecto en el que trabajamos
solo nosotros, o un proyecto en el que trabajan más
personas desarrollando código. Git nos ayudará a que
tú puedas trabajar en tu ordenador con un código, yo
en mi ordenador con otro código, y, llegado el caso,
podamos unir ambos desarrollos sin problemas. Si
durante ese proceso de combinación encontramos un
error,podremosverquécambioshahechounapersona
u otra, qué modificaciones en el código han provocado
unconflicto,volveraversionesanteriores,etc.
Comodecíamos,Gitnosobligaatenerasociadosiempre
un autor, un identificador a quien atribuirle todas
las acciones que hagamos desde Git. Lo primero que
vamos a hacer es configurar Git desde cero. Para ello,
necesitamostenerunnombredeusuarioyunemail(los
quetúquieras).Estoesesencial,unrequisitoobligatorio.
Elpropiosistemanosobligaráadefinirlosenelmomento
quequeramoshaceralgoenGitporprimeravez.
Para limpiar la consola, simplemente podemos escribir
clear. La configuración de Git es muy amplia, si
queremos profundizar, lo mejor es consultar la
documentación oficial, donde encontraremos todo
loquesepuedehacerconél:
git-scm.com/doc2
2https://git-scm.com/doc

40
Git es un sistema enorme, pero entender las bases no
es algo que lleve muchísimo tiempo. Aunque existan un
gran número de combinaciones de comandos, con más
omenosunadecenapodemosllevaracabolasacciones
más comunes con las que trabajaremos día a día desde
Git.
Para establecer la configuración de Git, tendremos que
escribir el comando git config. A continuación, lo que
tendremos que hacer es decidir a qué nivel vamos a
establecer esta configuración. Lo habitual, y sobre todo
cuando estamos empezando, es establecerla a nivel
global. Para ello, escribimos el parámetro --global.
Esto significará que la configuración se aplicará a nivel
global a la hora de trabajar con Git dentro de nuestro
equipo(nuestroordenador,nomerefieroaunequipode
personas)ysesióndeusuario.Laconfiguraciónnosuele
seralgoconcretoparaunproyecto,alcontrario,sesuele
usar de forma general para cualquier interacción que
realicemosconGit,independientementedelproyecto.Si
establecemosquelaconfiguraciónesglobal,vaaafectar
a todo lo que se haga desde Git en la sesión de usuario
denuestramáquina.

41
| Para configurar |           | nuestro      | usuario        | escribimos |               | git          | config |       |
| --------------- | --------- | ------------ | -------------- | ---------- | ------------- | ------------ | ------ | ----- |
| --global        |           | user.name,   | y entre        | comillas   |               | ponemos      |        | el    |
| nombre          | de        | nuestro      | usuario.       | Una        | vez           | hecho        |        | esto, |
| pulsamos        | Enter.    | Ahora,       | de la          | misma      | manera        | que          | hemos  |       |
| establecido     |           | el name,     | configuraremos |            |               | la segunda   |        | y     |
| última          | propiedad | obligatoria, |                | el email.  |               | Establecemos |        |       |
| la propiedad    |           |              |                |            |               | user.email.  |        |       |
|                 |           | git          | config         | --global   |               |              |        |       |
| Lo ejecutamos,  |           | y            | este fichero   | de         | configuración |              |        | se    |
actualizaráconnombreyemail.
| Esto es      | lo  | único    | que necesitamos |            | para |          | configurar |     |
| ------------ | --- | -------- | --------------- | ---------- | ---- | -------- | ---------- | --- |
| inicialmente |     | Git. Sin | estas dos       | variables, |      | sin      | el nombre  |     |
| y el email,  | no  | podremos | ni              | comenzar   | a    | trabajar | con        | el  |
sistemadecontroldeversiones.

| Capítulo |             | 6: Inicialización |     |     |     |     |
| -------- | ----------- | ----------------- | --- | --- | --- | --- |
| de un    | repositorio |                   |     |     |     |     |
$git
init
Comandos
1 git init
Conceptos
Introducción
| Configurado  | Git, es    | hora | de preparar |         | nuestro proyecto |     |
| ------------ | ---------- | ---- | ----------- | ------- | ---------------- | --- |
| para empezar | a trabajar |      | con el      | sistema | de control       | de  |
versiones.
Inicialización
| Antes           | de profundizar |             | en         | las        | herramientas  | y   |
| --------------- | -------------- | ----------- | ---------- | ---------- | ------------- | --- |
| características | de             | Git, es     | importante |            | entender      | los |
| conceptos       | básicos        | del sistema |            | de control | de versiones. |     |
| Git funciona    | mediante       | la          | creación   | de         | fotografías   | o   |
instantáneas(llamadascommits)delestadodenuestro
| proyecto | en diferentes | momentos |     | a lo | largo del tiempo. |     |
| -------- | ------------- | -------- | --- | ---- | ----------------- | --- |
42

43
Cada commit representa un conjunto de cambios
realizadosenél.
Para trabajar con Git, primero debemos inicializar un
repositorio en nuestro proyecto. Crearemos un nuevo
repositorio lanzando el comando git init desde la
consola, desde la carpeta raíz del proyecto. Al ejecutar
este comando, Git creará una carpeta oculta llamada
.git, que contendrá todas las referencias asociadas al
sistemadecontroldeversiones.
Una vez inicializado el repositorio, podemos comenzar
a lanzar distintos comandos en ese directorio para
ejecutaraccionespropiasdelcontextodeGit.Esunpaso
obligatorio para que el sistema reconozca ese directorio
comounlugarenelqueGitestáoperativo.
Repositorio
El término repositorio de Git hace referencia al lugar
donde se almacena el historial de cambios realizados
en un proyecto, así como las diferentes versiones del
mismo. Se podría decir que es una base de datos que
guarda la evolución de todo el proyecto a lo largo del
tiempo,incluyendoelcódigofuente,ladocumentacióny
cualquierotroarchivoqueseencuentreenesedirectorio.
En un repositorio de Git, se registran las diferentes
versiones de los archivos, y se realiza un seguimiento
de los cambios que se han llevado a cabo. Cada versión
de un archivo se almacena asociado a un commit, que
contiene una instantánea de los cambios realizados en
esemomento.Deestamanerapodemosconsultarcómo
haevolucionadoelproyectoalolargodeltiempo.
Los repositorios de Git pueden ser locales (almacenados

44
en el equipo local del desarrollador) o remotos
(almacenadosenunservidorenlanube).Losrepositorios
remotos nos resultan útiles para colaborar con otros
desarrolladoresenunmismoproyecto,yaquepermiten
compartir los cambios realizados y fusionarlos en una
única versión de este. También nos sirven para trabajar
de forma segura y tener en todo momento un respaldo
denuestroproyectoenlanube.
Es posible que alguno de estos términos te resulte
desconocido. No te preocupes, ya hablaremos al detalle
decadaunodeellos.
Curso
Lección6:mouredev.com/git-github-061
Inicio:00:36:47|Duración:00:05:36
Comencemos a aprender los conceptos principales con
losquetenemosquetrabajarenGit.
Nos vamos a nuestro editor de código y creamos un
archivo. En este caso, vamos a crear uno en Python
llamado hellogit.py. No importa el tipo de archivo o
su nombre mientras podamos escribir algo en él.
Supongamos que se corresponde con el código de
nuestroproyecto.
Dentro del archivo, escribimos algo simple, como un
print, o lo que se nos ocurra. No importa lo que
escribamos aquí, lo importante es entender el manejo
de control de versiones. Supongamos que en algún
momento editamos este archivo y agregamos otra línea
1https://mouredev.com/git-github-06

45
| de texto,  | otro print. | A continuación, |     |               | nos | preguntamos: |        |
| ---------- | ----------- | --------------- | --- | ------------- | --- | ------------ | ------ |
| ¿qué tenía | yo el otro  | día             | en  | este archivo? |     | ¿cómo        | vuelvo |
atrás?.Aquíesdondeentralaimportanciadelcontrolde
versiones.
| Sin un      | control de versiones, |            | cada | vez        | que     | trabajamos | en        |
| ----------- | --------------------- | ---------- | ---- | ---------- | ------- | ---------- | --------- |
| un proyecto | perdemos              |            | toda | referencia |         | a lo       | que había |
| antes.      | De aquí que           | comencemos |      | a          | guardar | copias     | de un     |
mismoproyectoparaintentarrespaldarlo.Yanuncamás
seráasí.
| Para trabajar | con           | Git, nos | vamos |     | a la | consola, | y nos   |
| ------------- | ------------- | -------- | ----- | --- | ---- | -------- | ------- |
| situamos      | en la carpeta | raíz     | donde |     | está | nuestro  | archivo |
hellogit.py.
| Para indicar | que                  | queremos |            | trabajar | con | Git     | en este    |
| ------------ | -------------------- | -------- | ---------- | -------- | --- | ------- | ---------- |
| directorio,  | simplemente          |          | ejecutamos |          | git | init    | en la      |
| consola.     | Al hacer             | esto,    | se creará  |          | una | carpeta | oculta     |
| llamada      | .git. No necesitamos |          |            | entender |     | todo    | lo que hay |
| dentro       | de esta carpeta,     |          | pero       | es donde |     | Git     | guarda las |
referenciasasociadasalsistemadecontroldeversiones.

46
Ya está, desde este momento nuestro directorio (y
todo lo que se encuentre en su interior) trabaja con
un control de versiones, lo que significa que podemos
empezar a emplear todas las características que Git
nos proporciona. A medida que avancemos, iremos
aprendiendomuchomássobreellas.
Cuando ejecutamos git init, la consola nos mostró
un mensaje diciendo que se había creado una rama
llamada master (de momento no le prestaremos
atención a estos términos). Dependiendo de la terminal
que estemos utilizando, incluso podría indicarnos el
nombre de la rama en la que nos encontramos. Esto
no es algo proporcionado por Git, sino que depende
de cómo está configurada la terminal a la hora de
mostrarnos rutas e información asociada a ellas. Si
queremos personalizar nuestro terminal, podemos
utilizar, entre otras muchas opciones, oh-my-zsh. Con
esta utilidad podremos configurar nuestra consola y
hacerlamásagradablealahoradetrabajarconGit:
ohmyz.sh2
2https://ohmyz.sh

| Capítulo |     |     | 7:  | Ramas |     |     |     |     |
| -------- | --- | --- | --- | ----- | --- | --- | --- | --- |
Comandos
| 1 git config |     | --global |     | init.defaultBranch |     |     | main |     |
| ------------ | --- | -------- | --- | ------------------ | --- | --- | ---- | --- |
| 2 git branch |     | -m main  |     |                    |     |     |      |     |
Conceptos
Introducción
| Las ramas       |               | (llamadas    |             | branch)      | en Git     | son         | una           | de las |
| --------------- | ------------- | ------------ | ----------- | ------------ | ---------- | ----------- | ------------- | ------ |
| características |               | más          | poderosas   |              | de esta    | herramienta |               | de     |
| control         | de versiones. |              | Cuando      |              | hablamos   |             | de ramas      | nos    |
| referimos       | simplemente   |              |             | a diferentes |            | líneas      | de desarrollo |        |
| separadas,      | en            | las          | cuales      | podemos      |            | trabajar    | de manera     |        |
| independiente,  |               | y            | sin afectar |              | el trabajo |             | que se        | está   |
| realizando      | en            | otras        | ramas.      | Esto         | nos        | permite     |               | probar |
| distintas       | ideas,        | experimentar |             | con          | diferentes |             | enfoques      | y      |
hacercambiosimportantessinafectaralcódigoprincipal
delproyecto.
| En la   | mayoría | de         | los | proyectos, |            | es común |              | poseer |
| ------- | ------- | ---------- | --- | ---------- | ---------- | -------- | ------------ | ------ |
| varias  | ramas   | de trabajo |     | al mismo   | tiempo.    |          | Por ejemplo, |        |
| podemos | usar    | ramas      |     | para el    | desarrollo |          | de distintas |        |
funcionalidades,paracorreccióndeerrores,paraprobar
nuestrosconceptos,etc.Cadaunadeestasramaspuede
| tener su | propio | conjunto |     | de cambios |     | y   | compromisos, |     |
| -------- | ------ | -------- | --- | ---------- | --- | --- | ------------ | --- |
47

48
lo que permite una mayor flexibilidad y control en el
desarrollodelproyecto.
Git mantiene un seguimiento de todas las ramas en un
repositorio, y nos permite cambiar de una rama a otra
confacilidad.
Ventajas
Una de las ventajas de trabajar con ramas en Git es
que podemos fusionar fácilmente los cambios de una
rama en otra. Por ejemplo, si hemos desarrollado
una característica en una rama, asociada a una nueva
funcionalidad, podemos fusionar los cambios de esa
rama en la rama principal, para integrarlos así dentro
del proyecto base. Git proporciona herramientas para
realizarestafusióndemanerasencilla,rápidaysegura.
Es importante tener en cuenta que trabajar con ramas
en Git puede ser un poco complicado al principio,
especialmente si no estamos familiarizados con la
terminología, o si no sabemos cómo manejar las ramas
correctamente. Sin embargo, una vez que entendamos
cómo funcionan las ramas, y cómo podemos utilizarlas
para nuestro beneficio, seremos capaces de trabajar
de manera mucho más eficiente y efectiva en nuestro
proyecto.
Conclusión
Las ramas en Git nos permiten trabajar de manera
independiente en diferentes líneas de evolución de
un proyecto, lo que nos brinda una mayor flexibilidad
y control en el desarrollo del mismo. Es importante

49
entender cómo funcionan las ramas, y cómo podemos
utilizarlas en nuestro beneficio. Con práctica y
experiencia podemos convertirnos en expertos en
el manejo de las ramas en Git, aprovechando así al
máximo la característica principal de esta herramienta
decontroldeversiones.
Curso
Lección7:mouredev.com/git-github-071
Inicio:00:42:23|Duración:00:02:58
En esta lección vamos a hablar de las ramas en Git.
Ya hemos creado un repositorio en la raíz de nuestro
proyecto,ynosencontramosenlaramallamadamaster.
Muyprontoentenderemosquésignificaesto.
¿Qué es una rama? Podemos imaginarnos una rama de
un árbol que se divide en otras ramas, y estas en otras.
El código que creamos puede seguir diferentes flujos,
teniendocadaramaunnombreypropósito.Porejemplo,
en nuestro caso, la rama en la que nos encontramos
situados se llama master. Esta rama contiene nuestro
código y proyecto actual, el directorio que hemos
nombradocomoHelloGit.
Como decíamos, nos encontramos en la rama master,
que es como Git ha decidido llamar a esta primera rama
del sistema de control de versiones. Sin embargo, hay
otros nombres que se han introducido recientemente
para referirse a la rama principal, como main o trunk,
y ya no como master. El propio GitHub ya utiliza main
1https://mouredev.com/git-github-07

50
| en lugar | de  | master, aunque | ya  | hablaremos |     | de ello | más |
| -------- | --- | -------------- | --- | ---------- | --- | ------- | --- |
adelante.
| Si queremos |     | cambiar | el nombre |     | de  | nuestra | rama |
| ----------- | --- | ------- | --------- | --- | --- | ------- | ---- |
principalamain,podemosejecutarelsiguientecomando:
(un
| git config   |            | --global         | init.defaultBranch |           |        | main          |      |
| ------------ | ---------- | ---------------- | ------------------ | --------- | ------ | ------------- | ---- |
| nuevo        | comando    | de configuración |                    | global    |        | de Git). Así, | al   |
| crear nuevos |            | repositorios,    | la rama            | principal |        | se llamará    |      |
| main por     | defecto.   | Para             | cambiar            | el nombre |        | de la         | rama |
| actual       | de nuestro | proyecto         | podemos            |           | usar   | git branch    |      |
| -m main.     | De         | esta manera,     | nuestra            | rama      | master | pasará        | a    |
llamarsemain.
| Recuerda |     | que en | la actualidad | es  | común | usar |     |
| -------- | --- | ------ | ------------- | --- | ----- | ---- | --- |
mainenlugardemasterparareferirsealarama
principaldeunproyecto.

| Capítulo |     |     | 8: Guardado |     |     |     |     |     |
| -------- | --- | --- | ----------- | --- | --- | --- | --- | --- |
y
| $git | add |     | $git |     | commit |     |     |     |
| ---- | --- | --- | ---- | --- | ------ | --- | --- | --- |
Comandos
1 git status
| 2 git add    | <archivo> |             |     |     |     |     |     |     |
| ------------ | --------- | ----------- | --- | --- | --- | --- | --- | --- |
| 3 git add    | .         |             |     |     |     |     |     |     |
| 4 git commit | -m        | "<mensaje>" |     |     |     |     |     |     |
Conceptos
Introducción
HemosdadonuestrosprimerospasosenGit.Ahoraque
yaconocemosalgunoscomandosbásicos,esimportante
quesigamosprofundizandoenmásconceptosclave.
| Como mencionamos |                   |               | en          | los       | capítulos  | anteriores,    |              | Git   |
| ---------------- | ----------------- | ------------- | ----------- | --------- | ---------- | -------------- | ------------ | ----- |
| trabaja          | con repositorios  |               |             | y ramas.  | El         | repositorio    |              | es un |
| espacio          | de almacenamiento |               |             |           | que guarda |                | el historial |       |
| de cambios       | de                | nuestro       |             | proyecto. | En         | el repositorio |              | se    |
| encuentra        | toda              | la            | información |           | de         | nuestro        | proyecto,    |       |
| incluyendo       | los               | distintos     |             | puntos    | de         | guardado       | que          | se    |
| hayan realizado. |                   | Una           | rama,       | por       | otro       | lado,          | es una       | línea |
| de desarrollo    |                   | independiente |             | que       | parte      | de             | un punto     | de    |
51

52
guardado o commit. Las ramas nos permiten trabajar
en diferentes funcionalidades de nuestro proyecto de
manera independiente y segura, sin afectar a la rama
principaloaotrasramassecundarias.
Commit
En cuanto al concepto de commit, debemos conocer que
se refiere a la toma de una fotografía del estado actual
de nuestro proyecto en un momento determinado.
Cadavezquerealizamosuncommit,estamosguardando
los cambios que hayamos realizado (y que nosotros
seleccionemos) en nuestro proyecto en ese momento
específico. Los commits se almacenan en el historial de
cambios del repositorio y se identifican por un hash, un
identificadorúnico.
Elcomandogit addnospermiteañadirarchivosalárea
deStage,queesunazonaintermediadondesepreparan
los cambios que queremos incluir en nuestro próximo
commit. Es importante tener en cuenta que Git solo
guarda los cambios que hayan sido incluidos en el área
deStagemediantegit add.Poreso,esnecesarioejecutar
este comando cada vez que queramos añadir cambios a
un commit. Todo esto lo veremos en detenimiento en la
seccióndestinadaalcurso.
Unavezquetenemosloscambiospreparadosenelárea
de Stage, ejecutaremos el comando git commit para
crearlafotografíayguardarloscambiosenelrepositorio.
Debemos añadir un mensaje al commit mediante la
opción -m. Este mensaje debe describir de manera clara
y concisa los cambios que hayamos realizado en los
ficherosqueafectanaesecommit.

53
Curso
Lección8:mouredev.com/git-github-081
Inicio:00:45:21|Duración:00:08:06
| Seguimos  | aprendiendo |           |     | distintos | comandos    |         | y          | cómo  |
| --------- | ----------- | --------- | --- | --------- | ----------- | ------- | ---------- | ----- |
| funciona  | el flujo    | principal |     | de        | Git. En     | Git, es | importante |       |
| saber que | trabajamos  |           |     | con un    | repositorio |         | y una      | rama, |
dondeelconceptoclaveestomarfotografías,instantáneas
| o puntos        | de  | guardado | de      | nuestro | proyecto. |        | Esto       | quiere |
| --------------- | --- | -------- | ------- | ------- | --------- | ------ | ---------- | ------ |
| decir, capturar |     | y        | guardar | el      | estado    | actual | de nuestro |        |
proyectoparapoderreflejarloasíensuhistorial.
| Ya hemos       | finalizado |            | los       | preparativos |                | previos,     |             | por lo |
| -------------- | ---------- | ---------- | --------- | ------------ | -------------- | ------------ | ----------- | ------ |
| que podemos    |            | empezar    |           | a realizar   |                | guardados    | en          | Git.   |
| Para comprobar |            | el         | estado    | de           | la rama        | actual,      | y           | de Git |
| en nuestro     | proyecto,  |            | usamos    |              | el comando     |              | git status. |        |
| Al ejecutarlo, |            | nos        | muestra   |              | diferente      | información, |             | y      |
| nos indica,    | por        | ejemplo,   |           | que          | en la          | rama         | main aún    | no     |
| hay commits.   |            | Más        | adelante, |              | explicaremos   |              | cómo        | hacer  |
| un commit.     |            | Por ahora, |           | vemos        | que            | tenemos      | el archivo  |        |
| hellogit.py,   | que        | hemos      |           | creado       | anteriormente, |              | y           | donde  |
| hemos          | añadido    | un         | print     | (básicamente |                |              | para que    | el     |
| fichero        | modifique  |            | su estado | al           | cambiar        | el           | contenido   | de     |
suinterior).
1https://mouredev.com/git-github-08

54
En mi caso, también hay un archivo llamado
.DS_Store, que es un archivo temporal y
oculto creado por macOS. No nos interesa
este archivo, así que lo dejamos al margen.
Nos enfocaremos únicamente en el archivo de
códigodenuestroproyecto,llamadohellogit.py.
Mediante git status, Git nos señala que, aunque
conoce estos archivos (hellogit.py y .DS_Store), no los
tiene guardados de ninguna manera. Para guardarlos,
debemos primero añadirlos utilizando git add. En
nuestro caso, queremos añadir y versionar únicamente
hellogit.py. Para hacerlo, ejecutamos la sentencia git
add hellogit.py. A continuación, al ejecutar de nuevo
git status, veremos que hellogit.py ya se encuentra
en el área de Stage, una zona intermedia lista para
ser para guardada mediante un commit. Muy pronto
conoceremosestenuevoconcepto.

55
| Con git | add ejecutado, |     | y siendo | conscientes |     | de los |
| ------- | -------------- | --- | -------- | ----------- | --- | ------ |
ficherosdelosquequeremostomarlaprimerafotografía,
losiguienteseráconfirmardichaacción.
| Recordemos      | los                                      | comandos: | git      | init      | para          | iniciar    |
| --------------- | ---------------------------------------- | --------- | -------- | --------- | ------------- | ---------- |
| el repositorio, | git                                      | status    | para     | ver       | el estado     | de los     |
| archivos        | en la rama,                              | y git     | add para | añadirlos |               | al área de |
| preparación     | o Stage.                                 | Debemos   | saber    | que,      | si ejecutamos |            |
| git add         | .,añadiremostodoslosarchivospendientesde |           |          |           |               |            |
| versionar,      | aunque                                   | en este   | ejemplo  | lo        | hemos         | hecho de   |
unoenuno.

56
Yatenemoslistoelarchivohellogit.pyparalafotografía.Lo
siguienteestomarlautilizandouncommit.
Sin más, para hacer una fotografía de lo que tenemos
en el área de Stage, ejecutamos git commit. Podríamos
simplemente presionar Enter para lanzarlo, pero esto
abriría un editor de texto dentro de la terminal, donde
deberíamos escribir un comentario asociado al commit.
En lugar de eso, usaremos el comando git commit -m,
seguido de un mensaje que describa de forma concisa
qué se incluye en la fotografía, por ejemplo, “Este es
mi primer commit”. De esta forma, especificando la
propiedad -m, asociamos un mensaje al commit sin abrir
uneditor.

57
| Al ejecutar | este | comando, | Git    | nos  | informa   | de  | que se    |
| ----------- | ---- | -------- | ------ | ---- | --------- | --- | --------- |
| ha creado   | un   |          | con un | hash | asociado. |     | Este hash |
commit
| es muy | importante | en  | Git, ya | que | identifica | de  | manera |
| ------ | ---------- | --- | ------- | --- | ---------- | --- | ------ |
únicacadapuntodeguardadoenelsistemadecontrolde
versiones.
| Si volvemos | a ejecutar |      | git status, |     | veremos   | que     | ahora |
| ----------- | ---------- | ---- | ----------- | --- | --------- | ------- | ----- |
| ya existe   | un commit, | pero | el archivo  |     | .DS_Store | (propio | de    |
macOS)siguesinestarincluidoeneláreadeStage,yaque
| no hemos | realizado | su  | git add, | y por | lo  | tanto | no se ha |
| -------- | --------- | --- | -------- | ----- | --- | ----- | -------- |
reflejadoenelcommit.
| De esta | manera | hemos | realizado |     | nuestra |     | primera |
| ------- | ------ | ----- | --------- | --- | ------- | --- | ------- |
fotografíaenGit.

| Capítulo |     | 9:  | Estado |     |     | $git |     |     |
| -------- | --- | --- | ------ | --- | --- | ---- | --- | --- |
y
| log |     | $git | status |     |     |     |     |     |
| --- | --- | ---- | ------ | --- | --- | --- | --- | --- |
Comandos
1 git log
2 git status
Conceptos
Introducción
| Como   | hemos | visto, una | de  | las principales |     | características |     |        |
| ------ | ----- | ---------- | --- | --------------- | --- | --------------- | --- | ------ |
| de Git | es su | capacidad  |     | para tomar      |     | fotografías     |     | de los |
cambiosrealizadosenelcódigofuente.Estasfotografías
sedenominancommits,yrepresentanunainstantáneadel
estadodelproyectoenunmomentodeterminado.
Log
| Para visualizar |            | las fotografías |             | realizadas |         | en un          | repositorio |     |
| --------------- | ---------- | --------------- | ----------- | ---------- | ------- | -------------- | ----------- | --- |
| de Git,         | se utiliza | el comando      |             | git        | log.    | Al ejecutarlo, |             | Git |
| muestra         | una        | lista con       | todos       | los        | commits | realizados     |             | en  |
| el repositorio, |            | incluyendo      |             | el hash    | único   | que            | identifica  | a   |
| cada uno        | de         | ellos. Esta     | información |            | es      | útil para      | rastrear    |     |
58

59
laevolucióndelproyectoyasegurarnosdequetodaslas
fotografíassehanalmacenadosegúnloesperado.
Además de mostrar los commits, git log también
comparte información sobre el autor de cada uno de
ellos, incluyendo su nombre de usuario y la dirección
de correo electrónico. Esta información es importante
porque ayuda a identificar quién realizó cada cambio
en el código fuente. Al configurar Git, recordemos que
es obligatorio especificar un nombre de usuario y una
dirección de correo electrónico para poder realizar
commitsenelrepositorio.
Status
El comando git status es otro de los comandos más
útiles en Git. Este comando muestra el estado actual
del repositorio, incluyendo los archivos modificados,
eliminados o agregados, así como también los archivos
que se han añadido al área de Stage, junto con los que
aúnnohansidoseguidosporGit.
Cuando se ejecuta git status, Git muestra una lista
de los archivos modificados en el directorio de trabajo.
Estos archivos pueden, o no, haber sido seguidos por
Git. Si se han seguido, se mostrarán como cambios listos
para commit en el área de Stage. Si no se han seguido,
se mostrarán como cambios no rastreados. El término
seguir hace referencia a si Git está teniendo en cuenta a
esearchivopararealizarfuturasaccionesdeguardadoo
eliminación.
Como ya hemos visto, para agregar los archivos
modificados al área de Stage, se utiliza el comando
git add, seguido del nombre del archivo modificado.
Esto mueve el archivo al área de Stage, que es donde se

60
| preparan | los | cambios | para | ser incluidos |     | en  | el próximo |     |
| -------- | --- | ------- | ---- | ------------- | --- | --- | ---------- | --- |
commit.
| Tambiénesimportantedestacarquegit |         |             |       |           |         | statusmuestra    |       |     |
| --------------------------------- | ------- | ----------- | ----- | --------- | ------- | ---------------- | ----- | --- |
| información                       |         | adicional   | sobre | el estado |         | del repositorio, |       |     |
| como                              | la rama | actual      | en    | la que    | nos     | encontramos      |       |     |
| trabajando,                       |         | información | sobre | los       | commits |                  | (como | el  |
mensajeasociado),ysisehafusionadoconotrarama.
HEAD
| El concepto  |       | de HEAD  | también    | es   | importante |        | en       | Git.  |
| ------------ | ----- | -------- | ---------- | ---- | ---------- | ------ | -------- | ----- |
| HEAD es      | un    | puntero  | que apunta |      | al commit  | actual |          | en el |
| repositorio. |       | En otras | palabras,  | HEAD | indica     | la     | posición |       |
| actual       | en la | línea    | de tiempo  | del  | proyecto.  | Cuando |          | se    |
realizaunnuevocommit,HEADsemuevealnuevocommit,
convirtiéndoseenelcommitmásactual.Estonospermite
| movernos | fácilmente |     | entre | las diferentes |     | versiones |     | del |
| -------- | ---------- | --- | ----- | -------------- | --- | --------- | --- | --- |
proyectoyverexactamentequécambiosserealizaronen
cadacommit.
Curso
Lección9:mouredev.com/git-github-091
Inicio:00:53:27|Duración:00:04:21
| ¿Quieres           | saber   | si se | ha realizado  | un    | commit | en          | Git?  | Para |
| ------------------ | ------- | ----- | ------------- | ----- | ------ | ----------- | ----- | ---- |
| ello,vamosausargit |         |       | log.          |       |        |             |       |      |
| Comprobaremos      |         |       | en la consola |       | que    | ya tenemos  |       | un   |
| commit,            | junto   | a su  | hash único    | en    | la     | rama        | main. | Más  |
| adelante,          | también |       | hablaremos    | sobre |        | el concepto |       | de   |
1https://mouredev.com/git-github-09

61
HEAD. El autor es importante, por eso hablamos de que
era obligatorio a la hora de configurar Git. Si intentamos
hacer un commit sin usuario o email, no nos dejará
hacerlo. En nuestro caso, nuestro propio usuario, con
ciertoemail,enunafechayhoradeterminadas,yconun
hash único asignado, generó un commit diciendo “Este es
mi primer commit”. El que consideramos como nuestra
primerafotografía.
Sigamos trabajando. Imaginemos que ahora creamos
otroarchivollamadohellogit2.py,yleañadimosunprint.
Regresamos a la terminal y escribimos git status.
Nos muestra que no solo está el archivo anterior, sino
también uno nuevo llamado hellogit2.py, que podría
añadirse al área de Stage. Comenzamos en un área
Local, y tenemos la posibilidad de pasar archivos al
área de Stage. ¿Cómo lo hacemos? Si queremos añadir
hellogit2.py auncommit,tendremosqueescribirgit add
hellogit2.py, y a continuación git commit -m "Este
es mi segundo commit". Finalizamos el commit y se
añadeesenuevoarchivo.
Al escribir git log, ahora aparecerán dos commits.

62
En uno, agregamos el primer archivo, y en el otro, el
segundo. Cada commit ha generado un hash diferente.
De esta manera tendremos dos fotografías, reflejando,
además del archivo hellogit.py, también el archivo
hellogit2.py.
Estas dos fotografías nos permiten movernos entre
ambos estados del proyecto, y visualizar en cada caso
lo que contienen. Ahora, imaginemos que editamos el
primerarchivo,cambiandoelmensajedelprint.Alhacer
esto, hemos modificado el archivo. Si escribimos git
status,nosdiráquehellogit.pyhacambiadoconrespecto
a su última fotografía. El sistema de control de versiones
lo detectará automáticamente. Visto esto, podemos
plantearnos: ¿queremos guardar una fotografía de este
momento? Si decidimos no guardarla, y continuar con el
desarrollo, podemos hacerlo. Por ejemplo, cambiemos
el contenido de hellogit2.py y guardemos los cambios. Al
volver a ejecutar git status nos mostrará que hemos
modificadotantohellogit.py comohellogit2.py.

| Capítulo | 10:   | Operaciones |          |     |
| -------- | ----- | ----------- | -------- | --- |
| con      | ramas |             |          |     |
|          |       | $git        | checkout |     |
| y $git   | reset |             |          |     |
Comandos
| 1 git checkout | <archivo>                |     |            |     |
| -------------- | ------------------------ | --- | ---------- | --- |
| 2 git reset    |                          |     |            |     |
| 3 git log      | --graph                  |     |            |     |
| 4 git log      | --pretty=oneline         |     |            |     |
| 5 git log      | --decorate               |     |            |     |
| 6 git log      | --graph --pretty=oneline |     | --decorate |     |
Conceptos
Introducción
Vamosaexplorarporunapartelosnuevoscomandosgit
| checkout | y git reset, | que nos permitirán, | entre | otras |
| -------- | ------------ | ------------------- | ----- | ----- |
cosas,regresaraestadosanterioresdenuestrosarchivos
queaúnnohanguardadosuscambiosenGit,y,porotro
| lado, | también aprenderemos | a visualizar | el historial | de  |
| ----- | -------------------- | ------------ | ------------ | --- |
commitsdediferentesmaneras.
63

64
Checkout
Supongamos que acabamos de hacer algunos cambios
en nuestro código y deseamos volver al estado anterior
singuardarlasmodificaciones.Parahaceresto,podemos
usar el comando git checkout. Este comando nos
permitesituarnosenunpuntoespecíficodelhistorialde
commitsodeunarchivo.
Por ejemplo, si deseamos volver al estado previo de
un archivo antes de modificarlo, podemos ejecutar el
comandogit checkout <archivo>.
Este comando nos llevará al estado previo de dicho
archivo, correspondiente a la última fotografía tomada
enlaramaactual.
Reset
Si deseamos volver a la última fotografía completa
tomada, podemos escribir git reset. Al lanzar este
comando se nos informará de que se perderán los
cambios en los archivos que no forman parte de un
commit.Hechoesto,recuperaremoselcontenidooriginal
delúltimopuntodeguardadodelarama.
Al ejecutar un git reset, Git nos mostrará una lista
de archivos modificados que aún no se han guardado.
Podemos elegir si deseamos hacer un reset de todos los
archivos,oúnicamentedealgunos.
Visualizaciones
Si hacemos memoria, el comando git log nos permitía
visualizar todo el historial de cambios que se han

65
realizado en un proyecto. Es muy útil para rastrear el
progreso del proyecto y ver qué cambios se han llevado
acabo.
Este comando nos mostrará una lista de todos los
commits que se han realizado en el proyecto, junto con
información detallada sobre quién hizo los cambios,
cuándosehicieron,yquéarchivossemodificaron.
• Si deseamos revisar el historial de commits de una
manera más visual, podemos usar el comando
git log --graph. Este comando nos mostrará
una representación gráfica de las ramas (cómo se
dividen,ycómoserelacionanentresí)yloscommits
delproyecto.
• Si queremos ver el historial de commits de una
manera más simplificada, podemos usar el
comando git log --pretty=oneline. Este
comando te mostrará una vista rápida de cada
commit en una sola línea. Podremos consultar
rápidamente el hash del commit y el mensaje de
confirmacióndesdeunavistacompacta.
• También podemos utilizar el comando git log
--decorate para consultar información adicional
sobre los commits. Este comando nos permite
visualizar rápidamente la línea de progreso
de nuestra rama y sus etiquetas (un concepto
que veremos más adelante) sin mostrar el hash
completo.
Por supuesto, puedes combinar todas las propiedades
nombradas: git log --graph --pretty=oneline
--decorate

66
Curso
Lección10:mouredev.com/git-github-101
Inicio:00:57:48|Duración:00:05:14
| Imaginemos | que        | no        | queremos | tener | en       | cuenta   | ciertos |     |
| ---------- | ---------- | --------- | -------- | ----- | -------- | -------- | ------- | --- |
| cambios    | en nuestro |           | código,  | y     | deseamos | regresar |         | a   |
| un estado  | anterior   | sin       | guardar  |       | lo que   | hemos    | estado  |     |
| haciendo   | en el      | proyecto. | Para     | eso,  | podemos  |          | usar    |     |
git
| checkout. | Si escribimos |     | git | y   | presionas | la  | tecla | de  |
| --------- | ------------- | --- | --- | --- | --------- | --- | ----- | --- |
tabulación,veremosdiferentescomandosquepodemos
utilizar.Recuerdaqueestonospuederesultarmuyútil.
Comocomentábamos,vamosaexplorarelcomandogit
checkout.Uncomandoquenospermitesituarnosenun
puntoespecíficodeuncommitoarchivo.
| Supongamos  | que           | queremos        |                               | volver       | al estado    |                | previo     | del |
| ----------- | ------------- | --------------- | ----------------------------- | ------------ | ------------ | -------------- | ---------- | --- |
| archivo     | hellogit2.py, | antes           | de                            | modificarlo. |              | Si recordamos, |            |     |
| no hicimos  | un commit     |                 | de los                        | cambios,     | así          | que            | vamos      | a   |
| ejecutargit | checkout      |                 | hellogit2.py.Allanzarlo,senos |              |              |                |            |     |
| indicará    | que se        | ha actualizado. |                               | Ahora,       | el           | archivo        | está       | en  |
| su estado   | previo        | al cambio,      |                               | como         | en la última |                | fotografía |     |
realizadaenlaesarama.
| Si queremos | volver   |     | a la           | última | fotografía   |           | completa, |     |
| ----------- | -------- | --- | -------------- | ------ | ------------ | --------- | --------- | --- |
| podemos     | escribir | git | reset.         |        | Al lanzarlo, |           | Git       | nos |
| informará   | de que   | las | modificaciones |        | no           | guardadas |           | en  |
losarchivosseperderán.
| Silodeseamos,podemoshacerungit |     |     |     |     | resetdetodoel       |     |     |     |
| ------------------------------ | --- | --- | --- | --- | ------------------- | --- | --- | --- |
| proyecto,osimplementeusargit   |     |     |     |     | checkoutyregresaral |     |     |     |
estadopreviodeunarchivoconcreto.
1https://mouredev.com/git-github-10

67
Recuperemosunconceptodelqueyahemoshablado:la
revisióndelLog.Vamosaextendersufuncionalidadpara
conocerlomásenprofundidad.
| Hagamos         | un  | nuevo | commit,       |     | modificando  |     | el contenido |       |
| --------------- | --- | ----- | ------------- | --- | ------------ | --- | ------------ | ----- |
| de hellogit.py. |     | Como  | siempre,      |     | ejecutaremos |     | git          | add   |
| hellogit.py     |     | para  | añadir        | los | cambios,     | y   | git commit   |       |
|                 |     | para  | confirmarlos. |     | Con          |     |              | ahora |
| -m "<mensaje>"  |     |       |               |     |              | git | log          |       |
descubriremosqueyasehanrealizadotrescommits.
| Podemos      | ver   | ese      | historial      |            | de commits        | de      | diferentes |     |
| ------------ | ----- | -------- | -------------- | ---------- | ----------------- | ------- | ---------- | --- |
| maneras.     | Por   | ejemplo, |                | utilizando | git               | log     | --graph    |     |
| para mostrar |       | una      | representación |            | gráficade         |         | las ramas. | Si  |
| deseamos     | verlo | de       | manera         |            | más simplificada, |         | podemos    |     |
| ejecutar     | git   | log      | --graph        |            | --pretty=oneline. |         |            | Así |
| tendremos    | una   | vista    | rápida         |            | de los tres       | commits | en         | una |
únicalíneacadauno.

68
| Además,    | es posible | abreviar    |       | los hashes    | de           | los         | commits  |
| ---------- | ---------- | ----------- | ----- | ------------- | ------------ | ----------- | -------- |
| utilizando | otro       | comando.    |       | Probemos      | con          |             |          |
|            |            |             |       |               |              | git         | log      |
| --graph    | --decorate |             | --all | --oneline.    |              |             | Esto nos |
| permite    | visualizar | rápidamente |       | la línea      | de           | progreso    | de       |
| nuestra    | rama       | sin mostrar | el    | hash completo |              | (suficiente |          |
| para la    | mayoría    | de los      | casos | en los        | que tengamos |             | que      |
utilizaresehash).
| En resumen,         |            | en esta                       | lección  | hemos        | aprendido |          | los    |
| ------------------- | ---------- | ----------------------------- | -------- | ------------ | --------- | -------- | ------ |
| comandos            | git        | checkout                      | y        | git reset    | para      | regresar | a      |
| estados             | anteriores | de                            | nuestros | archivos.    | Por       | otra     | parte, |
| también             | hemos      | ampliado                      | nuestro  | conocimiento |           |          | sobre  |
| cómovisualizarellog |            | decommitsdediferentesmaneras. |          |              |           |          |        |

| Capítulo |     |     | 11: | Alias |     | $git |     |     |
| -------- | --- | --- | --- | ----- | --- | ---- | --- | --- |
alias
Comandos
| 1 git config |     |          |            |     |             |     |     |     |
| ------------ | --- | -------- | ---------- | --- | ----------- | --- | --- | --- |
| 2 git config |     | --global | alias.tree |     | '<comando>' |     |     |     |
| 3 git tree   |     |          |            |     |             |     |     |     |
Conceptos
Introducción
Gitesunaherramientaesencialenelsectordeldesarrollo
| de software. |          | Posiblemente  |         | pasaremos |          | gran        | parte     | de  |
| ------------ | -------- | ------------- | ------- | --------- | -------- | ----------- | --------- | --- |
| nuestro      | tiempo   | interactuando |         |           | con Git  | y sus       | comandos. |     |
| Sin embargo, |          | a veces       | puede   |           | resultar | difícil     | recordar  |     |
| ciertos      | comandos |               | de Git, | junto     | a sus    | propiedades |           | y   |
combinaciones.
| Afortunadamente, |     |     | Git nos | permite |     | crear | Alias | para |
| ---------------- | --- | --- | ------- | ------- | --- | ----- | ----- | ---- |
simplificaresteproceso.
Alias
| Para acceder |     | a la configuración |       |     | de Git      | utilizábamos |           | el  |
| ------------ | --- | ------------------ | ----- | --- | ----------- | ------------ | --------- | --- |
| comando      | git | config             | junto | al  | modificador |              | --global, |     |
69

70
para que esta se aplique a todas las interacciones de
nuestrousuarioenGit.
Una vez que hemos accedido a la configuración de Git,
podemos crear distintos Alias para los comandos
que usamos con frecuencia, o que nos resulten
especialmente complejos. Por ejemplo, podríamos
crear un Alias llamado test, que tenga asociado un
comandoconcretodeGit.
Para crear ese Alias con el nombre test, simplemente
lanzamoselcomandogit config --global alias.test
'<comando>'. Hecho esto, cada vez que necesitemos
ejecutar ese comando, simplemente tendremos que
escribirgit testdesdelaterminal.
Conclusión
La creación de Alias es solo una de las formas con que
podemos personalizar Git para adaptarlo a nuestras
necesidades. A medida que nos familiaricemos con Git
y progresemos en su uso, podremos agregar más Alias
paraloscomandosqueusamosconfrecuencia.
Por darte un ejemplo, también podemos personalizar
Git ajustando la configuración predeterminada.
Tendremos la posibilidad de cambiar el editor de
texto predeterminado a la hora de confirmar un commit,
establecerlímitesparalosmensajesasociadosalcommit,
o cambiar la forma en que se muestran los mensajes de
error.Entremuchasotrasconfiguraciones.

71
Curso
Lección11:mouredev.com/git-github-111
Inicio:01:03:02|Duración:00:02:03
| A veces,      | resulta | difícil            | recordar | ciertos          | comandos   |              | de       | Git,  |
| ------------- | ------- | ------------------ | -------- | ---------------- | ---------- | ------------ | -------- | ----- |
| junto         | a sus   | propiedades        |          | y combinaciones. |            | Por          | suerte,  |       |
| Git nos       | permite | crear              | los      | llamados         | Alias      | dentro       |          | de la |
| configuración |         | de nuestro         |          | usuario.         | ¿Recuerdas |              |          | cómo  |
| se accede     |         | a la configuración |          | de               | Git?       | Utilizábamos |          | el    |
| comando       | git     | config.            | Y        | la marcábamos    |            | como         | --global |       |
paraquelaconfiguraciónfueraespecíficadelusuariode
lasesión.
| A continuación, |            | vamos     | a     | crear un     | Alias    | relacionado |        | con  |
| --------------- | ---------- | --------- | ----- | ------------ | -------- | ----------- | ------ | ---- |
| el capítulo     |            | anterior, | y el  | comando      | complejo |             | git    | log  |
| --graph         | --decorate |           | --all | --oneline.   |          | ¿Qué        | nombre |      |
| le pondremos?   |            | Vamos     | a     | elegir tree, | ya       | que         | nos    | hace |
pensarenlarepresentacióndeárboldenuestrasramas.
| Este nombre |     | es totalmente |     | personalizable. |     |     | Después, |     |
| ----------- | --- | ------------- | --- | --------------- | --- | --- | -------- | --- |
entrecomillas,especificamoselcomandoquequeremos
| ejecutar    | y          | asociar.   | En   | este caso, | será       |         |        |     |
| ----------- | ---------- | ---------- | ---- | ---------- | ---------- | ------- | ------ | --- |
|             |            |            |      |            |            | git     | config |     |
| --global    | alias.tree |            | 'log | --graph    | --decorate |         | --all  |     |
| --oneline'. |            | Ejecutamos |      | el comando | y ya       | estaría | creado |     |
nuestronuevoAlias.
1https://mouredev.com/git-github-11

72
¿Recuerdas del archivo de configuración donde guardamos
nuestro usuario y correo electrónico? Si lo abrimos ahora,
veremos que también incluye el Alias que acabamos
de crear. Este Alias, llamado tree, nos permitirá escribir
simplementegit treeparalanzarsuejecuciónasociada,
sintenerquerecordarelcomandocomplejocompleto.
Conforme avancemos, podremos agregar más Alias.
Únicamente tendremos que ir añadiéndolos a la
configuración. Incluso podemos crear un Alias que
ejecute varios comandos al mismo tiempo. La idea
principal es que conozcamos la posibilidad de
personalizar Git creando diferentes comandos más
sencillos.

| Capítulo |     |     | 12: | Ignorar |     |     |     |
| -------- | --- | --- | --- | ------- | --- | --- | --- |
ficheros
.gitignore
Comandos
1 touch .gitignore
| git add | .gitignore |     |     |     |     |     |     |
| ------- | ---------- | --- | --- | --- | --- | --- | --- |
2
Conceptos
Introducción
| A veces  | puede       | suceder    | que       | no queremos |             | incluir     | ciertos |
| -------- | ----------- | ---------- | --------- | ----------- | ----------- | ----------- | ------- |
| archivos | en          | un commit, | ya        | sea porque  | son         | temporales, |         |
| exponen  | información |            | delicada, | o           | simplemente |             | no son  |
relevantesparaelproyecto.
Enestoscasos,esnecesariosabercómoignorararchivos
| en Git. | En este | capítulo, |     | vamos a explicar |     | cómo | hacerlo |
| ------- | ------- | --------- | --- | ---------------- | --- | ---- | ------- |
utilizandoelarchivollamado.gitignore.
| El archivo    |         | .gitignore |         |             |            |              |       |
| ------------- | ------- | ---------- | ------- | ----------- | ---------- | ------------ | ----- |
| El .gitignore |         | es un      | archivo | especial    | que Git    | utiliza      | para  |
| ignorar       | ciertos | elementos, |         | directorios | o patrones |              | en un |
| proyecto.     | Este    | archivo    | se      | coloca en   | la raíz    | del proyecto | y     |
73

74
se le pueden añadir reglas. Es importante destacar que,
los archivos que se añadan a .gitignore no se eliminarán
del sistema, sino que simplemente serán ignorados por
Gitalahoradetrabajarconellos.
Creación
Para crear un archivo .gitignore podemos utilizar el
comando touch desde la terminal o desde el propio
sistemadearchivosdelsistemaoperativo.Esimportante
que el archivo lleve un punto al principio para que sea
interpretado como oculto. También es esencial que
se llame exactamente .gitignore. Una vez creado, se
visualizaráenlalistadearchivosdelproyecto.
Uso
Para ignorar archivos en Git, debemos añadir la ruta o
el nombre del archivo como contenido del .gitignore. Por
ejemplo, si queremos ignorar un <nombre_archivo> en
todo el proyecto, debemos añadir la línea **/<nombre_-
archivo>dentrodel.gitignore.
EstalíneaindicaaGitquedebeignorararchivosllamados
<nombre_archivo>, situados en cualquier parte proyecto.
Es importante destacar que la línea debe comenzar con
dos asteriscos, que indican que la regla se aplicará en
cualquierlugardelproyecto.
Una vez que se ha añadido la línea al archivo .gitignore,
Git dejará de considerar el archivo <nombre_archivo> en
eláreadeStage,ynoseincluiráenningúncommitfuturo.
A continuación, vamos a nombrar las formas más
habituales de ignorar archivos. Dentro del archivo

75
.gitignore se pueden utilizar diferentes mecanismos para
especificar los archivos, carpetas o patrones que se
quierenignorarenelsistemadecontroldeversiones.
Algunosdeestosmecanismosson:
• Archivos por su nombre: Se puede escribir el
nombre exacto del archivo que se quiere ignorar.
Porejemplo:archivo_temporal.txt.
• Carpeta completa: Se puede escribir el nombre de
una carpeta completa que se quiere ignorar. Por
ejemplo:carpeta_temporal/.
• Patrón: Se pueden utilizar patrones que coincidan
con múltiples archivos o carpetas que se quieren
ignorar. Algunos ejemplos de patrones comunes
son:
– *.log: Ignora todos los archivos con extensión
.log.
– **/temp: Ignora la carpeta temp en cualquier
partedelproyecto.
• Especificarvariasreglas:Sepuedenutilizarmúltiples
reglas en el archivo .gitignore, separadas por líneas
enblancooporunsaltodelínea.
Yestossonsolounospocosmecanismos.
EsimportantedestacarqueGittambiénsoportaalgunos
caracteresespecialesenlospatronesdelosarchivosque
sequierenignorar,comoelasterisco(*)pararepresentar
cero o más caracteres, el signo de interrogación (?) para
representar un solo carácter, el signo de admiración
(!) para realizar una negación, o los corchetes ([ ]) para
especificarunconjuntodecaracteres.Estosmecanismos
pueden ser muy útiles para definir patrones más
específicosydetallados.

76
Comprobación
Paracomprobarqueunarchivoestásiendoignoradopor
Git, podemos ejecutar el comando git status, aunque
existenotrasopciones.Sielarchivoestásiendoignorado,
nodeberíaaparecerenlalistadearchivospendientesde
añadiraláreadeStage.
Es importante destacar que el archivo .gitignore sí que
debe ser añadido al repositorio en Git. Para hacer esto,
se debe utilizar el comando git add .gitignore,
y a continuación realizar un commit con el mensaje
correspondiente. Una vez que se ha añadido el archivo
.gitignore al repositorio, ya no será necesario volver a
hacerlo.
Curso
Lección12:mouredev.com/git-github-121
Inicio:01:05:05|Duración:00:03:59
Hasta el momento, hemos realizado tres commits, en
los que hemos modificado distintos ficheros de nuestro
proyecto. Pero quizás nos hemos dejado algo atrás.
Si revisamos el estado de nuestro repositorio con git
status, nos daremos cuenta de que existe un archivo
llamado.DS_Store.Nuncahemosincluidoestearchivoen
los commits, porque es temporal y tiene referencias a la
propiamáquinayalsistemaoperativomacOS.Alsubirlos
cambios, no queremos incluirlo, y no podemos borrarlo,
yaqueseregeneraconstantemente.Aquíesdondeentra
el concepto de ignorar archivos. Si tenemos la certeza
1https://mouredev.com/git-github-12

77
dequenuncavamosaquererhacerunafotografíadeun
archivo,significaráquetampocoqueremosqueaparezca
como pendiente cada vez que ejecutamos git status.
Para conseguir esto, existe un archivo propio de Git
llamado.gitignore.
Vamos a crear un nuevo archivo llamado .gitignore.
Podemos hacerlo desde la terminal con el comando
touch, o desde el sistema de archivos. Recordemos que
tienequellevarunpuntoalprincipioparatransformarlo
oculto,y,porsupuesto,debellamarse.gitignore.Unavez
creado,lovisualizaremosenlalistadearchivos.
Los archivos, rutas, o expresiones que añadamos al
.gitignore, serán ignorados por Git. Creamos este archivo
en la raíz del proyecto, y, para excluir los .DS_Store,
agregamoslalínea**/.DS_Store.
Así estamos indicando que cualquier archivo llamado
.DS_Store no se tendrá en cuenta para añadir al área de
Stage. No importa dónde esté ubicado el archivo .DS_-
Store,seráignorado.
Sin embargo, el archivo .gitignore, sí que debe ser

78
| añadidoalrepositorio.Lanzamosgit |             |                           | add .gitignore,y |         |
| -------------------------------- | ----------- | ------------------------- | ---------------- | ------- |
| acontinuacióngit                 | commit      | -m "<mensaje>".Ejecutamos |                  |         |
| git status,                      | y validamos | que ya                    | no hay ningún    | fichero |
pendiente.Nuestraramaestálimpia.
Situproyectonotieneunficherotemporal.DS_-
Store,puedescrearunodeejemploparaponer
enprácticaloaprendidoignorandoficherospor
nombre.

| Capítulo    |      | 13: |     |     |         |     |     |
| ----------- | ---- | --- | --- | --- | ------- | --- | --- |
| Comparación |      |     |     | de  | commits |     |     |
| $git        | diff |     |     |     |         |     |     |
Comandos
| 1 git diff |                 |     |                 |                 |     |                 |     |
| ---------- | --------------- | --- | --------------- | --------------- | --- | --------------- | --- |
| 2 git diff | <hash_commit_a> |     |                 | <hash_commit_b> |     |                 |     |
| 3 git diff | --name-only     |     | <hash_commit_a> |                 |     | <hash_commit_b> |     |
Conceptos
Introducción
| En este | capítulo | vamos | a   | profundizar | en  | el concepto | de  |
| ------- | -------- | ----- | --- | ----------- | --- | ----------- | --- |
comparacióndecommitsysucontenido.
Diff
| Elcomandogit |     | diffnospermiteconsultarloscambios |     |     |     |     |     |
| ------------ | --- | --------------------------------- | --- | --- | --- | --- | --- |
exactosrealizadosennuestrocódigoantesderealizarun
commit(entreotrascosas).Paraentendercómofunciona,
| vamos     | a utilizar | un ejemplo. |         | Hasta  | el momento, |      | hemos |
| --------- | ---------- | ----------- | ------- | ------ | ----------- | ---- | ----- |
| realizado | diferentes |             | commits | en una | rama,       | pero | ahora |
79

80
queremos llevar a cabo otras modificaciones en ciertos
archivos.
Antes de hacer un commit, podemos utilizar el comando
git diffparaexaminarloscambiosexactosquehemos
realizado.
En la consola, los cambios realizados aparecen con un
signo menos (-) en la línea que ha sido eliminada, y un
signomás(+)enlalíneaquehasidoañadida.
De esta manera, podemos llevar un control preciso
de todos los cambios que hemos llevado a cabo en el
proyecto.
Diff entre commits
Además de utilizar el comando git diff, para ver los
cambios realizados en nuestro código antes de hacer
un commit, también podemos emplear este comando
para visualizar los cambios realizados entre dos commits
específicos.Estonospermitedetectartodosloscambios
efectuadosennuestroproyectoenunperíododetiempo
determinado.
Para utilizar el comando git diff entre dos commits
específicos, debemos indicar los identificadores únicos
de los commits, los llamados hash, que podemos
consultar cuando hacemos un git log. Por ejemplo, si
queremos ver los cambios realizados entre el commit
<hash_commit_a>yelcommit<hash_commit_b>,debemos
escribir el comando git diff <hash_commit_a>
<hash_commit_b>enlaconsola.
Estecomandonosmuestraloscambios realizadosentre
los dos commits especificados. En este caso, los cambios
realizadostambiénapareceránconunsignomenos(-)en

81
la línea que ha sido eliminada, y un signo más (+) en la
líneaquehasidoañadida.
También podemos utilizar el comando git diff con
otrosargumentos,comoelparámetro--name-only,que
nos muestra solo los nombres de los archivos que han
sido modificados entre los dos commits especificados.
Por ejemplo, si queremos ver los nombres de los
archivosmodificadosentreloscommits<hash_commit_a>
y <hash_commit_b>, podemos lanzar el comando git
diff --name-only <hash_commit_a> <hash_commit_b>.
Conclusión
El comando git diff es una herramienta muy útil
que nos permite ver los cambios realizados en nuestro
código, ya sea antes de hacer un commit, o entre dos
commits específicos. Esto nos permite tener un mayor
control sobre nuestro proyecto y asegurarnos de que
estamosrealizandoloscambioscorrectos.
Curso
Lección13:mouredev.com/git-github-131
Inicio:01:09:04|Duración:00:02:50
Vamos a aprender un comando más antes de
adentrarnos a fondo en el concepto de rama. Hasta
el momento, hemos seguido avanzando y realizando
diferentescommitsennuestraramamain.
1https://mouredev.com/git-github-13

82
Es todo este tiempo siempre nos hemos encontrado
en el extremo de la rama actual, pero recordemos que
poseemos referencias a los commits, llamados hash, y,
porlotanto,podemosconsultarquéhayencadapunto.
Ahora,realizaremosunnuevocambioenunodenuestros
archivos, hellogit.py, modificando el texto de su print.
Imaginemos que hemos estado programando y no nos
acordamos de todo lo que hemos modificado, así que
no estamos seguros de si queremos hacer un commit
de esos cambios. Como queremos saber qué hemos
cambiado,conrespectoalúltimocommit,podremosusar
elcomandogit diff.
Al ejecutarlo, observamos que nos señala que en el
archivo hellogit.py algo ha desaparecido y algo nuevo ha
aparecido. En concreto, una línea ha sido eliminada, y
otra línea ha sido añadida. Esto hace referencia a que el
contenidodelprinthasidomodificado.Deestamanera,
y sin hacer un commit, podemos examinar los cambios
realizadosenelcódigo.
Esta funcionalidad es muy importante para entender el
valorqueGitaportaanuestrotrabajo.Nospermitetener

83
uncontrolabsolutodetodoloquehacemosconnuestro
| código, | incluso antes de | realizar un | commit. | El comando |
| ------- | ---------------- | ----------- | ------- | ---------- |
git diffnosmuestraconunsignomenos(-)loqueseha
| eliminado, | y con un signo | más (+) | lo que se | ha añadido. |
| ---------- | -------------- | ------- | --------- | ----------- |
Graciasaestopodemosllevaruncontrolmásprecisode
nuestroscambios.

| Capítulo        |     | 14: |     |     |     |     |
| --------------- | --- | --- | --- | --- | --- | --- |
| Desplazamientos |     |     |     | en  | una |     |
rama
Comandos
| 1 git checkout | <hash> |      |     |     |     |     |
| -------------- | ------ | ---- | --- | --- | --- | --- |
| 2 git checkout | HEAD   | -- . |     |     |     |     |
Conceptos
Introducción
| Una de    | las características | más            | importantes |     | de Git  | es su |
| --------- | ------------------- | -------------- | ----------- | --- | ------- | ----- |
| capacidad | para realizar       | el seguimiento |             | de  | cambios | en el |
código,ypermitirnosdesplazarnosatravésdediferentes
| versiones   | del mismo.                            | En este sentido,  |      | los       | comandos | git     |
| ----------- | ------------------------------------- | ----------------- | ---- | --------- | -------- | ------- |
| logygit     | checkoutsonmuyútiles,yaquenospermiten |                   |      |           |          |         |
| visualizar  | todo el historial                     | de modificaciones |      |           | de       | nuestro |
| proyecto,   | incluyendo                            | quién hizo        | cada | cambio,   |          | cuándo  |
| se realizó, | y cuál                                | fue el mensaje    |      | asociado, | así      | como    |
movernosalestadodecadacommit.
84

85
Desplazamiento
| Aunqueelcomandogit |     |     |     | lognospermitevisualizartodo |     |     |     |     |
| ------------------ | --- | --- | --- | --------------------------- | --- | --- | --- | --- |
elhistorialdecambios,avecespuederesultarnecesario
desplazarnosauncommitespecífico.Paraello,podemos
| utilizar | el comando |     |     | checkout, | seguido |     | del hash | del |
| -------- | ---------- | --- | --- | --------- | ------- | --- | -------- | --- |
git
commitalquequeremosdesplazarnos.
Alhaceresto,Gitnosadvertirádequepodríamosperder
| cambios     | que no  | hemos       |         | guardado, | ya        | que nos  | estamos    |     |
| ----------- | ------- | ----------- | ------- | --------- | --------- | -------- | ---------- | --- |
| desplazando | a       | una         | versión | anterior  |           | del      | proyecto.  | Si  |
| estamos     | seguros | de          | que     | queremos  |           | hacerlo, | podemos    |     |
| confirmar,  | y Git   | actualizará |         | el        | contenido |          | de nuestro |     |
proyectoporeldelcommitcorrespondiente.
| Es importante |     | tener | en cuenta |     | que al | desplazarnos |     | a un |
| ------------- | --- | ----- | --------- | --- | ------ | ------------ | --- | ---- |
commitanterior,losarchivosquehansidomodificadoso
eliminadosenversionesposteriores,puedennoaparecer
| en nuestro | proyecto |     | actual. | Esto    | es normal, |     | ya que       | nos |
| ---------- | -------- | --- | ------- | ------- | ---------- | --- | ------------ | --- |
| estamos    | moviendo |     | a una   | versión | anterior   |     | del proyecto |     |
quenoincluyedichoscambios.Sinembargo,sienalgún
| momento   | queremos   |     | volver   | al  | estado       | fotografiado |          | del |
| --------- | ---------- | --- | -------- | --- | ------------ | ------------ | -------- | --- |
| proyecto, | podemos    |     | utilizar | el  | comando      | git          | checkout |     |
| HEAD      | -- .. Esto | no  | afectará | a   | los archivos |              | nuevos   | (no |
rastreados)nialáreadepreparación.
| Recordemos | que | otra | forma | de  | movernos |     | al final | de la |
| ---------- | --- | ---- | ----- | --- | -------- | --- | -------- | ----- |
ramaesutilizandoelhashabreviadodelúltimocommit.
| Visualizar | el        | árbol      | de  | commits     |     |               |           |     |
| ---------- | --------- | ---------- | --- | ----------- | --- | ------------- | --------- | --- |
| Para       | tener una | mejor      |     | comprensión |     | de            | cómo      | se  |
| relacionan | los       | diferentes |     | commits     | en  | nuestro       | proyecto, |     |
| utilicemos | los       | diferentes |     | comandos    | de  | visualización |           | del |
log.

86
Curso
Lección14:mouredev.com/git-github-141
Inicio:01:11:54|Duración:00:07:37
Recordemos el comando git log. Nos enseñaba todo
lo que había estado pasando en nuestro proyecto,
como cuando creamos un primer archivo, y, según
pasaba el tiempo, realizábamos varios commits en base
a sus modificaciones. Pero, ¿y si queremos desplazar
nuestro código al estado de un commit específico? Por
supuesto, podemos hacerlo. Ya hemos visto cómo usar
git checkout para devolver el contenido de un archivo
alúltimocommitrealizado.
Si escribimos el hash del primer commit después de git
checkout, algo así como git checkout 0000000, siendo
0000000 el hash correspondiente, Git nos avisará de que
podríamos perder los cambios que no hemos guardado
antes de llevar a cabo el desplazamiento. Al hacerlo,
nuestro editor indica que algunos archivos han sido
eliminados, pero en realidad, nos hemos movido a un
estadoanteriordelproyecto.
1https://mouredev.com/git-github-14

87
Para regresar al estado actual de nuestro proyecto,
podemos usar git checkout HEAD -- .. Al hacerlo,
nos desplazamos al extremo final de la rama actual.
Podemos revisar en el git log que hemos cambiado
nuestraposiciónenelhistorialdecommits.Otramanera
de movernos al final de la rama, es simplemente
haciendo git checkout seguido del hash abreviado del
últimocommit.
Con git log, podemos comprobar cómo nuestro
proyectopuedeidentificarloscommits,ymovernosentre
ellos usando solo la parte final del hash. Al ejecutar
git tree (creamos anteriormente un Alias para este
comando), observamos que el puntero dentro de la
rama main está en el último commit, al igual que HEAD.
Nuestros últimos archivos han vuelto a aparecer en
nuestroproyectolocal.

88
Recordemos que Git es un sistema de control de
versiones distribuido. Tenemos una copia de todas las
fotografías de la rama actual en nuestra máquina, pero
Git las almacena de tal manera que no las vemos. Los
archivos que parecían eliminados, en realidad, estaban
almacenados,volviendoaaparecercuandonosmovimos
alcommitcorrespondiente.
Al desplazarnos por los commits, los archivos
desaparecen y vuelven a aparecer dependiendo de
su estado en ese momento. Esto es lo que significa
trabajar con un control de versiones, y desplazarnos
por una rama, aunque el concepto de rama no haga
siempre referencia a una única. Lo veremos ampliado
másadelante.

| Capítulo |     | 15: | Reset |     | y log |     |
| -------- | --- | --- | ----- | --- | ----- | --- |
de referencias
$git
| reset | --hard |     | y   | $git |     |     |
| ----- | ------ | --- | --- | ---- | --- | --- |
reflog
Comandos
| 1 git reset | --hard |        |     |     |     |     |
| ----------- | ------ | ------ | --- | --- | --- | --- |
| 2 git reset | --hard | <hash> |     |     |     |     |
3 git reflog
Conceptos
Introducción
| Continuando | con   | nuestro    | aprendizaje |        | de Git,   | en este |
| ----------- | ----- | ---------- | ----------- | ------ | --------- | ------- |
| capítulo    | vamos | a explorar | dos         | nuevos | comandos: |         |
git
| reset      | --hard   | y git | reflog. | Estos   | comandos  | nos |
| ---------- | -------- | ----- | ------- | ------- | --------- | --- |
| permitirán | manejar, | aún   | mejor,  | nuestro | historial | de  |
commits,ycorregirerroresencasodesernecesario.
89

90
Reset —hard
El comando git reset --hard es una variante más
radicaldelcomandogit reset.Mientrasquegit reset
nos permitía retroceder en el tiempo hasta un punto
específico en nuestro historial de commits, con git
reset --hard podremos eliminar todo lo que se haya
hechodespuésdel puntode retornoque leindiquemos,
incluyendo los cambios no confirmados en el área de
trabajo,yloscommitsadicionalesquesehayanrealizado.
Es importante tener en cuenta que el comando git
reset --hard es una operación peligrosa, ya que borra
permanentemente cualquier cambio posterior al punto
dereseteo.
Reflog
¿Quésucedesinosequivocamosalrealizarungit reset
--hardyqueremosrecuperarloscambiosperdidos?Aquí
es donde aparecerá para rescatarnos el comando git
reflog.Estecomandonosmuestraelhistorialcompleto
de todas las acciones realizadas en nuestro repositorio,
incluidosloscommits quecreíamoshabereliminadocon
elcomandogit reset --hard.
Podemosutilizarestalistaparabuscarelhashdelcommit
alquequeremosvolveryrecuperarloscambiosperdidos.
Para recuperar esos cambios, simplemente buscamos
en el listado el hash del commit al que queremos volver
y ejecutamos de nuevo git reset --hard con ese
identificador. Esto nos llevará de vuelta al punto en el
que nos encontrábamos antes de ejecutar el git reset
--hard.

91
Conclusiones
| Es muy   | importante    |             | tener  |            | en cuenta |                  | que    | estos |
| -------- | ------------- | ----------- | ------ | ---------- | --------- | ---------------- | ------ | ----- |
| comandos | son           | operaciones |        | peligrosas |           | que              | pueden |       |
| tener    | consecuencias |             | graves | si se      | usan      | incorrectamente. |        |       |
Porlotanto,esfundamentaltenercuidadoyasegurarse
dequeestamosenelpuntocorrectodenuestrohistorial
antesdeejecutarcualquieradeellos.
| En general,      | estos   | comandos       |     | pueden          | ser                 | muy          | útiles     | en   |
| ---------------- | ------- | -------------- | --- | --------------- | ------------------- | ------------ | ---------- | ---- |
| situacionesenlas |         | quenecesitamos |     |                 | ajustarnuestralínea |              |            |      |
| de tiempo        | de      | commits        | o   | corregir        | errores             |              | en nuestro |      |
| código.          | Con la  | práctica       | y   | el uso          | constante,          |              | podemos    |      |
| aprovechar       | al      | máximo         | las | funcionalidades |                     | de           | Git        | para |
| mejorar          | nuestro | flujo          | de  | trabajo         | y                   | colaboración |            | en   |
proyectosdesoftware.
Curso
Lección15:mouredev.com/git-github-151
Inicio:01:19:31|Duración:00:08:06
| Sigamos   | aprendiendo  |          | nuevos         |        | comandos   |            | de Git.  | En   |
| --------- | ------------ | -------- | -------------- | ------ | ---------- | ---------- | -------- | ---- |
| concreto, | un           | par más. | En             | primer | lugar,     | recordemos |          |      |
| lo que    | hacía git    | reset.   |                | Este   | comando    | nos        | permitía |      |
| desechar  | cambios      | o        | movernos       |        | a un punto | específico |          | en   |
| nuestro   | repositorio, |          | sin considerar |        | todo       | lo que     | se       | hizo |
después.
1https://mouredev.com/git-github-15

92
| Existe una  | variante        |            | más     | destructiva |         | del git          | reset,   |       |
| ----------- | --------------- | ---------- | ------- | ----------- | ------- | ---------------- | -------- | ----- |
| que añade   | el              | argumento  |         | (o flag)    | --hard: | el               |          |       |
|             |                 |            |         |             |         |                  | git      | reset |
| --hard.     | ¿Qué            | significa? | Hemos   |             | hecho   | cuatro           | commits  |       |
| en nuestra  | rama            | main,      | cada    | uno         | con     | un identificador |          |       |
| que nos     | permite         | movernos   |         | entre       | ellos.  | Hasta            | ahora,   | ya    |
| comprobamos |                 | que        | con git | reset       |         | podíamos         | resetear |       |
| cambios     | o posicionarnos |            |         | en un       | punto   | concreto         |          | de la |
líneadeltiempodenuestrorepositorio.
| Supongamos |            | que nos | damos      |      | cuenta      | de que    | los          | dos   |
| ---------- | ---------- | ------- | ---------- | ---- | ----------- | --------- | ------------ | ----- |
| últimos    | commits    | son     | errores,   | y    | queremos    |           | eliminarlos. |       |
| En este    | caso,      | podemos |            | usar | su variante |           | git          | reset |
| --hard.    | Para hacer | esto,   | escribimos |      |             | git reset | --hard       |       |
seguidodelhashdelcommitalquequeremosregresar.Al
ejecutarestecomando,lacabeza(HEAD)denuestrarama
| se moverá   | al             | commit | seleccionado, |      | y   | todos    | los cambios |     |
| ----------- | -------------- | ------ | ------------- | ---- | --- | -------- | ----------- | --- |
| posteriores | desaparecerán, |        |               | como |     | si nunca | hubieran    |     |
existido.

93
Pero, ¿qué pasa si nos equivocamos al hacer un git
reset --hardyqueremosrecuperarloscambiosperdidos?
Aquí es donde aparece un nuevo comando, el llamado
git reflog (log de referencias). Este comando nos
muestra el historial completo, repito, completo, de
interacciones en nuestro repositorio, incluidos los
commits que pensábamos que habíamos eliminado.
Para recuperar los cambios perdidos, simplemente
buscamos el hash del commit al que queremos regresar
y ejecutamos de nuevo git reset --hard con ese
identificador.

94
| De esta | manera, utilizando | git | reset --hard | y git |
| ------- | ------------------ | --- | ------------ | ----- |
reflog,podemosmovernoshaciaadelanteyhaciaatrás
| en nuestro | historial        | de commits. Con | estos comandos, |     |
| ---------- | ---------------- | --------------- | --------------- | --- |
| podemos    | corregir errores | y ajustar       | nuestra línea   | del |
tiemposegúnseanecesario.

| Capítulo |     |     | 16: | Etiquetas |     |     |     |     |
| -------- | --- | --- | --- | --------- | --- | --- | --- | --- |
| $git     |     | tag |     |           |     |     |     |     |
Comandos
| 1 git tag    |              |              |               |     |     |     |     |     |
| ------------ | ------------ | ------------ | ------------- | --- | --- | --- | --- | --- |
| 2 git tag    | <nombre_tag> |              |               |     |     |     |     |     |
| 3 git tag    | <nombre_tag> |              | <hash_commit> |     |     |     |     |     |
| 4 git show   | <nombre_tag> |              |               |     |     |     |     |     |
| git checkout |              | <nombre_tag> |               |     |     |     |     |     |
5
| git tag | -d  | <nombre_tag> |     |     |     |     |     |     |
| ------- | --- | ------------ | --- | --- | --- | --- | --- | --- |
6
Conceptos
Introducción
| En desarrollo       |           | de software,   |             | es           | esencial     |            | mantener    | un  |
| ------------------- | --------- | -------------- | ----------- | ------------ | ------------ | ---------- | ----------- | --- |
| historial           | detallado | de             | los cambios |              | realizados   |            | en nuestro  |     |
| código.             | Git       | y GitHub       | son         | herramientas |              |            | ampliamente |     |
| utilizadas          |           | para gestionar |             | versiones    |              |            | de código   | y   |
| colaborar           | en        | proyectos      | de          | manera       |              | eficiente. | Una         | de  |
| las características |           | más            | útiles      | de           | Git          | es su      | capacidad   | de  |
| etiquetar           | puntos    | específicos    |             | en           | el historial |            | de cambios  |     |
denuestrorepositorioutilizandotags.
95

96
Tag o etiqueta
UntagenGitesunareferenciaaunpuntoespecíficoen
| el historial | de cambios |         | de  | nuestro   | repositorio. |      | Al            | crear |
| ------------ | ---------- | ------- | --- | --------- | ------------ | ---- | ------------- | ----- |
| un tag,      | podemos    | darle   |     | un nombre |              | para | identificarlo |       |
| fácilmente   | en el      | futuro. | Los | tags      | pueden       | ser  | utilizados    |       |
| para marcar  | versiones  |         | de  | una       | aplicación,  |      | o cualquier   |       |
otropuntoimportanteenelhistorialdecambios.
Creación
| Crear      | un tag | en Git | es      | muy | sencillo. | Simplemente |          |     |
| ---------- | ------ | ------ | ------- | --- | --------- | ----------- | -------- | --- |
| escribimos |        |        | seguido | del | nombre    | que         | queremos |     |
git tag
| darle a      | la etiqueta. |     | Es recomendable |           |                 | utilizar | nombres |     |
| ------------ | ------------ | --- | --------------- | --------- | --------------- | -------- | ------- | --- |
| descriptivos | y fáciles    |     | de              | entender, | preferiblemente |          |         | en  |
minúsculasyconguionesbajos.
| También       | podemos                     | asignar |     | un tag | a un | commit | concreto |     |
| ------------- | --------------------------- | ------- | --- | ------ | ---- | ------ | -------- | --- |
| utilizandogit | tagseguidodelnombreysuhash. |         |     |        |      |        |          |     |
Visualización
| Para ver | una lista | de         | todos | los tags | que  | hemos           | creado, |       |
| -------- | --------- | ---------- | ----- | -------- | ---- | --------------- | ------- | ----- |
| podemos  | utilizar  | el comando |       | git      | tag, | sin argumentos. |         |       |
| Esto nos | mostrará  | una        | lista | de todos |      | los tags        | en      | orden |
alfabético.
| Si queremos | ver | más | detalles | sobre | un  |     | específico, |     |
| ----------- | --- | --- | -------- | ----- | --- | --- | ----------- | --- |
tag
| podemos | utilizar | el  | comando | git | show |     | seguido | del |
| ------- | -------- | --- | ------- | --- | ---- | --- | ------- | --- |
nombredeltag.Estonosmostraráinformacióndetallada
sobreelcommitasociadoaesetag.

97
Desplazamiento
Los tags pueden ser utilizados para movernos
rápidamente entre diferentes commits en nuestro
historial de cambios. Para hacer esto, podemos utilizar
elcomandogit checkoutseguidodelnombredeltag.
Eliminación
Por último, si queremos eliminar un tag que ya no
necesitamos, podemos utilizar el comando git tag -d
seguidodelnombredeltag.
Es importante tener en cuenta que eliminar un tag no
afectaelhistorialdecambiosdenuestrorepositorio,solo
eliminalareferenciaalaetiquetaenesepuntoespecífico.
Conclusión
Los tags son una herramienta muy útil en Git que
nos permiten etiquetar puntos importantes en nuestro
historialdecambios.Conlostagsnospodemosdesplazar
rápidamente entre diferentes commits, y mantener un
registro detallado de versiones de nuestra aplicación.
Es importante utilizar nombres descriptivos y fáciles de
entender para nuestros tags, y recordar que eliminar un
tagnoafectaelhistorialdecambiosdelrepositorio.
Curso
Lección16:mouredev.com/git-github-161
1https://mouredev.com/git-github-16

98
Inicio:01:27:37|Duración:00:09:59
| Vamos        | a hablar | sobre          |        | el comando  |            | git tag.      | Un        | tag, |
| ------------ | -------- | -------------- | ------ | ----------- | ---------- | ------------- | --------- | ---- |
| o etiqueta,  |          | nos permite    |        | hacer       | referencia | a             | un commit |      |
| específico.  |          | Los tags       | nos    | resultan    | muy        | útiles        | cuando    |      |
| queremos     |          | marcar         | puntos | importantes |            | en            | nuestro   |      |
| repositorio, |          | como versiones |        | o puestas   |            | en producción |           | de   |
laaplicación.
| Podríamos                            |          | usar un    | tag para | marcar           | todo       | lo             | que hemos  |       |
| ------------------------------------ | -------- | ---------- | -------- | ---------------- | ---------- | -------------- | ---------- | ----- |
| subido                               | hasta    | un punto   |          | específico       | y          | significativo, |            | como  |
| por                                  | ejemplo  | una        | supuesta | versión          |            | 1.0            | de nuestra |       |
| aplicación.                          |          | Para crear | un       | tag, simplemente |            |                | escribimos |       |
| en la                                | terminal | git        | tag      | seguido          | del nombre |                | del tag.   | Es    |
| una                                  | buena    | práctica   | utilizar | minúsculas       |            | y guiones      |            | bajos |
| enlosnombresdelostags,porejemplo:git |          |            |          |                  |            |                |            | v1.0. |
tag
| Una          | vez              | creado | el tag, | si         | ejecutamos |        | git       | log, |
| ------------ | ---------------- | ------ | ------- | ---------- | ---------- | ------ | --------- | ---- |
| observaremos |                  | que    | se      | ha añadido |            | el tag | a nuestro |      |
|              | correspondiente. |        |         | Podemos    | agregar    |        | tantos    | tags |
commit
comoqueramos.

99
Para ver una lista de todos los tags que hemos creado,
podemos usar el comando git tag, sin argumentos.
Para movernos entre diferentes commits usando tags,
podemosempleargit checkoutseguidodelnombredel
tag,porejemplo:git checkout v1.0.
Los tags también nos permiten movernos rápidamente
a puntos específicos de nuestro repositorio sin tener
que buscar el hash del commit. Esto es especialmente
útil cuando queremos solucionar errores en versiones
anteriores, o trabajar sobre el código fuente de una
versiónespecífica.
Si nos hemos desplazado, otra manera de volver al final
de nuestra rama actual es utilizando git checkout
seguido del nombre de la rama, por ejemplo: git
checkout main.
Hemos visto cómo utilizar git tag, y cómo movernos
entre diferentes commits usando tags. Por supuesto, los
tagstambiénsepuedeeliminarusandoelargumento-d,
porejemplo,ejecutandogit tag -d v1.0.

| Capítulo |      | 17: | Creación |     | de   |
| -------- | ---- | --- | -------- | --- | ---- |
| ramas    |      |     |          | y   |      |
|          | $git |     | branch   |     | $git |
switch
Comandos
1 git branch
| 2 git branch   | <nombre_rama> |               |     |     |     |
| -------------- | ------------- | ------------- | --- | --- | --- |
| 3 git switch   | <nombre_rama> |               |     |     |     |
| 4 git checkout | -b            | <nombre_rama> |     |     |     |
| 5 git switch   | -c            | <nombre_rama> |     |     |     |
Conceptos
Introducción
| Uno de     | los conceptos |          | fundamentales | de  | Git son     |
| ---------- | ------------- | -------- | ------------- | --- | ----------- |
| las ramas, | que           | permiten | a los equipos |     | trabajar en |
diferentesflujosdedesarrollodemaneraindependiente
ycolaborativa.Enestalección,exploraremosmásafondo
| el concepto       | de ramas | en  | Git, aprendiendo | a   | gestionarlas |
| ----------------- | -------- | --- | ---------------- | --- | ------------ |
| conloscomandosgit |          |     | ygit switch.     |     |              |
branch
100

101
Utilidad
Las ramas en Git permiten a los equipos trabajar en
diferentesflujosdetrabajodemaneraindependientesin
afectarlaramaprincipal.
Creación
ParacrearunanuevaramaenGit,utilizamoselcomando
git branch,seguidodelnombredelanuevarama.
Desplazamiento
Para desplazarnos a una rama diferente, utilizamos el
comando git switch, seguido del nombre de la rama a
laquedeseamosmovernos.
También podemos crear una rama y desplazarnos
directamente a ella. Para ello utilizamos git checkout
-b o git switch -c, seguido del nombre de la nueva
rama.
Recuerda que al cambiar de rama, nuestros archivos y
directorios se actualizarán según el estado de la rama a
la que nos estamos desplazando. Por lo tanto, debemos
asegurarnos de guardar y hacer commit de nuestros
cambios antes de cambiar de rama para evitar perder
cualquiertrabajo.
Diferencia entre switch y checkout
Hemos utilizado switch y checkout para desplazarnos
entre ramas, por eso es muy habitual plantearse cuál

102
es la diferencia entre ambos comandos. La diferencia
principal entre git switch y git checkout es que git
switch está diseñado específicamente para cambiar
entre ramas, mientras que git checkout tiene varias
funciones, incluyendo la capacidad de cambiar entre
ramas,hash,tagsycommits.
En otras palabras, git switch es una instrucción más
especializada enfocada exclusivamente en las ramas,
mientras que git checkout contempla una utilización
másgeneral.
Además, git switch tiene una sintaxis más clara y fácil
de entender que git checkout, lo que la hace más fácil
deutilizar,yreducelaposibilidaddecometererrores.
Por lo tanto, siguiendo las propias recomendaciones
del equipo de Git, si deseamos cambiar entre ramas, es
recomendableutilizargitswitchenlugardegitcheckout.
Desarrollo
Una vez que estamos en una nueva rama, podemos
trabajarenellacomoloharíamosenotracualquiera.Por
ejemplo, podemos crear nuevos archivos, modificar los
existentes,ejecutarmáscomandosdeGit,etc.
Conclusión
LasramasenGitsonunaherramientafundamentalpara
permitir a los equipos trabajar en diferentes flujos de
maneraindependienteycolaborativa.Loscomandosgit
branchygit switchsonesencialesparagestionarestas
ramas.

103
Además, las ramas también pueden ser utilizadas para
experimentar sin afectar la rama principal. Por ejemplo,
podemos crear una nueva rama para probar una nueva
funcionalidad, o hacer cambios radicales en el código
fuentesinafectaralaestabilidaddelproyecto.
Sinembargo,tengamosencuentaque,tenerdemasiadas
ramas, puede hacer que la gestión del proyecto sea más
compleja. Por lo tanto, es recomendable mantener un
número limitado de ramas y eliminar las que ya no son
necesarias.
El uso de ramas en Git es una práctica esencial para
el desarrollo de proyectos colaborativos de manera
eficiente y organizada. Ya, por último, nombrar que
las ramas también se puede fusionar, integrando los
cambios y funcionalidades de ambas en una única, todo
esto sin afectar la estabilidad del proyecto. Hablaremos
másdelconceptodefusiónenloscapítulossiguientes.
Curso
Lección17:mouredev.com/git-github-171
Inicio:01:37:36|Duración:00:11:30
En esta lección, vamos a explorar el concepto principal
de Git: las ramas. Hasta ahora, hemos trabajado en una
sola línea del tiempo, la llamada rama principal o main.
Git permite crear ramificaciones, y, para gestionarlas,
usaremoselcomandogit branch.
¿Porquéusarramas?Imaginemosquequeremostrabajar
en una nueva funcionalidad, por ejemplo, cómo agregar
1https://mouredev.com/git-github-17

104
uniniciodesesiónanuestraaplicaciónficticia,claroestá,
sin afectar la rama principal. Para ello, crearemos una
rama llamada login, para así trabajar en ella de forma
independiente.
¿Cómo creamos ramas? Únicamente ejecutamos el
comando git branch seguido del nombre de la rama,
en este caso, git branch login. Esta ejecución creará
la rama login, pero, a pesar de haber creado la nueva
rama,aúnnosencontramossituadosenlaramamain.
Para desplazarnos a la nueva rama, utilizaremos un
nuevocomando,git switch,comosiempre,seguidodel
nombre de la rama a en la que nos queremos situar, en
este caso, git switch login. Ya nos encontramos en la
ramalogin,pudiendoasícomenzaratrabajarennuestra
nueva funcionalidad sin interferir con el contenido y
evolución de la rama principal. Por ejemplo, vamos a
crear un nuevo archivo llamado login.py, y a añadirle los
cambios que deseemos, como un print de prueba. A
continuación, ejecutamos nuestra combinación habitual
degit addygit commitparaguardarestoscambiosen
laramalogin.

105
| Para regresar | a la rama | main, ejecutaremos |     | git switch |
| ------------- | --------- | ------------------ | --- | ---------- |
main.Yaenlaramamain,observaremosquenoaparecen
| los cambios | realizados    | en la rama      | login.         | Así, cada rama |
| ----------- | ------------- | --------------- | -------------- | -------------- |
| funciona    | de manera     | independiente,  | permitiéndonos |                |
| trabajar    | en diferentes | funcionalidades |                | sin afectarse  |
entresí.
| Las ramas     | se pueden | dividir aún   | más, | lo que permite |
| ------------- | --------- | ------------- | ---- | -------------- |
| a los equipos | colaborar | autónomamente |      | y de maneras   |
infinitas.Cuandolasfuncionalidadesesténlistas,podrán

106
| integrarse | en  | la rama | principal |     | (o en la | rama | que se |
| ---------- | --- | ------- | --------- | --- | -------- | ---- | ------ |
desee).
| En resumen,     |        | las ramas | en       | Git | permiten | trabajar      | en  |
| --------------- | ------ | --------- | -------- | --- | -------- | ------------- | --- |
| diferentes      | flujos | de        | trabajo  | de  | manera   | independiente |     |
| y colaborativa. |        | Los       | comandos |     |          | (junto        | con |
git branch
| sus variaciones) |     | y git | switch | son | fundamentales |     | para |
| ---------------- | --- | ----- | ------ | --- | ------------- | --- | ---- |
gestionarestasramas.

Capítulo 18: Combinación
de ramas $git merge
Comandos
1 git merge <nombre_rama>
Conceptos
Introducción
Como ya hemos dicho, una de las características más
importantesdeGitessucapacidadparagestionarflujos
detrabajoenbasearamas.
Las ramas son copias independientes del código base,
lo que nos permite trabajar en diferentes características
o funcionalidades sin afectar al código principal. Sin
embargo, en algún momento será necesario integrar el
trabajo realizado en una rama en otra, para mantener
así la coherencia y la compatibilidad entre las diferentes
versiones,ycontinuarevolucionandoelproyecto.
Merge
Git nos ofrece el comando git merge para fusionar los
cambiosrealizadosenunaramaconotra.Estecomando
107

108
toma los cambios realizados en una rama y los aplica
a otra, creando un nuevo commit que combina ambos
historiales.
Para realizar un merge, es necesario estar situados en
la rama de destino y ejecutar el comando git merge,
seguido del nombre de la rama que se desea fusionar.
Es importante tener en cuenta que antes de realizar un
merge,sedebenresolverlosconflictosquepuedansurgir
si hay cambios en ambas ramas, cambios que afecten a
un mismo archivo. El concepto de conflicto lo veremos
detalladoenelsiguientecapítulo.
Una vez que se realiza un merge, es posible comprobar
que los cambios se han aplicado correctamente en la
rama de destino utilizando los comandos git status
o git log. Es importante recordar que, al realizar
un merge, se crea un nuevo commit que combina el
historialdeambasramas,loquesignificaquelaramade
destinoavanzaráeneltiempomásalládelúltimocommit
realizadoenlaramaquesefusionó.
Conclusión
El comando git merge es esencial para mantener la
coherenciaylacompatibilidadentrediferentesversiones
del código en un proyecto colaborativo (o individual).
A través de la fusión de ramas, es posible integrar el
trabajo realizado en una versión del código. Siempre es
importante asegurarnos de resolver cualquier conflicto
antesderealizarunmerge,ycomprobarqueloscambios
sehayanaplicadocorrectamenteenlaramadedestino.

109
Curso
Lección18:mouredev.com/git-github-181
Inicio:01:49:06|Duración:00:05:23
Imaginemos que otro equipo de desarrollo ha seguido
trabajando en la rama main, llegando un momento
en el que queremos saber qué ha estado haciendo
dicho equipo. Puede que hayan pasado horas, o incluso
días desde que comenzaron a trabajar, por lo que nos
interesa asegurarnos de que, lo que han implementado
en la rama main, sigue siendo compatible con lo que
tenemos en nuestra rama login (que creamos en la
lección anterior). Básicamente, necesitamos mantener
ambosflujosactualizados.
Loquebuscamosesaplicarloscambiosdelaramamain
en nuestra rama login. Así comprobaremos que ambos
desarrollos no han provocado ningún efecto colateral al
ejecutar sus funcionalidades de manera conjunta. Para
elloexisteelcomandogit merge.
Podemos definir merge como la acción de fusionar o
combinar cambios entre ramas. Si nos encontramos en
la rama login, y queremos añadir los cambios de la rama
main,únicamenteejecutaremosgit merge main.
1https://mouredev.com/git-github-18

110
| Al hacer              | esto,      | observaremos |                   | que     | la rama | login       | ha    |
| --------------------- | ---------- | ------------ | ----------------- | ------- | ------- | ----------- | ----- |
| podido                | actualizar | su           | contenido,        |         | siempre | que existan |       |
| modificaciones        |            | en           | main posteriores  |         | a       | la creación |       |
| de login.             | Esos       | cambios      | de                | main,   | ahora   | también     | los   |
| tenemos               | en         | login.       | Para comprobarlo, |         | podemos |             | usar  |
| git log,              | observando |              | un commit         |         | que nos | dice        | merge |
| branch                | main       | into login,  | lo que            | nos     | indica  | que hemos   |       |
| combinado             | el         | contenido    | de                | la rama | main    | dentro      | de    |
| login, confirmándonos |            |              | que dicho         |         | proceso | de fusión   | ha    |
funcionadocorrectamente.

111
| Hecho esto, | la rama    | login | ha avanzado |         | en el    | tiempo |
| ----------- | ---------- | ----- | ----------- | ------- | -------- | ------ |
| más allá    | del último |       | que         | se hizo | en main. | Cada   |
commit
| cambio              | en el historial |          | implica     | un nuevo | commit,     | por    |
| ------------------- | --------------- | -------- | ----------- | -------- | ----------- | ------ |
| lo que este         | proceso         | de       | combinación |          | ha generado | un     |
| nuevo identificador |                 | hash.    | Lo que      | acabamos | de          | hacer  |
| es el proceso       | básico          | asociado | a           | un merge | entre       | ramas, |
lograndoasímantenerlacoherenciaentreambas.

| Capítulo |     |     | 19: | Conflictos |     |     |     |     |
| -------- | --- | --- | --- | ---------- | --- | --- | --- | --- |
Comandos
| 1 git diff  |     |          |           |         |           |     |     |     |
| ----------- | --- | -------- | --------- | ------- | --------- | --- | --- | --- |
| 2 git merge |     | --theirs | <archivo> |         |           |     |     |     |
| 3 git merge |     | --mine   | (o        | --ours) | <archivo> |     |     |     |
Conceptos
Introducción
Recordemosunavezmásqueunadelasfuncionalidades
principalesdeGitespermitirquevariosequipostrabajen
| en un        | mismo | proyecto, |        | coordinando |     | sus       | cambios   | y       |
| ------------ | ----- | --------- | ------ | ----------- | --- | --------- | --------- | ------- |
| asegurándose |       | de        | que el | resultado   |     | final sea | coherente | y       |
| funcional.   | Todo  | esto,     | como   | vimos,      |     | gracias   | a las     | ramas y |
losmerge.
| Sin embargo,   |           | el        | trabajo       | con         | ramas      | puede   |             | presentar |
| -------------- | --------- | --------- | ------------- | ----------- | ---------- | ------- | ----------- | --------- |
| algunos        | desafíos, |           | especialmente |             | cuando     |         | los cambios | de        |
| dos o          | más       | ramas     | entran        | en          | conflicto, | y       | dicho       | conflicto |
| debe ser       | resuelto  |           | para          | poder       | continuar  |         | trabajando. | En        |
| este capítulo, |           | vamos     | a             | explorar    | en         | detalle | qué         | es un     |
| conflicto      | en        | Git, cómo |               | se produce, |            | y cómo  |             | podemos   |
solucionarlo.
112

113
Definición
Un conflicto en Git ocurre cuando dos ramas han
modificadoelmismoarchivo,yGitnopuededecidirqué
cambio es el correcto. Esto puede ocurrir, por ejemplo,
cuando dos equipos están trabajando en diferentes
funcionalidades de un proyecto, pero ambos necesitan
modificarunmismoarchivoparalograrsusobjetivos.
El conflicto se producirá cuando Git intenta fusionar las
diferentes ramas y detecta que existen actualizaciones
en el mismo archivo, y, en concreto, en la misma línea.
En lugar de elegir un cambio por encima del otro, Git
nos indica que existe un conflicto, y nos pide que lo
resolvamosmanualmente.
Solución
Resolver un conflicto en Git implica revisar los cambios
que han hecho las diferentes ramas, y decidir cuál es
el correcto. Para ello, Git nos muestra una vista de los
cambios en confrontación, indicando las diferentes
partesquehansidomodificadasencadarama.
Para solucionar el conflicto, debemos editar
manualmente el archivo en problemas, y elegir qué
cambios queremos mantener, o incluso combinar. Esto
implica revisar cuidadosamente los cambios y decidir
cuáles son necesarios para nuestro proyecto, siempre
teniendo en cuenta si esa decisión puede afectar
negativamente a la ejecución en la otra rama. Una vez
quehayamostomadounadecisión,debemosguardarel
archivoyrealizaruncommitparaconfirmarloscambios.
Existen varias maneras de solucionar un conflicto en Git,
pero una de las más comunes es utilizando el comando

114
| git merge.    | Este | comando |     | fusiona | dos | ramas,  | y si      | existe |
| ------------- | ---- | ------- | --- | ------- | --- | ------- | --------- | ------ |
| un conflicto, | nos  | permite |     | revisar | los | cambios | y aportar |        |
unasoluciónmanualmente.Tambiénpodemosutilizarel
| comandogit |     | diffparacompararloscambiosentredos |     |     |     |     |     |     |
| ---------- | --- | ---------------------------------- | --- | --- | --- | --- | --- | --- |
ramasydetectarposiblesproblemas.
Proceso
| Si hay conflictos, |     | Git         | nos | informará   |     | de que | ha         | habido |
| ------------------ | --- | ----------- | --- | ----------- | --- | ------ | ---------- | ------ |
| un problema        |     | al realizar | la  | combinación |     | de     | las ramas, | y      |
mostrarálosarchivosquesehanvistoimplicados.
| • Tenemos |           | que abrir    | el  | archivo | en  | conflicto | y resolver |     |
| --------- | --------- | ------------ | --- | ------- | --- | --------- | ---------- | --- |
| los       | problemas | manualmente. |     |         | Git | nos       | mostrará   | los |
conflictosencadaarchivoconelsiguienteformato:
| 1 <<<<<<< | HEAD |     |     |     |     |     |     |     |
| --------- | ---- | --- | --- | --- | --- | --- | --- | --- |
<codigo_rama_local>
2
=======
3
<codigo_rama_a_fusionar>
4
| >>>>>>> | <nombre_rama_a_combinar> |     |     |     |     |     |     |     |
| ------- | ------------------------ | --- | --- | --- | --- | --- | --- | --- |
5
| • Editaremos |     | manualmente |         |           | el archivo |                | para | dejar |
| ------------ | --- | ----------- | ------- | --------- | ---------- | -------------- | ---- | ----- |
| únicamente   |     | la          | versión | correcta, |            | o combinaremos |      |       |
fragmentosdeambos.
| • Después  |         | de solucionar |             | los | conflictos, |         | añadiremos   |     |
| ---------- | ------- | ------------- | ----------- | --- | ----------- | ------- | ------------ | --- |
| los        | cambios | a             | Git         | con | el comando  |         | git          | add |
| <archivo>. |         | Este          | comando     |     | marca       |         | el conflicto |     |
| del        | archivo | como          | solucionado |     |             | y listo | para         | ser |
confirmado.
| • Resueltos |              | los | problemas, |     | pasaremos |     |        | a la |
| ----------- | ------------ | --- | ---------- | --- | --------- | --- | ------ | ---- |
| fase        | confirmación |     | utilizando |     | git       |     | commit | -m   |
"<mensaje>".

115
Por otra parte, también existe un mecanismo para
seleccionar directamente las modificaciones de nuestra
rama, o las de la que queremos combinar. Todo esto
sin tener que editar y corregir el fichero en conflicto
de forma manual. Para ello utilizaremos git merge
--theirs <archivo> y git merge --mine (o --ours)
<archivo>, opciones que nos permiten especificar qué
versión de un archivo en conflicto se debe conservar
duranteelprocesodecombinación.
• git merge --theirs <archivo>: Esta opción
conserva los cambios de la rama que se está
fusionando (rama a combinar), y descarta los
cambios de la rama local (rama actual). Es decir,
tomasuversión(theirs)enlugardelanuestra(mine
oours).
• git merge --mine (o --ours) <archivo>: Esta
opción conserva los cambios de la rama local (rama
actual),ydescartaloscambiosdelaramaqueseestá
fusionando(ramaacombinar).Esdecir,tomanuestra
versión(mineoours)enlugardesuversión(theirs).
Esimportantetenerencuentaqueestasopcionesdeben
ser usadas con precaución, ya que pueden descartar
cambiosimportantesenalgunoscasos.
Por lo general, en caso de duda, la mejor práctica es
resolver los conflictos manualmente, revisando cada
archivo en conflicto, y decidiendo qué versión conservar.
De esta manera, nos aseguramos de que los cambios
másimportantesseanincluidosenlaramafinal.

116
Conclusión
Para evitar conflictos innecesarios, es recomendable
que los equipos de desarrollo se coordinen y trabajen
en diferentes partes del proyecto, minimizando así
la cantidad de archivos que se modifican al mismo
tiempo.Siunconflictoaparece,escrucialquelosequipos
mantengan una comunicación activa para resolverlo de
manera efectiva, asegurando el avance del proyecto sin
problemas.
Curso
Lección19:mouredev.com/git-github-191
Inicio:01:54:29|Duración:00:09:13
Llegamos a una parte donde se puede complicar el
proceso. Los merge que funcionan correctamente, no
presentan ningún problema, pero, ¿qué pasa con los
que no funcionan? Vamos a analizar uno de los grandes
dolores de cabeza cuando comenzamos a trabajar con
Gitysusramas,hablamosdelconceptodeconflicto.
Haremosalgo.Enunficherohellogit3.py,yaexistenteenla
ramamain,yestandosituadosenlaramalogin,decidimos
efectuar algún cambio en él, realizando un commit del
mismo.
Como equipo asignado a login, hemos modificado el
archivo hellogit3.py, que no debería ser competencia de
nuestro desarrollo. Nos desplazamos a la rama main
con git switch, y, por supuesto, observamos que ha
1https://mouredev.com/git-github-19

117
desaparecido el fichero login.py, ya que la rama main
no tiene conocimiento de lo que se está haciendo en la
rama login. Recordemos que anteriormente fusionamos
(merge)elcontenidodemainenlogin.Porello,logintiene
loscambiosdemain,peromainnolosdelogin.
Imaginemos que el equipo de main ha tenido que editar
también a posteriori el fichero hellogit3.py. Ese equipo sí
que estaba trabajando habitualmente en este archivo.
Supongamos que era una funcionalidad que estaban
implementando. Hacemos commit de ese cambio en
main.
Ahoramismo,estohaderivadoenquetenemosunnuevo
commitenlasramasmainylogin.
Desplacémonosdenuevoalaramalogin.
Recuerda que usamos git switch como buena práctica
para cambiar de rama. El comando git checkout nos
servíaparamáscosas.
Vamos a analizar el siguiente punto. Queremos hacer
exactamente lo mismo que antes. Hemos continuado
trabajando en el login. De nuevo, queremos comprobar
cómo está la rama main y combinar sus cambios en la
rama login. Hacemos git merge main, acción que no ha
funcionado,yaquehaaparecidounconflictoenelarchivo
hellogit3.py.

118
¿Porqué?Porquetantolagentequeestáenlaramamain,
| como el | equipo | de la | rama login, | han | tocado | el mismo |     |
| ------- | ------ | ----- | ----------- | --- | ------ | -------- | --- |
archivoenlamismalíneadecódigo.
| Aquí siempre |     | nos | asalta la | pregunta: |     | ¿Qué | haces |
| ------------ | --- | --- | --------- | --------- | --- | ---- | ----- |
modificandomicódigo?¿Porqué?
| Git es   | un sistema | muy          | listo, y | no le     | gusta      | complicase |      |
| -------- | ---------- | ------------ | -------- | --------- | ---------- | ---------- | ---- |
| la vida, | por los    | nos presenta | la       | siguiente | situación: |            | Aquí |
existeunconflicto.Comosistemadecontroldeversionesno
| tengo ni | idea | de si el código | que | debe permanecer |     | es  | el de |
| -------- | ---- | --------------- | --- | --------------- | --- | --- | ----- |
main, o el de login. Por lo tanto, no te voy a permitir hacer
unmergemientrasnolleguesaunacuerdo.
Sisemodificaranlíneasdiferentesdelcódigoenelmismo
| archivo,   | el merge | podría     | funcionar | en  | la mayor      | parte | de  |
| ---------- | -------- | ---------- | --------- | --- | ------------- | ----- | --- |
| los casos. | Pero     | en nuestro | ejemplo   | se  | ha modificado |       | la  |
mismalíneadecódigo.

119
¿En qué se traduce todo esto? En que debemos editar
el archivo hellogit3.py. Una vez abierto, el editor nos ha
resaltado las dos posibilidades. Nos ha dicho: aquí hay
conflictos, y mientras existan no puedes continuar con el
procesodemerge.
Dicho de otra forma, no podemos hacer un commit de
este código. Debemos aclararnos y solucionarlo. ¿Con
qué nos queremos quedar? ¿Con lo que hemos hecho
nosotrosenlogin,oconloquesehahechoporpartedel
otroequipoenlaramamain?
Supongamos que, como equipo trabajando en la rama
login,nosdamoscuentadelosiguiente:esteesunarchivo
delotroequipo.Noteníamosquehaberlomodificado.
Finalmente, nos hemos dado cuenta de que lo hemos
hecho mal (obviamente, las posibilidades en estos casos
son infinitas). Nos vamos a quedar con lo que había
desarrolladoelequipoquetrabajabadirectamentedesde
main.
¿Cómo acabaremos resolviendo este conflicto? Podemos
hacerlo directamente utilizando el comando git

120
checkout --theirs, ya que hemos decidido indicarle
que nos queremos quedar con los cambios de la otra
rama, no los nuestros. En caso contrario, utilizaríamos
el parámetro --mine (o --ours), en vez de --theirs.
Git es muy extenso, por lo que podríamos hacerlo de
más maneras. Incluso de manera manual, retocando
el código del archivo en caso de tener que combinar
el código de ambos equipos (ya que ambos podrían
haber modificado el archivo de manera totalmente lícita
ynecesariaparasusintereses).
Lo más importante de este proceso es que entendamos
los fundamentos. Qué es lo que está pasando, y qué
pasos debemos seguir paraalcanzar una solución. Estos
pasos, a veces son muy simples y rápidos, y otras veces
deberemos dedicarle todo el tiempo que sea necesario.
Aún así, Git es un sistema pensado para que existan
los menores conflictos posibles. En este ejemplo hemos
forzado el conflicto para poder explorar el proceso de
corrección.
Habitualmente, si las tareas de los equipos están bien
organizadas,nodeberíanexistirdemasiadosconflictos,y,
en el caso de aparecer alguno, tendrían que resolverse
sinmuchascomplicaciones.
Solucionada la colisión en el fichero, ¿qué es lo siguiente
que tenemos que hacer para acabar solucionando el
conflicto? Pues bien, vamos a intentar realizar un commit
delarchivo,consucorrespondientemensaje.Allanzarlo,
observaremos que no funciona. Git nos dice: ¿por qué
quieres hacer un commit? Revisemos con git status,
descubriendo que, al modificar de nuevo el archivo para
solucionar el conflicto, debemos realizar primeramente
ungit adddeeste.Loañadimos.Ahorasíquepodremos
realizarelgit commit.

121
Vamos a revisar el proceso. Ya no se nos indica ningún
problema. Al ejecutar de nuevo git status, parece que
todoestácorrecto.Sihacemosungit log,yaaparecerá
la corrección del conflicto. Por fin nos hemos puesto de
acuerdo.Volvemosaestartotalmenteactualizados.
Hemos aprendido a enfrentarnos a conflictos en Git
cuando trabajamos con ramas, y cómo solucionarlos de
maneraefectiva.Loimportanteesentenderquécambios
sehanhechoencadaramaycoordinarnosentreequipos
para evitar conflictos innecesarios. Cuando se presentan
conflictos, es crucial saber cómo abordarlos, resolverlos,
y hacer un commit con la solución, para que así el
trabajo en equipo pueda continuar, evitando futuros
problemas.Gitesunaherramientamuypotentealahora
degestionarcolaboraciones,siempreycuandoseutilice
demaneraadecuadaysiguiendobuenasprácticas.

| Capítulo |     | 20: | Cambios |     |     |     |     |
| -------- | --- | --- | ------- | --- | --- | --- | --- |
temporales
|     |     |     | $git |     | stash |     |     |
| --- | --- | --- | ---- | --- | ----- | --- | --- |
Comandos
1 git stash
| 2 git stash | pop   |         |     |     |     |     |     |
| ----------- | ----- | ------- | --- | --- | --- | --- | --- |
| 3 git stash | apply |         |     |     |     |     |     |
| 4 git stash | list  |         |     |     |     |     |     |
| git stash   | drop  | <stash> |     |     |     |     |     |
5
| git stash | clear |     |     |     |     |     |     |
| --------- | ----- | --- | --- | --- | --- | --- | --- |
6
Conceptos
Introducción
| Continuando | con      | el contexto | de              | trabajo    | sobre        | ramas, | en    |
| ----------- | -------- | ----------- | --------------- | ---------- | ------------ | ------ | ----- |
| ocasiones   | puede    | ocurrir     | que necesitemos |            | desplazarnos |        |       |
| entre ellas | mientras | estamos     |                 | trabajando | en           | una    | tarea |
| específica, | pero     | aún no      | queramos        |            | hacer        |        | de    |
commit
| los cambios | realizados. |        | Para | estos | casos,  | Git     | nos |
| ----------- | ----------- | ------ | ---- | ----- | ------- | ------- | --- |
| ofrece      | el comando  | stash, | que  | nos   | permite | guardar |     |
temporalmentelasmodificacionesenunaramasintener
quehacercommit.
122

123
Stash
Es un comando de Git que nos permite guardar
temporalmente los cambios que hemos realizado
en un archivo, o conjunto de archivos, sin tener que
hacer commit. Cuando utilizamos stash, Git guarda una
instantánea de los archivos modificados y los almacena
enunapila,paraquepodamostrabajarenotraramasin
perder nuestro progreso. Los cambios guardados con
stash se pueden aplicar posteriormente en la misma
rama,oenotradiferente.
Utilización
Parautilizarstash,debemosseguirlossiguientespasos:
• Realizamoscambiosenlaramaactual.Paraguardar
temporalmentenuestroscambiossinhacercommit,
debemosejecutargit stash.
• Una vez nos hemos desplazado a la nueva rama,
podemos realizar los cambios necesarios en los
archivos correspondientes. Ya de vuelta a la rama
enlaquenosencontrábamostrabajando,podemos
aplicar los cambios guardados previamente
con stash, y continuar trabajando en ellos.
Los recuperamos usando git stash pop. Este
comandoaplicaráloscambiosguardadosconstash
yloseliminarádelapila.
• Si preferimos recuperar, y mantener los cambios
en la pila, podemos utilizar el comando git stash
apply.
Es importante tener en cuenta que, si hemos guardado
varios cambios con stash, debemos aplicarlos en el

124
orden inverso al que los hemos guardado. Es decir, el
último conjunto de cambios guardado será el primero
enseraplicado.
Gestión
En ocasiones, puede ser útil visualizar qué conjunto de
cambios hemos guardado en la pila de stash. Para ello,
podemos utilizar el comando git stash list. Cada
conjunto de cambios estará identificado por un nombre
yunmensajedescriptivo.
Si decidimos que ya no necesitamos los cambios
guardadosenunconjuntodestash,podemoseliminarlo
delapilaconelcomandogit stash drop <stash>.Este
comando eliminará el conjunto de cambios asociados al
nombredelstash.
Utilizandogit stash clearlimpiaremoslapilacompleta
destash.Simplementeparaquelotengamosencuenta,
podríamos llegar a recuperar stash previamente
eliminados.
Conclusión
En resumen, stash es un mecanismo muy útil de Git
que nos sirve para guardar temporalmente los cambios
realizadosenunaramasintenerquehacercommit.Esto
nos permite desplazarnos entre ramas, o realizar otras
tareastemporalessinperdernuestroprogresoenlarama
actual. Con stash, podemos trabajar de manera más
eficiente y ordenada en nuestro proyecto, y recuperar
nuestros cambios guardados cuando lo consideremos
oportuno.

125
Curso
Lección20:mouredev.com/git-github-201
Inicio:02:03:42|Duración:00:06:29
Para esta lección, imaginemos que nos encontramos
trabajando en la rama login, haciendo cambios en el
archivo login.py. De repente, nos piden que arreglemos
un error urgente en la rama main. Por supuesto, no
queremos perder el trabajo de la rama login, ya que
todavía no están finalizados como para hacer un commit
con ellos. Para resolver esta situación tenemos el
comandostash.
git stash nos permite guardar de forma temporal
nuestros cambios sin hacer commit, así podremos
cambiar de rama sin perder nuestro avance. Para
guardar nuestros cambios en un stash simplemente
ejecutamosgit stash.
1https://mouredev.com/git-github-20

126
| Una vez          | hecho     | esto,       | ya  | podemos    |           | cambiar    | a la       | rama  |
| ---------------- | --------- | ----------- | --- | ---------- | --------- | ---------- | ---------- | ----- |
| main sin         | problemas |             | con | git switch |           | main.      | Realizamos |       |
| las correcciones |           | necesarias  |     | en         | la rama   | main,      | y, cuando  |       |
| terminamos,      |           | volvemos    |     | a la rama  | login     | utilizando |            | git   |
| switch           | login.    |             |     |            |           |            |            |       |
| Para recuperar   |           | los cambios |     | que        | guardamos |            | en el      | stash |
| utilizamos       | el        | comando     | git | stash      | pop.      | Esto       | aplicará   | los   |
cambiosdelstashyloseliminarádelalista.Sipreferimos
| mantenerlos |     | en la | lista, | podemos |     | utilizar | git | stash |
| ----------- | --- | ----- | ------ | ------- | --- | -------- | --- | ----- |
apply.
| En cualquier                 |     | momento |     | podremos |     | revisar     | la lista | de  |
| ---------------------------- | --- | ------- | --- | -------- | --- | ----------- | -------- | --- |
| nuestrosstashconelcomandogit |     |         |     |          |     | stash list. |          |     |
Sialfinaldecidimosquenoqueremosutilizarloscambios
queguardamosenunstash,seráposibleeliminarlosuno
| por uno | con     | el comando |          | git   | stash      | drop | <stash>. |       |
| ------- | ------- | ---------- | -------- | ----- | ---------- | ---- | -------- | ----- |
| También | podemos |            | eliminar | todos | utilizando |      |          |       |
|         |         |            |          |       |            |      | git      | stash |
clear.

| Capítulo      |     | 21: |     |     |       |     |     |
| ------------- | --- | --- | --- | --- | ----- | --- | --- |
| Reintegración |     |     |     | de  | ramas |     |     |
Comandos
| 1 git diff  | <nombre_rama_a_reintegrar> |     |     |     |     |     |     |
| ----------- | -------------------------- | --- | --- | --- | --- | --- | --- |
| 2 git merge | <nombre_rama_a_reintegrar> |     |     |     |     |     |     |
Conceptos
Introducción
| El proceso   | descrito |          | en este     | capítulo |                 | es fundamental   |          |
| ------------ | -------- | -------- | ----------- | -------- | --------------- | ---------------- | -------- |
| para un      | correcto | manejo   |             | de ramas | en              | Git. Al          | trabajar |
| en proyectos |          | que      | evolucionan |          | constantemente, |                  | es       |
| común que    | se       | utilicen | diferentes  |          | ramas           | para desarrollar |          |
funcionalidades,correccionesdeerroresymejoras.
Reintegración
Supongamosquehemostrabajadoenunafuncionalidad
| concreta    | dentro      | de    | una rama, | llegando |             | el momento | de        |
| ----------- | ----------- | ----- | --------- | -------- | ----------- | ---------- | --------- |
| integrar    | este código |       | con el    | de otra  | rama        | del        | proyecto, |
| para así    | poder       | hacer | uso       | de este  | desarrollo. |            | Primero,  |
| necesitamos | cambiar     |       | a la rama | donde    | queremos    |            | añadir    |
elnuevocódigo.
127

128
Una vez que ya estamos situados en dicha rama,
usaremos el comando git diff <nombre_rama_a_-
reintegrar> para comparar los cambios entre ambas
ramas.Sihaydiferencias,podemosusarelcomandogit
merge <nombre_rama_a_reintegrar> para agregar los
cambios de la rama en la que hemos estado trabajando
dentro de la rama en la que los queremos reintegrar.
Este es el proceso de merge, reintegración o fusión en
Git del que ya hemos hablado anteriormente, pero que
en este caso aplica al proceso de evolución seguro de
nuestroproyecto.
Después de completar el merge, podemos usar el
comando git status para asegurarnos de que todo
estécorrectamenteintegrado.
Conclusión
Emplear ramas en Git nos permite organizar nuestro
trabajo y colaborar de manera efectiva. Al aprender
a trabajar con diferentes ramas y fusionar cambios,

129
podemos mantener nuestro proyecto en orden y
continuartrabajandoenéldemanerasegurayefectiva.
Curso
Lección21:mouredev.com/git-github-211
Inicio:02:10:11|Duración:00:06:15
Siguiente concepto. Ya hemos trabajamos con merge
y stash, y también nos hemos desplazamos entre
ramas mientras el proyecto se seguía modificando.
Supongamos que terminamos la funcionalidad de login.
La hemos implementado, y todo está perfecto. Es hora
deañadirelcódigodeloginenlaramamain.
Nos cambiamos a dicha rama principal con git switch
main. Por supuesto, en la rama main no tenemos la
implementación del login. Así como anteriormente
pudimos hacer un merge en la rama login de lo que
teníamos en el main, ahora, teniendo una versión
final del login, podemos hacer lo mismo en el sentido
contrario.
Leindicamosalaramamain,asociadaalcódigoprincipal
denuestraaplicación,quequeremosañadirelcódigode
laramalogin.
Queremos fusionar el código de rama login con el de la
rama main. ¿Tenemos conflictos? Vamos a comprobarlo.
Podemos usar el comando llamado git diff, que
nos sirve para comparar ramas. Si ejecutamos git diff
login, estando en main, se nos mostrará que sí que hay
cambios en la rama login, tales como un nuevo archivo
1https://mouredev.com/git-github-21

130
| llamadologin.py        |             | conunprint.Estamosdeacuerdo,yno |         |           |           |             |            |       |
| ---------------------- | ----------- | ------------------------------- | ------- | --------- | --------- | ----------- | ---------- | ----- |
| existen                | conflictos, | así                             | que nos | traemos   |           | los cambios |            | main  |
| utilizandoelcomandogit |             |                                 |         | merge     | login.    |             |            |       |
| Hecho                  | esto, en    | la rama                         | main    | ya        | tendremos |             | un archivo |       |
| que llamado            | login.py,   |                                 | por     | lo que    | parece    | que         | ya         | se ha |
| reintegrado            | el          | contenido                       |         | de login. | Con       | git         | status     |       |
comprobaremosqueelprocesoescorrecto.
| Imaginemos  | que        | modificamos |             |            | login.py   | estando   | ya       | en  |
| ----------- | ---------- | ----------- | ----------- | ---------- | ---------- | --------- | -------- | --- |
| main, pero  | finalmente |             | lo queremos |            | descartar. |           | Recuerda |     |
| que podemos |            | hacerlo     | con         | el comando |            | checkout. |          | Al  |
git
| ejecutargit | checkout |     | mainvolveremosadejarelarchivo |     |     |     |     |     |
| ----------- | -------- | --- | ----------------------------- | --- | --- | --- | --- | --- |
login.pycomoestabaenelúltimocommit,enelmomento
| en el que | realizamos |     | el merge | y   | la reintegración |     | de  | los |
| --------- | ---------- | --- | -------- | --- | ---------------- | --- | --- | --- |
cambios.

| Capítulo |     | 22: | Eliminación |     |     |     |
| -------- | --- | --- | ----------- | --- | --- | --- |
| de ramas |     |     |             |     |     |     |
Comandos
| 1 git branch | --delete |               | <nombre_rama> |     |     |     |
| ------------ | -------- | ------------- | ------------- | --- | --- | --- |
| 2 git branch | -d       | <nombre_rama> |               |     |     |     |
Conceptos
Introducción
| Seguimos | hablando |     | de la importancia |     | de las ramas | en  |
| -------- | -------- | --- | ----------------- | --- | ------------ | --- |
Git.Enestecaso,haciendofocosobrequéhacerconuna
ramaqueyasehareintegradoynolanecesitamosmás.
Eliminación
| Una vez    | que se         | ha completado |          | el trabajo | en una             | rama, |
| ---------- | -------------- | ------------- | -------- | ---------- | ------------------ | ----- |
| y se ha    | fusionado      | con           | otra,    | la rama    | ya no es necesaria |       |
| y puede    | ser eliminada. |               | Esto     | se puede   | hacer mediante     |       |
| el comando | git            | branch        | --delete |            | o git branch       | -d,   |
seguidodelnombredelaramaquesequiereborrar.
131

132
Aleliminarlarama,seeliminanlasreferenciasaésta,yse
| borra de                     | la vista      | principal | del       | proyecto. |           | Sin embargo, |              | los |
| ---------------------------- | ------------- | --------- | --------- | --------- | --------- | ------------ | ------------ | --- |
| commits                      | realizados    | en        | la rama   | aún       | existen   | en           | el historial |     |
| de Git.                      | Por lo tanto, |           | si es     | necesario |           | volver       | a trabajar   |     |
| en la rama                   | eliminada,    |           | se pueden |           | recuperar | sus          | commits      |     |
| usandoelyaconocidocomandogit |               |           |           |           |           | reflog.      |              |     |
Recuperación
| Para recuperar | una | rama | eliminada, |     | es  | necesario | usar | el  |
| -------------- | --- | ---- | ---------- | --- | --- | --------- | ---- | --- |
identificadordelcommitalquesequiereregresar.Estose
| puedehacerusandoelcomandogit |     |     |     |     |     | checkout,seguido |     |     |
| ---------------------------- | --- | --- | --- | --- | --- | ---------------- | --- | --- |
delidentificadordelcommit.Unavezqueyahemosvuelto
| a ese commit, | podemos  |          | crear | una              | nueva | rama | a     | partir |
| ------------- | -------- | -------- | ----- | ---------------- | ----- | ---- | ----- | ------ |
| de él y       | volver a | trabajar | en    | la funcionalidad |       |      | de la | rama   |
eliminada.
| Sin embargo, | es          | importante |           | tener      |             | en cuenta  | que,       | al    |
| ------------ | ----------- | ---------- | --------- | ---------- | ----------- | ---------- | ---------- | ----- |
| realizar     | un merge,   | todos      | los       | commits    |             | de la      | rama       | que   |
| eliminaremos | a           | futuro     | se        | integrarán |             | en la      | otra rama. |       |
| Debemos      | asegurarnos |            | de        | que        | los cambios |            | realizados |       |
| en la rama   | que         | será       | eliminada |            | son         | necesarios | y          | están |

133
listos para ser reintegrados antes de realizar el merge y
elfuturoborrado.
Conclusión
Una vez que se ha completado el trabajo en una rama,
se puede eliminar, y recuperar a futuro, si es necesario.
Debemos tender a eliminar ramas siempre que ya no
sirvan de utilidad o se haya reintegrado su contenido en
otrarama.
Curso
Lección22:mouredev.com/git-github-221
Inicio:02:16:26|Duración:00:06:01
Ahora que ya hemos finalizado nuestra supuesta
funcionalidad de login, la rama en la que se trabajó ella
ya no aporta nada. Hemos implementado y reintegrado
el login en la rama main del proyecto, por lo que
es el momento de eliminar la rama login. Para ello,
utilizaremoselcomandogit branch --delete logino
git branch -d login.
Alejecutarelcomando,nosindicaráquelaramahasido
eliminada.
Las ramas, en general, representan trabajos temporales
que en algún momento se fusionarán con otra rama
y serán eliminadas. Al verificar con git branch,
observaremosquesoloquedalaramamain.
1https://mouredev.com/git-github-22

134
Aunque la rama se ha eliminado, aún podemos acceder
a sus commits usando git reflog. Este comando nos
muestra los commits, resets y otros movimientos que
hemos hecho en el repositorio. Si queremos movernos a
uncommitespecífico,usamosgit checkout,seguidodel
identificadordelcommit(hash).Alverificarcongit tree,
observamos que, aunque la rama se ha eliminado, sigue
existiendounareferenciaaella.
Resumiendo, al borrar una rama, la eliminamos de los
logs principales y del flujo de nuestro proyecto, pero los
commitsseguiránexistiendo.Podemosrecuperarlarama
si es necesario, volver a trabajar en ella, y realizar de
nuevo un merge. Recordemos que, al realizar un merge,
todos los commits de la rama eliminada se integrarán en
laramaseleccionada.
Siintentamosusargit switchparamovernosalarama
eliminada,Gitnosindicaráqueesunareferenciainválida.
Aunque oficialmente no existe, extraoficialmente aún
podemos acceder a ella mediante los identificadores
asociadosasucommits.
ElconceptoderamaesfundamentalenGit.Deunarama
pueden salir más, pudiéndose crear y reintegrar desde
diferentespuntos.

GITHUB
SeccióndedicadaalestudiodeGitHub
135

| Capítulo |     | 23: |     | Introducción |     |     |     |
| -------- | --- | --- | --- | ------------ | --- | --- | --- |
a GitHub
Conceptos
Introducción
| Hemos      | finalizado | la       | primera | parte   | del  | libro, dedicada |         |
| ---------- | ---------- | -------- | ------- | ------- | ---- | --------------- | ------- |
| únicamente | a          | Git, por | lo      | que nos | toca | dar el          | salto a |
GitHub.
| Si    | aún no       | tienes | una cuenta |             | en la plataforma, |           |     |
| ----- | ------------ | ------ | ---------- | ----------- | ----------------- | --------- | --- |
| te    | pediré,      | antes  | de nada,   | que         | te registres      |           | de  |
| forma | gratuita     |        | en la      | plataforma, | accediendo        |           |     |
| a     | github.com1. |        | Para       | hacerlo,    | solo              | necesitas |     |
proporcionaruncorreoelectrónico,unnombre
deusuarioyunacontraseña.
| De nuevo, | volvamos |            | a aclararlo. |            | Git y GitHub | no         | son lo |
| --------- | -------- | ---------- | ------------ | ---------- | ------------ | ---------- | ------ |
| mismo.    | Git es   | un sistema |              | de control | de           | versiones, | una    |
herramientaquenospermitetrabajarconnuestrocódigo
demanerasegura.
Noobstante,yhastaelmomento,conGithemosestado
| trabajando | de manera   |       | local. | Todo     | el flujo | de trabajo | que     |
| ---------- | ----------- | ----- | ------ | -------- | -------- | ---------- | ------- |
| hemos      | visto hasta | ahora | lo     | llevamos | a cabo   | en         | nuestra |
propiamáquina.
1https://github.com
136

137
GitHub
GitHub es una plataforma de alojamiento de código que
utilizaelsistemadecontroldeversionesGit.GitHubnos
permite subir nuestro código a un servidor remoto para
que otras personas puedan interactuar con él. En otras
palabras, GitHub nos facilita compartir nuestro código
con más gente y mantener el versionado de este gracias
aGit.
Con GitHub, varios colaboradores pueden trabajar en
el mismo proyecto, encargándose Git de controlar el
versionado de los archivos y las contribuciones de cada
uno de los colaboradores. Además, GitHub proporciona
herramientas adicionales, como seguimiento de
problemas, integración continua, revisión de código
ycolaboraciónenproyectosdecódigoabierto.
Cabe destacar que existen más plataformas similares,
incluso la posibilidad de montar tu propio servidor
remoto de Git, eso sí, GitHub es, con diferencia, la
plataformamásutilizada,gozandodemuybuenasalude
incrementandosunúmerodeusuariosyfuncionalidades
cada día (actualmente cuenta con más de 100 millones
deusuariosregistrados).
Hablaremos de colaboración, aunque GitHub también
será una herramienta igual de importante en proyectos
individuales,yaquenosaportalaposibilidaddetrabajar
enlanube,entreotrosmuchosbeneficios.
Pertenezcamos a un equipo, o desarrollemos de forma
individual,GitHubpuedeayudarnos.

138
Características
GitHubesunaplataformaricaencaracterísticasquenos
| permite gestionar |     | nuestros |     | proyectos |     | de una | manera |     |
| ----------------- | --- | -------- | --- | --------- | --- | ------ | ------ | --- |
eficiente.Algunasdelascaracterísticasmásimportantes
deGitHubincluyen:
| • Repositorios: |           | en GitHub |         | podemos   | crear  |        | repositorios |       |
| --------------- | --------- | --------- | ------- | --------- | ------ | ------ | ------------ | ----- |
| para            | almacenar |           | nuestro | código.   |        | Los    | repositorios |       |
| pueden          | ser       | públicos  | o       | privados, |        | lo que | significa    |       |
| que podemos     |           | compartir |         | nuestro   | código |        | con          | otros |
colaboradoresomantenerloprotegido.
| • Control     | de  | versiones:   |     | GitHub   | utiliza |          | el sistema |     |
| ------------- | --- | ------------ | --- | -------- | ------- | -------- | ---------- | --- |
| de control    |     | de versiones |     | Git      | para    | mantener |            | el  |
| versionado    |     | de nuestro   |     | código,  | y       | para     | que varios |     |
| colaboradores |     | puedan       |     | trabajar |         | en       | el mismo   |     |
proyecto.
| • Integración   |     | continua: | GitHub         |     | nos      | permite | integrar  |     |
| --------------- | --- | --------- | -------------- | --- | -------- | ------- | --------- | --- |
| la construcción |     | y         | las pruebas    |     | de       | nuestro | código    |     |
| directamente    |     | en        | la plataforma, |     | lo       | que nos | ayuda     | a   |
| mantener        |     | un flujo  | de trabajo     |     | continuo | y       | una mayor |     |
calidadennuestrocódigo.
| • Revisión | de  | código: | GitHub |     | nos permite |     | revisar | y   |
| ---------- | --- | ------- | ------ | --- | ----------- | --- | ------- | --- |
comentarelcódigodenuestroscolaboradorespara
mejorarlacalidaddelproyecto.
| • Seguimiento |     | de          | problemas: |               | GitHub    | nos | permite   |     |
| ------------- | --- | ----------- | ---------- | ------------- | --------- | --- | --------- | --- |
| realizar      | un  | seguimiento |            | de los        | problemas |     | y errores |     |
| de nuestro    |     | código      | y          | solucionarlos |           |     | de manera |     |
eficiente.
| • Colaboración |     | en             | proyectos |     | de      | código    | abierto: |        |
| -------------- | --- | -------------- | --------- | --- | ------- | --------- | -------- | ------ |
| GitHub         | es  | una plataforma |           | muy | popular |           | para     | alojar |
| proyectos      |     | de código      | abierto,  |     | lo que  | significa |          | que    |
cualquierapuedecontribuirasuevolución.
Yestassonsoloalgunasdesuscaracterísticas.

139
Beneficios
GitHub nos proporciona numerosos beneficios al
trabajarmaneraremota.Algunosdelosmásimportantes
incluyen:
• Colaboración: GitHub nos permite colaborar
con otros programadores de todo el mundo en
proyectosdecódigoabiertoyproyectosprivados.
• Control de versiones: Git y GitHub nos permiten
mantener un control de versiones efectivo de
nuestro código, lo que nos ayuda a evitar errores y
conflictos.
• Eficiencia: GitHub nos proporciona herramientas
para mejorar nuestra eficiencia, como integración
continua, seguimiento de problemas y revisión de
código.
• Almacenamiento en la nube: GitHub nos permite
almacenar nuestro código en la nube, lo que
significaquepodemosaccederaéldesdecualquier
lugarodispositivo.
• Comunidad: GitHub tiene una gran comunidad de
programadores que contribuyen y comparten su
conocimientoadiario.
Conclusión
GitHub es una plataforma muy útil para personas
que se dedican al desarrollo de software y quieren
trabajarenequipooindividualmentedemaneraremota.
Proporciona una gran cantidad de herramientas para
mejorar la eficiencia y la calidad de nuestro código,
así como para colaborar con más gente. Además,

140
| su comunidad |     | es una | de las más        | activas |          | del sector. |      |
| ------------ | --- | ------ | ----------------- | ------- | -------- | ----------- | ---- |
| En general,  |     | GitHub | es una plataforma |         | esencial |             | para |
cualquierprogramadorquequieratrabajarenproyectos
desoftwaredemaneraefectiva.
Curso
Lección23:mouredev.com/git-github-232
Inicio:02:22:27|Duración:00:05:26
| Por fin       | ha    | llegado     | el momento.   | Comienza |        | la          | parte |
| ------------- | ----- | ----------- | ------------- | -------- | ------ | ----------- | ----- |
| de GitHub.    |       | Para llevar | a cabo        | esta     | parte  | del         | curso |
| debemos       | tener | creada      | una cuenta    | en       | la     | plataforma. |       |
| Para hacerlo, |       | solo        | necesitaremos | un       | email, | nombre      |       |
| de usuario    | y     | contraseña. | Visitemos     | la       | web    | principal   | de    |
GitHub(github.com3)yechémosleunvistazo,yaquenos
proporcionaunresumenmuyclarodelascaracterísticas
principalesdeGitHub.
2https://mouredev.com/git-github-23
3https://github.com

141
Yalohemoscomentado,perodebemosaclarardenuevo
que Git y GitHub no son lo mismo. Git es un sistema de
control de versiones, una herramienta que nos permite
trabajar con nuestro código de manera segura. Hasta
ahora, hemos visto cómo guardar versiones, tomar
fotografías del código, desplazarnos por ramas y muchas
cosasmás.EsoeraGit,elsistemadeversionado.
Noobstante,yhastaelmomento,conGithemosestado
trabajando de manera local. Todo el flujo de trabajo que
hemosvistohastaahorasehallevadoacaboennuestro
propioordenador.
Dichoesto,¿quéesGitHub?GitHubesunaplataformaque
emplea Git en la nube, de forma remota. Nos permite
subirnuestrocódigoaunservidorremotoparaqueotras
personas (o nosotros mismos) puedan interactuar con
él desde cualquier parte. Nos facilita compartir nuestro
códigoconmásgente,ymantenersuversionadogracias
a Git. Dicho de otra forma, con GitHub como punto de
encuentro,podemostrabajarenequipodeformaremota
y guardar copias de seguridad de nuestros proyectos de
software.

142
Esto implica que podemos tener nuestro proyecto en
localtrabajandoconGit,yqueotrapersona,encualquier
partedelmundo,puedaestarhaciendolomismo.
Podremos trabajar individualmente, y, en algún
momento, subir nuestro código a GitHub, donde se
unirán todos los cambios realizados por todos los
colaboradores. Además, si nuestro ordenador falla, no
hay problema, ya que todo está guardado en GitHub,
juntoalhistorialcompletodecambios.
No debemos de preocuparnos si aún no entendemos
perfectamente el concepto. A medida que avancemos
en las lecciones, iremos comprendiendo mejor qué es
GitHubyporquésediferenciadeGit.
Una vez que tengamos creada la cuenta
de usuario, quiero compartir un resumen
de los comandos más importantes de Git.
No son todos, pero sí los que usamos
fundamentalmente a diario, y, por lo
tanto, los que hemos estado viendo
en este curso. Los encontraremos en
training.github.com/downloads/es_ES/github-
git-cheat-sheet4.
4https://training.github.com/downloads/es_ES/github-git-cheat-sheet

143
| En las siguientes |           | clases        | nos enfocaremos |      | en la    |
| ----------------- | --------- | ------------- | --------------- | ---- | -------- |
| característica    | principal | de GitHub:    | sincronizar     |      | cambios. |
| Esto significa    | no        | solo trabajar | en local,       | sino | también  |
interactuarconrepositoriosdeGitHubdemaneraremota.

Capítulo 24: Primeros
pasos
Conceptos
Introducción
¿YahascreadotucuentadeusuarioenGitHub?
Esmomentodeadentrarnosenelmundodela
colaboraciónenproyectosdesoftware.
En GitHub, los proyectos se guardan en repositorios, un
concepto que ya conocemos. Un repositorio es un lugar
donde se almacena todo el código de un proyecto, así
como los archivos asociados a este. Podemos crear un
repositorio paracualquierproyecto,yaseapersonal ode
equipo.
Público y privado
Los repositorios en GitHub pueden ser públicos o
privados. Si creamos un repositorio público, cualquier
persona podrá ver nuestro código y solicitar colaborar
en él. Por el contrario, si creamos un repositorio privado,
solo las personas que autoricemos podrán acceder al
proyecto.
144

145
Curso
Lección24:mouredev.com/git-github-241
Inicio:02:27:53|Duración:00:02:46
YatenemosnuestranuevacuentadeusuarioenGitHub.
| Empezaremos |     | por | entender | diferentes |     | conceptos, | ya  |
| ----------- | --- | --- | -------- | ---------- | --- | ---------- | --- |
que,talveznotengamosclaropordondeempezar.
| No nos        | preocupemos |                    | por            | ello,      | por        | suerte, | GitHub    |
| ------------- | ----------- | ------------------ | -------------- | ---------- | ---------- | ------- | --------- |
| cuenta        | con         | muchísima          | documentación. |            |            | Una     | de las    |
| mejores       | en          | el ámbito          | del            | desarrollo |            | de      | software. |
| Sinceramente, |             | existe             | documentación  |            |            | para    | casi todo |
| en español.   |             | Allí encontraremos |                |            | respuestas | a       | cualquier |
pregunta.Además,ladocumentacióndeGitHubtambién
| es una   | excelente |           | fuente | de   | información   |     | sobre Git. |
| -------- | --------- | --------- | ------ | ---- | ------------- | --- | ---------- |
| Podremos |           | encontrar | toda   | esta | documentación |     | en         |
docs.github.com/es2.
1https://mouredev.com/git-github-24
2https://docs.github.com/es

146
| Comencemos |               | hablando | del | concepto  | repositorio |     | en  |
| ---------- | ------------- | -------- | --- | --------- | ----------- | --- | --- |
| GitHub,    | el directorio | donde    |     | se guarda | un proyecto |     | que |
empleaGit.Igualquehastaelmomento,peroenlanube.
Unrepositorioesunespacioreservadoennuestracuenta
| de GitHub | para | trabajar | con | el código | de un | proyecto. |     |
| --------- | ---- | -------- | --- | --------- | ----- | --------- | --- |
Losrepositoriospuedenserpúblicosoprivados.
| Sin    | más, | si entras | en  | github.com/mouredev3, |                |     |     |
| ------ | ---- | --------- | --- | --------------------- | -------------- | --- | --- |
| podrás | ver  | todos     | mis | proyectos             | (repositorios) |     |     |
asociadosaesacuentadeGitHub.
| Lo primero     | que | nos                                     | puede  | llamar    | la atención, | es  | que,  |
| -------------- | --- | --------------------------------------- | ------ | --------- | ------------ | --- | ----- |
| al entrar      | en  | https://github.com/<nombre_de_usuario>, |        |           |              |     |       |
| visualizaremos |     | nuestra                                 | página | principal | de usuario   |     | de la |
plataforma.
3https://github.com/mouredev

Capítulo 25: Repositorio
personal
Conceptos
Introducción
GitHub es una plataforma de alojamiento de
código fuente y control de versiones basada en
Git. La plataforma está diseñada para ayudar a los
desarrolladores a colaborar en proyectos y mantener
un historial de cambios en el código fuente. En este
capítulo, aprenderemos a crear nuestro repositorio
personal en GitHub desde cero, algo así como nuestra
páginaprincipaldeusuariodentrodelaplataforma.
Usuario y perfil
Lo primero que debemos hacer es crear un usuario en
GitHub. Hecho esto, iniciamos sesión y nos vamos
directamente a nuestro perfil. Cuando entremos
en él por primera vez, observaremos que está en
blanco. Podemos agregar una foto, editar los diferentes
datos y, en resumen, hacerlo un poco más atractivo.
Recordemos que GitHub es una especie de red social
paradesarrolladores.
Para personalizar nuestro perfil, completemos los datos
que consideremos relevantes. La página principal de
147

148
nuestroGitHubserálaquemuestreinformacióngeneral
y proyectos. En la plataforma, tendremos la sección
Overview para visualizar la información general del
usuario,Repositoriesparacrearygestionarrepositorios,y
opcionescomoProjects,Packages,yStars,entreotras.Las
Stars nos sirven para seleccionar como favoritos otros
repositorios.
Exploración
Enlapartesuperiordelapáginadeinicio,encontraremos
accesos a Issues, Codespaces, Marketplace, Explore y Pull
Requests. Básicamente, en GitHub podemos trabajar
con nuestro usuario y proyectos, o explorar el trabajo
de otros usuarios. Para explorar, vayamos a la sección
Explore en la parte superior. Allí veremos distintos
proyectos destacados o tendencias. Por ejemplo, si
vamosaTrending,encontraremosrepositoriospopulares.
Repositorios
Cada repositorio tiene asociada una URL que
habitualmenteposeeesteformato:https://github.com/<nombre_-
usuario__u_organizacion>/<nombre_repositorio>. Como ya
hemosdicho,elrepositorioesellugardondesealmacena
ygestionatodoelcódigodeunproyecto.
El archivo README.md es un elemento muy importante
en todos los repositorios. Este archivo sirve como
documentación del proyecto, para que no solo
visualicemos directorios y archivos con código.
Principalmente nos ayuda a que no sea necesario
leer todo el código para entender el propósito del

149
proyecto. Dicho de otra forma, hace referencia a la
documentacióndelproyecto.
Creación repositorio
AhoraqueyaconocemoslaestructurabásicadeGitHub,
es hora de crear nuestro propio repositorio personal en
la plataforma. Este repositorio especial permitirá definir
nuestra página de inicio personal en GitHub. Para crear
este tipo de repositorio, debemos darle un nombre y
unadescripción,y,enestecaso,utilizarsiemprenuestro
nombredeusuarioenGitHub.
Este repositorio especial se genera con un archivo
README.md que inicialmente contiene un simple
saludo. El repositorio llamado <nombre_usuario> se
muestra en la página de inicio del perfil de GitHub.
De ahí que este sea un repositorio especial. Visita
https://github.com/<nombre_usuario> para acceder a
nuestrorepositoriopersonalopáginadeinicioenGitHub.
Exploración repositorio
Visualizaremos muchas opciones en la parte superior,
comoIssues(paraincidenciasabiertas),PullRequests(para
recibirpropuestasdecódigoqueseintegraráennuestro
repositorio), Actions, Projects, Wiki, Security y Settings. Lo
másimportanteesqueaquíveremoslalistadearchivos
que hemos subido, e incluso podremos editar su código
directamentedesdelaplataforma.

150
Markdown
Si accedemos al código fuente del README.md, nos
encontraremos con un tipo de escritura de tipo
Markdown, un lenguaje de marcado ligero para dar
formatoaltexto.Markdownesútilparacreardocumentos
legibles y estructurados con facilidad, y muy utilizado en
GitHubparaladocumentacióndeproyectos.
En Markdown, podemos dar formato al texto utilizando
combinaciones de caracteres. Por ejemplo, los
encabezados se representan con almohadilla (#) y el
texto en negrita se representa con dos asteriscos (**).
InclusoGitHubtienesupropiaguíadeMarkdown,donde
se muestra cómo aplicar diferentes formatos al texto,
negrita,cursiva,listasyotrasopciones.
Además de los formatos básicos de texto, en Markdown
también podemos agregar código HTML, lo que significa
que tendremos la posibilidad de combinar diferentes
elementos para dar formato al texto, como imágenes,
enlaces y tablas. Aprender a usar Markdown es simple.
Esencial para crear documentos bien estructurados y
legiblesdemanerafácilyrápida.
Conclusión
Hemos aprendido a crear nuestro repositorio personal
en GitHub, nuestra página principal en la plataforma.
También hemos visto cómo personalizarlo, así como la
importanciadetenerpresentelosarchivosdeMarkdown
paradocumentarnuestroproyecto.
Ahora que hemos creado nuestro repositorio personal,
es importante que sigamos aprendiendo a crear y

151
| administrar | nuestros        | repositorios, |      | cómo     | contribuir |         | en  |
| ----------- | --------------- | ------------- | ---- | -------- | ---------- | ------- | --- |
| ellos,      | y cómo utilizar | Git           | para | mantener | un         | control | de  |
versionesycambiosdemaneraremota.Estonosayudará
asacarelmáximoprovechoaestaplataforma.
Curso
Lección25:mouredev.com/git-github-251
Inicio:02:30:39|Duración:00:12:01
| Vamos | a crear nuestro |     | repositorio | personal |     | en GitHub |     |
| ----- | --------------- | --- | ----------- | -------- | --- | --------- | --- |
desdecero.Nuestrapáginaprincipal.
| Una vez | que tengamos |       | una cuenta | en          | la   | plataforma, |     |
| ------- | ------------ | ----- | ---------- | ----------- | ---- | ----------- | --- |
| GitHub  | nos sugerirá | crear | un         | repositorio | como | primer      |     |
paso.
| En nuestro | caso,             | lo primero |           | que podemos |             | hacer | es  |
| ---------- | ----------------- | ---------- | --------- | ----------- | ----------- | ----- | --- |
| agregar    | una foto, editar  |            | el perfil | y volverlo  | un          | poco  | más |
| atractivo. | Para personalizar |            | nuestro   | perfil,     | completemos |       |     |
| los datos  | que consideremos  |            |           | relevantes. | Recordemos, |       |     |
GitHubesnuestraredsocial.
| La página | principal   | de  | nuestro   | GitHub  |     | será la   | que |
| --------- | ----------- | --- | --------- | ------- | --- | --------- | --- |
| muestre   | información | y   | proyectos | a otros |     | usuarios. | A   |
continuación,vamoscomenzaraentenderquéesGitHub
yquécosaspodremoshacerdesdelaplataforma.
1https://mouredev.com/git-github-25

152
| Busquemos | la  | sección | Overview |     | para | visualizar | nuestro |     |
| --------- | --- | ------- | -------- | --- | ---- | ---------- | ------- | --- |
usuario,Repositoriesparacrearygestionarrepositorios,y
tambiénotrasopcionescomoProjects,PackagesoStars.
| En la parte | superior, |     | encontraremos |     |     | accesos | a   | Issues, |
| ----------- | --------- | --- | ------------- | --- | --- | ------- | --- | ------- |
Codespaces,Marketplace,ExploreyPullRequests.Veremos
algunadeestasopcionesmásadelante.Básicamente,en
GitHubpodemostrabajarconnuestrousuario,oexplorar
losproyectosdeotrosmiembrosdelaplataforma.
| Para comenzar |            | a     | explorar, | dirijámonos |      |         | a la sección |     |
| ------------- | ---------- | ----- | --------- | ----------- | ---- | ------- | ------------ | --- |
| Explore       | en la      | parte | superior. |             | Allí | veremos | distintos    |     |
| proyectos     | destacados |       | o         | tendencias. |      | Por     | ejemplo,     | si  |
vamosaTrending,encontraremosrepositoriospopulares.
| Hablamos              | de        | proyectos |             | muy       | conocidos, |             | por ejemplo, |     |
| --------------------- | --------- | --------- | ----------- | --------- | ---------- | ----------- | ------------ | --- |
| Visual Studio         |           | Code,     | el editor   | de        | código     | es          | un proyecto  |     |
| open source,          |           | y todo    | su código   |           | fuente     | está        | disponible   |     |
| en su correspondiente |           |           | repositorio |           | en         | GitHub.     | Gracias      | a   |
| esto, la              | comunidad |           | puede       | revisarlo | e          | interactuar | con          | él, |
| sugiriendo            | cambios,  |           | reportando  |           | errores    | o           | colaborando  |     |
enmejoras.
| Pensemos | en  | la importancia |     | de  | esto, | un repositorio |     | de  |
| -------- | --- | -------------- | --- | --- | ----- | -------------- | --- | --- |
unaherramientaanivelmundialseencuentraalalcance

153
decualquierpersona.Ycomoesteejemplo,existenmiles.
Cada repositorio posee una URL que suele tener
el formato https://github.com/<nombre_usuario__u_-
organizacion>/<nombre_repositorio>. Como ya hemos
dicho, el repositorio es el lugar donde se almacena y
gestionatodoelcódigodeunproyecto.
AhoraqueyaconocemoslaestructurabásicadeGitHub,
vamos a continuar aprendiendo sobre cómo crear y
administrar nuestros repositorios, cómo contribuir a
otros proyectos, y cómo utilizar Git para mantener un
historial de cambios en cada uno de ellos. Esto nos
ayudaráasacarelmáximoprovechodeestaplataforma.
Comencemos por el concepto de repositorio, el
directorio de nuestro proyecto. Sin más, uno de los
repositorios de GitHub es el asociado a este curso:
github.com/mouredev/hello-git2.
Valoremos hacer Star en los repositorios que
deseemos apoyar y añadir a favoritos. Existe
una sección para acceder a ellos rápidamente.
El mecanismo de Star es una manera muy
simple de ayudar, valorando así el trabajo de
laspersonasqueestándetrásdelproyecto.
Un repositorio es como un contenedor, similar a una
carpeta en tu equipo, donde almacenas el código de un
proyectoquetrabajaconGit.Estarepresentaciónexistirá
delamismamaneraenGitHub.
Hablandoderepositorios,unomuyimportanteenGitHub
eselasociadoalapáginaprincipaldenuestroperfil.Sino
hemoscreadouno,lapáginaprincipaldenuestrousuario
estarávacía.
2https://github.com/mouredev/hello-git

154
| El primer | concepto | que            | veremos |          | en profundidad |          |         | estará |
| --------- | -------- | -------------- | ------- | -------- | -------------- | -------- | ------- | ------ |
| asociado  | a crear  | un repositorio |         | para     |                | trabajar | con     | Git y  |
| GitHub.   | Hay dos  | formas         | de      | hacerlo: | Podemos        |          | crear   | un     |
| proyecto  | con Git  | en nuestra     |         | máquina  |                | y luego  | subirlo | a      |
GitHub,ocreardirectamenteunproyectoenGitHubque
| trabaje      | con Git. | Dependiendo    |                | del         | estado  |       | del proyecto,  |     |
| ------------ | -------- | -------------- | -------------- | ----------- | ------- | ----- | -------------- | --- |
| podemos      | crear    | el repositorio |                | en          | GitHub, |       | descargarlo,   |     |
| trabajar     | con él y | sincronizar    |                | los cambios |         | en    | la nube.       | En  |
| este primer  | ejemplo, | aprenderemos   |                |             | a       | crear | un proyecto    |     |
| directamente | en       | GitHub.        | Concretamente, |             |         |       | el repositorio |     |
personalasociadoanuestrousuarioenlaplataforma.
Paracrearestetipoderepositorio,debemosasignarleun
nombre(obligatorio)yunadescripción(opcional).Parael
| nombre     | utilizaremos | exactamente |     |          | el de | nuestro   | usuario |       |
| ---------- | ------------ | ----------- | --- | -------- | ----- | --------- | ------- | ----- |
| de GitHub. | Este         | repositorio |     | especial |       | permitirá |         | crear |
nuestrapáginadeiniciopersonalenGitHub.Porejemplo,
| si entras | en  | https://github.com/<nombre_usuario>, |     |     |     |     |     |     |
| --------- | --- | ------------------------------------ | --- | --- | --- | --- | --- | --- |
accederemosanuestrorepositoriopersonal,opáginade
iniciodentrodeGitHub.
| Al crear | un repositorio, |     | podemos  |     | seleccionar |     | si  | será |
| -------- | --------------- | --- | -------- | --- | ----------- | --- | --- | ---- |
| público  | o privado.      |     | Ya hemos |     | comentado   |     | que | los  |

155
repositoriosprivadossolopuedenservistospormiembros
del equipo con permisos de acceso, mientras que los
públicosseránvisiblesparatodoelmundo.
Un elemento muy importante en todos los repositorios
es el archivo README.md. Este archivo sirve como
documentación del proyecto. Por ejemplo, en el
repositorio de este curso (github.com/mouredev/hello-
git3), el archivo README.md contiene toda la información
sobre él. Es recomendable agregar siempre un archivo
README.md anuestrosrepositorios.
También podemos poseer un archivo README.md en
nuestra máquina local y subirlo a GitHub (ya veremos
cómo),peroesimportantetenerlodealgunaforma.
Tambiénestudiamosensumomentoelarchivo.gitignore,
quepodríamosagregardirectamentedurantelacreación
del repositorio en GitHub, aunque en este caso no lo
haremos. Por último, están las licencias. GitHub es
conocido por alojar código de proyectos de código
abiertoenmuchoscasos.Sinembargo,elcódigoabierto
puede tener asociado reglas para su uso y distribución.
Para ello, existen diferentes licencias de código, una
manera de establecer límites a la hora de utilizar un
proyecto ajeno. Durante la creación del repositorio
tambiénpodemosseleccionaruna.
Tanto el README.md, como el .gitignore o las licencias,
no dejan de ser archivos de nuestro proyecto. Aunque
funciona de manera especial podemos crearlos y
asociarlos al proyecto en cualquier momento, no solo
durantelacreacióndelrepositorioenGitHuboennuestra
máquinalocal.
Como hemos comentado, en este caso no
3https://github.com/mouredev/hello-git

156
seleccionaremos ninguna licencia, ya que es solo un
proyecto de prueba. Al final, simplemente crearemos
el repositorio. Dicho repositorio personal se inicializará
con un archivo README.md que contiene un simple
texto. Ahora que ya hemos finalizado este proceso,
podemos visitar https://github.com/<nombre_usuario>.
Observaremos que la página muestra el contenido del
README.md inicial. Como el repositorio tiene el mismo
nombre que nuestro usuario, se asocia a la página
de inicio del perfil de GitHub. De ahí que este sea un
repositorioespecial ymuyútil.
Ahoraqueyatenemosunrepositorioconnuestronombre
de usuario, podríamos darle una Star para agregarlo a
favoritos. Si vamos a la sección con ese mismo nombre,
apareceránlosrepositoriosseleccionados.
Volvamosalrepositorioycomenzaremosaexplorarloque
GitHub nos muestra. Obviamente, tenemos el archivo
creado README.md, así como otras funcionalidades que
iremos descubriendo a medida que trabajemos con la
plataforma.

157
Dispondremosdemuchasopcionesenlapartesuperior,
comoIssues(paraincidenciasabiertas),PullRequests(para
recibir código que se integrará en nuestro repositorio),
Actions, Projects, Wiki, Security y Settings. Principalmente
visualizaremos la lista de archivos que tiene asociado el
proyecto,pudiendoinclusoeditarlosdirectamentedesde
GitHub.
Si consultamos el código fuente del README.md,
nos encontraremos con un tipo de escritura de tipo
Markdown, un lenguaje de marcado para dar formato
al texto. Le dedicaremos un capítulo a hablar un poco
sobreMarkdown.
Markdown, básicamente, es una forma de escribir texto
enriquecido utilizando combinaciones de caracteres.
Hasta GitHub tiene su propia guía de Markdown, donde
semuestracómoaplicardiferentesformatosaltexto.
En Markdown, también podemos agregar código HTML.
A pesar de ello, aprender a usar Markdown resulta es
bastantesimple.
Si quieres ver cómo creé mi plantilla, puedes
visitar mi repositorio público en GitHub.
Simplementeaccedeagithub.com/mouredev4.
Allí podremos consultar todo el código
Markdown que utilicé para dar formato al
texto. Si te gusta algo, no dudes utilizarlo en
tu perfil. Recuerda que GitHub está diseñado
para colaborar, compartir nuestro código y
aprenderdelosdemás.
4https://github.com/mouredev

158

| Capítulo |     |     | 26: | Local |     | y   |     |
| -------- | --- | --- | --- | ----- | --- | --- | --- |
remoto
Conceptos
Introducción
| En este  | capítulo, |          | vamos | a comenzar |      | a entender  | cómo |
| -------- | --------- | -------- | ----- | ---------- | ---- | ----------- | ---- |
| podremos |           | utilizar | Git y | GitHub     | para | sincronizar | los  |
cambiosenunproyectodesoftware.
Remoto
| Hasta       | el momento, |     | únicamente  |        | hemos | visto            | a Git    |
| ----------- | ----------- | --- | ----------- | ------ | ----- | ---------------- | -------- |
| funcionando |             | en  | un entorno  | local, |       | pero, ¿y         | si ahora |
| queremos    | integrarlo  |     | con GitHub? |        | Para  | ello necesitamos |          |
queelproyectoseencuentreunservidorremoto.Deesta
| forma | siempre | trabajaremos |     | sincronizados |     | con | un punto |
| ----- | ------- | ------------ | --- | ------------- | --- | --- | -------- |
deunión,GitHub.
Interacción
| Una vez          | el  | proyecto | se         | encuentre |               | sincronizado | con       |
| ---------------- | --- | -------- | ---------- | --------- | ------------- | ------------ | --------- |
| GitHub,          | y   | varias   | personas   |           | trabajen      | en           | él, este  |
| nos informará    |     |          | de todas   | las       | interacciones |              | de los    |
| desarrolladores  |     | con      | el sistema |           | de control    | de           | versiones |
| y la plataforma. |     |          | Tendremos  | que       | poner         | nuestro      | código    |
159

160
en común y sincronizar los cambios para continuar
desarrollandoeinteractuandoconGit.
GitHub será el punto de unión entre ambos
desarrolladores. Como todo está en el servidor, no
hay problemas. Esto es para lo que existe GitHub: para
trabajar de forma colaborativa y disponer del código del
proyectocuandoydondequeramos.
Local
Tanto si trabajamos de manera individual como en
equipo, GitHub nos aporta mucho valor. Eso sí, nuestro
flujo de trabajo en local con Git seguirá siendo muy
parecido al que ya conocíamos antes de comenzar a
hablardeGitHub.
Curso
Lección26:mouredev.com/git-github-261
Inicio:02:42:40|Duración:00:04:52
Vamos a aprender a sincronizar los cambios en Git con
GitHub. Para ello, lo primero que tenemos que hacer es
configurar la integración de nuestro proyecto de Git con
GitHub.
En la terminal, abrimos el proyecto con el que hemos
estado trabajando en Git durante el curso, recordemos
que se llamaba Hello Git. Entraremos al proyecto y
ejecutaremos git log. Recordemos que, a lo largo del
curso, hemos estado haciendo diferentes acciones con
1https://mouredev.com/git-github-26

161
nuestro código relacionadas con el flujo de trabajo con
Git.
En este proyecto hemos trabajando con diferentes
archivos, siempre ayudados por el sistema de control
de versiones Git, para así permitirnos rastrear todo el
recorridoyevolucióndelcódigoatravésdeltiempo.
Pero, ¿qué pasa si ahora queremos trabajar con él
desde GitHub? Por ejemplo, si queremos que otra
persona participe en este proyecto, o que nosotros
mismos tengamos diferentes ordenadores desde los
que realicemos el proceso de desarrollo. Para ello,
necesitamos subir nuestro proyecto a un servidor
común, para poder seguir utilizando Git en local y
sincronizar loscambiosdesdeunúnicopuntodeunión.
Imaginemosquenosencontramostúyyotrabajandoen
un mismo proyecto. ¿Cuál sería la forma de interactuar?
No podemos simplemente pasarnos un .zip con lo que
hemos hecho, como decíamos al principio del curso.
Necesitamos trabajar con una única fuente de verdad.
¿Cuál será el punto común? Ya sabemos la respuesta,
GitHub.
Sigamos con el supuesto. Yo me encuentro trabajando
en el proyecto, y subo el código a GitHub. Tú quieres
hacer lo mismo. En el momento que lo intentes, GitHub
te informará que yo ya había subido algún cambio a
nuestro proyecto común. Sabido esto, tendremos que
poner nuestro código en común, sincronizando ambos
cambios.
Una vez que nos hemos puesto de acuerdo, y subido
todos los cambios a la plataforma, yo podría indicarle a
GitHubquedescarguedenuevoelcontenidoactualizado
del proyecto en local, en los que se incluye también tu

162
código.Lomismoentucaso.Así,GitHubseráelpuntode
unión entre ambos desarrolladores. Como todo está en
el servidor, no hay problemas.Esto es para lo que existe
GitHub: para trabajar de forma colaborativa y disponer
delcódigobajocualquiercircunstancia.
Pero,¿quépasasitrabajamosdemaneraindividualenlugar
de en equipo? GitHub será igual de potente. Imaginemos
que por error borramos parte del código, incluso que
afectamos a nuestro control de versiones local con Git.
No pasa nada, ya que hemos estado sincronizando
nuestro código con el servidor remoto. Por otra parte,
si cambiamos de máquina, o tenemos más de una,
simplemente descargamos el contenido del repositorio y
continuamosdesarrollando.
Muy simplificado, en GitHub, lo único que existe es otro
ordenador en el que vamos dejando nuestro código
como punto de reunión. Todo ese historial de Git que
hemos visto hasta ahora también lo tendremos en
GitHub, permitiéndonos trabajar de forma colaborativa
o individual, pero siempre de manera sincronizada y
segura.

Capítulo 27:
Autenticación SSH
Conceptos
Introducción
Vamos a aprender cómo conectar nuestro equipo a
GitHub mediante el uso de SSH. SSH es un protocolo de
autenticación basado en una clave privada y una clave
pública que permite mantener conexiones seguras de
entredossistemas,enestecaso,desdenuestramáquina
local yelservidorremotodeGitHub.
Autenticación
Una vez que tenemos un repositorio creado en GitHub,
el siguiente paso es autenticar nuestro equipo dentro
la plataforma. Para hacerlo, tendremos que utilizar la
terminalyseguirlasinstruccionesindicadasenlasección
de la documentación de GitHub destinada al proceso de
autenticación. Esta sección nos mostrará cómo conectar
nuestro equipo a GitHub, y cómo agregar un factor de
doble autenticación para proteger nuestra cuenta de
usuario,entremuchasotrascosas.
En este capítulo es muy importante que
tengamos en cuenta la documentación
163

164
| oficial |      | de  | GitHub   | sobre    | autenticación, |     |        |     |
| ------- | ---- | --- | -------- | -------- | -------------- | --- | ------ | --- |
| así     | como | el  | apartado | dedicado |                | al  | curso: |     |
docs.github.com/es/authentication1
Generación
ParaconectarnosaGitHubmedianteSSH,tendremosque
configurarlaclaveprivadaypúblicaennuestroequipo.La
| documentación |       | de   | GitHub  | nos  | proporciona |     | información |     |
| ------------- | ----- | ---- | ------- | ---- | ----------- | --- | ----------- | --- |
| detallada     | sobre | cómo | hacerlo | paso | a paso.     |     | En resumen, |     |
necesitaremosgenerarunanuevaclaveSSH,ylograrque
elsistemalasreconozca.
Documentación:docs.github.com/es/authentication2
Incorporación
| Una vez          | que             | tenemos    | la      | clave SSH           | generada |           | en nuestro    |     |
| ---------------- | --------------- | ---------- | ------- | ------------------- | -------- | --------- | ------------- | --- |
| equipo,          | el siguiente    |            | paso    | es agregarla        |          | a nuestra | cuenta        |     |
| de usuario       | en              | GitHub.    | Esto    | permitirá           |          | que       | el equipo     | se  |
| autentique       | automáticamente |            |         | dentro              |          | de la     | plataforma    |     |
| cada vez         | que             | accedemos  |         | a los repositorios. |          |           | Para hacerlo, |     |
| tendremos        | que             | dirigirnos |         | a la sección        |          | SSH y     | GPG keys,     | en  |
| la configuración |                 | de         | nuestra | cuenta              | de       | usuario,  | y agregar     |     |
laclavepúblicageneradapreviamente.
Documentación:docs.github.com/es/authentication3
1https://docs.github.com/es/authentication
2https://docs.github.com/es/authentication/connecting-to-github-with-
ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent
3https://docs.github.com/es/authentication/connecting-to-github-with-
ssh/adding-a-new-ssh-key-to-your-github-account

165
Verificación
Una vez hayamos seguido todos los pasos anteriores,
podremos comprobar si nuestro equipo está
correctamente conectado a GitHub mediante SSH.
Denuevo,recordarqueladocumentacióndeGitHubnos
proporciona información detallada sobre cómo hacerlo.
Una vez que se haya establecido la conexión, podremos
empezar a trabajar con nuestros repositorios de GitHub
desdeelequipolocal.
Documentación:docs.github.com/es/authentication4
Conclusión
Conectar nuestro equipo a GitHub mediante SSH
puede parecer un proceso complejo, pero siguiendo
las instrucciones detalladas en la documentación
oficial podremos hacerlo fácilmente. Una vez se haya
establecido la conexión, comenzaremos a utilizar todas
lasherramientasdecolaboraciónqueofreceGitHub.
Curso
Lección27:mouredev.com/git-github-275
Inicio:02:47:32|Duración:00:13:13
Nos seguimos aproximando a la gran pregunta, ¿cómo
hacemos para subir nuestro código e interactuar con
4https://docs.github.com/es/authentication/connecting-to-github-with-
ssh/testing-your-ssh-connection
5https://mouredev.com/git-github-27

166
GitHub? Aquí es donde se complica un poco el proceso,
pero, no nos preocupemos, las guías de GitHub son
geniales.
Sivamosalaseccióndeiniciorápido(docs.github.com/es/get-
started/quickstart6), se nos explicará cómo dar nuestros
primeros pasos en la plataforma. Veremos muchas
opciones para crear repositorios, configurar Git, bifurcar,
administrararchivos,etc.
Enestalección,lomásimportanteesbuscarinformación
sobre cómo conectar nuestro equipo y autenticarlo en
GitHub. Este proceso se hará principalmente desde la
terminal,aunquetambiénexistenherramientasgráficas.
Porahora,nosseguiremosenfocandoenlaconsola.
Existe una sección sobre autenticación con GitHub
(docs.github.com/es/authentication7),quenosayudaráa
entender perfectamente cómo conectar nuestro equipo
a GitHub. Existen diferentes maneras de conseguirlo,
pero conectarnos mediante SSH es una de las mejores
opciones. También es muy recomendable agregar un
factor de doble autenticación a nuestra cuenta de
GitHub.
6https://docs.github.com/es/get-started/quickstart
7https://docs.github.com/es/authentication

167
| Sigamos.      | Nos     | enfocaremos      |       | en        | la seguridad, | y           | en          | cómo  |
| ------------- | ------- | ---------------- | ----- | --------- | ------------- | ----------- | ----------- | ----- |
| configurar    | nuestra |                  | clave | SSH.      | SSH es un     | protocolo   |             | de    |
| autenticación |         | basado           | en    | una clave | privada       | y           | una         | clave |
| pública.      | No      | nos preocupemos, |       |           | seguiremos    |             | el tutorial |       |
| de GitHub     | paso    | a                | paso. | De alguna | forma,        | necesitamos |             |       |
establecerunascredencialesqueGitHubreconozca,para
que,cuandointentemosinteractuarconéldesdenuestro
| ordenador,    | determine |            | que | somos | usuarios        | autorizados |     |       |
| ------------- | --------- | ---------- | --- | ----- | --------------- | ----------- | --- | ----- |
| y autorizados |           | a realizar |     | según | qué operaciones |             |     | en la |
plataformaysusrepositorios.
| Pero    | antes, | exploremos |     | más     | sobre |          | SSH.    | Lo  |
| ------- | ------ | ---------- | --- | ------- | ----- | -------- | ------- | --- |
| primero | que    | nos        |     | sugiere | el    | tutorial | oficial |     |
(docs.github.com/es/authentication8),
|            |            |           |            |         | sección            |            | Conexión |     |
| ---------- | ---------- | --------- | ---------- | ------- | ------------------ | ---------- | -------- | --- |
| con SSH,   | es         | verificar | si ya      | tenemos | claves             | SSH        | creadas  |     |
| en nuestro |            | equipo,   | y nos      | muestra | cómo               | listarlas. |          | Si  |
| buscamos   | en         | la raíz   | de nuestro |         | disco, dependiendo |            |          | del |
| sistema    | operativo, |           | deberíamos |         | encontrar          | una        | carpeta  |     |
ocultallamada.ssh.
Nosubicaremoseneldirectoriodondedeberíanestarlas
| claves SSH, | aunque |     | se pueden | crear | en cualquier |     | lugar | y   |
| ----------- | ------ | --- | --------- | ----- | ------------ | --- | ----- | --- |
8https://docs.github.com/es/authentication

168
moverlasaotrositioespecífico.Apesardeello,labuena
prácticasuelesertenerlasenundirectoriollamado.ssh.
| Puede     | que ya        | tengamos | alguna      |            | clave SSH   | si            | esta se |
| --------- | ------------- | -------- | ----------- | ---------- | ----------- | ------------- | ------- |
| ha creado | por           | defecto  | en          | el equipo, | o           | si ya         | hemos   |
| pasado    | anteriormente |          | por este    | proceso,   | de          | lo contrario, |         |
| podemos   | generar       |          | dicha clave |            | SSH desde   | cero.         | Esto    |
| último,   | básicamente   |          | consiste    |            | en ejecutar |               | ciertos |
comandos.
| Revisa      |     | el primer | comando  |         | nos        | dice    | el  |
| ----------- | --- | --------- | -------- | ------- | ---------- | ------- | --- |
| tutorial    |     | oficial.  | Recuerda |         | que        | existen |     |
| variaciones |     | según     | el       | sistema | operativo: |         |     |
docs.github.com/es/authentication/connecting-
to-github-with-ssh/generating-a-new-ssh-key-
| and-adding-it-to-the-ssh-agent9. |     |                 |     |     | De  | nuevo,    | te  |
| -------------------------------- | --- | --------------- | --- | --- | --- | --------- | --- |
| recomiendo                       |     | encarecidamente |     |     | que | para este |     |
procesosigaslaguíaoficial,yaquecontarácon
muchamásinformaciónpasoapaso.
Senosindicaráquelancemosuncomandoyagreguemos
nuestrocorreoelectrónico.Alfinalizartodasuejecución,
| nos informará |     | de que | se ha | creado | una | clave pública | y   |
| ------------- | --- | ------ | ----- | ------ | --- | ------------- | --- |
unaclaveprivada.
| Durante    | el proceso, |         | la guía    | oficial | nos     | pedirá      | que    |
| ---------- | ----------- | ------- | ---------- | ------- | ------- | ----------- | ------ |
| asignemos  | un          | nombre  | al         | archivo | de la   | clave.      | GitHub |
| nos indica | que         | existen | diferentes |         | nombres | habituales, |        |
perononosvamosacomplicarmucho.Vamosallamarle
id_rsa,sinmás.Enrealidad,podemosasignarleelnombre
quequeramos.
Establecemosunnombre,ynospidequeingresemosuna
contraseña(passphrase).Podemosagregarlaodejarlaen
9https://docs.github.com/es/authentication/connecting-to-github-with-
ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent

169
blanco(tengamossiempreencuentalaimportanciadela
| seguridad).HacemosunEnter |     |     |     |     | yrepetimoselproceso.Ya |     |     |
| ------------------------- | --- | --- | --- | --- | ---------------------- | --- | --- |
hemosgeneradonuestraclaveSSH.
Eneldirectorioseleccionadoveremoslosiguiente:id_rsa
| e id_rsa.pub. |       | Para GitHub, |           | necesitaremos |        | el archivo   | .pub     |
| ------------- | ----- | ------------ | --------- | ------------- | ------ | ------------ | -------- |
| (público).    | Si no | entendemos   |           | perfectamente |        | qué          | es SSH,  |
| decirte       | que   | no es        | necesario |               | que lo | comprendamos |          |
| al 100%,      | pero  | sí           | debemos   |               | buscar | información  | para     |
| entender      | sus   | fundamentos. |           | Trabajar      | con    | claves       | privadas |
ypúblicasesmuycomúnenprogramación.
Elmecanismoeselsiguiente:Dadaunaclaveprivada,que
únicamentenosotrosposeemos,yunaclavepública,que
| podríamos     | compartir, |          | cuando  |        | estas claves  | se        | comparan   |
| ------------- | ---------- | -------- | ------- | ------ | ------------- | --------- | ---------- |
| y se combinan |            | de       | una     | manera | muy           | concreta, | pueden     |
| determinar    | si         | la clave | pública |        | está asociada |           | a la clave |
privada.
| Ya tenemos  |          | nuestra | clave.       | Veamos | qué        | más    | nos dice   |
| ----------- | -------- | ------- | ------------ | ------ | ---------- | ------ | ---------- |
| el tutorial | oficial. |         | Lo siguiente |        | que nos    | indica | es que     |
| debemos     | iniciar  | el      | agente       | SSH    | en segundo |        | plano, una |
formadecomprobarsielprocesoestáoperativo.Hecho

170
eso,nosdiráqueyatenemosunID,esdecir,yaposeemos
unaclaveSSHennuestrosistema.
Lo siguiente que nos indica es abrir el archivo de
configuración.Sinotenemosunarchivodeconfiguración
para SSH, debemos crearlo. ¿Cómo lo hacemos? Con el
comando touch desde la terminal, por ejemplo. Si
preferimos crearlo manualmente desde el sistema de
archivos, también podemos hacerlo. Hecho esto, nos
pide que agreguemos cierta información al archivo.
Copiamostodoloquefiguraenladocumentaciónoficial,
lopegamosyloguardamos.
¿Qué debemos poner en IdentityFile? Aquí deberíamos
ingresar el nombre del archivo de nuestra clave, en
nuestro caso, id_rsa. Vemos algunas cosas más, como
un Host que es el del propio GitHub. Básicamente, este
archivodeconfiguraciónnossirveparaprepararnuestra
clave y conectarnos a GitHub. En la documentación
oficialnosloexplicatodo.
Lo último que nos pide es agregar esta clave a nuestro
sistema de almacenamiento y gestión de claves. En

171
la documentación encontrarás información para
conseguirlo en Windows, Mac y Linux. Únicamente
existen ligeras variaciones. Simplemente seguiremos los
pasosparalograrañadirnuestraclaveid_rsa.
Realizada la acción, el sistema nos dirá algo así como
identidad añadida. Finalmente hemos asociado la clave
SSH a nuestro llavero para que el sistema la reconozca.
Esalgoasícomoocurreconloscertificadosdigitales,que
debemosinstalarennuestroequipoparaqueelsistema
sepaquepodemosutilizaresemétododeautenticación.
Eso es precisamente lo que acabamos de hacer con la
claveSSH.
Ahora que ya tenemos la clave creada y almacenada
en nuestro equipo, es momento de dirigirnos a GitHub.
Dentro de nuestro usuario, en Settings, encontraremos
muchas opciones para configurarlo. Existe una sección
quediceSSHandGPGkeys.Vayamosallí.
Tenemosdosformasdegestionarclaves.Existenincluso
tokens de usuario para crear una clave conocida por
nosotros y por GitHub, como una contraseña. Pero, si
vamos a trabajar siempre desde la misma máquina,
lo mejor es hacerlo como estamos indicando en esta
lección.Así,notendremosquevolveraintroducirclaves,
y nuestra máquina local estará vinculada a la máquina
remotadeGitHub,haciendotodomuchomásautomático
y simple. No importa si trabajamos con un proyecto o
varios,máquinayplataformaestaránvinculadas.
Dentro de la sección anterior, presionamos New SSH
Key para agregar una nueva clave de autenticación SSH.
Pudiendotambiénasignarleunnombre.
En nuestro equipo, abrimos la clave pública id_rsa.pub
con un editor de texto o cualquier otro programa. La
copiamos,lapegamosenGitHub,yhacemosclicenAdd.

172
Así tendremos una clave SSH vinculada a nuestra cuenta
de usuario de GitHub. Si queremos agregar más claves,
porque tenemos más dispositivos o usuarios, podremos
hacerlo sin problema.Yaestá casi todo listo. Volvamosa
laguíaoficial.
Esta nos indica que añadamos la clave SSH a GitHub, lo
cualyahemoshecho.Loúltimoseráprobarsirealmente
podemosconectarnosdesdenuestramáquinaaGitHub.
¿Cómo hacemos esto? Con otra nueva instrucción. Una
vez la lanzamos, nos dice que no se puede establecer la
conexión,yaqueprimerodebemosagregarlayaceptarla.
Laagregamoscomonosindica.
Volvemos a ejecutar el comando, y ahora nos dice:
¡Hola, ! Te has autenticado correctamente, pero GitHub no
proporciona acceso a la shell. Aunque no lo parezca, este
mensajeindicaquelaconexiónhasidoexitosa.
Gracias a que tenemos la clave privada en nuestra
máquina local, y la clave pública en GitHub, hemos
podido establecer una conexión con GitHub. También
hemos añadido GitHub como un host conocido en
nuestro archivo de configuración. En ese archivo,

173
especificamosnuestronombredeusuario,laclaveSSHy
otrosdetalles.
| Por ahora, | esto | es todo | lo que | deberíamos |     | hacer |     | para |
| ---------- | ---- | ------- | ------ | ---------- | --- | ----- | --- | ---- |
conectarnosaGitHub.Aunquelaguíamencionaalgunos
| apartados       | adicionales |                | sobre        | contraseñas  |          | intermedias |           | y     |
| --------------- | ----------- | -------------- | ------------ | ------------ | -------- | ----------- | --------- | ----- |
| otros detalles, |             | no necesitamos |              | preocuparnos |          |             | por       | ello. |
| Con esta        | conexión    |                | establecida, |              | podremos |             | comenzar  |       |
| a trabajar      | con         | nuestros       | repositorios |              | de       | GitHub      | desde     |       |
| nuestra         | máquina     | local,         | utilizando   |              | Git para |             | gestionar |       |
cambiosycolaborarconotrosdesarrolladores.
| Siento  | la       | complejidad |         | de este  | capítulo,     |     | de   |     |
| ------- | -------- | ----------- | ------- | -------- | ------------- | --- | ---- | --- |
| nuevo,  | recuerda |             | que en  | la       | documentación |     |      |     |
| oficial | tenemos  |             | todo al | detalle, | y             | que | este |     |
procesolollevaremosacabounaúnicavez.

Capítulo 28: Repositorio
proyecto
Conceptos
Introducción
Enestecapítulo,hablaremossobrecómocrearunnuevo
repositorio en GitHub asociado a nuestro proyecto de
software.
Creación
Primeramente, es importante entender que un
repositorio es un lugar donde se almacena todo el
código fuente y los archivos de un proyecto. En Git, un
repositorio se utiliza para llevar un seguimiento de la
evolucióndelproyectoalolargodeltiempo,loquefacilita
la colaboración y el trabajo en equipo. GitHub es una
plataforma que permite alojar y compartir repositorios
deGitenlínea.
Ya hemos creado un repositorio en GitHub para nuestra
página personal, ahora será el momento de crear uno
nuevo para nuestro proyecto de software. Para hacerlo,
nos dirigimos a nuestra página principal en GitHub, y
hacemos clic en la opción Repositories. A continuación,
pulsamosenelbotónverdeNew paracrearuno.
174

175
En la página de creación de repositorios, podremos
asignarleunnombreyunadescripción.
En la sección Initialize this repository with (inicializar este
repositorio con), podemos optar por crear un repositorio
vacío, o agregar archivos README.md o .gitignore, como
ya hemos visto anteriormente. Si elegimos crear un
repositorio vacío, tendremos que agregar archivos y
códigomanualmentemástarde.
Una vez hemos creado el nuevo repositorio, tendremos
acceso a una URL única, que podemos compartir con
otroscolaboradoresparaqueseunanalproyecto.
Conclusión
ConestenuevorepositoriodeGitHubenmarcha,estamos
listos para aprender nuevos comandos y funciones
relacionadas con Git y GitHub. A partir de ahora, no solo
estaremos trabajando en nuestro proyecto a nivel local,
sinoquetambiénpodremoshacerloenlínea.
Curso
Lección28:mouredev.com/git-github-281
Inicio:03:00:45|Duración:00:01:44
Volvamos a hablar de repositorios. Ya hemos creado
anteriormente uno con nuestro nombre de usuario,
encargadoderepresentarlapáginapersonal.
Ya que el primer repositorio se comporta de una manera
un tanto especial, vamos a crear otro. Nos dirigimos
1https://mouredev.com/git-github-28

176
| a nuestra | página | principal | en  | GitHub, | sección | de  |
| --------- | ------ | --------- | --- | ------- | ------- | --- |
repositorios,ycreamosunonuevo.Esteseráelrepositorio
| asociado | al proyecto | con el | que hemos | trabajado |     | hasta |
| -------- | ----------- | ------ | --------- | --------- | --- | ----- |
elmomentoenlocal.PodemosllamarleinclusoHelloGit.
| Lo marcamos | como  | público,    | y              | no creamos |     | ni un   |
| ----------- | ----- | ----------- | -------------- | ---------- | --- | ------- |
| README.md   | ni un | .gitignore. | Sencillamente, |            |     | creamos |
unrepositorioenblanco.
| Estamos      | preparados | para      | aprender | nuevos        | comandos |         |
| ------------ | ---------- | --------- | -------- | ------------- | -------- | ------- |
| relacionados | con Git    | y GitHub. | Ya       | no únicamente |          | a nivel |
local,comohastaelmomento.

Capítulo 29: Git en
remoto $git remote
Comandos
1 git remote add origin git@github.com:<usuario_githu\
2 b>/<nombre_repositorio>.git
3 git push -u origin <rama_principal>
Conceptos
Introducción
Cuando trabajamos con Git, es importante comprender
cómo podemos subir nuestro código local al repositorio
remoto en GitHub. Para hacerlo, tendremos que
comenzar utilizando el comando de Git llamado remote.
Este comando nos permite establecer una conexión
entre nuestro repositorio local y el repositorio remoto
creadoenGitHub.
Configuración
Para establecer esta conexión, ejecutamos el comando
git remote add origin git@github.com:<usuario_-
github>/<nombre_repositorio>.git desde la raíz
177

178
de nuestro proyecto local. Este comando añade una
referencia remota llamada origin asociada a nuestro
usuariodeGitHub,yalrepositorioquehemoscreado.
Una vez que hemos establecido la conexión, podemos
subirnuestrocódigolocalalrepositorioremotoenGitHub
utilizandoelcomandogit push.Alhacerlo,Gitnospedirá
más información sobre a qué repositorio remoto y a qué
ramaqueremoshacerdichopush.
Es importante tener en cuenta que la primera vez que
subimos nuestro código al repositorio remoto, debemos
utilizar el comando completo git push -u origin
<rama_principal>. Hacemos esto para establecer la
configuración de push predeterminada para nuestro
repositorio.
El parámetro -u en el comando git push -u origin
<rama_principal> establece una configuración
predeterminada para el comando git push. En
particular, establece que el repositorio remoto origin
y el nombre de la rama destino predeterminada para
futuroscomandosgit push.
Esimportanterecalcarqueelparámetro-usoloseutiliza
laprimeravezquesubimosnuestrocódigoalrepositorio
remotoenGitHub.
Una vez que hemos subido nuestro código al repositorio
remoto, cualquier persona con acceso este, y con los
permisos correspondientes, podrá descargar el código y
comenzaracolaborarenelproyectoutilizandoelflujode
trabajodeGityGitHub.
En otro capítulo, estudiaremos detenidamente
el comando git push y lo pondremos en
prácticaconunejemplo.

179
Conclusión
Subir nuestro código local al repositorio remoto en
GitHub es un proceso esencial para trabajar con Git y
GitHub. Con el comando remote y push de Git, podemos
establecer la conexión entre nuestro repositorio local y
el repositorio remoto en GitHub, lo que nos permitirá
trabajardemaneracolaborativayenlínea.
Curso
Lección29:mouredev.com/git-github-291
Inicio:03:02:29|Duración:00:04:50
Vamos a intentar subir todo nuestro código local a
GitHub, teniendo en cuenta por primera vez comando
de Git llamado remote. Git está diseñado para trabajar
en local y también de forma remota, siendo esta última
nuestrafuentedeverdad,elservidordeGitHub.
En la raíz de nuestro proyecto local, Hola Git,
ejecutaremos esta instrucción: git remote add
origin git@github.com:<usuario_github>/<nombre_-
repositorio>.git. De esta manera, Git añadirá una
referencia remota llamada origin asociada a nuestro
usuariodeGitHub,específicamenteenelrepositorioque
hemoscreado.
Debes utilizar la URL del repositorio que has
creado.
1https://mouredev.com/git-github-29

180
Nuestro proyecto, que solo estaba funcionando en local,
se ha vinculado con el repositorio que creamos en la
lecciónanteriordesdeGitHub.
Introducido el concepto de remoto, ¿cómo podemos subir
todoloquetenemosenGit,enelrepositoriolocal,aGitHub?
Ya le hemos indicado con qué repositorio de GitHub está
vinculado.
Toca introducir el siguiente comando esencial, y de uso
diario,queformapartedelflujodeGit.Recordemosque
hastaahorahacíamosgit status,git addygit commit
para realizar una fotografía de nuestro proyecto. Lo que
queremos ahora es pasar todo aquello que guardamos
enlocal alrepositorioremotodeGitHub.
En el proyecto ejecutaremos git push, un nuevo
comandoasociadoaenviarnuestroscommitspendientes
alrepositorioremoto.Sieslaprimeravezquelohacemos
enelproyectosenosavisarádequeprobablementenos
falta algo. Algo así como: ¿a dónde quieres hacer el push?,
damealgúndatomás.Recordamosqueelpuntoasociado
donde nos encontramos se llamaba origin. Intentemos
pues con git push origin, el repositorio de origen con

181
| el que | queremos |     | interactuar, | en  | este caso, | el  | repositorio |     |
| ------ | -------- | --- | ------------ | --- | ---------- | --- | ----------- | --- |
deorigenremoto.
| Y, ¿a dónde |     | queremos | hacer | el push? | A la | rama | principal |     |
| ----------- | --- | -------- | ----- | -------- | ---- | ---- | --------- | --- |
delproyecto,quesellamamain.Recordemosquecuando
| comenzamos |     | a utilizar | Git, | y lo | inicializamos, |     | la  | rama |
| ---------- | --- | ---------- | ---- | ---- | -------------- | --- | --- | ---- |
principalsellamabamaster,perolarenombramosamain
| ya que      | hoy | en día  | es el   | nuevo | estándar.  | Al  | crear   | un  |
| ----------- | --- | ------- | ------- | ----- | ---------- | --- | ------- | --- |
| repositorio | en  | GitHub, | también |       | se llamará | por | defecto |     |
main.
Tenemostodalainformación.Vamosaagruparlaylanzar
| git push | -u  | origin | main, | como | nos | dice | la web | del |
| -------- | --- | ------ | ----- | ---- | --- | ---- | ------ | --- |
propiorepositoriovacíoenGitHub.
| Recuerda |          | solo | tendremos |        | que           | lanzarlo | la   |     |
| -------- | -------- | ---- | --------- | ------ | ------------- | -------- | ---- | --- |
| primera  |          | vez. | Basta     | con    | utilizar      | git      | push |     |
| las      | próximas |      | veces,    | ya que | Git recordará |          | la   |     |
configuraciónestablecidaanteriormente.
| Vamos    | a dirigirnos |          | a la       | URL de | nuestro           | proyecto     |         | en  |
| -------- | ------------ | -------- | ---------- | ------ | ----------------- | ------------ | ------- | --- |
| GitHub   | para         | ver qué  | ha pasado. |        | En el repositorio |              | remoto, |     |
| llamado  | Hello        | Git,     | ya podemos |        | visualizar        |              | todos   | los |
| archivos | que          | teníamos | en         | local, | junto a           | su historial |         | de  |
Git.

182
| Así, usando | git | remote | y   | git | push, | hemos logrado |     |
| ----------- | --- | ------ | --- | --- | ----- | ------------- | --- |
subirnuestrocódigolocalalrepositorioremotoenGitHub.
| Ahora,  | cualquier  | persona  | con         | acceso |             | al repositorio | en  |
| ------- | ---------- | -------- | ----------- | ------ | ----------- | -------------- | --- |
| GitHub, | y los      | permisos | necesarios, |        | puede       | descargar      | el  |
| código  | y comenzar | a        | colaborar   | en     | el proyecto | siguiendo      |     |
elflujodetrabajodefinidoporGit.

| Capítulo |     |     | 30: | Subida |     | de  |     | un  |
| -------- | --- | --- | --- | ------ | --- | --- | --- | --- |
proyecto
Comandos
1 git push
| 2 git pull | origin | main |     |     |     |     |     |     |
| ---------- | ------ | ---- | --- | --- | --- | --- | --- | --- |
Conceptos
Introducción
| Continuemos |              | hablando |           | sobre | la importancia |     |            | de   |
| ----------- | ------------ | -------- | --------- | ----- | -------------- | --- | ---------- | ---- |
| mantener    | sincronizado |          | nuestro   |       | repositorio    |     | local      | y el |
| repositorio | remoto       |          | en GitHub | para  | evitar         |     | conflictos | y    |
garantizarlacoherenciadelhistorialdelproyecto.
| Cambios       | en  | remoto    |     |               |     |        |         |     |
| ------------- | --- | --------- | --- | ------------- | --- | ------ | ------- | --- |
| Si trabajamos |     | de manera |     | colaborativa, |     | es muy | posible |     |
queotrosmiembrosdelequipohayanrealizadocambios
| en el repositorio |            | remoto,    | sin         | que            | nosotros   |     | estemos | al  |
| ----------------- | ---------- | ---------- | ----------- | -------------- | ---------- | --- | ------- | --- |
| tanto. Es         | importante |            | sincronizar | periódicamente |            |     | nuestro |     |
| proyecto          | local      | con GitHub |             | para evitar    | conflictos |     | antes   | de  |
subirnuestrospropioscambios.
183

184
Conclusión
Es importante recordar que Git es un sistema muy
seguro, y que es esencial mantener la coherencia del
historial del proyecto. Por lo tanto, siempre debemos
asegurarnos de estar al día con los cambios en el
repositorio remoto, antes de intentar subir nuestros
propiosdesarrollos.
Curso
Lección30:mouredev.com/git-github-301
Inicio:03:07:19|Duración:00:04:12
Imaginemos que, de alguna manera, alguien acaba de
empezaratrabajarennuestrorepositorio.
Desde GitHub, podremos visualizar la recomendación
que nos recuerda añadirle un README.md a nuestro
repositorio.Aprovechamosparahacerlo.Estaseráunade
las muchas otras acciones que podemos realizar desde
la interfaz web. Agregamos el README.md, y le decimos
quehagauncommit.
La plataforma de GitHub nos sirve para gestionar
muchas cosas. Desde ella podemos crear commits, o
hasta una nueva rama asociada ese commit para no
interferir en la principal. En nuestro caso, lo vamos a
añadirdirectamente.Todoesteprocesoseloatribuimos
a la supuesta nueva persona que está trabajando en
nuestroproyecto.
1https://mouredev.com/git-github-30

185
Regresamos a nuestro entorno local, recordando que
nuestrafuentedeverdadactualesGitHub.
Como hemos dicho, imaginemos que este README.md
lo ha añadido otra persona del equipo, y lo ha subido
a la rama main de GitHub, pero claro, nosotros nos
encontramosennuestroentornolocal trabajandoconel
proyecto,sinserconscientesdeloqueestánhaciendoel
restodepersonasasociadasalproyecto.
Realizamos algunos cambios en nuestro proyecto y
nos disponemos a registrarlos en Git y GitHub. Si
hacemos un git status, se nos indicará que hemos
modificado ciertos archivos. Procedemos a añadirlos
como de costumbre y a realizar un commit. De vuelta
en GitHub, actualizamos la web. ¿Se muestran nuestros
nuevos cambios? Obviamente no, todo sigue en nuestro
repositoriolocal.
Si recordamos, nos falta la acción que se encarga de
enviar ese commit al repositorio remoto. Lanzamos el
comando git push, para que nuestro entorno local se
sincronice con el remoto. Rechazado, error. No podemos
enviarnuestroscambioslocales.¿Porqué?

186
Lohemoscomprobadomuchasvecesalolargodelcurso.
Gitesunsistemamuyseguro.
Cuandoenleccionesanteriorestrabajábamosconramas,
al modificar algo en alguna de ellas, y, si dichos cambios
no estaban sincronizados, nos obligaba a sincronizar el
estado de ambas ramas y solucionar cualquier tipo de
conflicto. Ahora mismo nos encontramos en un caso
muy parecido, con la diferencia de que no estamos
perfectamente sincronizados con lo que tenemos en el
repositorioremoto.
Dado que este es un proyecto muy pequeño, si nos
vamos a GitHub podríamos observar que efectivamente
existeunficheroREADME.mdquenotenemosennuestro
proyecto local, y ese es el causante de nuestra falta de
sincronización.Vamosasolucionarlo.
Para ello utilizaremos git pull origin main (git pull
a futuro), un comando que se encargará de descargar
los cambios que hay en GitHub y los mezclará con
los nuestros en local. Si hay conflictos, tendremos
que resolverlos manualmente. Una vez resueltos los
conflictos, y ambos entornos sincronizados, podremos
ejecutar git push nuevamente para enviar nuestros
cambiossinproblemasaGitHub.
Siempre es importante mantener sincronizado nuestro
proyecto con los cambios existentes en el repositorio
remoto antes de intentar subir nuestras propias
modificaciones. De esta manera, evitaremos conflictos
y garantizaremos que el historial del proyecto sea
coherenteyseguro.

| Capítulo       |       | 31: |        |        |     |      |     |
| -------------- | ----- | --- | ------ | ------ | --- | ---- | --- |
| Sincronización |       |     |        | remota |     |      |     |
| $git           | fetch |     | y $git |        |     | pull |     |
Comandos
| 1 git fetch  |             |     |       |     |     |     |     |
| ------------ | ----------- | --- | ----- | --- | --- | --- | --- |
| 2 git config | pull.rebase |     | false |     |     |     |     |
| 3 git pull   |             |     |       |     |     |     |     |
Conceptos
Introducción
| Sigamos     | profundizando |       | en  | la posibilidad |     | de    | enviar |
| ----------- | ------------- | ----- | --- | -------------- | --- | ----- | ------ |
| y descargar | cambios       | entre | un  | repositorio    |     | local | y uno  |
remoto.
Cuandotrabajamosenunproyecto,escomúnquevarias
personasmodifiquenelcódigoalmismotiempo.Eneste
| escenario, | se hace | indispensable |     | poseer |     | un mecanismo |     |
| ---------- | ------- | ------------- | --- | ------ | --- | ------------ | --- |
paramantenerelcódigoactualizadoentodosloslugares
| donde | se interactúa | con   | el repositorio |     | común. |       | Aquí es |
| ----- | ------------- | ----- | -------------- | --- | ------ | ----- | ------- |
| donde | entran en     | juego | los comandos   |     | git    | fetch | y git   |
pull.
187

188
Fetch
El comando git fetch se utiliza para descargar el
historial de cambios del repositorio remoto al repositorio
local,perosinaplicarloscambios.Enotraspalabras,este
comando descarga los commits realizados en la rama
remota,peronolosfusionaconloscommitslocales.
Pull
Porotrolado,elcomandogit pulldescargaloscambios
del repositorio remoto y los fusiona automáticamente
con los cambios locales. Esto significa que, si hay algún
conflictoentreloscambioslocalesyloscambiosremotos,
el comando git pull intentará fusionarlos de la mejor
manera posible, deteniendo el proceso en caso de que
exista algún conflicto que no permita la combinación de
ambos.
Es importante tener en cuenta que, la primera vez que
intentamos traernos cambios de un repositorio remoto,
debemos indicar cuál es el mecanismo por defecto para
realizaresacombinación.Elmecanismopordefectoque
se recomienda es el de merge (que se configura por
primera vez lanzando el siguiente comando git config
pull.rebase false), ya que nos obliga a asegurarnos
de que estamos realizando un seguimiento cercano y
consciente de nuestro código, tanto en local como en
remoto.

189
Curso
Lección31:mouredev.com/git-github-311
Inicio:03:11:31|Duración:00:05:10
Ya hemos hablado del comando git push para enviar
nuestro código al repositorio remoto, y de git pull para
descargarnosloscambios,peronolosuficiente.
El siguiente comando será git fetch, así que vamos a
comenzar por ejecutarlo. Aunque, hecho esto, parece
que no desencadena ninguna acción. Llamemos a
continuación a git log, y entendamos para para qué
sirveelgit fetch.Elfetchsedescargademaneralocal
el historial de cambios remotos, pero sin aplicar dichos
cambios.
Realizado el fetch, ahora consultamos cuál es el árbol
de cambios (con git tree o git log), observando
lo siguiente: En origin main, en el repositorio remoto,
alguien ha creado un README.md. De esta forma, ya
podemos deducir cuál es el problema por el que no
nos dejaba enviar nuestro último commit a remoto.
Estamos de acuerdo con el cambio, por lo que lo
siguiente que haremos será descargarlo y aplicarlo. Si
loscambiosremotosprovocaranalgúnproblemaconlos
quenosotrostenemosenlocal,tambiénsenosseñalaría
unconflicto,conceptoconelqueyahemostrabajado.
Tengamos esto claro: git fetch se descarga el historial
sin los cambios, git pull se descarga el historial y
tambiénloscambios.
Hagamos pues un git pull. ¿Cuál era el cambio que
nosotros teníamos en GitHub, pero no en local? El fichero
1https://mouredev.com/git-github-31

190
README.md. Lanzado el comando nos dirá que quizás
tengamosalgúnproblemaalahoraderealizaresaacción
por primera vez. Nos está diciendo lo siguiente, ¿qué
quieres hacer con toda esta acción? Debes reconciliar la
ramalocalyremotadealgunaformapordefecto.
Leamos la información que nos proporciona la salida
por la terminal. Se nos explica que podemos configurar
un mecanismo de reconciliación por defecto, en caso de
que la rama remota contenga cambios que nosotros no
hemos aplicado en local. Por defecto, lo que queremos
haceresunmerge,untipodeacciónqueespecificaremos
con el siguiente comando de configuración: git config
pull.rebase false.Lolanzamos.
Vamos a probar de nuevo a ejecutar git pull. Esta
segundavezfuncionarácorrectamente.
Recordemosque,debemosindicarcuáleselmecanismo
por defecto para realizar esa combinación la primera
vez que estamos intentando descargar cambios de un
repositorio remoto. Dicho mecanismo recomendado
es el de merge. ¿Por qué? Porque al final nos obliga
a asegurarnos de que somos conscientes de los
cambios en local y remoto. Esto es esencial, ya que
las otras opciones, como fast-forward o rebase, son
mecanismosqueasumenciertoestadodelcódigo.
Sigamos con nuestro pull, que implica un merge. Como
siempre, se nos pedirá que indiquemos un mensaje.
Ya sabemos que directamente podríamos guardar el
mensajeysalirdeleditorutilizando:q(estodependedel
editorpordefectodenuestraterminal).
Finalizadoelproceso,comprobaremosqueelREADME.md
queteníamosenremoto,ahoratambiénapareceenlocal.

191
| Esmuyimportanteconocerperfectamentegit |        |          |            | push,git         |
| -------------------------------------- | ------ | -------- | ---------- | ---------------- |
| y                                      | fetch. | Comandos | esenciales | (y suficientes), |
pull git
| en la gran | mayoría | de los casos | para establecer | todo un |
| ---------- | ------- | ------------ | --------------- | ------- |
flujocolaborativodetrabajo,primerolocal,ydespuésen
remoto.

| Capítulo |       |     | 32: | Clonación |     |     |
| -------- | ----- | --- | --- | --------- | --- | --- |
| $git     | clone |     |     |           |     |     |
Comandos
| 1 git clone | <URL> |     |     |     |     |     |
| ----------- | ----- | --- | --- | --- | --- | --- |
Conceptos
Introducción
Unavezquetenemoselcódigodeunproyectoennuestra
máquina,podemoscomenzaratrabajarenél.Pero,¿qué
| ocurre si | queremos |     | empezar | a colaborar |     | en un proyecto |
| --------- | -------- | --- | ------- | ----------- | --- | -------------- |
publicadodeformaremota?
Clone
cloneesuncomandodeGitquepermitedescargar
git
| una copia | completa |         | de un        | repositorio |           | de Git existente |
| --------- | -------- | ------- | ------------ | ----------- | --------- | ---------------- |
| en algún  | lugar    | remoto, | como         | GitHub,     | a nuestra | máquina          |
| local. Al | clonar   | un      | repositorio, | se          | crea una  | copia exacta     |
| de este   | remoto   | en      | local,       | incluyendo  | todos     | los archivos,    |
ramas,etiquetas,historial,etc.
192

193
El comando git clone es muy útil cuando necesitamos
trabajar en un proyecto en el que ya se encuentra
colaborando más gente, o simplemente necesitamos
crear una copia de un proyecto existente para realizar
modificaciones en él. Clonar un repositorio, en lugar de
descargar una copia manualmente, nos proporciona
una serie de ventajas, como mantener el historial de
versionesdelrepositorio,ypoderactualizarfácilmentela
copia local del repositorio con los cambios aplicados en
el repositorio remoto. Básicamente, clonar un repositorio
nospermiteseguirtrabajandoconGit.
git clone también permite clonar un repositorio
utilizando diferentes protocolos de conexión, como
HTTPS o SSH. HTTPS es el protocolo más simple para
clonar un repositorio en GitHub, pero SSH es preferible
en la mayoría de los casos, porque es más seguro
y no requiere que ingresemos nuestras credenciales
constantemente.
Conclusión
LaclonaciónesfundamentalenGit.Nospermiteobtener
una copia completa de un repositorio remoto, y trabajar
en él de manera eficiente en nuestra máquina local.
Es una herramienta esencial para colaborar con otros
desarrolladores, y contribuir tanto en proyectos de
códigoabiertocomoenproyectosprivados.
Curso
Lección32:mouredev.com/git-github-321
1https://mouredev.com/git-github-32

194
Inicio:03:16:41|Duración:00:03:14
Supongamos un nuevo caso de colaboración utilizando
GitHub. Acabamos de entrar en el equipo de desarrollo
del proyecto Hello Git, por lo tanto, aún no tenemos el
código del proyecto en nuestro equipo local. Algo que
necesitamosparapodercomenzaradesarrollar.Veamos
cómohacerlo.
Por supuesto, podríamos descargar el código de forma
manual, como un archivo .zip (posibilidad que tenemos
desde GitHub), pero lo que nos interesa realmente es
poder trabajar utilizando el control de versiones de Git.
Si hacemos clic en el botón Code en el repositorio, nos
mostrará opciones para clonarlo, sí, este es el nuevo
concepto. Para ello, podemos usar un cliente de GitHub,
osimplementeclonarlodesdelaconsola.
Para clonar el repositorio, podemos seleccionar entre
HTTPS o SSH. Actualmente es preferible usar SSH, dado
su grado de integración y seguridad. Con SSH, nuestra
máquina quedará vinculada con los repositorios que
permitan su acceso. Recuerda que ya hemos aprendido
aconfigurarloenunalecciónanterior.
Copiamos la URL del repositorio con SSH, y nos dirigimos
desde la terminal al lugar de nuestro equipo donde
queremos descargar el directorio remoto. Ya solo nos
queda escribir git clone, seguido de la URL que
copiamosenelrepositoriodeGitHub.

195
| Una vez  | realizada |           | esta | acción,       | veremos | una  | nueva  |
| -------- | --------- | --------- | ---- | ------------- | ------- | ---- | ------ |
| carpeta, | con       | el nombre |      | del proyecto, | en este | caso | Hello- |
Git,quecontienetodoelcontenidodelrepositorio.Ahora
| ya tenemos   |     | en nuestra |     | máquina     | una copia | sincronizada |     |
| ------------ | --- | ---------- | --- | ----------- | --------- | ------------ | --- |
| del proyecto |     | con        | el  | repositorio | base      | de GitHub.   | Ya  |
podemoscomenzaratrabajarenél.
| En cuanto       | a   | si necesitamos |     | autenticarnos |                 | para | clonar |
| --------------- | --- | -------------- | --- | ------------- | --------------- | ---- | ------ |
| el repositorio, |     | depende        |     | del tipo      | de repositorio. |      | Todo   |
dependerádelospermisosasignados.
Enestecasonosolosetratadedescargarloúnicamente,
| sino de | poder | modificar |     | el proyecto | a futuro |     | y enviar |
| ------- | ----- | --------- | --- | ----------- | -------- | --- | -------- |
nuestroscambioslocalesalrepositorioremoto.

| Capítulo |     |      | 33: | Subida |     |     | de  |     |
| -------- | --- | ---- | --- | ------ | --- | --- | --- | --- |
| código   |     | $git |     | push   |     |     |     |     |
Comandos
1 git push
| git push | --tags |     |     |     |     |     |     |     |
| -------- | ------ | --- | --- | --- | --- | --- | --- | --- |
2
Conceptos
Introducción
| Ya hemos | hablado |     | de cómo | sincronizar |     | cambios |     | entre |
| -------- | ------- | --- | ------- | ----------- | --- | ------- | --- | ----- |
nuestrorepositoriolocalyelrepositorioremotoenGitHub.
| Esto es  | esencial | para   | asegurarnos |        | de  | que       | todos   | los |
| -------- | -------- | ------ | ----------- | ------ | --- | --------- | ------- | --- |
| miembros | del      | equipo | tengan      | acceso | a   | la última | versión |     |
delproyecto.
| Para hacer              | esto,       | utilizamos |              | el            | comando         |        | git        | pull, |
| ----------------------- | ----------- | ---------- | ------------ | ------------- | --------------- | ------ | ---------- | ----- |
| descargando             |             | así los    | cambios      |               | del repositorio |        | remoto     |       |
| a nuestro               | repositorio |            | local.       | Si hay        | conflictos      |        | entre      | los   |
| cambios                 | realizados, |            | podemos      | solucionarlos |                 |        | utilizando |       |
| previamenteelcomandogit |             |            |              | merge.        |                 |        |            |       |
| Una vez                 | que hemos   |            | sincronizado |               | los cambios,    |        | podemos    |       |
| trabajar                | en el       | proyecto   | localmente,  |               | y,              | cuando | hayamos    |       |
| terminado,              | subir       | los        | cambios      |               | al repositorio  |        | remoto     |       |
| utilizandoelcomandogit  |             |            |              | push.         |                 |        |            |       |
196

197
Push
Antes de subir nuestro código al repositorio remoto, es
importante asegurarnos de que todo esté en orden y de
quenoexistanerrores.Parahaceresto,podemosutilizar
el comando git status, verificando así el estado de
nuestrorepositorio.
Si hay archivos modificados, podemos utilizar el
comando git add para prepararlos antes de ser
enviadosalrepositorio.Acontinuación,podemosutilizar
elcomandogit commitparacrearunanuevaversióndel
código con los cambios realizados, junto a un mensaje
descriptivo.
Una vez que hemos preparado nuestro código para ser
enviadoalrepositorioremoto,utilizaremosfinalmentegit
push para subir los cambios. Esto enviará los cambios al
repositorio remoto, y los hará disponibles para cualquier
personaconaccesoaél.
También podemos usar el comando git push --tags
para publicar todas las etiquetas (tags) en el repositorio
remoto.
Desde GitHub, podremos visualizar los cambios
realizadosyquiénescontribuyeronaellos.Esimportante
recordarque,siotrosmiembrosdelequipohanrealizado
cambios en el código mientras nosotros trabajábamos,
debemossincronizar nuestroscambiosantesdesubirlos
alrepositorioremoto.
Conclusión
Yahemosaprendidodiferentesmecanismosparautilizar
Git y GitHub, en equipo o de manera individual, de

198
| forma      | efectiva.  | Hemos |            | visto cómo | sincronizar |        | cambios, |     |
| ---------- | ---------- | ----- | ---------- | ---------- | ----------- | ------ | -------- | --- |
| solucionar | conflictos |       | y preparar |            | nuestro     | código | para     | ser |
enviadoalrepositorioremoto.
Curso
Lección33:mouredev.com/git-github-331
Inicio:03:19:55|Duración:00:01:36
| En la    | lección    | anterior |            | hablamos | de       | cómo    | sincronizar |        |
| -------- | ---------- | -------- | ---------- | -------- | -------- | ------- | ----------- | ------ |
| cambios, | solucionar |          | conflictos | y        | preparar | nuestro |             | código |
parasubirloalrepositorioremoto.
| Parece        | que ya     | todo  | está     | correcto.       | Nos     | toca     | dar el         | último |
| ------------- | ---------- | ----- | -------- | --------------- | ------- | -------- | -------------- | ------ |
| paso. Este    | consistirá |       | en       | enviar los      | commits |          | que tenemos    |        |
| en local      | a GitHub.  |       | Ahora    | sí, simplemente |         |          | ejecutemos     | el     |
| comandogit    |            | push. |          |                 |         |          |                |        |
| Si recargamos |            | la    | web      | de GitHub,      |         | podremos | ver            | que    |
| nuestros      | cambios    |       | ya están | disponibles     |         | en       | el repositorio |        |
remoto,ycómoelarchivomodificadotieneasignadodos
personasquehancontribuidoasuevolución.Lapersona
quelocreó,yotraqueloactualizó.
1https://mouredev.com/git-github-33

199
| En este momento, | disponemos | de todo | el código de |
| ---------------- | ---------- | ------- | ------------ |
nuestroproyectoenlanube.

| Capítulo |     | 34: |     |     |     |     |     |     |
| -------- | --- | --- | --- | --- | --- | --- | --- | --- |
Bifurcaciones
Conceptos
Introducción
| En este       | capítulo | exploraremos   |       |            | cómo    | funciona  | el       | flujo |
| ------------- | -------- | -------------- | ----- | ---------- | ------- | --------- | -------- | ----- |
| colaborativo  |          | en GitHub,     | lo    | cual       | nos     | permitirá | trabajar |       |
| en equipo     |          | en un proyecto |       | compartido |         | de una    | manera   |       |
| muy peculiar. |          | Para ello,     | vamos | a          | suponer | que       | somos    | un    |
usuarioajenoaunproyectoyaexistente,yquequeremos
colaborarenél.
Fork
| Aquí es            | donde | entra | en juego | el  | verdadero   | concepto |         | de  |
| ------------------ | ----- | ----- | -------- | --- | ----------- | -------- | ------- | --- |
| flujo colaborativo |       | en    | GitHub,  | la  | bifurcación |          | o Fork. | En  |
GitHub,unForkesunacopiadeunrepositorioquesecrea
ennuestrapropiacuentadeGitHub.Labifurcaciónesuna
| forma         | de poder | realizar    | cambios   |     | sobre          | el código |           | si no |
| ------------- | -------- | ----------- | --------- | --- | -------------- | --------- | --------- | ----- |
| tenemos       | permisos | de          | escritura | en  | el repositorio |           | original, |       |
| o si queremos |          | evolucionar |           | ese | repositorio    | por       | nuestra   |       |
cuentasinafectaralorigen.
| Para crear       |     | un Fork                         | de      | un repositorio |     |             | en GitHub, |     |
| ---------------- | --- | ------------------------------- | ------- | -------------- | --- | ----------- | ---------- | --- |
| simplemente      |     | debemos                         | navegar |                | al  | repositorio |            | que |
| queremosbifurcar |     | yhacerclicenelbotónFork.Alhacer |         |                |     |             |            |     |
200

201
clic en este botón, se creará una copia del repositorio en
nuestracuentadeGitHub.
Una vez que hemos bifurcado un repositorio, podemos
hacer cambios en el código, añadir archivos, commits, y
enviarlos de nuevo al repositorio, de igual manera que lo
haríamosencualquierotrorepositorioasociadoanuestra
cuentadeGitHub.
Clonación
Una vez que tenemos nuestra propia bifurcación del
repositorio, podemos clonarla en nuestro equipo local
utilizandoelyavistocomandogit clone.Deestaforma,
podremos trabajar en dicha bifurcación sin afectar al
repositoriooriginal.
Una vez tenemos una copia del repositorio en nuestra
cuenta de GitHub, podemos realizar cambios en
ella como si fuera nuestro propio repositorio. Esto
implica poder utilizar el comando git push para enviar
modificaciones.
Curso
Lección34:mouredev.com/git-github-341
Inicio:03:21:31|Duración:00:05:31
Sigamos adelante, para que todo lo que estamos
aprendiendo acabe derivando en un ejercicio real, y así
ponerenprácticaelflujodecolaboraciónenGitHub.
1https://mouredev.com/git-github-34

202
Imaginemosestavezquesomosotrousuarioquequiere
colaborar en el proyecto con el que hemos trabajado
hasta ahora. Accedemos desde ese otro usuario al
repositorio público del proyecto, donde hemos estado
reflejandotodoelcontenidodelcurso.
Nuestramisiónesclonar elrepositorio.BuscamossuURL
por SSH y la copiamos. Lanzamos un git clone del
repositorio que no es nuestro (recordemos que estamos
suponiendo que somos un usuario ajeno al proyecto).
Realizadoelclone,elrepositoriosedescarga.Hastaaquí,
todocorrecto.
Una vez tenemos el repositorio en local, abrimos el
archivo README.md y lo modificamos. A continuación,
nos disponemos a añadirlo a Git, hacer un commit con
su correspondiente mensaje, y, finalmente un git push.
¿Qué ha ocurrido? Que no podemos subir el código al
repositorio, porque no tenemos permisos de escritura
(somosotrousuario).
Aquí es donde entra el verdadero flujo colaborativo de
GitHub. Si tenemos permisos en el proyecto, podremos

203
trabajar con él sin mayor problema. Pero, ¿qué pasa
si no tenemos permisos de escritura? Aquí es donde
aparece este nuevo término propio de GitHub: El Fork o
bifurcación.
Seguimosconlaideainicial.Somosotrousuarioajenoal
proyecto,yqueremoshaceruncambioenelREADME.md
del repositorio del curso. ¿Cómo podemos indicarle a su
dueñonuestraintención?
El Fork es una especie de copia de un repositorio,
mejor dicho, la creación de una nueva rama en base
al contenido de un repositorio. El Fork es la manera de
poder realizar cambios sobre el código si no tenemos
permisos de escritura, o si queremos evolucionar ese
repositorio por nuestra cuenta. Dentro de la URL del
repositorio objetivo en GitHub únicamente tenemos
que seleccionar la opción Fork, y configurar una
serie de opciones, como seleccionar si queremos
clonar únicamente la rama main. El botón Fork nos
permiteclonar eserepositorioennuestropropiousuario.
Así podremos modificarlo sin afectar el original, por
supuesto,subiendoloscambiosanuestrorepositorio.

204
| ¿Cuál sería | el  | siguiente | paso | ahora | que | hemos | realizado el |
| ----------- | --- | --------- | ---- | ----- | --- | ----- | ------------ |
Fork?
| Ya que | tenemos | una | copia | en  | la que | podemos | hacer lo |
| ------ | ------- | --- | ----- | --- | ------ | ------- | -------- |
quequeramos,vamosaclonarlaennuestroequipolocal.
| Regresamos             | a           | la terminal, |         | nos                        | ubicamos   | en  | el directorio |
| ---------------------- | ----------- | ------------ | ------- | -------------------------- | ---------- | --- | ------------- |
| deseado,ylanzamoselgit |             |              |         | clonedelaURLdelrepositorio |            |     |               |
| bifurcado,             | que         | ahora        | tenemos |                            | en nuestro |     | usuario de    |
| GitHub.                | Repositorio |              | que     | en estos                   | momentos   |     | contiene      |
exactamentelomismoquesuoriginal.

205

| Capítulo |     | 35: | Flujo |     |     |     |     |
| -------- | --- | --- | ----- | --- | --- | --- | --- |
colaborativo
Conceptos
Introducción
Duranteestasleccionesestamosaprendiendoatrabajar
| con Git | para manejar |     | versiones | de  | archivos, | y a | utilizar |
| ------- | ------------ | --- | --------- | --- | --------- | --- | -------- |
GitHubparacolaborarconotrosusuariosenunproyecto.
Sincronización
| Si bifurcado | un repositorio      |       | y realizamos  |     | cambios        |              | en este, |
| ------------ | ------------------- | ----- | ------------- | --- | -------------- | ------------ | -------- |
| podremos     | enviarlos           | al    | repositorio   |     | original.      | Antes        | de       |
| hacer        | esto, es importante |       | sincronizar   |     | nuestro        | repositorio  |          |
| bifurcado    | con el repositorio  |       | original.     |     | Esto asegurará |              | que      |
| ambos        | repositorios        | estén | actualizados, |     |                | y evitaremos |          |
conflictosalrealizarunposiblemergeentreambos.
Curso
Lección35:mouredev.com/git-github-351
Inicio:03:27:02|Duración:00:03:44
1https://mouredev.com/git-github-35
206

207
VamosaañadirunnuevoarchivodeMarkdownanuestro
proyecto, ya bifurcado y clonado. Utilizamos el comando
touch, y lo llamamos, por ejemplo, hello.md, ya que
también será un archivo Markdown. Podemos abrirlo
paravisualizarsucontenido.
Recuerda que nos encontramos trabajando en local.
Vamos a añadir nuestro nombre de usuario de GitHub
comotextodelnuevofichero.
Hemos modificado el archivo, pero aún no se encuentra
en el repositorio original (el que utilizamos como
referencia para realizar el Fork). Continuamos en
nuestrorepositoriobifurcado.Vamosarealizarelproceso
completo para añadirlo al repositorio remoto. Ya nos lo
sabemosdememoria:git add,git commit,git push.
Ya de vuelta en el repositorio bifurcado de GitHub,
podremosvernuestronuevoarchivohello.md.
¿Qué será lo siguiente? Nos gustaría que el repositorio
original tuviera este código. Supongamos que el fichero
queacabamosdecrearnospareceútil,yqueremosque,
en el repositorio original del curso, donde no podemos

208
escribirporfaltadepermisos,sepuedanvisualizarestos
cambiosrealizadosenelrepositoriobifurcado.
Hagamos memoria. En primer lugar, realizamos el
Fork para hacer una copia y retocar el proyecto. Ahora
queremosenviaresoscambiosalrepositoriooriginal.
Lo primero que debemos hacer siempre es sincronizar
nuestro proyecto bifurcado, para que el repositorio,
creadoapartirdeunooriginal,semantengaalineadocon
este. Si alguien modificase el repositorio original, desde
nuestro repositorio bifurcado podríamos seleccionar la
opción Sync, para así evitar conflictos y realizar un merge.
Esto ya nos suena de lecciones anteriores. Es el mismo
concepto de ramas, pero aplicado a repositorios. Git es
unsistemamuyseguro,yGitHubnopuedesermenos.
En nuestro ejemplo, repositorio original y bifurcado
se encuentran al día, no existen problemas de
sincronización, ya que nadie ha realizado ninguna acción
sobre el repositorio original. En la siguiente clase, sí que
tendremosencuentalasituacióndesincronización.

Capítulo 36: Pull requests
Conceptos
Introducción
Una de las funcionalidades más importantes de GitHub
es la capacidad de contribuir a proyectos externos
mediantePullRequests,tambiénllamadasPR.
Pull Request
Una Pull Request es una solicitud que se realiza al
propietario de un repositorio para que revise, acepte e
incorporeloscambiosquesehanrealizadoenunarama
específica. En términos generales, una Pull Request se
utiliza para proponer cambios en un repositorio ajeno
y colaborar con otros desarrolladores en un proyecto
común.
ElmecanismodePull Requesttambiénsepuedeutilizar
dentro de un mismo equipo de desarrollo, permitiendo
solicitar la revisión de los cambios por parte de sus
miembros.
Una Pull Request suele contener un conjunto de
cambios (commits), que se han realizado en una rama
en particular, y que se desean integrar en el repositorio
original. Aplicado a un repositorio ajeno, una Pull
Request se puede considerar como una forma de
209

210
solicitar que se incorporen los cambios realizados en
una rama de nuestro repositorio, creado a partir de un
Fork,enotraramadelrepositoriooriginal.
Creación
Para crear una Pull Request, en primer lugar, es
necesario realizar una bifurcación (Fork) del repositorio
original en nuestra propia cuenta de GitHub. Esto se
hace con el objetivo de crear una copia del repositorio
original en nuestra cuenta, donde podemos realizar los
cambiosquedeseamos.
Cabe aclarar que una Pull Request también se puede
realizar entre ramas de un mismo repositorio, para así
controlarloscambiosqueseañadenyreintegranenellas.
Una vez que hemos realizado los cambios, podemos
crear una Pull Request, que solicita al propietario
del repositorio original (o, en su defecto, a un usuario
administrador o que se ocupe del mantenimiento
del repositorio) que revise y acepte los cambios que
hemos realizado en nuestra bifurcación. Para ello,
debemos dirigirnos al repositorio original, seleccionar
la opción Pull requests y elegir Open pull request. En
estemomento,GitHubnosmostraráunainterfazdonde
podemosseleccionarlasramasyloscambiosquehemos
realizado,añadircomentariosyenviarlaPull Requestal
administradordelrepositoriooriginal.Tambiénpodemos
realizar una Pull Request desde nuestro repositorio
bifurcado.
Después de enviar la Pull Request, el administrador
del repositorio original puede revisar los cambios,
comentarlos, y, si lo considera adecuados, aprobar
la Pull Request. En caso de que existan conflictos, o

211
problemas en los cambios propuestos, el administrador
del repositorio original puede solicitar que se realicen
ajustesantesdeaprobarlaPull Request.
Finalmente, una vez que la Pull Request ha sido
aprobada, el administrador del repositorio original debe
realizar el merge, seleccionando la opción Merge pull
request, para así combinar los cambios de la rama
bifurcada en la rama del repositorio original. Si no hay
conflictos, se podrá realizar el merge de la Pull Request
directamente. En este caso, la Pull Request se marcará
como mergeada y cerrada, y los cambios realizados se
incorporanalrepositoriooriginal.
Conclusión
Las Pull Requests son una forma de contribuir a
proyectos de código abierto y colaborar con otros
desarrolladores en un proyecto común. Para crear una
Pull Request, es necesario hacer una bifurcación del
repositorio original, realizar los cambios que deseamos
en nuestra propia bifurcación, y enviar la Pull Request
al administrador del repositorio original. Si la Pull
Request es aprobada, el administrador puede combinar
los cambios mediante un merge. En general, las Pull
Requestssonunaherramientamuyútilparatrabajaren
proyectos colaborativos en GitHub y mejorar la calidad
delcódigo.
Curso
Lección36:mouredev.com/git-github-361
1https://mouredev.com/git-github-36

212
Inicio:03:30:46|Duración:00:04:44
Para contribuir a otro repositorio, y enviar nuestros
cambios al repositorio original, utilizaremos las opciones
de contribución. Aquí nos encontraremos con un nuevo
término muy importante en GitHub: Pull Request
(abreviadocomoPR).
Si contamos con una aportación, que hemos creado a
partir de un Fork al repositorio original, podremos abrir
unaPull Request.
En nuestro caso, hemos realizado un commit en el que
añadimos un archivo llamado hello.md, con nuestro
nombre de usuario de GitHub, señalando que el dicho
commit añadía un archivo para saludar a la comunidad.
¿Hacia dónde queremos enviar esta Pull Request? Al
repositorio original que posee el contenido del curso
(github.com/mouredev/hello-git2).
Añadimos un comentario y creamos la Pull Request.
Hecho esto, en el repositorio original, observaremos
2https://github.com/mouredev/hello-git

213
que aparece una nueva Pull Request. Como
administradores, podríamos revisar los cambios,
observando que se desea añadir un archivo llamado
hello.md al proyecto original. Supongamos que nos
pareceunabuenaidea,yaprobamoslaPull Request.
Regresando a la sección de Pull Request, podemos
visualizar que se ha aceptado, faltándonos únicamente
unúltimopaso,realizarunmergedelaPull Request.
Lapersonaquemantieneoespropietariadelrepositorio
original debe hacer Merge pull request. Git no permitirá
combinarcódigoquecauseproblemasentresí.Sinohay
conflictos, como en este caso, se podrá realizar el merge
directamente.
Una vez confirmado, el proceso de Pull Request se
dará como finalizado. La PR se eliminará, y los cambios
propuestos se han incorporarán al repositorio original.
El código del repositorio original ya incluye el archivo
hello.md, con el contenido enviado por el usuario ajeno
alproyecto.

| Lección |     | 37: |     | Ejercicio |     |     |     |     |
| ------- | --- | --- | --- | --------- | --- | --- | --- | --- |
práctico
Conceptos
Introducción
| Ahora que               | ya       | tenemos |           | los conocimientos |                      |       | suficientes, | es  |
| ----------------------- | -------- | ------- | --------- | ----------------- | -------------------- | ----- | ------------ | --- |
| hora de                 | ponerlos | en      | práctica. |                   | Para ello            | vamos | a realizar   |     |
| unprocesocompletodePull |          |         |           |                   | RequestrealenGitHub. |       |              |     |
Ejercicio
Estossonlospasosallevaracaboparaponerenpráctica
| el flujo | colaborativo. |     | El  | objetivo | es  | proponer | cambios |     |
| -------- | ------------- | --- | --- | -------- | --- | -------- | ------- | --- |
sobreunproyectodeGitHub:
| 1. Fork:    | El  | primer     |     | paso es | realizar |     | un Fork | del |
| ----------- | --- | ---------- | --- | ------- | -------- | --- | ------- | --- |
| repositorio |     | principal. |     | Esto    | crea     | una | copia   | del |
repositorioennuestracuentapersonaldeGitHub.
| 2. Clonación: |                       | Después |     | de realizar |         | un Fork, | debemos         |     |
| ------------- | --------------------- | ------- | --- | ----------- | ------- | -------- | --------------- | --- |
| clonar        | elrepositorioenlocal. |         |     |             |         |          |                 |     |
| 3. (Extra)    | Rama:                 |         | Una | vez         | clonado |          | el repositorio, |     |
podemoscrearunaramaenlaqueserealizaránlos
| cambios. |     | Crear | ramas       | es  | una buena      |     | práctica | para |
| -------- | --- | ----- | ----------- | --- | -------------- | --- | -------- | ---- |
| aislar   | el  | flujo | de trabajo. |     | Esto implicará |     | realizar | un   |
procesodemergeparareintegrarsuscambiosenla
rama,apartirdelaquesehageneradolanueva.
214

215
4. Modificaciones: Una vez se ha clonado el
repositorio, se realizarán los cambios necesarios en
elcódigofuente.
5. Commit: Después de realizar los cambios
necesarios,esimportanterealizaruncommit.
6. Push: Realizado el commit, el siguiente paso es
ejecutar un push de la rama al repositorio remoto en
GitHub.
7. Pull Request: A continuación, podremos crear
una Pull Request en GitHub. Para hacer esto,
es necesario hacer clic en el botón New pull
request en la página del repositorio bifurcado. Debe
seleccionarselaramaquecontieneloscambiosyel
repositorio principal al que se van a proponer estas
modificaciones.
8. Revisión: Después de crear una Pull Request, es
importante esperar la revisión y aprobación por
partedelosadministradoresdelrepositoriooriginal.
Una vez se apruebe la Pull Request, los cambios
sefusionaránendichorepositorio.
Curso
Lección37:mouredev.com/git-github-371
Inicio:03:35:30|Duración:00:02:11
El flujo completo en Git y GitHub nos permiten trabajar
demaneracolaborativa.
Me gustaría comenzar a recibir Pull
Request en el proyecto principal
1https://mouredev.com/git-github-37

216
| (github.com/mouredev/hello-git2). |     |      |             |         |     | Recuerda |     |     |
| --------------------------------- | --- | ---- | ----------- | ------- | --- | -------- | --- | --- |
| que                               | en  | este | repositorio | tenemos |     | toda     | la  |     |
informaciónrelevantedelcurso.
| El reto         | es  | el siguiente: |     | debes      | añadir   |         | tu nombre |     |
| --------------- | --- | ------------- | --- | ---------- | -------- | ------- | --------- | --- |
| de usuario      |     | de GitHub     |     | al final   | del      | archivo | hello.md  |     |
| del repositorio |     | original      |     | del curso, | mediante |         | una       |     |
Pull
Request.PreviamentehaciendounForkentucuentade
| usuario,       | clonando       |            | el repositorio | en                | local,      | modificando  |        | el    |
| -------------- | -------------- | ---------- | -------------- | ----------------- | ----------- | ------------ | ------ | ----- |
| fichero        | hello.md,      | realizando |                | un add,           | un          | commit,      | un     | push, |
| y, finalmente, |                | una        | Pull           | Request           | solicitando |              | añadir | el    |
| cambio         | al repositorio |            | del            | curso. Yo         | seré        | el encargado |        | de    |
| revisarlasPull |                | Request.   |                |                   |             |              |        |       |
| Tengo          |                | como       | objetivo       | añadir            | en          | el fichero   |        |       |
| hello.md       |                | los        | nombres        | del usuario       |             | de GitHub    |        |       |
| de             | cada           | persona    | que            | está participando |             |              | en el  |       |
curso,yquehallevadoacaboestapráctica.
¿Quéteparecelaidea?
| Es un ejercicio |     | simple, | pero | sirve | para | poner | en práctica |     |
| --------------- | --- | ------- | ---- | ----- | ---- | ----- | ----------- | --- |
ungrannúmerodeconceptosquehemosvistosobreGit
yGitHub.
Ojalápuedaleertunombredeusuarioentrelas
Pullrequests.
¡Ánimo!
2https://github.com/mouredev/hello-git

| Capítulo |          |     | 38: | Conflictos |     |     |     | en  |
| -------- | -------- | --- | --- | ---------- | --- | --- | --- | --- |
| pull     | requests |     |     |            |     |     |     |     |
Conceptos
Introducción
Enestecapítulovamosaprofundizarenelflujodetrabajo
deGitHub,especialmenteenelprocesodecolaboración
| entreusuarios,comoeslacreacióndePull |     |     |     |     |     |     | Requestyla |     |
| ------------------------------------ | --- | --- | --- | --- | --- | --- | ---------- | --- |
resolucióndeconflictos.
Colaboración
| Como            | mencionamos |               | anteriormente, |              |               | una        | de         | las   |
| --------------- | ----------- | ------------- | -------------- | ------------ | ------------- | ---------- | ---------- | ----- |
| características |             | más           | importantes    |              |               | de GitHub, |            | es su |
| capacidad       |             | para permitir |                | que          | múltiples     |            | personas   |       |
| colaboren       | en          | un mismo      |                | repositorio. |               | Esto       | es posible |       |
| gracias         | a sus       | herramientas  |                | de           | colaboración, |            | como       | la    |
| creación        | de          | Pull Request, |                | y la         | capacidad     | de         | resolución |       |
deconflictosentreellas.
| Una |     | Request, | es  | una | solicitud | de  | extracción |     |
| --- | --- | -------- | --- | --- | --------- | --- | ---------- | --- |
Pull
| para que    | los    | cambios      | realizados |         | en   | una        | rama        | de un |
| ----------- | ------ | ------------ | ---------- | ------- | ---- | ---------- | ----------- | ----- |
| repositorio | sean   | incorporados |            | en      | la   | rama del   | repositorio |       |
| original.   | Esta   | solicitud    | es         | creada  | por  | un usuario | que         | ha    |
| bifurcado   | el     | repositorio  | original,  |         | y ha | realizado  | cambios     |       |
| en su       | propio | repositorio. |            | La Pull |      | Request    | permite     | al    |
217

218
usuario proponer sus cambios al administrador del
repositorio original, quién puede revisarlos, discutirlos
conelusuario,yfinalmenteaceptarlosorechazarlos.
Conflicto
Laresolucióndeconflictosesunprocesomuyimportante
que se puede presentar al intentar fusionar ramas con
cambios diferentes en un mismo archivo. Un conflicto
de fusión ocurre cuando Git no puede determinar
automáticamente cómo fusionar los cambios realizados
por dos usuarios diferentes. En este caso, los usuarios
deben resolver manualmente el conflicto, eligiendo qué
cambiosconservarycómocombinarlos.
Resolución
Cuando se encuentra un conflicto en una Pull Request,
se puede resolver de forma local, mediante la línea de
comandos de Git. Sin embargo, GitHub también ofrece
una herramienta para resolver conflictos directamente
desdelaplataforma.
Para resolver un conflicto en GitHub, debemos hacer
clic en el botón Resolve conflicts, en la Pull Request
afectada. Esto nos mostrará un editor de código en
línea, donde podremos visualizar las diferencias entre
lasramasqueestánenconflicto,yresolversuscolisiones
manualmente. Podemos elegir qué cambios conservar,
y cómo combinarlos, y, una vez resueltos los conflictos,
marcarlos como resueltos y hacer un commit para
fusionar lasramas.

219
Conclusión
El flujo de trabajo en GitHub y la colaboración entre
usuarios es una parte fundamental del desarrollo de
software moderno. A través de herramientas, como la
creación de Pull Request, y la resolución de conflictos,
GitHub permite que múltiples personas trabajen juntas
enunmismoproyectodemaneraefectivayeficiente.
Curso
Lección38:mouredev.com/git-github-381
Inicio:03:37:41|Duración:00:15:50
Hasta el momento, hemos explorado un flujo de trabajo
completoenGitHub,comosiyaestuviéramostrabajando
desde allí, y quisiéramos aportar alguna evolución a un
repositorio.
Hagamos un repaso. Si tenemos permiso en un
repositorio, trabajaremos con este siguiendo el flujo
habitual de Git, seleccionando la rama correspondiente,
y haciendo push de nuestros cambios, aunque recuerda
que también podemos utilizar el mecanismo de Pull
Request en ellas, para así añadir un extra de seguridad
medianterevisióndecambios.
Como ya sabemos, GitHub está diseñado para ser
totalmente colaborativo, permitiendo que personas,
sin permisos en un repositorio público, también puedan
colaborarenél.Estoformapartedelafilosofíadelcódigo
abierto.
1https://mouredev.com/git-github-38

220
El ejercicio para poner en práctica este flujo, fue el
siguiente: creamos un archivo llamado hello.md en
formato Markdown, y, desde otro usuario de GitHub,
añadimosnuestronombrealarchivo.
Como bifurcamos el repositorio, podemos hacer lo que
queramosconél.Unavezhechosloscambios,podemos
decidirsiseguirtrabajandoenesterepositorio,osolicitar
que dichos cambios se incorporen al repositorio original.
¿Cómo se hacía eso? Para ello aplicamos el concepto de
Pull Request.
La Pull Request es una solicitud para que los cambios
hechosennuestroForkseañadanalrepositoriooriginal.
En la lección anterior realizamos una Pull Request
completa.
Es importante entender que Pull Request no es lo
mismo que push. Este último sí que forma parte del
flujo de Git. Cuando hablamos de Pull Request y Fork,
nos referimos a conceptos aplicados en GitHub, ya
que es como funciona la plataforma para permitir la
colaboracióndeusuarios.
Por otra parte, cuando revisamos el repositorio en
GitHub,podremosencontrarenélunnuevoconceptode
colaboración, las llamadas Issues, que se corresponden
conposibleserroresennuestrorepositorio.Estotambién
forma parte del flujo de GitHub: reportar errores sin
necesariamente aportar código o correcciones. Sirven
paranotificarproblemasosugerenciasenelproyecto.
Dicho esto, volvamos a las Pull Request. Imaginemos
que otros colaboradores han enviado diferentes
solicitudes a nuestro repositorio, pidiendo añadir nuevos
usuariosalficherohello.md.
Llegado el momento, al revisar nuevas Pull Request,

221
podemos encontrarnos con un problema. Esto se debe
a que otros usuarios realizaron el Fork en un momento
específico, cuando el repositorio tenía ciertos datos, y un
listado de usuarios concreto. Pero el repositorio original
está vivo, y sigue evolucionando sin tener en cuenta
quiénycuándoserealizanlosFork.Asímismo,antesde
aceptar una PR, han podido aceptarse otras, que entren
en conflicto con nuestro código. Como ya sabemos, la
resolución de conflictos entre ramas es una constante en
Git.
Si para aceptar una PR, y realizar su merge en nuestro
repositorio,tenemosqueresolverunconflicto,podremos
hacerlodemaneralocal.Perotambiénpodemosintentar
resolver un conflicto desde el propio GitHub. Incluso la
propia plataforma nos sugiere hacerlo. Será tan simple
comoseleccionarlaopciónResolveconflicts(siempreque
exista uno a la hora de realizar el merge de una Pull
Request).
Que exista un conflicto, no quiere decir que el usuario
no haya sincronizado su progreso, simplemente puede
significar que el repositorio siguió avanzando, después

222
de que el usuario enviase una Pull Request a nuestro
repositorio. Vayamos a la opción de resolver conflictos
dentrodeGitHub.
Como en nuestro supuesto, hemos aceptado más PR
antesdellegaraladelconflicto,nosencontramosconque
en la línea de código donde se quiere añadir un nombre
de usuario al hello.md, ya dispone de otro nombre. Un
conflictomuyhabitual.
GitHubnosmostraráclaramenteelproblema,ylaslíneas
afectadas. ¿Qué hacemos? Como siempre, podemos
quedarnos con nuestro código, el código del otro
usuario, o realizar una combinación de ambos. En este
caso utilizaremos la última opción, ya que queremos
conservar todos los nombres de los usuarios anteriores,
yañadirelúltimo,elquehaprovocadoelconflicto.
Si es un conflicto simple, podemos resolverlo
directamente desde GitHub. En este caso, añadimos
el nuevo nombre al final del archivo, y eliminamos las
marcasdeGitqueseñalanelconflicto.
Una vez resuelto el conflicto, GitHub nos permite
marcarlo como resuelto, y nos sugiere que hagamos un
commit. Estamos trabajando en la rama main, que es
donde confluye todo el código de nuestra aplicación, así
queactualizamoslaramayhacemoselmerge.

223
| Una vez | que el archivo   |     | se encuentra |     | en un | estado | en el |
| ------- | ---------------- | --- | ------------ | --- | ----- | ------ | ----- |
| que no  | posee conflicto, |     | podemos      |     | hacer | de     | la    |
|         |                  |     |              |     | merge |        | Pull  |
Request,eincluirlaennuestrorepositorioprincipal.Sino
| nosconvenceelcontenidodelaPull |                |     |          |     | Request,podemos |         |     |
| ------------------------------ | -------------- | --- | -------- | --- | --------------- | ------- | --- |
| dejar                          | un comentario, |     | cerrarla | o   | solicitar       | cambios | al  |
usuarioqueabriólaPR.
| En este | caso, aceptamos |     | la  |     | Request, | realizamos |     |
| ------- | --------------- | --- | --- | --- | -------- | ---------- | --- |
Pull
| el merge, | y cerramos   | la  | Pull  | Request. | Esta          | última     | Pull |
| --------- | ------------ | --- | ----- | -------- | ------------- | ---------- | ---- |
| Request   | desaparecerá |     | de la | lista.   | Ya no existen | conflictos |      |
ennuestrorepositorio.
| Este proceso | de                 | colaboración |                | en           | GitHub       | es sumamente  |       |
| ------------ | ------------------ | ------------ | -------------- | ------------ | ------------ | ------------- | ----- |
| útil para    | trabajar           | en           | proyectos      |              | de código    | abierto       | y     |
| equipos      | de desarrollo,     |              | ya             | que permite  |              | que múltiples |       |
| personas     | colaboren          | en           | un mismo       |              | repositorio, | aportando     |       |
| nuevas       | funcionalidades,   |              |                | solucionando |              | errores       | y     |
| mejorando    | el código.         |              | Además,        | facilita     | la           | comunicación  |       |
| entre        | los colaboradores, |              | permitiéndoles |              |              | discutir      | sobre |
| los cambios  | propuestos,        |              | y decidir      |              | en conjunto  | cuál          | es la |
mejorsoluciónparacadasituación.
| Por último, | es        | importante    |     | recordar | de      | nuevo | que     |
| ----------- | --------- | ------------- | --- | -------- | ------- | ----- | ------- |
| estos       | flujos de | colaboración, |     | como     | el Fork | y     | la Pull |

224
| Request,            | son propios | de GitHub,     | y no forman   | parte de    |
| ------------------- | ----------- | -------------- | ------------- | ----------- |
| las funcionalidades |             | de Git. Aunque | Git es        | la base del |
| sistema             | de control  | de versiones   | utilizado por | GitHub, la  |
plataformaañadesuspropiasherramientasparafacilitar
lacolaboraciónentreusuarios.

| Capítulo       |     | 39: |     |     |     |     |     |
| -------------- | --- | --- | --- | --- | --- | --- | --- |
| Sincronización |     |     |     | de  |     |     |     |
bifurcaciones
Comandos
| 1 git remote | add                    | upstream | <url_repositorio_original> |     |     |     |     |
| ------------ | ---------------------- | -------- | -------------------------- | --- | --- | --- | --- |
| 2 git fetch  | upstream               |          |                            |     |     |     |     |
| 3 git merge  | upstream/<nombre_rama> |          |                            |     |     |     |     |
Conceptos
Introducción
| Cuando                | trabajamos   | en   | un proyecto | de      | código         | abierto, |      |
| --------------------- | ------------ | ---- | ----------- | ------- | -------------- | -------- | ---- |
| es habitual           | que deseemos |      | proponer    |         | modificaciones |          | en   |
| el proyecto           | original,    | pero | no tengamos |         | permisos       |          | para |
| hacerlo directamente. |              | En   | este caso,  | podemos |                | hacer    | una  |
bifurcacióndelproyecto(Fork),ennuestrapropiacuenta
deGitHub,realizarlamodificación,yenviarunasolicitud
| deextracción(Pull |     | Request),paraqueunadministrador |     |     |     |     |     |
| ----------------- | --- | ------------------------------- | --- | --- | --- | --- | --- |
delproyectooriginalreviseloscambiosylosincorpore.
Sinembargo,puedeocurrirquemientrastrabajamosen
| nuestro | Fork, el proyecto |     | original | reciba | actualizaciones. |     |     |
| ------- | ----------------- | --- | -------- | ------ | ---------------- | --- | --- |
225

226
Si queremos enviar una PR con nuestros cambios,
necesitamos sincronizar previamente nuestro Fork con
lasactualizacionesdelproyectooriginal.
En este capítulo, aprenderemos a sincronizar un Fork
en GitHub. Este será un proceso que podremos realizar
desdelíneadecomandosodesdeelpropioGitHub.
Sincronización
Para sincronizar nuestro Fork con el repositorio original,
loprimeroquedebemoshaceresiranuestrorepositorio
enGitHub.Acontinuación,haremosclicenelbotónSync
fork.
Estaacciónnosrecuperaráloscommitsquenoposeemos
delproyectooriginal,ylosañadiráanuestroForkremoto.
Pero estos cambios aún no están incorporados en
nuestro repositorio local. Por lo tanto, el siguiente paso
será actualizar nuestro repositorio local utilizando git
pull,parareflejarasíloscambiosdelrepositoriooriginal.
Siquisiéramoshacerestomismodesdelaterminallocal,
podríamosutilizarlossiguientescomandosdeGit:
• git remote add upstream <url_repositorio_-
original> para establecer la URL del repositorio
originalasociadoalabifurcación.
• git fetch upstream para recuperar las
actualizaciones del proyecto original en nuestro
repositorio local, pero sin fusionarlas con nuestros
cambioslocales.
• git merge upstream/<nombre_rama>parafusionar
loscambiosremotosconnuestrorepositoriolocal.

227
Esteúltimocomandofusionaráloscambiosdelrepositorio
originalconnuestroscambioslocalesenlaramaenlaque
nos encontremos. Si estamos trabajando en una rama
diferente, debemos asegurarnos de cambiar a esa rama
antesdefusionar loscambios.
Una vez hemos fusionado los cambios del repositorio
original con nuestros cambios locales, el siguiente
paso es enviar los cambios a GitHub. Para hacer esto,
utilizamos, como siempre, git push. Este comando
enviará los cambios fusionados a nuestro Fork en
GitHub. Ahora ya podremos abrir una Pull Request,
para que el dueño del proyecto original revise nuestros
cambiosylosincorporeensuproyecto.
Todo el proceso detallado en la línea de
comandos puede también realizarse desde
GitHub. Te recomiendo que para ello revises la
sección asociada al curso, ya que se trata un
casopráctico.
Conclusión
En este capítulo, hemos aprendido a sincronizar un Fork
en GitHub. Para hacer esto, debemos recuperar las
actualizaciones del proyecto original a nuestro Fork,
fusionar los cambios con nuestros cambios locales, y
enviarlos a GitHub. De esta manera podremos enviar
nuevasPull Requestalproyectooriginal.

228
Curso
Lección39:mouredev.com/git-github-391
Inicio:03:53:31|Duración:00:03:22
Vamos a sincronizar un Fork en GitHub. Volvamos
a imaginar que nos encontramos en el repositorio
bifurcado, y que somos un usuario ajeno al proyecto
original. Somos aquella persona que hizo un Fork para
realizar una modificación, y enviar un commit con su
nombredeusuarioañadidoalfinaldelarchivohello.md.
Cuando echamos un vistazo al archivo hello.md desde
nuestro Fork, observaremos que únicamente tiene un
nombre,mientrasqueelrepositoriooriginalcuentayacon
varios.Estoocurrióporque,alhacerelFork,aúnnohabía
nombresañadidos.
GitHub nos informa de que nuestro Fork está
desactualizado con respecto al repositorio original. Si
quisiéramos hacer una actualización sobre nuestro
repositorio, para más adelante enviar una nueva
Pull Request, necesitamos previamente que ambos
repositoriosseencuentrensincronizados.
Para solucionarlo, GitHub proporciona una opción de
sincronización.HacemosclicenSyncfork,observandoque
nosmostraráelnúmerodecommitsquesehanrealizado
desde el nuestro. Podemos revisarlos los cambios antes
de sincronizarlos, o simplemente actualizar nuestro
repositoriopara,finalmente,estardenuevoaldía.
1https://mouredev.com/git-github-39

229
| En nuestro      | caso,     | actualizamos  |     | el         | Fork,  | y, al revisar  | el      |
| --------------- | --------- | ------------- | --- | ---------- | ------ | -------------- | ------- |
| archivo         | hello.md, | ya contaremos |     | con        | todos  | los            | nombres |
| del repositorio |           | original      | en  | nuestro    | propio | repositorio.   |         |
| Así, hemos      | logrado   | sincronizar   |     | el Fork    | con    | el repositorio |         |
| original,       | pudiendo  | volver        |     | a trabajar |        | con ambos      | de      |
maneraeficiente.
| Comprendiendo   |          | este         | flujo  | completo, | hemos      | abordado |         |
| --------------- | -------- | ------------ | ------ | --------- | ---------- | -------- | ------- |
| los fundamentos |          | de           | Git    | y GitHub, | como       | clonar   | un      |
| repositorio,    | trabajar | con          | ramas, | hacer     |            | commits  | y push, |
| entre otras     |          | cosas. Ahora |        | estamos   | preparados |          | para    |
colaborarenproyectosutilizandoestasherramientas.

| Capítulo |     | 40: | Markdown |     |     |     |     |     |
| -------- | --- | --- | -------- | --- | --- | --- | --- | --- |
Conceptos
Introducción
| Una de      | las principales |     | ventajas |             | de    | GitHub,    |            | es la |
| ----------- | --------------- | --- | -------- | ----------- | ----- | ---------- | ---------- | ----- |
| posibilidad | de documentar   |     |          | el proyecto |       | de         | una manera |       |
| atractiva,  | y fácilmente    |     | legible. | Para        |       | ello,      | se utiliza | la    |
| sintaxis    | Markdown,       | que | permite  |             | crear | documentos |            | de    |
textoenriquecidodeformasencillayelegante.
Markdown
| Markdown | es un | lenguaje |     | de marcado |     | ligero | que | se  |
| -------- | ----- | -------- | --- | ---------- | --- | ------ | --- | --- |
utilizaparadarformatoalostextos.FuecreadoporJohn
| Gruber      | en 2004, con | el             | objetivo | de        | ser | fácil de | leer      | y fácil |
| ----------- | ------------ | -------------- | -------- | --------- | --- | -------- | --------- | ------- |
| de escribir | en su        | representación |          |           | sin | formato. | En        | otras   |
| palabras,   | el formato   | debe           | estar    | contenido |     | en       | el propio |         |
documentodetexto.
| La sintaxis | de Markdown,  |        |      | permite   | utilizar |             | una        | serie |
| ----------- | ------------- | ------ | ---- | --------- | -------- | ----------- | ---------- | ----- |
| de símbolos | que           | sirven | para | formatear |          | el          | texto.     | Estos |
| elementos   | se escriben   |        | como | texto     | plano,   | y           | enriquecen |       |
| visualmente | la apariencia |        | de   | nuestros  |          | documentos. |            | La    |
mayoríadelasherramientasdeedicióndetextoycódigo
| permiten | editar | archivos | de  | Markdown |     | y   | visualizar | el  |
| -------- | ------ | -------- | --- | -------- | --- | --- | ---------- | --- |
resultadofinal.
230

231
| GitHub       | y Markdown |                |              |            |        |              |       |      |
| ------------ | ---------- | -------------- | ------------ | ---------- | ------ | ------------ | ----- | ---- |
| Ya sabemos   |            | que GitHub     |              | nos        | ofrece | una          | serie | de   |
| herramientas |            | para facilitar |              | el trabajo |        | colaborativo |       | en   |
| equipo.      | Una        | de estas       | herramientas |            | es     | el soporte   |       | para |
archivosdedocumentaciónenestiloMarkdown.
GitHubnocreóMarkdown,perosíaceptagranpartedesu
estándar.Dehecho,GitHubofrecedocumentaciónsobre
| la sintaxis    | de          | Markdown | que         | acepta. | Así,       | podremos |     | ver |
| -------------- | ----------- | -------- | ----------- | ------- | ---------- | -------- | --- | --- |
| cómo formatear |             | el texto | para        | que     | se muestre |          | de  | una |
| manera         | específica. | Solo     | necesitamos |         | un         | archivo  | .md | y   |
trabajarconestasintaxis.
| El archivo    | README.md, |                   | siempre      |     | que se      | encuentre      |           | en  |
| ------------- | ---------- | ----------------- | ------------ | --- | ----------- | -------------- | --------- | --- |
| la raíz       | de nuestro |                   | repositorio, |     | actuará     | como           | página    |     |
| principal     | de         | este, conteniendo |              | la  | información |                | principal |     |
| del proyecto. |            | Debemos           | tener        | en  | cuenta      | la importancia |           |     |
| de documentar |            | adecuadamente,    |              |     | añadiendo   | siempre        |           | un  |
archivoREADME.mdalproyecto.Porotraparte,podemos
| tener tantos |     | archivos | .md | como queramos, |     | enlazarlos |     | y   |
| ------------ | --- | -------- | --- | -------------- | --- | ---------- | --- | --- |
navegarentreellos.
| Aquí    | puedes |             | consultar | la     | documentación |         |     |     |
| ------- | ------ | ----------- | --------- | ------ | ------------- | ------- | --- | --- |
| oficial |        | de Markdown |           | dentro | de            | GitHub: |     |     |
docs.github.com/es/get-started/writing-
on-github/getting-started-with-writing-and-
formatting-on-github/quickstart-for-writing-
on-github1
1https://docs.github.com/es/get-started/writing-on-github/getting-started-
with-writing-and-formatting-on-github/quickstart-for-writing-on-github

232
Sintaxis
A continuación, vamos a descubrir los elementos más
comunesasociadosalasintaxisdeMarkdown:
• Encabezados: se utilizan para indicar el nivel del
título. Se escriben con el símbolo almohadilla (#)
seguidodeunespacioyeltextodeltítulo.
• Énfasis: se utiliza para enfatizar el texto. Se puede
utilizarelasterisco(*),oelguionbajo(_).
• Listas:seutilizanparapresentarelementosenuna
lista. Se pueden crear listas numeradas utilizando
números (1.), o no numeradas usando el asterisco
(*).
• Enlaces: se utilizan para crear links a otras páginas
de Markdown o recursos web. Se escriben con el
texto del enlace entre corchetes [], y la URL entre
paréntesis().
• Imágenes: se utilizan para insertar imágenes
en el documento. Se escriben con un signo de
exclamación (!), seguido del texto alternativo entre
corchetes [] y la URL de la imagen entre paréntesis
().
• Citas: seutilizan paracitar texto.Se escribencon el
símbolomayorqué(>)seguidodeltextocitado.
• Código: se utiliza para resaltar fragmentos de
código dentro del texto. Se pueden utilizar las
comillassimples(‘)olascomillastriples(“‘).
• Tablas: se utilizan para crear tablas en el
documento. Se escriben con las columnas
separadas por tuberías (|), La primera fila se
utilizaparaindicarlacabeceradelatabla.
Poseemos muchos más elementos, pero estos son los
máscomunesyútilesparadarformatoaundocumento.

233
Existen distintas herramientas para trabajar con la
sintaxisdeMarkdown,ayudarnosaescribirla,yvisualizar
laaparienciadeldocumentoentiemporeal.
Documentación
No nos limitamos a usar GitHub solo para subir
código. Debemos documentarlo, hacerlo atractivo y
comprensible. Ya hemos visto, en uno de los primeros
capítulos sobre GitHub, cómo podemos personalizar la
página de inicio de nuestro perfil o proyecto. De esta
forma podemos añadir información adicional sobre
nosotrosonuestrosproyectos.
HTML
Los archivos .md, aparte de la sintaxis propia de
Markdown, también aceptan regularmente HTML.
Tengámoslo en cuenta para realizar presentaciones
más complejas. Si necesitamos dar formato a una
sección de nuestro documento, que no es posible con
la sintaxis de Markdown, podemos utilizar HTML para
lograrlo.
Conclusión
Los archivos de documentación en estilo Markdown, son
una forma sencilla y elegante de documentar nuestros
desarrollos.GitHubofrecesoporteparaMarkdown,ynos
permitepersonalizarlapáginadeiniciodenuestroperfil,
asícomocadaunodenuestrosproyectos.

234
Curso
Lección40:mouredev.com/git-github-402
Inicio:03:56:53|Duración:00:03:44
| Recordemos    |           | que        | la forma | más         | común        | de            | crear    |
| ------------- | --------- | ---------- | -------- | ----------- | ------------ | ------------- | -------- |
| documentación |           | en         | GitHub   | es          | usando       | archivos      | con      |
| formato       | Markdown. |            | De ahí   | que         | la extensión | del           | fichero  |
| README        | sea.md.   |            |          |             |              |               |          |
| Si revisamos  |           | los .md    | del      | proyecto,   | podremos     |               | observar |
| que poseen    |           | una        | serie de | símbolos    |              | que sirven    | para     |
| formatear     | el        | texto.     | Estos    | elementos   | se           | escriben      | como     |
| texto plano   | y         | enriquecen |          | visualmente |              | la apariencia | de       |
nuestrosdocumentosdemanerasencillayelegante.
| Markdown   | no           | ha sido   | creado    | por  | GitHub,   | pero      | sí acepta  |
| ---------- | ------------ | --------- | --------- | ---- | --------- | --------- | ---------- |
| gran parte | de           | su        | estándar. | De   | hecho,    | siempre   | que el     |
| archivo    | llamado      | README.md |           | se   | encuentre | en        | la raíz de |
| nuestro    | repositorio, |           | actuará   | como | página    | principal | de         |
este,conteniendolainformaciónprincipaldelproyecto.
2https://mouredev.com/git-github-40

235
Sobradecirque,sinosgustaelformatodealgúnfichero
| de Markdown  | que encontremos |              | en GitHub, | podemos |
| ------------ | --------------- | ------------ | ---------- | ------- |
| inspeccionar | su código       | y utilizarlo | en nuestra | propia  |
documentación.

GIT y GITHUB
| Sección dedicada | al estudio | conjunto | de Git y |
| ---------------- | ---------- | -------- | -------- |
GitHub
236

Capítulo 41:
Herramientas gráficas
Conceptos
Introducción
Durante todo el curso, hemos aprendido a usar Git a
través de la línea de comandos, sin embargo, existen
herramientas gráficas (GUI), que pueden facilitar
el proceso y adaptarse mejor a nuestra manera de
trabajar. En este capítulo, hablaremos de algunas de las
herramientas gráficas más populares para trabajar con
GityGitHub,comoGitHubDesktop,GitKraken,SourceTree,
Fork yprincipaleseditoresdecódigo.
GitHub Desktop
GitHub Desktop es una herramienta creada por el
equipo de GitHub, que permite trabajar con Git y
GitHub de manera visual. Es compatible con varios
sistemas operativos, y puede ser una gran opción para
familiarizarseconelmundodelasherramientasgráficas
yelflujodeGitHub.
PuedesdescargarGitHubDesktopdesdedesktop.github.com1
1https://desktop.github.com
237

238
GitKraken
GitKraken es una herramienta gráfica más profesional,
que ofrece muchas funcionalidades y puede ser una
buena opción para trabajar con varios repositorios y
equipos de desarrollo. Al abrir la herramienta se puede
apreciar el árbol de navegación que representa el
historial de Git. Podemos pasar los cambios al área de
Stage con solo presionar un botón y luego realizar un
commityunpush.
GitKraken es de uso gratuito en repositorios públicos,
contando con una versión de pago para interactuar con
repositoriosprivados.
PuedesdescargarGitKrakendesde.gitkraken.com2
SourceTree y Fork
Otras herramientas gráficas populares, para trabajar
con Git y GitHub, son SourceTree y Fork. SourceTree es
una herramienta desarrollada por Atlassian (creadores
de Jira), que permite trabajar con repositorios públicos y
privadosdeformagratuita,mientrasqueForkesdepago
entodassusversiones.
PuedesdescargarSourceTreedesde.sourcetreeapp.com3
PuedesdescargarFork desdegit-fork.com4
2https://.gitkraken.com
3https://.sourcetreeapp.com
4https://git-fork.com

239
Editores de código
Los editores de código (IDE) más populares, como Visual
Studio Code o IntelliJ IDEA, entre otros muchos, también
incluyen una GUI para interactuar desde ellos con Git o
GitHub,sinnecesidaddeutilizarherramientasexternas.
Conclusión
Las herramientas gráficas (GUI), como GitHub Desktop,
GitKraken,SourceTreeyFork,ofrecenunaalternativavisual
y fácil de usar frente a la terminal. Sin embargo, es
fundamental comprender primero cómo funcionan los
comandosdeGitylalíneadecomandos,antesdedecidir
quéherramientautilizar.Lomásimportanteesencontrar
la herramienta o enfoque que nos permita trabajar de
maneraeficienteycómodaennuestrosproyectos.
No hay una opción correcta o incorrecta, ya que cada
herramientatienesuspropiasventajaseinconvenientes.
Es importante evaluar las necesidades del proyecto y el
estilo de trabajo personal para decidir qué herramienta
utilizar.Engeneral,lasherramientasgráficaspuedenser
una excelente opción para aquellos que prefieren una
experiencia más visual y fácil o rápida de manejar, en
lugar de trabajar únicamente con comandos desde la
terminal.
Curso
Lección41:mouredev.com/git-github-415
5https://mouredev.com/git-github-41

240
Inicio:04:00:37|Duración:00:17:59
Recordemosque,alprincipiodelcursomencionamoslas
distintas formas de trabajar con Git. Durante todo su
transcurso,aprendimosausarGitatravésdelalíneade
comandos, la terminal. No obstante, no solo podemos
trabajar con la línea de comandos, sino también con
herramientasgráficas(GUI).
No se trata de si debemos usarlas o no, ya que es algo
muypersonal.Debemosconsideraresencialentenderlos
fundamentos de Git y GitHub, así como qué hace cada
comando de Git, o cada acción del flujo colaborativo de
GitHub.Sillegamosaestepuntodelcurso,yentendemos
dichos conceptos básicos, será el momento de conocer
más alternativas. Algunas personas prefieren la consola,
mientrasqueotrasoptanporherramientasgráficas.Por
algoexistenambasopciones.
Lo importante no es cómo trabajamos, sino que
entendamos por qué lo hacemos de esa manera. No
hagamos caso a quienes nos dicen que los verdaderos
programadores utilizan únicamente la consola. Eso
no es cierto. Tenemos a disposición gran variedad de
herramientas. Nuestra es la responsabilidad de conocer
lasopciones,valorarlasyelegirlaquemejorseadaptaa
nuestrasnecesidades.
Dicho esto, yo soy una de las personas que
trabaja habitualmente con herramientas
gráficas. Combinando esta en algunos casos
con la terminal. Esto no significa que no
sepa hacerlo todo desde la terminal, pero
me resulta más productivo utilizando la GUI.
Lo importante no es recordar comandos
complejos,sinotenerclaroelflujodetrabajo.

241
Vamosadescubrirunpocomássobreellas.
Enprimerlugar,nopensemosque,alusarherramientas
| gráficas,       | no tendremos     |              | que     | aprender   | nada.        |         | No se trata |
| --------------- | ---------------- | ------------ | ------- | ---------- | ------------ | ------- | ----------- |
| de eso.         | Las herramientas |              |         | gráficas   | pueden       | ayudar, | pero        |
| su propósito    |                  | no es evitar | que     | entendamos |              | Git     | o GitHub.   |
| Son simplemente |                  | otra         | forma   |            | de trabajar, |         | que podría  |
| adaptarse       | mejor            | a            | nuestro | flujo      | operativo,   |         | o nuestra   |
maneradeinteractuarconunproyecto.
| Existen         | muchas | herramientas |     |         | gráficas | muy | utilizadas, |
| --------------- | ------ | ------------ | --- | ------- | -------- | --- | ----------- |
| pero hablaremos |        | solo         | de  | algunas | de las   | más | comunes,    |
empezandoporlaoficialdeGitHub.
| Se llama | GitHub | Desktop, | y   | ha sido | creada | por | el equipo |
| -------- | ------ | -------- | --- | ------- | ------ | --- | --------- |
deGitHub.Noeslamásprofesional,perosíresultamuy
útilparafamiliarizarseconelmundodelasherramientas
gráficas,yaqueincluyeloscomandosmáshabituales.Es
| compatible | con | varios | sistemas |     | operativos, |     | y podemos |
| ---------- | --- | ------ | -------- | --- | ----------- | --- | --------- |
encontrartodasudocumentaciónenlaweboficial.
| Entender      | cómo | funciona |      | Git,    | facilita |     | el uso de  |
| ------------- | ---- | -------- | ---- | ------- | -------- | --- | ---------- |
| herramientas  |      | gráficas | como | GitHub  | Desktop. |     | Una vez    |
| que iniciemos |      | sesión   | con  | nuestra | cuenta   |     | de GitHub, |

242
podremos ver todos los repositorios asociados. Esta
herramienta es ideal para trabajar en conjunto con todo
el flujo de GitHub. Aunque es útil para tareas rápidas,
puede quedarse corta en flujos más complejos. Sin
embargo, como herramienta de aprendizaje, es muy
buena.
Con GitHub Desktop podemos clonar repositorios
automáticamenteconsolounosclics.Tambiénpodemos
agregar repositorios locales. En la ventana principal
visualizaremos el historial de commits, similar al log de
Git, pero de forma gráfica. Al hacer clic en un commit,
podremos ver rápidamente los archivos modificados y
loscambiosrealizados.
Parahacercambiosenunarchivo,loabrimosennuestro
editor de código, lo modificamos y lo guardamos. A
continuación, GitHub Desktop mostrará los cambios
realizados.Enlugardeusarcomandosdesdelaconsola,
simplementedebemosmarcarelcheckparapasardichos
cambiosaláreadeStage.
Ya solo nos quedará añadir un comentario y subir
los cambios. Si no tenemos permisos para hacerlo,
GitHub Desktop nos sugerirá hacer un Fork. Además,
podemos cambiar entre diferentes ramas, y consultar
Pull Requestasociadasdesdelaherramienta.
Las herramientas gráficas como GitHub Desktop pueden
ser potentes y facilitar la visualización del estado de
nuestrotrabajo,proporcionándonosmuchainformación
desde un solo lugar. Aunque no reemplazan por
completo el conocimiento de Git y la consola, ofrecen
otra forma de trabajar que podría adaptarse mejor a
nuestrasnecesidades.
Es cierto que GitHub Desktop tiene ciertas limitaciones,
por eso, veremos a continuación algunas herramientas

243
| gráficas | más potentes, |     | que | podrían |     | ser de | nuestro |
| -------- | ------------- | --- | --- | ------- | --- | ------ | ------- |
interés.
| Personalmente, |     |              | mi favorita |     | es         | GitKraken, |     |
| -------------- | --- | ------------ | ----------- | --- | ---------- | ---------- | --- |
| pero           | no  | es necesario |             | que | utilicemos | esta       |     |
herramientaenconcreto.
| GitKraken | es una           | herramienta |                 | gráfica  |     | profesional | con      |
| --------- | ---------------- | ----------- | --------------- | -------- | --- | ----------- | -------- |
| muchas    | funcionalidades. |             | Al              | abrirla, | se  | puede       | apreciar |
| el árbol  | de navegación    |             | correspondiente |          |     | a todos     | los      |
| commits   | realizados.      | Podemos     |                 | pasar    | los | cambios     | al área  |
deStageconsolopresionarunbotón,paraluegorealizar
uncommityunpush.
GitKrakentambiénpermitetrabajarcondiferentesramas,
| yvisualizarlasPull |            | Requestasociadas.Inclusopodemos |          |        |           |              |     |
| ------------------ | ---------- | ------------------------------- | -------- | ------ | --------- | ------------ | --- |
| resolver           | conflictos | en                              | PR desde | la     | propia    | herramienta. |     |
| Además,            | es posible | revisar                         | las      | Issues | abiertas, | junto        | con |
otrascaracterísticascomotagsysubmódulos.

244
Esta herramienta facilita la visualización y el trabajo
proporcionando mucha información sin necesidad de
utilizar comandos. GitKraken puede ser útil cuando
se necesita mantener el control y acceso rápido a la
informacióndelsistemadecontroldeversiones.
El funcionamiento de GitKraken es fácil de entender,
una vez que nos familiarizamos con la ubicación de las
acciones y opciones. Por ejemplo, para agregar cambios
al área de Stage, simplemente lo haremos presionando
el botón Stage. El uso de herramientas gráficas como
GitKraken puede ser una excelente alternativa para
quienes buscan una experiencia más visual y fácil o
rápida de manejar, en lugar de trabajar únicamente con
comandosenlaterminal.
Seguimos en GitKraken y queremos subir unos cambios,
¿cómoañadimosuntítulo,similaralcomandogit commit
-m?Simplemente,agregamosuncomentarioenSummary
y Description (si deseamos un comentario más extenso).
Para hacer el commit, solo tenemos que presionar el
botóncorrespondiente.Acontinuación,paraelpush,más
delomismo.

245
Inclusopodemosabrirlaterminal desdelaherramienta,
si deseamos ejecutar comandos o revisar registros.
También nos permite deshacer y rehacer cambios
fácilmente. Si trabajamos en varios proyectos, la
herramienta nos da la posibilidad de cambiar entre
ellos,abriendocadaunoenpestañasdiferentes.
GitKraken es de uso gratuito en repositorios públicos,
contando con una versión de pago para interactuar con
repositoriosprivados.
Dejando de lado GitKraken, debemos conocer otras
opciones, como SourceTree, desarrollado por Atlassian
(creadores de Jira), que permite trabajar con repositorios
públicos y privados de forma gratuita. Otra herramienta
popular es Fork, que en este caso de pago en todas sus
versiones.

246
| Estas       | herramientas |         | gráficas     | nos      | permiten | ejecutar       | las |
| ----------- | ------------ | ------- | ------------ | -------- | -------- | -------------- | --- |
| funciones   | más          | comunes | de           | Git con  | tan      | solo presionar |     |
| botones.    | Aunque       |         | puede haber  |          | casos    | en los que     | se  |
| prefiera    | utilizar     | la      | terminal     | para     | tareas   | específicas,   |     |
| en general, |              | estas   | herramientas |          | cubren   | todas          | las |
| operaciones | comunes      |         | de Git,      | y añaden | la       | posibilidad    | de  |
interactuarconGitHubsinaccederalaplataforma.
| Antes      | de probar      |     | herramientas |           | gráficas, | es           | muy |
| ---------- | -------------- | --- | ------------ | --------- | --------- | ------------ | --- |
| importante | entender       |     | cómo         | funcionan |           | los comandos |     |
| de Git     | y su uso       | en  | la terminal. | Hecho     | esto,     | podremos     |     |
| elegir     | la herramienta |     | que          | mejor     | se adapte | a nuestras   |     |
necesidades,inclusoutilizandoambasopcionesalavez.
Miconsejo,pruebayjuzga.

| Capítulo |     |     | 42: | Git | y   | GitHub |     |     |
| -------- | --- | --- | --- | --- | --- | ------ | --- | --- |
flow
Comandos
| 1 git flow | init    |     |        |                  |     |     |     |     |
| ---------- | ------- | --- | ------ | ---------------- | --- | --- | --- | --- |
| 2 git flow | feature |     | start  | <nombre_feature> |     |     |     |     |
| 3 git flow | feature |     | finish | <nombre_feature> |     |     |     |     |
| 4 git flow | release |     | start  | <nombre_release> |     |     |     |     |
| git flow   | release |     | finish | <nombre_release> |     |     |     |     |
5
| git flow | hotfix | start |     | <nombre_hotfix> |     |     |     |     |
| -------- | ------ | ----- | --- | --------------- | --- | --- | --- | --- |
6
| git flow | hotfix | finish |     | <nombre_hotfix> |     |     |     |     |
| -------- | ------ | ------ | --- | --------------- | --- | --- | --- | --- |
7
Conceptos
Introducción
| En este       | capítulo      |           | ampliaremos  |               | los             | conceptos         | sobre     |     |
| ------------- | ------------- | --------- | ------------ | ------------- | --------------- | ----------------- | --------- | --- |
| Git y         | GitHub,       | y nos     | centraremos  |               |                 | en la importancia |           |     |
| de establecer |               | un        | flujo        | de desarrollo |                 | profesional       |           | con |
| ambas         | herramientas. |           | Hablaremos   |               | específicamente |                   |           | del |
| flujo de      | trabajo       | llamado   |              | GitFlow,      |                 | el más            | utilizado | y   |
| recomendado   |               | por       | su eficacia. |               | Por             | supuesto,         | existen   |     |
| muchas        | más           | opciones, | pero         | GitFlow       |                 | es, sin duda,     | el        | más |
utilizado.
247

248
Comoeshabitual,lamejoropciónsiempreesprofundizar
más sobre el tema, para acabar encontrando así el tipo
de flujo de desarrollo que mejor se adapta a nuestras
necesidades.
GitFlow
GitFlow es un flujo de trabajo para Git, que se ha
convertido en uno de los más populares y utilizados en
desarrollo de software. Este flujo de trabajo fue creado
por Vincent Driessen en 2010, y se basa en la creación de
diferentes ramas para organizar y gestionar el trabajo
delequipodedesarrollo.
GitFlow se basa en dos ramas principales: la rama
develop, y la rama main. La rama develop se utiliza para
integrar las características y correcciones de errores que
están en desarrollo, mientras que la rama main es la
ramaquecontieneelcódigoqueestáenproducción.
Además, GitFlow utiliza principalmente tres tipos de
ramasparafacilitarelflujodetrabajo:
• Feature (ramas de características): Se utilizan
para desarrollar nuevas características o
funcionalidades. Se crean a partir de la rama
develop,ysefusionandenuevoenellaunavezque
lacaracterísticaestáterminada.
• Release (ramas de lanzamiento): Se utilizan para
preparar una nueva versión de la aplicación para el
despliegue. Se crean a partir de la rama develop, y
se fusionan tanto en la rama main como en la rama
developunavezquelaversiónestádesplegada.
• Hotfix (ramas de corrección de errores): Se
utilizanparasolucionarerrorescríticosenlaversión

249
de producción. Se crean a partir de la rama main, y
se fusionan tanto en la rama main como en la rama
developunavezquesehasolucionadoelerror.
GitFlow facilita la organización del trabajo en equipo y
ayuda a mantener un historial limpio y ordenado de los
cambios en el repositorio. Además, permite un control
másgranularsobrelasdiferentesfasesdeldesarrollode
unaaplicación,yfacilitalaintegracióndeloscambiosen
elproyecto.
Conclusión
Es importante tener en cuenta que GitFlow es un flujo
de trabajo ampliamente utilizado y respaldado por
empresas como GitKraken y Atlassian (entre muchísimas
otras), pero no es la única opción disponible. Es
recomendable evaluar si GitFlow se adapta a las
necesidadesdenuestroproyecto,ysitodoslosmiembros
del equipo están dispuestos a seguir las convenciones y
reglasestablecidasporesteflujodetrabajo.
También debemos conocer que GitFlow puede
combinarse con el flujo de trabajo de GitHub, en
especial con sus mecanismos de Pull Request o Issues,
para que así el equipo pueda validar el código antes de
reintegrar cambios locales en ramas remotas, o llevar un
controldeloserroresdetectados.
GitFlow es uno de los flujos de trabajo más utilizados y
recomendados,yutilizaramasespecíficasparacadatipo
de trabajo, lo que facilita el seguimiento y la integración
deloscambiosenelproyecto.
MásimportantequeutilizarGitFlow,esconocer

250
laexistenciadedistintosflujosdecolaboración
basados en unas normas, y que pueden
ayudarnosagestionarnuestroproyecto.
Curso
Lección42:mouredev.com/git-github-421
Inicio:04:18:36|Duración:0:16:39
Es momento de hablar de algún mecanismo para
establecer flujos de desarrollo profesionales con Git y
GitHub.
¿Qué hemos hecho hasta ahora en el curso?
Fundamentalmente hemos trabajado con rama main
y creado algunas adicionales. Pero este flujo de trabajo
en la rama main, creando ramas, realizando cambios, y
másadelantemerge,puedeparecerunpocoaleatorio.Es
decir, parece seguir una regla que solo se basa en crear
ramas y reintegrar sus modificaciones. Pensemos en un
equipo real, con necesidades propias de un producto de
software.
¿Cuáles pueden ser las necesidades de un equipo?
Básicamente, podemos partir de que lo que tengamos
en la rama principal (main) es el código que se está
desplegandoenproducción.
Imaginemos que nos encontramos en un equipo que
trabaja en una característicade la app, y adicionalmente
existe otro que está llevando a cabo una refactorización
del proyecto, y otro equipo más que trabaja en una
1https://mouredev.com/git-github-42

251
evolución diferente. De repente, aparece un error en
producciónquetenemosquecorregirconurgencia.Pero,
¿qué hacemos? ¿Corregimos ese error mientras se sigue
evolucionando la app? ¿Tenemos que esperar a que estén
listoslosnuevoscambios?
Para eso existen diferentes flujos de trabajo
profesionales. Existen muchos estudios, artículos, y
formasdetrabajarconGityGitHub.Enestecaso,vamos
a hablar de la más común, y, seguramente, la más
utilizada. Aunque, si somos capaces de establecer unas
normas, incluso podemos inventar cuál es el flujo de
trabajoquequeremosutilizarennuestroproyecto.
Personalmente, recomendaría que, aunque estemos
trabajandoenunproyectopropio,sigamosunasnormas
y establezcamos un orden a la hora de evolucionar
nuestrosoftware,evitando,antetodo,laaleatoriedaden
nuestrosistemadecontroldeversiones.
¿Cuál es el estándar del que hablaremos? No vamos a
entrar en discusiones. Cada persona tiene que utilizar lo
que mejor se adapte a sus necesidades. Dicho esto, el
flujodedesarrollomásutilizadoeselllamadoGitFlow.
Básicamente, tenemos dos grandes formas de trabajar
con Git y GitHub: el GitFlow, y el GitHub Flow. Estos flujos
establecen reglas a la hora de trabajar para que, si
todos las conocemos, el proyecto evolucione de manera
estructuradayseevitenproblemas.
El equipo de GitKraken, la herramienta visual de la que
hemos hablado en la lección anterior, y una empresa
dedicada a mejorar el flujo de trabajo con Git y con
GitHub,apoyaaGitFlow.
Hechaestareflexión,vamosacomenzaraexplorarcómo
usaresteflujodetrabajoenlapráctica.

252
Primero, debemos asegurarnos de instalar el plugin
de GitFlow en nuestro sistema. Podemos encontrar
instrucciones de instalación en la documentación,
o buscar tutoriales específicos para cada sistema
operativo.
Básicamente:
• macOS:brew install git-flow-avh
• Linux:apt-get install git-flow
• Windows: contenido en instalación de Git git-
scm.com/download/win2
Para no redundar en explicaciones, revisa la
sección de conceptos en este capítulo, ya que
allí detallamos cuál es la filosofía detrás de
GitFlow,susramasysuuso.
Una vez instalado GitFlow, podremos comenzar
a utilizarlo en nuestro proyecto. A continuación,
conozcamos algunos comandos básicos de GitFlow
quenosayudaránaempezar.
• Para inicializar GitFlow en nuestro repositorio: git
flow init
Este comando configurará nuestro repositorio para usar
Gitflow, estableciendo la rama principal, creando la rama
develop si no existe, y fijando las convenciones de
nombresparalasramasFeature,ReleaseyHotfix.
2https://git-scm.com/download/win

253
| • Para          | comenzar |     | a   | trabajar | en      | una | nueva |
| --------------- | -------- | --- | --- | -------- | ------- | --- | ----- |
| característica: |          |     | git | flow     | feature |     | start |
<nombre_feature>
| Este comando |          | creará | una   | nueva   | rama   | Feature | a partir |
| ------------ | -------- | ------ | ----- | ------- | ------ | ------- | -------- |
| de la rama   | develop, |        | y nos | situará | en esa | rama    | para que |
podamoscomenzaratrabajarenlanuevacaracterística.
| • Para | finalizar | la     | nueva | característica   |     | (Feature): | git |
| ------ | --------- | ------ | ----- | ---------------- | --- | ---------- | --- |
| flow   | feature   | finish |       | <nombre_feature> |     |            |     |
Cuandohayamoscompletadolacaracterística,yestemos
| preparados | para         | integrarla |          | en la    | rama    | develop, | este    |
| ---------- | ------------ | ---------- | -------- | -------- | ------- | -------- | ------- |
| comando    | se encargará |            | de       | fusionar | la      | rama     | de tipo |
| Feature    | en develop,  |            | eliminar | la rama  | Feature | y        | cambiar |
nuestroentornodetrabajodevueltaalaramadevelop.

254
| • Comenzar |                  | una | rama Release: |     |     |              |     |
| ---------- | ---------------- | --- | ------------- | --- | --- | ------------ | --- |
|            |                  |     |               |     | git | flow release |     |
| start      | <nombre_release> |     |               |     |     |              |     |
EstecomandocreaunaramadetipoReleaseapartirdela
ramadevelop,paraquepodamospreparareldespliegue
aproducción.
| • FinalizarlaramaRelease:git |     |     |     | flow | release |     | finish |
| ---------------------------- | --- | --- | --- | ---- | ------- | --- | ------ |
<nombre_release>
| Cuando       | estemos   | preparados |             | para     | desplegar, |         | este    |
| ------------ | --------- | ---------- | ----------- | -------- | ---------- | ------- | ------- |
| comando      | fusionará | la         | rama        | de tipo  | Release    | en      | la rama |
| main, creará |           | un nuevo   | tag         | en main, | fusionará  |         | la rama |
| Release      | en        |            | y eliminará | la       | rama       | Release | (de así |
develop
desearlo).

255
• ComenzarunaramaHotfix:git
|     |     |     | flow | hotfix start |
| --- | --- | --- | ---- | ------------ |
<nombre_hotfix>
| Este comando | crea | una rama | Hotfix | a partir de la rama |
| ------------ | ---- | -------- | ------ | ------------------- |
main,paraquepodamosasísolucionarerrorescríticosen
laversiónenproducción,conindependenciadelasramas
detipoFeature.
| • Finalizar | la rama | Hotfix: | git flow | hotfix finish |
| ----------- | ------- | ------- | -------- | ------------- |
<nombre_hotfix>
| Una vez que | hayamos | corregido | el error, | este comando |
| ----------- | ------- | --------- | --------- | ------------ |
fusionarálaramadetipoHotfixenlaramamain,crearáun
| nuevotagenmain,fusionarálaramaHotfix |     |     |     | endevelopy |
| ------------------------------------ | --- | --- | --- | ---------- |
eliminarálaramaHotfix.

256
Estos comandos básicos de GitFlow nos permitirán
comenzaratrabajarconesteflujodetrabajoennuestro
proyecto. A medida que nos familiaricemos con GitFlow,
podremos explorar comandos adicionales y ajustar el
flujodetrabajosegúnnuestrasnecesidades.
Aunque puede parecer complejo, la mejor manera de
entender GitFlow es practicándolo, revisando diagramas
ydocumentaciónparafamiliarizarnosconelproceso.
Ahora bien, ¿cómo podemos asegurarnos de estar
trabajando correctamente con GitFlow? Podríamos
hacerlo a nivel teórico, creando manualmente las
ramas necesarias, pero esto requeriría recordar todos
los pasos y ramas involucradas, con el riesgo que eso
puedeconllevar.
Parafacilitaresteproceso,existenherramientasenlínea
de comandos como la que hemos utilizado. Incluso
algunas herramientas gráficas, vistas en la lección
anterior, tienen soporte nativo para utilizar GitFlow
desdeellas.
El plugin de GitFlow nos ayuda a gestionar

257
automáticamenteelflujodetrabajo,abriendoycerrando
| ramas            | según    | sea            | necesario,    | asegurándose |           |                  | de que los |
| ---------------- | -------- | -------------- | ------------- | ------------ | --------- | ---------------- | ---------- |
| cambios          | se       | integren       | correctamente |              |           | en               | las ramas  |
| relevantes.      | Al       | utilizar       | este          | plugin,      | podemos   |                  | centrarnos |
| en el desarrollo |          | de             | nuestro       | proyecto     |           | sin preocuparnos |            |
| por los          | detalles | de             | la gestión    |              | de ramas, | simplificando    |            |
| el proceso       | y        | permitiéndonos |               | trabajar     |           | de manera        | más        |
eficienteycoordinada.
| Me               | gustaría | también |                        | reflejar | que | el flujo | de  |
| ---------------- | -------- | ------- | ---------------------- | -------- | --- | -------- | --- |
| trabajodeGitFlow |          |         | puedecombinarseconelde |          |     |          |     |
GitHub.Enespecial,consumecanismodePull
| Request, |        | para | que así | sea   | el propio | equipo     | el  |
| -------- | ------ | ---- | ------- | ----- | --------- | ---------- | --- |
| que      | valide | el   | código  | antes | de        | reintegrar | los |
cambioslocalesenramasremotas.
Antetodo,queremosgenerarunflujodetrabajoágilyde
calidad.

Capítulo 43: Ejemplo
GitFlow
Conceptos
Introducción
GitFlow es una metodología de desarrollo de software
que se basa en el uso de Git para manejar diferentes
tipos de ramas y flujos de trabajo durante el desarrollo
de un proyecto. Es especialmente útil en proyectos
complejos con múltiples colaboradores y diversas fases
dedesarrollo.
Una de las principales ventajas de GitFlow es que nos
permite organizar el desarrollo de nuestro proyecto
en diferentes tipos de ramas, cada una con su propio
propósitoyflujodetrabajo.Estostiposderamasson:
• Rama principal (main): es la rama donde se
encuentraelcódigoqueseenviaráaproducción.
• Ramadedesarrollo(develop):eslaramadondese
realizaeldesarrollodelproyecto.
• Ramas de características (Feature): son un tipo
de ramas que se crean para desarrollar nuevas
característicasenelproyecto.
• Ramas de lanzamiento (Release): son un tipo de
ramasquesecreanparaprepararunanuevaversión
delproyectoqueseenviaráaproducción.
258

259
• Ramas de corrección de errores (Hotfix): son un
tipo ramas que se crean para solucionar errores
críticosenlaversióndeproduccióndelproyecto.
Cadaunodeestostiposderamastienesupropioflujode
trabajo,yGitFlow nosayudaamanejarautomáticamente
la creación, fusiones y eliminación de estas ramas de
maneraordenadaysegura.
Enestecapítuloreflejamosunejemplopráctico
atravésdelcurso.
Uso
Para implementar GitFlow en nuestro proyecto, lo
primero que debemos hacer es instalar la herramienta
GitFlow en nuestro sistema operativo, como hemos
exploradoenelcapítuloanterior.
Una vez instalado, podemos iniciar el uso de GitFlow
en nuestro proyecto con el comando git flow init.
Este comando nos guiará para configurar las ramas
principales y los prefijos que utilizaremos en los
diferentestiposramasdenuestroproyecto.
Una vez que GitFlow está activado en nuestro proyecto,
podremos crear ramas de características, lanzamiento o
corrección de errores utilizando los comandos git flow
feature start, git flow release start y git flow
hotfix start, respectivamente. También podemos
fusionarestasramasconlasramasprincipales,utilizando
los comandos git flow feature finish, git flow
release finishygit flow hotfix finish.

260
Conclusión
Es importante recordar que GitFlow es solo una
herramienta, y que cada equipo de desarrollo debe
adaptar el flujo de trabajo a sus necesidades y
objetivos. Además, todos los miembros del equipo
deben estar familiarizados con GitFlow y utilizar las
mismas convenciones y prácticas para evitar conflictos y
erroresduranteeldesarrollodelproyecto.
Curso
Lección43:mouredev.com/git-github-431
Inicio:04:35:15|Duración:00:17:34
LlevemosalaprácticaelusodeGitFlow.
Desdelaterminal,yusandoelproyectoconelquehemos
trabajado durante el curso, vamos a implementar el uso
deGitFlow eneste.
LoprimeroseráinstalarGitFlow.Dependiendodenuestro
sistemaoperativo,tendremosquehacerlodeunauotra
forma. Ya hemos hablado de ello en el capítulo anterior,
pero siempre podemos buscar más información sobre
él. Esta utilidad nos permitirá utilizar GitFlow desde la
terminal.
Ya instalado, y situados en el directorio de nuestro
proyecto, recordemos que este aún no posee soporte
hacerusodeGitFlow,asíquevamosaañadirlo.
Para lanzar comandos de GitFlow desde la terminal
lo haremos utilizando el texto git flow, seguido
1https://mouredev.com/git-github-43

261
de diferentes rutinas. Las más habituales son:
init, feature, release, config, support y version.
No hablaremos support ya que se utiliza menos.
Básicamente nos vamos a enfocar en los aspectos más
habituales.
Si quisiéramos abrir una rama de tipo Feature, no
podríamos, ya que nuestro repositorio aún no está
trabajando con GitFlow. Para empezar a trabajar con él,
escribiremosgit flow init.Nosapareceráunasistente,
y nos preguntará por el nombre de la rama principal,
obligándonos a seguir ciertas normas. La rama principal
serálaqueposeaelcódigoaenviaraproducciónycrear
versiones a partir de ella. En nuestro caso será la rama
main.
Acontinuación,nospreguntarácómosellamarálarama
de desarrollo. Queremos que sea develop. Después,
se detendrá en los prefijos asignados a las ramas de
Feature, Release, Hotfix y Support. Dejamos los nombres
por defecto. También nos pregunta si queremos que los
tagtenganalgúnprefijo.Tampocolovamosamodificar.

262
Con solo seguir estos pasos, nuestro proyecto ya está
trabajando con GitFlow. Gitflow es muy inteligente, y ya
nos ha situado en la rama develop. Recordemos que,
nunca deberíamos estar situados sobre la rama main
a la hora de desarrollar, ya que es donde se refleja el
código de producción. Todo el desarrollo lo haremos
sobredevelop.
Supongamos que queremos evolucionar nuestro
proyecto, es decir, añadirle una nueva característica.
Para hacerlo, necesitamos crear un tipo de rama
especial llamada Feature. Y así será cómo la iniciaremos:
escribimos únicamente git flow feature start
<nombre_feature>, donde <nombre_feature> es el
nombrequequeramosdarlealarama.
¿Y qué queremos hacer ahora con esta Feature? Vamos
a repasar el orden de los pasos. Hemos indicado el
tipo especial de rama que queremos crear, Feature, le
hemos indicado que queremos iniciarla y le hemos
dado un nombre, creándose una rama llamada
feature/<nombre_feature>. Finalmente, nos ha situado en
ella de forma automática. Démonos cuenta de que con
un solo comando hemos realizado muchas acciones en
Git.
Ahora que estamos en esta nueva rama de tipo Feature,
vamos a realizar modificaciones en el proyecto. No
importa realmente lo que hagamos aquí, simplemente
realizaremos algún cambio en la nueva Feature, para
ponerenprácticaGitFlow.Unavezquehemosterminado
con esta característica, revisamos el estado de nuestro
repositorioysubimoselcambioconadd,commitypush.
Hagamos un pequeño paréntesis para hablar de las
herramientas gráficas, ya que en ellas (tomando como
ejemplo GitKraken) también se reflejará que estamos

263
trabajando con GitFlow. La herramienta gráfica nos
mostrará que Gitflow está activo y nos señalará sus
ramas: main, develop y Feature. Y dentro de la carpeta
Featureseagruparántodaslasnuevasramasasociadasa
característicasquesevayandesarrollando.
Sigamos. Cuando el desarrollo de la Feature esté
terminado, validado y probado, fusionaremos ese
desarrollo con la rama develop. En lugar de hacer
un merge de forma manual y eliminar la rama,
podemos utilizar GitFlow para agilizar este proceso.
Para ello, usaremos el comando git flow feature
finish <nombre_feature>. Este comando eliminará
la rama Feature y la fusionará con la rama develop
automáticamente.Todoconunúnicocomando.
Nos encontramos de nuevo en la rama develop, la cuál,
yaposeeloscambiosdelaramadetipoFeature.
Seguir estos pasos nos permite trabajar de
manera más sencilla y ordenada con las ramas
Feature,ReleaseyHotfix.
Cuando estemos listos para lanzar una nueva versión,
podemos iniciar una rama Release con el comando
git flow release start <nombre_release>, donde
<nombre_release> es el número o nombre de versión
que queremos asignar. En este ejemplo sería git flow
release start 1.0.
Vamosarepasardenuevoelprocesoalahoradelanzar
una versión de Release, y cómo se maneja todo con
GitFlow. Primero, iniciamos la rama de Release. Esto crea
una nueva rama release/1.0, en la que podemos hacer
cambiosadicionalessiesnecesario,comoajustesfinales
antesdepasaraproducción.

264
Cuando la rama de tipo Release esté validada y lista
para pasar a producción, haremos lo mismo que con la
Feature anterior: en lugar de hacer start, haremos finish.
Ejecutamos git flow release finish 1.0, lo que nos
permitiráagregaruncomentariosilodeseamos.
Vamos a realizar ahora este proceso desde la
herramienta gráfica (según la seleccionada tendremos
ligerasdiferencias).Ennuestrocaso,marcamoslaopción
de eliminar la rama Release después de fusionarla,
y creamos un tag para esta versión, llamado 1.0. Al
finalizar, nos encontraremos el contenido de la rama
Releaseenlasramasmainydevelop.
Hecho esto, la rama main contiene los cambios que
se han desarrollado en la rama Release, que pasaron
posteriormente a la rama develop, y finalmente se han
desplegado a través de una rama de tipo Release. En
caso de que durante la existencia de la rama de Release
se hayan realizado modificaciones, estas también se
actualizarán en la rama develop, para que ningún
cambiosepierdaduranteelflujo.
Aquí no hablamos del proceso de despliegue
en producción, ya que eso dependerá del tipo
de software desarrollado, y el mecanismo
que utilicemos para realizar dicho pase a un
entornoreal.
Más situaciones. Imaginemos que encontramos algún
error en la versión de producción. En este caso,
podríamos abrir una rama de tipo Hotfix y seguir el flujo
de corrección de errores, añadiendo esas correcciones
a las ramas main y develop, una vez resueltas. La rama
Hotfixsiemprepartedelaramamain,yaqueallíesdonde

265
se encuentra el código que hay que corregir sin afectar
elflujodeFeature.
En este proceso, GitFlow nos ha facilitado el trabajo,
al manejar automáticamente las operaciones de
merge, switch y delete (entre otras), asegurándonos
de que no cometamos errores. Además, ha creado
automáticamenteeltag1.0enlaramamain,paramarcar
la versión que hemos desplegado. Recuerda que todo
esto puede realizarse tanto desde las herramientas
gráficas como desde la línea de comandos. Gracias a
GitFlow, el proceso se vuelve más sencillo y ordenado,
permitiéndonos centrarnos en el desarrollo de nuevas
característicasycorrecciones.
Aunque GitFlow es muy útil, puede considerarse un
flujo de trabajo lento, debido a la cantidad de ramas
que se crean y gestionan antes de llegar al despliegue.
Existen otros flujos de trabajo más rápidos, que podrían
adaptarse mejor a ciertos equipos o situaciones, por lo
que es importante investigar y elegir el que mejor se
adapteanuestrasnecesidades.
En cuanto a la colaboración entre equipos utilizando
GitFlow, es común combinarlo con un repositorio remoto
como GitHub. En este caso, como ya hemos visto
anteriormente, se utilizan características de trabajo
de GitFlow y GitHub para mejorar la experiencia de
desarrollo.
Imaginemosunúltimoescenarioenelqueunequipoestá
trabajando en una rama Feature. Cada persona trabaja
en una parte diferente de la funcionalidad, dentro de
su rama Feature local, pero todos comparten una rama
común remota. Para evitar problemas, y garantizar que
los cambios se revisen antes de integrarse en la rama
Feature remota, los miembros del equipo pueden utilizar

266
Pull RequestenGitHub.
| En este | enfoque, | un miembro |     | del equipo | crea | una | Pull |
| ------- | -------- | ---------- | --- | ---------- | ---- | --- | ---- |
Request,paraquesuscambiosseanrevisadosporotros
| miembros | antes     | de ser   | aceptados |     | en la rama      | Feature |      |
| -------- | --------- | -------- | --------- | --- | --------------- | ------- | ---- |
| remota.  | De manera | similar, | cuando    |     | la rama Feature |         | esté |
listaparafusionarseconlaramadevelop,sepuedecrear
| otra Pull | Request, | para | que | otros | equipos | revisen | y   |
| --------- | -------- | ---- | --- | ----- | ------- | ------- | --- |
apruebenloscambios.Enrealidad,podemosextenderel
| proceso  | todo  | lo que queramos, |     | eso   | sí, intentando |     | que |
| -------- | ----- | ---------------- | --- | ----- | -------------- | --- | --- |
| el flujo | no se | haga tedioso     | y   | acabe | bloqueando     | a   | los |
diferentesmiembrosdelproyecto.

| Capítulo | 44: | Otros |     |     |
| -------- | --- | ----- | --- | --- |
comandos
$git
| cherry-pick |     | y $git |     |     |
| ----------- | --- | ------ | --- | --- |
rebase
Comandos
| 1 git cherry-pick | <hash_commit>    |               |     |     |
| ----------------- | ---------------- | ------------- | --- | --- |
| 2 git cherry-pick | --abort          |               |     |     |
| 3 git cherry-pick | -i               | <hash_commit> |     |     |
| 4 git cherry-pick | --continue       |               |     |     |
| 5 git rebase      | <nombre_rama>    |               |     |     |
| 6 git rebase      | --abort          |               |     |     |
| 7 git rebase      | -i <nombre_rama> |               |     |     |
| 8 git rebase      | --continue       |               |     |     |
Conceptos
Introducción
| En conjunto,   | Git y GitHub | se han                 | convertido   | en una   |
| -------------- | ------------ | ---------------------- | ------------ | -------- |
| parte integral | de la        | gestión del desarrollo | de           | software |
| moderno.       | Aunque Git   | es fácil               | de usar para | realizar |
267

268
operaciones básicas como commit, push y pull, existen
comandos más avanzados como cherry-pick y rebase,
quepuedenserunpocointimidantesalprincipio.Eneste
capítulo,hablaremosdeestosdoscomandosavanzados,
y veremos cómo se pueden utilizar para gestionar el
historialdecommitsenunproyecto.
Cherry-Pick
Elcomandogit cherry-pickesuncomandodeGitque
seutilizaparaañadirelcontenidodeuncommitespecífico
de una rama a otra rama. A veces, nos encontramos en
una situación donde tenemos cambios útiles en una
rama que no estamos utilizando actualmente, pero
necesitamos transferir esos cambios a nuestra rama
actual.
En lugar de fusionar toda la rama, podemos usar
cherry-pick para seleccionar un commit en particular,
que contiene los cambios que necesitamos, y añadirlos
anuestraramaactual.
• La sintaxis básica del comando cherry-pick es la
siguiente:git cherry-pick <hash_commit>
Donde<hash_commit>serefierealidentificadorúnicodel
commitquedeseamosañadiranuestraramaactual.Una
vezqueejecutamosestecomando,Gitañadiráelcommit
seleccionadoyloaplicaráennuestraramaactual.
Si existen conflictos al aplicar el commit, tendremos que
resolverlosmanualmente.
• Si durante el proceso de cherry-pick, nos damos
cuenta de que hemos cometido un error, podemos

269
cancelar el proceso con el siguiente comando: git
cherry-pick --abort
Esto revertirá todos los cambios, y nos devolverá al
estadoanterioralprocesodecherry-pick.
Tambiéntenemoslaposibilidaddeusargit cherry-pick
-i, una opción adicional del comando cherry-pick, que
nos permite interactuar con los cambios que se están
seleccionandoyaplicando.
Laopción-i(interactive),permiteseleccionarloscambios
que deseamos aplicar y editar el mensaje del commit
antesdeañadirlosennuestraramaactual.Estopuedeser
útil si deseamos aplicar solo parte de los cambios de un
commit, o si deseamos modificar el mensaje del commit
paraqueseamásdescriptivo.
• La sintaxis del comando cherry-pick -i es la
siguiente:git cherry-pick -i <hash_commit>
Unavezqueejecutamosestecomando,Gitnosmostrará
un editor de texto con una lista de cambios que se
están seleccionando. Aquí, podemos elegir los cambios
que deseamos añadir a nuestra rama actual y editar el
mensaje del commit, según sea necesario. Después de
guardar los cambios, Git los aplicará en nuestra rama
actual.
De nuevo, si existen conflictos al aplicar los cambios,
tendremos que resolverlos manualmente. También
podremos cancelar el proceso de cherry-pick -i
utilizandogit cherry-pick --abort.

270
Rebase
GitrebaseesuncomandoavanzadodeGitqueseutiliza
para modificar el historial de commits de una rama. En
lugar de fusionar dos ramas, el comando rebase mueve
todos los commits de una rama a otra, y modifica el
historialdecommitsduranteelproceso.
• La sintaxis básica del comando rebase es la
siguiente:git rebase <nombre_rama>
Donde <nombre_rama> se refiere al nombre de la rama
que deseamos fusionar con la rama actual. Una vez que
ejecutamosestecomando,Gitañadirátodosloscambios
de la rama seleccionada y los aplicará en la rama actual.
Si hay conflictos al aplicar los cambios, tendremos que
resolverlosmanualmente.
La operación de rebase puede ser útil en situaciones en
las que deseamos tener una rama actualizada con los
cambios más recientes de otra. En lugar de fusionar las
dos ramas, el comando rebase puede mover todos los
commits de la otra rama a la rama actual y mantener un
historialdecommitslineal.
Es importante tener en cuenta que, el comando rebase
es una operación delicada, ya que modifica el historial
de commits de una rama. Por lo tanto, se recomienda
utilizar este comando con precaución, y solo cuando
sea necesario. Si se utiliza de manera incorrecta, puede
causarproblemasenelhistorialdecommitsdelproyecto.
• Si durante el proceso de rebase nos damos cuenta
de que cometimos un error, o aparecen conflictos
que no podemos resolver en ese momento,

271
podremos cancelar el proceso con el siguiente
comando:git rebase --abort
Estorevertirátodosloscambiosynosdevolveráalestado
anterioralprocesoderebase.
De igual manera que en el cherry-pick, también
podemos usar la opción -i (interactive) con el comando
rebase.
Conclusión
El comando cherry-pick nos permite añadir un
commit específico de una rama a otra, mientras que
el comando rebase mueve toda una rama a otra,
modificando el historial de commits durante el proceso.
Ambos comandos son avanzados y se utilizan en casos
específicos. Es importante entender cómo funcionan
antesdeutilizarlos,paraevitarcausardañosimportantes
enelhistorialdecommitsdelproyecto.
En general, es mejor evitar la necesidad de usar
cherry-pickyrebasecomoprimeraopción.Trabajando
conramaslimpiasyevitandocambiosinnecesariosenel
historial de commits del proyecto. Sin embargo, cuando
sea necesario utilizarlos, asegurémonos de entender
completamente cómo funcionan y tomar precauciones
paraevitarproblemas.
Curso
Lección44:mouredev.com/git-github-441
1https://mouredev.com/git-github-44

272
Inicio:04:52:49|Duración:00:07:16
| Nos acercamos |           | al          | final   | del curso, |           | no sin   | antes explorar |     |
| ------------- | --------- | ----------- | ------- | ---------- | --------- | -------- | -------------- | --- |
| estos últimos |           | comandos    |         | de Git     | que       | a veces  | nos dan        | un  |
| poco de       | respeto:  | cherry-pick |         |            | y rebase. | No       | entraremos     |     |
| en muchos     | detalles, |             | porque  |            | son       | comandos | avanzados      |     |
| y no se       | usan      | tan a       | menudo, |            | pero      | debemos  | conocer        | su  |
existenciayaquepuedenresultarnosmuyútiles.
| El primer | comando, |     | cherry-pick, |     |     | nos deja | escoger | un  |
| --------- | -------- | --- | ------------ | --- | --- | -------- | ------- | --- |
commitespecíficoyañadirloalaramaactual.Imaginemos
| que trabajamos |     | hace           | tiempo      |           | en  | una rama    | que         | ya no  |
| -------------- | --- | -------------- | ----------- | --------- | --- | ----------- | ----------- | ------ |
| nos importa,   |     | pero           | recordamos  |           | que | poseemos    | en          | ella   |
| un componente  |     | útil           | en          | un commit |     | específico. | Con         | git    |
| cherry-pick    |     | <hash_commit>, |             |           |     | podemos     | recuperar   |        |
| ese cambio     |     | y añadirlo     |             | a nuestra |     | rama        | actual.     | Si nos |
| encontramos    |     | con            | conflictos, | tendremos |     | que         | resolverlos |        |
siguiendoelflujohabitualdeGit.
| También | podemos |     | cancelar |     | el  | proceso | con |     |
| ------- | ------- | --- | -------- | --- | --- | ------- | --- | --- |
git
| cherry-pick |     | --abort, |     | y la | rama | regresará | al estado |     |
| ----------- | --- | -------- | --- | ---- | ---- | --------- | --------- | --- |
previo.

273
| El segundo | comando,     | rebase,    | nos            | permite | mover        |         | una |
| ---------- | ------------ | ---------- | -------------- | ------- | ------------ | ------- | --- |
| rama a     | un punto     | específico | y modificar    |         | el historial |         | de  |
| commits.   | Este comando | es         | delicado,      | porque  | modifica     |         | el  |
| historial. | Con git      | rebase     | <nombre_rama>, |         |              | podemos |     |
añadirloscambiosdeunaramaalfinaldelaramaactual.
Siusamoslaopción-i(interactive),tantoencherry-pick
| como en            | rebase,  | podemos    | aplicar  |                    | cambios     | uno  | a   |
| ------------------ | -------- | ---------- | -------- | ------------------ | ----------- | ---- | --- |
| uno utilizando     | el       | comando    | git      | cherry-pick|rebase |             |      |     |
| --continue.        | Por el   | contrario, |          | si nos             | equivocamos |      | y   |
| queremos           | deshacer | el         | proceso, | podemos            |             | usar | git |
| cherry-pick|rebase |          | --abort.   |          |                    |             |      |     |

| Capítulo |     | 45:     | GitHub |     |     |     |     |
| -------- | --- | ------- | ------ | --- | --- | --- | --- |
| Pages    | y   | Actions |        |     |     |     |     |
Conceptos
Introducción
| Para finalizar, |     | vamos a | hablar | de  | dos herramientas |     | muy |
| --------------- | --- | ------- | ------ | --- | ---------------- | --- | --- |
útilesdeGitHub:PagesyActions.
Pages
| Esta herramienta    |             | nos permite                 |        | usar   | nuestros |     | repositorio |
| ------------------- | ----------- | --------------------------- | ------ | ------ | -------- | --- | ----------- |
| de GitHub           | como        | un hosting                  |        | para   | nuestra  |     | página web. |
| Todo lo             | que tenemos | que                         | hacer  |        | es crear | un  | repositorio |
| con el nombre       |             | <usuario_github>.github.io, |        |        |          |     | y colocar   |
| el contenido        |             | de nuestra                  | página |        | dentro   | de  | él. Hecho   |
| esto, podremos      |             | acceder                     | a      | este   | a través |     | de la URL   |
| usuario.github.io1. |             | Además,                     |        | GitHub | Pages    |     | nos permite |
utilizarundominiopersonalizadosiasílodeseamos.
Básicamente,GitHubPagesesunserviciodealojamiento
desitioswebestáticosproporcionadoporGitHub.Esuna
| forma fácil                         | y         | gratuita de | crear   | y      | publicar |              | un sitio web |
| ----------------------------------- | --------- | ----------- | ------- | ------ | -------- | ------------ | ------------ |
| utilizandoarchivosestáticosHTML,CSS |           |             |         |        |          | yJavaScript. |              |
| Con GitHub                          | Pages,    | podemos     |         | alojar |          | sitios       | web para     |
| nuestros                            | proyectos | de          | GitHub, | así    | como     | para         | nuestros     |
1https://usuario.github.io
274

275
proyectospersonales.Podemoscrearunsitiowebdesde
cero,outilizarunaplantillaproporcionadaporGitHub.
Para publicar nuestro sitio web, simplemente debemos
crear un repositorio en GitHub y subir los archivos
estáticos a él. A continuación, podremos habilitar la
opción de GitHub Pages en la configuración de nuestro
repositorio, y seleccionar la rama que deseamos utilizar
parapublicarelsitioweb.Unavezquehayamosrealizado
estospasos,nuestrositiowebestarádisponibleenlínea
y accesible desde la URL de GitHub Pages de nuestro
repositorio.
GitHub Pages es una gran opción para alojar sitios
web estáticos gratuitamente y sin la necesidad de
configurarunservidorweborealizarlaconfiguracióndel
alojamiento. Además, también es compatible con Jekyll,
un generador de sitios web estáticos, que permite crear
sitioswebmáscomplejosconmenosesfuerzo.
AccedeaGitHubPagesdesdepages.github.com2
Actions
Esta última herramienta nos permite automatizar
diferentes acciones en nuestro repositorio de GitHub,
como despliegues y ejecución de pruebas, entre otras.
Con GitHub Actions, podemos crear distintos trabajos
asociados al proyecto y ejecutarlos según nuestras
necesidades.
GitHub Actions es un servicio de automatización de
flujo de trabajo proporcionado por GitHub. Permite
2https://pages.github.com

276
automatizar tareas repetitivas y simplificar los procesos
deintegracióncontinua(CI)ydesplieguecontinuo(CD)para
nuestrosproyectosalojadosenGitHub.
Con GitHub Actions, podemos crear un flujo de trabajo
que se active automáticamente cuando se desencadene
un evento específico dentro de nuestro repositorio de
GitHub, como la creación de una Pull Request, la
creación de un nuevo commit o un nuevo tag. Podemos
personalizar el flujo de trabajo para que realice una
serie de tareas, como la compilación de tu proyecto,
la ejecución de pruebas automatizadas, el despliegue
de nuestro proyecto en un servidor o el envío de
notificaciones,entreotrasmuchas.
Además, GitHub Actions viene con una amplia biblioteca
de acciones pre construidas para que las podamos
utilizar directamente dentro de nuestros flujos
de trabajo. Por supuesto, también podemos crear
nuestraspropiasaccionespersonalizadasparasatisfacer
necesidadesespecíficas.
Una de las ventajas de GitHub Actions, es su integración
completa con el ecosistema de GitHub, lo que significa
que podemos utilizarlo fácilmente con todos nuestros
proyectosalojadosenlaplataforma.
Accede a GitHub Actions desde
github.com/features/actions3
Tutorial GitHub Actions
¿QuieresprofundizarmásenGitHubActions?
3https://github.com/features/actions

277
| Aquí tienes | un tutorial |             | en vídeo   | desde |         | cero,         | de una  |
| ----------- | ----------- | ----------- | ---------- | ----- | ------- | ------------- | ------- |
| hora de     | duración,   | donde       | aprenderás |       |         | los conceptos |         |
| principales | de esta     | herramienta |            | y     | crearás | tu            | primera |
Action:mouredev.com/github-actions4.
Conclusión
| GitHub | Pages y GitHub     |     | Actions | son | herramientas |          | muy |
| ------ | ------------------ | --- | ------- | --- | ------------ | -------- | --- |
| útiles | que nos permitirán |     | sacarle | aún | más          | provecho | a   |
nuestrosproyectosdentrodeGitHub.
Curso
Lección45:mouredev.com/git-github-455
Inicio:05:00:05|Duración:00:04:19
HemoscubiertogranpartedelasfuncionalidadesdeGit
4https://mouredev.com/github-actions
5https://mouredev.com/git-github-45

278
| y GitHub, | pero | esta última |     | plataforma |     | tiene | un montón |     |
| --------- | ---- | ----------- | --- | ---------- | --- | ----- | --------- | --- |
decaracterísticasadicionales,yporesovamosafinalizar
elcursohablandosobredosdelasmásútiles.
| La primera | herramienta |     | clave |     | de GitHub |     | es GitHub |     |
| ---------- | ----------- | --- | ----- | --- | --------- | --- | --------- | --- |
Pages.
| GitHub     | Pages | nos permite |             | usar       | nuestro | repositorio |             | de  |
| ---------- | ----- | ----------- | ----------- | ---------- | ------- | ----------- | ----------- | --- |
| GitHub     | como  | un hosting. |             | Si tenemos |         | una página  |             | web |
| que cumple |       | con ciertos | requisitos, |            | nuestro |             | repositorio |     |
decódigoseconvertiráenelmecanismoparadesplegar
| dicha           | web. Para | lograrlo, | debemos                     |     | crear | un       | repositorio |     |
| --------------- | --------- | --------- | --------------------------- | --- | ----- | -------- | ----------- | --- |
| con el          | nombre    |           | <usuario_github>.github.io. |     |       |          |             | Lo  |
| que contenga    |           | ese       | repositorio                 |     | se    | podrá    | desplegar   |     |
| automáticamente |           | (siempre  |                             | que | el    | proyecto |             | sea |
compatibleconGitHubPages).
| La segunda |     | herramienta |     | clave | de GitHub |     | es GitHub |     |
| ---------- | --- | ----------- | --- | ----- | --------- | --- | --------- | --- |
Actions.
| Con GitHub   |     | Actions, | podemos    | crear |       | automatizaciones |     |     |
| ------------ | --- | -------- | ---------- | ----- | ----- | ---------------- | --- | --- |
| para nuestro |     | código   | en GitHub. |       | Estas | automatizaciones |     |     |
puedenservirparadesplegaroprobarnuestroproyecto,
| entre muchas |     | otras | acciones. | GitHub |     | Actions | es una | API |
| ------------ | --- | ----- | --------- | ------ | --- | ------- | ------ | --- |

279
| que nos  | permite         | crear | distintos       |            | trabajos     | asociados    |            | al   |
| -------- | --------------- | ----- | --------------- | ---------- | ------------ | ------------ | ---------- | ---- |
| proyecto | y ejecutarlos   |       | según           | nuestras   |              | necesidades. |            | Por  |
| ejemplo, | podríamos       |       | configurar      | acciones   |              | para         | que        | cada |
| vez que  | hagamos         |       | push a          | nuestro    | repositorio, |              | GitHub     |      |
| ejecute  | automáticamente |       |                 | los tests. |              | También      | existen    |      |
| muchas   | posibilidades   |       | e integraciones |            |              | con          | diferentes |      |
plataformascomoAWS,AzureoGoogleCloud.
| Estas    | herramientas |     | nos permitirán |     | sacarles |           | aún | más |
| -------- | ------------ | --- | -------------- | --- | -------- | --------- | --- | --- |
| provecho | a nuestros   |     | proyectos      | en  | GitHub   | y mejorar |     | sus |
flujosdetrabajo.

Otros comandos
Introducción
Estamos llegando al final de esta guía. Desconozco si
los comandos que hemos aprendido te han parecido
muchos o pocos, eso sí, te puedo decir un par de cosas
sobreellos:
• En la inmensa mayoría de los casos, serán más
que suficientes para que puedas trabajar con Git y
GitHubdemaneraprofesionalyeficaz.
• A pesar de todo eso, existen decenas de comandos
más (Git posee más de 140 comandos), y cientos
de combinaciones junto a sus modificadores. Con
el paso del tiempo los irás conociendo, incluso
precisarás de su uso para llevar a cabo tareas
muchomenoshabituales.
En este capítulo voy a introducierte “otros comandos” (o
instrucciones)quepuedesencontrarenGit,paraqueasí
lostengaspresentes.
Listado
1. git blame <archivo>:Muestraquiénhamodificado
cada línea de un archivo y en qué commit. Esto
es especialmente útil para entender el historial de
cambiosespecíficosenunarchivoyquiénloshizo.
280

281
2. git revert <commit>: Crea un nuevo commit que
deshace los cambios introducidos por un commit
anterior, sin borrar el historial. Esto es útil para
deshacer cambios específicos manteniendo la
integridaddelhistorialdetuproyecto.
3. git archive --format=zip --output=<archivo.zip>
HEAD: Crea un archivo zip de tu repositorio en el
estado actual de HEAD, excluyendo lo que esté
definido en .gitignore. Útil para compartir una
versióndelproyectosinincluireldirectorio.git.
4. git clean -fd: Elimina archivos no rastreados
(no incluidos en el .gitignore) de tu directorio de
trabajo.Estoesútilparalimpiartuáreadetrabajode
archivosgeneradosporcompilacionesoediciones.
5. git diff --stagedogit diff --cached:Muestra
lasdiferenciasentrelosarchivoseneláreadestaging
(index) y el último commit. Es útil para revisar los
cambiosqueestánlistosparasercommitados.
6. git log --follow <archivo>: Muestra el historial
decambiosdeunarchivoespecífico,inclusoatravés
derenombramientos.Útilpararastrearlaevolución
deunarchivoalolargodeltiempo.
7. git show <commit>:<archivo>: Muestra el
contenido de un archivo específico en un commit
específico. Es una buena manera de ver versiones
anterioresdeunarchivo.
8. git log --grep=<expresión>: Busca en los
mensajes de commit por una expresión regular
dada. Es especialmente útil para encontrar commits
específicosporpalabrasclaveensumensaje.
9. git shortlog: Resume los commits de Git
agrupándolos por autor, lo que proporciona
una visión rápida de la contribución de cada
desarrolladoralproyecto.
10. git bisect startseguidodegit bisect badygit

282
| bisect |     | good | <commit>: | Se  | utiliza | para | encontrar |
| ------ | --- | ---- | --------- | --- | ------- | ---- | --------- |
mediantebúsquedabinariaelcommitqueintrodujo
| un  | bug | en el código. |     | Inicias con | git | bisect | start, |
| --- | --- | ------------- | --- | ----------- | --- | ------ | ------ |
marcaselestadoactualouncommitconocidocomo
| malo  | con | git    | bisect    | bad,     | y un | commit | antiguo |
| ----- | --- | ------ | --------- | -------- | ---- | ------ | ------- |
| donde |     | el bug | no estaba | presente | con  | git    | bisect  |
good.
| Como                 | puedes | imaginar,  |           | esos        | son sólo   | unos   | pocos     |
| -------------------- | ------ | ---------- | --------- | ----------- | ---------- | ------ | --------- |
| ejemplos.            | Prueba |            | a buscar  | información |            | sobre  | git       |
| restore              | o git  | submodule, |           | cómo        | renombrar  |        | un commit |
| con git              | commit |            | --amend,  | o qué       | comandos   |        | deberías  |
| ejecutarpara“borrar” |        |            | uncommit. |             |            |        |           |
| Aún así,             | como   | has        | podido    | observar,   | todos      | ellos  | hacen     |
| referencia           | a      | tareas     | muy       | puntuales   | y alejadas |        | del flujo |
| principal            | de     | trabajo    | en Git.   | Con esto    | sólo       | quiero | decirte   |
quenotepreocupesporaprendertodo,simplementeten
| en cuenta | que | existen | muchas | opciones |     | (y que | en algún |
| --------- | --- | ------- | ------ | -------- | --- | ------ | -------- |
momentopuedenresultarteútiles).

Buenas prácticas
Introducción
Para finalizar, vamos a hacer un recorrido por un listado
de 10 buenas prácticas que puedes seguir a la hora de
usartantoGitcomoGitHub.
Git
1. Organiza repositorios: Crea repositorios
independientes para diferentes proyectos. Esto
nos facilita la colaboración y el mantenimiento del
código.
2. Utiliza ramas: Trabaja en ramas separadas para
desarrollar nuevas características, correcciones de
errores o experimentar con tu proyecto. Las ramas
nos permiten mantener el código estable y facilitan
la integración de cambios sin afectar a la rama
principal.
3. Haz commits atómicos: Cada commit debe
representar una única unidad de cambio lógico,
facilitando la revisión del historial y el seguimiento
de los cambios. Además, es más fácil entender,
revisar,revertiroajustarcommitsindividuales.
4. Escribe mensajes de commit claros y
significativos: Describe el propósito y el contexto
del cambio en el mensaje del commit. Esto ayuda
283

284
a otros colaboradores, y a nosotros mismos, a
entender la razón del cambio y facilita la búsqueda
de commits específicos en el historial. También
podemos usar referencias que nos sirvan para
identificar su contenido, como identificadores
asociados a una tarea en un sistema de gestión de
trabajo.
5. Mantén un historial limpio: Evita enviar cambios
innecesarios o conflictivos, y usa la fusión de
cambios (merge) adecuadamente. Esto hace que el
historial de commits sea más fácil de entender y
navegar.
6. Usa etiquetas: Utiliza etiquetas (tags) para marcar
puntos importantes en el historial del proyecto,
como versiones o lanzamientos a producción. Las
etiquetas proporcionan una forma rápida de volver
a un punto específico en el tiempo y facilitan la
gestióndeversiones.
7. Haz revisiones de código: Antes de integrar
cambios en la rama principal, realiza revisiones de
códigoparagarantizarlacalidadylacoherenciadel
proyecto. GitHub ofrece características de revisión
de código a través de Pull Request que facilitan
estatarea.
8. Resuelve conflictos: Al fusionar ramas, pueden
surgirconflictos.Esimportanteresolverlosdeforma
coherente y asegurarse de que los cambios no
introduzcan errores ni afecten al rendimiento del
proyecto.
9. Mantén copias de seguridad y haz push con
regularidad: Debemos asegurarnos de poseer
copiasdeseguridaddenuestrosrepositorioslocales,
y hacer push de los cambios al repositorio remoto
regularmente.Estoprotegenuestrosdatosyfacilita
lacolaboración.

285
| 10. Continúa    | aprendiendo: |             |             | Git          | es una       | herramienta |         |      |
| --------------- | ------------ | ----------- | ----------- | ------------ | ------------ | ----------- | ------- | ---- |
| poderosa        |              | y flexible, | pero        |              | también      |             | puede   | ser  |
| compleja.       |              | Continúa    | aprendiendo |              |              |             | sobre   | sus  |
| características |              | y           | prácticas   |              | recomendadas |             |         | para |
| aprovechar      |              | al máximo   |             | su potencial |              | y           | mejorar | la   |
eficienciadenuestrosproyectos.
GitHub
| 1. Personaliza |     | tu perfil: | Configura |     | la  | página | de  | inicio |
| -------------- | --- | ---------- | --------- | --- | --- | ------ | --- | ------ |
asociadaatuusuariodeGitHub.GitHubesunagran
cartadepresentaciónparadesarrolladores.
| 2. Crea       | un README: |            | Incluye      |            | siempre |                | un archivo |        |
| ------------- | ---------- | ---------- | ------------ | ---------- | ------- | -------------- | ---------- | ------ |
| README.md     |            | en tu      | repositorio. |            | Este    | archivo        |            | debe   |
| proporcionar  |            | una        | descripción  |            | general | del            | proyecto,  |        |
| instrucciones |            | de         | instalación  |            | y       | configuración, |            |        |
| información   |            | sobre      | cómo         | contribuir |         | y cualquier    |            | otra   |
| información   |            | relevante. | Su           | contenido  |         | puede          |            | variar |
dependiendodesisetratadeunproyectopúblicoo
privado.
| 3. Licencia | de       | software: |       | Añade        | una      | licencia  |        | a tu |
| ----------- | -------- | --------- | ----- | ------------ | -------- | --------- | ------ | ---- |
| repositorio | para     | informar  |       | a otros      | usuarios |           | de     | cómo |
| lo pueden   | utilizar |           | y qué | limitaciones |          | posee.    | GitHub |      |
| ofrece      | una      | variedad  | de    | licencias    |          | populares |        | que  |
podemoselegirfácilmente.
| 4. Usa Pull |     | Request: | Utiliza | el  | mecanismo |     | de  | Pull |
| ----------- | --- | -------- | ------- | --- | --------- | --- | --- | ---- |
Requestparaproponercambiosaunrepositorio,en
| lugar de        | modificar |       | directamente |            | la       | rama | principal. |     |
| --------------- | --------- | ----- | ------------ | ---------- | -------- | ---- | ---------- | --- |
| Las Pull        | Request   |       | permiten     | la         | revisión | del  | código     | y   |
| las discusiones |           | antes | de           | incorporar |          | los  | cambios    | al  |
proyecto.
| 5. Revisión | de   | código: | Realiza |       | revisiones |              | de código |      |
| ----------- | ---- | ------- | ------- | ----- | ---------- | ------------ | --------- | ---- |
| en las      | Pull | Request |         | antes | de         | fusionarlas. |           | Esto |

286
asegura que el código cumple con los estándares
de calidad y reduce la probabilidad de introducir
errores. También puedes automatizar tareas con
GitHubActions.
6. Utiliza Issues: Emplea el sistema de Issues de
GitHub para rastrear y gestionar errores, mejoras
y otras tareas relacionadas con el proyecto. Esto
ayuda a mantener el progreso del proyecto y
fomentalacolaboración.
7. Etiquetas y Milestones: Usa etiquetas y milestones
enIssuesyPull Requestparacategorizarypriorizar
el trabajo. Las etiquetas pueden indicar el tipo de
tarea(porejemplo,erroromejora),mientrasquelos
milestonesnosayudanaorganizarlastareasenfases
asociadasalaevolucióndelproyecto.
8. Documentación: Mantén la documentación
actualizada, incluidos los archivos de ayuda, la
wiki del repositorio y cualquier otro material
relacionado. Una documentación adecuada facilita
lacomprensiónycontribuciónalproyectoporparte
deotrosdesarrolladores.
9. Comparte y colabora: Crea tus propios proyectos
de código abierto y colabora en otros. Esta es la
mejor manera para ayudar a que la comunidad de
desarrollodesoftwaresigaavanzando.
10. Continúa aprendiendo: GitHub es una gran
plataforma en constante evolución, y sus
herramientas van mucho más allá de los
mecanismos propios de Git. Sigue sus avances
yexplorasusdistintascaracterísticas

287
Conclusión
Alseguirestasprácticas,nosaseguraremosdeutilizarGit
| y GitHub    | de manera | efectiva, organizada     | y colaborativa, |
| ----------- | --------- | ------------------------ | --------------- |
| manteniendo | la        | calidad y la estabilidad | de nuestros     |
proyectos.

| Próximos |     | pasos |     |     |     |     |
| -------- | --- | ----- | --- | --- | --- | --- |
¡Enhorabuena!
| El haber | llegado | hasta | aquí | significa | que | ya estás |
| -------- | ------- | ----- | ---- | --------- | --- | -------- |
preparadoparatrabajarconGityGitHub.
¿Quétehaparecidoellibroyelcursoenvídeo?
| La mejor | manera | de seguir | aprendiendo |     | Git | y GitHub es |
| -------- | ------ | --------- | ----------- | --- | --- | ----------- |
practicandoyvisitandosusrecursosoficiales.
• DocumentaciónoficialGit:git-scm.com/doc1
• LibrooficialGit:git-scm.com/book/es/v22
• DocumentaciónoficialGitHub:docs.github.com/es3
| También      | puedes  | unirte        | a mi | plataforma | de   | retos de |
| ------------ | ------- | ------------- | ---- | ---------- | ---- | -------- |
| programación |         | en comunidad, |      | donde      | cada | semana   |
| mejoramos    | nuestra | lógica        | y    | utilizamos | Git  | y GitHub |
paracompartirnuestrosejercicios,empleandocualquier
lenguajedeprogramación.
Retosdeprogramación:retosdeprogramacion.com4
•
1https://git-scm.com/doc
2https://git-scm.com/book/es/v2
3https://docs.github.com/es
4https://retosdeprogramacion.com
288

| ¡Muchas | gracias! |     |     |
| ------- | -------- | --- | --- |
¿Seguimosencontacto?
| Te espero   | es mis redes | sociales     | para continuar |
| ----------- | ------------ | ------------ | -------------- |
| aprendiendo | programación | y desarrollo | de software    |
encomunidad.
Tienestodoslosenlacesenmoure.dev1
1https://moure.dev
289