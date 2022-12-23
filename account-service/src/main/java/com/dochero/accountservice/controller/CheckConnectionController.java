package com.dochero.accountservice.controller;

import com.dochero.accountservice.openfeign.DepartmentServiceFeignClient;
import com.dochero.accountservice.openfeign.dto.reponse.DepartmentBaseResponseDTO;
import com.dochero.accountservice.service.dto.department.DepartmentDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CheckConnectionController {

  @Autowired
  DepartmentServiceFeignClient departmentServiceFeignClient;

  @Operation(summary = "Ping To Account Service")
  @GetMapping("/")
  public String homepage() {
    return "Welcome To Account Service";
  }

  @Operation(summary = "Ping To Department Service From Account Service")
  @GetMapping("/ping-to-department-service")
  public DepartmentBaseResponseDTO<List<DepartmentDTO>> department() {
    return departmentServiceFeignClient.getAllDepartment();
  }
}
