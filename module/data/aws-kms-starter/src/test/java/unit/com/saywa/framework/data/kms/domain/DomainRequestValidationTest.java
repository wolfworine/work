package unit.com.saywa.framework.data.kms.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saywa.framework.data.kms.domain.DataKeySpec;
import com.saywa.framework.data.kms.domain.KmsDecryptRequest;
import com.saywa.framework.data.kms.domain.KmsEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsReEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsSignRequest;
import com.saywa.framework.data.kms.domain.KmsVerifyRequest;
import com.saywa.framework.data.kms.domain.KmsWrapRequest;
import com.saywa.framework.data.kms.domain.MacAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.Test;

class DomainRequestValidationTest {

    private static final byte[] SOME_BYTES = "data".getBytes(StandardCharsets.UTF_8);

    @Test
    void givenBlankKeyRef_whenCreateEncryptRequest_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new KmsEncryptRequest(" ", SOME_BYTES, null));
    }

    @Test
    void givenEmptyPlaintext_whenCreateEncryptRequest_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new KmsEncryptRequest("key", new byte[0], null));
    }

    @Test
    void givenNullEncryptionContext_whenCreateEncryptRequest_thenDefaultsToEmptyMap() {
        KmsEncryptRequest request = new KmsEncryptRequest("key", SOME_BYTES, null);

        assertEquals(Map.of(), request.encryptionContext());
    }

    @Test
    void givenEmptyCipherText_whenCreateDecryptRequest_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new KmsDecryptRequest("key", new byte[0], null));
    }

    @Test
    void givenBlankDestinationKeyRef_whenCreateReEncryptRequest_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new KmsReEncryptRequest("source-key", " ", SOME_BYTES, null, null));
    }

    @Test
    void givenEmptyCipherText_whenCreateReEncryptRequest_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new KmsReEncryptRequest("source-key", "dest-key", new byte[0], null, null));
    }

    @Test
    void givenNullEncryptionContexts_whenCreateReEncryptRequest_thenDefaultToEmptyMaps() {
        KmsReEncryptRequest request = new KmsReEncryptRequest(null, "dest-key", SOME_BYTES, null, null);

        assertEquals(Map.of(), request.sourceEncryptionContext());
        assertEquals(Map.of(), request.destinationEncryptionContext());
    }

    @Test
    void givenNullDataKeySpec_whenCreateEnvelopeEncryptRequest_thenDefaultsToAes256() {
        KmsWrapRequest request = new KmsWrapRequest("key", SOME_BYTES, null, null);

        assertEquals(DataKeySpec.AES_256, request.dataKeySpec());
    }

    @Test
    void givenNullAlgorithm_whenCreateSignRequest_thenDefaultsToHmacSha256() {
        KmsSignRequest request = new KmsSignRequest("key", SOME_BYTES, null);

        assertEquals(MacAlgorithm.HMAC_SHA_256, request.algorithm());
    }

    @Test
    void givenEmptyMac_whenCreateVerifyRequest_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new KmsVerifyRequest("key", SOME_BYTES, new byte[0], null));
    }

    @Test
    void givenValidVerifyRequest_whenCreated_thenFieldsArePreserved() {
        KmsVerifyRequest request = new KmsVerifyRequest("key", SOME_BYTES, SOME_BYTES, MacAlgorithm.HMAC_SHA_512);

        assertEquals("key", request.keyRef());
        assertTrue(request.algorithm() == MacAlgorithm.HMAC_SHA_512);
    }
}
