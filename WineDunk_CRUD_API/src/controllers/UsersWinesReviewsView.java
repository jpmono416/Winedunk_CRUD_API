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

import services.UsersWinesReviewsViewService;
import views.viewUsersWinesReviews;

/**
 * Servlet implementation class Wines
 */
@WebServlet("/usersWinesReviewsView")
public class UsersWinesReviewsView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsersWinesReviewsViewService reviewsService = new UsersWinesReviewsViewService();
    public UsersWinesReviewsView() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		String arrayToJson;
		ObjectMapper objectMapper = new ObjectMapper();
    	//Set pretty printing of json
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	
		switch(action) 
		{
			case "getUserWineReviews" :
				List<viewUsersWinesReviews> reviews = reviewsService.getReviews();
				arrayToJson = objectMapper.writeValueAsString(reviews);
				
				response.getWriter().write("{ \"Reviews\": " + arrayToJson + " }"); 
			break;
			
			case "getReviewsForWine" :
				Integer wineId = Integer.parseInt(request.getParameter("id"));
				try 
				{
					List<viewUsersWinesReviews> reviewsForWine = reviewsService.getReviewsForWine(wineId);
					arrayToJson = objectMapper.writeValueAsString(reviewsForWine);
					
					response.getWriter().write("{ \"Reviews\": " + arrayToJson + " }"); 
				} catch (Exception e) { e.printStackTrace(); }
			break;
			
			case "getReviewsForUser" :
				Integer userId = Integer.parseInt(request.getParameter("id"));
				List<viewUsersWinesReviews> reviewsForUser = reviewsService.getReviewsForUser(userId);
				arrayToJson = objectMapper.writeValueAsString(reviewsForUser);
				
				response.getWriter().write("{ \"Reviews\": " + arrayToJson + " }"); 
			break;
			
			case "getAmountOfReviewsForWine" :
				
				Integer id = Integer.parseInt(request.getParameter("id"));
				Long countOfReviews = reviewsService.getCountOfReviewsForWine(id);
				response.getWriter().write(countOfReviews.toString());
			break;
		}
	}
}