package com.nutriquestion.nutriquestion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.UsuarioDTO;
import com.nutriquestion.nutriquestion.dtos.UsuarioInsertDTO;
import com.nutriquestion.nutriquestion.entities.Usuario;
import com.nutriquestion.nutriquestion.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UsuarioRepository repository;

	
	@Transactional
	public UsuarioDTO insert(UsuarioInsertDTO dto) {
		Usuario entity = new Usuario();
		copyDTOToEntity(dto, entity);
		entity.setSenha(passwordEncoder.encode(dto.getSenha()));
		entity = repository.save(entity);
		return new UsuarioDTO(entity);
	}
	
	private void copyDTOToEntity(UsuarioDTO dto, Usuario entity) {
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCpf(dto.getCpf());
	}
}
