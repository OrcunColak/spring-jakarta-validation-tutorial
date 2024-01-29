package com.colak.springjakartavalidationtutorial.bindingresultcontroller.controller;

import com.colak.springjakartavalidationtutorial.bindingresultcontroller.dto.UserRegistrationDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/userregistration/")
public class UserRegistrationController {

    /**
     * The controller receives BindingResult
     */
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation failed");
        }
        return ResponseEntity.ok("User created successfully");
    }
}
