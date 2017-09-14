package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblBestOffersbyCountries;

@Stateless
@LocalBean
public class BestOffersByCountryService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblBestOffersbyCountries> getBestOffersByCountry() 
    {
        Query query	= em.createQuery("SELECT p FROM tblBestOffersbyCountry p");
        try { return (List<tblBestOffersbyCountries>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblBestOffersbyCountries getBestOfferByCountryById(Integer id)
    {
    	try { return em.find(tblBestOffersbyCountries.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addBestOfferByCountry(tblBestOffersbyCountries bestOfferByCountry) {
        try
        {
        	if(bestOfferByCountry.getId() != null) { bestOfferByCountry.setId(null); }
        	em.persist(bestOfferByCountry);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateBestOfferByCountry(tblBestOffersbyCountries bestOfferByCountry)
    {
    	if(bestOfferByCountry == null || bestOfferByCountry.getId() == null) { return false; }
        em.merge(bestOfferByCountry);
        return true;
    }

    public Boolean deleteBestOfferByCountry(Integer id)
    {
        tblBestOffersbyCountries bestOfferByCountry = getBestOfferByCountryById(id);
        if(bestOfferByCountry != null)
        {
            em.remove(bestOfferByCountry);
            return true;
        }
        return false;
    }
}