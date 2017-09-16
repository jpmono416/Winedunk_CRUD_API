package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
		return em.createNamedQuery("Tblpproduct.findAll", Tblpfproduct.class).getResultList();
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
			System.out.println("Couldn't find already existing product");
			return new Tblpfproduct();
		}
	}

	public Integer addProduct(Tblpfproduct product)
	{
		product.setId(null);

		try {
			em.persist(product);
			em.flush();
			System.out.println("ID: "+product.getId());
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
