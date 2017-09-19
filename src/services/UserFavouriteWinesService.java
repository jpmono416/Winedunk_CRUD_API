package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserFavouriteWines;
import models.tblUsers;

@Stateless
@LocalBean
public class UserFavouriteWinesService {
	
	@EJB
	UsersService usersService = new UsersService();
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserFavouriteWines> getUserFavouriteWines() 
    {
        Query query	= em.createQuery("SELECT b FROM tblUserFavouriteWines b");
        try { return (List<tblUserFavouriteWines>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserFavouriteWines getUserFavouriteWineById(Integer id)
    {
    	try { return em.find(tblUserFavouriteWines.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addUserFavouriteWine(tblUserFavouriteWines userFavouriteWine) {
        try
        {
        	if(userFavouriteWine.getId() != null) { userFavouriteWine.setId(null); }
        	em.persist(userFavouriteWine);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateUserFavouriteWine(tblUserFavouriteWines userFavouriteWine)
    {
    	if(userFavouriteWine == null || userFavouriteWine.getId() == null) { return false; }
        em.merge(userFavouriteWine);
        return true;
    }

    public Boolean deleteUserFavouriteWine(Integer id)
    {
        tblUserFavouriteWines userFavouriteWine = getUserFavouriteWineById(id);
        if(userFavouriteWine != null)
        {
        	System.out.println(userFavouriteWine);
            em.remove(userFavouriteWine);;
            return true;
        }
        return false;
    }
    
    public List<tblUserFavouriteWines> getFavouriteWinesByUser(Integer userId)
    {
    	try 
    	{
    		tblUsers user = usersService.getUserById(userId);
    		List<tblUserFavouriteWines> favouriteWines = user.getFavouriteWines();
    		em.getEntityManagerFactory().getCache().evictAll();
    		return favouriteWines;
    	} catch (Exception e) { return null; }
    }
    
    public tblUserFavouriteWines getFavouriteWineByUser(Integer userId, Integer wineId)
    {
    	try
    	{
    		Query query = em.createNativeQuery("SELECT * FROM tblUsers_Favourite_Wines WHERE userId = ?1 AND wineId = ?2", tblUserFavouriteWines.class);
    		query.setParameter(1, userId).setParameter(2, wineId);
    		System.out.println(query.toString()); //TODO DELETE
    		
    		tblUserFavouriteWines wine = (tblUserFavouriteWines) query.getSingleResult();
    		
    		return wine;
    	} catch (Exception e) { e.printStackTrace(); return null; }
    }
}