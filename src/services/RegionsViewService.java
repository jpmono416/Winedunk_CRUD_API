package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewRegions;

@Stateless
@LocalBean
public class RegionsViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewRegions> getRegions()
    {
        Query query	= em.createQuery("SELECT b FROM viewRegions b");
        try { return (List<viewRegions>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }
}