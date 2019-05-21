package com.gestaoescolar.service;

import com.gestaoescolar.domain.ProximoNumero;
import com.gestaoescolar.repositorio.ProximoNumeroRepositorio;
import com.gestaoescolar.service.map.ProximoNumeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProximoNumeroServiceImpl implements ProximoNumeroService {


    @Autowired
    private ProximoNumeroRepositorio proximoNumeroRepositorio;

    @Override
    public int getValueProfessor() {
        Long id= Long.valueOf(1);

        ProximoNumero proximoNumero = findById(id);

        return  proximoNumero.getNextNumb().get();
    }

    @Override
    public void incrementProfessor() {
        while(true) {

            Long id= Long.valueOf(1);
            ProximoNumero proximoNumero = findById(id);

            int existingValue = getValueProfessor();
            int newValue = existingValue + 1;

            if(proximoNumero.getNextNumb().compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    @Override
    public int getValueAluno() {
        Long id= Long.valueOf(2);

        ProximoNumero proximoNumero = findById(id);

        return  proximoNumero.getNextNumb().get();
    }

    @Override
    public void incrementAluno() {
        while(true) {

            Long id= Long.valueOf(2);
            com.gestaoescolar.domain.ProximoNumero proximoNumero = findById(id);

            int existingValue = getValueProfessor();
            int newValue = existingValue + 1;

            if(proximoNumero.getNextNumb().compareAndSet(existingValue, newValue)) {
                return;
            }
        }
    }

    @Override
    public ProximoNumero save(ProximoNumero proximoNumero) {
        return proximoNumeroRepositorio.save(proximoNumero);
    }

    @Override
    public ProximoNumero findById(Long id) {
        return proximoNumeroRepositorio.findById(id).orElse(null);
    }


}
