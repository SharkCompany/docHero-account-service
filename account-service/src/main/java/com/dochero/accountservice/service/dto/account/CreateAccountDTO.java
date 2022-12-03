package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.constant.ValidatationMessageConstants;
import com.dochero.accountservice.validation.custom_validation.NotEmptyString;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {

//  @NotNull(message = ValidatationMessageConstants.EMPTY_FULLNAME_ERROR)
  @NotEmptyString()
  private String fullname;

//  @NotNull(message = ValidatationMessageConstants.EMPTY_PASSWORD_ERROR)
  @NotEmptyString()
  private String password;

  @Email(message = ValidatationMessageConstants.INVALID_EMAIL_ERROR)
  private String email;

  private String department_id;
}
