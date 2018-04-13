package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserBasket;

@Stateless
@LocalBean
public class UserBasketsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserBasket> getUserBaskets() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblUserBasket p");
        try { return (List<tblUserBasket>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserBasket getUserBasketById(Integer id)
    {
    	try { return em.find(tblUserBasket.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addUserBasket(tblUserBasket device) {
        try
        {
        	//if(device.getId() != null) { device.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = device.getClass().getDeclaredField("partnerProductId").getAnnotation(Column.class).length();
        	if (device.getPartnerProductId() != null && device.getPartnerProductId().length() > maxColumnLength) {
        		device.setPartnerProductId( device.getPartnerProductId().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = device.getClass().getDeclaredField("destinationURL").getAnnotation(Column.class).length();
        	if (device.getDestinationURL() != null && device.getDestinationURL().length() > maxColumnLength) {
        		device.setDestinationURL( device.getDestinationURL().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateUserBasket(tblUserBasket device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteUserBasket(Integer id)
    {
        tblUserBasket device = getUserBasketById(id);
        if(device != null)
        {
            em.remove(device);
            return true;
        }
        return false;
    }
}