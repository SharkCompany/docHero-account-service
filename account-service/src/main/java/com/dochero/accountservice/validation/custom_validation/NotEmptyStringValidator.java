package com.dochero.accountservice.validation.custom_validation;

import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyStringValidator implements ConstraintValidator<NotEmptyString, String> {

  @Override
  public void initialize(NotEmptyString notEmptyString) {
  }

  @Override
  public boolean isValid(String objects, ConstraintValidatorContext context) {
//    if (Objects.isNull(objects)) return false;
    return !Objects.isNull(objects) && !objects.isEmpty();
  }
}

