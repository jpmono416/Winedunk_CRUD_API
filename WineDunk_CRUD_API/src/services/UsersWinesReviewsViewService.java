package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewUsersWinesReviews;

@Stateless
@LocalBean
public class UsersWinesReviewsViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewUsersWinesReviews> getReviews() 
	
    {
        Query query	= em.createQuery("SELECT b FROM viewUsers_Wines_Reviews b");
        try { return (List<viewUsersWinesReviews>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewUsersWinesReviews getReviewById(Integer id)
    {
    	try { return em.find(viewUsersWinesReviews.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    public List<viewUsersWinesReviews> getReviewsForUser(Integer userId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewUsers_Wines_Reviews WHERE `userId` = ?1", viewUsersWinesReviews.class);
    	query.setParameter(1, userId);
    	
    	@SuppressWarnings("unchecked")
		List<viewUsersWinesReviews> reviews = query.getResultList();
    	if(reviews != null) { return reviews; }
		
    	return null;
    }
    
    public List<viewUsersWinesReviews> getReviewsForWine(Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewUsers_Wines_Reviews WHERE `wineId` = ?1", viewUsersWinesReviews.class);
    	query.setParameter(1, wineId);
    	
    	@SuppressWarnings("unchecked")
		List<viewUsersWinesReviews> reviews = query.getResultList();
    	if(reviews != null) { return reviews; }
		
    	return null;
    }
    
    public Long getCountOfReviewsForWine(Integer wineId)
    {
    	Query query = em.createNativeQuery("SELECT count(`id`) FROM viewUsers_Wines_Reviews WHERE `wineId` = " + wineId);
    	Long amount = (Long) query.getSingleResult();
    	return amount;
    }
}