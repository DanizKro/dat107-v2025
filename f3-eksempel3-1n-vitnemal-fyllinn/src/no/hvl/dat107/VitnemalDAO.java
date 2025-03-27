package no.hvl.dat107;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class VitnemalDAO {

    private EntityManagerFactory emf;

    public VitnemalDAO() {
        emf = Persistence.createEntityManagerFactory("vitnemalPU"); 
        // //Passoret står i persistance.XML		
		//Map.of("jakarta.persistence.jdbc.password", "12Tusenfryd"));
    }
    
    /* --------------------------------------------------------------------- */

    public Vitnemal hentVitnemalForStudent(int studnr) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
        	
        	return em.find(Vitnemal.class, studnr);
        	
        } finally {
            em.close();
        }
    }

    /* --------------------------------------------------------------------- */

    public Karakter hentKarakterForStudentIEmne(int studnr, String emnekode) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
        	
//        	String sporring = "select k from Karakter as k" 
//        			+ " where k.vitnemal.studnr = :studnr"
//        			+ " and k.emnekode = :emnekode";
        	
        	String sporring = """
        		    SELECT k FROM Karakter k
        		    WHERE k.vitnemal.studnr = :studnr
        		    AND k.emnekode = :emnekode
        		""";
        	
        	TypedQuery<Karakter> query = em.createQuery(sporring, Karakter.class);
        	query.setParameter("studnr", studnr);
        	query.setParameter("emnekode", emnekode);
        	
        	return query.getSingleResult();
        	
        } catch(NoResultException e){
        	return null;
        	
        } finally {
            em.close();
        }
    }
    
    /* --------------------------------------------------------------------- */

    public void registrerKarakterForStudent(int studNr, String emnekode,
    		LocalDate eksDato, String bokstav) {
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();		//Starte en transaksjon fordi vi skal oppdatere en kolonne
        
        try {
        	tx.begin();
        	
        	Karakter k = hentKarakterForStudentIEmne(studNr, emnekode);	//Ditached
        	Vitnemal v = em.find(Vitnemal.class, studNr);				//Managed
        	
        	if(k != null) {
        		v.fjernKarakter(k);
        		k = em.merge(k);										//Må få K til å peke på Managed objekt
        		em.remove(k);
        	}
        	
        	Karakter nyK = new Karakter(emnekode, eksDato, bokstav, v);
        	em.persist(nyK);
        	
        	v.leggTilKarakter(nyK);
        	
        	tx.commit();
        	
        } finally {
            em.close();
        }
    }
    
    /* --------------------------------------------------------------------- */

    public List<Karakter> hentKarakterlisteForFerdige(String emnekode) {
        
        EntityManager em = emf.createEntityManager();
        
        try {
        	
        	/* 
        	   	Finne liste av DAT107-karakterer for studenter som er
				ferdig (har sluttdato). Forventer kun denne:
				(1, DAT107, '2022-05-30', 'A', 123456)
				
        	  I SQL kan det se slik ut:
        	  		SELECT k.* 
					FROM karakter AS k 
					NATURAL JOIN vitnemal AS v
					WHERE v.studieslutt IS NOT NULL
					AND k.emnekode LIKE 'DAT107';
        	 */
        	
        	String jpqlQuery = """
        			select k 
        			from Karakter as k, 
        			k.vitnemal as v 
        			where v.studieslutt is not null
        			and k.emnekode like :emnekode""";
        	
			TypedQuery<Karakter> query = em.createQuery(jpqlQuery, Karakter.class);
			query.setParameter("emnekode", emnekode);

			return query.getResultList();
        	
        } finally {
            em.close();
        }
    }
    

}

