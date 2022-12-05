package com.dochero.accountservice._draft;

public enum ResponseErrorMessage {
  DUPLICATED_EMAIL("Email already exists"),
  EMPTY_PASSWORD("Password cannot be empty"),
  EMPTY_FULL_NAME("FullName cannot be empty"),
  INVALID_EMAIL("Invalid Email "),
  ACCOUNT_NOT_FOUND("Account does not exist")
  ;

  private final String value;
  ResponseErrorMessage(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
