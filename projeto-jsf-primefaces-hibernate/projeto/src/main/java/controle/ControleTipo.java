//package controle;
//
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ViewScoped;
//
//import banco.DAOGenerico;
//import entidades.Tipo;
//
//@ManagedBean
//@ViewScoped
//public class ControleTipo {
//     private Tipo objetoTipo = new Tipo();
//    private DAOGenerico dao = new DAOGenerico();
//    private List<Tipo> lista = new ArrayList<>();
//
//    public ControleTipo(){
//        preencher();
//    }
//
//    public void novo() {
//        objetoTipo = new Tipo();
//    }
//    public void excluir(Tipo tipo){
//        objetoTipo = tipo;
//        if(tipo.getId()!=null){
//            try {
//                dao.exluir(tipo);
//            } catch (Exception ex) {
//                Logger.getLogger(ControleTipo.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        novo();
//        preencher();
//    }
//    public void inserir() {
//        if (objetoTipo.getId() == null) {
//            dao.inserir(objetoTipo);
//        } else {
//            dao.salvar(objetoTipo);
//        }
//        preencher();
//        novo();
//    }
//
//    private void preencher() {
//        lista = dao.lista(Tipo.class);
//    }
//
//    public Tipo getObjetoTipo() {
//        return objetoTipo;
//    }
//
//    public void setObjetoTipo(Tipo objetoTipo) {
//        this.objetoTipo = objetoTipo;
//    }
//
//    public List<Tipo> getLista() {
//        return lista;
//    }
//
//    public void setLista(List<Tipo> lista) {
//        this.lista = lista;
//    }
//    
//}
