package com.company.backend.security;

import io.jmix.core.JmixSecurityFilterChainOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    @Order(JmixSecurityFilterChainOrder.FLOWUI - 10)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/custom-as-login/icons/**", "/custom-as-login/styles/**")
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
        return http.build();
    }

}
