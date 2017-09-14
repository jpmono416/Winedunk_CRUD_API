package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewUserWinesRatings;

@Stateless
@LocalBean
public class UsersWinesRatingsViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewUserWinesRatings> getRatings() 
	
    {
        Query query	= em.createQuery("SELECT b FROM viewUsers_Wines_Ratings b");
        try { return (List<viewUserWinesRatings>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewUserWinesRatings getRatingById(Integer id)
    {
    	try { return em.find(viewUserWinesRatings.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    public List<viewUserWinesRatings> getRatingsForUser(Integer userId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewUsers_Wines_Ratings WHERE `userId` = ?1", viewUserWinesRatings.class);
    	query.setParameter(1, userId);
    	
    	@SuppressWarnings("unchecked")
		List<viewUserWinesRatings> ratings = query.getResultList();
    	if(ratings != null) { return ratings; }
		
    	return null;
    }
    
    public List<viewUserWinesRatings> getRatingsForWine(Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewUsers_Wines_Ratings WHERE `wineId` = ?1", viewUserWinesRatings.class);
    	query.setParameter(1, wineId);
    	
    	@SuppressWarnings("unchecked")
		List<viewUserWinesRatings> ratings = query.getResultList();
    	if(ratings != null) { return ratings; }
		
    	return null;
    }
    
    public Long getCountOfRatingsForWine(Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT count(`id`) FROM viewUsers_Wines_Ratings WHERE `wineId` = " + wineId);
    	Long amount = (Long) query.getSingleResult();
    	return amount;
    }
}