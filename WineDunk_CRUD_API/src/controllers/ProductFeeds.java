package controllers;

import java.util.List;
import java.io.IOException;
import java.sql.Time;

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

	final ProductFeedsService pfService;

    public ProductFeeds() {
        super();
		this.pfService = new ProductFeedsService();
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
				response.getWriter().write(new ObjectMapper().writeValueAsString(productFeeds));
				break;
			case "getById":
				if(request.getParameter("id")==null)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}

				response.setContentType("application/json");
				response.getWriter().write(new ObjectMapper().writeValueAsString(this.pfService.getById(Integer.valueOf(request.getParameter("id")))));
				break;
			case "addNew":
				for(String parameter : new String[] {"description, downloadURL, importPriority, partnerId, startTime, timePeriod"})
				{
					if(request.getParameter(parameter) == null)
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter "+parameter+" missing");
						return;
					}
				}

				//Populate a new ProductFeed, persist it (here we get the id automatically) and then return it as JSON
				String finalProductFeed = new ObjectMapper().writeValueAsString(this.pfService.persist(this.populateProductFeed(new Tblpf(), request)));

				response.setContentType("application/json");
				response.getWriter().write(finalProductFeed);
				break;
			case "edit":
				for(String parameter : new String[] {"id, description, downloadURL, importPriority, partnerId, startTime, timePeriod"})
				{
					if(request.getParameter(parameter) == null)
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter "+parameter+" missing");
						return;
					}
				}

				Tblpf productFeed = this.pfService.getById(Integer.valueOf(request.getParameter("id")));
				if(productFeed == null)
					return;

				String editedProductFeed = new ObjectMapper().writeValueAsString(this.pfService.persist(this.populateProductFeed(productFeed, request)));

				response.setContentType("application/json");
				response.getWriter().write(editedProductFeed);
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
			case "process":
				if(request.getParameter("id")==null)
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}

				this.pfService.processProductFeed(Integer.valueOf(request.getParameter("id")));
				break;
		}
	}

	public Tblpf populateProductFeed(Tblpf productFeed, HttpServletRequest request)
	{
		productFeed.setDescription(request.getParameter("description"));
		productFeed.setDownloadURL(request.getParameter("downloadURL"));
		productFeed.setImportPriority(Integer.valueOf(request.getParameter("importPriority")));
		productFeed.setPartnerId(Integer.valueOf(request.getParameter("partnerId")));

		//This field stores the exact value when this cron has to be executed for the first time
		//We'll process it if this value is older than the current timestamp and the lastImportation is null
		Time startTime = new Time(Long.valueOf(request.getParameter("startTime")));
		productFeed.setStartTime(startTime);

		//the time period will come in a cron format tha we will have to parse when executing the PFs processor
		productFeed.setTimePeriod(request.getParameter("downloadURL"));

		return productFeed;
	}
}