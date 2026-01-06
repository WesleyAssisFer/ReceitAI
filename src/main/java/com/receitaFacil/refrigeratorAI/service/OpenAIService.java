package com.receitaFacil.refrigeratorAI.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
// Aqui é a conexao com a ChatGPT
public class OpenAIService {
    private final WebClient webClient;
    private final String apiKey;

    public OpenAIService(WebClient webClient, @Value("${openai.api.key}") String apiKey){
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public String enviarPrompt(String prompt){
        Map<String, Object> corpoDoChat = Map.of(
                "model","gpt-4.1-mini",
                "input", prompt
        );

        return webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(corpoDoChat)
                .retrieve()
                .bodyToMono(String.class)
                .block(); // usando o block vai deixar sicrono
    }
}
