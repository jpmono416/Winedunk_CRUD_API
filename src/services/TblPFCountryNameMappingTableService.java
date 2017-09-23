package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
	private EntityManager em;

	@EJB
	private CountriesService countriesService;

	/**
     * Default constructor. 
     */
    public TblPFCountryNameMappingTableService() {}

    public List<TblPFCountryNameMappingTable> getAll()
    {
    	try {
    		return em.createNamedQuery("TblPFCountryNameMappingTable.findAll", TblPFCountryNameMappingTable.class).getResultList();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    public TblPFCountryNameMappingTable getById(Integer id)
    {
    	try {
    		return em.find(TblPFCountryNameMappingTable.class, id);
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    public TblPFCountryNameMappingTable getByMerchantName(String name)
    {
    	try {
    		return em.createNamedQuery("TblPFCountryNameMappingTable.findByMerchantName", TblPFCountryNameMappingTable.class)
    				 .setParameter("name", name)
    				 .getSingleResult();
    	} catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    public Integer addMapping(Integer countryId, String merchantName)
    {
    	try {
    		tblCountries country = countriesService.getCountryById(countryId);
    		TblPFCountryNameMappingTable mapping = new TblPFCountryNameMappingTable(country, merchantName);
    		em.persist(mapping);
    		em.flush();
    		return mapping.getId();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    public Boolean deleteMapping(Integer id)
    {
    	try {
    		em.remove(this.getById(id));
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    		return false;
    	}
    }
}
