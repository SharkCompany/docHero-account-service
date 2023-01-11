package com.dochero.accountservice.domain;

import com.dochero.accountservice.domain.compositeid.AccountFileHistoryId;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@IdClass(AccountFileHistoryId.class)
@Table(name="file_history")
public class FileHistory {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Id
    @Column(name = "document_id")
    private String documentId;

    @Column(name = "viewed_at")
    private Timestamp viewedAt;

}
