package com.gestaoescolar.service;

import com.gestaoescolar.domain.Diretor;
import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.domain.security.Papel;
import com.gestaoescolar.repositorio.DiretorRepositorio;
import com.gestaoescolar.repositorio.PapelRepositorio;
import com.gestaoescolar.repositorio.ProfessorRepositorio;
import com.gestaoescolar.repositorio.UsuarioRepositorio;
import com.gestaoescolar.service.map.DiretorService;
import com.gestaoescolar.service.map.ProfessorService;
import com.gestaoescolar.service.map.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@Service
public class DiretorServiceImpl implements DiretorService {

    @Autowired
    private DiretorRepositorio diretorRepositorio;

    @Autowired
    private ProfessorService professorRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioService;

    @Autowired
    private PapelRepositorio papelRepositorio;

    @Override
    public void registrarDiretor(Long idprofessor) {
        String url = null;
        Diretor diretor = new Diretor();

        Professor professor = professorRepositorio.findById(idprofessor);

        Usuario usuario = usuarioService.findByUsername(professor.getUsuario().getUsername());

        if (professor!=null){

            Papel papel = papelRepositorio.findByPapel("ROLE_PROFESSOR");
            usuario.clearPapel(papel);
            usuarioService.save(usuario);

            Papel pap = papelRepositorio.findByPapel("ROLE_DIRETOR");
            usuario.addPapel(pap);
            usuarioService.save(usuario);

            diretor.setProfessor(professor);
            diretorRepositorio.save(diretor);

            professor.setEstado(true);
            professorRepositorio.atualizarProfessor(professor,url);
        }
    }

    @Override
    public void apagarDiretor(Long idDiretor) {
        String url = null;
        Diretor diretor = findById(idDiretor);

        Professor professor = professorRepositorio.findById(diretor.getProfessor().getId());

        Usuario usuario = usuarioService.findByUsername(professor.getUsuario().getUsername());

        if (diretor.getId()!=null){

            Papel papel = papelRepositorio.findByPapel("ROLE_DIRETOR");
            usuario.clearPapel(papel);
            usuarioService.save(usuario);

            Papel pap = papelRepositorio.findByPapel("ROLE_PROFESSOR");
            usuario.addPapel(pap);
            usuarioService.save(usuario);

            diretor.setProfessor(professor);
            diretorRepositorio.save(diretor);

            diretorRepositorio.deleteById(idDiretor);

            professor.setEstado(false);
            professorRepositorio.atualizarProfessor(professor,url);
        }
    }

    @Override
    public Diretor findById(Long id) {
        return diretorRepositorio.findById(id).orElse(null);
    }

    @Override
    public Long count() {
        return diretorRepositorio.count();
    }

    @Override
    public List<Diretor> listarDiretores() {
        return diretorRepositorio.findAll();
    }
}
