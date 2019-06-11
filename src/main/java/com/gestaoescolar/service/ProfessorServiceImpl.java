package com.gestaoescolar.service;

import com.gestaoescolar.domain.Foto;
import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.domain.security.Papel;
import com.gestaoescolar.repositorio.FotoRepositorio;
import com.gestaoescolar.repositorio.PapelRepositorio;
import com.gestaoescolar.repositorio.ProfessorRepositorio;
import com.gestaoescolar.repositorio.UsuarioRepositorio;
import com.gestaoescolar.service.map.ProfessorService;
import com.gestaoescolar.service.map.ProximoNumeroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private FotoRepositorio fotoRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PapelRepositorio papelRepositorio;

    @Override
    public void registrarProfessor(Professor professor,String url) {

        log.info("Professor"+professor.toString());

        String numero= "IMELP"+proximoNumeroService.getValueProfessor()+"";

        log.info("IMELP: "+numero);

        proximoNumeroService.incrementProfessor();

        Papel papel =  papelRepositorio.findByPapel("ROLE_PROFESSOR");

        Usuario usuario = new Usuario();

        usuario.addPapel(papel);
        usuario.setUsername(numero);

        usuario.setPassword(passwordEncoder.encode(professor.getBilhete()));
        usuarioRepositorio.save(usuario);

        Foto foto = new Foto();
        foto.setFotoUrl(url);
        foto.setUsuario(usuario);
        fotoRepositorio.save(foto);

        professor.setUsuario(usuario);
        professor.setNumeroProfessor(numero);
        professorRepositorio.save(professor);
    }

    @Override
    public void atualizarProfessor(Professor professor,String url) {

        Professor setUsuario= findById(professor.getId());

        if (url == null){
            Usuario usuario = usuarioRepositorio.findByUsername(setUsuario.getUsuario().getUsername());
            Foto foto = fotoRepositorio.findByUsuario_Id(usuario.getId());
            foto.setUsuario(usuario);
            fotoRepositorio.save(foto);
            professor.setNumeroProfessor(usuario.getUsername());
            professor.setUsuario(usuario);
            professorRepositorio.save(professor);

        }else {
            Usuario usuario = usuarioRepositorio.findByUsername(setUsuario.getUsuario().getUsername());
            Foto foto = fotoRepositorio.findByUsuario_Id(usuario.getId());
            foto.setUsuario(usuario);
            foto.setFotoUrl(url);
            fotoRepositorio.save(foto);
            professor.setNumeroProfessor(usuario.getUsername());
            professor.setUsuario(usuario);
            professorRepositorio.save(professor);
        }
  }


    @Override
    public List<Professor> listarProfessoresEstado() {
        boolean estado= false;
        return professorRepositorio.findByEstado(estado);
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
    public void apagarProfessor(Long id) {
        Professor professor= findById(id);
        if (professor!=null){
            Usuario usuario = professor.getUsuario();
            if (usuario!=null){
                usuarioRepositorio.delete(usuario);
            }
        }
    }

    @Override
    public List<Professor> listarProfessores() {
        return professorRepositorio.findAll();
    }


    @Override
    public Professor procurarUsuarioId(Long id) {
        return professorRepositorio.findByUsuario_Id(id);
    }
}
