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

import models.tblUserPhoneNumbers;
import services.UserPhoneNumbersService;

/**
 * Servlet implementation class UserPhoneNumbers
 */
@WebServlet("/userPhoneNumbers")
public class UserPhoneNumbers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserPhoneNumbersService userPhoneNumberService = new UserPhoneNumbersService();
    public UserPhoneNumbers() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserPhoneNumbers" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserPhoneNumbers> userPhoneNumbers = userPhoneNumberService.getUserPhoneNumbers();
					
					for(tblUserPhoneNumbers number : userPhoneNumbers)
					{
						Integer numericUserId = number.getUserId().getId();
						number.setNumericUserId(numericUserId);
					}
					
					String arrayToJson = objectMapper.writeValueAsString(userPhoneNumbers);
					
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserPhoneNumber" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserPhoneNumbers userPhoneNumber = userPhoneNumberService.getUserPhoneNumberById(id);
					String arrayToJson = objectMapper.writeValueAsString(userPhoneNumber);
					
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
			case "addUserPhoneNumber" :
			{
				try
				{
					tblUserPhoneNumbers userPhoneNumber = new tblUserPhoneNumbers();
					ObjectMapper mapper = new ObjectMapper();
					userPhoneNumber = mapper.readValue(content, tblUserPhoneNumbers.class);
					
					if(userPhoneNumberService.addUserPhoneNumber(userPhoneNumber)) { response.getWriter().println("True"); }
				} catch (Exception e) {e.printStackTrace(); return;}
				break;
			}
			
			case "updateUserPhoneNumber" :
			{
				try
				{
					tblUserPhoneNumbers userPhoneNumber = new tblUserPhoneNumbers();
					ObjectMapper mapper = new ObjectMapper();
					userPhoneNumber = mapper.readValue(content, tblUserPhoneNumbers.class);
					
					if(userPhoneNumberService.updateUserPhoneNumber(userPhoneNumber)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteUserPhoneNumber" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(userPhoneNumberService.deleteUserPhoneNumber(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace() ;return; }
				break;
			}
			
			case "getUserPhoneNumbersForUser" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					List<tblUserPhoneNumbers> phoneNumbers = userPhoneNumberService.getPhoneNumbersForUser(id);
					
					for(tblUserPhoneNumbers number : phoneNumbers)
					{
						Integer numericUserId = number.getUserId().getId();
						number.setNumericUserId(numericUserId);
					}
					
					ObjectMapper mapper = new ObjectMapper();
					String jsonResult = "{ \"PhoneNumbers\" : " + mapper.writeValueAsString(phoneNumbers) + " }";
					
					response.getWriter().write(jsonResult);
				} catch(Exception e) { e.printStackTrace(); return; }
			}
		}
	}

}