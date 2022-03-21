package soulCode.empresa1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soulCode.empresa1.repositories.FuncionarioRepository;
import soulCode.empresa1.models.Cargos;
import soulCode.empresa1.models.Funcionarios;

@Service
public class FuncionarioService {
	
	@Autowired //faz injeção de dependência
	private FuncionarioRepository funcionarioRepository; //aqui instanciamos o FuncionarioRepository sem o new, no java o por padrão o objeto vem com a primeira letra em miúscula, assim tenho acesso a todos os 11 métodos do jpa repository.
	
//	@Autowired
//	private CargoService cargoService;
	
	
	//o primeiro serviço que vamos implementar é a listagem de todos os funcionarios cadastrados
	//get
	public List<Funcionarios> mostrarTodosFuncionarios(){
		return funcionarioRepository.findAll(); //o retorno é uma lista de alunos, findAll- método jpa-listar todos
	}
		
	//Optional - nos ajuda a evitar os erros NullPoiterExcepition, tira a necessidade de criarmos codificação (if aluno != null)
	//orElseThrow()- se o funcionario estiver presente no banco de dados, retorna o funcionario, senão lança uma exceção.
	
	//buscar um funcionario
	//Get
	public Funcionarios buscarUmFuncionario(Integer id_funcionario) { //passo parâmetro pelo atributo que vou buscar, no nosso caso é o ra_aluno que é o id, mas poderia ser nome etc. 
		Optional<Funcionarios> funcionario = funcionarioRepository.findById(id_funcionario);
		return funcionario.orElseThrow();
	}
	
	//get  //tabela inner join
	public List<List> funcComCargo(){
		return funcionarioRepository.funcComCargo();	
	}
		
	//buscar funcionario pelo cargo
	public List<Funcionarios> buscarFuncCargo(Integer id_cargo){
			List<Funcionarios> funcionario = funcionarioRepository.fetchByCargo(id_cargo);
			return funcionario;
	}
		
	//buscar id-turma pelo id_funcionario, usando a query criada no aluno repository
	/*public String buscarIdCargo(String id_funcionario) {
			String id_cargo = funcionarioRepository.fetchByIdCargo(id_funcionario);
			return id_cargo;
	}*/
	
	
	//inserir funcionario dentro do cargo 
	//Post
	public Funcionarios inserirFuncNoCargo(Integer id_func, Cargos cargos) {
		Funcionarios funcionario = buscarUmFuncionario(id_func);
		funcionario.setCargos(cargos);
		return funcionarioRepository.save(funcionario);
	}
	
	//deixar funcionário sem cargo
	public Funcionarios deixarFuncSemCargo(Integer id_func) {
		Funcionarios func = buscarUmFuncionario(id_func);
		func.setCargos(null);
		return funcionarioRepository.save(func);
	}
	
	//Inserir um funcionário 
	public Funcionarios InserirFunc(Funcionarios func) {
		func.setId_func(null);
		return funcionarioRepository.save(func);
	}
		
	//deletar um funcionario
	//Delete
	public void deletarUmFuncionario(Integer id_func) {
		funcionarioRepository.deleteById(id_func);
	}
	
		
	//primeiro buscamos qual funcionario queremos editar e depois salvar 
	//Put
	public Funcionarios editarFuncionario(Funcionarios funcionario) {
		buscarUmFuncionario(funcionario.getId_func());
		return funcionarioRepository.save(funcionario);
		
	}

}

