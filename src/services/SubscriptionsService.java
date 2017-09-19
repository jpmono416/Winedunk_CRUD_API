package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserSubscriptions;

@Stateless
@LocalBean
public class SubscriptionsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserSubscriptions> getSubscriptions() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblUserSubscriptions p");
        try { return (List<tblUserSubscriptions>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserSubscriptions getSubscriptionById(Integer id)
    {
    	try { return em.find(tblUserSubscriptions.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addSubscription(tblUserSubscriptions device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateSubscription(tblUserSubscriptions device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteSubscription(Integer id)
    {
        tblUserSubscriptions device = getSubscriptionById(id);
        if(device != null)
        {
            em.remove(device);
            return true;
        }
        return false;
    }
}