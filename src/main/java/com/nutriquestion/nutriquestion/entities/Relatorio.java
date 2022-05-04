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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tb_relatorio")
public class Relatorio implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	@Column(length = 10000)
	private String observacoes;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_relatorio_avaliacao",
			joinColumns = @JoinColumn(name = "relatorio_id"),
			inverseJoinColumns = @JoinColumn(name = "avaliacao_id"))
	private List<Avaliacao> historicoAvaliacao = new ArrayList<>();

	
}
