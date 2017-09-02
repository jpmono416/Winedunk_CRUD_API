package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Tblpf;

public class ProductFeedsService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;
	
	public List<Tblpf> getProductFeeds()
	{
		try {
			return em.createNamedQuery("Tblpf.findAll", Tblpf.class).getResultList();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
