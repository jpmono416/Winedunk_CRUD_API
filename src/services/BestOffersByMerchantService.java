package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblBestOffersbyMerchants;

@Stateless
@LocalBean
public class BestOffersByMerchantService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblBestOffersbyMerchants> getBestOffersByMerchant() 
    {
        Query query	= em.createQuery("SELECT p FROM tblBestOffersbyMerchants p");
        try { return (List<tblBestOffersbyMerchants>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblBestOffersbyMerchants getBestOfferByMerchantById(Integer id)
    {
    	try { return em.find(tblBestOffersbyMerchants.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addBestOfferByMerchant(tblBestOffersbyMerchants bestOfferByMerchant) {
        try
        {
        	if(bestOfferByMerchant.getId() != null) { bestOfferByMerchant.setId(null); }
        	em.persist(bestOfferByMerchant);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateBestOfferByMerchant(tblBestOffersbyMerchants bestOfferByMerchant)
    {
    	if(bestOfferByMerchant == null || bestOfferByMerchant.getId() == null) { return false; }
        em.merge(bestOfferByMerchant);
        return true;
    }

    public Boolean deleteBestOfferByMerchant(Integer id)
    {
        tblBestOffersbyMerchants bestOfferByMerchant = getBestOfferByMerchantById(id);
        if(bestOfferByMerchant != null)
        {
            em.remove(bestOfferByMerchant);
            return true;
        }
        return false;
    }
}