package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{

}
