package soulCode.empresa1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresa1.models.Cargos;
import soulCode.empresa1.models.Mentor;
import soulCode.empresa1.repositories.CargoRepository;
import soulCode.empresa1.repositories.MentorRepository;

@Service
public class MentorService {
	
	
	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	//MOSTRAR TODOS MENTORES
	public List<Mentor> mostrarTodosMentores(){
		return mentorRepository.findAll();	
	}
	
	//MOSTRAR UM MENTOR
	public Mentor mostrarUmMentor(Integer id_mentor) {
		Optional<Mentor> mentor = mentorRepository.findById(id_mentor);
		return mentor.orElseThrow();
	}
	
	//BUSCAR MENTOR DO CARGO - GET PELO ID
	public Mentor buscarMentorDoCargo(Integer id_cargo){
		Mentor mentor = mentorRepository.fetchByCargos(id_cargo);
		return mentor;
	}
	
	//MENTOR SEM CARGO - GET CARGO
	public List<Mentor> mentorSemCargo(){
		return mentorRepository.mentorSemCargo();
	}
	
	//MENTOR COM SEU CARGO - GET CARGO COM MENTOR
	public List<List> MentorComSeuCargo(){
		return mentorRepository.mentorComSeuCargo();
	}
	
	public Mentor InserirMentor(Integer id_cargo, Mentor mentor) {
		mentor.setId_mentor(null);
		
		if(id_cargo != null) {
			Cargos cargos = cargoService.buscarUmCargo(id_cargo);
			mentor.setCargos(cargos);
			cargos.setMentor(mentor);
		}
		return mentorRepository.save(mentor);
		
	}
	
	public Mentor editarMentor(Mentor mentor) {
		mostrarUmMentor(mentor.getId_mentor());
		return mentorRepository.save(mentor);
	}
	
	
	/*SALVAR FOTO FO MENTOR*/
	public Mentor salvarFoto(Integer id_mentor, String caminhoFoto) {
		Mentor mentor = mostrarUmMentor(id_mentor);
		mentor.setMentor_foto(caminhoFoto);
		return mentorRepository.save(mentor);
	}
	
	
	/* BUSCAR O MENTOR PELO CPF */
	public Mentor buscarMentorPeloCpf(String mentor_cpf) {
		Mentor mentor = mentorRepository.fetchByCpf(mentor_cpf);
	    return mentor;
	}
	
	
	/* MÉTODO DELETE ID*/
	public void deletarMentor(Integer id_mentor) {
		mentorRepository.deleteById(id_mentor);
	}
	
	
}
