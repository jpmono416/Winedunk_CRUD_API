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

import models.tblGrapeVarieties;
import services.GrapeVarietiesService;

/**
 * Servlet implementation class GrapeVarieties
 */
@WebServlet("/grapevarieties")
public class GrapeVarieties extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	GrapeVarietiesService grapeVarietyService = new GrapeVarietiesService();
    public GrapeVarieties() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getGrapeVarieties" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblGrapeVarieties> grapeVarieties = grapeVarietyService.getGrapeVarieties();
					String arrayToJson = objectMapper.writeValueAsString(grapeVarieties);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getGrapeVariety" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblGrapeVarieties grapeVariety = grapeVarietyService.getGrapeVarietyById(id);
					String arrayToJson = objectMapper.writeValueAsString(grapeVariety);
					
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
			case "addGrapeVariety" :
			{
				try
				{
					tblGrapeVarieties grapeVariety = new tblGrapeVarieties();
					ObjectMapper mapper = new ObjectMapper();
					grapeVariety = mapper.readValue(content, tblGrapeVarieties.class);
					
					if(grapeVarietyService.addGrapeVariety(grapeVariety)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateGrapeVariety" :
			{
				try
				{
					tblGrapeVarieties grapeVariety = new tblGrapeVarieties();
					ObjectMapper mapper = new ObjectMapper();
					grapeVariety = mapper.readValue(content, tblGrapeVarieties.class);
					
					if(grapeVarietyService.updateGrapeVariety(grapeVariety)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
			
			case "deleteGrapeVariety" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(grapeVarietyService.deleteGrapeVariety(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}