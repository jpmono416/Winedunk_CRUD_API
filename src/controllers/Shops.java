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

import models.tblShops;
import services.ShopsService;

/**
 * Servlet implementation class Shops
 */
@WebServlet("/shops")
public class Shops extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ShopsService shopService = new ShopsService();
    public Shops() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getShops" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblShops> shops = shopService.getShops();
					String arrayToJson = objectMapper.writeValueAsString(shops);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getShop" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblShops shop = shopService.getShopById(id);
					String arrayToJson = objectMapper.writeValueAsString(shop);
					
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
			case "addShop" :
			{
				try
				{
					tblShops shop = new tblShops();
					ObjectMapper mapper = new ObjectMapper();
					shop = mapper.readValue(content, tblShops.class);
					
					if(shopService.addShop(shop)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateShop" :
			{
				try
				{
					tblShops shop = new tblShops();
					ObjectMapper mapper = new ObjectMapper();
					shop = mapper.readValue(content, tblShops.class);
					
					if(shopService.updateShop(shop)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteShop" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(shopService.deleteShop(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}