package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblDeviceTypes;

@Stateless
@LocalBean
public class DeviceTypesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblDeviceTypes> getDeviceTypes() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblDeviceTypes b");
        try { return (List<tblDeviceTypes>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblDeviceTypes getDeviceTypeById(Integer id)
    {
    	try { return em.find(tblDeviceTypes.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addDeviceType(tblDeviceTypes device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateDeviceType(tblDeviceTypes device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteDeviceType(Integer id)
    {
        tblDeviceTypes device = getDeviceTypeById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
     /*
    DeviceTypes
     

    public Result getDeviceTypesForDevice(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblDeviceTypes.find.byId(id).getDeviceTypes())));
    }

    public Result getDeviceForDevice(Integer deviceId, Integer deviceId)
    {
        //Identify the device
        tblDeviceTypes device = tblDeviceTypes.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify the device
        tblDeviceTypes device = tblDeviceTypes.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of deviceTypes of this device
        List<tblDeviceTypes> deviceTypes = device.getDeviceTypes();
        if(deviceTypes.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToDevice(Integer deviceId, Integer deviceId)
    {
        //Identify which device to add to
        tblDeviceTypes device = tblDeviceTypes.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify which device we want to add
        tblDeviceTypes device = tblDeviceTypes.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        device.deviceTypes.add(device);
        device.save();
        return ok("true");
    }

    public Result deleteDeviceFromDevice(Integer deviceId, Integer deviceId) {
        //Find device
        tblDeviceTypes device = tblDeviceTypes.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Find device
        tblDeviceTypes device = tblDeviceTypes.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblDeviceTypes> deviceTypes = device.getDeviceTypes();
        if(deviceTypes.contains(device))
        {
            if (deviceTypes.remove(device)) {
                device.setDeviceTypes(deviceTypes);
                device.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}