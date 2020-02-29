/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import entidades.Animal;
import entidades.AnimalVenda;
import entidades.Origem;
import entidades.Raca;
import entidades.Remedios;
//import entidades.Tipo;
import entidades.Vacinacao;
import entidades.Venda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class ControleAnimalVenda {
	private List<Animal> novaLista = new ArrayList<>();
	private Animal objetoAnimal = new Animal();
    private DAOGenerico dao = new DAOGenerico();
    private List<Animal> lista = new ArrayList<>();
    private List<Animal> listaCaminhao = new ArrayList<>();
    private AnimalVenda objetoMovimentoVenda = new AnimalVenda();
    private List<AnimalVenda> listaVenda = new ArrayList<>();
    private Venda objetoVenda = new Venda();
    private List<AnimalVenda> listaV = new ArrayList<>();
    private List<AnimalVenda> listaAnimaisVendidos = new ArrayList<>();
   
    public ControleAnimalVenda(){
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
                Logger.getLogger(ControleAnimalVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        novo();
        preencher();
    }
    public void inserir() throws IOException{
    	if (objetoAnimal.getId() != null ) {
        		dao.salvar(objetoAnimal);
        } 
        preencher();
        novo();
    }
    public void removerIten(AnimalVenda ani) throws IOException{
    	FacesContext faces = FacesContext.getCurrentInstance();
    	objetoMovimentoVenda = ani;
    	faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Adicionado ao Caminhão!!!!"));
    	
    	listaV.remove(objetoMovimentoVenda);
    	
    	System.out.println("No remover!");
    
    	objetoMovimentoVenda = new AnimalVenda();
    	

   }
  
    public void adiconarAoCaminhao() throws IOException{
    	
    	FacesContext faces = FacesContext.getCurrentInstance();
        System.out.println("No metodo adicionar a lista do caminh�o!");
					listaCaminhao.add(objetoAnimal);		
					faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Adicionado ao Caminhão!!!!"));						
					for (int i = 0; i < listaCaminhao.size(); i++) {
						Object a = listaCaminhao.get(i);
						for (int j = i+1; j < listaCaminhao.size(); j++) {
							Object b = listaCaminhao.get(j);
							if (a.equals(b)) {
								listaCaminhao.remove(j);
								j--;
								
							}
						}
					}
					
					
   
}
  public void adiconarNaLista(){
	  listaV.clear();
	 
		for (Animal animal : listaCaminhao) {
     		
			System.out.println("no metodo Lista adicionar  ");
 			objetoMovimentoVenda = new AnimalVenda();
 			
 		//	objetoMovimentoVenda.setValorTotal(animal.getValorVenda());
 			objetoMovimentoVenda.setObjetoAnimal(animal);
			objetoMovimentoVenda.setObjetoVenda(objetoVenda);
	    	listaV.add(objetoMovimentoVenda);
		//	dao.inserir(objetoMovimentoVenda);
	    	System.out.println("For 118 "+objetoVenda.getValorTotal());	
	    	novaLista.add(animal);

	
		}
		listaCaminhao.clear();
		
		
  }
    
   public void trueAnimal(){
	   System.out.println("no metodo True!  ");
	   for (Animal animal : novaLista) {
		if(animal.getId()!=null){
			animal.setNaovendido(true);
			animal.setLactando("nao");
			dao.salvar(animal);
		}
	   }
   }
    
 public void inserirMovimentoVenda() throws IOException{
	 FacesContext context = FacesContext.getCurrentInstance();
		FacesContext faces = FacesContext.getCurrentInstance();
    	//la�o for que percore a lista de objetos e insere no banco cada item 
	
		double somaAnimalPreco=0 ;
    	//La�o para percorrer a lista e efetuar a soma do valor total da venda
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Venda Realizada","Para mais detalhes vá até as Vendas Realizadas No Menu Vendas!!!!"));	
    	for (AnimalVenda animal : listaV) {
    		
    		somaAnimalPreco +=animal.getValorTotal();
    		
    	 	
    	}
    	//aqui � efetuado a insers�o na tabela venda
    	if(objetoVenda.getId()==null && somaAnimalPreco!= 0){
    		System.out.println("if Venda ");
    		objetoVenda.setDataVenda(new Date());
        	objetoVenda.setQuantidadeTotal(novaLista.size());
        	objetoVenda.setValorTotal(somaAnimalPreco);
        	
        	dao.inserir(objetoVenda);
        		
    	}
    	
	   	for (AnimalVenda animal : listaV) {
				
			
	    	animal.setObjetoVenda(objetoVenda);
	    	
			dao.inserir(animal);
	    	System.out.println("For 138 ");	
			
			
	   
	   	}
		
	   	objetoVenda = new Venda();
	   	listaV = new ArrayList<AnimalVenda>();
	    novaLista.clear();
	   	System.out.println("no metodo inserir movimento  ");
	 	
	   	HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		session.removeAttribute("controleAnimalVenda");
//		session.removeAttribute("graficosGeral");
	
    }
 
 
    
    public List<Animal> getNovaLista() {
	return novaLista;
}

public void setNovaLista(List<Animal> novaLista) {
	this.novaLista = novaLista;
}

	public List<AnimalVenda> getListaV() {
		return listaV;
	}

	public void setListaV(List<AnimalVenda> listaV) {
		this.listaV = listaV;
	}

	public List<Animal> getListaCaminhao() {
		return listaCaminhao;
	}

	public void setListaCaminhao(List<Animal> listaCaminhao) {
		this.listaCaminhao = listaCaminhao;
	}

	private void preencher() {

        lista = dao.listaCondicao(Animal.class, "naovendido = 0");
        listaAnimaisVendidos = dao.lista(AnimalVenda.class);
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

	public AnimalVenda getObjetoMovimentoVenda() {
		return objetoMovimentoVenda;
	}

	public void setObjetoMovimentoVenda(AnimalVenda objetoMovimentoVenda) {
		this.objetoMovimentoVenda = objetoMovimentoVenda;
	}

	public List<AnimalVenda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(List<AnimalVenda> listaVenda) {
		this.listaVenda = listaVenda;
	}

	public Venda getObjetoVenda() {
		return objetoVenda;
	}

	public void setObjetoVenda(Venda objetoVenda) {
		this.objetoVenda = objetoVenda;
	}

	public List<AnimalVenda> getListaAnimaisVendidos() {
		return listaAnimaisVendidos;
	}

	public void setListaAnimaisVendidos(List<AnimalVenda> listaAnimaisVendidos) {
		this.listaAnimaisVendidos = listaAnimaisVendidos;
	}
	
}
