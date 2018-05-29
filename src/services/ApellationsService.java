package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.tblAppellations;
import models.tblRegions;
import models.viewAppellationsWithWines;

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
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch (Exception e) {
    		return new tblAppellations();
    	}
    	
    }

    public Integer addApellation(tblAppellations apellation) {
        try
        {
        	if(apellation.getId() != null) { apellation.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = apellation.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (apellation.getName() != null && apellation.getName().length() > maxColumnLength) {
        		apellation.setName( apellation.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(apellation);
        	em.flush();
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

	public List<tblAppellations> getAppellationsFilteredByCountryId(Integer countryId) {
        
		if ( (countryId != null) && (countryId > 0) ) {
		
			TypedQuery<tblAppellations> typedQuery = em.createNamedQuery("tblAppellations.findAllByCountryId", tblAppellations.class);
			typedQuery.setParameter("countryId", countryId);
			try {
				return (List<tblAppellations>) typedQuery.getResultList();
			} catch (Exception e) {
				return null;
			}
			
		} else {
			return null;
		}
		
	}

	public List<viewAppellationsWithWines> getAppellationsByRegionId(Integer regionId) {
        
		if ( (regionId != null) && (regionId > 0) ) {
		
			TypedQuery<viewAppellationsWithWines> typedQuery = em.createNamedQuery("viewAppellationsWithWines.findAllByRegionId", viewAppellationsWithWines.class);
			typedQuery.setParameter("regionId", regionId);
			try {
				return (List<viewAppellationsWithWines>) typedQuery.getResultList();
			} catch (Exception e) {
				return null;
			}
			
		} else {
			return null;
		}
		
	}

	public List<tblAppellations> getAppellationsFilteredByCountryIdAndRegionId(Integer countryId, Integer regionId) {
        
		if ( (countryId != null) && (countryId > 0) && (regionId != null) && (regionId > 0) ) {
		
			TypedQuery<tblAppellations> typedQuery = em.createNamedQuery("tblAppellations.findAllByCountryIdAndRegionId", tblAppellations.class);
			typedQuery.setParameter("countryId", countryId);
			typedQuery.setParameter("regionId", regionId);
			try {
				return (List<tblAppellations>) typedQuery.getResultList();
			} catch (Exception e) {
				return null;
			}
			
		} else {
			return null;
		}
		
	}
}