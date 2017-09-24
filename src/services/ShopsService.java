package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblShops;

@Stateless
@LocalBean
public class ShopsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblShops> getShops() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblShops p");
        try { return (List<tblShops>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblShops getShopById(Integer id)
    {
    	try { return em.find(tblShops.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblShops getByName(String name)
    {
		try {
			return em.createNamedQuery("tblShops.findByName", tblShops.class)
					 .setParameter("name", name)
					 .getSingleResult();
		} catch (Exception e) {
			if(!e.getClass().equals(NoResultException.class))
				e.printStackTrace();
			return null;
		}
    }

    public Boolean addShop(tblShops shop) {
        try
        {
        	if(shop.getId() != null) { shop.setId(null); }
        	em.persist(shop);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateShop(tblShops shop)
    {
    	if(shop == null || shop.getId() == null) { return false; }
        em.merge(shop);
        return true;
    }

    public Boolean deleteShop(Integer id)
    {
        tblShops shop = getShopById(id);
        if(shop != null)
        {
            shop.setDeleted(true);
            em.merge(shop);
            return true;
        }
        return false;
    }
}