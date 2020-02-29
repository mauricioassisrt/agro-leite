/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import entidades.Origem;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ControleOrigem {
     private Origem objetoOrigem = new Origem();
    private DAOGenerico dao = new DAOGenerico();
    private List<Origem> lista = new ArrayList<>();

    public ControleOrigem(){
        preencher();
    }

    public void novo() {
        objetoOrigem = new Origem();
    }
    public void excluir(Origem tipo){
        objetoOrigem = tipo;
        if(tipo.getId()!=null){
            try {
                dao.exluir(tipo);
            } catch (Exception ex) {
                Logger.getLogger(ControleOrigem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        novo();
        preencher();
    }
    public void inserir() {
        if (objetoOrigem.getId() == null) {
            dao.inserir(objetoOrigem);
        } else {
            dao.salvar(objetoOrigem);
        }
        preencher();
        novo();
    }

    private void preencher() {
        lista = dao.lista(Origem.class);
    }

    public Origem getObjetoOrigem() {
        return objetoOrigem;
    }

    public void setObjetoOrigem(Origem objetoOrigem) {
        this.objetoOrigem = objetoOrigem;
    }

    public List<Origem> getLista() {
        return lista;
    }

    public void setLista(List<Origem> lista) {
        this.lista = lista;
    }
    
}
