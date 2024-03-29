package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.validation.custom_validation.NotEmptyString;
import com.dochero.accountservice.validation.custom_validation.RoleConstraint;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountDTO {

  private String fullName;
  private List<String> departmentIDs;

  @RoleConstraint
  private String roleName;
}
