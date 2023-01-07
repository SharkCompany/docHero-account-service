package com.dochero.accountservice.domain.compositeid;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDepartmentId implements Serializable {
  private String userId;
  private String departmentId;

}
