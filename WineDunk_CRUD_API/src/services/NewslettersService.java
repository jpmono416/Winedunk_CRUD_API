package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblNewsletters;

@Stateless
@LocalBean
public class NewslettersService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblNewsletters> getNewsletters() 
	
    {
        Query query	= em.createQuery("SELECT p FROM tblNewsletters p");
        try { return (List<tblNewsletters>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblNewsletters getNewsletterById(Integer id)
    {
    	try { return em.find(tblNewsletters.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addNewsletter(tblNewsletters newsletter) {
        try
        {
        	if(newsletter.getId() != null) { newsletter.setId(null); }
        	em.persist(newsletter);
        	return true;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public Boolean updateNewsletter(tblNewsletters newsletter)
    {
    	if(newsletter == null || newsletter.getId() == null) { return false; }
        em.merge(newsletter);
        return true;
    }

    public Boolean deleteNewsletter(Integer id)
    {
        tblNewsletters newsletter = getNewsletterById(id);
        if(newsletter != null)
        {
            em.remove(newsletter);
            return true;
        }
        return false;
    }
}