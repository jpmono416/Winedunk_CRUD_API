package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblPlatforms;

@Stateless
@LocalBean
public class PlatformsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblPlatforms> getPlatforms() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblPlatforms b");
        try { return (List<tblPlatforms>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblPlatforms getPlatformById(Integer id)
    {
    	try { return em.find(tblPlatforms.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addPlatform(tblPlatforms device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updatePlatform(tblPlatforms device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deletePlatform(Integer id)
    {
        tblPlatforms device = getPlatformById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
     /*
    Platforms
     

    public Result getPlatformsForDevice(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblPlatforms.find.byId(id).getPlatforms())));
    }

    public Result getDeviceForDevice(Integer deviceId, Integer deviceId)
    {
        //Identify the device
        tblPlatforms device = tblPlatforms.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify the device
        tblPlatforms device = tblPlatforms.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of platforms of this device
        List<tblPlatforms> platforms = device.getPlatforms();
        if(platforms.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToDevice(Integer deviceId, Integer deviceId)
    {
        //Identify which device to add to
        tblPlatforms device = tblPlatforms.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify which device we want to add
        tblPlatforms device = tblPlatforms.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        device.platforms.add(device);
        device.save();
        return ok("true");
    }

    public Result deleteDeviceFromDevice(Integer deviceId, Integer deviceId) {
        //Find device
        tblPlatforms device = tblPlatforms.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Find device
        tblPlatforms device = tblPlatforms.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblPlatforms> platforms = device.getPlatforms();
        if(platforms.contains(device))
        {
            if (platforms.remove(device)) {
                device.setPlatforms(platforms);
                device.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}