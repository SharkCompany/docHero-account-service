package com.dochero.accountservice.validation.custom_validation;

import com.dochero.accountservice.domain.Account;
import com.dochero.accountservice.repository.AccountRepository;
import java.util.Objects;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  @Autowired
  AccountRepository accountRepository;

  @Override
  public void initialize(UniqueEmail uniqueEmail) {
  }

  @Override
  public boolean isValid(String objects, ConstraintValidatorContext context) {
    if (Objects.isNull(objects)) {
      return false;
    }
    Optional<Account> account = accountRepository.findByEmail(objects);
    return account.isEmpty();
  }
}

