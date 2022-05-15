package com.nutriquestion.nutriquestion.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.nutriquestion.nutriquestion.dtos.AvaliacaoDTO;
import com.nutriquestion.nutriquestion.services.AvaliacaoService;

@RestController
@RequestMapping(value = "/avaliacao")
public class AvaliacaoResource {

	@Autowired
	private AvaliacaoService avaliacaoService;

	@PostMapping(value = "/{nutricionistaId}")
	public ResponseEntity<AvaliacaoDTO> insert(@PathVariable Long nutricionistaId, @Valid @RequestBody AvaliacaoDTO dto){
		AvaliacaoDTO newdto = avaliacaoService.insert(nutricionistaId, dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
	
	@GetMapping
	public ResponseEntity<Page<AvaliacaoDTO>> findAll(Pageable pageable) {
		Page<AvaliacaoDTO> list = avaliacaoService.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AvaliacaoDTO> findById(@PathVariable Long id) {
		AvaliacaoDTO dto = avaliacaoService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AvaliacaoDTO> update(@Valid @PathVariable Long id, @RequestBody AvaliacaoDTO dto) {
		dto = avaliacaoService.update(id ,dto);
		return ResponseEntity.ok().body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		avaliacaoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
