package com.incluipay.api.dto;

public record LoginResponseDTO(
        String token,
        String tokenType
) {
    public static LoginResponseDTO fromJwt(String token) {
        return new LoginResponseDTO(token, "Bearer");
    }
}