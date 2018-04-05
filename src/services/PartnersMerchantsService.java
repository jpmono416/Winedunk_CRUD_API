// aripe 2018-04-05

package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import models.tblPartnersMerchants;



/**
 * Session Bean implementation class PartnersMerchantsService
 */
@Stateless
@LocalBean
public class PartnersMerchantsService {

    /**
     * Default constructor. 
     */
    public PartnersMerchantsService() {
        // TODO Auto-generated constructor stub
    }

	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

	
	public List<tblPartnersMerchants> getPartnersMerchants() {

		TypedQuery<tblPartnersMerchants> typedQuery = em.createNamedQuery("tblPartnersMerchants.findAll", tblPartnersMerchants.class);
		try {
			List<tblPartnersMerchants> resultList = typedQuery.getResultList();
			return resultList;
		} catch (NoResultException nre) {
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	public tblPartnersMerchants getPartnersMerchantsById(Integer id) {

		if (id > 0) {
			TypedQuery<tblPartnersMerchants> typedQuery = em.createNamedQuery("tblPartnersMerchants.findOneById", tblPartnersMerchants.class);
			typedQuery.setParameter("id", id);
			try {
				tblPartnersMerchants singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (NoResultException nre) {
				return null;
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		
	}
	
	
	public tblPartnersMerchants getPartnersMerchantsBypartnerMerchantName(String partnerMerchantName) {

		if ((partnerMerchantName != null) && (partnerMerchantName != "")) {
			TypedQuery<tblPartnersMerchants> typedQuery = em.createNamedQuery("tblPartnersMerchants.findOneBypartnerMerchantName", tblPartnersMerchants.class);
			typedQuery.setParameter("partnerMerchantName", partnerMerchantName);
			try {
				tblPartnersMerchants singleResult = typedQuery.getSingleResult();
				return singleResult;
			} catch (NoResultException nre) {
				return null;
			}
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}
	
	
}
