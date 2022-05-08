package com.nutriquestion.nutriquestion.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nutriquestion.nutriquestion.dtos.NutricionistaInsertDTO;
import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.resources.exceptions.FieldMessage;

public class NutricionistaInsertValidator implements ConstraintValidator<NutricionistaInserValid, NutricionistaInsertDTO> {
	
	@Autowired
	private NutricionistaRepository repository;
	
	@Override
	public void initialize(NutricionistaInserValid ann) {}

	@Override
	public boolean isValid(NutricionistaInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
	
		Nutricionista nutricionista = repository.findByEmail(dto.getEmail());
		if(nutricionista != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}