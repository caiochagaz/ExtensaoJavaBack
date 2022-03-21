package soulCode.empresa1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import soulCode.empresa1.models.Cargos;
import soulCode.empresa1.models.Mentor;
import soulCode.empresa1.repositories.CargoRepository;
import soulCode.empresa1.services.exceptions.ObjectNotFoundException;



@Service
public class CargoService {
	
	/////////////////////////////////////////////AUTOWIRED//////////////////////////////////////
	
	@Autowired
	private CargoRepository cargoRepository;
	
	@Lazy
	@Autowired
	private MentorService mentorService;
	
	//////////////////////////////////////////////MÉTODOS/////////////////////////////////////////

	public List<Cargos> mostrarTodosCargos() {
		return cargoRepository.findAll();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	
	public Cargos buscarUmCargo(Integer id_cargo) {
		Optional<Cargos> cargos = cargoRepository.findById(id_cargo);
		return cargos.orElseThrow(() -> new soulCode.empresa1.services.exceptions.ObjectNotFoundException(
				"Objeto não cadastrado! O id procurado foi: " + id_cargo));
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	//NOVA ROTINA COM QUERY NO REPOSITORY
	//ESSA ROTINA TRAZ A TURMA EM QUE AQUELE PROFESSOR ESTA INSERIDO
	//CRIO A QUERY DEPOIS O SERVIÇO E DEPOIS VOU LA NO CONTROLLER
	public Cargos cargoDoMentor(Integer id_mentor) {
		Cargos cargos = cargoRepository.cargoDoMentor(id_mentor);
		return cargos;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	

	public List<List> cargoComSeuMentor(){
		return cargoRepository.cargoComSeuMentor();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Cargos cadastrarCargo(Integer id_mentor, Cargos cargos) {
		// é uma forma de segurança para nao setarmos o id
		cargos.setId_cargo(null);
		if (id_mentor != null) {
			Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
			cargos.setMentor(mentor);
			
		}
		
		return cargoRepository.save(cargos);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////

	public Cargos editarCargo(Cargos cargos) {
		buscarUmCargo(cargos.getId_cargo());
		return cargoRepository.save(cargos);
	}

	public void deletarUmCargo(Integer id_cargo) {
		buscarUmCargo(id_cargo);
		try {
			cargoRepository.deleteById(id_cargo);
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			throw new soulCode.empresa1.services.exceptions.DataIntegrityViolationException(
					"O Cargo não pode ser deletada! Possui funcionarios relacionados!");
		}
		
	}
	
	////////////////////////////////////////////NOVO///////////////////////////////////////////////////
	
	public Cargos atribuirMentor(Integer id_cargo,Integer id_mentor){
		Cargos cargos = buscarUmCargo(id_cargo);
		Mentor mentorAnterior = mentorService.buscarMentorDoCargo(id_cargo);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		if(cargos.getMentor()!=null) {
			cargos.setMentor(null);
			mentorAnterior.setCargos(null);
		}
		cargos.setMentor(mentor);
		mentor.setCargos(cargos);
		return cargoRepository.save(cargos);
	}
	
	/////////////////////////////ROTINA QUE PEGA O ID DO CARGO E O ID DO MENTOR////////////////////
	
	public Cargos deixarCargoSemMentor(Integer id_cargo, Integer id_mentor) {
		Cargos cargos = buscarUmCargo(id_cargo);
		cargos.setMentor(null);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		mentor.setCargos(null);
		return cargoRepository.save(cargos);
	}
	
	
	//////////////////////////////////////////////READ CARGO////////////////////////////////////////////
	
	public List<Cargos> cargoSemMentor(){
		return cargoRepository.cargoSemMentor();
	}
			
		
		
}
		

	

