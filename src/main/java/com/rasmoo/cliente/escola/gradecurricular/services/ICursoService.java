package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoRequest;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoResponse;

import java.util.List;

public interface ICursoService {

    public List<CursoResponse> listar();

    public CursoResponse cadastrar(final CursoRequest cursoRequest);

}
