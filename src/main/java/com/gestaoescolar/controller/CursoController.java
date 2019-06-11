package com.gestaoescolar.controller;


import com.gestaoescolar.domain.Curso;
import com.gestaoescolar.domain.Departamento;
import com.gestaoescolar.service.map.CursoService;
import com.gestaoescolar.service.map.DepartamentoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@Controller
@RequestMapping("/imel/curso")
public class CursoController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/registar")
    public String CursoAdicionarGet(Model model){

        Curso curso = new Curso();
        model.addAttribute("departamentos",departamentoService.listarDepartmentos());
        model.addAttribute("curso",curso);
        return "addCurso";
    }

    @PostMapping("/registar")
    public String CursoAdicionarAdicionarPost(@ModelAttribute("curso")Curso curso, RedirectAttributes redirectAttributes, Model model){

        Curso curs = cursoService.encontarPorNome(curso.getNome());

        if (curs==null){
            redirectAttributes.addFlashAttribute("message", "Dados Salvo");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            cursoService.registrarCurso(curso);
            return "redirect:/imel/curso/registar";
        }

        redirectAttributes.addFlashAttribute("message", "Erro o curso "+curso.getNome()+" já existe.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/curso/registar";
    }

    @GetMapping("/pesquisar")
    public String pesquisarCurso(Model model){

        model.addAttribute("cursos",cursoService.lisgtarCursos());
        return "pesquisarCurso";
    }

    @PostMapping("/eliminar")
    public String cursoEliminar(@RequestParam long id, Model model) {
        System.out.println("ID: "+id);
        cursoService.apagarCurso(id);
        return "redirect:/imel/curso/pesquisar";
    }


    @GetMapping("/editar/{id}")
    public String cursoPesquisarId(@PathVariable("id") Long id ,Model model){

        model.addAttribute("departamentos",departamentoService.listarDepartmentos());
        model.addAttribute("curso",cursoService.findById(id));
        return "editarCurso";
    }

    @PostMapping("/editar")
    public String curdoEditar(@ModelAttribute("curso")Curso curso, RedirectAttributes redirectAttributes, BindingResult result, Model model){

        Curso curs = cursoService.findById(curso.getId());

        Curso editarcurso=cursoService.encontarPorNome(curso.getNome());

        if (editarcurso==null || curs.getNome().equals(editarcurso.getNome())){
            redirectAttributes.addFlashAttribute("message", "Dados Salvo");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            cursoService.registrarCurso(curso);
            return "redirect:/imel/curso/editar/"+curso.getId();
        }
        redirectAttributes.addFlashAttribute("message", "Erro o curso "+curso.getNome()+" já existe.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/curso/editar/"+curso.getId();
    }

}
