package com.dochero.accountservice.repository;

import com.dochero.accountservice.domain.AccountDepartment;
import com.dochero.accountservice.domain.compositeid.AccountDepartmentId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDepartmentRepository extends
    JpaRepository<AccountDepartment, AccountDepartmentId> {

  List<AccountDepartment> findAccountDepartmentByUserId(String userId);
}
