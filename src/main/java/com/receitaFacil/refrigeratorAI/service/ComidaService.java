package com.receitaFacil.refrigeratorAI.service;

import com.receitaFacil.refrigeratorAI.model.ComidaModel;
import com.receitaFacil.refrigeratorAI.repository.ComidaRepository;

import java.util.List;
import java.util.Optional;

public class ComidaService {

    private ComidaRepository comidaRepository;

    public ComidaService(ComidaRepository comidaRepository){
    comidaRepository = this.comidaRepository;
    }

    // Listar todos
    public List<ComidaModel> listar(){
        return comidaRepository.findAll();
    }

    // Listar Por id
    public Optional<ComidaModel> listarPorId(Long id){
        return comidaRepository.findById(id);
    }

    // Criar/ Salvar
    public ComidaModel salvarComida(ComidaModel comidaModel){
        return comidaRepository.save(comidaModel);
    }

    // Atualizar
    public Optional<ComidaModel> atualizarComida (Long id, ComidaModel novosDatos){
        Optional<ComidaModel> comidaAntiga = comidaRepository.findById(id); // Verifica se esse Id é existente

        if(comidaAntiga.isPresent()){
            ComidaModel comidaExistente = comidaAntiga.get();

            comidaExistente.setNome(novosDatos.getNome());
            comidaExistente.setCategoria(novosDatos.getCategoria());
            comidaExistente.setQuantidade(novosDatos.getQuantidade());
            comidaExistente.setValidade(novosDatos.getValidade());

            ComidaModel comidaAtualizda = comidaRepository.save(comidaExistente);
            return Optional.of(comidaAtualizda);
        }
        else {
            return Optional.empty();
        }

    }

    // Deletar
    public void deletar(Long id){
        comidaRepository.deleteById(id);
    }

}