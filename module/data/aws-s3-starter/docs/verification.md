# Guía de verificación — aws-s3-starter

## Verificación rápida

```bash
./init.sh
```

Esto verifica: Java 21, Maven, compilación, tests, y tres red flags críticos.

## Verificación manual por capa

### api/ y domain/
```bash
# No debe aparecer nada (sin tipos del SDK de AWS)
grep -r "software.amazon.awssdk" src/main/java/com/saywa/framework/data/s3/api/
grep -r "software.amazon.awssdk" src/main/java/com/saywa/framework/data/s3/domain/
```

### service/ — sin bloqueos
```bash
grep -r "\.join()\|\.get()\|await()\.indefinitely()\|Thread\.sleep" src/main/java/
```

### S3Presigner — siempre inyectado
```bash
grep -r "S3Presigner\.create()" src/main/java/
# No debe aparecer nada
```

### Causa preservada en todas las excepciones
```bash
grep -r "new S3" src/main/java/com/saywa/framework/data/s3/exception/
# Cada línea debe tener dos argumentos: (mensaje, throwable/causa)
```

### Javadoc presente
```bash
# Buscar clases públicas sin Javadoc (debe estar vacío)
grep -rn "^public " src/main/java/ | grep -v ".java:public record\|.java:public interface\|.java:public enum\|.java:public class" || true
```

## Tests específicos a verificar

### 1. presigned() — auditoría sin URL completa
```
Test: givenObjectExists_whenPresigned_thenAuditDoesNotContainFullUrl
Verifica: auditLogger.audit() recibe un evento donde errorCode es null y
          el objectKey coincide — pero la URL nunca se pasa al logger
```

### 2. move() — copy ok, delete falla
```
Test: givenDeleteFailsAfterCopySuccess_whenMove_thenPropagateErrorWithoutReverting
Verifica: cuando delete lanza excepción después de copy exitoso,
          move() propaga la excepción pero NO llama de nuevo a delete o copy
```

### 3. download() — valida tamaño antes de transferir
```
Test: givenObjectExceedsMaxDownloadSize_whenDownload_thenFailWithStorageConfigurationException
Verifica: cuando headObject devuelve contentLength > maxDownloadSize,
          download() falla ANTES de llamar a getObject (sin transferir bytes)
```

### 4. StorageExceptionMapper — causa preservada en cada rama
```
Test: givenNoSuchKeyException_whenMap_thenCauseIsPreserved
Test: givenAccessDeniedException_whenMap_thenCauseIsPreserved
Test: givenSdkClientException_whenMap_thenCauseIsPreserved
Test: givenGenericException_whenMap_thenCauseIsPreserved
Verifica: result.getCause() == originalException en cada caso
```

## Checklist antes de declarar feature done

- [ ] `mvn test -pl .` verde (0 fallos, 0 errores)
- [ ] `./init.sh` verde (compila + tests + red flags)
- [ ] Javadoc presente y real en todos los objetos públicos nuevos
- [ ] Tests nombrados con Gherkin (givenX_whenY_thenZ)
- [ ] Tests estructurados con AAA (Arrange, Act, Assert)
- [ ] `progress/current.md` actualizado con qué archivos se crearon
