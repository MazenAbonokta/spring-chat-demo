package com.demo.chatdemo;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@SecurityScheme(name = "keycloak",
type = SecuritySchemeType.OAUTH2,
bearerFormat = "JWT",
scheme = "bearer",
in = SecuritySchemeIn.HEADER,
flows = @OAuthFlows(
        password = @OAuthFlow(
                tokenUrl = "http://localhost:9090/realms/master/protocol/openid-connect/token",
        authorizationUrl = "http://localhost:9090/realms/master/protocol/openid-connect/auth")
))
public class ChatDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatDemoApplication.class, args);
    }

}
