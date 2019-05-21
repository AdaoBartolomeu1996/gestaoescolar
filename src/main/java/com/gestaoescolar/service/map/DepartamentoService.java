package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService {

    void registrarDepartamento(Departamento departamento);

    Departamento findById(Long id);

    void apagarDepartamento(Long id);

    List<Departamento> listarDepartmentos();

    Departamento encontarPorNome(String nome);
}
