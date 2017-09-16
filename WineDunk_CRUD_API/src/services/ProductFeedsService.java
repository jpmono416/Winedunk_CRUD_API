package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Tblpf;
import models.Tblpfstatus;

@Stateless
@LocalBean
public class ProductFeedsService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	/**
	 * 
	 * @return
	 */
	public List<Tblpf> getAll()
	{
		try {
			return em.createNamedQuery("Tblpf.findAll", Tblpf.class).getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param newProductFeed
	 * @return
	 */
	public Integer persist(Tblpf newProductFeed)
	{
		try {
			em.persist(newProductFeed);
			em.flush();
			return newProductFeed.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Tblpf getById(Integer id)
	{
		return id == null ? null : em.find(Tblpf.class, id);
	}

	/**
	 * 
	 * @param productFeedId
	 * @return
	 */
	public boolean delete(Integer productFeedId)
	{
		try {
			em.remove(productFeedId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param productFeed
	 * @return
	 */
	public boolean update(Tblpf productFeed)
	{
		if(productFeed==null || productFeed.getId()==null)
			return false;

		try {
			em.merge(productFeed);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Tblpf setStatusFailed(Integer id)
	{
		Tblpf productFeed = em.find(Tblpf.class, id);
		Tblpfstatus status = em.createNamedQuery("Tblpfstatus.findByName", Tblpfstatus.class).setParameter("name", "Error").getSingleResult();

		productFeed.setStandardisationStatus(status);
		return productFeed;
	}
}