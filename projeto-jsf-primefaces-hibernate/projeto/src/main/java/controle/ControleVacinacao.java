/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import entidades.Vacinacao;
import entidades.Remedios;
import entidades.Animal;
import entidades.Raca;
import entidades.Remedios;
//import entidades.Tipo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ControleVacinacao {
	
     private Vacinacao objetoVacinacao = new Vacinacao();
     private Animal objetoAnimal= new Animal();
    private DAOGenerico dao = new DAOGenerico();
    private List<Vacinacao> lista = new ArrayList<>();
    private List<Animal> listaAnimal  = new ArrayList<>();
    private Remedios objetoRemedios= new Remedios();

 
    
    
    public ControleVacinacao(){
        preencher();
    }

    public void novo() {
        objetoVacinacao = new Vacinacao();
    }
    public void excluir(Vacinacao vacina){
        objetoVacinacao = vacina;
        if(vacina.getId()!=null){
            try {
                dao.exluir(vacina);
            } catch (Exception ex) {
                Logger.getLogger(ControleVacinacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        novo();
        preencher();
    }
 

    private void preencher() {
        lista = dao.lista(Vacinacao.class);
        listaAnimal = dao.lista(Animal.class);
    }

    public Animal getObjetoAnimal() {
		return objetoAnimal;
	}


	public void setObjetoAnimal(Animal objetoAnimal) {
		this.objetoAnimal = objetoAnimal;
	}


	public List<Animal> getListaAnimal() {
		return listaAnimal;
	}


	public void setListaAnimal(List<Animal> listaAnimal) {
		this.listaAnimal = listaAnimal;
	}


	public Remedios getObjetoRemedios() {
		return objetoRemedios;
	}


	public void setObjetoRemedios(Remedios objetoRemedios) {
		this.objetoRemedios = objetoRemedios;
	}


	public Vacinacao getObjetoVacinacao() {
        return objetoVacinacao;
    }

    public void setObjetoVacinacao(Vacinacao objetoVacinacao) {
        this.objetoVacinacao = objetoVacinacao;
    }

    public List<Vacinacao> getLista() {
        return lista;
    }

    public void setLista(List<Vacinacao> lista) {
        this.lista = lista;
    }
    
}
