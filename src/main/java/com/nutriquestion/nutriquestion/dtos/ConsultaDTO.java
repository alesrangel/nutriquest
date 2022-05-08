package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.nutriquestion.nutriquestion.entities.Consulta;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Paciente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomePaciente;
	private String situacaoPaciente;
	private String dataHoraInicio;
	
	private Nutricionista nutricionista;
	
	private Paciente paciente;

	public ConsultaDTO(Consulta entity) {
		id = entity.getId();
		nomePaciente = entity.getNomePaciente();
		situacaoPaciente = entity.getSituacaoPaciente(); 
		dataHoraInicio = entity.getDataHoraInicio();
		nutricionista = entity.getNutricionista();
		paciente = entity.getPaciente();
	}
	
}
