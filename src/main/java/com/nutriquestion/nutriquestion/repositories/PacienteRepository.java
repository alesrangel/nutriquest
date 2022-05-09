package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
