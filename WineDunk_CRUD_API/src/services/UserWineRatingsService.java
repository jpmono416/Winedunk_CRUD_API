package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserWinesRatings;
import views.viewUsersWinesReviews;
@Stateless
@LocalBean
public class UserWineRatingsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserWinesRatings> getUserWineRatings() 
    {
        Query query	= em.createQuery("SELECT b FROM tblUserWineRatings b");
        try { return (List<tblUserWinesRatings>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserWinesRatings getUserWineRatingById(Integer id)
    {
    	try { return em.find(tblUserWinesRatings.class, id); }
    	catch (Exception e) { return null; }
    }

    public Boolean addUserWineRating(tblUserWinesRatings userWineRating) {
        try
        {
        	if(userWineRating.getId() != null) { userWineRating.setId(null); }
        	em.persist(userWineRating);
        	
        	// Make sure changes are visible immediately
        	em.getEntityManagerFactory().getCache().evictAll();
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateUserWineRating(tblUserWinesRatings userWineRating)
    {
    	System.out.println("Entered update method"); //TODO DELETE
    	if(userWineRating == null || userWineRating.getId() == null) { return false; }
        em.merge(userWineRating);
        return true;
    }

    public Boolean deleteUserWineRating(Integer id)
    {
        tblUserWinesRatings userWineRating = getUserWineRatingById(id);
        if(userWineRating != null)
        {
            em.remove(userWineRating);
            return true;
        }
        return false;
    }
    
    public Boolean userHasReviewed(Integer userId, Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM tblUsers_Wines_Ratings WHERE `userId` = ?1 AND `wineId` = ?2 LIMIT 1", tblUserWinesRatings.class);
    	query.setParameter(1, userId).setParameter(2, wineId);
    	
    	tblUserWinesRatings rating = new tblUserWinesRatings();
    	try
    	{
    		rating = (tblUserWinesRatings) query.getSingleResult();
    		if(rating.getId() != null) { return true; }
    	} catch(Exception e) { return false;}
    	
    	
    	return false;
    }
    
    public Long getCountOfRatingsForWine(Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT count(`id`) FROM tblUsers_Wines_Ratings WHERE `wineId` = " + wineId);
    	Long amount = (Long) query.getSingleResult();
    	return amount;
    }
    
    public List<tblUserWinesRatings> getRatingsForUser(Integer userId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM tblUsers_Wines_Ratings WHERE `userId` = ?1", tblUserWinesRatings.class);
    	query.setParameter(1, userId);
    	
    	@SuppressWarnings("unchecked")
    	List<tblUserWinesRatings> ratings = query.getResultList();
    	if(ratings != null) { return ratings; }
    	
    	return null;
    }
}