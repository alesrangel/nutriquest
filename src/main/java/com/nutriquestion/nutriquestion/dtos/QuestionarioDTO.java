package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.nutriquestion.nutriquestion.entities.Avaliacao;
import com.nutriquestion.nutriquestion.entities.Questao;
import com.nutriquestion.nutriquestion.entities.Questionario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	@Column(columnDefinition = "TEXT")
	private String titulo;

	private Avaliacao avaliacao;
	
	private List<Questao> questoes= new ArrayList<>();

	public QuestionarioDTO(Questionario entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		avaliacao = entity.getAvaliacao();
		questoes = entity.getQuestoes();
	}
}
