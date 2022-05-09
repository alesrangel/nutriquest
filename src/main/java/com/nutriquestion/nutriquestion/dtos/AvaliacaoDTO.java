package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;
import java.time.Instant;

import com.nutriquestion.nutriquestion.entities.Avaliacao;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Paciente;
import com.nutriquestion.nutriquestion.entities.Questionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String tipoAvaliacao;
	private Instant date;
	
	private Paciente paciente;
	
	private Questionario questionario;
	
	private Nutricionista nutricionista;
	

	public AvaliacaoDTO(Avaliacao entity) {
		id = entity.getId();
		tipoAvaliacao = entity.getTipoAvaliacao();
		date = entity.getDate();
	}

}
