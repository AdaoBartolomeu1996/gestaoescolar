package com.gestaoescolar.repositorio;

import com.gestaoescolar.domain.Departamento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartamentoRepositorio extends CrudRepository<Departamento,Long> {

    Departamento findByNome(String nome);
    void  deleteById(Long id);

    List<Departamento> findAll();
}
