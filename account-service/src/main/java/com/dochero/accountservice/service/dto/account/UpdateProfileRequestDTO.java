package com.dochero.accountservice.service.dto.account;

import com.dochero.accountservice.validation.custom_validation.RoleConstraint;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequestDTO {

  private String fullName;
  private String title;
  private String about;
  private String description;
  private String avatar;
  private String coverPhoto;
  private String location;

}
