package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.security.Papel;
import org.springframework.data.repository.CrudRepository;

public interface PapelRepositorio extends CrudRepository<Papel,Long> {

    Papel findByPapel(String papel);
}
