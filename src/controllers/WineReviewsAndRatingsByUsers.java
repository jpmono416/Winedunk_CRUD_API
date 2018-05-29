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

import models.ViewWineReviewsAndRatingByUsers;
import services.ViewWineReviewsAndRatingByUsersService;

/**
 * Servlet implementation class WineReviewsAndRatingsByUsers
 */
@WebServlet("/WineReviewsAndRatingsByUsers")
public class WineReviewsAndRatingsByUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	ViewWineReviewsAndRatingByUsersService viewWineReviewsAndRatingByUsersService = new ViewWineReviewsAndRatingByUsersService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WineReviewsAndRatingsByUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		if (request.getParameterMap().containsKey("action")) {

			StringBuilder sb = new StringBuilder();
		    BufferedReader reader = request.getReader();
		    String line;
	        
		    while ((line = reader.readLine()) != null) { sb.append('\n').append(line); }
		    reader.close();
	
		    String content = sb.toString().replaceFirst("\n", "");
		    
			String action = request.getParameter("action");
			
			Boolean boolResult = false;
			
			switch (action) 
			{
				case "getAllByUserId" : {
					
					Integer userId = 0;
					try {
						userId = Integer.parseInt(content);
					} catch (Exception e) {
						userId = 0;
					}
					
					if (userId > 0) {
						
						ObjectMapper objectMapper = new ObjectMapper();
				    	//Set pretty printing of json
				    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			    	
				    	try {
				    		
				    		List<ViewWineReviewsAndRatingByUsers> wineReviewsAndRatings = viewWineReviewsAndRatingByUsersService.getAllWineReviewsAndRatingForUser(userId);
				
				    		if (wineReviewsAndRatings != null) {

								String arrayToJson = "{ \"RatingsAndReviews\" : " + objectMapper.writeValueAsString(wineReviewsAndRatings) + " }";
								response.setStatus(200);
								response.getWriter().write(arrayToJson);
								
					    	} else {

								response.setStatus(204);
								response.getWriter().write("");
					    	}
				    	} catch (Exception e) {
							
							response.setStatus(500);
							response.getWriter().write("");
				    	}
					} else {
						
						response.setStatus(400);
						response.getWriter().write("");
					}
					break;
				}
				
				case "deleteRating": {
					
					Integer ratingId = 0;
					try {
						ratingId = Integer.parseInt(content);
					} catch (Exception e) {
						ratingId = 0;
					}

					if (ratingId > 0){
						
						try {
							boolResult = viewWineReviewsAndRatingByUsersService.deleteRating(ratingId);
							response.setStatus(200);
							response.getWriter().write(boolResult.toString());
						} catch (Exception e) {
							response.setStatus(500);
							response.getWriter().write("");
						}
						
					} else {
						
						response.setStatus(400);
						response.getWriter().write("");
					}
					break;
				}
				
				case "updateRating": {
					
					Integer ratingId = 0;
					Integer ratingValue = 0;
					try {
					
						List<String> splittedContent = new ArrayList<String>(Arrays.asList(content.split(",")));
						ratingId = Integer.parseInt(splittedContent.get(0));
						ratingValue = Integer.parseInt(splittedContent.get(1));
						
					} catch (Exception e) {
						ratingId = 0;
						ratingValue = 0;	
					}
					
					if ( (ratingId > 0) && (ratingValue > 0) ) {
						
						try {
							boolResult = viewWineReviewsAndRatingByUsersService.updateRating(ratingId, ratingValue);
							response.setStatus(200);
							response.getWriter().write(boolResult.toString());
						} catch (Exception e) {

							response.setStatus(500);
							response.getWriter().write("");
						}
						
					} else {
						
						response.setStatus(400);
						response.getWriter().write("");
					}
					
					break;
				}
				
				case "insertRating": {
					
					Integer userId = 0;
					Integer wineId = 0;
					Integer ratingValue = 0;
					
					try {
						
						List<String> splittedContent = new ArrayList<String>(Arrays.asList(content.split(",")));
						userId = Integer.parseInt(splittedContent.get(0));
						wineId = Integer.parseInt(splittedContent.get(1));
						ratingValue = Integer.parseInt(splittedContent.get(2));
						
					} catch (Exception e) {
						userId = 0;
						wineId = 0;
						ratingValue = 0;	
					}
					
					if ( (userId > 0) && (wineId > 0) && (ratingValue > 0) ) {
						
						try {
							
							boolResult = viewWineReviewsAndRatingByUsersService.insertRating(userId, wineId, ratingValue);
							response.setStatus(200);
							response.getWriter().write(boolResult.toString());
							
						} catch (Exception e) {	

							response.setStatus(500);
							response.getWriter().write("");
						}
						
					} else {
						
						response.setStatus(400);
						response.getWriter().write("");
					}
					
					break;
					
				}
				
				case "deleteReview": {
					
					Integer reviewId = 0;
					try {
						reviewId = Integer.parseInt(content);
					} catch (Exception e) {
						reviewId = 0;
					}

					if (reviewId > 0){
						
						try {
							boolResult = viewWineReviewsAndRatingByUsersService.deleteReview(reviewId);
							response.setStatus(200);
							response.getWriter().write(boolResult.toString());
						} catch (Exception e) {

							response.setStatus(500);
							response.getWriter().write("");
						}
						
					} else {
						
						response.setStatus(400);
						response.getWriter().write("");
					}
					break;
				}
				
				case "updateReview": {
					
					Integer reviewId = 0;
					String reviewTitle = "";
					String reviewComments = "";
					try {
				
						List<String> splittedContent = new ArrayList<String>(Arrays.asList(content.split(Character.toString ((char) 30))));
						reviewId = Integer.parseInt(splittedContent.get(0));
						reviewTitle = splittedContent.get(1);
						reviewComments = splittedContent.get(2);
						
					} catch (Exception e) {
						reviewId = 0;
						reviewTitle = "";
						reviewComments = "";	
					}
					
					if ( (reviewId > 0) && (reviewComments != null) && (!reviewComments.equals("")) ) {
						
						try {
							boolResult = viewWineReviewsAndRatingByUsersService.updateReview(reviewId, reviewTitle, reviewComments);
							response.setStatus(200);
							response.getWriter().write(boolResult.toString());
						} catch (Exception e) {

							response.setStatus(500);
							response.getWriter().write("");
						}
						
					} else {
						
						response.setStatus(400);
						response.getWriter().write("");
					}
					
					break;
				}
				
				case "insertReview": {
					
					Integer userId = 0;
					Integer wineId = 0;
					String reviewTitle = "";
					String reviewComments = "";
					try {
				
						List<String> splittedContent = new ArrayList<String>(Arrays.asList(content.split(Character.toString ((char) 30))));
						userId = Integer.parseInt(splittedContent.get(0));
						wineId = Integer.parseInt(splittedContent.get(1));
						reviewTitle = splittedContent.get(2);
						reviewComments = splittedContent.get(3);
						
					} catch (Exception e) {
						
						userId = 0;
						wineId = 0;
						reviewTitle = "";
						reviewComments = "";	
					}
					
					if ( (userId > 0) && (wineId > 0) && (reviewComments != null) && (!reviewComments.equals("")) ) {
						
						try {
							boolResult = viewWineReviewsAndRatingByUsersService.insertReview(userId, wineId, reviewTitle, reviewComments);
							response.setStatus(200);
							response.getWriter().write(boolResult.toString());
						} catch (Exception e) {

							response.setStatus(500);
							response.getWriter().write("");
						}
						
					} else {
						
						response.setStatus(400);
						response.getWriter().write("");
					}
					
					break;
				}
			}
			
		} else {
			
			response.setStatus(400);
			response.getWriter().write("");
		}
	}

}
