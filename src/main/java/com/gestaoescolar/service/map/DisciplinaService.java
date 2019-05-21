package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Disciplina;

import java.util.List;

public interface DisciplinaService {

    void registrarDisciplina(Disciplina departamento);

    Disciplina findById(Long id);

    void apagarDepartamento(Long id);

    List<Disciplina> listarDepartmentos();
}
