package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.constant.ValidatationMessageConstants;
import com.dochero.accountservice.validation.custom_validation.NotEmptyString;
import com.dochero.accountservice.validation.custom_validation.UniqueEmail;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {

  @NotEmptyString(message = ValidatationMessageConstants.EMPTY_FULLNAME_ERROR)
  private String fullname;

  @NotEmptyString(message = ValidatationMessageConstants.EMPTY_PASSWORD_ERROR)
  private String password;

  @Email(message = ValidatationMessageConstants.INVALID_EMAIL_ERROR)
  @NotEmptyString(message = ValidatationMessageConstants.INVALID_EMAIL_ERROR)
  @UniqueEmail(message = ValidatationMessageConstants.DUPLICATE_EMAIL_ERROR)
  private String email;


  private String department_id;
}
