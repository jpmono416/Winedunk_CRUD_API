package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.tblAppellations;
import models.tblCountries;
import models.tblRegions;
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
    
    /*
	 * aripe 2018-04-02 get a single winery by using just name does not work
	 * we need also countryId, regionId, appellarionId for better identification 
	*/
    
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
    
    private tblWineries getWineryByCountryAndRegionAndAppellationAndName(Integer countryId, Integer regionId, Integer appellationId, String name) {
    	try {
    		return em.createNamedQuery("tblWineries.findOneByCountryAndRegionAndAppellationAndName", tblWineries.class)
    				 .setParameter("countryId", countryId)
    				 .setParameter("regionId", regionId)
    				 .setParameter("appellationId", appellationId)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }
    
    private tblWineries getWineryByCountryAndRegionAndName(Integer countryId, Integer regionId, String name) {
    	try {
    		return em.createNamedQuery("tblWineries.findOneByCountryAndRegionAndName", tblWineries.class)
    				 .setParameter("countryId", countryId)
    				 .setParameter("regionId", regionId)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }
    
    private tblWineries getWineryByCountryAndName(Integer countryId, String name) {
    	try {
    		return em.createNamedQuery("tblWineries.findOneByCountryAndName", tblWineries.class)
    				 .setParameter("countryId", countryId)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }
    
    private tblWineries getWineryByName(String name) {
    	try {
    		return em.createNamedQuery("tblWineries.findOneByName", tblWineries.class)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }
    
    private Integer getCountryIdByName(String name) {

		TypedQuery<tblCountries> typedQuery = em.createNamedQuery("tblCountries.findByName", tblCountries.class);
		typedQuery.setParameter("name", name);
		try {
			tblCountries singleResult = typedQuery.getSingleResult();
			if (singleResult != null) {
				return singleResult.getId();
			} else {
				return 0;
			}
		} catch (NoResultException nre) {
			return 0;
		}
		  catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
    
    private Integer getRegionIdByName(String name) {

		TypedQuery<tblRegions> typedQuery = em.createNamedQuery("tblRegions.findByName", tblRegions.class);
		typedQuery.setParameter("name", name);
		try {
			tblRegions singleResult = typedQuery.getSingleResult();
			if (singleResult != null) {
				return singleResult.getId();
			} else {
				return 0;
			}
		} catch (NoResultException nre) {
			return 0;
		}
		  catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
    
    private Integer getAppellationIdByName(String name) {

		TypedQuery<tblAppellations> typedQuery = em.createNamedQuery("tblAppellations.findByName", tblAppellations.class);
		typedQuery.setParameter("name", name);
		try {
			tblAppellations singleResult = typedQuery.getSingleResult();
			if (singleResult != null) {
				return singleResult.getId();
			} else {
				return 0;
			}
		} catch (NoResultException nre) {
			return 0;
		}
		  catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}
    
    public tblWineries getByName(String countryName, String regionNane, String appellationName, String name)
    {
    	Integer countryId = this.getCountryIdByName(countryName);
    	Integer regionId = this.getRegionIdByName(regionNane);
    	Integer appellationId = this.getAppellationIdByName(appellationName);
    	
    	if ( (countryId >= 0) && (regionId >= 0) && (appellationId >= 0) )
    		return getWineryByCountryAndRegionAndAppellationAndName(countryId, regionId, appellationId, name);
    	
    	if ((countryId >= 0) && (regionId >= 0) )
    		return getWineryByCountryAndRegionAndName(countryId, regionId, name);

    	if ( (countryId >= 0) )
    		return getWineryByCountryAndName(countryId, name);

   		return getWineryByName(name);

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