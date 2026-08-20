package com.saywa.framework.data.kms.mapper;

import com.saywa.framework.data.kms.domain.CipherText;
import com.saywa.framework.data.kms.domain.DataKey;
import com.saywa.framework.data.kms.domain.DataKeySpec;
import com.saywa.framework.data.kms.domain.KmsDecryptResponse;
import com.saywa.framework.data.kms.domain.KmsReEncryptResponse;
import com.saywa.framework.data.kms.domain.KmsSignResponse;
import com.saywa.framework.data.kms.domain.KmsVerifyResponse;
import com.saywa.framework.data.kms.domain.MacAlgorithm;
import com.saywa.framework.data.kms.domain.SymmetricEncryptionAlgorithm;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import software.amazon.awssdk.services.kms.model.DecryptResponse;
import software.amazon.awssdk.services.kms.model.EncryptResponse;
import software.amazon.awssdk.services.kms.model.GenerateDataKeyResponse;
import software.amazon.awssdk.services.kms.model.GenerateMacResponse;
import software.amazon.awssdk.services.kms.model.ReEncryptResponse;
import software.amazon.awssdk.services.kms.model.VerifyMacResponse;

/**
 * Maps AWS SDK KMS response objects to this starter's domain records. The
 * only point in {@code mapper/} that imports {@code software.amazon.awssdk}
 * types.
 */
@ApplicationScoped
public class KmsResponseMapper {

    public CipherText toCipherText(EncryptResponse response, Map<String, String> encryptionContext) {
        return new CipherText(response.keyId(), SymmetricEncryptionAlgorithm.SYMMETRIC_DEFAULT,
                response.ciphertextBlob().asByteArray(), encryptionContext);
    }

    public KmsDecryptResponse toDecryptResponse(DecryptResponse response) {
        return new KmsDecryptResponse(response.keyId(), response.plaintext().asByteArray());
    }

    public KmsReEncryptResponse toReEncryptResponse(ReEncryptResponse response) {
        return new KmsReEncryptResponse(response.sourceKeyId(), response.keyId(), response.ciphertextBlob().asByteArray());
    }

    public DataKey toDataKey(GenerateDataKeyResponse response, DataKeySpec spec) {
        return new DataKey(response.keyId(), spec,
                response.plaintext().asByteArray(), response.ciphertextBlob().asByteArray());
    }

    public KmsSignResponse toSignatureResult(GenerateMacResponse response, MacAlgorithm algorithm) {
        return new KmsSignResponse(response.keyId(), algorithm, response.mac().asByteArray());
    }

    public KmsVerifyResponse toVerificationResult(VerifyMacResponse response, MacAlgorithm algorithm) {
        return new KmsVerifyResponse(response.keyId(), algorithm, response.macValid());
    }
}
