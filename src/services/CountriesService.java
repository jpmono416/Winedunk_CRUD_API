package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblCountries;

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

    public Boolean addCountry(tblCountries country) {
        try
        {
        	if(country.getId() != null) { country.setId(null); }
        	em.persist(country);
        	return true;
        } catch (Exception e) { return false; }
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