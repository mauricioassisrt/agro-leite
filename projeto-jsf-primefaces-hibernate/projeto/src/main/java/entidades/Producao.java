package entidades;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Producao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dataOrdenha;
	private double quantidadeLitros;
	private double valorTotalProDiaria;
	
	@ManyToOne
	private Pessoa objetoPessoa;
	@ManyToOne
	private Leite objetoLeite;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataOrdenha() {
		return dataOrdenha;
	}
	public void setDataOrdenha(Date dataOrdenha) {
		this.dataOrdenha = dataOrdenha;
	}
	public double getQuantidadeLitros() {
		return quantidadeLitros;
	}
	public void setQuantidadeLitros(double quantidadeLitros) {
		this.quantidadeLitros = quantidadeLitros;
	}
	public double getValorTotalProDiaria() {
		return valorTotalProDiaria;
	}
	public void setValorTotalProDiaria(double valorTotalProDiaria) {
		this.valorTotalProDiaria = valorTotalProDiaria;
	}
	public Pessoa getObjetoPessoa() {
		return objetoPessoa;
	}
	public void setObjetoPessoa(Pessoa objetoPessoa) {
		this.objetoPessoa = objetoPessoa;
	}
	public Leite getObjetoLeite() {
		return objetoLeite;
	}
	public void setObjetoLeite(Leite objetoLeite) {
		this.objetoLeite = objetoLeite;
	}
	
	
	
}
