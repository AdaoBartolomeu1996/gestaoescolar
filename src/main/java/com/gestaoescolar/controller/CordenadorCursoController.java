package com.gestaoescolar.controller;


import com.gestaoescolar.domain.CoordenadorCurso;
import com.gestaoescolar.domain.Curso;
import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.enumEstado.EstadoSexo;
import com.gestaoescolar.domain.security.Papel;
import com.gestaoescolar.repositorio.PapelRepositorio;
import com.gestaoescolar.service.map.CoordenadorCursoService;
import com.gestaoescolar.service.map.CursoService;
import com.gestaoescolar.service.map.ProfessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/imel/coordenadorCurso")
public class CordenadorCursoController {

    @Autowired
    private CoordenadorCursoService coordenadorCursoService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private PapelRepositorio papelRepositorio;

    @Autowired
    private CursoService cursoService;

    @GetMapping("/professores")
    public String coordenadorPesquisarProfessores(Model model){

        model.addAttribute("professores",professorService.listarProfessoresEstado());
        return "afetacaoProfessorCoordenadoor";
    }

    @GetMapping("/registar/{id}")
    public String coordenadorPesquisar(@ModelAttribute("id")Long id, Model model){

        CoordenadorCurso coordenadorCurso = new CoordenadorCurso();

        model.addAttribute("coordenador", coordenadorCurso);
        model.addAttribute("estadoSexo", EstadoSexo.values());
        model.addAttribute("cursos",cursoService.lisgtarCursos());
        model.addAttribute("professor",professorService.findById(id));
        return "addCoordenador";
    }

    @PostMapping("/registar")
    public String coordenadorAdicionarPost(@ModelAttribute("coordenador") CoordenadorCurso cordenador, @ModelAttribute("professor") Professor professor, RedirectAttributes redirectAttributes, Model model){

        Papel papel = papelRepositorio.findByPapel("ROLE_PROFESSOR");
        Professor professorserv = professorService.findById(professor.getId());
        Curso curso = cursoService.findById(cordenador.getCurso().getId());
        CoordenadorCurso coordenadorCursoProf = coordenadorCursoService.procrarProfessor(professorserv.getId());
        CoordenadorCurso coordenadorCursoCurs= coordenadorCursoService.peocurarCurso(curso.getId());

        if (coordenadorCursoProf == null){

            if (professorserv.getUsuario().getPapeis().contains(papel)){

                if (coordenadorCursoCurs==null){
                    cordenador.setProfessor(professorserv);
                    coordenadorCursoService.registrarCordenador(cordenador);
                    redirectAttributes.addFlashAttribute("message", "Dados Salvo");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                    return "redirect:/imel/coordenadorCurso/professores";
                } else {
                    redirectAttributes.addFlashAttribute("message", "Erro o Curso "+coordenadorCursoCurs.getCurso().getNome()+" já possui um Coordenador.");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                    return "redirect:/imel/coordenadorCurso/registar/"+professorserv.getId()+"";
                }
            }
                redirectAttributes.addFlashAttribute("message", "Erro o Diretor "+professorserv.getNome()+" não pode ser um Coordenador deve primeiro eliminar o Diretor.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                return "redirect:/imel/coordenadorCurso/registar/"+professorserv.getId()+"";
        }
        redirectAttributes.addFlashAttribute("message", "Erro o Coordenador de Curso "+professorserv.getNome()+" já existe.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/coordenadorCurso/registar/"+professorserv.getId()+"";
    }

    @GetMapping("/pesquisar")
    public String coordenadorPesquisar(Model model){

        model.addAttribute("coordenadores",coordenadorCursoService.listarCoordenadores());
        return "pesquisarCoordenador";
    }

    @PostMapping("/eliminar")
    public String coordenadorEliminar(@RequestParam long id, Model model) {

        coordenadorCursoService.apagarCordenador(id);
        return "redirect:/imel/coordenadorCurso/pesquisar";
    }

    @GetMapping("/editar/{id}")
    public String coordenadorPesquisarId(@PathVariable("id") Long id , Model model){

        model.addAttribute("estadoSexo",EstadoSexo.values());
        model.addAttribute("professor",professorService.findById(id));
        return "editarCoordenadorCurso";
    }

   
}
