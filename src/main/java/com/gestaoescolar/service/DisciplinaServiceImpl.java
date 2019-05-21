package com.gestaoescolar.service;

import com.gestaoescolar.domain.Disciplina;
import com.gestaoescolar.repositorio.DisciplinaRepositorio;
import com.gestaoescolar.service.map.DisciplinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class DisciplinaServiceImpl implements DisciplinaService {




    @Autowired
    private DisciplinaRepositorio disciplinaRepositorio;

    @Override
    public void registrarDisciplina(Disciplina departamento) {
        disciplinaRepositorio.save(departamento);
    }

    @Override
    public Disciplina findById(Long id) {
        return disciplinaRepositorio.findById(id).orElse(null);
    }

    @Override
    public void apagarDepartamento(Long id) {
        if (disciplinaRepositorio.existsById(id)){
            disciplinaRepositorio.deleteById(id);
        }else {
            log.info("Recurso não Existe");
        }
    }

    @Override
    public List<Disciplina> listarDepartmentos() {
        return disciplinaRepositorio.findAll();
    }



}
