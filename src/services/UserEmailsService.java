package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserEmails;
import models.tblUsers;

@Stateless
@LocalBean
public class UserEmailsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	@EJB
	UsersService usersService = new UsersService();
	
    @SuppressWarnings("unchecked")
	public List<tblUserEmails> getUserEmails() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblUserEmails b");
        try { return (List<tblUserEmails>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserEmails getUserEmailById(Integer id)
    {
    	try { return em.find(tblUserEmails.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addUserEmail(tblUserEmails userEmail) {
        try
        {
        	if(userEmail.getId() != null) { userEmail.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = userEmail.getClass().getDeclaredField("emailAddress").getAnnotation(Column.class).length();
        	if (userEmail.getEmailAddress() != null && userEmail.getEmailAddress().length() > maxColumnLength) {
        		userEmail.setEmailAddress( userEmail.getEmailAddress().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(userEmail);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateUserEmail(tblUserEmails userEmail)
    {
    	if(userEmail == null || userEmail.getId() == null) { return false; }
        em.merge(userEmail);
        return true;
    }

    public Boolean deleteUserEmail(Integer id)
    {
        tblUserEmails userEmail = getUserEmailById(id);
        if(userEmail != null)
        {
        	System.out.println(userEmail);
            em.remove(userEmail);;
            return true;
        }
        return false;
    }
    
    public List<tblUserEmails> getEmailsForUser(Integer userId)
    {
    	try
    	{
    		tblUsers user = usersService.getUserById(userId);
    		List<tblUserEmails> emailAddresses = user.getEmailAddresses();
    		return emailAddresses;
    	}catch (Exception e) { return null; }
    }
}