/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import entidades.Raca;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ControleRaca {
     private Raca objetoRaca = new Raca();
    private DAOGenerico dao = new DAOGenerico();
    private List<Raca> lista = new ArrayList<>();

    public ControleRaca(){
        preencher();
    }

    public void novo() {
        objetoRaca = new Raca();
    }
    public void excluir(Raca tipo){
        objetoRaca = tipo;
        if(tipo.getId()!=null){
            try {
                dao.exluir(tipo);
            } catch (Exception ex) {
                Logger.getLogger(ControleRaca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        novo();
        preencher();
    }
    public void inserir() {
        if (objetoRaca.getId() == null) {
            dao.inserir(objetoRaca);
        } else {
            dao.salvar(objetoRaca);
        }
        preencher();
        novo();
    }

    private void preencher() {
        lista = dao.lista(Raca.class);
    }

    public Raca getObjetoRaca() {
        return objetoRaca;
    }

    public void setObjetoRaca(Raca objetoRaca) {
        this.objetoRaca = objetoRaca;
    }

    public List<Raca> getLista() {
        return lista;
    }

    public void setLista(List<Raca> lista) {
        this.lista = lista;
    }
    
}
