package me.librostats.userservice.util;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "Password must be longer than 8 characters and contain at least one uppercase letter, one lowercase letter and one digit.";

    Class<?>[] groups() default {};

    Class<?>[] payload() default {};
}
