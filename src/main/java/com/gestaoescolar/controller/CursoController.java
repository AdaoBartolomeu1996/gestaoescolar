package com.gestaoescolar.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imel/curso")
public class CursoController {



    @GetMapping("/registar")
    public String cursoAdd(){

        return "addCurso";
    }

    @GetMapping("/pesquisar")
    public String pesquisarCurso(){

        return "pesquisarCurso";
    }
}
