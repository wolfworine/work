package unit.com.saywa.framework.data.kms.crypto;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.saywa.framework.data.kms.crypto.AesGcmCipher;
import com.saywa.framework.data.kms.crypto.AesGcmTagMismatchException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import org.junit.jupiter.api.Test;

class AesGcmCipherTest {

    private final AesGcmCipher cipher = new AesGcmCipher();

    private byte[] randomKey() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        return key;
    }

    @Test
    void givenValidKeyAndPlaintext_whenEncryptThenDecrypt_thenReturnsOriginalPlaintext() {
        byte[] key = randomKey();
        byte[] plaintext = "hello envelope".getBytes(StandardCharsets.UTF_8);

        byte[] ciphertext = cipher.encrypt(key, plaintext, new byte[0]);
        byte[] decrypted = cipher.decrypt(key, ciphertext, new byte[0]);

        assertArrayEquals(plaintext, decrypted);
        assertNotEquals(new String(plaintext, StandardCharsets.UTF_8), new String(ciphertext, StandardCharsets.UTF_8));
    }

    @Test
    void givenSameKeyAndPlaintext_whenEncryptTwice_thenIvDiffersAndCiphertextDiffers() {
        byte[] key = randomKey();
        byte[] plaintext = "same input twice".getBytes(StandardCharsets.UTF_8);

        byte[] first = cipher.encrypt(key, plaintext, new byte[0]);
        byte[] second = cipher.encrypt(key, plaintext, new byte[0]);

        assertNotEquals(new String(first, StandardCharsets.UTF_8), new String(second, StandardCharsets.UTF_8));
    }

    @Test
    void givenMatchingAad_whenDecrypt_thenSucceeds() {
        byte[] key = randomKey();
        byte[] plaintext = "with context".getBytes(StandardCharsets.UTF_8);
        byte[] aad = "purpose=test".getBytes(StandardCharsets.UTF_8);

        byte[] ciphertext = cipher.encrypt(key, plaintext, aad);
        byte[] decrypted = cipher.decrypt(key, ciphertext, aad);

        assertArrayEquals(plaintext, decrypted);
    }

    @Test
    void givenMismatchedAad_whenDecrypt_thenThrowsAesGcmTagMismatchException() {
        byte[] key = randomKey();
        byte[] ciphertext = cipher.encrypt(key, "payload".getBytes(StandardCharsets.UTF_8),
                "purpose=test".getBytes(StandardCharsets.UTF_8));

        assertThrows(AesGcmTagMismatchException.class,
                () -> cipher.decrypt(key, ciphertext, "purpose=other".getBytes(StandardCharsets.UTF_8)));
    }

    @Test
    void givenWrongKey_whenDecrypt_thenThrowsAesGcmTagMismatchException() {
        byte[] ciphertext = cipher.encrypt(randomKey(), "payload".getBytes(StandardCharsets.UTF_8), new byte[0]);

        assertThrows(AesGcmTagMismatchException.class, () -> cipher.decrypt(randomKey(), ciphertext, new byte[0]));
    }

    @Test
    void givenTamperedCiphertext_whenDecrypt_thenThrowsAesGcmTagMismatchException() {
        byte[] key = randomKey();
        byte[] ciphertext = cipher.encrypt(key, "payload".getBytes(StandardCharsets.UTF_8), new byte[0]);
        ciphertext[ciphertext.length - 1] ^= 0x01;

        assertThrows(AesGcmTagMismatchException.class, () -> cipher.decrypt(key, ciphertext, new byte[0]));
    }

    @Test
    void givenPayloadShorterThanIvPlusTag_whenDecrypt_thenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> cipher.decrypt(randomKey(), new byte[5], new byte[0]));
    }
}
