package com.dochero.accountservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CheckConnectionController {

  @Operation(summary = "Ping To Service")
  @GetMapping("/")
  public String homepage() {
    return "Welcome To Account Service";
  }
}
