package com.dochero.accountservice.service.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateAccountResponseDTO {
    private String id;
    private String fullName;
    private String email;
    private String departmentId;
    private String roleName;
}
