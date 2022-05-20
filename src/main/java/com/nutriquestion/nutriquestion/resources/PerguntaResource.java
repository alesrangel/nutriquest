//package com.nutriquestion.nutriquestion.resources;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nutriquestion.nutriquestion.dtos.QuestaoDTO;
//import com.nutriquestion.nutriquestion.services.QuestaoService;
//
//@RestController
//@RequestMapping(value = "/pergunta")
//@CrossOrigin
//public class PerguntaResource {
//
//	@Autowired
//	private QuestaoService questaoService;
//	
//	@GetMapping(value = "/{idQuestionario}")
//	public ResponseEntity<List<QuestaoDTO>> findAllQuestionario(@PathVariable Long idQuestionario){
//		List<QuestaoDTO> listDTO = questaoService.findAllQuestionario();
//		return ResponseEntity.ok().body(listDTO);
//	}
//}
