package controllers;

import java.io.BufferedReader;
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

import models.tblWinesbyMerchants;
import services.WinesByMerchantService;

/**
 * Servlet implementation class WinesByMerchant
 */
@WebServlet("/WinesByMerchant")
public class WinesByMerchant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WinesByMerchantService WinesByMerchantervice = new WinesByMerchantService();
    public WinesByMerchant() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWinesByMerchant" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblWinesbyMerchants> WinesByMerchant = WinesByMerchantervice.getWinesByMerchant();
					String arrayToJson = objectMapper.writeValueAsString(WinesByMerchant);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
				
			case "getWineByMerchant" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblWinesbyMerchants WineByMerchant = WinesByMerchantervice.getWineByMerchantById(id);
					String arrayToJson = objectMapper.writeValueAsString(WineByMerchant);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }

		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = request.getReader();
	    String line;
        
	    while ((line = reader.readLine()) != null) 
        { sb.append('\n').append(line); }
	    reader.close();

	    String content = sb.toString().replaceFirst("\n", "");
	    
		String action = request.getParameter("action");
		switch (action) 
		{
			case "addWineByMerchant" :
				try
				{
					tblWinesbyMerchants WineByMerchant = new tblWinesbyMerchants();
					ObjectMapper mapper = new ObjectMapper();
					WineByMerchant = mapper.readValue(content, tblWinesbyMerchants.class);
					
					if(WinesByMerchantervice.addWineByMerchant(WineByMerchant)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "updateWineByMerchant" :
				try
				{
					tblWinesbyMerchants WineByMerchant = new tblWinesbyMerchants();
					ObjectMapper mapper = new ObjectMapper();
					WineByMerchant = mapper.readValue(content, tblWinesbyMerchants.class);
					
					if(WinesByMerchantervice.updateWineByMerchant(WineByMerchant)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "deleteWineByMerchant" :
				try
				{
					Integer id = Integer.parseInt(content);
					if(WinesByMerchantervice.deleteWineByMerchant(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
		}
	}

}