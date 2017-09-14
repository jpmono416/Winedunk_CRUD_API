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

import models.tblWineTypes;
import services.WineTypesService;

/**
 * Servlet implementation class WineTypes
 */
@WebServlet("/winetypes")
public class WineTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	WineTypesService wineTypeService = new WineTypesService();
    public WineTypes() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWineTypes" :
			{
				try 
				{
			    	//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblWineTypes> wineTypes = wineTypeService.getWineTypes();
					String arrayToJson = this.mapper.writeValueAsString(wineTypes);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getWineType" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));

			    	//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblWineTypes wineType = wineTypeService.getWineTypeById(id);
					String arrayToJson = this.mapper.writeValueAsString(wineType);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			case "getByName":
			{
				if(!request.getParameterMap().containsKey("name"))
					return;

				tblWineTypes wineType = this.wineTypeService.getByName(request.getParameter("name"));
				
				response.getWriter().write(this.mapper.writeValueAsString(wineType));
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
			case "addWineType" :
				try
				{
					tblWineTypes wineType = new tblWineTypes();
					wineType = this.mapper.readValue(content, tblWineTypes.class);

					wineType = wineTypeService.addWineType(wineType);
					if(wineType!=null) { response.getWriter().println(wineType.getId()); }
				} catch (Exception e) {return;}
			break;
			
			case "updateWineType" :
				try
				{
					tblWineTypes wineType = new tblWineTypes();
					wineType = this.mapper.readValue(content, tblWineTypes.class);
					
					if(wineTypeService.updateWineType(wineType)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
			break;
			
			case "deleteWineType" :
				try
				{
					Integer id = Integer.parseInt(content);
					if(wineTypeService.deleteWineType(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
			break;
		}
	}

}