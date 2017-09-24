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
        //For testing purposes this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
		{
			System.out.println("Missing action on /TblPfMerchantsHTMLParsing#doPost");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
			return;
		}

		try {
			JsonNode json =  this.mapper.readTree(request.getInputStream());
			switch(request.getParameter("action"))
			{
				case "getAll":
					response.sendError(HttpServletResponse.SC_OK, this.mapper.writeValueAsString(this.merchantParsingService.getAll()));
					return;
				case "getByMerchant":
					String merchantParsingJson = this.mapper.writeValueAsString(this.merchantParsingService.getByMerchant(json.get("id").asInt()));
					System.out.println(merchantParsingJson);
					response.getWriter().write(merchantParsingJson);
					return;
				default:
					System.out.println("Action "+request.getParameter("action")+" not recognised");
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action "+request.getParameter("action")+" not recognised");
					return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
