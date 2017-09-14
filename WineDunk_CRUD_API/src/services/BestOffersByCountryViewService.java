package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewBestOffersbyCountries;

@Stateless
@LocalBean
public class BestOffersByCountryViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewBestOffersbyCountries> getBestOffersByCountry() 
    {
        Query query	= em.createQuery("SELECT b FROM viewBestOffersbyCountry b");
        try { return (List<viewBestOffersbyCountries>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewBestOffersbyCountries getBestOfferByCountryById(Integer id)
    {
    	try { return em.find(viewBestOffersbyCountries.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    @SuppressWarnings("unchecked")
	public List<viewBestOffersbyCountries> getBestOffersForCountry(Integer merchantId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewBestOffersbyCountry WHERE countryId = ?1");
    	query.setParameter(1, merchantId);
    	try { return (List<viewBestOffersbyCountries>) query.getResultList(); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
	
	public List<viewBestOffersbyCountries> getSimplifiedBestOffersForCountry(Integer merchantId)
	{
		String queryString = "SELECT `id`, `wineId`, `wineName`, `wineShortDescription`, `wineImageURL` FROM `viewBestOffersbyCountry` WHERE `countryId` =  ?1 ORDER BY `positionIndex`";
		Query query = em.createNativeQuery(queryString, viewBestOffersbyCountries.class);
		query.setParameter(1, merchantId);
		
		@SuppressWarnings("unchecked")
		List<viewBestOffersbyCountries> offers = query.getResultList();
		
		return offers;
	}
}