package com.fiap.infrastructure.api.exception;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        if (exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error("NOT_FOUND", exception.getMessage()))
                    .build();
        }

        if (exception instanceof IllegalArgumentException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error("BAD_REQUEST", exception.getMessage()))
                    .build();
        }

        if (exception instanceof BadRequestException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error("BAD_REQUEST", exception.getMessage()))
                    .build();
        }

        if (exception instanceof IllegalStateException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error("BUSINESS_RULE_VIOLATION", exception.getMessage()))
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error("INTERNAL_SERVER_ERROR", exception.getMessage()))
                .build();
    }

    private ApiErrorResponse error(String code, String message) {
        return new ApiErrorResponse(
                code,
                message,
                LocalDateTime.now()
        );
    }

    public record ApiErrorResponse(
            String code,
            String message,
            LocalDateTime timestamp
    ) {}
}
