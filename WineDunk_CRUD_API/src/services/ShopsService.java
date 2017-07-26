package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblShops;

@Stateless
@LocalBean
public class ShopsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblShops> getShops() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblShops p");
        try { return (List<tblShops>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblShops getShopById(Integer id)
    {
    	try { return em.find(tblShops.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addShop(tblShops device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateShop(tblShops device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteShop(Integer id)
    {
        tblShops device = getShopById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
}