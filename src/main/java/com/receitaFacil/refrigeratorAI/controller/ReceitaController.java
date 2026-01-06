package com.receitaFacil.refrigeratorAI.controller;

import com.receitaFacil.refrigeratorAI.service.ReceitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService){
        this.receitaService = receitaService;
    }

    @GetMapping
    public ResponseEntity<String> gerarReceita(){
        return ResponseEntity.ok(receitaService.receitaGpt());
    }

}
