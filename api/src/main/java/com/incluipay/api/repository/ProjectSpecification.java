package com.incluipay.api.repository;

import com.incluipay.api.model.Project;
import com.incluipay.api.model.ProjectCategory;
import com.incluipay.api.model.ProjectStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

import static com.incluipay.api.utils.SpecificationUtils.isNotNullOrEmpty;
import static com.incluipay.api.utils.SpecificationUtils.likeIgnoreCase;

public class ProjectSpecification {

    // Specification<Project> é basicamente uma função que gera a cláusula WHERE da query de forma dinâmica.
    public static Specification<Project> filterByParams(
            String title,
            ProjectCategory category,
            String organization,
            ProjectStatus status,
            LocalDateTime createdAt
    ) {
        return (root, query, builder) -> {

            // Cria um predicado inicial equivalente a "true"
            Predicate predicate = builder.conjunction();

            if (isNotNullOrEmpty(title)) {
                predicate = builder.and(predicate, likeIgnoreCase(builder, root.get("title"), title));
            }

            if (category != null) {
                predicate = builder.and(predicate, builder.equal(root.get("category"), category));
            }

            if (isNotNullOrEmpty(organization)) {
                predicate = builder.and(predicate, likeIgnoreCase(builder, root.get("organization"), organization));
            }

            if (status != null) {
                predicate = builder.and(predicate, builder.equal(root.get("status"), status));
            }

            if (createdAt != null) {
                predicate = builder.and(predicate, builder.greaterThanOrEqualTo(root.get("createdAt"), createdAt));
            }

            return predicate;
        };
    }

    /**
     * Specification que filtra apenas projetos ATIVOS.
     * Necessário para o metodo findAllActiveProjects.
     */
    public static Specification<Project> isActiveSpecification() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("status"), ProjectStatus.ACTIVE);
    }
}
