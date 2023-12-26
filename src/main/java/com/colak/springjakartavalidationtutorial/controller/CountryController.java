package com.colak.springjakartavalidationtutorial.controller;

import com.colak.springjakartavalidationtutorial.validation.ValidCountryCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "api/v1/countries")
public class CountryController {

    @GetMapping(value = "getname/{country}")
    public String getCountryName(@PathVariable("country") @ValidCountryCode String country) {
        return country;
    }

}
