package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblPartnersTypes;

@Stateless
@LocalBean
public class PartnersTypesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblPartnersTypes> getPartnerTypes()
	
    {
        Query query	= em.createQuery("SELECT b FROM tblPartnersTypes b");
        try { return (List<tblPartnersTypes>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblPartnersTypes getPartnerTypeById(Integer id)
    {
    	try { return em.find(tblPartnersTypes.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addPartnerType(tblPartnersTypes partnerType) {
        try
        {
        	if(partnerType.getId() != null) { partnerType.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = partnerType.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (partnerType.getName() != null && partnerType.getName().length() > maxColumnLength) {
        		partnerType.setName( partnerType.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(partnerType);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updatePartnerType(tblPartnersTypes partnerType)
    {
    	if(partnerType == null || partnerType.getId() == null) { return false; }
        em.merge(partnerType);
        return true;
    }

    public Boolean deletePartnerType(Integer id)
    {
        tblPartnersTypes partnerType = getPartnerTypeById(id);
        if(partnerType != null)
        {
            partnerType.setDeleted(true);
            em.merge(partnerType);
            return true;
        }
        return false;
    }
}