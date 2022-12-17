package com.dochero.accountservice.exception;

import com.dochero.accountservice.constant.ValidationErrorMessage;

public class WrongPasswordException extends RuntimeException{
  public WrongPasswordException() {
    super(ValidationErrorMessage.WRONG_PASSWORD);
  }
}
