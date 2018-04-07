// aripe 2018-04-07
package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Stateless
@LocalBean
public class StoredProceduresService {
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    public boolean callSPUpdateMinPriceOntblWines() {
    	try {
            
    		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("spUpdateMinPriceOntblWines");
            storedProcedure.execute();
            return true;
    	} catch (Exception e) {
    		System.out.println("An exception occurred while calling stored procedure spUpdateMinPriceOntblWines()");
    		e.printStackTrace();
            return false;
    	}
    }
    

    public boolean callSPUpdateCountriesWithWines() {
    	try {
    		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("spUpdateCountriesWithWines");
            storedProcedure.execute();
            return true;
    	} catch (Exception e) {
    		System.out.println("An exception occurred while calling stored procedure spUpdateCountriesWithWines()");
    		e.printStackTrace();
            return false;
    	}
    }
    
}