package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.TblWinesGrapeVariety;
import models.tblGrapeVarieties;
import models.tblWines;

/**
 * Session Bean implementation class WinesGrapeVarietiesService
 */
@Stateless
@LocalBean
public class WinesGrapeVarietiesService {

	@PersistenceContext(unitName="Winedunk")
	EntityManager em;

	public List<TblWinesGrapeVariety> getAll()
	{
		try {
			return em.createNamedQuery("tblWinesGrapeVarieties.findAll", TblWinesGrapeVariety.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	public TblWinesGrapeVariety getByWineAndGrape(tblWines wine, tblGrapeVarieties grape)
	{
		try {
			return em.createNamedQuery("tblWinesGrapeVarieties.getByWineAndGrape", TblWinesGrapeVariety.class)
					 .setParameter("wine", wine)
					 .setParameter("grape", grape)
					 .getSingleResult();
		} catch(Exception e) {
			if(e.getClass() != NoResultException.class)
				e.printStackTrace();
			return new TblWinesGrapeVariety();
		}
	}

	public Integer addNew(TblWinesGrapeVariety winesGrapeVariety)
	{
		try {
			if(winesGrapeVariety.getId()!=null)
				winesGrapeVariety.setId(null);
			
			em.persist(winesGrapeVariety);
			em.flush();

			return winesGrapeVariety.getId();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean remove(TblWinesGrapeVariety winesGrapeVariety)
	{
		try {
			em.remove(winesGrapeVariety);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
