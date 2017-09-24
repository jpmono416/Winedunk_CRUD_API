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

import models.tblTimeZones;
import services.TimeZonesService;

/**
 * Servlet implementation class TimeZones
 */
@WebServlet("/timezones")
public class TimeZones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	TimeZonesService timeZoneService = new TimeZonesService();
    public TimeZones() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getTimeZones" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblTimeZones> timeZones = timeZoneService.getTimeZones();
					String arrayToJson = objectMapper.writeValueAsString(timeZones);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getTimeZone" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblTimeZones timeZone = timeZoneService.getTimeZoneById(id);
					String arrayToJson = objectMapper.writeValueAsString(timeZone);
					
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
			case "addTimeZone" :
			{
				try
				{
					tblTimeZones timeZone = new tblTimeZones();
					ObjectMapper mapper = new ObjectMapper();
					timeZone = mapper.readValue(content, tblTimeZones.class);
					
					if(timeZoneService.addTimeZone(timeZone)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateTimeZone" :
			{
				try
				{
					tblTimeZones timeZone = new tblTimeZones();
					ObjectMapper mapper = new ObjectMapper();
					timeZone = mapper.readValue(content, tblTimeZones.class);
					
					if(timeZoneService.updateTimeZone(timeZone)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteTimeZone" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(timeZoneService.deleteTimeZone(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}