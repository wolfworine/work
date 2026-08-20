package com.saywa.framework.data.kms.service;

import com.saywa.framework.data.kms.domain.KmsUnwrapRequest;
import com.saywa.framework.data.kms.domain.KmsUnwrapResponse;
import com.saywa.framework.data.kms.domain.KmsWrapRequest;
import com.saywa.framework.data.kms.domain.KmsWrapResponse;
import io.smallrye.mutiny.Uni;

/**
 * Reactive contract for AWS KMS envelope encryption, backed by
 * {@code KmsAsyncClient}. All methods return {@code Uni<T>} and never block
 * the calling thread. No AWS SDK type is exposed by this interface — only
 * this starter's own {@code domain/} records.
 *
 * <p>Envelope encryption generates a one-time AES data key via KMS,
 * ciphers the payload locally with AES-GCM, and discards the plaintext key
 * immediately — the pattern AWS recommends for payloads that may exceed
 * the 4096-byte limit of {@link KmsCryptoService#encrypt}.</p>
 */
public interface KmsKeyWrapService {

    /**
     * Envelope-encrypts a payload of any size: generates a one-time AES
     * data key via KMS, ciphers the payload locally with AES-GCM, and
     * discards the plaintext key immediately.
     *
     * @param request the payload, target key, and data key spec.
     * @return the envelope: the encrypted data key plus the locally-ciphered payload.
     */
    Uni<KmsWrapResponse> wrap(KmsWrapRequest request);

    /**
     * Reverses {@link #wrap}: decrypts the envelope's data key
     * against KMS, then deciphers the payload locally with AES-GCM.
     *
     * @param request the envelope and matching encryption context.
     * @return the original plaintext, wrapped with the key that decrypted it.
     * @throws com.saywa.framework.core.error.exceptions.CryptoInvalidCiphertextException
     *         if the payload was tampered with, the data key doesn't match,
     *         or the encryption context doesn't match.
     */
    Uni<KmsUnwrapResponse> unwrap(KmsUnwrapRequest request);
}
