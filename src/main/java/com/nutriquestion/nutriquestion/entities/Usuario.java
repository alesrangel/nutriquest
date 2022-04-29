package com.nutriquestion.nutriquestion.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.nutriquestion.nutriquestion.dtos.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario {

	private Long id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
}
