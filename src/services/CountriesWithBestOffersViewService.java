package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewCountriesWithBestOffers;

@Stateless
@LocalBean
public class CountriesWithBestOffersViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewCountriesWithBestOffers> getCountriesWithBestOffers() 
    {
        Query query	= em.createQuery("SELECT b FROM viewCountriesWithBestOffers b");
        try { return (List<viewCountriesWithBestOffers>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewCountriesWithBestOffers getCountriesWithBestOfferBy(Integer id)
    {
    	try { return em.find(viewCountriesWithBestOffers.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
}