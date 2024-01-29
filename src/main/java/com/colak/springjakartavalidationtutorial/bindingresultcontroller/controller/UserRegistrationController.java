package com.colak.springjakartavalidationtutorial.bindingresultcontroller.controller;

import com.colak.springjakartavalidationtutorial.bindingresultcontroller.dto.UserRegistrationDto;
import com.colak.springjakartavalidationtutorial.util.BindingResultConverter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/userregistration/")
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationController {

    private final BindingResultConverter bindingResultConverter;

    /**
     * The controller receives BindingResult
     */
    @PostMapping(path = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody @Valid UserRegistrationDto userRegistrationDto,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errorMap = bindingResultConverter.errorMap(bindingResult);
            log.info("Error Map : {}", errorMap);

            return ResponseEntity.badRequest().body("Validation failed");
        }
        return ResponseEntity.ok("User created successfully");
    }
}
