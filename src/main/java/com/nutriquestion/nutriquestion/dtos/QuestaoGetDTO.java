package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;

import javax.persistence.Column;

import com.nutriquestion.nutriquestion.entities.Questao;
import com.nutriquestion.nutriquestion.entities.Questionario;
import com.nutriquestion.nutriquestion.entities.Resposta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestaoGetDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	@Column(columnDefinition = "TEXT")
	private String titulo;
	private RespostaDTO resposta;
	
	public QuestaoGetDTO(Questao entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		resposta = new RespostaDTO(entity.getResposta());
	}
	

	public QuestaoGetDTO(Questao entity, Resposta respostaEntity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		resposta = new RespostaDTO(respostaEntity);
	}
}
