package com.saywa.framework.data.s3.exception;

import com.saywa.framework.core.error.exceptions.StorageAccessDeniedException;
import com.saywa.framework.core.error.exceptions.StorageConnectionException;
import com.saywa.framework.core.error.exceptions.StorageException;
import com.saywa.framework.core.error.exceptions.StorageGenericException;
import com.saywa.framework.core.error.exceptions.StorageObjectNotFoundException;
import com.saywa.framework.data.s3.util.S3Constants;
import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.exception.SdkException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.S3Exception;

/**
 * Translates AWS SDK exceptions (and any other {@link Throwable}) into the
 * framework's shared storage exception hierarchy ({@link StorageException}
 * and its subclasses), so that neither {@code service/} nor {@code domain/}
 * need to know about SDK types.
 *
 * <p>This component is the only point in the {@code exception/} layer that
 * imports {@code software.amazon.awssdk} types; it is typically used in
 * {@code onFailure().transform(exceptionMapper::map)} inside
 * {@code S3StorageServiceImpl}.</p>
 */
@ApplicationScoped
public class StorageExceptionMapper {

    public RuntimeException map(Throwable throwable) {
        if (throwable instanceof S3Exception s3Exception && isAccessDenied(s3Exception)) {
            return new StorageAccessDeniedException(s3Exception.getMessage(), s3Exception);
        }

        return switch (throwable) {
            case null -> new StorageGenericException(S3Constants.S3_REQUEST_FAILED_MESSAGE, null);
            case NoSuchKeyException exception -> new StorageObjectNotFoundException(exception.getMessage(), exception);
            case SdkClientException exception -> new StorageConnectionException("Unable to communicate with the storage service", exception);
            case SdkException exception -> new StorageGenericException(S3Constants.S3_REQUEST_FAILED_MESSAGE, exception);
            case StorageException exception -> exception;
            default -> new StorageGenericException(S3Constants.S3_REQUEST_FAILED_MESSAGE, throwable);
        };
    }

    private boolean isAccessDenied(S3Exception exception) {
        return exception.awsErrorDetails() != null
                && "AccessDenied".equals(exception.awsErrorDetails().errorCode());
    }
}
