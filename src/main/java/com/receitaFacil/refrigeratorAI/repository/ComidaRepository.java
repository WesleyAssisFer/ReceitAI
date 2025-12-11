package com.receitaFacil.refrigeratorAI.repository;

import com.receitaFacil.refrigeratorAI.model.ComidaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRepository extends JpaRepository<ComidaModel, Long> {
}
