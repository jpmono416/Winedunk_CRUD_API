package controllers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import services.WinesMinPriceViewService;
import views.viewWinesMinimumPrice;


@WebServlet("/winesMinPriceView")
public class WinesMinPriceView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	WinesMinPriceViewService winesMinPriceViewService = new WinesMinPriceViewService();
	
    public WinesMinPriceView() { super(); }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getWine" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					viewWinesMinimumPrice wine = winesMinPriceViewService.getWineById(id);
					String arrayToJson = objectMapper.writeValueAsString(wine);
					
					PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
						    response.getOutputStream(), StandardCharsets.UTF_8), true);
					
					printWriter.write(arrayToJson);
					printWriter.close();
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
		}
	}
}