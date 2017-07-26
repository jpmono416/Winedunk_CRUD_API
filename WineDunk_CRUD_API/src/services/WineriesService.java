package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblWineries;

@Stateless
@LocalBean
public class WineriesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblWineries> getWineries() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblWineries b");
        try { return (List<tblWineries>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblWineries getWineryById(Integer id)
    {
    	try { return em.find(tblWineries.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addWinery(tblWineries winery) {
        try
        {
        	if(winery.getId() != null) { winery.setId(null); }
        	em.persist(winery);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateWinery(tblWineries winery)
    {
    	if(winery == null || winery.getId() == null) { return false; }
        em.merge(winery);
        return true;
    }

    public Boolean deleteWinery(Integer id)
    {
        tblWineries winery = getWineryById(id);
        if(winery != null)
        {
            winery.setDeleted(true);
            em.merge(winery);
            return true;
        }
        return false;
    }
}