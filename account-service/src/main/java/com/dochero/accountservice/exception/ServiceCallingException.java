package com.dochero.accountservice.exception;

import com.dochero.accountservice.constant.ValidationErrorMessage;

public class ServiceCallingException extends RuntimeException{
  public ServiceCallingException() {
  }

  public ServiceCallingException(String msg) {
    super(msg);
  }
}
