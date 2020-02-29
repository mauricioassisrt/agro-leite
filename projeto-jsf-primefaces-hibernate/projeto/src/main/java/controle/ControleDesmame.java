/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import entidades.Animal;
import entidades.Origem;
import entidades.Raca;
import entidades.Remedios;
//import entidades.Tipo;
import entidades.Vacinacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ControleDesmame {

	private Animal objetoAnimal = new Animal();
    private DAOGenerico dao = new DAOGenerico();
    private List<Animal> lista = new ArrayList<>();
    private Vacinacao objetoVacinacao = new Vacinacao();
    private Remedios objetoRemedios= new Remedios();
    private List<Animal> listaCondicaoAnimal = new ArrayList<>();
    
  
//    public List<Tipo> completaTipo(String parametro){
//		List<Tipo> listaTipo = new ArrayList<Tipo>();
//		listaTipo = dao.listaCondicao(Tipo.class, "LOWER(animalTipo)  LIKE LOWER('%"+parametro+"%')");
//		return listaTipo;
//	}
    
    public ControleDesmame(){
        preencher();
    }

    public void novo() {
        objetoAnimal = new Animal();
    }
    public void excluir(Animal tipo){
        objetoAnimal = tipo;
        if(tipo.getId()!=null){
            try {
                dao.exluir(tipo);
            } catch (Exception ex) {
                Logger.getLogger(ControleAnimal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        novo();
        preencher();
    }
    
   
    
    
    public void inserir() {
        if (objetoAnimal.getId() != null) {
        	objetoAnimal.setDesmamado("sim");
            dao.inserir(objetoAnimal);
           
        } else {
            dao.salvar(objetoAnimal);
        }
        preencher();
        novo();
    }

    
    private void preencher() {
        lista = dao.lista(Animal.class);
        listaCondicaoAnimal = dao.listaCondicao(Animal.class, "desmamado = 'nao' AND naovendido = 0 ");
    }

    
    
	
	public List<Animal> getListaCondicaoAnimal() {
		return listaCondicaoAnimal;
	}

	public void setListaCondicaoAnimal(List<Animal> listaCondicaoAnimal) {
		this.listaCondicaoAnimal = listaCondicaoAnimal;
	}

	public Animal getObjetoAnimal() {
        return objetoAnimal;
    }

    public void setObjetoAnimal(Animal objetoAnimal) {
        this.objetoAnimal = objetoAnimal;
    }

    public List<Animal> getLista() {
        return lista;
    }

    public void setLista(List<Animal> lista) {
        this.lista = lista;
    }
	public Vacinacao getObjetoVacinacao() {
		return objetoVacinacao;
	}
	public void setObjetoVacinacao(Vacinacao objetoVacinacao) {
		this.objetoVacinacao = objetoVacinacao;
	}
	public Remedios getObjetoRemedios() {
		return objetoRemedios;
	}
	public void setObjetoRemedios(Remedios objetoRemedios) {
		this.objetoRemedios = objetoRemedios;
	}
    
}
