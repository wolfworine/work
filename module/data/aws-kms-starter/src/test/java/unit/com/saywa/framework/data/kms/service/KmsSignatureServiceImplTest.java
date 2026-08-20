package unit.com.saywa.framework.data.kms.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.saywa.framework.data.kms.audit.KmsAuditLogger;
import com.saywa.framework.data.kms.config.KmsConfiguration;
import com.saywa.framework.data.kms.config.KmsConfigurationValidator;
import com.saywa.framework.data.kms.domain.KmsSignRequest;
import com.saywa.framework.data.kms.domain.KmsSignResponse;
import com.saywa.framework.data.kms.domain.KmsVerifyRequest;
import com.saywa.framework.data.kms.domain.KmsVerifyResponse;
import com.saywa.framework.data.kms.domain.MacAlgorithm;
import com.saywa.framework.data.kms.exception.KmsExceptionMapper;
import com.saywa.framework.data.kms.factory.KmsRequestFactory;
import com.saywa.framework.data.kms.mapper.KmsResponseMapper;
import com.saywa.framework.data.kms.service.KmsOperationSupport;
import com.saywa.framework.data.kms.service.impl.KmsSignatureServiceImpl;
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
import software.amazon.awssdk.services.kms.model.GenerateMacResponse;
import software.amazon.awssdk.services.kms.model.VerifyMacResponse;

/**
 * Unit tests for {@link KmsSignatureServiceImpl} (MAC sign/verify).
 * <p>
 * {@link KmsAsyncClient} is simulated with Mockito; {@link KmsRequestFactory},
 * {@link KmsResponseMapper}, and {@link KmsExceptionMapper} are used in
 * their real implementation.
 */
@ExtendWith(MockitoExtension.class)
class KmsSignatureServiceImplTest {

    private static final String KEY_REF = "alias/test-key";

    @Mock
    private KmsAsyncClient kmsAsyncClient;
    @Mock
    private KmsConfiguration configuration;
    @Mock
    private KmsAuditLogger auditLogger;
    @Mock
    private KmsConfigurationValidator configurationValidator;

    private KmsSignatureServiceImpl service;

    @BeforeEach
    void setUp() {
        lenient().when(configuration.maxMacMessageSize()).thenReturn(4096);
        lenient().when(configuration.auditEnabled()).thenReturn(true);

        KmsOperationSupport support = new KmsOperationSupport(configuration, auditLogger,
                new KmsExceptionMapper(), configurationValidator);
        service = new KmsSignatureServiceImpl(kmsAsyncClient, new KmsRequestFactory(),
                new KmsResponseMapper(), support);
    }

    @Test
    void givenValidMessage_whenSign_thenReturnsSignatureResult() {
        byte[] mac = "mac-bytes".getBytes(StandardCharsets.UTF_8);
        when(kmsAsyncClient.generateMac(any(software.amazon.awssdk.services.kms.model.GenerateMacRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(GenerateMacResponse.builder()
                        .keyId(KEY_REF)
                        .mac(SdkBytes.fromByteArray(mac))
                        .build()));

        KmsSignResponse result = service.sign(new KmsSignRequest(KEY_REF, "message".getBytes(StandardCharsets.UTF_8), null))
                .await().atMost(Duration.ofSeconds(5));

        assertEquals(MacAlgorithm.HMAC_SHA_256, result.algorithm());
        assertArrayEquals(mac, result.value());
    }

    @Test
    void givenValidMac_whenVerify_thenReturnsValidTrue() {
        when(kmsAsyncClient.verifyMac(any(software.amazon.awssdk.services.kms.model.VerifyMacRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(VerifyMacResponse.builder()
                        .keyId(KEY_REF)
                        .macValid(true)
                        .build()));

        KmsVerifyResponse result = service.verify(new KmsVerifyRequest(KEY_REF,
                        "message".getBytes(StandardCharsets.UTF_8), "mac".getBytes(StandardCharsets.UTF_8), null))
                .await().atMost(Duration.ofSeconds(5));

        assertTrue(result.valid());
    }

    @Test
    void givenTamperedMessage_whenVerify_thenReturnsValidFalseWithoutThrowing() {
        when(kmsAsyncClient.verifyMac(any(software.amazon.awssdk.services.kms.model.VerifyMacRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(VerifyMacResponse.builder()
                        .keyId(KEY_REF)
                        .macValid(false)
                        .build()));

        KmsVerifyResponse result = service.verify(new KmsVerifyRequest(KEY_REF,
                        "tampered".getBytes(StandardCharsets.UTF_8), "mac".getBytes(StandardCharsets.UTF_8), null))
                .await().atMost(Duration.ofSeconds(5));

        assertFalse(result.valid());
    }
}
