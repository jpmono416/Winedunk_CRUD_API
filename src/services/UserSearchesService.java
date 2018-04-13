package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserSearches;

@Stateless
@LocalBean
public class UserSearchesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserSearches> getUserSearches() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblUserSearches b");
        try { return (List<tblUserSearches>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserSearches getUserSearchById(Integer id)
    {
    	try { return em.find(tblUserSearches.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addUserSearch(tblUserSearches userSearch) {
        try
        {
        	if(userSearch.getId() != null) { userSearch.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = userSearch.getClass().getDeclaredField("keyWords").getAnnotation(Column.class).length();
        	if (userSearch.getKeyWords() != null && userSearch.getKeyWords().length() > maxColumnLength) {
        		userSearch.setKeyWords( userSearch.getKeyWords().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(userSearch);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateUserSearch(tblUserSearches userSearch)
    {
    	try
    	{
    		if(userSearch == null || userSearch.getId() == null) { return false; }
            em.merge(userSearch);
            return true;
    	} catch(Exception e) { return false; }
    }

    public Boolean deleteUserSearch(Integer id)
    {
        tblUserSearches userSearch = getUserSearchById(id);
        if(userSearch != null)
        {
        	System.out.println(userSearch);
            em.remove(userSearch);;
            return true;
        }
        return false;
    }
}