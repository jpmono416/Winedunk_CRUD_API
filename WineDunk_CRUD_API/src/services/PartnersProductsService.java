package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblPartnersProducts;

@Stateless
@LocalBean
public class PartnersProductsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblPartnersProducts> getPartnersProducts() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblPartnersProducts p");
        try { return (List<tblPartnersProducts>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblPartnersProducts getPartnersProductById(Integer id)
    {
    	try { return em.find(tblPartnersProducts.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addPartnersProduct(tblPartnersProducts device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updatePartnersProduct(tblPartnersProducts device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deletePartnersProduct(Integer id)
    {
        tblPartnersProducts device = getPartnersProductById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
}