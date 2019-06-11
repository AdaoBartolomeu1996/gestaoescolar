package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Curso;

import java.util.List;

public interface CursoService {

    Curso encontarPorNome(String nome);
    List<Curso> lisgtarCursos();
    void registrarCurso(Curso curso);
    void apagarCurso(Long id);
    Curso findById(Long id);
}
