package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserWineReviews;
@Stateless
@LocalBean
public class UserWineReviewsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserWineReviews> getUserWineReviews() 
    {
        Query query	= em.createQuery("SELECT b FROM tblUsers_Wines_Reviews b");
        try { return (List<tblUserWineReviews>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserWineReviews getUserWineReviewById(Integer id)
    {
    	try { return em.find(tblUserWineReviews.class, id); }
    	catch (Exception e) { return null; }
    }

    public Boolean addUserWineReview(tblUserWineReviews userWineReview) {
        try
        {
        	if(userWineReview.getId() != null) { userWineReview.setId(null); }
        	em.persist(userWineReview);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateUserWineReview(tblUserWineReviews userWineReview)
    {
    	if(userWineReview == null || userWineReview.getId() == null) { return false; }
        em.merge(userWineReview);
        return true;
    }

    public Boolean deleteUserWineReview(Integer id)
    {
        tblUserWineReviews userWineReview = getUserWineReviewById(id);
        if(userWineReview != null)
        {
            em.remove(userWineReview);
            return true;
        }
        return false;
    }
    
    public Boolean userHasReviewed(Integer userId, Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM tblUsers_Wines_Reviews WHERE `userId` = ?1 AND `wineId` = ?2 LIMIT 1", tblUserWineReviews.class);
    	query.setParameter(1, userId).setParameter(2, wineId);
    	
    	tblUserWineReviews review = (tblUserWineReviews) query.getSingleResult();
    	if(review != null) { return true; }
    	
    	return false;
    }
    
    public List<tblUserWineReviews> getReviewsForWine(Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM tblUsers_Wines_Reviews WHERE `wineId` = ?1", tblUserWineReviews.class);
    	query.setParameter(1, wineId);
    	
    	@SuppressWarnings("unchecked")
		List<tblUserWineReviews> reviews = query.getResultList();
    	if(reviews != null) { return reviews; }
		
    	return null;
    }
}