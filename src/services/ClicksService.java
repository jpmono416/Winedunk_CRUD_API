package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblClicks;

@Stateless
@LocalBean
public class ClicksService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblClicks> getClicks() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblClicks p");
        try { return (List<tblClicks>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblClicks getClickById(Integer id)
    {
    	try { return em.find(tblClicks.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addClick(tblClicks device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateClick(tblClicks device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteClick(Integer id)
    {
        tblClicks device = getClickById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
}