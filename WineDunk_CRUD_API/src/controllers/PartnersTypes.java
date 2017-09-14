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

import models.tblPartnersTypes;
import services.PartnersTypesService;

/**
 * Servlet implementation class PartnerTypes
 */
@WebServlet("/partnerTypes")
public class PartnersTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PartnersTypesService partnerTypeService = new PartnersTypesService();
    public PartnersTypes() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPartnerTypes" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblPartnersTypes> partnerTypes = partnerTypeService.getPartnerTypes();
					String arrayToJson = objectMapper.writeValueAsString(partnerTypes);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPartnerType" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	tblPartnersTypes partnerType = partnerTypeService.getPartnerTypeById(id);
					String arrayToJson = objectMapper.writeValueAsString(partnerType);
					
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
			case "addPartnerType" :
			{
				try
				{
					tblPartnersTypes partnerType = new tblPartnersTypes();
					ObjectMapper mapper = new ObjectMapper();
					partnerType = mapper.readValue(content, tblPartnersTypes.class);
					
					if(partnerTypeService.addPartnerType(partnerType)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updatePartnerType" :
			{
				try
				{
					tblPartnersTypes partnerType = new tblPartnersTypes();
					ObjectMapper mapper = new ObjectMapper();
					partnerType = mapper.readValue(content, tblPartnersTypes.class);
					
					if(partnerTypeService.updatePartnerType(partnerType)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "deletePartnerType" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(partnerTypeService.deletePartnerType(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); return; }
				break;
			}
		}
	}

}