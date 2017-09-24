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

import models.tblClicks;
import services.ClicksService;

/**
 * Servlet implementation class Clicks
 */
@WebServlet("/clicks")
public class Clicks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ClicksService clickService = new ClicksService();
    public Clicks() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getClicks" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblClicks> clicks = clickService.getClicks();
					String arrayToJson = objectMapper.writeValueAsString(clicks);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getClick" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblClicks click = clickService.getClickById(id);
					String arrayToJson = objectMapper.writeValueAsString(click);
					
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
			case "addClick" :
			{
				try
				{
					tblClicks click = new tblClicks();
					ObjectMapper mapper = new ObjectMapper();
					click = mapper.readValue(content, tblClicks.class);
					
					if(clickService.addClick(click)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateClick" :
			{
				try
				{
					tblClicks click = new tblClicks();
					ObjectMapper mapper = new ObjectMapper();
					click = mapper.readValue(content, tblClicks.class);
					
					if(clickService.updateClick(click)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteClick" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(clickService.deleteClick(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}