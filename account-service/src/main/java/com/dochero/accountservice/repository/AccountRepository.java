package com.dochero.accountservice.repository;

import com.dochero.accountservice.domain.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {
  public Optional<Account> findByEmail(String email);
}
