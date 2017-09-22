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

import models.tblPartnersContacts;
import services.PartnersContactsService;

/**
 * Servlet implementation class PartnerContacts
 */
@WebServlet("/partnerContacts")
public class PartnersContacts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PartnersContactsService partnerContactService = new PartnersContactsService();
    public PartnersContacts() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPartnerContacts" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblPartnersContacts> partnerContacts = partnerContactService.getPartnersContacts();
					String arrayToJson = objectMapper.writeValueAsString(partnerContacts);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPartnerContact" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblPartnersContacts partnerContact = partnerContactService.getPartnersContactById(id);
					String arrayToJson = objectMapper.writeValueAsString(partnerContact);
					
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
			case "addPartnerContact" :
			{
				try
				{
					tblPartnersContacts partnerContact = new tblPartnersContacts();
					ObjectMapper mapper = new ObjectMapper();
					partnerContact = mapper.readValue(content, tblPartnersContacts.class);
					
					if(partnerContactService.addPartnersContact(partnerContact)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace();return;}
				break;
			}
			
			case "updatePartnerContact" :
			{
				try
				{
					tblPartnersContacts partnerContact = new tblPartnersContacts();
					ObjectMapper mapper = new ObjectMapper();
					partnerContact = mapper.readValue(content, tblPartnersContacts.class);
					
					if(partnerContactService.updatePartnersContact(partnerContact)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deletePartnerContact" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(partnerContactService.deletePartnersContact(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}