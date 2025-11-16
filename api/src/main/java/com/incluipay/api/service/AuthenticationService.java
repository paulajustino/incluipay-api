package com.incluipay.api.service;

import com.incluipay.api.dto.LoginRequestDTO;
import com.incluipay.api.dto.LoginResponseDTO;
import com.incluipay.api.dto.UserRegistrationRequestDTO;
import com.incluipay.api.dto.UserResponseDTO;
import com.incluipay.api.exception.EmailAlreadyExistsException;
import org.springframework.security.authentication.BadCredentialsException;

public interface AuthenticationService {

    /**
     * Regista um novo usuario no sistema.
     * @param requestDTO Dados de registro (nome, email, password).
     * @return UserResponseDTO com os dados do usuario criado.
     * @throws EmailAlreadyExistsException Se o email já estiver em uso.
     */
    UserResponseDTO register(UserRegistrationRequestDTO requestDTO);

    /**
     * Autentica um usuario existente.
     * @param requestDTO Dados de login (email, password).
     * @return LoginResponseDTO contendo o token JWT.
     * @throws BadCredentialsException Se as credenciais forem inválidas.
     */
    LoginResponseDTO login(LoginRequestDTO requestDTO);
}
