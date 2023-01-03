package com.dochero.accountservice.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Column(name="email",unique=true)
    private String email;

    @Column(name="full_name")
    private String fullname;

    @Column(name="password")
    private String password;

    @Column(name="role_name")
    private String roleName;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="isDeleted")
    private boolean isDeleted;


}
