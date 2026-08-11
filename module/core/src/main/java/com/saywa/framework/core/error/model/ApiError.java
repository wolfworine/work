package com.saywa.framework.core.error.model;

import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@Builder
public final class ApiError {

    private final Integer statusCode;
    private final String timestamp;
    private final String userMessage;
    private final List<ErrorDetail> errors;
}
