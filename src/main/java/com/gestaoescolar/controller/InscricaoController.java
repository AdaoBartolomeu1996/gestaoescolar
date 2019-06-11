package com.gestaoescolar.controller;


import com.gestaoescolar.config.USLocalDateFormatter;
import com.gestaoescolar.domain.Estudante;
import com.gestaoescolar.domain.enumEstado.EnumMunicipio;
import com.gestaoescolar.domain.enumEstado.EstadoSexo;
import com.gestaoescolar.service.map.CursoService;
import com.gestaoescolar.service.map.EstudanteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/imel/inscricao")
public class InscricaoController {


    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudanteService estudanteService;

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

    @GetMapping("/registar")
    public String adicionarInscricao(Model model){

        Estudante estudante = new Estudante();

        model.addAttribute("cursos",cursoService.lisgtarCursos());
        model.addAttribute("estadoMunicipio", EnumMunicipio.values());
        model.addAttribute("estadoSexo", EstadoSexo.values());
        model.addAttribute("estudante",estudante);
        return "addInscricao";
    }

    @GetMapping("/pesquisar")
    public String pesquisarInscricao(Model model){

        model.addAttribute("estudantes",estudanteService.listarInscritos());
        return "pesquisarIscricaoEstudante";
    }
/*
timeview
 @GetMapping("/visualizar/{id}")
    public String visualizarInscricao(@PathVariable("id") Long id ,Model model){

        model.addAttribute("municipio", EnumMunicipio.values());
        model.addAttribute("estadoSexo",EstadoSexo.values());
        model.addAttribute("cursos",cursoService.lisgtarCursos());
        model.addAttribute("estudante",estudanteService.findById(id));
        return "view";
    }
 */

    @GetMapping("/visualizar/{id}")
    public String visualizarInscricao(@PathVariable("id") Long id ,Model model){

        model.addAttribute("municipio", EnumMunicipio.values());
        model.addAttribute("estadoSexo",EstadoSexo.values());
        model.addAttribute("cursos",cursoService.lisgtarCursos());
        model.addAttribute("estudante",estudanteService.findById(id));
        return "timeview";
    }

    @PostMapping("/registar")
    public  String adicionarInscricaoPost(@ModelAttribute("estudante")Estudante estudante, @RequestParam ("alunoFoto") MultipartFile alunoFoto, @RequestParam ("certificadoFoto") MultipartFile certificadoFoto, @RequestParam ("biFoto") MultipartFile biFoto, RedirectAttributes redirectAttributes, Model model){

        Estudante estudanteBi = estudanteService.procurarBilhete(estudante.getBilhete());

        if (estudanteBi==null) {
            try {

                byte[] biFotoBytes = biFoto.getBytes();
                Path path = Paths.get("/Users/danygaspar/Documents/Spring Projects/Spring5/gestaoescolar/src/main/resources/static/assets/imagens/fotoEstudante/" + biFoto.getOriginalFilename());
                Files.write(path, biFotoBytes);
                estudante.setFotoAluno(biFoto.getOriginalFilename());

                byte[] alunoFotoBytes = alunoFoto.getBytes();
                Path path1 = Paths.get("/Users/danygaspar/Documents/Spring Projects/Spring5/gestaoescolar/src/main/resources/static/assets/imagens/fotoPerfil/" + alunoFoto.getOriginalFilename());
                Files.write(path1, alunoFotoBytes);
                estudante.setFotoBi(alunoFoto.getOriginalFilename());

                byte[] certificadoFotoBytes = certificadoFoto.getBytes();
                Path path2 = Paths.get("/Users/danygaspar/Documents/Spring Projects/Spring5/gestaoescolar/src/main/resources/static/assets/imagens/fotoEstudante/" + certificadoFoto.getOriginalFilename());
                Files.write(path2, certificadoFotoBytes);
                estudante.setCertificadoOrininalFoto(certificadoFoto.getOriginalFilename());

                estudanteService.inscrverEstdante(estudante);
                redirectAttributes.addFlashAttribute("message", "Inscrição concluída por favor agurde pela publicação das lista.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                return "redirect:/imel/inscricao/registar";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        redirectAttributes.addFlashAttribute("message", "Erro o bilhete núnero "+estudante.getBilhete()+" já existe ");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/inscricao/registar";
    }

    @PostMapping("/eliminar")
    public String professorEliminar(@RequestParam Long id) {

            log.info("ID: "+id);
        estudanteService.apagarInscricao(id);
        return "redirect:/imel/inscricao/pesquisar";
    }

    @GetMapping("/editar/{id}")
    public String professorEditarIdPost(@PathVariable("id") Long id , Model model){

        model.addAttribute("estadoSexo",EstadoSexo.values());
        model.addAttribute("cursos",cursoService.lisgtarCursos());
        model.addAttribute("estudante",estudanteService.findById(id));
        return "editarInscricao";
    }

    @PostMapping("/editar")
    public String inscricaoEditarIdPost(@ModelAttribute("estudante")Estudante estudante, @RequestParam ("alunoFoto") MultipartFile alunoFoto,  RedirectAttributes redirectAttributes, Model model){

        Estudante estudanteId = estudanteService.findById(estudante.getId());
        Estudante estudanteBi = estudanteService.procurarBilhete(estudante.getBilhete());

        if (estudanteBi==null || estudante.getBilhete().equals(estudanteId.getBilhete())){

                estudanteService.inscrverEstdante(estudante);
                redirectAttributes.addFlashAttribute("message", "Dados salvo.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                return "redirect:/imel/inscricao/editar/"+estudante.getId();

        }

        redirectAttributes.addFlashAttribute("message", "Erro o bilhete núnero "+estudante.getBilhete()+" já existe ");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/inscricao/registar";
    }
}

/*
if (estudanteBi==null || estudante.getBilhete().equals(estudanteId.getBilhete())){

            try {

                if (alunoFoto.isEmpty()){
                    estudanteService.inscrverEstdante(estudante);
                    redirectAttributes.addFlashAttribute("message", "Inscrição concluída por favor agurde pela publicação das lista.");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                    return "redirect:/imel/inscricao/editar/"+estudante.getId();
                }

                byte[] alunoFotoBytes = alunoFoto.getBytes();
                Path path = Paths.get("/Users/danygaspar/Documents/Spring Projects/Spring5/gestaoescolar/src/main/resources/static/assets/imagens/fotoEstudante/" + alunoFoto.getOriginalFilename());
                Files.write(path, alunoFotoBytes);
                estudante.setFotoAluno(alunoFoto.getOriginalFilename());

                estudanteService.inscrverEstdante(estudante);
                redirectAttributes.addFlashAttribute("message", "Inscrição concluída por favor agurde pela publicação das lista.");
                redirectAttributes.addFlashAttribute("alertClass", "alert-success");
                return "redirect:/imel/inscricao/editar/"+estudante.getId();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
 */
