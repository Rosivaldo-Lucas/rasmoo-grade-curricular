package com.rasmoo.cliente.escola.gradecurricular.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class CursoResponse extends RepresentationModel<CursoResponse> {

    private Long id;

    private String nome;

    private String codigo;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MateriaDto> materias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<MateriaDto> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaDto> materias) {
        this.materias = materias;
    }

}
