package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblOperatingSystems;

@Stateless
@LocalBean
public class OperatingSystemsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblOperatingSystems> getOperatingSystems() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblOperatingSystems b");
        try { return (List<tblOperatingSystems>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblOperatingSystems getOperatingSystemById(Integer id)
    {
    	try { return em.find(tblOperatingSystems.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addOperatingSystem(tblOperatingSystems device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = device.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (device.getName() != null && device.getName().length() > maxColumnLength) {
        		device.setName( device.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateOperatingSystem(tblOperatingSystems device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteOperatingSystem(Integer id)
    {
        tblOperatingSystems device = getOperatingSystemById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
     /*
    OperatingSystems
     

    public Result getOperatingSystemsForDevice(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblOperatingSystems.find.byId(id).getOperatingSystems())));
    }

    public Result getDeviceForDevice(Integer deviceId, Integer deviceId)
    {
        //Identify the device
        tblOperatingSystems device = tblOperatingSystems.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify the device
        tblOperatingSystems device = tblOperatingSystems.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of operatingSystems of this device
        List<tblOperatingSystems> operatingSystems = device.getOperatingSystems();
        if(operatingSystems.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToDevice(Integer deviceId, Integer deviceId)
    {
        //Identify which device to add to
        tblOperatingSystems device = tblOperatingSystems.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify which device we want to add
        tblOperatingSystems device = tblOperatingSystems.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        device.operatingSystems.add(device);
        device.save();
        return ok("true");
    }

    public Result deleteDeviceFromDevice(Integer deviceId, Integer deviceId) {
        //Find device
        tblOperatingSystems device = tblOperatingSystems.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Find device
        tblOperatingSystems device = tblOperatingSystems.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblOperatingSystems> operatingSystems = device.getOperatingSystems();
        if(operatingSystems.contains(device))
        {
            if (operatingSystems.remove(device)) {
                device.setOperatingSystems(operatingSystems);
                device.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}