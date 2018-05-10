package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewBestOffersbyMerchants;

@Stateless
@LocalBean
public class BestOffersByMerchantViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewBestOffersbyMerchants> getBestOffersByMerchant() 
    {
        Query query	= em.createQuery("SELECT b FROM viewBestOffersbyMerchants b");
        try { return (List<viewBestOffersbyMerchants>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewBestOffersbyMerchants getBestOfferByMerchantById(Integer id)
    {
    	try { return em.find(viewBestOffersbyMerchants.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    @SuppressWarnings("unchecked")
	public List<viewBestOffersbyMerchants> getBestOffersForMerchant(Integer merchantId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewBestOffersbyMerchants WHERE merchantId = ?1");
    	query.setParameter(1, merchantId);
    	try { return (List<viewBestOffersbyMerchants>) query.getResultList(); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
	
	public List<viewBestOffersbyMerchants> getSimplifiedBestOffersForMerchant(Integer merchantId)
	{
		// aripe
		// String queryString = "SELECT `id`, `wineId`, `wineName`, `wineShortDescription`, `wineImageURL` FROM `viewBestOffersbyMerchants` WHERE `merchantId` =  ?1 ORDER BY `positionIndex`";
		
		String queryString = "SELECT `id`, `wineId`, `wineName`, `wineShortDescription`, `wineImageURL`, `wineMinimumPrice`"
						   + ", `winePreviousMaxPrice`, `wineMoneySaving`, `winePercentageOff`, `wineMinimumPriceClicktag` "
						   + "FROM `viewBestOffersbyMerchants` "
						   + "WHERE `merchantId` = ?1 "
						   + "ORDER BY `winePercentageOff` desc";
		
		Query query = em.createNativeQuery(queryString, viewBestOffersbyMerchants.class);
		query.setParameter(1, merchantId);
		
		@SuppressWarnings("unchecked")
		List<viewBestOffersbyMerchants> offers = query.getResultList();
		
		return offers;
	}
}