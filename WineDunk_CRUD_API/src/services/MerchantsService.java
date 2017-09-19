package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.tblShops;

/**
 * Session Bean implementation class MerchantsService
 */
@Stateless
@LocalBean
public class MerchantsService {

	@PersistenceContext(unitName="Winedunk")
	EntityManager em;

	public tblShops getById(Integer id)
	{
		try {
			return em.find(tblShops.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<tblShops> getAll()
	{
		try {
			return em.createNamedQuery("tblShops.findAll", tblShops.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public tblShops getByName(String name)
	{
		try {
			return em.createNamedQuery("tblShops.findByName", tblShops.class)
					 .setParameter("name", name)
					 .getSingleResult();
		} catch (NoResultException noResExc) {
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Integer addMerchant(tblShops merchant)
	{
		try {
			em.persist(merchant);
			return merchant.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean removeMerchant(Integer merchantId)
	{
		try {
			this.getById(merchantId).setDeleted(true);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
