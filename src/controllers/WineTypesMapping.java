package controllers;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.TblPFWineTypeMappingTable;
import services.WineTypesMappingService;

/**
 * Servlet implementation class WineTypesMapping
 */
@WebServlet("/WineTypesMapping")
public class WineTypesMapping extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ObjectMapper mapper = new ObjectMapper();

	@EJB
	WineTypesMappingService service;

	public WineTypesMapping() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String[]> requestMap = request.getParameterMap();
		if(!requestMap.containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action parameter");
			return;
		}
		
		switch(request.getParameter("action"))
		{
			case "getByWineType":
				if(!requestMap.containsKey("type"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing type parameter");
					return;
				}

				TblPFWineTypeMappingTable wineType = service.getByWineType(request.getParameter("type"));

				if(wineType!=null)
				{
					response.getWriter().print(mapper.writeValueAsString(wineType.getWineType()));
				}

				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
