package com.nutriquestion.nutriquestion.dtos;

import com.nutriquestion.nutriquestion.services.validation.NutricionistaInserValid;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NutricionistaInserValid
public class NutricionistaInsertDTO extends NutricionistaDTO{
	private static final long serialVersionUID = 1L;
	
	private String senha;
	
	public NutricionistaInsertDTO() {
		super();
	}
}
