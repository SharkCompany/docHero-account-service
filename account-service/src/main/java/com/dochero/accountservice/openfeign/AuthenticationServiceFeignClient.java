package com.dochero.accountservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("AUTHENTICATION-SERVICE")
public interface AuthenticationServiceFeignClient {
  @GetMapping("/auth")
  String ping();
}
