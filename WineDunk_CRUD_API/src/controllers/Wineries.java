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
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblWineries> wineries = wineryService.getWineries();
					String arrayToJson = objectMapper.writeValueAsString(wineries);
					
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
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblWineries winery = wineryService.getWineryById(id);
					String arrayToJson = objectMapper.writeValueAsString(winery);
					
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
			case "addWinery" :
			{
				try
				{
					System.out.println("Started");
					tblWineries winery = new tblWineries();
					ObjectMapper mapper = new ObjectMapper();
					winery = mapper.readValue(content, tblWineries.class);
					
					if(wineryService.addWinery(winery)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateWinery" :
			{
				try
				{
					tblWineries winery = new tblWineries();
					ObjectMapper mapper = new ObjectMapper();
					winery = mapper.readValue(content, tblWineries.class);
					
					if(wineryService.updateWinery(winery)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteWinery" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(wineryService.deleteWinery(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}