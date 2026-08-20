package unit.com.saywa.framework.data.kms.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.saywa.framework.core.error.exceptions.CryptoConfigurationException;
import com.saywa.framework.data.kms.audit.KmsAuditLogger;
import com.saywa.framework.data.kms.config.KmsConfiguration;
import com.saywa.framework.data.kms.config.KmsConfigurationValidator;
import com.saywa.framework.data.kms.domain.CipherText;
import com.saywa.framework.data.kms.domain.KmsDecryptRequest;
import com.saywa.framework.data.kms.domain.KmsDecryptResponse;
import com.saywa.framework.data.kms.domain.KmsEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsReEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsReEncryptResponse;
import com.saywa.framework.data.kms.exception.KmsExceptionMapper;
import com.saywa.framework.data.kms.factory.KmsRequestFactory;
import com.saywa.framework.data.kms.mapper.KmsResponseMapper;
import com.saywa.framework.data.kms.service.impl.KmsCryptoServiceImpl;
import com.saywa.framework.data.kms.service.KmsOperationSupport;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsAsyncClient;
import software.amazon.awssdk.services.kms.model.DecryptResponse;
import software.amazon.awssdk.services.kms.model.EncryptResponse;
import software.amazon.awssdk.services.kms.model.NotFoundException;
import software.amazon.awssdk.services.kms.model.ReEncryptResponse;

/**
 * Unit tests for {@link KmsCryptoServiceImpl} (direct encrypt/decrypt only —
 * envelope encryption and sign/verify have their own service and test
 * classes).
 * <p>
 * {@link KmsAsyncClient} is simulated with Mockito (SDK responses via
 * {@link CompletableFuture#completedFuture}/{@link CompletableFuture#failedFuture});
 * {@link KmsRequestFactory}, {@link KmsResponseMapper}, and
 * {@link KmsExceptionMapper} are used in their real implementation to
 * verify the service's end-to-end behavior, matching the same approach
 * {@code S3StorageServiceImplTest} uses for {@code S3AsyncClient}.
 */
@ExtendWith(MockitoExtension.class)
class KmsCryptoServiceImplTest {

    private static final String KEY_REF = "alias/test-key";

    @Mock
    private KmsAsyncClient kmsAsyncClient;
    @Mock
    private KmsConfiguration configuration;
    @Mock
    private KmsAuditLogger auditLogger;
    @Mock
    private KmsConfigurationValidator configurationValidator;

    private KmsCryptoServiceImpl service;

    @BeforeEach
    void setUp() {
        lenient().when(configuration.maxDirectPlaintextSize()).thenReturn(4096);
        lenient().when(configuration.auditEnabled()).thenReturn(true);

        KmsOperationSupport support = new KmsOperationSupport(configuration, auditLogger,
                new KmsExceptionMapper(), configurationValidator);
        service = new KmsCryptoServiceImpl(kmsAsyncClient, new KmsRequestFactory(),
                new KmsResponseMapper(), support);
    }

    // encrypt()

