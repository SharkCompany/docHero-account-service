package com.dochero.accountservice.service.dto.account;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
  public String id;
  public String fullName;
  public String email;
  public List<String> departmentIDs;
  public String roleName;
}
