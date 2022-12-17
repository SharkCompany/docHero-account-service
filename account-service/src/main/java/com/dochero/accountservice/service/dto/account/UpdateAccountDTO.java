package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.validation.custom_validation.RoleConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountDTO {
  private String fullName;
  private String departmentId;

  @RoleConstraint
  private String roleName;
}
