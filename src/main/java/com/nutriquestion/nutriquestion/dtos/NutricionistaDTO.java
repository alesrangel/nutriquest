package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.nutriquestion.nutriquestion.entities.Nutricionista;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutricionistaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo obrigatorio")
	private String nome;
	@Email(message = "Usar um email valido")
	private String email;
	@NotBlank(message = "Campo obrigatorio")
	private String crn;	
	
	
	public NutricionistaDTO(Nutricionista entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		crn = entity.getCrn();
	}
	
}
