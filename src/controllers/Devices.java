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
import services.DevicesService;

/**
 * Servlet implementation class Devices
 */
@WebServlet("/devices")
public class Devices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	DevicesService deviceService = new DevicesService();
    public Devices() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getDevices" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblDevices> devices = deviceService.getDevices();
					String arrayToJson = objectMapper.writeValueAsString(devices);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getDevice" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblDevices device = deviceService.getDeviceById(id);
					String arrayToJson = objectMapper.writeValueAsString(device);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getBrowsers" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblBrowsers> browsers = deviceService.getDeviceById(id).getBrowsers();
					String arrayToJson = objectMapper.writeValueAsString(browsers);
					
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
			case "addDevice" :
			{
				try
				{
					tblDevices device = new tblDevices();
					ObjectMapper mapper = new ObjectMapper();
					device = mapper.readValue(content, tblDevices.class);
					
					if(deviceService.addDevice(device)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateDevice" :
			{
				try
				{
					tblDevices device = new tblDevices();
					ObjectMapper mapper = new ObjectMapper();
					device = mapper.readValue(content, tblDevices.class);
					
					if(deviceService.updateDevice(device)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteDevice" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(deviceService.deleteDevice(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}