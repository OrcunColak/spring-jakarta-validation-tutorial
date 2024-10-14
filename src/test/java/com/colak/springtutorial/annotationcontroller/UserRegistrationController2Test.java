package com.colak.springtutorial.annotationcontroller;

import com.colak.springtutorial.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ProblemDetail;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationController2Test {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String url = "/api/v1/userregistration2/create";

    @Test
    void testValidUser() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setUsername("john");
        userRegistrationDto.setPassword("12345678");
        userRegistrationDto.setEmail("john@gmail.com");

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(userRegistrationDto);

        String result = testRestTemplate.postForObject(url, request, String.class);
        assertEquals("User created successfully", result);
    }

    @Test
    void testInvalid() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(userRegistrationDto);

        ProblemDetail result = testRestTemplate.postForObject(url, request, ProblemDetail.class);
        Map<String, Object> properties = result.getProperties();
        assert properties != null;

        assertEquals("Please provide a username", properties.get("username"));
    }
}