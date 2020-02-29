package entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class AnimalVenda implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double quantidade;
	private double valorTotal;
	@ManyToOne
	private Animal objetoAnimal;
	@ManyToOne
	private Venda objetoVenda;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
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
	public Venda getObjetoVenda() {
		return objetoVenda;
	}
	public void setObjetoVenda(Venda objetoVenda) {
		this.objetoVenda = objetoVenda;
	}
	
	
}
