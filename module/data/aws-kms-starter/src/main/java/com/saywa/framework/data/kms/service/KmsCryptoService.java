package com.saywa.framework.data.kms.service;

import com.saywa.framework.data.kms.domain.CipherText;
import com.saywa.framework.data.kms.domain.KmsDecryptRequest;
import com.saywa.framework.data.kms.domain.KmsDecryptResponse;
import com.saywa.framework.data.kms.domain.KmsEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsReEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsReEncryptResponse;
import io.smallrye.mutiny.Uni;

/**
 * Reactive contract for AWS KMS direct symmetric cryptography, backed by
 * {@code KmsAsyncClient}. All methods return {@code Uni<T>} and never block
 * the calling thread. No AWS SDK type is exposed by this interface — only
 * this starter's own {@code domain/} records.
 *
 * <p>Covers KMS's direct {@code Encrypt}/{@code Decrypt} operations against
 * a {@code SYMMETRIC_DEFAULT} CMK, limited to 4096 bytes of plaintext. For
 * payloads of any size, see {@link KmsKeyWrapService}; for MAC-based
 * signing, see {@link KmsSignatureService}.</p>
 */
public interface KmsCryptoService {

    /**
     * Ciphers a small payload directly against a symmetric CMK (KMS's
     * {@code Encrypt} operation).
     *
     * @param request the payload, target key, and optional encryption context.
     * @return the resulting ciphertext.
     * @throws com.saywa.framework.core.error.exceptions.CryptoConfigurationException
     *         if {@code request.plaintext()} exceeds the configured or AWS
     *         hard limit (4096 bytes) — emitted before calling KMS.
     */
    Uni<CipherText> encrypt(KmsEncryptRequest request);

    /**
     * Decrypts ciphertext previously produced by {@link #encrypt}.
     *
     * @param request the ciphertext, optional key reference, and
     *                encryption context (must match what was used to encrypt).
     * @return the original plaintext, wrapped with the key that decrypted it.
     * @throws com.saywa.framework.core.error.exceptions.CryptoInvalidCiphertextException
     *         if the ciphertext was tampered with, the wrong key was used,
     *         or the encryption context doesn't match.
     */
    Uni<KmsDecryptResponse> decrypt(KmsDecryptRequest request);

    /**
     * Moves ciphertext from one symmetric CMK to another without ever
     * exposing the plaintext outside KMS (KMS's {@code ReEncrypt} operation):
     * the source ciphertext is decrypted and the result re-encrypted under
     * the destination key in a single call.
     *
     * @param request the ciphertext, source and destination keys, and both encryption contexts.
     * @return the ciphertext re-encrypted under the destination key.
     * @throws com.saywa.framework.core.error.exceptions.CryptoInvalidCiphertextException
     *         if the ciphertext was tampered with, the wrong source key was
     *         used, or the source encryption context doesn't match.
     */
    Uni<KmsReEncryptResponse> reEncrypt(KmsReEncryptRequest request);
}
