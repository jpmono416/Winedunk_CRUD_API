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

import services.EncodeURL;
import services.WineTypesWithBestOffersViewService;
import views.viewWineTypesWithBestOffers;


@WebServlet("/wineTypesWithBestOffersView")
public class WineTypesWithBestOffersView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WineTypesWithBestOffersViewService wineTypesWithBestOffersService = new WineTypesWithBestOffersViewService();
    public WineTypesWithBestOffersView() { super(); }
    
    EncodeURL urlEncoder = new EncodeURL();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWineTypesWithBestOffers" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewWineTypesWithBestOffers> bestOffersByWineTypes = wineTypesWithBestOffersService.getWineTypesWithBestOffers();
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineTypes);
					response.getWriter().write("{ \"WineTypes\" : " + arrayToJson + " }");
				} 
				catch (Exception e) { e.printStackTrace(); }
			break;
			
			case "getWineTypeWithBestOffers" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					try {
						Integer id = Integer.parseInt(request.getParameter("id"));
						ObjectMapper objectMapper = new ObjectMapper();
				    	//Set pretty printing of json
				    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			    	
				    	viewWineTypesWithBestOffers bestOffersByWineType = wineTypesWithBestOffersService.getWineTypesWithBestOfferBy(id);
						String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineType);
						response.getWriter().write(arrayToJson);
					} catch (Exception e) {
						return;
					}
				}
				catch (Exception e) { e.printStackTrace(); }
			break;
		}
	}
}