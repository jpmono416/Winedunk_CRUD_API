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

import models.Tblpf;
import services.ProductFeedsService;

/**
 * Servlet implementation class ProductFeeds
 */
@WebServlet("/ProductFeeds")
public class ProductFeeds extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	ProductFeedsService pfService;

    public ProductFeeds() {
        super();
    }

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
    		return;
		}

		JsonNode json = this.mapper.readTree(request.getInputStream());
		switch(request.getParameter("action"))
		{
			case "getAll":
				response.getWriter().write(this.mapper.writeValueAsString(this.pfService.getAll()));
				break;
			case "getById":
				response.getWriter().write(this.mapper.writeValueAsString(this.pfService.getById(json.get("id").asInt())));
				break;
			case "addNew":				
				response.getWriter().write(this.pfService.persist(this.mapper.treeToValue(json, Tblpf.class)));
				break;
			case "edit":				
				if(this.pfService.update(this.mapper.treeToValue(json, Tblpf.class)))
					response.getWriter().write("true");
				break;
			case "delete":
				this.pfService.delete(json.get("id").asInt());
				break;
			case "fail":
				this.pfService.setStatusFailed(json.get("id").asInt());
		}
	}
}