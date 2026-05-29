package com.hospital.system.medicarex.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ✅ defines bean
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**").permitAll() // public
                        .anyRequest().authenticated() // everything else requires login
                )
                .formLogin(form -> form
                        .loginPage("/login")                // custom login page
                        .defaultSuccessUrl("/dashboard", true) // redirect after login
                        .permitAll()
                )
                .logout(logout -> logout.permitAll()); // new style

        return http.build();
    }
}
