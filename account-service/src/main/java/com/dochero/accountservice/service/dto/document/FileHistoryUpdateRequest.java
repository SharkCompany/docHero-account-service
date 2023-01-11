package com.dochero.accountservice.service.dto.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileHistoryUpdateRequest {
    @NotBlank(message = "File id is required")
    private String documentId;
}
