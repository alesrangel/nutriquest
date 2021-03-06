package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;

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
public class NutricionistaGetIdDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotBlank(message = "Campo obrigatorio")
	private String nome;
	@Email(message = "Usar um email valido")
	private String email;
	@NotBlank(message = "Campo obrigatorio")
	private String crn;	

	public NutricionistaGetIdDTO(Nutricionista entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		crn = entity.getCrn();
	}
	
}
