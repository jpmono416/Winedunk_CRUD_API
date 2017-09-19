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

import services.MerchantsViewService;
import views.viewMerchants;

/**
 * Servlet implementation class Merchants
 */
@WebServlet("/merchantsView")
public class MerchantsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	MerchantsViewService merchantService = new MerchantsViewService();
    public MerchantsView() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getMerchants" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<viewMerchants> merchants = merchantService.getMerchants();
					String arrayToJson = objectMapper.writeValueAsString(merchants);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getMerchant" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					viewMerchants merchant = merchantService.getMerchantById(id);
					String arrayToJson = objectMapper.writeValueAsString(merchant);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
				
			/*
			 * This method uses a special query that returns 
			 * less fields and only for the merchants that have
			 * Any best offers at the moment  
			 */
				
			case "getMerchantsWithOffers" :
				ObjectMapper objectMapper = new ObjectMapper();
		    	//Set pretty printing of json
		    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
		    	List<viewMerchants> merchants = merchantService.getMerchantsWithOffers();
		    	String arrayToJson = objectMapper.writeValueAsString(merchants);
				response.getWriter().write("{ \"Merchants\" : " + arrayToJson + " }");
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return;
	}

}