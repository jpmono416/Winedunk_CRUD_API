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

import models.tblDeviceTypes;
import services.DeviceTypesService;

/**
 * Servlet implementation class DeviceTypes
 */
@WebServlet("/deviceTypes")
public class DeviceTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	DeviceTypesService deviceTypeService = new DeviceTypesService();
    public DeviceTypes() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getDeviceTypes" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblDeviceTypes> deviceTypes = deviceTypeService.getDeviceTypes();
					String arrayToJson = objectMapper.writeValueAsString(deviceTypes);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getDeviceType" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblDeviceTypes deviceType = deviceTypeService.getDeviceTypeById(id);
					String arrayToJson = objectMapper.writeValueAsString(deviceType);
					
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
			case "addDeviceType" :
			{
				try
				{
					tblDeviceTypes deviceType = new tblDeviceTypes();
					ObjectMapper mapper = new ObjectMapper();
					deviceType = mapper.readValue(content, tblDeviceTypes.class);
					
					if(deviceTypeService.addDeviceType(deviceType)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateDeviceType" :
			{
				try
				{
					tblDeviceTypes deviceType = new tblDeviceTypes();
					ObjectMapper mapper = new ObjectMapper();
					deviceType = mapper.readValue(content, tblDeviceTypes.class);
					
					if(deviceTypeService.updateDeviceType(deviceType)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteDeviceType" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(deviceTypeService.deleteDeviceType(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}