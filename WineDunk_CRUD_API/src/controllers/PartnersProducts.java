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

import models.tblPartnersProducts;
import services.PartnersProductsService;

/**
 * Servlet implementation class PartnersProductss
 */
@WebServlet("/partnersProductss")
public class PartnersProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PartnersProductsService partnersProductsService = new PartnersProductsService();
    public PartnersProducts() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPartnersProductss" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblPartnersProducts> partnersProductss = partnersProductsService.getPartnersProducts();
					String arrayToJson = objectMapper.writeValueAsString(partnersProductss);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPartnersProducts" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblPartnersProducts partnersProducts = partnersProductsService.getPartnersProductById(id);
					String arrayToJson = objectMapper.writeValueAsString(partnersProducts);
					
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
			case "addPartnersProducts" :
			{
				try
				{
					tblPartnersProducts partnersProducts = new tblPartnersProducts();
					ObjectMapper mapper = new ObjectMapper();
					partnersProducts = mapper.readValue(content, tblPartnersProducts.class);
					
					if(partnersProductsService.addPartnersProduct(partnersProducts)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updatePartnersProducts" :
			{
				try
				{
					tblPartnersProducts partnersProducts = new tblPartnersProducts();
					ObjectMapper mapper = new ObjectMapper();
					partnersProducts = mapper.readValue(content, tblPartnersProducts.class);
					
					if(partnersProductsService.updatePartnersProduct(partnersProducts)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deletePartnersProducts" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(partnersProductsService.deletePartnersProduct(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}