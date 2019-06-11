package com.gestaoescolar.service;

import com.gestaoescolar.domain.Curso;
import com.gestaoescolar.domain.Turma;
import com.gestaoescolar.repositorio.TurmaRepositorio;
import com.gestaoescolar.service.map.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaServiceImpl implements TurmaService {

    @Autowired
    private TurmaRepositorio turmaRepositorio;

    @Override
    public Turma encontarPorNome(String nome) {
        return turmaRepositorio.findByNome(nome);
    }

    @Override
    public List<Turma> lisgtarTurma() {
        return turmaRepositorio.findAll();
    }

    @Override
    public List<Turma> listarTurmaCursoCoordenadorId(Long id) {
        return turmaRepositorio.findByCurso_CoordenadorCurso_Id(id);
    }

    @Override
    public void registrarTurma(Turma turma) {
            turmaRepositorio.save(turma);
    }

    @Override
    public void apagarTurma(Long id) {
            if (turmaRepositorio.existsById(id)){
                turmaRepositorio.deleteById(id);
            }
    }

    @Override
    public Turma findById(Long id) {
        return turmaRepositorio.findById(id).orElse(null);
    }
}
