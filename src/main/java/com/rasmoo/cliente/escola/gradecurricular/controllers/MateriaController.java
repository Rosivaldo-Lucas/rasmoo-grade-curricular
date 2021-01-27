package com.rasmoo.cliente.escola.gradecurricular.controllers;

import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.repositories.MateriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaRepository materiaRepository;

    public MateriaController(final MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Materia>> listarMaterias() {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Materia> buscarMateria(@PathVariable final Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.materiaRepository.findById(id).get());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Materia> cadastrarMateria(@RequestBody final Materia materia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaRepository.save(materia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Materia> atualizarMateria(@PathVariable final Long id, @RequestBody final Materia materia) {
        try {
            final Materia materiaSalva = this.materiaRepository.findById(id).get();

            materiaSalva.setNome(materia.getNome());
            materiaSalva.setCodigo(materia.getCodigo());
            materiaSalva.setFrequencia(materia.getFrequencia());
            materiaSalva.setHoras(materia.getHoras());

            final Materia materiaAtualizada = this.materiaRepository.save(materiaSalva);

            return ResponseEntity.status(HttpStatus.OK).body(materiaAtualizada);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMateria(@PathVariable final Long id) {
        try {
            final Materia materia = this.materiaRepository.findById(id).get();

            this.materiaRepository.delete(materia);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
