package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import constants.PFLogProcessesNamesConstants;
import models.TblPFLogProcesses;


@Stateless
@LocalBean
public class PFLogProcessesService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    public TblPFLogProcesses getOneById(Integer id) {
    	if ( (id != null) && (id > 0) ) {
    	
    		TypedQuery<TblPFLogProcesses> typedQuery = em.createNamedQuery("TblPFLogProcesses.findOneById", TblPFLogProcesses.class);
			typedQuery.setParameter("id", id);
			try {
				TblPFLogProcesses singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
    	} else {
    		return null; 
    	}
    }

    public TblPFLogProcesses getOneByName(String name) {
    	if ( (name != null) && (name != "") ) {
    	
    		TypedQuery<TblPFLogProcesses> typedQuery = em.createNamedQuery("TblPFLogProcesses.findOneByName", TblPFLogProcesses.class);
			typedQuery.setParameter("name", name);
			try {
				TblPFLogProcesses singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
    	} else {
    		return null; 
    	}
    }
    
    public TblPFLogProcesses getLogProcessProductFeedsPocessor() {	

    	PFLogProcessesNamesConstants pfLogProcessesNamesConstants = new PFLogProcessesNamesConstants();
    	return getOneByName(pfLogProcessesNamesConstants.getLogProductFeedProcessName());
    }
    
    public TblPFLogProcesses getLogProcessProductsProcessor() {	

    	PFLogProcessesNamesConstants pfLogProcessesNamesConstants = new PFLogProcessesNamesConstants();
    	return getOneByName(pfLogProcessesNamesConstants.getLogProductsProcessName());
    }

}
