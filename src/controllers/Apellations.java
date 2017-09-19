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

import models.tblAppellations;
import services.ApellationsService;
import services.DefaultServiceClass;

/**
 * Servlet implementation class Apellations
 */
@WebServlet("/appellations")
public class Apellations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ApellationsService apellationService = new ApellationsService();
	
	@EJB
	DefaultServiceClass defaultService = new DefaultServiceClass();
	
    public Apellations() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getAppellations" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblAppellations> apellations = apellationService.getApellations();
					String arrayToJson = objectMapper.writeValueAsString(apellations);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getAppellation" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblAppellations apellation = apellationService.getApellationById(id);
					String arrayToJson = objectMapper.writeValueAsString(apellation);
					
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
			case "addAppellation" :
			{
				try
				{
					/*
					tblAppellations appellation = new tblAppellations();
					StringBuilder sb = new StringBuilder();
				    BufferedReader reader = request.getReader();
				    String line;
			        
				    while ((line = reader.readLine()) != null) 
			        { sb.append(line).append('\n'); }
				    reader.close();
				    
					defaultService.addRecord("tblAppellations", sb.toString()); //TODO 
					
					if(apellationService.addApellation(appellation)) { response.getWriter().println("True"); }
					*/
				} catch (Exception e) {return;}
					 
				break;
			}
			
			case "updateAppellation" :
			{
				try
				{
					tblAppellations apellation = new tblAppellations();
					ObjectMapper mapper = new ObjectMapper();

					apellation = mapper.readValue(content, tblAppellations.class);
					
					if(apellationService.updateApellation(apellation)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteAppellation" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(apellationService.deleteApellation(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}