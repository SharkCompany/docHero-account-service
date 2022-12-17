package com.dochero.accountservice.validation.custom_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RoleConstraintValidator.class)
public @interface RoleConstraint {
  String message() default "Role is invalid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

