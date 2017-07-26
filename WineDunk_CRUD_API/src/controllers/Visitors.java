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

import models.tblVisitors;
import services.VisitorsService;

/**
 * Servlet implementation class Visitors
 */
@WebServlet("/visitors")
public class Visitors extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	VisitorsService visitorService = new VisitorsService();
    public Visitors() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getVisitors" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblVisitors> visitors = visitorService.getVisitors();
					String arrayToJson = objectMapper.writeValueAsString(visitors);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getVisitor" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblVisitors visitor = visitorService.getVisitorById(id);
					String arrayToJson = objectMapper.writeValueAsString(visitor);
					
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
			case "addVisitor" :
			{
				try
				{
					tblVisitors visitor = new tblVisitors();
					ObjectMapper mapper = new ObjectMapper();
					visitor = mapper.readValue(content, tblVisitors.class);
					
					if(visitorService.addVisitor(visitor)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateVisitor" :
			{
				try
				{
					tblVisitors visitor = new tblVisitors();
					ObjectMapper mapper = new ObjectMapper();
					visitor = mapper.readValue(content, tblVisitors.class);
					
					if(visitorService.updateVisitor(visitor)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteVisitor" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(visitorService.deleteVisitor(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}