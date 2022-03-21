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

import soulCode.empresa1.models.Cargos;
import soulCode.empresa1.services.CargoService;
import soulCode.empresa1.models.Mentor;

@CrossOrigin
@RestController
@RequestMapping("empresa")
public class cargoController {
	
	/////////////////////////////////////AUTOWIRED///////////////////////////////////////////
	
	@Autowired
	private CargoService cargoService;
	
	/////////////////////////////////////GETMAPPING//////////////////////////////////////////
	
	//MOSTRAR TODOS OS CARGOS
	@GetMapping("/cargo")
	public List<Cargos>mostrarTodosCargos(){
		List<Cargos> cargo = cargoService.mostrarTodosCargos();
		return cargo;
	}
	
	//BUSCAR UM CARGO
	@GetMapping("/cargo/{id_cargo}")
	public ResponseEntity<Cargos> buscarUmCargo(@PathVariable Integer id_cargo){
		Cargos cargo  = cargoService.buscarUmCargo(id_cargo);
		return ResponseEntity.ok().body(cargo);
	}
	
	//CARGO SEM MENTOR
	@GetMapping("/cargoSemMentor")
	public List<Cargos> mentorSemCargo(){
		List<Cargos> cargos = cargoService.cargoSemMentor();
		return cargos;
	}
	
	//CARGO DO MENTOR
	@GetMapping("/cargo/cargo-mentor/{id_mentor}")
	public Cargos cargoDoMentor(@PathVariable Integer id_mentor){
		
		return cargoService.cargoDoMentor(id_mentor);
	}
	
	//CARGO COM MENTOR
	@GetMapping("/cargo/cargo-mentor")
	public List<List> cargosComMentor(){
		List<List> cargoMentor = cargoService.cargoComSeuMentor();
		return cargoMentor;
	}
	
	//////////////////////////////////////POSTMAPPING///////////////////////////////////////////
	
	//CADASTRAR CARGO
	@PostMapping("/cargo")
	public ResponseEntity<Cargos> cadastrarCargo(@RequestParam(value="mentor", required = false)Integer id_mentor,@RequestBody Cargos cargos){
		cargos = cargoService.cadastrarCargo(id_mentor,cargos);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cargos.getId_cargo()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	//////////////////////////////////////PUTMAPPING///////////////////////////////////////////
	
	//EDITAR CARGO
	@PutMapping("/cargo/{id_cargo}")
	public ResponseEntity<Void> editarCargo(@PathVariable Integer id_cargo, @RequestBody Cargos cargos){
		cargos.setId_cargo(id_cargo);
		cargos = cargoService.editarCargo(cargos);
		return ResponseEntity.noContent().build();
	}
	
	//ATRIBUIR MENTOR
	@PutMapping("/cargo/definirMentor/{id_cargo}/{id_mentor}")
	public ResponseEntity<Mentor> atribuirMentor(@PathVariable Integer id_cargo, @PathVariable Integer id_mentor){
		cargoService.atribuirMentor(id_cargo, id_mentor);
		return ResponseEntity.noContent().build();
	}
	
	//DEIXAR CARGO SEM MENTOR
	@PutMapping("/cargo/tirarMentor/{id_cargo}/{id_mentor}")
	public ResponseEntity<Mentor> deixarCargoSemMentor(@PathVariable Integer id_cargo, @PathVariable Integer id_mentor){
		cargoService.deixarCargoSemMentor(id_cargo, id_mentor);
		return ResponseEntity.noContent().build();
	}
	
	/////////////////////////////////////DELETEMAPPING///////////////////////////////////////
	
	//DELETAR UM CARGO
	@DeleteMapping("/cargo/{id_cargo}")
	public ResponseEntity<Cargos> deletarUmCargo(@PathVariable Integer id_cargo){
		cargoService.deletarUmCargo(id_cargo);
		return ResponseEntity.noContent().build();
	}

	
}
