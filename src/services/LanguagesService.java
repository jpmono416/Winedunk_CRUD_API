package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblLanguages;

@Stateless
@LocalBean
public class LanguagesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblLanguages> getLanguages() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblLanguages b");
        try { return (List<tblLanguages>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblLanguages getLanguageById(Integer id)
    {
    	try { return em.find(tblLanguages.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addLanguage(tblLanguages device) {
        try
        {
        	if(device.getId() != null) { device.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = device.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (device.getName() != null && device.getName().length() > maxColumnLength) {
        		device.setName( device.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = device.getClass().getDeclaredField("shortName").getAnnotation(Column.class).length();
        	if (device.getShortName() != null && device.getShortName().length() > maxColumnLength) {
        		device.setShortName( device.getShortName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(device);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateLanguage(tblLanguages device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
    }

    public Boolean deleteLanguage(Integer id)
    {
        tblLanguages device = getLanguageById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
     /*
    Languages
     

    public Result getLanguagesForDevice(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblLanguages.find.byId(id).getLanguages())));
    }

    public Result getDeviceForDevice(Integer deviceId, Integer deviceId)
    {
        //Identify the device
        tblLanguages device = tblLanguages.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify the device
        tblLanguages device = tblLanguages.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of languages of this device
        List<tblLanguages> languages = device.getLanguages();
        if(languages.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToDevice(Integer deviceId, Integer deviceId)
    {
        //Identify which device to add to
        tblLanguages device = tblLanguages.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Identify which device we want to add
        tblLanguages device = tblLanguages.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        device.languages.add(device);
        device.save();
        return ok("true");
    }

    public Result deleteDeviceFromDevice(Integer deviceId, Integer deviceId) {
        //Find device
        tblLanguages device = tblLanguages.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //Find device
        tblLanguages device = tblLanguages.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblLanguages> languages = device.getLanguages();
        if(languages.contains(device))
        {
            if (languages.remove(device)) {
                device.setLanguages(languages);
                device.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}