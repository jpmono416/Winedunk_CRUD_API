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

	public List<Tblpfproduct> getByTblpf(Integer tblpfId)
	{
		try {
			Tblpf tblpf = em.find(Tblpf.class, tblpfId);
			return em.createNamedQuery("Tblpfproduct.findByTblpf", Tblpfproduct.class).setParameter(0, tblpf).getResultList();
		} catch(NoResultException noResExc) {
			return null;
		}
	}
	
	public Tblpfproduct getByPartnerIdAndMerchantId(Integer partnerProductId, Integer merchantProductId)
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
}
