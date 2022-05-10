package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long>{

}
