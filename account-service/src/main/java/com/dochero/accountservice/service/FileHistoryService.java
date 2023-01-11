package com.dochero.accountservice.service;

import com.dochero.accountservice.domain.FileHistory;

import java.util.List;

public interface FileHistoryService {
    FileHistory updateFileHistory(String userId, String documentId);

    List<FileHistory> getAllFileHistoryByUserId(String userId);
}
