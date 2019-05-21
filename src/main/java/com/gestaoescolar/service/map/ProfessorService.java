package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.ProfessorTelefone;

import java.util.List;

public interface ProfessorService {

    void registrarProfessor(Professor professor, ProfessorTelefone telefone);

    Professor findById(Long id);

    Professor procurarEmail(String emai);

    Professor procuararNumeroProfessor(String numeroProfessor);

    Professor procurarBilhete(String bilhete);

    void apagarProfessor(Long id);

    List<Professor> listarProfessores();
}
