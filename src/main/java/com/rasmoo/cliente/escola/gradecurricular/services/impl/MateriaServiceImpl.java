package com.rasmoo.cliente.escola.gradecurricular.services.impl;

import com.rasmoo.cliente.escola.gradecurricular.controllers.MateriaController;
import com.rasmoo.cliente.escola.gradecurricular.dto.MateriaDto;
import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repositories.MateriaRepository;
import com.rasmoo.cliente.escola.gradecurricular.services.IMateriaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rasmoo.cliente.escola.gradecurricular.constants.MensagensError.*;

@CacheConfig(cacheNames = { "materia" })
@Service
public class MateriaServiceImpl implements IMateriaService {

    private final MateriaRepository materiaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MateriaServiceImpl(final MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
        this.modelMapper = new ModelMapper();
    }

    @CachePut(unless = "#result.size() < 3")
    @Override
    public List<MateriaDto> listar() {
        try {
            final List<Materia> materias = this.materiaRepository.findAll();

            return this.adicionarLinks(materias);
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MateriaDto> listarPorHoraMinima(final int horaMinima) {
        try {
            final List<Materia> materias = this.materiaRepository.findByHoraMinima(horaMinima);

            return this.adicionarLinks(materias);
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<MateriaDto> listarPorFrequencia(final int frequencia) {
        try {
            final List<Materia> materias = this.materiaRepository.findByFrequencia(frequencia);

            return this.adicionarLinks(materias);
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut(value = "materia", key = "#id")
    @Override
    public MateriaDto buscar(final Long id) {
        try {
            final Optional<Materia> materia = this.materiaRepository.findById(id);

            if (materia.isEmpty()) {
                throw new MateriaException(MENSAGEM_ERRO_MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
            }

            return this.modelMapper.map(materia.get(), MateriaDto.class);
        } catch (MateriaException materiaException) {
            throw materiaException;
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MateriaDto cadastrar(final MateriaDto materiaDto) {
        try {
            final Materia materia = this.modelMapper.map(materiaDto, Materia.class);

            final Materia materiaSalva = this.materiaRepository.save(materia);

            return this.modelMapper.map(materiaSalva, MateriaDto.class);
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MateriaDto atualizar(final Long id, final MateriaDto materiaDto) {
        try {
            final Optional<Materia> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isEmpty()) {
                throw new MateriaException(MENSAGEM_ERRO_MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
            }

            materiaDto.setId(id);
            final Materia materiaAtualizada = this.modelMapper.map(materiaDto, Materia.class);

            final Materia materiaSalvaAtualizada = this.materiaRepository.save(materiaAtualizada);

            return this.modelMapper.map(materiaSalvaAtualizada, MateriaDto.class);
        } catch (MateriaException materiaException) {
            throw materiaException;
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deletar(final Long id) {
        try {
            final MateriaDto materiaDto = this.buscar(id);

            final Materia materia = this.modelMapper.map(materiaDto, Materia.class);

            this.materiaRepository.delete(materia);
        } catch (MateriaException materiaException) {
            throw materiaException;
        } catch (Exception exception) {
            throw new MateriaException(MENSAGEM_ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<MateriaDto> adicionarLinks(final List<Materia> materias) {
        final List<MateriaDto> materiasDto = toMateriasDto(materias);

        materiasDto.forEach((materia) -> materia.add(
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).buscarMateria(materia.getId())).withSelfRel()));

        return materiasDto;
    }

    private List<MateriaDto> toMateriasDto(final List<Materia> materias) {
        return this.modelMapper.map(materias, new TypeToken<List<MateriaDto>>() {}.getType());
    }

}
