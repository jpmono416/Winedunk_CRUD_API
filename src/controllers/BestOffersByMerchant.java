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

import models.tblBestOffersbyMerchants;
import services.BestOffersByMerchantService;

/**
 * Servlet implementation class BestOffersByMerchant
 */
@WebServlet("/BestOffersByMerchant")
public class BestOffersByMerchant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BestOffersByMerchantService BestOffersByMerchantervice = new BestOffersByMerchantService();
    public BestOffersByMerchant() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getBestOffersByMerchant" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblBestOffersbyMerchants> BestOffersByMerchant = BestOffersByMerchantervice.getBestOffersByMerchant();
					String arrayToJson = objectMapper.writeValueAsString(BestOffersByMerchant);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
				
			case "getBestOfferByMerchant" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblBestOffersbyMerchants BestOfferByMerchant = BestOffersByMerchantervice.getBestOfferByMerchantById(id);
					String arrayToJson = objectMapper.writeValueAsString(BestOfferByMerchant);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
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
			case "addBestOfferByMerchant" :
				try
				{
					tblBestOffersbyMerchants BestOfferByMerchant = new tblBestOffersbyMerchants();
					ObjectMapper mapper = new ObjectMapper();
					BestOfferByMerchant = mapper.readValue(content, tblBestOffersbyMerchants.class);
					
					if(BestOffersByMerchantervice.addBestOfferByMerchant(BestOfferByMerchant)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "updateBestOfferByMerchant" :
				try
				{
					tblBestOffersbyMerchants BestOfferByMerchant = new tblBestOffersbyMerchants();
					ObjectMapper mapper = new ObjectMapper();
					BestOfferByMerchant = mapper.readValue(content, tblBestOffersbyMerchants.class);
					
					if(BestOffersByMerchantervice.updateBestOfferByMerchant(BestOfferByMerchant)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "deleteBestOfferByMerchant" :
				try
				{
					Integer id = Integer.parseInt(content);
					if(BestOffersByMerchantervice.deleteBestOfferByMerchant(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
		}
	}

}