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

import models.tblUsers_wines_ratings;
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
		    	
					List<tblUsers_wines_ratings> userWineRatings = userWineRatingService.getUserWineRatings();
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
		    	
					tblUsers_wines_ratings userWineRating = userWineRatingService.getUserWineRatingById(id);
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
					tblUsers_wines_ratings userWineRating = new tblUsers_wines_ratings();
					ObjectMapper mapper = new ObjectMapper();
					userWineRating = mapper.readValue(content, tblUsers_wines_ratings.class);
					
					if(userWineRatingService.addUserWineRating(userWineRating)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace();return;}
				break;
			}
			
			case "updateUserWineRating" :
			{
				try
				{
					tblUsers_wines_ratings userWineRating = new tblUsers_wines_ratings();
					ObjectMapper mapper = new ObjectMapper();
					userWineRating = mapper.readValue(content, tblUsers_wines_ratings.class);
					
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
		}
	}

}