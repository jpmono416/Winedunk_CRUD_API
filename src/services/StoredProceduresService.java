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


	public boolean callSPUpdateRecommendedWines() {
    	try {
    		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("spUpdateRecommendedWines");
            storedProcedure.execute();
            return true;
    	} catch (Exception e) {
    		System.out.println("An exception occurred while calling stored procedure spUpdateRecommendedWines()");
    		e.printStackTrace();
            return false;
    	}
    }


	public boolean callSPUpdateBestOffersbyCountry() {
    	try {
    		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("spUpdateBestOffersbyCountry");
            storedProcedure.execute();
            return true;
    	} catch (Exception e) {
    		System.out.println("An exception occurred while calling stored procedure spUpdateBestOffersbyCountry()");
    		e.printStackTrace();
            return false;
    	}
    }


	public boolean callSPUpdateBestOffersbyMerchants() {
    	try {
    		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("spUpdateBestOffersbyMerchants");
            storedProcedure.execute();
            return true;
    	} catch (Exception e) {
    		System.out.println("An exception occurred while calling stored procedure spUpdateBestOffersbyMerchants()");
    		e.printStackTrace();
            return false;
    	}
    }


	public boolean callSPUpdateBestOffersbyWineType() {
    	try {
    		StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("spUpdateBestOffersbyWineType");
            storedProcedure.execute();
            return true;
    	} catch (Exception e) {
    		System.out.println("An exception occurred while calling stored procedure spUpdateBestOffersbyWineType()");
    		e.printStackTrace();
            return false;
    	}
    }
    
}