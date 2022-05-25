package com.nutriquestion.nutriquestion.services;

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
import com.nutriquestion.nutriquestion.dtos.QuestionarioDTO;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Questao;
import com.nutriquestion.nutriquestion.entities.Questionario;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.repositories.QuestaoRepository;
import com.nutriquestion.nutriquestion.repositories.QuestionarioRepository;
import com.nutriquestion.nutriquestion.services.exceptions.DatabaseException;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class QuestionarioService {

	@Autowired
	private QuestionarioRepository questionarioRepository;
	
	@Autowired
	private QuestaoRepository questaoRepository;
	
	@Autowired
	private NutricionistaRepository nutricionistaRepository;
	
	@Transactional
	public QuestionarioDTO insert(Long nutricionistaId, QuestionarioDTO dto) {
		Questionario entity = new Questionario();
		Nutricionista  nutriEntity = nutricionistaRepository.getOne(nutricionistaId);
		entity.setNutricionista(nutriEntity);
		copyDTOToEntity(dto, entity);
		entity = questionarioRepository.save(entity);
		return new QuestionarioDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<QuestaoDTO>  findByQestaoQuestionario(Long id) {
		List<Questao>list = questionarioRepository.findByQestaoQuestionario(id);
		return list.stream().map(x -> new QuestaoDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public QuestionarioDTO update(Long id, QuestionarioDTO dto) {
		try {
			Questionario entity = questionarioRepository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = questionarioRepository.save(entity);
			return new QuestionarioDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found " + id);
		}
	}
	
	@Transactional(readOnly = true)
	public List<QuestionarioDTO> findAll(Long nutricionistaId) {
		List<Questionario> list = questionarioRepository.findAllNutricionista(nutricionistaId);
		return list.stream().map(x -> new QuestionarioDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<QuestaoDTO> findQuestionario(Long nutricionistaId) {
		List<Questao> list = questionarioRepository.findQuestionario(nutricionistaId);
		return list.stream().map(x -> new QuestaoDTO(x)).collect(Collectors.toList());
	}
	
	public void delete(Long id) {
		try {
			questionarioRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDTOToEntity(QuestionarioDTO dto, Questionario entity) {
		entity.setId(dto.getId());
		entity.setTitulo(dto.getTitulo());
		entity.setAvaliacao(dto.getAvaliacao());
		
		entity.getQuestoes().clear();
		for(QuestaoDTO questDTO : dto.getQuestoes()) {
			Questao questao = new Questao();
			questao.setTitulo(questDTO.getTitulo());
			questao.setQuestionario(entity);
			questaoRepository.save(questao);
			entity.getQuestoes().add(questao);
			entity.getQuestoes().add(new Questao(questDTO));
		}
	}

}
