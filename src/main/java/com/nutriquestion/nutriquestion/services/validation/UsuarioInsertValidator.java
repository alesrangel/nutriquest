package com.nutriquestion.nutriquestion.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.nutriquestion.nutriquestion.dtos.UsuarioInsertDTO;
import com.nutriquestion.nutriquestion.entities.Usuario;
import com.nutriquestion.nutriquestion.repositories.UsuarioRepository;
import com.nutriquestion.nutriquestion.resources.exceptions.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInserValid, UsuarioInsertDTO> {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public void initialize(UsuarioInserValid ann) {
	}

	@Override
	public boolean isValid(UsuarioInsertDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
	
		Usuario user = repository.findByEmail(dto.getEmail());
		if(user != null) {
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