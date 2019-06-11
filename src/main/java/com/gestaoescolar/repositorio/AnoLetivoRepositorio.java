package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.AnoLetivo;
import com.gestaoescolar.domain.enumEstado.EstadoAnoLetivo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnoLetivoRepositorio extends CrudRepository<AnoLetivo,Long> {

    AnoLetivo findByAnoAbertura(String ano);

    List<AnoLetivo> findAll();

    List<AnoLetivo> findAllByAnoLetivo(EstadoAnoLetivo estadoAnoLetivo);
}
