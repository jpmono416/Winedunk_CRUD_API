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
import views.viewBestOffersbyWineTypes;


@WebServlet("/bestOffersByWineTypeView")
public class BestOffersByWineTypeView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BestOffersByWineTypeViewService bestOffersByWineTypeService = new BestOffersByWineTypeViewService();
    public BestOffersByWineTypeView() { super(); }
    
    EncodeURL urlEncoder = new EncodeURL();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getBestOffersByWineTypes" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewBestOffersbyWineTypes> bestOffersByWineTypes = bestOffersByWineTypeService.getBestOffersByWineType();
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineTypes);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getBestOffersByWineType" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					viewBestOffersbyWineTypes bestOffersByWineType = bestOffersByWineTypeService.getBestOfferByWineTypeById(id);
					String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineType);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getOffersForWineType" :
				try
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			    	
			    	List<viewBestOffersbyWineTypes> bestOffersByWineTypes = bestOffersByWineTypeService.getSimplifiedBestOffersForWineType(id);
			    	String arrayToJson = objectMapper.writeValueAsString(bestOffersByWineTypes);
					response.getWriter().write("{ \"BestOffers\" : " + arrayToJson + " }");
				} catch (Exception e) { e.printStackTrace(); }
				break;
		}
	}
}