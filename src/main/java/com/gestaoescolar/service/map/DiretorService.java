package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Diretor;

import java.util.List;

public interface DiretorService {

    void registrarDiretor(Long idProfessor);

    void apagarDiretor(Long idDiretor);

    List<Diretor> listarDiretores();

    Long count();

    Diretor findById(Long id);
}
