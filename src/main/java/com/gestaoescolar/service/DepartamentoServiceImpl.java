package com.gestaoescolar.service;


import com.gestaoescolar.domain.Departamento;
import com.gestaoescolar.repositorio.DepartamentoRepositorio;
import com.gestaoescolar.service.map.DepartamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartamentoServiceImpl implements DepartamentoService {


    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    @Override
    public void registrarDepartamento(Departamento departamento) {
            departamentoRepositorio.save(departamento);
    }

    @Override
    public Departamento findById(Long id) {
        return departamentoRepositorio.findById(id).orElse(null);
    }

    @Override
    public Departamento encontarPorNome(String nome) {
        return departamentoRepositorio.findByNome(nome);
    }

    @Override
    public void apagarDepartamento(Long id) {
       if (departamentoRepositorio.existsById(id)){
            departamentoRepositorio.deleteById(id);
        }else {
            log.info("Recurso não Existe");
        }
    }

    @Override
    public List<Departamento> listarDepartmentos() {
        return departamentoRepositorio.findAll();
    }

}

