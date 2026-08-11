package com.saywa.framework.core.error.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Builder
public final class ErrorDetail implements Serializable {

    private final String errorCode;
    private final String field;
    private final String message;
    private final String url;
    private final ErrorCategory category;

}
