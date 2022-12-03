package com.dochero.accountservice.exception;

public class DuplicateEmailException extends RuntimeException {
  public DuplicateEmailException(String email) {
    super("Email "+ email+ " is existed");
  }
}
