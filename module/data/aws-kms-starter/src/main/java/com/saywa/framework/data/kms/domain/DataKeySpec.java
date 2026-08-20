package com.saywa.framework.data.kms.domain;

/**
 * Length of the AES data key that KMS generates for envelope encryption
 * ({@code GenerateDataKey}). The plaintext key is used once, locally, to
 * cipher the payload with AES-GCM and is discarded immediately after.
 */
public enum DataKeySpec {

    AES_128(16),
    AES_256(32);

    private final int lengthInBytes;

    DataKeySpec(int lengthInBytes) {
        this.lengthInBytes = lengthInBytes;
    }

    /**
     * @return the length, in bytes, of the plaintext key produced by this spec.
     */
    public int lengthInBytes() {
        return lengthInBytes;
    }
}
