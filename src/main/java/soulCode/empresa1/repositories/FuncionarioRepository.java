package soulCode.empresa1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa1.models.Funcionarios;

public interface FuncionarioRepository extends JpaRepository <Funcionarios, Integer> {
	
	@Query(value = "SELECT * FROM funcionarios WHERE id_cargo = :id_cargo", nativeQuery = true)
	List<Funcionarios> fetchByCargo(Integer id_cargo);
	
	//@Query(value = "SELECT id_cargo FROM funcionarios WHERE id_func = :id_func", nativeQuery = true)
	//String fetchByIdCargo(String id_func);
	
	@Query(value = "SELECT id_func, func_nome, func_cidade, cargos.id_cargo, car_nome, car_atribuicao FROM cargos right JOIN funcionarios ON funcionarios.id_cargo = cargos.id_cargo order by func_nome",nativeQuery = true)
	List<List> funcComCargo();

}
