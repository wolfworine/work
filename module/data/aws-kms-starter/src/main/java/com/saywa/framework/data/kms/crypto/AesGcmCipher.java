package com.saywa.framework.data.kms.crypto;

import jakarta.enterprise.context.ApplicationScoped;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.AEADBadTagException;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * In-process AES-GCM cipher used by envelope encryption with the plaintext
 * data key that KMS generates. This is what lets envelope-encrypted
 * payloads be of any size: the large payload never travels to AWS, only
 * the small AES data key does.
 *
 * <p>Output format: {@code iv (12 bytes) || ciphertext || tag (16 bytes)}.
 * A fresh random IV is generated on every call — reusing an IV with the
 * same key completely breaks GCM's confidentiality and integrity
 * guarantees.</p>
 *
 * <p>Not a CDI {@code @ApplicationScoped} bean because it holds SDK/AWS
 * state — it's pure Java, safe to share, and kept as a bean only so it can
 * be {@code @Inject}ed like the rest of the starter's collaborators.</p>
 */
@ApplicationScoped
public class AesGcmCipher {

    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String ALGORITHM = "AES";
    private static final int IV_LENGTH_BYTES = 12;
    private static final int TAG_LENGTH_BITS = 128;

    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * @param key       AES key material; length must match one of {@code DataKeySpec}'s sizes.
     * @param plaintext data to cipher.
     * @param aad       additional authenticated data; not encrypted, but
     *                  alters the tag — pass an empty array when there is none.
     * @return {@code iv || ciphertext || tag} as a single array.
     */
    public byte[] encrypt(byte[] key, byte[] plaintext, byte[] aad) {
        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, ALGORITHM),
                    new GCMParameterSpec(TAG_LENGTH_BITS, iv));
            if (aad != null && aad.length > 0) {
                cipher.updateAAD(aad);
            }
            byte[] sealed = cipher.doFinal(plaintext);

            return ByteBuffer.allocate(iv.length + sealed.length)
                    .put(iv)
                    .put(sealed)
                    .array();
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("AES-GCM encrypt failed: " + e.getMessage(), e);
        }
    }

    /**
     * @param key     the same AES key material used to encrypt.
     * @param payload {@code iv || ciphertext || tag}, as produced by {@link #encrypt}.
     * @param aad     must be identical to the AAD used at encryption time, or the tag fails to validate.
     * @return the original plaintext.
     * @throws AesGcmTagMismatchException if the tag doesn't validate: the
     *                                     payload was tampered with, the
     *                                     wrong key was used, or the AAD
     *                                     doesn't match. Mapped by
     *                                     {@code KmsExceptionMapper} to
     *                                     {@code CryptoInvalidCiphertextException}.
     */
    public byte[] decrypt(byte[] key, byte[] payload, byte[] aad) {
        if (payload == null || payload.length <= IV_LENGTH_BYTES) {
            throw new IllegalArgumentException(
                    "The encrypted payload is too short: it doesn't contain an IV plus an AES-GCM tag");
        }
        try {
            ByteBuffer buffer = ByteBuffer.wrap(payload);
            byte[] iv = new byte[IV_LENGTH_BYTES];
            buffer.get(iv);
            byte[] sealed = new byte[buffer.remaining()];
            buffer.get(sealed);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, ALGORITHM),
                    new GCMParameterSpec(TAG_LENGTH_BITS, iv));
            if (aad != null && aad.length > 0) {
                cipher.updateAAD(aad);
            }
            return cipher.doFinal(sealed);
        } catch (AEADBadTagException e) {
            throw new AesGcmTagMismatchException(
                    "The GCM authentication tag is invalid: data was tampered with, the wrong data key was used, or the encryption context changed",
                    e);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("AES-GCM decrypt failed: " + e.getMessage(), e);
        }
    }
}
