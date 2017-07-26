package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUsers_wines_ratings;
@Stateless
@LocalBean
public class UserWineRatingsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUsers_wines_ratings> getUserWineRatings() 
    {
        Query query	= em.createQuery("SELECT b FROM tblUserWineRatings b");
        try { return (List<tblUsers_wines_ratings>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUsers_wines_ratings getUserWineRatingById(Integer id)
    {
    	try { return em.find(tblUsers_wines_ratings.class, id); }
    	catch (Exception e) { return null; }
    }

    public Boolean addUserWineRating(tblUsers_wines_ratings userWineRating) {
        try
        {
        	if(userWineRating.getId() != null) { userWineRating.setId(null); }
        	em.persist(userWineRating);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateUserWineRating(tblUsers_wines_ratings userWineRating)
    {
    	if(userWineRating == null || userWineRating.getId() == null) { return false; }
        em.merge(userWineRating);
        return true;
    }

    public Boolean deleteUserWineRating(Integer id)
    {
        tblUsers_wines_ratings userWineRating = getUserWineRatingById(id);
        if(userWineRating != null)
        {
            em.remove(userWineRating);
            return true;
        }
        return false;
    }
}