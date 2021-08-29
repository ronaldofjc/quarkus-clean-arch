package com.quarkus.clean.arch.usecase.validations;

import com.quarkus.clean.arch.endpoint.entity.UpdateBookRequest;
import com.quarkus.clean.arch.exception.ValidationException;
import com.quarkus.clean.arch.exception.entity.ErrorCode;
import com.quarkus.clean.arch.exception.entity.ErrorData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class ValidateObjectFields {

    private final Validator validator;

    public void execute(final Object object) {
        final Set<ErrorData> errors = new HashSet<>();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            violations.forEach(o -> {
                log.info("[Validation Exception]");
                errors.add(ErrorData.builder()
                        .code(ErrorCode.INVALID_PARAMETER)
                        .message(o.getMessage())
                        .field(o.getPropertyPath().toString())
                        .build());
            });

            if (!errors.isEmpty()) {
                throw new ValidationException(errors);
            }
        }
    }
}
