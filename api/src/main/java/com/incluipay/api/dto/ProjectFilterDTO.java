package com.incluipay.api.dto;

import com.incluipay.api.model.ProjectCategory;
import com.incluipay.api.model.ProjectStatus;

import java.time.LocalDateTime;

/**
 * DTO usado para receber todos os parâmetros de filtro de URL
 * (Query Parameters) para a busca dinâmica de projetos.
 */
public record ProjectFilterDTO(
        String title,
        ProjectCategory category,
        String organization,
        ProjectStatus status,
        LocalDateTime createdAt
) {}
