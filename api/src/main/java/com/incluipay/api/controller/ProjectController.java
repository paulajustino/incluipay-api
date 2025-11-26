package com.incluipay.api.controller;

import com.incluipay.api.dto.ProjectCreationDTO;
import com.incluipay.api.dto.ProjectFilterDTO;
import com.incluipay.api.dto.ProjectResponseDTO;
import com.incluipay.api.dto.ProjectStatusUpdateDTO;
import com.incluipay.api.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Endpoint para listar todos os projetos ativos.
     *
     * @return Lista de ProjectResponseDTO.
     */
    @GetMapping("/actives")
    public ResponseEntity<List<ProjectResponseDTO>> getAllActiveProjects() {
        List<ProjectResponseDTO> projects = projectService.findAllActiveProjects();
        return ResponseEntity.ok(projects);
    }

    /**
     * Endpoint para buscar um projeto ativo por ID.
     *
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
     * Endpoint para buscar projetos dinamicamente com filtros opcionais,
     * usando query parameters mapeados para um DTO.
     *
     * @param filters DTO contendo possíveis filtros como categoria, status, etc.
     * @return Lista filtrada de ProjectResponseDTO.
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProjectResponseDTO>> searchProjectsByFilter(@ModelAttribute ProjectFilterDTO filters) {
        List<ProjectResponseDTO> projects = projectService.searchProjects(filters);
        return ResponseEntity.ok(projects);
    }

    /**
     * Endpoint para criar um projeto.
     *
     * @param creationDTO DTO contendo dados necessários para criação de projeto.
     * @return O ProjectResponseDTO criado, com cabeçalho Location apontando para o recurso.
     */
    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectCreationDTO creationDTO) {
        ProjectResponseDTO createdProject = projectService.createProject(creationDTO);
        return ResponseEntity.created(URI.create("/projects/" + createdProject.id())).body(createdProject);
    }

    /**
     * Endpoint para atualizar o status de um projeto (por exemplo, ativar/inativar).
     *
     * @param id ID do projeto a ser atualizado.
     * @param statusUpdateDTO DTO contendo o novo status.
     * @return 200 OK se atualizado com sucesso.
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateProjectStatus(
            @PathVariable Long id,
            @RequestBody ProjectStatusUpdateDTO statusUpdateDTO
    ) {
        projectService.updateProjectStatus(id, statusUpdateDTO);
        return ResponseEntity.ok().build();
    }
}
