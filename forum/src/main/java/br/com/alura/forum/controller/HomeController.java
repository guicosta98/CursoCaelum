package br.com.alura.forum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index(){
        System.out.println("Log gerado ao acessar o index");

        return "Bem vindo ao Forum";
    }
}
