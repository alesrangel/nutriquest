package com.nutriquestion.nutriquestion.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Table;

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
@Table(name = "tb_consulta")
public class Consulta {

	private Long id;
	private String nomePaciente;
	private Instant dataHora;
	private String situacaoPaciente;
}