    @Test
    void givenValidRequest_whenEncrypt_thenReturnsCipherTextAndAuditsSuccess() {
        byte[] plaintext = "secret data".getBytes(StandardCharsets.UTF_8);
        byte[] ciphertextBytes = "cipher-bytes".getBytes(StandardCharsets.UTF_8);
        when(kmsAsyncClient.encrypt(any(software.amazon.awssdk.services.kms.model.EncryptRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(EncryptResponse.builder()
                        .keyId(KEY_REF)
                        .ciphertextBlob(SdkBytes.fromByteArray(ciphertextBytes))
                        .build()));

        CipherText result = service.encrypt(new KmsEncryptRequest(KEY_REF, plaintext, null))
                .await().atMost(Duration.ofSeconds(5));

        assertEquals(KEY_REF, result.keyId());
        assertArrayEquals(ciphertextBytes, result.value());
        verify(auditLogger).audit(argThatSuccessful());
    }

    @Test
    void givenPlaintextExceedsConfiguredMaxSize_whenEncrypt_thenFailsWithoutCallingSdk() {
        when(configuration.maxDirectPlaintextSize()).thenReturn(10);
        byte[] plaintext = "this plaintext is longer than ten bytes".getBytes(StandardCharsets.UTF_8);

        Throwable failure = service.encrypt(new KmsEncryptRequest(KEY_REF, plaintext, null))
                .subscribe().withSubscriber(io.smallrye.mutiny.helpers.test.UniAssertSubscriber.create())
                .awaitFailure().getFailure();

        assertInstanceOf(CryptoConfigurationException.class, failure);
        verifyNoInteractions(kmsAsyncClient);
    }

    // decrypt()

    @Test
    void givenValidCipherText_whenDecrypt_thenReturnsPlaintext() {
        byte[] plaintext = "recovered".getBytes(StandardCharsets.UTF_8);
        when(kmsAsyncClient.decrypt(any(software.amazon.awssdk.services.kms.model.DecryptRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(DecryptResponse.builder()
                        .keyId(KEY_REF)
                        .plaintext(SdkBytes.fromByteArray(plaintext))
                        .build()));

        KmsDecryptResponse result = service.decrypt(
                        new KmsDecryptRequest(KEY_REF, "cipher".getBytes(StandardCharsets.UTF_8), null))
                .await().atMost(Duration.ofSeconds(5));

        assertEquals(KEY_REF, result.keyId());
        assertArrayEquals(plaintext, result.plaintext());
    }

    @Test
    void givenKeyNotFound_whenDecrypt_thenFailsWithMappedExceptionAndAuditsFailure() {
        NotFoundException sdkException = NotFoundException.builder().message("no such key").build();
        when(kmsAsyncClient.decrypt(any(software.amazon.awssdk.services.kms.model.DecryptRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(sdkException));

        Throwable failure = service.decrypt(
                        new KmsDecryptRequest(KEY_REF, "cipher".getBytes(StandardCharsets.UTF_8), null))
                .subscribe().withSubscriber(io.smallrye.mutiny.helpers.test.UniAssertSubscriber.create())
                .awaitFailure().getFailure();

        assertInstanceOf(com.saywa.framework.core.error.exceptions.CryptoKeyNotFoundException.class, failure);
        assertEquals(sdkException, failure.getCause());
        verify(auditLogger).audit(argThatFailed());
    }

    // reEncrypt()

    @Test
    void givenValidCipherText_whenReEncrypt_thenReturnsCipherTextUnderDestinationKey() {
        String destinationKeyRef = "alias/dest-key";
        byte[] reEncrypted = "re-encrypted".getBytes(StandardCharsets.UTF_8);
        when(kmsAsyncClient.reEncrypt(any(software.amazon.awssdk.services.kms.model.ReEncryptRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(ReEncryptResponse.builder()
                        .sourceKeyId(KEY_REF)
                        .keyId(destinationKeyRef)
                        .ciphertextBlob(SdkBytes.fromByteArray(reEncrypted))
                        .build()));

        KmsReEncryptResponse result = service.reEncrypt(new KmsReEncryptRequest(
                        KEY_REF, destinationKeyRef, "cipher".getBytes(StandardCharsets.UTF_8), null, null))
                .await().atMost(Duration.ofSeconds(5));

        assertEquals(KEY_REF, result.sourceKeyId());
        assertEquals(destinationKeyRef, result.destinationKeyId());
        assertArrayEquals(reEncrypted, result.cipherText());
        verify(auditLogger).audit(argThatSuccessful());
    }

    // helpers

    private com.saywa.framework.data.kms.audit.KmsAuditEvent argThatSuccessful() {
        return org.mockito.ArgumentMatchers.argThat(com.saywa.framework.data.kms.audit.KmsAuditEvent::successful);
    }

    private com.saywa.framework.data.kms.audit.KmsAuditEvent argThatFailed() {
        return org.mockito.ArgumentMatchers.argThat(event -> !event.successful());
    }
}
