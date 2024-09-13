package com.bank.client.application.validator;

import com.bank.client.domain.entity.Estado;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EstadoValidator implements ConstraintValidator<EstadoValid, String> {
    private Enum<Estado>[] enumConstants;

    @Override
    public void initialize(EstadoValid constraintAnnotation) {
        this.enumConstants = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(enumConstants).anyMatch(
                e -> ((Estado) e).getValue().equals(value)
        );
    }
}