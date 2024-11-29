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

    @Size(min = 8, message = "{user.password.size}")
    private String password;

    @Email(message = "{user.email.invalid}")
    private String email;
}
