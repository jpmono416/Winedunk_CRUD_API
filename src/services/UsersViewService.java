package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewUsers;
@Stateless
@LocalBean
public class UsersViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewUsers> getUsers() 
	
    {
        Query query	= em.createQuery("SELECT b FROM viewUsers b");
        try { return (List<viewUsers>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewUsers getUserById(Integer id)
    {
    	try { return em.find(viewUsers.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
}