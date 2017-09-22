package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import views.viewAppellations;

@Stateless
@LocalBean
public class AppellationsViewService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<viewAppellations> getAppellations()
    {
        Query query	= em.createQuery("SELECT b FROM viewAppellations b");
        try { return (List<viewAppellations>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }
}