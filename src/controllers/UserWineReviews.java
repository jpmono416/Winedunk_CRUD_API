package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import models.tblUserWineReviews;
import services.UserWineReviewsService;

/**
 * Servlet implementation class UserWineReviews
 */
@WebServlet("/userWineReviews")
public class UserWineReviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserWineReviewsService userWineReviewService = new UserWineReviewsService();
    public UserWineReviews() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserWineReviews" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserWineReviews> userWineReviews = userWineReviewService.getUserWineReviews();
					String arrayToJson = objectMapper.writeValueAsString(userWineReviews);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserWineReview" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserWineReviews userWineReview = userWineReviewService.getUserWineReviewById(id);
					String arrayToJson = objectMapper.writeValueAsString(userWineReview);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getReviewsForWine" : {
				try 
				{
					if(!request.getParameterMap().containsKey("wineId")) { return; }
					Integer wineId = Integer.parseInt(request.getParameter("wineId"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	List<tblUserWineReviews> userWineReview = userWineReviewService.getReviewsForWine(wineId);
			    	String arrayToJson = objectMapper.writeValueAsString(userWineReview);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getReviewsForUser" : {
				try 
				{
					if(!request.getParameterMap().containsKey("userId")) { return; }
					Integer userId = Integer.parseInt(request.getParameter("userId"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	List<tblUserWineReviews> userWineReview = userWineReviewService.getReviewsForUser(userId);
			    	if (userWineReview != null) {

						String arrayToJson = objectMapper.writeValueAsString(userWineReview);
						response.setStatus(200);
						response.getWriter().write(arrayToJson);
						
			    	} else {

						response.setStatus(204);
						response.getWriter().write("");
						
			    	}
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getAmountOfReviewsForWine" : {
				try 
				{
					if(!request.getParameterMap().containsKey("wineId")) { return; }
					Integer wineId = Integer.parseInt(request.getParameter("wineId"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	Integer userWineReview = userWineReviewService.getAmountOfReviewsForWine(wineId);
					
					response.setStatus(200);
					response.getWriter().print(userWineReview);
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
			case "addUserWineReview" :
			{
				try
				{
					tblUserWineReviews userWineReview = new tblUserWineReviews();
					ObjectMapper mapper = new ObjectMapper();
					userWineReview = mapper.readValue(content, tblUserWineReviews.class);
					if(userWineReviewService.addUserWineReview(userWineReview)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace();return;}
				break;
			}
			
			case "updateUserWineReview" :
			{
				try
				{
					tblUserWineReviews userWineReview = new tblUserWineReviews();
					ObjectMapper mapper = new ObjectMapper();
					userWineReview = mapper.readValue(content, tblUserWineReviews.class);
					
					if(userWineReviewService.updateUserWineReview(userWineReview)) { response.getWriter().print("True"); }
				} catch (Exception e) { return;}
				break;
			}
			
			case "deleteUserWineReview" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(userWineReviewService.deleteUserWineReview(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { return; }
				break;
			}
			
			case "checkUserHasReviewed" :
			{
				try
				{
					List<String> splittedContent;					
					splittedContent = new ArrayList<String>(Arrays.asList(content.split(",")));
					
					Integer userId = Integer.parseInt(splittedContent.get(0));
					Integer wineId = Integer.parseInt(splittedContent.get(1));
					Boolean responseFromDB = userWineReviewService.userHasReviewed(userId, wineId);
					response.getWriter().print(responseFromDB);
				} catch (Exception e) { return; }
				break;
			}
			
			case "getReviewsForWine" : {
				try
				{
					Integer id = Integer.parseInt(content);
					List<tblUserWineReviews> reviews = userWineReviewService.getReviewsForWine(id);
					
					ObjectMapper mapper = new ObjectMapper();
					//Set pretty printing of json
					mapper.enable(SerializationFeature.INDENT_OUTPUT);
					String jsonResult = "{ \"Reviews\" : " + mapper.writeValueAsString(reviews) + " }";
					
					response.getWriter().write(jsonResult);
				} catch (Exception e) { return; }
			}
			
			case "getReviewsForUser" : {
				try
				{
					Integer id = Integer.parseInt(content);
					List<tblUserWineReviews> reviews = userWineReviewService.getReviewsForUser(id);
					
					ObjectMapper mapper = new ObjectMapper();
					//Set pretty printing of json
					mapper.enable(SerializationFeature.INDENT_OUTPUT);
					String jsonResult = "{ \"Reviews\" : " + mapper.writeValueAsString(reviews) + " }";
					
					response.getWriter().write(jsonResult);
				} catch (Exception e) { return; }
			}
		}
	}
	
}