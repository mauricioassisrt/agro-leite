package testes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import com.ibm.icu.text.SimpleDateFormat;

import banco.Banco;
import banco.DAOGenerico;
import entidades.Producao;
import entidades.Raca;
import entidades.teste;

public class Teste {
	
	public static void main(String args[]){
		
		double n, m;
		n=420;
		m=15;
		
		double ns = (n/m);
		
		System.out.println(ns);
		
		
		
	}
	public void Tests(){
		List<Producao> listas = new ArrayList<>();
		List<Producao> listas2 = new ArrayList<>();
		DAOGenerico dao = new DAOGenerico();
		listas = dao.lista(Producao.class);

		
		Date d = new Date();
		int i = (int)d.getDate();
		int b = (int)d.getMonth();
		int c = (int)d.getYear();
		int e = (int)d.getDay();
		//tamanho da lista
         int n = listas.size();
         
         Producao pr = new Producao();
         //for pra pegar o ultimo elemento da lista e armazenar no objeto
         for (int j = n-1; j < listas.size(); j++) {
        	
			pr = listas.get(j);
		}
         
     	if(pr.getDataOrdenha().getDate()==i && pr.getDataOrdenha().getMonth()==b && pr.getDataOrdenha().getYear()==c){
    		
    		System.out.println("NO IF");
    		
    	}else{
    		System.out.println("NO else");
    		
    	}
        
	}
}
