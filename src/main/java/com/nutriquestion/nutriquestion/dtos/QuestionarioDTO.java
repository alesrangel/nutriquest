package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	
	private Instant dataCriacao;

	private Avaliacao avaliacao;
	
	private List<QuestaoDTO> questoes= new ArrayList<>();
	
	public QuestionarioDTO(long id, String titulo) {
		this.id = id;
		this.titulo = titulo;
	}

	public QuestionarioDTO(Questionario entity) {
		id = entity.getId();
		titulo = entity.getTitulo();
		dataCriacao = entity.getDataCriacao();
		avaliacao = entity.getAvaliacao();
	}
	
	public QuestionarioDTO(Questionario entity, List<Questao> questoes) {
		this(entity);
		questoes.forEach(questao -> this.questoes.add(new QuestaoDTO(questao)));
	}
}
