package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.CoordenadorCurso;
import com.gestaoescolar.domain.Professor;


import java.util.List;

public interface CoordenadorCursoService {


    void registrarCordenador(CoordenadorCurso coordenadorCurso);

    void atualizarCoordenador(CoordenadorCurso coordenadorCurso);
    CoordenadorCurso procrarProfessor(Long id);

    CoordenadorCurso peocurarCurso(Long idCurso);

    void apagarCordenador(Long idCordenador);

    List<CoordenadorCurso> listarCoordenadores();

    Long count();

    CoordenadorCurso findById(Long id);
}
