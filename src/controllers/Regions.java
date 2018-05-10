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

import models.tblRegions;
import models.tblWineries;
import services.RegionsService;

/**
 * Servlet implementation class Regions
 */
@WebServlet("/regions")
public class Regions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();

	@EJB
	RegionsService regionService = new RegionsService();
    public Regions() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getRegions" :
			{
				try 
				{ 
			    	//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblRegions> regions = regionService.getRegions();
					String arrayToJson = this.mapper.writeValueAsString(regions);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			
			case "getRegion" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
			    	//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblRegions region = regionService.getRegionById(id);
					String arrayToJson = mapper.writeValueAsString(region);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			
			case "getRegionNameById" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					tblRegions region = regionService.getRegionById(id);
					if ( (region != null) && (region.getId() == id) ) {
						response.setStatus(200);
						response.getWriter().write(region.getName());	
					} else {
						response.setStatus(204);
						response.getWriter().write("");
					}
				}
				catch (Exception e) { 
					response.setStatus(500);
					response.getWriter().write("");
					e.printStackTrace(); 
				}
				break;
			}
			
			case "getByName":
			{
				if(!request.getParameterMap().containsKey("name"))
					return;

				tblRegions region = this.regionService.getRegionByName(request.getParameter("name"));

				if(region!=null)
					response.getWriter().write(this.mapper.writeValueAsString(region));
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
			case "addRegion" :
			{
				try
				{
					tblRegions region = this.mapper.readValue(content, tblRegions.class);
					Integer id = regionService.addRegion(region);
					if(id!=null) { response.getWriter().print(id); }
					else { response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong inserting the region "+region.getName()); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateRegion" :
			{
				try
				{
					tblRegions region = new tblRegions();
					region = this.mapper.readValue(content, tblRegions.class);
					
					if(regionService.updateRegion(region)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteRegion" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(regionService.deleteRegion(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}