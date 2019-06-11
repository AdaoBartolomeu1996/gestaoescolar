package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Diretor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiretorRepositorio extends CrudRepository<Diretor,Long> {

    List<Diretor> findAll();

    @Query("SELECT COUNT(d) FROM Diretor d")
    Long countAll();

}
