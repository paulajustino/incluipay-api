package com.incluipay.api.service;

import com.incluipay.api.dto.ProjectFilterDTO;
import com.incluipay.api.dto.ProjectResponseDTO;
import com.incluipay.api.mapper.ProjectMapper;
import com.incluipay.api.model.Project;
import com.incluipay.api.model.ProjectStatus;
import com.incluipay.api.repository.ProjectRepository;
import com.incluipay.api.repository.ProjectSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.incluipay.api.repository.ProjectSpecification.isActiveSpecification;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * Lista todos os projetos ATIVOS.
     * Usa o Specification para eficiência no banco de dados.
     */
    public List<ProjectResponseDTO> findAllActiveProjects() {
        return projectRepository.findAll(isActiveSpecification())
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }

    /**
     * Busca um projeto ativo pelo ID.
     */
    public ProjectResponseDTO findActiveProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent() && project.get().getStatus() == ProjectStatus.ACTIVE) {
            return ProjectMapper.toDTO(project.get());
        } else {
            throw new NoSuchElementException("Projeto com ID " + id + " não encontrado ou inativo.");
        }
    }

    /**
     * Busca projetos dinamicamente com base em múltiplos filtros.
     */
    public List<ProjectResponseDTO> searchProjects(ProjectFilterDTO filters) {
        Specification<Project> dynamicSpec = ProjectSpecification.filterByParams(
                filters.title(),
                filters.category(),
                filters.organization(),
                filters.status(),
                filters.createdAt()
        );

        return projectRepository.findAll(dynamicSpec)
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
    }
}
