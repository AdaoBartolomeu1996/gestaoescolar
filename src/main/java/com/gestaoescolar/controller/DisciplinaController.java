package com.gestaoescolar.controller;


import com.gestaoescolar.config.USLocalDateFormatter;
import com.gestaoescolar.domain.Disciplina;
import com.gestaoescolar.service.map.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;


@Controller
@RequestMapping("/imel/disciplina")
public class DisciplinaController {


    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/registar")
    public String disciplinaAdicionarGet(Model model){

        Disciplina disciplina = new Disciplina();

        model.addAttribute("disciplina",disciplina);
        return "addDisciplina";
    }

    @PostMapping("/registar")
    public String ddisciplinaAdicionarPost(@ModelAttribute("disciplina") Disciplina disciplina, BindingResult result, RedirectAttributes redirectAttributes, Model model){

        if (result.hasErrors()){

            redirectAttributes.addFlashAttribute("message", "Erro");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        }

        redirectAttributes.addFlashAttribute("message", "Dados Salvo");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        disciplinaService.registrarDisciplina(disciplina);
        return "redirect:/imel/disciplina/registar";
    }

    @GetMapping("/pesquisar")
    public String disciplinaPesquisar(Model model){

        model.addAttribute("disciplinas",disciplinaService.listarDepartmentos());
        return "pesquisarDisciplina";
    }

    @GetMapping("/editarId/{id}")
    public String disciplinaPesquisarId(@PathVariable("id") Long id , Model model){

        model.addAttribute("disciplina",disciplinaService.findById(id));
        return "editarDisciplina";
    }

    @PostMapping("/editar")
    public String disciplinaEditar(@ModelAttribute("disciplina")Disciplina disciplina, RedirectAttributes redirectAttributes, BindingResult result, Model model){

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("message", "Erro");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/disciplina/editar/"+disciplina.getId();
        }
        redirectAttributes.addFlashAttribute("message", "Dados Salvo");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        disciplinaService.registrarDisciplina(disciplina);
        return "redirect:/imel/disciplina/pesquisar";
    }

    @PostMapping("/eliminar")
    public String disciplinaEliminar(@RequestParam long id) {
        System.out.println("ID: "+id);
        disciplinaService.apagarDepartamento(id);
        return "redirect:/imel/disciplina/pesquisar";
    }
}
