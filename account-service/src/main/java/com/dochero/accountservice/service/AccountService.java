package com.dochero.accountservice.service;

import com.dochero.accountservice.domain.Account;
import com.dochero.accountservice.exception.AccountNotFoundException;
import com.dochero.accountservice.repository.AccountRepository;
import com.dochero.accountservice.service.dto.account.AccountResponseDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountResponseDTO;
import com.dochero.accountservice.service.dto.account.UpdateAccountDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountService {

  final AccountRepository accountRepository;

  public CreateAccountResponseDTO register(CreateAccountDTO account) {
    Account accountToCreate = Account.builder()
        .email(account.getEmail())
        .password(account.getPassword())
        .fullname(account.getFullName())
        .departmentId(account.getDepartmentId())
        .roleName(account.getRoleName())
        .build();
    accountToCreate.setEmail(account.getEmail());
    Account newAccount = accountRepository.save(accountToCreate);
    return CreateAccountResponseDTO.builder().id(newAccount.getId())
        .roleName(newAccount.getRoleName())
        .fullName(newAccount.getFullname())
        .email(newAccount.getEmail()).departmentId(newAccount.getDepartmentId()).build();
  }


  public List<AccountResponseDTO> getAccounts() {
    List<Account> accounts = accountRepository.findAll();
    List<AccountResponseDTO> accountResponseDTOS = new ArrayList<>();
    for (Account account : accounts
    ) {
      accountResponseDTOS.add(
          AccountResponseDTO
              .builder()
              .id(account.getId())
              .fullName(account.getFullname())
              .departmentId(account.getDepartmentId())
              .email(account.getEmail())
              .roleName(account.getRoleName())
              .build());
    }
    return accountResponseDTOS;
  }

  public Account findAccountById(String id) {
    Optional<Account> account = accountRepository.findById(id);
    account.orElseThrow(() -> new AccountNotFoundException());
    return account.get();
  }

  public AccountResponseDTO updateAccount(String id, UpdateAccountDTO accountDTO) {
    Account account = findAccountById(id);

    if (!Objects.isNull(accountDTO.getDepartmentId())) {
      account.setDepartmentId(accountDTO.getDepartmentId());
    }

    if (!Objects.isNull(accountDTO.getFullName())) {
      account.setFullname(accountDTO.getFullName());
    }

    if (!Objects.isNull(accountDTO.getRoleName())) {
      account.setRoleName(accountDTO.getRoleName());
    }

    account = accountRepository.save(account);

    return AccountResponseDTO.builder().id(account.getId())
        .departmentId(account.getDepartmentId())
        .fullName(account.getFullname())
        .email(account.getEmail())
        .roleName(account.getRoleName())
        .build();
  }

  public void deleteAccount(String id) {
    findAccountById(id);
    accountRepository.deleteById(id);
  }
}
