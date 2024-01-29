package com.colak.springjakartavalidationtutorial.bindingresultcontroller.controller;

import com.colak.springjakartavalidationtutorial.bindingresultcontroller.dto.UserRegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testValidUser() {
        String url = "/api/v1/user/create/";

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername("john");
        userRegistrationDto.setPassword("12345678");
        userRegistrationDto.setEmail("john@gmail.com");

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(userRegistrationDto);

        ResponseEntity<String> result = testRestTemplate.postForEntity(url, request, String.class);
        assertEquals("User created successfully", result.getBody());
    }

    @Test
    void testInvalid() {
        String url = "/api/v1/countries/getname/ooo";
        ResponseEntity<ProblemDetail> response = testRestTemplate.getForEntity(url, ProblemDetail.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ProblemDetail problemDetail = response.getBody();

        assert problemDetail != null;
        assertTrue(Objects.requireNonNull(problemDetail.getDetail())
                .equalsIgnoreCase("Request validation failed")
        );
    }

}
