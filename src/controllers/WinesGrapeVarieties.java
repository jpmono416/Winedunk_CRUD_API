package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.TblWinesGrapeVariety;
import models.tblGrapeVarieties;
import models.tblWines;
import services.GrapeVarietiesService;
import services.WinesGrapeVarietiesService;
import services.WinesService;

/**
 * Servlet implementation class WinesGrapeVarieties
 */
@WebServlet("/WinesGrapeVarieties")
public class WinesGrapeVarieties extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	private WinesGrapeVarietiesService service;
	@EJB
	private WinesService winesService;
	@EJB
	private GrapeVarietiesService grapesService;

	public WinesGrapeVarieties() {
        super();

        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter");
			return;
		}

		switch(request.getParameter("action"))
		{
			case "getAll":
			{
				response.getWriter().print(this.mapper.writeValueAsString(this.service.getAll()));
				return;
			}	
			case "getByWineAndGrape":
			{
				JsonNode json = this.mapper.readTree(request.getInputStream());

				tblWines wine = this.winesService.getWineById(json.get("id").asInt());
				tblGrapeVarieties grape = this.grapesService.getGrapeVarietyById(json.get("grapeId").asInt());

				response.getWriter().println(this.mapper.writeValueAsString(this.service.getByWineAndGrape(wine, grape)));
				return;
			}
			case "addNew":
			{
				TblWinesGrapeVariety winesGrapeVariety = this.mapper.readValue(request.getInputStream(), TblWinesGrapeVariety.class);
				Integer id = service.addNew(winesGrapeVariety);
				if(id!=null)
					response.getWriter().print(id);
				else
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Something went wrong adding a new TblWinesGrapeVariety");
				return;
			}
			case "removeById":
			{
				TblWinesGrapeVariety winesGrapeVariety = this.mapper.readValue(request.getInputStream(), TblWinesGrapeVariety.class);
				this.service.remove(winesGrapeVariety);
				return;
			}
		}
	}

}
