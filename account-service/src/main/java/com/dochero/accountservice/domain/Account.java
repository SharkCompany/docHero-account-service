package com.dochero.accountservice.domain;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Entity
@Table(name="accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;


}
