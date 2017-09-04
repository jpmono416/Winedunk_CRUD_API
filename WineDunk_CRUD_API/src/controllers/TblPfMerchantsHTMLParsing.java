package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import services.TblPfMerchantsHTMLParsingService;

/**
 * Servlet implementation class TblPfMerchantsHTMLParsing
 */
@WebServlet("/TblPfMerchantsHTMLParsing")
public class TblPfMerchantsHTMLParsing extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		
		switch(request.getParameter("action"))
		{
			case "getAll":
				response.sendError(HttpServletResponse.SC_OK, new ObjectMapper().writeValueAsString(this.merchantParsingService.getAll()));
				return;
			case "getByMerchant":
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
					return;
				}
				String merchantParsingJson = new ObjectMapper().writeValueAsString(this.merchantParsingService.getByMerchant(Integer.valueOf(request.getParameter("id"))));
				response.sendError(HttpServletResponse.SC_OK, merchantParsingJson);
				return;
		}
	}

}
