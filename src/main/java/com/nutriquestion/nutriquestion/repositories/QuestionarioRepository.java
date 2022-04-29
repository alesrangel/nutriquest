package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutriquestion.nutriquestion.entities.Questionario;

public interface QuestionarioRepository extends JpaRepository<Questionario, Long>{

}
