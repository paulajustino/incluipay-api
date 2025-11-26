package com.incluipay.api.dto;

import com.incluipay.api.model.ProjectStatus;
import jakarta.validation.constraints.NotNull;

/**
 * DTO usado para alterar o status de um projeto no sistema.
 */
public record ProjectStatusUpdateDTO(
        @NotNull
        ProjectStatus newStatus
) { }
