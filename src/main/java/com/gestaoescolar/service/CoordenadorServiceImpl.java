package com.gestaoescolar.service;

import com.gestaoescolar.domain.CoordenadorCurso;
import com.gestaoescolar.domain.Curso;
import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.domain.security.Papel;
import com.gestaoescolar.repositorio.CoordenadorCursoRepositorio;
import com.gestaoescolar.repositorio.PapelRepositorio;
import com.gestaoescolar.repositorio.UsuarioRepositorio;
import com.gestaoescolar.service.map.CoordenadorCursoService;
import com.gestaoescolar.service.map.CursoService;
import com.gestaoescolar.service.map.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class CoordenadorServiceImpl implements CoordenadorCursoService {

    @Autowired
    private CoordenadorCursoRepositorio coordenadorCursoRepositorio;

    @Autowired
    private ProfessorService professorRepositorio;

    @Autowired

    private CursoService cursoService;

    @Autowired
    private UsuarioRepositorio usuarioService;

    @Autowired
    private PapelRepositorio papelRepositorio;

    @Override
    public void registrarCordenador(CoordenadorCurso coordenadorCurso) {

        String url = null;
        Professor professor = professorRepositorio.findById(coordenadorCurso.getProfessor().getId());
        Usuario usuario = usuarioService.findByUsername(professor.getUsuario().getUsername());

            Papel papel = papelRepositorio.findByPapel("ROLE_PROFESSOR");
            usuario.clearPapel(papel);
            usuarioService.save(usuario);

            Papel pap = papelRepositorio.findByPapel("ROLE_COORDENADOR");
            usuario.addPapel(pap);
            usuarioService.save(usuario);

            coordenadorCurso.setProfessor(professor);
            coordenadorCursoRepositorio.save(coordenadorCurso);

            professor.setEstado(true);
            professorRepositorio.atualizarProfessor(professor,url);
    }

    @Override
    public void atualizarCoordenador(CoordenadorCurso coordenadorCurso) {

        CoordenadorCurso coordenador = peocurarCurso(coordenadorCurso.getCurso().getId());

        Curso curso = cursoService.findById(coordenador.getId());
        Professor professor= professorRepositorio.findById(coordenador.getProfessor().getId());

        coordenador.setProfessor(professor);
        coordenador.setCurso(curso);
        coordenadorCursoRepositorio.save(coordenador);
    }

    @Override
    public void apagarCordenador(Long idDiretor) {

        String url = null;

        CoordenadorCurso coordenadorCurso = findById(idDiretor);

        Professor professor = professorRepositorio.findById(coordenadorCurso.getProfessor().getId());

        Usuario usuario = usuarioService.findByUsername(professor.getUsuario().getUsername());

        log.info("USERNAME: "+usuario.getUsername());
        if (coordenadorCurso.getId()!=null){

            Papel papel = papelRepositorio.findByPapel("ROLE_COORDENADOR");
            usuario.clearPapel(papel);
            usuarioService.save(usuario);

             Papel pap = papelRepositorio.findByPapel("ROLE_PROFESSOR");
            usuario.addPapel(pap);
            usuarioService.save(usuario);

            coordenadorCurso.setProfessor(professor);
            coordenadorCursoRepositorio.save(coordenadorCurso);

            coordenadorCursoRepositorio.deleteById(idDiretor);

            professor.setEstado(false);
            professorRepositorio.atualizarProfessor(professor,url);
        }
    }

    @Override
    public CoordenadorCurso procrarProfessor(Long id) {
        return coordenadorCursoRepositorio.findByProfessor_Id(id);
    }

    @Override
    public CoordenadorCurso peocurarCurso(Long idCurso) {
        return coordenadorCursoRepositorio.findByCurso_Id(idCurso);
    }

    @Override
    public List<CoordenadorCurso> listarCoordenadores() {
        return coordenadorCursoRepositorio.findAll();
    }

    @Override
    public Long count() {
        return coordenadorCursoRepositorio.countAll();
    }

    @Override
    public CoordenadorCurso findById(Long id) {
        return coordenadorCursoRepositorio.findById(id).orElse(null);
    }
}
