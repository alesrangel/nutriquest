package com.nutriquestion.nutriquestion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nutriquestion.nutriquestion.entities.Nutricionista;
import com.nutriquestion.nutriquestion.repositories.NutricionistaRepository;
import com.nutriquestion.nutriquestion.services.exceptions.ForBiddenException;
import com.nutriquestion.nutriquestion.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private NutricionistaRepository nutricionistaRepository;

	@Transactional(readOnly = true)
	public Nutricionista authenticated() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return nutricionistaRepository.findByEmail(username);
		} catch (Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
		
	}

	public void validateSelfOrAdmin(Long userId) {
		Nutricionista user = authenticated();
		if(!user.getId().equals(userId)) {
			throw new ForBiddenException("Access danied");
		}
	}
}
