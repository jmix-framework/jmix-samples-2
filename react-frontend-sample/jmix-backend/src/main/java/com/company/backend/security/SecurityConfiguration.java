package com.company.backend.security;

import io.jmix.authserver.AuthServerProperties;
import io.jmix.core.JmixSecurityFilterChainOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private AuthServerProperties authServerProperties;

    @Bean
    @Order(JmixSecurityFilterChainOrder.FLOWUI - 10)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/custom-as-login/icons/**", "/custom-as-login/styles/**")
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());
        return http.build();
    }

    /*@Bean
    @Order(JmixSecurityFilterChainOrder.FLOWUI - 9)
    public SecurityFilterChain logoutSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/logout")
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/logout").authenticated()
        ).logout(logout ->
                logout.logoutUrl("/logout")
                        //.addLogoutHandler(new SecurityContextLogoutHandler()) // Custom logout handler
                        .logoutSuccessHandler(logoutSuccessHandler()) // Redirect after logout
                        .invalidateHttpSession(true) // Should be true by default
                        .clearAuthentication(true) // Should be true by default
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(RequestMatchers.anyOf(
                                new AntPathRequestMatcher("/logout", "GET"),
                                new AntPathRequestMatcher("/logout", "POST")) )
        );
        return http.build();
    }*/

    public LogoutSuccessHandler logoutSuccessHandler() {
        //TODO Implement proper redirect after logout
        SimpleUrlLogoutSuccessHandler successHandler = new SimpleUrlLogoutSuccessHandler();
        successHandler.setTargetUrlParameter("post_logout_redirect_uri"); // parameter 'post_logout_redirect_uri' is automatically sent by frontend app
        return successHandler;
    }

    //@Bean
    public LogoutSuccessHandler logoutSuccessHandlerDirectImpl() {
        return (request, response, authentication) -> {
            //TODO Implement proper redirect after logout

            String redirectUri = request.getParameter("post_logout_redirect_uri");

            // Validate the redirect URI
            if (redirectUri == null) {
                redirectUri = "/login";
            }

            // Add logout flag if not present
            if (!redirectUri.contains("logout")) {
                redirectUri += redirectUri.contains("?") ? "&logout" : "?logout";
            }

            response.sendRedirect(redirectUri);
        };
    }

}
