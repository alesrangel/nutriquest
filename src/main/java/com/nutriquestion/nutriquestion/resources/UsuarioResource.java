package com.nutriquestion.nutriquestion.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nutriquestion.nutriquestion.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	
}
