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

import models.TblWinesGrapeVariety;
import models.tblGrapeVarieties;
import services.WinesGrapeVarietiesService;

/**
 * Servlet implementation class WinesGrapeVarieties
 */
@WebServlet("/WinesGrapeVarieties")
public class WinesGrapeVarieties extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	WinesGrapeVarietiesService wineGrapeService = new WinesGrapeVarietiesService();

	public WinesGrapeVarieties() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getAll" :
			{
				try 
				{
					List<TblWinesGrapeVariety> grapeVarieties = wineGrapeService.getAll();
					String arrayToJson = this.mapper.writeValueAsString(grapeVarieties);

					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			
			case "getById" :
			{
				try 
				{
					if (!request.getParameterMap().containsKey("id")) 
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id parameter");
						return;
					}
					Integer id = Integer.parseInt(request.getParameter("id"));
		    	
					TblWinesGrapeVariety grapeVariety = wineGrapeService.getById(id);
					
					response.getWriter().write(this.mapper.writeValueAsString(grapeVariety));
				}
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		JsonNode json = this.mapper.readTree(request.getInputStream());
		switch (request.getParameter("action")) 
		{
			case "addNew" :
			{
				try
				{
					tblGrapeVarieties grapeVariety = this.mapper.treeToValue(json, tblGrapeVarieties.class);

					Integer id = wineGrapeService.addNew(grapeVariety);
					if(id!=null) { response.getWriter().write(id); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "update" :
			{
				try
				{
					tblGrapeVarieties grapeVariety = new tblGrapeVarieties();
					grapeVariety = this.mapper.treeToValue(json, tblGrapeVarieties.class);
					
					if(wineGrapeService.update(grapeVariety)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
			
			case "delete" :
			{
				try
				{
					if(wineGrapeService.delete(json.get("id").asInt())) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}