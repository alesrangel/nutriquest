package com.nutriquestion.nutriquestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Questao;
import com.nutriquestion.nutriquestion.entities.Questionario;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM tb_questionario WHERE nutricionista_id = :nutricionistaId")
	List<Questionario> findAllNutricionista(Long nutricionistaId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_questao WHere tb_questao.questionario_id = :questionarioId")
	List<Questao> findQuestionario(Long questionarioId);
}
