package com.dochero.accountservice.openfeign;

import com.dochero.accountservice.openfeign.dto.reponse.DepartmentBaseResponseDTO;
import com.dochero.accountservice.service.dto.department.DepartmentDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("department-service")
public interface DepartmentServiceFeignClient {
  @GetMapping("/department")
  DepartmentBaseResponseDTO<List<DepartmentDTO>> getAllDepartment();
}
