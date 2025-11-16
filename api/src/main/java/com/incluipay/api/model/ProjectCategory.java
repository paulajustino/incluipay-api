package com.incluipay.api.model;

import lombok.Getter;

@Getter
public enum ProjectCategory {
    CURSOS_TECNOLOGIA("Cursos de Tecnologia"),
    FERRAMENTAS_ASSISTIVAS("Ferramentas Assistivas"),
    ADAPTACAO_WEB("Adaptação Web e Conteúdo"),
    CAPACITACAO_PROFISSIONAL("Capacitação Profissional"),
    MENTORIA_EMPREENDEDORISMO("Mentoria e Empreendedorismo"),
    DOACAO_EQUIPAMENTOS("Doação de Equipamentos"),
    INFRAESTRUTURA_DIGITAL("Infraestrutura Digital"),
    OUTROS("Outros");

    private final String description;

    ProjectCategory(String description) {
        this.description = description;
    }
}
