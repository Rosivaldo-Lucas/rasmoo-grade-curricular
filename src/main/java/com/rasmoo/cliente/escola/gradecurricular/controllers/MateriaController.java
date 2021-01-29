package com.rasmoo.cliente.escola.gradecurricular.controllers;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    private final IMateriaService materiaService;

    public MateriaController(final IMateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseEntity<List<Materia>> listarMaterias() {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarMateria(@PathVariable final Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.buscar(id));
    }

    @PostMapping
    public ResponseEntity<Materia> cadastrarMateria(@RequestBody @Valid final MateriaDto materiaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.cadastrar(materiaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable final Long id, @RequestBody @Valid final MateriaDto materiaDto) {
        final Materia materiaAtualizada = this.materiaService.atualizar(id, materiaDto);

        return ResponseEntity.status(HttpStatus.OK).body(materiaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMateria(@PathVariable final Long id) {
        this.materiaService.deletar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
