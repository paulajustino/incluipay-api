package com.incluipay.api.service;

import com.incluipay.api.dto.LoginRequestDTO;
import com.incluipay.api.dto.LoginResponseDTO;
import com.incluipay.api.dto.UserRegistrationRequestDTO;
import com.incluipay.api.dto.UserResponseDTO;
import com.incluipay.api.exception.EmailAlreadyExistsException;
import com.incluipay.api.model.Role;
import com.incluipay.api.model.User;
import com.incluipay.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // injeta todas as dependências 'final'
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public UserResponseDTO register(UserRegistrationRequestDTO requestDTO) {

        if (userRepository.existsByEmail(requestDTO.email())) {
            throw new EmailAlreadyExistsException("O email '" + requestDTO.email() + "' já está em uso.");
        }

        User newUser = new User();
        newUser.setName(requestDTO.name());
        newUser.setEmail(requestDTO.email());

        newUser.setPassword(passwordEncoder.encode(requestDTO.password()));

        newUser.setRole(Role.USER);

        User savedUser = userRepository.save(newUser);

        return UserResponseDTO.fromEntity(savedUser);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.email(),
                        requestDTO.password()
                )
        );

        User user = userRepository.findByEmail(requestDTO.email())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado após autenticação"));

        String jwtToken = jwtService.generateToken(user);

        return LoginResponseDTO.fromJwt(jwtToken);
    }
}
