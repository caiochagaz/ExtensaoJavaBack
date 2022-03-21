package soulCode.empresa1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa1.models.Boleto;

public interface BoletoRepository extends JpaRepository<Boleto, Integer> {

	@Query(value = "SELECT * FROM boleto WHERE id_mentor = :id_mentor", nativeQuery = true)
	List<Boleto> buscarBoletoDoMentor(Integer id_mentor);	
	
}
