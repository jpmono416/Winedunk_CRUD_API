package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblWinesbyMerchants;

@Stateless
@LocalBean
public class WinesByMerchantService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblWinesbyMerchants> getWinesByMerchant() 
    {
        Query query	= em.createQuery("SELECT p FROM tblWinesbyMerchants p");
        try { return (List<tblWinesbyMerchants>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblWinesbyMerchants getWineByMerchantById(Integer id)
    {
    	try { return em.find(tblWinesbyMerchants.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addWineByMerchant(tblWinesbyMerchants wineByMerchant) {
        try
        {
        	if(wineByMerchant.getId() != null) { wineByMerchant.setId(null); }
        	em.persist(wineByMerchant);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateWineByMerchant(tblWinesbyMerchants wineByMerchant)
    {
    	if(wineByMerchant == null || wineByMerchant.getId() == null) { return false; }
        em.merge(wineByMerchant);
        return true;
    }

    public Boolean deleteWineByMerchant(Integer id)
    {
        tblWinesbyMerchants wineByMerchant = getWineByMerchantById(id);
        if(wineByMerchant != null)
        {
            em.remove(wineByMerchant);
            return true;
        }
        return false;
    }
}