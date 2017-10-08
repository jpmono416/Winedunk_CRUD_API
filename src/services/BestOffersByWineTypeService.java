package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblBestOffersbyType;

@Stateless
@LocalBean
public class BestOffersByWineTypeService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblBestOffersbyType> getBestOffersByWineType() 
    {
        Query query	= em.createQuery("SELECT p FROM tblBestOffersbyWineType p");
        try { return (List<tblBestOffersbyType>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblBestOffersbyType getBestOfferByWineTypeById(Integer id)
    {
    	try { return em.find(tblBestOffersbyType.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addBestOfferByWineType(tblBestOffersbyType bestOfferByWineType) {
        try
        {
        	if(bestOfferByWineType.getId() != null) { bestOfferByWineType.setId(null); }
        	em.persist(bestOfferByWineType);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateBestOfferByWineType(tblBestOffersbyType bestOfferByWineType)
    {
    	if(bestOfferByWineType == null || bestOfferByWineType.getId() == null) { return false; }
        em.merge(bestOfferByWineType);
        return true;
    }

    public Boolean deleteBestOfferByWineType(Integer id)
    {
        tblBestOffersbyType bestOfferByWineType = getBestOfferByWineTypeById(id);
        if(bestOfferByWineType != null)
        {
            em.remove(bestOfferByWineType);
            return true;
        }
        return false;
    }
}