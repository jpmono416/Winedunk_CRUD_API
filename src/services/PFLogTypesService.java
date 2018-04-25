package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import constants.PFLogTypesNamesConstants;
import models.TblPFLogTypes;


@Stateless
@LocalBean
public class PFLogTypesService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    public TblPFLogTypes getOneById(Integer id)
    {
    	if ( (id != null) && (id > 0) ) {
    	
    		TypedQuery<TblPFLogTypes> typedQuery = em.createNamedQuery("TblPFLogTypes.findOneById", TblPFLogTypes.class);
			typedQuery.setParameter("id", id);
			try {
				TblPFLogTypes singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
    	} else {
    		return null; 
    	}
    }

    public TblPFLogTypes getOneByName(String name)
    {
    	if ( (name != null) && (name != "") ) {
    	
    		TypedQuery<TblPFLogTypes> typedQuery = em.createNamedQuery("TblPFLogTypes.findOneByName", TblPFLogTypes.class);
			typedQuery.setParameter("name", name);
			try {
				TblPFLogTypes singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
    	} else {
    		return null; 
    	}
    }
    
    public TblPFLogTypes getLogTypeError() {	

		PFLogTypesNamesConstants pfLogTypesNamesConstants = new PFLogTypesNamesConstants();
    	return getOneByName(pfLogTypesNamesConstants.getLogtypeERRORname());
    }
    
    public TblPFLogTypes getLogTypeWarning() {	

		PFLogTypesNamesConstants pfLogTypesNamesConstants = new PFLogTypesNamesConstants();
    	return getOneByName(pfLogTypesNamesConstants.getLogtypeWARNINGname());
    }
    
    public TblPFLogTypes getLogTypeInformation() {	

		PFLogTypesNamesConstants pfLogTypesNamesConstants = new PFLogTypesNamesConstants();
    	return getOneByName(pfLogTypesNamesConstants.getLogtypeINFORMATIONname());
    }

}
