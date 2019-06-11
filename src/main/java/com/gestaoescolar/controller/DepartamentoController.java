package com.gestaoescolar.controller;


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
@RequestMapping("/imel/departamento")
public class DepartamentoController {


    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/registar")
    public String departamentoAdicionarGet(Model model){

        Departamento departamento = new Departamento();
        model.addAttribute("departamento",departamento);
        return "addDepartameto";
    }

    @PostMapping("/registar")
    public String departamentoAdicionarPost(@ModelAttribute("departamento")Departamento departamento, RedirectAttributes redirectAttributes, Model model){

        Departamento editardepartament=departamentoService.encontarPorNome(departamento.getNome());

        if (editardepartament!=null){
            redirectAttributes.addFlashAttribute("message", "Erro o departamento "+departamento.getNome()+" já existe.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return"redirect:/imel/departamento/registar";
        }
        redirectAttributes.addFlashAttribute("message", "Dados Salvo");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
        departamentoService.registrarDepartamento(departamento);
        return "redirect:/imel/departamento/registar";
    }

    @GetMapping("/pesquisar")
    public String departamentoPesquisar(Model model){

        model.addAttribute("departamentos",departamentoService.listarDepartmentos());
        return "pesquisarDepartamento";
    }

    @GetMapping("/editar/{id}")
    public String departamentoPesquisarId(@PathVariable("id") Long id ,Model model){

        model.addAttribute("departamento",departamentoService.findById(id));
        return "editarDepartamento";
    }

    @PostMapping("/editar")
    public String departamentoEditar(@ModelAttribute("departamento")Departamento departamento, RedirectAttributes redirectAttributes, BindingResult result, Model model){

        Departamento departamen = departamentoService.findById(departamento.getId());

        Departamento editardepartament=departamentoService.encontarPorNome(departamento.getNome());

        if (editardepartament==null || departamen.getNome().equals(editardepartament.getNome())){
            redirectAttributes.addFlashAttribute("message", "Dados Salvo");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            departamentoService.registrarDepartamento(departamento);
            return "redirect:/imel/departamento/editar/"+departamento.getId();
        }
        redirectAttributes.addFlashAttribute("message", "Erro o departamento "+departamento.getNome()+" já existe.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/departamento/editar/"+departamento.getId();
    }

    @PostMapping("/eliminar")
    public String departamentoEliminar(@RequestParam long id, Model model) {
        System.out.println("ID: "+id);
        departamentoService.apagarDepartamento(id);
        return "redirect:/imel/departamento/pesquisar";
    }
}
