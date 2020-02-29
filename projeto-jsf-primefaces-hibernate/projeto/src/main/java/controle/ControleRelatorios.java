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
import entidades.Producao;
import entidades.Raca;
import entidades.Remedios;
//import entidades.Tipo;
import entidades.Vacinacao;
import entidades.Venda;
import util.ChamaRelatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ibm.icu.text.SimpleDateFormat;

@ManagedBean
@ViewScoped
public class ControleRelatorios {
	 
	  private Date dataInicial = new Date();
	  private Date dataFinal = new Date();
	  public String novaData, novaData2;
	  private List<Leite> lista = new ArrayList<>();
	  private List<Venda> listaVenda = new ArrayList<>();
	  private List<Animal> listaAnimal = new ArrayList<>();
	  private List<Vacinacao> listaMedicacao = new ArrayList<>();
	  private List<Producao> listaProducao = new ArrayList<>();
	  private DAOGenerico dao = new DAOGenerico();
	  private Animal objetoAnimal = new Animal();
	  private Vacinacao objetoVaci = new Vacinacao();
	  
	  public ControleRelatorios() {
		  //lista com condição para pegar os animais em lactação
		  listaAnimal = dao.listaCondicao(Animal.class, " lactando = 'sim' ");
		  listaMedicacao = dao.lista(Vacinacao.class);
	  }
	//METODO DESTINADO AO ENVIO DAS DATAS QUE SERAO CONSULTADOS O RELATORIO ENTRE DT INICIAL E DATA FINAL
	  public void chamaRelatorioLeite() throws IOException{
			  
		  //formata a data vinda do calendario da vis�o  e converte para os parametros do banco de dados
		  SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
		  novaData = formatador.format(dataInicial);
	      novaData2 = formatador.format(dataFinal);
	      
	      // feito uma consulta no banco de dados entre as datas informadas
		  lista = dao.listaCondicao(Leite.class, "dataDoValor BETWEEN ' " + novaData + " ' AND ' " + novaData2 + " ' ");
		//verifica se a lista obitida na consltao possui algum item caso nao possua exibe a msg 
		  if(lista.size()==0){
			  FacesContext faces = FacesContext.getCurrentInstance();
			  faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Nenhuma valor cadastrado entre essa data! "));		
		  }else{
			  	//PARAMETRO JASPER DESTINADO PARA PASSAGEM DOS PARAMETROS QUE SERAO EXIBIDOS NO RELATORIO
			  	HashMap parameters = new HashMap<String, String>();
			  	//O VALOR DENTRO DAS "ASPAS" DEVE SER O MESMO DO CONFIGURADO NO RELATORIO JASPER 	
				parameters.put("datainicial", novaData);
				parameters.put("datafinal", novaData2);
			
				ChamaRelatorio.imprimeRelatorio("leite2.jasper", parameters, "leite");
		  }
		    
	  }
	  //METODO DESTINADO AO ENVIO DAS DATAS QUE SERAO CONSULTADOS O RELATORIO ENTRE DT INICIAL E DATA FINAL
	  public void chamaRelatorioAnimal() throws IOException{
			  
		 
	    
		//verifica se a lista obitida na consltao possui algum item caso nao possua exibe a msg 
		 
			  	//PARAMETRO JASPER DESTINADO PARA PASSAGEM DOS PARAMETROS QUE SERAO EXIBIDOS NO RELATORIO
			  	HashMap parameters = new HashMap<String, String>();
			  	//O VALOR DENTRO DAS "ASPAS" DEVE SER O MESMO DO CONFIGURADO NO RELATORIO JASPER 
			  	String idanimal = objetoAnimal.getId().toString();
				parameters.put("idanimal", idanimal);
				
				System.out.println(idanimal);
				//parametros ("nome do relatorio do arquivo", parametros obtidos atraves da view, o campo que será passado ao relatorio
				ChamaRelatorio.imprimeRelatorio("animal.jasper", parameters, "relatorioMedicamento");
		  
		    
	  }
	  public void chamaRelatorioMedicacao() throws IOException{
		  
			 
		    
			//verifica se a lista obitida na consltao possui algum item caso nao possua exibe a msg 
			 
				  	//PARAMETRO JASPER DESTINADO PARA PASSAGEM DOS PARAMETROS QUE SERAO EXIBIDOS NO RELATORIO
				  	HashMap parameters = new HashMap<String, String>();
				  	//O VALOR DENTRO DAS "ASPAS" DEVE SER O MESMO DO CONFIGURADO NO RELATORIO JASPER 
				  	String idanimal = objetoVaci.getObjetoAnimal().getId().toString();
					parameters.put("idanimal", idanimal);
					
					System.out.println("ID DO ANIMAL MEDICACAO "+idanimal);
					//parametros ("nome do relatorio do arquivo", parametros obtidos atraves da view, o campo que será passado ao relatorio
					ChamaRelatorio.imprimeRelatorio("medicacao.jasper", parameters, "relatoriol");
			  
			    
		  }
	  
	  
	  //METODO DESTINADO AO ENVIO DAS DATAS QUE SERAO CONSULTADOS O RELATORIO ENTRE DT INICIAL E DATA FINAL
	  public void chamaRelatorioVendas() throws IOException{
		  
		//formata a data vinda do calendario da vis�o  e converte para os parametros do banco de dados
		  SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
		  novaData = formatador.format(dataInicial);
	      novaData2 = formatador.format(dataFinal);
	      
	      // feito uma consulta no banco de dados entre as datas informadas
		  listaVenda = dao.listaCondicao(Venda.class, " dataVenda BETWEEN ' " + novaData + " ' AND ' " + novaData2 + " ' ");
		//verifica se a lista obitida na consltao possui algum item caso nao possua exibe a msg 
		  if(listaVenda.size()==0){
			  FacesContext faces = FacesContext.getCurrentInstance();
			  faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Nenhuma valor cadastrado entre essa data! "));		
		  }else{
			  	//PARAMETRO JASPER DESTINADO PARA PASSAGEM DOS PARAMETROS QUE SERAO EXIBIDOS NO RELATORIO
			  	HashMap parameters = new HashMap<String, String>();
			  	//O VALOR DENTRO DAS "ASPAS" DEVE SER O MESMO DO CONFIGURADO NO RELATORIO JASPER 	
				parameters.put("datainicial", novaData);
				parameters.put("datafinal", novaData2);
			
				ChamaRelatorio.imprimeRelatorio("vendas.jasper", parameters, "vendas");
		  }
		  
	  }
	  public void chamaRelatorioProducao() throws IOException{
		  
			//formata a data vinda do calendario da vis�o  e converte para os parametros do banco de dados
			  SimpleDateFormat formatador = new SimpleDateFormat("yyyy/MM/dd");
			  novaData = formatador.format(dataInicial);
		      novaData2 = formatador.format(dataFinal);
		      
		      // feito uma consulta no banco de dados entre as datas informadas
			  listaProducao = dao.listaCondicao(Producao.class, " dataOrdenha BETWEEN ' " + novaData + " ' AND ' " + novaData2 + " ' ");
			//verifica se a lista obitida na consltao possui algum item caso nao possua exibe a msg 
			  if(listaProducao.size()==0){
				  FacesContext faces = FacesContext.getCurrentInstance();
				  faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","Nenhuma valor cadastrado entre essa data! "));		
			  }else{
				  	//PARAMETRO JASPER DESTINADO PARA PASSAGEM DOS PARAMETROS QUE SERAO EXIBIDOS NO RELATORIO
				  	HashMap parameters = new HashMap<String, String>();
				  	//O VALOR DENTRO DAS "ASPAS" DEVE SER O MESMO DO CONFIGURADO NO RELATORIO JASPER 	
					parameters.put("datainicial", novaData);
					parameters.put("datafinal", novaData2);
				
					ChamaRelatorio.imprimeRelatorio("producao2.jasper", parameters, "producaototal");
			  }
			  
		  }

	  
	public List<Leite> getLista() {
		return lista;
	}

	public void setLista(List<Leite> lista) {
		this.lista = lista;
	}

	public List<Venda> getListaVenda() {
		return listaVenda;
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}

	public List<Producao> getListaProducao() {
		return listaProducao;
	}

	public void setListaProducao(List<Producao> listaProducao) {
		this.listaProducao = listaProducao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public List<Animal> getListaAnimal() {
		return listaAnimal;
	}
	public void setListaAnimal(List<Animal> listaAnimal) {
		this.listaAnimal = listaAnimal;
	}
	public Animal getObjetoAnimal() {
		return objetoAnimal;
	}
	public void setObjetoAnimal(Animal objetoAnimal) {
		this.objetoAnimal = objetoAnimal;
	}
	public List<Vacinacao> getListaMedicacao() {
		return listaMedicacao;
	}
	public void setListaMedicacao(List<Vacinacao> listaMedicacao) {
		this.listaMedicacao = listaMedicacao;
	}
	public Vacinacao getObjetoVaci() {
		return objetoVaci;
	}
	public void setObjetoVaci(Vacinacao objetoVaci) {
		this.objetoVaci = objetoVaci;
	}
	
	
	
	  
}
