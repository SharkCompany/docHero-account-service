package com.dochero.accountservice.domain.compositeid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountFileHistoryId implements Serializable {
    private String userId;
    private String documentId;
}
