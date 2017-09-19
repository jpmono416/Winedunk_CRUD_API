package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewCountriesWithWines;

@Stateless
@LocalBean
public class CountriesWithWinesViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewCountriesWithWines> getCountries()
    {
        Query query	= em.createQuery("SELECT b FROM viewCountriesWithWines b");
        try { return (List<viewCountriesWithWines>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }
}