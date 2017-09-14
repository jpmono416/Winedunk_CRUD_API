package controllers;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

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

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
    		return;
		}

		switch(request.getParameter("action"))
		{
			case "getAll":
				final List<Tblpf> productFeeds = this.pfService.getAll();

				response.setContentType("application/json");
				response.getWriter().write(this.mapper.writeValueAsString(productFeeds));
				break;
			case "getById":
				if(request.getParameter("id")==null)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}

				response.setContentType("application/json");
				response.getWriter().write(this.mapper.writeValueAsString(this.pfService.getById(Integer.valueOf(request.getParameter("id")))));
				break;
			case "addNew":
				String newProductFeed = this.requestToString(request);
				
				response.getWriter().write(this.pfService.persist(this.mapper.readValue(newProductFeed, Tblpf.class)));
				break;
			case "edit":
				String productFeedJson = this.requestToString(request);
				
				if(this.pfService.update(this.mapper.readValue(productFeedJson, Tblpf.class)))
					response.getWriter().write("true");
				break;
			case "delete":
				if(request.getParameter("id")==null)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}

				this.pfService.delete(Integer.valueOf(request.getParameter("id")));
				break;
			case "fail":
				if(request.getParameter("id")==null)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				this.pfService.setStatusFailed(Integer.valueOf(request.getParameter("id")));
		}
	}

	private String requestToString(HttpServletRequest request) throws IOException
	{
		BufferedReader reader = new BufferedReader(request.getReader());
		StringBuilder stringBuilder = new StringBuilder();
		String line;

		while((line = reader.readLine())!=null)
			stringBuilder.append(line);
		
		return stringBuilder.toString();
	}
}