/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import entidades.Remedios;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ControleRemedios {
     private Remedios objetoRemedios = new Remedios();
    private DAOGenerico dao = new DAOGenerico();
    private List<Remedios> lista = new ArrayList<>();

    public ControleRemedios(){
        preencher();
    }

    public void novo() {
        objetoRemedios = new Remedios();
    }
    public void excluir(Remedios remedio){
        objetoRemedios = remedio;
        if(remedio.getId()!=null){
            try {
                dao.exluir(remedio);
            } catch (Exception ex) {
                Logger.getLogger(ControleRemedios.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        novo();
        preencher();
    }
    public void inserir() {
        if (objetoRemedios.getId() == null) {
            dao.inserir(objetoRemedios);
        } else {
            dao.salvar(objetoRemedios);
        }
        preencher();
        novo();
    }

    private void preencher() {
        lista = dao.lista(Remedios.class);
    }

    public Remedios getObjetoRemedios() {
        return objetoRemedios;
    }

    public void setObjetoRemedios(Remedios objetoRemedios) {
        this.objetoRemedios = objetoRemedios;
    }

    public List<Remedios> getLista() {
        return lista;
    }

    public void setLista(List<Remedios> lista) {
        this.lista = lista;
    }
    
}
