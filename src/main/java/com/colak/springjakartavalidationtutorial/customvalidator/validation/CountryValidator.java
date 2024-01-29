package com.colak.springjakartavalidationtutorial.customvalidator.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

@Component
public class CountryValidator implements ConstraintValidator<ValidCountryCode, String> {
    private static final String[] COUNTRIES = Locale.getISOCountries();
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(COUNTRIES)
                .anyMatch(element -> element.equalsIgnoreCase(value));
    }
}
