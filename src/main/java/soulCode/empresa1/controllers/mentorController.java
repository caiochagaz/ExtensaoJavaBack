package soulCode.empresa1.controllers;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresa1.models.Mentor;
import soulCode.empresa1.models.Cargos;
import soulCode.empresa1.services.MentorService;

@CrossOrigin
@RestController
@RequestMapping("empresa")
public class mentorController {

		//INJECAO DE DEPENDENCIA
	
		@Autowired
		private MentorService mentorService;
		
		
		//////////////////////////////////////GET/////////////////////////////////////
		
		@GetMapping("/mentor")
		public List<Mentor> mostrarTodosMentores(){
			List<Mentor> mentor = mentorService.mostrarTodosMentores();
			return  mentor;
		}
		
		@GetMapping("/mentor/{id_mentor}")
		public ResponseEntity<Mentor> mostrarUmMentor(@PathVariable Integer id_mentor) {
			Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
			return ResponseEntity.ok().body(mentor);
		}
		
		@GetMapping("/mentor-cargo/{id_cargo}")
		public ResponseEntity<Mentor> buscarMentorDoCargo(@PathVariable Integer id_cargo){
			Mentor mentor = mentorService.buscarMentorDoCargo(id_cargo);
			return ResponseEntity.ok().body(mentor);
		}
		
		@GetMapping("/mentorSemCargo")
		public List<Mentor> mentorSemCargo(){
			List<Mentor> mentor= mentorService.mentorSemCargo();
			return mentor;
		}
		
		@GetMapping("/mentor/mentor-cargo")
		public List<List> mentorComCargo(){
			List<List> mentorCargo = mentorService.MentorComSeuCargo();
			return mentorCargo;
		}
		
		// BUSCAR O MENTOR PELO CPF
		@GetMapping("/mentor-cpf/{mentor_cpf}")
		public ResponseEntity<Mentor> buscarMentorPeloCpf(@PathVariable String mentor_cpf){
			Mentor mentor = mentorService.buscarMentorPeloCpf(mentor_cpf);
			return ResponseEntity.ok().body(mentor);
		}
		
		///////////////////////////////////////////POST///////////////////////////////////////////
		
		@PostMapping("/mentor")
		public ResponseEntity<Mentor> InserirMentorComCargo(@RequestParam(value="cargo", required = false)Integer id_cargo,@RequestBody Mentor mentor){
			mentor = mentorService.InserirMentor(id_cargo, mentor);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(mentor.getId_mentor()).toUri();
			return ResponseEntity.created(uri).build();
		}
		
		//////////////////////////////////////////PUT/////////////////////////////////////////////
		
		@PutMapping("/mentor/{id_mentor}")
		public ResponseEntity<Mentor> editarMentor(@RequestParam(value="cargo")Cargos cargos, @PathVariable Integer id_mentor, @RequestBody Mentor mentor){
			mentor.setId_mentor(id_mentor);
			mentor.setCargos(cargos);
			cargos.setMentor(mentor);
			mentor = mentorService.editarMentor(mentor);
			
			return ResponseEntity.noContent().build();
		}		
		
		@PutMapping("/mentorSemCargo/{id_mentor}")
		public ResponseEntity<Void> atualizarMentor(@PathVariable Integer id_mentor, @RequestBody Mentor mentor) {
			mentor.setId_mentor(id_mentor);
			mentor = mentorService.editarMentor(mentor);
			return ResponseEntity.noContent().build();
		}
		
		////////////////////////////////////////DELETE////////////////////////////////////////////
		
		
		@DeleteMapping("/mentor/{id_mentor}")
		// MÉTODO DELETE by ID
		public ResponseEntity<Void> deletarMentor(@PathVariable Integer id_mentor) {
			mentorService.deletarMentor(id_mentor);
			return ResponseEntity.noContent().build(); // PQ TEM RETORNO SE É VOID??
		}
		
		
	}


