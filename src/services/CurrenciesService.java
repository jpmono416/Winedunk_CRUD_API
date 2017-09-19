package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import models.tblCurrencies;

@Stateless
@LocalBean
public class CurrenciesService {
	@PersistenceContext(unitName = "Winedunk")
	EntityManager em;

    @SuppressWarnings("unchecked")
	public List<tblCurrencies> getCurrencies() 
	
    {
        Query query	= em.createQuery("SELECT b FROM tblCurrencies b");
        try { return (List<tblCurrencies>) query.getResultList(); }
        catch (Exception e) { e.printStackTrace(); return null; }
    }

    public tblCurrencies getCurrencyById(Integer id)
    {
    	try { return em.find(tblCurrencies.class, id); }
    	catch (Exception e) { e.printStackTrace(); return null; }
    }

    public Boolean addCurrency(tblCurrencies currency) {
        try
        {
        	if(currency.getId() != null) { currency.setId(null); }
        	em.persist(currency);
        	return true;
        } catch (Exception e) { return false; }
    }

    public Boolean updateCurrency(tblCurrencies currency)
    {
    	if(currency == null || currency.getId() == null) { return false; }
        em.merge(currency);
        return true;
    }

    public Boolean deleteCurrency(Integer id)
    {
        tblCurrencies currency = getCurrencyById(id);
        if(currency != null)
        {
            currency.setDeleted(true);
            em.merge(currency);
            return true;
        }
        return false;
    }
    
     /*
    Currencies
     

    public Result getCurrenciesForCurrency(Integer id)
    {
        return ok(Json.prettyPrint(Json.toJson(tblCurrencies.find.byId(id).getCurrencies())));
    }

    public Result getCurrencyForCurrency(Integer currencyId, Integer currencyId)
    {
        //Identify the currency
        tblCurrencies currency = tblCurrencies.find.byId(currencyId);
        if(currency == null){ return ok("false"); }

        //Identify the currency
        tblCurrencies currency = tblCurrencies.find.byId(currencyId);
        if(currency == null){ return ok("false"); }

        //List of currencies of this currency
        List<tblCurrencies> currencies = currency.getCurrencies();
        if(currencies.contains(currency)) { return ok("true"); }
        return ok("false");
    }

    public Result addCurrencyToCurrency(Integer currencyId, Integer currencyId)
    {
        //Identify which currency to add to
        tblCurrencies currency = tblCurrencies.find.byId(currencyId);
        if(currency == null){ return ok("false"); }

        //Identify which currency we want to add
        tblCurrencies currency = tblCurrencies.find.byId(currencyId);
        if(currency == null){ return ok("false"); }
        currency.currencies.add(currency);
        currency.save();
        return ok("true");
    }

    public Result deleteCurrencyFromCurrency(Integer currencyId, Integer currencyId) {
        //Find currency
        tblCurrencies currency = tblCurrencies.find.byId(currencyId);
        if(currency == null){ return ok("false"); }

        //Find currency
        tblCurrencies currency = tblCurrencies.find.byId(currencyId);
        if(currency == null){ return ok("false"); }

        List<tblCurrencies> currencies = currency.getCurrencies();
        if(currencies.contains(currency))
        {
            if (currencies.remove(currency)) {
                currency.setCurrencies(currencies);
                currency.save();
                currency.save();
                return ok("true");
            } else { return ok("false"); }
        }else { return ok("false"); }
    }
    */
}