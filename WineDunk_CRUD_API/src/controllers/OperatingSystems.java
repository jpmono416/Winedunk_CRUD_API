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

import models.tblOperatingSystems;
import services.OperatingSystemsService;

/**
 * Servlet implementation class OperatingSystems
 */
@WebServlet("/operatingSystems")
public class OperatingSystems extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	OperatingSystemsService operatingSystemService = new OperatingSystemsService();
    public OperatingSystems() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getOperatingSystems" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblOperatingSystems> operatingSystems = operatingSystemService.getOperatingSystems();
					String arrayToJson = objectMapper.writeValueAsString(operatingSystems);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getOperatingSystem" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblOperatingSystems operatingSystem = operatingSystemService.getOperatingSystemById(id);
					String arrayToJson = objectMapper.writeValueAsString(operatingSystem);
					
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
			case "addOperatingSystem" :
			{
				try
				{
					tblOperatingSystems operatingSystem = new tblOperatingSystems();
					ObjectMapper mapper = new ObjectMapper();
					operatingSystem = mapper.readValue(content, tblOperatingSystems.class);
					
					if(operatingSystemService.addOperatingSystem(operatingSystem)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateOperatingSystem" :
			{
				try
				{
					tblOperatingSystems operatingSystem = new tblOperatingSystems();
					ObjectMapper mapper = new ObjectMapper();
					operatingSystem = mapper.readValue(content, tblOperatingSystems.class);
					
					if(operatingSystemService.updateOperatingSystem(operatingSystem)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteOperatingSystem" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(operatingSystemService.deleteOperatingSystem(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}