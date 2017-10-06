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
	ObjectMapper mapper;

	@EJB
	CountriesService countryService = new CountriesService();

	public Countries() {
		super();
		this.mapper = new ObjectMapper();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getCountries" :
			{
				try 
				{ 
			    	//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblCountries> countries = countryService.getCountries();
					String arrayToJson = this.mapper.writeValueAsString(countries);
					
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
					
					
			    	//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblCountries country = countryService.getCountryById(id);
					if(country!=null)
					{
						String arrayToJson = mapper.writeValueAsString(country);
						response.getWriter().write(arrayToJson);
					}
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

				tblCountries country = this.countryService.getCountryByName(request.getParameter("name"));

				if(country!=null)
					response.getWriter().write(this.mapper.writeValueAsString(country));
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
					country = this.mapper.readValue(content, tblCountries.class);
					Integer id = countryService.addCountry(country);
					if(id!=null) { response.getWriter().print(id); }
					else { response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong inserting the country "+country.getName()); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateCountry" :
			{
				try
				{
					tblCountries country = new tblCountries();
					country = this.mapper.readValue(content, tblCountries.class);
					
					if(countryService.updateCountry(country)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteCountry" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(countryService.deleteCountry(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}