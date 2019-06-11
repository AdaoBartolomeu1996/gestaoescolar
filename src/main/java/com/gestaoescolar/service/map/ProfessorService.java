package com.gestaoescolar.service.map;

import com.gestaoescolar.domain.Professor;

import java.util.List;

public interface ProfessorService {

    void registrarProfessor(Professor professor,String url);

    void atualizarProfessor(Professor professor,String url);

    Professor procurarUsuarioId(Long id);

    Professor findById(Long id);

    List<Professor> listarProfessoresEstado();

    Professor procurarEmail(String emai);

    Professor procurarBilhete(String bilhete);

    void apagarProfessor(Long id);

    List<Professor> listarProfessores();
}
