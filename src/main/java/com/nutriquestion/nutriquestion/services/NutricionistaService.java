package com.nutriquestion.nutriquestion.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.NutricionistaDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaGetIdDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaInsertDTO;
import com.nutriquestion.nutriquestion.dtos.RoleDTO;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Role;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.repositories.RoleRepository;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class NutricionistaService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(NutricionistaService.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private NutricionistaRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;

	
	@Transactional
	public NutricionistaDTO insert(NutricionistaInsertDTO dto) {
		Nutricionista entity = new Nutricionista();
		copyDTOToEntity(dto, entity);
		entity.setSenha(passwordEncoder.encode(dto.getSenha()));
		entity = repository.save(entity);
		return new NutricionistaDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public NutricionistaGetIdDTO findById(Long id) {
		Optional<Nutricionista> obj = repository.findById(id);
		Nutricionista entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new NutricionistaGetIdDTO(entity);
	}

	
	private void copyDTOToEntity(NutricionistaInsertDTO dto, Nutricionista entity) {
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCrn(dto.getCrn());
		
		entity.getRoles().clear();
		for(RoleDTO roleDTO : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDTO.getId());
			entity.getRoles().add(role);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Nutricionista nutricionista = repository.findByEmail(username);
		if(nutricionista == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User found: " + username);
		return (UserDetails) nutricionista;
	}
}
