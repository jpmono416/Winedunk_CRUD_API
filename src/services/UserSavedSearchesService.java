package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

    public Boolean addUserSavedSearch(tblUserSavedSearches userSavedSearch) {
        try
        {
        	if(userSavedSearch.getId() != null) { userSavedSearch.setId(null); }
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
        	System.out.println(userSavedSearch);
            em.remove(userSavedSearch);;
            return true;
        }
        return false;
    }
}