package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Professor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessorRepositorio extends CrudRepository<Professor,Long> {

    Professor findByUsuario_Id(Long id);


    List<Professor> findAll();

    List<Professor> findByEstado(boolean estado);

    Professor findByBilhete(String bilhete);

    Professor findByEmail(String email);
}
