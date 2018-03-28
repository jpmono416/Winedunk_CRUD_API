package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import services.PFProductsBlackListService;

/**
 * Servlet implementation class PFProductsBlacklist
 */
@WebServlet("/PFProductsBlacklist")
public class PFProductsBlacklist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	PFProductsBlackListService productsBlackListService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PFProductsBlacklist() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String partnerId = request.getParameter("partnerId");
		final String partnerProductId = request.getParameter("partnerProductId");

		if(StringUtils.isEmpty(partnerId) || StringUtils.isEmpty(partnerProductId))
			return;

		response.getWriter().write(String.valueOf(productsBlackListService.isProductBlacklisted(Integer.parseInt(partnerId), partnerProductId)));
	}

}
