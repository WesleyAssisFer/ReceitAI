package com.receitaFacil.refrigeratorAI.controller;

import com.receitaFacil.refrigeratorAI.service.OpenAIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final OpenAIService openAIService;


    public ReceitaController(OpenAIService openAIService){
        this.openAIService = openAIService;
    }

    @GetMapping
    public Mono<ResponseEntity<String>> enviarReceita(){
        return openAIService.gerarReceita()
                .map(ResponseEntity::ok);

    }
}