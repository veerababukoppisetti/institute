package com.being.institutemanagementsystem.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @SuperBuilder
    @Data
    @NoArgsConstructor
    public static class Error {
        private String message;
    }

    @ExceptionHandler(value = {ServiceException.class})
    public ResponseEntity<Error> handleServiceException(final ServiceException ex) {
        return ResponseEntity.internalServerError()
                             .body(Error.builder()
                                        .message(ex.getMessage())
                                        .build());
    }
}
