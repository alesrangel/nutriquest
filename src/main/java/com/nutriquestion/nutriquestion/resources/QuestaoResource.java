package com.nutriquestion.nutriquestion.resources;

import java.net.URI;
import java.util.List;

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

import com.nutriquestion.nutriquestion.dtos.QuestaoDTO;
import com.nutriquestion.nutriquestion.dtos.QuestaoGetDTO;
import com.nutriquestion.nutriquestion.services	.QuestaoService;

@RestController
@RequestMapping(value = "/questao")
@CrossOrigin
public class QuestaoResource {

	@Autowired
	private QuestaoService questaoService;
	
	@PostMapping
	public ResponseEntity<QuestaoDTO> insert(@Valid @RequestBody QuestaoDTO dto) {
		QuestaoDTO newdto = questaoService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<QuestaoDTO> findById(@PathVariable Long id) {
		QuestaoDTO dto = questaoService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<QuestaoDTO> update(@PathVariable Long id, @Valid @RequestBody QuestaoDTO dto) {
		QuestaoDTO newDto = questaoService.update(id ,dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		questaoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/findAll/{idQuestionario}")
	public ResponseEntity<List<QuestaoDTO>> findAllQuestionario(@PathVariable Long idQuestionario){
		List<QuestaoDTO> listDTO = questaoService.findAllQuestionario(idQuestionario);
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/findAll/respostas/{idQuestionario}")
	public ResponseEntity<List<QuestaoGetDTO>> questoesRespondidas(@PathVariable Long idQuestionario){
		List<QuestaoGetDTO> listDTO = questaoService.questoesRespondidas(idQuestionario);
		return ResponseEntity.ok().body(listDTO);
	}
}
