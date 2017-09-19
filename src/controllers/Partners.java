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

import models.tblPartners;
import services.PartnersService;

/**
 * Servlet implementation class Partners
 */
@WebServlet("/partners")
public class Partners extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PartnersService partnerService = new PartnersService();
    public Partners() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPartners" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblPartners> partners = partnerService.getPartners();
					String arrayToJson = objectMapper.writeValueAsString(partners);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPartner" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblPartners partner = partnerService.getPartnerById(id);
					String arrayToJson = objectMapper.writeValueAsString(partner);
					
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
			case "addPartner" :
			{
				try
				{
					tblPartners partner = new tblPartners();
					ObjectMapper mapper = new ObjectMapper();
					partner = mapper.readValue(content, tblPartners.class);
					
					if(partnerService.addPartner(partner)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updatePartner" :
			{
				try
				{
					tblPartners partner = new tblPartners();
					ObjectMapper mapper = new ObjectMapper();
					partner = mapper.readValue(content, tblPartners.class);
					
					if(partnerService.updatePartner(partner)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deletePartner" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(partnerService.deletePartner(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}