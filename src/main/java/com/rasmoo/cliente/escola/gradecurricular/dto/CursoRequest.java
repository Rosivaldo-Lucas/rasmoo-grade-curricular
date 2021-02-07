package com.rasmoo.cliente.escola.gradecurricular.dto;

import com.rasmoo.cliente.escola.gradecurricular.validations.GroupsValidations;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import java.util.List;
import java.util.Set;

public class CursoRequest {

    @NotBlank(message = "nome deve ser preenchido")
    @Size(min = 10, max = 30)
    private String nome;

    @NotBlank(message = "código deve ser preenchido")
    @Size(min = 3, max = 5)
    private String codigo;

    @ConvertGroup(to = GroupsValidations.MateriaDtoId.class)
    @Valid
    @NotNull
    private Set<MateriaDto> materias;

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

    public Set<MateriaDto> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<MateriaDto> materias) {
        this.materias = materias;
    }

}
