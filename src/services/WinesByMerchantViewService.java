package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewWinesbyMerchants;

@Stateless
@LocalBean
public class WinesByMerchantViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewWinesbyMerchants> getWinesByMerchant() 
    {
        Query query	= em.createQuery("SELECT b FROM viewWinesbyMerchants b");
        try { return (List<viewWinesbyMerchants>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewWinesbyMerchants getWineByMerchantById(Integer id)
    {
    	try { return em.find(viewWinesbyMerchants.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    @SuppressWarnings("unchecked")
	public List<viewWinesbyMerchants> getWinesForMerchant(Integer merchantId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewWinesbyMerchants WHERE merchantId = ?1");
    	query.setParameter(1, merchantId);
    	try { return (List<viewWinesbyMerchants>) query.getResultList(); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
	
	public List<viewWinesbyMerchants> getSimplifiedWinesForMerchant(Integer merchantId)
	{
		String queryString = "SELECT `id`, `wineId`, `wineName`, `wineShortDescription`, `wineImageURL` FROM `viewWinesbyMerchants` WHERE `merchantId` =  ?1 ORDER BY `positionIndex`";
		Query query = em.createNativeQuery(queryString, viewWinesbyMerchants.class);
		query.setParameter(1, merchantId);
		
		@SuppressWarnings("unchecked")
		List<viewWinesbyMerchants> offers = query.getResultList();
		
		return offers;
	}
}