package com.roche.SchoolRegister.SchoolRegister.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index/dashboard"))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutSuccessUrl("/")
                        .permitAll())
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/lang").permitAll()
                        .requestMatchers("/index/dashboard/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/assignment/**").hasRole("TEACHER")
                        .requestMatchers("/student/**").hasAnyRole("ADMIN", "STUDENT"));

        return http.build();
    }

    @Bean(name = "encoder")
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
