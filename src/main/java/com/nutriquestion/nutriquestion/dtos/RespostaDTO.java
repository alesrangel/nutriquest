package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;

import javax.persistence.Column;

import com.nutriquestion.nutriquestion.entities.Resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String resposta;
	
	private PacienteDTO paciente;
	
	public RespostaDTO(Resposta entity) {
		id = entity.getId();
		resposta = entity.getResposta();
		paciente = new PacienteDTO(entity.getPaciente());
	}
}
