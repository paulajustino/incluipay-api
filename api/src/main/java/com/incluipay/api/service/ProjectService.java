package com.incluipay.api.service;

import com.incluipay.api.dto.ProjectFilterDTO;
import com.incluipay.api.dto.ProjectResponseDTO;

import java.util.List;
import java.util.NoSuchElementException;

public interface ProjectService {

    /**
     * Lista todos os projetos ATIVOS disponíveis.
     * @return Lista de projetos no formato DTO.
     */
    List<ProjectResponseDTO> findAllActiveProjects();

    /**
     * Busca um projeto ativo pelo ID.
     * @param id O ID do projeto a ser buscado.
     * @return O projeto encontrado no formato DTO.
     * @throws NoSuchElementException Se o projeto não for encontrado ou estiver inativo.
     */
    ProjectResponseDTO findActiveProjectById(Long id) throws NoSuchElementException;

    /**
     * Busca projetos dinamicamente com base em múltiplos filtros.
     * @param filters DTO contendo os parâmetros de filtro da URL.
     * @return Lista de projetos que correspondem aos filtros.
     */
    List<ProjectResponseDTO> searchProjects(ProjectFilterDTO filters);
}
