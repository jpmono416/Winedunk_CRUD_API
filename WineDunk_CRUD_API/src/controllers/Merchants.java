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

import models.tblShops;
import services.MerchantsService;

/**
 * Servlet implementation class Merchants
 */
@WebServlet("/Merchants")
public class Merchants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper(); 

	@EJB
	private MerchantsService merchantsService;
	
	public Merchants() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	if(!request.getParameterMap().containsKey("action"))
    	{
    		System.out.println("Missing action");
    		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
    		return;
    	}

    	JsonNode json = this.mapper.readTree(request.getInputStream());
    	switch(request.getParameter("action"))
    	{
    		case "getAll":
    			response.getWriter().write(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.merchantsService.getAll()));
    			return;
    		case "getByName":
    			response.getWriter().write(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.merchantsService.getByName(json.get("name").asText())));
    			return;
    		case "addMerchant":
    			response.getWriter().write(this.merchantsService.addMerchant(this.mapper.treeToValue(json, tblShops.class)));
    			return;
    		case "removeMerchant":
    			response.getWriter().write(this.merchantsService.removeMerchant(json.get("id").asInt()).toString());
    			return;
    	}
    }

}
