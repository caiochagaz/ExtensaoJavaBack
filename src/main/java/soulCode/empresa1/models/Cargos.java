package soulCode.empresa1.models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import soulCode.empresa1.models.Cargos;
import soulCode.empresa1.models.Mentor;

@Entity
public class Cargos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_cargo;
	
	@Column(nullable=false, length=30)
	private String car_nome;
	
	@Column(nullable=false, length=30)
	private String car_atribuicao;
	
	//relacionamento one to many
	//Criação de lista de cargo
	@OneToMany(mappedBy = "cargos")
	private List<Funcionarios> funcionarios = new ArrayList<>();
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_mentor", unique = true)
	private Mentor mentor;
	
	//GET AND SET

	public Integer getId_cargo() {
		return id_cargo;
	}

	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}

	public String getCar_nome() {
		return car_nome;
	}

	public void setCar_nome(String car_nome) {
		this.car_nome = car_nome;
	}
	

	public String getCar_atribuicao() {
		return car_atribuicao;
	}

	public void setCar_atribuicao(String car_atribuicao) {
		this.car_atribuicao = car_atribuicao;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
	
	
	


	
	
	
}
