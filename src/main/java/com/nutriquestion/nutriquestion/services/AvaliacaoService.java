package com.nutriquestion.nutriquestion.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.AvaliacaoDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaGetIdDTO;
import com.nutriquestion.nutriquestion.entities.Avaliacao;
import com.nutriquestion.nutriquestion.repositories.AvaliacaoRepository;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.services.exceptions.DatabaseException;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class AvaliacaoService {

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;
	
	@Autowired
	private NutricionistaService nutricionistaService;
	
	@Autowired
	private NutricionistaRepository nutricionistRepository;
	
	@Transactional
	public AvaliacaoDTO insert( Long nutricionistaId, @Valid AvaliacaoDTO dto) {
		Avaliacao entity = new Avaliacao();
		NutricionistaGetIdDTO nutri = nutricionistaService.findById(nutricionistaId);
		NutricionistaDTO nutriDTO = nutricionistaService.findByNomeDTO(nutri.getNome());
		copyDTOToEntity(dto, entity);
		entity.setNutricionistaAvaliacao(nutricionistRepository.findByEmail(nutri.getNome()));
		entity = avaliacaoRepository.save(entity);
		return new AvaliacaoDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<AvaliacaoDTO> findAllPaged(Pageable pageable) {
		Page<Avaliacao> list = avaliacaoRepository.findAll(pageable);
		return list.map(category -> new AvaliacaoDTO(category));
	}
	
	@Transactional(readOnly = true)
	public AvaliacaoDTO findById(Long id) {
		Optional<Avaliacao> obj = avaliacaoRepository.findById(id);
		Avaliacao entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new AvaliacaoDTO(entity);
	}
	
	public AvaliacaoDTO update(@Valid Long id, AvaliacaoDTO dto) {
		try {
			Avaliacao entity = avaliacaoRepository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = avaliacaoRepository.save(entity);
			return new AvaliacaoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found " + id);
		}
	}
	
	private void copyDTOToEntity(AvaliacaoDTO dto, Avaliacao entity) {
		entity.setTipoAvaliacao(dto.getTipoAvaliacao());
		entity.setNutricionistaAvaliacao(dto.getNutricionista());
		entity.setPaciente(dto.getPaciente());
		entity.setQuestionario(dto.getQuestionario());
		entity.setDate(dto.getDate());
	}

	public void delete(Long id) {
		try {
			avaliacaoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}

}
