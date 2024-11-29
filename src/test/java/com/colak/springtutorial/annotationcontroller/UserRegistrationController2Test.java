package com.colak.springtutorial.annotationcontroller;

import com.colak.springtutorial.dto.UserRegistrationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ProblemDetail;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("Password must be at least 8 characters long", properties.get("password"));
    }

    @Test
    void testInvalidFrench() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        // Test for French locale by changing Accept-Language header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept-Language", "fr"); // Change to French locale
        HttpEntity<UserRegistrationDto> requestWithFrenchLocale = new HttpEntity<>(userRegistrationDto, headers);

        ProblemDetail result = testRestTemplate.exchange(url, HttpMethod.POST, requestWithFrenchLocale, ProblemDetail.class).getBody();
        Map<String, Object> properties = result.getProperties();
        assert properties != null;
        assertEquals("Veuillez fournir un nom d'utilisateur", properties.get("username"));
        assertEquals("Le mot de passe doit contenir au moins 8 caractères", properties.get("password"));
    }

    @Test
    void testInvalidGerman() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        // Test for German locale by changing Accept-Language header
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT_LANGUAGE, "de"); // Change to German locale
        HttpEntity<UserRegistrationDto> requestWithGermanLocale = new HttpEntity<>(userRegistrationDto, headers);

        ProblemDetail result = testRestTemplate.exchange(url, HttpMethod.POST, requestWithGermanLocale, ProblemDetail.class).getBody();
        Map<String, Object> properties = result.getProperties();
        assert properties != null;
        assertEquals("Bitte geben Sie einen Benutzernamen an", properties.get("username"));
        assertEquals("Das Passwort muss mindestens 8 Zeichen lang sein", properties.get("password"));
    }
}