package no.hvl.dat107;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class TodoDAO {
	
	private EntityManagerFactory emf 
			= Persistence.createEntityManagerFactory("todoPersistenceUnit", 
			//Map.of("jakarta.persistence.jdbc.password", Passwords.AZURE_PASSWORD));
	
	/* --------------------------------------------------------------------- */

	public Todo finnAlleTodos(int id){
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Todo.class, id);

		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public Todo finnTodoMedPk(int id) {
		
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Todo.class, id);

		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public Todo finnTodoMedTekst(String tekst) {
		
		EntityManager em = emf.createEntityManager();
		
		String sporring = "select t from Todo as t where t.tekst = :tekst";
		
		try {
			TypedQuery<Todo> query = em.createQuery(sporring, Todo.class);
			query.setParameter("tekst", tekst);
			return query.getSingleResult();
			
		} finally {
			em.close();
		}
	}
	
	/* --------------------------------------------------------------------- */

	public Object/*???*/  finnTodosMedTekst(/*???*/) {
		EntityManager em = emf.createEntityManager();
		
		try {
			/*???*/
			return null /*???*/; 
		
		} finally {
			em.close();
		}
	}

	/* --------------------------------------------------------------------- */

	public void lagreNyTodo(Todo nyTodo) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(nyTodo);
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		//Kan returnere PK
	}

	/* --------------------------------------------------------------------- */

	public Object/*???*/ slettTodoMedPk(/*???*/) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			/*???*/
			
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
		return null /*???*/; 
	}

	/* --------------------------------------------------------------------- */

	public Object/*???*/ oppdaterTodo(/*???*/) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			/*???*/
			
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
		return null /*???*/; 
	}

	/* --------------------------------------------------------------------- */

	public Object/*???*/ oppdaterTekst(/*???*/) {
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			/*???*/
			
			tx.commit();
			
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
		
		return null /*???*/; 
	}
}
