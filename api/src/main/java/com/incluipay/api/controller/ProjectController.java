package com.incluipay.api.controller;

import com.incluipay.api.dto.ProjectFilterDTO;
import com.incluipay.api.dto.ProjectResponseDTO;
import com.incluipay.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Endpoint para listar todos os projetos ativos.
     * @return Lista de ProjectResponseDTO.
     */
    @GetMapping("/actives")
    public ResponseEntity<List<ProjectResponseDTO>> getAllActiveProjects() {
        List<ProjectResponseDTO> projects = projectService.findAllActiveProjects();
        return ResponseEntity.ok(projects);
    }

    /**
     * Endpoint para buscar um projeto ativo por ID.
     * @param id ID do projeto.
     * @return O ProjectResponseDTO ou 404 Not Found.
     */
    @GetMapping("/actives/{id}")
    public ResponseEntity<ProjectResponseDTO> getActiveProjectById(@PathVariable Long id) {
        try {
            ProjectResponseDTO project = projectService.findActiveProjectById(id);
            return ResponseEntity.ok(project);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para buscar projetos dinamicamente, permitindo o uso de filtros.
     * @param filters DTO mapeado dos query parameters.
     * @return Lista de ProjectResponseDTO.
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponseDTO>> searchProjectsByFilter(@ModelAttribute ProjectFilterDTO filters) {
        List<ProjectResponseDTO> projects = projectService.searchProjects(filters);
        return ResponseEntity.ok(projects);
    }
}
