package com.incluipay.api.mapper;

import com.incluipay.api.dto.ProjectResponseDTO;
import com.incluipay.api.model.Project;

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
}
