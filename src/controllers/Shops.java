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

import models.tblShops;
import services.ShopsService;

/**
 * Servlet implementation class Shops
 */
@WebServlet("/shops")
public class Shops extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();
	@EJB
	ShopsService shopService = new ShopsService();
    public Shops() {
    	super();

    	//Set pretty printing of json
    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) {
			case "getShops" : {
				try 
				{
					List<tblShops> shops = shopService.getShops();
					String arrayToJson = this.mapper.writeValueAsString(shops);

					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getShop" : {
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
		    	
					tblShops shop = shopService.getShopById(id);
					String arrayToJson = this.mapper.writeValueAsString(shop);

					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByName":
			{
				if(!request.getParameterMap().containsKey("name")) { return; }

				tblShops shop = shopService.getByName(request.getParameter("name"));
				response.getWriter().write(this.mapper.writeValueAsString(shop));
				break;
			}
			default:
			{
				System.out.println("Unrecognized GET request "+action);
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unrecognized action "+request.getParameter("action"));
				break;
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		JsonNode json = this.mapper.readTree(request.getInputStream());
		switch (request.getParameter("action")) {
			case "addShop" : {
				try
				{
					tblShops shop = new tblShops();
					shop = this.mapper.treeToValue(json, tblShops.class);
					
					if(shopService.addShop(shop)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateShop" : {
				try
				{
					tblShops shop = new tblShops();
					shop = this.mapper.treeToValue(json, tblShops.class);
					
					if(shopService.updateShop(shop)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteShop" : {
				try
				{
					if(shopService.deleteShop(json.get("id").asInt())) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
			
			default: {
				System.out.println("Unrecognized GET request "+request.getParameter("action"));
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unrecognized action "+request.getParameter("action"));

				break;
			}
		}
	}

}