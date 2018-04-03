package controllers; 

import java.io.IOException; 
 
import javax.ejb.EJB; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
import com.fasterxml.jackson.databind.JsonNode; 
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.SerializationFeature; 
 
import models.Tblpfproduct; 
import services.ProductsService; 
 
/** 
 * Servlet implementation class Products 
 */ 
@WebServlet("/Products") 
public class PFProducts extends HttpServlet { 
	private static final long serialVersionUID = 1L; 
	private final ObjectMapper mapper = new ObjectMapper(); 

	@EJB 
	ProductsService productsService; 

	public PFProducts() { 
        super(); 
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT); 
    } 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		if(!request.getParameterMap().containsKey("action")) 
		{ 
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action"); 
    		return; 
		} 

		JsonNode json = this.mapper.readTree(request.getInputStream()); 
		switch(request.getParameter("action")) 
    	{ 
			case "getAll": 
				response.getWriter().write(this.mapper.writeValueAsString(this.productsService.getAll())); 
				return; 
			case "getByPfId": 
				 
				response.getWriter().write(this.mapper.writeValueAsString(this.productsService.findByTblpf(json.get("id").asInt()))); 
				return; 
			case "findByPartnerProductIdAndMerchantProductId": 
				response.getWriter().write(this.mapper.writeValueAsString(this.productsService.findByPartnerIdAndMerchantId(json.get("partnerProductId").asText(), json.get("merchantProductId").asText()))); 
				return; 
				
			// aripe 2018-03-31, findByPartnerIdAndPartnerProductId added
			case "findByPartnerIdAndPartnerProductId": 
				response.getWriter().write(this.mapper.writeValueAsString(this.productsService.findByPartnerIdAndPartnerProductId(json.get("partnerId").asInt(), json.get("partnerProductId").asText()))); 
				return; 
				
			case "deleteProduct": 
				if(this.productsService.deleteProduct(json.get("id").asInt())) 
					response.getWriter().write("true"); 
				return; 
			case "updateProduct": 
				this.productsService.updateProduct(this.mapper.treeToValue(json, Tblpfproduct.class)); 
				response.getWriter().write("true"); 
				return; 
			case "addProduct":
				Integer id = this.productsService.addProduct(this.mapper.treeToValue(json, Tblpfproduct.class)); 
 
				response.getWriter().print(id); 
				return;
    	} 
	} 
} 