package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.constant.ValidationErrorMessage;
import com.dochero.accountservice.validation.custom_validation.NotEmptyString;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateAccountDTO {

  @Email(message = ValidationErrorMessage.INVALID_EMAIL)
  @NotEmptyString(message = ValidationErrorMessage.INVALID_EMAIL)
  private String email;

  @NotEmptyString(message = ValidationErrorMessage.EMPTY_PASSWORD)
  private String password;


}
