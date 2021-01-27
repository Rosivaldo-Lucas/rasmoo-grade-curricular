package com.rasmoo.cliente.escola.gradecurricular.services.impl;

import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.repositories.MateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServiceImpl implements IMateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaServiceImpl(final MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    @Override
    public List<Materia> listar() {
        return this.materiaRepository.findAll();
    }

    @Override
    public Materia buscar(final Long id) {
        try {
            return this.materiaRepository.findById(id).get();
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public Materia cadastrar(final Materia materia) {
        return this.materiaRepository.save(materia);
    }

    @Override
    public Materia atualizar(final Long id, final Materia materia) {
        try {
            final Materia materiaSalva = this.materiaRepository.findById(id).get();

            materiaSalva.setNome(materia.getNome());
            materiaSalva.setCodigo(materia.getCodigo());
            materiaSalva.setFrequencia(materia.getFrequencia());
            materiaSalva.setHoras(materia.getHoras());

            return this.materiaRepository.save(materiaSalva);
        } catch (Exception exception) {
            return null;
        }
    }

    @Override
    public void deletar(final Long id) {
        try {
            final Materia materia = this.materiaRepository.findById(id).get();

            this.materiaRepository.delete(materia);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
