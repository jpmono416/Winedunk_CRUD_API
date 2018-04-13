package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUsersFacebookLogin;

@Stateless
@LocalBean
public class UsersFacebookLoginService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUsersFacebookLogin> getUserFacebookLogins() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblUserFacebookLogins p");
        try { return (List<tblUsersFacebookLogin>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUsersFacebookLogin getUserFacebookLoginById(Integer id)
    {
    	try { return em.find(tblUsersFacebookLogin.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addUserFacebookLogin(tblUsersFacebookLogin device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = device.getClass().getDeclaredField("fbAccessToken").getAnnotation(Column.class).length();
        	if (device.getFbAccessToken() != null && device.getFbAccessToken().length() > maxColumnLength) {
        		device.setFbAccessToken( device.getFbAccessToken().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = device.getClass().getDeclaredField("fbUserID").getAnnotation(Column.class).length();
        	if (device.getFbUserID() != null && device.getFbUserID().length() > maxColumnLength) {
        		device.setFbUserID( device.getFbUserID().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateUserFacebookLogin(tblUsersFacebookLogin device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }
}