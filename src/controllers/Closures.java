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

import models.tblClosures;
import services.ClosuresService;
import services.DefaultServiceClass;

/**
 * Servlet implementation class Closures
 */
@WebServlet("/closures")
public class Closures extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper mapper = new ObjectMapper();

	@EJB
	ClosuresService closureService = new ClosuresService();
	@EJB
	DefaultServiceClass defaultService = new DefaultServiceClass();
	
    public Closures() { super(); }
    
    String className = "tblClosures";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getByName":
			{
				if(!request.getParameterMap().containsKey("name"))
					return;
	
				tblClosures closure = this.closureService.getByName(request.getParameter("name"));
				
				response.getWriter().write(this.mapper.writeValueAsString(closure));
				return;
			}
		}
		//TODO 
		/*
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
		
			case "getClosures" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<Object> closures = defaultService.getList(className);
					String arrayToJson = objectMapper.writeValueAsString(closures);
					
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getClosure" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					Object closure = closureService.getClosureById(id);
					String arrayToJson = objectMapper.writeValueAsString(closure);
					
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		}
		*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		if(!request.getParameterMap().containsKey("action")) { return; }

		JsonNode json = this.mapper.readTree(request.getInputStream());
		switch (request.getParameter("action")) 
		{
			case "addClosure" :
			{
				tblClosures closure = this.mapper.treeToValue(json, tblClosures.class);
				Integer id = this.closureService.addClosure(closure);
				if(id!=null)
					response.getWriter().print(id);
				else
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong while inserting a closure "+closure.getName());
				
				return;
			}
		}
	}
}