package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long>{

}
