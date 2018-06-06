package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.tblRegions;
import models.viewRegionsWithWines;

@Stateless
@LocalBean
public class RegionsService {
	
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblRegions> getRegions() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblRegions b");
        try { return (List<tblRegions>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblRegions getRegionById(Integer id)
    {
    	try { return em.find(tblRegions.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblRegions getRegionByName(String regionName)
    {
    	try {
    		
    		return em.createNamedQuery("tblRegions.findByName", tblRegions.class)
   		    	 	 .setParameter("name", regionName)
   		    	 	 .getSingleResult();
    	} catch ( Exception e) {
    		return new tblRegions();
    	}
    }

    public Integer addRegion(tblRegions device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = device.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (device.getName() != null && device.getName().length() > maxColumnLength) {
        		device.setName( device.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(device);
        	em.flush();
        	return device.getId();
        } catch (Exception e) { return null; }
    }

    public Boolean updateRegion(tblRegions device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteRegion(Integer id)
    {
        tblRegions device = getRegionById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }

	public List<viewRegionsWithWines> getRegionsFilteredByCountryId(Integer countryId) {
        
		if ( (countryId != null) && (countryId > 0) ) {
		
			TypedQuery<viewRegionsWithWines> typedQuery = em.createNamedQuery("viewRegionsWithWines.findAllByCountryId", viewRegionsWithWines.class);
			typedQuery.setParameter("countryId", countryId);
			try {
				return (List<viewRegionsWithWines>) typedQuery.getResultList();
			} catch (Exception e) {
				return null;
			}
			
		} else {
			return null;
		}
		
	}
    
     /*
    Regions
     

    public Result getRegionsForDevice(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblRegions.find.byId(id).getRegions())));
    }

    public Result getDeviceForDevice(Integer deviceId, Integer deviceId)
    {
        //Identify the device
        tblRegions device = tblRegions.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify the device
        tblRegions device = tblRegions.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of regions of this device
        List<tblRegions> regions = device.getRegions();
        if(regions.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToDevice(Integer deviceId, Integer deviceId)
    {
        //Identify which device to add to
        tblRegions device = tblRegions.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify which device we want to add
        tblRegions device = tblRegions.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        device.regions.add(device);
        device.save();
        return ok("true");
    }

    public Result deleteDeviceFromDevice(Integer deviceId, Integer deviceId) {
        //Find device
        tblRegions device = tblRegions.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Find device
        tblRegions device = tblRegions.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblRegions> regions = device.getRegions();
        if(regions.contains(device))
        {
            if (regions.remove(device)) {
                device.setRegions(regions);
                device.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}