package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_resposta")
public class Resposta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
//	private Questionario questionarioId;
////	@EmbeddedId
//	private Questao questaoId;
	@Column(length = 5000)
	private String resposta;
}
