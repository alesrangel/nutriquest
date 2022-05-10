package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Questionario;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

}
