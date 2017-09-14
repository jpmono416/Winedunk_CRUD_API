package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewMerchantsWithBestOffers;

@Stateless
@LocalBean
public class MerchantsWithBestOffersViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewMerchantsWithBestOffers> getMerchantsWithBestOffers() 
    {
        Query query	= em.createQuery("SELECT b FROM viewMerchantsWithBestOffers b");
        try { return (List<viewMerchantsWithBestOffers>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewMerchantsWithBestOffers getMerchantsWithBestOfferBy(Integer id)
    {
    	try { return em.find(viewMerchantsWithBestOffers.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
}