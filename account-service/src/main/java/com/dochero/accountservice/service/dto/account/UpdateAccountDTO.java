package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.constant.ValidatationMessageConstants;
import com.dochero.accountservice.validation.custom_validation.NotEmptyString;
import com.dochero.accountservice.validation.custom_validation.UniqueEmail;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountDTO {
  private String fullname;
  private String department_id;
}
