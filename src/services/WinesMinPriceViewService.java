package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import views.viewWinesMinimumPrice;

@Stateless
@LocalBean
public class WinesMinPriceViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    public viewWinesMinimumPrice getWineById(Integer id)
    {
    	try { return em.find(viewWinesMinimumPrice.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
}