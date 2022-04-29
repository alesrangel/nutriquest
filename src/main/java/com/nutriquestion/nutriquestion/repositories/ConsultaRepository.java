package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutriquestion.nutriquestion.entities.Avaliacao;

public interface ConsultaRepository extends JpaRepository<Avaliacao, Long>{

}
