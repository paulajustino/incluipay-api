package com.incluipay.api.mapper;

import com.incluipay.api.dto.ProjectCreationDTO;
import com.incluipay.api.dto.ProjectResponseDTO;
import com.incluipay.api.model.Project;
import com.incluipay.api.model.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProjectMapper {
    public static ProjectResponseDTO toDTO(Project project) {
        return new ProjectResponseDTO(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getOrganization(),
                project.getCurrentAmount(),
                project.getGoalAmount(),
                project.getStatus()
        );
    }

    public static Project toEntity(ProjectCreationDTO dto) {
        Project project = new Project();

        project.setTitle(dto.title());
        project.setDescription(dto.description());
        project.setCategory(dto.category());
        project.setOrganization(dto.organization());
        project.setGoalAmount(dto.goalAmount());
        project.setCurrentAmount(BigDecimal.ZERO);
        project.setStatus(ProjectStatus.ACTIVE);
        project.setCreatedAt(LocalDateTime.now());

        return project;
    }
}
