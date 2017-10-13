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

import models.tblPartnersProducts;
import services.PartnersProductsService;

/**
 * Servlet implementation class PartnersProductss
 */
@WebServlet("/partnersProductss")
public class PartnersProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();
	
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
			    	//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblPartnersProducts> partnersProductss = partnersProductsService.getPartnersProducts();
					String arrayToJson = this.mapper.writeValueAsString(partnersProductss);

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

					//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblPartnersProducts partnersProducts = partnersProductsService.getPartnersProductById(id);
					String arrayToJson = this.mapper.writeValueAsString(partnersProducts);

					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByPartnerProductIdAndMerchantProductId":
			{
				for(String parameter : new String[] {"partnerProductId", "merchantProductId"})
				{
					if(!request.getParameterMap().containsKey(parameter))
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing "+parameter);
						return;
					}
				}

				tblPartnersProducts result = partnersProductsService.getByPartnerProductIdAndMerchantProductId(request.getParameter("partnerProductId"), request.getParameter("merchantProductId"));
				response.getWriter().write(this.mapper.writeValueAsString(result));
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		switch (request.getParameter("action")) 
		{
			case "addPartnersProducts" :
				try
				{
					tblPartnersProducts partnersProducts = this.mapper.readValue(request.getInputStream(), tblPartnersProducts.class);

					Integer id = partnersProductsService.addPartnersProduct(partnersProducts);
					if(id != null) { response.getWriter().print(id); }
				} catch (Exception e) {e.printStackTrace(); return;}
				break;

			case "updatePartnersProducts" :
				try
				{
					tblPartnersProducts partnersProducts = this.mapper.readValue(request.getInputStream(), tblPartnersProducts.class);
					
					if(partnersProductsService.updatePartnersProduct(partnersProducts) != null) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;

			case "deletePartnersProducts" :
				try
				{
					JsonNode json = this.mapper.readTree(request.getInputStream());

					if(partnersProductsService.deletePartnersProduct(json.get("id").asInt())) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
		}
	}

}