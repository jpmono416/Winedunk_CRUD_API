package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewRecommendedWines;

@Stateless
@LocalBean
public class RecommendedWinesViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewRecommendedWines> getRecommendedWines()
    {
        Query query	= em.createNativeQuery("SELECT * FROM viewRecommendedWines LIMIT 6", viewRecommendedWines.class);
        try { return (List<viewRecommendedWines>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }
}