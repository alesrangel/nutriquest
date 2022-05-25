package com.nutriquestion.nutriquestion.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Questao;
import com.nutriquestion.nutriquestion.entities.Questionario;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM tb_questionario WHERE nutricionista_id = :nutricionistaId")
	List<Questionario> findAllNutricionista(Long nutricionistaId);
	
	@Query(nativeQuery = true, value = "select * from tb_questao left Join tb_questionario on tb_questao.questionario_id = tb_questionario.id where tb_questionario.id = :questionarioId")
	List<Questao> findByQestaoQuestionario(Long questionarioId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_questao WHere tb_questao.questionario_id = :questionarioId")
	List<Questao> findQuestionario(Long questionarioId);
	
	
	@Query(nativeQuery = true, value = "select distinct(tb_questionario.*) from tb_resposta, tb_questao, tb_questionario where tb_resposta.paciente_id = :idPaciente "
			+ "and tb_questao.resposta_id = tb_resposta.id and tb_questao.questionario_id = tb_questionario.id")
	List<Questionario> findRespondidosPorUsuariio(Long idPaciente);
}
