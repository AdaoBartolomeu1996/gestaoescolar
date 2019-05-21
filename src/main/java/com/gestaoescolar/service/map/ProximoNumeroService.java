package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.ProximoNumero;

public interface ProximoNumeroService {

    ProximoNumero findById(Long id);

    ProximoNumero save(ProximoNumero proximoNumero);

    int getValueProfessor();

    void incrementProfessor();

    int getValueAluno();

    void incrementAluno();

}
