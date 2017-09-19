package controllers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import services.EncodeURL;
import services.PersistWineSearch;
import services.WinesViewService;
import views.viewWines;


@WebServlet("/winesView")
public class WinesView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WinesViewService wineService = new WinesViewService();
    public WinesView() { super(); }
    
    @EJB
    PersistWineSearch persistSearch = new PersistWineSearch();
    
    EncodeURL urlEncoder = new EncodeURL();
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
		    	
					List<viewWines> wines = wineService.getWines();
					String arrayToJson = objectMapper.writeValueAsString(wines);
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
		    	
					viewWines wine = wineService.getWineById(id);
					String arrayToJson = objectMapper.writeValueAsString(wine);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getWinesWithQuery" :
			{
				Map<String, String[]> parameterMap = request.getParameterMap();
				
				persistSearch.resetParameters();
				
				if(parameterMap.containsKey("name")) 			{ persistSearch.setName(parameterMap.get("name")[0]); }
				if(parameterMap.containsKey("chosenRegion")) 	{ persistSearch.setRegionId(parameterMap.get("chosenRegion")[0]); }
				if(parameterMap.containsKey("chosenCountry")) 	{ persistSearch.setCountryId(parameterMap.get("chosenCountry")[0]); }
				if(parameterMap.containsKey("wineryId")) 		{ persistSearch.setWineryId(parameterMap.get("wineryId")[0]); }
				if(parameterMap.containsKey("appellationId")) 	{ persistSearch.setAppellationId(parameterMap.get("appellationId")[0]); }
				if(parameterMap.containsKey("chosenColour")) 	{ persistSearch.setColourId(parameterMap.get("chosenColour")[0]); }
				if(parameterMap.containsKey("vintageMin")) 		{ persistSearch.setVintageMin(parameterMap.get("vintageMin")[0]); }
				if(parameterMap.containsKey("vintageMax")) 		{ persistSearch.setVintageMax(parameterMap.get("vintageMax")[0]); }
				if(parameterMap.containsKey("abvMin")) 			{ persistSearch.setAbvMin(parameterMap.get("abvMin")[0]); }
				if(parameterMap.containsKey("abvMax")) 			{ persistSearch.setAbvMax(parameterMap.get("abvMax")[0]); }
				if(parameterMap.containsKey("minPrice")) 		{ persistSearch.setMinPrice(parameterMap.get("minPrice")[0]); }
				if(parameterMap.containsKey("maxPrice")) 		{ persistSearch.setMaxPrice(parameterMap.get("maxPrice")[0]); }
				if(parameterMap.containsKey("chosenClosure")) 	{ persistSearch.setClosureId(parameterMap.get("chosenClosure")[0]); }
				if(parameterMap.containsKey("chosenType")) 		{ persistSearch.setTypeId(parameterMap.get("chosenType")[0]); }
				if(parameterMap.containsKey("grapeVarietyId")) 	{ persistSearch.setGrapeVarietyId(parameterMap.get("grapeVarietyId")[0]); }
				if(parameterMap.containsKey("order")) 			{ persistSearch.setSortingMethod(parameterMap.get("order")[0]); }
				if(parameterMap.containsKey("currentPage"))		{ persistSearch.setPageNumber(Integer.parseInt(parameterMap.get("currentPage")[0])); }
				if(parameterMap.containsKey("merchant"))		{ persistSearch.setMerchantId(parameterMap.get("merchant")[0]); }
				
				try 
				{ 
					persistSearch.getResults();
					if(persistSearch.getJsonResult().length() <= 0) { return; }
					
					PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
						    response.getOutputStream(), StandardCharsets.UTF_8), true);
					
					printWriter.write("{ \"Wines\" : " + persistSearch.getJsonResult() + " }");
					printWriter.close();
				} catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case"getWineWithQuery" :
				if(!request.getParameterMap().containsKey("id")) { return; }
				persistSearch.resetParameters();
				persistSearch.getOneResult(request.getParameter("id"));
				response.setStatus(200);
				response.getWriter().write(persistSearch.getJsonResult());
				break;
			
			case "getCountWithQuery" :
				
				Map<String, String[]> parameterMap = request.getParameterMap();
				
				persistSearch.resetParameters();
				
				if(parameterMap.containsKey("name")) 			{ persistSearch.setName(parameterMap.get("name")[0]); }
				if(parameterMap.containsKey("chosenRegion")) 	{ persistSearch.setRegionId(parameterMap.get("chosenRegion")[0]); }
				if(parameterMap.containsKey("chosenCountry")) 	{ persistSearch.setCountryId(parameterMap.get("chosenCountry")[0]); }
				if(parameterMap.containsKey("wineryId")) 		{ persistSearch.setWineryId(parameterMap.get("wineryId")[0]); }
				if(parameterMap.containsKey("appellationId")) 	{ persistSearch.setAppellationId(parameterMap.get("appellationId")[0]); }
				if(parameterMap.containsKey("chosenColour")) 	{ persistSearch.setColourId(parameterMap.get("chosenColour")[0]); }
				if(parameterMap.containsKey("vintageMin")) 		{ persistSearch.setVintageMin(parameterMap.get("vintageMin")[0]); }
				if(parameterMap.containsKey("vintageMax")) 		{ persistSearch.setVintageMax(parameterMap.get("vintageMax")[0]); }
				if(parameterMap.containsKey("abvMin")) 			{ persistSearch.setAbvMin(parameterMap.get("abvMin")[0]); }
				if(parameterMap.containsKey("abvMax")) 			{ persistSearch.setAbvMax(parameterMap.get("abvMax")[0]); }
				if(parameterMap.containsKey("minPrice")) 		{ persistSearch.setMinPrice(parameterMap.get("minPrice")[0]); }
				if(parameterMap.containsKey("maxPrice")) 		{ persistSearch.setMaxPrice(parameterMap.get("maxPrice")[0]); }
				if(parameterMap.containsKey("chosenClosure")) 	{ persistSearch.setClosureId(parameterMap.get("chosenClosure")[0]); }
				if(parameterMap.containsKey("chosenType")) 		{ persistSearch.setTypeId(parameterMap.get("chosenType")[0]); }
				if(parameterMap.containsKey("grapeVarietyId")) 	{ persistSearch.setGrapeVarietyId(parameterMap.get("grapeVarietyId")[0]); }
				if(parameterMap.containsKey("order")) 			{ persistSearch.setSortingMethod(parameterMap.get("order")[0]); }
				if(parameterMap.containsKey("currentPage"))		{ persistSearch.setPageNumber(Integer.parseInt(parameterMap.get("currentPage")[0])); }
				
				try
				{
					persistSearch.getCountForQuery();
					response.getWriter().print(persistSearch.getTotalPages());
				} catch (Exception e) { e.printStackTrace(); }
				
				break;
		}
	}
}