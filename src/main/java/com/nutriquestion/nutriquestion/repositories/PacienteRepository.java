package com.nutriquestion.nutriquestion.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM tb_paciente WHERE tb_paciente.nutricionista_id = :nutricionistaId and tb_paciente.arquivado = true")
	List<Paciente> findArquivados();
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_paciente WHERE tb_paciente.nutricionista_id = :nutricionistaId and tb_paciente.arquivado = false")
	List<Paciente> findByNutricionista(Long nutricionistaId);

	
}
