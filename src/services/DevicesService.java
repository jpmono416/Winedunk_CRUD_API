package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblDevices;

@Stateless
@LocalBean
public class DevicesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblDevices> getDevices() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblDevices b");
        try { return (List<tblDevices>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblDevices getDeviceById(Integer id)
    {
    	try { return em.find(tblDevices.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addDevice(tblDevices device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateDevice(tblDevices device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteDevice(Integer id)
    {
        tblDevices device = getDeviceById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
     /*
    Devices
     

    public Result getDevicesForDevice(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblDevices.find.byId(id).getDevices())));
    }

    public Result getDeviceForDevice(Integer deviceId, Integer deviceId)
    {
        //Identify the device
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify the device
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of devices of this device
        List<tblDevices> devices = device.getDevices();
        if(devices.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToDevice(Integer deviceId, Integer deviceId)
    {
        //Identify which device to add to
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify which device we want to add
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        device.devices.add(device);
        device.save();
        return ok("true");
    }

    public Result deleteDeviceFromDevice(Integer deviceId, Integer deviceId) {
        //Find device
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Find device
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblDevices> devices = device.getDevices();
        if(devices.contains(device))
        {
            if (devices.remove(device)) {
                device.setDevices(devices);
                device.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}