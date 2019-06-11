package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Curso;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CursoRepositorio extends CrudRepository<Curso,Long> {

    Curso findByNome(String nome);
    List<Curso> findAll();
}
