package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Tblpfproduct;
import services.ProductsService;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();

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
				response.getWriter().write(this.mapper.writeValueAsString(this.productsService.getAll()));
				return;
			case "getByPfId":
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
		    		return;
				}

				response.getWriter().write(this.mapper.writeValueAsString(this.productsService.findByTblpf(Integer.valueOf(request.getParameter("id")))));
				return;
			case "findByPartnerProductIdAndMerchantProductId":
				for(String parameter : new String[] {"partnerProductId", "merchantProductId"})
				{
					if(!request.getParameterMap().containsKey(parameter))
					{
						response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing "+parameter);
			    		return;
					}
				}
				response.getWriter().write(this.mapper.writeValueAsString(this.productsService.findByPartnerIdAndMerchantId(Integer.valueOf(request.getParameter("partnerProductId")), Integer.valueOf(request.getParameter("merchantProductId")))));
				return;
			case "deleteProduct":
				if(!request.getParameterMap().containsKey("id"))
				{
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing id");
		    		return;
				}

				if(this.productsService.deleteProduct(Integer.parseInt(request.getParameter("id"))))
					response.getWriter().write("true");
				return;
			case "updateProduct":
				this.productsService.updateProduct(this.mapper.readValue(this.requestToString(request), Tblpfproduct.class));
				response.getWriter().write("true");
				return;				
			case "addProduct":
				Integer id = this.productsService.addProduct(this.mapper.readValue(this.requestToString(request), Tblpfproduct.class));
				response.getWriter().write(id);
				return;
				
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
