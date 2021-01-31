package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;

import java.util.List;

public interface IMateriaService {

    public List<MateriaDto> listar();

    public MateriaDto buscar(final Long id);

    public MateriaDto cadastrar(final MateriaDto materiaDto);

    public MateriaDto atualizar(final Long id, final MateriaDto materiaDto);

    public void deletar(final Long id);

}
