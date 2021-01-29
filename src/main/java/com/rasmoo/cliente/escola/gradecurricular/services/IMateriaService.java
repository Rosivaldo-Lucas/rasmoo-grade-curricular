package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;

import java.util.List;

public interface IMateriaService {

    public List<Materia> listar();

    public Materia buscar(final Long id);

    public Materia cadastrar(final MateriaDto materiaDto);

    public Materia atualizar(final Long id, final MateriaDto materiaDto);

    public void deletar(final Long id);

}
