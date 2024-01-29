package com.colak.springjakartavalidationtutorial.bindingresultcontroller.controller;

import com.colak.springjakartavalidationtutorial.bindingresultcontroller.dto.UserRegistrationDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserRegistrationController {

    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UserRegistrationDto userRegistrationDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        return ResponseEntity.ok("User created successfully");
    }
}
