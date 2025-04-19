package com.demo.chatdemo.constant;

import java.util.List;

public class SecurityConstants {
    public static final List<String> PERMITTED_URLS = List.of(
            "/auth/signin",
            "/ws/**",
            "/static/uploads/**",
            "/v1/api/get-token",
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/configuration/ui",
            "/configuration/security"

    );
}
