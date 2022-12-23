package com.dochero.accountservice.exception;

import com.dochero.accountservice.constant.ValidationErrorMessage;

public class DepartmentNotFoundException extends RuntimeException{
  public DepartmentNotFoundException() {
    super(ValidationErrorMessage.ACCOUNT_NOT_FOUND);
  }

  public DepartmentNotFoundException(String msg) {
    super(msg);
  }
}
