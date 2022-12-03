package com.dochero.accountservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ExceptionHandler({
      DuplicateEmailException.class})
  public ResponseEntity<Object> InvalidRequest(RuntimeException exception, WebRequest request) {
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("timestamp", LocalDateTime.now());
    responseBody.put("message", exception.getMessage());
    responseBody.put("statusCode", HttpStatus.BAD_REQUEST);
    responseBody.put("cause", exception.getCause());
    return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      ValidationException.class})
  public ResponseEntity<Object> InvalidRequest(ValidationException exception, WebRequest request) {
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("timestamp", LocalDateTime.now());
    responseBody.put("messages", exception.getListDefaultMessages());
    responseBody.put("statusCode", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
  }




}
