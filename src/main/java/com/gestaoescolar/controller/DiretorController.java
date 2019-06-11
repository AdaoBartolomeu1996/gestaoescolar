package com.gestaoescolar.controller;

import com.gestaoescolar.config.USLocalDateFormatter;
import com.gestaoescolar.domain.enumEstado.EstadoSexo;
import com.gestaoescolar.service.map.DiretorService;
import com.gestaoescolar.service.map.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller
@RequestMapping("/imel/diretor")
public class DiretorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private DiretorService diretorService;

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

    @GetMapping("/atribuir")
    public String diretorregistar(Model model){

        model.addAttribute("professores",professorService.listarProfessoresEstado());
        return "afetacaoDiretor";
    }

    @PostMapping("/registar")
    public String diretorRegistrarPost(@RequestParam long id, RedirectAttributes redirectAttributes) {

        if (diretorService.count()<1){
            diretorService.registrarDiretor(id);
            return "redirect:/imel/diretor/pesquisar";
        }
        redirectAttributes.addFlashAttribute("message", "Erro só pode existir um Diretor registado no sistema.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
       return "redirect:/imel/diretor/atribuir";
    }

    @GetMapping("/pesquisar")
    public String diretorPesquisar(Model model){

        model.addAttribute("diretores",diretorService.listarDiretores());
        return "pesquisarDiretor";
    }

    @PostMapping("/eliminar")
    public String diretorEliminar(@RequestParam long id, Model model) {

        diretorService.apagarDiretor(id);
        return "redirect:/imel/diretor/pesquisar";
    }

    @GetMapping("/editar/{id}")
    public String diretorPesquisarId(@PathVariable("id") Long id , Model model){

        // Professor professor = professorService.findById(id);
        model.addAttribute("estadoSexo", EstadoSexo.values());
        model.addAttribute("professor",professorService.findById(id));
        return "editarDiretor";
    }
}


