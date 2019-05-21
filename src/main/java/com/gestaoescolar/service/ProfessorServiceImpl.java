package com.gestaoescolar.service;

import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.ProfessorTelefone;
import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.repositorio.PapelRepositorio;
import com.gestaoescolar.repositorio.ProfessorRepositorio;
import com.gestaoescolar.repositorio.ProfessorTelefoneRepositorio;
import com.gestaoescolar.repositorio.UsuarioRepositorio;
import com.gestaoescolar.service.map.ProfessorService;
import com.gestaoescolar.service.map.ProximoNumeroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
public class ProfessorServiceImpl implements ProfessorService {



    @Autowired
    private ProximoNumeroService proximoNumeroService;

    @Autowired
    private ProfessorRepositorio professorRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ProfessorTelefoneRepositorio professorTelefoneRepositorio;

    @Autowired
    private PapelRepositorio papelRepositorio;

    @Override
    public void registrarProfessor(Professor professor,  ProfessorTelefone telefone) {

        log.info("Professor"+professor.toString());

        String numero= "IMEL"+proximoNumeroService.getValueProfessor()+"";

        log.info("IMEL: "+numero);

        proximoNumeroService.incrementProfessor();

        //Papel papel =  papelRepositorio.findByPapel("ROLE_PROFESSOR");

        Usuario usuario = new Usuario();

        usuario.setUsername(numero);

        usuario.setPassword(professor.getBilhete());
        usuarioRepositorio.save(usuario);

        professor.setUsuario(usuario);
        professor.setNumeroProfessor(numero);
        professorRepositorio.save(professor);

        telefone.setProfessor(professor);

        professorTelefoneRepositorio.save(telefone);


    }

    @Override
    public Professor procurarBilhete(String bilhete) {
        return professorRepositorio.findByBilhete(bilhete);
    }

    @Override
    public Professor findById(Long id) {
        return professorRepositorio.findById(id).orElse(null);
    }

    @Override
    public Professor procurarEmail(String emai) {
        return professorRepositorio.findByEmail(emai);
    }

    @Override
    public Professor procuararNumeroProfessor(String numeroProfessor) {
        return professorRepositorio.findByEmail(numeroProfessor);
    }

    @Override
    public void apagarProfessor(Long id) {
        if (professorRepositorio.existsById(id)){
            professorRepositorio.deleteById(id);
        }
    }

    @Override
    public List<Professor> listarProfessores() {
        return professorRepositorio.findAll();
    }


}
