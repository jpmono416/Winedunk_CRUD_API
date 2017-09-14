package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.tblPartners;

@Stateless
@LocalBean
public class PartnersService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	public List<tblPartners> getPartners() 
    {
		try { return em.createNamedQuery("tblPartners.findAll", tblPartners.class).getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblPartners getPartnerById(Integer id)
    {
    	try { return em.find(tblPartners.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addPartner(tblPartners device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updatePartner(tblPartners device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deletePartner(Integer id)
    {
        tblPartners device = getPartnerById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
}