package com.rasmoo.cliente.escola.gradecurricular.services.impl;

import com.rasmoo.cliente.escola.gradecurricular.dto.CursoRequest;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoResponse;
import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.Curso;
import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.CursoRepository;
import com.rasmoo.cliente.escola.gradecurricular.services.ICursoService;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.rasmoo.cliente.escola.gradecurricular.constants.MensagensError.MENSAGEM_ERRO_INTERNO;

@Service
public class CursoServiceImpl implements ICursoService {

    private final CursoRepository cursoRepository;

    private final IMateriaService materiaService;

    private final ModelMapper modelMapper;

    @Autowired
    public CursoServiceImpl(final CursoRepository cursoRepository, final IMateriaService materiaService) {
        this.cursoRepository = cursoRepository;
        this.materiaService = materiaService;

        this.modelMapper = new ModelMapper();
    }

    @Override
    public List<CursoResponse> listar() {
        try {
            final List<Curso> cursos = this.cursoRepository.findAll();

            return this.modelMapper.map(cursos, new TypeToken<List<CursoResponse>>() {}.getType());
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public CursoResponse cadastrar(final CursoRequest cursoRequest) {
        final List<Materia> materias = this.obterMaterias(cursoRequest);

        final Curso novoCurso = new Curso();
        novoCurso.setNome(cursoRequest.getNome());
        novoCurso.setCodigo(cursoRequest.getCodigo());
        novoCurso.setMaterias(materias);

        final Curso cursoSalvo = this.cursoRepository.save(novoCurso);

        final CursoResponse cursoResponse = new CursoResponse();
        cursoResponse.setId(cursoSalvo.getId());
        cursoResponse.setNome(cursoSalvo.getNome());
        cursoResponse.setCodigo(cursoSalvo.getCodigo());
        cursoResponse.setMaterias(this.modelMapper.map(cursoSalvo.getMaterias(), new TypeToken<List<MateriaDto>>() {}.getType()));

        return cursoResponse;
    }

    private List<Materia> obterMaterias(final CursoRequest cursoRequest) {
        final List<Materia> materias = new ArrayList<>();

        cursoRequest.getMaterias().forEach((materia) -> {
            final MateriaDto materiaDto = this.materiaService.buscar(materia.getId());

            materias.add(this.modelMapper.map(materiaDto, Materia.class));
        });

        return materias;
    }

}
