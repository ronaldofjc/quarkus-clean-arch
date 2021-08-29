package com.quarkus.clean.arch.exception;

import com.quarkus.clean.arch.exception.entity.ErrorCode;
import com.quarkus.clean.arch.exception.entity.ErrorData;
import lombok.ToString;

import java.util.Set;

@ToString
public class ValidationException extends RuntimeException {

    private final Set<ErrorData> errors;

    public ValidationException(final Set<ErrorData> errors) {
        this((String) null, (Throwable) null, errors);
    }

    public ValidationException(final String message, final Throwable cause, final Set<ErrorData> errors) {
        super(message, cause);
        this.errors = errors;
    }

    public Set<ErrorData> getErrors() {
        return this.errors;
    }
}