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

import models.tblUserSavedSearches;
import models.tblUsers;
import services.UserSavedSearchesService;

/**
 * Servlet implementation class UserSavedSearches
 */
@WebServlet("/userSavedSearches")
public class UserSavedSearches extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserSavedSearchesService userSavedSearchService = new UserSavedSearchesService();
    public UserSavedSearches() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserSavedSearches" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserSavedSearches> userSavedSearches = userSavedSearchService.getUserSavedSearches();
					String arrayToJson = objectMapper.writeValueAsString(userSavedSearches);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserSavedSearch" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserSavedSearches userSavedSearch = userSavedSearchService.getUserSavedSearchById(id);
					String arrayToJson = objectMapper.writeValueAsString(userSavedSearch);
					
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
			case "addUserSavedSearch" :
				try
				{
					tblUserSavedSearches userSavedSearch = new tblUserSavedSearches();
					ObjectMapper mapper = new ObjectMapper();
					userSavedSearch = mapper.readValue(content, tblUserSavedSearches.class);
					// This is added if the request comes from the CRUD
					if(userSavedSearch.getUser() == null)
					{
						tblUsers user = new tblUsers();
						user.setId(userSavedSearch.getNumericUserId());
						userSavedSearch.setUser(user);
					}
					if(userSavedSearchService.addUserSavedSearch(userSavedSearch)) { response.getWriter().print("True"); }
				} catch (Exception e) {e.printStackTrace(); return;}
				break;
			
			case "updateUserSavedSearch" :
				try
				{
					tblUserSavedSearches userSavedSearch = new tblUserSavedSearches();
					ObjectMapper mapper = new ObjectMapper();
					userSavedSearch = mapper.readValue(content, tblUserSavedSearches.class);
					
					if(userSavedSearchService.updateUserSavedSearch(userSavedSearch)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			
			case "deleteUserSavedSearch" :
				try
				{
					Integer id = Integer.parseInt(content);
					if(userSavedSearchService.deleteUserSavedSearch(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace() ;return; }
				break;
			
			case "getUserSavedSearchesForUser" :
				try
				{
					Integer id = Integer.parseInt(content);
					List<tblUserSavedSearches> searches = userSavedSearchService.getSearchesForUser(id);
					
					ObjectMapper mapper = new ObjectMapper();
					String jsonResult = "{ \"SavedSearches\" : " + mapper.writeValueAsString(searches) + " }";
					
					response.getWriter().write(jsonResult);
				} catch(Exception e) { e.printStackTrace(); return; }
			break;
		}
	}

}