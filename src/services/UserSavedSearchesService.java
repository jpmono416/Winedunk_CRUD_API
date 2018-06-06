package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserSavedSearches;

@Stateless
@LocalBean
public class UserSavedSearchesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserSavedSearches> getUserSavedSearches() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblUserSavedSearches b");
        try { return (List<tblUserSavedSearches>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserSavedSearches getUserSavedSearchById(Integer id)
    {
    	try { return em.find(tblUserSavedSearches.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public List<tblUserSavedSearches> getSearchesForUser(Integer userId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM tblUserSavedSearches WHERE `userId` = ?1 ", tblUserSavedSearches.class);
    	query.setParameter(1, userId);

    	@SuppressWarnings("unchecked")
		List<tblUserSavedSearches> results = query.getResultList();
		
		return results;
    }
    
    public Boolean addUserSavedSearch(tblUserSavedSearches userSavedSearch) {
        try
        {
        	if(userSavedSearch.getId() != null) { userSavedSearch.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = userSavedSearch.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (userSavedSearch.getName() != null && userSavedSearch.getName().length() > maxColumnLength) {
        		userSavedSearch.setName( userSavedSearch.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = userSavedSearch.getClass().getDeclaredField("urlString").getAnnotation(Column.class).length();
        	if (userSavedSearch.getUrlString() != null && userSavedSearch.getUrlString().length() > maxColumnLength) {
        		userSavedSearch.setUrlString( userSavedSearch.getUrlString().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(userSavedSearch);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateUserSavedSearch(tblUserSavedSearches userSavedSearch)
    {
    	if(userSavedSearch == null || userSavedSearch.getId() == null) { return false; }
        em.merge(userSavedSearch);
        return true;
    }

    public Boolean deleteUserSavedSearch(Integer id)
    {
        tblUserSavedSearches userSavedSearch = getUserSavedSearchById(id);
        if(userSavedSearch != null)
        {
            em.remove(userSavedSearch);;
            return true;
        }
        return false;
    }
}