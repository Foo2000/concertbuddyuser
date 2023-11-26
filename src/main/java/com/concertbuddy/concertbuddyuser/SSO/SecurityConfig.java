package com.concertbuddy.concertbuddyuser.SSO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.concertbuddy.concertbuddyuser.SSO.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.Collections;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtil jwtUtil; 

    @Autowired
    private JwtRequestFilter jwtRequestFilter; 

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                    .successHandler((request, response, authentication) -> {

                        // Extract email or username from SSO authentication
                        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
                        String email = oidcUser.getEmail();

                        // Generate JWT token
                        String jwt = jwtUtil.generateToken(email); // Use email for token generation

                        // Set the JWT token in the response body
                        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                        try {
                            new ObjectMapper().writeValue(response.getOutputStream(), 
                                Collections.singletonMap("jwt_token", jwt));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}