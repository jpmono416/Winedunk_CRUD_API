package controllers;

import java.util.List;
import java.io.IOException;
import java.io.StringWriter;

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

    public ProductFeeds() {
        super();
    }

	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action"))
			return;

		switch(request.getParameter("action"))
		{
			case "getProductFeeds":				
				final ProductFeedsService pfService = new ProductFeedsService();
				final List<Tblpf> productFeeds = pfService.getAllProductFeeds();

				final StringWriter outputString = new StringWriter();
				final ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.writeValue(outputString, productFeeds);

				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().write(outputString.toString());
				break;				
		}
	}

}
