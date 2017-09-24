package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.TblPFCountryNameMappingTable;
import models.tblCountries;

/**
 * Session Bean implementation class TblPFCountryNameMappingTableService
 */
@Stateless
@LocalBean
public class TblPFCountryNameMappingTableService {

	@PersistenceContext(unitName="Winedunk")
	EntityManager em;

    /**
	 * 
	 * @param id
	 * @return
	 */
    public TblPFCountryNameMappingTable getById(Integer id)
    {
    	return em.find(TblPFCountryNameMappingTable.class, id);
    }

    /**
     * 
     * @return
     */
    public List<TblPFCountryNameMappingTable> getAll()
    {
    	try {
    		return em.createNamedQuery("TblPFCountryNameMappingTable.findAll", TblPFCountryNameMappingTable.class).getResultList();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    /**
     * 
     * @param country
     * @param name
     * @return
     */
    public TblPFCountryNameMappingTable getByCountryAndName(tblCountries country, String name)
    {
    	try {
    		return em.createNamedQuery("TblPFCountryNameMappingTable.findByCountryAndName", TblPFCountryNameMappingTable.class)
    				 .setParameter(0, country)
    				 .setParameter(1, name)
    				 .getSingleResult();
    	} catch(NoResultException noResExc) {
    	}catch (Exception e) {
    		e.printStackTrace();
    	}

		return null;
    }

    /**
     * 
     * @param merchantCountryName
     */
    public TblPFCountryNameMappingTable getByName(String merchantCountryName)
    {
    	try {
    		return em.createNamedQuery("TblPFCountryNameMappingTable.findByMerchantNameInsensitive", TblPFCountryNameMappingTable.class)
    				 .setParameter("name", merchantCountryName)
    				 .getSingleResult();
    	} catch(NoResultException noResExc) {
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

		return null;
    }

    /**
     * 
     * @param countryId
     * @param name
     * @return The ID of the just added mapping
     */
    public Integer addMapping(Integer countryId, String name)
    {
    	try {
    		tblCountries country = em.find(tblCountries.class, countryId);
    		TblPFCountryNameMappingTable mapping = new TblPFCountryNameMappingTable(country, name);
    		em.persist(mapping);
    		return mapping.getId();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    public boolean deleteMapping(Integer id)
    {
    	try{
    		em.remove(this.getById(id));
        	return true;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }

    public boolean deleteMappingById(Integer id)
    {
		TblPFCountryNameMappingTable mapping = this.getById(id);

		try{
    		if(mapping!=null)
    		{
    			em.remove(mapping);
    			return true;
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    	return false;
    }
}
