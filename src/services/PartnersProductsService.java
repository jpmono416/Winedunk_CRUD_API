package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblPartnersProducts;
import models.tblUsers;

@Stateless
@LocalBean
public class PartnersProductsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblPartnersProducts> getPartnersProducts() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblPartnersProducts p");
        try { return (List<tblPartnersProducts>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblPartnersProducts getPartnersProductById(Integer id)
    {
    	try { return em.find(tblPartnersProducts.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Integer addPartnersProduct(tblPartnersProducts product) {
        try
        {
        	if(product.getId() != null) { product.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = product.getClass().getDeclaredField("partnerProductId").getAnnotation(Column.class).length();
        	if (product.getPartnerProductId() != null && product.getPartnerProductId().length() > maxColumnLength) {
        		product.setPartnerProductId( product.getPartnerProductId().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = product.getClass().getDeclaredField("partnerDestinationURL").getAnnotation(Column.class).length();
        	if (product.getPartnerDestinationUrl() != null && product.getPartnerDestinationUrl().length() > maxColumnLength) {
        		product.setPartnerDestinationUrl( product.getPartnerDestinationUrl().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = product.getClass().getDeclaredField("partnerMerchantId").getAnnotation(Column.class).length();
        	if (product.getPartnerMerchantId() != null && product.getPartnerMerchantId().length() > maxColumnLength) {
        		product.setPartnerMerchantId( product.getPartnerMerchantId().substring(0, maxColumnLength - 3).concat("...") );
        	}

        	
        	maxColumnLength = product.getClass().getDeclaredField("partnerMerchantProductId").getAnnotation(Column.class).length();
        	if (product.getPartnerMerchantProductId() != null && product.getPartnerMerchantProductId().length() > maxColumnLength) {
        		product.setPartnerMerchantProductId( product.getPartnerMerchantProductId().substring(0, maxColumnLength - 3).concat("...") );
        	}

        	
        	maxColumnLength = product.getClass().getDeclaredField("lastMD5").getAnnotation(Column.class).length();
        	if (product.getLastMD5() != null && product.getLastMD5().length() > maxColumnLength) {
        		product.setLastMD5( product.getLastMD5().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(product);
        	em.flush();
        	return product.getId();
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean updatePartnersProduct(Integer id, Float price)
    {
    	if(id == null || price == null)
    		return false;

    	tblPartnersProducts partnerProduct = em.find(tblPartnersProducts.class, id);
        partnerProduct.setPartnerProductPrice(price);

        return true;
    }

    public Boolean updatePartnersProductsPrice(tblPartnersProducts newPartnersProducts)
    {
    	// aripe 2018-04-03
    	
    	if(newPartnersProducts == null || newPartnersProducts.getId() == null || newPartnersProducts.getPartnerProductPrice() == null) { return false; }
        
    	tblPartnersProducts partnersProducts = getPartnersProductById(newPartnersProducts.getId());
        if(partnersProducts != null)
        {
        	partnersProducts.setPartnerProductPrice(newPartnersProducts.getPartnerProductPrice());
        	em.merge(partnersProducts);
            em.getEntityManagerFactory().getCache().evictAll();
            return true;
        }
    	return false;
    }

    public Boolean deletePartnersProduct(Integer id)
    {
        tblPartnersProducts device = getPartnersProductById(id);
        if(device != null)
        {
            device.setDeleted(true);
            em.merge(device);
            return true;
        }
        return false;
    }
    
    public tblPartnersProducts getByPartnerProductIdAndMerchantProductId(String partnerProductId, String merchantProductId)
    {
    	try {
    		return em.createNamedQuery("tblPartnersProducts.findByPartProdIdAndMercProdId", tblPartnersProducts.class)
    				 .setParameter("ppId", partnerProductId)
    				 .setParameter("mpId", merchantProductId)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return new tblPartnersProducts();
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    

	// aripe 2018-03-31
    public tblPartnersProducts getByPartnerIdAndPartnerProductId(String partnerId, String partnerProductId) {
		try {
			int partnerIdInt = Integer.parseInt(partnerId);
    		return em.createNamedQuery("tblPartnersProducts.findByPartnerIdAndPartnerProductId", tblPartnersProducts.class)
    				 .setParameter("partnerId", partnerIdInt)
    				 .setParameter("partnerProductId", partnerProductId)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return new tblPartnersProducts();
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}	
    }
}