package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblPartnersContacts;

@Stateless
@LocalBean
public class PartnersContactsService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblPartnersContacts> getPartnersContacts() 
	
    {
        Query query	= em.createQuery("SELECT pc FROM tblPartnersContacts pc");
        try { return (List<tblPartnersContacts>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblPartnersContacts getPartnersContactById(Integer id)
    {
    	try { return em.find(tblPartnersContacts.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addPartnersContact(tblPartnersContacts partnersContact) {
        try
        {
        	if(partnersContact.getId() != null) { partnersContact.setId(null); }
        	em.persist(partnersContact);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updatePartnersContact(tblPartnersContacts partnersContact)
    {
    	if(partnersContact == null || partnersContact.getId() == null) { return false; }
        em.merge(partnersContact);
        return true;
    }

    public Boolean deletePartnersContact(Integer id)
    {
        tblPartnersContacts partnersContact = getPartnersContactById(id);
        if(partnersContact != null)
        {
            partnersContact.setDeleted(true);
            em.merge(partnersContact);
            return true;
        }
        return false;
    }
}