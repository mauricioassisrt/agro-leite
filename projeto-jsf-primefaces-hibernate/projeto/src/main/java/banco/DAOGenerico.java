package banco;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class DAOGenerico {

    EntityManager em;


    public List listarComCondicaoInner(Class classe, Class classe2, Class classe3, String condicao, String condicao2) {
          em = Banco.getInstancia().getEm();
          em.getTransaction().begin();
          Query q = em.createQuery("from " + classe.getSimpleName() + " inner join "+classe2.getSimpleName()  + condicao +" inner join " + classe3.getSimpleName() + condicao2);
          em.getTransaction().commit();
          return q.getResultList();
      }
    
    public Object inserir(Object obj) {
        try {
            em = Banco.getInstancia().getEm();
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            System.out.println("Erro no Insert");
        }
        return obj;
    }

    public Object recuperaPorId(Class classe, Long id) {
        em = Banco.getInstancia().getEm();
        Object obj = null;
        em.getTransaction().begin();
        obj = em.find(classe, id);
        em.getTransaction().commit();
        return obj;
    }

    public void salvar(Object objeto) {
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
    }

    public void exluir(Object objeto) {
        try {
            em = Banco.getInstancia().getEm();
            em.getTransaction().begin();
            Method getChave = objeto.getClass().getMethod("getId", new Class[0]);
            objeto = em.find(objeto.getClass(), getChave.invoke(objeto, new Object[0]));
            em.remove(objeto);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            System.out.println("Erro no Excluir");
        }
    }

    public List lista(Class classe) {  //lista completa
        Query q = null;
        try {
            em = Banco.getInstancia().getEm();
            em.getTransaction().begin();
            q = em.createQuery("from " + classe.getSimpleName());
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            System.out.println("Erro na Lista");
        }
        return q.getResultList();
    }

    public Object recupera(Class classe, Long id) {  //busca por id
        em = Banco.getInstancia().getEm();
        Object retornando = null;
        em.getTransaction().begin();
        retornando = em.find(classe, id);
        em.getTransaction().commit();
        return retornando;
    }

    public List listaCondicao(Class classe, String condicao) {  //busca por qualquer atributoinformado
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        Query q = em.createQuery("from " + classe.getSimpleName() + " where " + condicao);
        em.getTransaction().commit();
        return q.getResultList();
    }
    public List listaOrder(Class classe, String condicao) {  //busca por qualquer atributoinformado
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        Query q = em.createQuery("from " + classe.getSimpleName() + " order by id desc " + condicao);
        em.getTransaction().commit();
        return q.getResultList();
    }
    
    public List listarSemCondicao() {
        em = Banco.getInstancia().getEm();
        em.getTransaction().begin();
        Query q = em.createNativeQuery(" from producao order by id DESC LIMIT 5 " );
        em.getTransaction().commit();
        return q.getResultList();
    }
}
