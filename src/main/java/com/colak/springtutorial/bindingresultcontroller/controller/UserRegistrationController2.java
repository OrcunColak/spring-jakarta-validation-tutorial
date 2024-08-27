package com.colak.springtutorial.bindingresultcontroller.controller;

import com.colak.springtutorial.bindingresultcontroller.dto.UserRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/userregistration2/")
public class UserRegistrationController2 {

    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        return ResponseEntity.ok("User created successfully");
    }
}
