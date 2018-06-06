package services; 
import java.util.List; 
import javax.ejb.LocalBean; 
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager; 
import javax.persistence.NoResultException; 
import javax.persistence.PersistenceContext; 
import models.Tblpfproduct; 
/** 
 * Session Bean implementation class ProductsService 
 */ 
@Stateless 
@LocalBean 
public class ProductsService { 
	@PersistenceContext(unitName="Winedunk") 
	EntityManager em; 

	public List<Tblpfproduct> getAll() 
	{ 
		return em.createNamedQuery("Tblpfproduct.findAll", Tblpfproduct.class).getResultList(); 
	} 

	public Tblpfproduct findById(Integer id) 
	{ 
		return em.find(Tblpfproduct.class, id); 
	} 

	public List<Tblpfproduct> findByTblpf(Integer tblpfId) 
	{
		try { 
			return em.createNamedQuery("Tblpfproduct.findByTblpfId", Tblpfproduct.class).setParameter("id", tblpfId).getResultList(); 
		} catch(Exception e) { 
			e.printStackTrace(); 
			return null; 
		} 
	}

	public Tblpfproduct findByPartnerIdAndMerchantId(String partnerProductId, String merchantProductId) 
	{ 
		try { 
			return em.createNamedQuery("Tblpfproduct.findByPartnerIdAndMerchantId", Tblpfproduct.class) 
					 .setParameter("merchantProductId", merchantProductId) 
					 .setParameter("partnerProductId", partnerProductId) 
					 .getSingleResult(); 
		} catch (NoResultException noResExc) {  
			return new Tblpfproduct(); 
		} 
	} 

	public Tblpfproduct findByPartnerIdAndPartnerProductId(Integer partnerId, String partnerProductId) 
	{
		try { 
			return em.createNamedQuery("Tblpfproduct.findByPartnerIdAndPartnerProductId", Tblpfproduct.class) 
					 .setParameter("partnerId", partnerId) 
					 .setParameter("partnerProductId", partnerProductId) 
					 .getSingleResult(); 
		} catch (NoResultException noResExc) { 
			return new Tblpfproduct(); 
		} 
	} 

	public Integer addProduct(Tblpfproduct product) 
	{ 
		product.setId(null); 
		try { 
			
			// aripe 2018-04-12
	    	
	    	Integer maxColumnLength = product.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
	    	if (product.getName() != null && product.getName().length() > maxColumnLength) {
	    		product.setName( product.getName().substring(0, maxColumnLength - 3).concat("...") );
	    	}
	    	
	    	maxColumnLength = product.getClass().getDeclaredField("clicktag").getAnnotation(Column.class).length();
	    	if (product.getClicktag() != null && product.getClicktag().length() > maxColumnLength) {
	    		product.setClicktag( product.getClicktag().substring(0, maxColumnLength - 3).concat("...") );
	    	}

	    	maxColumnLength = product.getClass().getDeclaredField("productURL").getAnnotation(Column.class).length();
	    	if (product.getProductURL() != null && product.getProductURL().length() > maxColumnLength) {
	    		product.setProductURL( product.getProductURL().substring(0, maxColumnLength - 3).concat("...") );
	    	}

	    	maxColumnLength = product.getClass().getDeclaredField("partnerProductId").getAnnotation(Column.class).length();
	    	if (product.getPartnerProductId() != null && product.getPartnerProductId().length() > maxColumnLength) {
	    		product.setPartnerProductId( product.getPartnerProductId().substring(0, maxColumnLength - 3).concat("...") );
	    	}

	    	maxColumnLength = product.getClass().getDeclaredField("productType").getAnnotation(Column.class).length();
	    	if (product.getProductType() != null && product.getProductType().length() > maxColumnLength) {
	    		product.setProductType( product.getProductType().substring(0, maxColumnLength - 3).concat("...") );
	    	}

	    	maxColumnLength = product.getClass().getDeclaredField("partnerMerchantId").getAnnotation(Column.class).length();
	    	if (product.getPartnerMerchantId() != null && product.getPartnerMerchantId().length() > maxColumnLength) {
	    		product.setPartnerMerchantId( product.getPartnerMerchantId().substring(0, maxColumnLength - 3).concat("...") );
	    	}

	    	maxColumnLength = product.getClass().getDeclaredField("merchantName").getAnnotation(Column.class).length();
	    	if (product.getMerchantName() != null && product.getMerchantName().length() > maxColumnLength) {
	    		product.setMerchantName( product.getMerchantName().substring(0, maxColumnLength - 3).concat("...") );
	    	}

	    	maxColumnLength = product.getClass().getDeclaredField("merchantProductId").getAnnotation(Column.class).length();
	    	if (product.getMerchantProductId() != null && product.getMerchantProductId().length() > maxColumnLength) {
	    		product.setMerchantProductId( product.getMerchantProductId().substring(0, maxColumnLength - 3).concat("...") );
	    	}

	    	maxColumnLength = product.getClass().getDeclaredField("imageURL").getAnnotation(Column.class).length();
	    	if (product.getImageURL() != null && product.getImageURL().length() > maxColumnLength) {
	    		product.setImageURL( product.getImageURL().substring(0, maxColumnLength - 3).concat("...") );
	    	}
	    	
			em.persist(product); 
			em.flush(); 
			return product.getId(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return null; 
		} 
	} 

	public boolean updateProduct(Tblpfproduct product) 
	{ 
		if(product==null || product.getId()==null) 
			return false; 
		try { 
			em.merge(product); 
			return true; 
		} catch(Exception e) { 
			return false; 
		} 
	}

	public boolean deleteProduct(Tblpfproduct product) 
	{ 
		try { 
			em.remove(product); 
			return true; 
		} catch (Exception e) { 
			e.printStackTrace(); 
			return false; 
		} 
	} 

	public boolean deleteProduct(Integer id) 
	{ 
		return this.deleteProduct(this.findById(id)); 
	} 
} 