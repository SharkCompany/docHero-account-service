package com.dochero.accountservice.exception;

import com.dochero.accountservice.constant.ValidatationMessageConstants;

public class AccountNotFoundException extends RuntimeException{
  public AccountNotFoundException() {
    super(ValidatationMessageConstants.ACCOUNT_NOT_FOUND_ERROR);
  }
}
