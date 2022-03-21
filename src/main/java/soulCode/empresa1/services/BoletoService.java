package soulCode.empresa1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresa1.models.Mentor;
import soulCode.empresa1.models.Boleto;
import soulCode.empresa1.models.StatusBoleto;
import soulCode.empresa1.repositories.MentorRepository;
import soulCode.empresa1.repositories.BoletoRepository;

@Service
public class BoletoService {

	@Autowired
	private BoletoRepository boletoRepository;
	
	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private MentorService mentorService;
	
	public List<Boleto> buscarTodosBoletos() {
		return boletoRepository.findAll();
	}
	
	public Boleto buscarUmBoleto(Integer codigo) {
		Optional<Boleto> boleto = boletoRepository.findById(codigo);
		return boleto.orElseThrow();
	}
	
	public List<Boleto> buscarBoletoDoMentor(Integer id_mentor) {
		List<Boleto> boleto = boletoRepository.buscarBoletoDoMentor(id_mentor);
		return boleto;
	}
	
	public Boleto InserirBoleto(Boleto boleto, Integer id_mentor) {
		boleto.setCodigo(null);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		boleto.setMentor(mentor);
		return boletoRepository.save(boleto);
	}
	
	public Boleto quitarBoleto(Integer codigo) {
		Boleto boleto = buscarUmBoleto(codigo);
		StatusBoleto st1 = StatusBoleto.RECEBIDO;
		boleto.setBo_status(st1);
		return boletoRepository.save(boleto);
	}

	public Boleto cancelarBoleto(Integer codigo) {
		Boleto boleto = buscarUmBoleto(codigo);
		StatusBoleto st1 = StatusBoleto.CANCELADO;
		boleto.setBo_status(st1);
		return boletoRepository.save(boleto);
	}
	
	public void deletarUmBoleto(Integer codigo, Integer id_mentor) {
		boletoRepository.deleteById(codigo);
	}
		
	public Boleto editarBoleto(Boleto boleto, Integer codigo, Integer id_mentor) {
		buscarUmBoleto(boleto.getCodigo());
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		boleto.setMentor(mentor);
		return boletoRepository.save(boleto);
	}
}
