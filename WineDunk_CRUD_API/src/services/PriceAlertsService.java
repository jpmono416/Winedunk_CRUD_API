package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblUserPriceAlerts;

@Stateless
@LocalBean
public class PriceAlertsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblUserPriceAlerts> getPriceAlerts() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblUserPriceAlerts p");
        try { return (List<tblUserPriceAlerts>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblUserPriceAlerts getPriceAlertById(Integer id)
    {
    	try { return em.find(tblUserPriceAlerts.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addPriceAlert(tblUserPriceAlerts device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updatePriceAlert(tblUserPriceAlerts device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deletePriceAlert(Integer id)
    {
        tblUserPriceAlerts device = getPriceAlertById(id);
        if(device != null)
        {
            em.remove(device);
            return true;
        }
        return false;
    }
}