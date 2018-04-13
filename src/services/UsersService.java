package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
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
    		String queryString = "SELECT * FROM viewUsers WHERE loginToken = \"" + auth + "\"";
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
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = user.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (user.getName() != null && user.getName().length() > maxColumnLength) {
        		user.setName( user.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = user.getClass().getDeclaredField("preferredEmail").getAnnotation(Column.class).length();
        	if (user.getPreferredEmail() != null && user.getPreferredEmail().length() > maxColumnLength) {
        		user.setPreferredEmail( user.getPreferredEmail().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = user.getClass().getDeclaredField("preferredPhoneNumber").getAnnotation(Column.class).length();
        	if (user.getPreferredPhoneNumber() != null && user.getPreferredPhoneNumber().length() > maxColumnLength) {
        		user.setPreferredPhoneNumber( user.getPreferredPhoneNumber().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = user.getClass().getDeclaredField("loginEmail").getAnnotation(Column.class).length();
        	if (user.getLoginEmail() != null && user.getLoginEmail().length() > maxColumnLength) {
        		user.setLoginEmail( user.getLoginEmail().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = user.getClass().getDeclaredField("loginPassword").getAnnotation(Column.class).length();
        	if (user.getLoginPassword() != null && user.getLoginPassword().length() > maxColumnLength) {
        		user.setLoginPassword( user.getLoginPassword().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = user.getClass().getDeclaredField("loginToken").getAnnotation(Column.class).length();
        	if (user.getLoginToken() != null && user.getLoginToken().length() > maxColumnLength) {
        		user.setLoginToken( user.getLoginToken().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = user.getClass().getDeclaredField("recoveringPassEmail").getAnnotation(Column.class).length();
        	if (user.getRecoveringPassEmail() != null && user.getRecoveringPassEmail().length() > maxColumnLength) {
        		user.setRecoveringPassEmail( user.getRecoveringPassEmail().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = user.getClass().getDeclaredField("recoveringPassToken").getAnnotation(Column.class).length();
        	if (user.getRecoveringPassToken() != null && user.getRecoveringPassToken().length() > maxColumnLength) {
        		user.setRecoveringPassToken( user.getRecoveringPassToken().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	if(persistUserAndRefresh(user)) 
        	{ 
	        	viewUsers persistedUser = getUserByEmail(user.getPreferredEmail());
	        	return persistedUser.getId();
        	}
        	return 0;
        } catch (Exception e) { e.printStackTrace(); return 0; }
    }

    public Boolean updateUser(tblUsers user)
    {
    	if(user == null || user.getId() == null) { return false; }
    	if(persistUserAndRefresh(user)) { return true; }
    	return false;
    }

    public Boolean deleteUser(Integer id)
    {
        tblUsers user = getUserById(id);
        if(user != null)
        {
        	user.setDeleted(true);
            if(persistUserAndRefresh(user)) { return true; }
        }
        return false;
    }
    
    public Boolean updatePasswordRecoveryToken(Integer id, String token)
    {
    	tblUsers user =  getUserById(id);
        if(user != null)
        {
        	user.setRecoveringPassToken(token);
        	em.merge(user);
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        }
    	return false;
    }
    
    public Boolean persistUserAndRefresh(tblUsers user)
    {
    	em.merge(user);
        em.getEntityManagerFactory().getCache().evictAll();
        return true;
    }
}