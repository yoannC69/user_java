package com.sskorupski.learn.springboot.config.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sskorupski.learn.springboot.errors.APIError;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
@AllArgsConstructor
public class RestResponseEntitySQLExceptionHandler
        extends ResponseEntityExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = {SQLIntegrityConstraintViolationException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;
        APIError apiError = new APIError()
                .setResource(String.format("%s %s", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getRequestURI()))
                // FIXME Not a good practice as far as it expose database structure to the client
                .setDetail(ex.getCause().getMessage())
                .setTitle("Integrity violation")
                .setStatus(HttpStatus.BAD_REQUEST.value());

        try {
            String responseBody = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(apiError);
            return handleExceptionInternal(
                    ex,
                    responseBody,
                    new HttpHeaders(),
                    HttpStatus.BAD_REQUEST,
                    request
            );
        } catch (JsonProcessingException e) {
            throw ex;
        }


    }
}