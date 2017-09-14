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

import models.TblWinesWineType;
import services.TblWinesWineTypesService;

/**
 * Servlet implementation class TblWinesWineTypes
 */
@WebServlet("/TblWinesWineTypes")
public class TblWinesWineTypes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();
       
    @EJB
    TblWinesWineTypesService tblWinesWineTypesService;

    public TblWinesWineTypes() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getTblWinesWineTypes" :
			{
				try 
				{ 
					//Set pretty printing of json
			    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);

					List<TblWinesWineType> tblWinesWineTypes= this.tblWinesWineTypesService.getTblWinesWineTypes();
					String arrayToJson = this.mapper.writeValueAsString(tblWinesWineTypes);
					
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
		    	
					TblWinesWineType tblWinesWineType = tblWinesWineTypesService.getTblWinesWineTypeById(id);
					String arrayToJson = this.mapper.writeValueAsString(tblWinesWineType);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				return;
			}
			case "getByWineIdAndWineTypeId":
			{
				for(String parameter : new String[] {"wineId", "wineTypeId"})
				{
					if(!request.getParameterMap().containsKey(parameter))
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter "+parameter+" missing");
						return;
					}
				}
				TblWinesWineType winesWineType = this.tblWinesWineTypesService.getTblWinesWineTypeByWineIdAndWineTypeId(Integer.valueOf(request.getParameter("wineId")), 
																					   									Integer.valueOf(request.getParameter("wineTypeId")));
				
				response.getWriter().write(this.mapper.writeValueAsString(winesWineType));
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		JsonNode json = this.mapper.readTree(request.getInputStream());
		switch (request.getParameter("action")) 
		{
			case "addTblWinesWineType" :
			{
				try
				{
					TblWinesWineType tblWinesWineType = this.mapper.treeToValue(json, TblWinesWineType.class);

					response.getWriter().write(this.tblWinesWineTypesService.addTblWinesWineType(tblWinesWineType));
				} catch (Exception e) {
					e.printStackTrace();
				}
					 
				return;
			}
			
			case "updateTblWinesWineType" :
			{
				try
				{
					TblWinesWineType tblWinesWineType = this.mapper.treeToValue(json, TblWinesWineType.class);
					
					if(tblWinesWineTypesService.updateTblWinesWineType(tblWinesWineType)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteTblWinesWineType" :
			{
				try
				{
					if(tblWinesWineTypesService.deleteTblWinesWineType(json.get("id").asInt())) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}