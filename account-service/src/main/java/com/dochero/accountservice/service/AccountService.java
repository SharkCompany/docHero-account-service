package com.dochero.accountservice.service;

import com.dochero.accountservice.domain.Account;
import com.dochero.accountservice.repository.AccountRepository;
import com.dochero.accountservice.service.dto.account.CreateAccountDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
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
}
