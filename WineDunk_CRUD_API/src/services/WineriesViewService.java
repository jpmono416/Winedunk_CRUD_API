package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewWineries;

@Stateless
@LocalBean
public class WineriesViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewWineries> getCountries()
    {
        Query query	= em.createQuery("SELECT b FROM viewWineries b");
        try { return (List<viewWineries>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }
}