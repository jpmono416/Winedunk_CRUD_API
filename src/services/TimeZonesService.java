package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblTimeZones;

@Stateless
@LocalBean
public class TimeZonesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblTimeZones> getTimeZones() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblTimeZones b");
        try { return (List<tblTimeZones>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblTimeZones getTimeZoneById(Integer id)
    {
    	try { return em.find(tblTimeZones.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addTimeZone(tblTimeZones device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateTimeZone(tblTimeZones device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteTimeZone(Integer id)
    {
        tblTimeZones device = getTimeZoneById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
     /*
    TimeZones
     

    public Result getTimeZonesForDevice(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblTimeZones.find.byId(id).getTimeZones())));
    }

    public Result getDeviceForDevice(Integer deviceId, Integer deviceId)
    {
        //Identify the device
        tblTimeZones device = tblTimeZones.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify the device
        tblTimeZones device = tblTimeZones.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of timeZones of this device
        List<tblTimeZones> timeZones = device.getTimeZones();
        if(timeZones.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToDevice(Integer deviceId, Integer deviceId)
    {
        //Identify which device to add to
        tblTimeZones device = tblTimeZones.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify which device we want to add
        tblTimeZones device = tblTimeZones.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        device.timeZones.add(device);
        device.save();
        return ok("true");
    }

    public Result deleteDeviceFromDevice(Integer deviceId, Integer deviceId) {
        //Find device
        tblTimeZones device = tblTimeZones.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Find device
        tblTimeZones device = tblTimeZones.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblTimeZones> timeZones = device.getTimeZones();
        if(timeZones.contains(device))
        {
            if (timeZones.remove(device)) {
                device.setTimeZones(timeZones);
                device.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}