package com.colak.springtutorial.bindingresultcontroller.controller;

import com.colak.springtutorial.bindingresultcontroller.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testValidUser() {
        String url = "/api/v1/userregistration/create";

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername("john");
        userRegistrationDto.setPassword("12345678");
        userRegistrationDto.setEmail("john@gmail.com");

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(userRegistrationDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("User created successfully", result);
    }

    @Test
    void testInvalid1() {
        String url = "/api/v1/userregistration/create";

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(userRegistrationDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("Validation failed", result);
    }

    @Test
    void testInvalid2() {
        String url = "/api/v1/userregistration2/create";

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(userRegistrationDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("""
                {"username":"Please provide a username"}""", result);
    }

}
