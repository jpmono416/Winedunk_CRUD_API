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

import models.tblUserSubscriptions;
import services.SubscriptionsService;

/**
 * Servlet implementation class Subscriptions
 */
@WebServlet("/subscriptions")
public class Subscriptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	SubscriptionsService subscriptionService = new SubscriptionsService();
    public Subscriptions() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getSubscriptions" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserSubscriptions> subscriptions = subscriptionService.getSubscriptions();
					String arrayToJson = objectMapper.writeValueAsString(subscriptions);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getSubscription" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserSubscriptions subscription = subscriptionService.getSubscriptionById(id);
					String arrayToJson = objectMapper.writeValueAsString(subscription);
					
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
			case "addSubscription" :
			{
				try
				{
					tblUserSubscriptions subscription = new tblUserSubscriptions();
					ObjectMapper mapper = new ObjectMapper();
					subscription = mapper.readValue(content, tblUserSubscriptions.class);
					
					if(subscriptionService.addSubscription(subscription)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateSubscription" :
			{
				try
				{
					tblUserSubscriptions subscription = new tblUserSubscriptions();
					ObjectMapper mapper = new ObjectMapper();
					subscription = mapper.readValue(content, tblUserSubscriptions.class);
					
					if(subscriptionService.updateSubscription(subscription)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteSubscription" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(subscriptionService.deleteSubscription(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}