package com.rasmoo.cliente.escola.gradecurricular.controllers;

import com.rasmoo.cliente.escola.gradecurricular.constants.HyperLinkConstant;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoRequest;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoResponse;
import com.rasmoo.cliente.escola.gradecurricular.entities.Curso;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final ICursoService cursoService;

    @Autowired
    public CursoController(final ICursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public ResponseEntity<Response<List<CursoResponse>>> listarCursos() {
        final List<CursoResponse> cursosResponse = this.cursoService.listar();

        final Response<List<CursoResponse>> response = new Response<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(cursosResponse);

        //        response.add(WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).buscarCurso(cursoResponse.getId())).withSelfRel());
//
//        response.add(WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).atualizarCurso(cursoResponse.getId(), cursoRequest)).withRel(HyperLinkConstant.ATUALIZAR.getDescricao()));
//
//        response.add(WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).deletarCurso(cursoResponse.getId())).withRel(HyperLinkConstant.EXCLUIR.getDescricao()));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{idCurso}")
    public ResponseEntity<Response<CursoResponse>> buscarCurso(@PathVariable final Long idCurso) {
        final CursoResponse cursoResponse = this.cursoService.buscar(idCurso);

        final Response<CursoResponse> response = new Response<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(cursoResponse);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response<CursoResponse>> cadastrarCurso(@RequestBody @Valid final CursoRequest cursoRequest) {
        final CursoResponse cursoResponse = this.cursoService.cadastrar(cursoRequest);

        final Response<CursoResponse> response = new Response<>();
        response.setStatusCode(HttpStatus.CREATED.value());
        response.setData(cursoResponse);

//        response.add(WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).buscarCurso(cursoResponse.getId())).withSelfRel());
//
//        response.add(WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).atualizarCurso(cursoResponse.getId(), cursoRequest)).withRel(HyperLinkConstant.ATUALIZAR.getDescricao()));
//
//        response.add(WebMvcLinkBuilder
//                .linkTo(WebMvcLinkBuilder.methodOn(CursoController.class).deletarCurso(cursoResponse.getId())).withRel(HyperLinkConstant.EXCLUIR.getDescricao()));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
