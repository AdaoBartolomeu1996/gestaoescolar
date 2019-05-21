package com.gestaoescolar.controller;

import com.gestaoescolar.config.USLocalDateFormatter;
import com.gestaoescolar.domain.Departamento;
import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.ProfessorTelefone;
import com.gestaoescolar.domain.enumEstado.EstadoSexo;
import com.gestaoescolar.service.map.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/imel/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

    @GetMapping("/registar")
    public String professorAdicionarGet(Model model){

        Professor professor = new Professor();

        model.addAttribute("estadoSexo", EstadoSexo.values());
        model.addAttribute("telefone", new ProfessorTelefone());
        model.addAttribute("professor",professor);
        return "addProfessor";
    }

    @PostMapping("/registar")
    public String professorAdicionarPost(@ModelAttribute("professor")Professor professor, @ModelAttribute("telefone")ProfessorTelefone professorTelefone, RedirectAttributes redirectAttributes){

        if (professorService.procurarEmail(professor.getEmail())==null){

            redirectAttributes.addFlashAttribute("message", "Dados Salvo");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            professorService.registrarProfessor(professor,professorTelefone);
            return "redirect:/imel/professor/registar";
        }

        if (professorService.procurarEmail(professor.getEmail())!=null){
            redirectAttributes.addFlashAttribute("message", "Erro o email "+professor.getEmail()+" já existe ");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/professor/registar";
        }

        if (professorService.procurarBilhete(professor.getBilhete())!=null){
            redirectAttributes.addFlashAttribute("message", "Erro o bilhete núnero "+professor.getBilhete()+" já existe ");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/professor/registar";
        }
        return "redirect:/imel/professor/registar";
    }

    @GetMapping("/pesquisar")
    public String professorPesquisar(Model model){

        model.addAttribute("professores",professorService.listarProfessores());
        return "pesquisarProfessor";
    }

    @GetMapping("/editarId/{id}")
    public String professorPesquisarId(@PathVariable("id") Long id , Model model){

        model.addAttribute("professor",professorService.findById(id));
        return "editarProfessor";
    }

    @PostMapping("/editar")
    public String professorEditar(@ModelAttribute("professor")Professor professor, RedirectAttributes redirectAttributes, BindingResult result, Model model){

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("message", "Erro");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/professor/editar/"+professor.getId();
        }
        redirectAttributes.addFlashAttribute("message", "Dados Salvo");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        //professorService.registrarProfessor(professor);
        return "redirect:/imel/professor/pesquisar";
    }

    @PostMapping("/eliminar")
    public String professorEliminar(@RequestParam long id, Model model) {
        System.out.println("ID: "+id);
        professorService.apagarProfessor(id);
        return "redirect:/imel/professor/pesquisar";
    }
}
