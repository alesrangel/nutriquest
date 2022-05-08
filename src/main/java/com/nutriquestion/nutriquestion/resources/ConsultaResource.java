package com.nutriquestion.nutriquestion.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nutriquestion.nutriquestion.dtos.ConsultaDTO;
import com.nutriquestion.nutriquestion.services.ConsultaService;

@RestController
@RequestMapping(value = "/consulta")
public class ConsultaResource {

	
	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	public ResponseEntity<ConsultaDTO> insert(@Valid @RequestBody ConsultaDTO dto){
		ConsultaDTO newdto = consultaService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
}
