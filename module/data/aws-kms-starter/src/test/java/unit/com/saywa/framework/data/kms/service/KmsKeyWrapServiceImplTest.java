package unit.com.saywa.framework.data.kms.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.saywa.framework.data.kms.audit.KmsAuditLogger;
import com.saywa.framework.data.kms.config.KmsConfiguration;
import com.saywa.framework.data.kms.config.KmsConfigurationValidator;
import com.saywa.framework.data.kms.crypto.AesGcmCipher;
import com.saywa.framework.data.kms.domain.DataKeySpec;
import com.saywa.framework.data.kms.domain.KmsWrapResponse;
import com.saywa.framework.data.kms.domain.KmsUnwrapRequest;
import com.saywa.framework.data.kms.domain.KmsUnwrapResponse;
import com.saywa.framework.data.kms.domain.KmsWrapRequest;
import com.saywa.framework.data.kms.exception.KmsExceptionMapper;
import com.saywa.framework.data.kms.factory.KmsRequestFactory;
import com.saywa.framework.data.kms.mapper.KmsResponseMapper;
import com.saywa.framework.data.kms.service.impl.KmsKeyWrapServiceImpl;
import com.saywa.framework.data.kms.service.KmsOperationSupport;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsAsyncClient;
import software.amazon.awssdk.services.kms.model.DecryptResponse;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyResponse;

/**
 * Unit tests for {@link KmsKeyWrapServiceImpl} (envelope encryption
 * roundtrip).
 * <p>
 * {@link KmsAsyncClient} is simulated with Mockito;
 * {@link KmsRequestFactory}, {@link KmsResponseMapper}, {@link AesGcmCipher},
 * and {@link KmsExceptionMapper} are used in their real implementation.
 */
@ExtendWith(MockitoExtension.class)
class KmsKeyWrapServiceImplTest {

    private static final String KEY_REF = "alias/test-key";

    @Mock
    private KmsAsyncClient kmsAsyncClient;
    @Mock
    private KmsConfiguration configuration;
    @Mock
    private KmsAuditLogger auditLogger;
    @Mock
    private KmsConfigurationValidator configurationValidator;

    private KmsKeyWrapServiceImpl service;

    @BeforeEach
    void setUp() {
        lenient().when(configuration.auditEnabled()).thenReturn(true);

        KmsOperationSupport support = new KmsOperationSupport(configuration, auditLogger,
                new KmsExceptionMapper(), configurationValidator);
        service = new KmsKeyWrapServiceImpl(kmsAsyncClient, new KmsRequestFactory(),
                new KmsResponseMapper(), new AesGcmCipher(), support);
    }

    @Test
    void givenLargePayload_whenWrapThenUnwrapEnvelope_thenRecoversOriginalPlaintext() {
        byte[] dataKeyPlaintext = new byte[32];
        new SecureRandom().nextBytes(dataKeyPlaintext);
        byte[] encryptedDataKey = "encrypted-data-key".getBytes(StandardCharsets.UTF_8);
        byte[] largePayload = "x".repeat(10_000).getBytes(StandardCharsets.UTF_8);
        Map<String, String> context = Map.of("purpose", "test");

        when(kmsAsyncClient.generateDataKey(any(software.amazon.awssdk.services.kms.model.GenerateDataKeyRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(GenerateDataKeyResponse.builder()
                        .keyId(KEY_REF)
                        .plaintext(SdkBytes.fromByteArray(dataKeyPlaintext.clone()))
                        .ciphertextBlob(SdkBytes.fromByteArray(encryptedDataKey))
                        .build()));

        KmsWrapResponse envelope = service.wrap(
                        new KmsWrapRequest(KEY_REF, largePayload, DataKeySpec.AES_256, context))
                .await().atMost(Duration.ofSeconds(5));

        assertEquals(KEY_REF, envelope.keyId());
        assertArrayEquals(encryptedDataKey, envelope.encryptedDataKey());

        // unwrap: KMS "decrypts" the data key back to its plaintext form
        when(kmsAsyncClient.decrypt(any(software.amazon.awssdk.services.kms.model.DecryptRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(DecryptResponse.builder()
                        .keyId(KEY_REF)
                        .plaintext(SdkBytes.fromByteArray(dataKeyPlaintext.clone()))
                        .build()));

        KmsUnwrapResponse recovered = service.unwrap(new KmsUnwrapRequest(envelope, context))
                .await().atMost(Duration.ofSeconds(5));

        assertEquals(KEY_REF, recovered.keyId());
        assertArrayEquals(largePayload, recovered.plaintext());
    }

    @Test
    void givenMismatchedEncryptionContext_whenUnwrapEnvelope_thenFailsWithInvalidCiphertextException() {
        byte[] dataKeyPlaintext = new byte[32];
        new SecureRandom().nextBytes(dataKeyPlaintext);

        when(kmsAsyncClient.generateDataKey(any(software.amazon.awssdk.services.kms.model.GenerateDataKeyRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(GenerateDataKeyResponse.builder()
                        .keyId(KEY_REF)
                        .plaintext(SdkBytes.fromByteArray(dataKeyPlaintext.clone()))
                        .ciphertextBlob(SdkBytes.fromByteArray("edk".getBytes(StandardCharsets.UTF_8)))
                        .build()));

        KmsWrapResponse envelope = service.wrap(new KmsWrapRequest(
                        KEY_REF, "payload".getBytes(StandardCharsets.UTF_8), DataKeySpec.AES_256, Map.of("purpose", "a")))
                .await().atMost(Duration.ofSeconds(5));

        when(kmsAsyncClient.decrypt(any(software.amazon.awssdk.services.kms.model.DecryptRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(DecryptResponse.builder()
                        .keyId(KEY_REF)
                        .plaintext(SdkBytes.fromByteArray(dataKeyPlaintext.clone()))
                        .build()));

        Throwable failure = service.unwrap(new KmsUnwrapRequest(envelope, Map.of("purpose", "b")))
                .subscribe().withSubscriber(io.smallrye.mutiny.helpers.test.UniAssertSubscriber.create())
                .awaitFailure().getFailure();

        assertInstanceOf(com.saywa.framework.core.error.exceptions.CryptoInvalidCiphertextException.class, failure);
    }
}
