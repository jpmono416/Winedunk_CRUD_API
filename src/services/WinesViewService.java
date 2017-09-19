package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewWines;

@Stateless
@LocalBean
public class WinesViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewWines> getWines() 
	
    {
        Query query	= em.createQuery("SELECT b FROM viewWines b");
        try { return (List<viewWines>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewWines getWineById(Integer id)
    {
    	try { return em.find(viewWines.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    public List<viewWines> getWinesWithQuery(String query)
    {
    	try
    	{
    		Query selectQuery = em.createNativeQuery(query);
        	
        	@SuppressWarnings("unchecked")
    		List <viewWines> results = selectQuery.getResultList();
    		
    		return results;
    	} catch(Exception e) { e.printStackTrace(); }
    	return null;
    }
}