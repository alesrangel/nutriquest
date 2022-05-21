package com.nutriquestion.nutriquestion.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nutriquestion.nutriquestion.dtos.RespostaDTO;
import com.nutriquestion.nutriquestion.services.RespostaService;

@RestController
@RequestMapping(value = "/resposta")
@CrossOrigin
public class RespostaResource {

	@Autowired
	private RespostaService respostaService;
	
	@PostMapping(value = "/{idQuestao}")
	public ResponseEntity<RespostaDTO> insert(@PathVariable Long idQuestao ,@Valid @RequestBody RespostaDTO dto) {
		RespostaDTO newdto = respostaService.insert(idQuestao ,dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RespostaDTO> findById(@PathVariable Long id) {
		RespostaDTO dto = respostaService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RespostaDTO> update(@PathVariable Long id, @Valid @RequestBody RespostaDTO dto) {
		RespostaDTO newDto = respostaService.update(id ,dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		respostaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
