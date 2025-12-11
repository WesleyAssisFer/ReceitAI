package com.receitaFacil.refrigeratorAI.model;

import com.receitaFacil.refrigeratorAI.enums.Categoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ComidaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Categoria categoria;
    private Integer quantidade;
    private LocalDate validade;

}
