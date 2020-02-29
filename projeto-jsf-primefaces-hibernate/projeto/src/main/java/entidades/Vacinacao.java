package entidades;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Vacinacao implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dataVacinacao;
	@ManyToOne
	private Animal objetoAnimal;
	@ManyToOne
	private Remedios objetoRemedio;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataVacinacao() {
		return dataVacinacao;
	}
	public void setDataVacinacao(Date dataVacinacao) {
		this.dataVacinacao = dataVacinacao;
	}
	public Animal getObjetoAnimal() {
		return objetoAnimal;
	}
	public void setObjetoAnimal(Animal objetoAnimal) {
		this.objetoAnimal = objetoAnimal;
	}
	public Remedios getObjetoRemedio() {
		return objetoRemedio;
	}
	public void setObjetoRemedio(Remedios objetoRemedio) {
		this.objetoRemedio = objetoRemedio;
	}
	
	
}
