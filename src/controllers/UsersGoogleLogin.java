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

import models.tblUsersGoogleLogin;
import services.UsersGoogleLoginService;

/**
 * Servlet implementation class UsersGoogleLogin
 */
@WebServlet("/usersGoogleLogin")
public class UsersGoogleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsersGoogleLoginService userGoogleLoginService = new UsersGoogleLoginService();
    public UsersGoogleLogin() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUsersGoogleLogin" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUsersGoogleLogin> usersGoogleLogin = userGoogleLoginService.getUserGoogleLogins();
					String arrayToJson = objectMapper.writeValueAsString(usersGoogleLogin);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserGoogleLogin" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUsersGoogleLogin userGoogleLogin = userGoogleLoginService.getUserGoogleLoginById(id);
					String arrayToJson = objectMapper.writeValueAsString(userGoogleLogin);
					
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
			case "addUserGoogleLogin" :
			{
				try
				{
					tblUsersGoogleLogin userGoogleLogin = new tblUsersGoogleLogin();
					ObjectMapper mapper = new ObjectMapper();
					userGoogleLogin = mapper.readValue(content, tblUsersGoogleLogin.class);
					
					if(userGoogleLoginService.addUserGoogleLogin(userGoogleLogin)) { response.getWriter().print("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateUserGoogleLogin" :
			{
				try
				{
					tblUsersGoogleLogin userGoogleLogin = new tblUsersGoogleLogin();
					ObjectMapper mapper = new ObjectMapper();
					userGoogleLogin = mapper.readValue(content, tblUsersGoogleLogin.class);
					
					if(userGoogleLoginService.updateUserGoogleLogin(userGoogleLogin)) { response.getWriter().print("True"); }
				} catch (Exception e) {return;}
				break;
			}
		}
	}

}