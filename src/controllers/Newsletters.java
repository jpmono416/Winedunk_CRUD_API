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

import models.tblNewsletters;
import services.NewslettersService;

/**
 * Servlet implementation class Newsletters
 */
@WebServlet("/newsletters")
public class Newsletters extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	NewslettersService newsletterService = new NewslettersService();
    public Newsletters() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getNewsletters" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblNewsletters> newsletters = newsletterService.getNewsletters();
					String arrayToJson = objectMapper.writeValueAsString(newsletters);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getNewsletter" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblNewsletters newsletter = newsletterService.getNewsletterById(id);
					String arrayToJson = objectMapper.writeValueAsString(newsletter);
					
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
			case "addNewsletter" :
			{
				try
				{
					tblNewsletters newsletter = new tblNewsletters();
					ObjectMapper mapper = new ObjectMapper();
					newsletter = mapper.readValue(content, tblNewsletters.class);
					
					if(newsletterService.addNewsletter(newsletter)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateNewsletter" :
			{
				try
				{
					tblNewsletters newsletter = new tblNewsletters();
					ObjectMapper mapper = new ObjectMapper();
					newsletter = mapper.readValue(content, tblNewsletters.class);
					
					if(newsletterService.updateNewsletter(newsletter)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteNewsletter" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(newsletterService.deleteNewsletter(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}