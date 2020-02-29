/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.Banco;
import banco.DAOGenerico;
import entidades.Animal;
import entidades.AnimalProducao;
import entidades.Leite;
import entidades.Origem;
import entidades.Pessoa;
import entidades.Raca;
import entidades.Remedios;
//import entidades.Tipo;
import entidades.Vacinacao;
import entidades.Producao;

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

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;

@ManagedBean
@SessionScoped
public class ControleAnimalProducao {
    
	private Animal objetoAnimal = new Animal();
    private DAOGenerico dao = new DAOGenerico();
    private List<Animal> lista = new ArrayList<>();
    private List<Animal> listaCaminhao = new ArrayList<>();
    private AnimalProducao objetoMovimentoProducao = new AnimalProducao();
    private List<AnimalProducao> listaProducao = new ArrayList<>();
    private Producao objetoProducao = new Producao();
    private List<AnimalProducao> listaV = new ArrayList<>();
    private List<AnimalProducao> listaAnimaisVendidos = new ArrayList<>();
    private Leite objetoLeite = new Leite();
    private List<Producao> listaProducaoHora = new ArrayList<>();
    private Pessoa objetoPessoa = new Pessoa();
    
    public ControleAnimalProducao(){
        preencher();
    }
    
    public List<Leite> completaLeite(String parametro){
          List<Leite> listaLeite = new ArrayList<Leite>();
          listaLeite = dao.listaCondicao(Leite.class, "LOWER(valorLitro)  LIKE LOWER('%"+parametro+"%')");
          return listaLeite;
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
                Logger.getLogger(ControleAnimalProducao.class.getName()).log(Level.SEVERE, null, ex);
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
    public void removerIten(AnimalProducao ani) throws IOException{
    	FacesContext faces = FacesContext.getCurrentInstance();
    	objetoMovimentoProducao = ani;
    	faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Informa��o :","Removido!!!!"));
    	
    	listaV.remove(objetoMovimentoProducao);
    	
    	System.out.println("No remover!");
    
    	objetoMovimentoProducao = new AnimalProducao();
    	

   }
  
    public void adiconarAoCaminhao() throws IOException{
    	
    	FacesContext faces = FacesContext.getCurrentInstance();
        System.out.println("No metodo adicionar a lista do caminh�o!");
					listaCaminhao.add(objetoAnimal);		
					faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Apartado!!!!"));						
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
	   
		for (Animal animal : listaCaminhao) {
     		
			System.out.println("no metodo Lista adicionar  ");
 			objetoMovimentoProducao = new AnimalProducao();
 			
 			
 			objetoMovimentoProducao.setObjetoAnimal(animal);
			objetoMovimentoProducao.setObjetoProducao(objetoProducao);
	    	listaV.add(objetoMovimentoProducao);
		//	dao.inserir(objetoMovimentoProducao);
	    	System.out.println("For 118 ");	
	

	
		}
		listaCaminhao.clear();
		
		
  }
 
    
 public void inserirMovimentoProducao() throws IOException{
	  objetoPessoa = UsuarioLogado.retornaUsuarioLogado();
	  
	 FacesContext context = FacesContext.getCurrentInstance();
     
  
   
    	//la�o for que percore a lista de objetos e insere no banco cada item 
	
		double somaQuantLitrosLeite=0, valorDaProducaoLeiteira=0;
		
    	//La�o para percorrer a lista e efetuar a soma do valor total da Producao
		for (AnimalProducao animal : listaV) {
    		
    		somaQuantLitrosLeite +=animal.getQuantidadeTotal();
    		valorDaProducaoLeiteira += objetoLeite.getValorLitro()*animal.getQuantidadeTotal();
    	 	
    	}
    	
    	Date d = new Date();
		int i = (int)d.getDate();
		int b = (int)d.getMonth();
		int c = (int)d.getYear();
		
		//tamanho da lista
        int n = listaProducaoHora.size();
      
        if(n==0){
        	System.out.println("166");
       		
    		//aqui � efetuado a insers�o na tabela Producao
        	if(objetoProducao.getId()==null && somaQuantLitrosLeite!= 0){
        		System.out.println("170 ");
        		objetoProducao.setDataOrdenha(d);
            	objetoProducao.setQuantidadeLitros(somaQuantLitrosLeite);;
            	objetoProducao.setValorTotalProDiaria(valorDaProducaoLeiteira);
            	objetoProducao.setObjetoLeite(objetoLeite);
            	objetoProducao.setObjetoPessoa(objetoPessoa);
            	dao.inserir(objetoProducao);
            		
        	}
        	double vlrTotalMovimentoAnimal;
    	   	for (AnimalProducao animal : listaV) {
    				
    			vlrTotalMovimentoAnimal = objetoLeite.getValorLitro()* animal.getQuantidadeTotal();
    	    	animal.setObjetoProducao(objetoProducao);
    	    	animal.setValorTotal(vlrTotalMovimentoAnimal);
    	    	
    			dao.inserir(animal);
    	    	System.out.println("For 187 ");	
    			
    			
    	   
    	   	}
    		
    	   	objetoProducao = new Producao();
    	   	listaV = new ArrayList<AnimalProducao>();
    	   	listaCaminhao.clear();
    	   	System.out.println("196 ");
    		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
    		session.removeAttribute("controleAnimalProducao");
    		 context.addMessage(null, new FacesMessage("Sucesso ",  "Ordenha Realizada para mais detalhes v� em Ordenhas Realizadas! " ) );
    	       	
    	   	
        }else{
   
        Producao pr = new Producao();
            //for pra pegar o ultimo elemento da lista e armazenar no objeto
            for (int j = n-1; j < listaProducaoHora.size(); j++) {
    			pr = listaProducaoHora.get(j);
    			System.out.println(pr.getDataOrdenha());
    		}
            
        	if(pr.getDataOrdenha().getDate()==i && pr.getDataOrdenha().getMonth()==b && pr.getDataOrdenha().getYear()==c){
       		
       		System.out.println("206");
       	  context.addMessage(null, new FacesMessage("Aten��o ",  "Na data atual j� possui uma ordenha! " ) );
       		
       	}else{
       		System.out.println("210");
       		
    		//aqui � efetuado a insers�o na tabela Producao
        	if(objetoProducao.getId()==null && somaQuantLitrosLeite!= 0){
        		System.out.println("214");
        		objetoProducao.setDataOrdenha(d);
            	objetoProducao.setQuantidadeLitros(somaQuantLitrosLeite);;
            	objetoProducao.setValorTotalProDiaria(valorDaProducaoLeiteira);
            	objetoProducao.setObjetoLeite(objetoLeite);
            	objetoProducao.setObjetoPessoa(objetoPessoa);
            	dao.inserir(objetoProducao);
            		
        	}
        	double vlrTotalMovimentoAnimal;
    	   	for (AnimalProducao animal : listaV) {
    				
    			vlrTotalMovimentoAnimal = objetoLeite.getValorLitro()* animal.getQuantidadeTotal();
    	    	animal.setObjetoProducao(objetoProducao);
    	    	animal.setValorTotal(vlrTotalMovimentoAnimal);
    	    	
    			dao.inserir(animal);
    	    	System.out.println("For 231 ");	
    			
    			
    	   
    	   	}
    		
    	   	objetoProducao = new Producao();
    	   	listaV = new ArrayList<AnimalProducao>();
    	   	listaCaminhao.clear();
    	   	System.out.println("Fim");
    	   	
    	   	HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
    		session.removeAttribute("controleAnimalProducao");
    		session.removeAttribute("graficosGeral");
    		 context.addMessage(null, new FacesMessage("Sucesso ",  "Ordenha Realizada para mais detalhes v� em Ordenhas Realizadas! " ) );
    	       	
            
       		}
        }
       
		
        
    
    }
    
 
 
    public AnimalProducao getObjetoMovimentoProducao() {
	return objetoMovimentoProducao;
}
    

public Pessoa getObjetoPessoa() {
		return objetoPessoa;
	}

	public void setObjetoPessoa(Pessoa objetoPessoa) {
		this.objetoPessoa = objetoPessoa;
	}

public void setObjetoMovimentoProducao(AnimalProducao objetoMovimentoProducao) {
	this.objetoMovimentoProducao = objetoMovimentoProducao;
}

public Leite getObjetoLeite() {
	return objetoLeite;
}

public void setObjetoLeite(Leite objetoLeite) {
	this.objetoLeite = objetoLeite;
}

	public List<AnimalProducao> getListaV() {
		return listaV;
	}

	public void setListaV(List<AnimalProducao> listaV) {
		this.listaV = listaV;
	}

	public List<Animal> getListaCaminhao() {
		return listaCaminhao;
	}

	public void setListaCaminhao(List<Animal> listaCaminhao) {
		this.listaCaminhao = listaCaminhao;
	}

	private void preencher() {

        lista = dao.listaCondicao(Animal.class, " lactando = 'sim' ");
        listaAnimaisVendidos = dao.lista(AnimalProducao.class);
        listaProducaoHora = dao.lista(Producao.class);
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

	public AnimalProducao getobjetoMovimentoProducao() {
		return objetoMovimentoProducao;
	}

	public void setobjetoMovimentoProducao(AnimalProducao objetoMovimentoProducao) {
		this.objetoMovimentoProducao = objetoMovimentoProducao;
	}

	public List<AnimalProducao> getListaProducao() {
		return listaProducao;
	}

	public void setListaProducao(List<AnimalProducao> listaProducao) {
		this.listaProducao = listaProducao;
	}

	public Producao getObjetoProducao() {
		return objetoProducao;
	}

	public void setObjetoProducao(Producao objetoProducao) {
		this.objetoProducao = objetoProducao;
	}

	public List<AnimalProducao> getListaAnimaisVendidos() {
		return listaAnimaisVendidos;
	}

	public void setListaAnimaisVendidos(List<AnimalProducao> listaAnimaisVendidos) {
		this.listaAnimaisVendidos = listaAnimaisVendidos;
	}
	
}
