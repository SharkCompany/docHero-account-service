package com.dochero.accountservice.service.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
  public String id;
  public String fullname;
  public String email;
  public String department_id;
}
