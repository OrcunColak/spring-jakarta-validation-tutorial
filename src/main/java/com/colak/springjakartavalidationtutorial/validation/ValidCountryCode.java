package com.colak.springjakartavalidationtutorial.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ElementType.PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = CountryValidator.class)
@Documented
public @interface ValidCountryCode {

    String COUNTRY_CODE_IS_NOT_VALID = "Country code is not valid";

    String message() default COUNTRY_CODE_IS_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
