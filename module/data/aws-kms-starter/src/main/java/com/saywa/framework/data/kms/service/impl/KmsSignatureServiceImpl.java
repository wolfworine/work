package com.saywa.framework.data.kms.service.impl;

import com.saywa.framework.data.kms.domain.KmsOperation;
import com.saywa.framework.data.kms.domain.KmsSignRequest;
import com.saywa.framework.data.kms.domain.KmsSignResponse;
import com.saywa.framework.data.kms.domain.KmsVerifyRequest;
import com.saywa.framework.data.kms.domain.KmsVerifyResponse;
import com.saywa.framework.data.kms.factory.KmsRequestFactory;
import com.saywa.framework.data.kms.mapper.KmsResponseMapper;
import com.saywa.framework.data.kms.service.KmsOperationSupport;
import com.saywa.framework.data.kms.service.KmsSignatureService;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.kms.KmsAsyncClient;

/**
 * Default reactive implementation of {@link KmsSignatureService}, backed
 * directly by {@link KmsAsyncClient}. Delegates to {@link KmsRequestFactory}
 * the construction of SDK requests, to {@link KmsResponseMapper} the
 * translation of SDK responses into {@code domain/} records, and to
 * {@link KmsOperationSupport} request-size validation plus the shared
 * audit-and-map-exceptions behavior.
 */
@Slf4j
@ApplicationScoped
public class KmsSignatureServiceImpl implements KmsSignatureService {

    private final KmsAsyncClient kmsAsyncClient;
    private final KmsRequestFactory requestFactory;
    private final KmsResponseMapper responseMapper;
    private final KmsOperationSupport support;

    @Inject
    public KmsSignatureServiceImpl(KmsAsyncClient kmsAsyncClient,
                                    KmsRequestFactory requestFactory,
                                    KmsResponseMapper responseMapper,
                                    KmsOperationSupport support) {
        this.kmsAsyncClient = kmsAsyncClient;
        this.requestFactory = requestFactory;
        this.responseMapper = responseMapper;
        this.support = support;
    }

    @Override
    public Uni<KmsSignResponse> sign(KmsSignRequest request) {
        long startedAt = System.nanoTime();

        Uni<KmsSignResponse> signed = validateMacMessageSize(request)
                .onItem().transformToUni(validRequest -> Uni.createFrom()
                        .completionStage(() -> kmsAsyncClient.generateMac(requestFactory.createGenerateMacRequest(validRequest)))
                        .onItem().transform(response -> responseMapper.toSignatureResult(response, validRequest.algorithm())));

        return support.audited(signed, KmsOperation.SIGN_MAC, request.keyRef(), request.message().length, startedAt);
    }

    @Override
    public Uni<KmsVerifyResponse> verify(KmsVerifyRequest request) {
        long startedAt = System.nanoTime();

        Uni<KmsVerifyResponse> verified = Uni.createFrom()
                .completionStage(() -> kmsAsyncClient.verifyMac(requestFactory.createVerifyMacRequest(request)))
                .onItem().transform(response -> responseMapper.toVerificationResult(response, request.algorithm()));

        return support.audited(verified, KmsOperation.VERIFY_MAC, request.keyRef(), request.message().length, startedAt);
    }

    private Uni<KmsSignRequest> validateMacMessageSize(KmsSignRequest request) {
        return support.validateSize(request, request.message().length,
                support.configuration().maxMacMessageSize(), "message", "SIGN");
    }
}
