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
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresa1.models.Cargos;
import soulCode.empresa1.models.Funcionarios;
	import soulCode.empresa1.repositories.FuncionarioRepository;
	import soulCode.empresa1.services.FuncionarioService;


	@CrossOrigin
	
	@RestController
	
	@RequestMapping("empresa")
	public class funcionarioController {
		
		//INJECOES DE DEPENDENCIA
		
		@Autowired
		private FuncionarioRepository funcionarioRepository;
		
		@Autowired
		private FuncionarioService funcionarioService;
		
		//MOSTRAR TODOS OS FUNCIONARIOS
		@GetMapping("/funcionario")
		public List<Funcionarios> mostrarTodosFunc(){
			List<Funcionarios> funcionarios = funcionarioService.mostrarTodosFuncionarios();
			return funcionarios;
		}
		
		
		//FUNCIONARIO COM CARGO
		@GetMapping("/funcionario-cargo")
		public List<List> funcComCargo(){
			List<List> funcionarioCargo = funcionarioService.funcComCargo();
			return funcionarioCargo;
		}
		
		
		//BUSCAR UM FUNCIONARIO
		@GetMapping("/funcionario/{id_func}")
		public ResponseEntity<?> buscarUmFuncionario(@PathVariable Integer id_func){
			Funcionarios funcionarios = funcionarioService.buscarUmFuncionario(id_func);
			return ResponseEntity.ok().body(funcionarios);
			
		}
		
		//BUSCAR FUNCIONARIO CARGO
		@GetMapping("/funcionario/busca-funcionario/{id_cargo}")
		public List<Funcionarios> buscarFuncCargo(@PathVariable Integer id_cargo) {
			List<Funcionarios> funcionario = funcionarioService.buscarFuncCargo(id_cargo);
			return funcionario;
		}
		
		
		// INSERIR FUNCIONARIO NO CARGO
		@PutMapping("funcionario/inserirCargo/{id_func}")
		public ResponseEntity<Funcionarios> inserirFuncNoCargo(@PathVariable Integer id_func, @RequestBody Cargos cargos){
		Funcionarios funcionarios = funcionarioService.inserirFuncNoCargo(id_func, cargos);
		return ResponseEntity.noContent().build();
		}
		
		
		//DEIXAR ALUNOS SEM TURMA
		@PutMapping("/funcionario/deixarSemCargo/{id_func}")
		public ResponseEntity<Funcionarios> deixarFuncSemCargo(@PathVariable Integer id_func){
			Funcionarios funcionarios = funcionarioService.deixarFuncSemCargo(id_func);
			return ResponseEntity.noContent().build();
		}
		
		//INSERIR FUNCIONARIO
		@PostMapping("/funcionario")
		public ResponseEntity<Void> InserirFunc(@RequestBody Funcionarios funcionarios){
			funcionarios = funcionarioService.InserirFunc(funcionarios);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(funcionarios.getId_func()).toUri();
			return ResponseEntity.created(uri).build();
			
		}
		
		//DELETAR UM FUNCIONARIO
		@DeleteMapping("/funcionario/{id_func}")
		public ResponseEntity<Void>deletarUmFunc(@PathVariable Integer id_func){
			funcionarioService.deletarUmFuncionario(id_func);
			return ResponseEntity.noContent().build();
			
		}	
		
		// EDITAR FUNCIONARIO 
		@PutMapping("/funcionario/{id_func}")
		public ResponseEntity<Void> editarFuncionario(@PathVariable Integer id_func, @RequestBody Funcionarios funcionarios){
		funcionarios.setId_func(id_func);
		funcionarios = funcionarioService.editarFuncionario(funcionarios);
		return ResponseEntity.noContent().build();
		}
		
		
		//ATUALIZAR FUNCIONARIO
		@PutMapping("/funcionarioSemCargo/{id_func}")
		public ResponseEntity<Void> atualizarFunc(@PathVariable Integer id_func, @RequestBody Funcionarios func) {
			func.setId_func(id_func);
			func = funcionarioService.editarFuncionario(func);
			return ResponseEntity.noContent().build();
		}


	

}
