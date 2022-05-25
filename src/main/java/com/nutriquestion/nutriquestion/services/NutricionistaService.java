package com.nutriquestion.nutriquestion.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.NutricionistaDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaGetIdDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaInsertDTO;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.services.exceptions.DatabaseException;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class NutricionistaService implements UserDetailsService{
	
	private static Logger logger = LoggerFactory.getLogger(NutricionistaService.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	private AuthService authService;
	
	@Autowired
	private NutricionistaRepository repository;
	
	@Transactional
	public NutricionistaDTO insert(NutricionistaInsertDTO dto) {
		Nutricionista entity = new Nutricionista();
		copyDTOToEntity(dto, entity);
//		entity.setSenha(passwordEncoder.encode(dto.getSenha()));
		entity.setSenha(dto.getSenha());
		entity = repository.save(entity);
		return new NutricionistaDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public NutricionistaGetIdDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		Optional<Nutricionista> obj = repository.findById(id);
		Nutricionista entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new NutricionistaGetIdDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public NutricionistaGetIdDTO findByNome(String nome) {
		Nutricionista entity = repository.findByEmail(nome);
		return new NutricionistaGetIdDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public NutricionistaDTO findByNomeDTO(String nome) {
		Nutricionista entity = repository.findByEmail(nome);
		return new NutricionistaDTO(entity);
	}

	@Transactional
	public NutricionistaDTO update(Long id, NutricionistaDTO dto) {
		try {
			Nutricionista entity = repository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = repository.save(entity);
			return new NutricionistaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDTOToEntity(NutricionistaDTO dto, Nutricionista entity) {
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCrn(dto.getCrn());
		if(!dto.getSenha().isEmpty())
			entity.setSenha(dto.getSenha());
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

	@Transactional(readOnly = true)
	public NutricionistaGetIdDTO loginEmailSenha(String email, String senha) {
		Optional<Nutricionista> obj = repository.findEmailSenha(email, senha);
		Nutricionista entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new NutricionistaGetIdDTO(entity);
	}
}
