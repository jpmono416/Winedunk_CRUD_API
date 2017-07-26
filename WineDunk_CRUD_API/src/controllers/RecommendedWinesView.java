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

import services.RecommendedWinesViewService;
import views.viewRecommendedWines;

/**
 * Servlet implementation class Wines
 */
@WebServlet("/recommendedWinesView")
public class RecommendedWinesView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	RecommendedWinesViewService wineService = new RecommendedWinesViewService();
    public RecommendedWinesView() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getRecommendedWines" :
			{
				try 
				{
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewRecommendedWines> wines = wineService.getRecommendedWines();
					String arrayToJson = objectMapper.writeValueAsString(wines);
					
					response.setStatus(200);
					response.getWriter().write("{ \"Wines\": " + arrayToJson + " }");
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		}
	}
}