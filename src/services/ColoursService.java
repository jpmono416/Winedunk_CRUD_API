package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblColours;

@Stateless
@LocalBean
public class ColoursService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblColours> getColours() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblColours b");
        try { return (List<tblColours>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblColours getColourById(Integer id)
    {
    	try { return em.find(tblColours.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    public tblColours getByName(String name)
    {
    	try {
    		return em.createNamedQuery("tblColours.findByName", tblColours.class)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }

    public Integer addColour(tblColours colour) {
        try
        {
        	if(colour.getId() != null) { colour.setId(null); }
        	em.persist(colour);
        	em.flush();
        	return colour.getId();
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean updateColour(tblColours colour)
    {
    	if(colour == null || colour.getId() == null) { return false; }
        em.merge(colour);
        return true;
    }

    public Boolean deleteColour(Integer id)
    {
        tblColours colour = getColourById(id);
        if(colour != null)
        {
            colour.setDeleted(true);
            em.merge(colour);
            return true;
        }
        return false;
    }
}