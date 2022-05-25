package com.nutriquestion.nutriquestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Paciente;
import com.nutriquestion.nutriquestion.entities.Questionario;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM tb_paciente WHERE tb_paciente.nutricionista_id = :nutricionistaId and tb_paciente.arquivado = true")
	List<Paciente> findArquivados();
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_paciente WHERE tb_paciente.nutricionista_id = :nutricionistaId and tb_paciente.arquivado = false")
	List<Paciente> findByNutricionista(Long nutricionistaId);

	@Query(nativeQuery = true, value = "select distinct(tb_questionario.titulo) from tb_resposta, tb_questao, tb_questionario where tb_resposta.paciente_id = :idPaciente "
			+ "and tb_questao.resposta_id = tb_resposta.id and tb_questao.questionario_id = tb_questionario.id")
	List<Questionario> findRespondidosPorUsuariio(Long idPaciente);
}
