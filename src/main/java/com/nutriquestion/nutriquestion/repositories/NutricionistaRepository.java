package com.nutriquestion.nutriquestion.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nutriquestion.nutriquestion.entities.Nutricionista;

@Repository
public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long>{

	Nutricionista findByEmail(String email);
	
	@Query(nativeQuery = true, value = "select * from tb_nutricionista where tb_nutricionista.email = :email and tb_nutricionista.senha = :senha")
	Optional<Nutricionista> findEmailSenha(String email, String senha);

}
