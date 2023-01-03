package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.constant.ValidationErrorMessage;
import com.dochero.accountservice.validation.custom_validation.NotEmptyString;
import com.dochero.accountservice.validation.custom_validation.RoleConstraint;
import com.dochero.accountservice.validation.custom_validation.UniqueEmail;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDTO {

  @NotEmptyString(message = ValidationErrorMessage.EMPTY_FULLNAME)
  private String fullName;

  private String title;

  private String description;

  @NotEmptyString(message = ValidationErrorMessage.EMPTY_PASSWORD)
  private String password;

  @Email(message = ValidationErrorMessage.INVALID_EMAIL)
  @NotEmptyString(message = ValidationErrorMessage.INVALID_EMAIL)
  @UniqueEmail(message = "The email ${validatedValue} already exists")
  private String email;

  @NotNull(message = "departmentIDs must not be empty")
  private List<String> departmentIDs;

  @RoleConstraint
  @NotEmptyString(message = ValidationErrorMessage.EMPTY_ROLE)
  private String roleName;
}
