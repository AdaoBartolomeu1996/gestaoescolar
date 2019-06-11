package com.gestaoescolar.controller;

import com.gestaoescolar.config.USLocalDateFormatter;
import com.gestaoescolar.domain.Foto;
import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.domain.enumEstado.EstadoSexo;
import com.gestaoescolar.repositorio.FotoRepositorio;
import com.gestaoescolar.service.map.ProfessorService;
import com.gestaoescolar.service.map.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/imel/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

    @GetMapping("/registar")
    public String professorAdicionarGet(Model model){

        Professor professor = new Professor();
        model.addAttribute("estadoSexo", EstadoSexo.values());
        model.addAttribute("professor",professor);
        return "addProfessor";
    }

    @PostMapping("/registar")
    public String professorAdicionarPost(@ModelAttribute("professor")Professor professor, @RequestParam ("fotoP") MultipartFile fotoP,RedirectAttributes redirectAttributes){

        String url;

        Professor professorEmail = professorService.procurarEmail(professor.getEmail());

        Professor professorBilehte= professorService.procurarBilhete(professor.getBilhete());

        if (professorEmail==null){

           if (professorBilehte==null){
               try {
                   byte[] biFotoBytes = fotoP.getBytes();
                   Path path = Paths.get("//Users/danygaspar/Documents/Spring Projects/Spring5/gestaoescolar/src/main/resources/static/assets/imagens/fotoPerfil/" + fotoP.getOriginalFilename());
                   Files.write(path, biFotoBytes);
                   url = fotoP.getOriginalFilename();
                   professorService.registrarProfessor(professor,url);
                   redirectAttributes.addFlashAttribute("message", "Dados Salvo");
                   redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                   return "redirect:/imel/professor/registar";
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
            redirectAttributes.addFlashAttribute("message", "Erro o bilhete núnero "+professor.getBilhete()+" já existe ");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
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

    @GetMapping("/editar/{id}")
    public String professorPesquisarId(@PathVariable("id") Long id , Model model){

       Professor professor = professorService.findById(id);
       Usuario usuario = usuarioService.procurarUsername(professor.getUsuario().getUsername());
       Foto foto = fotoRepositorio.findByUsuario_Id(usuario.getId());


        model.addAttribute("estadoSexo",EstadoSexo.values());
        model.addAttribute("foto",foto);
        model.addAttribute("professor",professor);
        return "editarProfessor";
    }

    @PostMapping("/editar")
    public String professorEditar(@ModelAttribute("professor")Professor professor, RedirectAttributes redirectAttributes, @RequestParam ("fotoP") MultipartFile fotoP, BindingResult result, Model model){

        String url;
        Professor prof = professorService.findById(professor.getId());

        Professor professorEmail = professorService.procurarEmail(professor.getEmail());

        Professor professorBilehte= professorService.procurarBilhete(professor.getBilhete());

        if (professorEmail==null || prof.getEmail().equals(professor.getEmail())){

            if ((prof.getBilhete().equals(professor.getBilhete()) || professorBilehte==null) ){
                if (fotoP.isEmpty()){
                    redirectAttributes.addFlashAttribute("message", "Dados Salvo");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                    url =null;
                    professorService.atualizarProfessor(professor,url);
                    redirectAttributes.addFlashAttribute("message", "Dados Salvo");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                    return "redirect:/imel/professor/editar/"+professor.getId()+"";
                }else {
                    try {
                        byte[] biFotoBytes= fotoP.getBytes();
                        Path path = Paths.get("/Users/danygaspar/Documents/Spring Projects/Spring5/gestaoescolar/src/main/resources/static/assets/imagens/fotoPerfil/" + fotoP.getOriginalFilename());
                        Files.write(path, biFotoBytes);
                        url = fotoP.getOriginalFilename();
                        professorService.atualizarProfessor(professor,url);
                        redirectAttributes.addFlashAttribute("message", "Dados Salvo");
                        redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                        return "redirect:/imel/professor/editar/"+professor.getId()+"";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            redirectAttributes.addFlashAttribute("message", "Erro o bilhete núnero "+professor.getBilhete()+" já existe ");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/professor/editar/"+professor.getId()+"";
        }

        if (professorEmail.getEmail()!=null){
            redirectAttributes.addFlashAttribute("message", "Erro o email "+professor.getEmail()+" já existe ");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/professor/editar/"+professor.getId()+"";
        }

        return "redirect:/imel/professor/pesquisar";

    }

    @PostMapping("/eliminar")
    public String professorEliminar(@RequestParam long id) {
        System.out.println("ID: "+id);
        professorService.apagarProfessor(id);
        return "redirect:/imel/professor/pesquisar";
    }
}
