package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewBestOffersbyWineTypes;

@Stateless
@LocalBean
public class BestOffersByWineTypeViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewBestOffersbyWineTypes> getBestOffersByWineType() 
    {
        Query query	= em.createQuery("SELECT b FROM viewBestOffersbyWineTypes b");
        try { return (List<viewBestOffersbyWineTypes>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewBestOffersbyWineTypes getBestOfferByWineTypeById(Integer id)
    {
    	try { return em.find(viewBestOffersbyWineTypes.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
    
    @SuppressWarnings("unchecked")
	public List<viewBestOffersbyWineTypes> getBestOffersForWineType(Integer wineTypeId)
    {
    	Query query = em.createNativeQuery("SELECT * FROM viewBestOffersbyWineTypes WHERE wineTypeId = ?1");
    	query.setParameter(1, wineTypeId);
    	try { return (List<viewBestOffersbyWineTypes>) query.getResultList(); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
	
	public List<viewBestOffersbyWineTypes> getSimplifiedBestOffersForWineType(Integer wineTypeId)
	{
		// aripe
		// String queryString = "SELECT `id`, `wineId`, `wineName`, `wineShortDescription`, `wineImageURL` FROM `viewBestOffersbyWineTypes` WHERE `wineTypeId` =  ?1 ORDER BY `positionIndex`";
		
		String queryString = "SELECT `id`, `wineId`, `wineName`, `wineShortDescription`, `wineImageURL`, `wineMinimumPrice`"
				   + ", `winePreviousMaxPrice`, `wineMoneySaving`, `winePercentageOff`, `wineMinimumPriceClicktag` "
				   + "FROM `viewBestOffersbyWineTypes` "
				   + "WHERE `wineTypeId` = ?1 "
				   + "ORDER BY `winePercentageOff` desc";
		
		Query query = em.createNativeQuery(queryString, viewBestOffersbyWineTypes.class);
		query.setParameter(1, wineTypeId);
		
		@SuppressWarnings("unchecked")
		List<viewBestOffersbyWineTypes> offers = query.getResultList();
		
		return offers;
	}
}