package com.nutriquestion.nutriquestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nutriquestion.nutriquestion.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);

}
