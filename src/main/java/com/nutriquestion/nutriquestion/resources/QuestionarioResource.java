package com.nutriquestion.nutriquestion.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nutriquestion.nutriquestion.dtos.QuestionarioDTO;
import com.nutriquestion.nutriquestion.services.QuestionarioService;

@RestController
@RequestMapping(value = "/questionario")
public class QuestionarioResource {

	@Autowired
	private QuestionarioService questionarioService;
	
	@PostMapping
	public ResponseEntity<QuestionarioDTO> insert(@Valid @RequestBody QuestionarioDTO dto) {
		QuestionarioDTO newdto = questionarioService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<QuestionarioDTO> findById(@PathVariable Long id) {
		QuestionarioDTO dto = questionarioService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<QuestionarioDTO> update(@PathVariable Long id, @Valid @RequestBody QuestionarioDTO dto) {
		QuestionarioDTO newDto = questionarioService.update(id ,dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		questionarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
