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

import models.tblGrapeVarieties;
import services.GrapeVarietiesService;

/**
 * Servlet implementation class GrapeVarieties
 */
@WebServlet("/grapevarieties")
public class GrapeVarieties extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	GrapeVarietiesService grapeVarietyService = new GrapeVarietiesService();

	public GrapeVarieties() {
		super();
		// Set pretty printing of json
		this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getGrapeVarieties" :
			{
				try 
				{
					List<tblGrapeVarieties> grapeVarieties = grapeVarietyService.getGrapeVarieties();
					String arrayToJson = this.mapper.writeValueAsString(grapeVarieties);

					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			
			case "getGrapeVariety" :
			{
				try 
				{
					if (!request.getParameterMap().containsKey("id")) 
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
						return;
					}
					Integer id = Integer.parseInt(request.getParameter("id"));
		    	
					tblGrapeVarieties grapeVariety = grapeVarietyService.getGrapeVarietyById(id);
					
					response.getWriter().write(this.mapper.writeValueAsString(grapeVariety));
				}
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			case "getByName":
			{
				if(!request.getParameterMap().containsKey("name"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing name parameter");
					return;
				}
				tblGrapeVarieties grapeVariety = this.grapeVarietyService.getByName(request.getParameter("name"));
				response.getWriter().write(this.mapper.writeValueAsString(grapeVariety));
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		JsonNode json = this.mapper.readTree(request.getInputStream());
		switch (request.getParameter("action")) 
		{
			case "addGrapeVariety" :
			{
				try
				{
					tblGrapeVarieties grapeVariety = this.mapper.treeToValue(json, tblGrapeVarieties.class);
					
					if(grapeVarietyService.addGrapeVariety(grapeVariety)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "updateGrapeVariety" :
			{
				try
				{
					tblGrapeVarieties grapeVariety = new tblGrapeVarieties();
					grapeVariety = this.mapper.treeToValue(json, tblGrapeVarieties.class);
					
					if(grapeVarietyService.updateGrapeVariety(grapeVariety)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
			
			case "deleteGrapeVariety" :
			{
				try
				{
					if(grapeVarietyService.deleteGrapeVariety(json.get("id").asInt())) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}