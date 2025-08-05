package com.chatvote.chat_vote_app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                    .requestMatchers("/").permitAll() // Разрешаем корневой путь
                    .requestMatchers("/error").permitAll() // Разрешаем обработку ошибок
                    .requestMatchers("/favicon.ico").permitAll() // Разрешаем иконку
                    .requestMatchers("/api/**").permitAll() // API доступ
                    .anyRequest().authenticated()
            )
            .httpBasic(org.springframework.security.config.Customizer.withDefaults());

        return http.build();
    }
}