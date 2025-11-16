package com.incluipay.api.model;

import lombok.Getter;

@Getter
public enum ProjectStatus {
    ACTIVE("Ativo"),
    INACTIVE("Inativo"),
    COMPLETED("Completo");

    private final String description;

    ProjectStatus(String description) {
        this.description = description;
    }
}
