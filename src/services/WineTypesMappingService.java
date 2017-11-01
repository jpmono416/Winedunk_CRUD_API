package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
			TypedQuery<TblPFWineTypeMappingTable> query = em.createNamedQuery("TblPFWineTypeMappingTable.findByWineType", TblPFWineTypeMappingTable.class);
			 query.setParameter("wineType", typeName);

			 return query.getSingleResult();
		} catch (Exception e) {
			if(e.getClass()!=NoResultException.class)
				e.printStackTrace();
			return null;
		}
	}
}
