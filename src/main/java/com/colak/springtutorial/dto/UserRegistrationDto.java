package com.colak.springtutorial.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {

    @NotBlank(message = "{user.username.blank}")
    private String username;

    // The @Size annotation in Java validation does not handle blank (empty) values by default.
    // It only checks for the length of the string (or the size of a collection or array), and it doesn't validate whether
    // the string is non-empty or contains non-whitespace characters.
    @NotBlank(message = "{user.password.size}")
    @Size(min = 8, message = "{user.password.size}")
    private String password;

    @Email(message = "{user.email.invalid}")
    private String email;
}
