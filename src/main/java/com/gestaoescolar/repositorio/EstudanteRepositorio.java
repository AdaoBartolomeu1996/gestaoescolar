package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Estudante;
import com.gestaoescolar.domain.enumEstado.EstadoEstudante;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudanteRepositorio extends CrudRepository<Estudante,Long> {

    List<Estudante> findAllByEstadoEstudante(EstadoEstudante estadoEstudante);

    Estudante findByBilhete(String bi);

    Estudante findByUsuario_Id(Long id);
}
