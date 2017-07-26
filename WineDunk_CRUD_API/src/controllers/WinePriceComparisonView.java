package controllers;

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

import services.PersistComparisonSearch;
import services.WinePriceComparisonViewService;
import views.viewWinePriceComparison;

/**
 * Servlet implementation class Wines
 */
@WebServlet("/winePriceComparisonView")
public class WinePriceComparisonView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WinePriceComparisonViewService winePriceComparisonService = new WinePriceComparisonViewService();
    public WinePriceComparisonView() { super(); }

    @EJB
    PersistComparisonSearch persistComparisonSearch = new PersistComparisonSearch(); 

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
		    	
					List<viewWinePriceComparison> wines = winePriceComparisonService.getWinePriceComparisons();
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
		    	
			    	viewWinePriceComparison wine = winePriceComparisonService.getWinePriceComparisonById(id);
					String arrayToJson = objectMapper.writeValueAsString(wine);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getComparisonWithQuery" :
			{
				try
				{
					if (!request.getParameterMap().containsKey("id")) { return; }
					persistComparisonSearch.getResults(request.getParameter("id"));
					response.setStatus(200);
					response.getWriter().write("{ \"Shops\" : " + persistComparisonSearch.getJsonResult() + " }");
				} catch (Exception e) { e.printStackTrace(); }
				
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

}