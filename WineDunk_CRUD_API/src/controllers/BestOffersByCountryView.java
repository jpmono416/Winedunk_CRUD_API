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

import services.BestOffersByCountryViewService;
import services.EncodeURL;
import views.viewBestOffersbyCountries;


@WebServlet("/bestOffersByCountryView")
public class BestOffersByCountryView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BestOffersByCountryViewService bestOffersByCountryService = new BestOffersByCountryViewService();
    public BestOffersByCountryView() { super(); }
    
    EncodeURL urlEncoder = new EncodeURL();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getBestOffersByCountries" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewBestOffersbyCountries> bestOffersByCountries = bestOffersByCountryService.getBestOffersByCountry();
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByCountries);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getBestOffersByCountry" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					viewBestOffersbyCountries bestOffersByCountry = bestOffersByCountryService.getBestOfferByCountryById(id);
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByCountry);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getOffersForCountry" :
				try
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			    	
			    	List<viewBestOffersbyCountries> bestOffersByCountries = bestOffersByCountryService.getSimplifiedBestOffersForCountry(id);
			    	String arrayToJson = objectMapper.writeValueAsString(bestOffersByCountries);
					response.getWriter().write("{ \"BestOffers\" : " + arrayToJson + " }");
				} catch (Exception e) { e.printStackTrace(); }
				break;
		}
	}
}