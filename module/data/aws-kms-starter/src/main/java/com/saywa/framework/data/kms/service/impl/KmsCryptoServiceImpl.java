package com.saywa.framework.data.kms.service.impl;

import com.saywa.framework.data.kms.domain.CipherText;
import com.saywa.framework.data.kms.domain.KmsDecryptRequest;
import com.saywa.framework.data.kms.domain.KmsDecryptResponse;
import com.saywa.framework.data.kms.domain.KmsEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsOperation;
import com.saywa.framework.data.kms.domain.KmsReEncryptRequest;
import com.saywa.framework.data.kms.domain.KmsReEncryptResponse;
import com.saywa.framework.data.kms.factory.KmsRequestFactory;
import com.saywa.framework.data.kms.mapper.KmsResponseMapper;
import com.saywa.framework.data.kms.service.KmsCryptoService;
import com.saywa.framework.data.kms.service.KmsOperationSupport;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.kms.KmsAsyncClient;

/**
 * Default reactive implementation of {@link KmsCryptoService}, backed
 * directly by {@link KmsAsyncClient} — the same pattern
 * {@code S3StorageServiceImpl} uses with {@code S3AsyncClient}: the SDK
 * client is injected straight into this class, with no separate
 * adapter/port module in between.
 * <p>
 * Delegates to {@link KmsRequestFactory} the construction of SDK requests,
 * to {@link KmsResponseMapper} the translation of SDK responses into
 * {@code domain/} records, and to {@link KmsOperationSupport} request-size
 * validation plus the shared audit-and-map-exceptions behavior. No method
 * of this class may block the calling thread: every interaction with the
 * SDK is adapted to {@link Uni} via {@code Uni.createFrom().completionStage(...)}.
 */
@Slf4j
@ApplicationScoped
public class KmsCryptoServiceImpl implements KmsCryptoService {

    private final KmsAsyncClient kmsAsyncClient;
    private final KmsRequestFactory requestFactory;
    private final KmsResponseMapper responseMapper;
    private final KmsOperationSupport support;

    @Inject
    public KmsCryptoServiceImpl(KmsAsyncClient kmsAsyncClient,
                                 KmsRequestFactory requestFactory,
                                 KmsResponseMapper responseMapper,
                                 KmsOperationSupport support) {
        this.kmsAsyncClient = kmsAsyncClient;
        this.requestFactory = requestFactory;
        this.responseMapper = responseMapper;
        this.support = support;
    }

    @Override
    public Uni<CipherText> encrypt(KmsEncryptRequest request) {
        long startedAt = System.nanoTime();

        Uni<CipherText> encrypted = validateDirectPlaintextSize(request)
                .onItem().transformToUni(validRequest -> Uni.createFrom()
                        .completionStage(() -> kmsAsyncClient.encrypt(requestFactory.createEncryptRequest(validRequest)))
                        .onItem().transform(response -> responseMapper.toCipherText(response, validRequest.encryptionContext())));

        return support.audited(encrypted, KmsOperation.ENCRYPT_SYMMETRIC, request.keyRef(), request.plaintext().length, startedAt);
    }

    @Override
    public Uni<KmsDecryptResponse> decrypt(KmsDecryptRequest request) {
        long startedAt = System.nanoTime();

        Uni<KmsDecryptResponse> decrypted = Uni.createFrom()
                .completionStage(() -> kmsAsyncClient.decrypt(requestFactory.createDecryptRequest(request)))
                .onItem().transform(responseMapper::toDecryptResponse);

        return support.audited(decrypted, KmsOperation.DECRYPT_SYMMETRIC, request.keyRef(), request.cipherText().length, startedAt);
    }

    @Override
    public Uni<KmsReEncryptResponse> reEncrypt(KmsReEncryptRequest request) {
        long startedAt = System.nanoTime();

        Uni<KmsReEncryptResponse> reEncrypted = Uni.createFrom()
                .completionStage(() -> kmsAsyncClient.reEncrypt(requestFactory.createReEncryptRequest(request)))
                .onItem().transform(responseMapper::toReEncryptResponse);

        return support.audited(reEncrypted, KmsOperation.REENCRYPT, request.destinationKeyRef(),
                request.cipherText().length, startedAt);
    }

    private Uni<KmsEncryptRequest> validateDirectPlaintextSize(KmsEncryptRequest request) {
        return support.validateSize(request, request.plaintext().length,
                support.configuration().maxDirectPlaintextSize(), "plaintext", "ENCRYPT");
    }
}
