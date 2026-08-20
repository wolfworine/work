package com.saywa.framework.data.kms.factory;

import com.saywa.framework.data.kms.domain.DataKeySpec;
import com.saywa.framework.data.kms.domain.KmsDecryptRequest;
import com.saywa.framework.data.kms.domain.KmsEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsReEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsSignRequest;
import com.saywa.framework.data.kms.domain.KmsVerifyRequest;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.model.DecryptRequest.Builder;
import software.amazon.awssdk.services.kms.model.EncryptionAlgorithmSpec;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyRequest;
import software.amazon.awssdk.services.kms.model.GenerateMacRequest;
import software.amazon.awssdk.services.kms.model.MacAlgorithmSpec;
import software.amazon.awssdk.services.kms.model.ReEncryptRequest;
import software.amazon.awssdk.services.kms.model.VerifyMacRequest;

/**
 * Builds AWS SDK KMS request objects from this starter's domain records.
 * The only point in {@code factory/} that imports
 * {@code software.amazon.awssdk} types.
 */
@ApplicationScoped
public class KmsRequestFactory {

    /**
     * @param request the domain request to cipher a small payload directly.
     * @return an SDK {@code EncryptRequest} against a {@code SYMMETRIC_DEFAULT} CMK.
     */
    public software.amazon.awssdk.services.kms.model.EncryptRequest createEncryptRequest(KmsEncryptRequest request) {
        return software.amazon.awssdk.services.kms.model.EncryptRequest.builder()
                .keyId(request.keyRef())
                .plaintext(SdkBytes.fromByteArray(request.plaintext()))
                .encryptionAlgorithm(EncryptionAlgorithmSpec.SYMMETRIC_DEFAULT)
                .encryptionContext(request.encryptionContext())
                .build();
    }

    /**
     * @param request the domain request to decrypt ciphertext.
     * @return an SDK {@code DecryptRequest}; {@code keyId} is omitted when
     *         {@code request.keyRef()} is {@code null}, letting KMS resolve
     *         the CMK from the ciphertext blob itself.
     */
    public software.amazon.awssdk.services.kms.model.DecryptRequest createDecryptRequest(KmsDecryptRequest request) {
        Builder builder = software.amazon.awssdk.services.kms.model.DecryptRequest.builder()
                .ciphertextBlob(SdkBytes.fromByteArray(request.cipherText()))
                .encryptionAlgorithm(EncryptionAlgorithmSpec.SYMMETRIC_DEFAULT)
                .encryptionContext(request.encryptionContext());
        if (request.keyRef() != null && !request.keyRef().isBlank()) {
            builder.keyId(request.keyRef());
        }
        return builder.build();
    }

    /**
     * @param request the domain request to move ciphertext from one symmetric CMK to another.
     * @return an SDK {@code ReEncryptRequest}; {@code sourceKeyId} is omitted
     *         when {@code request.sourceKeyRef()} is {@code null}, letting
     *         KMS resolve the source CMK from the ciphertext blob itself.
     */
    public ReEncryptRequest createReEncryptRequest(KmsReEncryptRequest request) {
        ReEncryptRequest.Builder builder = ReEncryptRequest.builder()
                .ciphertextBlob(SdkBytes.fromByteArray(request.cipherText()))
                .destinationKeyId(request.destinationKeyRef())
                .sourceEncryptionAlgorithm(EncryptionAlgorithmSpec.SYMMETRIC_DEFAULT)
                .destinationEncryptionAlgorithm(EncryptionAlgorithmSpec.SYMMETRIC_DEFAULT)
                .sourceEncryptionContext(request.sourceEncryptionContext())
                .destinationEncryptionContext(request.destinationEncryptionContext());
        if (request.sourceKeyRef() != null && !request.sourceKeyRef().isBlank()) {
            builder.sourceKeyId(request.sourceKeyRef());
        }
        return builder.build();
    }

    /**
     * @param keyRef            key id, ARN, or alias of the symmetric CMK that will wrap the data key.
     * @param spec               length of the AES data key to generate.
     * @param encryptionContext additional authenticated data; never {@code null}.
     * @return an SDK {@code GenerateDataKeyRequest}.
     */
    public GenerateDataKeyRequest createGenerateDataKeyRequest(String keyRef, DataKeySpec spec,
            Map<String, String> encryptionContext) {
        return GenerateDataKeyRequest.builder()
                .keyId(keyRef)
                .keySpec(spec == DataKeySpec.AES_128
                        ? software.amazon.awssdk.services.kms.model.DataKeySpec.AES_128
                        : software.amazon.awssdk.services.kms.model.DataKeySpec.AES_256)
                .encryptionContext(encryptionContext)
                .build();
    }

    /**
     * @param request the domain request to sign a message with an HMAC key.
     * @return an SDK {@code GenerateMacRequest}.
     */
    public GenerateMacRequest createGenerateMacRequest(KmsSignRequest request) {
        return GenerateMacRequest.builder()
                .keyId(request.keyRef())
                .message(SdkBytes.fromByteArray(request.message()))
                .macAlgorithm(toMacAlgorithmSpec(request.algorithm()))
                .build();
    }

    /**
     * @param request the domain request to validate a MAC.
     * @return an SDK {@code VerifyMacRequest}.
     */
    public VerifyMacRequest createVerifyMacRequest(KmsVerifyRequest request) {
        return VerifyMacRequest.builder()
                .keyId(request.keyRef())
                .message(SdkBytes.fromByteArray(request.message()))
                .mac(SdkBytes.fromByteArray(request.mac()))
                .macAlgorithm(toMacAlgorithmSpec(request.algorithm()))
                .build();
    }

    private MacAlgorithmSpec toMacAlgorithmSpec(com.saywa.framework.data.kms.domain.MacAlgorithm algorithm) {
        return switch (algorithm) {
            case HMAC_SHA_224 -> MacAlgorithmSpec.HMAC_SHA_224;
            case HMAC_SHA_256 -> MacAlgorithmSpec.HMAC_SHA_256;
            case HMAC_SHA_384 -> MacAlgorithmSpec.HMAC_SHA_384;
            case HMAC_SHA_512 -> MacAlgorithmSpec.HMAC_SHA_512;
        };
    }
}
