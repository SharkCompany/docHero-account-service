package com.dochero.accountservice.service;

import com.dochero.accountservice.domain.Account;
import com.dochero.accountservice.domain.AccountDepartment;
import com.dochero.accountservice.domain.compositeid.AccountDepartmentId;
import com.dochero.accountservice.exception.AccountNotFoundException;
import com.dochero.accountservice.exception.DepartmentNotFoundException;
import com.dochero.accountservice.exception.WrongPasswordException;
import com.dochero.accountservice.openfeign.DepartmentServiceFeignClient;
import com.dochero.accountservice.repository.AccountDepartmentRepository;
import com.dochero.accountservice.repository.AccountRepository;
import com.dochero.accountservice.service.dto.account.AccountResponseDTO;
import com.dochero.accountservice.service.dto.account.ChangePasswordRequestDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountDTO;
import com.dochero.accountservice.service.dto.account.CreateAccountResponseDTO;
import com.dochero.accountservice.service.dto.account.UpdateAccountDTO;
import com.dochero.accountservice.service.dto.account.UpdateProfileRequestDTO;
import com.dochero.accountservice.service.dto.account.ValidateAccountDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class AccountService {

  final AccountRepository accountRepository;

  final AccountDepartmentRepository accountDepartmentRepository;

  final PasswordEncoder passwordEncoder;

  final DepartmentServiceFeignClient departmentServiceFeignClient;

  public CreateAccountResponseDTO register(CreateAccountDTO account) {
    Account accountToCreate = Account.builder()
        .email(account.getEmail())
        .password(passwordEncoder.encode(account.getPassword()))
        .fullname(account.getFullName())
        .roleName(account.getRoleName())
        .build();
    accountToCreate.setEmail(account.getEmail());

    List<String> departmentIDs = account.getDepartmentIDs();
    List<AccountDepartment> accountDepartments = new ArrayList<>();
    Account newAccount = accountRepository.save(accountToCreate);

    for (String departmentID : departmentIDs) {
      try {
        var res = departmentServiceFeignClient.getById(departmentID);
        if (Objects.nonNull(res.getData())) {
          accountDepartments.add(new AccountDepartment(newAccount.getId(), departmentID));
        }
      } catch (Exception ex) {
        throw new DepartmentNotFoundException(
            String.format("Department with id = %s is not found. Error: %s", departmentID,
                ex));
      }
    }

    accountDepartmentRepository.saveAll(accountDepartments);

    return CreateAccountResponseDTO.builder().id(newAccount.getId())
        .roleName(newAccount.getRoleName())
        .fullName(newAccount.getFullname())
        .departmentIDs(accountDepartments.stream().map(AccountDepartment::getDepartmentId).collect(
            Collectors.toList()))
        .email(newAccount.getEmail()).build();
  }

  private void deleteDepartmentsFromAccount(String userId) {
    List<AccountDepartment> accountDepartments = accountDepartmentRepository.findAccountDepartmentByUserId(
        userId);
    List<AccountDepartmentId> accountDepartmentIdsToDelete = new ArrayList<>();
    for (AccountDepartment accountDepartment : accountDepartments) {
      try {
        var res = departmentServiceFeignClient.getById(accountDepartment.getDepartmentId());
      } catch (Exception ex) {
        System.out.println(ex);
        System.out.println(accountDepartment);
        AccountDepartmentId accountDepartmentId = new AccountDepartmentId(
            accountDepartment.getUserId(), accountDepartment.getDepartmentId());
        accountDepartmentIdsToDelete.add(accountDepartmentId);
      }
    }
    accountDepartmentRepository.deleteAllById(accountDepartmentIdsToDelete);
  }

  public AccountResponseDTO getAccountById(String userId) {
    deleteDepartmentsFromAccount(userId);
    Optional<Account> accountOptional = accountRepository.findById(userId);
    if (accountOptional.isEmpty()) {
      throw new AccountNotFoundException();
    }

    Account account = accountOptional.get();

    List<AccountDepartment> accountDepartments = accountDepartmentRepository.findAccountDepartmentByUserId(
        userId);
    return AccountResponseDTO.builder()
        .id(account.getId())
        .email(account.getEmail())
        .roleName(account.getRoleName())
        .departmentIDs(accountDepartments.stream().map(AccountDepartment::getDepartmentId).collect(
            Collectors.toList()))
        .fullName(account.getFullname())
        .build();
  }

  public List<AccountResponseDTO> getAccounts() {
    List<Account> accounts = accountRepository.findAllByDeletedOrderByEmail(false);
    List<AccountResponseDTO> accountResponseDTOS = new ArrayList<>();
    for (Account account : accounts
    ) {
      deleteDepartmentsFromAccount(account.getId());
      List<AccountDepartment> accountDepartments = accountDepartmentRepository.findAccountDepartmentByUserId(
          account.getId());
      accountResponseDTOS.add(
          AccountResponseDTO
              .builder()
              .id(account.getId())
              .fullName(account.getFullname())
              .departmentIDs(
                  accountDepartments.stream().map(AccountDepartment::getDepartmentId).collect(
                      Collectors.toList()))
              .email(account.getEmail())
              .roleName(account.getRoleName())
              .build());
    }
    return accountResponseDTOS;
  }

  public AccountResponseDTO getAccountByEmailAndPassword(ValidateAccountDTO validateAccountDTO) {
    Optional<Account> accountOptional = accountRepository.findByEmail(
        validateAccountDTO.getEmail()
    );
    accountOptional.orElseThrow(AccountNotFoundException::new);

    String hashedPassword = accountOptional.get().getPassword();

    if (!passwordEncoder.matches(validateAccountDTO.getPassword(), hashedPassword)) {
      throw new WrongPasswordException();
    }

    Account account = accountOptional.get();
    return AccountResponseDTO
        .builder()
        .id(account.getId())
        .fullName(account.getFullname())
//        .departmentIDs(account.getDepartmentIDs())
        .email(account.getEmail())
        .roleName(account.getRoleName())
        .build();
  }

  public Account findAccountById(String id) {
    Optional<Account> account = accountRepository.findById(id);
    account.orElseThrow(AccountNotFoundException::new);
    return account.get();
  }

  public AccountResponseDTO updateProfile(String id, UpdateProfileRequestDTO accountDTO) {
    Account account = findAccountById(id);

    if (!Objects.isNull(accountDTO.getFullName())) {
      account.setFullname(accountDTO.getFullName());
    }

    if (!Objects.isNull(accountDTO.getTitle())) {
      account.setTitle(accountDTO.getTitle());
    }

    if (!Objects.isNull(accountDTO.getAbout())) {
      account.setAbout(accountDTO.getAbout());
    }
    if (!Objects.isNull(accountDTO.getDescription())) {
      account.setDescription(accountDTO.getDescription());
    }
    if (!Objects.isNull(accountDTO.getAvatar())) {
      account.setAvatar(accountDTO.getAvatar());
    }
    if (!Objects.isNull(accountDTO.getCoverPhoto())) {
      account.setCoverPhoto(accountDTO.getCoverPhoto());
    }
    if (!Objects.isNull(accountDTO.getLocation())) {
      account.setLocation(accountDTO.getLocation());
    }

    account = accountRepository.save(account);

    return AccountResponseDTO.builder().id(account.getId())
        .fullName(account.getFullname())
        .email(account.getEmail())
        .roleName(account.getRoleName())
        .title(account.getTitle())
        .description(account.getDescription())
        .coverPhoto(account.getCoverPhoto())
        .avatar(account.getAvatar())
        .location(account.getLocation())
        .about(account.getAbout())
        .build();
  }

  public String changePassword(String userId,ChangePasswordRequestDTO changePasswordRequestDTO) {
    Optional<Account> accountOpt = accountRepository.findById(userId);

    if (accountOpt.isEmpty()) throw new AccountNotFoundException();

    if (!passwordEncoder.matches(changePasswordRequestDTO.getOldPassword(), accountOpt.get().getPassword())) {
      throw new WrongPasswordException();
    }

    accountOpt.get().setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));

    accountRepository.save(accountOpt.get());

    return "successfully";
  }

  public AccountResponseDTO updateAccount(String id, UpdateAccountDTO accountDTO) {
    Account account = findAccountById(id);

    if (!Objects.isNull(accountDTO.getDepartmentIDs())) {
//      account.setDepartmentIDs(accountDTO.getDepartmentId());
      accountDepartmentRepository.deleteAllByUserId(id);
      List<AccountDepartment> accountDepartments = new ArrayList<>();
      for (String departmentID : accountDTO.getDepartmentIDs()) {
        try {
          var res = departmentServiceFeignClient.getById(departmentID);
          if (Objects.nonNull(res.getData())) {
            accountDepartments.add(new AccountDepartment(id, departmentID));
          }
        } catch (Exception ex) {
          throw new DepartmentNotFoundException(
              String.format("Department with id = %s is not found. Error: %s", departmentID,
                  ex));
        }
      }
      accountDepartmentRepository.saveAll(accountDepartments);
    }

    if (!Objects.isNull(accountDTO.getFullName())) {
      account.setFullname(accountDTO.getFullName());
    }

    if (!Objects.isNull(accountDTO.getRoleName())) {
      account.setRoleName(accountDTO.getRoleName());
    }

    account = accountRepository.save(account);

    return AccountResponseDTO.builder().id(account.getId())
//        .departmentIDs(account.getDepartmentIDs())
        .fullName(account.getFullname())
        .email(account.getEmail())
        .departmentIDs(accountDTO.getDepartmentIDs())
        .roleName(account.getRoleName())
        .build();
  }


  public void deleteAccount(String id) {
//    Account account = findAccountById(id);
    accountRepository.deleteById(id);
  }

  public List<String> getUserDepartmentIDs(String userId) {
    List<AccountDepartment> accountDepartments = accountDepartmentRepository.findAccountDepartmentByUserId(
        userId);
    return accountDepartments.stream().map(AccountDepartment::getDepartmentId)
        .collect(Collectors.toList());
  }

}
