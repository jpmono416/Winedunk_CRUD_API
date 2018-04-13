package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
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
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = partnersContact.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (partnersContact.getName() != null && partnersContact.getName().length() > maxColumnLength) {
        		partnersContact.setName( partnersContact.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = partnersContact.getClass().getDeclaredField("position").getAnnotation(Column.class).length();
        	if (partnersContact.getPosition() != null && partnersContact.getPosition().length() > maxColumnLength) {
        		partnersContact.setPosition( partnersContact.getPosition().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = partnersContact.getClass().getDeclaredField("primaryEmail").getAnnotation(Column.class).length();
        	if (partnersContact.getPrimaryEmail() != null && partnersContact.getPrimaryEmail().length() > maxColumnLength) {
        		partnersContact.setPrimaryEmail( partnersContact.getPrimaryEmail().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = partnersContact.getClass().getDeclaredField("primaryPhoneNumber").getAnnotation(Column.class).length();
        	if (partnersContact.getPrimaryPhoneNumber() != null && partnersContact.getPrimaryPhoneNumber().length() > maxColumnLength) {
        		partnersContact.setPrimaryPhoneNumber( partnersContact.getPrimaryPhoneNumber().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = partnersContact.getClass().getDeclaredField("secondaryEmail").getAnnotation(Column.class).length();
        	if (partnersContact.getSecondaryEmail() != null && partnersContact.getSecondaryEmail().length() > maxColumnLength) {
        		partnersContact.setSecondaryEmail( partnersContact.getSecondaryEmail().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = partnersContact.getClass().getDeclaredField("secondaryPhoneNumber").getAnnotation(Column.class).length();
        	if (partnersContact.getSecondaryPhoneNumber() != null && partnersContact.getSecondaryPhoneNumber().length() > maxColumnLength) {
        		partnersContact.setSecondaryPhoneNumber( partnersContact.getSecondaryPhoneNumber().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
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