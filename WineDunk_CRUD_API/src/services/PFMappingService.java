package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import models.Tblpfmapping;

/**
 * Session Bean implementation class PFMappingService
 */
@Stateless
@LocalBean
public class PFMappingService {

    @PersistenceContext(unitName = "Winedunk")
    EntityManager em;

    public PFMappingService() {}

    /**
     * 
     * @param id
     * @return
     */
    public Tblpfmapping findByPFId(Integer id)
    {
    	try {
	    	return em.createNamedQuery("Tblpfmapping.findByPFId", Tblpfmapping.class)
	    			 .setParameter("tblpf", id)
	    			 .getSingleResult();
    	} catch(NoResultException noResExc) {
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}

    	return null;
    }

    /**
     * 
     * @return
     */
    public List<Tblpfmapping> getAll()
    {
    	try {
	    	return em.createNamedQuery("Tblpfmapping.findAll", Tblpfmapping.class)
	    			 .getResultList();
    	} catch(NoResultException noResExc) {
    		
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }

    /**
     * 
     * @param mapping
     * @return
     */
    public boolean addPFMapping(Tblpfmapping mapping)
    {
    	try {
    		em.persist(mapping);
    		return true;
    	} catch (Exception e) {
    		return false;
    	}
    }

    /**
     * 
     * @param mapping
     * @return
     */
    public boolean deletePFMapping(Tblpfmapping mapping)
    {
    	try {
    		em.remove(mapping);
    		return true;
    	} catch (Exception e) {
    		return false;
    	}
    }
}
