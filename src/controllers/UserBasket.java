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

import models.tblUserBasket;
import services.UserBasketsService;

/**
 * Servlet implementation class UserBaskets
 */
@WebServlet("/userBasket")
public class UserBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserBasketsService userBasketService = new UserBasketsService();
    public UserBasket() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserBaskets" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserBasket> userBaskets = userBasketService.getUserBaskets();
					String arrayToJson = objectMapper.writeValueAsString(userBaskets);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserBasket" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	tblUserBasket userBasket = userBasketService.getUserBasketById(id);
					String arrayToJson = objectMapper.writeValueAsString(userBasket);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
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
			case "addUserBasket" :
			{
				try
				{
					tblUserBasket userBasket = new tblUserBasket();
					ObjectMapper mapper = new ObjectMapper();
					userBasket = mapper.readValue(content, tblUserBasket.class);
					
					if(userBasketService.addUserBasket(userBasket)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateUserBasket" :
			{
				try
				{
					tblUserBasket userBasket = new tblUserBasket();
					ObjectMapper mapper = new ObjectMapper();
					userBasket = mapper.readValue(content, tblUserBasket.class);
					
					if(userBasketService.updateUserBasket(userBasket)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteUserBasket" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(userBasketService.deleteUserBasket(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}