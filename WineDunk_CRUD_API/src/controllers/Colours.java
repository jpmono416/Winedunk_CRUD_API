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

import models.tblColours;
import services.ColoursService;

/**
 * Servlet implementation class Colours
 */
@WebServlet("/colours")
public class Colours extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();

	@EJB
	ColoursService colourService = new ColoursService();
    public Colours() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getColours" :
			{
				try 
				{ 
					//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblColours> colours = colourService.getColours();
					String arrayToJson = this.mapper.writeValueAsString(colours);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getColour" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblColours colour = colourService.getColourById(id);
					String arrayToJson = this.mapper.writeValueAsString(colour);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByName":
			{
				if(!request.getParameterMap().containsKey("name"))
					return;

				tblColours colour= this.colourService.getByName(request.getParameter("name"));
				
				response.getWriter().write(this.mapper.writeValueAsString(colour));
				return;
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
			case "addColour" :
			{
				try
				{
					tblColours colour = new tblColours();
					colour = this.mapper.readValue(content, tblColours.class);
					
					if(colourService.addColour(colour)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateColour" :
			{
				try
				{
					tblColours colour = new tblColours();
					colour = this.mapper.readValue(content, tblColours.class);
					
					if(colourService.updateColour(colour)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteColour" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(colourService.deleteColour(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}