package soulCode.empresa1.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import soulCode.empresa1.models.Mentor;
import soulCode.empresa1.models.Boleto;
import soulCode.empresa1.services.BoletoService;

@CrossOrigin @RestController @RequestMapping("empresa")
public class boletoController {
	
	//Necessario injeçao de dependencias
	
		@Autowired
		private BoletoService boletoService;
		
		//MOSTRAR TODOS
		@GetMapping("/mentor/boleto")
		public List<Boleto> BuscarTodosBoletos(){
			List<Boleto> boleto = boletoService.buscarTodosBoletos();
			return boleto;
		}
		
		//BUSCAR UM BOLETO
		@GetMapping("/mentor/boleto/{codigo}")
		public ResponseEntity<Boleto> buscarUmBoleto(@PathVariable Integer codigo){
			Boleto boleto  = boletoService.buscarUmBoleto(codigo);
			return ResponseEntity.ok().body(boleto);
			
		}
		
		//BUSCAR BOLETO DO MENTOR
		@GetMapping("/mentor/boleto-mentor/{id_mentor}")
		public List<Boleto> buscarBoletoDoMentor(@PathVariable Integer id_mentor){
			List<Boleto> boleto = boletoService.buscarBoletoDoMentor(id_mentor);
			return boleto;
		}
		
		//INSERIR BOLETO
		@PostMapping("/mentor/boleto/{id_mentor}")
		public ResponseEntity<Boleto> InserirBoleto(@RequestBody Boleto boleto, @PathVariable Integer id_mentor){
			boleto = boletoService.InserirBoleto(boleto,id_mentor);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(boleto.getCodigo()).toUri();
			return ResponseEntity.created(uri).build();
			
		}
		
		//DELETAR UM BOLETO
		@DeleteMapping("/mentor/boleto-excluir/{codigo}/{id_mentor}")
		public ResponseEntity<Void> deletarUmBoleto(@PathVariable Integer codigo, @PathVariable  Integer id_mentor){
			boletoService.deletarUmBoleto(codigo, id_mentor);
			return ResponseEntity.noContent().build();
		}
		
		//EDITAR BOLETO
		@PutMapping("/mentor/boleto-editar/{codigo}/{id_mentor}")
		public ResponseEntity<Boleto> editarBoleto( @RequestBody Boleto boleto, @PathVariable Integer codigo, @PathVariable Integer id_mentor){
			boleto.setCodigo(codigo);
			boleto = boletoService.editarBoleto(boleto, codigo, id_mentor);
			return ResponseEntity.noContent().build();
		}
		
		//QUITAR BOLETO
		@PutMapping("mentor/quitarBoleto/{codigo}")
		public ResponseEntity<Boleto> quitarBoleto(@PathVariable Integer codigo){
			boletoService.quitarBoleto(codigo);
			return ResponseEntity.noContent().build();
		}
		
		//CANCELAR BOLETO
		@PutMapping("mentor/cancelarBoleto/{codigo}")
		public ResponseEntity<Boleto> cancelarBoleto(@PathVariable Integer codigo){
			boletoService.cancelarBoleto(codigo);
			return ResponseEntity.noContent().build();
		}

}
