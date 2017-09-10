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

import models.tblUserWinesRatings;
import models.tblUsers;
import models.tblWines;
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
					
					tblUsers userIdObject = new tblUsers();
					tblWines wineIdObject = new tblWines();
					
					// Check for numericXId and assign to XId - if request comes from PriceComparison REST API 
					if(userWineRating.getUserId() == null)
					{
						userIdObject.setId(userWineRating.getNumericUserId());
						userWineRating.setUserId(userIdObject);
					}
					
					if(userWineRating.getWineId() == null)
					{
						wineIdObject.setId(userWineRating.getNumericWineId());
						userWineRating.setWineId(wineIdObject);
					}
					
					if(userWineRatingService.addUserWineRating(userWineRating)) { response.getWriter().println("True"); }
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
					
					if(userWineRatingService.updateUserWineRating(userWineRating)) { response.getWriter().println("True"); }
				} catch (Exception e) { return;}
				break;
			}
			
			case "deleteUserWineRating" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(userWineRatingService.deleteUserWineRating(id)) { response.getWriter().println("True"); }
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
					
					if(userWineRatingService.userHasReviewed(userId, wineId)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
				break;
			}
		}
	}

}