package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_consulta")
public class Consulta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank 
	private String nomePaciente;
	@NotBlank
	private String situacaoPaciente;
	
//	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private String dataHoraInicio;
	
	@ManyToOne
	@JoinColumn(name = "nutricionista_id")
	private Nutricionista nutricionista;
	
	@OneToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
}
