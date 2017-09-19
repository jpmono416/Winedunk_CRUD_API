package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblCurrencies;
import services.CurrenciesService;

/**
 * Servlet implementation class Currencies
 */
@WebServlet("/currencies")
public class Currencies extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CurrenciesService currencyService = new CurrenciesService();
    public Currencies() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getCurrencies" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblCurrencies> currencies = currencyService.getCurrencies();
					String arrayToJson = objectMapper.writeValueAsString(currencies);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getCurrency" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblCurrencies currency = currencyService.getCurrencyById(id);
					String arrayToJson = objectMapper.writeValueAsString(currency);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
        
	    while ((line = reader.readLine()) != null) 
        { sb.append('\n').append(line); }
	    reader.close();

	    String content = sb.toString().replaceFirst("\n", "");
	    
		String action = request.getParameter("action");
		switch (action) 
		{
			case "addCurrency" :
			{
				try
				{
					tblCurrencies currency = new tblCurrencies();
					ObjectMapper mapper = new ObjectMapper();
					currency = mapper.readValue(content, tblCurrencies.class);
					
					if(currencyService.addCurrency(currency)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateCurrency" :
			{
				try
				{
					tblCurrencies currency = new tblCurrencies();
					ObjectMapper mapper = new ObjectMapper();
					currency = mapper.readValue(content, tblCurrencies.class);
					
					if(currencyService.updateCurrency(currency)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteCurrency" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(currencyService.deleteCurrency(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}