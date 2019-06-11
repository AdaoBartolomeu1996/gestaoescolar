package com.gestaoescolar.controller;


import com.gestaoescolar.config.USLocalDateFormatter;

import com.gestaoescolar.domain.Turma;
import com.gestaoescolar.domain.enumEstado.EnumMunicipio;
import com.gestaoescolar.domain.enumEstado.EstadoTurno;
import com.gestaoescolar.service.map.CursoService;
import com.gestaoescolar.service.map.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
@RequestMapping("/imel/turma")
public class TurmaController {


    @Autowired
    private CursoService cursoService;

    @Autowired
    private TurmaService turmaService;

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

    @GetMapping("/registar")
    public String CursoAdicionarGet(Model model){

        Turma turma = new Turma();
        model.addAttribute("turnos", EstadoTurno.values());
        model.addAttribute("cursos",cursoService.lisgtarCursos());
        model.addAttribute("turma",turma);
        return "addTurma";
    }
}
