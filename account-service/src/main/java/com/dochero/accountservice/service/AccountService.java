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
        .fullname(account.getFullname())
        .department_id(account.getDepartment_id())
        .build();
    accountToCreate.setEmail(account.getEmail());
    Account newAccount = accountRepository.save(accountToCreate);
    return CreateAccountResponseDTO.builder().id(newAccount.getId())
        .fullname(newAccount.getFullname())
        .email(newAccount.getEmail()).department_id(newAccount.getDepartment_id()).build();
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
              .fullname(account.getFullname())
              .department_id(account.getDepartment_id())
              .email(account.getEmail())
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

    if (!Objects.isNull(accountDTO.getDepartment_id())) {
      account.setDepartment_id(accountDTO.getDepartment_id());
    }

    if (!Objects.isNull(accountDTO.getFullname())) {
      account.setFullname(accountDTO.getFullname());
    }

    account = accountRepository.save(account);

    return AccountResponseDTO.builder().id(account.getId())
        .department_id(account.getDepartment_id()).fullname(
            account.getFullname()).email(account.getEmail()).build();
  }

  public void deleteAccount(String id) {
    findAccountById(id);
    accountRepository.deleteById(id);
  }
}
