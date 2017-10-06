package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Tblpf;

@Stateless
@LocalBean
public class ProductFeedsService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	public ProductFeedsService() {}

	public List<Tblpf> getAllProductFeeds()
	{
		try {
			return em.createNamedQuery("Tblpf.findAll", Tblpf.class)
					 .getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
