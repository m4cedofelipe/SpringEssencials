package br.com.devdojo.demo.handler;

import br.com.devdojo.demo.error.ResourceNotFoundDetails;
import br.com.devdojo.demo.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFounddException(ResourceNotFoundException e) {
        ResourceNotFoundDetails details = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(e.getMessage())
                .developerMessage(e.getClass().getName())
                .build();

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

}
