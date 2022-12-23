package com.dochero.accountservice.service.dto.department;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
  private String id;
  private String departmentName;
  private String description;
  private boolean delete;
}
