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

import services.TblPfMerchantsHTMLParsingService;

/**
 * Servlet implementation class TblPfMerchantsHTMLParsing
 */
@WebServlet("/TblPfMerchantsHTMLParsing")
public class TblPfMerchantsHTMLParsing extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

	@EJB
	TblPfMerchantsHTMLParsingService merchantParsingService;

    public TblPfMerchantsHTMLParsing() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
			return;
		}

		JsonNode json =  this.mapper.readTree(request.getInputStream());
		switch(request.getParameter("action"))
		{
			case "getAll":
				response.sendError(HttpServletResponse.SC_OK, this.mapper.writeValueAsString(this.merchantParsingService.getAll()));
				return;
			case "getByMerchant":
				String merchantParsingJson = this.mapper.writeValueAsString(this.merchantParsingService.getByMerchant(json.get("id").asInt()));
				response.sendError(HttpServletResponse.SC_OK, merchantParsingJson);
				return;
		}
	}

}
