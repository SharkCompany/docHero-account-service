package com.dochero.accountservice.openfeign.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentBaseResponseDTO<T> {
  private T data;
  private String message;
  private boolean error;
  private String errorCode;
}
