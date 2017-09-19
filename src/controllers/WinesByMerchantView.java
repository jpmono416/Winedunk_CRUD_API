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

import services.WinesByMerchantViewService;
import services.EncodeURL;
import views.viewWinesbyMerchants;


@WebServlet("/winesByMerchantView")
public class WinesByMerchantView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WinesByMerchantViewService bestOffersByMerchantService = new WinesByMerchantViewService();
    public WinesByMerchantView() { super(); }
    
    EncodeURL urlEncoder = new EncodeURL();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWinesByMerchants" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewWinesbyMerchants> bestOffersByMerchants = bestOffersByMerchantService.getWinesByMerchant();
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByMerchants);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getWinesByMerchant" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					viewWinesbyMerchants bestOffersByMerchant = bestOffersByMerchantService.getWineByMerchantById(id);
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByMerchant);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getWinesForMerchant" :
				try
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			    	
			    	List<viewWinesbyMerchants> bestOffersByMerchants = bestOffersByMerchantService.getSimplifiedWinesForMerchant(id);
			    	String arrayToJson = objectMapper.writeValueAsString(bestOffersByMerchants);
					response.getWriter().write("{ \"Wines\" : " + arrayToJson + " }");
				} catch (Exception e) { e.printStackTrace(); }
				break;
		}
	}
}