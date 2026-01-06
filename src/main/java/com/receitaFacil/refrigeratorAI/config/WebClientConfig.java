package com.receitaFacil.refrigeratorAI.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${openai.api.url}")
    private String chatGptUrl;

    @Bean
    WebClient webClient(WebClient.Builder builder, @Value("${openai.api.url}") String baseUrl){
        return builder.baseUrl(chatGptUrl).build();
    }
}
