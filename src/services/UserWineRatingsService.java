package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserWineReviews;
import models.tblUserWinesRatings;
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
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateUserWineRating(tblUserWinesRatings userWineRating)
    {
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
    
    public Boolean userHasRated(Integer userId, Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM tblUsers_Wines_Ratings WHERE `userId` = ?1 AND `wineId` = ?2 LIMIT 1", tblUserWinesRatings.class);
    	query.setParameter(1, userId).setParameter(2, wineId);
    	tblUserWinesRatings rating = null;
    	try {
    		rating = (tblUserWinesRatings) query.getSingleResult();
    	} catch (Exception e) {
    		rating = null;
    	}
    	return (rating != null);
    }

	public Integer getAmountOfRatingForWine(Integer wineId) {
		if ( (wineId != null) && (wineId > 0) ) {
			
			String query = "SELECT count(*) FROM `tblUsers_Wines_Ratings` WHERE `wineId` = "+wineId;
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

	public List<tblUserWinesRatings> getRatingsForUser(Integer userId) {
		if ( (userId != null) && (userId > 0) ) {
			
			Query query = em.createNativeQuery("SELECT * FROM `tblUsers_Wines_Ratings` WHERE `userId` = ?1", tblUserWinesRatings.class);
	    	query.setParameter(1, userId);
	    	
	    	@SuppressWarnings("unchecked")
			List<tblUserWinesRatings> reviews = query.getResultList();
	    	return reviews;
		} else {
			return null;
		}
		
	}

	public Double getAVGRatingForWine(Integer wineId) {
		if ( (wineId != null) && (wineId > 0) ) {
			try {
				String query = "SELECT avg(rating) FROM tblUsers_Wines_Ratings WHERE wineId = " + wineId;
				Number singleResult = ((Number) em.createNativeQuery(query).getSingleResult());
				
				if (singleResult != null) { return singleResult.doubleValue(); } else { return (double) 0; }
				
			} catch (Exception e) {
				return (double) 0;
			}
		} else {
			return (double) 0;
		}
		
	}

	public Integer getUserRatingValue(Integer userId, Integer wineId) {
		if ( (wineId != null) && (wineId > 0) && (userId != null) && (userId > 0)) {
			
			try {
				String query = "SELECT `rating` FROM `tblUsers_Wines_Ratings` WHERE userId = "+userId+" AND wineId = " + wineId;
				
				return ((Integer) em.createNativeQuery(query).getSingleResult());
				
			} catch (Exception e) {
				return 0;
			}
			
		} else {
			return 0;
		}
	}
	
}