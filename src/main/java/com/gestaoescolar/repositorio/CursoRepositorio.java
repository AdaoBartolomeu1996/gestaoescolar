package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepositorio extends CrudRepository<Curso,Long> {
}
