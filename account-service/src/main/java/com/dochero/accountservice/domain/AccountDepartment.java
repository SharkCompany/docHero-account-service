package com.dochero.accountservice.domain;

import com.dochero.accountservice.domain.compositeid.AccountDepartmentId;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="account_department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(AccountDepartmentId.class)
public class AccountDepartment implements Serializable {

    @Id
    @Column(name="user_id")
    private String userId;

    @Id
    @Column(name="department_id")
    private String departmentId;
}
