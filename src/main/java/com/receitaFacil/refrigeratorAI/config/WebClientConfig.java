package com.receitaFacil.refrigeratorAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${openai.api.url}")
    private String chatGptUrl;

    @Value("${openai.api.key}")
    private String chatGptKey;


    @Bean
    WebClient webClient(WebClient.Builder builder){
     return builder.baseUrl(chatGptUrl)
             .defaultHeader("Content-Type", "application/json")
             .defaultHeader("Authorization", "Bearer " + chatGptKey)
             .build();

    }

}