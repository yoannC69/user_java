package com.sskorupski.learn.springboot.config.rest;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
class EmptyListBodyControllerAdvice implements ResponseBodyAdvice<List<?>> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return List.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public List<?> beforeBodyWrite(List<?> body, MethodParameter returnType, MediaType selectedContentType,
                                   Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                   ServerHttpRequest request, ServerHttpResponse response) {
        if (Optional.ofNullable(body)
                .orElse(Collections.emptyList())
                .isEmpty()) {
            response.setStatusCode(HttpStatus.NO_CONTENT);
        }
        return body;
    }
}