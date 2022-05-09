package com.nutriquestion.nutriquestion.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nutriquestion.nutriquestion.dtos.NutricionistaDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaGetIdDTO;
import com.nutriquestion.nutriquestion.dtos.PacienteDTO;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Paciente;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.repositories.PacienteRepository;
import com.nutriquestion.nutriquestion.services.exceptions.ResourceNotFoundException;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private NutricionistaService nutricionistaService;
	
	@Autowired
	private NutricionistaRepository nutricionistaRepository;
	
	public PacienteDTO insert(@Valid PacienteDTO dto) {
		Paciente entity = new Paciente();
		String nutriNome  = SecurityContextHolder.getContext().getAuthentication().getName();
		NutricionistaGetIdDTO nutriGet = nutricionistaService.findByNome(nutriNome);
		Optional<Nutricionista> obj = nutricionistaRepository.findById(nutriGet.getId());
		Nutricionista entityNutri = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		NutricionistaDTO nutriDTO = new NutricionistaDTO(entityNutri);
		copyDTOToEntity(dto, entity);
		entity.setNutricionista(NutricionistaDTO.DtoToEntity(nutriDTO));
		entity = pacienteRepository.save(entity);
		return new PacienteDTO(entity);
	}
	
	private void copyDTOToEntity(PacienteDTO dto, Paciente entity) {
		entity.setNomePaciente(dto.getNomePaciente());
		entity.setIdade(dto.getIdade());
		entity.setSexo(dto.getSexo());
	}

}
