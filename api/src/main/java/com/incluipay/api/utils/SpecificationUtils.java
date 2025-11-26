package com.incluipay.api.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;

public class SpecificationUtils {

    // Função utilitária para like case-insensitive
    public static Predicate likeIgnoreCase(CriteriaBuilder builder, Path<String> path, String value) {
        return builder.like(builder.lower(path), "%" + value.toLowerCase() + "%");
    }

    // Função utilitária para verificar nulo ou vazio
    public static boolean isNotNullOrEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
