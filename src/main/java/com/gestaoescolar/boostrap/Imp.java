package com.gestaoescolar.boostrap;

import com.gestaoescolar.domain.Estudante;
import com.gestaoescolar.domain.Foto;
import com.gestaoescolar.domain.ProximoNumero;
import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.domain.security.Papel;
import com.gestaoescolar.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class Imp implements CommandLineRunner {

    @Autowired
    private ProximoNumeroRepositorio proximoNumeroRepositorio;

    @Autowired
    private EstudanteRepositorio estudanteRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncodern;

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PapelRepositorio papelRepositorio;

    @Override
    public void run(String... args) throws Exception {

        if (proximoNumeroRepositorio.count()>0)
            return;

        AtomicInteger nextNumber = new AtomicInteger(1);

        Papel admin = new Papel();
        admin.setPapel("ROLE_ADIMIN");
        papelRepositorio.save(admin);

        Papel professor = new Papel();
        professor.setPapel("ROLE_PROFESSOR");
        papelRepositorio.save(professor);

        Papel coordenador = new Papel();
        coordenador.setPapel("ROLE_COORDENADOR");
        papelRepositorio.save(coordenador);

        Papel diretor = new Papel();
        diretor.setPapel("ROLE_DIRETOR");
        papelRepositorio.save(diretor);

        Papel candidato = new Papel();
        candidato.setPapel("ROLE_CANDIDATO");
        papelRepositorio.save(candidato);

        Papel matricula = new Papel();
        matricula.setPapel("ROLE_MATRICULA");
        papelRepositorio.save(matricula);

        Papel estuante = new Papel();
        estuante.setPapel("ROLE_ESTUDANTE");
        papelRepositorio.save(estuante);

        ProximoNumero proximoNumeroProf = new ProximoNumero();
        proximoNumeroProf.setNextNumb(nextNumber);
        proximoNumeroRepositorio.save(proximoNumeroProf);

        ProximoNumero proximoNumeroAluno = new ProximoNumero();
        proximoNumeroAluno.setNextNumb(nextNumber);
        proximoNumeroRepositorio.save(proximoNumeroAluno);


        Usuario administrador = new Usuario();
        administrador.setPassword(passwordEncodern.encode("admin"));
        administrador.setUsername("admin");
        administrador.addPapel(admin);
        usuarioRepositorio.save(administrador);

        Usuario usuarioC = new Usuario();
        usuarioC.setUsername("candidato");
        usuarioC.setPassword(passwordEncodern.encode("candidato"));
        usuarioC.addPapel(candidato);
        usuarioRepositorio.save(usuarioC);

        Usuario matricul = new Usuario();
        matricul.setUsername("matricula");
        matricul.setPassword(passwordEncodern.encode("matricula"));
        matricul.addPapel(matricula);
        usuarioRepositorio.save(matricul);



    }

}
