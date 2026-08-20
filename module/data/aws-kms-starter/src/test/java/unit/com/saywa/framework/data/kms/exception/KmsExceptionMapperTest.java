package unit.com.saywa.framework.data.kms.exception;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.saywa.framework.core.error.exceptions.CryptoAccessDeniedException;
import com.saywa.framework.core.error.exceptions.CryptoConfigurationException;
import com.saywa.framework.core.error.exceptions.CryptoConnectionException;
import com.saywa.framework.core.error.exceptions.CryptoGenericException;
import com.saywa.framework.core.error.exceptions.CryptoInvalidCiphertextException;
import com.saywa.framework.core.error.exceptions.CryptoKeyNotFoundException;
import com.saywa.framework.data.kms.crypto.AesGcmTagMismatchException;
import com.saywa.framework.data.kms.exception.KmsExceptionMapper;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.kms.model.DisabledException;
import software.amazon.awssdk.services.kms.model.InvalidCiphertextException;
import software.amazon.awssdk.services.kms.model.InvalidKeyUsageException;
import software.amazon.awssdk.services.kms.model.KmsException;
import software.amazon.awssdk.services.kms.model.NotFoundException;

class KmsExceptionMapperTest {

    private final KmsExceptionMapper mapper = new KmsExceptionMapper();

    @Test
    void givenNotFoundException_whenMap_thenReturnsCryptoKeyNotFoundExceptionWithCause() {
        NotFoundException sdkException = NotFoundException.builder().message("no such key").build();

        RuntimeException mapped = mapper.map(sdkException);

        assertInstanceOf(CryptoKeyNotFoundException.class, mapped);
        assertSame(sdkException, mapped.getCause());
    }

    @Test
    void givenInvalidCiphertextException_whenMap_thenReturnsCryptoInvalidCiphertextExceptionWithCause() {
        InvalidCiphertextException sdkException = InvalidCiphertextException.builder().message("bad ciphertext").build();

        RuntimeException mapped = mapper.map(sdkException);

        assertInstanceOf(CryptoInvalidCiphertextException.class, mapped);
        assertSame(sdkException, mapped.getCause());
    }

    @Test
    void givenAesGcmTagMismatchException_whenMap_thenReturnsCryptoInvalidCiphertextExceptionWithCause() {
        AesGcmTagMismatchException localException = new AesGcmTagMismatchException("tag mismatch", null);

        RuntimeException mapped = mapper.map(localException);

        assertInstanceOf(CryptoInvalidCiphertextException.class, mapped);
        assertSame(localException, mapped.getCause());
    }

    @Test
    void givenDisabledException_whenMap_thenReturnsCryptoAccessDeniedExceptionWithCause() {
        DisabledException sdkException = DisabledException.builder().message("key disabled").build();

        RuntimeException mapped = mapper.map(sdkException);

        assertInstanceOf(CryptoAccessDeniedException.class, mapped);
        assertSame(sdkException, mapped.getCause());
    }

    @Test
    void givenInvalidKeyUsageException_whenMap_thenReturnsCryptoConfigurationExceptionWithCause() {
        InvalidKeyUsageException sdkException = InvalidKeyUsageException.builder().message("wrong usage").build();

        RuntimeException mapped = mapper.map(sdkException);

        assertInstanceOf(CryptoConfigurationException.class, mapped);
        assertSame(sdkException, mapped.getCause());
    }

    @Test
    void givenIllegalArgumentException_whenMap_thenReturnsCryptoConfigurationExceptionWithCause() {
        IllegalArgumentException localException = new IllegalArgumentException("bad request");

        RuntimeException mapped = mapper.map(localException);

        assertInstanceOf(CryptoConfigurationException.class, mapped);
        assertSame(localException, mapped.getCause());
    }

    @Test
    void givenSdkClientException_whenMap_thenReturnsCryptoConnectionExceptionWithCause() {
        SdkClientException sdkException = SdkClientException.create("network down");

        RuntimeException mapped = mapper.map(sdkException);

        assertInstanceOf(CryptoConnectionException.class, mapped);
        assertSame(sdkException, mapped.getCause());
    }

    @Test
    void givenUnclassifiedKmsException_whenMap_thenReturnsCryptoGenericExceptionWithCause() {
        KmsException sdkException = (KmsException) KmsException.builder().message("unexpected").build();

        RuntimeException mapped = mapper.map(sdkException);

        assertInstanceOf(CryptoGenericException.class, mapped);
        assertSame(sdkException, mapped.getCause());
    }

    @Test
    void givenNullThrowable_whenMap_thenReturnsCryptoGenericExceptionWithoutCause() {
        RuntimeException mapped = mapper.map(null);

        assertInstanceOf(CryptoGenericException.class, mapped);
        assertNotNull(mapped.getMessage());
    }
}
