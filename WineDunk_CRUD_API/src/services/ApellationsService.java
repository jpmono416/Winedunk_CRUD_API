package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblAppellations;

@Stateless
@LocalBean
public class ApellationsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblAppellations> getApellations() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblAppellations b");
        try { return (List<tblAppellations>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblAppellations getApellationById(Integer id)
    {
    	try { return em.find(tblAppellations.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblAppellations getApellationByName(String name)
    {
    	try {
    		return em.createNamedQuery("tblAppellations.findByName", tblAppellations.class)
    				 .setParameter(0, name)
    				 .getSingleResult();
    	} catch (Exception e) {
    		return null;
    	}
    	
    }

    public Integer addApellation(tblAppellations apellation) {
        try
        {
        	if(apellation.getId() != null) { apellation.setId(null); }
        	em.persist(apellation);
        	return apellation.getId();
        } catch (Exception e) { return null; }
    }

    public Boolean updateApellation(tblAppellations apellation)
    {
    	if(apellation == null || apellation.getId() == null) { return false; }
        em.merge(apellation);
        return true;
    }

    public Boolean deleteApellation(Integer id)
    {
        tblAppellations apellation = getApellationById(id);
        if(apellation != null)
        {
            apellation.setDeleted(true);
            em.merge(apellation);
            return true;
        }
        return false;
    }
}