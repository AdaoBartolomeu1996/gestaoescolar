package com.gestaoescolar.service;

import com.gestaoescolar.domain.Curso;
import com.gestaoescolar.repositorio.CursoRepositorio;
import com.gestaoescolar.service.map.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {


    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Override
    public Curso encontarPorNome(String nome) {
        return cursoRepositorio.findByNome(nome);
    }

    @Override
    public void registrarCurso( Curso curso) {
        cursoRepositorio.save(curso);
    }

    @Override
    public List<Curso> lisgtarCursos() {
        return cursoRepositorio.findAll();
    }

    @Override
    public Curso findById(Long id) {
        return cursoRepositorio.findById(id).orElse(null);
    }

    @Override
    public void apagarCurso(Long id) {
        if (cursoRepositorio.existsById(id)){
            cursoRepositorio.deleteById(id);
        }
    }
}
