package com.nutriquestion.nutriquestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM tb_questao WHERE tb_questao.questionario_id = :questionarioId")
	List<Questao> findAllQuestionario(Long questionarioId);
}
