package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.nutriquestion.nutriquestion.entities.Questao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String titulo;

	private QuestionarioDTO questionario;
//	
//	private Resposta resposta;
	
	public QuestaoDTO(Questao entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		questionario = new QuestionarioDTO(entity.getQuestionario());
//		resposta = entity.getResposta();
	}
	

}
