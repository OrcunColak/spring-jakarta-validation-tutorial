package com.colak.springtutorial.bindingresultcontroller;

import com.colak.springtutorial.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserRegistrationControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final String url = "/api/v1/userregistration/create";

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
    void testInvalid1() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        HttpEntity<UserRegistrationDto> request = new HttpEntity<>(userRegistrationDto);

        ResponseEntity<String> response = testRestTemplate.postForEntity(url, request,String.class);

        // Assert that the response status is 400 BAD REQUEST due to validation failure
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Assert that the error response contains the expected fields and messages
        // Get the response body as a Map representing the error response
        String errorResponse = response.getBody();
        assertThat(errorResponse).isEqualTo("{password=[Password must be at least 8 characters long], username=[Please provide a username]}");
    }
}
