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

	public Tblpf getById(Integer id)
	{
		try {
			return em.find(Tblpf.class, id);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean failStandardisation(Integer id)
	{
		return this.setStandardisationStatus("Errors", id);
	}

	public boolean okStandardisation(Integer id)
	{
		return this.setStandardisationStatus("Ok", id);
	}
	
	public boolean processingStandardisation(Integer id)
	{
		return this.setStandardisationStatus("Processing", id);
	}

	private boolean setStandardisationStatus(String statusName, Integer id)
	{
		try {
			Tblpfstatus status = em.createNamedQuery("Tblpfstatus.findByName", Tblpfstatus.class).setParameter("name", statusName).getSingleResult();
			em.find(Tblpf.class, id).setStandardisationStatus(status);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean failImportation(Integer id)
	{
		return this.setImportationStatus("Errors", id);
	}

	public boolean okImportation(Integer id)
	{
		return this.setImportationStatus("Ok", id);
	}
	
	public boolean processingImportation(Integer id)
	{
		return this.setImportationStatus("Processing", id);
	}

	private boolean setImportationStatus(String statusName, Integer id)
	{
		try {
			Tblpfstatus status = em.createNamedQuery("Tblpfstatus.findByName", Tblpfstatus.class).setParameter("name", statusName).getSingleResult();
			em.find(Tblpf.class, id).setImportationStatus(status);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean update(Tblpf pf) {
		if(pf.getId()==null || pf.getId()<1)
			return false;

		try {
			em.merge(pf);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
}
