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

import services.UsersWinesRatingsViewService;
import views.viewUserWinesRatings;

/**
 * Servlet implementation class Wines
 */
@WebServlet("/usersWinesRatingsView")
public class UsersWinesRatingsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsersWinesRatingsViewService reviewsService = new UsersWinesRatingsViewService();
    public UsersWinesRatingsView() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		String arrayToJson;
		ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	
		switch(action) 
		{
			case "getUserWineRatings" :
				List<viewUserWinesRatings> reviews = reviewsService.getRatings();
				arrayToJson = objectMapper.writeValueAsString(reviews);
				
				response.getWriter().write("{ \"Ratings\": " + arrayToJson + " }"); 
			break;
			
			case "getRatingsForWine" :
				Integer wineId = Integer.parseInt(request.getParameter("id"));
				try 
				{
					List<viewUserWinesRatings> reviewsForWine = reviewsService.getRatingsForWine(wineId);
					arrayToJson = objectMapper.writeValueAsString(reviewsForWine);
					
					response.getWriter().write("{ \"Ratings\": " + arrayToJson + " }"); 
				} catch (Exception e) { e.printStackTrace(); }
			break;
			
			case "getRatingsForUser" :
				Integer userId = Integer.parseInt(request.getParameter("id"));
				List<viewUserWinesRatings> reviewsForUser = reviewsService.getRatingsForUser(userId);
				arrayToJson = objectMapper.writeValueAsString(reviewsForUser);
				
				response.getWriter().write("{ \"Ratings\": " + arrayToJson + " }"); 
			break;
			
			case "getAmountOfRatingsForWine" :
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				Long countOfRatings = reviewsService.getCountOfRatingsForWine(id);
				response.getWriter().write(countOfRatings.toString());
			break;
		}
	}
}