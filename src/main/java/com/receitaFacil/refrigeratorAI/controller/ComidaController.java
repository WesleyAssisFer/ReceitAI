package com.receitaFacil.refrigeratorAI.controller;


import com.receitaFacil.refrigeratorAI.service.ComidaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/comida")
public class ComidaController {

    private ComidaService comidaService;

    public ComidaController(ComidaService comidaService){
        this.comidaService = comidaService;
    }


}