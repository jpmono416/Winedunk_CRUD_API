package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DecimalFormat;
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
import models.tblUserWinesRatings;
import services.UserWineRatingsService;

/**
 * Servlet implementation class UserWineRatings
 */
@WebServlet("/userWineRatings")
public class UserWineRatings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserWineRatingsService userWineRatingService = new UserWineRatingsService();
    public UserWineRatings() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserWineRatings" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserWinesRatings> userWineRatings = userWineRatingService.getUserWineRatings();
					String arrayToJson = objectMapper.writeValueAsString(userWineRatings);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserWineRating" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserWinesRatings userWineRating = userWineRatingService.getUserWineRatingById(id);
					String arrayToJson = objectMapper.writeValueAsString(userWineRating);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getRatingsForUser" : {
				try 
				{
					if(!request.getParameterMap().containsKey("userId")) { return; }
					Integer userId = Integer.parseInt(request.getParameter("userId"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
			    	List<tblUserWinesRatings> userWineReview = userWineRatingService.getRatingsForUser(userId);
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
			case "addUserWineRating" :
			{
				try
				{
					tblUserWinesRatings userWineRating = new tblUserWinesRatings();
					ObjectMapper mapper = new ObjectMapper();
					userWineRating = mapper.readValue(content, tblUserWinesRatings.class);
					
					response.getWriter().print(userWineRatingService.addUserWineRating(userWineRating)); 
				} catch (Exception e) { e.printStackTrace();return;}
				break;
			}
			
			case "updateUserWineRating" :
			{
				try
				{
					tblUserWinesRatings userWineRating = new tblUserWinesRatings();
					ObjectMapper mapper = new ObjectMapper();
					userWineRating = mapper.readValue(content, tblUserWinesRatings.class);
					
					response.getWriter().print(userWineRatingService.updateUserWineRating(userWineRating));
				} catch (Exception e) { return;}
				break;
			}
			
			case "deleteUserWineRating" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					response.getWriter().print(userWineRatingService.deleteUserWineRating(id));
				} catch (Exception e) { return; }
				break;
			}
			
			case "checkUserHasRated" :
			{
				try
				{
					List<String> splittedContent;					
					splittedContent = new ArrayList<String>(Arrays.asList(content.split(",")));
					
					Integer userId = Integer.parseInt(splittedContent.get(0));
					Integer wineId = Integer.parseInt(splittedContent.get(1));
					
					response.getWriter().print(userWineRatingService.userHasRated(userId, wineId));
				} catch (Exception e) { return; }
				break;
			}
			
			case "getCountForWine" : {
				try 
				{
					Integer wineId = Integer.parseInt(content);
					if (wineId > 0) {
						
						Integer count = userWineRatingService.getAmountOfRatingForWine(wineId);
						response.setStatus(200);
						response.getWriter().print(count);
						return;
					} else {
						response.setStatus(204);
						response.getWriter().print("0");
						return;
					}
				}
				catch (Exception e) {

					response.setStatus(204);
					response.getWriter().print("0");
					e.printStackTrace(); 
				}
				break;
			}
			
			case "getUserRatingValue": {
				try 
				{
					List<String> splittedContent;					
					splittedContent = new ArrayList<String>(Arrays.asList(content.split(",")));
					
					Integer userId = Integer.parseInt(splittedContent.get(0));
					Integer wineId = Integer.parseInt(splittedContent.get(1));
					
					if ( (wineId != null) && (wineId > 0) && (userId != null) && (userId > 0)) {

						Integer userRating = userWineRatingService.getUserRatingValue(userId, wineId);
						response.setStatus(200);
						response.getWriter().print(userRating);
						return;
						
					} else {
						response.setStatus(204);
						response.getWriter().print("0");
						return;
					}
				} catch (Exception e) {

					response.setStatus(204);
					response.getWriter().print("0");
					e.printStackTrace(); 
				}
				break;
			}
			
			case "getAVGForWine" : {
				try 
				{
					Integer wineId = Integer.parseInt(content);
					if (wineId > 0) {
						
						double AVG = userWineRatingService.getAVGRatingForWine(wineId);
				    	DecimalFormat df = new DecimalFormat();
				    	df.setMaximumFractionDigits(1);
				    	
						response.setStatus(200);
						response.getWriter().print(df.format(AVG));
						
					} else {
						response.setStatus(204);
						response.getWriter().print("0");
					}
				}
				catch (Exception e) {

					response.setStatus(204);
					response.getWriter().print("0");
					e.printStackTrace(); 
				}
				break;
			}
			
		}
	}

}