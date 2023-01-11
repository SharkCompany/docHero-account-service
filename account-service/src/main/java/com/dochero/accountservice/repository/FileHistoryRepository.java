package com.dochero.accountservice.repository;

import com.dochero.accountservice.domain.FileHistory;
import com.dochero.accountservice.domain.compositeid.AccountFileHistoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileHistoryRepository extends JpaRepository<FileHistory, AccountFileHistoryId> {
    Optional<FileHistory> findByUserIdAndDocumentId(String userId, String documentId);

    List<FileHistory> findByUserId(String userId);
}
