package com.mynt.shippingcalculator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ShippingCostExceptionHandler {

    public static class ExceptionResponse {
        private final String message;
        private final Integer code;

        public ExceptionResponse(final String message, final Integer code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public Integer getCode() {
            return code;
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleException(final ResponseStatusException e) {
        return ResponseEntity.status(e.getStatus()).body(new ExceptionResponse(e.getReason(), e.getStatus().value()));
    }
}
