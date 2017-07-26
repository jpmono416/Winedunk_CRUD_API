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
import models.tblUserEmails;
import services.UserEmailsService;

/**
 * Servlet implementation class UserEmails
 */
@WebServlet("/userEmails")
public class UserEmails extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserEmailsService userEmailService = new UserEmailsService();
    public UserEmails() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserEmails" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserEmails> userEmails = userEmailService.getUserEmails();
					String arrayToJson = objectMapper.writeValueAsString(userEmails);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserEmail" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserEmails userEmail = userEmailService.getUserEmailById(id);
					String arrayToJson = objectMapper.writeValueAsString(userEmail);
					
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
			case "addUserEmail" :
			{
				try
				{
					tblUserEmails userEmail = new tblUserEmails();
					ObjectMapper mapper = new ObjectMapper();
					userEmail = mapper.readValue(content, tblUserEmails.class);
					
					if(userEmailService.addUserEmail(userEmail)) { response.getWriter().println("True"); }
				} catch (Exception e) {e.printStackTrace(); return;}
				break;
			}
			
			case "updateUserEmail" :
			{
				try
				{
					tblUserEmails userEmail = new tblUserEmails();
					ObjectMapper mapper = new ObjectMapper();
					userEmail = mapper.readValue(content, tblUserEmails.class);
					
					if(userEmailService.updateUserEmail(userEmail)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteUserEmail" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(userEmailService.deleteUserEmail(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace() ;return; }
				break;
			}
			
			case "getUserEmailAddressesForUser" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					List<tblUserEmails> emails = userEmailService.getEmailsForUser(id);
					
					for(tblUserEmails email : emails)
					{
						Integer numericUserId = email.getUserId().getId();
						email.setNumericUserId(numericUserId);
					}
					
					ObjectMapper mapper = new ObjectMapper();
					String jsonResult = "{ \"EmailAddresses\" : " + mapper.writeValueAsString(emails) + " }";
					
					response.getWriter().write(jsonResult);
				} catch (Exception e) { e.printStackTrace() ;return; }
				break;	
			}
		}
	}

}