package com.nutriquestion.nutriquestion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.dtos.ConsultaDTO;
import com.nutriquestion.nutriquestion.entities.Consulta;
import com.nutriquestion.nutriquestion.repositories.ConsultaRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Transactional
	public ConsultaDTO insert(ConsultaDTO dto) {
		Consulta entity = new Consulta();
		copyDTOToEntity(dto, entity);
		entity = consultaRepository.save(entity);
		return new ConsultaDTO(entity);
	}
	
	
	private void copyDTOToEntity(ConsultaDTO dto, Consulta entity) {
		entity.setNomePaciente(dto.getNomePaciente());
		entity.setSituacaoPaciente(dto.getSituacaoPaciente());
		entity.setDataHoraInicio(dto.getDataHoraInicio());
		entity.setNutricionista(dto.getNutricionista());
		entity.setPaciente(dto.getPaciente());

	}
}
