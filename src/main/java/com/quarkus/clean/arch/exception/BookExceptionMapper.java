package com.quarkus.clean.arch.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.quarkus.clean.arch.exception.entity.ErrorCode;
import com.quarkus.clean.arch.exception.entity.ErrorData;

@Provider
public class BookExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(final RuntimeException exception) {
        int httpStatus = 500;
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        String message = "An unexpected error has occurred on the server";

        if (exception instanceof ValidationException) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(((ValidationException) exception).getErrors()).type(MediaType.APPLICATION_JSON)
                    .build();
        }

        switch (exception.getClass().getSimpleName()) {
            case "BusinessException":
                httpStatus = 422;
                errorCode = ErrorCode.BUSINESS_ERROR;
                message = exception.getMessage();
                break;
            case "GatewayException":
                httpStatus = 503;
                errorCode = ErrorCode.GATEWAY_ERROR;
                message = exception.getMessage();
                break;
            case "EntityNotFoundException":
                httpStatus = 404;
                errorCode = ErrorCode.NOT_FOUND;
                message = exception.getMessage();
                break;
        }

        return Response.status(httpStatus)
            .entity(ErrorData.builder().message(message).code(errorCode).build())
            .type(MediaType.APPLICATION_JSON).build();
    }
}
