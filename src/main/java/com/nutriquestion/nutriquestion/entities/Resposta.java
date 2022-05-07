package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_resposta")
public class Resposta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@Column(columnDefinition = "TEXT")
	private String resposta;

	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@OneToOne(mappedBy = "resposta")
	private Questao questao;
}
