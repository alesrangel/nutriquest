package com.nutriquestion.nutriquestion.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "tb_nutricionista")
public class Nutricionista implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Column(unique = true)
	private String email;
	private String senha;
	@Column(unique = true)
	private String crn;
	
	@OneToMany(mappedBy = "nutricionista", fetch = FetchType.LAZY)
	private List<Paciente> pacientes = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_relatorios_nutricionista", 
			joinColumns = @JoinColumn(name = "relatorio_id"),
			inverseJoinColumns = @JoinColumn(name = "nutricionista_id"))
	private Set<Relatorio> relarios = new HashSet<>();
	
	@OneToMany(mappedBy = "nutricionistaAvaliacao", fetch = FetchType.LAZY)
	private List<Avaliacao> Avaliacoes = new ArrayList<>();
	
	@OneToMany(mappedBy = "nutricionista")
	private List<Questionario> questionarios= new ArrayList<>();
	
}
