/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import entidades.Pessoa;
import util.UsuarioLogado;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ControlePessoa {
     private Pessoa objetoPessoa = new Pessoa();
    private DAOGenerico dao = new DAOGenerico();
    private List<Pessoa> lista = new ArrayList<>();
    private int senha2;
    
    public ControlePessoa(){
        preencher();
        objetoPessoa = UsuarioLogado.retornaUsuarioLogado();
    }

    public void novo() {
        objetoPessoa = new Pessoa();
    }
    
   
    public void excluir(Pessoa tipo){
        objetoPessoa = tipo;
        if(tipo.getId()!=null){
            try {
                dao.exluir(tipo);
            } catch (Exception ex) {
                Logger.getLogger(ControlePessoa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        novo();
        preencher();
    }
    public void inserir() throws IOException{
        if (objetoPessoa.getId() == null) {
        		
                dao.inserir(objetoPessoa);
            
            
        } else {
        	
            dao.salvar(objetoPessoa);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Tcc/j_spring_security_logout");
        }
        preencher();
        novo();
    }

    private void preencher() {
        lista = dao.lista(Pessoa.class);
    }

    public Pessoa getObjetoPessoa() {
        return objetoPessoa;
    }

    public void setObjetoPessoa(Pessoa objetoPessoa) {
        this.objetoPessoa = objetoPessoa;
    }

    public List<Pessoa> getLista() {
        return lista;
    }

    public void setLista(List<Pessoa> lista) {
        this.lista = lista;
    }

	public int getSenha2() {
		return senha2;
	}

	public void setSenha2(int senha2) {
		this.senha2 = senha2;
	}

	
    
}
