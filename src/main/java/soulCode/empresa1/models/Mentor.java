package soulCode.empresa1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import soulCode.empresa1.models.Cargos;

@Entity
public class Mentor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_mentor;
	
	@Column(nullable = false, length=40)
	private String mentor_nome;
	
	@Column(nullable = false, length = 11)
	private String mentor_cpf;	
	
	@Column(nullable = true, length=40)
	private String mentor_cargo;
	
	@Column(nullable = true)
	private String mentor_foto;
	
	//determinação de relacionamento one to one
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_cargo", unique = true)
	private Cargos cargos;
	
	//GETTERS and SETTERS

	public Integer getId_mentor() {
		return id_mentor;
	}

	public void setId_mentor(Integer id_mentor) {
		this.id_mentor = id_mentor;
	}

	public String getMentor_nome() {
		return mentor_nome;
	}

	public void setMentor_nome(String mentor_nome) {
		this.mentor_nome = mentor_nome;
	}

	public String getMentor_cargo() {
		return mentor_cargo;
	}

	public void setMentor_cargo(String mentor_cargo) {
		this.mentor_cargo = mentor_cargo;
	}

	public String getMentor_foto() {
		return mentor_foto;
	}

	public void setMentor_foto(String mentor_foto) {
		this.mentor_foto = mentor_foto;
	}

	public Cargos getCargos() {
		return cargos;
	}

	public void setCargos(Cargos cargos) {
		this.cargos = cargos;
	}

	public String getMentor_cpf() {
		return mentor_cpf;
	}

	public void setMentor_cpf(String mentor_cpf) {
		this.mentor_cpf = mentor_cpf;
	}
	
	
}
