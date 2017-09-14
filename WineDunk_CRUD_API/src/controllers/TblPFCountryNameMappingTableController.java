package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.TblPFCountryNameMappingTable;
import services.TblPFCountryNameMappingTableService;

/**
 * Servlet implementation class TblPFCountryNameMappingTable
 */
@WebServlet("/TblPFCountryNameMappingTableController")
public class TblPFCountryNameMappingTableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();
	
	@EJB
	TblPFCountryNameMappingTableService countryNameMappingService;
	
    public TblPFCountryNameMappingTableController() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getAll" :
			{
				try 
				{ 
					//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<TblPFCountryNameMappingTable> mappings = countryNameMappingService.getAll();
					String arrayToJson = this.mapper.writeValueAsString(mappings);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			
			case "getById" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
			    	//Set pretty printing of json
					this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					TblPFCountryNameMappingTable mapping = countryNameMappingService.getById(id);
					String arrayToJson = this.mapper.writeValueAsString(mapping);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			case "getByName":
			{
				if(!request.getParameterMap().containsKey("name"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter name missing");
					return;
				}

				try {
					TblPFCountryNameMappingTable mapping = this.countryNameMappingService.getByName(request.getParameter("name"));
					response.getWriter().write(this.mapper.writeValueAsString(mapping));
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
	    
		String action = request.getParameter("action");
		JsonNode json = this.mapper.readTree(request.getInputStream());
		switch (action) 
		{
			case "addTblPFCountryNameMappingTable" :
			{
				try
				{
					response.getWriter().write(this.countryNameMappingService.addMapping(json.get("countryId").asInt(), json.get("name").asText()));
				} catch (Exception e) {
					e.printStackTrace();
				}
					 
				return;
			}
			case "deleteTblPFCountryNameMappingTable" :
			{
				try
				{
					if(countryNameMappingService.deleteMappingById(json.get("id").asInt())) { response.getWriter().println(true); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}