package com.gestaoescolar.boostrap;

import com.gestaoescolar.domain.ProximoNumero;
import com.gestaoescolar.repositorio.ProximoNumeroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class Imp implements CommandLineRunner {

    @Autowired
    private ProximoNumeroRepositorio proximoNumeroRepositorio;

    @Override
    public void run(String... args) throws Exception {

        AtomicInteger nextNumber = new AtomicInteger(1);


        if (proximoNumeroRepositorio.count()>0)
            return;

        ProximoNumero proximoNumeroProf = new com.gestaoescolar.domain.ProximoNumero();
        proximoNumeroProf.setNextNumb(nextNumber);

        proximoNumeroRepositorio.save(proximoNumeroProf);

        ProximoNumero proximoNumeroAluno = new com.gestaoescolar.domain.ProximoNumero();
        proximoNumeroAluno.setNextNumb(nextNumber);

        proximoNumeroRepositorio.save(proximoNumeroAluno);


    }
}
