package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.TblWinesWineType;
import models.tblWineTypes;
import models.tblWines;



/**
 * Session Bean implementation class TblWinesWineTypeService
 */
@Stateless
@LocalBean
public class TblWinesWineTypesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	public List<TblWinesWineType> getTblWinesWineTypes() 
	
    {
    	try {
    		return em.createNamedQuery("TblWinesWineType.findAll", TblWinesWineType.class).getResultList();
        }
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

	/**
	 * 
	 * @param id
	 * @return
	 */
    public TblWinesWineType getTblWinesWineTypeById(Integer id)
    {
		try {
			return em.find(TblWinesWineType.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }

    /**
     * 
     * @param wine
     * @return
     */
    public TblWinesWineType getTblWinesWineTypeByWine(tblWines wine)
    {
    	try {
    		return em.createNamedQuery("TblWinesWineType.findByWine", TblWinesWineType.class)
    				 .setParameter(0, wine)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 
     * @param wineType
     * @return
     */
    public TblWinesWineType getTblWinesWineTypeByWineType(tblWineTypes wineType)
    {
    	try {
    		return em.createNamedQuery("TblWinesWineType.findByWineType", TblWinesWineType.class)
    				 .setParameter(0, wineType)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 
     * @param wine
     * @return
     */
    public TblWinesWineType getTblWinesWineTypeByWineAndWineType(tblWines wine, tblWineTypes wineType)
    {
    	try {
    		return em.createNamedQuery("TblWinesWineType.findByWineAndWineType", TblWinesWineType.class)
    				 .setParameter(0, wine).
    				 setParameter(1, wineType)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 
     * @param wine
     * @return
     */
    public TblWinesWineType getTblWinesWineTypeByWineId(Integer id)
    {
    	try {
    		return em.createNamedQuery("TblWinesWineType.findByWineId", TblWinesWineType.class)
    				 .setParameter(0, id)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 
     * @param id
     * @return
     */
    public TblWinesWineType getTblWinesWineTypeByWineTypeId(Integer id)
    {
    	try {
    		return em.createNamedQuery("TblWinesWineType.findByWineTypeId", TblWinesWineType.class)
    				 .setParameter(0, id)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 
     * @param wineId
     * @param wineTypeId
     * @return
     */
    public TblWinesWineType getTblWinesWineTypeByWineIdAndWineTypeId(Integer wineId, Integer wineTypeId)
    {
    	try {
    		return em.createNamedQuery("TblWinesWineType.findByWineIdAndWineTypeId", TblWinesWineType.class)
    				 .setParameter(0, wineId)
    				 .setParameter(1, wineTypeId)
    				 .getSingleResult();
    	} catch (NoResultException noResExc) {	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 
     * @param apellation
     * @return
     */
    public Integer addTblWinesWineType(TblWinesWineType apellation) {
        try
        {
        	if(apellation.getId() != null) { apellation.setId(null); }
        	em.persist(apellation);
        	return apellation.getId();
        } catch (Exception e) { return null; }
    }

    /**
     * 
     * @param apellation
     * @return
     */
    public Boolean updateTblWinesWineType(TblWinesWineType apellation)
    {
    	if(apellation == null || apellation.getId() == null) { return false; }
        em.merge(apellation);
        return true;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Boolean deleteTblWinesWineType(Integer id)
    {
        TblWinesWineType apellation = getTblWinesWineTypeById(id);
        if(apellation != null)
        {
        	em.remove(apellation);
            return true;
        }
        return false;
    }
}