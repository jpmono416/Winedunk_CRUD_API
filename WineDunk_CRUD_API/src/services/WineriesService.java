package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    public tblWineries getByName(String name)
    {
    	try {
    		return em.createNamedQuery("tblWineries.findByName", tblWineries.class)
    				 .setParameter(0, name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }

    public Integer addWinery(tblWineries winery) {
        try
        {
        	if(winery.getId() != null) { winery.setId(null); }
        	em.persist(winery);
        	return winery.getId();
        } catch (Exception e) { return null; }
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