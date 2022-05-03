package com.nutriquestion.nutriquestion.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nutriquestion.nutriquestion.dtos.NutricionistaDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaGetIdDTO;
import com.nutriquestion.nutriquestion.dtos.NutricionistaInsertDTO;
import com.nutriquestion.nutriquestion.services.NutricionistaService;

@RestController
@RequestMapping(value = "/usuario/nutricionista")
public class NutricionistaResource {

	@Autowired
	private NutricionistaService nutricionistaService;
	
	@PostMapping
	public ResponseEntity<NutricionistaDTO> insert(@Valid @RequestBody NutricionistaInsertDTO dto) {
		NutricionistaDTO newdto = nutricionistaService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NutricionistaGetIdDTO> findById(@PathVariable Long id) {
		NutricionistaGetIdDTO dto = nutricionistaService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
}
