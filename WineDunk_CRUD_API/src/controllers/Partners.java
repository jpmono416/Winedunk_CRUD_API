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

import models.tblPartners;
import services.PartnersService;

/**
 * Servlet implementation class Partners
 */
@WebServlet("/partners")
public class Partners extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	PartnersService partnerService = new PartnersService();

	public Partners() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getParameterMap().containsKey("action")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parameter action missing");
			return;
		}

		switch (request.getParameter("action")) {
		case "getPartners": {
			try {
				// Set pretty printing of json
				this.mapper.enable(SerializationFeature.INDENT_OUTPUT);

				List<tblPartners> partners = partnerService.getPartners();
				String arrayToJson = this.mapper.writeValueAsString(partners);

				response.getWriter().write(arrayToJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

		case "getPartner": {
			try {
				if (!request.getParameterMap().containsKey("id")) {
					return;
				}
				Integer id = Integer.parseInt(request.getParameter("id"));

				// Set pretty printing of json
				this.mapper.enable(SerializationFeature.INDENT_OUTPUT);

				tblPartners partner = partnerService.getPartnerById(id);
				String arrayToJson = this.mapper.writeValueAsString(partner);

				response.getWriter().write(arrayToJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!request.getParameterMap().containsKey("action")) {
			return;
		}

		switch (request.getParameter("action")) {
		case "addPartner": {
			try {
				tblPartners partner = this.mapper.readValue(request.getInputStream(), tblPartners.class);
				System.out.println(partner);
				if (partnerService.addPartner(partner)) {
					response.getWriter().println("True");
				}
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
			break;
		}

		case "updatePartner": {
			try {
				tblPartners partner = this.mapper.readValue(request.getInputStream(), tblPartners.class);

				if (partnerService.updatePartner(partner)) {
					response.getWriter().println("True");
				}
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
			break;
		}

		case "deletePartner": {
			try {
				JsonNode json = this.mapper.readTree(request.getInputStream());
				if (partnerService.deletePartner(json.get("id").asInt())) {
					response.getWriter().println("True");
				}
			} catch (Exception e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
			break;
		}
		}
	}

}