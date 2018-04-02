package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    
    // aripe 2018-04-02, the following NamedQuery is wrong because a name can't univocally identify a winery
    // but at least a combination of country + region + appellation + name
    /*
    public tblWineries getByName(String name)
    {
    	try {
    		return em.createNamedQuery("tblWineries.findByName", tblWineries.class)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return new tblWineries();
    	}
    }
    */

    // aripe, following above comments, I'm creating two new methods:

    // for getting a single result both countryId, regionId, appellationId and name are needed
    public tblWineries getByName(Integer countryId, Integer regionId, Integer appellationId, String name)
    {	
    	try {
    		return em.createNamedQuery("tblWineries.findOneByName", tblWineries.class)
    				 .setParameter("countryId", countryId)
    				 .setParameter("regionId", regionId)
    				 .setParameter("appellationId", appellationId)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return new tblWineries();
    	}
    }
    
    // if just name is passed a list will be returned
    public List<tblWineries> getAllByName(String name)
    {
    	try {
    		return em.createNamedQuery("tblWineries.findAllByName", tblWineries.class)
    				 .setParameter("name", name)
    				 .getResultList();
    	} catch (NoResultException noResExc) {
    		return new ArrayList<tblWineries>();
    	}
    }

    public Integer addWinery(tblWineries winery) {
        try
        {
        	if(winery.getId() != null) { winery.setId(null); }
        	em.persist(winery);
        	em.flush();
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