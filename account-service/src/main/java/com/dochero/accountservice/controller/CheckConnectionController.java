package com.dochero.accountservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CheckConnectionController {
  @GetMapping("/")
  public String homepage() {
    return "Welcome To Account Service";
  }
}
