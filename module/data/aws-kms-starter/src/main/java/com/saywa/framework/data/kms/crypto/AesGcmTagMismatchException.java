package com.saywa.framework.data.kms.crypto;

/**
 * Thrown by {@link AesGcmCipher#decrypt} when the GCM authentication tag
 * doesn't validate: the payload was tampered with, the wrong key was used,
 * or the additional authenticated data (AAD) doesn't match what was used
 * at encryption time.
 *
 * <p>Unchecked wrapper around {@link javax.crypto.AEADBadTagException} (a
 * checked exception), so it can propagate cleanly through
 * {@code Uni}-based reactive chains. Mapped by {@code KmsExceptionMapper}
 * to {@code CryptoInvalidCiphertextException}.</p>
 */
public final class AesGcmTagMismatchException extends RuntimeException {

    public AesGcmTagMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
