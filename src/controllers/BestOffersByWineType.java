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

import models.tblBestOffersbyType;
import services.BestOffersByWineTypeService;

/**
 * Servlet implementation class BestOffersByWineType
 */
@WebServlet("/BestOffersByWineType")
public class BestOffersByWineType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BestOffersByWineTypeService BestOffersByWineTypeervice = new BestOffersByWineTypeService();
    public BestOffersByWineType() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getBestOffersByWineType" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblBestOffersbyType> BestOffersByWineType = BestOffersByWineTypeervice.getBestOffersByWineType();
					String arrayToJson = objectMapper.writeValueAsString(BestOffersByWineType);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
				
			case "getBestOfferByWineType" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblBestOffersbyType BestOfferByWineType = BestOffersByWineTypeervice.getBestOfferByWineTypeById(id);
					String arrayToJson = objectMapper.writeValueAsString(BestOfferByWineType);
					
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
			case "addBestOfferByWineType" :
				try
				{
					tblBestOffersbyType BestOfferByWineType = new tblBestOffersbyType();
					ObjectMapper mapper = new ObjectMapper();
					BestOfferByWineType = mapper.readValue(content, tblBestOffersbyType.class);
					
					if(BestOffersByWineTypeervice.addBestOfferByWineType(BestOfferByWineType)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "updateBestOfferByWineType" :
				try
				{
					tblBestOffersbyType BestOfferByWineType = new tblBestOffersbyType();
					ObjectMapper mapper = new ObjectMapper();
					BestOfferByWineType = mapper.readValue(content, tblBestOffersbyType.class);
					
					if(BestOffersByWineTypeervice.updateBestOfferByWineType(BestOfferByWineType)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "deleteBestOfferByWineType" :
				try
				{
					Integer id = Integer.parseInt(content);
					if(BestOffersByWineTypeervice.deleteBestOfferByWineType(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
		}
	}

}