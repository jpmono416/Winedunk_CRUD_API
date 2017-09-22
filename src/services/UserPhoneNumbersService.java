package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserPhoneNumbers;
import models.tblUsers;

@Stateless
@LocalBean
public class UserPhoneNumbersService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	@EJB
	UsersService usersService = new UsersService();
	
    @SuppressWarnings("unchecked")
	public List<tblUserPhoneNumbers> getUserPhoneNumbers() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblUserPhoneNumbers b");
        try { return (List<tblUserPhoneNumbers>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserPhoneNumbers getUserPhoneNumberById(Integer id)
    {
    	try { return em.find(tblUserPhoneNumbers.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addUserPhoneNumber(tblUserPhoneNumbers userPhoneNumber) {
        try
        {
        	if(userPhoneNumber.getId() != null) { userPhoneNumber.setId(null); }
        	em.persist(userPhoneNumber);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateUserPhoneNumber(tblUserPhoneNumbers userPhoneNumber)
    {
    	if(userPhoneNumber == null || userPhoneNumber.getId() == null) { return false; }
        em.merge(userPhoneNumber);
        return true;
    }

    public Boolean deleteUserPhoneNumber(Integer id)
    {
        tblUserPhoneNumbers userPhoneNumber = getUserPhoneNumberById(id);
        if(userPhoneNumber != null)
        {
        	System.out.println(userPhoneNumber);
            em.remove(userPhoneNumber);
            return true;
        }
        return false;
    }
    
    public List<tblUserPhoneNumbers> getPhoneNumbersForUser(Integer userId)
    {
    	try
    	{
    		tblUsers user = usersService.getUserById(userId);
        	List<tblUserPhoneNumbers> phoneNumbers = user.getPhoneNumbers();
        	em.getEntityManagerFactory().getCache().evictAll();
        	return phoneNumbers;
    	} catch (Exception e) { return null; }
    }
}