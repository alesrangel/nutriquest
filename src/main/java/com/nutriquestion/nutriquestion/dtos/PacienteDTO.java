package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.nutriquestion.nutriquestion.entities.Avaliacao;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.entities.Paciente;
import com.nutriquestion.nutriquestion.entities.Resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	private String nomePaciente;
	private Integer idade;
	@NotBlank
	private String sexo;

	private Nutricionista nutricionista;

	private List<Avaliacao> avaliacoes = new ArrayList<>();
	private List<Resposta> respostas = new ArrayList<>();
	
	
	public PacienteDTO(Paciente entity) {
		id = entity.getId();
		nomePaciente = entity.getNomePaciente();
		idade = entity.getIdade();
		sexo = entity.getSexo();
		nutricionista = entity.getNutricionista();
		avaliacoes = entity.getAvaliacoes();
		respostas = entity.getRespostas();
	}

}
