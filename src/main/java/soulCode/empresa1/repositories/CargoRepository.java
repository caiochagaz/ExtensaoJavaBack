package soulCode.empresa1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa1.models.Cargos;


public interface CargoRepository extends JpaRepository <Cargos, Integer> {
	
	@Query(value="SELECT * FROM cargos WHERE id_mentor is null", nativeQuery = true)
	List<Cargos> cargoSemMentor();
	
	@Query(value="SELECT * FROM cargos where id_mentor =:id_mentor",nativeQuery = true)
	Cargos cargoDoMentor(Integer id_mentor);
	
	@Query(value="SELECT cargos.id_cargo,cargos.car_nome, cargos.car_atribuicao, mentor.id_mentor, mentor.mentor_nome, mentor.mentor_cargo FROM cargos left JOIN mentor ON mentor.id_cargo = cargos.id_cargo order by cargos.car_nome;",nativeQuery = true)
	List<List> cargoComSeuMentor();

}
