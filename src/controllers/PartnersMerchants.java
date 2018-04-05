// aripe 2018-04-05

package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblPartnersMerchants;
import services.PartnersMerchantsService;

/**
 * Servlet implementation class PartnersMerchants
 */
@WebServlet("/partnersMerchants")
public class PartnersMerchants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper mapper = new ObjectMapper();
	@EJB
	PartnersMerchantsService partnersMerchantsService = new PartnersMerchantsService();
    public PartnersMerchants() {
    	super();

    	//Set pretty printing of json
    	this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getPartnersMerchants" :
			{
				try 
				{
					List<tblPartnersMerchants> partnersMerchantsList = partnersMerchantsService.getPartnersMerchants();
					String arrayToJson = this.mapper.writeValueAsString(partnersMerchantsList);

					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPartnersMerchantsById" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
		    	
					tblPartnersMerchants partnerMerchant = partnersMerchantsService.getPartnersMerchantsById(id);
					String arrayToJson = this.mapper.writeValueAsString(partnerMerchant);

					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getPartnersMerchantsBypartnerMerchantName":
				if(!request.getParameterMap().containsKey("partnerMerchantName")) { return; }

				tblPartnersMerchants partnerMerchant = partnersMerchantsService.getPartnersMerchantsBypartnerMerchantName(request.getParameter("partnerMerchantName"));
				response.getWriter().write(this.mapper.writeValueAsString(partnerMerchant));
				break;
				
				
			default:
			{
				System.out.println("Unrecognized GET request "+action);
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unrecognized action "+request.getParameter("action"));
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}