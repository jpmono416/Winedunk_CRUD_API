package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
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
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = shop.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (shop.getName() != null && shop.getName().length() > maxColumnLength) {
        		shop.setName( shop.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = shop.getClass().getDeclaredField("Logo").getAnnotation(Column.class).length();
        	if (shop.getLogo() != null && shop.getLogo().length() > maxColumnLength) {
        		shop.setLogo( shop.getLogo().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = shop.getClass().getDeclaredField("homePage").getAnnotation(Column.class).length();
        	if (shop.getHomePage() != null && shop.getHomePage().length() > maxColumnLength) {
        		shop.setHomePage( shop.getHomePage().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = shop.getClass().getDeclaredField("genericProductPage").getAnnotation(Column.class).length();
        	if (shop.getGenericProductPage() != null && shop.getGenericProductPage().length() > maxColumnLength) {
        		shop.setGenericProductPage( shop.getGenericProductPage().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
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