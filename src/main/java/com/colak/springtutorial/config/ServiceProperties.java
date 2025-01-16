package com.colak.springtutorial.config;

import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
@ConfigurationProperties(prefix = "service")
public class ServiceProperties {

    @NotNull(message = "Service name must not be null")
    private String name;

    // for the nested configurations, the validation only cascades down to nested properties when the corresponding field is annotated with @Valid.
    @Valid
    private Connection connection = new Connection();

    @Getter
    @Setter
    public static class Connection {

        @URL
        @NotBlank(message = "URL can not be blank")
        private String url;

        @NotBlank(message = "Token not be blank")
        private String token;
    }
}
