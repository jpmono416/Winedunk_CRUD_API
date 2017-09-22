package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addGrapeVariety(tblGrapeVarieties grapeVariety) {
        try
        {
        	if(grapeVariety.getId() != null) { grapeVariety.setId(null); }
        	em.persist(grapeVariety);
        	return true;
        } catch (Exception e) { return false; }
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