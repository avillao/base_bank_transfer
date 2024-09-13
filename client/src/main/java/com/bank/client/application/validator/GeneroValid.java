package com.bank.client.application.validator;

import com.bank.client.domain.entity.Genero;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GeneroValidator.class)
public @interface GeneroValid {
    String message() default "Genero no válido, valores permitidos: ['M', 'F']";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends Enum<Genero>> enumClass() default Genero.class;
}
