package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblBrowsers;
@Stateless
@LocalBean
public class BrowsersService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblBrowsers> getBrowsers() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblBrowsers b");
        try { return (List<tblBrowsers>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblBrowsers getBrowserById(Integer id)
    {
    	try { return em.find(tblBrowsers.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addBrowser(tblBrowsers browser) {
        try
        {
        	if(browser.getId() != null) { browser.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = browser.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (browser.getName() != null && browser.getName().length() > maxColumnLength) {
        		browser.setName( browser.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(browser);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateBrowser(tblBrowsers browser)
    {
    	if(browser == null || browser.getId() == null) { return false; }
        em.merge(browser);
        return true;
    }

    public Boolean deleteBrowser(Integer id)
    {
        tblBrowsers browser = getBrowserById(id);
        if(browser != null)
        {
            browser.setDeleted(true);
            em.merge(browser);
            return true;
        }
        return false;
    }
    
     /*
    Devices
     

    public Result getDevicesForBrowser(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblBrowsers.find.byId(id).getDevices())));
    }

    public Result getDeviceForBrowser(Integer browserId, Integer deviceId)
    {
        //Identify the browser
        tblBrowsers browser = tblBrowsers.find.byId(browserId);
        if(browser == null){ return ok("false"); }

        //Identify the device
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        //List of devices of this browser
        List<tblDevices> devices = browser.getDevices();
        if(devices.contains(device)) { return ok("true"); }
        return ok("false");
    }

    public Result addDeviceToBrowser(Integer browserId, Integer deviceId)
    {
        //Identify which browser to add to
        tblBrowsers browser = tblBrowsers.find.byId(browserId);
        if(browser == null){ return ok("false"); }

        //Identify which device we want to add
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }
        browser.devices.add(device);
        browser.save();
        return ok("true");
    }

    public Result deleteDeviceFromBrowser(Integer browserId, Integer deviceId) {
        //Find browser
        tblBrowsers browser = tblBrowsers.find.byId(browserId);
        if(browser == null){ return ok("false"); }

        //Find device
        tblDevices device = tblDevices.find.byId(deviceId);
        if(device == null){ return ok("false"); }

        List<tblDevices> devices = browser.getDevices();
        if(devices.contains(device))
        {
            if (devices.remove(device)) {
                browser.setDevices(devices);
                browser.save();
                device.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}