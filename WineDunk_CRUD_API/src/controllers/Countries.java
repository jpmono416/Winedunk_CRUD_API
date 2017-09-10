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

import models.tblCountries;
import services.CountriesService;

/**
 * Servlet implementation class Countries
 */
@WebServlet("/countries")
public class Countries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CountriesService countryService = new CountriesService();
    public Countries() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getCountries" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblCountries> countries = countryService.getCountries();
					String arrayToJson = objectMapper.writeValueAsString(countries);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getCountry" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblCountries country = countryService.getCountryById(id);
					String arrayToJson = objectMapper.writeValueAsString(country);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByName":
				if(!request.getParameterMap().containsKey("name"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing name");
					return;
				}

				countryService.getCountryByName(request.getParameter("name"));
				break;
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
			case "addCountry" :
			{
				try
				{
					tblCountries country = new tblCountries();
					ObjectMapper mapper = new ObjectMapper();
					country = mapper.readValue(content, tblCountries.class);
					
					if(countryService.addCountry(country)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateCountry" :
			{
				try
				{
					tblCountries country = new tblCountries();
					ObjectMapper mapper = new ObjectMapper();
					country = mapper.readValue(content, tblCountries.class);
					
					if(countryService.updateCountry(country)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteCountry" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(countryService.deleteCountry(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}