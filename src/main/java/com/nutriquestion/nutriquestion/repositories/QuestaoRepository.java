package com.nutriquestion.nutriquestion.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Questao;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long>{

	@Query(nativeQuery = true, value = "select * from tb_questao where tb_questao.questionario_id = :idQuest order by tb_resposta.id")
	List<Questao> findByQestaoQuestionario(@Param("idQuest") Long idQuestionario);
	
	@Query(nativeQuery = true, value = "UPDATE tb_questao SET resposta_id = :respostaId  WHERE tb_questao.id = :idQuestao")
	Questao adicionaResposta(Long idQuestao, Long respostaId);

}
