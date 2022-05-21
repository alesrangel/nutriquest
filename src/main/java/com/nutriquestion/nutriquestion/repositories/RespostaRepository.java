package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Resposta;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long>{

	
	@Query(nativeQuery = true, value = "UPDATE tb_questao SET resposta_id = :respostaId WHERE tb_questao.id = :questaoId")
	void fazerRelacao(Long respostaId, Long questaoId);
	
}
