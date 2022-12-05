package com.dochero.accountservice.service.dto.account;

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
