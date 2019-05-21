package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Estudante;
import org.springframework.data.repository.CrudRepository;

public interface EstudanteRepositorio extends CrudRepository<Estudante,Long> {
}
