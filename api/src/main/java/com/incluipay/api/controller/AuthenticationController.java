package com.incluipay.api.controller;

import com.incluipay.api.dto.LoginRequestDTO;
import com.incluipay.api.dto.LoginResponseDTO;
import com.incluipay.api.dto.UserRegistrationRequestDTO;
import com.incluipay.api.dto.UserResponseDTO;
import com.incluipay.api.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Endpoint para registrar um novo usuario.
     * Retorna 201 CREATED em caso de sucesso.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegistrationRequestDTO requestDTO) {

        UserResponseDTO newUser = authenticationService.register(requestDTO);

        // Retorna 201 CREATED com os dados do usuario criado
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    /**
     * Endpoint para autenticar um usuario e obter um token JWT.
     * Retorna 200 OK com o token em caso de sucesso.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO) {

        LoginResponseDTO tokenResponse = authenticationService.login(requestDTO);

        // Retorna 200 OK com o token
        return ResponseEntity.ok(tokenResponse);
    }
}
