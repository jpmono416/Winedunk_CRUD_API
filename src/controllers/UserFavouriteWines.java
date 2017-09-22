package controllers;

import java.io.BufferedReader;
import java.io.IOException;
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

import models.tblUserFavouriteWines;
import services.UserFavouriteWinesService;

/**
 * Servlet implementation class UserFavouriteWines
 */
@WebServlet("/userFavouriteWines")
public class UserFavouriteWines extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserFavouriteWinesService userFavouriteWineService = new UserFavouriteWinesService();
    public UserFavouriteWines() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserFavouriteWines" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserFavouriteWines> userFavouriteWines = userFavouriteWineService.getUserFavouriteWines();
					String arrayToJson = "{ \"FavouriteWines\" : " + objectMapper.writeValueAsString(userFavouriteWines) + " }";
					
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserFavouriteWine" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserFavouriteWines userFavouriteWine = userFavouriteWineService.getUserFavouriteWineById(id);
					String arrayToJson = objectMapper.writeValueAsString(userFavouriteWine);
					
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
			case "addUserFavouriteWine" :
				try
				{
					tblUserFavouriteWines userFavouriteWine = new tblUserFavouriteWines();
					ObjectMapper mapper = new ObjectMapper();
					userFavouriteWine = mapper.readValue(content, tblUserFavouriteWines.class);
					
					if(userFavouriteWineService.addUserFavouriteWine(userFavouriteWine)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); return; }
				break;
			
			case "updateUserFavouriteWine" :
				try
				{
					tblUserFavouriteWines userFavouriteWine = new tblUserFavouriteWines();
					ObjectMapper mapper = new ObjectMapper();
					userFavouriteWine = mapper.readValue(content, tblUserFavouriteWines.class);
					
					if(userFavouriteWineService.updateUserFavouriteWine(userFavouriteWine)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "deleteUserFavouriteWine" :
				try
				{
					Integer id = Integer.parseInt(content);
					if(userFavouriteWineService.deleteUserFavouriteWine(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace() ;return; }
				break;
			
			case "getFavouriteWinesForUser" :
				try
				{
					Integer id = Integer.parseInt(content);
					List<tblUserFavouriteWines> favouriteWines = userFavouriteWineService.getFavouriteWinesByUser(id);

					if(favouriteWines != null)
					{
						for(tblUserFavouriteWines wine : favouriteWines)
						{
							Integer numericWineId = wine.getWineId().getId();
							Integer numericUserId = wine.getUserId().getId();
							
							wine.setNumericWineId(numericWineId);
							wine.setNumericUserId(numericUserId);
						}
						
						ObjectMapper objectMapper = new ObjectMapper();
				    	//Set pretty printing of json
				    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			    	
						String arrayToJson = "{ \"FavouriteWines\" : " + objectMapper.writeValueAsString(favouriteWines) + " }";
						response.getWriter().write(arrayToJson);
					}
				} catch (Exception e) { e.printStackTrace(); return; }
				break;
			
			case "getFavouriteWineForUser" :
				try
				{
					System.out.println("Entered into the servlet"); //TODO DELETE
					//Get IDs passed via POST in the format "userId,wineId"
					List<String> IDsList = Arrays.asList(content.trim().split(","));
					
					Integer userId = Integer.parseInt(IDsList.get(0));
					Integer wineId = Integer.parseInt(IDsList.get(1));
					
					tblUserFavouriteWines wine = userFavouriteWineService.getFavouriteWineByUser(userId, wineId);
					System.out.println("Passed the method"); //TODO DELETE
					if(wine == null) { return; }
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
					objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
					
					String wineToJson = objectMapper.writeValueAsString(wine);
					System.out.println("Result: " + wineToJson); //TODO DELETE
					response.getWriter().write(wineToJson);
				} catch (Exception e) { e.printStackTrace(); return; }
				break;
		}
	}

}