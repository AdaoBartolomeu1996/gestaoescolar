package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.CoordenadorCurso;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoordenadorCursoRepositorio extends CrudRepository<CoordenadorCurso,Long> {

    List<CoordenadorCurso> findAll();

    CoordenadorCurso findByProfessor_Id(Long id);

    CoordenadorCurso findByCurso_Id(Long idCurso);

    @Query("SELECT COUNT(c) FROM CoordenadorCurso c")
    Long countAll();
}
