package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblVisitors;

@Stateless
@LocalBean
public class VisitorsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblVisitors> getVisitors() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblVisitors p");
        try { return (List<tblVisitors>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblVisitors getVisitorById(Integer id)
    {
    	try { return em.find(tblVisitors.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addVisitor(tblVisitors device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = device.getClass().getDeclaredField("referrerURL").getAnnotation(Column.class).length();
        	if (device.getReferrerURL() != null && device.getReferrerURL().length() > maxColumnLength) {
        		device.setReferrerURL( device.getReferrerURL().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = device.getClass().getDeclaredField("userAgent").getAnnotation(Column.class).length();
        	if (device.getUserAgent() != null && device.getUserAgent().length() > maxColumnLength) {
        		device.setUserAgent( device.getUserAgent().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateVisitor(tblVisitors device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteVisitor(Integer id)
    {
        tblVisitors device = getVisitorById(id);
        if(device != null)
        {
            em.remove(device);
            return true;
        }
        return false;
    }
}