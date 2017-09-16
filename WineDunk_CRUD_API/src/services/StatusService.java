package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import models.Tblpfstatus;

/**
 * Session Bean implementation class StatusService
 */
@Stateless
@LocalBean
public class StatusService {

	@PersistenceContext(unitName="Winedunk")
	EntityManager em;

	public StatusService() {}

    public Tblpfstatus getByID(Integer id) {
    	try {
    		return em.find(Tblpfstatus.class, id);
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
