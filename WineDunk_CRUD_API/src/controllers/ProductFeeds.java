package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*if(!request.getParameterMap().containsKey("action"))
			return;
		
		switch(request.getParameter("action"))
		{
			case "getProductFeeds":
				ObjectMapper objectMapper = new ObjectMapper();
				
				ProductFeedsService pfService = new ProductFeedsService();
				break;				
		}*/
		response.setStatus(200);
		response.getWriter().write(request.getParameterMap().toString());
	}

}
