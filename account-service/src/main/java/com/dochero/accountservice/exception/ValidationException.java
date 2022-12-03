package com.dochero.accountservice.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationException extends RuntimeException {

  private final BindingResult errors;

  public ValidationException(BindingResult errors) {
    this.errors = errors;
  }

  public List<String> getListDefaultMessages() {
    return getDefaultMessages(this.errors);
  }

  private static String getDefaultMessage(ObjectError error) {
    return error.getDefaultMessage();
  }


  @Override
  public String getMessage() {
    return this.getDefaultMessages(this.errors).toString();
  }

  private List<String> getDefaultMessages(BindingResult bindingResult) {
    return bindingResult.getAllErrors()
        .stream()
        .map(ValidationException::getDefaultMessage)
        .collect(Collectors.toList());
  }

}