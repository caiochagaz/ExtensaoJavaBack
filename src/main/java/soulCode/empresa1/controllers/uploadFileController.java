package soulCode.empresa1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import soulCode.empresa1.models.Mentor;
import soulCode.empresa1.services.MentorService;
import soulCode.empresa1.utils.UploadFileUtil;

@RestController
@RequestMapping("empresa")
@CrossOrigin
public class uploadFileController {
	
	@Autowired
	private MentorService mentorService;
	
	
	@PostMapping("/envio/{id_mentor}")
	public ResponseEntity<String> enviarDados(@PathVariable Integer id_mentor,MultipartFile foto,@RequestParam("cpf") String cpf){
		
		String fileName = cpf;
		
		String uploadDir = "C:\\Users\\bpta\\Desktop\\java1\\empresa1Front\\src\\assets\\img";
		String nomeMaisCaminho =  "assets/img/" + cpf;
		
		Mentor mentor = mentorService.salvarFoto(id_mentor, nomeMaisCaminho);
		
		
		try {
			UploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
			
		}catch(Exception e) {
			System.out.println("O arquivo não foi enviado" + e);
		}
		
		System.out.println("Deu certo:" + nomeMaisCaminho);
		return ResponseEntity.ok("Arquivo enviado");
		
	}

}
