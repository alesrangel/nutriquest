package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.nutriquestion.nutriquestion.dtos.QuestionarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_questionario")
public class Questionario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String titulo;
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dataCriacao;

	@OneToOne(mappedBy = "questionario", fetch = FetchType.LAZY)
	private Avaliacao avaliacao;
	
	@OneToMany(mappedBy = "questionario",fetch = FetchType.EAGER,  orphanRemoval = true)
	private List<Questao> questoes = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "nutricionista_id")
	private Nutricionista nutricionista;
	
	
	public Questionario(QuestionarioDTO dto) {
		this.titulo = dto.getTitulo();
	}
	
	@PrePersist
	public void prePersist() {
		dataCriacao = Instant.now();
	}
}
