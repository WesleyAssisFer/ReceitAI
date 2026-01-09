package com.receitaFacil.refrigeratorAI.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.receitaFacil.refrigeratorAI.model.ComidaModel;
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
    private final ObjectMapper objectMapper = new ObjectMapper();

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
                .bodyToMono(String.class)
                .map(this::extrairTexto);

    }
    private String extrairTexto (String jsonCompleto){
        try {
            JsonNode acessarJson = objectMapper.readTree(jsonCompleto);
            return acessarJson
                    .path("output")
                    .get(0)
                    .path("content")
                    .get(0)
                    .path("text")
                    .asText();
        }catch (Exception exception){
            throw new RuntimeException("Erro ao extrair texto da OpenAI ", exception);
        }
    }
}