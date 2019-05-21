package com.gestaoescolar.repositorio;


import com.gestaoescolar.domain.ProximoNumero;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public interface ProximoNumeroRepositorio extends CrudRepository<com.gestaoescolar.domain.ProximoNumero,Long> {

    Optional<ProximoNumero> findById(Long id);

   ProximoNumero findByNextNumb(AtomicInteger numero);

}
