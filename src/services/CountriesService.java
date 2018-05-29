package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import models.tblCountries;
import models.tblCountryBasicData;
import models.viewCountriesWithWines;

@Stateless
@LocalBean
public class CountriesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblCountries> getCountries() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblCountries b");
        try { return (List<tblCountries>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblCountries getCountryById(Integer id)
    {
    	try { return em.find(tblCountries.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblCountries getCountryByName(String name)
    {
    	try {
    		return em.createNamedQuery("tblCountries.findByName", tblCountries.class).setParameter("name", name).getSingleResult();
    	} catch (NoResultException noResExc) {
    		return null;
    	}
    }

    public Integer addCountry(tblCountries country) {
        try
        {
        	if(country.getId() != null) { country.setId(null); }
        	
        	// aripe 2018-04-12
        	Integer maxColumnLength = country.getClass().getDeclaredField("name").getAnnotation(Column.class).length();
        	if (country.getName() != null && country.getName().length() > maxColumnLength) {
        		country.setName( country.getName().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = country.getClass().getDeclaredField("isoAlpha-2-Code").getAnnotation(Column.class).length();
        	if (country.getIsoAlpha2Code() != null && country.getIsoAlpha2Code().length() > maxColumnLength) {
        		country.setIsoAlpha2Code( country.getIsoAlpha2Code().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	maxColumnLength = country.getClass().getDeclaredField("isoAlpha-3-Code").getAnnotation(Column.class).length();
        	if (country.getIsoAlpha3Code() != null && country.getIsoAlpha3Code().length() > maxColumnLength) {
        		country.setIsoAlpha3Code( country.getIsoAlpha3Code().substring(0, maxColumnLength - 3).concat("...") );
        	}
        	
        	em.persist(country);
        	em.flush();
        	return country.getId();
        } catch (Exception e) { return null; }
    }

    public Boolean updateCountry(tblCountries country)
    {
    	if(country == null || country.getId() == null) { return false; }
        em.merge(country);
        return true;
    }

    public Boolean deleteCountry(Integer id)
    {
        tblCountries country = getCountryById(id);
        if(country != null)
        {
            country.setDeleted(true);
            em.merge(country);
            return true;
        }
        return false;
    }

	public tblCountryBasicData getCountryBasicDataById(Integer id) {
    	try { return em.find(tblCountryBasicData.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
	}

	public List<viewCountriesWithWines> getCountriesWithWines() {
        
		TypedQuery<viewCountriesWithWines> typedQuery = em.createNamedQuery("viewCountriesWithWines.findAll", viewCountriesWithWines.class);
		try {
			return (List<viewCountriesWithWines>) typedQuery.getResultList();
		} catch (Exception e) {
			return null;
		}
		
	}
    
     /*
    Countries
     

    public Result getCountriesForCountry(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblCountries.find.byId(id).getCountries())));
    }

    public Result getCountryForCountry(Integer countryId, Integer countryId)
    {
        //Identify the country
        tblCountries country = tblCountries.find.byId(countryId);
        if(country == null){ return ok("false"); }

        //Identify the country
        tblCountries country = tblCountries.find.byId(countryId);
        if(country == null){ return ok("false"); }

        //List of countries of this country
        List<tblCountries> countries = country.getCountries();
        if(countries.contains(country)) { return ok("true"); }
        return ok("false");
    }

    public Result addCountryToCountry(Integer countryId, Integer countryId)
    {
        //Identify which country to add to
        tblCountries country = tblCountries.find.byId(countryId);
        if(country == null){ return ok("false"); }

        //Identify which country we want to add
        tblCountries country = tblCountries.find.byId(countryId);
        if(country == null){ return ok("false"); }
        country.countries.add(country);
        country.save();
        return ok("true");
    }

    public Result deleteCountryFromCountry(Integer countryId, Integer countryId) {
        //Find country
        tblCountries country = tblCountries.find.byId(countryId);
        if(country == null){ return ok("false"); }

        //Find country
        tblCountries country = tblCountries.find.byId(countryId);
        if(country == null){ return ok("false"); }

        List<tblCountries> countries = country.getCountries();
        if(countries.contains(country))
        {
            if (countries.remove(country)) {
                country.setCountries(countries);
                country.save();
                country.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}