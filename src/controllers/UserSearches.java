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

import models.tblUserSearches;
import services.UserSearchesService;

/**
 * Servlet implementation class UserSearches
 */
@WebServlet("/userSearches")
public class UserSearches extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserSearchesService userSearchService = new UserSearchesService();
    public UserSearches() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUserSearches" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUserSearches> userSearches = userSearchService.getUserSearches();
					String arrayToJson = objectMapper.writeValueAsString(userSearches);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserSearch" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUserSearches userSearch = userSearchService.getUserSearchById(id);
					String arrayToJson = objectMapper.writeValueAsString(userSearch);
					
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
			case "addUserSearch" :
			{
				try
				{
					tblUserSearches userSearch = new tblUserSearches();
					ObjectMapper mapper = new ObjectMapper();
					userSearch = mapper.readValue(content, tblUserSearches.class);
					
					if(userSearchService.addUserSearch(userSearch)) { response.getWriter().print("True"); }
				} catch (Exception e) {e.printStackTrace(); return;}
				break;
			}
			
			case "updateUserSearch" :
			{
				try
				{
					tblUserSearches userSearch = new tblUserSearches();
					ObjectMapper mapper = new ObjectMapper();
					userSearch = mapper.readValue(content, tblUserSearches.class);
					
					if(userSearchService.updateUserSearch(userSearch)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
			
			case "deleteUserSearch" :
			{
				try
				{
					Integer id = Integer.parseInt(content);
					if(userSearchService.deleteUserSearch(id)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace() ;return; }
				break;
			}
		}
	}

}