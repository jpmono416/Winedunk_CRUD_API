package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblBestOffersbyCountries;
import services.BestOffersByCountryService;

/**
 * Servlet implementation class BestOffersByCountry
 */
@WebServlet("/BestOffersByCountry")
public class BestOffersByCountry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper= new ObjectMapper();
	
	@EJB
	BestOffersByCountryService BestOffersByCountryervice = new BestOffersByCountryService();
    public BestOffersByCountry() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
    	//Set pretty printing of json
    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);

    	switch(request.getParameter("action"))
		{
			case "getBestOffersByCountry" :
				try 
				{
		    	
					List<tblBestOffersbyCountries> BestOffersByCountry = BestOffersByCountryervice.getBestOffersByCountry();
					String arrayToJson = this.mapper.writeValueAsString(BestOffersByCountry);
					
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); }
				break;
				
			case "getBestOfferByCountry" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }

			    	//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
					Integer id = Integer.parseInt(request.getParameter("id"));

					tblBestOffersbyCountries BestOfferByCountry = BestOffersByCountryervice.getBestOfferByCountryById(id);
					String arrayToJson = this.mapper.writeValueAsString(BestOfferByCountry);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); }
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		String action = request.getParameter("action");
		switch (action) 
		{
			case "addBestOfferByCountry" :
				try
				{
					tblBestOffersbyCountries bestOfferByCountry = this.mapper.readValue(request.getInputStream(), tblBestOffersbyCountries.class);
					
					if(BestOffersByCountryervice.addBestOfferByCountry(bestOfferByCountry)) { response.getWriter().println("True"); }
				} catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); }
				return;
			
			case "updateBestOfferByCountry" :
				try
				{
					tblBestOffersbyCountries bestOfferByCountry = this.mapper.readValue(request.getInputStream(), tblBestOffersbyCountries.class);
					
					if(BestOffersByCountryervice.updateBestOfferByCountry(bestOfferByCountry)) { response.getWriter().println("True"); }
				} catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); }
				return;
			
			case "deleteBestOfferByCountry" :
				try
				{
					JsonNode json = this.mapper.readTree(request.getInputStream());
					if(BestOffersByCountryervice.deleteBestOfferByCountry(json.get("id").asInt())) { response.getWriter().println("True"); }
				} catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); }
				return;
		}
	}

}