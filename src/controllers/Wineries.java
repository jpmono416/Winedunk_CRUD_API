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

import models.tblWineries;
import services.WineriesService;

/**
 * Servlet implementation class Wineries
 */
@WebServlet("/wineries")
public class Wineries extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();
	
	@EJB
	WineriesService wineryService = new WineriesService();
    public Wineries() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWineries" :
			{
				try 
				{ 
					//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblWineries> wineries = wineryService.getWineries();
					String arrayToJson = this.mapper.writeValueAsString(wineries);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getWinery" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblWineries winery = wineryService.getWineryById(id);

					if(winery!=null)
					{
						String arrayToJson = this.mapper.writeValueAsString(winery);
						response.getWriter().write(arrayToJson);
					}
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByName":
			{
				/*
				 * aripe 2018-04-02 get a single winery by using just name does not work
				 * we need also countryId, regionId, appellarionId
				 */
				/*
				if(!request.getParameterMap().containsKey("name"))
					return;

				tblWineries winery = this.wineryService.getByName(request.getParameter("name"));
				
				if(winery!=null)
					response.getWriter().write(this.mapper.writeValueAsString(winery));
				return;
			    */
				
				if ( (!request.getParameterMap().containsKey("countryId")) || (!request.getParameterMap().containsKey("regionId"))
				  || (!request.getParameterMap().containsKey("appellationId")) || (!request.getParameterMap().containsKey("name")) ) {
					return;
				}
				Integer countryId;
				try {
					countryId = Integer.parseInt(request.getParameter("countryId"));
				} catch (Exception e){
					countryId = 0;
				}

				Integer regionId;
				try {
					regionId = Integer.parseInt(request.getParameter("regionId"));
				} catch (Exception e){
					regionId = 0;
				}
				
				Integer appellationId;
				try {
					appellationId = Integer.parseInt(request.getParameter("appellationId"));
				} catch (Exception e){
					appellationId = 0;
				}

				tblWineries winery = this.wineryService.getByName(countryId, regionId, appellationId, request.getParameter("name"));
				
				if(winery!=null)
					response.getWriter().write(this.mapper.writeValueAsString(winery));
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
			case "addWinery" :
			{
				try
				{
					tblWineries winery = new tblWineries();
					winery = this.mapper.readValue(content, tblWineries.class);
					Integer id = wineryService.addWinery(winery);
					if(id!=null) { response.getWriter().print(id); }
					else { response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong while inserting the winery "+winery.getName()); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateWinery" :
			{
				try
				{
					tblWineries winery = new tblWineries();
					winery = this.mapper.readValue(content, tblWineries.class);
					
					if(wineryService.updateWinery(winery)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteWinery" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(wineryService.deleteWinery(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}