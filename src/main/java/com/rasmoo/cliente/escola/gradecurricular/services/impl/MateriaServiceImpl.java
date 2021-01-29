package com.rasmoo.cliente.escola.gradecurricular.services.impl;

import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.MateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            final Optional<Materia> materia = this.materiaRepository.findById(id);

            if (materia.isEmpty()) {
                throw new MateriaException("Materia não encontrada", HttpStatus.NOT_FOUND);
            }

            return materia.get();
        } catch (MateriaException materiaException) {
            throw materiaException;
        } catch (Exception exception) {
            throw new MateriaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Materia cadastrar(final MateriaDto materiaDto) {
        final ModelMapper modelMapper = new ModelMapper();

        final Materia materia = modelMapper.map(materiaDto, Materia.class);

        return this.materiaRepository.save(materia);
    }

    @Override
    public Materia atualizar(final Long id, final MateriaDto materiaDto) {
        try {
            final ModelMapper modelMapper = new ModelMapper();

            final Optional<Materia> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isEmpty()) {
                throw new MateriaException("Materia não encontrada", HttpStatus.NOT_FOUND);
            }

            materiaDto.setId(id);
            final Materia materiaAtualizada = modelMapper.map(materiaDto, Materia.class);

            return this.materiaRepository.save(materiaAtualizada);
        } catch (MateriaException materiaException) {
            throw materiaException;
        } catch (Exception exception) {
            throw new MateriaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deletar(final Long id) {
        try {
            final Materia materia = this.buscar(id);

            this.materiaRepository.delete(materia);
        } catch (MateriaException materiaException) {
            throw materiaException;
        } catch (Exception exception) {
            throw new MateriaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
