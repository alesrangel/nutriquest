package com.nutriquestion.nutriquestion.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.NutricionistaDTO;
import com.nutriquestion.nutriquestion.dtos.PacienteDTO;
import com.nutriquestion.nutriquestion.dtos.QuestionarioDTO;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Paciente;
import com.nutriquestion.nutriquestion.entities.Questionario;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.repositories.PacienteRepository;
import com.nutriquestion.nutriquestion.services.exceptions.DatabaseException;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private NutricionistaService nutricionistaService;
	
	@Autowired
	private NutricionistaRepository nutricionistaRepository;
	
	@Transactional
	public PacienteDTO insert(Long id, @Valid PacienteDTO dto) {
		Paciente entity = new Paciente();
//		NutricionistaGetIdDTO nutriGet = nutricionistaService.findById(null);
		Optional<Nutricionista> obj = nutricionistaRepository.findById(id);
		Nutricionista entityNutri = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		NutricionistaDTO nutriDTO = new NutricionistaDTO(entityNutri);
		copyDTOToEntity(dto, entity);
		entity.setNutricionista(NutricionistaDTO.DtoToEntity(nutriDTO));
		entity.setArquivado(false);
		entity = pacienteRepository.save(entity);
		return new PacienteDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public PacienteDTO findById(Long id) {
		Optional<Paciente> obj = pacienteRepository.findById(id);
		Paciente entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PacienteDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PacienteDTO> findByNutricionista(Long nutricionistaId) {
		List<Paciente> list = pacienteRepository.findByNutricionista(nutricionistaId);
		return list.stream().map(x -> new PacienteDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<PacienteDTO> findArquivados() {
		List<Paciente> list = pacienteRepository.findArquivados();
		return list.stream().map(x -> new PacienteDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<QuestionarioDTO> questionarioRespondidoPorPaciente(Long idPaciente) {
		List<Questionario> list = pacienteRepository.findRespondidosPorUsuariio(idPaciente);
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<PacienteDTO> findAll() {
		List<Paciente> list = pacienteRepository.findAll();
		return list.stream().map(x -> new PacienteDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public PacienteDTO update(Long id, PacienteDTO dto) {
		try {
			Paciente entity = pacienteRepository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = pacienteRepository.save(entity);
			return new PacienteDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found " + id);
		}
	}
	
	@Transactional
	public PacienteDTO arquivar(Long id) {
		try {
			Paciente entity = pacienteRepository.getById(id);
			entity.setArquivado(true);
			entity = pacienteRepository.save(entity);
			return new PacienteDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Not Found " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			pacienteRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void copyDTOToEntity(PacienteDTO dto, Paciente entity) {
		entity.setNomePaciente(dto.getNomePaciente());
		entity.setIdade(dto.getIdade());
		entity.setSexo(dto.getSexo());
	}

	



}
