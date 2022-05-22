package com.nutriquestion.nutriquestion.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.RespostaDTO;
import com.nutriquestion.nutriquestion.entities.Paciente;
import com.nutriquestion.nutriquestion.entities.Questao;
import com.nutriquestion.nutriquestion.entities.Resposta;
import com.nutriquestion.nutriquestion.repositories.PacienteRepository;
import com.nutriquestion.nutriquestion.repositories.QuestaoRepository;
import com.nutriquestion.nutriquestion.repositories.RespostaRepository;
import com.nutriquestion.nutriquestion.services.exceptions.DatabaseException;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class RespostaService {
	
	@Autowired
	private RespostaRepository respostaRepository;
	
	@Autowired
	private QuestaoRepository questaoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	

	@Transactional
	public RespostaDTO insert(Long idQuestao, Long idPaciente, @Valid RespostaDTO dto) {
		Resposta entity = new Resposta();
		Optional<Paciente> objPaciente = pacienteRepository.findById(idPaciente);
		Paciente pacienteEntity = objPaciente.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		copyDTOToEntity(dto, entity);
		entity.setPaciente(pacienteEntity);
		entity = respostaRepository.save(entity);
		Optional<Questao> obj = questaoRepository.findById(idQuestao);
		Questao questaoEntity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		questaoEntity.setResposta(entity);
		
		questaoRepository.save(questaoEntity);
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
		entity.setResposta(dto.getResposta());
	}

}
