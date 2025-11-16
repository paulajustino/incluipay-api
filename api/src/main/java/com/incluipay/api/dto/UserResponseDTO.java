package com.incluipay.api.dto;

import com.incluipay.api.model.User;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String role
) {
    /**
     * Converte uma Entidade User em um DTO de resposta.
     */
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
