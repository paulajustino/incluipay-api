package com.incluipay.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.swing.*;

@Configuration
@EnableWebSecurity // Garante que o Spring Security está ativo
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configuração do H2 e CSRF
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

                // Regras de Autorização
                .authorizeHttpRequests(auth -> auth
                        // Permite acesso público ao H2 Console e aos endpoints de Auth
                        .requestMatchers("/auth/**", "/h2-console/**").permitAll()
                        // Exige autenticação (Token JWT) para todos os outros endpoints
                        .anyRequest().authenticated()
                )

                // Gestão de Sessão (Chave para JWT)
                // Define o modo STATELESS (sem sessão), pois o JWT é autocontido.
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Adiciona filtro JWT ANTES do filtro de autenticação padrão do Spring.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}