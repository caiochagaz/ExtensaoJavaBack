package soulCode.empresa1.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Boleto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(nullable = false)
	private String bo_descricao;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
//	@Temporal(TemporalType.DATE) Dava erro na data
	@Column(nullable = false, columnDefinition = "date")
	private Date bo_dataPagamento;
	
	// Serve para modificar o front end, caso utilizemos HTML no Java
	@NumberFormat(pattern = "#.##0,00") //o zero indica dígitos obrigatórios, ou seja, deverá ter pelo menos um dígito antes da vírgula e apenas dois após.
	//O # indica que não é obrigatório ter as demais casas. Usamos pra indicar a separação por ponto.
	@Column(nullable = false)
	private Double bo_valor;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusBoleto bo_status;
	
	@ManyToOne
	@JoinColumn(name = "id_mentor")
	private Mentor mentor;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getBo_descricao() {
		return bo_descricao;
	}

	public void setBo_descricao(String bo_descricao) {
		this.bo_descricao = bo_descricao;
	}

	public Date getBo_dataPagamento() {
		return bo_dataPagamento;
	}

	public void setBo_dataPagamento(Date bo_dataPagamento) {
		this.bo_dataPagamento = bo_dataPagamento;
	}

	public Double getBo_valor() {
		return bo_valor;
	}

	public void setBo_valor(Double bo_valor) {
		this.bo_valor = bo_valor;
	}

	public StatusBoleto getBo_status() {
		return bo_status;
	}

	public void setBo_status(StatusBoleto bo_status) {
		this.bo_status = bo_status;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
	
	

}
