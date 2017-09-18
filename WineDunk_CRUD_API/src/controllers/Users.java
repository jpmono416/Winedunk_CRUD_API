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

import models.tblUsers;
import services.UsersService;
import views.viewUsers;

/**
 * Servlet implementation class Users
 */
@WebServlet("/users")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsersService userService = new UsersService();
    public Users() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUsers" :
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUsers> users = userService.getUsers();
					String arrayToJson = objectMapper.writeValueAsString(users);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getUser" :
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUsers user = userService.getUserById(id);
					String arrayToJson = objectMapper.writeValueAsString(user);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				}
				catch (Exception e) { e.printStackTrace(); }
				break;
			
			case "getAuth" :
				try
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUsers user = userService.getUserById(id);
					String password = user.getLoginPassword();
					response.getWriter().write(password);
				}catch (Exception e) { e.printStackTrace(); }
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
		
			case "addUser" :
				try
				{
					tblUsers user = new tblUsers();
					ObjectMapper mapper = new ObjectMapper();
					user = mapper.readValue(content, tblUsers.class);
					System.out.println("User before persisting:" + user.toString());
					 try 
					 { 
						 Integer userId = userService.addUser(user);
						 response.getWriter().println(userId);
					 } catch (Exception e) {e.printStackTrace(); return;} 
				} catch (Exception e) {e.printStackTrace(); return;}
			break;
			
			case "updateUser" :
				try
				{
					tblUsers user = new tblUsers();
					ObjectMapper mapper = new ObjectMapper();
					user = mapper.readValue(content, tblUsers.class);
					// Edit only for the recover password method
					if(user.getRecoveringPassToken() != null)
					{
						tblUsers previousUser = userService.getUserById(user.getId());
						previousUser.setRecoveringPassToken(user.getRecoveringPassToken());
						if(userService.updateUser(previousUser)) { response.getWriter().println("True"); }
						return;
					}
					
					if(userService.updateUser(user)) { response.getWriter().println("True"); }
				} catch (Exception e) {e.printStackTrace();}
			break;
			
			case "deleteUser" :
				try
				{
					Integer id = Integer.parseInt(content);
					if(userService.deleteUser(id)) { response.getWriter().println("True"); }
				} catch (Exception e) { return; }
			break;
			
			case "getUserByEmail" :
			    try
			    {
					String email = content;
			    	viewUsers user = userService.getUserByEmail(email);
			    	
			    	ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					String arrayToJson = objectMapper.writeValueAsString(user);
					
					response.getWriter().write(arrayToJson);
			    } catch (Exception e) { return; }
			break;
			
			case "getUserByAuth" :
				try
				{
					
					String auth = content;
					viewUsers user = userService.getUserByAuth(auth);
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					String arrayToJson = objectMapper.writeValueAsString(user);
					
					response.getWriter().write(arrayToJson);
					
				} catch (Exception e) { e.printStackTrace(); }
			break;
				
			case "changePassword" :
				try
				{ 
					List<String> splittedContent;					
					splittedContent = new ArrayList<String>(Arrays.asList(content.split(",")));
					
					Integer userId = Integer.parseInt(splittedContent.get(0));
					String password = splittedContent.get(1);
					
					tblUsers user = userService.getUserById(userId);
					user.setLoginPassword(password);
					
					if(userService.updateUser(user)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); }
			break;
			
			case "deleteTokenAndSaveUser" :
				try
				{
					tblUsers user = new tblUsers();
					ObjectMapper mapper = new ObjectMapper();
					user = mapper.readValue(content, tblUsers.class);
					if(user.getRecoveringPassToken() == null) { return; }
					
					tblUsers previousUser = userService.getUserById(user.getId());
					
					previousUser.setLoginPassword(user.getLoginPassword());
					previousUser.setRecoveringPassToken(null);
					if(userService.updateUser(previousUser)) { response.getWriter().write("true"); }
				} catch (Exception e) { e.printStackTrace(); }
			break;
			
			case "updateUserDetails" :
				try
				{
					System.out.println("Content: " + content); //TODO DELETE
					tblUsers userIncoming = new tblUsers();
					ObjectMapper mapper = new ObjectMapper();
					userIncoming = mapper.readValue(content, tblUsers.class);
					System.out.println("User: " + userIncoming.toString()); //TODO DELETE
					tblUsers previousUser = userService.getUserById(userIncoming.getId());
					String userName = userIncoming.getName();
					String preferredEmail = userIncoming.getPreferredEmail();
					String recoveryEmail = userIncoming.getRecoveringPassEmail();
					
					if(userName != null && !userName.equals("")) { previousUser.setName(userName); }
					if(preferredEmail != null && !preferredEmail.equals("")) { previousUser.setPreferredEmail(preferredEmail); }
					if(recoveryEmail != null && !recoveryEmail.equals("")) {  previousUser.setRecoveringPassEmail(recoveryEmail);}
					if(userService.updateUser(previousUser)) { response.getWriter().write("true"); }
				} catch (Exception e) { e.printStackTrace(); }
				break;
		}
	}

}