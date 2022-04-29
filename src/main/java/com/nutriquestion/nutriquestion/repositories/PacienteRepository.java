package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutriquestion.nutriquestion.entities.Consulta;

public interface PacienteRepository extends JpaRepository<Consulta, Long>{

}
