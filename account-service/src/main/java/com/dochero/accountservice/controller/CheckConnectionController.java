package com.dochero.accountservice.controller;

import com.dochero.accountservice.exception.ServiceCallingException;
import com.dochero.accountservice.openfeign.AuthenticationServiceFeignClient;
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

  @Autowired
  AuthenticationServiceFeignClient authenticationServiceFeignClient;

  @Operation(summary = "Ping To Account Service")
  @GetMapping("/")
  public String homepage() {
    return "Welcome To Account Service";
  }

  @Operation(summary = "Ping To Department Service From Account Service")
  @GetMapping("/ping-to-department-service")
  public DepartmentBaseResponseDTO<List<DepartmentDTO>> department() {

    try {
      return departmentServiceFeignClient.getAllDepartment();
    } catch (Exception ex) {
      throw new ServiceCallingException(ex.toString());
    }
  }

  @Operation(summary = "Ping To Authentication Service From Account Service")
  @GetMapping("/ping-to-authentication-service")
  public String authentication() {
    try {
      return authenticationServiceFeignClient.ping();
    } catch (Exception ex) {
      throw new ServiceCallingException(ex.toString());
    }
  }


}
