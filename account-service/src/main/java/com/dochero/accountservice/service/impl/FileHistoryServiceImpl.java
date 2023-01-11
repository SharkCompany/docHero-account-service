package com.dochero.accountservice.service.impl;

import com.dochero.accountservice.domain.Account;
import com.dochero.accountservice.domain.FileHistory;
import com.dochero.accountservice.domain.compositeid.AccountFileHistoryId;
import com.dochero.accountservice.repository.AccountRepository;
import com.dochero.accountservice.repository.FileHistoryRepository;
import com.dochero.accountservice.service.FileHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class FileHistoryServiceImpl implements FileHistoryService {

    private final FileHistoryRepository fileHistoryRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public FileHistoryServiceImpl(FileHistoryRepository fileHistoryRepository, AccountRepository accountRepository) {
        this.fileHistoryRepository = fileHistoryRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public FileHistory updateFileHistory(String userId, String documentId) {
        Account accountUser = accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FileHistory fileHistory = fileHistoryRepository.findByUserIdAndDocumentId(userId, documentId)
                .orElseGet(() -> FileHistory.builder()
                        .userId(accountUser.getId())
                        .documentId(documentId)
                        .build());
        fileHistory.setViewedAt(Timestamp.from(Instant.now()));
        return fileHistoryRepository.save(fileHistory);
    }

    @Override
    public List<FileHistory> getAllFileHistoryByUserId(String userId) {
        accountRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return fileHistoryRepository.findByUserId(userId);
    }
}
