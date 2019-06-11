package com.gestaoescolar.service;

import com.gestaoescolar.domain.Curso;
import com.gestaoescolar.domain.Estudante;
import com.gestaoescolar.domain.enumEstado.EstadoEstudante;
import com.gestaoescolar.repositorio.EstudanteRepositorio;
import com.gestaoescolar.service.map.CursoService;
import com.gestaoescolar.service.map.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EstudanteServiceImpl implements EstudanteService {

    @Autowired
    private EstudanteRepositorio estudanteRepositorio;

    @Autowired
    private CursoService cursoService;

    @Override
    public void inscrverEstdante(Estudante estudante) {

        Curso curso = cursoService.findById(estudante.getCurso().getId());
        estudante.setEstadoEstudante(EstadoEstudante.INSCRITO);
        estudante.setCurso(curso);
        estudanteRepositorio.save(estudante);
    }

    @Override
    public void atualizarEstudante(Estudante estudante){

      Curso curso = cursoService.findById(estudante.getCurso().getId());

      estudante.setCurso(curso);
      estudanteRepositorio.save(estudante);
    }

    @Override
    public void matricularEstdante(Estudante estudante) {}

    @Override
    public void apagarInscricao(Long idEstidante) {
        if (estudanteRepositorio.existsById(idEstidante)){
            estudanteRepositorio.deleteById(idEstidante);
        }
    }

    @Override
    public void apagarEstudante(Long idEstidante) {}

    @Override
    public Estudante procurarBilhete(String bilhete) {
        return estudanteRepositorio.findByBilhete(bilhete);
    }

    @Override
    public List<Estudante> listarInscritos() {
        return estudanteRepositorio.findAllByEstadoEstudante(EstadoEstudante.INSCRITO);
    }

    @Override
    public Estudante procurarIdUsuario(Long id) {
        return estudanteRepositorio.findByUsuario_Id(id);
    }

    @Override
    public Estudante findById(Long id) {
        return estudanteRepositorio.findById(id).orElse(null);
    }
}
