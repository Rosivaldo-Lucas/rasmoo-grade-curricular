package com.rasmoo.cliente.escola.gradecurricular.repositories;

import com.rasmoo.cliente.escola.gradecurricular.entities.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

    @Query("SELECT m FROM Materia as m WHERE m.horas >= :horaMinima")
    public List<Materia> findByHoraMinima(@Param(value = "horaMinima") final int horaMinima);

}
