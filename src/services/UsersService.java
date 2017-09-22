package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import models.tblUsers;
import views.viewUsers;
@Stateless
@LocalBean
public class UsersService extends DefaultServiceClass {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager entityManager;
	
    @SuppressWarnings("unchecked")
	public List<tblUsers> getUsers() 
    {
        Query query	= em.createQuery("SELECT b FROM tblUsers b");
        try { return (List<tblUsers>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUsers getUserById(Integer id)
    {
    	try 
    	{ 
    		tblUsers user = entityManager.find(tblUsers.class, id);
    		return user;
    	}
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewUsers getUserByEmail(String email)
    {
    	try 
    	{
    		System.out.println("Email on class: " + email);
    		Query query = em.createQuery("SELECT u FROM viewUsers u WHERE u.loginEmail = :email", viewUsers.class).setParameter("email", email);
    		System.out.println("Query: " + query.toString()); //TODO DEL
    		viewUsers user = (viewUsers) query.getSingleResult();
        	return user;
    	} catch (Exception e) { e.printStackTrace();  return null; } 
    }
    
    public viewUsers getUserByAuth(String auth)
    {
    	try
    	{
    		System.out.println("Auth on class: " + auth);//TODO
    		String queryString = "SELECT * FROM viewUsers WHERE loginToken = \"" + auth + "\"";
    		System.out.println("String: " + queryString); //TODO
    		Query query = em.createNativeQuery(queryString, viewUsers.class);
    		System.out.println("Query: " + query.toString());
    		viewUsers user = (viewUsers) query.getSingleResult();
    		return user;
    	} catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    public Integer addUser(tblUsers user) {
        try
        {
        	if(user.getId() != null) { user.setId(null); }
        	em.persist(user);
        	em.getEntityManagerFactory().getCache().evictAll();
        	viewUsers persistedUser = getUserByEmail(user.getPreferredEmail());
        	return persistedUser.getId();
        } catch (Exception e) { e.printStackTrace(); return 0; }
    }

    public Boolean updateUser(tblUsers user)
    {
    	if(user == null || user.getId() == null) { return false; }
        em.merge(user);
        em.getEntityManagerFactory().getCache().evictAll();
        return true;
    }

    public Boolean deleteUser(Integer id)
    {
        tblUsers user = getUserById(id);
        if(user != null)
        {
            user.setDeleted(true);
            em.merge(user);
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        }
        return false;
    }
}