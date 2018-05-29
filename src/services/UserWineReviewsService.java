package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
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
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = userWineReview.getClass().getDeclaredField("title").getAnnotation(Column.class).length();
        	if (userWineReview.getTitle() != null && userWineReview.getTitle().length() > maxColumnLength) {
        		userWineReview.setTitle( userWineReview.getTitle().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = userWineReview.getClass().getDeclaredField("comments").getAnnotation(Column.class).length();
        	if (userWineReview.getComments() != null && userWineReview.getComments().length() > maxColumnLength) {
        		userWineReview.setComments( userWineReview.getComments().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
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
    	tblUserWineReviews review = null;
    	try {
    		review = (tblUserWineReviews) query.getSingleResult();
    	} catch (Exception e) {
    		review = null;
    	}
    	return (review != null);
    }
    
    public List<tblUserWineReviews> getReviewsForWine(Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM tblUsers_Wines_Reviews WHERE `wineId` = ?1 ORDER BY `addedTimestamp` DESC", tblUserWineReviews.class);
    	query.setParameter(1, wineId);
    	
    	@SuppressWarnings("unchecked")
		List<tblUserWineReviews> reviews = query.getResultList();
    	if(reviews != null) { return reviews; }
		
    	return null;
    }

	public List<tblUserWineReviews> getReviewsForUser(Integer userId) {
		if ( (userId != null) && (userId > 0) ) {
			
			Query query = em.createNativeQuery("SELECT * FROM tblUsers_Wines_Reviews WHERE `userId` = ?1 ORDER BY `addedTimestamp` DESC", tblUserWineReviews.class);
	    	query.setParameter(1, userId);
	    	
	    	@SuppressWarnings("unchecked")
			List<tblUserWineReviews> reviews = query.getResultList();
	    	return reviews;
		} else {
			return null;
		}
		
	}

	public Integer getAmountOfReviewsForWine(Integer wineId) {
		if ( (wineId != null) && (wineId > 0) ) {
			
			String query = "SELECT count(*) FROM `tblUsers_Wines_Reviews` WHERE `wineId` = "+wineId;
			Integer amountOfResults = 0;
			try {
				amountOfResults = Integer.valueOf(((Long) em.createNativeQuery(query).getSingleResult()).intValue());
			} catch (Exception e) {
				amountOfResults = 0;
			}
			
	    	return amountOfResults;
		} else {
			return 0;
		}
		
	}
}