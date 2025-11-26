package com.incluipay.api.dto;

import com.incluipay.api.model.ProjectCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * DTO usado para cadastrar um novo projeto no sistema.
 */
public record ProjectCreationDTO(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        ProjectCategory category,

        @NotBlank
        String organization,

        @NotNull
        @Positive
        BigDecimal goalAmount
) { }