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

import services.BestOffersByWineTypeViewService;
import services.EncodeURL;
import services.MerchantsWithBestOffersViewService;
import views.viewBestOffersbyWineTypes;
import views.viewMerchantsWithBestOffers;


@WebServlet("/merchantsWithBestOffersView")
public class MerchantsWithBestOffersView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	MerchantsWithBestOffersViewService merchantsWithBestOffersService = new MerchantsWithBestOffersViewService();
    public MerchantsWithBestOffersView() { super(); }
    
    EncodeURL urlEncoder = new EncodeURL();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getMerchantsWithBestOffers" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewMerchantsWithBestOffers> bestOffersByWineTypes = merchantsWithBestOffersService.getMerchantsWithBestOffers();
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineTypes);
					response.getWriter().write("{ \"Merchants\" : " + arrayToJson + " }");
				} 
				catch (Exception e) { e.printStackTrace(); }
			break;
			
			case "getMerchantWithBestOffers" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	viewMerchantsWithBestOffers bestOffersByWineType = merchantsWithBestOffersService.getMerchantsWithBestOfferBy(id);
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineType);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
			break;
		}
	}
}