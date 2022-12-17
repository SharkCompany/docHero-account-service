package com.dochero.accountservice.validation.custom_validation;

import com.dochero.accountservice.domain.Role;
import com.dochero.accountservice.repository.RoleRepository;
import java.util.Objects;
import java.util.Optional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleConstraintValidator implements ConstraintValidator<RoleConstraint, String> {

  @Autowired
  RoleRepository roleRepository;

  @Override
  public void initialize(RoleConstraint roleConstraint) {
  }

  @Override
  public boolean isValid(String s, ConstraintValidatorContext context) {
    if (Objects.isNull(s)) return true;
    Optional<Role> roleOpt = roleRepository.findByName(s);
    return roleOpt.isPresent();
  }
}

