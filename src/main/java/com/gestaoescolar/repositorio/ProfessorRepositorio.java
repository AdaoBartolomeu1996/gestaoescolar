package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Professor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessorRepositorio extends CrudRepository<Professor,Long> {

    Professor findByNumeroProfessor(String numero);

    List<Professor> findAll();

    Professor findByBilhete(String bilhete);

    Professor findByEmail(String email);
}
