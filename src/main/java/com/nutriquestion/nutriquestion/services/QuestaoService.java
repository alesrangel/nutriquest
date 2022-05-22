package com.nutriquestion.nutriquestion.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.QuestaoDTO;
import com.nutriquestion.nutriquestion.dtos.QuestaoGetDTO;
import com.nutriquestion.nutriquestion.entities.Questao;
import com.nutriquestion.nutriquestion.entities.Resposta;
import com.nutriquestion.nutriquestion.repositories.QuestaoRepository;
import com.nutriquestion.nutriquestion.services.exceptions.DatabaseException;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class QuestaoService {

	@Autowired
	private QuestaoRepository questaoRepository;
	
	@Transactional
	public QuestaoDTO insert(QuestaoDTO dto) {
		Questao entity = new Questao();
		copyDTOToEntity(dto, entity);
		entity = questaoRepository.save(entity);
		return new QuestaoDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public QuestaoDTO findById(Long id) {
		Optional<Questao> obj = questaoRepository.findById(id);
		Questao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new QuestaoDTO(entity);
	}
	
	@Transactional
	public QuestaoDTO update(Long id, QuestaoDTO dto) {
		try {
			Questao entity = questaoRepository.getOne(id);
			copyDTOToEntityUpdate(dto, entity);
			entity = questaoRepository.save(entity);
			return new QuestaoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			questaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	@Transactional(readOnly = true)
	public List<QuestaoDTO> findAllQuestionario(Long idQuestionario) {
		List<Questao>list = questaoRepository.findByQestaoQuestionario(idQuestionario);
		return list.stream().map(x -> new QuestaoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public List<QuestaoGetDTO> questoesRespondidas(Long idQuestionario) {
		List<Questao> list = questaoRepository.findByQestaoQuestionario(idQuestionario);
		return list.stream().map(x -> new QuestaoGetDTO(x)).collect(Collectors.toList());
	}
	
	private void copyDTOToEntity(QuestaoDTO dto, Questao entity) {
		entity.setId(dto.getId());
		entity.setTitulo(dto.getTitulo());
//		entity.setResposta(dto.getResposta());
//		entity.setQuestionario(dto.getQuestionario());
	}
	
	private void copyDTOToEntityUpdate(QuestaoDTO dto, Questao entity) {
		entity.setId(dto.getId());
		entity.setTitulo(dto.getTitulo());
	}

}
