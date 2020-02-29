package entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class AnimalProducao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double quantidadeTotal;
	private double valorTotal;
	private String meioObtencaoQuantidade;
	@ManyToOne
	private Animal objetoAnimal;
	@ManyToOne
	private Producao objetoProducao;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMeioObtencaoQuantidade() {
		return meioObtencaoQuantidade;
	}

	public void setMeioObtencaoQuantidade(String meioObtencaoQuantidade) {
		this.meioObtencaoQuantidade = meioObtencaoQuantidade;
	}

	public double getQuantidadeTotal() {
		return quantidadeTotal;
	}
	public void setQuantidadeTotal(double quantidadeTotal) {
		this.quantidadeTotal = quantidadeTotal;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Animal getObjetoAnimal() {
		return objetoAnimal;
	}
	public void setObjetoAnimal(Animal objetoAnimal) {
		this.objetoAnimal = objetoAnimal;
	}
	public Producao getObjetoProducao() {
		return objetoProducao;
	}
	public void setObjetoProducao(Producao objetoProducao) {
		this.objetoProducao = objetoProducao;
	}
	
	
}
