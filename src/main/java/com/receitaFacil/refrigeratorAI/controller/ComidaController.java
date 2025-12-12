package com.receitaFacil.refrigeratorAI.controller;


import com.receitaFacil.refrigeratorAI.model.ComidaModel;
import com.receitaFacil.refrigeratorAI.service.ComidaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comida")
public class ComidaController {

    private ComidaService comidaService;

    public ComidaController(ComidaService comidaService){
        this.comidaService = comidaService;
    }

    // Listar Todos
    @GetMapping
    public ResponseEntity<List<ComidaModel>> listarTodos(){
        return ResponseEntity.ok(comidaService.listar());
    }

    // Listar Por id
    @GetMapping("{id}")
    public ResponseEntity<ComidaModel> listarPorId(@PathVariable Long id){
        return comidaService.listarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Salvar / Atualizar
    @PostMapping
    public ResponseEntity<ComidaModel> salvarComida(@RequestBody ComidaModel comidaModel){
      return ResponseEntity.ok(comidaService.salvarComida(comidaModel));
    }

    // Atulizar Comida
    @PutMapping("{id}")
    public ResponseEntity<ComidaModel> atualizarComida(@PathVariable Long id, @RequestBody ComidaModel comidaModel){
       return comidaService.atualizarComida(id, comidaModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletarComida(@PathVariable Long id){
        if(comidaService.deletar(id)){
              return ResponseEntity.ok("Id " + id + " deletado com sucesso");
        }
        return ResponseEntity.notFound().build();
    }
}