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
import models.Tblpfstatus;
import services.PartnersService;
import services.ProductFeedsService;
import services.StatusService;

/**
 * Servlet implementation class ProductFeeds
 */
@WebServlet("/ProductFeeds")
public class ProductFeeds extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	ProductFeedsService pfService;
	@EJB
	StatusService statusService;
	@EJB
	PartnersService partnersService;

    public ProductFeeds() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
    		return;
		}

		JsonNode json = this.mapper.readTree(request.getInputStream());
		System.out.print(json.asText());
		switch(request.getParameter("action"))
		{
			case "getAll":
				response.getWriter().write(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.pfService.getAll()));
				break;
			case "getById":
				response.getWriter().write(this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.pfService.getById(json.get("id").asInt())));
				break;
			case "addNew":
			{
				Tblpfstatus status = this.statusService.getByID(1);
				Tblpf productFeed = this.mapper.treeToValue(json, Tblpf.class);

				System.out.print(json.get("partnerId").asInt());
				System.out.println(this.partnersService.getPartnerById(json.get("partnerId").asInt()));

				productFeed.setLatestStatus(status)
						   .setStandardisationStatus(status)
						   .setImportationStatus(status)
						   .setPartnerId(this.partnersService.getPartnerById(json.get("partnerId").asInt()));
				response.getWriter().write(this.pfService.persist(productFeed));
				break;
			}
			case "update":	
			{
				//Make sure the status are not null before updating
				Tblpf existingPf = this.pfService.getById(json.get("id").asInt());
				Tblpf newPf = this.mapper.treeToValue(json, Tblpf.class);

				if(newPf.getStandardisationStatus()==null)
					newPf.setStandardisationStatus(existingPf.getStandardisationStatus());
				if(newPf.getImportationStatus()==null)
					newPf.setImportationStatus(existingPf.getImportationStatus());
				if(newPf.getLatestStatus()==null)
					newPf.setLatestStatus(existingPf.getLatestStatus());

				if(this.pfService.update(newPf))
					response.getWriter().write("true");
				break;
			}
			case "delete":
				this.pfService.delete(json.get("id").asInt());
				break;
			case "fail":
				this.pfService.setStatusFailed(json.get("id").asInt());
		}
	}
}