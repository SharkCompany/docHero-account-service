package com.dochero.accountservice.controller;

import com.dochero.accountservice.exception.ValidationException;
import com.dochero.accountservice.service.AccountService;
import com.dochero.accountservice.service.dto.account.AccountResponseDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountResponseDTO;
import com.dochero.accountservice.service.dto.account.UpdateAccountDTO;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

  final AccountService accountService;

  @GetMapping("/ping")
  public String helloWorld() {
    return "hello world from local";
  }

  @PostMapping("/register")
  public ResponseEntity<CreateAccountResponseDTO> register(
      @RequestBody @Valid CreateAccountDTO account, BindingResult validationErrors) {
    if (validationErrors.hasErrors()) {
      throw new ValidationException(validationErrors);
    } else {
      return new ResponseEntity<>(accountService.register(account), HttpStatus.OK);
    }
  }

  @GetMapping("/accounts")
  public ResponseEntity<List<AccountResponseDTO>> getAccounts() {
    return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
  }

  @PutMapping("/account/{id}")
  public ResponseEntity<AccountResponseDTO> updateAccount(
      @RequestBody @Valid UpdateAccountDTO updateAccountDTO, @PathVariable String id) {
    return new ResponseEntity<>(accountService.updateAccount(id, updateAccountDTO), HttpStatus.OK);
  }

  @DeleteMapping("/account/{id}")
  public ResponseEntity<String> deleteAccount(
      @PathVariable String id) {
    accountService.deleteAccount(id);
    return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
  }
}
