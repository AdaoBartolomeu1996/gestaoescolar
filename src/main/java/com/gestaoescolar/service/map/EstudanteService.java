package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Diretor;
import com.gestaoescolar.domain.Estudante;
import com.gestaoescolar.domain.Professor;

import java.util.List;

public interface EstudanteService {

    void inscrverEstdante(Estudante estudante);

    void atualizarEstudante(Estudante estudante);

    void matricularEstdante(Estudante estudante);

    void apagarInscricao(Long idEstidante);

    void apagarEstudante(Long idEstidante);

    Estudante procurarBilhete(String bilhete);

    List<Estudante> listarInscritos();

    Estudante procurarIdUsuario(Long id);
    Estudante findById(Long id);
}
