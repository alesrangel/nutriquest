package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Nutricionista;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long>{

	Nutricionista findByEmail(String email);

}
