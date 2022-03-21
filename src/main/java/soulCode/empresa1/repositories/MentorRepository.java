package soulCode.empresa1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa1.models.Funcionarios;
import soulCode.empresa1.models.Mentor;


public interface MentorRepository extends JpaRepository <Mentor, Integer> {
	
	
	//buscar mentor pelo id_cargo
	@Query(value = "SELECT * FROM mentor WHERE id_cargo = :id_cargo", nativeQuery = true)
	Mentor fetchByCargos(Integer id_cargo);
	
	//traz todos os mentores que não tem cargo atrelado
	@Query(value="SELECT * FROM mentor WHERE id_cargo is null", nativeQuery = true)
	List<Mentor> mentorSemCargo();
	
	//buscar o mentor do cargo através do id_mentor
	//join da tabela mentor com o cargo 
	//traz todos os mentores, independente sem tem cargo ou não
	@Query(value = "SELECT mentor.id_mentor, mentor.mentor_nome, mentor.mentor_cargo, mentor.mentor_cpf, mentor.mentor_foto, cargos.id_cargo, cargos.car_nome, cargos.car_atribuicao FROM cargos right JOIN mentor ON mentor.id_cargo = cargos.id_cargo order by mentor.mentor_nome;",nativeQuery = true)
	List<List> mentorComSeuCargo();
	
	//buscar o cpf do mentor para pegar o id_mentor para atribuir a foto para ao mentor certo
	@Query(value = "SELECT * FROM mentor WHERE mentor_cpf =:mentor_cpf", nativeQuery = true)
	Mentor fetchByCpf(String mentor_cpf);
	
	
	

}
