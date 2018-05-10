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

import org.apache.commons.lang3.math.NumberUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblWines;
import services.WinesService;

/**
 * Servlet implementation class Wines
 */
@WebServlet("/wines")
public class Wines extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WinesService wineService = new WinesService();
    public Wines() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWines" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblWines> wines = wineService.getWines();
					String arrayToJson = objectMapper.writeValueAsString(wines);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getWine" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblWines wine = wineService.getWineById(id);
					String arrayToJson = objectMapper.writeValueAsString(wine);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByGtin":
			{
				if(!request.getParameterMap().containsKey("gtin")) 
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing Gtin");
					return;
				}

				tblWines wine = wineService.getWineByGtin(request.getParameter("gtin"));

				response.getWriter().write(new ObjectMapper().writeValueAsString(wine));
				break;
			}
			case "getByNameBottleAndVintage":
			{
				for(String parameter : new String[] {"name", "bottleSize", "vintage"})
				{
					if(!request.getParameterMap().containsKey(parameter))
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing "+parameter);
					}
				}

				tblWines wine = wineService.getWineByNameBottleAndVintage(request.getParameter("name"), 
																		  NumberUtils.isCreatable(request.getParameter("bottleSize")) ? Float.parseFloat(request.getParameter("bottleSize")) : null,
																		  NumberUtils.isCreatable(request.getParameter("vintage")) ? Integer.parseInt(request.getParameter("vintage")) : null);
				
				response.getWriter().write(new ObjectMapper().writeValueAsString(wine));
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
			case "addWine" :
			{
				try
				{
					tblWines wine = new tblWines();
					ObjectMapper mapper = new ObjectMapper();
					wine = mapper.readValue(content, tblWines.class);
					Integer id = wineService.addWine(wine);
					if(id!=null) { response.getWriter().print(id); }
					else { response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong inserting the wine "+wine.getName()); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateWine" :
			{
				try
				{
					tblWines wine = new tblWines();
					ObjectMapper mapper = new ObjectMapper();
					wine = mapper.readValue(content, tblWines.class);
					
					if(wineService.updateWine(wine)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "deleteWine" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(wineService.deleteWine(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); return; }
				break;
			}
		}
	}

}