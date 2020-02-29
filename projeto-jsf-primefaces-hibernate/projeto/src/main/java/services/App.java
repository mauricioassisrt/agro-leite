package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import banco.DAOGenerico;
import controle.UsuarioLogado;
import entidades.Animal;
import entidades.AnimalProducao;
import entidades.Leite;
import entidades.Pessoa;
import entidades.Producao;
import entidades.Raca;


@Path(value = "app")
public class App {
	DAOGenerico dao = new DAOGenerico();
	List<Leite> listaLeite = new ArrayList<>();
	List<Pessoa> listaPessoa = new ArrayList<>();
	private Animal objetoAnimal = new Animal();
    private List<Animal> lista = new ArrayList<>();
    private Pessoa objetoPessoa = new Pessoa();
    private Producao objetoProducao = new Producao();
    private Leite objetoLeite = new Leite();
    private List<AnimalProducao> listaProducao = new ArrayList<>();
    private List<Producao> listaProducaoHora = new ArrayList<>();
    private AnimalProducao objetoMovimentoProducao = new AnimalProducao();
	@GET
	@Path("/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GET
	@Path("/producao/{valorleite}/{producao}/{nome}/{senha}")
	public Double calculadoraSimples2(@PathParam("valorleite") Double leite,
			@PathParam("producao") Double producao,  @PathParam("nome") String nome,  @PathParam("senha") String senha) {
		
		lista = dao.listaCondicao(Animal.class, " lactando = 'sim'");
		listaPessoa = dao.listaCondicao(Pessoa.class, "permissao = 'usuario'");
		listaLeite = dao.lista(Leite.class);
		listaProducaoHora = dao.lista(Producao.class);
		
		  for (Animal animal : lista) {
				objetoMovimentoProducao.setObjetoAnimal(animal);
				listaProducao.add(objetoMovimentoProducao);
				System.out.println("nome animal preencher metodo" + animal.getNomeAnimal());
	
				System.out.println("nome animal preencher metodo assoiativa" + objetoMovimentoProducao.getObjetoAnimal().getNomeAnimal());
				objetoMovimentoProducao = new AnimalProducao();
	        }
		
		System.out.println("DADOS CHEGANDO !"+senha+nome+producao+leite);
		/// laço no qual compara o usuario vindo do Aplicativo se há usuario no banco 
		for (Pessoa objetoP : listaPessoa) {
				if(objetoP.getNome().equals(nome.toString()) && objetoP.getSenha()==Integer.parseInt(senha)){
					objetoPessoa.setId(objetoP.getId());
					objetoPessoa.setNome(objetoP.getNome());
					objetoPessoa.setPermissao("usuario");
					objetoPessoa.setSenha(objetoP.getSenha());
					
					System.out.println("NOME DA PESSOA "+objetoPessoa.getNome());
					
					
				}
				System.out.println("NOME DA PESSOA "+objetoP.getNome());
		}
		
		for (Leite objL : listaLeite) {
			if(objL.getValorLitro()== leite){
				objetoLeite.setId(objL.getId());
				objetoLeite.setDataDoValor(objL.getDataDoValor());
				objetoLeite.setValorLitro(leite);
				
				break;
			}
		}
		
			    //obtem os animais totais que est�o em lactacao 
		    	 double qtdAnimaisLactando= lista.size();
				 
		    	 //efetua a divis�o pela quantidade de litros de leite inseridos com o total de animais lactandos
				double variavelLeitePorAnilal = (producao / qtdAnimaisLactando);
				// multiplica a quantidade total de litros de leite pelo valor do litro para obter assim o total em R$
				double valorTotalPrdNoDia= (producao*objetoLeite.getValorLitro());    	    		
				    	
		    	
				System.out.println("valor da producao R$"+valorTotalPrdNoDia+"\n"
						+ "quantidade de litros por animal "+variavelLeitePorAnilal);  	
				    	Date d = new Date();
						int i = (int)d.getDate();
						int b = (int)d.getMonth();
						int c = (int)d.getYear();
						
						//tamanho da lista de producao
				        int n = listaProducaoHora.size();
				        System.out.println(n);
				      //caso nao tenha registros no banco efetua esse if 
				        if(n==0){
				        	System.out.println("if sem produ��o nenhuma na tabela do banco de dados ");
				       		
				    		//aqui � efetuado a insers�o na tabela Producao
				        	if(objetoProducao.getId()==null && producao!= 0){
				        		System.out.println("170 ");
				        		objetoProducao.setDataOrdenha(d);
				            	objetoProducao.setQuantidadeLitros(producao);
				            	objetoProducao.setValorTotalProDiaria(valorTotalPrdNoDia);
				            	objetoProducao.setObjetoLeite(objetoLeite);
				            	objetoProducao.setObjetoPessoa(objetoPessoa);
				          	dao.inserir(objetoProducao);
				            		
				        	}
				        	double vlrTotalMovimentoAnimal;
				    	   	for (AnimalProducao animal : listaProducao) {
				    				
				    			vlrTotalMovimentoAnimal = objetoLeite.getValorLitro()* animal.getQuantidadeTotal();
				    	    	animal.setObjetoProducao(objetoProducao);
				    	    	animal.setValorTotal(vlrTotalMovimentoAnimal);
				    	    	
				    		dao.inserir(animal);
				    	    	System.out.println("Caso nao haja producao entra neste if e percorre este la�o");	
				    			
				    			
				    	   
				    	   	}
				    		
				    	   	objetoProducao = new Producao();
				    	   	listaProducao = new ArrayList<AnimalProducao>();
				    	   	
				    	   	System.out.println("196 ");
				    	
				    	   	
				        }else{
				       //caso tenha registro no banco entra neste else 
				        Producao pr = new Producao();
				            //for pra pegar o ultimo elemento da lista e armazenar no objeto
				            for (int j = n-1; j < listaProducaoHora.size(); j++) {
				    			pr = listaProducaoHora.get(j);
				    			System.out.println(pr.getDataOrdenha());
				    		}
				            
				        	if(pr.getDataOrdenha().getDate()==i && pr.getDataOrdenha().getMonth()==b && pr.getDataOrdenha().getYear()==c){
				       		
				        		System.out.println("erro ja possui ordenha !");
				        	
				       		
				        	}else{
				        		System.out.println("210");
				       		
				    		//aqui � efetuado a insers�o na tabela Producao
					        	if(objetoProducao.getId()==null && producao!= 0){
					        		System.out.println("inserir a producao diaria  ");
					        		objetoProducao.setDataOrdenha(d);
					            	objetoProducao.setQuantidadeLitros(producao);;
					            	objetoProducao.setValorTotalProDiaria(valorTotalPrdNoDia);
					            	objetoProducao.setObjetoLeite(objetoLeite);
					            	objetoProducao.setObjetoPessoa(objetoPessoa);
					            	dao.inserir(objetoProducao);
					            		
					        	}
				        	double vlrTotalMovimentoAnimal;
				    	   	for (AnimalProducao animal : listaProducao) {
				    			//multiplica o valor do litro do leite pela quantidade de litros por animal	
				    			vlrTotalMovimentoAnimal = objetoLeite.getValorLitro()* variavelLeitePorAnilal;
				    			
				    	    	animal.setObjetoProducao(objetoProducao);
				    	    	animal.setValorTotal(vlrTotalMovimentoAnimal);
				    	    	animal.setQuantidadeTotal(variavelLeitePorAnilal);
				    			dao.inserir(animal);
				    	    	System.out.println("La�o for associativa animalProducao ");	
				    			
				    			
				    	   
				    	   	}
				    		
				    	   	objetoProducao = new Producao();
				    	   
				    	   
				    	   	
				    	   	System.out.println("Fim");
				    	         	
				            
				       		}
				        }
				       
		
		
		
		return null;
	}
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Raca getUserInXML() {
		
	Raca objraca = new Raca();
	objraca.setNomeDaRaca("Animal");
	
		
		return objraca; 
	}
	@GET
	@Path("/get2")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pessoa> getUser() {
		
	listaPessoa = dao.listaCondicao(Pessoa.class, "permissao = 'usuario'");
		
		return listaPessoa; 
	}
	@GET
	@Path("/getleite")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Leite> getLeite() {
		
	List<Leite>  lista = dao.lista(Leite.class);
		
		return lista; 
	}
	@GET
	@Path("/getProducao")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producao> getProducao() {
		
	List<Producao>  listaPro = dao.lista(Producao.class);
		
		return listaPro; 
	}
	
	
	
}
