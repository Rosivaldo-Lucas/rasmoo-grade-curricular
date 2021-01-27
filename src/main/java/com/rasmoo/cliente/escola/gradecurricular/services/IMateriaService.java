package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;

import java.util.List;

public interface IMateriaService {

    public List<Materia> listar();

    public Materia buscar(final Long id);

    public Materia cadastrar(final Materia materia);

    public Materia atualizar(final Long id, final Materia materia);

    public void deletar(final Long id);

}
