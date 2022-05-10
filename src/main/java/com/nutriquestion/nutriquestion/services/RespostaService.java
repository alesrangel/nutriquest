package com.nutriquestion.nutriquestion.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.NutricionistaDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaGetIdDTO;
import com.nutriquestion.nutriquestion.dtos.RespostaDTO;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Resposta;
import com.nutriquestion.nutriquestion.repositories.RespostaRepository;
import com.nutriquestion.nutriquestion.services.exceptions.DatabaseException;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class RespostaService {
	
	@Autowired
	private RespostaRepository respostaRepository;

	@Transactional
	public RespostaDTO insert(@Valid RespostaDTO dto) {
		Resposta entity = new Resposta();
		copyDTOToEntity(dto, entity);
		entity = respostaRepository.save(entity);
		return new RespostaDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public RespostaDTO findById(Long id) {
		Optional<Resposta> obj = respostaRepository.findById(id);
		Resposta entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new RespostaDTO(entity);
	}
	
	@Transactional
	public RespostaDTO update(Long id, RespostaDTO dto) {
		try {
			Resposta entity = respostaRepository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = respostaRepository.save(entity);
			return new RespostaDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			respostaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDTOToEntity(RespostaDTO dto, Resposta entity) {
		entity.setPaciente(dto.getPaciente());
		entity.setQuestao(dto.getQuestao());
		entity.setResposta(dto.getResposta());
	}

}
