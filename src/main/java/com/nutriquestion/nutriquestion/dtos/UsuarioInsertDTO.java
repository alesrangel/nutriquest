package com.nutriquestion.nutriquestion.dtos;

import com.nutriquestion.nutriquestion.services.validation.UsuarioInserValid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@UsuarioInserValid
public class UsuarioInsertDTO extends UsuarioDTO{
	private static final long serialVersionUID = 1L;
	
	private String senha;
	
	public UsuarioInsertDTO() {
		super();
	}
}
