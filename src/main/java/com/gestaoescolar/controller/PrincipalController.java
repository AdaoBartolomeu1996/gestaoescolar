package com.gestaoescolar.controller;



import com.gestaoescolar.domain.Foto;
import com.gestaoescolar.domain.Professor;
import com.gestaoescolar.domain.Usuario;
import com.gestaoescolar.repositorio.FotoRepositorio;
import com.gestaoescolar.service.map.EstudanteService;
import com.gestaoescolar.service.map.ProfessorService;
import com.gestaoescolar.service.map.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;

@Slf4j
@Controller
public class PrincipalController {


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EstudanteService estudanteService;


    @Autowired
    private ProfessorService professorService;

    @Autowired
    private FotoRepositorio fotoRepositorio;


    @GetMapping("/imel/painel")
    public String pagina(){
        return "painel";
    }

    @GetMapping("/login")
    public  String login(){
        return "login";
    }


    @GetMapping("/imel/inicio")
    public String index(Principal principal, Model model,HttpServletRequest request){

        Usuario usuario = usuarioService.procurarUsername(principal.getName());
        Professor professor = professorService.procurarUsuarioId(usuario.getId());

        //Foto foto = fotoRepositorio.findByUsuario_Id(usuario.getId());
        model.addAttribute("usuario",usuario);
        model.addAttribute("professor",professor);

        if (request.isUserInRole("ROLE_ADIMIN")){

            return "redirect:/imel/painel";
        }

        if (request.isUserInRole("ROLE_DIRETOR")){
            return "redirect:/imel/painel";

        }
        if (request.isUserInRole("ROLE_COORDENADOR") ){
            return "redirect:/imel/painel";
        }

        if (request.isUserInRole("ROLE_CANDIDATO")){
            return "redirect:/imel/inscricao/registar";
        }

        if (request.isUserInRole("ROLE_PROFESSOR")){
            //return "redirect:/imel/inscricao/registar";
        }


        return "/fragments/index";
    }


}
