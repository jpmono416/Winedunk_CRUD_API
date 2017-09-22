package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblWines;
@Stateless
@LocalBean
public class WinesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblWines> getWines()
    {
        Query query	= em.createQuery("SELECT b FROM tblWines b");
        try { return (List<tblWines>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblWines getWineById(Integer id)
    {
    	try { return em.find(tblWines.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addWine(tblWines wine) {
        try
        {
        	if(wine.getId() != null) { wine.setId(null); }
        	em.persist(wine);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateWine(tblWines wine)
    {
    	if(wine == null || wine.getId() == null) { return false; }
        em.merge(wine);
        return true;
    }

    public Boolean deleteWine(Integer id)
    {
        tblWines wine = getWineById(id);
        if(wine != null)
        {
            wine.setDeleted(true);
            em.merge(wine);
            return true;
        }
        return false;
    }
}