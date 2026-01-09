package com.receitaFacil.refrigeratorAI.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.receitaFacil.refrigeratorAI.config.WebClientConfig;
import com.receitaFacil.refrigeratorAI.model.ComidaModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OpenAIService {
   private final ComidaService comidaService;
   private final WebClient webClient;

    public OpenAIService(ComidaService comidaService, WebClient webClient){
       this.comidaService = comidaService;
       this.webClient = webClient;
    }

    public Mono<String> gerarReceita(){
        // Pegar Somente o nomes do ComidaModel
        List<String> listarNomesComida = comidaService.listar()
                .stream()
                .map(ComidaModel::getNome)
                .toList();

        // organizar os Ingredientes(tirar da lista e colocar virgula)
        String ingredientesOrganizado = listarNomesComida.stream()
                .collect(Collectors.joining(", "));

        // Criar o prompt para ser incerido no body do crul
        String prompt = """
                Com esses ingredientes:
                %s
               Monte uma receita, passando um passo a passo.
               """.formatted(ingredientesOrganizado);

        Map<Object, String> dadosDoCorpo = Map.of("model","gpt-4.1-mini", "input", prompt);

        return webClient.post()
                .bodyValue(dadosDoCorpo)
                .retrieve()
                .bodyToMono(String.class);
    }

}