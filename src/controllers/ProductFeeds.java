package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
			return;

		switch(request.getParameter("action"))
		{
			case "getAll":
			{
				final List<Tblpf> productFeeds = this.pfService.getAllProductFeeds();
				response.getWriter().write(this.mapper.writeValueAsString(productFeeds));
				break;
			}
			case "getById":
			{
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				Tblpf pf = this.pfService.getById(Integer.parseInt(request.getParameter("id")));
				if(pf!=null)
					response.getWriter().print(this.mapper.writeValueAsString(pf));
				break;
			}
			case "failStandardisation":
			{
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				this.pfService.failStandardisation(Integer.parseInt(request.getParameter("id")));
				break;
			}
			case "okStandardisation":
			{
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				this.pfService.okStandardisation(Integer.parseInt(request.getParameter("id")));
				break;
			}
			case "processingStandardisation":
			{
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				this.pfService.processingStandardisation(Integer.parseInt(request.getParameter("id")));
				break;
			}
			case "failImportation":
			{
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				this.pfService.failStandardisation(Integer.parseInt(request.getParameter("id")));
				break;
			}
			case "okImportation":
			{
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				this.pfService.okStandardisation(Integer.parseInt(request.getParameter("id")));
				break;
			}
			case "processingImportation":
			{
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				this.pfService.processingStandardisation(Integer.parseInt(request.getParameter("id")));
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
			return;

		//JsonNode json = this.mapper.readTree();
		switch(request.getParameter("action"))
		{
			case "update":
			{
				Tblpf pf = this.mapper.readValue(request.getInputStream(), Tblpf.class);
				response.getWriter().print(this.pfService.update(pf));
				break;
			}
		}
	}
}
