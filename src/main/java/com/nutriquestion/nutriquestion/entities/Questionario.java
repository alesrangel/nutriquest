package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_questionario")
public class Questionario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String titulo;

	@OneToOne(mappedBy = "paciete", fetch = FetchType.LAZY)
	private Consulta consulta;

	@OneToOne(mappedBy = "questionario", fetch = FetchType.LAZY)
	private Avaliacao avaliacao;
	
	@OneToMany(mappedBy = "questionario")
	private List<Questao> questoes= new ArrayList<>();
}
