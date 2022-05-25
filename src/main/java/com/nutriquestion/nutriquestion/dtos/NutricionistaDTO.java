package com.nutriquestion.nutriquestion.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.nutriquestion.nutriquestion.entities.Nutricionista;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	private String senha;
	
	
	public NutricionistaDTO(Nutricionista entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		crn = entity.getCrn();
		senha = entity.getSenha();
	}
	
	public static Nutricionista DtoToEntity(NutricionistaDTO dto) {
		Nutricionista entity = new Nutricionista();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setCrn(dto.getCrn());
		entity.setSenha(dto.getSenha());
		return entity;
	}
	
}
