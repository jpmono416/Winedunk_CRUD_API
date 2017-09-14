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

import models.tblPartnersTypes;
import services.PartnersTypesService;

/**
 * Servlet implementation class PartnerTypes
 */
@WebServlet("/partnerTypes")
public class PartnersTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	PartnersTypesService partnerTypeService = new PartnersTypesService();
    public PartnersTypes() { super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(!request.getParameterMap().containsKey("action")) { response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action"); return; }

		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPartnerTypes" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

					List<tblPartnersTypes> partnerTypes = partnerTypeService.getPartnerTypes();
					String arrayToJson = objectMapper.writeValueAsString(partnerTypes);

					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); }
				break;
			}

			case "getPartnerType" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));

					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

			    	tblPartnersTypes partnerType = partnerTypeService.getPartnerTypeById(id);
					String arrayToJson = objectMapper.writeValueAsString(partnerType);

					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); }
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action"); return; }
		
		switch (request.getParameter("action")) 
		{
			case "addPartnerType" :
			{
				try
				{
					tblPartnersTypes partnerType = mapper.readValue(request.getInputStream(), tblPartnersTypes.class);

					if(partnerTypeService.addPartnerType(partnerType)) { response.getWriter().println("True"); }
				} catch (Exception e) { response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); return;}
				break;
			}
			
			case "updatePartnerType" :
			{
				try
				{
					tblPartnersTypes partnerType = mapper.readValue(request.getInputStream(), tblPartnersTypes.class);

					if(partnerTypeService.updatePartnerType(partnerType)) { response.getWriter().println("True"); }
				} catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); return;}
				break;
			}
			
			case "deletePartnerType" :
			{
			    JsonNode json = this.mapper.readTree(request.getInputStream());
				try
				{
					if(partnerTypeService.deletePartnerType(json.get("id").asInt())) { response.getWriter().println("True"); }
				} catch (Exception e) {response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); e.printStackTrace(); return; }
				break;
			}
		}
	}
}