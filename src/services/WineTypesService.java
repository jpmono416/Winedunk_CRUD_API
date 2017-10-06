package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblWineTypes;

@Stateless
@LocalBean
public class WineTypesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblWineTypes> getWineTypes() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblWineTypes b");
        try { return (List<tblWineTypes>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblWineTypes getWineTypeById(Integer id)
    {
    	try { return em.find(tblWineTypes.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Integer addWineType(tblWineTypes wineType) {
        try
        {
        	if(wineType.getId() != null) { wineType.setId(null); }
        	em.persist(wineType);
        	em.flush();
        	System.out.println("Wine type id = "+wineType.getId());
        	return wineType.getId();
        } catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean updateWineType(tblWineTypes wineType)
    {
    	if(wineType == null || wineType.getId() == null) { return false; }
        em.merge(wineType);
        return true;
    }

    public Boolean deleteWineType(Integer id)
    {
        tblWineTypes wineType = getWineTypeById(id);
        if(wineType != null)
        {
            wineType.setDeleted(true);
            em.merge(wineType);
            return true;
        }
        return false;
    }

	public tblWineTypes getByName(String wineTypeName) {
		try {
			return em.createNamedQuery("tblWineTypes.findByName", tblWineTypes.class)
				     .setParameter("name", wineTypeName)
				     .getSingleResult();
		} catch (Exception e) {
			if(!e.getClass().equals(NoResultException.class))
				e.printStackTrace();
			return new tblWineTypes();
		}
	}
}