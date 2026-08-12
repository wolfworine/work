# aws-s3-starter

> Starter Quarkus/Mutiny del framework Saywa para almacenar y recuperar
> objetos en Amazon S3 mediante una API reactiva, sin exponer tipos del SDK
> de AWS a la aplicación consumidora.

`com.saywa.framework:aws-s3-starter`

## Índice

- [Propósito](#propósito)
- [Arquitectura](#arquitectura)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [Manejo de errores](#manejo-de-errores)
- [Auditoría](#auditoría)
- [Extensión para transferencias masivas (aws-s3-transfer-manager-starter)](#extensión-para-transferencias-masivas-aws-s3-transfer-manager-starter)
- [Cómo ejecutar los tests](#cómo-ejecutar-los-tests)
- [Sobre este repositorio](#sobre-este-repositorio)

## Propósito

`aws-s3-starter` es una **librería JAR** (no una aplicación: sin `main`, sin
endpoints REST, sin `@QuarkusMain`) que cualquier microservicio Quarkus del
framework Saywa puede añadir como dependencia para operar contra un bucket de
Amazon S3 sin tener que:

- Configurar manualmente `S3AsyncClient` / `S3Presigner` (los gestiona
  `quarkus-amazon-s3` como beans CDI).
- Lidiar con excepciones crudas del SDK de AWS.
- Reimplementar validaciones de tamaño máximo de subida/descarga.
- Reimplementar logging de auditoría por operación.

El único punto de entrada para el consumidor es la interfaz
`com.saywa.framework.data.s3.api.S3StorageService`, inyectable por CDI:

```java
@Inject
S3StorageService s3StorageService;
```

Todas sus operaciones son **reactivas y no bloqueantes**: cada método
devuelve un `io.smallrye.mutiny.Uni<T>` que nunca se resuelve internamente
con `.await().indefinitely()`, `.join()` ni `.get()`.

**`bucketName` es obligatorio en cada llamada.** El starter se comporta
como una conexión a base de datos: la "conexión" (cliente S3 async,
presigner, credenciales, región) se establece una sola vez vía
`quarkus-amazon-s3`, pero cada operación individual indica explícitamente
contra qué bucket opera — no hay un bucket implícito ni un fallback
silencioso. `upload()` lo recibe como campo de `S3ObjectRequest`; el resto
de operaciones lo reciben como primer parámetro.

## Arquitectura

```
Aplicación consumidora (microservicio Quarkus)
        │  @Inject S3StorageService
        ▼
┌─────────────────────────────────────────────┐
│  api/S3StorageService                        │  ← interfaz pública
│  (Uni<T> Mutiny — sin tipos del SDK de AWS)  │
└──────────────────┬────────────────────────────┘
                    │
┌───────────────────▼────────────────────────────┐
│  service/S3StorageServiceImpl                   │
│  ├── factory/S3RequestFactory   (construye      │
│  │       los *Request del SDK, normaliza claves) │
│  ├── mapper/S3ResponseMapper    (SDK → domain)   │
│  ├── audit/S3AuditLogger        (logging         │
│  │       estructurado, sin datos sensibles)      │
│  └── exception/StorageExceptionMapper (SDK →     │
│          jerarquía compartida del módulo `core`) │
└───────────────────┬────────────────────────────┘
                    │
┌───────────────────▼────────────────────────────┐
│  S3AsyncClient + S3Presigner                    │  ← AWS SDK 2.x
│  (beans CDI provistos por quarkus-amazon-s3)    │
└───────────────────┬────────────────────────────┘
                    │
             Amazon S3 / LocalStack
```

Principios de diseño que se mantienen en toda nueva versión del starter:

- **`api/` y `domain/` no dependen del SDK de AWS.** El consumidor solo ve
  `Uni<T>`, records Java (`S3ObjectRequest`, `S3ObjectResponse`,
  `S3ObjectContent`, `S3ObjectSummary`) y la jerarquía de excepciones propia.
- **Todo es asíncrono con Mutiny.** El SDK (`CompletableFuture`) se adapta
  siempre con `Uni.createFrom().completionStage(...)`.
- **`S3Presigner` siempre inyectado** vía `@Inject`, nunca creado a mano con
  `S3Presigner.create()` dentro de un método.
- **Dominio inmutable**: todos los records de `domain/` son inmutables, sin
  setters.
- **`move()` no hace rollback automático**: internamente es
  `copy(origen, destino)` seguido de `delete(origen)`. Si la copia tiene
  éxito pero el borrado del origen falla, el error se propaga tal cual y el
  objeto queda presente tanto en origen como en destino — la aplicación
  consumidora es responsable de reconciliar ese estado si lo necesita.

## Instalación

Añade la dependencia en el `pom.xml` del microservicio consumidor (la
versión la gestiona el BOM `saywa-parent-bom`):

```xml
<dependency>
    <groupId>com.saywa.framework</groupId>
    <artifactId>aws-s3-starter</artifactId>
</dependency>
```

`aws-s3-starter` ya trae transitivamente `io.quarkiverse.amazonservices:quarkus-amazon-s3`
(que provee los beans `S3AsyncClient`/`S3Presigner`), `quarkus-arc` y
`com.saywa.framework:core` (jerarquía de excepciones compartida `Storage*`,
ver [Manejo de errores](#manejo-de-errores)). No es necesario declarar esas
dependencias por separado en la aplicación consumidora.

## Configuración

El starter usa dos familias de propiedades de configuración, ambas resueltas
por Quarkus/SmallRye Config en `application.properties` (o el
`application.yaml` equivalente) de la aplicación consumidora:

- `quarkus.s3.*` — configuración estándar de `quarkus-amazon-s3` (región,
  credenciales, endpoint override para pruebas contra LocalStack, etc.). Ver
  la documentación de esa extensión para el listado completo.
- `compartamos.storage.s3.*` — configuración propia de este starter,
  mapeada por `com.saywa.framework.data.s3.config.S3Configuration`
  (`@ConfigMapping`) y validada al arrancar por `S3ConfigurationValidator`.

### Propiedades `compartamos.storage.s3.*`

| Propiedad | Tipo | Valor por defecto | Descripción |
|---|---|---|---|
| `compartamos.storage.s3.bucket-name` | `String` | *(sin valor por defecto, obligatoria)* | Se mantiene como configuración obligatoria a nivel de aplicación (validada al arrancar), pero **ya no se usa para resolver el bucket de ninguna operación** — cada llamada a `S3StorageService` indica su propio `bucketName` explícito (ver nota arriba). |
| `compartamos.storage.s3.default-prefix` | `String` | `""` | Prefijo que `S3RequestFactory` antepone **automáticamente a la clave de todo objeto individual** (`upload`, `download`, `delete`, `copy`/`move`, `presigned`) que no la tenga ya — no es opt-in por operación. En `list`, en cambio, solo se usa como *fallback* cuando la llamada no pasa un `prefix` explícito; un `prefix` explícito no se combina con `default-prefix`. |
| `compartamos.storage.s3.max-upload-size` | `long` (bytes) | `10485760` (10 MiB) | Tamaño máximo permitido para el contenido de un `upload()`. Si se excede, la operación falla **antes** de invocar al SDK. |
| `compartamos.storage.s3.max-download-size` | `long` (bytes) | `10485760` (10 MiB) | Tamaño máximo permitido para el contenido de un `download()`. Se valida contra el `contentLength` obtenido vía `headObject` **antes** de transferir los bytes. |
| `compartamos.storage.s3.presigned-ttl` | `Duration` (ISO-8601) | `PT15M` (15 minutos) | Tiempo de vida por defecto de las URLs prefirmadas cuando `presigned()` no especifica un TTL explícito. |

Ejemplo mínimo de configuración en la aplicación consumidora:

```properties
# Propio del starter
compartamos.storage.s3.bucket-name=mi-bucket-saywa
compartamos.storage.s3.default-prefix=documentos
compartamos.storage.s3.max-upload-size=20971520
compartamos.storage.s3.max-download-size=20971520
compartamos.storage.s3.presigned-ttl=PT10M

# Gestionado por quarkus-amazon-s3
quarkus.s3.aws.region=us-east-1
quarkus.s3.aws.credentials.type=default
```

`S3ConfigurationValidator` falla rápido en el arranque (lanzando
`IllegalArgumentException`) si, en este orden: el bucket está vacío o en
blanco, el prefijo por defecto es nulo, `max-upload-size` es menor o igual a
cero, `max-download-size` es menor o igual a cero, `presigned-ttl` es nulo o
no estrictamente positivo, o el prefijo por defecto contiene `"//"`.

## Uso

Inyecta `S3StorageService` en cualquier bean CDI y encadena las operaciones
como cualquier otro `Uni<T>` de Mutiny.

### upload — subir un objeto

```java
@Inject
S3StorageService s3StorageService;

public Uni<S3ObjectResponse> guardarFactura(String bucketName, byte[] pdf) {
    var request = new S3ObjectRequest(
            bucketName,
            "facturas/2026/factura-001.pdf",
            pdf,
            "application/pdf",
            Map.of("origen", "facturacion"));

    return s3StorageService.upload(request);
}
```

### download — descargar un objeto

```java
public Uni<S3ObjectContent> leerFactura(String bucketName, String objectKey) {
    return s3StorageService.download(bucketName, objectKey);
    // S3ObjectContent#content() (byte[]) y #contentType() (String)
}
```

### list — listar objetos por prefijo

```java
public Uni<List<S3ObjectSummary>> listarFacturas2026(String bucketName) {
    return s3StorageService.list(bucketName, "facturas/2026/");
    // cada S3ObjectSummary expone objectKey(), size() y lastModified() (Instant)
}
```

### delete — eliminar un objeto

```java
public Uni<Void> eliminarFactura(String bucketName, String objectKey) {
    return s3StorageService.delete(bucketName, objectKey);
}
```

### copy — copiar un objeto (server-side, dentro del mismo bucket)

```java
public Uni<S3ObjectResponse> archivarFactura(String bucketName, String objectKey) {
    return s3StorageService.copy(bucketName, objectKey, "archivo/" + objectKey);
}
```

### move — mover un objeto (copy + delete)

```java
public Uni<S3ObjectResponse> moverAArchivo(String bucketName, String objectKey) {
    return s3StorageService.move(bucketName, objectKey, "archivo/" + objectKey);
    // Si el delete del origen falla tras un copy exitoso, el error se
    // propaga y el objeto queda presente en origen Y destino (ver Arquitectura).
}
```

### exists — comprobar si un objeto existe

```java
public Uni<Boolean> facturaExiste(String bucketName, String objectKey) {
    return s3StorageService.exists(bucketName, objectKey);
    // false si no existe; nunca falla el Uni solo por ausencia del objeto
}
```

### presigned — generar una URL prefirmada de solo lectura

```java
public Uni<String> urlTemporalFactura(String bucketName, String objectKey) {
    return s3StorageService.presigned(bucketName, objectKey, Duration.ofMinutes(5));
    // ttl == null usa compartamos.storage.s3.presigned-ttl
}
```

## Manejo de errores

El starter nunca propaga excepciones crudas del SDK de AWS
(`software.amazon.awssdk.*`) como fallo de un `Uni`. Todas las operaciones
fallan exclusivamente con una excepción de la jerarquía de almacenamiento
**compartida por el framework Saywa**, definida en el módulo `core`
(`com.saywa.framework.core.error.exceptions`) — no es propia de este
starter, sino el mismo tipo que usará `aws-s3-transfer-manager-starter` y
cualquier otro starter de almacenamiento. La traduce
`exception/StorageExceptionMapper` (paquete
`com.saywa.framework.data.s3.exception`), preservando siempre la causa
original vía `getCause()`:

```
RuntimeException
└── StorageException                    (raíz abstracta, sealed — no instanciable)
    ├── StorageObjectNotFoundException  (clave inexistente en el bucket)
    ├── StorageAccessDeniedException    (S3 rechaza la operación, HTTP 403)
    ├── StorageConfigurationException   (config inválida o límite de tamaño excedido)
    ├── StorageConnectionException      (fallo de conectividad, sin respuesta del servicio)
    └── StorageGenericException         (catch-all; cualquier otro error no clasificado)
```

`StorageException` está declarada `sealed` con exactamente esas cinco
subclases (`permits`): no puede instanciarse directamente y no puede
extenderse fuera del módulo `core`, así que capturar por la clase base cubre
siempre el universo completo de fallos posibles. Todas exponen el mismo
constructor público, `(String message, Throwable cause)`, por lo que siempre
es seguro capturar por la clase base y examinar la causa:

```java
import com.saywa.framework.core.error.exceptions.StorageException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;

s3StorageService.download(bucketName, objectKey)
    .onFailure(StorageObjectNotFoundException.class).recoverWithItem(this::contenidoPorDefecto)
    .onFailure(StorageException.class).invoke(e ->
        log.error("Error al descargar {}: {}", objectKey, e.getMessage(), e.getCause()));
```

Casos típicos por excepción:

| Excepción | Cuándo se produce |
|---|---|
| `StorageObjectNotFoundException` | `download`, `copy`/`move` (clave origen) o `presigned` sobre una clave que no existe en el bucket configurado. Traducida desde `NoSuchKeyException` del SDK. |
| `StorageAccessDeniedException` | Amazon S3 responde con HTTP 403 (`AccessDenied`, permisos insuficientes de la identidad configurada). Traducida desde `S3Exception`. |
| `StorageConfigurationException` | El contenido de un `upload` excede `max-upload-size`, el objeto a descargar excede `max-download-size`, un `bucketName`/`objectKey` requerido llega nulo o en blanco (validación de `S3ObjectRequest`/`S3RequestFactory`, traducida desde `IllegalArgumentException`), o hay un problema de credenciales/región/bucket detectado por el cliente antes de contactar a S3. |
| `StorageConnectionException` | Fallo de comunicación de red con S3 antes de que el servicio llegue a procesar la petición: timeout, conexión rechazada, DNS sin resolver. Traducida desde `SdkClientException` (cuando no aplica ninguno de los casos más específicos anteriores). |
| `StorageGenericException` | Cualquier otro error no clasificado devuelto por Amazon S3 o el SDK (`SdkException` genérica, o cualquier `Throwable` no mapeado por una regla más específica), incluyendo un fallo de `delete` tras un `copy` exitoso dentro de `move`. |

`delete(objectKey)` es una excepción a la regla de "objeto no encontrado":
Amazon S3 no distingue entre borrar una clave existente o inexistente, así
que `delete` **no** falla con `S3ObjectNotFoundException` si la clave ya no
existía. De forma similar, `exists(objectKey)` nunca falla solo porque el
objeto no exista: devuelve `false`.

## Auditoría

Cada operación del `S3StorageService` emite un evento de auditoría a través
de `audit/S3AuditLogger`, usando el record inmutable `audit/S3AuditEvent`:

```java
public record S3AuditEvent(S3Operation operation, String bucket, String objectKey,
                            boolean successful, Long durationMs, String errorCode) { }
```

`S3Operation` clasifica la operación auditada: `PUT`, `GET`, `DELETE`,
`LIST`, `COPY`, `HEAD` (usado por `exists`/`presigned`) o `PRESIGN`.

**Regla dura de este starter, sin excepciones**: `S3AuditEvent` nunca
transporta contenido de archivos, credenciales ni URLs prefirmadas
completas — solo metadatos operacionales (operación, bucket, clave, éxito,
duración y, en caso de fallo, un código de error). En particular,
`presigned()` audita el `objectKey` y el TTL efectivo utilizado, pero
**jamás** la URL prefirmada generada; esa URL solo se devuelve al llamador
en el resultado del `Uni<String>`, nunca se registra en logs de auditoría.

Formato de log emitido por `S3AuditLogger`:

```
s3.audit operation={} bucket={} objectKey={} success={} durationMs={} errorCode={}
```

## Extensión para transferencias masivas (aws-s3-transfer-manager-starter)

`aws-s3-starter` está pensado para operaciones simples sobre objetos
individuales (subida/descarga/copia/listado/borrado/URL prefirmada) con
límites de tamaño configurables (`max-upload-size`, `max-download-size`).
Para casos de uso de **transferencias masivas o de gran tamaño** —por
ejemplo, subida multipart de archivos grandes, sincronización de
directorios completos o transferencias con reanudación automática— el
framework Saywa provee, como módulo **separado y opcional**,
`aws-s3-transfer-manager-starter`, construido sobre `S3TransferManager` del
SDK de AWS.

Los paquetes `config/` y `audit/` de este módulo están diseñados
explícitamente para ser reutilizados (no duplicados) por
`aws-s3-transfer-manager-starter`, de forma que ambos starters comparten la
misma configuración base (`compartamos.storage.s3.*`) y el mismo mecanismo
de auditoría. La jerarquía de excepciones ya es compartida por diseño, al
vivir en el módulo `core` en lugar de en `aws-s3-starter`: cada starter solo
aporta su propio `StorageExceptionMapper` que traduce las excepciones del
SDK a esa misma jerarquía (ver [Manejo de errores](#manejo-de-errores)). Un
consumidor que solo necesite operaciones simples sobre objetos puede
depender únicamente de `aws-s3-starter`; si además necesita transferencias
masivas, añade `aws-s3-transfer-manager-starter` como dependencia adicional.

## Cómo ejecutar los tests

Este módulo se prueba con dos suites independientes:

### Tests unitarios (por defecto, sin infraestructura externa)

Ubicados en `src/test/java/unit/`. Usan `@ExtendWith(MockitoExtension.class)`
— **sin** `@QuarkusTest` — mockeando `S3AsyncClient` y `S3Presigner`
directamente con Mockito, por lo que no requieren ningún servicio S3 real ni
LocalStack en ejecución:

```bash
mvn test -pl .
```

### Tests de integración (carpeta reservada, aún sin casos ni perfil Maven)

`src/test/java/integration/` existe como carpeta reservada para tests que
levanten contexto Quarkus real contra un LocalStack en
`http://localhost:4566` (`src/test/resources/application.properties` ya
configura credenciales estáticas de prueba y el
`%test.quarkus.s3.endpoint-override` para ese caso), pero **hoy está vacía**
y el `pom.xml` de este módulo no define un perfil Maven separado para
ejecutarlas (a diferencia de `aws-s3-starter-deployment`, que sí expone
`-Pintegration-tests`). Antes de añadir tests ahí, hay que dar de alta ese
perfil (o excluir `**/integration/**` del `maven-surefire-plugin` heredado
de `saywa-parent-bom`, que hoy solo filtra por nombre `**/*Test.java`).

## Sobre este repositorio

Este módulo fue generado siguiendo un harness de ingeniería asistido por IA
(`harness-saywa-s3-starter`), que dividió el trabajo en una secuencia de
features verificadas incrementalmente (`feature_list.json`) contra las
convenciones del framework Saywa (`docs/conventions.md`,
`docs/architecture.md`). Esos documentos internos de proceso no forman parte
de la API pública del starter y no son necesarios para consumirlo — este
`README.md` es la referencia autosuficiente para cualquier equipo de Saywa
que quiera integrar `aws-s3-starter` en un microservicio.
