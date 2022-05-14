package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.nutriquestion.nutriquestion.dtos.QuestaoDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tb_questao")
public class Questao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(columnDefinition = "TEXT")
	private String titulo;
	
	@ManyToOne
	@JoinColumn(name = "questionario_id")
	private Questionario questionario;
	
	@OneToOne
	@JoinColumn(name = "resposta_id")
	private Resposta resposta;
	
	public Questao (QuestaoDTO questDTO) {
		this.titulo = questDTO.getTitulo();
//		this.questionario = new Questionario(questDTO.getQuestionario());
//		this.questionario.setTitulo(questDTO.getQuestionario().getTitulo());
//		this.questionario.setId(questDTO.getQuestionario().getId());
	}
}
