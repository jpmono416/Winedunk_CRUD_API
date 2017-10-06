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

import services.CountriesWithBestOffersViewService;
import services.EncodeURL;
import views.viewCountriesWithBestOffers;


@WebServlet("/countriesWithBestOffersView")
public class CountriesWithBestOffersView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	CountriesWithBestOffersViewService wineTypesWithBestOffersService = new CountriesWithBestOffersViewService();
    public CountriesWithBestOffersView() { super(); }
    
    EncodeURL urlEncoder = new EncodeURL();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getCountriesWithBestOffers" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewCountriesWithBestOffers> bestOffersByCoutries = wineTypesWithBestOffersService.getCountriesWithBestOffers();
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByCoutries);
					response.getWriter().write("{ \"Countries\" : " + arrayToJson + " }");
				} 
				catch (Exception e) { e.printStackTrace(); }
			break;
			
			case "getCountryWithBestOffers" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	viewCountriesWithBestOffers bestOffersByWineType = wineTypesWithBestOffersService.getCountriesWithBestOfferBy(id);
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineType);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
			break;
		}
	}
}