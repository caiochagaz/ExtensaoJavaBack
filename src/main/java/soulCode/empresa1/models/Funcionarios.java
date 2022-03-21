package soulCode.empresa1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import soulCode.empresa1.models.Funcionarios;


//CRIANDO A TABELA

@Entity
public class Funcionarios {
	
	
	@Id
	//criar automático
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_func;
	
	//esse campo não pode ficar nulo por causa do false
	@Column(nullable = false, length = 60)
	private String func_nome;
	
	@Column(nullable = true, length = 60)
	private String func_cidade;
	
	//@Column(nullable = true, length = 30)
	//private String func_cargo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cargo")
	private Cargos cargos;
	

	//GET AND SET
	public Integer getId_func() {
		return id_func;
	}
	
	
	public void setId_func(Integer id_func) {
		this.id_func = id_func;
	}

	public String getFunc_nome() {
		return func_nome;
	}

	public void setFunc_nome(String func_nome) {
		this.func_nome = func_nome;
	}

	public String getFunc_cidade() {
		return func_cidade;
	}

	public void setFunc_cidade(String func_cidade) {
		this.func_cidade = func_cidade;
	}


	public Cargos getCargos() {
		return cargos;
	}


	public void setCargos(Cargos cargos) {
		this.cargos = cargos;
	}
	
	
	
	//ACHO QUE AQUI É PARA REVIVER AS FUNÇOES

	//public String getFunc_cargo() {
		//return func_cargo;
	//}

	//public void setFunc_cargo(String func_cargo) {
		//this.func_cargo = func_cargo;
	//}
	
	
}
