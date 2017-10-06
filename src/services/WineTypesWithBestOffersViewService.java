package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewWineTypesWithBestOffers;

@Stateless
@LocalBean
public class WineTypesWithBestOffersViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewWineTypesWithBestOffers> getWineTypesWithBestOffers() 
    {
        Query query	= em.createQuery("SELECT b FROM viewWineTypesWithBestOffers b");
        try { return (List<viewWineTypesWithBestOffers>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewWineTypesWithBestOffers getWineTypesWithBestOfferBy(Integer id)
    {
    	try { return em.find(viewWineTypesWithBestOffers.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
}