package com.dochero.accountservice.exception;

import com.dochero.accountservice.constant.ValidationErrorMessage;

public class AccountNotFoundException extends RuntimeException{
  public AccountNotFoundException() {
    super(ValidationErrorMessage.ACCOUNT_NOT_FOUND);
  }
}
