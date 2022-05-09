package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_paciente")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nomePaciente;
	private Integer idade;
	private String sexo;

	@ManyToOne
	@JoinColumn(name = "nutricionista_id")
	private Nutricionista nutricionista;

	@OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
	private List<Avaliacao> avaliacoes = new ArrayList<>();

	@OneToMany(mappedBy = "", fetch = FetchType.LAZY)
	private List<Resposta> respostas = new ArrayList<>();

}
