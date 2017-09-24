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

import models.tblPlatforms;
import services.PlatformsService;

/**
 * Servlet implementation class Platforms
 */
@WebServlet("/platforms")
public class Platforms extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	PlatformsService platformService = new PlatformsService();
    public Platforms() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPlatforms" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblPlatforms> platforms = platformService.getPlatforms();
					String arrayToJson = objectMapper.writeValueAsString(platforms);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPlatform" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblPlatforms platform = platformService.getPlatformById(id);
					String arrayToJson = objectMapper.writeValueAsString(platform);
					
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
			case "addPlatform" :
			{
				try
				{
					tblPlatforms platform = new tblPlatforms();
					ObjectMapper mapper = new ObjectMapper();
					platform = mapper.readValue(content, tblPlatforms.class);
					
					if(platformService.addPlatform(platform)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updatePlatform" :
			{
				try
				{
					tblPlatforms platform = new tblPlatforms();
					ObjectMapper mapper = new ObjectMapper();
					platform = mapper.readValue(content, tblPlatforms.class);
					
					if(platformService.updatePlatform(platform)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deletePlatform" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(platformService.deletePlatform(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}