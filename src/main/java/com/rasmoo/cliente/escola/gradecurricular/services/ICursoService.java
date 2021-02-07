package com.rasmoo.cliente.escola.gradecurricular.services;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoRequest;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoResponse;

public interface ICursoService {

    public CursoResponse cadastrar(final CursoRequest cursoRequest);

}
