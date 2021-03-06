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

import com.nutriquestion.nutriquestion.dtos.PacienteDTO;
import com.nutriquestion.nutriquestion.dtos.QuestionarioDTO;
import com.nutriquestion.nutriquestion.services.PacienteService;

@RestController
@RequestMapping(value = "/paciente")
@CrossOrigin
public class PacienteResource {

	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping(value = "/{nutricionistaId}")
	public ResponseEntity<PacienteDTO> insert(@PathVariable Long nutricionistaId, @Valid @RequestBody PacienteDTO dto) {
		PacienteDTO newdto = pacienteService.insert(nutricionistaId,dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newdto.getId()).toUri();
		return ResponseEntity.created(uri).body(newdto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
		PacienteDTO dto = pacienteService.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/findByNutricionista/{nutricionistaId}")
	public ResponseEntity<List<PacienteDTO>> findByNutricionista(@PathVariable Long nutricionistaId) {
		List<PacienteDTO> dto = pacienteService.findByNutricionista(nutricionistaId);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/arquivados/{idNutricionista}")
	public ResponseEntity<List<PacienteDTO>> findArquivadosNutricionista(@PathVariable Long idNutricionista) {
		List<PacienteDTO> dto = pacienteService.findArquivados(idNutricionista);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/desarquivados/{pacienteId}")
	public ResponseEntity<PacienteDTO> desarquivar(@PathVariable Long pacienteId) {
		PacienteDTO dto = pacienteService.desarquivar(pacienteId);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/arquivar/{id}")
	public ResponseEntity<PacienteDTO> arquivar(@PathVariable Long id) {
		PacienteDTO newDto = pacienteService.arquivar(id);
		return ResponseEntity.ok().body(newDto);
	}
	
	
	@GetMapping
	public ResponseEntity<List<PacienteDTO>> findAll() {
		List<PacienteDTO> listDtos = pacienteService.findAll();
		return ResponseEntity.ok().body(listDtos);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @Valid @RequestBody PacienteDTO dto) {
		PacienteDTO newDto = pacienteService.update(id ,dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		pacienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/respondidos/{idPaciente}")
	public ResponseEntity<List<QuestionarioDTO>> findArquivados(@PathVariable Long idPaciente) {
		List<QuestionarioDTO> dto = pacienteService.questionarioRespondidoPorPaciente(idPaciente);
		return ResponseEntity.ok().body(dto);
	}
	
	
}
