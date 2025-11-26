package com.incluipay.api.dto;

import com.incluipay.api.model.ProjectStatus;

import java.math.BigDecimal;

public record ProjectResponseDTO(
        Long id,
        String title,
        String description,
        String organizationName,
        BigDecimal currentAmount,
        BigDecimal goalAmount,
        ProjectStatus status
) {}
