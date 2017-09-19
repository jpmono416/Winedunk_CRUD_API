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

import models.tblBrowsers;
import models.tblDevices;
import services.BrowsersService;

/**
 * Servlet implementation class Browsers
 */
@WebServlet("/browsers")
public class Browsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	BrowsersService browserService = new BrowsersService();
    public Browsers() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getBrowsers" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblBrowsers> browsers = browserService.getBrowsers();
					String arrayToJson = objectMapper.writeValueAsString(browsers);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getBrowser" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblBrowsers browser = browserService.getBrowserById(id);
					String arrayToJson = objectMapper.writeValueAsString(browser);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getDevices" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblDevices> devices = browserService.getBrowserById(id).getDevices();
					String arrayToJson = objectMapper.writeValueAsString(devices);
					
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
			case "addBrowser" :
			{
				try
				{
					tblBrowsers browser = new tblBrowsers();
					
					ObjectMapper mapper = new ObjectMapper();
					browser = mapper.readValue(content, tblBrowsers.class);
					
					if(browserService.addBrowser(browser)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateBrowser" :
			{
				try
				{
					tblBrowsers browser = new tblBrowsers();
					ObjectMapper mapper = new ObjectMapper();
					browser = mapper.readValue(content, tblBrowsers.class);
					
					if(browserService.updateBrowser(browser)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteBrowser" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(browserService.deleteBrowser(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}