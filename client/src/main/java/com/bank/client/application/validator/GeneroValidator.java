package com.bank.client.application.validator;

import com.bank.client.domain.entity.Genero;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GeneroValidator implements ConstraintValidator<GeneroValid, String> {
    private Enum<Genero>[] enumConstants;

    @Override
    public void initialize(GeneroValid constraintAnnotation) {
        this.enumConstants = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(enumConstants).anyMatch(
                e -> ((Genero) e).getValue().equals(value)
        );
    }
}
