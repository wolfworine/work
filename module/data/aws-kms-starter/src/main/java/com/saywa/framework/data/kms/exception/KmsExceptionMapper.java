package com.saywa.framework.data.kms.exception;

import com.saywa.framework.core.error.exceptions.CryptoAccessDeniedException;
import com.saywa.framework.core.error.exceptions.CryptoConfigurationException;
import com.saywa.framework.core.error.exceptions.CryptoConnectionException;
import com.saywa.framework.core.error.exceptions.CryptoException;
import com.saywa.framework.core.error.exceptions.CryptoGenericException;
import com.saywa.framework.core.error.exceptions.CryptoInvalidCiphertextException;
import com.saywa.framework.core.error.exceptions.CryptoKeyNotFoundException;
import com.saywa.framework.data.kms.crypto.AesGcmTagMismatchException;
import com.saywa.framework.data.kms.util.KmsConstants;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.kms.model.DisabledException;
import software.amazon.awssdk.services.kms.model.InvalidCiphertextException;
import software.amazon.awssdk.services.kms.model.InvalidKeyUsageException;
import software.amazon.awssdk.services.kms.model.KeyUnavailableException;
import software.amazon.awssdk.services.kms.model.KmsInvalidStateException;
import software.amazon.awssdk.services.kms.model.NotFoundException;

/**
 * Translates AWS SDK exceptions (and the local AES-GCM cipher's own
 * {@link AesGcmTagMismatchException}, plus any other {@link Throwable})
 * into the framework's shared crypto exception hierarchy
 * ({@link CryptoException} and its subclasses), so that neither
 * {@code service/} nor {@code domain/} need to know about SDK types.
 *
 * <p>This component is the only point in the {@code exception/} layer that
 * imports {@code software.amazon.awssdk} types; it is typically used in
 * {@code onFailure().transform(exceptionMapper::map)} inside
 * {@code KmsCryptoServiceImpl}.</p>
 */
@ApplicationScoped
public class KmsExceptionMapper {

    public RuntimeException map(Throwable throwable) {
        return switch (throwable) {
            case null -> new CryptoGenericException(KmsConstants.KMS_REQUEST_FAILED_MESSAGE, null);
            case CryptoException exception -> exception;
            case NotFoundException exception -> new CryptoKeyNotFoundException(exception.getMessage(), exception);
            case InvalidCiphertextException exception ->
                    new CryptoInvalidCiphertextException(exception.getMessage(), exception);
            case AesGcmTagMismatchException exception ->
                    new CryptoInvalidCiphertextException(exception.getMessage(), exception);
            case DisabledException exception -> new CryptoAccessDeniedException(exception.getMessage(), exception);
            case KmsInvalidStateException exception ->
                    new CryptoAccessDeniedException(exception.getMessage(), exception);
            case KeyUnavailableException exception ->
                    new CryptoAccessDeniedException(exception.getMessage(), exception);
            case InvalidKeyUsageException exception ->
                    new CryptoConfigurationException(exception.getMessage(), exception);
            case IllegalArgumentException exception ->
                    new CryptoConfigurationException(exception.getMessage(), exception);
            case SdkClientException exception ->
                    new CryptoConnectionException("Unable to communicate with AWS KMS", exception);
            case SdkException exception -> new CryptoGenericException(KmsConstants.KMS_REQUEST_FAILED_MESSAGE, exception);
            default -> new CryptoGenericException(KmsConstants.KMS_REQUEST_FAILED_MESSAGE, throwable);
        };
    }
}
