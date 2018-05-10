// aripe 2018-04-07
package controllers;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import services.StoredProceduresService;


/**
 * Servlet implementation for storedProceduresService
 */
@WebServlet("/storedProcedures")
public class StoredProcedures extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	StoredProceduresService storedProcedures = new StoredProceduresService();
    public StoredProcedures() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{

			case "callSPUpdateDataAfterImporting": {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPUpdateDataAfterImporting();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "callSPUpdateMinPriceOntblWines" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPUpdateMinPriceOntblWines();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		
			case "callSPUpdateCountriesWithWines" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPUpdateCountriesWithWines();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		
			case "callSPUpdateRecommendedWines" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPUpdateRecommendedWines();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		
			case "callSPUpdateBestOffersbyCountry" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPUpdateBestOffersbyCountry();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		
			case "callSPUpdateBestOffersbyMerchants" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPUpdateBestOffersbyMerchants();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		
			case "callSPUpdateBestOffersbyWineType" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPUpdateBestOffersbyWineType();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		
			case "callSPFlaggingProductsAsDeleted" : {
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	boolean result = storedProcedures.callSPFlaggingProductsAsDeleted();
					
					response.setStatus(200);
					response.getWriter().write("{\"result\" = \"" + result + "\"}");
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
		}
	}
}