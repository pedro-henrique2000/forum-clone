package com.project.forumapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class InvalidExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handle(HttpServletRequest req, MethodArgumentNotValidException ex) {
        InvalidError error = new InvalidError();
        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setPath(req.getRequestURI());
        error.setTimestamp(LocalDateTime.now());
        error.setStatusValue(status.value());
        error.setMessage(ex.getBindingResult().getAllErrors().stream().findFirst().get().getDefaultMessage());
        error.setError("Validation Error!");

        return ResponseEntity.status(status).body(error);
    }

}
