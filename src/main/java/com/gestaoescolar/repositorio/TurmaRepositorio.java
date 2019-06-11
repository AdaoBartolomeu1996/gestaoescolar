package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Disciplina;
import com.gestaoescolar.domain.Turma;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TurmaRepositorio extends CrudRepository<Turma,Long> {

    Turma findByNome(String nome);

    List<Turma> findByCurso_CoordenadorCurso_Id(Long id);

    void  deleteById(Long id);

    List<Turma> findAll();
}
