package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblPartnersProducts;

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
        	em.persist(product);
        	em.flush();
        	return product.getId();
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean updatePartnersProduct(tblPartnersProducts device)
    {
    	if(device == null || device.getId() == null) { return false; }
        em.merge(device);
        return true;
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
    
    public tblPartnersProducts getByPartnerProductIdAndMerchantProductId(Integer partnerProductId, Integer merchantProductId)
    {
    	try {
    		return em.createNamedQuery("tblPartnersProducts.findByPartProdIdAndMercProdId", tblPartnersProducts.class)
    				 .setParameter(0, partnerProductId)
    				 .setParameter(1, merchantProductId)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }
}