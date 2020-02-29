package controle;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import banco.DAOGenerico;
import entidades.Leite;

@ManagedBean
@ViewScoped
public class ControleLeite{
    private Leite objetoLeite = new Leite();
   private DAOGenerico dao = new DAOGenerico();
   private List<Leite> lista = new ArrayList<>();

   public ControleLeite(){
       preencher();
     
   }
   public void novo() {
       objetoLeite = new Leite();
   }
 
   
   public void excluir(Leite Leite){
       objetoLeite = Leite;
       if(Leite.getId()!=null){
           try {
               dao.exluir(Leite);
           } catch (Exception ex) {
               Logger.getLogger(ControleLeite.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       novo();
       preencher();
   }
   public void inserir() {
	   FacesContext context = FacesContext.getCurrentInstance();
	   if (objetoLeite.getId() == null) {
           dao.inserir(objetoLeite);
       } else {
           dao.salvar(objetoLeite);
       }
      	
       preencher();
       novo();	
       
//   	HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
//	session.removeAttribute("graficosGeral");
//       
// 
       
   }

   private void preencher() {
       lista = dao.lista(Leite.class);
   }

   public Leite getObjetoLeite() {
       return objetoLeite;
   }

   public void setObjetoLeite(Leite objetoLeite) {
       this.objetoLeite = objetoLeite;
   }

   public List<Leite> getLista() {
       return lista;
   }

   public void setLista(List<Leite> lista) {
       this.lista = lista;
   }

}
