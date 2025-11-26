package com.incluipay.api.security;

import com.incluipay.api.exception.JwtAuthEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    private final JwtAuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Configuração de desabilitação do CSRF e Cabeçalhos
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

                // Regras de autorização de acesso
                .authorizeHttpRequests(auth -> auth
                        // Liberação de rotas públicas
                        .requestMatchers(HttpMethod.GET, "/projects/**").permitAll()
                        .requestMatchers("/auth/**", "/h2-console/**").permitAll()

                        // Rotas restritas a Role = ADMIN
                        .requestMatchers(HttpMethod.POST, "/projects").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/projects/{id}/status").hasRole("ADMIN")

                        // Exige Autenticação para o restante ‘default’
                        .anyRequest().authenticated()
                )

                // Tratamento de exceçao de autenticaçao
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(authEntryPoint)
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