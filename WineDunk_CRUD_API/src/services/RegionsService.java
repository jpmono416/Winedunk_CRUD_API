package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblRegions;

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
   		    	 	 .setParameter(0, regionName)
   		    	 	 .getSingleResult();
    	} catch ( Exception e) {
    		return null;
    	}
    }

    public Integer addRegion(tblRegions device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
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