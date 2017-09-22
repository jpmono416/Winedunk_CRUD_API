package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewWinePriceComparison;

@Stateless
@LocalBean
public class WinePriceComparisonViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewWinePriceComparison> getWinePriceComparisons()
    {
        Query query	= em.createQuery("SELECT b FROM viewWinePriceComparison b");
        try { return (List<viewWinePriceComparison>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public viewWinePriceComparison getWinePriceComparisonById(Integer id)
    {
    	try { return em.find(viewWinePriceComparison.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }
}