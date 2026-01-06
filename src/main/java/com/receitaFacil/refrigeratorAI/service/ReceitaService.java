package com.receitaFacil.refrigeratorAI.service;

import com.receitaFacil.refrigeratorAI.model.ComidaModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ReceitaService {

    private final OpenAIService openAIService;
    private final ComidaService comidaService;

    public ReceitaService(OpenAIService openAIService, ComidaService comidaService){
        this.openAIService = openAIService;
        this.comidaService = comidaService;
    }

 // Pega somente o nome da comida
    public String receitaGpt(){
        List<String> ingredientes = comidaService.listar()
                .stream()
                .map(ComidaModel::getNome)
                .toList();

        if(ingredientes.isEmpty()){
            throw new RuntimeException("Nenhum ingrediente cadastrado");
        }

        String prompt = """
                Crie uma receita usando apenas os seguintes ingredientes:
                %s
                Explique o modo de preparo passo a passo.
                """
                .formatted(String.join(", ", ingredientes));

        // Quando controller pegar as receitas, aqui será enviado para o ChatGPT
        return openAIService.enviarPrompt(prompt);

    }

}
