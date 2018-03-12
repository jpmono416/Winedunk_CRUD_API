package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.TblPFWineTypeMappingTable;

/**
 * Session Bean implementation class WineTypesMappingService
 */
@Stateless
@LocalBean
public class WineTypesMappingService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	public TblPFWineTypeMappingTable getByWineType(String typeName)
	{
		try {
			return em.createNamedQuery("TblPFWineTypeMappingTable.findByWineType", TblPFWineTypeMappingTable.class)
					 .setParameter("wineType", typeName)
					 .getSingleResult();
		} catch (Exception e) {
			if(e.getClass()!=NoResultException.class)
				e.printStackTrace();
			return new TblPFWineTypeMappingTable();
		}
	}
}
