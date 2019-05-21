package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Disciplina;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DisciplinaRepositorio extends CrudRepository<Disciplina,Long> {

    Disciplina findByNome(String nome);

    void  deleteById(Long id);

    List<Disciplina> findAll();
}
