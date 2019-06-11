package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Curso;
import com.gestaoescolar.domain.Turma;

import java.util.List;

public interface TurmaService {

    Turma encontarPorNome(String nome);

    List<Turma> lisgtarTurma();

    List<Turma> listarTurmaCursoCoordenadorId(Long id);

    void registrarTurma(Turma turma);

    void apagarTurma(Long id);

    Turma findById(Long id);
}
