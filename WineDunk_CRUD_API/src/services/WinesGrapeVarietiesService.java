package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.TblWinesGrapeVariety;
import models.tblGrapeVarieties;

@Stateless
@LocalBean
public class WinesGrapeVarietiesService {

	@PersistenceContext
	EntityManager em;

	public List<TblWinesGrapeVariety> getAll() {
		try {
			return em.createNamedQuery("TblWinesGrapeVariety.findAll", TblWinesGrapeVariety.class).getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public TblWinesGrapeVariety getById(Integer id) {
		try {
			return em.find(TblWinesGrapeVariety.class, id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Integer addNew(tblGrapeVarieties wineGrapeVariety) {
		wineGrapeVariety.setId(null);
		try {
			em.persist(wineGrapeVariety);
			em.flush();
			return wineGrapeVariety.getId();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(tblGrapeVarieties wineGrapeVariety) {
		if(wineGrapeVariety.getId()==null)
			return false;

		try {
			em.persist(wineGrapeVariety);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int asInt) {
		try {
			em.remove(this.getById(asInt));
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
