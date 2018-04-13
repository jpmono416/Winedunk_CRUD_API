package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblGrapeVarieties;

@Stateless
@LocalBean
public class GrapeVarietiesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblGrapeVarieties> getGrapeVarieties() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblGrapeVarieties b");
        try { return (List<tblGrapeVarieties>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblGrapeVarieties getGrapeVarietyById(Integer id)
    {
    	try { return em.find(tblGrapeVarieties.class, id); }
    	catch (Exception e) { e.printStackTrace(); return new tblGrapeVarieties(); }
    }

    public Integer addGrapeVariety(tblGrapeVarieties grapeVariety) {
        try
        {
        	if(grapeVariety.getId() != null) { grapeVariety.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = grapeVariety.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (grapeVariety.getName() != null && grapeVariety.getName().length() > maxColumnLength) {
        		grapeVariety.setName( grapeVariety.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(grapeVariety);
        	em.flush();
        	return grapeVariety.getId();
        } catch (Exception e) { return null; }
    }

    public tblGrapeVarieties getByName(String varietyName)
    {
    	try {
    		return em.createNamedQuery("tblGrapeVarieties.findByName", tblGrapeVarieties.class)
		    		 .setParameter("name", varietyName)
		    		 .getSingleResult();
    	} catch(Exception e) {
    		if(!e.getClass().equals(NoResultException.class))
    			e.printStackTrace();
			return new tblGrapeVarieties();
    	}
    }

    public Boolean updateGrapeVariety(tblGrapeVarieties grapeVariety)
    {
    	if(grapeVariety == null || grapeVariety.getId() == null) { return false; }
        em.merge(grapeVariety);
        return true;
    }

    public Boolean deleteGrapeVariety(Integer id)
    {
        tblGrapeVarieties grapeVariety = getGrapeVarietyById(id);
        if(grapeVariety != null)
        {
            grapeVariety.setDeleted(true);
            em.merge(grapeVariety);
            return true;
        }
        return false;
    }
}