package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.constant.ValidationErrorMessage;
import com.dochero.accountservice.validation.custom_validation.NotEmptyString;
import com.dochero.accountservice.validation.custom_validation.UniqueEmail;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {

  @NotEmptyString(message = ValidationErrorMessage.EMPTY_FULLNAME)
  private String fullname;

  @NotEmptyString(message = ValidationErrorMessage.EMPTY_PASSWORD)
  private String password;

  @Email(message = ValidationErrorMessage.INVALID_EMAIL)
  @NotEmptyString(message = ValidationErrorMessage.INVALID_EMAIL)
  @UniqueEmail(message = "The email ${validatedValue} already exists")
  private String email;

  private String department_id;
}
