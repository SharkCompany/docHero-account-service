package com.dochero.accountservice.controller;

import com.dochero.accountservice.exception.ValidationException;
import com.dochero.accountservice.service.AccountService;
import com.dochero.accountservice.service.dto.account.AccountResponseDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountResponseDTO;
import com.dochero.accountservice.service.dto.account.UpdateAccountDTO;
import com.dochero.accountservice.service.dto.account.ValidateAccountDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.ws.rs.PathParam;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

  final AccountService accountService;

  @GetMapping("/ping")
  public String helloWorld() {
    return "hello world from account service";
  }

  @Operation(summary = "Create Account")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = AccountResponseDTO.class))
          }),
  })
  @PostMapping("/account/create")
  public ResponseEntity<CreateAccountResponseDTO> register(
      @RequestBody @Valid CreateAccountDTO account, BindingResult validationErrors) {
    if (validationErrors.hasErrors()) {
      throw new ValidationException(validationErrors);
    } else {
      return new ResponseEntity<>(accountService.register(account), HttpStatus.OK);
    }
  }

  @Operation(summary = "Get List of Accounts")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = List.class))
      }),
  })
  @GetMapping("/accounts")
  public ResponseEntity<List<AccountResponseDTO>> getAccounts() {
    return new ResponseEntity<>(accountService.getAccounts(), HttpStatus.OK);
  }

  @Operation(summary = "Get Account By Password And Email")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = AccountResponseDTO.class))}),
      @ApiResponse(responseCode = "404", description = "Account Not Found",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Object.class))
          }),
  })
  @PostMapping("/validate")
  public ResponseEntity<AccountResponseDTO> findAccountByEmailAndPassword(
      @Valid @RequestBody ValidateAccountDTO accountAuthPayload,
      BindingResult errors
  ) {
    if (errors.hasErrors()) throw new ValidationException(errors);
    return new ResponseEntity<>(accountService.getAccountByEmailAndPassword(accountAuthPayload), HttpStatus.OK);
  }

  @PutMapping("/account/{id}")
  public ResponseEntity<AccountResponseDTO> updateAccount(
      @RequestBody @Valid UpdateAccountDTO updateAccountDTO,
      @PathVariable String id,
      BindingResult errors) {
    if (errors.hasErrors()) {
      throw new ValidationException(errors);
    }
    return new ResponseEntity<>(accountService.updateAccount(id, updateAccountDTO), HttpStatus.OK);
  }



  @DeleteMapping("/account/{id}")
  public ResponseEntity<String> deleteAccount(
      @PathVariable String id) {
    accountService.deleteAccount(id);
    return new ResponseEntity<>("Delete Successfully", HttpStatus.OK);
  }
}
