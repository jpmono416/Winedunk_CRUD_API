package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.Tblpf;
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
			Tblpf tblpf = em.find(Tblpf.class, tblpfId);
			return em.createNamedQuery("Tblpfproduct.findByTblpf", Tblpfproduct.class).setParameter(0, tblpf).getResultList();
		} catch(NoResultException noResExc) {
			return null;
		}
	}
	
	public Tblpfproduct findByPartnerIdAndMerchantId(Integer partnerProductId, Integer merchantProductId)
	{
		try {
			return em.createNamedQuery("Tblpfproduct.findByPartnerIdAndMerchantId", Tblpfproduct.class)
					 .setParameter(0, partnerProductId)
					 .setParameter(1, merchantProductId)
					 .getSingleResult();
		} catch (NoResultException noResExc) {
			return null;
		}
	}

	public Integer addProduct(Tblpfproduct product)
	{
		product.setId(null);

		try {
			em.persist(product);
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
		try {
			em.remove(this.findById(id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
