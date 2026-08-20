package com.saywa.framework.data.kms.service.impl;

import com.saywa.framework.data.kms.crypto.AesGcmCipher;
import com.saywa.framework.data.kms.domain.DataKey;
import com.saywa.framework.data.kms.domain.DataKeySpec;
import com.saywa.framework.data.kms.domain.KmsWrapResponse;
import com.saywa.framework.data.kms.domain.KmsDecryptRequest;
import com.saywa.framework.data.kms.domain.KmsOperation;
import com.saywa.framework.data.kms.domain.KmsUnwrapRequest;
import com.saywa.framework.data.kms.domain.KmsUnwrapResponse;
import com.saywa.framework.data.kms.domain.KmsWrapRequest;
import com.saywa.framework.data.kms.factory.KmsRequestFactory;
import com.saywa.framework.data.kms.mapper.KmsResponseMapper;
import com.saywa.framework.data.kms.service.KmsKeyWrapService;
import com.saywa.framework.data.kms.service.KmsOperationSupport;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.kms.KmsAsyncClient;

/**
 * Default reactive implementation of {@link KmsKeyWrapService}, backed
 * directly by {@link KmsAsyncClient}. Delegates to {@link KmsRequestFactory}
 * the construction of SDK requests, to {@link KmsResponseMapper} the
 * translation of SDK responses into {@code domain/} records, and to
 * {@link KmsOperationSupport} the shared audit-and-map-exceptions behavior.
 * The local AES-GCM cipher used by envelope encryption is pure CPU work and
 * is chained with {@code .onItem().transform(...)} without an artificial
 * async boundary.
 */
@Slf4j
@ApplicationScoped
public class KmsKeyWrapServiceImpl implements KmsKeyWrapService {

    private final KmsAsyncClient kmsAsyncClient;
    private final KmsRequestFactory requestFactory;
    private final KmsResponseMapper responseMapper;
    private final AesGcmCipher localCipher;
    private final KmsOperationSupport support;

    @Inject
    public KmsKeyWrapServiceImpl(KmsAsyncClient kmsAsyncClient,
                                  KmsRequestFactory requestFactory,
                                  KmsResponseMapper responseMapper,
                                  AesGcmCipher localCipher,
                                  KmsOperationSupport support) {
        this.kmsAsyncClient = kmsAsyncClient;
        this.requestFactory = requestFactory;
        this.responseMapper = responseMapper;
        this.localCipher = localCipher;
        this.support = support;
    }

    @Override
    public Uni<KmsWrapResponse> wrap(KmsWrapRequest request) {
        long startedAt = System.nanoTime();

        Uni<KmsWrapResponse> wrapped = generateDataKey(request.keyRef(), request.dataKeySpec(), request.encryptionContext())
                .onItem().transform(dataKey -> cipherAndDiscard(dataKey, request.plaintext(), request.encryptionContext()));

        return support.audited(wrapped, KmsOperation.WRAP_ENVELOPE, request.keyRef(), request.plaintext().length, startedAt);
    }

    @Override
    public Uni<KmsUnwrapResponse> unwrap(KmsUnwrapRequest request) {
        long startedAt = System.nanoTime();
        KmsWrapResponse envelope = request.envelope();

        KmsDecryptRequest dataKeyDecryptRequest = new KmsDecryptRequest(
                envelope.keyId(), envelope.encryptedDataKey(), request.encryptionContext());

        Uni<KmsUnwrapResponse> unwrapped = decryptDataKey(dataKeyDecryptRequest)
                .onItem().transform(plaintextKey -> {
                    try {
                        byte[] plaintext = localCipher.decrypt(plaintextKey, envelope.ciphertext(),
                                additionalAuthenticatedData(request.encryptionContext()));
                        return new KmsUnwrapResponse(envelope.keyId(), plaintext);
                    } finally {
                        wipe(plaintextKey);
                    }
                });

        return support.audited(unwrapped, KmsOperation.UNWRAP_ENVELOPE, envelope.keyId(), envelope.ciphertext().length, startedAt);
    }

    /** Decrypts the envelope's wrapped data key against KMS, returning its plaintext form. */
    private Uni<byte[]> decryptDataKey(KmsDecryptRequest request) {
        return Uni.createFrom()
                .completionStage(() -> kmsAsyncClient.decrypt(requestFactory.createDecryptRequest(request)))
                .onItem().transform(response -> responseMapper.toDecryptResponse(response).plaintext());
    }

    private Uni<DataKey> generateDataKey(String keyRef, DataKeySpec spec, Map<String, String> encryptionContext) {
        return Uni.createFrom()
                .completionStage(() -> kmsAsyncClient.generateDataKey(
                        requestFactory.createGenerateDataKeyRequest(keyRef, spec, encryptionContext)))
                .onItem().transform(response -> responseMapper.toDataKey(response, spec));
    }

    /** Ciphers {@code plaintext} locally with the data key's plaintext form, then wipes it from memory. */
    private KmsWrapResponse cipherAndDiscard(DataKey dataKey, byte[] plaintext, Map<String, String> encryptionContext) {
        byte[] plaintextKey = dataKey.plaintextKey();
        try {
            byte[] ciphertext = localCipher.encrypt(plaintextKey, plaintext,
                    additionalAuthenticatedData(encryptionContext));
            return new KmsWrapResponse(dataKey.keyId(), dataKey.spec(), dataKey.encryptedKey(), ciphertext);
        } finally {
            wipe(plaintextKey);
        }
    }

    /**
     * The encryption context also doubles as the local AES-GCM's AAD: if
     * anyone changes the context, the tag stops validating and decryption
     * fails. Deterministic serialization (sorted keys) so encryption and
     * decryption produce the exact same bytes.
     */
    private byte[] additionalAuthenticatedData(Map<String, String> context) {
        if (context == null || context.isEmpty()) {
            return new byte[0];
        }
        String canonical = context.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(";"));
        return canonical.getBytes(StandardCharsets.UTF_8);
    }

    /** Minimizes the window in which the plaintext data key lives on the heap. */
    private void wipe(byte[] key) {
        if (key != null) {
            Arrays.fill(key, (byte) 0);
        }
    }
}
