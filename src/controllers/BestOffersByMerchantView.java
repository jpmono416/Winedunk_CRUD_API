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

import services.BestOffersByMerchantViewService;
import services.EncodeURL;
import views.viewBestOffersbyMerchants;


@WebServlet("/bestOffersByMerchantView")
public class BestOffersByMerchantView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BestOffersByMerchantViewService bestOffersByMerchantService = new BestOffersByMerchantViewService();
    public BestOffersByMerchantView() { super(); }
    
    EncodeURL urlEncoder = new EncodeURL();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getBestOffersByMerchants" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewBestOffersbyMerchants> bestOffersByMerchants = bestOffersByMerchantService.getBestOffersByMerchant();
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByMerchants);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getBestOffersByMerchant" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					viewBestOffersbyMerchants bestOffersByMerchant = bestOffersByMerchantService.getBestOfferByMerchantById(id);
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByMerchant);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getOffersForMerchant" :
				try
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			    	
			    	List<viewBestOffersbyMerchants> bestOffersByMerchants = bestOffersByMerchantService.getSimplifiedBestOffersForMerchant(id);
			    	String arrayToJson = objectMapper.writeValueAsString(bestOffersByMerchants);
					response.getWriter().write("{ \"BestOffers\" : " + arrayToJson + " }");
				} catch (Exception e) { e.printStackTrace(); }
				break;
		}
	}
}