package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewMerchants;
@Stateless
@LocalBean
public class MerchantsViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewMerchants> getMerchants() 
    {
        Query query	= em.createQuery("SELECT m FROM viewMerchants m");
        try { return (List<viewMerchants>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewMerchants getMerchantById(Integer id)
    {
    	try { return em.find(viewMerchants.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    public List<viewMerchants> getMerchantsWithOffers()
    {
    	String queryString = "SELECT DISTINCT `bo`.`merchantId` AS `id`,  `m`.`name`, `m`.`imageURL` FROM `viewBestOffersbyMerchants` `bo` LEFT JOIN `viewMerchants` `m` ON `m`.`id` = `bo`.`merchantId`";
    	Query query = em.createNativeQuery(queryString, viewMerchants.class);
    	
    	@SuppressWarnings("unchecked")
		List<viewMerchants> merchants = query.getResultList();
		return merchants;
    }
}