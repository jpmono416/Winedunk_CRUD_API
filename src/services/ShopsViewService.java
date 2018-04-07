package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewShops;

@Stateless
@LocalBean
public class ShopsViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewShops> getShops()
    {
        Query query	= em.createQuery("SELECT b FROM viewShops b");
        try { return (List<viewShops>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }
}