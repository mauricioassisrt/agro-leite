/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package banco;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.internal.EntityManagerImpl;

public class Banco {

    private static Banco instancia;
    private EntityManager em;

    private Banco() {
        em = Persistence.createEntityManagerFactory("ProjetoTcc").createEntityManager();
    }

    public synchronized static Banco getInstancia() {
        if (instancia == null) {
            instancia = new Banco();
          System.out.println("Entrou ");
        }
        return instancia;
    }

    public EntityManager getEm() {
        return em;
    }
    public Connection getConnection() {
		EntityManagerImpl factory = (EntityManagerImpl) em;
		SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) factory.getSession().getSessionFactory();
		try {
			return sessionFactoryImpl.getConnectionProvider().getConnection();
		} catch (SQLException ex) {
			Logger.getLogger(Banco.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

}
