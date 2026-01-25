package me.librostats.userservice.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_LENGTH = 8;
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile("[a-z]");
    private static final Pattern DIGIT_PATTERN = Pattern.compile("\\d");

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isBlank()) {
            return false;
        }
        if (password.length() < MIN_LENGTH) {
            setCustomMessage(context, "Password must contain at least 8 characters");
            return false;
        }
        if (!UPPERCASE_PATTERN.matcher(password).find()) {
            setCustomMessage(context, "Password must contain at least one uppercase letter.");
            return false;
        }
        if (!LOWERCASE_PATTERN.matcher(password).find()) {
            setCustomMessage(context, "Password must contain at least one lowercase letter.");
            return false;
        }
        if (!DIGIT_PATTERN.matcher(password).find()) {
            setCustomMessage(context, "Password must contain at least one digit");
            return false;
        }

        return true;
    }

    private void setCustomMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
