package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import services.ProductsService;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ProductsService productsService;

	public Products() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(!request.getParameterMap().containsKey("action"))
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing action");
    		return;
		}

		switch(request.getParameter("action"))
    	{
			case "getAll":
				response.sendError(HttpServletResponse.SC_OK, new ObjectMapper().writeValueAsString(productsService.getAll()));
				return;
			case "getByPfId":
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
		    		return;
				}

				response.sendError(HttpServletResponse.SC_OK, new ObjectMapper().writeValueAsString(productsService.getByTblpf(Integer.valueOf(request.getParameter("id")))));
				break;
    	}
	}

}
