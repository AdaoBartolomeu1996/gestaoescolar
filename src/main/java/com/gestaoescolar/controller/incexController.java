package com.gestaoescolar.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imel")
public class incexController {


    @GetMapping("/inicio")
    public String pagina(){
        return "painel";
    }

    @GetMapping("/login")
    public  String login(){
        return "login";
    }
}
