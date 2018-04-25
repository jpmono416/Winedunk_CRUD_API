package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import constants.PFLogCategoriesNamesConstants;
import models.TblPFLogCategories;


@Stateless
@LocalBean
public class PFLogCategoriesService {

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    public TblPFLogCategories getOneById(Integer id)
    {
    	if ( (id != null) && (id > 0) ) {
    	
    		TypedQuery<TblPFLogCategories> typedQuery = em.createNamedQuery("TblPFLogCategories.findOneById", TblPFLogCategories.class);
			typedQuery.setParameter("id", id);
			try {
				TblPFLogCategories singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
    	} else {
    		return null; 
    	}
    }

    public TblPFLogCategories getOneByName(String name)
    {
    	if ( (name != null) && (name != "") ) {
    	
    		TypedQuery<TblPFLogCategories> typedQuery = em.createNamedQuery("TblPFLogCategories.findOneByName", TblPFLogCategories.class);
			typedQuery.setParameter("name", name);
			try {
				TblPFLogCategories singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
    	} else {
    		return null; 
    	}
    }
    
    public TblPFLogCategories getLogcategoryPROCESSSTARTED() {	

		PFLogCategoriesNamesConstants pfLogCategoriesNamesConstants = new PFLogCategoriesNamesConstants();
    	return getOneByName(pfLogCategoriesNamesConstants.getLogcategoryPROCESSSTARTEDname());
    }
    
    public TblPFLogCategories getLogcategoryPRODUCTSTANDARDIZATIONSTARTED() {	

		PFLogCategoriesNamesConstants pfLogCategoriesNamesConstants = new PFLogCategoriesNamesConstants();
    	return getOneByName(pfLogCategoriesNamesConstants.getLogcategoryPRODUCTSTANDARDIZATIONSTARTEDname());
    }
    
    public TblPFLogCategories getLogcategoryPRODUCTSTANDARDIZATIONFINISHED() {	

		PFLogCategoriesNamesConstants pfLogCategoriesNamesConstants = new PFLogCategoriesNamesConstants();
    	return getOneByName(pfLogCategoriesNamesConstants.getLogcategoryPRODUCTSTANDARDIZATIONFINISHEDname());
    }
    
    public TblPFLogCategories getLogcategoryPRODUCTPROCESSINGSTARTED() {	

		PFLogCategoriesNamesConstants pfLogCategoriesNamesConstants = new PFLogCategoriesNamesConstants();
    	return getOneByName(pfLogCategoriesNamesConstants.getLogcategoryPRODUCTPROCESSINGSTARTEDname());
    }
    
    public TblPFLogCategories getLogcategoryPRODUCTPROCESSING() {	

		PFLogCategoriesNamesConstants pfLogCategoriesNamesConstants = new PFLogCategoriesNamesConstants();
    	return getOneByName(pfLogCategoriesNamesConstants.getLogcategoryPRODUCTPROCESSINGname());
    }
    
    public TblPFLogCategories getLogcategoryPRODUCTPROCESSINGFINISHED() {	

		PFLogCategoriesNamesConstants pfLogCategoriesNamesConstants = new PFLogCategoriesNamesConstants();
    	return getOneByName(pfLogCategoriesNamesConstants.getLogcategoryPRODUCTPROCESSINGFINISHEDname());
    }
    
    public TblPFLogCategories getLogcategoryPROCESSFINISHED() {	

		PFLogCategoriesNamesConstants pfLogCategoriesNamesConstants = new PFLogCategoriesNamesConstants();
    	return getOneByName(pfLogCategoriesNamesConstants.getLogcategoryPROCESSFINISHEDname());
    }

}
