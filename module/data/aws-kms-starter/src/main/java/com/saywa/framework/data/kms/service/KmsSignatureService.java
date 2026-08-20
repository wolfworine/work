package com.saywa.framework.data.kms.service;

import com.saywa.framework.data.kms.domain.KmsSignRequest;
import com.saywa.framework.data.kms.domain.KmsSignResponse;
import com.saywa.framework.data.kms.domain.KmsVerifyRequest;
import com.saywa.framework.data.kms.domain.KmsVerifyResponse;
import io.smallrye.mutiny.Uni;

/**
 * Reactive contract for AWS KMS MAC-based signing, backed by
 * {@code KmsAsyncClient}. All methods return {@code Uni<T>} and never block
 * the calling thread. No AWS SDK type is exposed by this interface — only
 * this starter's own {@code domain/} records.
 *
 * <p>Backed by KMS's {@code GenerateMac}/{@code VerifyMac} against an
 * {@code HMAC_*} key — the symmetric equivalent of a digital signature: the
 * same key both signs and verifies, and it never leaves KMS. True
 * asymmetric signing (RSASSA/ECDSA) is out of scope for this phase and will
 * be added when this starter grows asymmetric support.</p>
 */
public interface KmsSignatureService {

    /**
     * Signs a message. Backed by KMS's {@code GenerateMac} against an
     * {@code HMAC_*} key.
     *
     * @param request the message, target HMAC key, and algorithm.
     * @return the resulting MAC.
     */
    Uni<KmsSignResponse> sign(KmsSignRequest request);

    /**
     * Verifies a MAC previously produced by {@link #sign}. Backed by KMS's
     * {@code VerifyMac}. An invalid MAC is a valid business result — not an
     * exception — so callers must check {@link KmsVerifyResponse#valid()}.
     *
     * @param request the original message, the MAC to validate, the key, and the algorithm.
     * @return the verification outcome.
     */
    Uni<KmsVerifyResponse> verify(KmsVerifyRequest request);
}
