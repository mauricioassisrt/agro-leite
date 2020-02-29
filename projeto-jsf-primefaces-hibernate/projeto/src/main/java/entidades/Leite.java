package entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity

public class Leite implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private double valorLitro;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dataDoValor;
	
	public Date getDataDoValor() {
		return dataDoValor;
	}
	public void setDataDoValor(Date dataDoValor) {
		this.dataDoValor = dataDoValor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValorLitro() {
		return valorLitro;
	}
	public void setValorLitro(double valorLitro) {
		this.valorLitro = valorLitro;
	}
	
	
	
	
	
}
