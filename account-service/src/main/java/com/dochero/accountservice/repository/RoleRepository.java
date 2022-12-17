package com.dochero.accountservice.repository;

import com.dochero.accountservice.domain.Account;
import com.dochero.accountservice.domain.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
  public Optional<Role> findByName(String name);
}
