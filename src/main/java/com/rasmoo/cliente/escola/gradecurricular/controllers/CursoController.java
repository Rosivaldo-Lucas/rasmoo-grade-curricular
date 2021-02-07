package com.rasmoo.cliente.escola.gradecurricular.controllers;

import com.rasmoo.cliente.escola.gradecurricular.constants.HyperLinkConstant;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoRequest;
import com.rasmoo.cliente.escola.gradecurricular.dto.CursoResponse;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.services.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final ICursoService cursoService;

    @Autowired
    public CursoController(final ICursoService cursoService) {
        this.cursoService = cursoService;
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
