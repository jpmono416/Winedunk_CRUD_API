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

import models.tblUsersFacebookLogin;
import services.UsersFacebookLoginService;

/**
 * Servlet implementation class UsersFacebookLogin
 */
@WebServlet("/usersFacebookLogin")
public class UsersFacebookLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsersFacebookLoginService userFacebookLoginService = new UsersFacebookLoginService();
    public UsersFacebookLogin() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getParameterMap().containsKey("action")) { return; }
		
		String action = request.getParameter("action");
		switch(action) 
		{
			case "getUsersFacebookLogin" :
			{
				try 
				{ 
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					List<tblUsersFacebookLogin> usersFacebookLogin = userFacebookLoginService.getUserFacebookLogins();
					String arrayToJson = objectMapper.writeValueAsString(usersFacebookLogin);
					
					response.setStatus(200);
					response.getWriter().write(arrayToJson);
				} 
				catch (Exception e) { e.printStackTrace(); }
				break;
			}
			
			case "getUserFacebookLogin" :
			{
				try 
				{
					if(!request.getParameterMap().containsKey("id")) { return; }
					Integer id = Integer.parseInt(request.getParameter("id"));
					
					ObjectMapper objectMapper = new ObjectMapper();
			    	//Set pretty printing of json
			    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		    	
					tblUsersFacebookLogin userFacebookLogin = userFacebookLoginService.getUserFacebookLoginById(id);
					String arrayToJson = objectMapper.writeValueAsString(userFacebookLogin);
					
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
			case "addUserFacebookLogin" :
			{
				try
				{
					tblUsersFacebookLogin userFacebookLogin = new tblUsersFacebookLogin();
					ObjectMapper mapper = new ObjectMapper();
					userFacebookLogin = mapper.readValue(content, tblUsersFacebookLogin.class);
					
					if(userFacebookLoginService.addUserFacebookLogin(userFacebookLogin)) { response.getWriter().println("True"); }
				} catch (Exception e) { e.printStackTrace(); return;}
				break;
			}
			
			case "updateUserFacebookLogin" :
			{
				try
				{
					tblUsersFacebookLogin userFacebookLogin = new tblUsersFacebookLogin();
					ObjectMapper mapper = new ObjectMapper();
					userFacebookLogin = mapper.readValue(content, tblUsersFacebookLogin.class);
					
					if(userFacebookLoginService.updateUserFacebookLogin(userFacebookLogin)) { response.getWriter().println("True"); }
				} catch (Exception e) {return;}
				break;
			}
		}
	}

}