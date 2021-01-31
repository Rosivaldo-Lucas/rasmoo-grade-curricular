package com.rasmoo.cliente.escola.gradecurricular.controllers;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.model.Response;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    private static final String DELETE = "DELETE";
    private static final String UPDATE = "UPDATE";

    private final IMateriaService materiaService;

    public MateriaController(final IMateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>>> listarMaterias() {
        final Response<List<MateriaDto>> response = new Response<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(this.materiaService.listar());

        response.add(WebMvcLinkBuilder.
                linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listarMaterias()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MateriaDto>> buscarMateria(@PathVariable final Long id) {
        final MateriaDto materiaDto = this.materiaService.buscar(id);

        final Response<MateriaDto> response = new Response<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setData(materiaDto);

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).buscarMateria(id)).withSelfRel());

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).atualizarMateria(id, materiaDto)).withRel(UPDATE));

        response.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).deletarMateria(id)).withRel(DELETE));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<MateriaDto> cadastrarMateria(@RequestBody @Valid final MateriaDto materiaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.cadastrar(materiaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDto> atualizarMateria(@PathVariable final Long id, @RequestBody @Valid final MateriaDto materiaDto) {
        final MateriaDto materiaAtualizada = this.materiaService.atualizar(id, materiaDto);

        return ResponseEntity.status(HttpStatus.OK).body(materiaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMateria(@PathVariable final Long id) {
        this.materiaService.deletar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
