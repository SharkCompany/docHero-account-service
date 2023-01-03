package com.dochero.accountservice.repository;

import com.dochero.accountservice.domain.Account;
import com.dochero.accountservice.repository.CustomRepository.MyRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long>, MyRepository {
  public Optional<Account> findByEmail(String email);
  public Optional<Account> findById(String id);

  public Optional<Account> findByEmailAndPassword(String email, String password);
  public void deleteById(String id);

  public List<Account> findAllByIsDeletedFalseOrderByEmail();
}
