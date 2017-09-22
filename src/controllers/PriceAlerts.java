package controllers;

import java.io.BufferedReader;
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

import models.tblUserPriceAlerts;
import services.PriceAlertsService;

/**
 * Servlet implementation class PriceAlerts
 */
@WebServlet("/priceAlerts")
public class PriceAlerts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PriceAlertsService priceAlertService = new PriceAlertsService();
    public PriceAlerts() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPriceAlerts" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserPriceAlerts> priceAlerts = priceAlertService.getPriceAlerts();
					String arrayToJson = objectMapper.writeValueAsString(priceAlerts);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPriceAlert" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserPriceAlerts priceAlert = priceAlertService.getPriceAlertById(id);
					String arrayToJson = objectMapper.writeValueAsString(priceAlert);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
        
	    while ((line = reader.readLine()) != null) 
        { sb.append('\n').append(line); }
	    reader.close();

	    String content = sb.toString().replaceFirst("\n", "");
	    
		String action = request.getParameter("action");
		switch (action) 
		{
			case "addPriceAlert" :
			{
				try
				{
					tblUserPriceAlerts priceAlert = new tblUserPriceAlerts();
					ObjectMapper mapper = new ObjectMapper();
					priceAlert = mapper.readValue(content, tblUserPriceAlerts.class);
					
					if(priceAlertService.addPriceAlert(priceAlert)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updatePriceAlert" :
			{
				try
				{
					tblUserPriceAlerts priceAlert = new tblUserPriceAlerts();
					ObjectMapper mapper = new ObjectMapper();
					priceAlert = mapper.readValue(content, tblUserPriceAlerts.class);
					
					if(priceAlertService.updatePriceAlert(priceAlert)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deletePriceAlert" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(priceAlertService.deletePriceAlert(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}